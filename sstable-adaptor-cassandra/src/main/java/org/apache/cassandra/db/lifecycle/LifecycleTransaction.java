/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.cassandra.db.lifecycle;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.cassandra.db.Directories;
import org.apache.cassandra.db.compaction.OperationType;
import org.apache.cassandra.io.sstable.format.SSTableReader;
import org.apache.cassandra.io.sstable.format.SSTableReader.UniqueIdentifier;
import org.apache.cassandra.utils.concurrent.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiFunction;

import static com.google.common.base.Predicates.in;
import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Iterables.transform;
import static org.apache.cassandra.db.lifecycle.Helpers.*;
import static org.apache.cassandra.utils.Throwables.maybeFail;
import static org.apache.cassandra.utils.concurrent.Refs.release;
import static org.apache.cassandra.utils.concurrent.Refs.selfRefs;

/**
 * IMPORTANT: When this object is involved in a transactional graph, for correct behaviour its commit MUST occur before
 * any others, since it may legitimately fail. This is consistent with the Transactional API, which permits one failing
 * action to occur at the beginning of the commit phase, but also *requires* that the prepareToCommit() phase only take
 * actions that can be rolled back.
 */
public class LifecycleTransaction extends Transactional.AbstractTransactional
{
    private static final Logger logger = LoggerFactory.getLogger(LifecycleTransaction.class);

    /**
     * A class that represents accumulated modifications to the Tracker.
     * has two instances, one containing modifications that are "staged" (i.e. invisible)
     * and one containing those "logged" that have been made visible through a call to checkpoint()
     */
    private static class State
    {
        // readers that are either brand new, update a previous new reader, or update one of the original readers
        final Set<SSTableReader> update = new HashSet<>();
        // disjoint from update, represents a subset of originals that is no longer needed
        final Set<SSTableReader> obsolete = new HashSet<>();

        void log(State staged)
        {
            update.removeAll(staged.obsolete);
            update.removeAll(staged.update);
            update.addAll(staged.update);
            obsolete.addAll(staged.obsolete);
        }

        boolean contains(SSTableReader reader)
        {
            return update.contains(reader) || obsolete.contains(reader);
        }

        boolean isEmpty()
        {
            return update.isEmpty() && obsolete.isEmpty();
        }

        void clear()
        {
            update.clear();
            obsolete.clear();
        }

        @Override
        public String toString()
        {
            return String.format("[obsolete: %s, update: %s]", obsolete, update);
        }
    }

    // The transaction logs keep track of new and old sstable files
    private final LogTransaction log;
    // the original readers this transaction was opened over, and that it guards
    // (no other transactions may operate over these readers concurrently)
    private final Set<SSTableReader> originals = new HashSet<>();
    // the set of readers we've marked as compacting (only updated on creation and in checkpoint())
    private final Set<SSTableReader> marked = new HashSet<>();
    // the identity set of readers we've ever encountered; used to ensure we don't accidentally revisit the
    // same version of a reader. potentially a dangerous property if there are reference counting bugs
    // as they won't be caught until the transaction's lifespan is over.
    private final Set<UniqueIdentifier> identities = Collections.newSetFromMap(new IdentityHashMap<>());

    // changes that have been made visible
    private final State logged = new State();
    // changes that are pending
    private final State staged = new State();

    // the tidier and their readers, to be used for marking readers obsoleted during a commit
    private List<LogTransaction.Obsoletion> obsoletions;

    /**
     * construct an empty Transaction with no existing readers
     */
    @SuppressWarnings("resource") // log closed during postCleanup
    public static LifecycleTransaction offline(OperationType operationType)
    {
        return new LifecycleTransaction(new LogTransaction(operationType), Collections.emptyList());
    }

    LifecycleTransaction(LogTransaction log, Iterable<SSTableReader> readers)
    {
        this.log = log;
        for (SSTableReader reader : readers)
        {
            originals.add(reader);
            marked.add(reader);
            identities.add(reader.instanceId);
        }
    }

    public LogTransaction log()
    {
        return log;
    }

    public OperationType opType()
    {
        return log.type();
    }

    public UUID opId()
    {
        return log.id();
    }

    public void doPrepare()
    {
        // note for future: in anticompaction two different operations use the same Transaction, and both prepareToCommit()
        // separately: the second prepareToCommit is ignored as a "redundant" transition. since it is only a checkpoint
        // (and these happen anyway) this is fine but if more logic gets inserted here than is performed in a checkpoint,
        // it may break this use case, and care is needed
        //checkpoint();

        // prepare for compaction obsolete readers as long as they were part of the original set
        // since those that are not original are early readers that share the same desc with the finals
        //maybeFail(prepareForObsoletion(filterIn(logged.obsolete, originals), log, obsoletions = new ArrayList<>(), null));
        //log.prepareToCommit();
    }

    /**
     * point of no return: commit all changes, but leave all readers marked as compacting
     */
    public Throwable doCommit(Throwable accumulate)
    {
        assert staged.isEmpty() : "must be no actions introduced between prepareToCommit and a commit";

        if (logger.isTraceEnabled())
            logger.trace("Committing transaction over {} staged: {}, logged: {}", originals, staged, logged);

        // accumulate must be null if we have been used correctly, so fail immediately if it is not
        maybeFail(accumulate);

        // transaction log commit failure means we must abort; safe commit is not possible
        maybeFail(log.commit(null));

        // this is now the point of no return; we cannot safely rollback, so we ignore exceptions until we're done
        // we restore state by obsoleting our obsolete files, releasing our references to them, and updating our size
        // and notification status for the obsolete and new files

        accumulate = markObsolete(obsoletions, accumulate);
        //accumulate = tracker.updateSizeTracking(logged.obsolete, logged.update, accumulate);
        accumulate = release(selfRefs(logged.obsolete), accumulate);
        //accumulate = tracker.notifySSTablesChanged(originals, logged.update, log.type(), accumulate);

        return accumulate;
    }

    /**
     * undo all of the changes made by this transaction, resetting the state to its original form
     */
    public Throwable doAbort(Throwable accumulate)
    {
        if (logger.isTraceEnabled())
            logger.trace("Aborting transaction over {} staged: {}, logged: {}", originals, staged, logged);

        accumulate = abortObsoletion(obsoletions, accumulate);

        if (logged.isEmpty() && staged.isEmpty())
            return log.abort(accumulate);

        // mark obsolete all readers that are not versions of those present in the original set
        Iterable<SSTableReader> obsolete = filterOut(concatUniq(staged.update, logged.update), originals);
        logger.trace("Obsoleting {}", obsolete);

        accumulate = prepareForObsoletion(obsolete, log, obsoletions = new ArrayList<>(), accumulate);
        // it's safe to abort even if committed, see maybeFail in doCommit() above, in this case it will just report
        // a failure to abort, which is useful information to have for debug
        accumulate = log.abort(accumulate);
        accumulate = markObsolete(obsoletions, accumulate);

        // replace all updated readers with a version restored to its original state
        List<SSTableReader> restored = restoreUpdatedOriginals();
        List<SSTableReader> invalid = Lists.newArrayList(Iterables.concat(logged.update, logged.obsolete));
        //accumulate = tracker.apply(updateLiveSet(logged.update, restored), accumulate);
        //accumulate = tracker.notifySSTablesChanged(invalid, restored, OperationType.COMPACTION, accumulate);
        // setReplaced immediately preceding versions that have not been obsoleted
        accumulate = setReplaced(logged.update, accumulate);
        // we have replaced all of logged.update and never made visible staged.update,
        // and the files we have logged as obsolete we clone fresh versions of, so they are no longer needed either
        // any _staged_ obsoletes should either be in staged.update already, and dealt with there,
        // or is still in its original form (so left as is); in either case no extra action is needed
        accumulate = release(selfRefs(concat(staged.update, logged.update, logged.obsolete)), accumulate);

        logged.clear();
        staged.clear();
        return accumulate;
    }

    @Override
    protected Throwable doPostCleanup(Throwable accumulate)
    {
        log.close();
        return accumulate;
    }

    public void permitRedundantTransitions()
    {
        super.permitRedundantTransitions();
    }

    /**
     * returns the currently visible readers managed by this transaction
     */
    public Iterable<SSTableReader> current()
    {
        // i.e., those that are updates that have been logged (made visible),
        // and any original readers that have neither been obsoleted nor updated
        return concat(logged.update, filterOut(originals, logged.update, logged.obsolete));
    }

    /**
     * update the current replacement of any original reader back to its original start
     */
    private List<SSTableReader> restoreUpdatedOriginals()
    {
        Iterable<SSTableReader> torestore = filterIn(originals, logged.update, logged.obsolete);
        return ImmutableList.copyOf(transform(torestore, (reader) -> current(reader).cloneWithRestoredStart(reader.first)));
    }


    /**
     * return the current version of the provided reader, whether or not it is visible or staged;
     * i.e. returns the first version present by testing staged, logged and originals in order.
     */
    public SSTableReader current(SSTableReader reader)
    {
        Set<SSTableReader> container;
        if (staged.contains(reader))
            container = staged.update.contains(reader) ? staged.update : staged.obsolete;
        else if (logged.contains(reader))
            container = logged.update.contains(reader) ? logged.update : logged.obsolete;
        else if (originals.contains(reader))
            container = originals;
        else throw new AssertionError();
        return select(reader, container);
    }

    /**
     * remove the reader from the set we're modifying
     */
    public void cancel(SSTableReader cancel)
    {
        logger.trace("Cancelling {} from transaction", cancel);
        assert originals.contains(cancel) : "may only cancel a reader in the 'original' set: " + cancel + " vs " + originals;
        assert !(staged.contains(cancel) || logged.contains(cancel)) : "may only cancel a reader that has not been updated or obsoleted in this transaction: " + cancel;
        originals.remove(cancel);
        marked.remove(cancel);
        identities.remove(cancel.instanceId);
        //maybeFail(unmarkCompacting(singleton(cancel), null));
    }

    /**
     * remove the readers from the set we're modifying
     */
    public void cancel(Iterable<SSTableReader> cancels)
    {
        for (SSTableReader cancel : cancels)
            cancel(cancel);
    }


    /**
     * check this transaction has never been used
     */
    private void checkUnused()
    {
        assert logged.isEmpty();
        assert staged.isEmpty();
        assert identities.size() == originals.size();
        assert originals.size() == marked.size();
    }

    /**
     * Get the files in the folder specified, provided that the filter returns true.
     * A filter is given each file and its type, and decides which files should be returned
     * and which should be discarded. To classify files into their type, we read transaction
     * log files. Should we fail to read these log files after a few times, we look at onTxnErr
     * to determine what to do.
     *
     * @param folder - the folder to scan
     * @param onTxnErr - how to handle a failure to read a txn log file
     * @param filter - A function that receives each file and its type, it should return true to have the file returned
     * @return - the list of files that were scanned and for which the filter returned true
     */
    public static List<File> getFiles(Path folder, BiFunction<File, Directories.FileType, Boolean> filter, Directories.OnTxnErr onTxnErr)
    {
        return new LogAwareFileLister(folder, filter, onTxnErr).list();
    }

    // a class representing the current state of the reader within this transaction, encoding the actions both logged
    // and pending, and the reader instances that are visible now, and will be after the next checkpoint (with null
    // indicating either obsolescence, or that the reader does not occur in the transaction; which is defined
    // by the corresponding Action)
    @VisibleForTesting
    public static class ReaderState
    {
        public enum Action
        {
            UPDATED, OBSOLETED, NONE;
            public static Action get(boolean updated, boolean obsoleted)
            {
                assert !(updated && obsoleted);
                return updated ? UPDATED : obsoleted ? OBSOLETED : NONE;
            }
        }

        final Action staged;
        final Action logged;
        final SSTableReader nextVisible;
        final SSTableReader currentlyVisible;
        final boolean original;

        public ReaderState(Action logged, Action staged, SSTableReader currentlyVisible, SSTableReader nextVisible, boolean original)
        {
            this.staged = staged;
            this.logged = logged;
            this.currentlyVisible = currentlyVisible;
            this.nextVisible = nextVisible;
            this.original = original;
        }

        public boolean equals(Object that)
        {
            return that instanceof ReaderState && equals((ReaderState) that);
        }

        public boolean equals(ReaderState that)
        {
            return this.staged == that.staged && this.logged == that.logged && this.original == that.original
                && this.currentlyVisible == that.currentlyVisible && this.nextVisible == that.nextVisible;
        }

        public String toString()
        {
            return String.format("[logged=%s staged=%s original=%s]", logged, staged, original);
        }

        public static SSTableReader visible(SSTableReader reader, Predicate<SSTableReader> obsolete, Collection<SSTableReader> ... selectFrom)
        {
            return obsolete.apply(reader) ? null : selectFirst(reader, selectFrom);
        }
    }

    @VisibleForTesting
    public ReaderState state(SSTableReader reader)
    {
        SSTableReader currentlyVisible = ReaderState.visible(reader, in(logged.obsolete), logged.update, originals);
        SSTableReader nextVisible = ReaderState.visible(reader, orIn(staged.obsolete, logged.obsolete), staged.update, logged.update, originals);
        return new ReaderState(ReaderState.Action.get(logged.update.contains(reader), logged.obsolete.contains(reader)),
                               ReaderState.Action.get(staged.update.contains(reader), staged.obsolete.contains(reader)),
                               currentlyVisible, nextVisible, originals.contains(reader)
        );
    }

    public String toString()
    {
        return originals.toString();
    }
}

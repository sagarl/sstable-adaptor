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

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import org.apache.cassandra.config.DatabaseDescriptor;
import org.apache.cassandra.db.ColumnFamilyStore;
import org.apache.cassandra.db.compaction.OperationType;
import org.apache.cassandra.io.sstable.format.SSTableReader;
import org.apache.cassandra.utils.Pair;
import org.apache.cassandra.utils.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.base.Predicates.and;
import static com.google.common.collect.ImmutableSet.copyOf;
import static com.google.common.collect.Iterables.filter;
import static java.util.Collections.singleton;
import static org.apache.cassandra.db.lifecycle.Helpers.abortObsoletion;
import static org.apache.cassandra.db.lifecycle.Helpers.markObsolete;
import static org.apache.cassandra.db.lifecycle.Helpers.notIn;
import static org.apache.cassandra.db.lifecycle.Helpers.prepareForObsoletion;
import static org.apache.cassandra.db.lifecycle.Helpers.setupOnline;
import static org.apache.cassandra.db.lifecycle.View.permitCompacting;
import static org.apache.cassandra.db.lifecycle.View.updateCompacting;
import static org.apache.cassandra.db.lifecycle.View.updateLiveSet;
import static org.apache.cassandra.utils.Throwables.maybeFail;
import static org.apache.cassandra.utils.Throwables.merge;
import static org.apache.cassandra.utils.concurrent.Refs.release;
import static org.apache.cassandra.utils.concurrent.Refs.selfRefs;

/**
 * Tracker tracks live {@link View} of data store for a table.
 */
public class Tracker
{
    private static final Logger logger = LoggerFactory.getLogger(Tracker.class);

    public final ColumnFamilyStore cfstore;
    final AtomicReference<View> view;
    public final boolean loadsstables;

    /**
     * @param loadsstables true to indicate to load SSTables (TODO: remove as this is only accessed from 2i)
     */
    public Tracker(boolean loadsstables)
    {
        this.cfstore = null;
        this.view = new AtomicReference<>();
        this.loadsstables = loadsstables;
    }

    public LifecycleTransaction tryModify(SSTableReader sstable, OperationType operationType)
    {
        return tryModify(singleton(sstable), operationType);
    }

    /**
     * @return a Transaction over the provided sstables if we are able to mark the given @param sstables as compacted, before anyone else
     */
    public LifecycleTransaction tryModify(Iterable<SSTableReader> sstables, OperationType operationType)
    {
        if (Iterables.isEmpty(sstables))
            return new LifecycleTransaction(this, operationType, sstables);
        if (null == apply(permitCompacting(sstables), updateCompacting(emptySet(), sstables)))
            return null;
        return new LifecycleTransaction(this, operationType, sstables);
    }


    // METHODS FOR ATOMICALLY MODIFYING THE VIEW

    Pair<View, View> apply(Function<View, View> function)
    {
        return apply(Predicates.<View>alwaysTrue(), function);
    }

    Throwable apply(Function<View, View> function, Throwable accumulate)
    {
        try
        {
            apply(function);
        }
        catch (Throwable t)
        {
            accumulate = merge(accumulate, t);
        }
        return accumulate;
    }

    /**
     * atomically tests permit against the view and applies function to it, if permit yields true, returning the original;
     * otherwise the method aborts, returning null
     */
    Pair<View, View> apply(Predicate<View> permit, Function<View, View> function)
    {
        while (true)
        {
            View cur = view.get();
            if (!permit.apply(cur))
                return null;
            View updated = function.apply(cur);
            if (view.compareAndSet(cur, updated))
                return Pair.create(cur, updated);
        }
    }

    Throwable updateSizeTracking(Iterable<SSTableReader> oldSSTables, Iterable<SSTableReader> newSSTables, Throwable accumulate)
    {
        if (isDummy())
            return accumulate;

        long add = 0;
        for (SSTableReader sstable : newSSTables)
        {
            if (logger.isTraceEnabled())
                logger.trace("adding {} to list of files tracked for {}.{}", sstable.descriptor, cfstore.keyspace.getName(), cfstore.name);
            try
            {
                add += sstable.bytesOnDisk();
            }
            catch (Throwable t)
            {
                accumulate = merge(accumulate, t);
            }
        }
        long subtract = 0;
        for (SSTableReader sstable : oldSSTables)
        {
            if (logger.isTraceEnabled())
                logger.trace("removing {} from list of files tracked for {}.{}", sstable.descriptor, cfstore.keyspace.getName(), cfstore.name);
            try
            {
                subtract += sstable.bytesOnDisk();
            }
            catch (Throwable t)
            {
                accumulate = merge(accumulate, t);
            }
        }

        //StorageMetrics.load.inc(add - subtract);
        cfstore.metric.liveDiskSpaceUsed.inc(add - subtract);

        // we don't subtract from total until the sstable is deleted, see TransactionLogs.SSTableTidier
        cfstore.metric.totalDiskSpaceUsed.inc(add);
        return accumulate;
    }

    // SETUP / CLEANUP

    public void addInitialSSTables(Iterable<SSTableReader> sstables)
    {
        if (!isDummy())
            setupOnline(sstables);
        apply(updateLiveSet(emptySet(), sstables));
        maybeFail(updateSizeTracking(emptySet(), sstables, null));
        // no notifications or backup necessary
    }


    public Throwable dropSSTablesIfInvalid(Throwable accumulate)
    {
        if (!isDummy() && !cfstore.isValid())
            accumulate = dropSSTables(accumulate);
        return accumulate;
    }

    public Throwable dropSSTables(Throwable accumulate)
    {
        return dropSSTables(Predicates.<SSTableReader>alwaysTrue(), OperationType.UNKNOWN, accumulate);
    }

    /**
     * removes all sstables that are not busy compacting.
     */
    public Throwable dropSSTables(final Predicate<SSTableReader> remove, OperationType operationType, Throwable accumulate)
    {
        try (LogTransaction txnLogs = new LogTransaction(operationType, this))
        {
            Pair<View, View> result = apply(view -> {
                Set<SSTableReader> toremove = copyOf(filter(view.sstables, and(remove, notIn(view.compacting))));
                return updateLiveSet(toremove, emptySet()).apply(view);
            });

            Set<SSTableReader> removed = Sets.difference(result.left.sstables, result.right.sstables);
            assert Iterables.all(removed, remove);

            // It is important that any method accepting/returning a Throwable never throws an exception, and does its best
            // to complete the instructions given to it
            List<LogTransaction.Obsoletion> obsoletions = new ArrayList<>();
            accumulate = prepareForObsoletion(removed, txnLogs, obsoletions, accumulate);
            try
            {
                txnLogs.finish();
                if (!removed.isEmpty())
                {
                    accumulate = markObsolete(obsoletions, accumulate);
                    accumulate = updateSizeTracking(removed, emptySet(), accumulate);
                    accumulate = release(selfRefs(removed), accumulate);
                    // notifySSTablesChanged -> LeveledManifest.promote doesn't like a no-op "promotion"
                    //accumulate = notifySSTablesChanged(removed, Collections.<SSTableReader>emptySet(), txnLogs.type(), accumulate);
                }
            }
            catch (Throwable t)
            {
                accumulate = abortObsoletion(obsoletions, accumulate);
                accumulate = Throwables.merge(accumulate, t);
            }
        }
        catch (Throwable t)
        {
            accumulate = Throwables.merge(accumulate, t);
        }

        return accumulate;
    }

    public void notifyDeleting(SSTableReader deleting)
    {
    }


    public boolean isDummy()
    {
        return cfstore == null || !DatabaseDescriptor.isDaemonInitialized();
    }


    private static Set<SSTableReader> emptySet()
    {
        return Collections.emptySet();
    }

    public View getView()
    {
        return view.get();
    }
}

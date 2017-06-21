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

package org.apache.cassandra.io.sstable;

import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.db.ColumnFamilyStore;
import org.apache.cassandra.db.SerializationHeader;
import org.apache.cassandra.db.compaction.OperationType;
import org.apache.cassandra.db.lifecycle.LifecycleTransaction;
import org.apache.cassandra.db.rows.UnfilteredRowIterator;
import org.apache.cassandra.io.sstable.format.SSTableReader;
import org.apache.cassandra.io.sstable.metadata.MetadataCollector;
import org.apache.cassandra.utils.concurrent.Transactional;

import java.util.Collection;

/**
 * A wrapper for SSTableWriter and LifecycleTransaction to be used when
 * the writer is the only participant in the transaction and therefore
 * it can safely own the transaction.
 */
public class SSTableTxnWriter extends Transactional.AbstractTransactional implements Transactional
{
    private final SSTableMultiWriter writer;

    public SSTableTxnWriter(SSTableMultiWriter writer)
    {
        this.writer = writer;
    }

    public boolean append(UnfilteredRowIterator iterator)
    {
        return writer.append(iterator);
    }

    public String getFilename()
    {
        return writer.getFilename();
    }

    public long getFilePointer()
    {
        return writer.getFilePointer();
    }

    protected Throwable doCommit(Throwable accumulate)
    {
        return writer.commit(accumulate);
    }

    protected Throwable doAbort(Throwable accumulate)
    {
        return writer.abort(accumulate);
    }

    protected void doPrepare()
    {
        writer.prepareToCommit();
        //txn.prepareToCommit();
    }

    @Override
    protected Throwable doPostCleanup(Throwable accumulate)
    {
        //txn.close();
        writer.close();
        return super.doPostCleanup(accumulate);
    }

    public Collection<SSTableReader> finish(boolean openResult)
    {
        writer.setOpenResult(openResult);
        finish();
        return writer.finished();
    }

    @SuppressWarnings("resource") // log and writer closed during doPostCleanup
    public static SSTableTxnWriter create(CFMetaData cfm,
                                          Descriptor descriptor,
                                          long keyCount,
                                          long repairedAt,
                                          int sstableLevel,
                                          SerializationHeader header)
    {
        // if the column family store does not exist, we create a new default SSTableMultiWriter to use:
        LifecycleTransaction txn = LifecycleTransaction.offline(OperationType.WRITE);
        MetadataCollector collector = new MetadataCollector(cfm.comparator).sstableLevel(sstableLevel);
        SSTableMultiWriter writer = SimpleSSTableMultiWriter.create(descriptor, keyCount, repairedAt, cfm, collector, header, txn);
        return new SSTableTxnWriter(writer);
    }

    public static SSTableTxnWriter createWithNoLogging(CFMetaData cfm,
                                                       Descriptor descriptor,
                                                       long keyCount,
                                                       long repairedAt,
                                                       int sstableLevel,
                                                       SerializationHeader header)
    {
        // if the column family store does not exist, we create a new default SSTableMultiWriter to use:
        LifecycleTransaction txn = LifecycleTransaction.offline(OperationType.CLEANUP);
        MetadataCollector collector = new MetadataCollector(cfm.comparator).sstableLevel(sstableLevel);
        SSTableMultiWriter writer = SimpleSSTableMultiWriter.create(descriptor, keyCount, repairedAt, cfm, collector, header, txn);
        return new SSTableTxnWriter(writer);
    }

}

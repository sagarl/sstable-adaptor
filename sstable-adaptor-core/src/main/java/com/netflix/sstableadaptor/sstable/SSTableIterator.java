/*
 * Copyright 2017 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.sstableadaptor.sstable;


import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.db.Columns;
import org.apache.cassandra.db.DecoratedKey;
import org.apache.cassandra.db.DeletionTime;
import org.apache.cassandra.db.EmptyIterators;
import org.apache.cassandra.db.partitions.PurgeFunction;
import org.apache.cassandra.db.partitions.UnfilteredPartitionIterator;
import org.apache.cassandra.db.partitions.UnfilteredPartitionIterators;
import org.apache.cassandra.db.rows.RangeTombstoneMarker;
import org.apache.cassandra.db.rows.Row;
import org.apache.cassandra.db.rows.UnfilteredRowIterator;
import org.apache.cassandra.db.rows.UnfilteredRowIterators;
import org.apache.cassandra.db.transform.Transformation;
import org.apache.cassandra.io.sstable.ISSTableScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Borrow this from Cassandra's code base.
 *
 * Merge multiple iterators over the content of sstable into a "compacted" iterator.
 * <p>
 * On top of the actual merging the source iterators, this class:
 * <ul>
 * <li>purge gc-able tombstones if possible (see PurgeIterator below).</li>
 * </ul>
 *
 */
public class SSTableIterator implements UnfilteredPartitionIterator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SSTableIterator.class);
    private static final long UNFILTERED_TO_UPDATE_PROGRESS = 100;

    private final List<ISSTableScanner> scanners;
    private final int nowInSec;

    private final long totalBytes;
    private long bytesRead;
    private long totalSourceCQLRows;
    private CFMetaData cfMetaData;

    /**
     * counters for merged rows.
     * array index represents (number of merged rows - 1), so index 0 is counter for no merge (1 row),
     * index 1 is counter for 2 rows merged, and so on.
     */
    private final long[] mergeCounters;
    private final UnfilteredPartitionIterator compacted;

    /**
     * We make sure to close mergedIterator in close() and CompactionIterator is itself an AutoCloseable.
     *
     * @param scanners   - a list of sstable scanners
     * @param cfMetaData - CF Metadata for those sstables
     * @param nowInSec   - now in secs
     */
    @SuppressWarnings("resource")
    public SSTableIterator(final List<ISSTableScanner> scanners, final CFMetaData cfMetaData, final int nowInSec) {
        this.cfMetaData = cfMetaData;
        this.scanners = scanners;
        this.nowInSec = nowInSec;
        this.bytesRead = 0;

        long bytes = 0;
        for (ISSTableScanner scanner : scanners) {
            bytes += scanner.getLengthInBytes();
        }

        this.totalBytes = bytes;
        this.mergeCounters = new long[scanners.size()];

        final UnfilteredPartitionIterator merged = scanners.isEmpty()
            ? EmptyIterators.unfilteredPartition(cfMetaData, false)
            : UnfilteredPartitionIterators.merge(scanners, nowInSec, listener());

        // to stop capture of iterator in Purger, which is confusing for debug
        //boolean isForThrift = merged.isForThrift();
        this.compacted = Transformation.apply(merged, new Purger(nowInSec));
    }

    /**
     * Is this a thrift data.
     *
     * @return true/false
     */
    public boolean isForThrift() {
        return false;
    }

    /**
     * Return CF Metadata.
     *
     * @return CFMetaData
     */
    public CFMetaData metadata() {
        return this.cfMetaData;
    }


    private void updateCounterFor(final int rows) {
        assert rows > 0 && rows - 1 < mergeCounters.length;
        mergeCounters[rows - 1] += 1;
    }

    /**
     * Return total merged rows.
     *
     * @return long array
     */
    public long[] getMergedRowCounts() {
        return mergeCounters;
    }

    /**
     * Return total raw rows.
     *
     * @return long
     */
    public long getTotalSourceCQLRows() {
        return totalSourceCQLRows;
    }

    /**
     * Return read bytes.
     * @return long
     */
    public long getBytesRead() {
        return bytesRead;
    }

    private UnfilteredPartitionIterators.MergeListener listener() {
        return new UnfilteredPartitionIterators.MergeListener() {
            public UnfilteredRowIterators.MergeListener
            getRowMergeListener(final DecoratedKey partitionKey,
                                final List<UnfilteredRowIterator> versions) {
                int merged = 0;
                for (UnfilteredRowIterator iter : versions) {
                    if (iter != null) {
                        merged++;
                    }
                }

                assert merged > 0;

                SSTableIterator.this.updateCounterFor(merged);

                Columns statics = Columns.NONE;
                Columns regulars = Columns.NONE;
                for (UnfilteredRowIterator iter : versions) {
                    if (iter != null) {
                        statics = statics.mergeTo(iter.columns().statics);
                        regulars = regulars.mergeTo(iter.columns().regulars);
                    }
                }

                //final PartitionColumns partitionColumns = new PartitionColumns(statics, regulars);

                return new UnfilteredRowIterators.MergeListener() {
                    public void onMergedPartitionLevelDeletion(final DeletionTime mergedDeletion,
                                                               final DeletionTime[] versions) {
                    }

                    public void onMergedRows(final Row merged, final Row[] versions) {

                    }

                    public void onMergedRangeTombstoneMarkers(final RangeTombstoneMarker mergedMarker,
                                                              final RangeTombstoneMarker[] versions) {
                    }

                    public void close() {
                    }
                };
            }

            public void close() {
            }
        };
    }

    private void updateBytesRead() {
        long n = 0;
        for (ISSTableScanner scanner : scanners) {
            n += scanner.getCurrentPosition();
        }
        bytesRead = n;
    }

    /**
     * Have a next item.
     *
     * @return true/false
     */
    public boolean hasNext() {
        return compacted.hasNext();
    }

    /**
     * Return next item.
     *
     * @return UnfilteredRowIterator
     */
    public UnfilteredRowIterator next() {
        return compacted.next();
    }

    /**
     * Unsupported.
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * close the underneath iterator.
     */
    public void close() {
        try {
            compacted.close();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
    }

    private final class Purger extends PurgeFunction {
        private DecoratedKey currentKey;
        private long maxPurgeableTimestamp;
        private boolean hasCalculatedMaxPurgeableTimestamp;

        private long compactedUnfiltered;

        private Purger(final int nowInSec) {
            super(false, nowInSec, nowInSec, Integer.MAX_VALUE, true);
        }

        @Override
        protected void onEmptyPartitionPostPurge(final DecoratedKey key) {
        }

        @Override
        protected void onNewPartition(final DecoratedKey key) {
            currentKey = key;
            hasCalculatedMaxPurgeableTimestamp = false;
        }

        @Override
        protected void updateProgress() {
            totalSourceCQLRows++;
            if ((++compactedUnfiltered) % UNFILTERED_TO_UPDATE_PROGRESS == 0) {
                updateBytesRead();
            }
        }

        /**
         * Tombstones with a localDeletionTime before this can be purged. This is the minimum timestamp for any sstable
         * containing `currentKey` outside of the set of sstables involved in this compaction. This is computed lazily
         * on demand as we only need this if there is tombstones and this a bit expensive (see #8914).
         */
        protected long getMaxPurgeableTimestamp() {
            if (!hasCalculatedMaxPurgeableTimestamp) {
                hasCalculatedMaxPurgeableTimestamp = true;
                maxPurgeableTimestamp = Long.MAX_VALUE; //Todo: will make it usable
            }
            return maxPurgeableTimestamp;
        }
    }

}

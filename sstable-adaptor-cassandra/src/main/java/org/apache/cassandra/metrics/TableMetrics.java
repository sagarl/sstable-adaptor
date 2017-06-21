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
package org.apache.cassandra.metrics;

import com.codahale.metrics.*;
import com.google.common.collect.Maps;
import org.apache.cassandra.db.ColumnFamilyStore;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Metrics for {@link ColumnFamilyStore}.
 */
public class TableMetrics
{

    public static final long[] EMPTY = new long[0];

    /** Total amount of data stored in the memtable that resides on-heap, including column related overhead and partitions overwritten. */
    public  Gauge<Long> memtableOnHeapSize;
    /** Total amount of data stored in the memtable that resides off-heap, including column related overhead and partitions overwritten. */
    public  Gauge<Long> memtableOffHeapSize;
    /** Total amount of live data stored in the memtable, excluding any data structure overhead */
    public  Gauge<Long> memtableLiveDataSize;
    /** Total amount of data stored in the memtables (2i and pending flush memtables included) that resides on-heap. */
    public  Gauge<Long> allMemtablesOnHeapSize;
    /** Total amount of data stored in the memtables (2i and pending flush memtables included) that resides off-heap. */
    public  Gauge<Long> allMemtablesOffHeapSize;
    /** Total amount of live data stored in the memtables (2i and pending flush memtables included) that resides off-heap, excluding any data structure overhead */
    public  Gauge<Long> allMemtablesLiveDataSize;
    /** Total number of columns present in the memtable. */
    public  Gauge<Long> memtableColumnsCount;
    /** Number of times flush has resulted in the memtable being switched out. */
    public  Counter memtableSwitchCount;
    /** Current compression ratio for all SSTables */
    public  Gauge<Double> compressionRatio;
    /** Histogram of estimated partition size (in bytes). */
    public  Gauge<long[]> estimatedPartitionSizeHistogram;
    /** Approximate number of keys in table. */
    public  Gauge<Long> estimatedPartitionCount;
    /** Histogram of estimated number of columns. */
    public  Gauge<long[]> estimatedColumnCountHistogram;
    /** Histogram of the number of sstable data files accessed per read */
    public  TableHistogram sstablesPerReadHistogram;

    /** Estimated number of tasks pending for this table */
    public  Counter pendingFlushes;
    /** Total number of bytes flushed since server [re]start */
    public  Counter bytesFlushed;
    /** Total number of bytes written by compaction since server [re]start */
    public  Counter compactionBytesWritten;
    /** Estimate of number of pending compactios for this table */
    public  Gauge<Integer> pendingCompactions;
    /** Number of SSTables on disk for this CF */
    public  Gauge<Integer> liveSSTableCount;
    /** Disk space used by SSTables belonging to this table */
    public  Counter liveDiskSpaceUsed;
    /** Total disk space used by SSTables belonging to this table, including obsolete ones waiting to be GC'd */
    public  Counter totalDiskSpaceUsed;
    /** Size of the smallest compacted partition */
    public  Gauge<Long> minPartitionSize;
    /** Size of the largest compacted partition */
    public  Gauge<Long> maxPartitionSize;
    /** Size of the smallest compacted partition */
    public  Gauge<Long> meanPartitionSize;
    /** Number of false positives in bloom filter */
    public  Gauge<Long> bloomFilterFalsePositives;
    /** Number of false positives in bloom filter from last read */
    public  Gauge<Long> recentBloomFilterFalsePositives;
    /** False positive ratio of bloom filter */
    public  Gauge<Double> bloomFilterFalseRatio;
    /** False positive ratio of bloom filter from last read */
    public  Gauge<Double> recentBloomFilterFalseRatio;
    /** Disk space used by bloom filter */
    public  Gauge<Long> bloomFilterDiskSpaceUsed;
    /** Off heap memory used by bloom filter */
    public  Gauge<Long> bloomFilterOffHeapMemoryUsed;
    /** Off heap memory used by index summary */
    public  Gauge<Long> indexSummaryOffHeapMemoryUsed;
    /** Off heap memory used by compression meta data*/
    public  Gauge<Long> compressionMetadataOffHeapMemoryUsed;
    /** Key cache hit rate  for this CF */
    public  Gauge<Double> keyCacheHitRate;
    /** Tombstones scanned in queries on this CF */
    public  TableHistogram tombstoneScannedHistogram;
    /** Live cells scanned in queries on this CF */
    public  TableHistogram liveScannedHistogram;
    /** Column update time delta on this CF */
    public  TableHistogram colUpdateTimeDeltaHistogram;
    /** time taken acquiring the partition lock for materialized view updates for this table */
    public  TableTimer viewLockAcquireTime;
    /** time taken during the local read of a materialized view update */
    public  TableTimer viewReadTime;
    /** Disk space used by snapshot files which */
    public  Gauge<Long> trueSnapshotsSize;
    /** Row cache hits, but result out of range */
    public  Counter rowCacheHitOutOfRange;
    /** Number of row cache hits */
    public  Counter rowCacheHit;
    /** Number of row cache misses */
    public  Counter rowCacheMiss;


    public  Timer coordinatorReadLatency;

    private static  MetricNameFactory globalFactory = new AllTableMetricNameFactory("Table");
    private static  MetricNameFactory globalAliasFactory = new AllTableMetricNameFactory("ColumnFamily");

    public  Counter speculativeRetries;

    /**
     * stores metrics that will be rolled into a single global metric
     */
    public final static ConcurrentMap<String, Set<Metric>> allTableMetrics = Maps.newConcurrentMap();

    /**
     * Stores all metric names created that can be used when unregistering, optionally mapped to an alias name.
     */
    public final static Map<String, String> all = Maps.newHashMap();


    /**
     * Creates metrics for given {@link ColumnFamilyStore}.
     *
     * @param cfs ColumnFamilyStore to measure metrics
     */
    public TableMetrics(final ColumnFamilyStore cfs)
    {

    }

    /**
     * Release all associated metrics.
     */
    public void release() {
    }

    public static class TableHistogram
    {
        public final Histogram[] all;
        public final Histogram cf;
        private TableHistogram(Histogram cf, Histogram keyspace, Histogram global)
        {
            this.cf = cf;
            this.all = new Histogram[]{cf, keyspace, global};
        }

        public void update(long i)
        {
            for(Histogram histo : all)
            {
                histo.update(i);
            }
        }
    }

    public static class TableTimer
    {
        public final Timer[] all;
        public final Timer cf;
        private TableTimer(Timer cf, Timer keyspace, Timer global)
        {
            this.cf = cf;
            this.all = new Timer[]{cf, keyspace, global};
        }

        public void update(long i, TimeUnit unit)
        {
            for(Timer timer : all)
            {
                timer.update(i, unit);
            }
        }
    }


    static class AllTableMetricNameFactory implements MetricNameFactory
    {
        private final String type;
        public AllTableMetricNameFactory(String type)
        {
            this.type = type;
        }

        public CassandraMetricsRegistry.MetricName createMetricName(String metricName)
        {
            String groupName = TableMetrics.class.getPackage().getName();
            StringBuilder mbeanName = new StringBuilder();
            mbeanName.append(groupName).append(":");
            mbeanName.append("type=").append(type);
            mbeanName.append(",name=").append(metricName);
            return new CassandraMetricsRegistry.MetricName(groupName, type, metricName, "all", mbeanName.toString());
        }
    }

}

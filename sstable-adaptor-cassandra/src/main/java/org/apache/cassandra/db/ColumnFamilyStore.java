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
package org.apache.cassandra.db;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Throwables;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.db.lifecycle.LifecycleTransaction;
import org.apache.cassandra.db.lifecycle.SSTableSet;
import org.apache.cassandra.dht.IPartitioner;
import org.apache.cassandra.dht.Range;
import org.apache.cassandra.dht.Token;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.cassandra.io.sstable.Descriptor;
import org.apache.cassandra.io.sstable.SSTableMultiWriter;
import org.apache.cassandra.io.sstable.format.SSTableReader;
import org.apache.cassandra.io.sstable.metadata.MetadataCollector;
import org.apache.cassandra.metrics.TableMetrics;
import org.apache.cassandra.schema.CompressionParams;
import org.apache.cassandra.utils.FBUtilities;
import org.apache.cassandra.utils.concurrent.OpOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.openmbean.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ColumnFamilyStore implements ColumnFamilyStoreMBean
{
    // The directories which will be searched for sstables on cfs instantiation.
    private static volatile Directories.DataDirectory[] initialDirectories = Directories.dataDirectories;


    public static Directories.DataDirectory[] getInitialDirectories()
    {
        Directories.DataDirectory[] src = initialDirectories;
        return Arrays.copyOf(src, src.length);
    }

    private static final Logger logger = LoggerFactory.getLogger(ColumnFamilyStore.class);

    private static final String[] COUNTER_NAMES = new String[]{"raw", "count", "error", "string"};
    private static final String[] COUNTER_DESCS = new String[]
    { "partition key in raw hex bytes",
      "value of this partition for given sampler",
      "value is within the error bounds plus or minus of this",
      "the partition key turned into a human readable format" };
    private static final CompositeType COUNTER_COMPOSITE_TYPE;
    private static final TabularType COUNTER_TYPE;

    private static final String[] SAMPLER_NAMES = new String[]{"cardinality", "partitions"};
    private static final String[] SAMPLER_DESCS = new String[]
    { "cardinality of partitions",
      "list of counter results" };

    private static final String SAMPLING_RESULTS_NAME = "SAMPLING_RESULTS";
    private static final CompositeType SAMPLING_RESULT;

    public static final String SNAPSHOT_TRUNCATE_PREFIX = "truncated";
    public static final String SNAPSHOT_DROP_PREFIX = "dropped";

    static
    {
        try
        {
            OpenType<?>[] counterTypes = new OpenType[] { SimpleType.STRING, SimpleType.LONG, SimpleType.LONG, SimpleType.STRING };
            COUNTER_COMPOSITE_TYPE = new CompositeType(SAMPLING_RESULTS_NAME, SAMPLING_RESULTS_NAME, COUNTER_NAMES, COUNTER_DESCS, counterTypes);
            COUNTER_TYPE = new TabularType(SAMPLING_RESULTS_NAME, SAMPLING_RESULTS_NAME, COUNTER_COMPOSITE_TYPE, COUNTER_NAMES);

            OpenType<?>[] samplerTypes = new OpenType[] { SimpleType.LONG, COUNTER_TYPE };
            SAMPLING_RESULT = new CompositeType(SAMPLING_RESULTS_NAME, SAMPLING_RESULTS_NAME, SAMPLER_NAMES, SAMPLER_DESCS, samplerTypes);
        } catch (OpenDataException e)
        {
            throw Throwables.propagate(e);
        }
    }

    public  Keyspace keyspace;
    public  String name;
    public  CFMetaData metadata;

    private volatile boolean valid = true;


    /* The read order, used to track accesses to off-heap memtable storage */
    public final OpOrder readOrdering = new OpOrder();

    private volatile Directories directories;

    public  TableMetrics metric;


    private volatile boolean compactionSpaceCheck = true;


    public void setCompactionParametersJson(String options)
    {
        setCompactionParameters(FBUtilities.fromJsonMap(options));
    }

    public String getCompactionParametersJson()
    {
        return FBUtilities.json(getCompactionParameters());
    }

    public void setCompactionParameters(Map<String, String> options)
    {

    }

    public Map<String, String> getCompactionParameters()
    {
        return null;
    }

    public Map<String,String> getCompressionParameters()
    {
        return metadata.params.compression.asMap();
    }

    public void setCompressionParameters(Map<String,String> opts)
    {
        try
        {
            metadata.compression(CompressionParams.fromMap(opts));
            metadata.params.compression.validate();
        }
        catch (ConfigurationException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @VisibleForTesting
    public ColumnFamilyStore(Keyspace keyspace,
                              String columnFamilyName,
                              int generation,
                              CFMetaData metadata,
                              Directories directories,
                              boolean loadSSTables,
                              boolean registerBookeeping,
                              boolean offline)
    {

    }

    public Directories getDirectories()
    {
        return directories;
    }

    public SSTableMultiWriter createSSTableMultiWriter(Descriptor descriptor, long keyCount, long repairedAt, int sstableLevel, SerializationHeader header, LifecycleTransaction txn)
    {
        MetadataCollector collector = new MetadataCollector(metadata.comparator).sstableLevel(sstableLevel);
        return createSSTableMultiWriter(descriptor, keyCount, repairedAt, collector, header, txn);
    }

    public SSTableMultiWriter createSSTableMultiWriter(Descriptor descriptor, long keyCount, long repairedAt, MetadataCollector metadataCollector, SerializationHeader header, LifecycleTransaction txn) {
        return null;
    }

    /**
     * #{@inheritDoc}
     */
    public synchronized void loadNewSSTables()
    {

    }


    @Deprecated
    public String getColumnFamilyName()
    {
        return getTableName();
    }

    public String getTableName()
    {
        return name;
    }

    /**
     * Finds the largest memtable, as a percentage of *either* on- or off-heap memory limits, and immediately
     * queues it for flushing. If the memtable selected is flushed before this completes, no work is done.
     */
    public static class FlushLargestColumnFamily implements Runnable
    {
        public void run()
        {

        }
    }

    public boolean isValid()
    {
        return valid;
    }



    public Iterable<SSTableReader> getSSTables(SSTableSet sstableSet)
    {
        return null;
    }


    // WARNING: this returns the set of LIVE sstables only, which may be only partially written
    public List<String> getSSTablesForKey(String key)
    {
        return getSSTablesForKey(key, false);
    }

    public List<String> getSSTablesForKey(String key, boolean hexFormat)
    {
        return null;
    }

    public CompositeData finishLocalSampling(String sampler, int count) throws OpenDataException
    {
        return null;
    }

    public boolean isCompactionDiskSpaceCheckEnabled()
    {
        return compactionSpaceCheck;
    }

    public void compactionDiskSpaceCheck(boolean enable)
    {
        compactionSpaceCheck = enable;
    }


    public ClusteringComparator getComparator()
    {
        return metadata.comparator;
    }

    public void forceMajorCompaction(boolean splitOutput) throws InterruptedException, ExecutionException
    {

    }

    public void forceCompactionForTokenRange(Collection<Range<Token>> tokenRanges) throws ExecutionException, InterruptedException
    {

    }


    @Override
    public String toString()
    {
        return "CFS(" +
               "Keyspace='" + keyspace.getName() + '\'' +
               ", ColumnFamily='" + name + '\'' +
               ')';
    }


    public void setCrcCheckChance(double crcCheckChance)
    {

    }

    @Override
    public boolean isAutoCompactionDisabled() {
        return false;
    }


    public Double getCrcCheckChance()
    {
        return 1.1;
    }

    public void setCompactionThresholds(int minThreshold, int maxThreshold)
    {

    }

    public int getMinimumCompactionThreshold()
    {
        return 1;
    }

    public void setMinimumCompactionThreshold(int minCompactionThreshold)
    {

    }

    public int getMaximumCompactionThreshold()
    {
        return 1;
    }

    public void setMaximumCompactionThreshold(int maxCompactionThreshold)
    {

    }

    // End JMX get/set.

    public int getMeanColumns()
    {
        return 1;
    }

    public long estimateKeys()
    {
        long n = 0;
        for (SSTableReader sstable : getSSTables(SSTableSet.CANONICAL))
            n += sstable.estimatedKeys();
        return n;
    }

    @Override
    public List<String> getBuiltIndexes() {
        return null;
    }

    public IPartitioner getPartitioner()
    {
        return metadata.partitioner;
    }

    public DecoratedKey decorateKey(ByteBuffer key)
    {
        return metadata.decorateKey(key);
    }

    /** true if this CFS contains secondary index data */
    public boolean isIndex()
    {
        return metadata.isIndex();
    }

    public int getUnleveledSSTables()
    {
        return 1;
    }

    public int[] getSSTableCountPerLevel()
    {
        return null;
    }

    public int getLevelFanoutSize()
    {
        return 1;
    }

    public boolean isEmpty()
    {
        return true;
    }

    public double getDroppableTombstoneRatio()
    {
        return 0;
    }

    @Override
    public long trueSnapshotsSize() {
        return 0;
    }

}

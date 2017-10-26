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

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.sstableadaptor.config.CassandraTable;
import com.netflix.sstableadaptor.util.SSTableUtils;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.db.DecoratedKey;
import org.apache.cassandra.dht.IPartitioner;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.dht.RandomPartitioner;
import org.apache.cassandra.dht.Range;
import org.apache.cassandra.dht.Token;
import org.apache.cassandra.io.sstable.Descriptor;
import org.apache.cassandra.io.sstable.ISSTableScanner;
import org.apache.cassandra.io.sstable.IndexSummary;
import org.apache.cassandra.io.sstable.format.SSTableReader;
import org.apache.cassandra.io.sstable.metadata.StatsMetadata;
import org.apache.cassandra.io.util.HadoopFileUtils;
import org.apache.cassandra.utils.EstimatedHistogram;
import org.apache.hadoop.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *  This is the SSTable Adaptor's reader that can help to
 *  build iterator of rows out of a sstable file.
 *
 *  @author mdo
 */
public class SSTableSingleReader {
    private static final Logger LOGGER  = LoggerFactory.getLogger(SSTableSingleReader.class);
    private String fileLocation;
    private long fileLength;
    private int version;  //sstable version according to C*
    private int generation; //C* generation number
    private Comparator clusteringComparator; //clusterkey comparator;
    private Comparator<ByteBuffer> keyValidator;
    private DecoratedKey first;
    private DecoratedKey last;
    private long totalRows;

    private Descriptor descriptor; //C* Descriptor object
    private IPartitioner partitioner; //C* partitioner
    private EstimatedHistogram estimatedPartitionSize;  //histogram on the partition size?
    private StatsMetadata stats;  //other table stats
    private org.apache.cassandra.io.sstable.format.SSTableReader sstableReader;
    private IndexSummary indexSummary;

    private CFMetaData cfMetaData;
    private Configuration conf;

    /**
     *  Constructing a reader instance to take in a location for the file and set the
     *  rest by the info found in the file and file path
     *  @param filePath location of the sstable file
     *  @throws IOException when file location is not valid
     */
    public SSTableSingleReader(final String filePath, Configuration configuration) throws IOException {
        this(filePath, Collections.emptyList(), Collections.emptyList(), configuration);
    }

    public SSTableSingleReader(final String filePath, CFMetaData cfMetaData, Configuration configuration)
            throws IOException {
        this.fileLocation = filePath;
        this.conf = configuration;
        initialization(cfMetaData);
    }


    /**
     *  Constructing a reader instance to take in a location for the file and set the
     *  rest by the info found in the file and file path
     *  @param filePath location of the sstable file
     *  @throws IOException when file location is not valid
     */
    public SSTableSingleReader(final String filePath, CassandraTable cassandraTable, Configuration configuration)
            throws IOException {
        this(filePath, cassandraTable.getKeyspaceName(),
                cassandraTable.getTableName(),
                cassandraTable.getPartitionKeyNames(),
                cassandraTable.getClusteringKeyNames(),
                configuration);
    }

    /**
     *  Constructing a reader instance to take in a location for the file, keyspace and table names.
     *  @param filePath location of the sstable file
     *  @throws IOException when file location is not valid
     */
    public SSTableSingleReader(final String filePath, String keyspaceName,
                               String tableName, Configuration configuration)
            throws IOException {
        this(filePath, keyspaceName, tableName, Collections.<String>emptyList(),
                Collections.<String>emptyList(), configuration);
    }


    /**
     *  Constructing a reader instance to take in additional list of partition key names
     *  and clustering key names.
     *  @param filePath location of the sstable file
     *  @param partitionKeyNames list of partition key names
     *  @param clusteringKeyNames list of clustering key names
     *  @throws IOException when file location is not valid
     */
    public SSTableSingleReader(final String filePath,
                               final List<String> partitionKeyNames,
                               final List<String> clusteringKeyNames,
                               Configuration configuration) throws IOException {
        this(filePath, "", "", partitionKeyNames, clusteringKeyNames, configuration);
    }

    /**
     *  Constructing a reader instance to take in additional list of partition key names
     *  and clustering key names and overrided the keyspace and table name
     *  with the supplied parameters instead of pulling them out from the file path.
     *  @param filePath location of the sstable file
     *  @param keyspaceName keyspace name
     *  @param tableName table name
     *  @param partitionKeyNames list of partition key names
     *  @param clustringKeyNames list of clustering key names
     *  @throws IOException when file location is not valid
     */
    public SSTableSingleReader(final String filePath,
                               final String keyspaceName,
                               final String tableName,
                               final List<String> partitionKeyNames,
                               final List<String> clustringKeyNames,
                               Configuration configuration) throws IOException {
        this.fileLocation = filePath;
        this.conf = configuration;
        initialization(keyspaceName, tableName, partitionKeyNames, clustringKeyNames);
    }

    /**
     * Initialization with already defined CFMetaData.
     * @param cfMetaData CFMetaData to represent the table schema
     * @throws IOException when file location is not valid
     */
    private void initialization(final CFMetaData cfMetaData) throws IOException {
        descriptor = Descriptor.fromFilename(HadoopFileUtils.normalizeFileName(fileLocation), this.conf);
        this.cfMetaData = cfMetaData;
        initHelper();
    }

    /**
     * Initialization with provided keyspace, table, and key names.
     * @param keyspaceName keyspace name
     * @param tableName table name
     * @param partitionKeyNames list of partition key names
     * @param clusteringKeyNames list of clustering key names
     * @throws IOException when file location is not valid
     */
    private void initialization(final String keyspaceName,
                                final String tableName,
                                final List<String> partitionKeyNames,
                                final List<String> clusteringKeyNames) throws IOException {
        descriptor = Descriptor.fromFilename(HadoopFileUtils.normalizeFileName(fileLocation), conf);
        cfMetaData = SSTableUtils.metadataFromSSTable(descriptor, keyspaceName, tableName, partitionKeyNames, clusteringKeyNames);
        initHelper();
    }

    private void initHelper() throws IOException {
        sstableReader = SSTableReader.openNoValidation(descriptor, cfMetaData);
        fileLength = sstableReader.onDiskLength();
        version = descriptor.version.correspondingMessagingVersion();
        generation = descriptor.generation;
        keyValidator = cfMetaData.getKeyValidator();
        clusteringComparator = cfMetaData.getKeyValidatorAsClusteringComparator();
        first = sstableReader.first;
        last = sstableReader.last;
        totalRows = sstableReader.getTotalRows();
        partitioner = cfMetaData.partitioner;
        stats = sstableReader.getSSTableMetadata();
        estimatedPartitionSize = sstableReader.getEstimatedPartitionSize();
        indexSummary = sstableReader.getIndexSummary();
    }

    /**
     * Return the Descriptor associated to this sstable
     * @return Descriptor
     */
    public Descriptor getDescriptor() {
        return descriptor;
    }

    /**
     * Obtain CFMetaData.
     * @return CFMetaData instance to describe the underneath sstable file
     */
    public CFMetaData getCfMetaData() {
        return this.cfMetaData;
    }

    /**
     * Return SSTableSingleReader that represents the underneath sstable file.
     *
     * @return IPartitioner
     */
    public org.apache.cassandra.io.sstable.format.SSTableReader getSstableReader() {
        return this.sstableReader;
    }

    /**
     * Return partitioner that is used to compare keys on a sstable file.
     *
     * @return IPartitioner
     */
    public IPartitioner getPartitioner() {
        return this.partitioner;
    }

    /**
     * Build the IndexSummary from a sstable file.
     *
     * @return IndexSummary
     */
    public IndexSummary getIndexSummary() {
        return indexSummary;
    }

    /**
     * Build a SSTable scanner on the entire sstable file.
     *
     * @return ISSTableScanner
     */
    public ISSTableScanner getSSTableScanner() {
        return this.sstableReader.getScanner();
    }

    /**
     * Build a SSTable scanner on the entire sstable file.
     *
     * @param limiter rate limiter to control the rate of reading the underneath SSTable file
     * @return ISSTableScanner
     */
    public ISSTableScanner getSSTableScanner(final RateLimiter limiter) {
        return this.sstableReader.getScanner(limiter);
    }

    /**
     * Build a SSTable scanner.
     *
     * @param range  token range
     * @return ISSTableScanner
     */
    public ISSTableScanner getSSTableScanner(final Range<Token> range) {
        return this.sstableReader.getScanner(range, null);
    }

    /**
     * Build a SSTable scanner.
     *
     * @param range  token range
     * @param limiter rate limiter to control the rate of reading the underneath SSTable file
     * @return ISSTableScanner
     */
    public ISSTableScanner getSSTableScanner(final Range<Token> range, final RateLimiter limiter) {
        return this.sstableReader.getScanner(range, limiter);
    }

    /**
     * Build a SSTable scanner.
     *
     * @param left  start token of the range
     * @param right end token
     * @return ISSTableScanner
     */
    public ISSTableScanner getSSTableScanner(final Token left, final Token right) {
        return this.sstableReader.getScanner(new Range<Token>(left, right), null);
    }

    /**
     * Build a SSTable scanner.
     *
     * @param left  start token of the range
     * @param right end token
     * @param limiter rate limiter to scan
     * @return ISSTableScanner
     */
    public ISSTableScanner getSSTableScanner(final Token left, final Token right, final RateLimiter limiter) {
        return this.sstableReader.getScanner(new Range<Token>(left, right), limiter);
    }

    /**
     * Build a SSTable scanner.
     *
     * @param start start token
     * @param end end token
     * @return ISSTableScanner
     * @throws IllegalArgumentException when partitioner is not Murmur3Partitioner
     */
    public ISSTableScanner getSSTableScanner(final long start, final long end) {
        return getSSTableScanner(start, end, null);
    }

    /**
     * Build a SSTable scanner.
     *
     * @param start start token
     * @param end end token
     * @param limiter rate limiter to control the rate of reading the underneath SSTable file
     * @return ISSTableScanner
     * @throws IllegalArgumentException when partitioner is not Murmur3Partitioner
     */
    public ISSTableScanner getSSTableScanner(final long start, final long end, final RateLimiter limiter) {
        if (this.getPartitioner() instanceof Murmur3Partitioner) {
            return getSSTableScanner(new Range<Token>(new Murmur3Partitioner.LongToken(start),
                new Murmur3Partitioner.LongToken(end)), limiter);
        } else {
            throw new IllegalArgumentException(this.getPartitioner().getClass() + " is not supported by this method!");
        }
    }

    /**
     * Build a SSTable scanner.
     *
     * @param start start token
     * @param end end token
     * @param limiter rate limiter to control the rate of reading the underneath SSTable file
     * @return ISSTableScanner
     * @throws IllegalArgumentException when partitioner is not RandomPartitioner
     */
    public ISSTableScanner getSSTableScanner(final BigInteger start, final BigInteger end, final RateLimiter limiter) {
        if (this.getPartitioner() instanceof RandomPartitioner) {
            return getSSTableScanner(new Range<Token>(new RandomPartitioner.BigIntegerToken(start),
                new RandomPartitioner.BigIntegerToken(end)), limiter);
        } else {
            throw new IllegalArgumentException(this.getPartitioner().getClass() + " is not supported by this method!");
        }
    }

    /**
     * Close resources.
     */
    public void close() {
        LOGGER.info("Closing down!!! Calling SSTableReader.close()");
        //this.sstableReader.selfRef().close();
        sstableReader.close();
    }

   /**
    * Obtain file length.
    * @return  file size of the sstable file
    */
    public long getFileLength() {
        return fileLength;
    }

    /**
     *  Obtain sstable file location.
     *  @return file location of the sstable file
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     *  Obtain EstimatedHistogram.
     *  @return EstimatedHistogram of the sstable file
     */
    public EstimatedHistogram getEstimatedPartitionSize() {
        return estimatedPartitionSize;
    }

    /**
     * Obtain an estimated number of total rows.
     *  @return estimated total rows of the sstable file
     */
    public long getTotalRows() {
        return totalRows;
    }

    /**
     *  Obtain StatsMetadata which has various statistics about the sstable file.
     *  @return StatsMetadata of the sstable file
     */
    public StatsMetadata getStats() {
        return stats;
    }

    /**
     *  Obtain first key.
     *  @return first key of the sstable file
     */
    public DecoratedKey getFirstKey() {
        return first;
    }

    /**
     *  Obtain last key.
     *  @return last key of the sstable file
     */
    public DecoratedKey getLastKey() {
        return last;
    }
}

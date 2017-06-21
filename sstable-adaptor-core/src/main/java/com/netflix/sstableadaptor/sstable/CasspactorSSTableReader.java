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
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.cql3.ColumnIdentifier;
import org.apache.cassandra.db.DecoratedKey;
import org.apache.cassandra.db.SerializationHeader;
import org.apache.cassandra.db.marshal.AbstractType;
import org.apache.cassandra.db.marshal.CompositeType;
import org.apache.cassandra.db.marshal.UTF8Type;
import org.apache.cassandra.dht.*;
import org.apache.cassandra.io.sstable.Descriptor;
import org.apache.cassandra.io.sstable.ISSTableScanner;
import org.apache.cassandra.io.sstable.IndexSummary;
import org.apache.cassandra.io.sstable.format.SSTableReader;
import org.apache.cassandra.io.sstable.metadata.MetadataComponent;
import org.apache.cassandra.io.sstable.metadata.MetadataType;
import org.apache.cassandra.io.sstable.metadata.StatsMetadata;
import org.apache.cassandra.utils.EstimatedHistogram;
import org.apache.cassandra.utils.FBUtilities;
import org.apache.directory.api.util.Strings;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.*;

/**
 *  This is the Casspactor's SSTable reader that can help to
 *  build iterator of rows out of a sstable file.
 *
 *  @author mdo
 */
public class CasspactorSSTableReader {
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
    private SSTableReader sstableReader;
    private IndexSummary indexSummary;

    private CFMetaData cfMetaData;

    //TODO: need to make it to support SSTable writer too
    /**
     *  Constructor.
     *  @param filePath location of the sstable file
     *  @throws IOException when file location is not valid
     */
    public CasspactorSSTableReader(final String filePath) throws IOException {
        this(filePath, Collections.<String>emptyList(), Collections.<String>emptyList());
    }

    /**
     *  Constructor.
     *  @param filePath location of the sstable file
     *  @param keyspaceName keyspace name
     *  @param tableName table name
     *  @param partitionKeyNames list of partition key names
     *  @param clustringKeyNames list of clustering key names
     *  @throws IOException when file location is not valid
     */
    public CasspactorSSTableReader(final String filePath,
                                   final String keyspaceName,
                                   final String tableName,
                                   final List<String> partitionKeyNames,
                                   final List<String> clustringKeyNames) throws IOException {
        this.fileLocation = filePath;

        initialization(keyspaceName, tableName, partitionKeyNames, clustringKeyNames);
    }

    /**
     *  Constructor.
     *  @param filePath location of the sstable file
     *  @param partitionKeyNames list of partition key names
     *  @param clustringKeyNames list of clustering key names
     *  @throws IOException when file location is not valid
     */
    public CasspactorSSTableReader(final String filePath,
                                   final List<String> partitionKeyNames,
                                   final List<String> clustringKeyNames) throws IOException {
        this(filePath, "", "", partitionKeyNames, clustringKeyNames);
    }

    /**
     * Initialization.
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
        descriptor = Descriptor.fromFilename(fileLocation);
        cfMetaData = metadataFromSSTable(descriptor, keyspaceName, tableName, partitionKeyNames, clusteringKeyNames);
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
     * Obtain CFMetaData.
     * @return CFMetaData instance to describe the underneath sstable file
     */
    public CFMetaData getCfMetaData() {
        return this.cfMetaData;
    }

    /**
     * Return SSTableReader that represents the underneath sstable file.
     *
     * @return IPartitioner
     */
    public SSTableReader getSstableReader() {
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
     * Construct table schema from info stored in SSTable's Stats.db.
     *
     * @param desc SSTable's descriptor
     * @param keyspaceName keyspace name
     * @param tableName table name
     * @param partitionKeyNames list of partition key names
     * @param clusteringKeyNames list of clustering key names
     * @return Restored CFMetaData
     * @throws IOException when Stats.db cannot be read
     */
    public static CFMetaData metadataFromSSTable(final Descriptor desc,
                                                 final String keyspaceName,
                                                 final String tableName,
                                                 final List<String> partitionKeyNames,
                                                 final List<String> clusteringKeyNames) throws IOException {
        if (!desc.version.storeRows()) {
            throw new IOException("pre-3.0 SSTable is not supported.");
        }

        final EnumSet<MetadataType> types = EnumSet.of(MetadataType.STATS, MetadataType.HEADER);
        final Map<MetadataType, MetadataComponent> sstableMetadata =
            desc.getMetadataSerializer().deserialize(desc, types);
        final SerializationHeader.Component header =
            (SerializationHeader.Component) sstableMetadata.get(MetadataType.HEADER);
        final IPartitioner partitioner = FBUtilities.newPartitioner(desc);
        final String keyspace = Strings.isEmpty(keyspaceName) ? desc.ksname : keyspaceName;
        final String table = Strings.isEmpty(tableName) ? desc.cfname : tableName;
        final CFMetaData.Builder builder = CFMetaData.Builder
            .create(keyspace, table)
            .withPartitioner(partitioner);
        header.getStaticColumns().entrySet().stream()
            .forEach(entry -> {
                final ColumnIdentifier ident =
                    ColumnIdentifier.getInterned(UTF8Type.instance.getString(entry.getKey()), true);
                builder.addStaticColumn(ident, entry.getValue());
            });
        header.getRegularColumns().entrySet().stream()
            .forEach(entry -> {
                final ColumnIdentifier ident =
                    ColumnIdentifier.getInterned(UTF8Type.instance.getString(entry.getKey()), true);
                builder.addRegularColumn(ident, entry.getValue());
            });


        if (header.getKeyType() instanceof CompositeType) {
            assert partitionKeyNames.size() == 0
                    || partitionKeyNames.size() == ((CompositeType) header.getKeyType()).types.size();
            int counter = 0;
            for (AbstractType type: ((CompositeType) header.getKeyType()).types) {
                String partitionColName = "PartitionKey" + counter;
                if (partitionKeyNames.size() > 0) {
                    partitionColName = partitionKeyNames.get(counter);
                }
                builder.addPartitionKey(partitionColName, type);
                counter++;
            }
        } else {
            String partitionColName = "PartitionKey";
            if (partitionKeyNames.size() > 0) {
                partitionColName = partitionKeyNames.get(0);
            }
            builder.addPartitionKey(partitionColName, header.getKeyType());
        }

        for (int i = 0; i < header.getClusteringTypes().size(); i++) {
            assert clusteringKeyNames.size() == 0
                    || clusteringKeyNames.size() == header.getClusteringTypes().size();
            String clusteringColName = "clustering" + (i > 0 ? i : "");
            if (clusteringKeyNames.size() > 0) {
                clusteringColName = clusteringKeyNames.get(i);
            }
            builder.addClusteringColumn(clusteringColName, header.getClusteringTypes().get(i));
        }
        return builder.build();
    }

    /**
     * Construct table schema from a file.
     *
     * @param filePath SSTable file location
     * @param keyspaceName keyspace name
     * @param tableName table name
     * @param partitionKeyNames list of partition key names
     * @param clusteringKeyNames list of clustering key names
     * @return Restored CFMetaData
     * @throws IOException when Stats.db cannot be read
     */
    public static CFMetaData metaDataFromSSTable(final String filePath,
                                                 final String keyspaceName,
                                                 final String tableName,
                                                 final List<String> partitionKeyNames,
                                                 final List<String> clusteringKeyNames) throws IOException {
        final Descriptor descriptor = Descriptor.fromFilename(filePath);

        return metadataFromSSTable(descriptor, keyspaceName, tableName,
                                   partitionKeyNames, clusteringKeyNames);
    }

    /**
     * Close resources.
     */
    public void close() {
        this.sstableReader.selfRef().close();
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

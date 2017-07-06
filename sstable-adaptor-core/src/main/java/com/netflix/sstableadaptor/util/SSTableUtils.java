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

package com.netflix.sstableadaptor.util;


import com.netflix.sstableadaptor.config.CassandraTable;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.config.ColumnDefinition;
import org.apache.cassandra.cql3.ColumnIdentifier;
import org.apache.cassandra.db.SerializationHeader;
import org.apache.cassandra.db.marshal.AbstractType;
import org.apache.cassandra.db.marshal.CompositeType;
import org.apache.cassandra.db.marshal.UTF8Type;
import org.apache.cassandra.dht.IPartitioner;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.io.FSWriteError;
import org.apache.cassandra.io.sstable.Descriptor;
import org.apache.cassandra.io.sstable.format.SSTableFormat;
import org.apache.cassandra.io.sstable.format.SSTableReader;
import org.apache.cassandra.io.sstable.format.SSTableWriter;
import org.apache.cassandra.io.sstable.format.big.BigTableWriter;
import org.apache.cassandra.io.sstable.metadata.MetadataCollector;
import org.apache.cassandra.io.sstable.metadata.MetadataComponent;
import org.apache.cassandra.io.sstable.metadata.MetadataType;
import org.apache.cassandra.schema.CompressionParams;
import org.apache.cassandra.utils.FBUtilities;
import org.apache.directory.api.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

/**
 *  Utilities on a sstable file.
 *  @author mdo
 */
public final class SSTableUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SSTableUtils.class);

    /**
     * Parse the list of values out of a composite key.
     * @param cfMetaData CFMetaData of a given table
     * @param partitionKey a byte buffer of a composite partition key
     * @return a list of value objects corresponding to the key declaration and the order
     */
    public static List<Object> parsePrimaryKey(final CFMetaData cfMetaData, final ByteBuffer partitionKey) {
        final List<Object> keyValue = new ArrayList<>(cfMetaData.partitionKeyColumns().size());
        final AbstractType<?> keyDataType = cfMetaData.getKeyValidator();

        if (keyDataType instanceof CompositeType) {
            final ByteBuffer[] components = ((CompositeType) cfMetaData.getKeyValidator()).split(partitionKey);
            cfMetaData.partitionKeyColumns();
            for (int i = 0; i < cfMetaData.partitionKeyColumns().size(); i++) {
                final ColumnDefinition colDef = cfMetaData.partitionKeyColumns().get(i);
                //logDebug("Partition Key ::: " + colDef.debugString)
                keyValue.add(colDef.cellValueType().compose(components[i]));
            }
        } else {
            final ColumnDefinition colDef = cfMetaData.partitionKeyColumns().get(0);
            keyValue.add(colDef.cellValueType().compose(partitionKey));
        }

        return keyValue;
    }

    public static CFMetaData loadCFMetaData(String dbFile,
                                            CassandraTable cassTable,
                                            List<String> partitionKeyNames,
                                            List<String> clusteringKeyNames) throws IOException {
        return metaDataFromSSTable(dbFile,
                cassTable.getKeyspaceName(),
                cassTable.getTableName(),
                partitionKeyNames,
                clusteringKeyNames);
    }

    public static SSTableWriter createSSTableWriter(final Descriptor inputSSTableDescriptor,
                                                     final CFMetaData outCfmMetaData,
                                                     final SSTableReader inputSSTable) {
        final String sstableDirectory = System.getProperty("user.dir") + "/cassandra/compresseddata";
        LOGGER.info("Output directory: " + sstableDirectory);

        final File outputDirectory = new File(sstableDirectory + File.separatorChar
                + inputSSTableDescriptor.ksname
                + File.separatorChar + inputSSTableDescriptor.cfname);

        if (!outputDirectory.exists() && !outputDirectory.mkdirs()) {
            throw new FSWriteError(new IOException("failed to create tmp directory"),
                    outputDirectory.getAbsolutePath());
        }

        final SSTableFormat.Type sstableFormat = SSTableFormat.Type.BIG;

        final BigTableWriter writer = new BigTableWriter(
                new Descriptor(
                        sstableFormat.info.getLatestVersion().getVersion(),
                        outputDirectory.getAbsolutePath(),
                        inputSSTableDescriptor.ksname, inputSSTableDescriptor.cfname,
                        inputSSTableDescriptor.generation,
                        sstableFormat),
                inputSSTable.getTotalRows(), 0L, outCfmMetaData,
                new MetadataCollector(outCfmMetaData.comparator)
                        .sstableLevel(inputSSTable.getSSTableMetadata().sstableLevel),
                new SerializationHeader(true,
                        outCfmMetaData, outCfmMetaData.partitionColumns(),
                        org.apache.cassandra.db.rows.EncodingStats.NO_STATS));

        return writer;
    }

    public static CFMetaData createNewCFMetaData(final Descriptor inputSSTableDescriptor,
                                                  final CFMetaData metadata) {
        final CFMetaData.Builder cfMetadataBuilder = CFMetaData.Builder.create(inputSSTableDescriptor.ksname,
                inputSSTableDescriptor.cfname);

        final Collection<ColumnDefinition> colDefs = metadata.allColumns();

        for (ColumnDefinition colDef : colDefs) {
            switch (colDef.kind) {
                case PARTITION_KEY:
                    cfMetadataBuilder.addPartitionKey(colDef.name, colDef.cellValueType());
                    break;
                case CLUSTERING:
                    cfMetadataBuilder.addClusteringColumn(colDef.name, colDef.cellValueType());
                    break;
                case STATIC:
                    cfMetadataBuilder.addStaticColumn(colDef.name, colDef.cellValueType());
                    break;
                default:
                    cfMetadataBuilder.addRegularColumn(colDef.name, colDef.cellValueType());
            }
        }

        cfMetadataBuilder.withPartitioner(Murmur3Partitioner.instance);
        final CFMetaData cfm = cfMetadataBuilder.build();
        cfm.compression(CompressionParams.DEFAULT);

        return cfm;
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
     * @return Restored CFMetaData
     * @throws IOException when Stats.db cannot be read
     */
    public static CFMetaData metaDataFromSSTable(final String filePath) throws IOException {
        final Descriptor descriptor = Descriptor.fromFilename(filePath);

        return metadataFromSSTable(descriptor, null, null,
                Collections.<String>emptyList(), Collections.<String>emptyList());
    }

    /**
     * Construct table schema from a file.
     *
     * @param filePath SSTable file location
     * @param partitionKeyNames list of partition key names
     * @param clusteringKeyNames list of clustering key names
     * @return Restored CFMetaData
     * @throws IOException when Stats.db cannot be read
     */
    public static CFMetaData metaDataFromSSTable(final String filePath,
                                                 final List<String> partitionKeyNames,
                                                 final List<String> clusteringKeyNames) throws IOException {
        final Descriptor descriptor = Descriptor.fromFilename(filePath);

        return metadataFromSSTable(descriptor, null, null,
                partitionKeyNames, clusteringKeyNames);
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

}

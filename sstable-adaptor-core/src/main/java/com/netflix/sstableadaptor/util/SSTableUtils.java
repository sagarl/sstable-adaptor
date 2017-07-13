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


import com.datastax.driver.core.Row;
import com.netflix.sstableadaptor.config.CassandraTable;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.config.ColumnDefinition;
import org.apache.cassandra.cql3.ColumnIdentifier;
import org.apache.cassandra.db.SerializationHeader;
import org.apache.cassandra.db.marshal.AbstractType;
import org.apache.cassandra.db.marshal.AsciiType;
import org.apache.cassandra.db.marshal.CompositeType;
import org.apache.cassandra.db.marshal.Int32Type;
import org.apache.cassandra.db.marshal.IntegerType;
import org.apache.cassandra.db.marshal.ReversedType;
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
import org.apache.cassandra.io.sstable.metadata.ValidationMetadata;
import org.apache.cassandra.schema.CQLTypeParser;
import org.apache.cassandra.schema.CompressionParams;
import org.apache.cassandra.schema.Types;
import org.apache.cassandra.utils.ByteBufferUtil;
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
import java.util.Optional;
import java.util.UUID;

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


    private static ColumnDefinition createDefinitionFromRow(String keyspace, String table, Types types,
                                                            AbstractType<?> type,
                                                            ColumnIdentifier name, int position,
                                                            ColumnDefinition.Kind kind)
    {
        //ColumnDefinition.ClusteringOrder order = ColumnDefinition.ClusteringOrder.valueOf(row.getString("clustering_order").toUpperCase());
        //AbstractType<?> type = CQLTypeParser.parse(keyspace, row.getString("type"), types);
        //if (order == ColumnDefinition.ClusteringOrder.DESC)
        //    type = ReversedType.getInstance(type);

        return new ColumnDefinition(keyspace, table, name, type, position, kind);
    }

    //For thrift table
    public static CFMetaData metadataFromSSTableHacked(final Descriptor desc,
                                                 final String keyspaceName,
                                                 final String tableName,
                                                 final List<String> partitionKeyNames,
                                                 final List<String> clusteringKeyNames) throws IOException {

        boolean isSuper = false; //flags.contains(CFMetaData.Flag.SUPER);
        boolean isCounter = false; //flags.contains(CFMetaData.Flag.COUNTER);
        boolean isDense = false; //flags.contains(CFMetaData.Flag.DENSE);
        boolean isCompound = false;  //isView || flags.contains(CFMetaData.Flag.COMPOUND);

        List<ColumnDefinition> defs = new ArrayList<>();

        ColumnIdentifier key = new ColumnIdentifier("key", false);
        defs.add(createDefinitionFromRow(keyspaceName, tableName, null, AsciiType.instance, key, -1, ColumnDefinition.Kind.PARTITION_KEY));

        ColumnIdentifier email = new ColumnIdentifier("email", false);
        defs.add(createDefinitionFromRow(keyspaceName, tableName, null, AsciiType.instance, email, -1, ColumnDefinition.Kind.REGULAR));

        //ColumnIdentifier account_id = new ColumnIdentifier(ByteBufferUtil.bytes(("account_id")), AsciiType.instance);
        ColumnIdentifier first_name = new ColumnIdentifier("first_name", false);
        defs.add(createDefinitionFromRow(keyspaceName, tableName, null, AsciiType.instance, first_name, -1, ColumnDefinition.Kind.REGULAR));

        //ColumnIdentifier amount = new ColumnIdentifier(ByteBufferUtil.bytes(("amount")), AsciiType.instance);
        ColumnIdentifier last_name = new ColumnIdentifier("last_name", false);
        defs.add(createDefinitionFromRow(keyspaceName, tableName, null, AsciiType.instance, last_name, -1, ColumnDefinition.Kind.REGULAR));

        //ColumnIdentifier balance = new ColumnIdentifier(ByteBufferUtil.bytes(("balance")), AsciiType.instance);
        ColumnIdentifier year_of_birth = new ColumnIdentifier("year_of_birth", false);
        defs.add(createDefinitionFromRow(keyspaceName, tableName, null, Int32Type.instance, year_of_birth, -1, ColumnDefinition.Kind.REGULAR));


        CFMetaData metadata = CFMetaData.create(keyspaceName,
                tableName,
                UUID.randomUUID(),
                isDense,
                isCompound,
                isSuper,
                isCounter,
                false,
                defs,
                Murmur3Partitioner.instance);

        return metadata;
    }

    public static CFMetaData metadataFromSSTableHacked1(final Descriptor desc,
                                                       final String keyspaceName,
                                                       final String tableName,
                                                       final List<String> partitionKeyNames,
                                                       final List<String> clusteringKeyNames) throws IOException {

        boolean isSuper = false; //flags.contains(CFMetaData.Flag.SUPER);
        boolean isCounter = false; //flags.contains(CFMetaData.Flag.COUNTER);
        boolean isDense = false; //flags.contains(CFMetaData.Flag.DENSE);
        boolean isCompound = true;  //isView || flags.contains(CFMetaData.Flag.COMPOUND);

        List<ColumnDefinition> defs = new ArrayList<>();

        ColumnIdentifier expense_id = new ColumnIdentifier("expense_id", false);
        defs.add(createDefinitionFromRow(keyspaceName, tableName, null, Int32Type.instance, expense_id, 0, ColumnDefinition.Kind.CLUSTERING));

        ColumnIdentifier item_id = new ColumnIdentifier("item_id", false);
        defs.add(createDefinitionFromRow(keyspaceName, tableName, null, Int32Type.instance, item_id, 1, ColumnDefinition.Kind.CLUSTERING));

        //ColumnIdentifier account_id = new ColumnIdentifier(ByteBufferUtil.bytes(("account_id")), AsciiType.instance);
        ColumnIdentifier account_id = new ColumnIdentifier("account_id", false);
        defs.add(createDefinitionFromRow(keyspaceName, tableName, null, AsciiType.instance, account_id, -1, ColumnDefinition.Kind.STATIC));

        //ColumnIdentifier amount = new ColumnIdentifier(ByteBufferUtil.bytes(("amount")), AsciiType.instance);
        ColumnIdentifier amount = new ColumnIdentifier("amount", false);
        defs.add(createDefinitionFromRow(keyspaceName, tableName, null, Int32Type.instance, amount, -1, ColumnDefinition.Kind.REGULAR));

        //ColumnIdentifier balance = new ColumnIdentifier(ByteBufferUtil.bytes(("balance")), AsciiType.instance);
        ColumnIdentifier balance = new ColumnIdentifier("balance", false);
        defs.add(createDefinitionFromRow(keyspaceName, tableName, null, Int32Type.instance, balance, -1, ColumnDefinition.Kind.STATIC));

        //ColumnIdentifier name = new ColumnIdentifier(ByteBufferUtil.bytes(("name")), AsciiType.instance);
        ColumnIdentifier name = new ColumnIdentifier("name", false);
        defs.add(createDefinitionFromRow(keyspaceName, tableName, null, AsciiType.instance, name, -1, ColumnDefinition.Kind.REGULAR));

        //ColumnIdentifier user = new ColumnIdentifier(ByteBufferUtil.bytes(("user")), AsciiType.instance);
        ColumnIdentifier user = new ColumnIdentifier("user", false);
        defs.add(createDefinitionFromRow(keyspaceName, tableName, null, AsciiType.instance, user, 0, ColumnDefinition.Kind.PARTITION_KEY));

        //ColumnIdentifier email = new ColumnIdentifier(ByteBufferUtil.bytes(("email")), AsciiType.instance);
        ColumnIdentifier email = new ColumnIdentifier("email", false);
        defs.add(createDefinitionFromRow(keyspaceName, tableName, null, AsciiType.instance, email, 1, ColumnDefinition.Kind.PARTITION_KEY));


        CFMetaData metadata = CFMetaData.create(keyspaceName,
                tableName,
                UUID.randomUUID(),
                isDense,
                isCompound,
                isSuper,
                isCounter,
                false,
                defs,
                Murmur3Partitioner.instance);

        return metadata;
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

        final EnumSet<MetadataType> types = EnumSet.of(MetadataType.STATS, MetadataType.HEADER, MetadataType.VALIDATION);
        final Map<MetadataType, MetadataComponent> sstableMetadata =
                desc.getMetadataSerializer().deserialize(desc, types);

        ValidationMetadata validationMetadata = (ValidationMetadata) sstableMetadata.get(MetadataType.VALIDATION);
        final SerializationHeader.Component header =
                (SerializationHeader.Component) sstableMetadata.get(MetadataType.HEADER);

        final IPartitioner partitioner = FBUtilities.newPartitioner(validationMetadata, header);

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

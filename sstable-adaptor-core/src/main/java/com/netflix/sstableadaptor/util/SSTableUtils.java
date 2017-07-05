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
import com.netflix.sstableadaptor.sstable.SSTableSingleReader;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.config.ColumnDefinition;
import org.apache.cassandra.db.SerializationHeader;
import org.apache.cassandra.db.marshal.AbstractType;
import org.apache.cassandra.db.marshal.CompositeType;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.io.FSWriteError;
import org.apache.cassandra.io.sstable.Descriptor;
import org.apache.cassandra.io.sstable.format.SSTableFormat;
import org.apache.cassandra.io.sstable.format.SSTableWriter;
import org.apache.cassandra.io.sstable.format.big.BigTableWriter;
import org.apache.cassandra.io.sstable.metadata.MetadataCollector;
import org.apache.cassandra.schema.CompressionParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        return SSTableSingleReader.metaDataFromSSTable(dbFile,
                cassTable.getKeyspaceName(),
                cassTable.getTableName(),
                partitionKeyNames,
                clusteringKeyNames);
    }

    public static SSTableWriter createSSTableWriter(final Descriptor inputSSTableDescriptor,
                                                     final CFMetaData outCfmMetaData,
                                                     final org.apache.cassandra.io.sstable.format.SSTableReader inputSstable) {
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
                inputSstable.getTotalRows(), 0L, outCfmMetaData,
                new MetadataCollector(outCfmMetaData.comparator)
                        .sstableLevel(inputSstable.getSSTableMetadata().sstableLevel),
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

}

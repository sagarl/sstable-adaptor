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

package com.netflix.sstableadaptor;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.netflix.sstableadaptor.config.CassandraTable;
import com.netflix.sstableadaptor.sstable.SSTableSingleReader;
import com.netflix.sstableadaptor.sstable.SSTableSingleWriter;
import com.netflix.sstableadaptor.util.SSTableUtils;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.config.ColumnDefinition;
import org.apache.cassandra.db.BufferClustering;
import org.apache.cassandra.db.Clustering;
import org.apache.cassandra.db.DecoratedKey;
import org.apache.cassandra.db.SerializationHeader;
import org.apache.cassandra.db.marshal.Int32Type;
import org.apache.cassandra.db.marshal.UTF8Type;
import org.apache.cassandra.db.partitions.PartitionUpdate;
import org.apache.cassandra.db.rows.BTreeRow;
import org.apache.cassandra.db.rows.BufferCell;
import org.apache.cassandra.db.rows.Cell;
import org.apache.cassandra.db.rows.EncodingStats;
import org.apache.cassandra.db.rows.Row;
import org.apache.cassandra.db.rows.Unfiltered;
import org.apache.cassandra.db.rows.UnfilteredRowIterator;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.io.sstable.Descriptor;
import org.apache.cassandra.io.sstable.ISSTableScanner;
import org.apache.cassandra.io.sstable.SSTableTxnWriter;
import org.apache.cassandra.io.sstable.format.SSTableFlushObserver;
import org.apache.cassandra.io.sstable.format.SSTableFormat;
import org.apache.cassandra.io.sstable.format.SSTableReader;
import org.apache.cassandra.io.sstable.format.SSTableWriter;
import org.apache.cassandra.io.util.FileUtils;
import org.apache.cassandra.utils.Pair;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//TODO: Also need to add some assertions to verify data writing correction

/**
 *
 * Test writing out a new SSTable file locally.
 */
public class TestSSTableDataWriter extends TestBaseSSTableFunSuite {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestSSTableDataWriter.class);

    /**
     * Setting up resources prior to running any tests.
     *
     * @throws Exception when we cannot initialize the resources
     */
    @BeforeClass
    public static void setup() throws Exception {
        LOGGER.info("Running TestSSTableDataWriter setup ...");
        TestBaseSSTableFunSuite.setup();
    }

    /**
     * Tear down resources after all tests.
     *
     * @throws Exception when teardown has an issue
     */
    @AfterClass
    public static void teardown() throws Exception {
        LOGGER.info("Tearing TestSSTableDataWriter down ...");
        TestBaseSSTableFunSuite.teardown();
    }


    /******************************************************
     * 1. Input data
     * This is the schema definition of the table that is used to generate the non-compressed input data:
     * <p>
     * CREATE TABLE bills_nc (
     * user text,
     * balance int static,
     * expense_id int,
     * amount int,
     * name text,
     * PRIMARY KEY (user, expense_id))
     * WITH compression = { 'sstable_compression' : '' };
     * <p>
     * <p>
     * 2. Compressing and producing output data
     * Running this main will convert data file under src/test/resources/data/bills_compress/mc-6-big-Data.db
     * in to the corresponding compressed file, using LZ4 compression, along with auxiliary
     * files (CompressionInfo.db, Index.db, etc).
     * <p>
     * The output is under cassanrda/compresseddata/cassandra/data directory
     * <p>
     * 3. Verification
     * Since this is C* 3.0 format, you should use sstabledump command to dump out the json contents
     * for both intput data and output data to verify.
     * %>sstabledump cassandra/data/mc-1-big-Data.db
     * and
     * %>sstabledump cassandra/compresseddata/cassandra/data/mc-1-big-Data.db
     *******************************************************/
    @Test
    public void testWritingToLocalSSTable() {
        final String inputSSTableFullPathFileName = CASS3_DATA_DIR + "bills_compress/mc-6-big-Data.db";
        LOGGER.info("Input file name: " + inputSSTableFullPathFileName);

        final Descriptor inputSSTableDescriptor = Descriptor.fromFilename(inputSSTableFullPathFileName,
                                                                TestBaseSSTableFunSuite.HADOOP_CONF);
        SSTableWriter writer = null;

        try {
            SSTableSingleReader reader = new SSTableSingleReader(inputSSTableFullPathFileName,
                                                                 "casspactor",
                                                                 "bills_nc",
                                                                 TestBaseSSTableFunSuite.HADOOP_CONF);
            final CFMetaData inputCFMetaData = reader.getCfMetaData();
            final ISSTableScanner currentScanner = reader.getSSTableScanner();
            final SSTableReader inputSStable = reader.getSstableReader();

            //Create writer
            final CFMetaData outputCFMetaData = SSTableUtils.createNewCFMetaData(inputSSTableDescriptor, inputCFMetaData);
            writer = SSTableUtils.createSSTableWriter(inputSSTableDescriptor, outputCFMetaData, inputSStable);

            while (currentScanner.hasNext()) {
                final UnfilteredRowIterator row = currentScanner.next();
                writer.append(row);
            }
            writer.finish(false);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        } finally {
            FileUtils.closeQuietly(writer);
        }
    }

    /**
     * Test creating sstable files using SSTableTxnWriter.
     * @throws IOException
     */
    @Test
    public void testCreatingSSTableWithTnx() throws IOException {
        final String inputSSTableFullPathFileName = CASS3_DATA_DIR + "bills_compress/mc-6-big-Data.db";

        final Descriptor descriptor = Descriptor.fromFilename(inputSSTableFullPathFileName,
                                                              TestBaseSSTableFunSuite.HADOOP_CONF);
        final CFMetaData inputCFMetaData =
                SSTableUtils.metaDataFromSSTable(inputSSTableFullPathFileName,
                                                        "casspactor",
                                                        "bills_compress",
                                                        Collections.<String>emptyList(),
                                                        Collections.<String>emptyList(),
                                                        TestBaseSSTableFunSuite.HADOOP_CONF);

        final CFMetaData outputCFMetaData = SSTableUtils.createNewCFMetaData(descriptor, inputCFMetaData);
        final SerializationHeader header = new SerializationHeader(true, outputCFMetaData,
            inputCFMetaData.partitionColumns(),
            EncodingStats.NO_STATS);

        final Descriptor outDescriptor = new Descriptor(
            SSTableFormat.Type.BIG.info.getLatestVersion().getVersion(),
            "/tmp",
            "casspactor",
            "bills_compress",
            9,
            SSTableFormat.Type.BIG, TestBaseSSTableFunSuite.HADOOP_CONF);

        final SSTableTxnWriter writer = SSTableTxnWriter.create(outputCFMetaData,
                                                                outDescriptor,
                                                                4,
                                                                -1,
                                                                1,
                                                                header);

        final ColumnDefinition staticCollDef =
            ColumnDefinition.staticDef(inputCFMetaData, ByteBuffer.wrap("balance".getBytes()), Int32Type.instance);
        final ColumnDefinition regCollDef1 =
            ColumnDefinition.regularDef(inputCFMetaData, ByteBuffer.wrap("amount".getBytes()), Int32Type.instance);
        final ColumnDefinition regCollDef2 =
            ColumnDefinition.regularDef(inputCFMetaData, ByteBuffer.wrap("name".getBytes()), UTF8Type.instance);

        final DecoratedKey key = Murmur3Partitioner.instance.decorateKey(ByteBuffer.wrap("user1".getBytes()));
        final long now = System.currentTimeMillis();

        final Row.Builder builder = BTreeRow.sortedBuilder();
        builder.newRow(Clustering.STATIC_CLUSTERING);
        builder.addCell(BufferCell.live(staticCollDef, now, Int32Type.instance.decompose(123)));
        final PartitionUpdate partitionUpdate = PartitionUpdate.singleRowUpdate(inputCFMetaData,
            key, builder.build());
        final Row.Builder builder2 = BTreeRow.sortedBuilder();
        final Clustering clustering2 = new BufferClustering(Int32Type.instance.decompose(10000));
        builder2.newRow(clustering2);
        builder2.addCell(BufferCell.live(regCollDef1, now, Int32Type.instance.decompose(5)));
        builder2.addCell(BufferCell.live(regCollDef2, now, UTF8Type.instance.decompose("minh1")));

        final PartitionUpdate partitionUpdate2 = PartitionUpdate.singleRowUpdate(inputCFMetaData,
            key, builder2.build());

        final List<PartitionUpdate> partitionUpdates = new ArrayList<PartitionUpdate>() {
            private static final long serialVersionUID = 1L;
            {
                add(partitionUpdate);
                add(partitionUpdate2);
            }
        };

        final PartitionUpdate mergedUpdate = PartitionUpdate.merge(partitionUpdates);

        writer.append(mergedUpdate.unfilteredIterator());
        writer.finish(false);
    }

    /**
     * Helper to trigger post-actions.
     */
    private static class FlushObserver implements SSTableFlushObserver {
        private final Multimap<Pair<ByteBuffer, Long>, Cell> rows = ArrayListMultimap.create();
        private Pair<ByteBuffer, Long> currentKey;

        @Override
        public void begin() {
        }

        @Override
        public void startPartition(final DecoratedKey key, final long indexPosition) {
            currentKey = Pair.create(key.getKey(), indexPosition);
            LOGGER.info("Current key: " + new String(key.getKey().array()));
        }

        @Override
        public void nextUnfilteredCluster(final Unfiltered row) {
            if (row.isRow()) {
                ((Row) row).forEach((c) -> {
                    rows.put(currentKey, (Cell) c);
                    LOGGER.info("Cell: " + c);
                });
            }
        }

        @Override
        public void complete() {
            LOGGER.info("Complete writing with the last key: " + new String(currentKey.left.array()));
        }
    }


    @Test
    public void testConvertingSSTable() throws IOException {
        final String inputFile = CASS3_DATA_DIR + "bills_compress/mc-6-big-Data.db";
        final CFMetaData inputCFMetaData = SSTableUtils.metaDataFromSSTable(inputFile,
                                                                            TestBaseSSTableFunSuite.HADOOP_CONF);
        final CassandraTable cassandraTable = new CassandraTable.CassandraTableBuilder()
                                                                .withClusterName("cass_share")
                                                                .withKeyspaceName("casspactor")
                                                                .withTableName("bills_compress")
                                                                .build();
        final String outputLocation = "/tmp";

        final SSTableSingleWriter<UnfilteredRowIterator> writer =
                new SSTableSingleWriter(inputCFMetaData, cassandraTable, outputLocation,
                                        TestBaseSSTableFunSuite.HADOOP_CONF);

        SSTableSingleReader reader = new SSTableSingleReader(inputFile, cassandraTable,
                                                             TestBaseSSTableFunSuite.HADOOP_CONF);
        final ISSTableScanner currentScanner = reader.getSSTableScanner();

        writer.write(currentScanner);
    }


}

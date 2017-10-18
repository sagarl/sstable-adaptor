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

import com.netflix.sstableadaptor.sstable.SSTableSingleReader;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.config.ColumnDefinition;
import org.apache.cassandra.db.PartitionColumns;
import org.apache.cassandra.db.marshal.CompositeType;
import org.apache.cassandra.db.marshal.Int32Type;
import org.apache.cassandra.db.marshal.UTF8Type;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.io.sstable.IndexSummary;
import org.apache.cassandra.serializers.UTF8Serializer;
import org.apache.hadoop.conf.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Test reading SSTable's metadata using SSTableSingleReader API.
 */
public class TestSSTableMetadata extends TestBaseSSTableFunSuite {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestSStableDataLister.class);

    /**
     * Setting up resources prior to running any tests.
     * @throws Exception when we cannot initialize the resources
     */
    @BeforeClass
    public static void setup() throws Exception {
        LOGGER.info("Running TestSSTableMetadata setup ...");
        TestBaseSSTableFunSuite.setup();
    }

    /**
     * Tear down resources after all tests.
     * @throws Exception when teardown has an issue
     */
    @AfterClass
    public static void teardown() throws Exception {
        LOGGER.info("Tearing TestSSTableMetadata down ...");
        TestBaseSSTableFunSuite.teardown();
    }

    /**
     *  Compound primary key with simple partition key
     *  This test works on the sstable file.
     *  Location: /src/test/resources/data/bills_compress/mc-6-big-Data.db
     *
     *   The corresponding table definition is :
     *       CREATE TABLE bills_compress (
     *          user text,
     *          expense_id int,
     *          balance int static,
     *          amount int,
     *          name text,
     *          PRIMARY KEY (user, expense_id)
     *       )
     * @throws IOException when code is unable to read sstable file
     */
    @Test
    public void testAccessSSTableMetadataCase1() throws IOException {
        final String inputSSTableFullPathFileName = CASS3_DATA_DIR + "bills_compress/mc-6-big-Data.db";
        final SSTableSingleReader SSTableSingleReader =
                   new SSTableSingleReader(inputSSTableFullPathFileName, TestBaseSSTableFunSuite.HADOOP_CONF);

        LOGGER.info("File location: " + SSTableSingleReader.getFileLocation());
        LOGGER.info("File size: " + SSTableSingleReader.getFileLength());
        LOGGER.info("Partitioner: " + SSTableSingleReader.getPartitioner());
        LOGGER.info("Min token: " + SSTableSingleReader.getPartitioner().getMinimumToken());
        LOGGER.info("Max token: " + SSTableSingleReader.getPartitioner().getMaximumToken());

        final IndexSummary indexSummary = SSTableSingleReader.getIndexSummary();
        LOGGER.info("IndexSummary - Estimated key count:" + indexSummary.getEstimatedKeyCount());
        LOGGER.info("IndexSummary - Size: " + indexSummary.size());
        LOGGER.info("IndexSummary - Effective index interval: " + indexSummary.getEffectiveIndexInterval());
        LOGGER.info("IndexSummary - Max number of entries: " + indexSummary.getMaxNumberOfEntries());
        LOGGER.info("IndexSummary - Min index interval: " + indexSummary.getMinIndexInterval());
        LOGGER.info("IndexSummary - OffHeapSize: " + indexSummary.getOffHeapSize());
        for (int i = 0; i < indexSummary.size(); i++) {
            final ByteBuffer keyBuf = ByteBuffer.wrap(indexSummary.getKey(i));
            LOGGER.info("IndexSummary - key[{}]: {}",
                    i, UTF8Serializer.instance.deserialize(keyBuf));
            LOGGER.info("\ttoken: {}",
                    SSTableSingleReader.getPartitioner().getToken(keyBuf));
            LOGGER.info("\tposition: {}, position in summary: {}, end in summary: {}",
                    indexSummary.getPosition(i),
                    indexSummary.getPositionInSummary(i),
                    indexSummary.getEndInSummary(i));
        }

        final CFMetaData cfMetaData = SSTableSingleReader.getCfMetaData();

        LOGGER.info("=======================================");
        LOGGER.info("Keyspace name: " + cfMetaData.ksName);
        LOGGER.info("Table name: " + cfMetaData.cfName);

        LOGGER.info("Total columns: " + cfMetaData.allColumns().size());
        Assert.assertEquals(5, cfMetaData.allColumns().size());

        LOGGER.info("Table params: " + cfMetaData.params);

        LOGGER.info("=======================================");
        LOGGER.info("Listing column names: ");
        final Iterator<ColumnDefinition> columnDefinitionIterator =  cfMetaData.allColumnsInSelectOrder();
        while (columnDefinitionIterator.hasNext()) {
            final ColumnDefinition columnDefinition = columnDefinitionIterator.next();
            LOGGER.info("\t" + columnDefinition.toString());
        }

        final PartitionColumns partitionColumns = cfMetaData.partitionColumns();
        partitionColumns.iterator().forEachRemaining(cf -> LOGGER.info("Column in partition: " + cf.name));

        LOGGER.info("Num dropped columns: " + cfMetaData.getDroppedColumns().size());
        for (CFMetaData.DroppedColumn droppedCol : cfMetaData.getDroppedColumns().values()) {
             LOGGER.info("Column name " + droppedCol.name + " was dropped at " + droppedCol.droppedTime);
        }

        Assert.assertEquals(5, cfMetaData.allColumns().size());

        LOGGER.info("=======================================");
        final Map<ByteBuffer, ColumnDefinition> columnMetaData = cfMetaData.getColumnMetadata();
        final Collection<ColumnDefinition> colDefinitions = columnMetaData.values();
        for (ColumnDefinition colDef : colDefinitions) {
            printColumnDefinition(colDef);
        }

        LOGGER.info("Estimated partition size: " + SSTableSingleReader.getEstimatedPartitionSize());
        LOGGER.info("Estimated total rows: " + SSTableSingleReader.getTotalRows());

        LOGGER.info("Estimated column count: " + SSTableSingleReader.getStats().estimatedColumnCount);
        LOGGER.info("Estimated partition size: ");
        final long[] buckets = SSTableSingleReader.getStats().estimatedPartitionSize.getBuckets(false);
        for (int i = 0; i < buckets.length; i++) {
            LOGGER.info("\tPartition bucket[" + i + "]: " + buckets[i]);
        }

        LOGGER.info("=======================================");
        LOGGER.info("First key: " + UTF8Type.instance.compose(SSTableSingleReader.getFirstKey().getKey()));
        LOGGER.info("Last key: " + UTF8Type.instance.compose(SSTableSingleReader.getLastKey().getKey()));

        LOGGER.info("=======================================");
        LOGGER.info("Min TTL: " + SSTableSingleReader.getStats().minTTL);
        LOGGER.info("Max TTL: " + SSTableSingleReader.getStats().maxTTL);
        LOGGER.info("Min timestamp: " + SSTableSingleReader.getStats().minTimestamp);
        LOGGER.info("Max timestamp: " + SSTableSingleReader.getStats().maxTimestamp);

        //TODO: this block only works because we know there is only one clustering column
        LOGGER.info("Min clustering value: ");
        for (ByteBuffer buffer: SSTableSingleReader.getStats().minClusteringValues) {
            LOGGER.info("\t" + cfMetaData.clusteringColumns().get(0).type.compose(buffer));
        }
        LOGGER.info("Max clustering value: ");
        for (ByteBuffer buffer: SSTableSingleReader.getStats().maxClusteringValues) {
              LOGGER.info("\t" + cfMetaData.clusteringColumns().get(0).type.compose(buffer));
        }

        LOGGER.info("Min local deletion time: " + SSTableSingleReader.getStats().minLocalDeletionTime);
        LOGGER.info("Max local deletion time: " + SSTableSingleReader.getStats().maxLocalDeletionTime);
        LOGGER.info("SSTable level: " + SSTableSingleReader.getStats().sstableLevel);

        LOGGER.info("=======================================");
        LOGGER.info("Key validator: " + cfMetaData.getKeyValidator());
        Assert.assertTrue(cfMetaData.getKeyValidator() == UTF8Type.instance);

        LOGGER.info("Clustering comparator: " + cfMetaData.comparator);
        Assert.assertTrue(cfMetaData.comparator.subtype(0) == Int32Type.instance);
        LOGGER.info("Parent Column Family: " + cfMetaData.getParentColumnFamilyName());
        LOGGER.info("Has collection columns? : " + cfMetaData.hasCollectionColumns());
        Assert.assertFalse(cfMetaData.hasCollectionColumns());

        LOGGER.info("Has complex columns? : " + cfMetaData.hasComplexColumns());
        Assert.assertFalse(cfMetaData.hasComplexColumns());

        LOGGER.info("Has dropped collection columns? : " + cfMetaData.hasDroppedCollectionColumns());
        LOGGER.info("Has static columns? : " + cfMetaData.hasStaticColumns());

        Assert.assertTrue(cfMetaData.hasStaticColumns());

        LOGGER.info("Is compound? : " + cfMetaData.isCompound());
        Assert.assertTrue(cfMetaData.isCompound());

        LOGGER.info("Is view? : " + cfMetaData.isView());
        Assert.assertFalse(cfMetaData.isView());

        LOGGER.info("Partitioner: " + cfMetaData.partitioner);
        Assert.assertTrue(cfMetaData.partitioner instanceof Murmur3Partitioner);

        LOGGER.info("Serializers: " + cfMetaData.serializers());

    }


    /**
     *  Compound primary key with composite partition key.
     *  CREATE TABLE compressed_bills (
     *       user text,
     *       email text,
     *       account_id text static,
     *       balance int static,
     *       expense_id int,
     *       item_id int,
     *       amount int,
     *       name text,
     *   PRIMARY KEY ((user, email), expense_id, item_id))
     */
    @Test
    public void testAccessSSTableMetadataCase2() throws IOException {
        final String inputSSTableFullPathFileName = CASS3_DATA_DIR + "compressed_bills/mc-2-big-Data.db";
        final Configuration conf = new Configuration();
        final SSTableSingleReader SSTableSingleReader =
            new SSTableSingleReader(inputSSTableFullPathFileName, conf);

        final CFMetaData cfMetaData = SSTableSingleReader.getCfMetaData();

        LOGGER.info("=======================================");
        LOGGER.info("Keyspace name: " + cfMetaData.ksName);
        LOGGER.info("Table name: " + cfMetaData.cfName);

        LOGGER.info("Total columns: " + cfMetaData.allColumns().size());
        Assert.assertEquals(8, cfMetaData.allColumns().size());

        LOGGER.info("Table params: " + cfMetaData.params);

        LOGGER.info("=======================================");
        LOGGER.info("Listing column names: ");
        final Iterator<ColumnDefinition> columnDefinitionIterator =  cfMetaData.allColumnsInSelectOrder();
        while (columnDefinitionIterator.hasNext()) {
            final ColumnDefinition columnDefinition = columnDefinitionIterator.next();
            LOGGER.info("\t" + columnDefinition.toString());
        }

        final PartitionColumns partitionColumns = cfMetaData.partitionColumns();
        partitionColumns.iterator().forEachRemaining(cf -> LOGGER.info("Column in partition: " + cf.name));


        LOGGER.info("=======================================");
        final Map<ByteBuffer, ColumnDefinition> columnMetaData = cfMetaData.getColumnMetadata();
        final Collection<ColumnDefinition> colDefinitions = columnMetaData.values();
        for (ColumnDefinition colDef : colDefinitions) {
            printColumnDefinition(colDef);
        }

        LOGGER.info("Estimated partition size: " + SSTableSingleReader.getEstimatedPartitionSize());
        LOGGER.info("Estimated total rows: " + SSTableSingleReader.getTotalRows());

        LOGGER.info("Estimated column count: " + SSTableSingleReader.getStats().estimatedColumnCount);

        LOGGER.info("=======================================");
        LOGGER.info("First key: " + UTF8Type.instance.compose(SSTableSingleReader.getFirstKey().getKey()));
        LOGGER.info("Last key: " + UTF8Type.instance.compose(SSTableSingleReader.getLastKey().getKey()));

        LOGGER.info("=======================================");
        LOGGER.info("Min TTL: " + SSTableSingleReader.getStats().minTTL);
        LOGGER.info("Max TTL: " + SSTableSingleReader.getStats().maxTTL);
        LOGGER.info("Min timestamp: " + SSTableSingleReader.getStats().minTimestamp);
        LOGGER.info("Max timestamp: " + SSTableSingleReader.getStats().maxTimestamp);

        //TODO: this block only works because we know there is only one clustering column
        LOGGER.info("Min clustering value: ");
        for (ByteBuffer buffer: SSTableSingleReader.getStats().minClusteringValues) {
            LOGGER.info("\t" + cfMetaData.clusteringColumns().get(0).type.compose(buffer));
        }
        LOGGER.info("Max clustering value: ");
        for (ByteBuffer buffer: SSTableSingleReader.getStats().maxClusteringValues) {
            LOGGER.info("\t" + cfMetaData.clusteringColumns().get(0).type.compose(buffer));
        }

        LOGGER.info("Min local deletion time: " + SSTableSingleReader.getStats().minLocalDeletionTime);
        LOGGER.info("Max local deletion time: " + SSTableSingleReader.getStats().maxLocalDeletionTime);
        LOGGER.info("SSTable level: " + SSTableSingleReader.getStats().sstableLevel);

        LOGGER.info("=======================================");
        LOGGER.info("Key validator: " + cfMetaData.getKeyValidator());
        Assert.assertTrue(cfMetaData.getKeyValidator() instanceof CompositeType);
        for (ColumnDefinition colDef : cfMetaData.primaryKeyColumns()) {
            LOGGER.info("Primary Key: " + colDef.debugString());
            Assert.assertTrue(colDef.cellValueType() instanceof UTF8Type
                || colDef.cellValueType() instanceof Int32Type);
        }

        for (ColumnDefinition colDef : cfMetaData.partitionKeyColumns()) {
            LOGGER.info("Partition Key ::: " + colDef.debugString());
            Assert.assertEquals(colDef.cellValueType(), UTF8Type.instance);
        }

        LOGGER.info("Clustering comparator: " + cfMetaData.comparator);
        Assert.assertTrue(cfMetaData.comparator.subtype(0) == Int32Type.instance);
        Assert.assertTrue(cfMetaData.comparator.subtype(1) == Int32Type.instance);

        LOGGER.info("Parent Column Family: " + cfMetaData.getParentColumnFamilyName());

        LOGGER.info("Has collection columns? : " + cfMetaData.hasCollectionColumns());
        Assert.assertFalse(cfMetaData.hasCollectionColumns());

        LOGGER.info("Has complex columns? : " + cfMetaData.hasComplexColumns());
        Assert.assertFalse(cfMetaData.hasComplexColumns());

        LOGGER.info("Has dropped collection columns? : " + cfMetaData.hasDroppedCollectionColumns());
        LOGGER.info("Has static columns? : " + cfMetaData.hasStaticColumns());
        Assert.assertTrue(cfMetaData.hasStaticColumns());

        LOGGER.info("Is compound? : " + cfMetaData.isCompound());
        Assert.assertTrue(cfMetaData.isCompound());

        LOGGER.info("Is view? : " + cfMetaData.isView());
        Assert.assertFalse(cfMetaData.isView());

        LOGGER.info("Partitioner: " + cfMetaData.partitioner);
        Assert.assertTrue(cfMetaData.partitioner instanceof Murmur3Partitioner);

        LOGGER.info("Serializers: " + cfMetaData.serializers());

    }

    private void printColumnDefinition(final ColumnDefinition colDef) {
        LOGGER.info("Name: " + colDef.name);
        LOGGER.info("\tcellComparator: " + colDef.cellComparator());
        LOGGER.info("\tType: " + colDef.cellValueType());
        LOGGER.info("\tCell path comparator: " + colDef.cellPathComparator());
        LOGGER.info("\tCell path serializer: " + colDef.cellPathSerializer());
        LOGGER.info("\tPrimary key? :" + colDef.isPrimaryKeyColumn());
        LOGGER.info("\tPartition key? : " + colDef.isPartitionKey());
        LOGGER.info("\tClustering order: " + colDef.clusteringOrder());
        LOGGER.info("\tClustering column? : " + colDef.isClusteringColumn());
        LOGGER.info("\tRegular? :" + colDef.isRegular());
        LOGGER.info("\tType: " + colDef.type);
        LOGGER.info("\tSimple key?: " + colDef.isSimple());
        LOGGER.info("\tStatic key?: " + colDef.isStatic());
        LOGGER.info("+++++++++++++++++++++++++++++++++++++");
    }

    /**
     *  Test on reading table for keyspace and table names.
     */
    @Test
    public void testConstructingKeyspaceAndTable() {
        final String inputSSTableFullPathFileName = CASS3_DATA_DIR + "compressed_bills/mc-2-big-Data.db";

        try {
            final Configuration conf = new Configuration();
            SSTableSingleReader sstableSingleReader =
                    new SSTableSingleReader(inputSSTableFullPathFileName,
                                           "ks1", "table1", conf);

            Assert.assertEquals("ks1", sstableSingleReader.getCfMetaData().ksName);
            Assert.assertEquals("table1", sstableSingleReader.getCfMetaData().cfName);
            Assert.assertEquals(inputSSTableFullPathFileName, sstableSingleReader.getSstableReader().getFilename());

            sstableSingleReader =
                    new SSTableSingleReader(inputSSTableFullPathFileName, conf);
            Assert.assertEquals("keyspace1", sstableSingleReader.getCfMetaData().ksName);
            Assert.assertEquals("compressed_bills", sstableSingleReader.getCfMetaData().cfName);

        } catch (IOException e) {
           Assert.fail(e.getMessage());
        }
    }
}

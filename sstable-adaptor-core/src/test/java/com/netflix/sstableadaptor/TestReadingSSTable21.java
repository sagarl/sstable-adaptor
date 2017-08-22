package com.netflix.sstableadaptor;

import com.netflix.sstableadaptor.sstable.SSTableSingleReader;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.cql3.QueryProcessor;
import org.apache.cassandra.cql3.statements.CFProperties;
import org.apache.cassandra.cql3.statements.CreateTableStatement;
import org.apache.cassandra.cql3.statements.ParsedStatement;
import org.apache.cassandra.db.rows.UnfilteredRowIterator;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.dht.RandomPartitioner;
import org.apache.cassandra.io.sstable.ISSTableScanner;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * These tests are for reading SSTable in Cassandra 2.1.
 * Note that the code would probably work for Cassandra 2.2 files too but we don't test them here
 */
public class TestReadingSSTable21 extends TestBaseSSTableFunSuite {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestReadingSSTable21.class);

    /**
     * Setting up resources prior to running any tests.
     * @throws Exception when we cannot initialize the resources
     */
    @BeforeClass
    public static void setup() throws Exception {
        LOGGER.info("Running TestReadingSSTable21 setup ...");
        TestBaseSSTableFunSuite.setup();
    }

    /**
     * Tear down resources after all tests.
     * @throws Exception when teardown has an issue
     */
    @AfterClass
    public static void teardown() throws Exception {
        LOGGER.info("Tearing TestReadingSSTable21 down ...");
        TestBaseSSTableFunSuite.teardown();
    }

    /**
     *  Test on a local C* 2.1 data
     *      compressed_bills-03c5a7b0643c11e7936273d8df3aeac7/casspactor2-compressed_bills-ka-1-Data.db
     *  with composite partition key.
     */
    @Test
    public void testOnLocalDataCompositePartitionKey() throws IOException {
        final String cql = "CREATE TABLE casspactor2.compressed_bills (\n" +
                "    user text,\n" +
                "    email text,\n" +
                "    expense_id int,\n" +
                "    item_id int,\n" +
                "    account_id text static,\n" +
                "    amount int,\n" +
                "    balance int static,\n" +
                "    name text,\n" +
                "    PRIMARY KEY ((user, email), expense_id, item_id)\n" +
                ") WITH CLUSTERING ORDER BY (expense_id ASC, item_id ASC)\n" +
                "    AND bloom_filter_fp_chance = 0.01\n" +
                "    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}\n" +
                "    AND comment = ''\n" +
                "    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}\n" +
                "    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}\n" +
                "    AND crc_check_chance = 1.0\n" +
                "    AND dclocal_read_repair_chance = 0.1\n" +
                "    AND default_time_to_live = 0\n" +
                "    AND gc_grace_seconds = 864000\n" +
                "    AND max_index_interval = 2048\n" +
                "    AND memtable_flush_period_in_ms = 0\n" +
                "    AND min_index_interval = 128\n" +
                "    AND read_repair_chance = 0.0\n" +
                "    AND speculative_retry = '99PERCENTILE'";

        final String inputSSTableFullPathFileName = CASS21_DATA_DIR + "compressed_bills-03c5a7b0643c11e7936273d8df3aeac7/casspactor2-compressed_bills-ka-1-Data.db";

        final int counter = getRowCount(inputSSTableFullPathFileName, cql, false);

        LOGGER.info("\nCounter: " + counter);
        Assert.assertEquals(15, counter);
    }

    /**
     *  Test on a local C* 2.1 data
     *      compressed_bills-03c5a7b0643c11e7936273d8df3aeac7/casspactor2-compressed_bills-ka-1-Data.db
     *  with composite partition key.
     */
    @Test
    public void testOnLocalDataCompositePartitionKey2() throws IOException {
        final String cql = "CREATE TABLE casspactor2.viewing_history (\n" +
                "    user text PRIMARY KEY,\n" +
                "    movie_id text\n" +
                ") WITH bloom_filter_fp_chance = 0.01\n" +
                "    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}\n" +
                "    AND comment = ''\n" +
                "    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}\n" +
                "    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}\n" +
                "    AND crc_check_chance = 1.0\n" +
                "    AND dclocal_read_repair_chance = 0.1\n" +
                "    AND default_time_to_live = 0\n" +
                "    AND gc_grace_seconds = 864000\n" +
                "    AND max_index_interval = 2048\n" +
                "    AND memtable_flush_period_in_ms = 0\n" +
                "    AND min_index_interval = 128\n" +
                "    AND read_repair_chance = 0.0\n" +
                "    AND speculative_retry = '99PERCENTILE';";

        final String inputSSTableFullPathFileName = CASS21_DATA_DIR + "viewing_history-04d2b570643d11e7936273d8df3aeac7/casspactor2-viewing_history-ka-1-Data.db";

        final int counter = getRowCount(inputSSTableFullPathFileName, cql, false);

        LOGGER.info("\nCounter: " + counter);
        Assert.assertEquals(9, counter);
    }

    /**
     *  Test on a local C* 2.1 data
     *      user_profiles-b355bee0669911e7a49e993faaf28cc4/abc-user_profiles-ka-1-Data.db
     *  with composite partition key.
     */
    @Test
    public void testOnLocalDataThriftCreatedTable() throws IOException {
        final String cql = "CREATE TABLE abc.user_profiles (\n" +
                "    key text PRIMARY KEY,\n" +
                "    email text,\n" +
                "    first_name text,\n" +
                "    last_name text,\n" +
                "    year_of_birth varint\n" +
                ") WITH COMPACT STORAGE\n" +
                "    AND bloom_filter_fp_chance = 0.01\n" +
                "    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}\n" +
                "    AND comment = ''\n" +
                "    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}\n" +
                "    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}\n" +
                "    AND crc_check_chance = 1.0\n" +
                "    AND dclocal_read_repair_chance = 0.1\n" +
                "    AND default_time_to_live = 0\n" +
                "    AND gc_grace_seconds = 864000\n" +
                "    AND max_index_interval = 2048\n" +
                "    AND memtable_flush_period_in_ms = 0\n" +
                "    AND min_index_interval = 128\n" +
                "    AND read_repair_chance = 0.0\n" +
                "    AND speculative_retry = 'NONE';";

        final String inputSSTableFullPathFileName = CASS21_DATA_DIR + "user_profiles-b355bee0669911e7a49e993faaf28cc4/abc-user_profiles-ka-1-Data.db";

        final int counter = getRowCount(inputSSTableFullPathFileName, cql, true);

        LOGGER.info("\nCounter: " + counter);
        Assert.assertEquals(3, counter);
    }

    private int getRowCount(final String inputSSTableFullPathFileName, String cql,
                            boolean isThriftTable) throws IOException {
        LOGGER.info("Input file name: " + inputSSTableFullPathFileName);
        int counter = 0;
        CFMetaData cfMetaData = CFMetaData.compile(cql, "casspactor2");

        final SSTableSingleReader sstableSingleReader =
                new SSTableSingleReader(inputSSTableFullPathFileName, cfMetaData);
        final ISSTableScanner currentScanner =
                sstableSingleReader.getSSTableScanner(Long.MIN_VALUE, Long.MAX_VALUE);

        while (currentScanner.hasNext()) {
            final UnfilteredRowIterator unfilteredRowIterator = currentScanner.next();
            counter += printRowDetails(cfMetaData, unfilteredRowIterator, isThriftTable);
        }

        return counter;
    }

    @Test
    public void testParsingCQLOnCompoundedTable() {
       String cql = "CREATE TABLE keyspace1.compressed_bills (\n" +
               "     user text,\n" +
               "     email text,\n" +
               "     account_id text static,\n" +
               "     balance int static,\n" +
               "     expense_id int,\n" +
               "     item_id int,\n" +
               "     amount int,\n" +
               "     name text,\n" +
               "     PRIMARY KEY ((user, email), expense_id, item_id))";


        ParsedStatement stmt = QueryProcessor.parseStatement(cql);
        stmt.properties.properties.addProperty(CFProperties.KEYSPACE_NAME, "keyspace1");
        stmt.properties.properties.addProperty(CFProperties.PARTITIONER_CLASS, "org.apache.cassandra.dht.Murmur3Partitioner");

        ParsedStatement.Prepared preparedStmt = stmt.prepare();

        CFMetaData cfMetaData = ((CreateTableStatement) preparedStmt.statement).getCFMetaData();
        Assert.assertEquals(cfMetaData.ksName, "keyspace1");
        Assert.assertEquals(cfMetaData.partitioner, Murmur3Partitioner.instance);

    }

    @Test
    public void testParsingCQLOnCompoundedTableWithCFMetaData() {
        String cql = "CREATE TABLE compressed_bills (\n" +
                "     user text,\n" +
                "     email text,\n" +
                "     account_id text static,\n" +
                "     balance int static,\n" +
                "     expense_id int,\n" +
                "     item_id int,\n" +
                "     amount int,\n" +
                "     name text,\n" +
                "     PRIMARY KEY ((user, email), expense_id, item_id))";

        CFMetaData cfMetaData = CFMetaData.compile(cql, "keyspace1");

        Assert.assertEquals(cfMetaData.ksName, "keyspace1");
        Assert.assertEquals(cfMetaData.cfName, "compressed_bills");


        cfMetaData.primaryKeyColumns().forEach(col -> {
            String colName = col.name.toString();
            Assert.assertTrue(colName.equals("user") || colName.equals("email") || colName.equals("expense_id") || colName.equals("item_id"));
        });

        cfMetaData.partitionColumns().forEach(col -> {
            String colName = col.name.toString();
            Assert.assertTrue(colName.equals("account_id") || colName.equals("balance") || colName.equals("amount") || colName.equals("name"));
        });

        cfMetaData.clusteringColumns().forEach(col -> {
            String colName = col.name.toString();
            Assert.assertTrue(colName.equals("expense_id") || colName.equals("item_id"));
        });

        Assert.assertTrue(8 == cfMetaData.allColumns().size());

        Assert.assertFalse(cfMetaData.isStaticCompactTable());
        Assert.assertTrue(cfMetaData.isCompound());
        Assert.assertTrue(cfMetaData.hasStaticColumns());
        Assert.assertTrue(cfMetaData.isCQLTable());
        Assert.assertFalse(cfMetaData.isView());
        Assert.assertFalse(cfMetaData.isCompactTable());
        Assert.assertFalse(cfMetaData.isCounter());
        Assert.assertFalse(cfMetaData.isDense());
        Assert.assertFalse(cfMetaData.isIndex());
        Assert.assertFalse(cfMetaData.isSuper());
        Assert.assertFalse(cfMetaData.isThriftCompatible());
        Assert.assertFalse(cfMetaData.hasCollectionColumns());
        Assert.assertFalse(cfMetaData.hasComplexColumns());

        Assert.assertEquals(cfMetaData.partitioner, Murmur3Partitioner.instance);
    }

    @Test
    public void testParsingCQLOnSimpleTable() throws IOException {
        final String cql = "CREATE TABLE casspactor2.viewing_history (\n" +
                "    user text PRIMARY KEY,\n" +
                "    movie_id text\n" +
                ") WITH bloom_filter_fp_chance = 0.01\n" +
                "    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}\n" +
                "    AND comment = ''\n" +
                "    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}\n" +
                "    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}\n" +
                "    AND crc_check_chance = 1.0\n" +
                "    AND dclocal_read_repair_chance = 0.1\n" +
                "    AND default_time_to_live = 0\n" +
                "    AND gc_grace_seconds = 864000\n" +
                "    AND max_index_interval = 2048\n" +
                "    AND memtable_flush_period_in_ms = 0\n" +
                "    AND min_index_interval = 128\n" +
                "    AND read_repair_chance = 0.0\n" +
                "    AND speculative_retry = '99PERCENTILE';";

        CFMetaData cfMetaData = CFMetaData.compile(cql, "casspactor2");

        Assert.assertEquals(cfMetaData.ksName, "casspactor2");
        Assert.assertEquals(cfMetaData.cfName, "viewing_history");

        cfMetaData.primaryKeyColumns().forEach(col -> {
            String colName = col.name.toString();
            Assert.assertTrue(colName.equals("user"));
        });

        cfMetaData.partitionColumns().forEach(col -> {
            String colName = col.name.toString();
            Assert.assertTrue(colName.equals("movie_id"));
        });

        Assert.assertTrue(cfMetaData.clusteringColumns().isEmpty());
        Assert.assertTrue(2 == cfMetaData.allColumns().size());
        Assert.assertFalse(cfMetaData.isStaticCompactTable());
        Assert.assertTrue(cfMetaData.isCompound());
        Assert.assertFalse(cfMetaData.hasStaticColumns());
        Assert.assertTrue(cfMetaData.isCQLTable());
        Assert.assertFalse(cfMetaData.isView());
        Assert.assertFalse(cfMetaData.isCompactTable());
        Assert.assertFalse(cfMetaData.isCounter());
        Assert.assertFalse(cfMetaData.isDense());
        Assert.assertFalse(cfMetaData.isIndex());
        Assert.assertFalse(cfMetaData.isSuper());
        Assert.assertFalse(cfMetaData.isThriftCompatible());
        Assert.assertFalse(cfMetaData.hasCollectionColumns());
        Assert.assertFalse(cfMetaData.hasComplexColumns());

        Assert.assertEquals(cfMetaData.partitioner, Murmur3Partitioner.instance);
    }

    @Test
    public void testParsingCQLOnThriftCreatedTable() throws IOException {
        final String cql = "CREATE TABLE abc.user_profiles (\n" +
                "    key text PRIMARY KEY,\n" +
                "    email text,\n" +
                "    first_name text,\n" +
                "    last_name text,\n" +
                "    year_of_birth varint\n" +
                ") WITH COMPACT STORAGE\n" +
                "    AND bloom_filter_fp_chance = 0.01\n" +
                "    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}\n" +
                "    AND comment = ''\n" +
                "    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}\n" +
                "    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}\n" +
                "    AND crc_check_chance = 1.0\n" +
                "    AND dclocal_read_repair_chance = 0.1\n" +
                "    AND default_time_to_live = 0\n" +
                "    AND gc_grace_seconds = 864000\n" +
                "    AND max_index_interval = 2048\n" +
                "    AND memtable_flush_period_in_ms = 0\n" +
                "    AND min_index_interval = 128\n" +
                "    AND read_repair_chance = 0.0\n" +
                "    AND speculative_retry = 'NONE';";

        CFMetaData cfMetaData = CFMetaData.compile(cql, "abc");

        Assert.assertEquals(cfMetaData.ksName, "abc");
        Assert.assertEquals(cfMetaData.cfName, "user_profiles");

        cfMetaData.primaryKeyColumns().forEach(col -> {
            String colName = col.name.toString();
            Assert.assertTrue(colName.equals("key") || colName.equals("column1"));
        });

        cfMetaData.partitionColumns().forEach(col -> {
            String colName = col.name.toString();
            Assert.assertTrue(colName.equals("email") ||
                              colName.equals("first_name") ||
                              colName.equals("last_name") ||
                              colName.equals("year_of_birth") ||
                              colName.equals("value"));
        });

        cfMetaData.clusteringColumns().forEach(col -> {
            String colName = col.name.toString();
            Assert.assertTrue(colName.equals("column1"));
        });

        Assert.assertFalse(cfMetaData.clusteringColumns().isEmpty());
        Assert.assertTrue(7 == cfMetaData.allColumns().size());
        Assert.assertTrue(cfMetaData.isStaticCompactTable());
        Assert.assertFalse(cfMetaData.isCompound());
        Assert.assertTrue(cfMetaData.hasStaticColumns());
        Assert.assertFalse(cfMetaData.isCQLTable());
        Assert.assertFalse(cfMetaData.isView());
        Assert.assertTrue(cfMetaData.isCompactTable());
        Assert.assertFalse(cfMetaData.isCounter());
        Assert.assertFalse(cfMetaData.isDense());
        Assert.assertFalse(cfMetaData.isIndex());
        Assert.assertFalse(cfMetaData.isSuper());
        Assert.assertTrue(cfMetaData.isThriftCompatible());
        Assert.assertFalse(cfMetaData.hasCollectionColumns());
        Assert.assertFalse(cfMetaData.hasComplexColumns());

        Assert.assertEquals(cfMetaData.partitioner, Murmur3Partitioner.instance);
    }

}



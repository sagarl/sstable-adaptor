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

import com.netflix.sstableadaptor.util.SSTableUtils;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.config.DatabaseDescriptor;
import org.apache.cassandra.db.rows.Cell;
import org.apache.cassandra.db.rows.Row;
import org.apache.cassandra.db.rows.UnfilteredRowIterator;
import org.apache.cassandra.utils.ByteBufferUtil;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *  Base class for all test suites.
 */
public class TestBaseSSTableFunSuite {

    /** Base directory location. */
    public static final String CASS3_DATA_DIR = "src/test/resources/data/cass3/keyspace1/";
    public static final String CASS21_DATA_DIR = "src/test/resources/data/cass2.1/keyspace1/";

    /** S3 location to contain the input sstable files */
    public static final String S3_INPUT_DIR = System.getenv("S3_INPUT_DIR");

    /** Writable S3 location to store the sstable output files */
    public static final String S3_OUTPUT_DIR = System.getenv("S3_OUTPUT_DIR");

    private static final Logger LOGGER = LoggerFactory.getLogger(TestBaseSSTableFunSuite.class);

    static {
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                    envName,
                    env.get(envName));
        }
    }

    /**
     * Setting up resources.
     * @throws Exception throws Exception when unable to set up
     */
    public static void setup() throws Exception {
        LOGGER.info("Running TestBaseSSTableFunSuite setup ...");
        System.setProperty("hadoop.home.dir", "/");
        DatabaseDescriptor.getRawConfig().file_cache_size_in_mb =
                      Math.min(512, (int) (Runtime.getRuntime().maxMemory() / (4 * 1048576)));
    }

    /**
     * Tearing down resources.
     * @throws Exception throws Exception when unable to tear down
     */
    public static void teardown() throws Exception {
        LOGGER.info("Tearing down ...");
    }

    /**
     *   To get around checkStyle complaint.
     */
    protected void fakeTest() {

    }

    /**
     *  Print out a row with details.
     */
    protected int printRowDetails(final CFMetaData cfMetaData,
                                  final UnfilteredRowIterator unfilteredRowIterator,
                                  final boolean isThriftTable) {
        int counter = 0;
        final ByteBuffer partitionKey = unfilteredRowIterator.partitionKey().getKey();

        LOGGER.info("===================New Row==================================");
        LOGGER.info("Partition key: " + new String(unfilteredRowIterator.partitionKey().getKey().array()));

        final List<Object> list = SSTableUtils.parsePrimaryKey(cfMetaData, partitionKey);
        Assert.assertEquals(cfMetaData.partitionKeyColumns().size(), list.size());
        for (Object val : list) {
            LOGGER.info("\tPartition key val ::::: " + val);
        }

        final Row staticRow = unfilteredRowIterator.staticRow();
        LOGGER.info("static info: " + staticRow.isStatic());

        LOGGER.info("\tStatic: " + staticRow);
        staticRow.cells().forEach(cell -> {
            LOGGER.info("\tName: " + cell.column() + ", value: " + cell.column().cellValueType().compose(cell.value()));
        });

        if (isThriftTable)
            counter++;

        while (unfilteredRowIterator.hasNext()) {
            final Row row = (Row) unfilteredRowIterator.next();
            LOGGER.info("\t------------------New sub-row ------------------------------");
            LOGGER.info("Clustering size: " + row.clustering().size());
            for(int k=0; k<row.clustering().size(); k++)
                LOGGER.info("\tClustering: " + ByteBufferUtil.toInt(row.clustering().get(k)));

            final Iterable<Cell> cells = row.cells();
            final Iterator<Cell> cellsIterator = cells.iterator();
            LOGGER.info("\tCells: ");
            while (cellsIterator.hasNext()) {
                final Cell cell = cellsIterator.next();
                LOGGER.info("Type: " + cell.column().type);
                LOGGER.info("\t\t" + cell.toString());
            }

            if (!isThriftTable)
              counter++;
        }

        return counter;
    }
}

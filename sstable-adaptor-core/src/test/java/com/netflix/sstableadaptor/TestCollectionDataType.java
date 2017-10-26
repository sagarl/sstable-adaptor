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

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCollectionDataType extends TestBaseSSTableFunSuite {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestCollectionDataType.class);

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
     *  This test works on the sstable file.
     *      location: src/test/resources/data/cass3/casspactor/cyclist_teams/mc-1-big-Data.db
     *
     * CREATE TABLE casspactor.cyclist_teams (
     *     id UUID PRIMARY KEY,
     *     lastname text,
     *     firstname text,
     *     teams map<int,text>
     * );
     *
     *
     */
    @Test
    public void testMapDataType() {
        final String inputSSTableFullPathFileName = CASS3_DATA_DIR + "casspactor/cyclist_teams/mc-1-big-Data.db";
        final int counter = getRowCount(inputSSTableFullPathFileName);

        LOGGER.info("\nCounter: " + counter);
        Assert.assertEquals(6, counter);
    }


}

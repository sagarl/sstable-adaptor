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

import org.apache.cassandra.config.DatabaseDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 *  Base class for all test suites.
 */
public class TestBaseSSTableFunSuite {

    /** Base directory location. */
    public static final String DATA_DIR = "src/test/resources/data/";

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
}

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
import com.netflix.sstableadaptor.util.SSTableUtils;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.db.marshal.AbstractType;
import org.apache.cassandra.db.marshal.CompositeType;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Test SSTable utilites.
 */
public class TestSSTableUtils extends TestBaseSSTableFunSuite {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestSSTableUtils.class);

    /**
     * Setting up resources prior to running any tests.
     * @throws Exception when we cannot initialize the resources
     */
    @BeforeClass
    public static void setup() throws Exception {
        LOGGER.info("Running TestSSTableUtils setup ...");
        TestBaseSSTableFunSuite.setup();
    }

    /**
     * Tear down resources after all tests.
     * @throws Exception when teardown has an issue
     */
    @AfterClass
    public static void teardown() throws Exception {
        LOGGER.info("Tearing TestSSTableUtils down ...");
        TestBaseSSTableFunSuite.teardown();
    }

    /**
     * Test on parsing out a ByteBuffer to a list of strings.
     * @throws IOException
     */
    @Test
    public void testParsingCompositeKey() throws IOException {
        final String inputSSTableFullPathFileName = DATA_DIR + "compressed_bills/mc-2-big-Data.db";

        final SSTableSingleReader SSTableSingleReader =
                                new SSTableSingleReader(inputSSTableFullPathFileName);

        final CFMetaData cfMetaData = SSTableSingleReader.getCfMetaData();
        final String user = "user2";
        final String email = "abc@netflix.com";
        final AbstractType<?> keyDataType = cfMetaData.getKeyValidator();

        Assert.assertTrue(keyDataType instanceof CompositeType);
        final ByteBuffer keyInByteBuffer = ((CompositeType) keyDataType).decompose(user, email);

        final List<Object> objects = SSTableUtils.parsePrimaryKey(cfMetaData, keyInByteBuffer);

        Assert.assertEquals(2, objects.size());
        Assert.assertEquals(user, objects.get(0));
        Assert.assertEquals(email, objects.get(1));
    }


    @Test
    public void testRegex1() {
        // String to be scanned to find the pattern.
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(.*)(\\d+)(.*)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        while (m.find( )) {
            System.out.println("Count: " + m.groupCount());
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
        }

        //else {
        //    System.out.println("NO MATCH");
        //}
    }

    @Test
    public void testRegex2() {
        // String to be scanned to find the pattern.
        String line = "CREATE TABLE mytable (\n" +
                "        user text,\n" +
                "        region_id string,\n" +
                "        expense_id int,\n" +
                "        check_id int,\n" +
                "        name text,\n" +
                "        PRIMARY KEY ( ( user  ,  region_id ), expense_id, check_id )\n" +
                "        WITH CLUSTERING ORDER BY ( expense_id DESC )\n" +
                "    )";

        String pattern = "\\bPRIMARY\\s+KEY\\b\\s*\\((.*)\\)";

        String partitionPattern = "\\(\\s*([\\w,\\s]+)\\s*\\)";
        String clusteringPattern = "\\)\\s*,\\s*([\\w,\\s]+)\\s*";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        while (m.find( )) {
            for (int i = 0; i < m.groupCount(); i++) {
                String keysStr = m.group(i + 1);
                System.out.println("Found keysStr: " + keysStr);
                //parsing out the partition key
                Pattern r2 = Pattern.compile(partitionPattern);
                Matcher m2 = r2.matcher(keysStr);
                while (m2.find()) {
                    for(int j=0; j< m2.groupCount(); j++)
                       System.out.println("Found partition key: " + m2.group(j+1));
                }

                Pattern r3 = Pattern.compile(clusteringPattern);
                Matcher m3 = r3.matcher(keysStr);
                while (m3.find()) {
                    for(int j=0; j< m3.groupCount(); j++)
                        System.out.println("Found clustering key: " + m3.group(j+1));
                }
            }
            //System.out.println("Found value: " + m.group(1) );
            //System.out.println("Found value: " + m.group(2) );
        }
        //}else {
        //    System.out.println("NO MATCH");
        //}
    }

    @Test
    public void testRegex3() {
        // String to be scanned to find the pattern.
        String line = "CREATE TABLE mytable (\n" +
                "        user text,\n" +
                "        region_id string,\n" +
                "        expense_id int,\n" +
                "        check_id int,\n" +
                "        name text,\n" +
                "        PRIMARY KEY ( ( user  ,  region_id ), expense_id, check_id )\n" +
                "        WITH CLUSTERING ORDER BY ( expense_id DESC )\n" +
                "    )";

        String pattern = "\\s*(\\w+)\\s*(text|string)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        while (m.find()) {
            for(int i=0; i< m.groupCount(); i++)
                System.out.println("Found value: " + m.group(i+1) );
            //System.out.println("Found value 1: " + m.group(1) );
            //System.out.println("Found value 2: " + m.group(2) );
        }
        //else {
        //    System.out.println("NO MATCH");
        //}
    }
}

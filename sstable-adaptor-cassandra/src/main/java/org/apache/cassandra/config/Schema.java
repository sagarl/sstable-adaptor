/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.cassandra.config;

import org.apache.cassandra.io.sstable.Descriptor;
import org.apache.cassandra.utils.ConcurrentBiMap;
import org.apache.cassandra.utils.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class Schema
{
    private static final Logger logger = LoggerFactory.getLogger(Schema.class);

    public static final Schema instance = new Schema();

    /* metadata map for faster ColumnFamily lookup */
    private final ConcurrentBiMap<Pair<String, String>, UUID> cfIdMap = new ConcurrentBiMap<>();

    private volatile UUID version;

    /**
     * Initialize empty schema object and load the hardcoded system tables
     */
    public Schema()
    {
    }

    /**
     * Given a keyspace name and column family name, get the column family
     * meta data. If the keyspace name or column family name is not valid
     * this function returns null.
     *
     * @param keyspaceName The keyspace name
     * @param cfName The ColumnFamily name
     *
     * @return ColumnFamily Metadata object or null if it wasn't found
     */
    public CFMetaData getCFMetaData(String keyspaceName, String cfName)
    {
        return null;
    }

    /**
     * Get ColumnFamily metadata by its identifier
     *
     * @param cfId The ColumnFamily identifier
     *
     * @return metadata about ColumnFamily
     */
    public CFMetaData getCFMetaData(UUID cfId)
    {
        Pair<String,String> cf = getCF(cfId);
        return (cf == null) ? null : getCFMetaData(cf.left, cf.right);
    }

    public CFMetaData getCFMetaData(Descriptor descriptor)
    {
        return getCFMetaData(descriptor.ksname, descriptor.cfname);
    }

    /* ColumnFamily query/control methods */

    /**
     * @param cfId The identifier of the ColumnFamily to lookup
     * @return The (ksname,cfname) pair for the given id, or null if it has been dropped.
     */
    public Pair<String,String> getCF(UUID cfId)
    {
        return cfIdMap.inverse().get(cfId);
    }

    /**
     * Load individual ColumnFamily Definition to the schema
     * (to make ColumnFamily lookup faster)
     *
     * @param cfm The ColumnFamily definition to load
     */
    public void load(CFMetaData cfm)
    {

    }

    /* Version control */

    /**
     * @return current schema version
     */
    public UUID getVersion()
    {
        return version;
    }


    /**
     * Clear all KS/CF metadata and reset version.
     */
    public synchronized void clear()
    {

    }

}

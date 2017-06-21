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
package org.apache.cassandra.db;

import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.db.commitlog.CommitLogPosition;
import org.apache.cassandra.dht.Range;
import org.apache.cassandra.dht.Token;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.cassandra.metrics.RestorableMeter;
import org.apache.cassandra.utils.Pair;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class SystemKeyspace
{
    private SystemKeyspace()
    {
    }


    public static final String BATCHES = "batches";


    @Deprecated public static final String LEGACY_HINTS = "hints";
    @Deprecated public static final String LEGACY_BATCHLOG = "batchlog";
    @Deprecated public static final String LEGACY_KEYSPACES = "schema_keyspaces";
    @Deprecated public static final String LEGACY_COLUMNFAMILIES = "schema_columnfamilies";
    @Deprecated public static final String LEGACY_COLUMNS = "schema_columns";
    @Deprecated public static final String LEGACY_TRIGGERS = "schema_triggers";
    @Deprecated public static final String LEGACY_USERTYPES = "schema_usertypes";
    @Deprecated public static final String LEGACY_FUNCTIONS = "schema_functions";
    @Deprecated public static final String LEGACY_AGGREGATES = "schema_aggregates";



    private static CFMetaData compile(String name, String description, String schema)
    {
        return null;
    }


    private static volatile Map<UUID, Pair<CommitLogPosition, Long>> truncationRecords;

    public enum BootstrapState
    {
        NEEDS_BOOTSTRAP,
        COMPLETED,
        IN_PROGRESS,
        DECOMMISSIONED
    }



    public static long getTruncatedAt(UUID cfId)
    {
        Pair<CommitLogPosition, Long> record = getTruncationRecord(cfId);
        return record == null ? Long.MIN_VALUE : record.right;
    }

    private static synchronized Pair<CommitLogPosition, Long> getTruncationRecord(UUID cfId)
    {
        return null;
    }


    public static synchronized void updatePreferredIP(InetAddress ep, InetAddress preferred_ip)
    {

    }


    public static synchronized void updateSchemaVersion(UUID version)
    {

    }


    /**
     * Get preferred IP for given endpoint if it is known. Otherwise this returns given endpoint itself.
     *
     * @param ep endpoint address to check
     * @return Preferred IP for given endpoint if present, otherwise returns given ep
     */
    public static InetAddress getPreferredIP(InetAddress ep)
    {
       return null;
    }

    /**
     * Return a map of IP addresses containing a map of dc and rack info
     */
    public static Map<InetAddress, Map<String,String>> loadDcRackInfo()
    {
        return null;
    }


    public static void checkHealth() throws ConfigurationException {
    }

    public static BootstrapState getBootstrapState()
    {
        return null;
    }


    public static boolean isIndexBuilt(String keyspaceName, String indexName)
    {
        return true;
    }

    public static void setIndexBuilt(String keyspaceName, String indexName)
    {

    }

    public static void setIndexRemoved(String keyspaceName, String indexName)
    {

    }

    public static List<String> getBuiltIndexes(String keyspaceName, Set<String> indexNames)
    {
        return null;
    }

    /**
     * Read the host ID from the system keyspace, creating (and storing) one if
     * none exists.
     */
    public static UUID getLocalHostId()
    {
        return null;
    }

    /**
     * Sets the local host ID explicitly.  Should only be called outside of SystemTable when replacing a node.
     */
    public static UUID setLocalHostId(UUID hostId)
    {
        return null;
    }

    /**
     * Gets the stored rack for the local node, or null if none have been set yet.
     */
    public static String getRack()
    {

        return null;
    }

    /**
     * Gets the stored data center for the local node, or null if none have been set yet.
     */
    public static String getDatacenter()
    {

        return null;
    }

    /**
     * Returns a RestorableMeter tracking the average read rate of a particular SSTable, restoring the last-seen rate
     * from values in system.sstable_activity if present.
     * @param keyspace the keyspace the sstable belongs to
     * @param table the table the sstable belongs to
     * @param generation the generation number for the sstable
     */
    public static RestorableMeter getSSTableReadMeter(String keyspace, String table, int generation) {
        return null;
    }

    /**
     * Writes the current read rates for a given SSTable to system.sstable_activity
     */
    public static void persistSSTableReadMeter(String keyspace, String table, int generation, RestorableMeter meter)
    {
    }

    /**
     * Clears persisted read rates from system.sstable_activity for SSTables that have been deleted.
     */
    public static void clearSSTableReadMeter(String keyspace, String table, int generation)
    {

    }

    /**
     * Writes the current partition count and size estimates into SIZE_ESTIMATES_CF
     */
    public static void updateSizeEstimates(String keyspace, String table, Map<Range<Token>, Pair<Long, Long>> estimates)
    {

    }

    /**
     * Clears size estimates for a table (on table drop)
     */
    public static void clearSizeEstimates(String keyspace, String table)
    {

    }


}

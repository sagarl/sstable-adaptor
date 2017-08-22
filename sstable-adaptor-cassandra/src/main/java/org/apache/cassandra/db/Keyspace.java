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

import org.apache.cassandra.config.DatabaseDescriptor;
import org.apache.cassandra.utils.concurrent.OpOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;

/**
 * It represents a Keyspace.
 */
public class Keyspace
{
    private static final Logger logger = LoggerFactory.getLogger(Keyspace.class);

    private static final String TEST_FAIL_WRITES_KS = System.getProperty("cassandra.test.fail_writes_ks", "");
    private static final boolean TEST_FAIL_WRITES = !TEST_FAIL_WRITES_KS.isEmpty();
    private static int TEST_FAIL_MV_LOCKS_COUNT = Integer.getInteger("cassandra.test.fail_mv_locks_count", 0);

    // It is possible to call Keyspace.open without a running daemon, so it makes sense to ensure
    // proper directories here as well as in CassandraDaemon.
    static
    {
        if (DatabaseDescriptor.isDaemonInitialized() || DatabaseDescriptor.isToolInitialized())
            DatabaseDescriptor.createAllDirectories();
    }

    public List<Future<?>> flush()
    {
        return null;
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "(name='" + getName() + "')";
    }

    public String getName()
    {
        return "";
    }
}

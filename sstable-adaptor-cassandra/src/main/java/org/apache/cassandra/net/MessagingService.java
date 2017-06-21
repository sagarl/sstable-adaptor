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
package org.apache.cassandra.net;

import org.apache.cassandra.config.DatabaseDescriptor;

import java.net.UnknownHostException;
import java.util.Map;

public final class MessagingService implements MessagingServiceMBean
{
    public static final String MBEAN_NAME = "org.apache.cassandra.net:type=MessagingService";

    // 8 bits version, so don't waste versions
    public static final int VERSION_12 = 6;
    public static final int VERSION_20 = 7;
    public static final int VERSION_21 = 8;
    public static final int VERSION_22 = 9;
    public static final int VERSION_30 = 10;
    public static final int current_version = VERSION_30;

    public static final String FAILURE_CALLBACK_PARAM = "CAL_BAC";
    public static final byte[] ONE_BYTE = new byte[1];
    public static final String FAILURE_RESPONSE_PARAM = "FAIL";
    public static final String FAILURE_REASON_PARAM = "FAIL_REASON";

    /**
     * we preface every message with this number so the recipient can validate the sender is sane
     */
    public static final int PROTOCOL_MAGIC = 0xCA552DFA;

    private boolean allNodesAtLeast22 = true;
    private boolean allNodesAtLeast30 = true;

    public int getVersion(String endpoint) throws UnknownHostException
    {
        return 1;
    }

    public Map<String, Integer> getLargeMessagePendingTasks()
    {
        return null;
    }


    public Map<String, Long> getLargeMessageCompletedTasks()
    {
        return null;
    }

    public Map<String, Long> getLargeMessageDroppedTasks()
    {
        return null;
    }

    public Map<String, Integer> getSmallMessagePendingTasks()
    {
        return null;
    }

    public Map<String, Long> getSmallMessageCompletedTasks()
    {
        return null;
    }

    public Map<String, Long> getSmallMessageDroppedTasks()
    {
        return null;
    }

    public Map<String, Integer> getGossipMessagePendingTasks()
    {
        return null;
    }

    public Map<String, Long> getGossipMessageCompletedTasks()
    {
        return null;
    }

    public Map<String, Long> getGossipMessageDroppedTasks()
    {
        return null;
    }

    public Map<String, Integer> getDroppedMessages()
    {
        return null;
    }


    public long getTotalTimeouts()
    {
        return 1;
    }

    public Map<String, Long> getTimeoutsPerHost()
    {
        return null;
    }

    public Map<String, Double> getBackPressurePerHost()
    {
        return null;
    }

    @Override
    public void setBackPressureEnabled(boolean enabled)
    {
        DatabaseDescriptor.setBackPressureEnabled(enabled);
    }

    @Override
    public boolean isBackPressureEnabled()
    {
        return DatabaseDescriptor.backPressureEnabled();
    }

}

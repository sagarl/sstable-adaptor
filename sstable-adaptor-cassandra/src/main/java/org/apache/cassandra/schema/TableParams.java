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
package org.apache.cassandra.schema;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.cassandra.utils.BloomCalculations;

import java.nio.ByteBuffer;
import java.util.Map;

import static java.lang.String.format;

public final class TableParams
{
    public static final TableParams DEFAULT = TableParams.builder().build();

    public enum Option
    {
        BLOOM_FILTER_FP_CHANCE,
        CACHING,
        COMMENT,
        COMPACTION,
        COMPRESSION,
        DCLOCAL_READ_REPAIR_CHANCE,
        DEFAULT_TIME_TO_LIVE,
        EXTENSIONS,
        GC_GRACE_SECONDS,
        MAX_INDEX_INTERVAL,
        MEMTABLE_FLUSH_PERIOD_IN_MS,
        MIN_INDEX_INTERVAL,
        READ_REPAIR_CHANCE,
        SPECULATIVE_RETRY,
        CRC_CHECK_CHANCE,
        CDC,
        KEYSPACE_NAME,
        PARTITIONER_CLASS;

        @Override
        public String toString()
        {
            return name().toLowerCase();
        }
    }

    public static final String DEFAULT_COMMENT = "";
    public static final double DEFAULT_READ_REPAIR_CHANCE = 0.0;
    public static final double DEFAULT_DCLOCAL_READ_REPAIR_CHANCE = 0.1;
    public static final int DEFAULT_GC_GRACE_SECONDS = 864000; // 10 days
    public static final int DEFAULT_DEFAULT_TIME_TO_LIVE = 0;
    public static final int DEFAULT_MEMTABLE_FLUSH_PERIOD_IN_MS = 0;
    public static final int DEFAULT_MIN_INDEX_INTERVAL = 128;
    public static final int DEFAULT_MAX_INDEX_INTERVAL = 2048;
    public static final double DEFAULT_CRC_CHECK_CHANCE = 1.0;

    public double bloomFilterFpChance;
    public final double crcCheckChance;
    public final int defaultTimeToLive;
    public final int minIndexInterval;
    public final int maxIndexInterval;

    public final CompressionParams compression;
    public final ImmutableMap<String, ByteBuffer> extensions;
    public final boolean cdc;

    private TableParams(Builder builder)
    {
        bloomFilterFpChance = builder.bloomFilterFpChance;
        crcCheckChance = builder.crcCheckChance;
        defaultTimeToLive = builder.defaultTimeToLive;
        minIndexInterval = builder.minIndexInterval;
        maxIndexInterval = builder.maxIndexInterval;
        compression = builder.compression;
        extensions = builder.extensions;
        cdc = builder.cdc;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static Builder builder(TableParams params)
    {
        return new Builder().bloomFilterFpChance(params.bloomFilterFpChance)
                            .compression(params.compression)
                            .crcCheckChance(params.crcCheckChance)
                            .defaultTimeToLive(params.defaultTimeToLive)
                            .maxIndexInterval(params.maxIndexInterval)
                            .minIndexInterval(params.minIndexInterval)
                            .extensions(params.extensions)
                            .cdc(params.cdc);
    }

    public void validate()
    {
        compression.validate();

        double minBloomFilterFpChanceValue = BloomCalculations.minSupportedBloomFilterFpChance();
        if (bloomFilterFpChance <=  minBloomFilterFpChanceValue || bloomFilterFpChance > 1)
        {
            fail("%s must be larger than %s and less than or equal to 1.0 (got %s)",
                 Option.BLOOM_FILTER_FP_CHANCE,
                 minBloomFilterFpChanceValue,
                 bloomFilterFpChance);
        }


        if (crcCheckChance < 0 || crcCheckChance > 1.0)
        {
            fail("%s must be larger than or equal to 0 and smaller than or equal to 1.0 (got %s)",
                 Option.CRC_CHECK_CHANCE,
                 crcCheckChance);
        }

        if (defaultTimeToLive < 0)
            fail("%s must be greater than or equal to 0 (got %s)", Option.DEFAULT_TIME_TO_LIVE, defaultTimeToLive);

        if (minIndexInterval < 1)
            fail("%s must be greater than or equal to 1 (got %s)", Option.MIN_INDEX_INTERVAL, minIndexInterval);

        if (maxIndexInterval < minIndexInterval)
        {
            fail("%s must be greater than or equal to %s (%s) (got %s)",
                 Option.MAX_INDEX_INTERVAL,
                 Option.MIN_INDEX_INTERVAL,
                 minIndexInterval,
                 maxIndexInterval);
        }

    }

    private static void fail(String format, Object... args)
    {
        throw new ConfigurationException(format(format, args));
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (!(o instanceof TableParams))
            return false;

        TableParams p = (TableParams) o;

        return bloomFilterFpChance == p.bloomFilterFpChance
            && crcCheckChance == p.crcCheckChance
            && defaultTimeToLive == p.defaultTimeToLive
            && minIndexInterval == p.minIndexInterval
            && maxIndexInterval == p.maxIndexInterval
            && compression.equals(p.compression)
            && extensions.equals(p.extensions)
            && cdc == p.cdc;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(bloomFilterFpChance,
                                crcCheckChance,
                                defaultTimeToLive,
                                minIndexInterval,
                                maxIndexInterval,
                                compression,
                                extensions,
                                cdc);
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
                          .add(Option.BLOOM_FILTER_FP_CHANCE.toString(), bloomFilterFpChance)
                          .add(Option.CRC_CHECK_CHANCE.toString(), crcCheckChance)
                          .add(Option.DEFAULT_TIME_TO_LIVE.toString(), defaultTimeToLive)
                          .add(Option.MIN_INDEX_INTERVAL.toString(), minIndexInterval)
                          .add(Option.MAX_INDEX_INTERVAL.toString(), maxIndexInterval)
                          .add(Option.COMPRESSION.toString(), compression)
                          .add(Option.EXTENSIONS.toString(), extensions)
                          .add(Option.CDC.toString(), cdc)
                          .toString();
    }

    public static final class Builder
    {
        private Double bloomFilterFpChance = 0.01;
        public Double crcCheckChance = DEFAULT_CRC_CHECK_CHANCE;
        private int defaultTimeToLive = DEFAULT_DEFAULT_TIME_TO_LIVE;
        private int minIndexInterval = DEFAULT_MIN_INDEX_INTERVAL;
        private int maxIndexInterval = DEFAULT_MAX_INDEX_INTERVAL;
        private CompressionParams compression = CompressionParams.DEFAULT;
        private ImmutableMap<String, ByteBuffer> extensions = ImmutableMap.of();
        private boolean cdc;

        public Builder()
        {
        }

        public TableParams build()
        {
            return new TableParams(this);
        }

        public Builder bloomFilterFpChance(double val)
        {
            bloomFilterFpChance = val;
            return this;
        }

        public Builder crcCheckChance(double val)
        {
            crcCheckChance = val;
            return this;
        }

        public Builder defaultTimeToLive(int val)
        {
            defaultTimeToLive = val;
            return this;
        }

        public Builder minIndexInterval(int val)
        {
            minIndexInterval = val;
            return this;
        }

        public Builder maxIndexInterval(int val)
        {
            maxIndexInterval = val;
            return this;
        }

        public Builder compression(CompressionParams val)
        {
            compression = val;
            return this;
        }

        public Builder cdc(boolean val)
        {
            cdc = val;
            return this;
        }

        public Builder extensions(Map<String, ByteBuffer> val)
        {
            extensions = ImmutableMap.copyOf(val);
            return this;
        }
    }
}

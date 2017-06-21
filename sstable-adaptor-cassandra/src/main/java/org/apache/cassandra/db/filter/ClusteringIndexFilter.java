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
package org.apache.cassandra.db.filter;

import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.db.Clustering;
import org.apache.cassandra.db.ClusteringComparator;
import org.apache.cassandra.db.Slices;
import org.apache.cassandra.db.partitions.CachedPartition;
import org.apache.cassandra.db.partitions.Partition;
import org.apache.cassandra.db.rows.UnfilteredRowIterator;
import org.apache.cassandra.io.sstable.format.SSTableReader;
import org.apache.cassandra.io.util.DataInputPlus;
import org.apache.cassandra.io.util.DataOutputPlus;

import java.io.IOException;

/**
 * A filter that selects a subset of the rows of a given partition by using the "clustering index".
 * <p>
 * In CQL terms, this correspond to the clustering columns selection and correspond to what
 * the storage engine can do without filtering (and without 2ndary indexes). This does not include
 * the restrictions on non-PK columns which can be found in RowFilter.
 */
public interface ClusteringIndexFilter
{
    public static Serializer serializer = AbstractClusteringIndexFilter.serializer;

    public enum Kind
    {
        SLICE (ClusteringIndexSliceFilter.deserializer),
        NAMES (ClusteringIndexNamesFilter.deserializer);

        protected final InternalDeserializer deserializer;

        private Kind(InternalDeserializer deserializer)
        {
            this.deserializer = deserializer;
        }
    }

    static interface InternalDeserializer
    {
        public ClusteringIndexFilter deserialize(DataInputPlus in, int version, CFMetaData metadata, boolean reversed) throws IOException;
    }

    /**
     * Whether the filter query rows in reversed clustering order or not.
     *
     * @return whether the filter query rows in reversed clustering order or not.
     */
    public boolean isReversed();

    /**
     * Returns a filter for continuing the paging of this filter given the last returned clustering prefix.
     *
     * @param comparator the comparator for the table this is a filter for.
     * @param lastReturned the last clustering that was returned for the query we are paging for. The
     * resulting filter will be such that results coming after {@code lastReturned} are returned
     * (where coming after means "greater than" if the filter is not reversed, "lesser than" otherwise;
     * futher, whether the comparison is strict or not depends on {@code inclusive}).
     * @param inclusive whether or not we want to include the {@code lastReturned} in the newly returned
     * page of results.
     *
     * @return a new filter that selects results coming after {@code lastReturned}.
     */
    public ClusteringIndexFilter forPaging(ClusteringComparator comparator, Clustering lastReturned, boolean inclusive);


    /**
     * Whether this filter selects all the row of a partition (it's an "identity" filter).
     *
     * @return whether this filter selects all the row of a partition (it's an "identity" filter).
     */
    public boolean selectsAllPartition();


    public Slices getSlices(CFMetaData metadata);


    public Kind kind();

    public String toString(CFMetaData metadata);
    public String toCQLString(CFMetaData metadata);

    public interface Serializer
    {
        public void serialize(ClusteringIndexFilter filter, DataOutputPlus out, int version) throws IOException;
        public ClusteringIndexFilter deserialize(DataInputPlus in, int version, CFMetaData metadata) throws IOException;
        public long serializedSize(ClusteringIndexFilter filter, int version);
    }
}

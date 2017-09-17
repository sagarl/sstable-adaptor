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
package org.apache.cassandra.db.partitions;

import java.io.IOError;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.*;

import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.db.*;
import org.apache.cassandra.db.filter.ColumnFilter;
import org.apache.cassandra.db.rows.*;
import org.apache.cassandra.db.transform.FilteredPartitions;
import org.apache.cassandra.db.transform.MorePartitions;
import org.apache.cassandra.db.transform.Transformation;
import org.apache.cassandra.io.util.DataInputPlus;
import org.apache.cassandra.io.util.DataOutputPlus;
import org.apache.cassandra.net.MessagingService;
import org.apache.cassandra.utils.MergeIterator;

/**
 * Static methods to work with partition iterators.
 */
public abstract class UnfilteredPartitionIterators
{
    private static final Serializer serializer = new Serializer();

    private static final Comparator<UnfilteredRowIterator> partitionComparator = (p1, p2) -> p1.partitionKey().compareTo(p2.partitionKey());

    private UnfilteredPartitionIterators() {}

    public interface MergeListener
    {
        public UnfilteredRowIterators.MergeListener getRowMergeListener(DecoratedKey partitionKey, List<UnfilteredRowIterator> versions);
        public void close();
    }

    public static UnfilteredPartitionIterator concat(final List<UnfilteredPartitionIterator> iterators)
    {
        if (iterators.size() == 1)
            return iterators.get(0);

        class Extend implements MorePartitions<UnfilteredPartitionIterator>
        {
            int i = 1;
            public UnfilteredPartitionIterator moreContents()
            {
                if (i >= iterators.size())
                    return null;
                return iterators.get(i++);
            }
        }
        return MorePartitions.extend(iterators.get(0), new Extend());
    }


    public static PartitionIterator mergeAndFilter(List<? extends UnfilteredPartitionIterator> iterators,
                                                   int nowInSec, MergeListener listener)
    {
        // TODO: we could have a somewhat faster version if we were to merge the
        // UnfilteredRowIterators directly as RowIterators
        return filter(merge(iterators, nowInSec, listener), nowInSec);
    }

    public static PartitionIterator filter(final UnfilteredPartitionIterator iterator, final int nowInSec)
    {
        return FilteredPartitions.filter(iterator, nowInSec);
    }

    public static UnfilteredPartitionIterator merge(final List<? extends UnfilteredPartitionIterator> iterators,
                                                    final int nowInSec, final MergeListener listener)
    {
        assert listener != null;
        assert !iterators.isEmpty();

        final boolean isForThrift = iterators.get(0).isForThrift();
        final CFMetaData metadata = iterators.get(0).metadata();

        final MergeIterator<UnfilteredRowIterator, UnfilteredRowIterator> merged = MergeIterator.get(iterators,
                partitionComparator,
                new MergeIterator.Reducer<UnfilteredRowIterator, UnfilteredRowIterator>()
        {
            private final List<UnfilteredRowIterator> toMerge = new ArrayList<>(iterators.size());

            private DecoratedKey partitionKey;
            private boolean isReverseOrder;

            public void reduce(int idx, UnfilteredRowIterator current)
            {
                partitionKey = current.partitionKey();
                isReverseOrder = current.isReverseOrder();

                // Note that because the MergeListener cares about it, we want to preserve the index of the iterator.
                // Non-present iterator will thus be set to empty in getReduced.
                toMerge.set(idx, current);
            }

            protected UnfilteredRowIterator getReduced()
            {
                UnfilteredRowIterators.MergeListener rowListener = listener.getRowMergeListener(partitionKey, toMerge);

                // Replace nulls by empty iterators
                for (int i = 0; i < toMerge.size(); i++)
                    if (toMerge.get(i) == null)
                        toMerge.set(i, EmptyIterators.unfilteredRow(metadata, partitionKey, isReverseOrder));

                return UnfilteredRowIterators.merge(toMerge, nowInSec, rowListener);
            }

            protected void onKeyChange()
            {
                toMerge.clear();
                for (int i = 0; i < iterators.size(); i++)
                    toMerge.add(null);
            }
        });

        return new AbstractUnfilteredPartitionIterator()
        {
            public boolean isForThrift()
            {
                return isForThrift;
            }

            public CFMetaData metadata()
            {
                return metadata;
            }

            public boolean hasNext()
            {
                return merged.hasNext();
            }

            public UnfilteredRowIterator next()
            {
                return merged.next();
            }

            @Override
            public void close()
            {
                merged.close();
                listener.close();
            }
        };
    }

    public static UnfilteredPartitionIterator mergeLazily(final List<? extends UnfilteredPartitionIterator> iterators, final int nowInSec)
    {
        assert !iterators.isEmpty();

        if (iterators.size() == 1)
            return iterators.get(0);

        final boolean isForThrift = iterators.get(0).isForThrift();
        final CFMetaData metadata = iterators.get(0).metadata();

        final MergeIterator<UnfilteredRowIterator, UnfilteredRowIterator> merged = MergeIterator.get(iterators, partitionComparator, new MergeIterator.Reducer<UnfilteredRowIterator, UnfilteredRowIterator>()
        {
            private final List<UnfilteredRowIterator> toMerge = new ArrayList<>(iterators.size());

            public void reduce(int idx, UnfilteredRowIterator current)
            {
                toMerge.add(current);
            }

            protected UnfilteredRowIterator getReduced()
            {
                return new LazilyInitializedUnfilteredRowIterator(toMerge.get(0).partitionKey())
                {
                    protected UnfilteredRowIterator initializeIterator()
                    {
                        return UnfilteredRowIterators.merge(toMerge, nowInSec);
                    }
                };
            }

            protected void onKeyChange()
            {
                toMerge.clear();
            }
        });

        return new AbstractUnfilteredPartitionIterator()
        {
            public boolean isForThrift()
            {
                return isForThrift;
            }

            public CFMetaData metadata()
            {
                return metadata;
            }

            public boolean hasNext()
            {
                return merged.hasNext();
            }

            public UnfilteredRowIterator next()
            {
                return merged.next();
            }

            @Override
            public void close()
            {
                merged.close();
            }
        };
    }


    public static Serializer serializerForIntraNode()
    {
        return serializer;
    }



    /**
     * Serialize each UnfilteredSerializer one after the other, with an initial byte that indicates whether
     * we're done or not.
     */
    public static class Serializer
    {
        public void serialize(UnfilteredPartitionIterator iter, ColumnFilter selection, DataOutputPlus out, int version) throws IOException
        {
            assert version >= MessagingService.VERSION_30; // We handle backward compatibility directy in ReadResponse.LegacyRangeSliceReplySerializer

            out.writeBoolean(iter.isForThrift());
            while (iter.hasNext())
            {
                out.writeBoolean(true);
                try (UnfilteredRowIterator partition = iter.next())
                {
                    UnfilteredRowIteratorSerializer.serializer.serialize(partition, selection, out, version);
                }
            }
            out.writeBoolean(false);
        }

        public UnfilteredPartitionIterator deserialize(final DataInputPlus in, final int version, final CFMetaData metadata, final ColumnFilter selection, final SerializationHelper.Flag flag) throws IOException
        {
            assert version >= MessagingService.VERSION_30; // We handle backward compatibility directy in ReadResponse.LegacyRangeSliceReplySerializer
            final boolean isForThrift = in.readBoolean();

            return new AbstractUnfilteredPartitionIterator()
            {
                private UnfilteredRowIterator next;
                private boolean hasNext;
                private boolean nextReturned = true;

                public boolean isForThrift()
                {
                    return isForThrift;
                }

                public CFMetaData metadata()
                {
                    return metadata;
                }

                public boolean hasNext()
                {
                    if (!nextReturned)
                        return hasNext;

                    // We can't answer this until the previously returned iterator has been fully consumed,
                    // so complain if that's not the case.
                    if (next != null && next.hasNext())
                        throw new IllegalStateException("Cannot call hasNext() until the previous iterator has been fully consumed");

                    try
                    {
                        hasNext = in.readBoolean();
                        nextReturned = false;
                        return hasNext;
                    }
                    catch (IOException e)
                    {
                        throw new IOError(e);
                    }
                }

                public UnfilteredRowIterator next()
                {
                    if (nextReturned && !hasNext())
                        throw new NoSuchElementException();

                    try
                    {
                        nextReturned = true;
                        next = UnfilteredRowIteratorSerializer.serializer.deserialize(in, version, metadata, selection, flag);
                        return next;
                    }
                    catch (IOException e)
                    {
                        throw new IOError(e);
                    }
                }

                @Override
                public void close()
                {
                    if (next != null)
                        next.close();
                }
            };
        }
    }
}

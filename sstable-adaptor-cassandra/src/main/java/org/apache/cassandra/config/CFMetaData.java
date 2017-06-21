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

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.cassandra.cql3.ColumnIdentifier;
import org.apache.cassandra.db.*;
import org.apache.cassandra.db.filter.ColumnFilter;
import org.apache.cassandra.db.marshal.*;
import org.apache.cassandra.dht.IPartitioner;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.cassandra.io.util.DataInputPlus;
import org.apache.cassandra.io.util.DataOutputPlus;
import org.apache.cassandra.schema.CompressionParams;
import org.apache.cassandra.schema.TableParams;
import org.apache.cassandra.utils.*;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.github.jamm.Unmetered;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class can be tricky to modify. Please read http://wiki.apache.org/cassandra/ConfigurationNotes for how to do so safely.
 */
@Unmetered
public final class CFMetaData
{
    public enum Flag
    {
        SUPER, COUNTER, DENSE, COMPOUND
    }

    private static final Pattern PATTERN_WORD_CHARS = Pattern.compile("\\w+");

    private static final Logger logger = LoggerFactory.getLogger(CFMetaData.class);

    public static final Serializer serializer = new Serializer();

    //REQUIRED
    public final UUID cfId;                           // internal id, never exposed to user
    public final String ksName;                       // name of keyspace
    public final String cfName;                       // name of this column family
    public final Pair<String, String> ksAndCFName;
    public final byte[] ksAndCFBytes;

    private final ImmutableSet<Flag> flags;
    private final boolean isDense;
    private final boolean isCompound;
    private final boolean isSuper;
    private final boolean isCounter;
    private final boolean isView;
    private final boolean isIndex;

    public volatile ClusteringComparator comparator;  // bytes, long, timeuuid, utf8, etc. This is built directly from clusteringColumns
    public final IPartitioner partitioner;            // partitioner the table uses
    private volatile AbstractType<?> keyValidator;

    private final Serializers serializers;

    // non-final, for now
    public volatile TableParams params = TableParams.DEFAULT;

    private volatile Map<ByteBuffer, DroppedColumn> droppedColumns = new HashMap<>();

    /*
     * All CQL3 columns definition are stored in the columnMetadata map.
     * On top of that, we keep separated collection of each kind of definition, to
     * 1) allow easy access to each kind and 2) for the partition key and
     * clustering key ones, those list are ordered by the "component index" of the
     * elements.
     */
    private volatile Map<ByteBuffer, ColumnDefinition> columnMetadata = new HashMap<>();
    private volatile List<ColumnDefinition> partitionKeyColumns;  // Always of size keyValidator.componentsCount, null padded if necessary
    private volatile List<ColumnDefinition> clusteringColumns;    // Of size comparator.componentsCount or comparator.componentsCount -1, null padded if necessary
    private volatile PartitionColumns partitionColumns;           // Always non-PK, non-clustering columns

    // For dense tables, this alias the single non-PK column the table contains (since it can only have one). We keep
    // that as convenience to access that column more easily (but we could replace calls by partitionColumns().iterator().next()
    // for those tables in practice).
    private volatile ColumnDefinition compactValueColumn;

    //For hot path serialization it's often easier to store this info here
    private volatile ColumnFilter allColumnFilter;

    /*
     * All of these methods will go away once CFMetaData becomes completely immutable.
     */
    public CFMetaData params(TableParams params)
    {
        this.params = params;
        return this;
    }

    public CFMetaData compression(CompressionParams prop)
    {
        params = TableParams.builder(params).compression(prop).build();
        return this;
    }

    private CFMetaData(String keyspace,
                       String name,
                       UUID cfId,
                       boolean isSuper,
                       boolean isCounter,
                       boolean isDense,
                       boolean isCompound,
                       boolean isView,
                       List<ColumnDefinition> partitionKeyColumns,
                       List<ColumnDefinition> clusteringColumns,
                       PartitionColumns partitionColumns,
                       IPartitioner partitioner)
    {
        this.cfId = cfId;
        this.ksName = keyspace;
        this.cfName = name;
        ksAndCFName = Pair.create(keyspace, name);
        byte[] ksBytes = FBUtilities.toWriteUTFBytes(ksName);
        byte[] cfBytes = FBUtilities.toWriteUTFBytes(cfName);
        ksAndCFBytes = Arrays.copyOf(ksBytes, ksBytes.length + cfBytes.length);
        System.arraycopy(cfBytes, 0, ksAndCFBytes, ksBytes.length, cfBytes.length);

        this.isDense = isDense;
        this.isCompound = isCompound;
        this.isSuper = isSuper;
        this.isCounter = isCounter;
        this.isView = isView;

        EnumSet<Flag> flags = EnumSet.noneOf(Flag.class);
        if (isSuper)
            flags.add(Flag.SUPER);
        if (isCounter)
            flags.add(Flag.COUNTER);
        if (isDense)
            flags.add(Flag.DENSE);
        if (isCompound)
            flags.add(Flag.COMPOUND);
        this.flags = Sets.immutableEnumSet(flags);

        isIndex = cfName.contains(".");

        assert partitioner != null : "This assertion failure is probably due to accessing Schema.instance " +
                                     "from client-mode tools - See CASSANDRA-8143.";
        this.partitioner = partitioner;

        // A compact table should always have a clustering
        assert isCQLTable() || !clusteringColumns.isEmpty() : String.format("For table %s.%s, isDense=%b, isCompound=%b, clustering=%s", ksName, cfName, isDense, isCompound, clusteringColumns);

        // All tables should have a partition key
        assert !partitionKeyColumns.isEmpty() : String.format("Have no partition keys for table %s.%s", ksName, cfName);

        this.partitionKeyColumns = partitionKeyColumns;
        this.clusteringColumns = clusteringColumns;
        this.partitionColumns = partitionColumns;

        rebuild();

        //this.resource = DataResource.table(ksName, cfName);
        this.serializers = new Serializers(this);
    }

    // This rebuild informations that are intrinsically duplicate of the table definition but
    // are kept because they are often useful in a different format.
    private void rebuild()
    {
        this.comparator = new ClusteringComparator(extractTypes(clusteringColumns));

        Map<ByteBuffer, ColumnDefinition> newColumnMetadata = Maps.newHashMapWithExpectedSize(partitionKeyColumns.size() + clusteringColumns.size() + partitionColumns.size());

        for (ColumnDefinition def : partitionKeyColumns)
            newColumnMetadata.put(def.name.bytes, def);
        for (ColumnDefinition def : clusteringColumns)
            newColumnMetadata.put(def.name.bytes, def);
        for (ColumnDefinition def : partitionColumns)
            newColumnMetadata.put(def.name.bytes, def);

        this.columnMetadata = newColumnMetadata;

        List<AbstractType<?>> keyTypes = extractTypes(partitionKeyColumns);
        this.keyValidator = keyTypes.size() == 1 ? keyTypes.get(0) : CompositeType.getInstance(keyTypes);

        if (isCompactTable())
            this.compactValueColumn = CompactTables.getCompactValueColumn(partitionColumns, isSuper());

        this.allColumnFilter = ColumnFilter.all(this);
    }

    public static CFMetaData create(String ksName,
                                    String name,
                                    UUID cfId,
                                    boolean isDense,
                                    boolean isCompound,
                                    boolean isSuper,
                                    boolean isCounter,
                                    boolean isView,
                                    List<ColumnDefinition> columns,
                                    IPartitioner partitioner)
    {
        List<ColumnDefinition> partitions = new ArrayList<>();
        List<ColumnDefinition> clusterings = new ArrayList<>();
        PartitionColumns.Builder builder = PartitionColumns.builder();

        for (ColumnDefinition column : columns)
        {
            switch (column.kind)
            {
                case PARTITION_KEY:
                    partitions.add(column);
                    break;
                case CLUSTERING:
                    clusterings.add(column);
                    break;
                default:
                    builder.add(column);
                    break;
            }
        }

        Collections.sort(partitions);
        Collections.sort(clusterings);

        return new CFMetaData(ksName,
                              name,
                              cfId,
                              isSuper,
                              isCounter,
                              isDense,
                              isCompound,
                              isView,
                              partitions,
                              clusterings,
                              builder.build(),
                              partitioner);
    }

    public static List<AbstractType<?>> extractTypes(Iterable<ColumnDefinition> clusteringColumns)
    {
        List<AbstractType<?>> types = new ArrayList<>();
        for (ColumnDefinition def : clusteringColumns)
            types.add(def.type);
        return types;
    }

    public Set<Flag> flags()
    {
        return flags;
    }

    /**
     * There is a couple of places in the code where we need a CFMetaData object and don't have one readily available
     * and know that only the keyspace and name matter. This creates such "fake" metadata. Use only if you know what
     * you're doing.
     */
    public static CFMetaData createFake(String keyspace, String name)
    {
        return CFMetaData.Builder.create(keyspace, name).addPartitionKey("key", BytesType.instance).build();
    }

    private static List<ColumnDefinition> copy(List<ColumnDefinition> l)
    {
        List<ColumnDefinition> copied = new ArrayList<>(l.size());
        for (ColumnDefinition cd : l)
            copied.add(cd.copy());
        return copied;
    }

    /**
     * true if this CFS contains secondary index data.
     */
    public boolean isIndex()
    {
        return isIndex;
    }

    public DecoratedKey decorateKey(ByteBuffer key)
    {
        return partitioner.decorateKey(key);
    }

    public Map<ByteBuffer, ColumnDefinition> getColumnMetadata()
    {
        return columnMetadata;
    }

    /**
     *
     * @return The name of the parent cf if this is a seconday index
     */
    public String getParentColumnFamilyName()
    {
        return isIndex ? cfName.substring(0, cfName.indexOf('.')) : null;
    }


    public AbstractType<?> getColumnDefinitionNameComparator(ColumnDefinition.Kind kind)
    {
        return (isSuper() && kind == ColumnDefinition.Kind.REGULAR) || (isStaticCompactTable() && kind == ColumnDefinition.Kind.STATIC)
             ? thriftColumnNameType()
             : UTF8Type.instance;
    }

    public AbstractType<?> getKeyValidator()
    {
        return keyValidator;
    }

    public Collection<ColumnDefinition> allColumns()
    {
        return columnMetadata.values();
    }

    // An iterator over all column definitions but that respect the order of a SELECT *.
    // This also "hide" the clustering/regular columns for a non-CQL3 non-dense table for backward compatibility
    // sake (those are accessible through thrift but not through CQL currently).
    public Iterator<ColumnDefinition> allColumnsInSelectOrder()
    {
        final boolean isStaticCompactTable = isStaticCompactTable();
        final boolean noNonPkColumns = isCompactTable() && CompactTables.hasEmptyCompactValue(this);
        return new AbstractIterator<ColumnDefinition>()
        {
            private final Iterator<ColumnDefinition> partitionKeyIter = partitionKeyColumns.iterator();
            private final Iterator<ColumnDefinition> clusteringIter = isStaticCompactTable ? Collections.<ColumnDefinition>emptyIterator() : clusteringColumns.iterator();
            private final Iterator<ColumnDefinition> otherColumns = noNonPkColumns
                                                                  ? Collections.<ColumnDefinition>emptyIterator()
                                                                  : (isStaticCompactTable
                                                                     ?  partitionColumns.statics.selectOrderIterator()
                                                                     :  partitionColumns.selectOrderIterator());

            protected ColumnDefinition computeNext()
            {
                if (partitionKeyIter.hasNext())
                    return partitionKeyIter.next();

                if (clusteringIter.hasNext())
                    return clusteringIter.next();

                return otherColumns.hasNext() ? otherColumns.next() : endOfData();
            }
        };
    }

    public Iterable<ColumnDefinition> primaryKeyColumns()
    {
        return Iterables.concat(partitionKeyColumns, clusteringColumns);
    }

    public List<ColumnDefinition> partitionKeyColumns()
    {
        return partitionKeyColumns;
    }

    public List<ColumnDefinition> clusteringColumns()
    {
        return clusteringColumns;
    }

    public PartitionColumns partitionColumns()
    {
        return partitionColumns;
    }

    public ColumnDefinition compactValueColumn()
    {
        return compactValueColumn;
    }

    public ClusteringComparator getKeyValidatorAsClusteringComparator()
    {
        boolean isCompound = keyValidator instanceof CompositeType;
        List<AbstractType<?>> types = isCompound
                                    ? ((CompositeType) keyValidator).types
                                    : Collections.<AbstractType<?>>singletonList(keyValidator);
        return new ClusteringComparator(types);
    }

    public static ByteBuffer serializePartitionKey(ClusteringPrefix keyAsClustering)
    {
        // TODO: we should stop using Clustering for partition keys. Maybe we can add
        // a few methods to DecoratedKey so we don't have to (note that while using a Clustering
        // allows to use buildBound(), it's actually used for partition keys only when every restriction
        // is an equal, so we could easily create a specific method for keys for that.
        if (keyAsClustering.size() == 1)
            return keyAsClustering.get(0);

        ByteBuffer[] values = new ByteBuffer[keyAsClustering.size()];
        for (int i = 0; i < keyAsClustering.size(); i++)
            values[i] = keyAsClustering.get(i);
        return CompositeType.build(values);
    }

    public Map<ByteBuffer, DroppedColumn> getDroppedColumns()
    {
        return droppedColumns;
    }

    public ColumnDefinition getDroppedColumnDefinition(ByteBuffer name)
    {
        return getDroppedColumnDefinition(name, false);
    }

    /**
     * Returns a "fake" ColumnDefinition corresponding to the dropped column {@code name}
     * of {@code null} if there is no such dropped column.
     *
     * @param name - the column name
     * @param isStatic - whether the column was a static column, if known
     */
    public ColumnDefinition getDroppedColumnDefinition(ByteBuffer name, boolean isStatic)
    {
        DroppedColumn dropped = droppedColumns.get(name);
        if (dropped == null)
            return null;

        // We need the type for deserialization purpose. If we don't have the type however,
        // it means that it's a dropped column from before 3.0, and in that case using
        // BytesType is fine for what we'll be using it for, even if that's a hack.
        AbstractType<?> type = dropped.type == null ? BytesType.instance : dropped.type;
        return isStatic
               ? ColumnDefinition.staticDef(this, name, type)
               : ColumnDefinition.regularDef(this, name, type);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (!(o instanceof CFMetaData))
            return false;

        CFMetaData other = (CFMetaData) o;

        return Objects.equal(cfId, other.cfId)
            && Objects.equal(flags, other.flags)
            && Objects.equal(ksName, other.ksName)
            && Objects.equal(cfName, other.cfName)
            && Objects.equal(params, other.params)
            && Objects.equal(comparator, other.comparator)
            && Objects.equal(keyValidator, other.keyValidator)
            && Objects.equal(columnMetadata, other.columnMetadata)
            && Objects.equal(droppedColumns, other.droppedColumns);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(29, 1597)
            .append(cfId)
            .append(ksName)
            .append(cfName)
            .append(flags)
            .append(comparator)
            .append(params)
            .append(keyValidator)
            .append(columnMetadata)
            .append(droppedColumns)
            .toHashCode();
    }

    public void validateCompatibility(CFMetaData cfm) throws ConfigurationException
    {
        // validate
        if (!cfm.ksName.equals(ksName))
            throw new ConfigurationException(String.format("Keyspace mismatch (found %s; expected %s)",
                                                           cfm.ksName, ksName));
        if (!cfm.cfName.equals(cfName))
            throw new ConfigurationException(String.format("Column family mismatch (found %s; expected %s)",
                                                           cfm.cfName, cfName));
        if (!cfm.cfId.equals(cfId))
            throw new ConfigurationException(String.format("Column family ID mismatch (found %s; expected %s)",
                                                           cfm.cfId, cfId));
        if (!cfm.flags.equals(flags))
            throw new ConfigurationException("types do not match.");
    }


    /**
     * Returns the ColumnDefinition for {@code name}.
     */
    public ColumnDefinition getColumnDefinition(ColumnIdentifier name)
    {
        return columnMetadata.get(name.bytes);
    }

    // In general it is preferable to work with ColumnIdentifier to make it
    // clear that we are talking about a CQL column, not a cell name, but there
    // is a few cases where all we have is a ByteBuffer (when dealing with IndexExpression
    // for instance) so...
    public ColumnDefinition getColumnDefinition(ByteBuffer name)
    {
        return columnMetadata.get(name);
    }

    public static boolean isNameValid(String name)
    {
        return name != null && !name.isEmpty()
               && name.length() <= SchemaConstants.NAME_LENGTH && PATTERN_WORD_CHARS.matcher(name).matches();
    }

    // The comparator to validate the definition name with thrift.
    public AbstractType<?> thriftColumnNameType()
    {
        if (isSuper())
        {
            ColumnDefinition def = compactValueColumn();
            assert def != null && def.type instanceof MapType;
            return ((MapType)def.type).nameComparator();
        }

        assert isStaticCompactTable();
        return clusteringColumns.get(0).type;
    }

    public CFMetaData addColumnDefinition(ColumnDefinition def) throws ConfigurationException
    {
        if (columnMetadata.containsKey(def.name.bytes))
            throw new ConfigurationException(String.format("Cannot add column %s, a column with the same name already exists", def.name));

        return addOrReplaceColumnDefinition(def);
    }

    // This method doesn't check if a def of the same name already exist and should only be used when we
    // know this cannot happen.
    public CFMetaData addOrReplaceColumnDefinition(ColumnDefinition def)
    {
        // Adds the definition and rebuild what is necessary. We could call rebuild() but it's not too hard to
        // only rebuild the necessary bits.
        switch (def.kind)
        {
            case PARTITION_KEY:
                partitionKeyColumns.set(def.position(), def);
                break;
            case CLUSTERING:
                clusteringColumns.set(def.position(), def);
                break;
            case REGULAR:
            case STATIC:
                PartitionColumns.Builder builder = PartitionColumns.builder();
                for (ColumnDefinition column : partitionColumns)
                    if (!column.name.equals(def.name))
                        builder.add(column);
                builder.add(def);
                partitionColumns = builder.build();
                // If dense, we must have modified the compact value since that's the only one we can have.
                if (isDense())
                    this.compactValueColumn = def;
                break;
        }
        this.columnMetadata.put(def.name.bytes, def);
        return this;
    }

    public boolean isCQLTable()
    {
        return !isSuper() && !isDense() && isCompound();
    }

    public boolean isCompactTable()
    {
        return !isCQLTable();
    }

    public boolean isStaticCompactTable()
    {
        return !isSuper() && !isDense() && !isCompound();
    }

    /**
     * Returns whether this CFMetaData can be returned to thrift.
     */
    public boolean isThriftCompatible()
    {
        return isCompactTable();
    }

    public boolean hasStaticColumns()
    {
        return !partitionColumns.statics.isEmpty();
    }

    public boolean hasCollectionColumns()
    {
        for (ColumnDefinition def : partitionColumns())
            if (def.type instanceof CollectionType && def.type.isMultiCell())
                return true;
        return false;
    }

    public boolean hasComplexColumns()
    {
        for (ColumnDefinition def : partitionColumns())
            if (def.isComplex())
                return true;
        return false;
    }

    public boolean hasDroppedCollectionColumns()
    {
        for (DroppedColumn def : getDroppedColumns().values())
            if (def.type instanceof CollectionType && def.type.isMultiCell())
                return true;
        return false;
    }

    public boolean isSuper()
    {
        return isSuper;
    }

    public boolean isCounter()
    {
        return isCounter;
    }

    // We call dense a CF for which each component of the comparator is a clustering column, i.e. no
    // component is used to store a regular column names. In other words, non-composite static "thrift"
    // and CQL3 CF are *not* dense.
    public boolean isDense()
    {
        return isDense;
    }

    public boolean isCompound()
    {
        return isCompound;
    }

    public boolean isView()
    {
        return isView;
    }

    public Serializers serializers()
    {
        return serializers;
    }

    public AbstractType<?> makeLegacyDefaultValidator()
    {
        return isCounter()
             ? CounterColumnType.instance
             : (isCompactTable() ? compactValueColumn().type : BytesType.instance);
    }

    public static Set<Flag> flagsFromStrings(Set<String> strings)
    {
        return strings.stream()
                      .map(String::toUpperCase)
                      .map(Flag::valueOf)
                      .collect(Collectors.toSet());
    }

    public static Set<String> flagsToStrings(Set<Flag> flags)
    {
        return flags.stream()
                    .map(Flag::toString)
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());
    }


    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
            .append("cfId", cfId)
            .append("ksName", ksName)
            .append("cfName", cfName)
            .append("flags", flags)
            .append("params", params)
            .append("comparator", comparator)
            .append("partitionColumns", partitionColumns)
            .append("partitionKeyColumns", partitionKeyColumns)
            .append("clusteringColumns", clusteringColumns)
            .append("keyValidator", keyValidator)
            .append("columnMetadata", columnMetadata.values())
            .append("droppedColumns", droppedColumns)
            .toString();
    }

    public static class Builder
    {
        private final String keyspace;
        private final String table;
        private final boolean isDense;
        private final boolean isCompound;
        private final boolean isSuper;
        private final boolean isCounter;
        private final boolean isView;
        private Optional<IPartitioner> partitioner;

        private UUID tableId;

        private final List<Pair<ColumnIdentifier, AbstractType>> partitionKeys = new ArrayList<>();
        private final List<Pair<ColumnIdentifier, AbstractType>> clusteringColumns = new ArrayList<>();
        private final List<Pair<ColumnIdentifier, AbstractType>> staticColumns = new ArrayList<>();
        private final List<Pair<ColumnIdentifier, AbstractType>> regularColumns = new ArrayList<>();

        private Builder(String keyspace, String table, boolean isDense, boolean isCompound, boolean isSuper, boolean isCounter, boolean isView)
        {
            this.keyspace = keyspace;
            this.table = table;
            this.isDense = isDense;
            this.isCompound = isCompound;
            this.isSuper = isSuper;
            this.isCounter = isCounter;
            this.isView = isView;
            this.partitioner = Optional.empty();
        }

        public static Builder create(String keyspace, String table)
        {
            return create(keyspace, table, false, true, false);
        }

        public static Builder create(String keyspace, String table, boolean isDense, boolean isCompound, boolean isCounter)
        {
            return create(keyspace, table, isDense, isCompound, false, isCounter);
        }

        public static Builder create(String keyspace, String table, boolean isDense, boolean isCompound, boolean isSuper, boolean isCounter)
        {
            return new Builder(keyspace, table, isDense, isCompound, isSuper, isCounter, false);
        }

        public static Builder createView(String keyspace, String table)
        {
            return new Builder(keyspace, table, false, true, false, false, true);
        }

        public static Builder createDense(String keyspace, String table, boolean isCompound, boolean isCounter)
        {
            return create(keyspace, table, true, isCompound, isCounter);
        }

        public static Builder createSuper(String keyspace, String table, boolean isCounter)
        {
            return create(keyspace, table, false, false, true, isCounter);
        }

        public Builder withPartitioner(IPartitioner partitioner)
        {
            this.partitioner = Optional.ofNullable(partitioner);
            return this;
        }

        public Builder withId(UUID tableId)
        {
            this.tableId = tableId;
            return this;
        }

        public Builder addPartitionKey(String name, AbstractType type)
        {
            return addPartitionKey(ColumnIdentifier.getInterned(name, false), type);
        }

        public Builder addPartitionKey(ColumnIdentifier name, AbstractType type)
        {
            this.partitionKeys.add(Pair.create(name, type));
            return this;
        }

        public Builder addClusteringColumn(String name, AbstractType type)
        {
            return addClusteringColumn(ColumnIdentifier.getInterned(name, false), type);
        }

        public Builder addClusteringColumn(ColumnIdentifier name, AbstractType type)
        {
            this.clusteringColumns.add(Pair.create(name, type));
            return this;
        }

        public Builder addRegularColumn(String name, AbstractType type)
        {
            return addRegularColumn(ColumnIdentifier.getInterned(name, false), type);
        }

        public Builder addRegularColumn(ColumnIdentifier name, AbstractType type)
        {
            this.regularColumns.add(Pair.create(name, type));
            return this;
        }

        public boolean hasRegulars()
        {
            return !this.regularColumns.isEmpty();
        }

        public Builder addStaticColumn(String name, AbstractType type)
        {
            return addStaticColumn(ColumnIdentifier.getInterned(name, false), type);
        }

        public Builder addStaticColumn(ColumnIdentifier name, AbstractType type)
        {
            this.staticColumns.add(Pair.create(name, type));
            return this;
        }

        public CFMetaData build()
        {
            if (tableId == null)
                tableId = UUIDGen.getTimeUUID();

            List<ColumnDefinition> partitions = new ArrayList<>(partitionKeys.size());
            List<ColumnDefinition> clusterings = new ArrayList<>(clusteringColumns.size());
            PartitionColumns.Builder builder = PartitionColumns.builder();

            for (int i = 0; i < partitionKeys.size(); i++)
            {
                Pair<ColumnIdentifier, AbstractType> p = partitionKeys.get(i);
                partitions.add(new ColumnDefinition(keyspace, table, p.left, p.right, i, ColumnDefinition.Kind.PARTITION_KEY));
            }

            for (int i = 0; i < clusteringColumns.size(); i++)
            {
                Pair<ColumnIdentifier, AbstractType> p = clusteringColumns.get(i);
                clusterings.add(new ColumnDefinition(keyspace, table, p.left, p.right, i, ColumnDefinition.Kind.CLUSTERING));
            }

            for (Pair<ColumnIdentifier, AbstractType> p : regularColumns)
                builder.add(new ColumnDefinition(keyspace, table, p.left, p.right, ColumnDefinition.NO_POSITION, ColumnDefinition.Kind.REGULAR));

            for (Pair<ColumnIdentifier, AbstractType> p : staticColumns)
                builder.add(new ColumnDefinition(keyspace, table, p.left, p.right, ColumnDefinition.NO_POSITION, ColumnDefinition.Kind.STATIC));

            return new CFMetaData(keyspace,
                                  table,
                                  tableId,
                                  isSuper,
                                  isCounter,
                                  isDense,
                                  isCompound,
                                  isView,
                                  partitions,
                                  clusterings,
                                  builder.build(),
                                  partitioner.orElseGet(DatabaseDescriptor::getPartitioner));
        }
    }

    public static class Serializer
    {
        public void serialize(CFMetaData metadata, DataOutputPlus out, int version) throws IOException
        {
            UUIDSerializer.serializer.serialize(metadata.cfId, out, version);
        }

        public CFMetaData deserialize(DataInputPlus in, int version) throws IOException
        {
            UUID cfId = UUIDSerializer.serializer.deserialize(in, version);
            CFMetaData metadata = Schema.instance.getCFMetaData(cfId);
            if (metadata == null)
            {
                String message = String.format("Couldn't find table for cfId %s. If a table was just " +
                        "created, this is likely due to the schema not being fully propagated.  Please wait for schema " +
                        "agreement on table creation.", cfId);
                throw new UnknownColumnFamilyException(message, cfId);
            }

            return metadata;
        }

        public long serializedSize(CFMetaData metadata, int version)
        {
            return UUIDSerializer.serializer.serializedSize(metadata.cfId, version);
        }
    }

    public static class DroppedColumn
    {
        // we only allow dropping REGULAR columns, from CQL-native tables, so the names are always of UTF8Type
        public final String name;
        public final AbstractType<?> type;

        // drop timestamp, in microseconds, yet with millisecond granularity
        public final long droppedTime;

        public DroppedColumn(String name, AbstractType<?> type, long droppedTime)
        {
            this.name = name;
            this.type = type;
            this.droppedTime = droppedTime;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;

            if (!(o instanceof DroppedColumn))
                return false;

            DroppedColumn dc = (DroppedColumn) o;

            return name.equals(dc.name) && type.equals(dc.type) && droppedTime == dc.droppedTime;
        }

        @Override
        public int hashCode()
        {
            return Objects.hashCode(name, type, droppedTime);
        }

        @Override
        public String toString()
        {
            return MoreObjects.toStringHelper(this)
                              .add("name", name)
                              .add("type", type)
                              .add("droppedTime", droppedTime)
                              .toString();
        }
    }
}

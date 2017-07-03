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

package com.netflix.sstableadaptor.util;


import com.netflix.sstableadaptor.config.CassandraTable;
import com.netflix.sstableadaptor.sstable.SSTableReader;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.config.ColumnDefinition;
import org.apache.cassandra.db.marshal.AbstractType;
import org.apache.cassandra.db.marshal.CompositeType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 *  Utilities on a sstable file.
 *  @author mdo
 */
public final class SSTableUtils {

    /**
     * Parse the list of values out of a composite key.
     * @param cfMetaData CFMetaData of a given table
     * @param partitionKey a byte buffer of a composite partition key
     * @return a list of value objects corresponding to the key declaration and the order
     */
    public static List<Object> parsePrimaryKey(final CFMetaData cfMetaData, final ByteBuffer partitionKey) {
        final List<Object> keyValue = new ArrayList<>(cfMetaData.partitionKeyColumns().size());
        final AbstractType<?> keyDataType = cfMetaData.getKeyValidator();

        if (keyDataType instanceof CompositeType) {
            final ByteBuffer[] components = ((CompositeType) cfMetaData.getKeyValidator()).split(partitionKey);
            cfMetaData.partitionKeyColumns();
            for (int i = 0; i < cfMetaData.partitionKeyColumns().size(); i++) {
                final ColumnDefinition colDef = cfMetaData.partitionKeyColumns().get(i);
                //logDebug("Partition Key ::: " + colDef.debugString)
                keyValue.add(colDef.cellValueType().compose(components[i]));
            }
        } else {
            final ColumnDefinition colDef = cfMetaData.partitionKeyColumns().get(0);
            keyValue.add(colDef.cellValueType().compose(partitionKey));
        }

        return keyValue;
    }

    public static CFMetaData loadCFMetaData(String dbFile,
                                            CassandraTable cassTable,
                                            List<String> partitionKeyNames,
                                            List<String> clusteringKeyNames) throws IOException {
        return SSTableReader.metaDataFromSSTable(dbFile,
                cassTable.getKeyspaceName(),
                cassTable.getTableName(),
                partitionKeyNames,
                clusteringKeyNames);
    }

}

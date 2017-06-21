/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cassandra.cql3.selection;

import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.config.ColumnDefinition;
import org.apache.cassandra.cql3.AssignmentTestable;
import org.apache.cassandra.cql3.ColumnSpecification;
import org.apache.cassandra.db.marshal.AbstractType;

import java.util.List;

public interface Selectable extends AssignmentTestable
{
    /**
     * The type of the {@code Selectable} if it can be infered.
     *
     * @param keyspace the keyspace on which the statement for which this is a
     * {@code Selectable} is on.
     * @return the type of this {@code Selectable} if inferrable, or {@code null}
     * otherwise (for instance, the type isn't inferable for a bind marker. Even for
     * literals, the exact type is not inferrable since they are valid for many
     * different types and so this will return {@code null} too).
     */
    public AbstractType<?> getExactTypeIfKnown(String keyspace);

    // Term.Raw overrides this since some literals can be WEAKLY_ASSIGNABLE
    default public TestResult testAssignment(String keyspace, ColumnSpecification receiver)
    {
        AbstractType<?> type = getExactTypeIfKnown(keyspace);
        return type == null ? TestResult.NOT_ASSIGNABLE : type.testAssignment(keyspace, receiver);
    }

    default int addAndGetIndex(ColumnDefinition def, List<ColumnDefinition> l)
    {
        int idx = l.indexOf(def);
        if (idx < 0)
        {
            idx = l.size();
            l.add(def);
        }
        return idx;
    }

    public static abstract class Raw
    {
        public abstract Selectable prepare(CFMetaData cfm);

        /**
         * Returns true if any processing is performed on the selected column.
         **/
        public boolean processesSelection()
        {
            // ColumnIdentifier is the only case that returns false and override this
            return true;
        }
    }

}

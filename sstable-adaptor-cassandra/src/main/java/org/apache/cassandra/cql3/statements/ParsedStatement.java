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
package org.apache.cassandra.cql3.statements;

import org.apache.cassandra.cql3.CQLStatement;
import org.apache.cassandra.cql3.ColumnIdentifier;
import org.apache.cassandra.cql3.ColumnSpecification;
import org.apache.cassandra.cql3.VariableSpecifications;
import org.apache.cassandra.cql3.functions.Function;
import org.apache.cassandra.exceptions.RequestValidationException;

import java.util.Collections;
import java.util.List;

public abstract class ParsedStatement
{
    private VariableSpecifications variables;
    public final CFProperties properties = new CFProperties();

    public VariableSpecifications getBoundVariables()
    {
        return variables;
    }

    // Used by the parser and preparable statement
    public void setBoundVariables(List<ColumnIdentifier> boundNames)
    {
        this.variables = new VariableSpecifications(boundNames);
    }

    public void setBoundVariables(VariableSpecifications variables)
    {
        this.variables = variables;
    }

    public abstract Prepared prepare() throws RequestValidationException;

    public static class Prepared
    {
        public String rawCQLStatement;

        public final CQLStatement statement;
        public final List<ColumnSpecification> boundNames;

        protected Prepared(CQLStatement statement, List<ColumnSpecification> boundNames)
        {
            this.statement = statement;
            this.boundNames = boundNames;
            this.rawCQLStatement = "";
        }

        public Prepared(CQLStatement statement)
        {
            this(statement, Collections.<ColumnSpecification>emptyList());
        }
    }

    public Iterable<Function> getFunctions()
    {
        return Collections.emptyList();
    }
}

package com.netflix.sstableadaptor.config;


import org.apache.cassandra.schema.CompressionParams;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CassandraTable {
    private String clusterName;
    private String keyspaceName;
    private String tableName;
    private Map<String, String> others;
    private List<String> partitionKeyNames;
    private List<String> clusteringKeyNames;
    private CassandraTableBuilder builder;

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getKeyspaceName() {
        return keyspaceName;
    }

    public void setKeyspaceName(String keyspaceName) {
        this.keyspaceName = keyspaceName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, String> getOthers() {
        return others;
    }

    public void setOthers(Map<String, String> others) {
        this.others = others;
    }

    public CompressionParams getCompressionParams() {
        return CompressionParams.DEFAULT;
    }

    public List<String> getPartitionKeyNames() {
        return partitionKeyNames;
    }

    public List<String> getClusteringKeyNames() {
        return clusteringKeyNames;
    }

    public static class CassandraTableBuilder {
        private String clusterName;
        private String keyspaceName;
        private String tableName;
        private List<String> partitionKeyNames;
        private List<String> clusteringKeyNames;
        private Map<String, String> others = new HashMap<String, String>();

        public CassandraTableBuilder withClusterName(String clusterName) {
            this.clusterName = clusterName;
            return this;
        }

        public CassandraTableBuilder withKeyspaceName(String keyspaceName) {
            this.keyspaceName = keyspaceName;
            return this;
        }

        public CassandraTableBuilder withTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public CassandraTableBuilder withProperties(Map<String, String> properties) {
            this.others.putAll(properties);
            return this;
        }

        public CassandraTableBuilder withParitionKeyNames(List<String > keyNames) {
            this.partitionKeyNames = keyNames;
            return this;
        }

        public CassandraTableBuilder withClusteringKeyNames(List<String> keyNames) {
            this.clusteringKeyNames = keyNames;
            return this;
        }

        public CassandraTable build() {
            CassandraTable retVal = new CassandraTable();
            retVal.clusterName = clusterName;
            retVal.keyspaceName = keyspaceName;
            retVal.tableName = tableName;
            retVal.others = others;
            retVal.partitionKeyNames = partitionKeyNames == null? Collections.<String>emptyList() : partitionKeyNames;
            retVal.clusteringKeyNames = clusteringKeyNames == null? Collections.<String>emptyList() : clusteringKeyNames;

            //Todo: set the compression codec here

            return retVal;
        }
    }
}

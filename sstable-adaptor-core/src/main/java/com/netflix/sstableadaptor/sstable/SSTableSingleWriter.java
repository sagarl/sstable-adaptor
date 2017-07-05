package com.netflix.sstableadaptor.sstable;


import com.netflix.sstableadaptor.config.CassandraTable;
import com.netflix.sstableadaptor.util.SSTableUtils;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.db.SerializationHeader;
import org.apache.cassandra.db.rows.EncodingStats;
import org.apache.cassandra.db.rows.UnfilteredRowIterator;
import org.apache.cassandra.io.sstable.Descriptor;
import org.apache.cassandra.io.sstable.SSTableTxnWriter;
import org.apache.cassandra.io.sstable.format.SSTableFormat;
import org.apache.cassandra.io.sstable.format.big.BigFormat;
import org.apache.cassandra.io.util.FileUtils;
import org.apache.cassandra.schema.CompressionParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SSTableSingleWriter<T extends UnfilteredRowIterator> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SSTableSingleWriter.class);
    private String filePath;
    private CassandraTable cassTable;
    private String outLocation;

    public SSTableSingleWriter(String filePath, CassandraTable cassTable, String outLocation) {
        this.filePath = filePath;
        this.cassTable = cassTable;
        this.outLocation = outLocation;
    }

    public List<String> write(Iterator<T> data) throws IOException {
        SSTableTxnWriter writer = null;
        try {
            int generation = 1; //Todo: calculate this?
            CFMetaData cFMetaData = SSTableUtils.loadCFMetaData(filePath, cassTable,
                    Collections.<String>emptyList(),
                    Collections.<String>emptyList());

            CFMetaData outputCFMetaData = setCFMetadataWithParams(cFMetaData, cassTable.getKeyspaceName(), cassTable.getTableName());

            Descriptor outDescriptor = new Descriptor(BigFormat.latestVersion.getVersion(),
                    outLocation,
                    cassTable.getKeyspaceName(),
                    cassTable.getTableName(),
                    generation,
                    SSTableFormat.Type.BIG);

            SerializationHeader header = new SerializationHeader(true,
                    outputCFMetaData,
                    outputCFMetaData.partitionColumns(),
                    EncodingStats.NO_STATS);

            writer = SSTableTxnWriter.createWithNoLogging(outputCFMetaData, outDescriptor, 4, -1, 1, header);

            while (data.hasNext())
                writer.append(data.next());

        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            throw e;
        } finally {
            if (writer != null) {
                writer.finish();
                LOGGER.info("Done saving sstable to: " + outLocation);
            }
            FileUtils.closeQuietly(writer);

        }

        List retVal = new LinkedList();
        retVal.add(outLocation);

        return retVal;
    }

    private CFMetaData setCFMetadataWithParams(CFMetaData cFMetaData,
                                        String ks,
                                        String table) {
        cFMetaData.compression(cassTable.getCompressionParams());
        return cFMetaData;
    }

}

package com.netflix.sstableadaptor.sstable;


import com.netflix.sstableadaptor.config.CassandraTable;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.db.SerializationHeader;
import org.apache.cassandra.db.rows.EncodingStats;
import org.apache.cassandra.db.rows.UnfilteredRowIterator;
import org.apache.cassandra.io.sstable.Descriptor;
import org.apache.cassandra.io.sstable.SSTableTxnWriter;
import org.apache.cassandra.io.sstable.format.SSTableFormat;
import org.apache.cassandra.io.sstable.format.big.BigFormat;
import org.apache.cassandra.io.util.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SSTableSingleWriter<T extends UnfilteredRowIterator> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SSTableSingleWriter.class);
    private static int generation = 1;
    private CFMetaData origCFMetaData;
    private CassandraTable cassTable;
    private String outLocation;
    private Configuration conf;

    public SSTableSingleWriter(CFMetaData origCFMetaData, CassandraTable cassTable,
                               String outLocation, Configuration conf) {
        this.origCFMetaData = origCFMetaData;
        this.cassTable = cassTable;
        this.outLocation = outLocation;
        this.conf = conf;
    }

    public List<String> write(Iterator<T> data) throws IOException {
        SSTableTxnWriter writer = null;
        try {
            CFMetaData outputCFMetaData = setCFMetadataWithParams(origCFMetaData,
                                                                  cassTable.getKeyspaceName(),
                                                                  cassTable.getTableName());

            Descriptor outDescriptor = new Descriptor(BigFormat.latestVersion.getVersion(),
                    outLocation,
                    cassTable.getKeyspaceName(),
                    cassTable.getTableName(),
                    generation++,
                    SSTableFormat.Type.BIG,
                    conf);

            SerializationHeader header = new SerializationHeader(true,
                    outputCFMetaData,
                    outputCFMetaData.partitionColumns(),
                    EncodingStats.NO_STATS);

            //Todo: fix these settings
            writer = SSTableTxnWriter.createWithNoLogging(outputCFMetaData, outDescriptor, 4, -1, 1, header);

            while (data.hasNext())
                writer.append(data.next());

        } catch (Exception e) {
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

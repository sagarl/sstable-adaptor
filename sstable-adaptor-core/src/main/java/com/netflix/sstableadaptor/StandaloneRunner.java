package com.netflix.sstableadaptor;


import com.netflix.sstableadaptor.util.SSTableUtils;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.db.rows.UnfilteredRowIterator;
import org.apache.cassandra.io.sstable.Descriptor;
import org.apache.cassandra.io.sstable.ISSTableScanner;
import org.apache.cassandra.io.sstable.format.SSTableReader;
import org.apache.cassandra.io.sstable.format.SSTableWriter;
import org.apache.cassandra.io.util.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class StandaloneRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(StandaloneRunner.class);
    public static final String DATA_DIR = "src/test/resources/data/";

    public static void main(String[] args) {
        String inputSSTableFullPathFileName = new File(getInputFile(args)).getAbsolutePath();
        LOGGER.info("Input file name: " + inputSSTableFullPathFileName);
        Configuration conf = new Configuration();
        final Descriptor inputSSTableDescriptor = Descriptor.fromFilename(inputSSTableFullPathFileName, conf);
        SSTableWriter writer = null;

        try {
            final CFMetaData inputCFMetaData =
                    SSTableUtils.metaDataFromSSTable(inputSSTableFullPathFileName, conf);
            final CFMetaData outputCFMetaData = SSTableUtils.createNewCFMetaData(inputSSTableDescriptor, inputCFMetaData);

            final SSTableReader inputSStable = SSTableReader.openNoValidation(inputSSTableDescriptor, inputCFMetaData);
            writer = SSTableUtils.createSSTableWriter(inputSSTableDescriptor, outputCFMetaData, inputSStable);

            final ISSTableScanner currentScanner = inputSStable.getScanner();

            while (currentScanner.hasNext()) {
                final UnfilteredRowIterator row = currentScanner.next();
                writer.append(row);
            }
            writer.finish(false);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        } finally {
            FileUtils.closeQuietly(writer);
        }

    }

    private static String getInputFile(String[] args) {
        if (args.length == 0) {
            return DATA_DIR + "bills_compress/mc-6-big-Data.db";
        }

        return args[0];
    }

}

package com.netflix.sstableadaptor;


import com.netflix.sstableadaptor.sstable.CasspactorSSTableReader;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.config.ColumnDefinition;
import org.apache.cassandra.db.SerializationHeader;
import org.apache.cassandra.db.rows.UnfilteredRowIterator;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.io.FSWriteError;
import org.apache.cassandra.io.sstable.Descriptor;
import org.apache.cassandra.io.sstable.ISSTableScanner;
import org.apache.cassandra.io.sstable.format.SSTableFormat;
import org.apache.cassandra.io.sstable.format.SSTableReader;
import org.apache.cassandra.io.sstable.format.SSTableWriter;
import org.apache.cassandra.io.sstable.format.big.BigTableWriter;
import org.apache.cassandra.io.sstable.metadata.MetadataCollector;
import org.apache.cassandra.io.util.FileUtils;
import org.apache.cassandra.schema.CompressionParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public class StandaloneRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(StandaloneRunner.class);
    public static final String DATA_DIR = "src/test/resources/data/";

    public static void main(String[] args) {
        String inputSSTableFullPathFileName = new File(getInputFile(args)).getAbsolutePath();
        LOGGER.info("Input file name: " + inputSSTableFullPathFileName);

        final Descriptor inputSSTableDescriptor = Descriptor.fromFilename(inputSSTableFullPathFileName);
        SSTableWriter writer = null;

        try {
            final CFMetaData inputCFMetaData =
                    CasspactorSSTableReader.metaDataFromSSTable(inputSSTableFullPathFileName,
                            "casspactor",
                            "bills_nc",
                            Collections.<String>emptyList(),
                            Collections.<String>emptyList());
            final CFMetaData outputCFMetaData = createNewCFMetaData(inputSSTableDescriptor, inputCFMetaData);

            final SSTableReader inputSStable = SSTableReader.openNoValidation(inputSSTableDescriptor, inputCFMetaData);
            writer = createSSTableWriter(inputSSTableDescriptor, outputCFMetaData, inputSStable);

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

    private static CFMetaData createNewCFMetaData(final Descriptor inputSSTableDescriptor,
                                                  final CFMetaData metadata) {
        final CFMetaData.Builder cfMetadataBuilder = CFMetaData.Builder.create(inputSSTableDescriptor.ksname,
                inputSSTableDescriptor.cfname);

        final Collection<ColumnDefinition> colDefs = metadata.allColumns();

        for (ColumnDefinition colDef : colDefs) {
            switch (colDef.kind) {
                case PARTITION_KEY:
                    cfMetadataBuilder.addPartitionKey(colDef.name, colDef.cellValueType());
                    break;
                case CLUSTERING:
                    cfMetadataBuilder.addClusteringColumn(colDef.name, colDef.cellValueType());
                    break;
                case STATIC:
                    cfMetadataBuilder.addStaticColumn(colDef.name, colDef.cellValueType());
                    break;
                default:
                    cfMetadataBuilder.addRegularColumn(colDef.name, colDef.cellValueType());
            }
        }

        cfMetadataBuilder.withPartitioner(Murmur3Partitioner.instance);
        final CFMetaData cfm = cfMetadataBuilder.build();
        cfm.compression(CompressionParams.DEFAULT);

        return cfm;
    }

    private static SSTableWriter createSSTableWriter(final Descriptor inputSSTableDescriptor,
                                                     final CFMetaData outCfmMetaData,
                                                     final SSTableReader inputSstable) {
        final String sstableDirectory = System.getProperty("user.dir") + "/cassandra/compresseddata";
        LOGGER.info("Output directory: " + sstableDirectory);

        final File outputDirectory = new File(sstableDirectory + File.separatorChar
                + inputSSTableDescriptor.ksname
                + File.separatorChar + inputSSTableDescriptor.cfname);

        if (!outputDirectory.exists() && !outputDirectory.mkdirs()) {
            throw new FSWriteError(new IOException("failed to create tmp directory"),
                    outputDirectory.getAbsolutePath());
        }

        final SSTableFormat.Type sstableFormat = SSTableFormat.Type.BIG;

        final BigTableWriter writer = new BigTableWriter(
                new Descriptor(
                        sstableFormat.info.getLatestVersion().getVersion(),
                        outputDirectory.getAbsolutePath(),
                        inputSSTableDescriptor.ksname, inputSSTableDescriptor.cfname,
                        inputSSTableDescriptor.generation,
                        sstableFormat),
                inputSstable.getTotalRows(), 0L, outCfmMetaData,
                new MetadataCollector(outCfmMetaData.comparator)
                        .sstableLevel(inputSstable.getSSTableMetadata().sstableLevel),
                new SerializationHeader(true,
                        outCfmMetaData, outCfmMetaData.partitionColumns(),
                        org.apache.cassandra.db.rows.EncodingStats.NO_STATS));

        return writer;
    }
}

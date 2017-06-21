package org.apache.cassandra.io.util;

import io.netty.util.concurrent.FastThreadLocal;
import org.apache.cassandra.config.Config;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.file.OpenOption;


public class HadoopFileUtils {
    public static Configuration CONF;
    public static int DEFAULT_BUFFER_SIZE = 65536;

    static {
        CONF = new Configuration(); //load stuffs from properties files?
    }

    public final static boolean exists(String filePath) {
        filePath = normalizeFileName(filePath);

        try {
            Path path = new Path(filePath);
            FileSystem fs = path.getFileSystem(CONF);
            return fs.exists(path);
        } catch (IOException e) {
            e.printStackTrace(); //TODO: using logging
            return false;
        }
    }

    public static String normalizeFileName(String fileName)
    {
        if (fileName.startsWith("s3a:")) {
            return fileName.replace("s3a:", "s3:");
        }

        if (fileName.startsWith("s3n:")) {
            return fileName.replace("s3n:", "s3:");
        }

        return fileName;
    }

    public static BufferedWriter newBufferedWriter(String filePath, Charset cs)
        throws IOException
    {
        CharsetEncoder encoder = cs.newEncoder();
        Writer writer = new OutputStreamWriter(getOutputStream(filePath), encoder);
        return new BufferedWriter(writer);
    }

    public static FSDataOutputStream getOutputStream(String filePath) {
        Path path = new Path(filePath);
        Configuration conf = CONF;
        FileSystem fs;
        FSDataOutputStream outputStream;
        try {
            fs = path.getFileSystem(conf);
            outputStream = fs.create(path, true, 1024);
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }

        return outputStream;
    }

    public final static FSDataInputStream buildInputStream(String filePath) throws IOException {
        Path path = new Path(filePath);
        return buildInputStream(path);
    }

    public final static FSDataInputStream buildInputStream(Path path) throws IOException {
        return buildInputStream(path, DEFAULT_BUFFER_SIZE);
    }

    public final static FSDataInputStream buildInputStream(Path path, int bufferSize)
        throws IOException {
        try {
            FileSystem fs = path.getFileSystem(CONF);
            return fs.open(path, bufferSize);
        } catch (IOException e) {
            e.printStackTrace(); //TODO: using logging
            throw new RuntimeException(e.getCause());
        }
    }

    public final static FSDataInputStream buildInputStream(FileSystem fs, Path path, int bufferSize)
        throws IOException {
        return fs.open(path, bufferSize);
    }

    public interface HadoopFileChannel extends WritableByteChannel {
        public void flush() throws IOException;
    }

    public static HadoopFileChannel newFilesystemChannel(String filePath) {
        Path path = new Path(filePath);
        Configuration conf = ChannelProxy.CONF;
        FileSystem fs;
        FSDataOutputStream outputStream;
        try {
            fs = path.getFileSystem(conf);
            outputStream = fs.create(path, true, 1024);
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }

        return new HadoopFileChannel()
        {
            @Override
            public boolean isOpen()
            {
                return true;
            }

            @Override
            public int write(ByteBuffer src) throws IOException
            {
                int toWrite = src.remaining();

                if (src.hasArray())
                {
                    outputStream.write(src.array(), src.arrayOffset() + src.position(), src.remaining());
                    src.position(src.limit());
                    return toWrite;
                }

                if (toWrite < 16)
                {
                    int offset = src.position();
                    for (int i = 0 ; i < toWrite ; i++)
                        outputStream.write(src.get(i + offset));
                    src.position(src.limit());
                    return toWrite;
                }

                byte[] buf = retrieveTemporaryBuffer(toWrite);

                int totalWritten = 0;
                while (totalWritten < toWrite)
                {
                    int toWriteThisTime = Math.min(buf.length, toWrite - totalWritten);

                    org.apache.cassandra.utils.ByteBufferUtil.arrayCopy(src, src.position() + totalWritten, buf, 0, toWriteThisTime);

                    outputStream.write(buf, 0, toWriteThisTime);

                    totalWritten += toWriteThisTime;
                }

                src.position(src.limit());
                return totalWritten;
            }

            public void flush() throws IOException {
                outputStream.hsync();
            }

            @Override
            public void close() throws IOException {
                flush();
                outputStream.close();
            }
        };
    }

    private static final FastThreadLocal<byte[]> tempBuffer = new FastThreadLocal<byte[]>()
    {
        @Override
        public byte[] initialValue()
        {
            return new byte[16];
        }
    };

    private static int MAX_BUFFER_SIZE =
        Integer.getInteger(Config.PROPERTY_PREFIX + "data_output_stream_plus_temp_buffer_size", 8192);

    /*
     * Factored out into separate method to create more flexibility around inlining
     */
    public static byte[] retrieveTemporaryBuffer(int minSize)
    {
        byte[] bytes = tempBuffer.get();
        if (bytes.length < Math.min(minSize, MAX_BUFFER_SIZE))
        {
            // increase in powers of 2, to avoid wasted repeat allocations
            bytes = new byte[Math.min(MAX_BUFFER_SIZE, 2 * Integer.highestOneBit(minSize))];
            tempBuffer.set(bytes);
        }
        return bytes;
    }
}

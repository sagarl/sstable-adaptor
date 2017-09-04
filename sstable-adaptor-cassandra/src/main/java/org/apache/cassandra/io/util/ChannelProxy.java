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
package org.apache.cassandra.io.util;

import org.apache.cassandra.io.FSReadError;
import org.apache.cassandra.utils.concurrent.RefCounted;
import org.apache.cassandra.utils.concurrent.SharedCloseableImpl;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;


/**
 * A proxy of a FileChannel that:
 *
 * - implements reference counting
 * - exports only thread safe FileChannel operations
 * - wraps IO exceptions into runtime exceptions
 *
 * Tested by RandomAccessReaderTest.
 */
public class ChannelProxy extends SharedCloseableImpl
{
    private static final Logger logger = LoggerFactory.getLogger(ChannelProxy.class);

    public static Configuration CONF = HadoopFileUtils.CONF;
    private static int DEFAULT_BUFFER_SIZE = HadoopFileUtils.DEFAULT_BUFFER_SIZE;

    private Path filePath;
    private FileSystem fs;
    private FSDataInputStream inputStream;
    private long fileLength = -1;
    private boolean isExists = false;
    private int bufferSize = 0;

    public ChannelProxy(Cleanup cleanup, FileSystem fs, FSDataInputStream inputStream, Path path, int bufferSize)
    {
        super(cleanup);
        this.inputStream = inputStream;
        this.filePath = path;
        this.bufferSize = bufferSize;
        this.fs = fs;
        this.fileLength = size();
    }

    public static ChannelProxy newInstance(String filePath) {
        return newInstance(filePath, DEFAULT_BUFFER_SIZE);
    }


    public static ChannelProxy newInstance(String filePath, int bufferSize) {
        filePath = HadoopFileUtils.normalizeFileName(filePath);

        try {
            Path path = new Path(filePath);
            FileSystem fs = path.getFileSystem(CONF);
            FSDataInputStream inputStream = HadoopFileUtils.buildInputStream(path, bufferSize);
            Cleanup cleanup = new Cleanup(filePath, inputStream);
            return new ChannelProxy(cleanup, fs, inputStream, path, bufferSize);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    //cannot actually share the same resource - should rename this to copy()
    public ChannelProxy sharedCopy()
    {
        try {
            FSDataInputStream inputStream = HadoopFileUtils.buildInputStream(this.fs, this.filePath, this.bufferSize);
            Cleanup cleanup = new Cleanup(this.filePath(), inputStream);
            return new ChannelProxy(cleanup, this.fs, inputStream, this.filePath, this.bufferSize);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException((e.getCause()));
        }
    }

    public InputStream getInputStream()
    {
        return this.inputStream;
    }

    public boolean exists()
    {
        try {
            if (this.isExists)
                return true;

            isExists = fs.exists(this.filePath);
            return isExists;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            return false;
        }
    }

    public String filePath()
    {
        return filePath.getParent() + "/" + filePath.getName();
    }

    public int read(long position, byte[] buffer, int offset, int length)
            throws IOException
    {
        int readBytes = 0;

        while (readBytes < length) {
            int fileBytesRemained = (this.fileLength - position - readBytes) > Integer.MAX_VALUE?
                    Integer.MAX_VALUE :
                    (int) (this.fileLength - position - readBytes);

            int minLength = Math.min(length-readBytes, fileBytesRemained);
            if (minLength == 0)
                return readBytes;

            int n = inputStream.read(position + readBytes, buffer, offset + readBytes, minLength);
            if (n == -1)
                return readBytes;

            readBytes += n;
        }

        return readBytes;
    }

    public int read(ByteBuffer buffer, long position)
    {
        byte[] temBuff = buffer.array();

        try {
            int size = read(position, temBuff, 0, buffer.limit());
            buffer.limit(buffer.capacity());
            buffer.position(size);
            return size;

        } catch (IOException e) {
            throw new FSReadError(e, filePath.getName());
        }
    }

    public long size()
    {
        if (this.fileLength != -1)
            return fileLength;

        try
        {
            if (fs != null && filePath != null) {
                FileStatus fileStatus = fs.getFileStatus(filePath);
                fileLength = fileStatus.getLen();
                return fileLength;
            }

            return -1;
        } catch (IOException e)
        {
            throw new FSReadError(e, filePath.getName());
        }
    }

    @Override
    public String toString()
    {
        return filePath();
    }

    private final static class Cleanup implements RefCounted.Tidy
    {
        final String filePath;
        final InputStream inputStream;

        Cleanup(String filePath, InputStream inputStream) {
            this.filePath = filePath;
            this.inputStream = inputStream;
        }

        public String name()
        {
            return filePath;
        }

        public void tidy()
        {
            try
            {

                logger.info("Cleaning ChannelProxy for file: " + filePath);
                this.inputStream.close();
            }
            catch (IOException e)
            {
                //Don't propagate the exception as we are closing down
                StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
                StringBuilder sb = new StringBuilder();
                for (StackTraceElement element : stackTraceElements) {
                    sb.append(element.toString());
                }
                logger.error(sb.toString());
                logger.error("Exception on file: " + filePath + " with exception: " + e.getMessage());
            }
        }
    }

}

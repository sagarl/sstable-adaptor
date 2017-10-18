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

import org.apache.cassandra.io.FSWriteError;
import org.apache.cassandra.utils.concurrent.Transactional;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

import static org.apache.cassandra.utils.Throwables.merge;

/**
 * Adds buffering, mark, and fsyncing to OutputStream.  We always fsync on close; we may also
 * fsync incrementally if Config.trickle_fsync is enabled.
 */
public class SequentialWriter extends BufferedDataOutputStreamPlus implements Transactional
{
    // absolute path to the given file
    private final String filePath;

    // Offset for start of buffer relative to underlying file
    protected long bufferOffset;

    // whether to do trickling fsync() to avoid sudden bursts of dirty buffer flushing by kernel causing read
    // latency spikes
    private final SequentialWriterOption option;
    private int bytesSinceTrickleFsync = 0;

    protected long lastFlushOffset;

    protected Runnable runPostFlush;

    private final TransactionalProxy txnProxy = txnProxy();

    // due to lack of multiple-inheritance, we proxy our transactional implementation
    protected class TransactionalProxy extends AbstractTransactional
    {
        @Override
        protected Throwable doPreCleanup(Throwable accumulate)
        {
            // close is idempotent
            try {

                channel.close();
            } catch (Throwable t) { accumulate = merge(accumulate, t); }

            if (buffer != null)
            {
                try { FileUtils.clean(buffer); }
                catch (Throwable t) { accumulate = merge(accumulate, t); }
                buffer = null;
            }

            return accumulate;
        }

        protected void doPrepare()
        {
            syncInternal();
        }

        protected Throwable doCommit(Throwable accumulate)
        {
            return accumulate;
        }

        protected Throwable doAbort(Throwable accumulate)
        {
            return accumulate;
        }
    }

    /**
     * Create heap-based, non-compressed SequenialWriter with default buffer size(64k).
     *
     * @param filePath file to write
     */
    public SequentialWriter(String filePath, Configuration conf) throws IOException {
        this(filePath, SequentialWriterOption.DEFAULT, conf);
    }

    /**
     * Create SequentialWriter for given file with specific writer option.
     *
     * @param filePath File to write
     * @param option Writer option
     */
    public SequentialWriter(String filePath, SequentialWriterOption option, Configuration conf) {
        super(HadoopFileUtils.newFilesystemChannel(filePath, conf), option.allocateBuffer());
        strictFlushing = true;
        this.filePath = filePath;
        this.option = option;
    }

    /**
     * Synchronize file contents with disk.
     */
    public void sync()
    {
        syncInternal();
    }

    protected void syncDataOnlyInternal()
    {
        try
        {
            ((HadoopFileUtils.HadoopFileChannel) channel).flush();

        }
        catch (IOException e)
        {
            throw new FSWriteError(e, getPath());
        }
    }

    /*
     * This is only safe to call before truncation or close for CompressedSequentialWriter
     * Otherwise it will leave a non-uniform size compressed block in the middle of the file
     * and the compressed format can't handle that.
     */
    protected void syncInternal()
    {
        doFlush(0);
        syncDataOnlyInternal();
    }

    @Override
    protected void doFlush(int count)
    {
        flushData();

        if (option.trickleFsync())
        {
            bytesSinceTrickleFsync += buffer.position();
            if (bytesSinceTrickleFsync >= option.trickleFsyncByteInterval())
            {
                syncDataOnlyInternal();
                bytesSinceTrickleFsync = 0;
            }
        }

        // Remember that we wrote, so we don't write it again on next flush().
        resetBuffer();
    }

    public void setPostFlushListener(Runnable runPostFlush)
    {
        assert this.runPostFlush == null;
        this.runPostFlush = runPostFlush;
    }

    /**
     * Override this method instead of overriding flush()
     * @throws FSWriteError on any I/O error.
     */
    protected void flushData()
    {
        try
        {
            buffer.flip();
            channel.write(buffer);
            lastFlushOffset += buffer.position();
        }
        catch (IOException e)
        {
            throw new FSWriteError(e, getPath());
        }
        if (runPostFlush != null)
            runPostFlush.run();
    }

    public boolean hasPosition()
    {
        return true;
    }

    public long position()
    {
        return current();
    }

    /**
     * Returns the current file pointer of the underlying on-disk file.
     * Note that since write works by buffering data, the value of this will increase by buffer
     * size and not every write to the writer will modify this value.
     * Furthermore, for compressed files, this value refers to compressed data, while the
     * writer getFilePointer() refers to uncompressedFile
     *
     * @return the current file pointer
     */
    public long getOnDiskFilePointer()
    {
        return position();
    }

    public long getEstimatedOnDiskBytesWritten()
    {
        return getOnDiskFilePointer();
    }

    public long length()
    {
        /*
        try
        {
            return Math.max(current(), fchannel.size());
        }
        catch (IOException e)
        {
            throw new FSReadError(e, getPath());
        }
        */
        return position();
    }

    public String getPath()
    {
        return filePath;
    }

    protected void resetBuffer()
    {
        bufferOffset = current();
        buffer.clear();
    }

    protected long current()
    {
        return bufferOffset + (buffer == null ? 0 : buffer.position());
    }

    public DataPosition mark()
    {
        return new BufferedFileWriterMark(current());
    }

    public long getLastFlushOffset()
    {
        return lastFlushOffset;
    }

    public boolean isOpen()
    {
        return channel.isOpen();
    }

    public final void prepareToCommit()
    {
        txnProxy.prepareToCommit();
    }

    public final Throwable commit(Throwable accumulate)
    {
        return txnProxy.commit(accumulate);
    }

    public final Throwable abort(Throwable accumulate)
    {
        return txnProxy.abort(accumulate);
    }

    @Override
    public final void close()
    {
        if (option.finishOnClose())
            txnProxy.finish();
        else
            txnProxy.close();
    }

    public final void finish()
    {
        txnProxy.finish();
    }

    protected TransactionalProxy txnProxy()
    {
        return new TransactionalProxy();
    }

    /**
     * Class to hold a mark to the position of the file
     */
    protected static class BufferedFileWriterMark implements DataPosition
    {
        final long pointer;

        public BufferedFileWriterMark(long pointer)
        {
            this.pointer = pointer;
        }
    }

}

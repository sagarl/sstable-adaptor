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
package org.apache.cassandra.db;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.config.DatabaseDescriptor;
import org.apache.cassandra.db.lifecycle.LifecycleTransaction;
import org.apache.cassandra.io.FSError;
import org.apache.cassandra.io.FSWriteError;
import org.apache.cassandra.io.sstable.Component;
import org.apache.cassandra.io.sstable.Descriptor;
import org.apache.cassandra.io.sstable.SSTable;
import org.apache.cassandra.io.util.FileUtils;
import org.apache.cassandra.utils.ByteBufferUtil;
import org.apache.cassandra.utils.DirectorySizeCalculator;
import org.apache.cassandra.utils.Pair;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;

/**
 * Encapsulate handling of paths to the data files.
 *
 * <pre> {@code
 *   /<path_to_data_dir>/ks/<cf dir>/ks-cf1-jb-1-Data.db
 *                         /<cf dir>/la-2-Data.db
 *                         /<cf dir>/.<index name>/ks-cf1.idx-jb-1-Data.db
 *                         /<cf dir>/.<index name>/la-1-Data.db
 *                         ...
 * } </pre>
 *
 * Until v2.0, {@code <cf dir>} is just column family name.
 * Since v2.1, {@code <cf dir>} has column family ID(cfId) added to its end.
 *
 * SSTables from secondary indexes were put in the same directory as their parent.
 * Since v2.2, they have their own directory under the parent directory whose name is index name.
 * Upon startup, those secondary index files are moved to new directory when upgrading.
 *
 * For backward compatibility, Directories can use directory without cfId if exists.
 *
 * In addition, more that one 'root' data directory can be specified so that
 * {@code <path_to_data_dir>} potentially represents multiple locations.
 * Note that in the case of multiple locations, the manifest for the leveled
 * compaction is only in one of the location.
 *
 * Snapshots (resp. backups) are always created along the sstables there are
 * snapshotted (resp. backuped) but inside a subdirectory named 'snapshots'
 * (resp. backups) (and snapshots are further inside a subdirectory of the name
 * of the snapshot). For secondary indexes, snapshots (backups) are not created in
 * their own directory, but are in their parent's snapshot (backup) directory.
 *
 * This class abstracts all those details from the rest of the code.
 */
public class Directories
{
    private static final Logger logger = LoggerFactory.getLogger(Directories.class);


    public static final DataDirectory[] dataDirectories;

    static
    {
        String[] locations = DatabaseDescriptor.getAllDataFileLocations();
        dataDirectories = new DataDirectory[locations.length];
        for (int i = 0; i < locations.length; ++i)
            dataDirectories[i] = new DataDirectory(new File(locations[i]));
    }

    public static class DataDirectory
    {
        public final File location;

        public DataDirectory(File location)
        {
            this.location = location;
        }

        public long getAvailableSpace()
        {
            long availableSpace = location.getUsableSpace() - DatabaseDescriptor.getMinFreeSpacePerDriveInBytes();
            return availableSpace > 0 ? availableSpace : 0;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DataDirectory that = (DataDirectory) o;

            return location.equals(that.location);

        }

        @Override
        public int hashCode()
        {
            return location.hashCode();
        }
    }

    /** The type of files that can be listed by SSTableLister, we never return txn logs,
     * use LifecycleTransaction.getFiles() if you need txn logs. */
    public enum FileType
    {
        /** A permanent sstable file that is safe to use. */
        FINAL,

        /** A temporary sstable file that will soon be deleted. */
        TEMPORARY,

        /** A transaction log file (contains information on final and temporary files). */
        TXN_LOG;
    }

    /**
     * How to handle a failure to read a txn log file. Note that we will try a few
     * times before giving up.
     **/
    public enum OnTxnErr
    {
        /** Throw the exception */
        THROW,

        /** Ignore the problematic parts of the txn log file */
        IGNORE
    }

}

package org.conscrypt;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSession;
import org.conscrypt.io.IoUtils;

/* loaded from: classes.dex */
public final class FileClientSessionCache {
    public static final int MAX_SIZE = 12;
    private static final Logger logger = Logger.getLogger(FileClientSessionCache.class.getName());
    static final Map<File, Impl> caches = new HashMap();

    /* loaded from: classes.dex */
    public static class CacheFile extends File {
        long lastModified;
        final String name;

        public CacheFile(File file, String str) {
            super(file, str);
            this.lastModified = -1L;
            this.name = str;
        }

        @Override // java.io.File
        public long lastModified() {
            long j2 = this.lastModified;
            if (j2 == -1) {
                long lastModified = super.lastModified();
                this.lastModified = lastModified;
                return lastModified;
            }
            return j2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.Comparable
        public int compareTo(File file) {
            long lastModified = lastModified() - file.lastModified();
            if (lastModified == 0) {
                return super.compareTo(file);
            }
            return lastModified < 0 ? -1 : 1;
        }
    }

    /* loaded from: classes.dex */
    public static class Impl implements SSLClientSessionCache {
        Map<String, File> accessOrder = newAccessOrder();
        final File directory;
        String[] initialFiles;
        int size;

        public Impl(File file) {
            boolean exists = file.exists();
            if (exists && !file.isDirectory()) {
                throw new IOException(file + " exists but is not a directory.");
            }
            if (exists) {
                String[] list = file.list();
                this.initialFiles = list;
                if (list != null) {
                    Arrays.sort(list);
                    this.size = this.initialFiles.length;
                } else {
                    throw new IOException(file + " exists but cannot list contents.");
                }
            } else if (file.mkdirs()) {
                this.size = 0;
            } else {
                throw new IOException("Creation of " + file + " directory failed.");
            }
            this.directory = file;
        }

        private void delete(File file) {
            if (!file.delete()) {
                IOException iOException = new IOException("FileClientSessionCache: Failed to delete " + file + ".");
                FileClientSessionCache.logger.log(Level.WARNING, iOException.getMessage(), (Throwable) iOException);
            }
            this.size--;
        }

        private static String fileName(String str, int i2) {
            if (str != null) {
                return str + "." + i2;
            }
            throw new NullPointerException("host == null");
        }

        private void indexFiles() {
            String[] strArr = this.initialFiles;
            if (strArr != null) {
                this.initialFiles = null;
                TreeSet treeSet = new TreeSet();
                for (String str : strArr) {
                    if (!this.accessOrder.containsKey(str)) {
                        treeSet.add(new CacheFile(this.directory, str));
                    }
                }
                if (!treeSet.isEmpty()) {
                    Map<String, File> newAccessOrder = newAccessOrder();
                    Iterator it = treeSet.iterator();
                    while (it.hasNext()) {
                        CacheFile cacheFile = (CacheFile) it.next();
                        newAccessOrder.put(cacheFile.name, cacheFile);
                    }
                    newAccessOrder.putAll(this.accessOrder);
                    this.accessOrder = newAccessOrder;
                }
            }
        }

        public static void logReadError(String str, File file, Throwable th) {
            FileClientSessionCache.logger.log(Level.WARNING, "FileClientSessionCache: Error reading session data for " + str + " from " + file + ".", th);
        }

        public static void logWriteError(String str, File file, Throwable th) {
            FileClientSessionCache.logger.log(Level.WARNING, "FileClientSessionCache: Error writing session data for " + str + " to " + file + ".", th);
        }

        private void makeRoom() {
            if (this.size <= 12) {
                return;
            }
            indexFiles();
            int i2 = this.size - 12;
            Iterator<File> it = this.accessOrder.values().iterator();
            do {
                delete(it.next());
                it.remove();
                i2--;
            } while (i2 > 0);
        }

        private static Map<String, File> newAccessOrder() {
            return new LinkedHashMap(12, 0.75f, true);
        }

        @Override // org.conscrypt.SSLClientSessionCache
        public synchronized byte[] getSessionData(String str, int i2) {
            String fileName = fileName(str, i2);
            File file = this.accessOrder.get(fileName);
            if (file == null) {
                String[] strArr = this.initialFiles;
                if (strArr == null) {
                    return null;
                }
                if (Arrays.binarySearch(strArr, fileName) < 0) {
                    return null;
                }
                file = new File(this.directory, fileName);
                this.accessOrder.put(fileName, file);
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[(int) file.length()];
                    new DataInputStream(fileInputStream).readFully(bArr);
                    return bArr;
                } catch (IOException e2) {
                    logReadError(str, file, e2);
                    return null;
                } finally {
                    IoUtils.closeQuietly(fileInputStream);
                }
            } catch (FileNotFoundException e3) {
                logReadError(str, file, e3);
                return null;
            }
        }

        @Override // org.conscrypt.SSLClientSessionCache
        public synchronized void putSessionData(SSLSession sSLSession, byte[] bArr) {
            String peerHost = sSLSession.getPeerHost();
            if (bArr != null) {
                String fileName = fileName(peerHost, sSLSession.getPeerPort());
                File file = new File(this.directory, fileName);
                boolean exists = file.exists();
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    if (!exists) {
                        this.size++;
                        makeRoom();
                    }
                    try {
                        try {
                            try {
                                fileOutputStream.write(bArr);
                            } catch (IOException e2) {
                                logWriteError(peerHost, file, e2);
                                try {
                                    try {
                                        fileOutputStream.close();
                                    } finally {
                                    }
                                } catch (IOException e3) {
                                    logWriteError(peerHost, file, e3);
                                }
                                delete(file);
                            }
                            try {
                                fileOutputStream.close();
                                this.accessOrder.put(fileName, file);
                            } catch (IOException e4) {
                                logWriteError(peerHost, file, e4);
                                delete(file);
                            }
                        } catch (Throwable th) {
                            try {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e5) {
                                    logWriteError(peerHost, file, e5);
                                    throw th;
                                }
                                throw th;
                            } finally {
                            }
                        }
                    } finally {
                    }
                } catch (FileNotFoundException e6) {
                    logWriteError(peerHost, file, e6);
                }
            } else {
                throw new NullPointerException("sessionData == null");
            }
        }
    }

    private FileClientSessionCache() {
    }

    public static synchronized void reset() {
        synchronized (FileClientSessionCache.class) {
            caches.clear();
        }
    }

    public static synchronized SSLClientSessionCache usingDirectory(File file) {
        Impl impl;
        synchronized (FileClientSessionCache.class) {
            Map<File, Impl> map = caches;
            impl = map.get(file);
            if (impl == null) {
                impl = new Impl(file);
                map.put(file, impl);
            }
        }
        return impl;
    }
}

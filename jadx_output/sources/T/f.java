package T;

import A.m;
import android.content.pm.PackageInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

/* loaded from: classes.dex */
public abstract class f {

    /* renamed from: a, reason: collision with root package name */
    public static final m f638a = new m(11);

    /* renamed from: b, reason: collision with root package name */
    public static final byte[] f639b = {112, 114, 111, 0};

    /* renamed from: c, reason: collision with root package name */
    public static final byte[] f640c = {112, 114, 109, 0};

    /* renamed from: d, reason: collision with root package name */
    public static final byte[] f641d = {48, 49, 53, 0};

    /* renamed from: e, reason: collision with root package name */
    public static final byte[] f642e = {48, 49, 48, 0};

    /* renamed from: f, reason: collision with root package name */
    public static final byte[] f643f = {48, 48, 57, 0};

    /* renamed from: g, reason: collision with root package name */
    public static final byte[] f644g = {48, 48, 53, 0};
    public static final byte[] h = {48, 48, 49, 0};

    /* renamed from: i, reason: collision with root package name */
    public static final byte[] f645i = {48, 48, 49, 0};

    /* renamed from: j, reason: collision with root package name */
    public static final byte[] f646j = {48, 48, 50, 0};

    public static byte[] a(byte[] bArr) {
        Deflater deflater = new Deflater(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            try {
                deflaterOutputStream.write(bArr);
                deflaterOutputStream.close();
                deflater.end();
                return byteArrayOutputStream.toByteArray();
            } finally {
            }
        } catch (Throwable th) {
            deflater.end();
            throw th;
        }
    }

    public static byte[] b(c[] cVarArr, byte[] bArr) {
        int i2 = 0;
        int i3 = 0;
        for (c cVar : cVarArr) {
            i3 += ((((cVar.f636g * 2) + 7) & (-8)) / 8) + (cVar.f634e * 2) + d(cVar.f630a, cVar.f631b, bArr).getBytes(StandardCharsets.UTF_8).length + 16 + cVar.f635f;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i3);
        if (Arrays.equals(bArr, f643f)) {
            int length = cVarArr.length;
            while (i2 < length) {
                c cVar2 = cVarArr[i2];
                q(byteArrayOutputStream, cVar2, d(cVar2.f630a, cVar2.f631b, bArr));
                p(byteArrayOutputStream, cVar2);
                i2++;
            }
        } else {
            for (c cVar3 : cVarArr) {
                q(byteArrayOutputStream, cVar3, d(cVar3.f630a, cVar3.f631b, bArr));
            }
            int length2 = cVarArr.length;
            while (i2 < length2) {
                p(byteArrayOutputStream, cVarArr[i2]);
                i2++;
            }
        }
        if (byteArrayOutputStream.size() == i3) {
            return byteArrayOutputStream.toByteArray();
        }
        throw new IllegalStateException("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + i3);
    }

    public static boolean c(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return false;
            }
            boolean z2 = true;
            for (File file2 : listFiles) {
                if (c(file2) && z2) {
                    z2 = true;
                } else {
                    z2 = false;
                }
            }
            return z2;
        }
        file.delete();
        return true;
    }

    public static String d(String str, String str2, byte[] bArr) {
        Object obj;
        byte[] bArr2 = h;
        boolean equals = Arrays.equals(bArr, bArr2);
        byte[] bArr3 = f644g;
        String str3 = "!";
        if (!equals && !Arrays.equals(bArr, bArr3)) {
            obj = "!";
        } else {
            obj = ":";
        }
        if (str.length() <= 0) {
            if ("!".equals(obj)) {
                return str2.replace(":", "!");
            }
            if (":".equals(obj)) {
                return str2.replace("!", ":");
            }
            return str2;
        }
        if (str2.equals("classes.dex")) {
            return str;
        }
        if (!str2.contains("!") && !str2.contains(":")) {
            if (str2.endsWith(".apk")) {
                return str2;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            if (Arrays.equals(bArr, bArr2) || Arrays.equals(bArr, bArr3)) {
                str3 = ":";
            }
            sb.append(str3);
            sb.append(str2);
            return sb.toString();
        }
        if ("!".equals(obj)) {
            return str2.replace(":", "!");
        }
        if (":".equals(obj)) {
            return str2.replace("!", ":");
        }
        return str2;
    }

    public static void e(PackageInfo packageInfo, File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } finally {
            }
        } catch (IOException unused) {
        }
    }

    public static byte[] f(InputStream inputStream, int i2) {
        byte[] bArr = new byte[i2];
        int i3 = 0;
        while (i3 < i2) {
            int read = inputStream.read(bArr, i3, i2 - i3);
            if (read >= 0) {
                i3 += read;
            } else {
                throw new IllegalStateException(A.e.a("Not enough bytes to read: ", i2));
            }
        }
        return bArr;
    }

    public static int[] g(ByteArrayInputStream byteArrayInputStream, int i2) {
        int[] iArr = new int[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += (int) m(byteArrayInputStream, 2);
            iArr[i4] = i3;
        }
        return iArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x005d, code lost:
    
        if (r0.finished() == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0062, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006a, code lost:
    
        throw new java.lang.IllegalStateException("Inflater did not finish");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] h(java.io.FileInputStream r8, int r9, int r10) {
        /*
            java.util.zip.Inflater r0 = new java.util.zip.Inflater
            r0.<init>()
            byte[] r1 = new byte[r10]     // Catch: java.lang.Throwable -> L2e
            r2 = 2048(0x800, float:2.87E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L2e
            r3 = 0
            r4 = r3
            r5 = r4
        Le:
            boolean r6 = r0.finished()     // Catch: java.lang.Throwable -> L2e
            if (r6 != 0) goto L57
            boolean r6 = r0.needsDictionary()     // Catch: java.lang.Throwable -> L2e
            if (r6 != 0) goto L57
            if (r4 >= r9) goto L57
            int r6 = r8.read(r2)     // Catch: java.lang.Throwable -> L2e
            if (r6 < 0) goto L3b
            r0.setInput(r2, r3, r6)     // Catch: java.lang.Throwable -> L2e
            int r7 = r10 - r5
            int r7 = r0.inflate(r1, r5, r7)     // Catch: java.lang.Throwable -> L2e java.util.zip.DataFormatException -> L30
            int r5 = r5 + r7
            int r4 = r4 + r6
            goto Le
        L2e:
            r8 = move-exception
            goto L8a
        L30:
            r8 = move-exception
            java.lang.String r8 = r8.getMessage()     // Catch: java.lang.Throwable -> L2e
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L2e
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L2e
            throw r9     // Catch: java.lang.Throwable -> L2e
        L3b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2e
            r8.<init>()     // Catch: java.lang.Throwable -> L2e
            java.lang.String r10 = "Invalid zip data. Stream ended after $totalBytesRead bytes. Expected "
            r8.append(r10)     // Catch: java.lang.Throwable -> L2e
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r9 = " bytes"
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L2e
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L2e
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L2e
            throw r9     // Catch: java.lang.Throwable -> L2e
        L57:
            if (r4 != r9) goto L6b
            boolean r8 = r0.finished()     // Catch: java.lang.Throwable -> L2e
            if (r8 == 0) goto L63
            r0.end()
            return r1
        L63:
            java.lang.String r8 = "Inflater did not finish"
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L2e
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L2e
            throw r9     // Catch: java.lang.Throwable -> L2e
        L6b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2e
            r8.<init>()     // Catch: java.lang.Throwable -> L2e
            java.lang.String r10 = "Didn't read enough bytes during decompression. expected="
            r8.append(r10)     // Catch: java.lang.Throwable -> L2e
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r9 = " actual="
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            r8.append(r4)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L2e
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L2e
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L2e
            throw r9     // Catch: java.lang.Throwable -> L2e
        L8a:
            r0.end()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: T.f.h(java.io.FileInputStream, int, int):byte[]");
    }

    public static c[] i(FileInputStream fileInputStream, byte[] bArr, byte[] bArr2, c[] cVarArr) {
        byte[] bArr3 = f645i;
        if (Arrays.equals(bArr, bArr3)) {
            if (!Arrays.equals(f641d, bArr2)) {
                if (Arrays.equals(bArr, bArr3)) {
                    int m2 = (int) m(fileInputStream, 1);
                    byte[] h2 = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
                    if (fileInputStream.read() <= 0) {
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(h2);
                        try {
                            c[] j2 = j(byteArrayInputStream, m2, cVarArr);
                            byteArrayInputStream.close();
                            return j2;
                        } catch (Throwable th) {
                            try {
                                byteArrayInputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    }
                    throw new IllegalStateException("Content found after the end of file");
                }
                throw new IllegalStateException("Unsupported meta version");
            }
            throw new IllegalStateException("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
        }
        if (Arrays.equals(bArr, f646j)) {
            int m3 = (int) m(fileInputStream, 2);
            byte[] h3 = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
            if (fileInputStream.read() <= 0) {
                ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(h3);
                try {
                    c[] k2 = k(byteArrayInputStream2, bArr2, m3, cVarArr);
                    byteArrayInputStream2.close();
                    return k2;
                } catch (Throwable th3) {
                    try {
                        byteArrayInputStream2.close();
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                    }
                    throw th3;
                }
            }
            throw new IllegalStateException("Content found after the end of file");
        }
        throw new IllegalStateException("Unsupported meta version");
    }

    public static c[] j(ByteArrayInputStream byteArrayInputStream, int i2, c[] cVarArr) {
        if (byteArrayInputStream.available() == 0) {
            return new c[0];
        }
        if (i2 == cVarArr.length) {
            String[] strArr = new String[i2];
            int[] iArr = new int[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                int m2 = (int) m(byteArrayInputStream, 2);
                iArr[i3] = (int) m(byteArrayInputStream, 2);
                strArr[i3] = new String(f(byteArrayInputStream, m2), StandardCharsets.UTF_8);
            }
            for (int i4 = 0; i4 < i2; i4++) {
                c cVar = cVarArr[i4];
                if (cVar.f631b.equals(strArr[i4])) {
                    int i5 = iArr[i4];
                    cVar.f634e = i5;
                    cVar.h = g(byteArrayInputStream, i5);
                } else {
                    throw new IllegalStateException("Order of dexfiles in metadata did not match baseline");
                }
            }
            return cVarArr;
        }
        throw new IllegalStateException("Mismatched number of dex files found in metadata");
    }

    public static c[] k(ByteArrayInputStream byteArrayInputStream, byte[] bArr, int i2, c[] cVarArr) {
        String str;
        if (byteArrayInputStream.available() == 0) {
            return new c[0];
        }
        if (i2 == cVarArr.length) {
            for (int i3 = 0; i3 < i2; i3++) {
                m(byteArrayInputStream, 2);
                String str2 = new String(f(byteArrayInputStream, (int) m(byteArrayInputStream, 2)), StandardCharsets.UTF_8);
                long m2 = m(byteArrayInputStream, 4);
                int m3 = (int) m(byteArrayInputStream, 2);
                c cVar = null;
                if (cVarArr.length > 0) {
                    int indexOf = str2.indexOf("!");
                    if (indexOf < 0) {
                        indexOf = str2.indexOf(":");
                    }
                    if (indexOf > 0) {
                        str = str2.substring(indexOf + 1);
                    } else {
                        str = str2;
                    }
                    int i4 = 0;
                    while (true) {
                        if (i4 >= cVarArr.length) {
                            break;
                        }
                        if (cVarArr[i4].f631b.equals(str)) {
                            cVar = cVarArr[i4];
                            break;
                        }
                        i4++;
                    }
                }
                if (cVar != null) {
                    cVar.f633d = m2;
                    int[] g2 = g(byteArrayInputStream, m3);
                    if (Arrays.equals(bArr, h)) {
                        cVar.f634e = m3;
                        cVar.h = g2;
                    }
                } else {
                    throw new IllegalStateException("Missing profile key: ".concat(str2));
                }
            }
            return cVarArr;
        }
        throw new IllegalStateException("Mismatched number of dex files found in metadata");
    }

    public static c[] l(FileInputStream fileInputStream, byte[] bArr, String str) {
        if (Arrays.equals(bArr, f642e)) {
            int m2 = (int) m(fileInputStream, 1);
            byte[] h2 = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
            if (fileInputStream.read() <= 0) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(h2);
                try {
                    c[] n2 = n(byteArrayInputStream, str, m2);
                    byteArrayInputStream.close();
                    return n2;
                } catch (Throwable th) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            throw new IllegalStateException("Content found after the end of file");
        }
        throw new IllegalStateException("Unsupported version");
    }

    public static long m(InputStream inputStream, int i2) {
        byte[] f2 = f(inputStream, i2);
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j2 += (f2[i3] & 255) << (i3 * 8);
        }
        return j2;
    }

    public static c[] n(ByteArrayInputStream byteArrayInputStream, String str, int i2) {
        TreeMap treeMap;
        int i3;
        if (byteArrayInputStream.available() == 0) {
            return new c[0];
        }
        c[] cVarArr = new c[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            int m2 = (int) m(byteArrayInputStream, 2);
            int m3 = (int) m(byteArrayInputStream, 2);
            cVarArr[i4] = new c(str, new String(f(byteArrayInputStream, m2), StandardCharsets.UTF_8), m(byteArrayInputStream, 4), m3, (int) m(byteArrayInputStream, 4), (int) m(byteArrayInputStream, 4), new int[m3], new TreeMap());
        }
        for (int i5 = 0; i5 < i2; i5++) {
            c cVar = cVarArr[i5];
            int available = byteArrayInputStream.available() - cVar.f635f;
            int i6 = 0;
            while (true) {
                int available2 = byteArrayInputStream.available();
                treeMap = cVar.f637i;
                if (available2 <= available) {
                    break;
                }
                i6 += (int) m(byteArrayInputStream, 2);
                treeMap.put(Integer.valueOf(i6), 1);
                for (int m4 = (int) m(byteArrayInputStream, 2); m4 > 0; m4--) {
                    m(byteArrayInputStream, 2);
                    int m5 = (int) m(byteArrayInputStream, 1);
                    if (m5 != 6 && m5 != 7) {
                        while (m5 > 0) {
                            m(byteArrayInputStream, 1);
                            for (int m6 = (int) m(byteArrayInputStream, 1); m6 > 0; m6--) {
                                m(byteArrayInputStream, 2);
                            }
                            m5--;
                        }
                    }
                }
            }
            if (byteArrayInputStream.available() == available) {
                cVar.h = g(byteArrayInputStream, cVar.f634e);
                int i7 = cVar.f636g;
                BitSet valueOf = BitSet.valueOf(f(byteArrayInputStream, (((i7 * 2) + 7) & (-8)) / 8));
                for (int i8 = 0; i8 < i7; i8++) {
                    if (valueOf.get(i8)) {
                        i3 = 2;
                    } else {
                        i3 = 0;
                    }
                    if (valueOf.get(i8 + i7)) {
                        i3 |= 4;
                    }
                    if (i3 != 0) {
                        Integer num = (Integer) treeMap.get(Integer.valueOf(i8));
                        if (num == null) {
                            num = 0;
                        }
                        treeMap.put(Integer.valueOf(i8), Integer.valueOf(i3 | num.intValue()));
                    }
                }
            } else {
                throw new IllegalStateException("Read too much data during profile line parse");
            }
        }
        return cVarArr;
    }

    /* JADX WARN: Finally extract failed */
    public static boolean o(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, c[] cVarArr) {
        long j2;
        ArrayList arrayList;
        int length;
        byte[] bArr2 = f641d;
        int i2 = 0;
        if (Arrays.equals(bArr, bArr2)) {
            ArrayList arrayList2 = new ArrayList(3);
            ArrayList arrayList3 = new ArrayList(3);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                v(byteArrayOutputStream2, cVarArr.length);
                int i3 = 2;
                int i4 = 2;
                for (c cVar : cVarArr) {
                    u(byteArrayOutputStream2, cVar.f632c, 4);
                    u(byteArrayOutputStream2, cVar.f633d, 4);
                    u(byteArrayOutputStream2, cVar.f636g, 4);
                    String d2 = d(cVar.f630a, cVar.f631b, bArr2);
                    Charset charset = StandardCharsets.UTF_8;
                    int length2 = d2.getBytes(charset).length;
                    v(byteArrayOutputStream2, length2);
                    i4 = i4 + 14 + length2;
                    byteArrayOutputStream2.write(d2.getBytes(charset));
                }
                byte[] byteArray = byteArrayOutputStream2.toByteArray();
                if (i4 == byteArray.length) {
                    l lVar = new l(1, byteArray, false);
                    byteArrayOutputStream2.close();
                    arrayList2.add(lVar);
                    ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
                    int i5 = 0;
                    int i6 = 0;
                    while (i5 < cVarArr.length) {
                        try {
                            c cVar2 = cVarArr[i5];
                            v(byteArrayOutputStream3, i5);
                            v(byteArrayOutputStream3, cVar2.f634e);
                            i6 = i6 + 4 + (cVar2.f634e * 2);
                            int[] iArr = cVar2.h;
                            int length3 = iArr.length;
                            int i7 = i2;
                            while (i2 < length3) {
                                int i8 = iArr[i2];
                                v(byteArrayOutputStream3, i8 - i7);
                                i2++;
                                i7 = i8;
                            }
                            i5++;
                            i2 = 0;
                        } catch (Throwable th) {
                        }
                    }
                    byte[] byteArray2 = byteArrayOutputStream3.toByteArray();
                    if (i6 == byteArray2.length) {
                        l lVar2 = new l(3, byteArray2, true);
                        byteArrayOutputStream3.close();
                        arrayList2.add(lVar2);
                        byteArrayOutputStream3 = new ByteArrayOutputStream();
                        int i9 = 0;
                        int i10 = 0;
                        while (i9 < cVarArr.length) {
                            try {
                                c cVar3 = cVarArr[i9];
                                Iterator it = cVar3.f637i.entrySet().iterator();
                                int i11 = 0;
                                while (it.hasNext()) {
                                    i11 |= ((Integer) ((Map.Entry) it.next()).getValue()).intValue();
                                }
                                ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
                                try {
                                    r(byteArrayOutputStream4, i11, cVar3);
                                    byte[] byteArray3 = byteArrayOutputStream4.toByteArray();
                                    byteArrayOutputStream4.close();
                                    byteArrayOutputStream4 = new ByteArrayOutputStream();
                                    try {
                                        s(byteArrayOutputStream4, cVar3);
                                        byte[] byteArray4 = byteArrayOutputStream4.toByteArray();
                                        byteArrayOutputStream4.close();
                                        v(byteArrayOutputStream3, i9);
                                        int length4 = byteArray3.length + i3 + byteArray4.length;
                                        int i12 = i10 + 6;
                                        ArrayList arrayList4 = arrayList3;
                                        u(byteArrayOutputStream3, length4, 4);
                                        v(byteArrayOutputStream3, i11);
                                        byteArrayOutputStream3.write(byteArray3);
                                        byteArrayOutputStream3.write(byteArray4);
                                        i10 = i12 + length4;
                                        i9++;
                                        arrayList3 = arrayList4;
                                        i3 = 2;
                                    } finally {
                                    }
                                } finally {
                                }
                            } finally {
                                try {
                                    byteArrayOutputStream3.close();
                                    throw th;
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            }
                        }
                        ArrayList arrayList5 = arrayList3;
                        byte[] byteArray5 = byteArrayOutputStream3.toByteArray();
                        if (i10 == byteArray5.length) {
                            l lVar3 = new l(4, byteArray5, true);
                            byteArrayOutputStream3.close();
                            arrayList2.add(lVar3);
                            long j3 = 4;
                            long size = j3 + j3 + 4 + (arrayList2.size() * 16);
                            u(byteArrayOutputStream, arrayList2.size(), 4);
                            int i13 = 0;
                            while (i13 < arrayList2.size()) {
                                l lVar4 = (l) arrayList2.get(i13);
                                int i14 = lVar4.f658a;
                                if (i14 == 1) {
                                    j2 = 0;
                                } else if (i14 == 2) {
                                    j2 = 1;
                                } else if (i14 == 3) {
                                    j2 = 2;
                                } else if (i14 == 4) {
                                    j2 = 3;
                                } else {
                                    if (i14 != 5) {
                                        throw null;
                                    }
                                    j2 = 4;
                                }
                                u(byteArrayOutputStream, j2, 4);
                                u(byteArrayOutputStream, size, 4);
                                byte[] bArr3 = lVar4.f659b;
                                if (lVar4.f660c) {
                                    long length5 = bArr3.length;
                                    byte[] a2 = a(bArr3);
                                    arrayList = arrayList5;
                                    arrayList.add(a2);
                                    u(byteArrayOutputStream, a2.length, 4);
                                    u(byteArrayOutputStream, length5, 4);
                                    length = a2.length;
                                } else {
                                    arrayList = arrayList5;
                                    arrayList.add(bArr3);
                                    u(byteArrayOutputStream, bArr3.length, 4);
                                    u(byteArrayOutputStream, 0L, 4);
                                    length = bArr3.length;
                                }
                                size += length;
                                i13++;
                                arrayList5 = arrayList;
                            }
                            ArrayList arrayList6 = arrayList5;
                            for (int i15 = 0; i15 < arrayList6.size(); i15++) {
                                byteArrayOutputStream.write((byte[]) arrayList6.get(i15));
                            }
                            return true;
                        }
                        throw new IllegalStateException("Expected size " + i10 + ", does not match actual size " + byteArray5.length);
                    }
                    throw new IllegalStateException("Expected size " + i6 + ", does not match actual size " + byteArray2.length);
                }
                throw new IllegalStateException("Expected size " + i4 + ", does not match actual size " + byteArray.length);
            } catch (Throwable th3) {
                try {
                    byteArrayOutputStream2.close();
                    throw th3;
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                    throw th3;
                }
            }
        }
        byte[] bArr4 = f642e;
        if (Arrays.equals(bArr, bArr4)) {
            byte[] b2 = b(cVarArr, bArr4);
            u(byteArrayOutputStream, cVarArr.length, 1);
            u(byteArrayOutputStream, b2.length, 4);
            byte[] a3 = a(b2);
            u(byteArrayOutputStream, a3.length, 4);
            byteArrayOutputStream.write(a3);
            return true;
        }
        byte[] bArr5 = f644g;
        if (Arrays.equals(bArr, bArr5)) {
            u(byteArrayOutputStream, cVarArr.length, 1);
            for (c cVar4 : cVarArr) {
                int size2 = cVar4.f637i.size() * 4;
                String d3 = d(cVar4.f630a, cVar4.f631b, bArr5);
                Charset charset2 = StandardCharsets.UTF_8;
                v(byteArrayOutputStream, d3.getBytes(charset2).length);
                v(byteArrayOutputStream, cVar4.h.length);
                u(byteArrayOutputStream, size2, 4);
                u(byteArrayOutputStream, cVar4.f632c, 4);
                byteArrayOutputStream.write(d3.getBytes(charset2));
                Iterator it2 = cVar4.f637i.keySet().iterator();
                while (it2.hasNext()) {
                    v(byteArrayOutputStream, ((Integer) it2.next()).intValue());
                    v(byteArrayOutputStream, 0);
                }
                for (int i16 : cVar4.h) {
                    v(byteArrayOutputStream, i16);
                }
            }
            return true;
        }
        byte[] bArr6 = f643f;
        if (Arrays.equals(bArr, bArr6)) {
            byte[] b3 = b(cVarArr, bArr6);
            u(byteArrayOutputStream, cVarArr.length, 1);
            u(byteArrayOutputStream, b3.length, 4);
            byte[] a4 = a(b3);
            u(byteArrayOutputStream, a4.length, 4);
            byteArrayOutputStream.write(a4);
            return true;
        }
        byte[] bArr7 = h;
        if (!Arrays.equals(bArr, bArr7)) {
            return false;
        }
        v(byteArrayOutputStream, cVarArr.length);
        for (c cVar5 : cVarArr) {
            String d4 = d(cVar5.f630a, cVar5.f631b, bArr7);
            Charset charset3 = StandardCharsets.UTF_8;
            v(byteArrayOutputStream, d4.getBytes(charset3).length);
            TreeMap treeMap = cVar5.f637i;
            v(byteArrayOutputStream, treeMap.size());
            v(byteArrayOutputStream, cVar5.h.length);
            u(byteArrayOutputStream, cVar5.f632c, 4);
            byteArrayOutputStream.write(d4.getBytes(charset3));
            Iterator it3 = treeMap.keySet().iterator();
            while (it3.hasNext()) {
                v(byteArrayOutputStream, ((Integer) it3.next()).intValue());
            }
            for (int i17 : cVar5.h) {
                v(byteArrayOutputStream, i17);
            }
        }
        return true;
    }

    public static void p(ByteArrayOutputStream byteArrayOutputStream, c cVar) {
        s(byteArrayOutputStream, cVar);
        int[] iArr = cVar.h;
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = iArr[i2];
            v(byteArrayOutputStream, i4 - i3);
            i2++;
            i3 = i4;
        }
        int i5 = cVar.f636g;
        byte[] bArr = new byte[(((i5 * 2) + 7) & (-8)) / 8];
        for (Map.Entry entry : cVar.f637i.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            int intValue2 = ((Integer) entry.getValue()).intValue();
            if ((intValue2 & 2) != 0) {
                int i6 = intValue / 8;
                bArr[i6] = (byte) (bArr[i6] | (1 << (intValue % 8)));
            }
            if ((intValue2 & 4) != 0) {
                int i7 = intValue + i5;
                int i8 = i7 / 8;
                bArr[i8] = (byte) ((1 << (i7 % 8)) | bArr[i8]);
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void q(ByteArrayOutputStream byteArrayOutputStream, c cVar, String str) {
        Charset charset = StandardCharsets.UTF_8;
        v(byteArrayOutputStream, str.getBytes(charset).length);
        v(byteArrayOutputStream, cVar.f634e);
        u(byteArrayOutputStream, cVar.f635f, 4);
        u(byteArrayOutputStream, cVar.f632c, 4);
        u(byteArrayOutputStream, cVar.f636g, 4);
        byteArrayOutputStream.write(str.getBytes(charset));
    }

    public static void r(ByteArrayOutputStream byteArrayOutputStream, int i2, c cVar) {
        int bitCount = Integer.bitCount(i2 & (-2));
        int i3 = cVar.f636g;
        byte[] bArr = new byte[(((bitCount * i3) + 7) & (-8)) / 8];
        for (Map.Entry entry : cVar.f637i.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            int intValue2 = ((Integer) entry.getValue()).intValue();
            int i4 = 0;
            for (int i5 = 1; i5 <= 4; i5 <<= 1) {
                if (i5 != 1 && (i5 & i2) != 0) {
                    if ((i5 & intValue2) == i5) {
                        int i6 = (i4 * i3) + intValue;
                        int i7 = i6 / 8;
                        bArr[i7] = (byte) ((1 << (i6 % 8)) | bArr[i7]);
                    }
                    i4++;
                }
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void s(ByteArrayOutputStream byteArrayOutputStream, c cVar) {
        int i2 = 0;
        for (Map.Entry entry : cVar.f637i.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            if ((((Integer) entry.getValue()).intValue() & 1) != 0) {
                v(byteArrayOutputStream, intValue - i2);
                v(byteArrayOutputStream, 0);
                i2 = intValue;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x01cb, code lost:
    
        if (r7 == null) goto L132;
     */
    /* JADX WARN: Removed duplicated region for block: B:109:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0180 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:243:0x010b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02f8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01e0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r8v0, types: [T.e] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void t(android.content.Context r18, java.util.concurrent.Executor r19, T.e r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 780
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: T.f.t(android.content.Context, java.util.concurrent.Executor, T.e, boolean):void");
    }

    public static void u(ByteArrayOutputStream byteArrayOutputStream, long j2, int i2) {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) ((j2 >> (i3 * 8)) & 255);
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void v(ByteArrayOutputStream byteArrayOutputStream, int i2) {
        u(byteArrayOutputStream, i2, 2);
    }
}

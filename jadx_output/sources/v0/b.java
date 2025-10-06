package v0;

import B0.C0002c;
import C0.f;
import H0.m;
import H0.o;
import H0.t;
import b0.C0091q;
import j0.AbstractC0150d;
import j0.g;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import k.C0213z;
import q0.d;
import u0.k;
import u0.u;
import u0.v;

/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    public static final byte[] f2808a;

    /* renamed from: b, reason: collision with root package name */
    public static final k f2809b = f.w(new String[0]);

    /* renamed from: c, reason: collision with root package name */
    public static final v f2810c;

    /* renamed from: d, reason: collision with root package name */
    public static final C0213z f2811d;

    /* renamed from: e, reason: collision with root package name */
    public static final m f2812e;

    /* renamed from: f, reason: collision with root package name */
    public static final TimeZone f2813f;

    /* renamed from: g, reason: collision with root package name */
    public static final g f2814g;
    public static final String h;

    /* JADX WARN: Code restructure failed: missing block: B:75:0x0188, code lost:
    
        continue;
     */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.Object, H0.e] */
    /* JADX WARN: Type inference failed for: r2v0, types: [byte[], java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Object, H0.e] */
    static {
        /*
            Method dump skipped, instructions count: 526
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: v0.b.<clinit>():void");
    }

    public static final boolean a(u0.m mVar, u0.m mVar2) {
        AbstractC0150d.e(mVar, "<this>");
        AbstractC0150d.e(mVar2, "other");
        if (AbstractC0150d.a(mVar.f2486d, mVar2.f2486d) && mVar.f2487e == mVar2.f2487e && AbstractC0150d.a(mVar.f2483a, mVar2.f2483a)) {
            return true;
        }
        return false;
    }

    public static final void b(long j2, long j3, long j4) {
        if ((j3 | j4) >= 0 && j3 <= j2 && j2 - j3 >= j4) {
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static final void c(Closeable closeable) {
        AbstractC0150d.e(closeable, "<this>");
        try {
            closeable.close();
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception unused) {
        }
    }

    public static final void d(Socket socket) {
        AbstractC0150d.e(socket, "<this>");
        try {
            socket.close();
        } catch (AssertionError e2) {
            throw e2;
        } catch (RuntimeException e3) {
            if (AbstractC0150d.a(e3.getMessage(), "bio == null")) {
            } else {
                throw e3;
            }
        } catch (Exception unused) {
        }
    }

    public static final int e(String str, char c2, int i2, int i3) {
        while (i2 < i3) {
            int i4 = i2 + 1;
            if (str.charAt(i2) == c2) {
                return i2;
            }
            i2 = i4;
        }
        return i3;
    }

    public static final int f(String str, String str2, int i2, int i3) {
        while (i2 < i3) {
            int i4 = i2 + 1;
            if (d.C(str2, str.charAt(i2))) {
                return i2;
            }
            i2 = i4;
        }
        return i3;
    }

    public static final boolean g(t tVar, TimeUnit timeUnit) {
        AbstractC0150d.e(timeUnit, "timeUnit");
        try {
            return t(tVar, 100, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    public static final String h(String str, Object... objArr) {
        AbstractC0150d.e(str, "format");
        Locale locale = Locale.US;
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
        return String.format(locale, str, Arrays.copyOf(copyOf, copyOf.length));
    }

    public static final boolean i(String[] strArr, String[] strArr2, Comparator comparator) {
        boolean z2;
        AbstractC0150d.e(strArr, "<this>");
        if (strArr.length != 0 && strArr2 != null && strArr2.length != 0) {
            int length = strArr.length;
            int i2 = 0;
            while (i2 < length) {
                String str = strArr[i2];
                i2++;
                int i3 = 0;
                while (true) {
                    if (i3 < strArr2.length) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        int i4 = i3 + 1;
                        try {
                            if (comparator.compare(str, strArr2[i3]) == 0) {
                                return true;
                            }
                            i3 = i4;
                        } catch (ArrayIndexOutOfBoundsException e2) {
                            throw new NoSuchElementException(e2.getMessage());
                        }
                    }
                }
            }
        }
        return false;
    }

    public static final long j(u uVar) {
        String a2 = uVar.f2571f.a("Content-Length");
        if (a2 != null) {
            try {
            } catch (NumberFormatException unused) {
                return -1L;
            }
        }
        return Long.parseLong(a2);
    }

    public static final List k(Object... objArr) {
        List list;
        AbstractC0150d.e(objArr, "elements");
        Object[] objArr2 = (Object[]) objArr.clone();
        Object[] copyOf = Arrays.copyOf(objArr2, objArr2.length);
        AbstractC0150d.e(copyOf, "elements");
        if (copyOf.length > 0) {
            list = Arrays.asList(copyOf);
            AbstractC0150d.d(list, "asList(...)");
        } else {
            list = C0091q.f1234a;
        }
        List unmodifiableList = Collections.unmodifiableList(list);
        AbstractC0150d.d(unmodifiableList, "unmodifiableList(listOf(*elements.clone()))");
        return unmodifiableList;
    }

    public static final int l(String str) {
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            char charAt = str.charAt(i2);
            if (AbstractC0150d.f(charAt, 31) > 0 && AbstractC0150d.f(charAt, 127) < 0) {
                i2 = i3;
            } else {
                return i2;
            }
        }
        return -1;
    }

    public static final int m(String str, int i2, int i3) {
        while (i2 < i3) {
            int i4 = i2 + 1;
            char charAt = str.charAt(i2);
            if (charAt == '\t' || charAt == '\n' || charAt == '\f' || charAt == '\r' || charAt == ' ') {
                i2 = i4;
            } else {
                return i2;
            }
        }
        return i3;
    }

    public static final int n(String str, int i2, int i3) {
        int i4 = i3 - 1;
        if (i2 <= i4) {
            while (true) {
                int i5 = i4 - 1;
                char charAt = str.charAt(i4);
                if (charAt == '\t' || charAt == '\n' || charAt == '\f' || charAt == '\r' || charAt == ' ') {
                    if (i4 == i2) {
                        break;
                    }
                    i4 = i5;
                } else {
                    return i4 + 1;
                }
            }
        }
        return i2;
    }

    public static final String[] o(String[] strArr, String[] strArr2, Comparator comparator) {
        AbstractC0150d.e(strArr2, "other");
        ArrayList arrayList = new ArrayList();
        int length = strArr.length;
        int i2 = 0;
        while (i2 < length) {
            String str = strArr[i2];
            i2++;
            int length2 = strArr2.length;
            int i3 = 0;
            while (true) {
                if (i3 < length2) {
                    String str2 = strArr2[i3];
                    i3++;
                    if (comparator.compare(str, str2) == 0) {
                        arrayList.add(str);
                        break;
                    }
                }
            }
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }

    public static final boolean p(String str) {
        AbstractC0150d.e(str, "name");
        if (!str.equalsIgnoreCase("Authorization") && !str.equalsIgnoreCase("Cookie") && !str.equalsIgnoreCase("Proxy-Authorization") && !str.equalsIgnoreCase("Set-Cookie")) {
            return false;
        }
        return true;
    }

    public static final int q(char c2) {
        if ('0' <= c2 && c2 < ':') {
            return c2 - '0';
        }
        if ('a' <= c2 && c2 < 'g') {
            return c2 - 'W';
        }
        if ('A' <= c2 && c2 < 'G') {
            return c2 - '7';
        }
        return -1;
    }

    public static final Charset r(H0.g gVar, Charset charset) {
        Charset charset2;
        AbstractC0150d.e(gVar, "<this>");
        AbstractC0150d.e(charset, "default");
        int g2 = gVar.g(f2812e);
        if (g2 != -1) {
            if (g2 != 0) {
                if (g2 != 1) {
                    if (g2 != 2) {
                        if (g2 != 3) {
                            if (g2 == 4) {
                                Charset charset3 = q0.a.f2219a;
                                charset2 = q0.a.f2220b;
                                if (charset2 == null) {
                                    charset2 = Charset.forName("UTF-32LE");
                                    AbstractC0150d.d(charset2, "forName(...)");
                                    q0.a.f2220b = charset2;
                                }
                            } else {
                                throw new AssertionError();
                            }
                        } else {
                            Charset charset4 = q0.a.f2219a;
                            charset2 = q0.a.f2221c;
                            if (charset2 == null) {
                                charset2 = Charset.forName("UTF-32BE");
                                AbstractC0150d.d(charset2, "forName(...)");
                                q0.a.f2221c = charset2;
                            }
                        }
                        return charset2;
                    }
                    Charset charset5 = StandardCharsets.UTF_16LE;
                    AbstractC0150d.d(charset5, "UTF_16LE");
                    return charset5;
                }
                Charset charset6 = StandardCharsets.UTF_16BE;
                AbstractC0150d.d(charset6, "UTF_16BE");
                return charset6;
            }
            Charset charset7 = StandardCharsets.UTF_8;
            AbstractC0150d.d(charset7, "UTF_8");
            return charset7;
        }
        return charset;
    }

    public static final int s(o oVar) {
        AbstractC0150d.e(oVar, "<this>");
        return (oVar.i() & 255) | ((oVar.i() & 255) << 16) | ((oVar.i() & 255) << 8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v6, types: [java.lang.Object, H0.e] */
    public static final boolean t(t tVar, int i2, TimeUnit timeUnit) {
        long j2;
        AbstractC0150d.e(timeUnit, "timeUnit");
        long nanoTime = System.nanoTime();
        if (tVar.a().e()) {
            j2 = tVar.a().c() - nanoTime;
        } else {
            j2 = Long.MAX_VALUE;
        }
        tVar.a().d(Math.min(j2, timeUnit.toNanos(i2)) + nanoTime);
        try {
            ?? obj = new Object();
            while (tVar.b(8192L, obj) != -1) {
                obj.o(obj.f412b);
            }
            if (j2 == Long.MAX_VALUE) {
                tVar.a().a();
            } else {
                tVar.a().d(nanoTime + j2);
            }
            return true;
        } catch (InterruptedIOException unused) {
            if (j2 == Long.MAX_VALUE) {
                tVar.a().a();
            } else {
                tVar.a().d(nanoTime + j2);
            }
            return false;
        } catch (Throwable th) {
            if (j2 == Long.MAX_VALUE) {
                tVar.a().a();
            } else {
                tVar.a().d(nanoTime + j2);
            }
            throw th;
        }
    }

    public static final k u(List list) {
        ArrayList arrayList = new ArrayList(20);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C0002c c0002c = (C0002c) it.next();
            String h2 = c0002c.f94a.h();
            String h3 = c0002c.f95b.h();
            arrayList.add(h2);
            arrayList.add(d.P(h3).toString());
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return new k((String[]) array);
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }

    public static final String v(u0.m mVar, boolean z2) {
        int i2;
        AbstractC0150d.e(mVar, "<this>");
        String str = mVar.f2486d;
        if (d.D(str, ":")) {
            str = "[" + str + ']';
        }
        int i3 = mVar.f2487e;
        if (!z2) {
            String str2 = mVar.f2483a;
            AbstractC0150d.e(str2, "scheme");
            if (str2.equals("http")) {
                i2 = 80;
            } else if (str2.equals("https")) {
                i2 = 443;
            } else {
                i2 = -1;
            }
            if (i3 == i2) {
                return str;
            }
        }
        return str + ':' + i3;
    }

    public static final List w(List list) {
        AbstractC0150d.e(list, "<this>");
        List unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        AbstractC0150d.d(unmodifiableList, "unmodifiableList(toMutableList())");
        return unmodifiableList;
    }

    public static final int x(String str, int i2) {
        Long valueOf;
        if (str == null) {
            valueOf = null;
        } else {
            try {
                valueOf = Long.valueOf(Long.parseLong(str));
            } catch (NumberFormatException unused) {
                return i2;
            }
        }
        if (valueOf == null) {
            return i2;
        }
        long longValue = valueOf.longValue();
        if (longValue > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (longValue < 0) {
            return 0;
        }
        return (int) longValue;
    }

    public static final String y(String str, int i2, int i3) {
        int m2 = m(str, i2, i3);
        String substring = str.substring(m2, n(str, m2, i3));
        AbstractC0150d.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    public static final void z(IOException iOException, List list) {
        AbstractC0150d.e(iOException, "<this>");
        if (list.size() > 1) {
            System.out.println(list);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C0.d.e(iOException, (Exception) it.next());
        }
    }
}

package okhttp3.internal.publicsuffix;

import A.e;
import C0.d;
import C0.m;
import H0.c;
import H0.j;
import H0.l;
import H0.o;
import b0.AbstractC0082h;
import b0.C0091q;
import j0.AbstractC0150d;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import org.conscrypt.BuildConfig;

/* loaded from: classes.dex */
public final class PublicSuffixDatabase {

    /* renamed from: e, reason: collision with root package name */
    public static final byte[] f2141e = {42};

    /* renamed from: f, reason: collision with root package name */
    public static final List f2142f = d.y("*");

    /* renamed from: g, reason: collision with root package name */
    public static final PublicSuffixDatabase f2143g = new PublicSuffixDatabase();

    /* renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f2144a = new AtomicBoolean(false);

    /* renamed from: b, reason: collision with root package name */
    public final CountDownLatch f2145b = new CountDownLatch(1);

    /* renamed from: c, reason: collision with root package name */
    public byte[] f2146c;

    /* renamed from: d, reason: collision with root package name */
    public byte[] f2147d;

    public static List c(String str) {
        List M2 = q0.d.M(str, new char[]{'.'});
        if (!M2.isEmpty()) {
            if (AbstractC0150d.a(M2.get(M2.size() - 1), BuildConfig.FLAVOR)) {
                int size = M2.size() - 1;
                if (size < 0) {
                    size = 0;
                }
                if (size >= 0) {
                    C0091q c0091q = C0091q.f1234a;
                    if (size != 0) {
                        if (size >= M2.size()) {
                            return AbstractC0082h.L(M2);
                        }
                        if (size == 1) {
                            if (!M2.isEmpty()) {
                                return d.y(M2.get(0));
                            }
                            throw new NoSuchElementException("List is empty.");
                        }
                        ArrayList arrayList = new ArrayList(size);
                        Iterator it = M2.iterator();
                        int i2 = 0;
                        while (it.hasNext()) {
                            arrayList.add(it.next());
                            i2++;
                            if (i2 == size) {
                                break;
                            }
                        }
                        int size2 = arrayList.size();
                        if (size2 != 0) {
                            if (size2 != 1) {
                                return arrayList;
                            }
                            return d.y(arrayList.get(0));
                        }
                        return c0091q;
                    }
                    return c0091q;
                }
                throw new IllegalArgumentException(e.b("Requested element count ", size, " is less than zero.").toString());
            }
            return M2;
        }
        throw new NoSuchElementException("List is empty.");
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x00bd, code lost:
    
        j0.AbstractC0150d.i("publicSuffixListBytes");
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x00c0, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x00c1, code lost:
    
        r10 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
    
        if (r3 <= 1) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x009f, code lost:
    
        r8 = (byte[][]) r4.clone();
        r9 = r8.length - 1;
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a8, code lost:
    
        if (r10 >= r9) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00aa, code lost:
    
        r11 = r10 + 1;
        r8[r10] = okhttp3.internal.publicsuffix.PublicSuffixDatabase.f2141e;
        r12 = r13.f2146c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b2, code lost:
    
        if (r12 == null) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b4, code lost:
    
        r10 = A.m.b(r12, r8, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b8, code lost:
    
        if (r10 == null) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00bb, code lost:
    
        r10 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00c2, code lost:
    
        if (r10 == null) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c4, code lost:
    
        r3 = r3 - 1;
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c6, code lost:
    
        if (r7 >= r3) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c8, code lost:
    
        r8 = r7 + 1;
        r9 = r13.f2147d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00cc, code lost:
    
        if (r9 == null) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ce, code lost:
    
        r7 = A.m.b(r9, r4, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00d2, code lost:
    
        if (r7 == null) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d5, code lost:
    
        r7 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00e0, code lost:
    
        if (r7 == null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00e2, code lost:
    
        r3 = q0.d.M(j0.AbstractC0150d.h(r7, "!"), new char[]{'.'});
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0130, code lost:
    
        if (r2.size() != r3.size()) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x013c, code lost:
    
        if (((java.lang.String) r3.get(0)).charAt(0) == '!') goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x013e, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0149, code lost:
    
        if (((java.lang.String) r3.get(0)).charAt(0) != '!') goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x014b, code lost:
    
        r2 = r2.size();
        r3 = r3.size();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0153, code lost:
    
        r2 = r2 - r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x015f, code lost:
    
        r3 = new b0.C0089o(0, c(r14));
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0168, code lost:
    
        if (r2 < 0) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x016a, code lost:
    
        if (r2 != 0) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x016d, code lost:
    
        r3 = new p0.b(r3, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0173, code lost:
    
        r14 = new java.lang.StringBuilder();
        r14.append((java.lang.CharSequence) org.conscrypt.BuildConfig.FLAVOR);
        r3 = r3.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0185, code lost:
    
        if (r3.hasNext() == false) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0187, code lost:
    
        r4 = r3.next();
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x018c, code lost:
    
        if (r0 <= 1) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x018e, code lost:
    
        r14.append((java.lang.CharSequence) ".");
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0193, code lost:
    
        C0.m.a(r14, r4, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0197, code lost:
    
        r14.append((java.lang.CharSequence) org.conscrypt.BuildConfig.FLAVOR);
        r14 = r14.toString();
        j0.AbstractC0150d.d(r14, "toString(...)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01a3, code lost:
    
        return r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01b5, code lost:
    
        throw new java.lang.IllegalArgumentException(A.e.b("Requested element count ", r2, " is less than zero.").toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0155, code lost:
    
        r2 = r2.size();
        r3 = r3.size() + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00f1, code lost:
    
        if (r5 != null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00f3, code lost:
    
        if (r10 != null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00f5, code lost:
    
        r3 = okhttp3.internal.publicsuffix.PublicSuffixDatabase.f2142f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00f8, code lost:
    
        if (r5 != null) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00fa, code lost:
    
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0104, code lost:
    
        r5 = b0.C0091q.f1234a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0106, code lost:
    
        if (r4 != null) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0108, code lost:
    
        r4 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0109, code lost:
    
        if (r10 != null) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x010b, code lost:
    
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0115, code lost:
    
        if (r3 != null) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0118, code lost:
    
        r5 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0121, code lost:
    
        if (r4.size() <= r5.size()) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0123, code lost:
    
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0125, code lost:
    
        r3 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x010d, code lost:
    
        r3 = q0.d.M(r10, new char[]{'.'});
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x00fc, code lost:
    
        r4 = q0.d.M(r5, new char[]{'.'});
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x00d7, code lost:
    
        j0.AbstractC0150d.i("publicSuffixExceptionListBytes");
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x00dc, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x00dd, code lost:
    
        r7 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String a(java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.a(java.lang.String):java.lang.String");
    }

    public final void b() {
        InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream("publicsuffixes.gz");
        if (resourceAsStream == null) {
            return;
        }
        Logger logger = l.f427a;
        o oVar = new o(new j(new c(resourceAsStream, new Object(), 1)));
        try {
            long l2 = oVar.l();
            oVar.q(l2);
            byte[] j2 = oVar.f434b.j(l2);
            long l3 = oVar.l();
            oVar.q(l3);
            byte[] j3 = oVar.f434b.j(l3);
            m.b(oVar, null);
            synchronized (this) {
                this.f2146c = j2;
                this.f2147d = j3;
            }
            this.f2145b.countDown();
        } finally {
        }
    }
}

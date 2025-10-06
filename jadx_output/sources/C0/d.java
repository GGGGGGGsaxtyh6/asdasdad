package C0;

import H.T;
import M.v;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EdgeEffect;
import d0.AbstractC0098a;
import e0.AbstractC0100a;
import j0.AbstractC0150d;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import k.C0213z;
import k.N0;
import k.f1;
import k.h1;
import org.conscrypt.BuildConfig;
import u0.r;
import z.C0260e;

/* loaded from: classes.dex */
public abstract class d implements T, K.g {
    public d() {
        new ConcurrentHashMap();
    }

    public static float B(EdgeEffect edgeEffect, float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 31) {
            return K.c.c(edgeEffect, f2, f3);
        }
        K.b.a(edgeEffect, f2, f3);
        return f2;
    }

    public static C0213z C(String str) {
        int i2;
        String str2;
        AbstractC0150d.e(str, "statusLine");
        boolean B2 = q0.k.B(str, false, "HTTP/1.");
        r rVar = r.HTTP_1_0;
        if (B2) {
            i2 = 9;
            if (str.length() >= 9 && str.charAt(8) == ' ') {
                int charAt = str.charAt(7) - '0';
                if (charAt != 0) {
                    if (charAt == 1) {
                        rVar = r.HTTP_1_1;
                    } else {
                        throw new ProtocolException(AbstractC0150d.h(str, "Unexpected status line: "));
                    }
                }
            } else {
                throw new ProtocolException(AbstractC0150d.h(str, "Unexpected status line: "));
            }
        } else if (q0.k.B(str, false, "ICY ")) {
            i2 = 4;
        } else {
            throw new ProtocolException(AbstractC0150d.h(str, "Unexpected status line: "));
        }
        int i3 = i2 + 3;
        if (str.length() >= i3) {
            try {
                String substring = str.substring(i2, i3);
                AbstractC0150d.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                int parseInt = Integer.parseInt(substring);
                if (str.length() > i3) {
                    if (str.charAt(i3) == ' ') {
                        str2 = str.substring(i2 + 4);
                        AbstractC0150d.d(str2, "this as java.lang.String).substring(startIndex)");
                    } else {
                        throw new ProtocolException(AbstractC0150d.h(str, "Unexpected status line: "));
                    }
                } else {
                    str2 = BuildConfig.FLAVOR;
                }
                return new C0213z(rVar, parseInt, str2, 2);
            } catch (NumberFormatException unused) {
                throw new ProtocolException(AbstractC0150d.h(str, "Unexpected status line: "));
            }
        }
        throw new ProtocolException(AbstractC0150d.h(str, "Unexpected status line: "));
    }

    public static void H(View view, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            f1.a(view, charSequence);
            return;
        }
        h1 h1Var = h1.f1914k;
        if (h1Var != null && h1Var.f1916a == view) {
            h1.b(null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            h1 h1Var2 = h1.f1915l;
            if (h1Var2 != null && h1Var2.f1916a == view) {
                h1Var2.a();
            }
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            view.setOnHoverListener(null);
            return;
        }
        new h1(view, charSequence);
    }

    public static final void d(x0.a aVar, x0.b bVar, String str) {
        x0.d.f2847i.fine(bVar.f2841b + ' ' + String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1)) + ": " + aVar.f2836a);
    }

    public static void e(Throwable th, Throwable th2) {
        AbstractC0150d.e(th, "<this>");
        AbstractC0150d.e(th2, "exception");
        if (th != th2) {
            Integer num = AbstractC0100a.f1261a;
            if (num != null && num.intValue() < 19) {
                Method method = AbstractC0098a.f1260a;
                if (method != null) {
                    method.invoke(th, th2);
                    return;
                }
                return;
            }
            th.addSuppressed(th2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v4, types: [java.lang.Object, H0.e, H0.t] */
    public static void f(long j2, H0.e eVar, int i2, ArrayList arrayList, int i3, int i4, ArrayList arrayList2) {
        int i5;
        int i6;
        int i7;
        long j3;
        Object obj;
        long j4;
        int i8 = i2;
        int i9 = 0;
        int i10 = 1;
        if (i3 < i4) {
            if (i3 < i4) {
                int i11 = i3;
                while (true) {
                    int i12 = i11 + 1;
                    if (((H0.h) arrayList.get(i11)).a() < i8) {
                        throw new IllegalArgumentException("Failed requirement.");
                    }
                    if (i12 >= i4) {
                        break;
                    } else {
                        i11 = i12;
                    }
                }
            }
            H0.h hVar = (H0.h) arrayList.get(i3);
            H0.h hVar2 = (H0.h) arrayList.get(i4 - 1);
            if (i8 == hVar.a()) {
                int intValue = ((Number) arrayList2.get(i3)).intValue();
                int i13 = i3 + 1;
                H0.h hVar3 = (H0.h) arrayList.get(i13);
                i5 = i13;
                i6 = intValue;
                hVar = hVar3;
            } else {
                i5 = i3;
                i6 = -1;
            }
            if (hVar.d(i8) != hVar2.d(i8)) {
                int i14 = i5 + 1;
                if (i14 < i4) {
                    while (true) {
                        int i15 = i14 + 1;
                        if (((H0.h) arrayList.get(i14 - 1)).d(i8) != ((H0.h) arrayList.get(i14)).d(i8)) {
                            i10++;
                        }
                        if (i15 >= i4) {
                            break;
                        } else {
                            i14 = i15;
                        }
                    }
                }
                long j5 = 4;
                long j6 = (i10 * 2) + (eVar.f412b / j5) + j2 + 2;
                eVar.w(i10);
                eVar.w(i6);
                if (i5 < i4) {
                    int i16 = i5;
                    while (true) {
                        int i17 = i16 + 1;
                        byte d2 = ((H0.h) arrayList.get(i16)).d(i8);
                        if (i16 == i5 || d2 != ((H0.h) arrayList.get(i16 - 1)).d(i8)) {
                            eVar.w(d2 & 255);
                        }
                        if (i17 >= i4) {
                            break;
                        } else {
                            i16 = i17;
                        }
                    }
                }
                H0.e eVar2 = new Object();
                while (i5 < i4) {
                    byte d3 = ((H0.h) arrayList.get(i5)).d(i8);
                    int i18 = i5 + 1;
                    if (i18 < i4) {
                        int i19 = i18;
                        while (true) {
                            int i20 = i19 + 1;
                            if (d3 != ((H0.h) arrayList.get(i19)).d(i8)) {
                                i7 = i19;
                                break;
                            } else if (i20 >= i4) {
                                break;
                            } else {
                                i19 = i20;
                            }
                        }
                    }
                    i7 = i4;
                    if (i18 == i7 && i8 + 1 == ((H0.h) arrayList.get(i5)).a()) {
                        eVar.w(((Number) arrayList2.get(i5)).intValue());
                        j3 = j6;
                        obj = eVar2;
                        j4 = j5;
                    } else {
                        eVar.w(((int) ((eVar2.f412b / j5) + j6)) * (-1));
                        j3 = j6;
                        obj = eVar2;
                        j4 = j5;
                        f(j6, eVar2, i8 + 1, arrayList, i5, i7, arrayList2);
                    }
                    i5 = i7;
                    eVar2 = obj;
                    j5 = j4;
                    j6 = j3;
                }
                eVar.t(eVar2);
                return;
            }
            int min = Math.min(hVar.a(), hVar2.a());
            if (i8 < min) {
                int i21 = i8;
                while (true) {
                    int i22 = i21 + 1;
                    if (hVar.d(i21) != hVar2.d(i21)) {
                        break;
                    }
                    i9++;
                    if (i22 >= min) {
                        break;
                    } else {
                        i21 = i22;
                    }
                }
            }
            long j7 = 4;
            long j8 = (eVar.f412b / j7) + j2 + 2 + i9 + 1;
            eVar.w(-i9);
            eVar.w(i6);
            int i23 = i8 + i9;
            if (i8 < i23) {
                while (true) {
                    int i24 = i8 + 1;
                    eVar.w(hVar.d(i8) & 255);
                    if (i24 >= i23) {
                        break;
                    } else {
                        i8 = i24;
                    }
                }
            }
            if (i5 + 1 == i4) {
                if (i23 == ((H0.h) arrayList.get(i5)).a()) {
                    eVar.w(((Number) arrayList2.get(i5)).intValue());
                    return;
                }
                throw new IllegalStateException("Check failed.");
            }
            ?? obj2 = new Object();
            eVar.w(((int) ((obj2.f412b / j7) + j8)) * (-1));
            f(j8, obj2, i23, arrayList, i5, i4, arrayList2);
            eVar.t(obj2);
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public static void j(Object obj, String str) {
        if (obj != null) {
        } else {
            throw new NullPointerException(str);
        }
    }

    public static final String q(long j2) {
        String str;
        if (j2 <= -999500000) {
            str = ((j2 - 500000000) / 1000000000) + " s ";
        } else if (j2 <= -999500) {
            str = ((j2 - 500000) / 1000000) + " ms";
        } else if (j2 <= 0) {
            str = ((j2 - 500) / 1000) + " µs";
        } else if (j2 < 999500) {
            str = ((j2 + 500) / 1000) + " µs";
        } else if (j2 < 999500000) {
            str = ((j2 + 500000) / 1000000) + " ms";
        } else {
            str = ((j2 + 500000000) / 1000000000) + " s ";
        }
        return String.format("%6s", Arrays.copyOf(new Object[]{str}, 1));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static u0.j r(javax.net.ssl.SSLSession r6) {
        /*
            b0.q r0 = b0.C0091q.f1234a
            java.lang.String r1 = r6.getCipherSuite()
            if (r1 == 0) goto L7a
            java.lang.String r2 = "TLS_NULL_WITH_NULL_NULL"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L12
            r2 = 1
            goto L18
        L12:
            java.lang.String r2 = "SSL_NULL_WITH_NULL_NULL"
            boolean r2 = r1.equals(r2)
        L18:
            if (r2 != 0) goto L6e
            u0.b r2 = u0.g.f2433b
            u0.g r1 = r2.c(r1)
            java.lang.String r2 = r6.getProtocol()
            if (r2 == 0) goto L66
            java.lang.String r3 = "NONE"
            boolean r3 = r3.equals(r2)
            if (r3 != 0) goto L5e
            u0.y r2 = C0.f.o(r2)
            java.security.cert.Certificate[] r3 = r6.getPeerCertificates()     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> L42
            if (r3 == 0) goto L42
            int r4 = r3.length     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> L42
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r4)     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> L42
            java.util.List r3 = v0.b.k(r3)     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> L42
            goto L43
        L42:
            r3 = r0
        L43:
            u0.j r4 = new u0.j
            java.security.cert.Certificate[] r6 = r6.getLocalCertificates()
            if (r6 == 0) goto L54
            int r0 = r6.length
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r0)
            java.util.List r0 = v0.b.k(r6)
        L54:
            androidx.lifecycle.I r6 = new androidx.lifecycle.I
            r5 = 1
            r6.<init>(r5, r3)
            r4.<init>(r2, r1, r0, r6)
            return r4
        L5e:
            java.io.IOException r6 = new java.io.IOException
            java.lang.String r0 = "tlsVersion == NONE"
            r6.<init>(r0)
            throw r6
        L66:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "tlsVersion == null"
            r6.<init>(r0)
            throw r6
        L6e:
            java.io.IOException r6 = new java.io.IOException
            java.lang.String r0 = "cipherSuite == "
            java.lang.String r0 = j0.AbstractC0150d.h(r1, r0)
            r6.<init>(r0)
            throw r6
        L7a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "cipherSuite == null"
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: C0.d.r(javax.net.ssl.SSLSession):u0.j");
    }

    public static r s(String str) {
        if (str.equals("http/1.0")) {
            return r.HTTP_1_0;
        }
        if (str.equals("http/1.1")) {
            return r.HTTP_1_1;
        }
        if (str.equals("h2_prior_knowledge")) {
            return r.H2_PRIOR_KNOWLEDGE;
        }
        if (str.equals("h2")) {
            return r.HTTP_2;
        }
        if (str.equals("spdy/3.1")) {
            return r.SPDY_3;
        }
        if (str.equals("quic")) {
            return r.QUIC;
        }
        throw new IOException(AbstractC0150d.h(str, "Unexpected protocol: "));
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0047, code lost:
    
        if (r5.f2952c == r8.hashCode()) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.content.res.ColorStateList t(android.content.Context r8, int r9) {
        /*
            android.content.res.Resources r0 = r8.getResources()
            android.content.res.Resources$Theme r8 = r8.getTheme()
            z.i r1 = new z.i
            r1.<init>(r0, r8)
            java.lang.Object r2 = z.AbstractC0266k.f2957c
            monitor-enter(r2)
            java.util.WeakHashMap r3 = z.AbstractC0266k.f2956b     // Catch: java.lang.Throwable -> L3c
            java.lang.Object r3 = r3.get(r1)     // Catch: java.lang.Throwable -> L3c
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L3c
            r4 = 0
            if (r3 == 0) goto L50
            int r5 = r3.size()     // Catch: java.lang.Throwable -> L3c
            if (r5 <= 0) goto L50
            java.lang.Object r5 = r3.get(r9)     // Catch: java.lang.Throwable -> L3c
            z.h r5 = (z.C0263h) r5     // Catch: java.lang.Throwable -> L3c
            if (r5 == 0) goto L50
            android.content.res.Configuration r6 = r5.f2951b     // Catch: java.lang.Throwable -> L3c
            android.content.res.Configuration r7 = r0.getConfiguration()     // Catch: java.lang.Throwable -> L3c
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L4d
            if (r8 != 0) goto L3f
            int r6 = r5.f2952c     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L49
            goto L3f
        L3c:
            r8 = move-exception
            goto Lb9
        L3f:
            if (r8 == 0) goto L4d
            int r6 = r5.f2952c     // Catch: java.lang.Throwable -> L3c
            int r7 = r8.hashCode()     // Catch: java.lang.Throwable -> L3c
            if (r6 != r7) goto L4d
        L49:
            android.content.res.ColorStateList r3 = r5.f2950a     // Catch: java.lang.Throwable -> L3c
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            goto L52
        L4d:
            r3.remove(r9)     // Catch: java.lang.Throwable -> L3c
        L50:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            r3 = r4
        L52:
            if (r3 == 0) goto L55
            goto Lb8
        L55:
            java.lang.ThreadLocal r2 = z.AbstractC0266k.f2955a
            java.lang.Object r3 = r2.get()
            android.util.TypedValue r3 = (android.util.TypedValue) r3
            if (r3 != 0) goto L67
            android.util.TypedValue r3 = new android.util.TypedValue
            r3.<init>()
            r2.set(r3)
        L67:
            r2 = 1
            r0.getValue(r9, r3, r2)
            int r2 = r3.type
            r3 = 28
            if (r2 < r3) goto L76
            r3 = 31
            if (r2 > r3) goto L76
            goto L87
        L76:
            android.content.res.XmlResourceParser r2 = r0.getXml(r9)
            android.content.res.ColorStateList r4 = z.AbstractC0258c.a(r0, r2, r8)     // Catch: java.lang.Exception -> L7f
            goto L87
        L7f:
            r2 = move-exception
            java.lang.String r3 = "ResourcesCompat"
            java.lang.String r5 = "Failed to inflate ColorStateList, leaving it to the framework"
            android.util.Log.w(r3, r5, r2)
        L87:
            if (r4 == 0) goto Lb4
            java.lang.Object r2 = z.AbstractC0266k.f2957c
            monitor-enter(r2)
            java.util.WeakHashMap r0 = z.AbstractC0266k.f2956b     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r3 = r0.get(r1)     // Catch: java.lang.Throwable -> L9f
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L9f
            if (r3 != 0) goto La1
            android.util.SparseArray r3 = new android.util.SparseArray     // Catch: java.lang.Throwable -> L9f
            r3.<init>()     // Catch: java.lang.Throwable -> L9f
            r0.put(r1, r3)     // Catch: java.lang.Throwable -> L9f
            goto La1
        L9f:
            r8 = move-exception
            goto Lb2
        La1:
            z.h r0 = new z.h     // Catch: java.lang.Throwable -> L9f
            android.content.res.Resources r1 = r1.f2953a     // Catch: java.lang.Throwable -> L9f
            android.content.res.Configuration r1 = r1.getConfiguration()     // Catch: java.lang.Throwable -> L9f
            r0.<init>(r4, r1, r8)     // Catch: java.lang.Throwable -> L9f
            r3.append(r9, r0)     // Catch: java.lang.Throwable -> L9f
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L9f
            r3 = r4
            goto Lb8
        Lb2:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L9f
            throw r8
        Lb4:
            android.content.res.ColorStateList r3 = r0.getColorStateList(r9, r8)
        Lb8:
            return r3
        Lb9:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: C0.d.t(android.content.Context, int):android.content.res.ColorStateList");
    }

    public static float u(EdgeEffect edgeEffect) {
        if (Build.VERSION.SDK_INT >= 31) {
            return K.c.b(edgeEffect);
        }
        return 0.0f;
    }

    public static Drawable v(Context context, int i2) {
        return N0.b().c(context, i2);
    }

    public static boolean x() {
        return e.f207d;
    }

    public static List y(Object obj) {
        List singletonList = Collections.singletonList(obj);
        AbstractC0150d.d(singletonList, "singletonList(...)");
        return singletonList;
    }

    public abstract void A(v vVar);

    public abstract void D(p.f fVar, p.f fVar2);

    public abstract void E(p.f fVar, Thread thread);

    public abstract void F(boolean z2);

    public abstract void G(boolean z2);

    public abstract boolean g(p.g gVar, p.c cVar);

    public abstract boolean h(p.g gVar, Object obj, Object obj2);

    public abstract boolean i(p.g gVar, p.f fVar, p.f fVar2);

    public abstract Typeface k(Context context, C0260e c0260e, Resources resources, int i2);

    public abstract Typeface l(Context context, E.l[] lVarArr, int i2);

    public Typeface m(Context context, List list, int i2) {
        throw new IllegalStateException("createFromFontInfoWithFallback must only be called on API 29+");
    }

    public Typeface n(Context context, InputStream inputStream) {
        File s2 = f.s(context);
        if (s2 == null) {
            return null;
        }
        try {
            if (!f.m(s2, inputStream)) {
                return null;
            }
            return Typeface.createFromFile(s2.getPath());
        } catch (RuntimeException unused) {
            return null;
        } finally {
            s2.delete();
        }
    }

    public Typeface o(Context context, Resources resources, int i2, String str, int i3) {
        File s2 = f.s(context);
        if (s2 == null) {
            return null;
        }
        try {
            if (!f.l(s2, resources, i2)) {
                return null;
            }
            return Typeface.createFromFile(s2.getPath());
        } catch (RuntimeException unused) {
            return null;
        } finally {
            s2.delete();
        }
    }

    public E.l p(E.l[] lVarArr, int i2) {
        int i3;
        boolean z2;
        int i4;
        new A.m(0);
        if ((i2 & 1) == 0) {
            i3 = 400;
        } else {
            i3 = 700;
        }
        if ((i2 & 2) != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        E.l lVar = null;
        int i5 = Integer.MAX_VALUE;
        for (E.l lVar2 : lVarArr) {
            int abs = Math.abs(lVar2.f298c - i3) * 2;
            if (lVar2.f299d == z2) {
                i4 = 0;
            } else {
                i4 = 1;
            }
            int i6 = abs + i4;
            if (lVar == null || i5 > i6) {
                lVar = lVar2;
                i5 = i6;
            }
        }
        return lVar;
    }

    public abstract InputFilter[] w(InputFilter[] inputFilterArr);

    public abstract void z(Throwable th);

    @Override // H.T
    public void b() {
    }

    @Override // H.T
    public void c() {
    }
}

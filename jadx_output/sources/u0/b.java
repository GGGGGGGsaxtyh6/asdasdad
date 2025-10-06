package u0;

import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    public static final b f2414a = new Object();

    /* renamed from: b, reason: collision with root package name */
    public static final b f2415b = new Object();

    /* renamed from: c, reason: collision with root package name */
    public static final b f2416c = new Object();

    public static final g a(b bVar, String str) {
        g gVar = new g(str);
        g.f2435d.put(str, gVar);
        return gVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6, types: [H0.e] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.lang.Object, H0.e] */
    public static String b(String str, int i2, int i3, String str2, int i4) {
        int i5;
        int i6;
        boolean z2;
        boolean z3;
        boolean z4;
        String str3;
        boolean z5 = false;
        if ((i4 & 1) != 0) {
            i5 = 0;
        } else {
            i5 = i2;
        }
        if ((i4 & 2) != 0) {
            i6 = str.length();
        } else {
            i6 = i3;
        }
        if ((i4 & 8) != 0) {
            z2 = false;
        } else {
            z2 = true;
        }
        if ((i4 & 16) != 0) {
            z3 = false;
        } else {
            z3 = true;
        }
        if ((i4 & 32) != 0) {
            z4 = false;
        } else {
            z4 = true;
        }
        if ((i4 & 64) == 0) {
            z5 = true;
        }
        AbstractC0150d.e(str, "<this>");
        int i7 = i5;
        while (i7 < i6) {
            int codePointAt = str.codePointAt(i7);
            int i8 = 32;
            int i9 = 128;
            if (codePointAt >= 32 && codePointAt != 127 && ((codePointAt < 128 || z5) && !q0.d.C(str2, (char) codePointAt) && ((codePointAt != 37 || (z2 && (!z3 || d(str, i7, i6)))) && (codePointAt != 43 || !z4)))) {
                i7 += Character.charCount(codePointAt);
            } else {
                ?? obj = new Object();
                obj.y(str, i5, i7);
                ?? r2 = 0;
                while (i7 < i6) {
                    int codePointAt2 = str.codePointAt(i7);
                    if (!z2 || (codePointAt2 != 9 && codePointAt2 != 10 && codePointAt2 != 12 && codePointAt2 != 13)) {
                        if (codePointAt2 == 43 && z4) {
                            if (z2) {
                                str3 = "+";
                            } else {
                                str3 = "%2B";
                            }
                            obj.x(str3);
                        } else if (codePointAt2 >= i8 && codePointAt2 != 127 && ((codePointAt2 < i9 || z5) && !q0.d.C(str2, (char) codePointAt2) && (codePointAt2 != 37 || (z2 && (!z3 || d(str, i7, i6)))))) {
                            obj.z(codePointAt2);
                        } else {
                            if (r2 == 0) {
                                r2 = new Object();
                            }
                            r2.z(codePointAt2);
                            while (!r2.f()) {
                                byte i10 = r2.i();
                                obj.u(37);
                                char[] cArr = m.f2482j;
                                obj.u(cArr[((i10 & 255) >> 4) & 15]);
                                obj.u(cArr[i10 & 15]);
                            }
                        }
                    }
                    i7 += Character.charCount(codePointAt2);
                    i8 = 32;
                    i9 = 128;
                    r2 = r2;
                }
                return obj.n(obj.f412b, q0.a.f2219a);
            }
        }
        String substring = str.substring(i5, i6);
        AbstractC0150d.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    public static boolean d(String str, int i2, int i3) {
        int i4 = i2 + 2;
        if (i4 < i3 && str.charAt(i2) == '%' && v0.b.q(str.charAt(i2 + 1)) != -1 && v0.b.q(str.charAt(i4)) != -1) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, H0.e] */
    public static String e(String str, int i2, int i3, int i4) {
        int i5;
        boolean z2 = false;
        if ((i4 & 1) != 0) {
            i2 = 0;
        }
        if ((i4 & 2) != 0) {
            i3 = str.length();
        }
        if ((i4 & 4) == 0) {
            z2 = true;
        }
        AbstractC0150d.e(str, "<this>");
        int i6 = i2;
        while (i6 < i3) {
            int i7 = i6 + 1;
            char charAt = str.charAt(i6);
            if (charAt != '%' && (charAt != '+' || !z2)) {
                i6 = i7;
            } else {
                ?? obj = new Object();
                obj.y(str, i2, i6);
                while (i6 < i3) {
                    int codePointAt = str.codePointAt(i6);
                    if (codePointAt == 37 && (i5 = i6 + 2) < i3) {
                        int q2 = v0.b.q(str.charAt(i6 + 1));
                        int q3 = v0.b.q(str.charAt(i5));
                        if (q2 != -1 && q3 != -1) {
                            obj.u((q2 << 4) + q3);
                            i6 = Character.charCount(codePointAt) + i5;
                        }
                        obj.z(codePointAt);
                        i6 += Character.charCount(codePointAt);
                    } else {
                        if (codePointAt == 43 && z2) {
                            obj.u(32);
                            i6++;
                        }
                        obj.z(codePointAt);
                        i6 += Character.charCount(codePointAt);
                    }
                }
                return obj.n(obj.f412b, q0.a.f2219a);
            }
        }
        String substring = str.substring(i2, i3);
        AbstractC0150d.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    public static ArrayList f(String str) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 <= str.length()) {
            int G2 = q0.d.G(str, '&', i2, 4);
            if (G2 == -1) {
                G2 = str.length();
            }
            int G3 = q0.d.G(str, '=', i2, 4);
            if (G3 != -1 && G3 <= G2) {
                String substring = str.substring(i2, G3);
                AbstractC0150d.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                arrayList.add(substring);
                String substring2 = str.substring(G3 + 1, G2);
                AbstractC0150d.d(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                arrayList.add(substring2);
            } else {
                String substring3 = str.substring(i2, G2);
                AbstractC0150d.d(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
                arrayList.add(substring3);
                arrayList.add(null);
            }
            i2 = G2 + 1;
        }
        return arrayList;
    }

    public synchronized g c(String str) {
        g gVar;
        String str2;
        try {
            AbstractC0150d.e(str, "javaName");
            LinkedHashMap linkedHashMap = g.f2435d;
            gVar = (g) linkedHashMap.get(str);
            if (gVar == null) {
                if (q0.k.B(str, false, "TLS_")) {
                    String substring = str.substring(4);
                    AbstractC0150d.d(substring, "this as java.lang.String).substring(startIndex)");
                    str2 = AbstractC0150d.h(substring, "SSL_");
                } else if (q0.k.B(str, false, "SSL_")) {
                    String substring2 = str.substring(4);
                    AbstractC0150d.d(substring2, "this as java.lang.String).substring(startIndex)");
                    str2 = AbstractC0150d.h(substring2, "TLS_");
                } else {
                    str2 = str;
                }
                gVar = (g) linkedHashMap.get(str2);
                if (gVar == null) {
                    gVar = new g(str);
                }
                linkedHashMap.put(str, gVar);
            }
        } catch (Throwable th) {
            throw th;
        }
        return gVar;
    }
}

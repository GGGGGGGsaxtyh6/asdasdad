package t;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import s.C0234a;
import s.C0236c;
import s.C0237d;

/* renamed from: t.h, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0245h {

    /* renamed from: a, reason: collision with root package name */
    public static final C0239b f2383a = new Object();

    public static boolean a(C0237d c0237d) {
        s.e eVar;
        boolean z2;
        boolean z3;
        int[] iArr = c0237d.f2315o0;
        int i2 = iArr[0];
        int i3 = iArr[1];
        C0237d c0237d2 = c0237d.f2281S;
        if (c0237d2 != null) {
            eVar = (s.e) c0237d2;
        } else {
            eVar = null;
        }
        if (eVar != null) {
            int i4 = eVar.f2315o0[0];
        }
        if (eVar != null) {
            int i5 = eVar.f2315o0[1];
        }
        if (i2 != 1 && !c0237d.y() && i2 != 2 && ((i2 != 3 || c0237d.f2318r != 0 || c0237d.f2284V != 0.0f || !c0237d.r(0)) && (i2 != 3 || c0237d.f2318r != 1 || !c0237d.s(0, c0237d.o())))) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (i3 != 1 && !c0237d.z() && i3 != 2 && ((i3 != 3 || c0237d.f2319s != 0 || c0237d.f2284V != 0.0f || !c0237d.r(1)) && (i3 != 3 || c0237d.f2319s != 1 || !c0237d.s(1, c0237d.i())))) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (c0237d.f2284V > 0.0f && (z2 || z3)) {
            return true;
        }
        if (!z2 || !z3) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [t.n, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v5, types: [t.n, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    public static n b(C0237d c0237d, int i2, ArrayList arrayList, n nVar) {
        int i3;
        int i4;
        if (i2 == 0) {
            i3 = c0237d.f2311m0;
        } else {
            i3 = c0237d.f2313n0;
        }
        int i5 = 0;
        if (i3 != -1 && (nVar == 0 || i3 != nVar.f2391b)) {
            int i6 = 0;
            while (true) {
                if (i6 >= arrayList.size()) {
                    break;
                }
                n nVar2 = (n) arrayList.get(i6);
                if (nVar2.f2391b == i3) {
                    if (nVar != 0) {
                        nVar.c(i2, nVar2);
                        arrayList.remove((Object) nVar);
                    }
                    nVar = nVar2;
                } else {
                    i6++;
                }
            }
        } else if (i3 != -1) {
            return nVar;
        }
        n nVar3 = nVar;
        if (nVar == 0) {
            if (c0237d instanceof C0234a) {
                C0234a c0234a = (C0234a) c0237d;
                int i7 = 0;
                while (true) {
                    if (i7 < c0234a.f2235q0) {
                        C0237d c0237d2 = c0234a.f2234p0[i7];
                        if ((i2 == 0 && (i4 = c0237d2.f2311m0) != -1) || (i2 == 1 && (i4 = c0237d2.f2313n0) != -1)) {
                            break;
                        }
                        i7++;
                    } else {
                        i4 = -1;
                        break;
                    }
                }
                if (i4 != -1) {
                    int i8 = 0;
                    while (true) {
                        if (i8 >= arrayList.size()) {
                            break;
                        }
                        n nVar4 = (n) arrayList.get(i8);
                        if (nVar4.f2391b == i4) {
                            nVar = nVar4;
                            break;
                        }
                        i8++;
                    }
                }
            }
            if (nVar == 0) {
                nVar = new Object();
                nVar.f2390a = new ArrayList();
                nVar.f2393d = null;
                nVar.f2394e = -1;
                int i9 = n.f2389f;
                n.f2389f = i9 + 1;
                nVar.f2391b = i9;
                nVar.f2392c = i2;
            }
            arrayList.add(nVar);
            nVar3 = nVar;
        }
        ArrayList arrayList2 = nVar3.f2390a;
        if (!arrayList2.contains(c0237d)) {
            arrayList2.add(c0237d);
            if (c0237d instanceof s.f) {
                s.f fVar = (s.f) c0237d;
                C0236c c0236c = fVar.f2349s0;
                if (fVar.t0 == 0) {
                    i5 = 1;
                }
                c0236c.b(i5, arrayList, nVar3);
            }
            int i10 = nVar3.f2391b;
            if (i2 == 0) {
                c0237d.f2311m0 = i10;
                c0237d.f2270H.b(i2, arrayList, nVar3);
                c0237d.f2272J.b(i2, arrayList, nVar3);
            } else {
                c0237d.f2313n0 = i10;
                c0237d.f2271I.b(i2, arrayList, nVar3);
                c0237d.f2274L.b(i2, arrayList, nVar3);
                c0237d.f2273K.b(i2, arrayList, nVar3);
            }
            c0237d.f2277O.b(i2, arrayList, nVar3);
        }
        return nVar3;
    }

    /* JADX WARN: Type inference failed for: r3v11, types: [t.b, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v8, types: [t.b, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v7, types: [t.b, java.lang.Object] */
    public static void c(int i2, C0237d c0237d, v.f fVar, boolean z2) {
        boolean z3;
        C0236c c0236c;
        C0236c c0236c2;
        char c2;
        C0236c c0236c3;
        C0236c c0236c4;
        if (c0237d.f2310m) {
            return;
        }
        if (!(c0237d instanceof s.e) && c0237d.x() && a(c0237d)) {
            s.e.R(c0237d, fVar, new Object());
        }
        C0236c g2 = c0237d.g(2);
        C0236c g3 = c0237d.g(4);
        int c3 = g2.c();
        int c4 = g3.c();
        HashSet hashSet = g2.f2255a;
        char c5 = 0;
        if (hashSet != null && g2.f2257c) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                C0236c c0236c5 = (C0236c) it.next();
                C0237d c0237d2 = c0236c5.f2258d;
                int i3 = i2 + 1;
                boolean a2 = a(c0237d2);
                if (c0237d2.x() && a2) {
                    s.e.R(c0237d2, fVar, new Object());
                }
                C0236c c0236c6 = c0237d2.f2270H;
                C0236c c0236c7 = c0237d2.f2272J;
                if ((c0236c5 == c0236c6 && (c0236c4 = c0236c7.f2260f) != null && c0236c4.f2257c) || (c0236c5 == c0236c7 && (c0236c3 = c0236c6.f2260f) != null && c0236c3.f2257c)) {
                    c2 = 1;
                } else {
                    c2 = c5;
                }
                int i4 = c0237d2.f2315o0[c5];
                if (i4 == 3 && !a2) {
                    if (i4 == 3 && c0237d2.f2322v >= 0 && c0237d2.f2321u >= 0 && ((c0237d2.f2300f0 == 8 || (c0237d2.f2318r == 0 && c0237d2.f2284V == 0.0f)) && !c0237d2.v() && c2 != 0 && !c0237d2.v())) {
                        e(i3, c0237d, fVar, c0237d2, z2);
                    }
                } else if (!c0237d2.x()) {
                    if (c0236c5 == c0236c6 && c0236c7.f2260f == null) {
                        int d2 = c0236c6.d() + c3;
                        c0237d2.F(d2, c0237d2.o() + d2);
                        c(i3, c0237d2, fVar, z2);
                    } else if (c0236c5 == c0236c7 && c0236c6.f2260f == null) {
                        int d3 = c3 - c0236c7.d();
                        c0237d2.F(d3 - c0237d2.o(), d3);
                        c(i3, c0237d2, fVar, z2);
                    } else if (c2 != 0 && !c0237d2.v()) {
                        d(i3, c0237d2, fVar, z2);
                    }
                }
                c5 = 0;
            }
        }
        if (c0237d instanceof s.f) {
            return;
        }
        HashSet hashSet2 = g3.f2255a;
        if (hashSet2 != null && g3.f2257c) {
            Iterator it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                C0236c c0236c8 = (C0236c) it2.next();
                C0237d c0237d3 = c0236c8.f2258d;
                int i5 = i2 + 1;
                boolean a3 = a(c0237d3);
                if (c0237d3.x() && a3) {
                    s.e.R(c0237d3, fVar, new Object());
                }
                C0236c c0236c9 = c0237d3.f2270H;
                C0236c c0236c10 = c0237d3.f2272J;
                if ((c0236c8 == c0236c9 && (c0236c2 = c0236c10.f2260f) != null && c0236c2.f2257c) || (c0236c8 == c0236c10 && (c0236c = c0236c9.f2260f) != null && c0236c.f2257c)) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                int i6 = c0237d3.f2315o0[0];
                if (i6 == 3 && !a3) {
                    if (i6 == 3 && c0237d3.f2322v >= 0 && c0237d3.f2321u >= 0) {
                        if (c0237d3.f2300f0 != 8) {
                            if (c0237d3.f2318r == 0) {
                                if (c0237d3.f2284V == 0.0f) {
                                }
                            }
                        }
                        if (!c0237d3.v() && z3 && !c0237d3.v()) {
                            e(i5, c0237d, fVar, c0237d3, z2);
                        }
                    }
                } else if (!c0237d3.x()) {
                    if (c0236c8 == c0236c9 && c0236c10.f2260f == null) {
                        int d4 = c0236c9.d() + c4;
                        c0237d3.F(d4, c0237d3.o() + d4);
                        c(i5, c0237d3, fVar, z2);
                    } else if (c0236c8 == c0236c10 && c0236c9.f2260f == null) {
                        int d5 = c4 - c0236c10.d();
                        c0237d3.F(d5 - c0237d3.o(), d5);
                        c(i5, c0237d3, fVar, z2);
                    } else if (z3 && !c0237d3.v()) {
                        d(i5, c0237d3, fVar, z2);
                    }
                }
            }
        }
        c0237d.f2310m = true;
    }

    public static void d(int i2, C0237d c0237d, v.f fVar, boolean z2) {
        float f2;
        float f3 = c0237d.f2294c0;
        C0236c c0236c = c0237d.f2270H;
        int c2 = c0236c.f2260f.c();
        C0236c c0236c2 = c0237d.f2272J;
        int c3 = c0236c2.f2260f.c();
        int d2 = c0236c.d() + c2;
        int d3 = c3 - c0236c2.d();
        if (c2 == c3) {
            f3 = 0.5f;
        } else {
            c2 = d2;
            c3 = d3;
        }
        int o2 = c0237d.o();
        int i3 = (c3 - c2) - o2;
        if (c2 > c3) {
            i3 = (c2 - c3) - o2;
        }
        if (i3 > 0) {
            f2 = (f3 * i3) + 0.5f;
        } else {
            f2 = f3 * i3;
        }
        int i4 = ((int) f2) + c2;
        int i5 = i4 + o2;
        if (c2 > c3) {
            i5 = i4 - o2;
        }
        c0237d.F(i4, i5);
        c(i2 + 1, c0237d, fVar, z2);
    }

    public static void e(int i2, C0237d c0237d, v.f fVar, C0237d c0237d2, boolean z2) {
        int o2;
        float f2 = c0237d2.f2294c0;
        C0236c c0236c = c0237d2.f2270H;
        int d2 = c0236c.d() + c0236c.f2260f.c();
        C0236c c0236c2 = c0237d2.f2272J;
        int c2 = c0236c2.f2260f.c() - c0236c2.d();
        if (c2 >= d2) {
            int o3 = c0237d2.o();
            if (c0237d2.f2300f0 != 8) {
                int i3 = c0237d2.f2318r;
                if (i3 == 2) {
                    if (c0237d instanceof s.e) {
                        o2 = c0237d.o();
                    } else {
                        o2 = c0237d.f2281S.o();
                    }
                    o3 = (int) (c0237d2.f2294c0 * 0.5f * o2);
                } else if (i3 == 0) {
                    o3 = c2 - d2;
                }
                o3 = Math.max(c0237d2.f2321u, o3);
                int i4 = c0237d2.f2322v;
                if (i4 > 0) {
                    o3 = Math.min(i4, o3);
                }
            }
            int i5 = d2 + ((int) ((f2 * ((c2 - d2) - o3)) + 0.5f));
            c0237d2.F(i5, o3 + i5);
            c(i2 + 1, c0237d2, fVar, z2);
        }
    }

    public static void f(int i2, C0237d c0237d, v.f fVar) {
        float f2;
        float f3 = c0237d.f2296d0;
        C0236c c0236c = c0237d.f2271I;
        int c2 = c0236c.f2260f.c();
        C0236c c0236c2 = c0237d.f2273K;
        int c3 = c0236c2.f2260f.c();
        int d2 = c0236c.d() + c2;
        int d3 = c3 - c0236c2.d();
        if (c2 == c3) {
            f3 = 0.5f;
        } else {
            c2 = d2;
            c3 = d3;
        }
        int i3 = c0237d.i();
        int i4 = (c3 - c2) - i3;
        if (c2 > c3) {
            i4 = (c2 - c3) - i3;
        }
        if (i4 > 0) {
            f2 = (f3 * i4) + 0.5f;
        } else {
            f2 = f3 * i4;
        }
        int i5 = (int) f2;
        int i6 = c2 + i5;
        int i7 = i6 + i3;
        if (c2 > c3) {
            i6 = c2 - i5;
            i7 = i6 - i3;
        }
        c0237d.G(i6, i7);
        i(i2 + 1, c0237d, fVar);
    }

    public static void g(int i2, C0237d c0237d, v.f fVar, C0237d c0237d2) {
        int i3;
        float f2 = c0237d2.f2296d0;
        C0236c c0236c = c0237d2.f2271I;
        int d2 = c0236c.d() + c0236c.f2260f.c();
        C0236c c0236c2 = c0237d2.f2273K;
        int c2 = c0236c2.f2260f.c() - c0236c2.d();
        if (c2 >= d2) {
            int i4 = c0237d2.i();
            if (c0237d2.f2300f0 != 8) {
                int i5 = c0237d2.f2319s;
                if (i5 == 2) {
                    if (c0237d instanceof s.e) {
                        i3 = c0237d.i();
                    } else {
                        i3 = c0237d.f2281S.i();
                    }
                    i4 = (int) (f2 * 0.5f * i3);
                } else if (i5 == 0) {
                    i4 = c2 - d2;
                }
                i4 = Math.max(c0237d2.f2324x, i4);
                int i6 = c0237d2.y;
                if (i6 > 0) {
                    i4 = Math.min(i6, i4);
                }
            }
            int i7 = d2 + ((int) ((f2 * ((c2 - d2) - i4)) + 0.5f));
            c0237d2.G(i7, i4 + i7);
            i(i2 + 1, c0237d2, fVar);
        }
    }

    public static boolean h(int i2, int i3, int i4, int i5) {
        boolean z2;
        boolean z3;
        if (i4 != 1 && i4 != 2 && (i4 != 4 || i2 == 2)) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (i5 != 1 && i5 != 2 && (i5 != 4 || i3 == 2)) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (z2 || z3) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r10v14, types: [t.b, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v7, types: [t.b, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r15v2, types: [t.b, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v5, types: [t.b, java.lang.Object] */
    public static void i(int i2, C0237d c0237d, v.f fVar) {
        C0236c c0236c;
        boolean z2;
        C0236c c0236c2;
        C0236c c0236c3;
        boolean z3;
        C0236c c0236c4;
        C0236c c0236c5;
        if (c0237d.f2312n) {
            return;
        }
        if (!(c0237d instanceof s.e) && c0237d.x() && a(c0237d)) {
            s.e.R(c0237d, fVar, new Object());
        }
        C0236c g2 = c0237d.g(3);
        C0236c g3 = c0237d.g(5);
        int c2 = g2.c();
        int c3 = g3.c();
        HashSet hashSet = g2.f2255a;
        if (hashSet != null && g2.f2257c) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                C0236c c0236c6 = (C0236c) it.next();
                C0237d c0237d2 = c0236c6.f2258d;
                int i3 = i2 + 1;
                boolean a2 = a(c0237d2);
                if (c0237d2.x() && a2) {
                    s.e.R(c0237d2, fVar, new Object());
                }
                C0236c c0236c7 = c0237d2.f2271I;
                C0236c c0236c8 = c0237d2.f2273K;
                if ((c0236c6 == c0236c7 && (c0236c5 = c0236c8.f2260f) != null && c0236c5.f2257c) || (c0236c6 == c0236c8 && (c0236c4 = c0236c7.f2260f) != null && c0236c4.f2257c)) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                int i4 = c0237d2.f2315o0[1];
                if (i4 == 3 && !a2) {
                    if (i4 == 3 && c0237d2.y >= 0 && c0237d2.f2324x >= 0 && (c0237d2.f2300f0 == 8 || (c0237d2.f2319s == 0 && c0237d2.f2284V == 0.0f))) {
                        if (!c0237d2.w() && z3 && !c0237d2.w()) {
                            g(i3, c0237d, fVar, c0237d2);
                        }
                    }
                } else if (!c0237d2.x()) {
                    if (c0236c6 == c0236c7 && c0236c8.f2260f == null) {
                        int d2 = c0236c7.d() + c2;
                        c0237d2.G(d2, c0237d2.i() + d2);
                        i(i3, c0237d2, fVar);
                    } else if (c0236c6 == c0236c8 && c0236c7.f2260f == null) {
                        int d3 = c2 - c0236c8.d();
                        c0237d2.G(d3 - c0237d2.i(), d3);
                        i(i3, c0237d2, fVar);
                    } else if (z3 && !c0237d2.w()) {
                        f(i3, c0237d2, fVar);
                    }
                }
            }
        }
        if (c0237d instanceof s.f) {
            return;
        }
        HashSet hashSet2 = g3.f2255a;
        if (hashSet2 != null && g3.f2257c) {
            Iterator it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                C0236c c0236c9 = (C0236c) it2.next();
                C0237d c0237d3 = c0236c9.f2258d;
                int i5 = i2 + 1;
                boolean a3 = a(c0237d3);
                if (c0237d3.x() && a3) {
                    s.e.R(c0237d3, fVar, new Object());
                }
                C0236c c0236c10 = c0237d3.f2271I;
                C0236c c0236c11 = c0237d3.f2273K;
                if ((c0236c9 == c0236c10 && (c0236c3 = c0236c11.f2260f) != null && c0236c3.f2257c) || (c0236c9 == c0236c11 && (c0236c2 = c0236c10.f2260f) != null && c0236c2.f2257c)) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                int i6 = c0237d3.f2315o0[1];
                if (i6 == 3 && !a3) {
                    if (i6 == 3 && c0237d3.y >= 0 && c0237d3.f2324x >= 0) {
                        if (c0237d3.f2300f0 != 8) {
                            if (c0237d3.f2319s == 0) {
                                if (c0237d3.f2284V == 0.0f) {
                                }
                            }
                        }
                        if (!c0237d3.w() && z2 && !c0237d3.w()) {
                            g(i5, c0237d, fVar, c0237d3);
                        }
                    }
                } else if (!c0237d3.x()) {
                    if (c0236c9 == c0236c10 && c0236c11.f2260f == null) {
                        int d4 = c0236c10.d() + c3;
                        c0237d3.G(d4, c0237d3.i() + d4);
                        i(i5, c0237d3, fVar);
                    } else if (c0236c9 == c0236c11 && c0236c10.f2260f == null) {
                        int d5 = c3 - c0236c11.d();
                        c0237d3.G(d5 - c0237d3.i(), d5);
                        i(i5, c0237d3, fVar);
                    } else if (z2 && !c0237d3.w()) {
                        f(i5, c0237d3, fVar);
                    }
                }
            }
        }
        C0236c g4 = c0237d.g(6);
        if (g4.f2255a != null && g4.f2257c) {
            int c4 = g4.c();
            Iterator it3 = g4.f2255a.iterator();
            while (it3.hasNext()) {
                C0236c c0236c12 = (C0236c) it3.next();
                C0237d c0237d4 = c0236c12.f2258d;
                int i7 = i2 + 1;
                boolean a4 = a(c0237d4);
                if (c0237d4.x() && a4) {
                    s.e.R(c0237d4, fVar, new Object());
                }
                if (c0237d4.f2315o0[1] != 3 || a4) {
                    if (!c0237d4.x() && c0236c12 == (c0236c = c0237d4.f2274L)) {
                        int d6 = c0236c12.d() + c4;
                        if (c0237d4.f2267E) {
                            int i8 = d6 - c0237d4.f2288Z;
                            int i9 = c0237d4.f2283U + i8;
                            c0237d4.f2287Y = i8;
                            c0237d4.f2271I.i(i8);
                            c0237d4.f2273K.i(i9);
                            c0236c.i(d6);
                            c0237d4.f2308l = true;
                        }
                        i(i7, c0237d4, fVar);
                    }
                }
            }
        }
        c0237d.f2312n = true;
    }
}

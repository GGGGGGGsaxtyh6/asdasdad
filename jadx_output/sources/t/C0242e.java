package t;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import s.C0234a;
import s.C0237d;

/* renamed from: t.e, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0242e {

    /* renamed from: a, reason: collision with root package name */
    public s.e f2364a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f2365b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f2366c;

    /* renamed from: d, reason: collision with root package name */
    public s.e f2367d;

    /* renamed from: e, reason: collision with root package name */
    public ArrayList f2368e;

    /* renamed from: f, reason: collision with root package name */
    public v.f f2369f;

    /* renamed from: g, reason: collision with root package name */
    public C0239b f2370g;
    public ArrayList h;

    /* JADX WARN: Type inference failed for: r10v2, types: [t.l, java.lang.Object] */
    public final void a(C0243f c0243f, int i2, ArrayList arrayList, C0249l c0249l) {
        o oVar = c0243f.f2374d;
        if (oVar.f2397c == null) {
            s.e eVar = this.f2364a;
            if (oVar != eVar.f2295d) {
                C0249l c0249l2 = c0249l;
                if (oVar != eVar.f2297e) {
                    if (c0249l == null) {
                        ?? obj = new Object();
                        obj.f2385a = null;
                        obj.f2386b = new ArrayList();
                        obj.f2385a = oVar;
                        arrayList.add(obj);
                        c0249l2 = obj;
                    }
                    oVar.f2397c = c0249l2;
                    c0249l2.f2386b.add(oVar);
                    C0243f c0243f2 = oVar.h;
                    Iterator it = c0243f2.f2380k.iterator();
                    while (it.hasNext()) {
                        InterfaceC0241d interfaceC0241d = (InterfaceC0241d) it.next();
                        if (interfaceC0241d instanceof C0243f) {
                            a((C0243f) interfaceC0241d, i2, arrayList, c0249l2);
                        }
                    }
                    C0243f c0243f3 = oVar.f2402i;
                    Iterator it2 = c0243f3.f2380k.iterator();
                    while (it2.hasNext()) {
                        InterfaceC0241d interfaceC0241d2 = (InterfaceC0241d) it2.next();
                        if (interfaceC0241d2 instanceof C0243f) {
                            a((C0243f) interfaceC0241d2, i2, arrayList, c0249l2);
                        }
                    }
                    if (i2 == 1 && (oVar instanceof m)) {
                        Iterator it3 = ((m) oVar).f2387k.f2380k.iterator();
                        while (it3.hasNext()) {
                            InterfaceC0241d interfaceC0241d3 = (InterfaceC0241d) it3.next();
                            if (interfaceC0241d3 instanceof C0243f) {
                                a((C0243f) interfaceC0241d3, i2, arrayList, c0249l2);
                            }
                        }
                    }
                    Iterator it4 = c0243f2.f2381l.iterator();
                    while (it4.hasNext()) {
                        a((C0243f) it4.next(), i2, arrayList, c0249l2);
                    }
                    Iterator it5 = c0243f3.f2381l.iterator();
                    while (it5.hasNext()) {
                        a((C0243f) it5.next(), i2, arrayList, c0249l2);
                    }
                    if (i2 == 1 && (oVar instanceof m)) {
                        Iterator it6 = ((m) oVar).f2387k.f2381l.iterator();
                        while (it6.hasNext()) {
                            a((C0243f) it6.next(), i2, arrayList, c0249l2);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0260 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0008 A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(s.e r17) {
        /*
            Method dump skipped, instructions count: 791
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: t.C0242e.b(s.e):void");
    }

    public final void c() {
        ArrayList arrayList = this.f2368e;
        arrayList.clear();
        s.e eVar = this.f2367d;
        eVar.f2295d.f();
        eVar.f2297e.f();
        arrayList.add(eVar.f2295d);
        arrayList.add(eVar.f2297e);
        Iterator it = eVar.f2336p0.iterator();
        HashSet hashSet = null;
        while (it.hasNext()) {
            C0237d c0237d = (C0237d) it.next();
            if (c0237d instanceof s.f) {
                o oVar = new o(c0237d);
                c0237d.f2295d.f();
                c0237d.f2297e.f();
                oVar.f2400f = ((s.f) c0237d).t0;
                arrayList.add(oVar);
            } else {
                if (c0237d.v()) {
                    if (c0237d.f2291b == null) {
                        c0237d.f2291b = new C0240c(c0237d, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(c0237d.f2291b);
                } else {
                    arrayList.add(c0237d.f2295d);
                }
                if (c0237d.w()) {
                    if (c0237d.f2293c == null) {
                        c0237d.f2293c = new C0240c(c0237d, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(c0237d.f2293c);
                } else {
                    arrayList.add(c0237d.f2297e);
                }
                if (c0237d instanceof C0234a) {
                    arrayList.add(new o(c0237d));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((o) it2.next()).f();
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            o oVar2 = (o) it3.next();
            if (oVar2.f2396b != eVar) {
                oVar2.d();
            }
        }
        ArrayList arrayList2 = this.h;
        arrayList2.clear();
        s.e eVar2 = this.f2364a;
        e(eVar2.f2295d, 0, arrayList2);
        e(eVar2.f2297e, 1, arrayList2);
        this.f2365b = false;
    }

    public final int d(s.e eVar, int i2) {
        o oVar;
        o oVar2;
        ArrayList arrayList;
        int i3;
        int i4;
        long j2;
        float f2;
        long j3;
        s.e eVar2 = eVar;
        ArrayList arrayList2 = this.h;
        int size = arrayList2.size();
        int i5 = 0;
        long j4 = 0;
        while (i5 < size) {
            o oVar3 = ((C0249l) arrayList2.get(i5)).f2385a;
            if (!(oVar3 instanceof C0240c) ? !(i2 != 0 ? (oVar3 instanceof m) : (oVar3 instanceof C0248k)) : ((C0240c) oVar3).f2400f != i2) {
                arrayList = arrayList2;
                i3 = size;
                i4 = i5;
                j2 = 0;
            } else {
                if (i2 == 0) {
                    oVar = eVar2.f2295d;
                } else {
                    oVar = eVar2.f2297e;
                }
                C0243f c0243f = oVar.h;
                if (i2 == 0) {
                    oVar2 = eVar2.f2295d;
                } else {
                    oVar2 = eVar2.f2297e;
                }
                C0243f c0243f2 = oVar2.f2402i;
                boolean contains = oVar3.h.f2381l.contains(c0243f);
                C0243f c0243f3 = oVar3.f2402i;
                boolean contains2 = c0243f3.f2381l.contains(c0243f2);
                long j5 = oVar3.j();
                C0243f c0243f4 = oVar3.h;
                if (contains && contains2) {
                    long b2 = C0249l.b(c0243f4, 0L);
                    ArrayList arrayList3 = arrayList2;
                    i3 = size;
                    long a2 = C0249l.a(c0243f3, 0L);
                    long j6 = b2 - j5;
                    int i6 = c0243f3.f2376f;
                    arrayList = arrayList3;
                    i4 = i5;
                    if (j6 >= (-i6)) {
                        j6 += i6;
                    }
                    long j7 = (-a2) - j5;
                    long j8 = c0243f4.f2376f;
                    long j9 = j7 - j8;
                    if (j9 >= j8) {
                        j9 -= j8;
                    }
                    C0237d c0237d = oVar3.f2396b;
                    if (i2 == 0) {
                        f2 = c0237d.f2294c0;
                    } else if (i2 == 1) {
                        f2 = c0237d.f2296d0;
                    } else {
                        c0237d.getClass();
                        f2 = -1.0f;
                    }
                    if (f2 > 0.0f) {
                        j3 = (((float) j6) / (1.0f - f2)) + (((float) j9) / f2);
                    } else {
                        j3 = 0;
                    }
                    float f3 = (float) j3;
                    j2 = (c0243f4.f2376f + ((((f3 * f2) + 0.5f) + j5) + (((1.0f - f2) * f3) + 0.5f))) - c0243f3.f2376f;
                } else {
                    arrayList = arrayList2;
                    i3 = size;
                    i4 = i5;
                    if (contains) {
                        j2 = Math.max(C0249l.b(c0243f4, c0243f4.f2376f), c0243f4.f2376f + j5);
                    } else if (contains2) {
                        j2 = Math.max(-C0249l.a(c0243f3, c0243f3.f2376f), (-c0243f3.f2376f) + j5);
                    } else {
                        j2 = (oVar3.j() + c0243f4.f2376f) - c0243f3.f2376f;
                    }
                }
            }
            j4 = Math.max(j4, j2);
            i5 = i4 + 1;
            eVar2 = eVar;
            size = i3;
            arrayList2 = arrayList;
        }
        return (int) j4;
    }

    public final void e(o oVar, int i2, ArrayList arrayList) {
        C0243f c0243f;
        Iterator it = oVar.h.f2380k.iterator();
        while (true) {
            boolean hasNext = it.hasNext();
            c0243f = oVar.f2402i;
            if (!hasNext) {
                break;
            }
            InterfaceC0241d interfaceC0241d = (InterfaceC0241d) it.next();
            if (interfaceC0241d instanceof C0243f) {
                a((C0243f) interfaceC0241d, i2, arrayList, null);
            } else if (interfaceC0241d instanceof o) {
                a(((o) interfaceC0241d).h, i2, arrayList, null);
            }
        }
        Iterator it2 = c0243f.f2380k.iterator();
        while (it2.hasNext()) {
            InterfaceC0241d interfaceC0241d2 = (InterfaceC0241d) it2.next();
            if (interfaceC0241d2 instanceof C0243f) {
                a((C0243f) interfaceC0241d2, i2, arrayList, null);
            } else if (interfaceC0241d2 instanceof o) {
                a(((o) interfaceC0241d2).f2402i, i2, arrayList, null);
            }
        }
        if (i2 == 1) {
            Iterator it3 = ((m) oVar).f2387k.f2380k.iterator();
            while (it3.hasNext()) {
                InterfaceC0241d interfaceC0241d3 = (InterfaceC0241d) it3.next();
                if (interfaceC0241d3 instanceof C0243f) {
                    a((C0243f) interfaceC0241d3, i2, arrayList, null);
                }
            }
        }
    }

    public final void f(int i2, int i3, int i4, int i5, C0237d c0237d) {
        boolean z2;
        C0239b c0239b = this.f2370g;
        c0239b.f2353a = i2;
        c0239b.f2354b = i4;
        c0239b.f2355c = i3;
        c0239b.f2356d = i5;
        this.f2369f.b(c0237d, c0239b);
        c0237d.K(c0239b.f2357e);
        c0237d.H(c0239b.f2358f);
        c0237d.f2267E = c0239b.h;
        int i6 = c0239b.f2359g;
        c0237d.f2288Z = i6;
        if (i6 > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        c0237d.f2267E = z2;
    }

    public final void g() {
        boolean z2;
        C0238a c0238a;
        Iterator it = this.f2364a.f2336p0.iterator();
        while (it.hasNext()) {
            C0237d c0237d = (C0237d) it.next();
            if (!c0237d.f2289a) {
                int[] iArr = c0237d.f2315o0;
                boolean z3 = false;
                int i2 = iArr[0];
                int i3 = iArr[1];
                int i4 = c0237d.f2318r;
                int i5 = c0237d.f2319s;
                if (i2 != 2 && (i2 != 3 || i4 != 1)) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (i3 == 2 || (i3 == 3 && i5 == 1)) {
                    z3 = true;
                }
                C0244g c0244g = c0237d.f2295d.f2399e;
                boolean z4 = c0244g.f2379j;
                C0244g c0244g2 = c0237d.f2297e.f2399e;
                boolean z5 = c0244g2.f2379j;
                if (z4 && z5) {
                    f(1, c0244g.f2377g, 1, c0244g2.f2377g, c0237d);
                    c0237d.f2289a = true;
                } else if (z4 && z3) {
                    f(1, c0244g.f2377g, 2, c0244g2.f2377g, c0237d);
                    if (i3 == 3) {
                        c0237d.f2297e.f2399e.f2382m = c0237d.i();
                    } else {
                        c0237d.f2297e.f2399e.d(c0237d.i());
                        c0237d.f2289a = true;
                    }
                } else if (z5 && z2) {
                    f(2, c0244g.f2377g, 1, c0244g2.f2377g, c0237d);
                    if (i2 == 3) {
                        c0237d.f2295d.f2399e.f2382m = c0237d.o();
                    } else {
                        c0237d.f2295d.f2399e.d(c0237d.o());
                        c0237d.f2289a = true;
                    }
                }
                if (c0237d.f2289a && (c0238a = c0237d.f2297e.f2388l) != null) {
                    c0238a.d(c0237d.f2288Z);
                }
            }
        }
    }
}

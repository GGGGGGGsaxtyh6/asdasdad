package q;

import B0.F;
import D0.h;
import java.util.Arrays;
import s.C0236c;

/* renamed from: q.c, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0232c {

    /* renamed from: p, reason: collision with root package name */
    public static boolean f2189p = false;

    /* renamed from: q, reason: collision with root package name */
    public static int f2190q = 1000;

    /* renamed from: c, reason: collision with root package name */
    public final e f2193c;

    /* renamed from: f, reason: collision with root package name */
    public C0231b[] f2196f;

    /* renamed from: l, reason: collision with root package name */
    public final h f2201l;

    /* renamed from: o, reason: collision with root package name */
    public C0231b f2204o;

    /* renamed from: a, reason: collision with root package name */
    public boolean f2191a = false;

    /* renamed from: b, reason: collision with root package name */
    public int f2192b = 0;

    /* renamed from: d, reason: collision with root package name */
    public int f2194d = 32;

    /* renamed from: e, reason: collision with root package name */
    public int f2195e = 32;

    /* renamed from: g, reason: collision with root package name */
    public boolean f2197g = false;
    public boolean[] h = new boolean[32];

    /* renamed from: i, reason: collision with root package name */
    public int f2198i = 1;

    /* renamed from: j, reason: collision with root package name */
    public int f2199j = 0;

    /* renamed from: k, reason: collision with root package name */
    public int f2200k = 32;

    /* renamed from: m, reason: collision with root package name */
    public g[] f2202m = new g[f2190q];

    /* renamed from: n, reason: collision with root package name */
    public int f2203n = 0;

    /* JADX WARN: Type inference failed for: r1v2, types: [q.b, q.e] */
    public C0232c() {
        this.f2196f = null;
        this.f2196f = new C0231b[32];
        s();
        h hVar = new h();
        hVar.f258b = new F(3);
        hVar.f259c = new F(3);
        hVar.f260d = new g[32];
        this.f2201l = hVar;
        ?? c0231b = new C0231b(hVar);
        c0231b.f2205f = new g[128];
        c0231b.f2206g = new g[128];
        c0231b.h = 0;
        c0231b.f2207i = new E.c((e) c0231b);
        this.f2193c = c0231b;
        this.f2204o = new C0231b(hVar);
    }

    public static int n(Object obj) {
        g gVar = ((C0236c) obj).f2262i;
        if (gVar != null) {
            return (int) (gVar.f2212e + 0.5f);
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r4v0 */
    public final g a(int i2) {
        F f2 = (F) this.f2201l.f259c;
        int i3 = f2.f79a;
        g gVar = null;
        if (i3 > 0) {
            int i4 = i3 - 1;
            ?? r3 = (Object[]) f2.f80b;
            ?? r4 = r3[i4];
            r3[i4] = 0;
            f2.f79a = i4;
            gVar = r4;
        }
        g gVar2 = gVar;
        if (gVar2 == null) {
            gVar2 = new g(i2);
            gVar2.f2218l = i2;
        } else {
            gVar2.c();
            gVar2.f2218l = i2;
        }
        int i5 = this.f2203n;
        int i6 = f2190q;
        if (i5 >= i6) {
            int i7 = i6 * 2;
            f2190q = i7;
            this.f2202m = (g[]) Arrays.copyOf(this.f2202m, i7);
        }
        g[] gVarArr = this.f2202m;
        int i8 = this.f2203n;
        this.f2203n = i8 + 1;
        gVarArr[i8] = gVar2;
        return gVar2;
    }

    public final void b(g gVar, g gVar2, int i2, float f2, g gVar3, g gVar4, int i3, int i4) {
        C0231b l2 = l();
        if (gVar2 == gVar3) {
            l2.f2187d.g(gVar, 1.0f);
            l2.f2187d.g(gVar4, 1.0f);
            l2.f2187d.g(gVar2, -2.0f);
        } else if (f2 == 0.5f) {
            l2.f2187d.g(gVar, 1.0f);
            l2.f2187d.g(gVar2, -1.0f);
            l2.f2187d.g(gVar3, -1.0f);
            l2.f2187d.g(gVar4, 1.0f);
            if (i2 > 0 || i3 > 0) {
                l2.f2185b = (-i2) + i3;
            }
        } else if (f2 <= 0.0f) {
            l2.f2187d.g(gVar, -1.0f);
            l2.f2187d.g(gVar2, 1.0f);
            l2.f2185b = i2;
        } else if (f2 >= 1.0f) {
            l2.f2187d.g(gVar4, -1.0f);
            l2.f2187d.g(gVar3, 1.0f);
            l2.f2185b = -i3;
        } else {
            float f3 = 1.0f - f2;
            l2.f2187d.g(gVar, f3 * 1.0f);
            l2.f2187d.g(gVar2, f3 * (-1.0f));
            l2.f2187d.g(gVar3, (-1.0f) * f2);
            l2.f2187d.g(gVar4, 1.0f * f2);
            if (i2 > 0 || i3 > 0) {
                l2.f2185b = (i3 * f2) + ((-i2) * f3);
            }
        }
        if (i4 != 8) {
            l2.a(this, i4);
        }
        c(l2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x00d0, code lost:
    
        if (r4.f2217k <= 1) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00d3, code lost:
    
        r12 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00dd, code lost:
    
        if (r4.f2217k <= 1) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x00f2, code lost:
    
        if (r4.f2217k <= 1) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00f5, code lost:
    
        r14 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x00ff, code lost:
    
        if (r4.f2217k <= 1) goto L87;
     */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01b9 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(q.C0231b r17) {
        /*
            Method dump skipped, instructions count: 449
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: q.C0232c.c(q.b):void");
    }

    public final void d(g gVar, int i2) {
        int i3 = gVar.f2210c;
        if (i3 == -1) {
            gVar.d(this, i2);
            for (int i4 = 0; i4 < this.f2192b + 1; i4++) {
                g gVar2 = ((g[]) this.f2201l.f260d)[i4];
            }
            return;
        }
        if (i3 != -1) {
            C0231b c0231b = this.f2196f[i3];
            if (c0231b.f2188e) {
                c0231b.f2185b = i2;
                return;
            }
            if (c0231b.f2187d.d() == 0) {
                c0231b.f2188e = true;
                c0231b.f2185b = i2;
                return;
            }
            C0231b l2 = l();
            if (i2 < 0) {
                l2.f2185b = i2 * (-1);
                l2.f2187d.g(gVar, 1.0f);
            } else {
                l2.f2185b = i2;
                l2.f2187d.g(gVar, -1.0f);
            }
            c(l2);
            return;
        }
        C0231b l3 = l();
        l3.f2184a = gVar;
        float f2 = i2;
        gVar.f2212e = f2;
        l3.f2185b = f2;
        l3.f2188e = true;
        c(l3);
    }

    public final void e(g gVar, g gVar2, int i2, int i3) {
        if (i3 == 8 && gVar2.f2213f && gVar.f2210c == -1) {
            gVar.d(this, gVar2.f2212e + i2);
            return;
        }
        C0231b l2 = l();
        boolean z2 = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z2 = true;
            }
            l2.f2185b = i2;
        }
        if (!z2) {
            l2.f2187d.g(gVar, -1.0f);
            l2.f2187d.g(gVar2, 1.0f);
        } else {
            l2.f2187d.g(gVar, 1.0f);
            l2.f2187d.g(gVar2, -1.0f);
        }
        if (i3 != 8) {
            l2.a(this, i3);
        }
        c(l2);
    }

    public final void f(g gVar, g gVar2, int i2, int i3) {
        C0231b l2 = l();
        g m2 = m();
        m2.f2211d = 0;
        l2.b(gVar, gVar2, m2, i2);
        if (i3 != 8) {
            l2.f2187d.g(j(i3), (int) (l2.f2187d.c(m2) * (-1.0f)));
        }
        c(l2);
    }

    public final void g(g gVar, g gVar2, int i2, int i3) {
        C0231b l2 = l();
        g m2 = m();
        m2.f2211d = 0;
        l2.c(gVar, gVar2, m2, i2);
        if (i3 != 8) {
            l2.f2187d.g(j(i3), (int) (l2.f2187d.c(m2) * (-1.0f)));
        }
        c(l2);
    }

    public final void h(C0231b c0231b) {
        int i2;
        if (c0231b.f2188e) {
            c0231b.f2184a.d(this, c0231b.f2185b);
        } else {
            C0231b[] c0231bArr = this.f2196f;
            int i3 = this.f2199j;
            c0231bArr[i3] = c0231b;
            g gVar = c0231b.f2184a;
            gVar.f2210c = i3;
            this.f2199j = i3 + 1;
            gVar.e(this, c0231b);
        }
        if (this.f2191a) {
            int i4 = 0;
            while (i4 < this.f2199j) {
                if (this.f2196f[i4] == null) {
                    System.out.println("WTF");
                }
                C0231b c0231b2 = this.f2196f[i4];
                if (c0231b2 != null && c0231b2.f2188e) {
                    c0231b2.f2184a.d(this, c0231b2.f2185b);
                    ((F) this.f2201l.f258b).e(c0231b2);
                    this.f2196f[i4] = null;
                    int i5 = i4 + 1;
                    int i6 = i5;
                    while (true) {
                        i2 = this.f2199j;
                        if (i5 >= i2) {
                            break;
                        }
                        C0231b[] c0231bArr2 = this.f2196f;
                        int i7 = i5 - 1;
                        C0231b c0231b3 = c0231bArr2[i5];
                        c0231bArr2[i7] = c0231b3;
                        g gVar2 = c0231b3.f2184a;
                        if (gVar2.f2210c == i5) {
                            gVar2.f2210c = i7;
                        }
                        i6 = i5;
                        i5++;
                    }
                    if (i6 < i2) {
                        this.f2196f[i6] = null;
                    }
                    this.f2199j = i2 - 1;
                    i4--;
                }
                i4++;
            }
            this.f2191a = false;
        }
    }

    public final void i() {
        for (int i2 = 0; i2 < this.f2199j; i2++) {
            C0231b c0231b = this.f2196f[i2];
            c0231b.f2184a.f2212e = c0231b.f2185b;
        }
    }

    public final g j(int i2) {
        if (this.f2198i + 1 >= this.f2195e) {
            o();
        }
        g a2 = a(4);
        int i3 = this.f2192b + 1;
        this.f2192b = i3;
        this.f2198i++;
        a2.f2209b = i3;
        a2.f2211d = i2;
        ((g[]) this.f2201l.f260d)[i3] = a2;
        e eVar = this.f2193c;
        eVar.f2207i.f271b = a2;
        float[] fArr = a2.h;
        Arrays.fill(fArr, 0.0f);
        fArr[a2.f2211d] = 1.0f;
        eVar.j(a2);
        return a2;
    }

    public final g k(Object obj) {
        g gVar = null;
        if (obj == null) {
            return null;
        }
        if (this.f2198i + 1 >= this.f2195e) {
            o();
        }
        if (obj instanceof C0236c) {
            C0236c c0236c = (C0236c) obj;
            gVar = c0236c.f2262i;
            if (gVar == null) {
                c0236c.h();
                gVar = c0236c.f2262i;
            }
            int i2 = gVar.f2209b;
            h hVar = this.f2201l;
            if (i2 == -1 || i2 > this.f2192b || ((g[]) hVar.f260d)[i2] == null) {
                if (i2 != -1) {
                    gVar.c();
                }
                int i3 = this.f2192b + 1;
                this.f2192b = i3;
                this.f2198i++;
                gVar.f2209b = i3;
                gVar.f2218l = 1;
                ((g[]) hVar.f260d)[i3] = gVar;
            }
        }
        return gVar;
    }

    public final C0231b l() {
        Object obj;
        h hVar = this.f2201l;
        F f2 = (F) hVar.f258b;
        int i2 = f2.f79a;
        if (i2 > 0) {
            int i3 = i2 - 1;
            Object[] objArr = (Object[]) f2.f80b;
            obj = objArr[i3];
            objArr[i3] = null;
            f2.f79a = i3;
        } else {
            obj = null;
        }
        C0231b c0231b = (C0231b) obj;
        if (c0231b == null) {
            return new C0231b(hVar);
        }
        c0231b.f2184a = null;
        c0231b.f2187d.b();
        c0231b.f2185b = 0.0f;
        c0231b.f2188e = false;
        return c0231b;
    }

    public final g m() {
        if (this.f2198i + 1 >= this.f2195e) {
            o();
        }
        g a2 = a(3);
        int i2 = this.f2192b + 1;
        this.f2192b = i2;
        this.f2198i++;
        a2.f2209b = i2;
        ((g[]) this.f2201l.f260d)[i2] = a2;
        return a2;
    }

    public final void o() {
        int i2 = this.f2194d * 2;
        this.f2194d = i2;
        this.f2196f = (C0231b[]) Arrays.copyOf(this.f2196f, i2);
        h hVar = this.f2201l;
        hVar.f260d = (g[]) Arrays.copyOf((g[]) hVar.f260d, this.f2194d);
        int i3 = this.f2194d;
        this.h = new boolean[i3];
        this.f2195e = i3;
        this.f2200k = i3;
    }

    public final void p() {
        e eVar = this.f2193c;
        if (eVar.e()) {
            i();
            return;
        }
        if (this.f2197g) {
            for (int i2 = 0; i2 < this.f2199j; i2++) {
                if (!this.f2196f[i2].f2188e) {
                    q(eVar);
                    return;
                }
            }
            i();
            return;
        }
        q(eVar);
    }

    public final void q(e eVar) {
        int i2 = 0;
        while (true) {
            if (i2 >= this.f2199j) {
                break;
            }
            C0231b c0231b = this.f2196f[i2];
            int i3 = 1;
            if (c0231b.f2184a.f2218l != 1) {
                float f2 = 0.0f;
                if (c0231b.f2185b < 0.0f) {
                    boolean z2 = false;
                    int i4 = 0;
                    while (!z2) {
                        i4 += i3;
                        float f3 = Float.MAX_VALUE;
                        int i5 = -1;
                        int i6 = -1;
                        int i7 = 0;
                        int i8 = 0;
                        while (i7 < this.f2199j) {
                            C0231b c0231b2 = this.f2196f[i7];
                            if (c0231b2.f2184a.f2218l != i3 && !c0231b2.f2188e && c0231b2.f2185b < f2) {
                                int d2 = c0231b2.f2187d.d();
                                int i9 = 0;
                                while (i9 < d2) {
                                    g e2 = c0231b2.f2187d.e(i9);
                                    float c2 = c0231b2.f2187d.c(e2);
                                    if (c2 > f2) {
                                        for (int i10 = 0; i10 < 9; i10++) {
                                            float f4 = e2.f2214g[i10] / c2;
                                            if ((f4 < f3 && i10 == i8) || i10 > i8) {
                                                i8 = i10;
                                                i6 = e2.f2209b;
                                                i5 = i7;
                                                f3 = f4;
                                            }
                                        }
                                    }
                                    i9++;
                                    f2 = 0.0f;
                                }
                            }
                            i7++;
                            f2 = 0.0f;
                            i3 = 1;
                        }
                        if (i5 != -1) {
                            C0231b c0231b3 = this.f2196f[i5];
                            c0231b3.f2184a.f2210c = -1;
                            c0231b3.g(((g[]) this.f2201l.f260d)[i6]);
                            g gVar = c0231b3.f2184a;
                            gVar.f2210c = i5;
                            gVar.e(this, c0231b3);
                        } else {
                            z2 = true;
                        }
                        if (i4 > this.f2198i / 2) {
                            z2 = true;
                        }
                        f2 = 0.0f;
                        i3 = 1;
                    }
                }
            }
            i2++;
        }
        r(eVar);
        i();
    }

    public final void r(C0231b c0231b) {
        boolean z2;
        int i2 = 0;
        for (int i3 = 0; i3 < this.f2198i; i3++) {
            this.h[i3] = false;
        }
        boolean z3 = false;
        int i4 = 0;
        while (!z3) {
            int i5 = 1;
            i4++;
            if (i4 >= this.f2198i * 2) {
                return;
            }
            g gVar = c0231b.f2184a;
            if (gVar != null) {
                this.h[gVar.f2209b] = true;
            }
            g d2 = c0231b.d(this.h);
            if (d2 != null) {
                boolean[] zArr = this.h;
                int i6 = d2.f2209b;
                if (zArr[i6]) {
                    return;
                } else {
                    zArr[i6] = true;
                }
            }
            if (d2 != null) {
                float f2 = Float.MAX_VALUE;
                int i7 = i2;
                int i8 = -1;
                while (i7 < this.f2199j) {
                    C0231b c0231b2 = this.f2196f[i7];
                    if (c0231b2.f2184a.f2218l != i5 && !c0231b2.f2188e) {
                        C0230a c0230a = c0231b2.f2187d;
                        int i9 = c0230a.h;
                        if (i9 != -1) {
                            for (int i10 = 0; i9 != -1 && i10 < c0230a.f2175a; i10++) {
                                if (c0230a.f2179e[i9] == d2.f2209b) {
                                    z2 = true;
                                    break;
                                }
                                i9 = c0230a.f2180f[i9];
                            }
                        }
                        z2 = false;
                        if (z2) {
                            float c2 = c0231b2.f2187d.c(d2);
                            if (c2 < 0.0f) {
                                float f3 = (-c0231b2.f2185b) / c2;
                                if (f3 < f2) {
                                    f2 = f3;
                                    i8 = i7;
                                }
                            }
                        }
                    }
                    i7++;
                    i5 = 1;
                }
                if (i8 > -1) {
                    C0231b c0231b3 = this.f2196f[i8];
                    c0231b3.f2184a.f2210c = -1;
                    c0231b3.g(d2);
                    g gVar2 = c0231b3.f2184a;
                    gVar2.f2210c = i8;
                    gVar2.e(this, c0231b3);
                }
            } else {
                z3 = true;
            }
            i2 = 0;
        }
    }

    public final void s() {
        for (int i2 = 0; i2 < this.f2199j; i2++) {
            C0231b c0231b = this.f2196f[i2];
            if (c0231b != null) {
                ((F) this.f2201l.f258b).e(c0231b);
            }
            this.f2196f[i2] = null;
        }
    }

    public final void t() {
        h hVar;
        int i2 = 0;
        while (true) {
            hVar = this.f2201l;
            g[] gVarArr = (g[]) hVar.f260d;
            if (i2 >= gVarArr.length) {
                break;
            }
            g gVar = gVarArr[i2];
            if (gVar != null) {
                gVar.c();
            }
            i2++;
        }
        F f2 = (F) hVar.f259c;
        g[] gVarArr2 = this.f2202m;
        int i3 = this.f2203n;
        f2.getClass();
        if (i3 > gVarArr2.length) {
            i3 = gVarArr2.length;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            g gVar2 = gVarArr2[i4];
            int i5 = f2.f79a;
            Object[] objArr = (Object[]) f2.f80b;
            if (i5 < objArr.length) {
                objArr[i5] = gVar2;
                f2.f79a = i5 + 1;
            }
        }
        this.f2203n = 0;
        Arrays.fill((g[]) hVar.f260d, (Object) null);
        this.f2192b = 0;
        e eVar = this.f2193c;
        eVar.h = 0;
        eVar.f2185b = 0.0f;
        this.f2198i = 1;
        for (int i6 = 0; i6 < this.f2199j; i6++) {
            C0231b c0231b = this.f2196f[i6];
        }
        s();
        this.f2199j = 0;
        this.f2204o = new C0231b(hVar);
    }
}

package t;

import s.C0234a;
import s.C0236c;
import s.C0237d;

/* renamed from: t.k, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0248k extends o {

    /* renamed from: k, reason: collision with root package name */
    public static final int[] f2384k = new int[2];

    public static void m(int[] iArr, int i2, int i3, int i4, int i5, float f2, int i6) {
        int i7 = i3 - i2;
        int i8 = i5 - i4;
        if (i6 != -1) {
            if (i6 != 0) {
                if (i6 == 1) {
                    iArr[0] = i7;
                    iArr[1] = (int) ((i7 * f2) + 0.5f);
                    return;
                }
                return;
            }
            iArr[0] = (int) ((i8 * f2) + 0.5f);
            iArr[1] = i8;
            return;
        }
        int i9 = (int) ((i8 * f2) + 0.5f);
        int i10 = (int) ((i7 / f2) + 0.5f);
        if (i9 <= i7) {
            iArr[0] = i9;
            iArr[1] = i8;
        } else if (i10 <= i8) {
            iArr[0] = i7;
            iArr[1] = i10;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:0x0244, code lost:
    
        if (r3 != 1) goto L128;
     */
    @Override // t.InterfaceC0241d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(t.InterfaceC0241d r24) {
        /*
            Method dump skipped, instructions count: 907
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: t.C0248k.a(t.d):void");
    }

    @Override // t.o
    public final void d() {
        C0237d c0237d;
        C0237d c0237d2;
        int i2;
        C0237d c0237d3;
        C0237d c0237d4;
        int i3;
        C0237d c0237d5 = this.f2396b;
        boolean z2 = c0237d5.f2289a;
        C0244g c0244g = this.f2399e;
        if (z2) {
            c0244g.d(c0237d5.o());
        }
        boolean z3 = c0244g.f2379j;
        C0243f c0243f = this.f2402i;
        C0243f c0243f2 = this.h;
        if (!z3) {
            C0237d c0237d6 = this.f2396b;
            int i4 = c0237d6.f2315o0[0];
            this.f2398d = i4;
            if (i4 != 3) {
                if (i4 == 4 && (c0237d4 = c0237d6.f2281S) != null && ((i3 = c0237d4.f2315o0[0]) == 1 || i3 == 4)) {
                    int o2 = (c0237d4.o() - this.f2396b.f2270H.d()) - this.f2396b.f2272J.d();
                    o.b(c0243f2, c0237d4.f2295d.h, this.f2396b.f2270H.d());
                    o.b(c0243f, c0237d4.f2295d.f2402i, -this.f2396b.f2272J.d());
                    c0244g.d(o2);
                    return;
                }
                if (i4 == 1) {
                    c0244g.d(c0237d6.o());
                }
            }
        } else if (this.f2398d == 4 && (c0237d2 = (c0237d = this.f2396b).f2281S) != null && ((i2 = c0237d2.f2315o0[0]) == 1 || i2 == 4)) {
            o.b(c0243f2, c0237d2.f2295d.h, c0237d.f2270H.d());
            o.b(c0243f, c0237d2.f2295d.f2402i, -this.f2396b.f2272J.d());
            return;
        }
        if (c0244g.f2379j) {
            C0237d c0237d7 = this.f2396b;
            if (c0237d7.f2289a) {
                C0236c[] c0236cArr = c0237d7.f2278P;
                C0236c c0236c = c0236cArr[0];
                C0236c c0236c2 = c0236c.f2260f;
                if (c0236c2 != null && c0236cArr[1].f2260f != null) {
                    if (c0237d7.v()) {
                        c0243f2.f2376f = this.f2396b.f2278P[0].d();
                        c0243f.f2376f = -this.f2396b.f2278P[1].d();
                        return;
                    }
                    C0243f h = o.h(this.f2396b.f2278P[0]);
                    if (h != null) {
                        o.b(c0243f2, h, this.f2396b.f2278P[0].d());
                    }
                    C0243f h2 = o.h(this.f2396b.f2278P[1]);
                    if (h2 != null) {
                        o.b(c0243f, h2, -this.f2396b.f2278P[1].d());
                    }
                    c0243f2.f2372b = true;
                    c0243f.f2372b = true;
                    return;
                }
                if (c0236c2 != null) {
                    C0243f h3 = o.h(c0236c);
                    if (h3 != null) {
                        o.b(c0243f2, h3, this.f2396b.f2278P[0].d());
                        o.b(c0243f, c0243f2, c0244g.f2377g);
                        return;
                    }
                    return;
                }
                C0236c c0236c3 = c0236cArr[1];
                if (c0236c3.f2260f != null) {
                    C0243f h4 = o.h(c0236c3);
                    if (h4 != null) {
                        o.b(c0243f, h4, -this.f2396b.f2278P[1].d());
                        o.b(c0243f2, c0243f, -c0244g.f2377g);
                        return;
                    }
                    return;
                }
                if (!(c0237d7 instanceof C0234a) && c0237d7.f2281S != null && c0237d7.g(7).f2260f == null) {
                    C0237d c0237d8 = this.f2396b;
                    o.b(c0243f2, c0237d8.f2281S.f2295d.h, c0237d8.p());
                    o.b(c0243f, c0243f2, c0244g.f2377g);
                    return;
                }
                return;
            }
        }
        if (this.f2398d == 3) {
            C0237d c0237d9 = this.f2396b;
            int i5 = c0237d9.f2318r;
            if (i5 != 2) {
                if (i5 == 3) {
                    if (c0237d9.f2319s == 3) {
                        c0243f2.f2371a = this;
                        c0243f.f2371a = this;
                        m mVar = c0237d9.f2297e;
                        mVar.h.f2371a = this;
                        mVar.f2402i.f2371a = this;
                        c0244g.f2371a = this;
                        if (c0237d9.w()) {
                            c0244g.f2381l.add(this.f2396b.f2297e.f2399e);
                            this.f2396b.f2297e.f2399e.f2380k.add(c0244g);
                            m mVar2 = this.f2396b.f2297e;
                            mVar2.f2399e.f2371a = this;
                            c0244g.f2381l.add(mVar2.h);
                            c0244g.f2381l.add(this.f2396b.f2297e.f2402i);
                            this.f2396b.f2297e.h.f2380k.add(c0244g);
                            this.f2396b.f2297e.f2402i.f2380k.add(c0244g);
                        } else if (this.f2396b.v()) {
                            this.f2396b.f2297e.f2399e.f2381l.add(c0244g);
                            c0244g.f2380k.add(this.f2396b.f2297e.f2399e);
                        } else {
                            this.f2396b.f2297e.f2399e.f2381l.add(c0244g);
                        }
                    } else {
                        C0244g c0244g2 = c0237d9.f2297e.f2399e;
                        c0244g.f2381l.add(c0244g2);
                        c0244g2.f2380k.add(c0244g);
                        this.f2396b.f2297e.h.f2380k.add(c0244g);
                        this.f2396b.f2297e.f2402i.f2380k.add(c0244g);
                        c0244g.f2372b = true;
                        c0244g.f2380k.add(c0243f2);
                        c0244g.f2380k.add(c0243f);
                        c0243f2.f2381l.add(c0244g);
                        c0243f.f2381l.add(c0244g);
                    }
                }
            } else {
                C0237d c0237d10 = c0237d9.f2281S;
                if (c0237d10 != null) {
                    C0244g c0244g3 = c0237d10.f2297e.f2399e;
                    c0244g.f2381l.add(c0244g3);
                    c0244g3.f2380k.add(c0244g);
                    c0244g.f2372b = true;
                    c0244g.f2380k.add(c0243f2);
                    c0244g.f2380k.add(c0243f);
                }
            }
        }
        C0237d c0237d11 = this.f2396b;
        C0236c[] c0236cArr2 = c0237d11.f2278P;
        C0236c c0236c4 = c0236cArr2[0];
        C0236c c0236c5 = c0236c4.f2260f;
        if (c0236c5 != null && c0236cArr2[1].f2260f != null) {
            if (c0237d11.v()) {
                c0243f2.f2376f = this.f2396b.f2278P[0].d();
                c0243f.f2376f = -this.f2396b.f2278P[1].d();
                return;
            }
            C0243f h5 = o.h(this.f2396b.f2278P[0]);
            C0243f h6 = o.h(this.f2396b.f2278P[1]);
            if (h5 != null) {
                h5.b(this);
            }
            if (h6 != null) {
                h6.b(this);
            }
            this.f2403j = 4;
            return;
        }
        if (c0236c5 != null) {
            C0243f h7 = o.h(c0236c4);
            if (h7 != null) {
                o.b(c0243f2, h7, this.f2396b.f2278P[0].d());
                c(c0243f, c0243f2, 1, c0244g);
                return;
            }
            return;
        }
        C0236c c0236c6 = c0236cArr2[1];
        if (c0236c6.f2260f != null) {
            C0243f h8 = o.h(c0236c6);
            if (h8 != null) {
                o.b(c0243f, h8, -this.f2396b.f2278P[1].d());
                c(c0243f2, c0243f, -1, c0244g);
                return;
            }
            return;
        }
        if (!(c0237d11 instanceof C0234a) && (c0237d3 = c0237d11.f2281S) != null) {
            o.b(c0243f2, c0237d3.f2295d.h, c0237d11.p());
            c(c0243f, c0243f2, 1, c0244g);
        }
    }

    @Override // t.o
    public final void e() {
        C0243f c0243f = this.h;
        if (c0243f.f2379j) {
            this.f2396b.f2286X = c0243f.f2377g;
        }
    }

    @Override // t.o
    public final void f() {
        this.f2397c = null;
        this.h.c();
        this.f2402i.c();
        this.f2399e.c();
        this.f2401g = false;
    }

    @Override // t.o
    public final boolean k() {
        if (this.f2398d != 3 || this.f2396b.f2318r == 0) {
            return true;
        }
        return false;
    }

    public final void n() {
        this.f2401g = false;
        C0243f c0243f = this.h;
        c0243f.c();
        c0243f.f2379j = false;
        C0243f c0243f2 = this.f2402i;
        c0243f2.c();
        c0243f2.f2379j = false;
        this.f2399e.f2379j = false;
    }

    public final String toString() {
        return "HorizontalRun " + this.f2396b.g0;
    }
}

package t;

import s.C0234a;
import s.C0236c;
import s.C0237d;

/* loaded from: classes.dex */
public final class m extends o {

    /* renamed from: k, reason: collision with root package name */
    public C0243f f2387k;

    /* renamed from: l, reason: collision with root package name */
    public C0238a f2388l;

    @Override // t.InterfaceC0241d
    public final void a(InterfaceC0241d interfaceC0241d) {
        float f2;
        float f3;
        float f4;
        int i2;
        if (q.f.a(this.f2403j) != 3) {
            C0244g c0244g = this.f2399e;
            if (c0244g.f2373c && !c0244g.f2379j && this.f2398d == 3) {
                C0237d c0237d = this.f2396b;
                int i3 = c0237d.f2319s;
                if (i3 != 2) {
                    if (i3 == 3) {
                        C0244g c0244g2 = c0237d.f2295d.f2399e;
                        if (c0244g2.f2379j) {
                            int i4 = c0237d.f2285W;
                            if (i4 != -1) {
                                if (i4 != 0) {
                                    if (i4 != 1) {
                                        i2 = 0;
                                        c0244g.d(i2);
                                    } else {
                                        f2 = c0244g2.f2377g;
                                        f3 = c0237d.f2284V;
                                    }
                                } else {
                                    f4 = c0244g2.f2377g * c0237d.f2284V;
                                    i2 = (int) (f4 + 0.5f);
                                    c0244g.d(i2);
                                }
                            } else {
                                f2 = c0244g2.f2377g;
                                f3 = c0237d.f2284V;
                            }
                            f4 = f2 / f3;
                            i2 = (int) (f4 + 0.5f);
                            c0244g.d(i2);
                        }
                    }
                } else {
                    C0237d c0237d2 = c0237d.f2281S;
                    if (c0237d2 != null) {
                        if (c0237d2.f2297e.f2399e.f2379j) {
                            c0244g.d((int) ((r5.f2377g * c0237d.f2325z) + 0.5f));
                        }
                    }
                }
            }
            C0243f c0243f = this.h;
            if (c0243f.f2373c) {
                C0243f c0243f2 = this.f2402i;
                if (c0243f2.f2373c) {
                    if (c0243f.f2379j && c0243f2.f2379j && c0244g.f2379j) {
                        return;
                    }
                    if (!c0244g.f2379j && this.f2398d == 3) {
                        C0237d c0237d3 = this.f2396b;
                        if (c0237d3.f2318r == 0 && !c0237d3.w()) {
                            C0243f c0243f3 = (C0243f) c0243f.f2381l.get(0);
                            C0243f c0243f4 = (C0243f) c0243f2.f2381l.get(0);
                            int i5 = c0243f3.f2377g + c0243f.f2376f;
                            int i6 = c0243f4.f2377g + c0243f2.f2376f;
                            c0243f.d(i5);
                            c0243f2.d(i6);
                            c0244g.d(i6 - i5);
                            return;
                        }
                    }
                    if (!c0244g.f2379j && this.f2398d == 3 && this.f2395a == 1 && c0243f.f2381l.size() > 0 && c0243f2.f2381l.size() > 0) {
                        C0243f c0243f5 = (C0243f) c0243f.f2381l.get(0);
                        int i7 = (((C0243f) c0243f2.f2381l.get(0)).f2377g + c0243f2.f2376f) - (c0243f5.f2377g + c0243f.f2376f);
                        int i8 = c0244g.f2382m;
                        if (i7 < i8) {
                            c0244g.d(i7);
                        } else {
                            c0244g.d(i8);
                        }
                    }
                    if (c0244g.f2379j && c0243f.f2381l.size() > 0 && c0243f2.f2381l.size() > 0) {
                        C0243f c0243f6 = (C0243f) c0243f.f2381l.get(0);
                        C0243f c0243f7 = (C0243f) c0243f2.f2381l.get(0);
                        int i9 = c0243f6.f2377g;
                        int i10 = c0243f.f2376f + i9;
                        int i11 = c0243f7.f2377g;
                        int i12 = c0243f2.f2376f + i11;
                        float f5 = this.f2396b.f2296d0;
                        if (c0243f6 == c0243f7) {
                            f5 = 0.5f;
                        } else {
                            i9 = i10;
                            i11 = i12;
                        }
                        c0243f.d((int) ((((i11 - i9) - c0244g.f2377g) * f5) + i9 + 0.5f));
                        c0243f2.d(c0243f.f2377g + c0244g.f2377g);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        C0237d c0237d4 = this.f2396b;
        l(c0237d4.f2271I, c0237d4.f2273K, 1);
    }

    /* JADX WARN: Type inference failed for: r0v124, types: [t.a, t.g] */
    @Override // t.o
    public final void d() {
        C0237d c0237d;
        C0237d c0237d2;
        C0237d c0237d3;
        C0237d c0237d4;
        C0237d c0237d5 = this.f2396b;
        boolean z2 = c0237d5.f2289a;
        C0244g c0244g = this.f2399e;
        if (z2) {
            c0244g.d(c0237d5.i());
        }
        boolean z3 = c0244g.f2379j;
        C0243f c0243f = this.f2402i;
        C0243f c0243f2 = this.h;
        if (!z3) {
            C0237d c0237d6 = this.f2396b;
            this.f2398d = c0237d6.f2315o0[1];
            if (c0237d6.f2267E) {
                this.f2388l = new C0244g(this);
            }
            int i2 = this.f2398d;
            if (i2 != 3) {
                if (i2 == 4 && (c0237d4 = this.f2396b.f2281S) != null && c0237d4.f2315o0[1] == 1) {
                    int i3 = (c0237d4.i() - this.f2396b.f2271I.d()) - this.f2396b.f2273K.d();
                    o.b(c0243f2, c0237d4.f2297e.h, this.f2396b.f2271I.d());
                    o.b(c0243f, c0237d4.f2297e.f2402i, -this.f2396b.f2273K.d());
                    c0244g.d(i3);
                    return;
                }
                if (i2 == 1) {
                    c0244g.d(this.f2396b.i());
                }
            }
        } else if (this.f2398d == 4 && (c0237d2 = (c0237d = this.f2396b).f2281S) != null && c0237d2.f2315o0[1] == 1) {
            o.b(c0243f2, c0237d2.f2297e.h, c0237d.f2271I.d());
            o.b(c0243f, c0237d2.f2297e.f2402i, -this.f2396b.f2273K.d());
            return;
        }
        boolean z4 = c0244g.f2379j;
        C0243f c0243f3 = this.f2387k;
        if (z4) {
            C0237d c0237d7 = this.f2396b;
            if (c0237d7.f2289a) {
                C0236c[] c0236cArr = c0237d7.f2278P;
                C0236c c0236c = c0236cArr[2];
                C0236c c0236c2 = c0236c.f2260f;
                if (c0236c2 != null && c0236cArr[3].f2260f != null) {
                    if (c0237d7.w()) {
                        c0243f2.f2376f = this.f2396b.f2278P[2].d();
                        c0243f.f2376f = -this.f2396b.f2278P[3].d();
                    } else {
                        C0243f h = o.h(this.f2396b.f2278P[2]);
                        if (h != null) {
                            o.b(c0243f2, h, this.f2396b.f2278P[2].d());
                        }
                        C0243f h2 = o.h(this.f2396b.f2278P[3]);
                        if (h2 != null) {
                            o.b(c0243f, h2, -this.f2396b.f2278P[3].d());
                        }
                        c0243f2.f2372b = true;
                        c0243f.f2372b = true;
                    }
                    C0237d c0237d8 = this.f2396b;
                    if (c0237d8.f2267E) {
                        o.b(c0243f3, c0243f2, c0237d8.f2288Z);
                        return;
                    }
                    return;
                }
                if (c0236c2 != null) {
                    C0243f h3 = o.h(c0236c);
                    if (h3 != null) {
                        o.b(c0243f2, h3, this.f2396b.f2278P[2].d());
                        o.b(c0243f, c0243f2, c0244g.f2377g);
                        C0237d c0237d9 = this.f2396b;
                        if (c0237d9.f2267E) {
                            o.b(c0243f3, c0243f2, c0237d9.f2288Z);
                            return;
                        }
                        return;
                    }
                    return;
                }
                C0236c c0236c3 = c0236cArr[3];
                if (c0236c3.f2260f != null) {
                    C0243f h4 = o.h(c0236c3);
                    if (h4 != null) {
                        o.b(c0243f, h4, -this.f2396b.f2278P[3].d());
                        o.b(c0243f2, c0243f, -c0244g.f2377g);
                    }
                    C0237d c0237d10 = this.f2396b;
                    if (c0237d10.f2267E) {
                        o.b(c0243f3, c0243f2, c0237d10.f2288Z);
                        return;
                    }
                    return;
                }
                C0236c c0236c4 = c0236cArr[4];
                if (c0236c4.f2260f != null) {
                    C0243f h5 = o.h(c0236c4);
                    if (h5 != null) {
                        o.b(c0243f3, h5, 0);
                        o.b(c0243f2, c0243f3, -this.f2396b.f2288Z);
                        o.b(c0243f, c0243f2, c0244g.f2377g);
                        return;
                    }
                    return;
                }
                if (!(c0237d7 instanceof C0234a) && c0237d7.f2281S != null && c0237d7.g(7).f2260f == null) {
                    C0237d c0237d11 = this.f2396b;
                    o.b(c0243f2, c0237d11.f2281S.f2297e.h, c0237d11.q());
                    o.b(c0243f, c0243f2, c0244g.f2377g);
                    C0237d c0237d12 = this.f2396b;
                    if (c0237d12.f2267E) {
                        o.b(c0243f3, c0243f2, c0237d12.f2288Z);
                        return;
                    }
                    return;
                }
                return;
            }
        }
        if (!z4 && this.f2398d == 3) {
            C0237d c0237d13 = this.f2396b;
            int i4 = c0237d13.f2319s;
            if (i4 != 2) {
                if (i4 == 3 && !c0237d13.w()) {
                    C0237d c0237d14 = this.f2396b;
                    if (c0237d14.f2318r != 3) {
                        C0244g c0244g2 = c0237d14.f2295d.f2399e;
                        c0244g.f2381l.add(c0244g2);
                        c0244g2.f2380k.add(c0244g);
                        c0244g.f2372b = true;
                        c0244g.f2380k.add(c0243f2);
                        c0244g.f2380k.add(c0243f);
                    }
                }
            } else {
                C0237d c0237d15 = c0237d13.f2281S;
                if (c0237d15 != null) {
                    C0244g c0244g3 = c0237d15.f2297e.f2399e;
                    c0244g.f2381l.add(c0244g3);
                    c0244g3.f2380k.add(c0244g);
                    c0244g.f2372b = true;
                    c0244g.f2380k.add(c0243f2);
                    c0244g.f2380k.add(c0243f);
                }
            }
        } else {
            c0244g.b(this);
        }
        C0237d c0237d16 = this.f2396b;
        C0236c[] c0236cArr2 = c0237d16.f2278P;
        C0236c c0236c5 = c0236cArr2[2];
        C0236c c0236c6 = c0236c5.f2260f;
        if (c0236c6 != null && c0236cArr2[3].f2260f != null) {
            if (c0237d16.w()) {
                c0243f2.f2376f = this.f2396b.f2278P[2].d();
                c0243f.f2376f = -this.f2396b.f2278P[3].d();
            } else {
                C0243f h6 = o.h(this.f2396b.f2278P[2]);
                C0243f h7 = o.h(this.f2396b.f2278P[3]);
                if (h6 != null) {
                    h6.b(this);
                }
                if (h7 != null) {
                    h7.b(this);
                }
                this.f2403j = 4;
            }
            if (this.f2396b.f2267E) {
                c(c0243f3, c0243f2, 1, this.f2388l);
            }
        } else if (c0236c6 != null) {
            C0243f h8 = o.h(c0236c5);
            if (h8 != null) {
                o.b(c0243f2, h8, this.f2396b.f2278P[2].d());
                c(c0243f, c0243f2, 1, c0244g);
                if (this.f2396b.f2267E) {
                    c(c0243f3, c0243f2, 1, this.f2388l);
                }
                if (this.f2398d == 3) {
                    C0237d c0237d17 = this.f2396b;
                    if (c0237d17.f2284V > 0.0f) {
                        C0248k c0248k = c0237d17.f2295d;
                        if (c0248k.f2398d == 3) {
                            c0248k.f2399e.f2380k.add(c0244g);
                            c0244g.f2381l.add(this.f2396b.f2295d.f2399e);
                            c0244g.f2371a = this;
                        }
                    }
                }
            }
        } else {
            C0236c c0236c7 = c0236cArr2[3];
            if (c0236c7.f2260f != null) {
                C0243f h9 = o.h(c0236c7);
                if (h9 != null) {
                    o.b(c0243f, h9, -this.f2396b.f2278P[3].d());
                    c(c0243f2, c0243f, -1, c0244g);
                    if (this.f2396b.f2267E) {
                        c(c0243f3, c0243f2, 1, this.f2388l);
                    }
                }
            } else {
                C0236c c0236c8 = c0236cArr2[4];
                if (c0236c8.f2260f != null) {
                    C0243f h10 = o.h(c0236c8);
                    if (h10 != null) {
                        o.b(c0243f3, h10, 0);
                        c(c0243f2, c0243f3, -1, this.f2388l);
                        c(c0243f, c0243f2, 1, c0244g);
                    }
                } else if (!(c0237d16 instanceof C0234a) && (c0237d3 = c0237d16.f2281S) != null) {
                    o.b(c0243f2, c0237d3.f2297e.h, c0237d16.q());
                    c(c0243f, c0243f2, 1, c0244g);
                    if (this.f2396b.f2267E) {
                        c(c0243f3, c0243f2, 1, this.f2388l);
                    }
                    if (this.f2398d == 3) {
                        C0237d c0237d18 = this.f2396b;
                        if (c0237d18.f2284V > 0.0f) {
                            C0248k c0248k2 = c0237d18.f2295d;
                            if (c0248k2.f2398d == 3) {
                                c0248k2.f2399e.f2380k.add(c0244g);
                                c0244g.f2381l.add(this.f2396b.f2295d.f2399e);
                                c0244g.f2371a = this;
                            }
                        }
                    }
                }
            }
        }
        if (c0244g.f2381l.size() == 0) {
            c0244g.f2373c = true;
        }
    }

    @Override // t.o
    public final void e() {
        C0243f c0243f = this.h;
        if (c0243f.f2379j) {
            this.f2396b.f2287Y = c0243f.f2377g;
        }
    }

    @Override // t.o
    public final void f() {
        this.f2397c = null;
        this.h.c();
        this.f2402i.c();
        this.f2387k.c();
        this.f2399e.c();
        this.f2401g = false;
    }

    @Override // t.o
    public final boolean k() {
        if (this.f2398d != 3 || this.f2396b.f2319s == 0) {
            return true;
        }
        return false;
    }

    public final void m() {
        this.f2401g = false;
        C0243f c0243f = this.h;
        c0243f.c();
        c0243f.f2379j = false;
        C0243f c0243f2 = this.f2402i;
        c0243f2.c();
        c0243f2.f2379j = false;
        C0243f c0243f3 = this.f2387k;
        c0243f3.c();
        c0243f3.f2379j = false;
        this.f2399e.f2379j = false;
    }

    public final String toString() {
        return "VerticalRun " + this.f2396b.g0;
    }
}

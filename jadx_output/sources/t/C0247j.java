package t;

import java.util.Iterator;
import s.C0234a;
import s.C0237d;

/* renamed from: t.j, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0247j extends o {
    @Override // t.InterfaceC0241d
    public final void a(InterfaceC0241d interfaceC0241d) {
        C0234a c0234a = (C0234a) this.f2396b;
        int i2 = c0234a.f2236r0;
        C0243f c0243f = this.h;
        Iterator it = c0243f.f2381l.iterator();
        int i3 = 0;
        int i4 = -1;
        while (it.hasNext()) {
            int i5 = ((C0243f) it.next()).f2377g;
            if (i4 == -1 || i5 < i4) {
                i4 = i5;
            }
            if (i3 < i5) {
                i3 = i5;
            }
        }
        if (i2 != 0 && i2 != 2) {
            c0243f.d(i3 + c0234a.t0);
        } else {
            c0243f.d(i4 + c0234a.t0);
        }
    }

    @Override // t.o
    public final void d() {
        C0237d c0237d = this.f2396b;
        if (c0237d instanceof C0234a) {
            C0243f c0243f = this.h;
            c0243f.f2372b = true;
            C0234a c0234a = (C0234a) c0237d;
            int i2 = c0234a.f2236r0;
            boolean z2 = c0234a.f2237s0;
            int i3 = 0;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 == 3) {
                            c0243f.f2375e = 7;
                            while (i3 < c0234a.f2235q0) {
                                C0237d c0237d2 = c0234a.f2234p0[i3];
                                if (z2 || c0237d2.f2300f0 != 8) {
                                    C0243f c0243f2 = c0237d2.f2297e.f2402i;
                                    c0243f2.f2380k.add(c0243f);
                                    c0243f.f2381l.add(c0243f2);
                                }
                                i3++;
                            }
                            m(this.f2396b.f2297e.h);
                            m(this.f2396b.f2297e.f2402i);
                            return;
                        }
                        return;
                    }
                    c0243f.f2375e = 6;
                    while (i3 < c0234a.f2235q0) {
                        C0237d c0237d3 = c0234a.f2234p0[i3];
                        if (z2 || c0237d3.f2300f0 != 8) {
                            C0243f c0243f3 = c0237d3.f2297e.h;
                            c0243f3.f2380k.add(c0243f);
                            c0243f.f2381l.add(c0243f3);
                        }
                        i3++;
                    }
                    m(this.f2396b.f2297e.h);
                    m(this.f2396b.f2297e.f2402i);
                    return;
                }
                c0243f.f2375e = 5;
                while (i3 < c0234a.f2235q0) {
                    C0237d c0237d4 = c0234a.f2234p0[i3];
                    if (z2 || c0237d4.f2300f0 != 8) {
                        C0243f c0243f4 = c0237d4.f2295d.f2402i;
                        c0243f4.f2380k.add(c0243f);
                        c0243f.f2381l.add(c0243f4);
                    }
                    i3++;
                }
                m(this.f2396b.f2295d.h);
                m(this.f2396b.f2295d.f2402i);
                return;
            }
            c0243f.f2375e = 4;
            while (i3 < c0234a.f2235q0) {
                C0237d c0237d5 = c0234a.f2234p0[i3];
                if (z2 || c0237d5.f2300f0 != 8) {
                    C0243f c0243f5 = c0237d5.f2295d.h;
                    c0243f5.f2380k.add(c0243f);
                    c0243f.f2381l.add(c0243f5);
                }
                i3++;
            }
            m(this.f2396b.f2295d.h);
            m(this.f2396b.f2295d.f2402i);
        }
    }

    @Override // t.o
    public final void e() {
        C0237d c0237d = this.f2396b;
        if (c0237d instanceof C0234a) {
            int i2 = ((C0234a) c0237d).f2236r0;
            C0243f c0243f = this.h;
            if (i2 != 0 && i2 != 1) {
                c0237d.f2287Y = c0243f.f2377g;
            } else {
                c0237d.f2286X = c0243f.f2377g;
            }
        }
    }

    @Override // t.o
    public final void f() {
        this.f2397c = null;
        this.h.c();
    }

    @Override // t.o
    public final boolean k() {
        return false;
    }

    public final void m(C0243f c0243f) {
        C0243f c0243f2 = this.h;
        c0243f2.f2380k.add(c0243f);
        c0243f.f2381l.add(c0243f2);
    }
}

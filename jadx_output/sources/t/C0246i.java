package t;

import s.C0237d;

/* renamed from: t.i, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0246i extends o {
    @Override // t.InterfaceC0241d
    public final void a(InterfaceC0241d interfaceC0241d) {
        C0243f c0243f = this.h;
        if (!c0243f.f2373c || c0243f.f2379j) {
            return;
        }
        c0243f.d((int) ((((C0243f) c0243f.f2381l.get(0)).f2377g * ((s.f) this.f2396b).f2346p0) + 0.5f));
    }

    @Override // t.o
    public final void d() {
        C0237d c0237d = this.f2396b;
        s.f fVar = (s.f) c0237d;
        int i2 = fVar.f2347q0;
        int i3 = fVar.f2348r0;
        int i4 = fVar.t0;
        C0243f c0243f = this.h;
        if (i4 == 1) {
            if (i2 != -1) {
                c0243f.f2381l.add(c0237d.f2281S.f2295d.h);
                this.f2396b.f2281S.f2295d.h.f2380k.add(c0243f);
                c0243f.f2376f = i2;
            } else if (i3 != -1) {
                c0243f.f2381l.add(c0237d.f2281S.f2295d.f2402i);
                this.f2396b.f2281S.f2295d.f2402i.f2380k.add(c0243f);
                c0243f.f2376f = -i3;
            } else {
                c0243f.f2372b = true;
                c0243f.f2381l.add(c0237d.f2281S.f2295d.f2402i);
                this.f2396b.f2281S.f2295d.f2402i.f2380k.add(c0243f);
            }
            m(this.f2396b.f2295d.h);
            m(this.f2396b.f2295d.f2402i);
            return;
        }
        if (i2 != -1) {
            c0243f.f2381l.add(c0237d.f2281S.f2297e.h);
            this.f2396b.f2281S.f2297e.h.f2380k.add(c0243f);
            c0243f.f2376f = i2;
        } else if (i3 != -1) {
            c0243f.f2381l.add(c0237d.f2281S.f2297e.f2402i);
            this.f2396b.f2281S.f2297e.f2402i.f2380k.add(c0243f);
            c0243f.f2376f = -i3;
        } else {
            c0243f.f2372b = true;
            c0243f.f2381l.add(c0237d.f2281S.f2297e.f2402i);
            this.f2396b.f2281S.f2297e.f2402i.f2380k.add(c0243f);
        }
        m(this.f2396b.f2297e.h);
        m(this.f2396b.f2297e.f2402i);
    }

    @Override // t.o
    public final void e() {
        C0237d c0237d = this.f2396b;
        int i2 = ((s.f) c0237d).t0;
        C0243f c0243f = this.h;
        if (i2 == 1) {
            c0237d.f2286X = c0243f.f2377g;
        } else {
            c0237d.f2287Y = c0243f.f2377g;
        }
    }

    @Override // t.o
    public final void f() {
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

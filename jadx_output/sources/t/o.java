package t;

import s.C0236c;
import s.C0237d;

/* loaded from: classes.dex */
public abstract class o implements InterfaceC0241d {

    /* renamed from: a, reason: collision with root package name */
    public int f2395a;

    /* renamed from: b, reason: collision with root package name */
    public C0237d f2396b;

    /* renamed from: c, reason: collision with root package name */
    public C0249l f2397c;

    /* renamed from: d, reason: collision with root package name */
    public int f2398d;

    /* renamed from: e, reason: collision with root package name */
    public final C0244g f2399e = new C0244g(this);

    /* renamed from: f, reason: collision with root package name */
    public int f2400f = 0;

    /* renamed from: g, reason: collision with root package name */
    public boolean f2401g = false;
    public final C0243f h = new C0243f(this);

    /* renamed from: i, reason: collision with root package name */
    public final C0243f f2402i = new C0243f(this);

    /* renamed from: j, reason: collision with root package name */
    public int f2403j = 1;

    public o(C0237d c0237d) {
        this.f2396b = c0237d;
    }

    public static void b(C0243f c0243f, C0243f c0243f2, int i2) {
        c0243f.f2381l.add(c0243f2);
        c0243f.f2376f = i2;
        c0243f2.f2380k.add(c0243f);
    }

    public static C0243f h(C0236c c0236c) {
        C0236c c0236c2 = c0236c.f2260f;
        if (c0236c2 == null) {
            return null;
        }
        int a2 = q.f.a(c0236c2.f2259e);
        C0237d c0237d = c0236c2.f2258d;
        if (a2 != 1) {
            if (a2 != 2) {
                if (a2 != 3) {
                    if (a2 != 4) {
                        if (a2 != 5) {
                            return null;
                        }
                        return c0237d.f2297e.f2387k;
                    }
                    return c0237d.f2297e.f2402i;
                }
                return c0237d.f2295d.f2402i;
            }
            return c0237d.f2297e.h;
        }
        return c0237d.f2295d.h;
    }

    public static C0243f i(C0236c c0236c, int i2) {
        o oVar;
        C0236c c0236c2 = c0236c.f2260f;
        if (c0236c2 == null) {
            return null;
        }
        C0237d c0237d = c0236c2.f2258d;
        if (i2 == 0) {
            oVar = c0237d.f2295d;
        } else {
            oVar = c0237d.f2297e;
        }
        int a2 = q.f.a(c0236c2.f2259e);
        if (a2 != 1 && a2 != 2) {
            if (a2 != 3 && a2 != 4) {
                return null;
            }
            return oVar.f2402i;
        }
        return oVar.h;
    }

    public final void c(C0243f c0243f, C0243f c0243f2, int i2, C0244g c0244g) {
        c0243f.f2381l.add(c0243f2);
        c0243f.f2381l.add(this.f2399e);
        c0243f.h = i2;
        c0243f.f2378i = c0244g;
        c0243f2.f2380k.add(c0243f);
        c0244g.f2380k.add(c0243f);
    }

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final int g(int i2, int i3) {
        int max;
        if (i3 == 0) {
            C0237d c0237d = this.f2396b;
            int i4 = c0237d.f2322v;
            max = Math.max(c0237d.f2321u, i2);
            if (i4 > 0) {
                max = Math.min(i4, i2);
            }
            if (max == i2) {
                return i2;
            }
        } else {
            C0237d c0237d2 = this.f2396b;
            int i5 = c0237d2.y;
            max = Math.max(c0237d2.f2324x, i2);
            if (i5 > 0) {
                max = Math.min(i5, i2);
            }
            if (max == i2) {
                return i2;
            }
        }
        return max;
    }

    public long j() {
        if (this.f2399e.f2379j) {
            return r0.f2377g;
        }
        return 0L;
    }

    public abstract boolean k();

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0051, code lost:
    
        if (r9.f2395a == 3) goto L50;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void l(s.C0236c r12, s.C0236c r13, int r14) {
        /*
            Method dump skipped, instructions count: 232
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: t.o.l(s.c, s.c, int):void");
    }
}

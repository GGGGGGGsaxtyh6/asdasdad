package t;

import java.util.ArrayList;
import java.util.Iterator;
import s.C0236c;
import s.C0237d;

/* renamed from: t.c, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0240c extends o {

    /* renamed from: k, reason: collision with root package name */
    public final ArrayList f2362k;

    /* renamed from: l, reason: collision with root package name */
    public int f2363l;

    public C0240c(C0237d c0237d, int i2) {
        super(c0237d);
        C0237d c0237d2;
        o oVar;
        int i3;
        o oVar2;
        this.f2362k = new ArrayList();
        this.f2400f = i2;
        C0237d c0237d3 = this.f2396b;
        C0237d k2 = c0237d3.k(i2);
        while (true) {
            C0237d c0237d4 = k2;
            c0237d2 = c0237d3;
            c0237d3 = c0237d4;
            if (c0237d3 == null) {
                break;
            } else {
                k2 = c0237d3.k(this.f2400f);
            }
        }
        this.f2396b = c0237d2;
        int i4 = this.f2400f;
        if (i4 == 0) {
            oVar = c0237d2.f2295d;
        } else if (i4 == 1) {
            oVar = c0237d2.f2297e;
        } else {
            oVar = null;
        }
        ArrayList arrayList = this.f2362k;
        arrayList.add(oVar);
        C0237d j2 = c0237d2.j(this.f2400f);
        while (j2 != null) {
            int i5 = this.f2400f;
            if (i5 == 0) {
                oVar2 = j2.f2295d;
            } else if (i5 == 1) {
                oVar2 = j2.f2297e;
            } else {
                oVar2 = null;
            }
            arrayList.add(oVar2);
            j2 = j2.j(this.f2400f);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            o oVar3 = (o) it.next();
            int i6 = this.f2400f;
            if (i6 == 0) {
                oVar3.f2396b.f2291b = this;
            } else if (i6 == 1) {
                oVar3.f2396b.f2293c = this;
            }
        }
        if (this.f2400f == 0 && ((s.e) this.f2396b.f2281S).f2340u0 && arrayList.size() > 1) {
            this.f2396b = ((o) arrayList.get(arrayList.size() - 1)).f2396b;
        }
        if (this.f2400f == 0) {
            i3 = this.f2396b.h0;
        } else {
            i3 = this.f2396b.f2303i0;
        }
        this.f2363l = i3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:291:0x03aa, code lost:
    
        r2 = r2 - r12;
     */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00da  */
    @Override // t.InterfaceC0241d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(t.InterfaceC0241d r27) {
        /*
            Method dump skipped, instructions count: 969
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: t.C0240c.a(t.d):void");
    }

    @Override // t.o
    public final void d() {
        ArrayList arrayList = this.f2362k;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((o) it.next()).d();
        }
        int size = arrayList.size();
        if (size < 1) {
            return;
        }
        C0237d c0237d = ((o) arrayList.get(0)).f2396b;
        C0237d c0237d2 = ((o) arrayList.get(size - 1)).f2396b;
        int i2 = this.f2400f;
        C0243f c0243f = this.f2402i;
        C0243f c0243f2 = this.h;
        if (i2 == 0) {
            C0236c c0236c = c0237d.f2270H;
            C0236c c0236c2 = c0237d2.f2272J;
            C0243f i3 = o.i(c0236c, 0);
            int d2 = c0236c.d();
            C0237d m2 = m();
            if (m2 != null) {
                d2 = m2.f2270H.d();
            }
            if (i3 != null) {
                o.b(c0243f2, i3, d2);
            }
            C0243f i4 = o.i(c0236c2, 0);
            int d3 = c0236c2.d();
            C0237d n2 = n();
            if (n2 != null) {
                d3 = n2.f2272J.d();
            }
            if (i4 != null) {
                o.b(c0243f, i4, -d3);
            }
        } else {
            C0236c c0236c3 = c0237d.f2271I;
            C0236c c0236c4 = c0237d2.f2273K;
            C0243f i5 = o.i(c0236c3, 1);
            int d4 = c0236c3.d();
            C0237d m3 = m();
            if (m3 != null) {
                d4 = m3.f2271I.d();
            }
            if (i5 != null) {
                o.b(c0243f2, i5, d4);
            }
            C0243f i6 = o.i(c0236c4, 1);
            int d5 = c0236c4.d();
            C0237d n3 = n();
            if (n3 != null) {
                d5 = n3.f2273K.d();
            }
            if (i6 != null) {
                o.b(c0243f, i6, -d5);
            }
        }
        c0243f2.f2371a = this;
        c0243f.f2371a = this;
    }

    @Override // t.o
    public final void e() {
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.f2362k;
            if (i2 < arrayList.size()) {
                ((o) arrayList.get(i2)).e();
                i2++;
            } else {
                return;
            }
        }
    }

    @Override // t.o
    public final void f() {
        this.f2397c = null;
        Iterator it = this.f2362k.iterator();
        while (it.hasNext()) {
            ((o) it.next()).f();
        }
    }

    @Override // t.o
    public final long j() {
        ArrayList arrayList = this.f2362k;
        int size = arrayList.size();
        long j2 = 0;
        for (int i2 = 0; i2 < size; i2++) {
            j2 = r5.f2402i.f2376f + ((o) arrayList.get(i2)).j() + j2 + r5.h.f2376f;
        }
        return j2;
    }

    @Override // t.o
    public final boolean k() {
        ArrayList arrayList = this.f2362k;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!((o) arrayList.get(i2)).k()) {
                return false;
            }
        }
        return true;
    }

    public final C0237d m() {
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.f2362k;
            if (i2 < arrayList.size()) {
                C0237d c0237d = ((o) arrayList.get(i2)).f2396b;
                if (c0237d.f2300f0 != 8) {
                    return c0237d;
                }
                i2++;
            } else {
                return null;
            }
        }
    }

    public final C0237d n() {
        ArrayList arrayList = this.f2362k;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            C0237d c0237d = ((o) arrayList.get(size)).f2396b;
            if (c0237d.f2300f0 != 8) {
                return c0237d;
            }
        }
        return null;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("ChainRun ");
        if (this.f2400f == 0) {
            str = "horizontal : ";
        } else {
            str = "vertical : ";
        }
        sb.append(str);
        Iterator it = this.f2362k.iterator();
        while (it.hasNext()) {
            o oVar = (o) it.next();
            sb.append("<");
            sb.append(oVar);
            sb.append("> ");
        }
        return sb.toString();
    }
}

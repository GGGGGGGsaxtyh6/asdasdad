package t;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import q.C0232c;
import s.C0237d;

/* loaded from: classes.dex */
public final class n {

    /* renamed from: f, reason: collision with root package name */
    public static int f2389f;

    /* renamed from: a, reason: collision with root package name */
    public ArrayList f2390a;

    /* renamed from: b, reason: collision with root package name */
    public int f2391b;

    /* renamed from: c, reason: collision with root package name */
    public int f2392c;

    /* renamed from: d, reason: collision with root package name */
    public ArrayList f2393d;

    /* renamed from: e, reason: collision with root package name */
    public int f2394e;

    public final void a(ArrayList arrayList) {
        int size = this.f2390a.size();
        if (this.f2394e != -1 && size > 0) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                n nVar = (n) arrayList.get(i2);
                if (this.f2394e == nVar.f2391b) {
                    c(this.f2392c, nVar);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public final int b(C0232c c0232c, int i2) {
        int n2;
        int n3;
        ArrayList arrayList = this.f2390a;
        if (arrayList.size() == 0) {
            return 0;
        }
        s.e eVar = (s.e) ((C0237d) arrayList.get(0)).f2281S;
        c0232c.t();
        eVar.b(c0232c, false);
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            ((C0237d) arrayList.get(i3)).b(c0232c, false);
        }
        if (i2 == 0 && eVar.f2344y0 > 0) {
            s.g.a(eVar, c0232c, arrayList, 0);
        }
        if (i2 == 1 && eVar.f2345z0 > 0) {
            s.g.a(eVar, c0232c, arrayList, 1);
        }
        try {
            c0232c.p();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.f2393d = new ArrayList();
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            C0237d c0237d = (C0237d) arrayList.get(i4);
            A.m mVar = new A.m(22);
            new WeakReference(c0237d);
            C0232c.n(c0237d.f2270H);
            C0232c.n(c0237d.f2271I);
            C0232c.n(c0237d.f2272J);
            C0232c.n(c0237d.f2273K);
            C0232c.n(c0237d.f2274L);
            this.f2393d.add(mVar);
        }
        if (i2 == 0) {
            n2 = C0232c.n(eVar.f2270H);
            n3 = C0232c.n(eVar.f2272J);
            c0232c.t();
        } else {
            n2 = C0232c.n(eVar.f2271I);
            n3 = C0232c.n(eVar.f2273K);
            c0232c.t();
        }
        return n3 - n2;
    }

    public final void c(int i2, n nVar) {
        Iterator it = this.f2390a.iterator();
        while (it.hasNext()) {
            C0237d c0237d = (C0237d) it.next();
            ArrayList arrayList = nVar.f2390a;
            if (!arrayList.contains(c0237d)) {
                arrayList.add(c0237d);
            }
            int i3 = nVar.f2391b;
            if (i2 == 0) {
                c0237d.f2311m0 = i3;
            } else {
                c0237d.f2313n0 = i3;
            }
        }
        this.f2394e = nVar.f2391b;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        int i2 = this.f2392c;
        if (i2 == 0) {
            str = "Horizontal";
        } else if (i2 == 1) {
            str = "Vertical";
        } else if (i2 == 2) {
            str = "Both";
        } else {
            str = "Unknown";
        }
        sb.append(str);
        sb.append(" [");
        sb.append(this.f2391b);
        sb.append("] <");
        String sb2 = sb.toString();
        Iterator it = this.f2390a.iterator();
        while (it.hasNext()) {
            sb2 = sb2 + " " + ((C0237d) it.next()).g0;
        }
        return A.e.c(sb2, " >");
    }
}

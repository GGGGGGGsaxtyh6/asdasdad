package z0;

import M.v;
import j0.AbstractC0150d;
import java.util.ArrayList;
import u0.m;
import u0.n;
import u0.u;
import y0.h;

/* loaded from: classes.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public final h f2971a;

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f2972b;

    /* renamed from: c, reason: collision with root package name */
    public final int f2973c;

    /* renamed from: d, reason: collision with root package name */
    public final v f2974d;

    /* renamed from: e, reason: collision with root package name */
    public final B0.h f2975e;

    /* renamed from: f, reason: collision with root package name */
    public final int f2976f;

    /* renamed from: g, reason: collision with root package name */
    public final int f2977g;
    public final int h;

    /* renamed from: i, reason: collision with root package name */
    public int f2978i;

    public f(h hVar, ArrayList arrayList, int i2, v vVar, B0.h hVar2, int i3, int i4, int i5) {
        AbstractC0150d.e(hVar, "call");
        this.f2971a = hVar;
        this.f2972b = arrayList;
        this.f2973c = i2;
        this.f2974d = vVar;
        this.f2975e = hVar2;
        this.f2976f = i3;
        this.f2977g = i4;
        this.h = i5;
    }

    public static f a(f fVar, int i2, v vVar, B0.h hVar, int i3) {
        if ((i3 & 1) != 0) {
            i2 = fVar.f2973c;
        }
        int i4 = i2;
        if ((i3 & 2) != 0) {
            vVar = fVar.f2974d;
        }
        v vVar2 = vVar;
        if ((i3 & 4) != 0) {
            hVar = fVar.f2975e;
        }
        B0.h hVar2 = hVar;
        AbstractC0150d.e(hVar2, "request");
        return new f(fVar.f2971a, fVar.f2972b, i4, vVar2, hVar2, fVar.f2976f, fVar.f2977g, fVar.h);
    }

    public final u b(B0.h hVar) {
        AbstractC0150d.e(hVar, "request");
        ArrayList arrayList = this.f2972b;
        int size = arrayList.size();
        int i2 = this.f2973c;
        if (i2 < size) {
            this.f2978i++;
            v vVar = this.f2974d;
            if (vVar != null) {
                if (((y0.e) vVar.f571b).b((m) hVar.f118b)) {
                    if (this.f2978i != 1) {
                        throw new IllegalStateException(("network interceptor " + arrayList.get(i2 - 1) + " must call proceed() exactly once").toString());
                    }
                } else {
                    throw new IllegalStateException(("network interceptor " + arrayList.get(i2 - 1) + " must retain the same host and port").toString());
                }
            }
            int i3 = i2 + 1;
            f a2 = a(this, i3, null, hVar, 58);
            n nVar = (n) arrayList.get(i2);
            u a3 = nVar.a(a2);
            if (a3 != null) {
                if (vVar != null && i3 < arrayList.size() && a2.f2978i != 1) {
                    throw new IllegalStateException(("network interceptor " + nVar + " must call proceed() exactly once").toString());
                }
                if (a3.f2572g != null) {
                    return a3;
                }
                throw new IllegalStateException(("interceptor " + nVar + " returned a response with no body").toString());
            }
            throw new NullPointerException("interceptor " + nVar + " returned null");
        }
        throw new IllegalStateException("Check failed.");
    }
}

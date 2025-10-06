package t;

import java.util.ArrayList;

/* renamed from: t.l, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0249l {

    /* renamed from: a, reason: collision with root package name */
    public o f2385a;

    /* renamed from: b, reason: collision with root package name */
    public ArrayList f2386b;

    public static long a(C0243f c0243f, long j2) {
        o oVar = c0243f.f2374d;
        if (oVar instanceof C0247j) {
            return j2;
        }
        ArrayList arrayList = c0243f.f2380k;
        int size = arrayList.size();
        long j3 = j2;
        for (int i2 = 0; i2 < size; i2++) {
            InterfaceC0241d interfaceC0241d = (InterfaceC0241d) arrayList.get(i2);
            if (interfaceC0241d instanceof C0243f) {
                C0243f c0243f2 = (C0243f) interfaceC0241d;
                if (c0243f2.f2374d != oVar) {
                    j3 = Math.min(j3, a(c0243f2, c0243f2.f2376f + j2));
                }
            }
        }
        if (c0243f == oVar.f2402i) {
            long j4 = oVar.j();
            long j5 = j2 - j4;
            return Math.min(Math.min(j3, a(oVar.h, j5)), j5 - r9.f2376f);
        }
        return j3;
    }

    public static long b(C0243f c0243f, long j2) {
        o oVar = c0243f.f2374d;
        if (oVar instanceof C0247j) {
            return j2;
        }
        ArrayList arrayList = c0243f.f2380k;
        int size = arrayList.size();
        long j3 = j2;
        for (int i2 = 0; i2 < size; i2++) {
            InterfaceC0241d interfaceC0241d = (InterfaceC0241d) arrayList.get(i2);
            if (interfaceC0241d instanceof C0243f) {
                C0243f c0243f2 = (C0243f) interfaceC0241d;
                if (c0243f2.f2374d != oVar) {
                    j3 = Math.max(j3, b(c0243f2, c0243f2.f2376f + j2));
                }
            }
        }
        if (c0243f == oVar.h) {
            long j4 = oVar.j();
            long j5 = j2 + j4;
            return Math.max(Math.max(j3, b(oVar.f2402i, j5)), j5 - r9.f2376f);
        }
        return j3;
    }
}

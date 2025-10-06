package y0;

import B0.C0000a;
import B0.EnumC0001b;
import B0.F;
import B0.G;
import j0.AbstractC0150d;
import java.io.IOException;
import u0.x;

/* loaded from: classes.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    public final l f2873a;

    /* renamed from: b, reason: collision with root package name */
    public final u0.a f2874b;

    /* renamed from: c, reason: collision with root package name */
    public final h f2875c;

    /* renamed from: d, reason: collision with root package name */
    public F f2876d;

    /* renamed from: e, reason: collision with root package name */
    public A0.h f2877e;

    /* renamed from: f, reason: collision with root package name */
    public int f2878f;

    /* renamed from: g, reason: collision with root package name */
    public int f2879g;
    public int h;

    /* renamed from: i, reason: collision with root package name */
    public x f2880i;

    public e(l lVar, u0.a aVar, h hVar) {
        AbstractC0150d.e(lVar, "connectionPool");
        AbstractC0150d.e(hVar, "call");
        this.f2873a = lVar;
        this.f2874b = aVar;
        this.f2875c = hVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0343 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x02e9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v10, types: [java.util.List, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final y0.k a(int r15, int r16, int r17, boolean r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 921
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: y0.e.a(int, int, int, boolean, boolean):y0.k");
    }

    public final boolean b(u0.m mVar) {
        AbstractC0150d.e(mVar, "url");
        u0.m mVar2 = this.f2874b.h;
        if (mVar.f2487e == mVar2.f2487e && AbstractC0150d.a(mVar.f2486d, mVar2.f2486d)) {
            return true;
        }
        return false;
    }

    public final void c(IOException iOException) {
        AbstractC0150d.e(iOException, "e");
        this.f2880i = null;
        if (iOException instanceof G) {
            if (((G) iOException).f81a == EnumC0001b.REFUSED_STREAM) {
                this.f2878f++;
                return;
            }
        }
        if (iOException instanceof C0000a) {
            this.f2879g++;
        } else {
            this.h++;
        }
    }
}

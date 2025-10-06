package B0;

import j0.AbstractC0150d;
import java.io.IOException;

/* loaded from: classes.dex */
public final class y implements H0.t {

    /* renamed from: a, reason: collision with root package name */
    public final long f192a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f193b;

    /* renamed from: c, reason: collision with root package name */
    public final H0.e f194c;

    /* renamed from: d, reason: collision with root package name */
    public final H0.e f195d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f196e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ A f197f;

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, H0.e] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object, H0.e] */
    public y(A a2, long j2, boolean z2) {
        AbstractC0150d.e(a2, "this$0");
        this.f197f = a2;
        this.f192a = j2;
        this.f193b = z2;
        this.f194c = new Object();
        this.f195d = new Object();
    }

    @Override // H0.t
    public final H0.v a() {
        return this.f197f.f62k;
    }

    @Override // H0.t
    public final long b(long j2, H0.e eVar) {
        Throwable th;
        boolean z2;
        long j3;
        do {
            A a2 = this.f197f;
            synchronized (a2) {
                a2.f62k.h();
                try {
                    if (a2.f() != null) {
                        th = a2.f65n;
                        if (th == null) {
                            EnumC0001b f2 = a2.f();
                            AbstractC0150d.b(f2);
                            th = new G(f2);
                        }
                    } else {
                        th = null;
                    }
                    if (!this.f196e) {
                        H0.e eVar2 = this.f195d;
                        long j4 = eVar2.f412b;
                        z2 = false;
                        if (j4 > 0) {
                            j3 = eVar2.b(Math.min(8192L, j4), eVar);
                            long j5 = a2.f55c + j3;
                            a2.f55c = j5;
                            long j6 = j5 - a2.f56d;
                            if (th == null && j6 >= a2.f54b.f162p.b() / 2) {
                                a2.f54b.o(j6, a2.f53a);
                                a2.f56d = a2.f55c;
                            }
                        } else {
                            if (!this.f193b && th == null) {
                                a2.l();
                                z2 = true;
                            }
                            j3 = -1;
                        }
                        a2.f62k.k();
                    } else {
                        throw new IOException("stream closed");
                    }
                } catch (Throwable th2) {
                    a2.f62k.k();
                    throw th2;
                }
            }
        } while (z2);
        if (j3 != -1) {
            f(j3);
            return j3;
        }
        if (th == null) {
            return -1L;
        }
        throw th;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        long j2;
        A a2 = this.f197f;
        synchronized (a2) {
            this.f196e = true;
            H0.e eVar = this.f195d;
            j2 = eVar.f412b;
            eVar.o(j2);
            a2.notifyAll();
        }
        if (j2 > 0) {
            f(j2);
        }
        this.f197f.a();
    }

    public final void f(long j2) {
        byte[] bArr = v0.b.f2808a;
        this.f197f.f54b.l(j2);
    }
}

package B0;

import j0.AbstractC0150d;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayDeque;

/* loaded from: classes.dex */
public final class A {

    /* renamed from: a, reason: collision with root package name */
    public final int f53a;

    /* renamed from: b, reason: collision with root package name */
    public final s f54b;

    /* renamed from: c, reason: collision with root package name */
    public long f55c;

    /* renamed from: d, reason: collision with root package name */
    public long f56d;

    /* renamed from: e, reason: collision with root package name */
    public long f57e;

    /* renamed from: f, reason: collision with root package name */
    public long f58f;

    /* renamed from: g, reason: collision with root package name */
    public final ArrayDeque f59g;
    public boolean h;

    /* renamed from: i, reason: collision with root package name */
    public final y f60i;

    /* renamed from: j, reason: collision with root package name */
    public final x f61j;

    /* renamed from: k, reason: collision with root package name */
    public final z f62k;

    /* renamed from: l, reason: collision with root package name */
    public final z f63l;

    /* renamed from: m, reason: collision with root package name */
    public EnumC0001b f64m;

    /* renamed from: n, reason: collision with root package name */
    public IOException f65n;

    public A(int i2, s sVar, boolean z2, boolean z3, u0.k kVar) {
        AbstractC0150d.e(sVar, "connection");
        this.f53a = i2;
        this.f54b = sVar;
        this.f58f = sVar.f163q.b();
        ArrayDeque arrayDeque = new ArrayDeque();
        this.f59g = arrayDeque;
        this.f60i = new y(this, sVar.f162p.b(), z3);
        this.f61j = new x(this, z2);
        this.f62k = new z(this);
        this.f63l = new z(this);
        if (kVar != null) {
            if (!h()) {
                arrayDeque.add(kVar);
                return;
            }
            throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
        }
        if (h()) {
        } else {
            throw new IllegalStateException("remotely-initiated streams should have headers");
        }
    }

    public final void a() {
        boolean z2;
        boolean i2;
        byte[] bArr = v0.b.f2808a;
        synchronized (this) {
            y yVar = this.f60i;
            if (!yVar.f193b && yVar.f196e) {
                x xVar = this.f61j;
                if (xVar.f188a || xVar.f190c) {
                    z2 = true;
                    i2 = i();
                }
            }
            z2 = false;
            i2 = i();
        }
        if (z2) {
            c(EnumC0001b.CANCEL, null);
        } else if (!i2) {
            this.f54b.j(this.f53a);
        }
    }

    public final void b() {
        x xVar = this.f61j;
        if (!xVar.f190c) {
            if (!xVar.f188a) {
                if (this.f64m != null) {
                    IOException iOException = this.f65n;
                    if (iOException == null) {
                        EnumC0001b enumC0001b = this.f64m;
                        AbstractC0150d.b(enumC0001b);
                        throw new G(enumC0001b);
                    }
                    throw iOException;
                }
                return;
            }
            throw new IOException("stream finished");
        }
        throw new IOException("stream closed");
    }

    public final void c(EnumC0001b enumC0001b, IOException iOException) {
        if (!d(enumC0001b, iOException)) {
            return;
        }
        this.f54b.f169w.m(this.f53a, enumC0001b);
    }

    public final boolean d(EnumC0001b enumC0001b, IOException iOException) {
        byte[] bArr = v0.b.f2808a;
        synchronized (this) {
            if (f() != null) {
                return false;
            }
            if (this.f60i.f193b && this.f61j.f188a) {
                return false;
            }
            this.f64m = enumC0001b;
            this.f65n = iOException;
            notifyAll();
            this.f54b.j(this.f53a);
            return true;
        }
    }

    public final void e(EnumC0001b enumC0001b) {
        if (!d(enumC0001b, null)) {
            return;
        }
        this.f54b.n(this.f53a, enumC0001b);
    }

    public final synchronized EnumC0001b f() {
        return this.f64m;
    }

    public final x g() {
        synchronized (this) {
            if (!this.h && !h()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.f61j;
    }

    public final boolean h() {
        boolean z2;
        if ((this.f53a & 1) == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f54b.getClass();
        if (true == z2) {
            return true;
        }
        return false;
    }

    public final synchronized boolean i() {
        if (this.f64m != null) {
            return false;
        }
        y yVar = this.f60i;
        if (yVar.f193b || yVar.f196e) {
            x xVar = this.f61j;
            if (xVar.f188a || xVar.f190c) {
                if (this.h) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021 A[Catch: all -> 0x0016, TryCatch #0 {all -> 0x0016, blocks: (B:4:0x0008, B:8:0x0010, B:10:0x0021, B:11:0x0025, B:19:0x0018), top: B:3:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j(u0.k r3, boolean r4) {
        /*
            r2 = this;
            java.lang.String r0 = "headers"
            j0.AbstractC0150d.e(r3, r0)
            byte[] r0 = v0.b.f2808a
            monitor-enter(r2)
            boolean r0 = r2.h     // Catch: java.lang.Throwable -> L16
            r1 = 1
            if (r0 == 0) goto L18
            if (r4 != 0) goto L10
            goto L18
        L10:
            B0.y r3 = r2.f60i     // Catch: java.lang.Throwable -> L16
            r3.getClass()     // Catch: java.lang.Throwable -> L16
            goto L1f
        L16:
            r3 = move-exception
            goto L37
        L18:
            r2.h = r1     // Catch: java.lang.Throwable -> L16
            java.util.ArrayDeque r0 = r2.f59g     // Catch: java.lang.Throwable -> L16
            r0.add(r3)     // Catch: java.lang.Throwable -> L16
        L1f:
            if (r4 == 0) goto L25
            B0.y r3 = r2.f60i     // Catch: java.lang.Throwable -> L16
            r3.f193b = r1     // Catch: java.lang.Throwable -> L16
        L25:
            boolean r3 = r2.i()     // Catch: java.lang.Throwable -> L16
            r2.notifyAll()     // Catch: java.lang.Throwable -> L16
            monitor-exit(r2)
            if (r3 != 0) goto L36
            B0.s r3 = r2.f54b
            int r4 = r2.f53a
            r3.j(r4)
        L36:
            return
        L37:
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: B0.A.j(u0.k, boolean):void");
    }

    public final synchronized void k(EnumC0001b enumC0001b) {
        if (this.f64m == null) {
            this.f64m = enumC0001b;
            notifyAll();
        }
    }

    public final void l() {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }
}

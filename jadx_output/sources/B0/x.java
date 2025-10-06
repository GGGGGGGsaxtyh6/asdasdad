package B0;

import j0.AbstractC0150d;

/* loaded from: classes.dex */
public final class x implements H0.s {

    /* renamed from: a, reason: collision with root package name */
    public final boolean f188a;

    /* renamed from: b, reason: collision with root package name */
    public final H0.e f189b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f190c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ A f191d;

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, H0.e] */
    public x(A a2, boolean z2) {
        AbstractC0150d.e(a2, "this$0");
        this.f191d = a2;
        this.f188a = z2;
        this.f189b = new Object();
    }

    @Override // H0.s
    public final H0.v a() {
        return this.f191d.f63l;
    }

    @Override // H0.s
    public final void c(long j2, H0.e eVar) {
        byte[] bArr = v0.b.f2808a;
        H0.e eVar2 = this.f189b;
        eVar2.c(j2, eVar);
        while (eVar2.f412b >= 16384) {
            f(false);
        }
    }

    @Override // H0.s, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        boolean z2;
        A a2 = this.f191d;
        byte[] bArr = v0.b.f2808a;
        synchronized (a2) {
            if (this.f190c) {
                return;
            }
            if (a2.f() == null) {
                z2 = true;
            } else {
                z2 = false;
            }
            A a3 = this.f191d;
            if (!a3.f61j.f188a) {
                if (this.f189b.f412b > 0) {
                    while (this.f189b.f412b > 0) {
                        f(true);
                    }
                } else if (z2) {
                    a3.f54b.m(a3.f53a, true, null, 0L);
                }
            }
            synchronized (this.f191d) {
                this.f190c = true;
            }
            this.f191d.f54b.flush();
            this.f191d.a();
        }
    }

    public final void f(boolean z2) {
        long min;
        boolean z3;
        boolean z4;
        A a2 = this.f191d;
        synchronized (a2) {
            a2.f63l.h();
            while (a2.f57e >= a2.f58f && !this.f188a && !this.f190c && a2.f() == null) {
                try {
                    a2.l();
                } finally {
                    a2.f63l.k();
                }
            }
            a2.f63l.k();
            a2.b();
            min = Math.min(a2.f58f - a2.f57e, this.f189b.f412b);
            a2.f57e += min;
            if (z2 && min == this.f189b.f412b) {
                z3 = true;
            } else {
                z3 = false;
            }
            z4 = z3;
        }
        this.f191d.f63l.h();
        try {
            A a3 = this.f191d;
            a3.f54b.m(a3.f53a, z4, this.f189b, min);
        } finally {
            a2 = this.f191d;
        }
    }

    @Override // H0.s, java.io.Flushable
    public final void flush() {
        A a2 = this.f191d;
        byte[] bArr = v0.b.f2808a;
        synchronized (a2) {
            a2.b();
        }
        while (this.f189b.f412b > 0) {
            f(false);
            this.f191d.f54b.flush();
        }
    }
}

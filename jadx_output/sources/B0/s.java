package B0;

import j0.AbstractC0150d;
import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
public final class s implements Closeable {

    /* renamed from: z, reason: collision with root package name */
    public static final F f147z;

    /* renamed from: a, reason: collision with root package name */
    public final j f148a;

    /* renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f149b = new LinkedHashMap();

    /* renamed from: c, reason: collision with root package name */
    public final String f150c;

    /* renamed from: d, reason: collision with root package name */
    public int f151d;

    /* renamed from: e, reason: collision with root package name */
    public int f152e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f153f;

    /* renamed from: g, reason: collision with root package name */
    public final x0.d f154g;
    public final x0.b h;

    /* renamed from: i, reason: collision with root package name */
    public final x0.b f155i;

    /* renamed from: j, reason: collision with root package name */
    public final x0.b f156j;

    /* renamed from: k, reason: collision with root package name */
    public final E f157k;

    /* renamed from: l, reason: collision with root package name */
    public long f158l;

    /* renamed from: m, reason: collision with root package name */
    public long f159m;

    /* renamed from: n, reason: collision with root package name */
    public long f160n;

    /* renamed from: o, reason: collision with root package name */
    public long f161o;

    /* renamed from: p, reason: collision with root package name */
    public final F f162p;

    /* renamed from: q, reason: collision with root package name */
    public F f163q;

    /* renamed from: r, reason: collision with root package name */
    public long f164r;

    /* renamed from: s, reason: collision with root package name */
    public long f165s;

    /* renamed from: t, reason: collision with root package name */
    public long f166t;

    /* renamed from: u, reason: collision with root package name */
    public long f167u;

    /* renamed from: v, reason: collision with root package name */
    public final Socket f168v;

    /* renamed from: w, reason: collision with root package name */
    public final B f169w;

    /* renamed from: x, reason: collision with root package name */
    public final m f170x;
    public final LinkedHashSet y;

    static {
        F f2 = new F(0);
        f2.f(7, 65535);
        f2.f(5, 16384);
        f147z = f2;
    }

    public s(h hVar) {
        this.f148a = (j) hVar.f123g;
        String str = (String) hVar.f120d;
        if (str != null) {
            this.f150c = str;
            this.f152e = 3;
            x0.d dVar = (x0.d) hVar.f118b;
            this.f154g = dVar;
            this.h = dVar.e();
            this.f155i = dVar.e();
            this.f156j = dVar.e();
            this.f157k = E.f78a;
            F f2 = new F(0);
            f2.f(7, 16777216);
            this.f162p = f2;
            this.f163q = f147z;
            this.f167u = r0.b();
            Socket socket = (Socket) hVar.f119c;
            if (socket != null) {
                this.f168v = socket;
                H0.n nVar = (H0.n) hVar.f122f;
                if (nVar != null) {
                    this.f169w = new B(nVar);
                    H0.o oVar = (H0.o) hVar.f121e;
                    if (oVar != null) {
                        this.f170x = new m(this, new w(oVar));
                        this.y = new LinkedHashSet();
                        return;
                    } else {
                        AbstractC0150d.i("source");
                        throw null;
                    }
                }
                AbstractC0150d.i("sink");
                throw null;
            }
            AbstractC0150d.i("socket");
            throw null;
        }
        AbstractC0150d.i("connectionName");
        throw null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        f(EnumC0001b.NO_ERROR, EnumC0001b.CANCEL, null);
    }

    public final void f(EnumC0001b enumC0001b, EnumC0001b enumC0001b2, IOException iOException) {
        int i2;
        Object[] objArr;
        byte[] bArr = v0.b.f2808a;
        try {
            k(enumC0001b);
        } catch (IOException unused) {
        }
        synchronized (this) {
            if (!this.f149b.isEmpty()) {
                objArr = this.f149b.values().toArray(new A[0]);
                if (objArr != null) {
                    this.f149b.clear();
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                }
            } else {
                objArr = null;
            }
        }
        A[] aArr = (A[]) objArr;
        if (aArr != null) {
            for (A a2 : aArr) {
                try {
                    a2.c(enumC0001b2, iOException);
                } catch (IOException unused2) {
                }
            }
        }
        try {
            this.f169w.close();
        } catch (IOException unused3) {
        }
        try {
            this.f168v.close();
        } catch (IOException unused4) {
        }
        this.h.e();
        this.f155i.e();
        this.f156j.e();
    }

    public final void flush() {
        this.f169w.flush();
    }

    public final void h(IOException iOException) {
        EnumC0001b enumC0001b = EnumC0001b.PROTOCOL_ERROR;
        f(enumC0001b, enumC0001b, iOException);
    }

    public final synchronized A i(int i2) {
        return (A) this.f149b.get(Integer.valueOf(i2));
    }

    public final synchronized A j(int i2) {
        A a2;
        a2 = (A) this.f149b.remove(Integer.valueOf(i2));
        notifyAll();
        return a2;
    }

    public final void k(EnumC0001b enumC0001b) {
        synchronized (this.f169w) {
            synchronized (this) {
                if (this.f153f) {
                    return;
                }
                this.f153f = true;
                this.f169w.j(this.f151d, enumC0001b, v0.b.f2808a);
            }
        }
    }

    public final synchronized void l(long j2) {
        long j3 = this.f164r + j2;
        this.f164r = j3;
        long j4 = j3 - this.f165s;
        if (j4 >= this.f162p.b() / 2) {
            o(j4, 0);
            this.f165s += j4;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0033, code lost:
    
        throw new java.io.IOException("stream closed");
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0035, code lost:
    
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r6 - r4), r8.f169w.f69c);
        r6 = r2;
        r8.f166t += r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m(int r9, boolean r10, H0.e r11, long r12) {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto Ld
            B0.B r12 = r8.f169w
            r12.h(r10, r9, r11, r3)
            return
        Ld:
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 <= 0) goto L68
            monitor-enter(r8)
        L12:
            long r4 = r8.f166t     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            long r6 = r8.f167u     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 < 0) goto L34
            java.util.LinkedHashMap r2 = r8.f149b     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            boolean r2 = r2.containsKey(r4)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            if (r2 == 0) goto L2c
            r8.wait()     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            goto L12
        L2a:
            r9 = move-exception
            goto L66
        L2c:
            java.io.IOException r9 = new java.io.IOException     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            throw r9     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
        L34:
            long r6 = r6 - r4
            long r4 = java.lang.Math.min(r12, r6)     // Catch: java.lang.Throwable -> L2a
            int r2 = (int) r4     // Catch: java.lang.Throwable -> L2a
            B0.B r4 = r8.f169w     // Catch: java.lang.Throwable -> L2a
            int r4 = r4.f69c     // Catch: java.lang.Throwable -> L2a
            int r2 = java.lang.Math.min(r2, r4)     // Catch: java.lang.Throwable -> L2a
            long r4 = r8.f166t     // Catch: java.lang.Throwable -> L2a
            long r6 = (long) r2     // Catch: java.lang.Throwable -> L2a
            long r4 = r4 + r6
            r8.f166t = r4     // Catch: java.lang.Throwable -> L2a
            monitor-exit(r8)
            long r12 = r12 - r6
            B0.B r4 = r8.f169w
            if (r10 == 0) goto L54
            int r5 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r5 != 0) goto L54
            r5 = 1
            goto L55
        L54:
            r5 = r3
        L55:
            r4.h(r5, r9, r11, r2)
            goto Ld
        L59:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L2a
            r9.interrupt()     // Catch: java.lang.Throwable -> L2a
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L2a
            r9.<init>()     // Catch: java.lang.Throwable -> L2a
            throw r9     // Catch: java.lang.Throwable -> L2a
        L66:
            monitor-exit(r8)
            throw r9
        L68:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: B0.s.m(int, boolean, H0.e, long):void");
    }

    public final void n(int i2, EnumC0001b enumC0001b) {
        this.h.c(new q(this.f150c + '[' + i2 + "] writeSynReset", this, i2, enumC0001b), 0L);
    }

    public final void o(long j2, int i2) {
        this.h.c(new r(this.f150c + '[' + i2 + "] windowUpdate", this, i2, j2), 0L);
    }
}

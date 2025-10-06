package H0;

import j0.AbstractC0150d;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class n implements f {

    /* renamed from: a, reason: collision with root package name */
    public final s f430a;

    /* renamed from: b, reason: collision with root package name */
    public final e f431b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f432c;

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, H0.e] */
    public n(s sVar) {
        AbstractC0150d.e(sVar, "sink");
        this.f430a = sVar;
        this.f431b = new Object();
    }

    @Override // H0.s
    public final v a() {
        return this.f430a.a();
    }

    @Override // H0.s
    public final void c(long j2, e eVar) {
        AbstractC0150d.e(eVar, "source");
        if (!this.f432c) {
            this.f431b.c(j2, eVar);
            f();
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // H0.s, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        s sVar = this.f430a;
        if (!this.f432c) {
            try {
                e eVar = this.f431b;
                long j2 = eVar.f412b;
                if (j2 > 0) {
                    sVar.c(j2, eVar);
                }
                th = null;
            } catch (Throwable th) {
                th = th;
            }
            try {
                sVar.close();
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                }
            }
            this.f432c = true;
            if (th == null) {
            } else {
                throw th;
            }
        }
    }

    @Override // H0.f
    public final f d(String str) {
        AbstractC0150d.e(str, "string");
        if (!this.f432c) {
            this.f431b.x(str);
            f();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    public final f f() {
        if (!this.f432c) {
            e eVar = this.f431b;
            long j2 = eVar.f412b;
            if (j2 == 0) {
                j2 = 0;
            } else {
                p pVar = eVar.f411a;
                AbstractC0150d.b(pVar);
                p pVar2 = pVar.f442g;
                AbstractC0150d.b(pVar2);
                if (pVar2.f438c < 8192 && pVar2.f440e) {
                    j2 -= r6 - pVar2.f437b;
                }
            }
            if (j2 > 0) {
                this.f430a.c(j2, eVar);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // H0.s, java.io.Flushable
    public final void flush() {
        if (!this.f432c) {
            e eVar = this.f431b;
            long j2 = eVar.f412b;
            s sVar = this.f430a;
            if (j2 > 0) {
                sVar.c(j2, eVar);
            }
            sVar.flush();
            return;
        }
        throw new IllegalStateException("closed");
    }

    public final f h(int i2) {
        if (!this.f432c) {
            this.f431b.u(i2);
            f();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    public final f i(int i2) {
        if (!this.f432c) {
            this.f431b.w(i2);
            f();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.f432c;
    }

    public final String toString() {
        return "buffer(" + this.f430a + ')';
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        AbstractC0150d.e(byteBuffer, "source");
        if (!this.f432c) {
            int write = this.f431b.write(byteBuffer);
            f();
            return write;
        }
        throw new IllegalStateException("closed");
    }
}

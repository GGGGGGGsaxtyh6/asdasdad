package H0;

import B0.z;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public final class c implements t {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f403a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f404b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f405c;

    public /* synthetic */ c(Object obj, Object obj2, int i2) {
        this.f403a = i2;
        this.f404b = obj;
        this.f405c = obj2;
    }

    @Override // H0.t
    public final v a() {
        switch (this.f403a) {
            case 0:
                return (z) this.f404b;
            default:
                return (v) this.f405c;
        }
    }

    @Override // H0.t
    public final long b(long j2, e eVar) {
        switch (this.f403a) {
            case 0:
                c cVar = (c) this.f405c;
                z zVar = (z) this.f404b;
                zVar.h();
                try {
                    long b2 = cVar.b(8192L, eVar);
                    if (!zVar.i()) {
                        return b2;
                    }
                    throw zVar.l(null);
                } catch (IOException e2) {
                    if (!zVar.i()) {
                        throw e2;
                    }
                    throw zVar.l(e2);
                } finally {
                    zVar.i();
                }
            default:
                try {
                    ((v) this.f405c).f();
                    p q2 = eVar.q(1);
                    int read = ((InputStream) this.f404b).read(q2.f436a, q2.f438c, (int) Math.min(8192L, 8192 - q2.f438c));
                    if (read == -1) {
                        if (q2.f437b == q2.f438c) {
                            eVar.f411a = q2.a();
                            q.a(q2);
                        }
                        return -1L;
                    }
                    q2.f438c += read;
                    long j3 = read;
                    eVar.f412b += j3;
                    return j3;
                } catch (AssertionError e3) {
                    if (C0.m.l(e3)) {
                        throw new IOException(e3);
                    }
                    throw e3;
                }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        switch (this.f403a) {
            case 0:
                c cVar = (c) this.f405c;
                z zVar = (z) this.f404b;
                zVar.h();
                try {
                    cVar.close();
                    if (!zVar.i()) {
                        return;
                    } else {
                        throw zVar.l(null);
                    }
                } catch (IOException e2) {
                    if (!zVar.i()) {
                        throw e2;
                    }
                    throw zVar.l(e2);
                } finally {
                    zVar.i();
                }
            default:
                ((InputStream) this.f404b).close();
                return;
        }
    }

    public final String toString() {
        switch (this.f403a) {
            case 0:
                return "AsyncTimeout.source(" + ((c) this.f405c) + ')';
            default:
                return "source(" + ((InputStream) this.f404b) + ')';
        }
    }
}

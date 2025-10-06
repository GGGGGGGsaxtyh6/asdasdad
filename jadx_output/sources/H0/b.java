package H0;

import B0.z;
import j0.AbstractC0150d;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public final class b implements s {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f400a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final z f401b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f402c;

    public b(OutputStream outputStream, z zVar) {
        this.f402c = outputStream;
        this.f401b = zVar;
    }

    @Override // H0.s
    public final v a() {
        switch (this.f400a) {
            case 0:
                return this.f401b;
            default:
                return this.f401b;
        }
    }

    @Override // H0.s
    public final void c(long j2, e eVar) {
        switch (this.f400a) {
            case 0:
                C0.f.e(eVar.f412b, 0L, j2);
                while (true) {
                    long j3 = 0;
                    if (j2 > 0) {
                        p pVar = eVar.f411a;
                        AbstractC0150d.b(pVar);
                        while (true) {
                            if (j3 < 65536) {
                                j3 += pVar.f438c - pVar.f437b;
                                if (j3 >= j2) {
                                    j3 = j2;
                                } else {
                                    pVar = pVar.f441f;
                                    AbstractC0150d.b(pVar);
                                }
                            }
                        }
                        b bVar = (b) this.f402c;
                        z zVar = this.f401b;
                        zVar.h();
                        try {
                            bVar.c(j3, eVar);
                            if (!zVar.i()) {
                                j2 -= j3;
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
                    } else {
                        return;
                    }
                }
            default:
                C0.f.e(eVar.f412b, 0L, j2);
                while (j2 > 0) {
                    this.f401b.f();
                    p pVar2 = eVar.f411a;
                    AbstractC0150d.b(pVar2);
                    int min = (int) Math.min(j2, pVar2.f438c - pVar2.f437b);
                    ((OutputStream) this.f402c).write(pVar2.f436a, pVar2.f437b, min);
                    int i2 = pVar2.f437b + min;
                    pVar2.f437b = i2;
                    long j4 = min;
                    j2 -= j4;
                    eVar.f412b -= j4;
                    if (i2 == pVar2.f438c) {
                        eVar.f411a = pVar2.a();
                        q.a(pVar2);
                    }
                }
                return;
        }
    }

    @Override // H0.s, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        switch (this.f400a) {
            case 0:
                b bVar = (b) this.f402c;
                z zVar = this.f401b;
                zVar.h();
                try {
                    bVar.close();
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
                ((OutputStream) this.f402c).close();
                return;
        }
    }

    @Override // H0.s, java.io.Flushable
    public final void flush() {
        switch (this.f400a) {
            case 0:
                b bVar = (b) this.f402c;
                z zVar = this.f401b;
                zVar.h();
                try {
                    bVar.flush();
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
                ((OutputStream) this.f402c).flush();
                return;
        }
    }

    public final String toString() {
        switch (this.f400a) {
            case 0:
                return "AsyncTimeout.sink(" + ((b) this.f402c) + ')';
            default:
                return "sink(" + ((OutputStream) this.f402c) + ')';
        }
    }

    public b(z zVar, b bVar) {
        this.f401b = zVar;
        this.f402c = bVar;
    }
}

package B0;

import j0.AbstractC0150d;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public final class v implements H0.t {

    /* renamed from: a, reason: collision with root package name */
    public final H0.o f178a;

    /* renamed from: b, reason: collision with root package name */
    public int f179b;

    /* renamed from: c, reason: collision with root package name */
    public int f180c;

    /* renamed from: d, reason: collision with root package name */
    public int f181d;

    /* renamed from: e, reason: collision with root package name */
    public int f182e;

    /* renamed from: f, reason: collision with root package name */
    public int f183f;

    public v(H0.o oVar) {
        AbstractC0150d.e(oVar, "source");
        this.f178a = oVar;
    }

    @Override // H0.t
    public final H0.v a() {
        return this.f178a.f433a.a();
    }

    @Override // H0.t
    public final long b(long j2, H0.e eVar) {
        int i2;
        int l2;
        do {
            int i3 = this.f182e;
            H0.o oVar = this.f178a;
            if (i3 == 0) {
                oVar.r(this.f183f);
                this.f183f = 0;
                if ((this.f180c & 4) != 0) {
                    return -1L;
                }
                i2 = this.f181d;
                int s2 = v0.b.s(oVar);
                this.f182e = s2;
                this.f179b = s2;
                int i4 = oVar.i() & 255;
                this.f180c = oVar.i() & 255;
                Logger logger = w.f184d;
                if (logger.isLoggable(Level.FINE)) {
                    H0.h hVar = AbstractC0006g.f113a;
                    logger.fine(AbstractC0006g.a(true, this.f181d, this.f179b, i4, this.f180c));
                }
                l2 = oVar.l() & Integer.MAX_VALUE;
                this.f181d = l2;
                if (i4 != 9) {
                    throw new IOException(i4 + " != TYPE_CONTINUATION");
                }
            } else {
                long b2 = oVar.b(Math.min(8192L, i3), eVar);
                if (b2 == -1) {
                    return -1L;
                }
                this.f182e -= (int) b2;
                return b2;
            }
        } while (l2 == i2);
        throw new IOException("TYPE_CONTINUATION streamId changed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}

package A0;

import H0.o;
import j0.AbstractC0150d;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import q0.k;
import u0.m;
import u0.q;

/* loaded from: classes.dex */
public final class d extends b {

    /* renamed from: d, reason: collision with root package name */
    public final m f36d;

    /* renamed from: e, reason: collision with root package name */
    public long f37e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f38f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ h f39g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(h hVar, m mVar) {
        super(hVar);
        AbstractC0150d.e(hVar, "this$0");
        AbstractC0150d.e(mVar, "url");
        this.f39g = hVar;
        this.f36d = mVar;
        this.f37e = -1L;
        this.f38f = true;
    }

    @Override // A0.b, H0.t
    public final long b(long j2, H0.e eVar) {
        if (!this.f31b) {
            if (!this.f38f) {
                return -1L;
            }
            long j3 = this.f37e;
            h hVar = this.f39g;
            if (j3 == 0 || j3 == -1) {
                if (j3 != -1) {
                    ((o) hVar.f49d).o(Long.MAX_VALUE);
                }
                try {
                    this.f37e = ((o) hVar.f49d).k();
                    String obj = q0.d.P(((o) hVar.f49d).o(Long.MAX_VALUE)).toString();
                    if (this.f37e >= 0 && (obj.length() <= 0 || k.B(obj, false, ";"))) {
                        if (this.f37e == 0) {
                            this.f38f = false;
                            hVar.f52g = ((a) hVar.f51f).a();
                            q qVar = (q) hVar.f47b;
                            AbstractC0150d.b(qVar);
                            u0.k kVar = (u0.k) hVar.f52g;
                            AbstractC0150d.b(kVar);
                            z0.e.b(qVar.f2527j, this.f36d, kVar);
                            f();
                        }
                        if (!this.f38f) {
                            return -1L;
                        }
                    } else {
                        throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f37e + obj + '\"');
                    }
                } catch (NumberFormatException e2) {
                    throw new ProtocolException(e2.getMessage());
                }
            }
            long b2 = super.b(Math.min(8192L, this.f37e), eVar);
            if (b2 != -1) {
                this.f37e -= b2;
                return b2;
            }
            ((y0.k) hVar.f48c).k();
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            f();
            throw protocolException;
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f31b) {
            return;
        }
        if (this.f38f && !v0.b.g(this, TimeUnit.MILLISECONDS)) {
            ((y0.k) this.f39g.f48c).k();
            f();
        }
        this.f31b = true;
    }
}

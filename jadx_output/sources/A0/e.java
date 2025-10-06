package A0;

import j0.AbstractC0150d;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import y0.k;

/* loaded from: classes.dex */
public final class e extends b {

    /* renamed from: d, reason: collision with root package name */
    public long f40d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ h f41e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(h hVar, long j2) {
        super(hVar);
        AbstractC0150d.e(hVar, "this$0");
        this.f41e = hVar;
        this.f40d = j2;
        if (j2 == 0) {
            f();
        }
    }

    @Override // A0.b, H0.t
    public final long b(long j2, H0.e eVar) {
        if (!this.f31b) {
            long j3 = this.f40d;
            if (j3 == 0) {
                return -1L;
            }
            long b2 = super.b(Math.min(j3, 8192L), eVar);
            if (b2 != -1) {
                long j4 = this.f40d - b2;
                this.f40d = j4;
                if (j4 == 0) {
                    f();
                }
                return b2;
            }
            ((k) this.f41e.f48c).k();
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
        if (this.f40d != 0 && !v0.b.g(this, TimeUnit.MILLISECONDS)) {
            ((k) this.f41e.f48c).k();
            f();
        }
        this.f31b = true;
    }
}

package A0;

/* loaded from: classes.dex */
public final class g extends b {

    /* renamed from: d, reason: collision with root package name */
    public boolean f45d;

    @Override // A0.b, H0.t
    public final long b(long j2, H0.e eVar) {
        if (!this.f31b) {
            if (this.f45d) {
                return -1L;
            }
            long b2 = super.b(8192L, eVar);
            if (b2 == -1) {
                this.f45d = true;
                f();
                return -1L;
            }
            return b2;
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f31b) {
            return;
        }
        if (!this.f45d) {
            f();
        }
        this.f31b = true;
    }
}

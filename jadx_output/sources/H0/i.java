package H0;

import j0.AbstractC0150d;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class i extends v {

    /* renamed from: e, reason: collision with root package name */
    public v f417e;

    public i(v vVar) {
        AbstractC0150d.e(vVar, "delegate");
        this.f417e = vVar;
    }

    @Override // H0.v
    public final v a() {
        return this.f417e.a();
    }

    @Override // H0.v
    public final v b() {
        return this.f417e.b();
    }

    @Override // H0.v
    public final long c() {
        return this.f417e.c();
    }

    @Override // H0.v
    public final v d(long j2) {
        return this.f417e.d(j2);
    }

    @Override // H0.v
    public final boolean e() {
        return this.f417e.e();
    }

    @Override // H0.v
    public final void f() {
        this.f417e.f();
    }

    @Override // H0.v
    public final v g(long j2, TimeUnit timeUnit) {
        AbstractC0150d.e(timeUnit, "unit");
        return this.f417e.g(j2, timeUnit);
    }
}

package A0;

import H0.i;
import H0.n;
import H0.s;
import H0.v;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public final class f implements s {

    /* renamed from: a, reason: collision with root package name */
    public final i f42a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f43b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ h f44c;

    public f(h hVar) {
        AbstractC0150d.e(hVar, "this$0");
        this.f44c = hVar;
        this.f42a = new i(((n) hVar.f50e).f430a.a());
    }

    @Override // H0.s
    public final v a() {
        return this.f42a;
    }

    @Override // H0.s
    public final void c(long j2, H0.e eVar) {
        if (!this.f43b) {
            v0.b.b(eVar.f412b, 0L, j2);
            ((n) this.f44c.f50e).c(j2, eVar);
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // H0.s, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f43b) {
            return;
        }
        this.f43b = true;
        h hVar = this.f44c;
        hVar.getClass();
        i iVar = this.f42a;
        v vVar = iVar.f417e;
        iVar.f417e = v.f448d;
        vVar.a();
        vVar.b();
        hVar.f46a = 3;
    }

    @Override // H0.s, java.io.Flushable
    public final void flush() {
        if (this.f43b) {
            return;
        }
        ((n) this.f44c.f50e).flush();
    }
}

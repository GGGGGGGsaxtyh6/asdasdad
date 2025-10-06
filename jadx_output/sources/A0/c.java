package A0;

import H0.i;
import H0.n;
import H0.s;
import H0.v;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public final class c implements s {

    /* renamed from: a, reason: collision with root package name */
    public final i f33a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f34b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ h f35c;

    public c(h hVar) {
        AbstractC0150d.e(hVar, "this$0");
        this.f35c = hVar;
        this.f33a = new i(((n) hVar.f50e).f430a.a());
    }

    @Override // H0.s
    public final v a() {
        return this.f33a;
    }

    @Override // H0.s
    public final void c(long j2, H0.e eVar) {
        if (!this.f34b) {
            if (j2 == 0) {
                return;
            }
            h hVar = this.f35c;
            n nVar = (n) hVar.f50e;
            if (!nVar.f432c) {
                nVar.f431b.v(j2);
                nVar.f();
                n nVar2 = (n) hVar.f50e;
                nVar2.d("\r\n");
                nVar2.c(j2, eVar);
                nVar2.d("\r\n");
                return;
            }
            throw new IllegalStateException("closed");
        }
        throw new IllegalStateException("closed");
    }

    @Override // H0.s, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        if (this.f34b) {
            return;
        }
        this.f34b = true;
        ((n) this.f35c.f50e).d("0\r\n\r\n");
        h hVar = this.f35c;
        i iVar = this.f33a;
        hVar.getClass();
        v vVar = iVar.f417e;
        iVar.f417e = v.f448d;
        vVar.a();
        vVar.b();
        this.f35c.f46a = 3;
    }

    @Override // H0.s, java.io.Flushable
    public final synchronized void flush() {
        if (this.f34b) {
            return;
        }
        ((n) this.f35c.f50e).flush();
    }
}

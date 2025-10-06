package A0;

import H0.i;
import H0.o;
import H0.t;
import H0.v;
import j0.AbstractC0150d;
import java.io.IOException;
import y0.k;

/* loaded from: classes.dex */
public abstract class b implements t {

    /* renamed from: a, reason: collision with root package name */
    public final i f30a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f31b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ h f32c;

    public b(h hVar) {
        AbstractC0150d.e(hVar, "this$0");
        this.f32c = hVar;
        this.f30a = new i(((o) hVar.f49d).f433a.a());
    }

    @Override // H0.t
    public final v a() {
        return this.f30a;
    }

    @Override // H0.t
    public long b(long j2, H0.e eVar) {
        h hVar = this.f32c;
        AbstractC0150d.e(eVar, "sink");
        try {
            return ((o) hVar.f49d).b(j2, eVar);
        } catch (IOException e2) {
            ((k) hVar.f48c).k();
            f();
            throw e2;
        }
    }

    public final void f() {
        h hVar = this.f32c;
        int i2 = hVar.f46a;
        if (i2 == 6) {
            return;
        }
        if (i2 == 5) {
            i iVar = this.f30a;
            v vVar = iVar.f417e;
            iVar.f417e = v.f448d;
            vVar.a();
            vVar.b();
            hVar.f46a = 6;
            return;
        }
        throw new IllegalStateException(AbstractC0150d.h(Integer.valueOf(hVar.f46a), "state: "));
    }
}

package y0;

import i0.InterfaceC0131a;
import j0.AbstractC0150d;
import j0.AbstractC0151e;

/* loaded from: classes.dex */
public final class j extends AbstractC0151e implements InterfaceC0131a {

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ u0.e f2900b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ u0.j f2901c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ u0.a f2902d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(u0.e eVar, u0.j jVar, u0.a aVar) {
        super(0);
        this.f2900b = eVar;
        this.f2901c = jVar;
        this.f2902d = aVar;
    }

    @Override // i0.InterfaceC0131a
    public final Object a() {
        C0.f fVar = this.f2900b.f2432b;
        AbstractC0150d.b(fVar);
        return fVar.j(this.f2902d.h.f2486d, this.f2901c.a());
    }
}

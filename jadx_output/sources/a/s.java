package a;

import androidx.lifecycle.AbstractC0068o;
import androidx.lifecycle.EnumC0066m;
import androidx.lifecycle.InterfaceC0072t;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public final class s implements androidx.lifecycle.r, InterfaceC0035b {

    /* renamed from: a, reason: collision with root package name */
    public final AbstractC0068o f768a;

    /* renamed from: b, reason: collision with root package name */
    public final androidx.fragment.app.o f769b;

    /* renamed from: c, reason: collision with root package name */
    public t f770c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ v f771d;

    public s(v vVar, AbstractC0068o abstractC0068o, androidx.fragment.app.o oVar) {
        AbstractC0150d.e(abstractC0068o, "lifecycle");
        AbstractC0150d.e(oVar, "onBackPressedCallback");
        this.f771d = vVar;
        this.f768a = abstractC0068o;
        this.f769b = oVar;
        abstractC0068o.a(this);
    }

    @Override // a.InterfaceC0035b
    public final void cancel() {
        this.f768a.b(this);
        this.f769b.f1067b.remove(this);
        t tVar = this.f770c;
        if (tVar != null) {
            tVar.cancel();
        }
        this.f770c = null;
    }

    @Override // androidx.lifecycle.r
    public final void f(InterfaceC0072t interfaceC0072t, EnumC0066m enumC0066m) {
        if (enumC0066m == EnumC0066m.ON_START) {
            v vVar = this.f771d;
            androidx.fragment.app.o oVar = this.f769b;
            AbstractC0150d.e(oVar, "onBackPressedCallback");
            vVar.f780b.addLast(oVar);
            t tVar = new t(vVar, oVar);
            oVar.f1067b.add(tVar);
            vVar.d();
            oVar.f1068c = new u(1, vVar);
            this.f770c = tVar;
            return;
        }
        if (enumC0066m == EnumC0066m.ON_STOP) {
            t tVar2 = this.f770c;
            if (tVar2 != null) {
                tVar2.cancel();
                return;
            }
            return;
        }
        if (enumC0066m == EnumC0066m.ON_DESTROY) {
            cancel();
        }
    }
}

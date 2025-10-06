package a;

import b0.C0080f;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public final class t implements InterfaceC0035b {

    /* renamed from: a, reason: collision with root package name */
    public final androidx.fragment.app.o f772a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ v f773b;

    public t(v vVar, androidx.fragment.app.o oVar) {
        AbstractC0150d.e(oVar, "onBackPressedCallback");
        this.f773b = vVar;
        this.f772a = oVar;
    }

    @Override // a.InterfaceC0035b
    public final void cancel() {
        v vVar = this.f773b;
        C0080f c0080f = vVar.f780b;
        androidx.fragment.app.o oVar = this.f772a;
        c0080f.remove(oVar);
        if (AbstractC0150d.a(vVar.f781c, oVar)) {
            oVar.getClass();
            vVar.f781c = null;
        }
        oVar.f1067b.remove(this);
        u uVar = oVar.f1068c;
        if (uVar != null) {
            uVar.a();
        }
        oVar.f1068c = null;
    }
}

package j;

import android.view.ActionProvider;

/* loaded from: classes.dex */
public final class q implements ActionProvider.VisibilityListener {

    /* renamed from: a, reason: collision with root package name */
    public A.f f1711a;

    /* renamed from: b, reason: collision with root package name */
    public final ActionProvider f1712b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ u f1713c;

    public q(u uVar, ActionProvider actionProvider) {
        this.f1713c = uVar;
        this.f1712b = actionProvider;
    }

    @Override // android.view.ActionProvider.VisibilityListener
    public final void onActionProviderVisibilityChanged(boolean z2) {
        A.f fVar = this.f1711a;
        if (fVar != null) {
            n nVar = ((p) fVar.f8b).f1699n;
            nVar.h = true;
            nVar.p(true);
        }
    }
}

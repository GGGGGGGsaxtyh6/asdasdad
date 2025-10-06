package g;

import androidx.appcompat.widget.ActionMenuView;
import k.C0176g;
import k.C0184k;

/* loaded from: classes.dex */
public final class J implements j.y {

    /* renamed from: a, reason: collision with root package name */
    public boolean f1367a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ K f1368b;

    public J(K k2) {
        this.f1368b = k2;
    }

    @Override // j.y
    public final void a(j.n nVar, boolean z2) {
        C0184k c0184k;
        if (this.f1367a) {
            return;
        }
        this.f1367a = true;
        K k2 = this.f1368b;
        ActionMenuView actionMenuView = k2.f1369a.f1895a.f925a;
        if (actionMenuView != null && (c0184k = actionMenuView.f885t) != null) {
            c0184k.e();
            C0176g c0176g = c0184k.f1969t;
            if (c0176g != null && c0176g.b()) {
                c0176g.f1730i.dismiss();
            }
        }
        k2.f1370b.onPanelClosed(108, nVar);
        this.f1367a = false;
    }

    @Override // j.y
    public final boolean c(j.n nVar) {
        this.f1368b.f1370b.onMenuOpened(108, nVar);
        return true;
    }
}

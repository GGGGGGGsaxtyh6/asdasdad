package k;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

/* loaded from: classes.dex */
public final class X0 implements InterfaceC0190n, j.l {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Toolbar f1858a;

    public /* synthetic */ X0(Toolbar toolbar) {
        this.f1858a = toolbar;
    }

    @Override // j.l
    public void k(j.n nVar) {
        Toolbar toolbar = this.f1858a;
        C0184k c0184k = toolbar.f925a.f885t;
        if (c0184k == null || !c0184k.i()) {
            toolbar.f911G.c();
        }
        g.I i2 = toolbar.f919O;
        if (i2 != null) {
            i2.k(nVar);
        }
    }

    @Override // j.l
    public boolean m(j.n nVar, MenuItem menuItem) {
        g.I i2 = this.f1858a.f919O;
        return false;
    }
}

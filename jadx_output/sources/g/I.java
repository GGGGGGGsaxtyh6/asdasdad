package g;

import android.view.MenuItem;
import k.b1;

/* loaded from: classes.dex */
public final class I implements b1, j.l {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ K f1366a;

    public /* synthetic */ I(K k2) {
        this.f1366a = k2;
    }

    @Override // j.l
    public void k(j.n nVar) {
        K k2 = this.f1366a;
        boolean o2 = k2.f1369a.f1895a.o();
        w wVar = k2.f1370b;
        if (o2) {
            wVar.onPanelClosed(108, nVar);
        } else if (wVar.onPreparePanel(0, null, nVar)) {
            wVar.onMenuOpened(108, nVar);
        }
    }

    @Override // j.l
    public boolean m(j.n nVar, MenuItem menuItem) {
        return false;
    }
}

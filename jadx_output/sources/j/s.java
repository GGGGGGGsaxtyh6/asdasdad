package j;

import android.view.MenuItem;

/* loaded from: classes.dex */
public final class s implements MenuItem.OnActionExpandListener {

    /* renamed from: a, reason: collision with root package name */
    public final MenuItem.OnActionExpandListener f1715a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ u f1716b;

    public s(u uVar, MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f1716b = uVar;
        this.f1715a = onActionExpandListener;
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public final boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return this.f1715a.onMenuItemActionCollapse(this.f1716b.f(menuItem));
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public final boolean onMenuItemActionExpand(MenuItem menuItem) {
        return this.f1715a.onMenuItemActionExpand(this.f1716b.f(menuItem));
    }
}

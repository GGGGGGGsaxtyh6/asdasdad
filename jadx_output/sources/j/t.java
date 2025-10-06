package j;

import android.view.MenuItem;

/* loaded from: classes.dex */
public final class t implements MenuItem.OnMenuItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final MenuItem.OnMenuItemClickListener f1717a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ u f1718b;

    public t(u uVar, MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f1718b = uVar;
        this.f1717a = onMenuItemClickListener;
    }

    @Override // android.view.MenuItem.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        return this.f1717a.onMenuItemClick(this.f1718b.f(menuItem));
    }
}

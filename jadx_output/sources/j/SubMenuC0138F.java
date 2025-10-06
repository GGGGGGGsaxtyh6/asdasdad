package j;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: j.F, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class SubMenuC0138F extends n implements SubMenu {

    /* renamed from: A, reason: collision with root package name */
    public final p f1591A;

    /* renamed from: z, reason: collision with root package name */
    public final n f1592z;

    public SubMenuC0138F(Context context, n nVar, p pVar) {
        super(context);
        this.f1592z = nVar;
        this.f1591A = pVar;
    }

    @Override // j.n
    public final boolean d(p pVar) {
        return this.f1592z.d(pVar);
    }

    @Override // j.n
    public final boolean e(n nVar, MenuItem menuItem) {
        if (!super.e(nVar, menuItem) && !this.f1592z.e(nVar, menuItem)) {
            return false;
        }
        return true;
    }

    @Override // j.n
    public final boolean f(p pVar) {
        return this.f1592z.f(pVar);
    }

    @Override // android.view.SubMenu
    public final MenuItem getItem() {
        return this.f1591A;
    }

    @Override // j.n
    public final String j() {
        int i2;
        p pVar = this.f1591A;
        if (pVar != null) {
            i2 = pVar.f1687a;
        } else {
            i2 = 0;
        }
        if (i2 == 0) {
            return null;
        }
        return A.e.a("android:menu:actionviewstates:", i2);
    }

    @Override // j.n
    public final n k() {
        return this.f1592z.k();
    }

    @Override // j.n
    public final boolean m() {
        return this.f1592z.m();
    }

    @Override // j.n
    public final boolean n() {
        return this.f1592z.n();
    }

    @Override // j.n
    public final boolean o() {
        return this.f1592z.o();
    }

    @Override // j.n, android.view.Menu
    public final void setGroupDividerEnabled(boolean z2) {
        this.f1592z.setGroupDividerEnabled(z2);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        u(0, null, 0, drawable, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        u(0, charSequence, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderView(View view) {
        u(0, null, 0, null, view);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(Drawable drawable) {
        this.f1591A.setIcon(drawable);
        return this;
    }

    @Override // j.n, android.view.Menu
    public final void setQwertyMode(boolean z2) {
        this.f1592z.setQwertyMode(z2);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i2) {
        u(0, null, i2, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i2) {
        u(i2, null, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(int i2) {
        this.f1591A.setIcon(i2);
        return this;
    }
}

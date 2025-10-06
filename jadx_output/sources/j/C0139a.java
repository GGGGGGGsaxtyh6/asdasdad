package j;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: j.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0139a implements C.a {

    /* renamed from: a, reason: collision with root package name */
    public CharSequence f1593a;

    /* renamed from: b, reason: collision with root package name */
    public CharSequence f1594b;

    /* renamed from: c, reason: collision with root package name */
    public Intent f1595c;

    /* renamed from: d, reason: collision with root package name */
    public char f1596d;

    /* renamed from: e, reason: collision with root package name */
    public int f1597e;

    /* renamed from: f, reason: collision with root package name */
    public char f1598f;

    /* renamed from: g, reason: collision with root package name */
    public int f1599g;
    public Drawable h;

    /* renamed from: i, reason: collision with root package name */
    public Context f1600i;

    /* renamed from: j, reason: collision with root package name */
    public CharSequence f1601j;

    /* renamed from: k, reason: collision with root package name */
    public CharSequence f1602k;

    /* renamed from: l, reason: collision with root package name */
    public ColorStateList f1603l;

    /* renamed from: m, reason: collision with root package name */
    public PorterDuff.Mode f1604m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f1605n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f1606o;

    /* renamed from: p, reason: collision with root package name */
    public int f1607p;

    @Override // C.a
    public final C.a a(q qVar) {
        throw new UnsupportedOperationException();
    }

    @Override // C.a
    public final q b() {
        return null;
    }

    public final void c() {
        Drawable drawable = this.h;
        if (drawable != null) {
            if (this.f1605n || this.f1606o) {
                this.h = drawable;
                Drawable mutate = drawable.mutate();
                this.h = mutate;
                if (this.f1605n) {
                    mutate.setTintList(this.f1603l);
                }
                if (this.f1606o) {
                    this.h.setTintMode(this.f1604m);
                }
            }
        }
    }

    @Override // android.view.MenuItem
    public final boolean collapseActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final View getActionView() {
        return null;
    }

    @Override // C.a, android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return this.f1599g;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.f1598f;
    }

    @Override // C.a, android.view.MenuItem
    public final CharSequence getContentDescription() {
        return this.f1601j;
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return 0;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        return this.h;
    }

    @Override // C.a, android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return this.f1603l;
    }

    @Override // C.a, android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return this.f1604m;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.f1595c;
    }

    @Override // android.view.MenuItem
    public final int getItemId() {
        return R.id.home;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // C.a, android.view.MenuItem
    public final int getNumericModifiers() {
        return this.f1597e;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.f1596d;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return 0;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitle() {
        return this.f1593a;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f1594b;
        if (charSequence == null) {
            return this.f1593a;
        }
        return charSequence;
    }

    @Override // C.a, android.view.MenuItem
    public final CharSequence getTooltipText() {
        return this.f1602k;
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        if ((this.f1607p & 1) != 0) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        if ((this.f1607p & 2) != 0) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        if ((this.f1607p & 16) != 0) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        if ((this.f1607p & 8) == 0) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c2) {
        this.f1598f = Character.toLowerCase(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z2) {
        this.f1607p = (z2 ? 1 : 0) | (this.f1607p & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z2) {
        int i2;
        int i3 = this.f1607p & (-3);
        if (z2) {
            i2 = 2;
        } else {
            i2 = 0;
        }
        this.f1607p = i2 | i3;
        return this;
    }

    @Override // C.a, android.view.MenuItem
    public final C.a setContentDescription(CharSequence charSequence) {
        this.f1601j = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z2) {
        int i2;
        int i3 = this.f1607p & (-17);
        if (z2) {
            i2 = 16;
        } else {
            i2 = 0;
        }
        this.f1607p = i2 | i3;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.h = drawable;
        c();
        return this;
    }

    @Override // C.a, android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f1603l = colorStateList;
        this.f1605n = true;
        c();
        return this;
    }

    @Override // C.a, android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f1604m = mode;
        this.f1606o = true;
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.f1595c = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c2) {
        this.f1596d = c2;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c2, char c3) {
        this.f1596d = c2;
        this.f1598f = Character.toLowerCase(c3);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.f1593a = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f1594b = charSequence;
        return this;
    }

    @Override // C.a, android.view.MenuItem
    public final C.a setTooltipText(CharSequence charSequence) {
        this.f1602k = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z2) {
        int i2 = 8;
        int i3 = this.f1607p & 8;
        if (z2) {
            i2 = 0;
        }
        this.f1607p = i3 | i2;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // C.a, android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c2, int i2) {
        this.f1598f = Character.toLowerCase(c2);
        this.f1599g = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setContentDescription(CharSequence charSequence) {
        this.f1601j = charSequence;
        return this;
    }

    @Override // C.a, android.view.MenuItem
    public final MenuItem setNumericShortcut(char c2, int i2) {
        this.f1596d = c2;
        this.f1597e = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i2) {
        this.f1593a = this.f1600i.getResources().getString(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTooltipText(CharSequence charSequence) {
        this.f1602k = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i2) {
        this.h = this.f1600i.getDrawable(i2);
        c();
        return this;
    }

    @Override // C.a, android.view.MenuItem
    public final MenuItem setShortcut(char c2, char c3, int i2, int i3) {
        this.f1596d = c2;
        this.f1597e = KeyEvent.normalizeMetaState(i2);
        this.f1598f = Character.toLowerCase(c3);
        this.f1599g = KeyEvent.normalizeMetaState(i3);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        return this;
    }

    @Override // android.view.MenuItem
    public final void setShowAsAction(int i2) {
    }

    @Override // android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i2) {
        return this;
    }
}

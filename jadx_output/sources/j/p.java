package j;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class p implements C.a {

    /* renamed from: A, reason: collision with root package name */
    public q f1684A;

    /* renamed from: B, reason: collision with root package name */
    public MenuItem.OnActionExpandListener f1685B;

    /* renamed from: a, reason: collision with root package name */
    public final int f1687a;

    /* renamed from: b, reason: collision with root package name */
    public final int f1688b;

    /* renamed from: c, reason: collision with root package name */
    public final int f1689c;

    /* renamed from: d, reason: collision with root package name */
    public final int f1690d;

    /* renamed from: e, reason: collision with root package name */
    public CharSequence f1691e;

    /* renamed from: f, reason: collision with root package name */
    public CharSequence f1692f;

    /* renamed from: g, reason: collision with root package name */
    public Intent f1693g;
    public char h;

    /* renamed from: j, reason: collision with root package name */
    public char f1695j;

    /* renamed from: l, reason: collision with root package name */
    public Drawable f1697l;

    /* renamed from: n, reason: collision with root package name */
    public final n f1699n;

    /* renamed from: o, reason: collision with root package name */
    public SubMenuC0138F f1700o;

    /* renamed from: p, reason: collision with root package name */
    public MenuItem.OnMenuItemClickListener f1701p;

    /* renamed from: q, reason: collision with root package name */
    public CharSequence f1702q;

    /* renamed from: r, reason: collision with root package name */
    public CharSequence f1703r;
    public int y;

    /* renamed from: z, reason: collision with root package name */
    public View f1710z;

    /* renamed from: i, reason: collision with root package name */
    public int f1694i = 4096;

    /* renamed from: k, reason: collision with root package name */
    public int f1696k = 4096;

    /* renamed from: m, reason: collision with root package name */
    public int f1698m = 0;

    /* renamed from: s, reason: collision with root package name */
    public ColorStateList f1704s = null;

    /* renamed from: t, reason: collision with root package name */
    public PorterDuff.Mode f1705t = null;

    /* renamed from: u, reason: collision with root package name */
    public boolean f1706u = false;

    /* renamed from: v, reason: collision with root package name */
    public boolean f1707v = false;

    /* renamed from: w, reason: collision with root package name */
    public boolean f1708w = false;

    /* renamed from: x, reason: collision with root package name */
    public int f1709x = 16;

    /* renamed from: C, reason: collision with root package name */
    public boolean f1686C = false;

    public p(n nVar, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        this.f1699n = nVar;
        this.f1687a = i3;
        this.f1688b = i2;
        this.f1689c = i4;
        this.f1690d = i5;
        this.f1691e = charSequence;
        this.y = i6;
    }

    public static void c(int i2, int i3, String str, StringBuilder sb) {
        if ((i2 & i3) == i3) {
            sb.append(str);
        }
    }

    @Override // C.a
    public final C.a a(q qVar) {
        this.f1710z = null;
        this.f1684A = qVar;
        this.f1699n.p(true);
        q qVar2 = this.f1684A;
        if (qVar2 != null) {
            qVar2.f1711a = new A.f(23, this);
            qVar2.f1712b.setVisibilityListener(qVar2);
        }
        return this;
    }

    @Override // C.a
    public final q b() {
        return this.f1684A;
    }

    @Override // android.view.MenuItem
    public final boolean collapseActionView() {
        if ((this.y & 8) == 0) {
            return false;
        }
        if (this.f1710z == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.f1685B;
        if (onActionExpandListener != null && !onActionExpandListener.onMenuItemActionCollapse(this)) {
            return false;
        }
        return this.f1699n.d(this);
    }

    public final Drawable d(Drawable drawable) {
        if (drawable != null && this.f1708w && (this.f1706u || this.f1707v)) {
            drawable = drawable.mutate();
            if (this.f1706u) {
                drawable.setTintList(this.f1704s);
            }
            if (this.f1707v) {
                drawable.setTintMode(this.f1705t);
            }
            this.f1708w = false;
        }
        return drawable;
    }

    public final boolean e() {
        q qVar;
        if ((this.y & 8) == 0) {
            return false;
        }
        if (this.f1710z == null && (qVar = this.f1684A) != null) {
            this.f1710z = qVar.f1712b.onCreateActionView(this);
        }
        if (this.f1710z == null) {
            return false;
        }
        return true;
    }

    @Override // android.view.MenuItem
    public final boolean expandActionView() {
        if (!e()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.f1685B;
        if (onActionExpandListener != null && !onActionExpandListener.onMenuItemActionExpand(this)) {
            return false;
        }
        return this.f1699n.f(this);
    }

    public final boolean f() {
        if ((this.f1709x & 32) == 32) {
            return true;
        }
        return false;
    }

    public final void g(boolean z2) {
        if (z2) {
            this.f1709x |= 32;
        } else {
            this.f1709x &= -33;
        }
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // android.view.MenuItem
    public final View getActionView() {
        View view = this.f1710z;
        if (view != null) {
            return view;
        }
        q qVar = this.f1684A;
        if (qVar != null) {
            View onCreateActionView = qVar.f1712b.onCreateActionView(this);
            this.f1710z = onCreateActionView;
            return onCreateActionView;
        }
        return null;
    }

    @Override // C.a, android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return this.f1696k;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.f1695j;
    }

    @Override // C.a, android.view.MenuItem
    public final CharSequence getContentDescription() {
        return this.f1702q;
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return this.f1688b;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        Drawable drawable = this.f1697l;
        if (drawable != null) {
            return d(drawable);
        }
        int i2 = this.f1698m;
        if (i2 != 0) {
            Drawable v2 = C0.d.v(this.f1699n.f1658a, i2);
            this.f1698m = 0;
            this.f1697l = v2;
            return d(v2);
        }
        return null;
    }

    @Override // C.a, android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return this.f1704s;
    }

    @Override // C.a, android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return this.f1705t;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.f1693g;
    }

    @Override // android.view.MenuItem
    public final int getItemId() {
        return this.f1687a;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // C.a, android.view.MenuItem
    public final int getNumericModifiers() {
        return this.f1694i;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.h;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return this.f1689c;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return this.f1700o;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitle() {
        return this.f1691e;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f1692f;
        if (charSequence == null) {
            return this.f1691e;
        }
        return charSequence;
    }

    @Override // C.a, android.view.MenuItem
    public final CharSequence getTooltipText() {
        return this.f1703r;
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        if (this.f1700o != null) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return this.f1686C;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        if ((this.f1709x & 1) == 1) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        if ((this.f1709x & 2) == 2) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        if ((this.f1709x & 16) != 0) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        q qVar = this.f1684A;
        if (qVar != null && qVar.f1712b.overridesItemVisibility()) {
            if ((this.f1709x & 8) != 0 || !this.f1684A.f1712b.isVisible()) {
                return false;
            }
            return true;
        }
        if ((this.f1709x & 8) != 0) {
            return false;
        }
        return true;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(View view) {
        int i2;
        this.f1710z = view;
        this.f1684A = null;
        if (view != null && view.getId() == -1 && (i2 = this.f1687a) > 0) {
            view.setId(i2);
        }
        n nVar = this.f1699n;
        nVar.f1667k = true;
        nVar.p(true);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c2) {
        if (this.f1695j == c2) {
            return this;
        }
        this.f1695j = Character.toLowerCase(c2);
        this.f1699n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z2) {
        int i2 = this.f1709x;
        int i3 = (z2 ? 1 : 0) | (i2 & (-2));
        this.f1709x = i3;
        if (i2 != i3) {
            this.f1699n.p(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z2) {
        boolean z3;
        int i2;
        int i3 = this.f1709x;
        int i4 = 2;
        if ((i3 & 4) != 0) {
            n nVar = this.f1699n;
            nVar.getClass();
            ArrayList arrayList = nVar.f1663f;
            int size = arrayList.size();
            nVar.w();
            for (int i5 = 0; i5 < size; i5++) {
                p pVar = (p) arrayList.get(i5);
                if (pVar.f1688b == this.f1688b && (pVar.f1709x & 4) != 0 && pVar.isCheckable()) {
                    if (pVar == this) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    int i6 = pVar.f1709x;
                    int i7 = i6 & (-3);
                    if (z3) {
                        i2 = 2;
                    } else {
                        i2 = 0;
                    }
                    int i8 = i2 | i7;
                    pVar.f1709x = i8;
                    if (i6 != i8) {
                        pVar.f1699n.p(false);
                    }
                }
            }
            nVar.v();
        } else {
            int i9 = i3 & (-3);
            if (!z2) {
                i4 = 0;
            }
            int i10 = i9 | i4;
            this.f1709x = i10;
            if (i3 != i10) {
                this.f1699n.p(false);
            }
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence charSequence) {
        setContentDescription(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z2) {
        if (z2) {
            this.f1709x |= 16;
        } else {
            this.f1709x &= -17;
        }
        this.f1699n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.f1698m = 0;
        this.f1697l = drawable;
        this.f1708w = true;
        this.f1699n.p(false);
        return this;
    }

    @Override // C.a, android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f1704s = colorStateList;
        this.f1706u = true;
        this.f1708w = true;
        this.f1699n.p(false);
        return this;
    }

    @Override // C.a, android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f1705t = mode;
        this.f1707v = true;
        this.f1708w = true;
        this.f1699n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.f1693g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c2) {
        if (this.h == c2) {
            return this;
        }
        this.h = c2;
        this.f1699n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f1685B = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f1701p = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c2, char c3) {
        this.h = c2;
        this.f1695j = Character.toLowerCase(c3);
        this.f1699n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final void setShowAsAction(int i2) {
        int i3 = i2 & 3;
        if (i3 != 0 && i3 != 1 && i3 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.y = i2;
        n nVar = this.f1699n;
        nVar.f1667k = true;
        nVar.p(true);
    }

    @Override // android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.f1691e = charSequence;
        this.f1699n.p(false);
        SubMenuC0138F subMenuC0138F = this.f1700o;
        if (subMenuC0138F != null) {
            subMenuC0138F.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f1692f = charSequence;
        this.f1699n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence charSequence) {
        setTooltipText(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z2) {
        int i2;
        int i3 = this.f1709x;
        int i4 = i3 & (-9);
        if (z2) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        int i5 = i2 | i4;
        this.f1709x = i5;
        if (i3 != i5) {
            n nVar = this.f1699n;
            nVar.h = true;
            nVar.p(true);
        }
        return this;
    }

    public final String toString() {
        CharSequence charSequence = this.f1691e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    @Override // C.a, android.view.MenuItem
    public final C.a setContentDescription(CharSequence charSequence) {
        this.f1702q = charSequence;
        this.f1699n.p(false);
        return this;
    }

    @Override // C.a, android.view.MenuItem
    public final C.a setTooltipText(CharSequence charSequence) {
        this.f1703r = charSequence;
        this.f1699n.p(false);
        return this;
    }

    @Override // C.a, android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c2, int i2) {
        if (this.f1695j == c2 && this.f1696k == i2) {
            return this;
        }
        this.f1695j = Character.toLowerCase(c2);
        this.f1696k = KeyEvent.normalizeMetaState(i2);
        this.f1699n.p(false);
        return this;
    }

    @Override // C.a, android.view.MenuItem
    public final MenuItem setNumericShortcut(char c2, int i2) {
        if (this.h == c2 && this.f1694i == i2) {
            return this;
        }
        this.h = c2;
        this.f1694i = KeyEvent.normalizeMetaState(i2);
        this.f1699n.p(false);
        return this;
    }

    @Override // C.a, android.view.MenuItem
    public final MenuItem setShortcut(char c2, char c3, int i2, int i3) {
        this.h = c2;
        this.f1694i = KeyEvent.normalizeMetaState(i2);
        this.f1695j = Character.toLowerCase(c3);
        this.f1696k = KeyEvent.normalizeMetaState(i3);
        this.f1699n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i2) {
        this.f1697l = null;
        this.f1698m = i2;
        this.f1708w = true;
        this.f1699n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i2) {
        setTitle(this.f1699n.f1658a.getString(i2));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(int i2) {
        int i3;
        Context context = this.f1699n.f1658a;
        View inflate = LayoutInflater.from(context).inflate(i2, (ViewGroup) new LinearLayout(context), false);
        this.f1710z = inflate;
        this.f1684A = null;
        if (inflate != null && inflate.getId() == -1 && (i3 = this.f1687a) > 0) {
            inflate.setId(i3);
        }
        n nVar = this.f1699n;
        nVar.f1667k = true;
        nVar.p(true);
        return this;
    }
}

package j;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/* renamed from: j.C, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class MenuC0135C extends g.z implements Menu {

    /* renamed from: c, reason: collision with root package name */
    public final n f1572c;

    public MenuC0135C(Context context, n nVar) {
        super(context);
        if (nVar != null) {
            this.f1572c = nVar;
            return;
        }
        throw new IllegalArgumentException("Wrapped Object can not be null.");
    }

    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return f(this.f1572c.a(0, 0, 0, charSequence));
    }

    @Override // android.view.Menu
    public final int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2;
        if (menuItemArr != null) {
            menuItemArr2 = new MenuItem[menuItemArr.length];
        } else {
            menuItemArr2 = null;
        }
        int addIntentOptions = this.f1572c.addIntentOptions(i2, i3, i4, componentName, intentArr, intent, i5, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i6 = 0; i6 < length; i6++) {
                menuItemArr[i6] = f(menuItemArr2[i6]);
            }
        }
        return addIntentOptions;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return this.f1572c.addSubMenu(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public final void clear() {
        n.k kVar = (n.k) this.f1483b;
        if (kVar != null) {
            kVar.clear();
        }
        this.f1572c.clear();
    }

    @Override // android.view.Menu
    public final void close() {
        this.f1572c.close();
    }

    @Override // android.view.Menu
    public final MenuItem findItem(int i2) {
        return f(this.f1572c.findItem(i2));
    }

    @Override // android.view.Menu
    public final MenuItem getItem(int i2) {
        return f(this.f1572c.getItem(i2));
    }

    @Override // android.view.Menu
    public final boolean hasVisibleItems() {
        return this.f1572c.hasVisibleItems();
    }

    @Override // android.view.Menu
    public final boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return this.f1572c.isShortcutKey(i2, keyEvent);
    }

    @Override // android.view.Menu
    public final boolean performIdentifierAction(int i2, int i3) {
        return this.f1572c.performIdentifierAction(i2, i3);
    }

    @Override // android.view.Menu
    public final boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        return this.f1572c.performShortcut(i2, keyEvent, i3);
    }

    @Override // android.view.Menu
    public final void removeGroup(int i2) {
        if (((n.k) this.f1483b) != null) {
            int i3 = 0;
            while (true) {
                n.k kVar = (n.k) this.f1483b;
                if (i3 >= kVar.f2127c) {
                    break;
                }
                if (((C.a) kVar.e(i3)).getGroupId() == i2) {
                    ((n.k) this.f1483b).f(i3);
                    i3--;
                }
                i3++;
            }
        }
        this.f1572c.removeGroup(i2);
    }

    @Override // android.view.Menu
    public final void removeItem(int i2) {
        if (((n.k) this.f1483b) != null) {
            int i3 = 0;
            while (true) {
                n.k kVar = (n.k) this.f1483b;
                if (i3 >= kVar.f2127c) {
                    break;
                }
                if (((C.a) kVar.e(i3)).getItemId() == i2) {
                    ((n.k) this.f1483b).f(i3);
                    break;
                }
                i3++;
            }
        }
        this.f1572c.removeItem(i2);
    }

    @Override // android.view.Menu
    public final void setGroupCheckable(int i2, boolean z2, boolean z3) {
        this.f1572c.setGroupCheckable(i2, z2, z3);
    }

    @Override // android.view.Menu
    public final void setGroupEnabled(int i2, boolean z2) {
        this.f1572c.setGroupEnabled(i2, z2);
    }

    @Override // android.view.Menu
    public final void setGroupVisible(int i2, boolean z2) {
        this.f1572c.setGroupVisible(i2, z2);
    }

    @Override // android.view.Menu
    public final void setQwertyMode(boolean z2) {
        this.f1572c.setQwertyMode(z2);
    }

    @Override // android.view.Menu
    public final int size() {
        return this.f1572c.size();
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2) {
        return this.f1572c.addSubMenu(i2);
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2) {
        return f(this.f1572c.add(i2));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        return this.f1572c.addSubMenu(i2, i3, i4, charSequence);
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return f(this.f1572c.a(i2, i3, i4, charSequence));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return this.f1572c.addSubMenu(i2, i3, i4, i5);
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2, int i3, int i4, int i5) {
        return f(this.f1572c.add(i2, i3, i4, i5));
    }
}

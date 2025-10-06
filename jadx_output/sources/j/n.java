package j;

import H.O;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class n implements Menu {
    public static final int[] y = {1, 4, 5, 3, 2, 0};

    /* renamed from: a, reason: collision with root package name */
    public final Context f1658a;

    /* renamed from: b, reason: collision with root package name */
    public final Resources f1659b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f1660c;

    /* renamed from: d, reason: collision with root package name */
    public final boolean f1661d;

    /* renamed from: e, reason: collision with root package name */
    public l f1662e;

    /* renamed from: f, reason: collision with root package name */
    public final ArrayList f1663f;

    /* renamed from: g, reason: collision with root package name */
    public final ArrayList f1664g;
    public boolean h;

    /* renamed from: i, reason: collision with root package name */
    public final ArrayList f1665i;

    /* renamed from: j, reason: collision with root package name */
    public final ArrayList f1666j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f1667k;

    /* renamed from: m, reason: collision with root package name */
    public CharSequence f1669m;

    /* renamed from: n, reason: collision with root package name */
    public Drawable f1670n;

    /* renamed from: o, reason: collision with root package name */
    public View f1671o;

    /* renamed from: v, reason: collision with root package name */
    public p f1678v;

    /* renamed from: x, reason: collision with root package name */
    public boolean f1680x;

    /* renamed from: l, reason: collision with root package name */
    public int f1668l = 0;

    /* renamed from: p, reason: collision with root package name */
    public boolean f1672p = false;

    /* renamed from: q, reason: collision with root package name */
    public boolean f1673q = false;

    /* renamed from: r, reason: collision with root package name */
    public boolean f1674r = false;

    /* renamed from: s, reason: collision with root package name */
    public boolean f1675s = false;

    /* renamed from: t, reason: collision with root package name */
    public final ArrayList f1676t = new ArrayList();

    /* renamed from: u, reason: collision with root package name */
    public final CopyOnWriteArrayList f1677u = new CopyOnWriteArrayList();

    /* renamed from: w, reason: collision with root package name */
    public boolean f1679w = false;

    public n(Context context) {
        boolean z2;
        boolean z3 = false;
        this.f1658a = context;
        Resources resources = context.getResources();
        this.f1659b = resources;
        this.f1663f = new ArrayList();
        this.f1664g = new ArrayList();
        this.h = true;
        this.f1665i = new ArrayList();
        this.f1666j = new ArrayList();
        this.f1667k = true;
        if (resources.getConfiguration().keyboard != 1) {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            int i2 = O.f332a;
            if (Build.VERSION.SDK_INT >= 28) {
                z2 = B.a.f(viewConfiguration);
            } else {
                Resources resources2 = context.getResources();
                int identifier = resources2.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android");
                if (identifier != 0 && resources2.getBoolean(identifier)) {
                    z2 = true;
                } else {
                    z2 = false;
                }
            }
            if (z2) {
                z3 = true;
            }
        }
        this.f1661d = z3;
    }

    public final p a(int i2, int i3, int i4, CharSequence charSequence) {
        int i5;
        int i6 = ((-65536) & i4) >> 16;
        if (i6 >= 0 && i6 < 6) {
            int i7 = (y[i6] << 16) | (65535 & i4);
            p pVar = new p(this, i2, i3, i4, i7, charSequence, this.f1668l);
            ArrayList arrayList = this.f1663f;
            int size = arrayList.size() - 1;
            while (true) {
                if (size >= 0) {
                    if (((p) arrayList.get(size)).f1690d <= i7) {
                        i5 = size + 1;
                        break;
                    }
                    size--;
                } else {
                    i5 = 0;
                    break;
                }
            }
            arrayList.add(i5, pVar);
            p(true);
            return pVar;
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public final int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        int i6;
        Intent intent2;
        int i7;
        PackageManager packageManager = this.f1658a.getPackageManager();
        List<ResolveInfo> queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        if (queryIntentActivityOptions != null) {
            i6 = queryIntentActivityOptions.size();
        } else {
            i6 = 0;
        }
        if ((i5 & 1) == 0) {
            removeGroup(i2);
        }
        for (int i8 = 0; i8 < i6; i8++) {
            ResolveInfo resolveInfo = queryIntentActivityOptions.get(i8);
            int i9 = resolveInfo.specificIndex;
            if (i9 < 0) {
                intent2 = intent;
            } else {
                intent2 = intentArr[i9];
            }
            Intent intent3 = new Intent(intent2);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent3.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            p a2 = a(i2, i3, i4, resolveInfo.loadLabel(packageManager));
            a2.setIcon(resolveInfo.loadIcon(packageManager));
            a2.f1693g = intent3;
            if (menuItemArr != null && (i7 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i7] = a2;
            }
        }
        return i6;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public final void b(z zVar, Context context) {
        this.f1677u.add(new WeakReference(zVar));
        zVar.f(context, this);
        this.f1667k = true;
    }

    public final void c(boolean z2) {
        if (this.f1675s) {
            return;
        }
        this.f1675s = true;
        CopyOnWriteArrayList copyOnWriteArrayList = this.f1677u;
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            z zVar = (z) weakReference.get();
            if (zVar == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                zVar.a(this, z2);
            }
        }
        this.f1675s = false;
    }

    @Override // android.view.Menu
    public final void clear() {
        p pVar = this.f1678v;
        if (pVar != null) {
            d(pVar);
        }
        this.f1663f.clear();
        p(true);
    }

    public final void clearHeader() {
        this.f1670n = null;
        this.f1669m = null;
        this.f1671o = null;
        p(false);
    }

    @Override // android.view.Menu
    public final void close() {
        c(true);
    }

    public boolean d(p pVar) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.f1677u;
        boolean z2 = false;
        if (!copyOnWriteArrayList.isEmpty() && this.f1678v == pVar) {
            w();
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                z zVar = (z) weakReference.get();
                if (zVar == null) {
                    copyOnWriteArrayList.remove(weakReference);
                } else {
                    z2 = zVar.d(pVar);
                    if (z2) {
                        break;
                    }
                }
            }
            v();
            if (z2) {
                this.f1678v = null;
            }
        }
        return z2;
    }

    public boolean e(n nVar, MenuItem menuItem) {
        l lVar = this.f1662e;
        if (lVar != null && lVar.m(nVar, menuItem)) {
            return true;
        }
        return false;
    }

    public boolean f(p pVar) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.f1677u;
        boolean z2 = false;
        if (copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        w();
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            z zVar = (z) weakReference.get();
            if (zVar == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                z2 = zVar.k(pVar);
                if (z2) {
                    break;
                }
            }
        }
        v();
        if (z2) {
            this.f1678v = pVar;
        }
        return z2;
    }

    @Override // android.view.Menu
    public final MenuItem findItem(int i2) {
        MenuItem findItem;
        ArrayList arrayList = this.f1663f;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            p pVar = (p) arrayList.get(i3);
            if (pVar.f1687a == i2) {
                return pVar;
            }
            if (pVar.hasSubMenu() && (findItem = pVar.f1700o.findItem(i2)) != null) {
                return findItem;
            }
        }
        return null;
    }

    public final p g(int i2, KeyEvent keyEvent) {
        char c2;
        ArrayList arrayList = this.f1676t;
        arrayList.clear();
        h(arrayList, i2, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return (p) arrayList.get(0);
        }
        boolean n2 = n();
        for (int i3 = 0; i3 < size; i3++) {
            p pVar = (p) arrayList.get(i3);
            if (n2) {
                c2 = pVar.f1695j;
            } else {
                c2 = pVar.h;
            }
            char[] cArr = keyData.meta;
            if ((c2 == cArr[0] && (metaState & 2) == 0) || ((c2 == cArr[2] && (metaState & 2) != 0) || (n2 && c2 == '\b' && i2 == 67))) {
                return pVar;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public final MenuItem getItem(int i2) {
        return (MenuItem) this.f1663f.get(i2);
    }

    public final void h(ArrayList arrayList, int i2, KeyEvent keyEvent) {
        char c2;
        int i3;
        boolean n2 = n();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (!keyEvent.getKeyData(keyData) && i2 != 67) {
            return;
        }
        ArrayList arrayList2 = this.f1663f;
        int size = arrayList2.size();
        for (int i4 = 0; i4 < size; i4++) {
            p pVar = (p) arrayList2.get(i4);
            if (pVar.hasSubMenu()) {
                pVar.f1700o.h(arrayList, i2, keyEvent);
            }
            if (n2) {
                c2 = pVar.f1695j;
            } else {
                c2 = pVar.h;
            }
            if (n2) {
                i3 = pVar.f1696k;
            } else {
                i3 = pVar.f1694i;
            }
            if ((modifiers & 69647) == (i3 & 69647) && c2 != 0) {
                char[] cArr = keyData.meta;
                if (c2 != cArr[0] && c2 != cArr[2]) {
                    if (n2 && c2 == '\b') {
                        if (i2 != 67) {
                        }
                    }
                }
                if (pVar.isEnabled()) {
                    arrayList.add(pVar);
                }
            }
        }
    }

    @Override // android.view.Menu
    public final boolean hasVisibleItems() {
        if (this.f1680x) {
            return true;
        }
        ArrayList arrayList = this.f1663f;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((p) arrayList.get(i2)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public final void i() {
        ArrayList l2 = l();
        if (!this.f1667k) {
            return;
        }
        CopyOnWriteArrayList copyOnWriteArrayList = this.f1677u;
        Iterator it = copyOnWriteArrayList.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            z zVar = (z) weakReference.get();
            if (zVar == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                z2 |= zVar.g();
            }
        }
        ArrayList arrayList = this.f1665i;
        ArrayList arrayList2 = this.f1666j;
        if (z2) {
            arrayList.clear();
            arrayList2.clear();
            int size = l2.size();
            for (int i2 = 0; i2 < size; i2++) {
                p pVar = (p) l2.get(i2);
                if (pVar.f()) {
                    arrayList.add(pVar);
                } else {
                    arrayList2.add(pVar);
                }
            }
        } else {
            arrayList.clear();
            arrayList2.clear();
            arrayList2.addAll(l());
        }
        this.f1667k = false;
    }

    @Override // android.view.Menu
    public final boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        if (g(i2, keyEvent) != null) {
            return true;
        }
        return false;
    }

    public String j() {
        return "android:menu:actionviewstates";
    }

    public final ArrayList l() {
        boolean z2 = this.h;
        ArrayList arrayList = this.f1664g;
        if (!z2) {
            return arrayList;
        }
        arrayList.clear();
        ArrayList arrayList2 = this.f1663f;
        int size = arrayList2.size();
        for (int i2 = 0; i2 < size; i2++) {
            p pVar = (p) arrayList2.get(i2);
            if (pVar.isVisible()) {
                arrayList.add(pVar);
            }
        }
        this.h = false;
        this.f1667k = true;
        return arrayList;
    }

    public boolean m() {
        return this.f1679w;
    }

    public boolean n() {
        return this.f1660c;
    }

    public boolean o() {
        return this.f1661d;
    }

    public final void p(boolean z2) {
        if (!this.f1672p) {
            if (z2) {
                this.h = true;
                this.f1667k = true;
            }
            CopyOnWriteArrayList copyOnWriteArrayList = this.f1677u;
            if (!copyOnWriteArrayList.isEmpty()) {
                w();
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    WeakReference weakReference = (WeakReference) it.next();
                    z zVar = (z) weakReference.get();
                    if (zVar == null) {
                        copyOnWriteArrayList.remove(weakReference);
                    } else {
                        zVar.c();
                    }
                }
                v();
                return;
            }
            return;
        }
        this.f1673q = true;
        if (z2) {
            this.f1674r = true;
        }
    }

    @Override // android.view.Menu
    public final boolean performIdentifierAction(int i2, int i3) {
        return q(findItem(i2), null, i3);
    }

    @Override // android.view.Menu
    public final boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        boolean z2;
        p g2 = g(i2, keyEvent);
        if (g2 != null) {
            z2 = q(g2, null, i3);
        } else {
            z2 = false;
        }
        if ((i3 & 2) != 0) {
            c(true);
        }
        return z2;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean q(android.view.MenuItem r7, j.z r8, int r9) {
        /*
            r6 = this;
            j.p r7 = (j.p) r7
            r0 = 0
            if (r7 == 0) goto Ld4
            boolean r1 = r7.isEnabled()
            if (r1 != 0) goto Ld
            goto Ld4
        Ld:
            android.view.MenuItem$OnMenuItemClickListener r1 = r7.f1701p
            r2 = 1
            if (r1 == 0) goto L1a
            boolean r1 = r1.onMenuItemClick(r7)
            if (r1 == 0) goto L1a
        L18:
            r1 = r2
            goto L43
        L1a:
            j.n r1 = r7.f1699n
            boolean r3 = r1.e(r1, r7)
            if (r3 == 0) goto L23
            goto L18
        L23:
            android.content.Intent r3 = r7.f1693g
            if (r3 == 0) goto L35
            android.content.Context r1 = r1.f1658a     // Catch: android.content.ActivityNotFoundException -> L2d
            r1.startActivity(r3)     // Catch: android.content.ActivityNotFoundException -> L2d
            goto L18
        L2d:
            r1 = move-exception
            java.lang.String r3 = "MenuItemImpl"
            java.lang.String r4 = "Can't find activity to handle intent; ignoring"
            android.util.Log.e(r3, r4, r1)
        L35:
            j.q r1 = r7.f1684A
            if (r1 == 0) goto L42
            android.view.ActionProvider r1 = r1.f1712b
            boolean r1 = r1.onPerformDefaultAction()
            if (r1 == 0) goto L42
            goto L18
        L42:
            r1 = r0
        L43:
            j.q r3 = r7.f1684A
            if (r3 == 0) goto L51
            android.view.ActionProvider r4 = r3.f1712b
            boolean r4 = r4.hasSubMenu()
            if (r4 == 0) goto L51
            r4 = r2
            goto L52
        L51:
            r4 = r0
        L52:
            boolean r5 = r7.e()
            if (r5 == 0) goto L64
            boolean r7 = r7.expandActionView()
            r1 = r1 | r7
            if (r1 == 0) goto Ld3
            r6.c(r2)
            goto Ld3
        L64:
            boolean r5 = r7.hasSubMenu()
            if (r5 != 0) goto L75
            if (r4 == 0) goto L6d
            goto L75
        L6d:
            r7 = r9 & 1
            if (r7 != 0) goto Ld3
            r6.c(r2)
            goto Ld3
        L75:
            r9 = r9 & 4
            if (r9 != 0) goto L7c
            r6.c(r0)
        L7c:
            boolean r9 = r7.hasSubMenu()
            if (r9 != 0) goto L90
            j.F r9 = new j.F
            android.content.Context r5 = r6.f1658a
            r9.<init>(r5, r6, r7)
            r7.f1700o = r9
            java.lang.CharSequence r5 = r7.f1691e
            r9.setHeaderTitle(r5)
        L90:
            j.F r7 = r7.f1700o
            if (r4 == 0) goto L9b
            j.u r9 = r3.f1713c
            android.view.ActionProvider r9 = r3.f1712b
            r9.onPrepareSubMenu(r7)
        L9b:
            java.util.concurrent.CopyOnWriteArrayList r9 = r6.f1677u
            boolean r3 = r9.isEmpty()
            if (r3 == 0) goto La4
            goto Lcd
        La4:
            if (r8 == 0) goto Laa
            boolean r0 = r8.j(r7)
        Laa:
            java.util.Iterator r8 = r9.iterator()
        Lae:
            boolean r3 = r8.hasNext()
            if (r3 == 0) goto Lcd
            java.lang.Object r3 = r8.next()
            java.lang.ref.WeakReference r3 = (java.lang.ref.WeakReference) r3
            java.lang.Object r4 = r3.get()
            j.z r4 = (j.z) r4
            if (r4 != 0) goto Lc6
            r9.remove(r3)
            goto Lae
        Lc6:
            if (r0 != 0) goto Lae
            boolean r0 = r4.j(r7)
            goto Lae
        Lcd:
            r1 = r1 | r0
            if (r1 != 0) goto Ld3
            r6.c(r2)
        Ld3:
            return r1
        Ld4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: j.n.q(android.view.MenuItem, j.z, int):boolean");
    }

    public final void r(z zVar) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.f1677u;
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            z zVar2 = (z) weakReference.get();
            if (zVar2 == null || zVar2 == zVar) {
                copyOnWriteArrayList.remove(weakReference);
            }
        }
    }

    @Override // android.view.Menu
    public final void removeGroup(int i2) {
        ArrayList arrayList = this.f1663f;
        int size = arrayList.size();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 < size) {
                if (((p) arrayList.get(i4)).f1688b == i2) {
                    break;
                } else {
                    i4++;
                }
            } else {
                i4 = -1;
                break;
            }
        }
        if (i4 >= 0) {
            int size2 = arrayList.size() - i4;
            while (true) {
                int i5 = i3 + 1;
                if (i3 >= size2 || ((p) arrayList.get(i4)).f1688b != i2) {
                    break;
                }
                if (i4 >= 0) {
                    ArrayList arrayList2 = this.f1663f;
                    if (i4 < arrayList2.size()) {
                        arrayList2.remove(i4);
                    }
                }
                i3 = i5;
            }
            p(true);
        }
    }

    @Override // android.view.Menu
    public final void removeItem(int i2) {
        ArrayList arrayList = this.f1663f;
        int size = arrayList.size();
        int i3 = 0;
        while (true) {
            if (i3 < size) {
                if (((p) arrayList.get(i3)).f1687a == i2) {
                    break;
                } else {
                    i3++;
                }
            } else {
                i3 = -1;
                break;
            }
        }
        if (i3 >= 0) {
            ArrayList arrayList2 = this.f1663f;
            if (i3 < arrayList2.size()) {
                arrayList2.remove(i3);
                p(true);
            }
        }
    }

    public final void s(Bundle bundle) {
        MenuItem findItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(j());
        int size = this.f1663f.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((SubMenuC0138F) item.getSubMenu()).s(bundle);
            }
        }
        int i3 = bundle.getInt("android:menu:expandedactionview");
        if (i3 > 0 && (findItem = findItem(i3)) != null) {
            findItem.expandActionView();
        }
    }

    @Override // android.view.Menu
    public final void setGroupCheckable(int i2, boolean z2, boolean z3) {
        int i3;
        ArrayList arrayList = this.f1663f;
        int size = arrayList.size();
        for (int i4 = 0; i4 < size; i4++) {
            p pVar = (p) arrayList.get(i4);
            if (pVar.f1688b == i2) {
                int i5 = pVar.f1709x & (-5);
                if (z3) {
                    i3 = 4;
                } else {
                    i3 = 0;
                }
                pVar.f1709x = i5 | i3;
                pVar.setCheckable(z2);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z2) {
        this.f1679w = z2;
    }

    @Override // android.view.Menu
    public final void setGroupEnabled(int i2, boolean z2) {
        ArrayList arrayList = this.f1663f;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            p pVar = (p) arrayList.get(i3);
            if (pVar.f1688b == i2) {
                pVar.setEnabled(z2);
            }
        }
    }

    @Override // android.view.Menu
    public final void setGroupVisible(int i2, boolean z2) {
        int i3;
        ArrayList arrayList = this.f1663f;
        int size = arrayList.size();
        boolean z3 = false;
        for (int i4 = 0; i4 < size; i4++) {
            p pVar = (p) arrayList.get(i4);
            if (pVar.f1688b == i2) {
                int i5 = pVar.f1709x;
                int i6 = i5 & (-9);
                if (z2) {
                    i3 = 0;
                } else {
                    i3 = 8;
                }
                int i7 = i6 | i3;
                pVar.f1709x = i7;
                if (i5 != i7) {
                    z3 = true;
                }
            }
        }
        if (z3) {
            p(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z2) {
        this.f1660c = z2;
        p(false);
    }

    @Override // android.view.Menu
    public final int size() {
        return this.f1663f.size();
    }

    public final void t(Bundle bundle) {
        int size = this.f1663f.size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((SubMenuC0138F) item.getSubMenu()).t(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(j(), sparseArray);
        }
    }

    public final void u(int i2, CharSequence charSequence, int i3, Drawable drawable, View view) {
        if (view != null) {
            this.f1671o = view;
            this.f1669m = null;
            this.f1670n = null;
        } else {
            if (i2 > 0) {
                this.f1669m = this.f1659b.getText(i2);
            } else if (charSequence != null) {
                this.f1669m = charSequence;
            }
            if (i3 > 0) {
                this.f1670n = this.f1658a.getDrawable(i3);
            } else if (drawable != null) {
                this.f1670n = drawable;
            }
            this.f1671o = null;
        }
        p(false);
    }

    public final void v() {
        this.f1672p = false;
        if (this.f1673q) {
            this.f1673q = false;
            p(this.f1674r);
        }
    }

    public final void w() {
        if (!this.f1672p) {
            this.f1672p = true;
            this.f1673q = false;
            this.f1674r = false;
        }
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2) {
        return a(0, 0, 0, this.f1659b.getString(i2));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2) {
        return addSubMenu(0, 0, 0, this.f1659b.getString(i2));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return a(i2, i3, i4, charSequence);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        p a2 = a(i2, i3, i4, charSequence);
        SubMenuC0138F subMenuC0138F = new SubMenuC0138F(this.f1658a, this, a2);
        a2.f1700o = subMenuC0138F;
        subMenuC0138F.setHeaderTitle(a2.f1691e);
        return subMenuC0138F;
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2, int i3, int i4, int i5) {
        return a(i2, i3, i4, this.f1659b.getString(i5));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return addSubMenu(i2, i3, i4, this.f1659b.getString(i5));
    }

    public n k() {
        return this;
    }
}

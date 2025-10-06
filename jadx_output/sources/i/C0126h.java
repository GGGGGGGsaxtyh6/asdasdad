package i;

import H.AbstractC0022p;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.Log;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import j.p;
import j.q;
import j.u;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* renamed from: i.h, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0126h {

    /* renamed from: A, reason: collision with root package name */
    public CharSequence f1527A;

    /* renamed from: B, reason: collision with root package name */
    public CharSequence f1528B;

    /* renamed from: E, reason: collision with root package name */
    public final /* synthetic */ C0127i f1531E;

    /* renamed from: a, reason: collision with root package name */
    public final Menu f1532a;
    public boolean h;

    /* renamed from: i, reason: collision with root package name */
    public int f1539i;

    /* renamed from: j, reason: collision with root package name */
    public int f1540j;

    /* renamed from: k, reason: collision with root package name */
    public CharSequence f1541k;

    /* renamed from: l, reason: collision with root package name */
    public CharSequence f1542l;

    /* renamed from: m, reason: collision with root package name */
    public int f1543m;

    /* renamed from: n, reason: collision with root package name */
    public char f1544n;

    /* renamed from: o, reason: collision with root package name */
    public int f1545o;

    /* renamed from: p, reason: collision with root package name */
    public char f1546p;

    /* renamed from: q, reason: collision with root package name */
    public int f1547q;

    /* renamed from: r, reason: collision with root package name */
    public int f1548r;

    /* renamed from: s, reason: collision with root package name */
    public boolean f1549s;

    /* renamed from: t, reason: collision with root package name */
    public boolean f1550t;

    /* renamed from: u, reason: collision with root package name */
    public boolean f1551u;

    /* renamed from: v, reason: collision with root package name */
    public int f1552v;

    /* renamed from: w, reason: collision with root package name */
    public int f1553w;

    /* renamed from: x, reason: collision with root package name */
    public String f1554x;
    public String y;

    /* renamed from: z, reason: collision with root package name */
    public q f1555z;

    /* renamed from: C, reason: collision with root package name */
    public ColorStateList f1529C = null;

    /* renamed from: D, reason: collision with root package name */
    public PorterDuff.Mode f1530D = null;

    /* renamed from: b, reason: collision with root package name */
    public int f1533b = 0;

    /* renamed from: c, reason: collision with root package name */
    public int f1534c = 0;

    /* renamed from: d, reason: collision with root package name */
    public int f1535d = 0;

    /* renamed from: e, reason: collision with root package name */
    public int f1536e = 0;

    /* renamed from: f, reason: collision with root package name */
    public boolean f1537f = true;

    /* renamed from: g, reason: collision with root package name */
    public boolean f1538g = true;

    public C0126h(C0127i c0127i, Menu menu) {
        this.f1531E = c0127i;
        this.f1532a = menu;
    }

    public final Object a(String str, Class[] clsArr, Object[] objArr) {
        try {
            Constructor<?> constructor = Class.forName(str, false, this.f1531E.f1560c.getClassLoader()).getConstructor(clsArr);
            constructor.setAccessible(true);
            return constructor.newInstance(objArr);
        } catch (Exception e2) {
            Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e2);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v33, types: [android.view.MenuItem$OnMenuItemClickListener, i.g, java.lang.Object] */
    public final void b(MenuItem menuItem) {
        boolean z2;
        MenuItem enabled = menuItem.setChecked(this.f1549s).setVisible(this.f1550t).setEnabled(this.f1551u);
        boolean z3 = false;
        if (this.f1548r >= 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        enabled.setCheckable(z2).setTitleCondensed(this.f1542l).setIcon(this.f1543m);
        int i2 = this.f1552v;
        if (i2 >= 0) {
            menuItem.setShowAsAction(i2);
        }
        String str = this.y;
        C0127i c0127i = this.f1531E;
        if (str != null) {
            if (!c0127i.f1560c.isRestricted()) {
                if (c0127i.f1561d == null) {
                    c0127i.f1561d = C0127i.a(c0127i.f1560c);
                }
                Object obj = c0127i.f1561d;
                String str2 = this.y;
                ?? obj2 = new Object();
                obj2.f1525a = obj;
                Class<?> cls = obj.getClass();
                try {
                    obj2.f1526b = cls.getMethod(str2, MenuItemOnMenuItemClickListenerC0125g.f1524c);
                    menuItem.setOnMenuItemClickListener(obj2);
                } catch (Exception e2) {
                    InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str2 + " in class " + cls.getName());
                    inflateException.initCause(e2);
                    throw inflateException;
                }
            } else {
                throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
            }
        }
        if (this.f1548r >= 2) {
            if (menuItem instanceof p) {
                p pVar = (p) menuItem;
                pVar.f1709x = (pVar.f1709x & (-5)) | 4;
            } else if (menuItem instanceof u) {
                u uVar = (u) menuItem;
                try {
                    Method method = uVar.f1720d;
                    C.a aVar = uVar.f1719c;
                    if (method == null) {
                        uVar.f1720d = aVar.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
                    }
                    uVar.f1720d.invoke(aVar, Boolean.TRUE);
                } catch (Exception e3) {
                    Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e3);
                }
            }
        }
        String str3 = this.f1554x;
        if (str3 != null) {
            menuItem.setActionView((View) a(str3, C0127i.f1556e, c0127i.f1558a));
            z3 = true;
        }
        int i3 = this.f1553w;
        if (i3 > 0) {
            if (!z3) {
                menuItem.setActionView(i3);
            } else {
                Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
            }
        }
        q qVar = this.f1555z;
        if (qVar != null) {
            if (menuItem instanceof C.a) {
                ((C.a) menuItem).a(qVar);
            } else {
                Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
            }
        }
        CharSequence charSequence = this.f1527A;
        boolean z4 = menuItem instanceof C.a;
        if (z4) {
            ((C.a) menuItem).setContentDescription(charSequence);
        } else if (Build.VERSION.SDK_INT >= 26) {
            AbstractC0022p.b(menuItem, charSequence);
        }
        CharSequence charSequence2 = this.f1528B;
        if (z4) {
            ((C.a) menuItem).setTooltipText(charSequence2);
        } else if (Build.VERSION.SDK_INT >= 26) {
            AbstractC0022p.f(menuItem, charSequence2);
        }
        char c2 = this.f1544n;
        int i4 = this.f1545o;
        if (z4) {
            ((C.a) menuItem).setAlphabeticShortcut(c2, i4);
        } else if (Build.VERSION.SDK_INT >= 26) {
            AbstractC0022p.a(menuItem, c2, i4);
        }
        char c3 = this.f1546p;
        int i5 = this.f1547q;
        if (z4) {
            ((C.a) menuItem).setNumericShortcut(c3, i5);
        } else if (Build.VERSION.SDK_INT >= 26) {
            AbstractC0022p.e(menuItem, c3, i5);
        }
        PorterDuff.Mode mode = this.f1530D;
        if (mode != null) {
            if (z4) {
                ((C.a) menuItem).setIconTintMode(mode);
            } else if (Build.VERSION.SDK_INT >= 26) {
                AbstractC0022p.d(menuItem, mode);
            }
        }
        ColorStateList colorStateList = this.f1529C;
        if (colorStateList != null) {
            if (z4) {
                ((C.a) menuItem).setIconTintList(colorStateList);
            } else if (Build.VERSION.SDK_INT >= 26) {
                AbstractC0022p.c(menuItem, colorStateList);
            }
        }
    }
}

package g;

import android.content.Context;
import android.content.IntentFilter;
import android.view.MenuItem;

/* loaded from: classes.dex */
public abstract class z {

    /* renamed from: a, reason: collision with root package name */
    public Object f1482a;

    /* renamed from: b, reason: collision with root package name */
    public Object f1483b;

    public z(Context context) {
        this.f1482a = context;
    }

    public void c() {
        y yVar = (y) this.f1482a;
        if (yVar != null) {
            try {
                ((C) this.f1483b).f1338k.unregisterReceiver(yVar);
            } catch (IllegalArgumentException unused) {
            }
            this.f1482a = null;
        }
    }

    public abstract IntentFilter d();

    public abstract int e();

    public MenuItem f(MenuItem menuItem) {
        if (menuItem instanceof C.a) {
            C.a aVar = (C.a) menuItem;
            if (((n.k) this.f1483b) == null) {
                this.f1483b = new n.k(0);
            }
            MenuItem menuItem2 = (MenuItem) ((n.k) this.f1483b).get(aVar);
            if (menuItem2 == null) {
                j.u uVar = new j.u((Context) this.f1482a, aVar);
                ((n.k) this.f1483b).put(aVar, uVar);
                return uVar;
            }
            return menuItem2;
        }
        return menuItem;
    }

    public abstract void g();

    public void h() {
        c();
        IntentFilter d2 = d();
        if (d2.countActions() == 0) {
            return;
        }
        if (((y) this.f1482a) == null) {
            this.f1482a = new y(this);
        }
        ((C) this.f1483b).f1338k.registerReceiver((y) this.f1482a, d2);
    }

    public z(C c2) {
        this.f1483b = c2;
    }
}

package g;

import android.content.Context;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.WeakHashMap;
import k.C0184k;
import k.Z0;
import k.e1;

/* loaded from: classes.dex */
public final class K extends AbstractC0103a {

    /* renamed from: a, reason: collision with root package name */
    public final e1 f1369a;

    /* renamed from: b, reason: collision with root package name */
    public final w f1370b;

    /* renamed from: c, reason: collision with root package name */
    public final I f1371c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f1372d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f1373e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f1374f;

    /* renamed from: g, reason: collision with root package name */
    public final ArrayList f1375g = new ArrayList();
    public final E.b h = new E.b(7, this);

    public K(Toolbar toolbar, CharSequence charSequence, w wVar) {
        I i2 = new I(this);
        e1 e1Var = new e1(toolbar, false);
        this.f1369a = e1Var;
        wVar.getClass();
        this.f1370b = wVar;
        e1Var.f1904k = wVar;
        toolbar.setOnMenuItemClickListener(i2);
        if (!e1Var.f1901g) {
            e1Var.h = charSequence;
            if ((e1Var.f1896b & 8) != 0) {
                Toolbar toolbar2 = e1Var.f1895a;
                toolbar2.setTitle(charSequence);
                if (e1Var.f1901g) {
                    H.N.i(toolbar2.getRootView(), charSequence);
                }
            }
        }
        this.f1371c = new I(this);
    }

    @Override // g.AbstractC0103a
    public final boolean a() {
        C0184k c0184k;
        ActionMenuView actionMenuView = this.f1369a.f1895a.f925a;
        if (actionMenuView != null && (c0184k = actionMenuView.f885t) != null && c0184k.e()) {
            return true;
        }
        return false;
    }

    @Override // g.AbstractC0103a
    public final boolean b() {
        j.p pVar;
        Z0 z02 = this.f1369a.f1895a.f917M;
        if (z02 != null && (pVar = z02.f1872b) != null) {
            if (z02 == null) {
                pVar = null;
            }
            if (pVar != null) {
                pVar.collapseActionView();
                return true;
            }
            return true;
        }
        return false;
    }

    @Override // g.AbstractC0103a
    public final void c(boolean z2) {
        if (z2 == this.f1374f) {
            return;
        }
        this.f1374f = z2;
        ArrayList arrayList = this.f1375g;
        if (arrayList.size() <= 0) {
            return;
        }
        arrayList.get(0).getClass();
        throw new ClassCastException();
    }

    @Override // g.AbstractC0103a
    public final int d() {
        return this.f1369a.f1896b;
    }

    @Override // g.AbstractC0103a
    public final Context e() {
        return this.f1369a.f1895a.getContext();
    }

    @Override // g.AbstractC0103a
    public final boolean f() {
        e1 e1Var = this.f1369a;
        Toolbar toolbar = e1Var.f1895a;
        E.b bVar = this.h;
        toolbar.removeCallbacks(bVar);
        Toolbar toolbar2 = e1Var.f1895a;
        WeakHashMap weakHashMap = H.N.f327a;
        toolbar2.postOnAnimation(bVar);
        return true;
    }

    @Override // g.AbstractC0103a
    public final void h() {
        this.f1369a.f1895a.removeCallbacks(this.h);
    }

    @Override // g.AbstractC0103a
    public final boolean i(int i2, KeyEvent keyEvent) {
        Menu p2 = p();
        if (p2 == null) {
            return false;
        }
        boolean z2 = true;
        if (KeyCharacterMap.load(keyEvent.getDeviceId()).getKeyboardType() == 1) {
            z2 = false;
        }
        p2.setQwertyMode(z2);
        return p2.performShortcut(i2, keyEvent, 0);
    }

    @Override // g.AbstractC0103a
    public final boolean j(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            k();
        }
        return true;
    }

    @Override // g.AbstractC0103a
    public final boolean k() {
        return this.f1369a.f1895a.u();
    }

    @Override // g.AbstractC0103a
    public final void n(CharSequence charSequence) {
        e1 e1Var = this.f1369a;
        if (!e1Var.f1901g) {
            e1Var.h = charSequence;
            if ((e1Var.f1896b & 8) != 0) {
                Toolbar toolbar = e1Var.f1895a;
                toolbar.setTitle(charSequence);
                if (e1Var.f1901g) {
                    H.N.i(toolbar.getRootView(), charSequence);
                }
            }
        }
    }

    public final Menu p() {
        boolean z2 = this.f1373e;
        e1 e1Var = this.f1369a;
        if (!z2) {
            J j2 = new J(this);
            I i2 = new I(this);
            Toolbar toolbar = e1Var.f1895a;
            toolbar.f918N = j2;
            toolbar.f919O = i2;
            ActionMenuView actionMenuView = toolbar.f925a;
            if (actionMenuView != null) {
                actionMenuView.f886u = j2;
                actionMenuView.f887v = i2;
            }
            this.f1373e = true;
        }
        return e1Var.f1895a.getMenu();
    }

    @Override // g.AbstractC0103a
    public final void g() {
    }

    @Override // g.AbstractC0103a
    public final void l(boolean z2) {
    }

    @Override // g.AbstractC0103a
    public final void m(boolean z2) {
    }
}

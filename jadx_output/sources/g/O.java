package g;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import i.AbstractC0120b;
import i.C0127i;
import i.InterfaceC0119a;
import java.lang.ref.WeakReference;
import k.C0184k;

/* loaded from: classes.dex */
public final class O extends AbstractC0120b implements j.l {

    /* renamed from: c, reason: collision with root package name */
    public final Context f1384c;

    /* renamed from: d, reason: collision with root package name */
    public final j.n f1385d;

    /* renamed from: e, reason: collision with root package name */
    public E.c f1386e;

    /* renamed from: f, reason: collision with root package name */
    public WeakReference f1387f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ P f1388g;

    public O(P p2, Context context, E.c cVar) {
        this.f1388g = p2;
        this.f1384c = context;
        this.f1386e = cVar;
        j.n nVar = new j.n(context);
        nVar.f1668l = 1;
        this.f1385d = nVar;
        nVar.f1662e = this;
    }

    @Override // i.AbstractC0120b
    public final void a() {
        P p2 = this.f1388g;
        if (p2.f1397i != this) {
            return;
        }
        if (p2.f1404p) {
            p2.f1398j = this;
            p2.f1399k = this.f1386e;
        } else {
            this.f1386e.d(this);
        }
        this.f1386e = null;
        p2.p(false);
        ActionBarContextView actionBarContextView = p2.f1395f;
        if (actionBarContextView.f841k == null) {
            actionBarContextView.e();
        }
        p2.f1392c.setHideOnContentScrollEnabled(p2.f1409u);
        p2.f1397i = null;
    }

    @Override // i.AbstractC0120b
    public final View b() {
        WeakReference weakReference = this.f1387f;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // i.AbstractC0120b
    public final j.n c() {
        return this.f1385d;
    }

    @Override // i.AbstractC0120b
    public final MenuInflater d() {
        return new C0127i(this.f1384c);
    }

    @Override // i.AbstractC0120b
    public final CharSequence e() {
        return this.f1388g.f1395f.getSubtitle();
    }

    @Override // i.AbstractC0120b
    public final CharSequence f() {
        return this.f1388g.f1395f.getTitle();
    }

    @Override // i.AbstractC0120b
    public final void g() {
        if (this.f1388g.f1397i != this) {
            return;
        }
        j.n nVar = this.f1385d;
        nVar.w();
        try {
            this.f1386e.b(this, nVar);
        } finally {
            nVar.v();
        }
    }

    @Override // i.AbstractC0120b
    public final boolean h() {
        return this.f1388g.f1395f.f849s;
    }

    @Override // i.AbstractC0120b
    public final void i(View view) {
        this.f1388g.f1395f.setCustomView(view);
        this.f1387f = new WeakReference(view);
    }

    @Override // i.AbstractC0120b
    public final void j(int i2) {
        l(this.f1388g.f1390a.getResources().getString(i2));
    }

    @Override // j.l
    public final void k(j.n nVar) {
        if (this.f1386e == null) {
            return;
        }
        g();
        C0184k c0184k = this.f1388g.f1395f.f835d;
        if (c0184k != null) {
            c0184k.l();
        }
    }

    @Override // i.AbstractC0120b
    public final void l(CharSequence charSequence) {
        this.f1388g.f1395f.setSubtitle(charSequence);
    }

    @Override // j.l
    public final boolean m(j.n nVar, MenuItem menuItem) {
        E.c cVar = this.f1386e;
        if (cVar != null) {
            return ((InterfaceC0119a) cVar.f271b).a(this, menuItem);
        }
        return false;
    }

    @Override // i.AbstractC0120b
    public final void n(int i2) {
        o(this.f1388g.f1390a.getResources().getString(i2));
    }

    @Override // i.AbstractC0120b
    public final void o(CharSequence charSequence) {
        this.f1388g.f1395f.setTitle(charSequence);
    }

    @Override // i.AbstractC0120b
    public final void p(boolean z2) {
        this.f1510b = z2;
        this.f1388g.f1395f.setTitleOptional(z2);
    }
}

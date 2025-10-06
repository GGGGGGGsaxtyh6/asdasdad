package i;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;
import k.C0184k;

/* renamed from: i.e, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0123e extends AbstractC0120b implements j.l {

    /* renamed from: c, reason: collision with root package name */
    public Context f1517c;

    /* renamed from: d, reason: collision with root package name */
    public ActionBarContextView f1518d;

    /* renamed from: e, reason: collision with root package name */
    public E.c f1519e;

    /* renamed from: f, reason: collision with root package name */
    public WeakReference f1520f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f1521g;
    public j.n h;

    @Override // i.AbstractC0120b
    public final void a() {
        if (this.f1521g) {
            return;
        }
        this.f1521g = true;
        this.f1519e.d(this);
    }

    @Override // i.AbstractC0120b
    public final View b() {
        WeakReference weakReference = this.f1520f;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // i.AbstractC0120b
    public final j.n c() {
        return this.h;
    }

    @Override // i.AbstractC0120b
    public final MenuInflater d() {
        return new C0127i(this.f1518d.getContext());
    }

    @Override // i.AbstractC0120b
    public final CharSequence e() {
        return this.f1518d.getSubtitle();
    }

    @Override // i.AbstractC0120b
    public final CharSequence f() {
        return this.f1518d.getTitle();
    }

    @Override // i.AbstractC0120b
    public final void g() {
        this.f1519e.b(this, this.h);
    }

    @Override // i.AbstractC0120b
    public final boolean h() {
        return this.f1518d.f849s;
    }

    @Override // i.AbstractC0120b
    public final void i(View view) {
        WeakReference weakReference;
        this.f1518d.setCustomView(view);
        if (view != null) {
            weakReference = new WeakReference(view);
        } else {
            weakReference = null;
        }
        this.f1520f = weakReference;
    }

    @Override // i.AbstractC0120b
    public final void j(int i2) {
        l(this.f1517c.getString(i2));
    }

    @Override // j.l
    public final void k(j.n nVar) {
        g();
        C0184k c0184k = this.f1518d.f835d;
        if (c0184k != null) {
            c0184k.l();
        }
    }

    @Override // i.AbstractC0120b
    public final void l(CharSequence charSequence) {
        this.f1518d.setSubtitle(charSequence);
    }

    @Override // j.l
    public final boolean m(j.n nVar, MenuItem menuItem) {
        return ((InterfaceC0119a) this.f1519e.f271b).a(this, menuItem);
    }

    @Override // i.AbstractC0120b
    public final void n(int i2) {
        o(this.f1517c.getString(i2));
    }

    @Override // i.AbstractC0120b
    public final void o(CharSequence charSequence) {
        this.f1518d.setTitle(charSequence);
    }

    @Override // i.AbstractC0120b
    public final void p(boolean z2) {
        this.f1510b = z2;
        this.f1518d.setTitleOptional(z2);
    }
}

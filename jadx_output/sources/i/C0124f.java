package i;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import j.MenuC0135C;

/* renamed from: i.f, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0124f extends ActionMode {

    /* renamed from: a, reason: collision with root package name */
    public final Context f1522a;

    /* renamed from: b, reason: collision with root package name */
    public final AbstractC0120b f1523b;

    public C0124f(Context context, AbstractC0120b abstractC0120b) {
        this.f1522a = context;
        this.f1523b = abstractC0120b;
    }

    @Override // android.view.ActionMode
    public final void finish() {
        this.f1523b.a();
    }

    @Override // android.view.ActionMode
    public final View getCustomView() {
        return this.f1523b.b();
    }

    @Override // android.view.ActionMode
    public final Menu getMenu() {
        return new MenuC0135C(this.f1522a, this.f1523b.c());
    }

    @Override // android.view.ActionMode
    public final MenuInflater getMenuInflater() {
        return this.f1523b.d();
    }

    @Override // android.view.ActionMode
    public final CharSequence getSubtitle() {
        return this.f1523b.e();
    }

    @Override // android.view.ActionMode
    public final Object getTag() {
        return this.f1523b.f1509a;
    }

    @Override // android.view.ActionMode
    public final CharSequence getTitle() {
        return this.f1523b.f();
    }

    @Override // android.view.ActionMode
    public final boolean getTitleOptionalHint() {
        return this.f1523b.f1510b;
    }

    @Override // android.view.ActionMode
    public final void invalidate() {
        this.f1523b.g();
    }

    @Override // android.view.ActionMode
    public final boolean isTitleOptional() {
        return this.f1523b.h();
    }

    @Override // android.view.ActionMode
    public final void setCustomView(View view) {
        this.f1523b.i(view);
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(CharSequence charSequence) {
        this.f1523b.l(charSequence);
    }

    @Override // android.view.ActionMode
    public final void setTag(Object obj) {
        this.f1523b.f1509a = obj;
    }

    @Override // android.view.ActionMode
    public final void setTitle(CharSequence charSequence) {
        this.f1523b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public final void setTitleOptionalHint(boolean z2) {
        this.f1523b.p(z2);
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(int i2) {
        this.f1523b.j(i2);
    }

    @Override // android.view.ActionMode
    public final void setTitle(int i2) {
        this.f1523b.n(i2);
    }
}

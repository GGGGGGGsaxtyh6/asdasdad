package k;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/* renamed from: k.j0, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0183j0 extends ToggleButton {

    /* renamed from: a, reason: collision with root package name */
    public final C0194p f1947a;

    /* renamed from: b, reason: collision with root package name */
    public final Z f1948b;

    /* renamed from: c, reason: collision with root package name */
    public C0209x f1949c;

    public C0183j0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.buttonStyleToggle);
        S0.a(this, getContext());
        C0194p c0194p = new C0194p(this);
        this.f1947a = c0194p;
        c0194p.d(attributeSet, R.attr.buttonStyleToggle);
        Z z2 = new Z(this);
        this.f1948b = z2;
        z2.f(attributeSet, R.attr.buttonStyleToggle);
        getEmojiTextViewHelper().a(attributeSet, R.attr.buttonStyleToggle);
    }

    private C0209x getEmojiTextViewHelper() {
        if (this.f1949c == null) {
            this.f1949c = new C0209x(this);
        }
        return this.f1949c;
    }

    @Override // android.widget.ToggleButton, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0194p c0194p = this.f1947a;
        if (c0194p != null) {
            c0194p.a();
        }
        Z z2 = this.f1948b;
        if (z2 != null) {
            z2.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0194p c0194p = this.f1947a;
        if (c0194p != null) {
            return c0194p.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0194p c0194p = this.f1947a;
        if (c0194p != null) {
            return c0194p.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1948b.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1948b.e();
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z2) {
        super.setAllCaps(z2);
        getEmojiTextViewHelper().b(z2);
    }

    @Override // android.widget.ToggleButton, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0194p c0194p = this.f1947a;
        if (c0194p != null) {
            c0194p.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        C0194p c0194p = this.f1947a;
        if (c0194p != null) {
            c0194p.f(i2);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        Z z2 = this.f1948b;
        if (z2 != null) {
            z2.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        Z z2 = this.f1948b;
        if (z2 != null) {
            z2.b();
        }
    }

    public void setEmojiCompatEnabled(boolean z2) {
        getEmojiTextViewHelper().c(z2);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(((C0.d) getEmojiTextViewHelper().f2050b.f8b).w(inputFilterArr));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0194p c0194p = this.f1947a;
        if (c0194p != null) {
            c0194p.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0194p c0194p = this.f1947a;
        if (c0194p != null) {
            c0194p.i(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        Z z2 = this.f1948b;
        z2.l(colorStateList);
        z2.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        Z z2 = this.f1948b;
        z2.m(mode);
        z2.b();
    }
}

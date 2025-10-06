package k;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.CheckBox;
import org.conscrypt.R;

/* loaded from: classes.dex */
public final class r extends CheckBox {

    /* renamed from: a, reason: collision with root package name */
    public final C0201t f2001a;

    /* renamed from: b, reason: collision with root package name */
    public final C0194p f2002b;

    /* renamed from: c, reason: collision with root package name */
    public final Z f2003c;

    /* renamed from: d, reason: collision with root package name */
    public C0209x f2004d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.checkboxStyle);
        T0.a(context);
        S0.a(this, getContext());
        C0201t c0201t = new C0201t(this);
        this.f2001a = c0201t;
        c0201t.c(attributeSet, R.attr.checkboxStyle);
        C0194p c0194p = new C0194p(this);
        this.f2002b = c0194p;
        c0194p.d(attributeSet, R.attr.checkboxStyle);
        Z z2 = new Z(this);
        this.f2003c = z2;
        z2.f(attributeSet, R.attr.checkboxStyle);
        getEmojiTextViewHelper().a(attributeSet, R.attr.checkboxStyle);
    }

    private C0209x getEmojiTextViewHelper() {
        if (this.f2004d == null) {
            this.f2004d = new C0209x(this);
        }
        return this.f2004d;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0194p c0194p = this.f2002b;
        if (c0194p != null) {
            c0194p.a();
        }
        Z z2 = this.f2003c;
        if (z2 != null) {
            z2.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0194p c0194p = this.f2002b;
        if (c0194p != null) {
            return c0194p.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0194p c0194p = this.f2002b;
        if (c0194p != null) {
            return c0194p.c();
        }
        return null;
    }

    public ColorStateList getSupportButtonTintList() {
        C0201t c0201t = this.f2001a;
        if (c0201t != null) {
            return c0201t.f2012a;
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        C0201t c0201t = this.f2001a;
        if (c0201t != null) {
            return c0201t.f2013b;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f2003c.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f2003c.e();
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z2) {
        super.setAllCaps(z2);
        getEmojiTextViewHelper().b(z2);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0194p c0194p = this.f2002b;
        if (c0194p != null) {
            c0194p.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        C0194p c0194p = this.f2002b;
        if (c0194p != null) {
            c0194p.f(i2);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        C0201t c0201t = this.f2001a;
        if (c0201t != null) {
            if (c0201t.f2016e) {
                c0201t.f2016e = false;
            } else {
                c0201t.f2016e = true;
                c0201t.a();
            }
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        Z z2 = this.f2003c;
        if (z2 != null) {
            z2.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        Z z2 = this.f2003c;
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
        C0194p c0194p = this.f2002b;
        if (c0194p != null) {
            c0194p.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0194p c0194p = this.f2002b;
        if (c0194p != null) {
            c0194p.i(mode);
        }
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        C0201t c0201t = this.f2001a;
        if (c0201t != null) {
            c0201t.f2012a = colorStateList;
            c0201t.f2014c = true;
            c0201t.a();
        }
    }

    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        C0201t c0201t = this.f2001a;
        if (c0201t != null) {
            c0201t.f2013b = mode;
            c0201t.f2015d = true;
            c0201t.a();
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        Z z2 = this.f2003c;
        z2.l(colorStateList);
        z2.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        Z z2 = this.f2003c;
        z2.m(mode);
        z2.b();
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i2) {
        setButtonDrawable(C0.d.v(getContext(), i2));
    }
}

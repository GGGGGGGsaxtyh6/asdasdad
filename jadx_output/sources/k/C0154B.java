package k;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;

/* renamed from: k.B, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0154B extends MultiAutoCompleteTextView {

    /* renamed from: d, reason: collision with root package name */
    public static final int[] f1744d = {R.attr.popupBackground};

    /* renamed from: a, reason: collision with root package name */
    public final C0194p f1745a;

    /* renamed from: b, reason: collision with root package name */
    public final Z f1746b;

    /* renamed from: c, reason: collision with root package name */
    public final C0156D f1747c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0154B(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, org.conscrypt.R.attr.autoCompleteTextViewStyle);
        T0.a(context);
        S0.a(this, getContext());
        D0.h p2 = D0.h.p(getContext(), attributeSet, f1744d, org.conscrypt.R.attr.autoCompleteTextViewStyle);
        if (((TypedArray) p2.f259c).hasValue(0)) {
            setDropDownBackgroundDrawable(p2.j(0));
        }
        p2.r();
        C0194p c0194p = new C0194p(this);
        this.f1745a = c0194p;
        c0194p.d(attributeSet, org.conscrypt.R.attr.autoCompleteTextViewStyle);
        Z z2 = new Z(this);
        this.f1746b = z2;
        z2.f(attributeSet, org.conscrypt.R.attr.autoCompleteTextViewStyle);
        z2.b();
        C0156D c0156d = new C0156D(this);
        this.f1747c = c0156d;
        c0156d.b(attributeSet, org.conscrypt.R.attr.autoCompleteTextViewStyle);
        KeyListener keyListener = getKeyListener();
        if (!(keyListener instanceof NumberKeyListener)) {
            boolean isFocusable = isFocusable();
            boolean isClickable = isClickable();
            boolean isLongClickable = isLongClickable();
            int inputType = getInputType();
            KeyListener a2 = c0156d.a(keyListener);
            if (a2 != keyListener) {
                super.setKeyListener(a2);
                setRawInputType(inputType);
                setFocusable(isFocusable);
                setClickable(isClickable);
                setLongClickable(isLongClickable);
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0194p c0194p = this.f1745a;
        if (c0194p != null) {
            c0194p.a();
        }
        Z z2 = this.f1746b;
        if (z2 != null) {
            z2.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0194p c0194p = this.f1745a;
        if (c0194p != null) {
            return c0194p.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0194p c0194p = this.f1745a;
        if (c0194p != null) {
            return c0194p.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1746b.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1746b.e();
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C0.m.m(editorInfo, onCreateInputConnection, this);
        return this.f1747c.c(onCreateInputConnection, editorInfo);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0194p c0194p = this.f1745a;
        if (c0194p != null) {
            c0194p.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        C0194p c0194p = this.f1745a;
        if (c0194p != null) {
            c0194p.f(i2);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        Z z2 = this.f1746b;
        if (z2 != null) {
            z2.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        Z z2 = this.f1746b;
        if (z2 != null) {
            z2.b();
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i2) {
        setDropDownBackgroundDrawable(C0.d.v(getContext(), i2));
    }

    public void setEmojiCompatEnabled(boolean z2) {
        this.f1747c.d(z2);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.f1747c.a(keyListener));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0194p c0194p = this.f1745a;
        if (c0194p != null) {
            c0194p.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0194p c0194p = this.f1745a;
        if (c0194p != null) {
            c0194p.i(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        Z z2 = this.f1746b;
        z2.l(colorStateList);
        z2.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        Z z2 = this.f1746b;
        z2.m(mode);
        z2.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        Z z2 = this.f1746b;
        if (z2 != null) {
            z2.g(context, i2);
        }
    }
}

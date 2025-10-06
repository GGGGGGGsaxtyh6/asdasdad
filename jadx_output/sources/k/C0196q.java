package k;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import org.conscrypt.R;

/* renamed from: k.q, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0196q extends Button {

    /* renamed from: a, reason: collision with root package name */
    public final C0194p f1998a;

    /* renamed from: b, reason: collision with root package name */
    public final Z f1999b;

    /* renamed from: c, reason: collision with root package name */
    public C0209x f2000c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0196q(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.buttonStyle);
        T0.a(context);
        S0.a(this, getContext());
        C0194p c0194p = new C0194p(this);
        this.f1998a = c0194p;
        c0194p.d(attributeSet, R.attr.buttonStyle);
        Z z2 = new Z(this);
        this.f1999b = z2;
        z2.f(attributeSet, R.attr.buttonStyle);
        z2.b();
        getEmojiTextViewHelper().a(attributeSet, R.attr.buttonStyle);
    }

    private C0209x getEmojiTextViewHelper() {
        if (this.f2000c == null) {
            this.f2000c = new C0209x(this);
        }
        return this.f2000c;
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0194p c0194p = this.f1998a;
        if (c0194p != null) {
            c0194p.a();
        }
        Z z2 = this.f1999b;
        if (z2 != null) {
            z2.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (m1.f1981c) {
            return super.getAutoSizeMaxTextSize();
        }
        Z z2 = this.f1999b;
        if (z2 != null) {
            return Math.round(z2.f1866i.f1933e);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (m1.f1981c) {
            return super.getAutoSizeMinTextSize();
        }
        Z z2 = this.f1999b;
        if (z2 != null) {
            return Math.round(z2.f1866i.f1932d);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (m1.f1981c) {
            return super.getAutoSizeStepGranularity();
        }
        Z z2 = this.f1999b;
        if (z2 != null) {
            return Math.round(z2.f1866i.f1931c);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (m1.f1981c) {
            return super.getAutoSizeTextAvailableSizes();
        }
        Z z2 = this.f1999b;
        if (z2 != null) {
            return z2.f1866i.f1934f;
        }
        return new int[0];
    }

    @Override // android.widget.TextView
    public int getAutoSizeTextType() {
        if (m1.f1981c) {
            if (super.getAutoSizeTextType() != 1) {
                return 0;
            }
            return 1;
        }
        Z z2 = this.f1999b;
        if (z2 == null) {
            return 0;
        }
        return z2.f1866i.f1929a;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return C0.f.B(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0194p c0194p = this.f1998a;
        if (c0194p != null) {
            return c0194p.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0194p c0194p = this.f1998a;
        if (c0194p != null) {
            return c0194p.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1999b.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1999b.e();
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        Z z3 = this.f1999b;
        if (z3 != null && !m1.f1981c) {
            z3.f1866i.a();
        }
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        Z z2 = this.f1999b;
        if (z2 != null && !m1.f1981c) {
            C0181i0 c0181i0 = z2.f1866i;
            if (c0181i0.f()) {
                c0181i0.a();
            }
        }
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z2) {
        super.setAllCaps(z2);
        getEmojiTextViewHelper().b(z2);
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i2, int i3, int i4, int i5) {
        if (m1.f1981c) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
            return;
        }
        Z z2 = this.f1999b;
        if (z2 != null) {
            z2.i(i2, i3, i4, i5);
        }
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i2) {
        if (m1.f1981c) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
            return;
        }
        Z z2 = this.f1999b;
        if (z2 != null) {
            z2.j(iArr, i2);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i2) {
        if (m1.f1981c) {
            super.setAutoSizeTextTypeWithDefaults(i2);
            return;
        }
        Z z2 = this.f1999b;
        if (z2 != null) {
            z2.k(i2);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0194p c0194p = this.f1998a;
        if (c0194p != null) {
            c0194p.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        C0194p c0194p = this.f1998a;
        if (c0194p != null) {
            c0194p.f(i2);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C0.f.C(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z2) {
        getEmojiTextViewHelper().c(z2);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(((C0.d) getEmojiTextViewHelper().f2050b.f8b).w(inputFilterArr));
    }

    public void setSupportAllCaps(boolean z2) {
        Z z3 = this.f1999b;
        if (z3 != null) {
            z3.f1859a.setAllCaps(z2);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0194p c0194p = this.f1998a;
        if (c0194p != null) {
            c0194p.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0194p c0194p = this.f1998a;
        if (c0194p != null) {
            c0194p.i(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        Z z2 = this.f1999b;
        z2.l(colorStateList);
        z2.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        Z z2 = this.f1999b;
        z2.m(mode);
        z2.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        Z z2 = this.f1999b;
        if (z2 != null) {
            z2.g(context, i2);
        }
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i2, float f2) {
        boolean z2 = m1.f1981c;
        if (z2) {
            super.setTextSize(i2, f2);
            return;
        }
        Z z3 = this.f1999b;
        if (z3 != null && !z2) {
            C0181i0 c0181i0 = z3.f1866i;
            if (!c0181i0.f()) {
                c0181i0.g(i2, f2);
            }
        }
    }
}

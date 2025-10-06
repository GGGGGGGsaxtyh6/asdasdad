package k;

import H.AbstractC0029x;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: k.d0, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0171d0 extends TextView {

    /* renamed from: a, reason: collision with root package name */
    public final C0194p f1886a;

    /* renamed from: b, reason: collision with root package name */
    public final Z f1887b;

    /* renamed from: c, reason: collision with root package name */
    public final C0156D f1888c;

    /* renamed from: d, reason: collision with root package name */
    public C0209x f1889d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f1890e;

    /* renamed from: f, reason: collision with root package name */
    public A.f f1891f;

    /* renamed from: g, reason: collision with root package name */
    public Future f1892g;

    public C0171d0(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    private C0209x getEmojiTextViewHelper() {
        if (this.f1889d == null) {
            this.f1889d = new C0209x(this);
        }
        return this.f1889d;
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0194p c0194p = this.f1886a;
        if (c0194p != null) {
            c0194p.a();
        }
        Z z2 = this.f1887b;
        if (z2 != null) {
            z2.b();
        }
    }

    public final void g() {
        Future future = this.f1892g;
        if (future != null) {
            try {
                this.f1892g = null;
                if (future.get() == null) {
                    if (Build.VERSION.SDK_INT >= 29) {
                        throw null;
                    }
                    C0.f.t(this);
                    throw null;
                }
                throw new ClassCastException();
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (m1.f1981c) {
            return super.getAutoSizeMaxTextSize();
        }
        Z z2 = this.f1887b;
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
        Z z2 = this.f1887b;
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
        Z z2 = this.f1887b;
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
        Z z2 = this.f1887b;
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
        Z z2 = this.f1887b;
        if (z2 == null) {
            return 0;
        }
        return z2.f1866i.f1929a;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return C0.f.B(super.getCustomSelectionActionModeCallback());
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public InterfaceC0165a0 getSuperCaller() {
        if (this.f1891f == null) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 34) {
                this.f1891f = new C0169c0(this);
            } else if (i2 >= 28) {
                this.f1891f = new C0167b0(this);
            } else if (i2 >= 26) {
                this.f1891f = new A.f(26, this);
            }
        }
        return this.f1891f;
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0194p c0194p = this.f1886a;
        if (c0194p != null) {
            return c0194p.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0194p c0194p = this.f1886a;
        if (c0194p != null) {
            return c0194p.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1887b.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1887b.e();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        g();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        C0156D c0156d;
        if (Build.VERSION.SDK_INT < 28 && (c0156d = this.f1888c) != null) {
            TextClassifier textClassifier = (TextClassifier) c0156d.f1753c;
            if (textClassifier == null) {
                return T.a((TextView) c0156d.f1752b);
            }
            return textClassifier;
        }
        return super.getTextClassifier();
    }

    public F.c getTextMetricsParamsCompat() {
        return C0.f.t(this);
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.f1887b.getClass();
        Z.h(editorInfo, onCreateInputConnection, this);
        C0.m.m(editorInfo, onCreateInputConnection, this);
        return onCreateInputConnection;
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30 && i2 < 33 && onCheckIsTextEditor()) {
            ((InputMethodManager) getContext().getSystemService("input_method")).isActive(this);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        Z z3 = this.f1887b;
        if (z3 != null && !m1.f1981c) {
            z3.f1866i.a();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        g();
        super.onMeasure(i2, i3);
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        Z z2 = this.f1887b;
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
        Z z2 = this.f1887b;
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
        Z z2 = this.f1887b;
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
        Z z2 = this.f1887b;
        if (z2 != null) {
            z2.k(i2);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0194p c0194p = this.f1886a;
        if (c0194p != null) {
            c0194p.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        C0194p c0194p = this.f1886a;
        if (c0194p != null) {
            c0194p.f(i2);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        Z z2 = this.f1887b;
        if (z2 != null) {
            z2.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        Z z2 = this.f1887b;
        if (z2 != null) {
            z2.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        Z z2 = this.f1887b;
        if (z2 != null) {
            z2.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        Z z2 = this.f1887b;
        if (z2 != null) {
            z2.b();
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

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            getSuperCaller().d(i2);
        } else {
            C0.f.y(this, i2);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            getSuperCaller().x(i2);
        } else {
            C0.f.z(this, i2);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i2) {
        C0.f.A(this, i2);
    }

    public void setPrecomputedText(F.d dVar) {
        if (Build.VERSION.SDK_INT >= 29) {
            throw null;
        }
        C0.f.t(this);
        throw null;
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0194p c0194p = this.f1886a;
        if (c0194p != null) {
            c0194p.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0194p c0194p = this.f1886a;
        if (c0194p != null) {
            c0194p.i(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        Z z2 = this.f1887b;
        z2.l(colorStateList);
        z2.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        Z z2 = this.f1887b;
        z2.m(mode);
        z2.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        Z z2 = this.f1887b;
        if (z2 != null) {
            z2.g(context, i2);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        C0156D c0156d;
        if (Build.VERSION.SDK_INT < 28 && (c0156d = this.f1888c) != null) {
            c0156d.f1753c = textClassifier;
        } else {
            super.setTextClassifier(textClassifier);
        }
    }

    public void setTextFuture(Future<F.d> future) {
        this.f1892g = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(F.c cVar) {
        TextDirectionHeuristic textDirectionHeuristic;
        TextDirectionHeuristic textDirectionHeuristic2 = cVar.f308b;
        TextDirectionHeuristic textDirectionHeuristic3 = TextDirectionHeuristics.FIRSTSTRONG_RTL;
        int i2 = 1;
        if (textDirectionHeuristic2 != textDirectionHeuristic3 && textDirectionHeuristic2 != (textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR)) {
            if (textDirectionHeuristic2 == TextDirectionHeuristics.ANYRTL_LTR) {
                i2 = 2;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.LTR) {
                i2 = 3;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.RTL) {
                i2 = 4;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.LOCALE) {
                i2 = 5;
            } else if (textDirectionHeuristic2 == textDirectionHeuristic) {
                i2 = 6;
            } else if (textDirectionHeuristic2 == textDirectionHeuristic3) {
                i2 = 7;
            }
        }
        setTextDirection(i2);
        getPaint().set(cVar.f307a);
        setBreakStrategy(cVar.f309c);
        setHyphenationFrequency(cVar.f310d);
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i2, float f2) {
        boolean z2 = m1.f1981c;
        if (z2) {
            super.setTextSize(i2, f2);
            return;
        }
        Z z3 = this.f1887b;
        if (z3 != null && !z2) {
            C0181i0 c0181i0 = z3.f1866i;
            if (!c0181i0.f()) {
                c0181i0.g(i2, f2);
            }
        }
    }

    @Override // android.widget.TextView
    public final void setTypeface(Typeface typeface, int i2) {
        Typeface typeface2;
        if (this.f1890e) {
            return;
        }
        if (typeface != null && i2 > 0) {
            Context context = getContext();
            C0.d dVar = A.g.f9a;
            if (context != null) {
                typeface2 = Typeface.create(typeface, i2);
            } else {
                throw new IllegalArgumentException("Context cannot be null");
            }
        } else {
            typeface2 = null;
        }
        this.f1890e = true;
        if (typeface2 != null) {
            typeface = typeface2;
        }
        try {
            super.setTypeface(typeface, i2);
        } finally {
            this.f1890e = false;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0171d0(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        T0.a(context);
        this.f1890e = false;
        this.f1891f = null;
        S0.a(this, getContext());
        C0194p c0194p = new C0194p(this);
        this.f1886a = c0194p;
        c0194p.d(attributeSet, i2);
        Z z2 = new Z(this);
        this.f1887b = z2;
        z2.f(attributeSet, i2);
        z2.b();
        C0156D c0156d = new C0156D();
        c0156d.f1752b = this;
        this.f1888c = c0156d;
        getEmojiTextViewHelper().a(attributeSet, i2);
    }

    @Override // android.widget.TextView
    public final void setLineHeight(int i2, float f2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 34) {
            getSuperCaller().r(i2, f2);
        } else if (i3 >= 34) {
            AbstractC0029x.i(this, i2, f2);
        } else {
            C0.f.A(this, Math.round(TypedValue.applyDimension(i2, f2, getResources().getDisplayMetrics())));
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i2 != 0 ? C0.d.v(context, i2) : null, i3 != 0 ? C0.d.v(context, i3) : null, i4 != 0 ? C0.d.v(context, i4) : null, i5 != 0 ? C0.d.v(context, i5) : null);
        Z z2 = this.f1887b;
        if (z2 != null) {
            z2.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i2 != 0 ? C0.d.v(context, i2) : null, i3 != 0 ? C0.d.v(context, i3) : null, i4 != 0 ? C0.d.v(context, i4) : null, i5 != 0 ? C0.d.v(context, i5) : null);
        Z z2 = this.f1887b;
        if (z2 != null) {
            z2.b();
        }
    }
}

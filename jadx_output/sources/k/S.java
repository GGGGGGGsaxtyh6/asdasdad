package k;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import j.ViewTreeObserverOnGlobalLayoutListenerC0142d;

/* loaded from: classes.dex */
public final class S extends Spinner {

    /* renamed from: i, reason: collision with root package name */
    public static final int[] f1829i = {R.attr.spinnerMode};

    /* renamed from: a, reason: collision with root package name */
    public final C0194p f1830a;

    /* renamed from: b, reason: collision with root package name */
    public final Context f1831b;

    /* renamed from: c, reason: collision with root package name */
    public final C0162J f1832c;

    /* renamed from: d, reason: collision with root package name */
    public SpinnerAdapter f1833d;

    /* renamed from: e, reason: collision with root package name */
    public final boolean f1834e;

    /* renamed from: f, reason: collision with root package name */
    public final Q f1835f;

    /* renamed from: g, reason: collision with root package name */
    public int f1836g;
    public final Rect h;

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0060, code lost:
    
        if (r7 == null) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public S(android.content.Context r13, android.util.AttributeSet r14) {
        /*
            r12 = this;
            r0 = 2130903486(0x7f0301be, float:1.7413791E38)
            r12.<init>(r13, r14, r0)
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r12.h = r1
            android.content.Context r1 = r12.getContext()
            k.S0.a(r12, r1)
            int[] r1 = f.AbstractC0101a.f1281u
            D0.h r2 = D0.h.p(r13, r14, r1, r0)
            k.p r3 = new k.p
            r3.<init>(r12)
            r12.f1830a = r3
            java.lang.Object r3 = r2.f259c
            android.content.res.TypedArray r3 = (android.content.res.TypedArray) r3
            r4 = 4
            r5 = 0
            int r4 = r3.getResourceId(r4, r5)
            if (r4 == 0) goto L35
            i.d r6 = new i.d
            r6.<init>(r13, r4)
            r12.f1831b = r6
            goto L37
        L35:
            r12.f1831b = r13
        L37:
            r4 = -1
            r6 = 0
            int[] r7 = k.S.f1829i     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L57
            android.content.res.TypedArray r7 = r13.obtainStyledAttributes(r14, r7, r0, r5)     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L57
            boolean r8 = r7.hasValue(r5)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4e
            if (r8 == 0) goto L50
            int r4 = r7.getInt(r5, r5)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4e
            goto L50
        L4a:
            r13 = move-exception
            r6 = r7
            goto Ld5
        L4e:
            r8 = move-exception
            goto L59
        L50:
            r7.recycle()
            goto L63
        L54:
            r13 = move-exception
            goto Ld5
        L57:
            r8 = move-exception
            r7 = r6
        L59:
            java.lang.String r9 = "AppCompatSpinner"
            java.lang.String r10 = "Could not read android:spinnerMode"
            android.util.Log.i(r9, r10, r8)     // Catch: java.lang.Throwable -> L4a
            if (r7 == 0) goto L63
            goto L50
        L63:
            r7 = 2
            r8 = 1
            if (r4 == 0) goto L9d
            if (r4 == r8) goto L6a
            goto Laa
        L6a:
            k.O r4 = new k.O
            android.content.Context r9 = r12.f1831b
            r4.<init>(r12, r9, r14)
            android.content.Context r9 = r12.f1831b
            D0.h r1 = D0.h.p(r9, r14, r1, r0)
            r9 = 3
            r10 = -2
            java.lang.Object r11 = r1.f259c
            android.content.res.TypedArray r11 = (android.content.res.TypedArray) r11
            int r9 = r11.getLayoutDimension(r9, r10)
            r12.f1836g = r9
            android.graphics.drawable.Drawable r9 = r1.j(r8)
            r4.k(r9)
            java.lang.String r7 = r3.getString(r7)
            r4.f1816B = r7
            r1.r()
            r12.f1835f = r4
            k.J r1 = new k.J
            r1.<init>(r12, r12, r4)
            r12.f1832c = r1
            goto Laa
        L9d:
            k.L r1 = new k.L
            r1.<init>(r12)
            r12.f1835f = r1
            java.lang.String r4 = r3.getString(r7)
            r1.f1801c = r4
        Laa:
            java.lang.CharSequence[] r1 = r3.getTextArray(r5)
            if (r1 == 0) goto Lc1
            android.widget.ArrayAdapter r3 = new android.widget.ArrayAdapter
            r4 = 17367048(0x1090008, float:2.5162948E-38)
            r3.<init>(r13, r4, r1)
            r13 = 2131427373(0x7f0b002d, float:1.847636E38)
            r3.setDropDownViewResource(r13)
            r12.setAdapter(r3)
        Lc1:
            r2.r()
            r12.f1834e = r8
            android.widget.SpinnerAdapter r13 = r12.f1833d
            if (r13 == 0) goto Lcf
            r12.setAdapter(r13)
            r12.f1833d = r6
        Lcf:
            k.p r13 = r12.f1830a
            r13.d(r14, r0)
            return
        Ld5:
            if (r6 == 0) goto Lda
            r6.recycle()
        Lda:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: k.S.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public final int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i2 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        View view = null;
        int i3 = 0;
        for (int max2 = Math.max(0, max - (15 - (min - max))); max2 < min; max2++) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != i2) {
                view = null;
                i2 = itemViewType;
            }
            view = spinnerAdapter.getView(max2, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i3 = Math.max(i3, view.getMeasuredWidth());
        }
        if (drawable != null) {
            Rect rect = this.h;
            drawable.getPadding(rect);
            return i3 + rect.left + rect.right;
        }
        return i3;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0194p c0194p = this.f1830a;
        if (c0194p != null) {
            c0194p.a();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        Q q2 = this.f1835f;
        if (q2 != null) {
            return q2.d();
        }
        return super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        Q q2 = this.f1835f;
        if (q2 != null) {
            return q2.j();
        }
        return super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        if (this.f1835f != null) {
            return this.f1836g;
        }
        return super.getDropDownWidth();
    }

    public final Q getInternalPopup() {
        return this.f1835f;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        Q q2 = this.f1835f;
        if (q2 != null) {
            return q2.n();
        }
        return super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.f1831b;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        Q q2 = this.f1835f;
        if (q2 != null) {
            return q2.a();
        }
        return super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0194p c0194p = this.f1830a;
        if (c0194p != null) {
            return c0194p.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0194p c0194p = this.f1830a;
        if (c0194p != null) {
            return c0194p.c();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Q q2 = this.f1835f;
        if (q2 != null && q2.b()) {
            q2.dismiss();
        }
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f1835f != null && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i2)), getMeasuredHeight());
        }
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        P p2 = (P) parcelable;
        super.onRestoreInstanceState(p2.getSuperState());
        if (p2.f1821a && (viewTreeObserver = getViewTreeObserver()) != null) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserverOnGlobalLayoutListenerC0142d(2, this));
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.View$BaseSavedState, android.os.Parcelable, k.P] */
    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final Parcelable onSaveInstanceState() {
        boolean z2;
        ?? baseSavedState = new View.BaseSavedState(super.onSaveInstanceState());
        Q q2 = this.f1835f;
        if (q2 != null && q2.b()) {
            z2 = true;
        } else {
            z2 = false;
        }
        baseSavedState.f1821a = z2;
        return baseSavedState;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        C0162J c0162j = this.f1832c;
        if (c0162j != null && c0162j.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean performClick() {
        Q q2 = this.f1835f;
        if (q2 != null) {
            if (!q2.b()) {
                this.f1835f.g(getTextDirection(), getTextAlignment());
                return true;
            }
            return true;
        }
        return super.performClick();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0194p c0194p = this.f1830a;
        if (c0194p != null) {
            c0194p.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        C0194p c0194p = this.f1830a;
        if (c0194p != null) {
            c0194p.f(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i2) {
        Q q2 = this.f1835f;
        if (q2 != null) {
            q2.p(i2);
            q2.c(i2);
        } else {
            super.setDropDownHorizontalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i2) {
        Q q2 = this.f1835f;
        if (q2 != null) {
            q2.m(i2);
        } else {
            super.setDropDownVerticalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i2) {
        if (this.f1835f != null) {
            this.f1836g = i2;
        } else {
            super.setDropDownWidth(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        Q q2 = this.f1835f;
        if (q2 != null) {
            q2.k(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i2) {
        setPopupBackgroundDrawable(C0.d.v(getPopupContext(), i2));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        Q q2 = this.f1835f;
        if (q2 != null) {
            q2.h(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0194p c0194p = this.f1830a;
        if (c0194p != null) {
            c0194p.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0194p c0194p = this.f1830a;
        if (c0194p != null) {
            c0194p.i(mode);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.widget.ListAdapter, java.lang.Object, k.M] */
    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f1834e) {
            this.f1833d = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        Q q2 = this.f1835f;
        if (q2 != 0) {
            Context context = this.f1831b;
            if (context == null) {
                context = getContext();
            }
            Resources.Theme theme = context.getTheme();
            ?? obj = new Object();
            obj.f1805a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                obj.f1806b = (ListAdapter) spinnerAdapter;
            }
            if (theme != null && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                AbstractC0163K.a((ThemedSpinnerAdapter) spinnerAdapter, theme);
            }
            q2.o(obj);
        }
    }
}

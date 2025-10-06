package androidx.appcompat.widget;

import A.f;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import g.J;
import j.InterfaceC0134B;
import j.l;
import j.m;
import j.n;
import j.p;
import j.y;
import k.AbstractC0212y0;
import k.C0176g;
import k.C0182j;
import k.C0184k;
import k.C0188m;
import k.C0210x0;
import k.InterfaceC0186l;
import k.InterfaceC0190n;
import k.m1;

/* loaded from: classes.dex */
public class ActionMenuView extends AbstractC0212y0 implements m, InterfaceC0134B {

    /* renamed from: A, reason: collision with root package name */
    public InterfaceC0190n f880A;

    /* renamed from: p, reason: collision with root package name */
    public n f881p;

    /* renamed from: q, reason: collision with root package name */
    public Context f882q;

    /* renamed from: r, reason: collision with root package name */
    public int f883r;

    /* renamed from: s, reason: collision with root package name */
    public boolean f884s;

    /* renamed from: t, reason: collision with root package name */
    public C0184k f885t;

    /* renamed from: u, reason: collision with root package name */
    public J f886u;

    /* renamed from: v, reason: collision with root package name */
    public l f887v;

    /* renamed from: w, reason: collision with root package name */
    public boolean f888w;

    /* renamed from: x, reason: collision with root package name */
    public int f889x;
    public final int y;

    /* renamed from: z, reason: collision with root package name */
    public final int f890z;

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.y = (int) (56.0f * f2);
        this.f890z = (int) (f2 * 4.0f);
        this.f882q = context;
        this.f883r = 0;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.widget.LinearLayout$LayoutParams, k.m] */
    public static C0188m i() {
        ?? layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.f1973a = false;
        ((LinearLayout.LayoutParams) layoutParams).gravity = 16;
        return layoutParams;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.widget.LinearLayout$LayoutParams, k.m] */
    public static C0188m j(ViewGroup.LayoutParams layoutParams) {
        C0188m c0188m;
        if (layoutParams != null) {
            if (layoutParams instanceof C0188m) {
                C0188m c0188m2 = (C0188m) layoutParams;
                ?? layoutParams2 = new LinearLayout.LayoutParams((ViewGroup.LayoutParams) c0188m2);
                layoutParams2.f1973a = c0188m2.f1973a;
                c0188m = layoutParams2;
            } else {
                c0188m = new LinearLayout.LayoutParams(layoutParams);
            }
            if (((LinearLayout.LayoutParams) c0188m).gravity <= 0) {
                ((LinearLayout.LayoutParams) c0188m).gravity = 16;
            }
            return c0188m;
        }
        return i();
    }

    @Override // j.InterfaceC0134B
    public final void a(n nVar) {
        this.f881p = nVar;
    }

    @Override // j.m
    public final boolean b(p pVar) {
        return this.f881p.q(pVar, null, 0);
    }

    @Override // k.AbstractC0212y0, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0188m;
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // k.AbstractC0212y0
    /* renamed from: e */
    public final /* bridge */ /* synthetic */ C0210x0 generateDefaultLayoutParams() {
        return i();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.widget.LinearLayout$LayoutParams, k.x0] */
    @Override // k.AbstractC0212y0
    /* renamed from: f */
    public final C0210x0 generateLayoutParams(AttributeSet attributeSet) {
        return new LinearLayout.LayoutParams(getContext(), attributeSet);
    }

    @Override // k.AbstractC0212y0
    /* renamed from: g */
    public final /* bridge */ /* synthetic */ C0210x0 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return j(layoutParams);
    }

    @Override // k.AbstractC0212y0, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return i();
    }

    @Override // k.AbstractC0212y0, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return j(layoutParams);
    }

    public Menu getMenu() {
        if (this.f881p == null) {
            Context context = getContext();
            n nVar = new n(context);
            this.f881p = nVar;
            nVar.f1662e = new f(25, this);
            C0184k c0184k = new C0184k(context);
            this.f885t = c0184k;
            c0184k.f1961l = true;
            c0184k.f1962m = true;
            y yVar = this.f886u;
            if (yVar == null) {
                yVar = new A.m(20);
            }
            c0184k.f1955e = yVar;
            this.f881p.b(c0184k, this.f882q);
            C0184k c0184k2 = this.f885t;
            c0184k2.h = this;
            this.f881p = c0184k2.f1953c;
        }
        return this.f881p;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        C0184k c0184k = this.f885t;
        C0182j c0182j = c0184k.f1958i;
        if (c0182j != null) {
            return c0182j.getDrawable();
        }
        if (c0184k.f1960k) {
            return c0184k.f1959j;
        }
        return null;
    }

    public int getPopupTheme() {
        return this.f883r;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public final boolean k(int i2) {
        boolean z2 = false;
        if (i2 == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i2 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i2);
        if (i2 < getChildCount() && (childAt instanceof InterfaceC0186l)) {
            z2 = ((InterfaceC0186l) childAt).b();
        }
        if (i2 > 0 && (childAt2 instanceof InterfaceC0186l)) {
            return z2 | ((InterfaceC0186l) childAt2).a();
        }
        return z2;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        C0184k c0184k = this.f885t;
        if (c0184k != null) {
            c0184k.c();
            if (this.f885t.i()) {
                this.f885t.e();
                this.f885t.l();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C0184k c0184k = this.f885t;
        if (c0184k != null) {
            c0184k.e();
            C0176g c0176g = c0184k.f1969t;
            if (c0176g != null && c0176g.b()) {
                c0176g.f1730i.dismiss();
            }
        }
    }

    @Override // k.AbstractC0212y0, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        boolean z3;
        int i6;
        int width;
        int i7;
        if (!this.f888w) {
            super.onLayout(z2, i2, i3, i4, i5);
            return;
        }
        int childCount = getChildCount();
        int i8 = (i5 - i3) / 2;
        int dividerWidth = getDividerWidth();
        int i9 = i4 - i2;
        int paddingRight = (i9 - getPaddingRight()) - getPaddingLeft();
        boolean z4 = m1.f1979a;
        if (getLayoutDirection() == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                C0188m c0188m = (C0188m) childAt.getLayoutParams();
                if (c0188m.f1973a) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (k(i12)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (z3) {
                        i7 = getPaddingLeft() + ((LinearLayout.LayoutParams) c0188m).leftMargin;
                        width = i7 + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((LinearLayout.LayoutParams) c0188m).rightMargin;
                        i7 = width - measuredWidth;
                    }
                    int i13 = i8 - (measuredHeight / 2);
                    childAt.layout(i7, i13, width, measuredHeight + i13);
                    paddingRight -= measuredWidth;
                    i10 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((LinearLayout.LayoutParams) c0188m).leftMargin) + ((LinearLayout.LayoutParams) c0188m).rightMargin;
                    k(i12);
                    i11++;
                }
            }
        }
        if (childCount == 1 && i10 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i14 = (i9 / 2) - (measuredWidth2 / 2);
            int i15 = i8 - (measuredHeight2 / 2);
            childAt2.layout(i14, i15, measuredWidth2 + i14, measuredHeight2 + i15);
            return;
        }
        int i16 = i11 - (i10 ^ 1);
        if (i16 > 0) {
            i6 = paddingRight / i16;
        } else {
            i6 = 0;
        }
        int max = Math.max(0, i6);
        if (z3) {
            int width2 = getWidth() - getPaddingRight();
            for (int i17 = 0; i17 < childCount; i17++) {
                View childAt3 = getChildAt(i17);
                C0188m c0188m2 = (C0188m) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !c0188m2.f1973a) {
                    int i18 = width2 - ((LinearLayout.LayoutParams) c0188m2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i19 = i8 - (measuredHeight3 / 2);
                    childAt3.layout(i18 - measuredWidth3, i19, i18, measuredHeight3 + i19);
                    width2 = i18 - ((measuredWidth3 + ((LinearLayout.LayoutParams) c0188m2).leftMargin) + max);
                }
            }
            return;
        }
        int paddingLeft = getPaddingLeft();
        for (int i20 = 0; i20 < childCount; i20++) {
            View childAt4 = getChildAt(i20);
            C0188m c0188m3 = (C0188m) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !c0188m3.f1973a) {
                int i21 = paddingLeft + ((LinearLayout.LayoutParams) c0188m3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i22 = i8 - (measuredHeight4 / 2);
                childAt4.layout(i21, i22, i21 + measuredWidth4, measuredHeight4 + i22);
                paddingLeft = measuredWidth4 + ((LinearLayout.LayoutParams) c0188m3).rightMargin + max + i21;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v29, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r4v36 */
    @Override // k.AbstractC0212y0, android.view.View
    public final void onMeasure(int i2, int i3) {
        boolean z2;
        int i4;
        boolean z3;
        boolean z4;
        boolean z5;
        int i5;
        boolean z6;
        int i6;
        int i7;
        int i8;
        int i9;
        ?? r4;
        boolean z7;
        int i10;
        int i11;
        int i12;
        ActionMenuItemView actionMenuItemView;
        boolean z8;
        int i13;
        boolean z9;
        n nVar;
        boolean z10 = this.f888w;
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f888w = z2;
        if (z10 != z2) {
            this.f889x = 0;
        }
        int size = View.MeasureSpec.getSize(i2);
        if (this.f888w && (nVar = this.f881p) != null && size != this.f889x) {
            this.f889x = size;
            nVar.p(true);
        }
        int childCount = getChildCount();
        if (this.f888w && childCount > 0) {
            int mode = View.MeasureSpec.getMode(i3);
            int size2 = View.MeasureSpec.getSize(i2);
            int size3 = View.MeasureSpec.getSize(i3);
            int paddingRight = getPaddingRight() + getPaddingLeft();
            int paddingBottom = getPaddingBottom() + getPaddingTop();
            int childMeasureSpec = ViewGroup.getChildMeasureSpec(i3, paddingBottom, -2);
            int i14 = size2 - paddingRight;
            int i15 = this.y;
            int i16 = i14 / i15;
            int i17 = i14 % i15;
            if (i16 == 0) {
                setMeasuredDimension(i14, 0);
                return;
            }
            int i18 = (i17 / i16) + i15;
            int childCount2 = getChildCount();
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            boolean z11 = false;
            int i23 = 0;
            long j2 = 0;
            while (true) {
                i4 = this.f890z;
                if (i22 >= childCount2) {
                    break;
                }
                View childAt = getChildAt(i22);
                int i24 = size3;
                int i25 = i14;
                if (childAt.getVisibility() == 8) {
                    i11 = mode;
                    i12 = paddingBottom;
                } else {
                    boolean z12 = childAt instanceof ActionMenuItemView;
                    int i26 = i20 + 1;
                    if (z12) {
                        childAt.setPadding(i4, 0, i4, 0);
                    }
                    C0188m c0188m = (C0188m) childAt.getLayoutParams();
                    c0188m.f1978f = false;
                    c0188m.f1975c = 0;
                    c0188m.f1974b = 0;
                    c0188m.f1976d = false;
                    ((LinearLayout.LayoutParams) c0188m).leftMargin = 0;
                    ((LinearLayout.LayoutParams) c0188m).rightMargin = 0;
                    if (z12 && !TextUtils.isEmpty(((ActionMenuItemView) childAt).getText())) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    c0188m.f1977e = z7;
                    if (c0188m.f1973a) {
                        i10 = 1;
                    } else {
                        i10 = i16;
                    }
                    C0188m c0188m2 = (C0188m) childAt.getLayoutParams();
                    i11 = mode;
                    i12 = paddingBottom;
                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(childMeasureSpec) - paddingBottom, View.MeasureSpec.getMode(childMeasureSpec));
                    if (z12) {
                        actionMenuItemView = (ActionMenuItemView) childAt;
                    } else {
                        actionMenuItemView = null;
                    }
                    if (actionMenuItemView != null && !TextUtils.isEmpty(actionMenuItemView.getText())) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    if (i10 > 0 && (!z8 || i10 >= 2)) {
                        childAt.measure(View.MeasureSpec.makeMeasureSpec(i10 * i18, Integer.MIN_VALUE), makeMeasureSpec);
                        int measuredWidth = childAt.getMeasuredWidth();
                        i13 = measuredWidth / i18;
                        if (measuredWidth % i18 != 0) {
                            i13++;
                        }
                        if (z8 && i13 < 2) {
                            i13 = 2;
                        }
                    } else {
                        i13 = 0;
                    }
                    if (!c0188m2.f1973a && z8) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    c0188m2.f1976d = z9;
                    c0188m2.f1974b = i13;
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i13 * i18, 1073741824), makeMeasureSpec);
                    i21 = Math.max(i21, i13);
                    if (c0188m.f1976d) {
                        i23++;
                    }
                    if (c0188m.f1973a) {
                        z11 = true;
                    }
                    i16 -= i13;
                    i19 = Math.max(i19, childAt.getMeasuredHeight());
                    if (i13 == 1) {
                        j2 |= 1 << i22;
                    }
                    i20 = i26;
                }
                i22++;
                size3 = i24;
                i14 = i25;
                paddingBottom = i12;
                mode = i11;
            }
            int i27 = mode;
            int i28 = i14;
            int i29 = size3;
            if (z11 && i20 == 2) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z13 = false;
            while (i23 > 0 && i16 > 0) {
                int i30 = Integer.MAX_VALUE;
                int i31 = 0;
                int i32 = 0;
                long j3 = 0;
                while (i32 < childCount2) {
                    C0188m c0188m3 = (C0188m) getChildAt(i32).getLayoutParams();
                    boolean z14 = z13;
                    if (c0188m3.f1976d) {
                        int i33 = c0188m3.f1974b;
                        if (i33 < i30) {
                            j3 = 1 << i32;
                            i30 = i33;
                            i31 = 1;
                        } else if (i33 == i30) {
                            j3 |= 1 << i32;
                            i31++;
                        }
                    }
                    i32++;
                    z13 = z14;
                }
                z4 = z13;
                j2 |= j3;
                if (i31 > i16) {
                    break;
                }
                int i34 = i30 + 1;
                int i35 = 0;
                while (i35 < childCount2) {
                    View childAt2 = getChildAt(i35);
                    C0188m c0188m4 = (C0188m) childAt2.getLayoutParams();
                    int i36 = i19;
                    int i37 = childMeasureSpec;
                    int i38 = childCount2;
                    long j4 = 1 << i35;
                    if ((j3 & j4) == 0) {
                        if (c0188m4.f1974b == i34) {
                            j2 |= j4;
                        }
                    } else {
                        if (z3 && c0188m4.f1977e) {
                            r4 = 1;
                            r4 = 1;
                            if (i16 == 1) {
                                childAt2.setPadding(i4 + i18, 0, i4, 0);
                            }
                        } else {
                            r4 = 1;
                        }
                        c0188m4.f1974b += r4;
                        c0188m4.f1978f = r4;
                        i16--;
                    }
                    i35++;
                    childMeasureSpec = i37;
                    i19 = i36;
                    childCount2 = i38;
                }
                z13 = true;
            }
            z4 = z13;
            int i39 = i19;
            int i40 = childMeasureSpec;
            int i41 = childCount2;
            if (!z11 && i20 == 1) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (i16 <= 0 || j2 == 0 || (i16 >= i20 - 1 && !z5 && i21 <= 1)) {
                i5 = i41;
                z6 = z4;
            } else {
                float bitCount = Long.bitCount(j2);
                if (!z5) {
                    if ((j2 & 1) != 0 && !((C0188m) getChildAt(0).getLayoutParams()).f1977e) {
                        bitCount -= 0.5f;
                    }
                    int i42 = i41 - 1;
                    if ((j2 & (1 << i42)) != 0 && !((C0188m) getChildAt(i42).getLayoutParams()).f1977e) {
                        bitCount -= 0.5f;
                    }
                }
                if (bitCount > 0.0f) {
                    i9 = (int) ((i16 * i18) / bitCount);
                } else {
                    i9 = 0;
                }
                boolean z15 = z4;
                i5 = i41;
                for (int i43 = 0; i43 < i5; i43++) {
                    if ((j2 & (1 << i43)) != 0) {
                        View childAt3 = getChildAt(i43);
                        C0188m c0188m5 = (C0188m) childAt3.getLayoutParams();
                        if (childAt3 instanceof ActionMenuItemView) {
                            c0188m5.f1975c = i9;
                            c0188m5.f1978f = true;
                            if (i43 == 0 && !c0188m5.f1977e) {
                                ((LinearLayout.LayoutParams) c0188m5).leftMargin = (-i9) / 2;
                            }
                            z15 = true;
                        } else {
                            if (c0188m5.f1973a) {
                                c0188m5.f1975c = i9;
                                c0188m5.f1978f = true;
                                ((LinearLayout.LayoutParams) c0188m5).rightMargin = (-i9) / 2;
                                z15 = true;
                            } else {
                                if (i43 != 0) {
                                    ((LinearLayout.LayoutParams) c0188m5).leftMargin = i9 / 2;
                                }
                                if (i43 != i5 - 1) {
                                    ((LinearLayout.LayoutParams) c0188m5).rightMargin = i9 / 2;
                                }
                            }
                        }
                    }
                }
                z6 = z15;
            }
            if (z6) {
                int i44 = 0;
                while (i44 < i5) {
                    View childAt4 = getChildAt(i44);
                    C0188m c0188m6 = (C0188m) childAt4.getLayoutParams();
                    if (!c0188m6.f1978f) {
                        i8 = i40;
                    } else {
                        i8 = i40;
                        childAt4.measure(View.MeasureSpec.makeMeasureSpec((c0188m6.f1974b * i18) + c0188m6.f1975c, 1073741824), i8);
                    }
                    i44++;
                    i40 = i8;
                }
            }
            if (i27 != 1073741824) {
                i7 = i28;
                i6 = i39;
            } else {
                i6 = i29;
                i7 = i28;
            }
            setMeasuredDimension(i7, i6);
            return;
        }
        for (int i45 = 0; i45 < childCount; i45++) {
            C0188m c0188m7 = (C0188m) getChildAt(i45).getLayoutParams();
            ((LinearLayout.LayoutParams) c0188m7).rightMargin = 0;
            ((LinearLayout.LayoutParams) c0188m7).leftMargin = 0;
        }
        super.onMeasure(i2, i3);
    }

    public void setExpandedActionViewsExclusive(boolean z2) {
        this.f885t.f1966q = z2;
    }

    public void setOnMenuItemClickListener(InterfaceC0190n interfaceC0190n) {
        this.f880A = interfaceC0190n;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        C0184k c0184k = this.f885t;
        C0182j c0182j = c0184k.f1958i;
        if (c0182j != null) {
            c0182j.setImageDrawable(drawable);
        } else {
            c0184k.f1960k = true;
            c0184k.f1959j = drawable;
        }
    }

    public void setOverflowReserved(boolean z2) {
        this.f884s = z2;
    }

    public void setPopupTheme(int i2) {
        if (this.f883r != i2) {
            this.f883r = i2;
            if (i2 == 0) {
                this.f882q = getContext();
            } else {
                this.f882q = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public void setPresenter(C0184k c0184k) {
        this.f885t = c0184k;
        c0184k.h = this;
        this.f881p = c0184k.f1953c;
    }

    @Override // k.AbstractC0212y0, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LinearLayout.LayoutParams(getContext(), attributeSet);
    }
}

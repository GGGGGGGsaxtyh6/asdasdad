package androidx.appcompat.widget;

import C0.d;
import D0.h;
import E.b;
import H.C0021o;
import H.N;
import L.c;
import Z.q;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import f.AbstractC0101a;
import g.I;
import g.J;
import i.C0127i;
import j.n;
import j.p;
import java.util.ArrayList;
import java.util.Iterator;
import k.C0153A;
import k.C0171d0;
import k.C0184k;
import k.C0211y;
import k.InterfaceC0189m0;
import k.P0;
import k.W0;
import k.X0;
import k.Y0;
import k.Z0;
import k.a1;
import k.b1;
import k.c1;
import k.e1;
import k.m1;
import org.conscrypt.R;

/* loaded from: classes.dex */
public class Toolbar extends ViewGroup {

    /* renamed from: A, reason: collision with root package name */
    public ColorStateList f905A;

    /* renamed from: B, reason: collision with root package name */
    public boolean f906B;

    /* renamed from: C, reason: collision with root package name */
    public boolean f907C;

    /* renamed from: D, reason: collision with root package name */
    public final ArrayList f908D;

    /* renamed from: E, reason: collision with root package name */
    public final ArrayList f909E;

    /* renamed from: F, reason: collision with root package name */
    public final int[] f910F;

    /* renamed from: G, reason: collision with root package name */
    public final C0021o f911G;

    /* renamed from: H, reason: collision with root package name */
    public ArrayList f912H;

    /* renamed from: I, reason: collision with root package name */
    public b1 f913I;

    /* renamed from: J, reason: collision with root package name */
    public final X0 f914J;

    /* renamed from: K, reason: collision with root package name */
    public e1 f915K;

    /* renamed from: L, reason: collision with root package name */
    public C0184k f916L;

    /* renamed from: M, reason: collision with root package name */
    public Z0 f917M;

    /* renamed from: N, reason: collision with root package name */
    public J f918N;

    /* renamed from: O, reason: collision with root package name */
    public I f919O;

    /* renamed from: P, reason: collision with root package name */
    public boolean f920P;

    /* renamed from: Q, reason: collision with root package name */
    public OnBackInvokedCallback f921Q;

    /* renamed from: R, reason: collision with root package name */
    public OnBackInvokedDispatcher f922R;

    /* renamed from: S, reason: collision with root package name */
    public boolean f923S;

    /* renamed from: T, reason: collision with root package name */
    public final b f924T;

    /* renamed from: a, reason: collision with root package name */
    public ActionMenuView f925a;

    /* renamed from: b, reason: collision with root package name */
    public C0171d0 f926b;

    /* renamed from: c, reason: collision with root package name */
    public C0171d0 f927c;

    /* renamed from: d, reason: collision with root package name */
    public C0211y f928d;

    /* renamed from: e, reason: collision with root package name */
    public C0153A f929e;

    /* renamed from: f, reason: collision with root package name */
    public final Drawable f930f;

    /* renamed from: g, reason: collision with root package name */
    public final CharSequence f931g;
    public C0211y h;

    /* renamed from: i, reason: collision with root package name */
    public View f932i;

    /* renamed from: j, reason: collision with root package name */
    public Context f933j;

    /* renamed from: k, reason: collision with root package name */
    public int f934k;

    /* renamed from: l, reason: collision with root package name */
    public int f935l;

    /* renamed from: m, reason: collision with root package name */
    public int f936m;

    /* renamed from: n, reason: collision with root package name */
    public final int f937n;

    /* renamed from: o, reason: collision with root package name */
    public final int f938o;

    /* renamed from: p, reason: collision with root package name */
    public int f939p;

    /* renamed from: q, reason: collision with root package name */
    public int f940q;

    /* renamed from: r, reason: collision with root package name */
    public int f941r;

    /* renamed from: s, reason: collision with root package name */
    public int f942s;

    /* renamed from: t, reason: collision with root package name */
    public P0 f943t;

    /* renamed from: u, reason: collision with root package name */
    public int f944u;

    /* renamed from: v, reason: collision with root package name */
    public int f945v;

    /* renamed from: w, reason: collision with root package name */
    public final int f946w;

    /* renamed from: x, reason: collision with root package name */
    public CharSequence f947x;
    public CharSequence y;

    /* renamed from: z, reason: collision with root package name */
    public ColorStateList f948z;

    public Toolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.toolbarStyle);
        this.f946w = 8388627;
        this.f908D = new ArrayList();
        this.f909E = new ArrayList();
        this.f910F = new int[2];
        this.f911G = new C0021o(new W0(this, 1));
        this.f912H = new ArrayList();
        this.f914J = new X0(this);
        this.f924T = new b(10, this);
        Context context2 = getContext();
        int[] iArr = AbstractC0101a.f1283w;
        h p2 = h.p(context2, attributeSet, iArr, R.attr.toolbarStyle);
        N.g(this, context, iArr, attributeSet, (TypedArray) p2.f259c, R.attr.toolbarStyle);
        TypedArray typedArray = (TypedArray) p2.f259c;
        this.f935l = typedArray.getResourceId(28, 0);
        this.f936m = typedArray.getResourceId(19, 0);
        this.f946w = typedArray.getInteger(0, 8388627);
        this.f937n = typedArray.getInteger(2, 48);
        int dimensionPixelOffset = typedArray.getDimensionPixelOffset(22, 0);
        dimensionPixelOffset = typedArray.hasValue(27) ? typedArray.getDimensionPixelOffset(27, dimensionPixelOffset) : dimensionPixelOffset;
        this.f942s = dimensionPixelOffset;
        this.f941r = dimensionPixelOffset;
        this.f940q = dimensionPixelOffset;
        this.f939p = dimensionPixelOffset;
        int dimensionPixelOffset2 = typedArray.getDimensionPixelOffset(25, -1);
        if (dimensionPixelOffset2 >= 0) {
            this.f939p = dimensionPixelOffset2;
        }
        int dimensionPixelOffset3 = typedArray.getDimensionPixelOffset(24, -1);
        if (dimensionPixelOffset3 >= 0) {
            this.f940q = dimensionPixelOffset3;
        }
        int dimensionPixelOffset4 = typedArray.getDimensionPixelOffset(26, -1);
        if (dimensionPixelOffset4 >= 0) {
            this.f941r = dimensionPixelOffset4;
        }
        int dimensionPixelOffset5 = typedArray.getDimensionPixelOffset(23, -1);
        if (dimensionPixelOffset5 >= 0) {
            this.f942s = dimensionPixelOffset5;
        }
        this.f938o = typedArray.getDimensionPixelSize(13, -1);
        int dimensionPixelOffset6 = typedArray.getDimensionPixelOffset(9, Integer.MIN_VALUE);
        int dimensionPixelOffset7 = typedArray.getDimensionPixelOffset(5, Integer.MIN_VALUE);
        int dimensionPixelSize = typedArray.getDimensionPixelSize(7, 0);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(8, 0);
        d();
        P0 p02 = this.f943t;
        p02.h = false;
        if (dimensionPixelSize != Integer.MIN_VALUE) {
            p02.f1826e = dimensionPixelSize;
            p02.f1822a = dimensionPixelSize;
        }
        if (dimensionPixelSize2 != Integer.MIN_VALUE) {
            p02.f1827f = dimensionPixelSize2;
            p02.f1823b = dimensionPixelSize2;
        }
        if (dimensionPixelOffset6 != Integer.MIN_VALUE || dimensionPixelOffset7 != Integer.MIN_VALUE) {
            p02.a(dimensionPixelOffset6, dimensionPixelOffset7);
        }
        this.f944u = typedArray.getDimensionPixelOffset(10, Integer.MIN_VALUE);
        this.f945v = typedArray.getDimensionPixelOffset(6, Integer.MIN_VALUE);
        this.f930f = p2.j(4);
        this.f931g = typedArray.getText(3);
        CharSequence text = typedArray.getText(21);
        if (!TextUtils.isEmpty(text)) {
            setTitle(text);
        }
        CharSequence text2 = typedArray.getText(18);
        if (!TextUtils.isEmpty(text2)) {
            setSubtitle(text2);
        }
        this.f933j = getContext();
        setPopupTheme(typedArray.getResourceId(17, 0));
        Drawable j2 = p2.j(16);
        if (j2 != null) {
            setNavigationIcon(j2);
        }
        CharSequence text3 = typedArray.getText(15);
        if (!TextUtils.isEmpty(text3)) {
            setNavigationContentDescription(text3);
        }
        Drawable j3 = p2.j(11);
        if (j3 != null) {
            setLogo(j3);
        }
        CharSequence text4 = typedArray.getText(12);
        if (!TextUtils.isEmpty(text4)) {
            setLogoDescription(text4);
        }
        if (typedArray.hasValue(29)) {
            setTitleTextColor(p2.i(29));
        }
        if (typedArray.hasValue(20)) {
            setSubtitleTextColor(p2.i(20));
        }
        if (typedArray.hasValue(14)) {
            getMenuInflater().inflate(typedArray.getResourceId(14, 0), getMenu());
        }
        p2.r();
    }

    private ArrayList<MenuItem> getCurrentMenuItems() {
        ArrayList<MenuItem> arrayList = new ArrayList<>();
        Menu menu = getMenu();
        for (int i2 = 0; i2 < menu.size(); i2++) {
            arrayList.add(menu.getItem(i2));
        }
        return arrayList;
    }

    private MenuInflater getMenuInflater() {
        return new C0127i(getContext());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [k.a1, android.view.ViewGroup$MarginLayoutParams] */
    public static a1 h() {
        ?? marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
        marginLayoutParams.f1878b = 0;
        marginLayoutParams.f1877a = 8388627;
        return marginLayoutParams;
    }

    public static a1 i(ViewGroup.LayoutParams layoutParams) {
        boolean z2 = layoutParams instanceof a1;
        if (z2) {
            a1 a1Var = (a1) layoutParams;
            a1 a1Var2 = new a1(a1Var);
            a1Var2.f1878b = 0;
            a1Var2.f1878b = a1Var.f1878b;
            return a1Var2;
        }
        if (z2) {
            a1 a1Var3 = new a1((a1) layoutParams);
            a1Var3.f1878b = 0;
            return a1Var3;
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            a1 a1Var4 = new a1(marginLayoutParams);
            a1Var4.f1878b = 0;
            ((ViewGroup.MarginLayoutParams) a1Var4).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) a1Var4).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) a1Var4).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) a1Var4).bottomMargin = marginLayoutParams.bottomMargin;
            return a1Var4;
        }
        a1 a1Var5 = new a1(layoutParams);
        a1Var5.f1878b = 0;
        return a1Var5;
    }

    public static int k(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.getMarginEnd() + marginLayoutParams.getMarginStart();
    }

    public static int l(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public final void a(ArrayList arrayList, int i2) {
        boolean z2;
        if (getLayoutDirection() == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        int childCount = getChildCount();
        int absoluteGravity = Gravity.getAbsoluteGravity(i2, getLayoutDirection());
        arrayList.clear();
        if (z2) {
            for (int i3 = childCount - 1; i3 >= 0; i3--) {
                View childAt = getChildAt(i3);
                a1 a1Var = (a1) childAt.getLayoutParams();
                if (a1Var.f1878b == 0 && t(childAt)) {
                    int i4 = a1Var.f1877a;
                    int layoutDirection = getLayoutDirection();
                    int absoluteGravity2 = Gravity.getAbsoluteGravity(i4, layoutDirection) & 7;
                    if (absoluteGravity2 != 1 && absoluteGravity2 != 3 && absoluteGravity2 != 5) {
                        absoluteGravity2 = layoutDirection == 1 ? 5 : 3;
                    }
                    if (absoluteGravity2 == absoluteGravity) {
                        arrayList.add(childAt);
                    }
                }
            }
            return;
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt2 = getChildAt(i5);
            a1 a1Var2 = (a1) childAt2.getLayoutParams();
            if (a1Var2.f1878b == 0 && t(childAt2)) {
                int i6 = a1Var2.f1877a;
                int layoutDirection2 = getLayoutDirection();
                int absoluteGravity3 = Gravity.getAbsoluteGravity(i6, layoutDirection2) & 7;
                if (absoluteGravity3 != 1 && absoluteGravity3 != 3 && absoluteGravity3 != 5) {
                    absoluteGravity3 = layoutDirection2 == 1 ? 5 : 3;
                }
                if (absoluteGravity3 == absoluteGravity) {
                    arrayList.add(childAt2);
                }
            }
        }
    }

    public final void b(View view, boolean z2) {
        a1 a1Var;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            a1Var = h();
        } else if (!checkLayoutParams(layoutParams)) {
            a1Var = i(layoutParams);
        } else {
            a1Var = (a1) layoutParams;
        }
        a1Var.f1878b = 1;
        if (z2 && this.f932i != null) {
            view.setLayoutParams(a1Var);
            this.f909E.add(view);
        } else {
            addView(view, a1Var);
        }
    }

    public final void c() {
        if (this.h == null) {
            C0211y c0211y = new C0211y(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            this.h = c0211y;
            c0211y.setImageDrawable(this.f930f);
            this.h.setContentDescription(this.f931g);
            a1 h = h();
            h.f1877a = (this.f937n & 112) | 8388611;
            h.f1878b = 2;
            this.h.setLayoutParams(h);
            this.h.setOnClickListener(new q(3, this));
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (super.checkLayoutParams(layoutParams) && (layoutParams instanceof a1)) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [k.P0, java.lang.Object] */
    public final void d() {
        if (this.f943t == null) {
            ?? obj = new Object();
            obj.f1822a = 0;
            obj.f1823b = 0;
            obj.f1824c = Integer.MIN_VALUE;
            obj.f1825d = Integer.MIN_VALUE;
            obj.f1826e = 0;
            obj.f1827f = 0;
            obj.f1828g = false;
            obj.h = false;
            this.f943t = obj;
        }
    }

    public final void e() {
        f();
        ActionMenuView actionMenuView = this.f925a;
        if (actionMenuView.f881p == null) {
            n nVar = (n) actionMenuView.getMenu();
            if (this.f917M == null) {
                this.f917M = new Z0(this);
            }
            this.f925a.setExpandedActionViewsExclusive(true);
            nVar.b(this.f917M, this.f933j);
            v();
        }
    }

    public final void f() {
        if (this.f925a == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext(), null);
            this.f925a = actionMenuView;
            actionMenuView.setPopupTheme(this.f934k);
            this.f925a.setOnMenuItemClickListener(this.f914J);
            ActionMenuView actionMenuView2 = this.f925a;
            J j2 = this.f918N;
            X0 x02 = new X0(this);
            actionMenuView2.f886u = j2;
            actionMenuView2.f887v = x02;
            a1 h = h();
            h.f1877a = (this.f937n & 112) | 8388613;
            this.f925a.setLayoutParams(h);
            b(this.f925a, false);
        }
    }

    public final void g() {
        if (this.f928d == null) {
            this.f928d = new C0211y(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            a1 h = h();
            h.f1877a = (this.f937n & 112) | 8388611;
            this.f928d.setLayoutParams(h);
        }
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return h();
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return i(layoutParams);
    }

    public CharSequence getCollapseContentDescription() {
        C0211y c0211y = this.h;
        if (c0211y != null) {
            return c0211y.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        C0211y c0211y = this.h;
        if (c0211y != null) {
            return c0211y.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        P0 p02 = this.f943t;
        if (p02 != null) {
            if (p02.f1828g) {
                return p02.f1822a;
            }
            return p02.f1823b;
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i2 = this.f945v;
        if (i2 == Integer.MIN_VALUE) {
            return getContentInsetEnd();
        }
        return i2;
    }

    public int getContentInsetLeft() {
        P0 p02 = this.f943t;
        if (p02 != null) {
            return p02.f1822a;
        }
        return 0;
    }

    public int getContentInsetRight() {
        P0 p02 = this.f943t;
        if (p02 != null) {
            return p02.f1823b;
        }
        return 0;
    }

    public int getContentInsetStart() {
        P0 p02 = this.f943t;
        if (p02 != null) {
            if (p02.f1828g) {
                return p02.f1823b;
            }
            return p02.f1822a;
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i2 = this.f944u;
        if (i2 == Integer.MIN_VALUE) {
            return getContentInsetStart();
        }
        return i2;
    }

    public int getCurrentContentInsetEnd() {
        n nVar;
        ActionMenuView actionMenuView = this.f925a;
        if (actionMenuView != null && (nVar = actionMenuView.f881p) != null && nVar.hasVisibleItems()) {
            return Math.max(getContentInsetEnd(), Math.max(this.f945v, 0));
        }
        return getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        if (getLayoutDirection() == 1) {
            return getCurrentContentInsetEnd();
        }
        return getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        if (getLayoutDirection() == 1) {
            return getCurrentContentInsetStart();
        }
        return getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        if (getNavigationIcon() != null) {
            return Math.max(getContentInsetStart(), Math.max(this.f944u, 0));
        }
        return getContentInsetStart();
    }

    public Drawable getLogo() {
        C0153A c0153a = this.f929e;
        if (c0153a != null) {
            return c0153a.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        C0153A c0153a = this.f929e;
        if (c0153a != null) {
            return c0153a.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        e();
        return this.f925a.getMenu();
    }

    public View getNavButtonView() {
        return this.f928d;
    }

    public CharSequence getNavigationContentDescription() {
        C0211y c0211y = this.f928d;
        if (c0211y != null) {
            return c0211y.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        C0211y c0211y = this.f928d;
        if (c0211y != null) {
            return c0211y.getDrawable();
        }
        return null;
    }

    public C0184k getOuterActionMenuPresenter() {
        return this.f916L;
    }

    public Drawable getOverflowIcon() {
        e();
        return this.f925a.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.f933j;
    }

    public int getPopupTheme() {
        return this.f934k;
    }

    public CharSequence getSubtitle() {
        return this.y;
    }

    public final TextView getSubtitleTextView() {
        return this.f927c;
    }

    public CharSequence getTitle() {
        return this.f947x;
    }

    public int getTitleMarginBottom() {
        return this.f942s;
    }

    public int getTitleMarginEnd() {
        return this.f940q;
    }

    public int getTitleMarginStart() {
        return this.f939p;
    }

    public int getTitleMarginTop() {
        return this.f941r;
    }

    public final TextView getTitleTextView() {
        return this.f926b;
    }

    public InterfaceC0189m0 getWrapper() {
        if (this.f915K == null) {
            this.f915K = new e1(this, true);
        }
        return this.f915K;
    }

    public final int j(View view, int i2) {
        int i3;
        a1 a1Var = (a1) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        if (i2 > 0) {
            i3 = (measuredHeight - i2) / 2;
        } else {
            i3 = 0;
        }
        int i4 = a1Var.f1877a & 112;
        if (i4 != 16 && i4 != 48 && i4 != 80) {
            i4 = this.f946w & 112;
        }
        if (i4 != 48) {
            if (i4 != 80) {
                int paddingTop = getPaddingTop();
                int paddingBottom = getPaddingBottom();
                int height = getHeight();
                int i5 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
                int i6 = ((ViewGroup.MarginLayoutParams) a1Var).topMargin;
                if (i5 < i6) {
                    i5 = i6;
                } else {
                    int i7 = (((height - paddingBottom) - measuredHeight) - i5) - paddingTop;
                    int i8 = ((ViewGroup.MarginLayoutParams) a1Var).bottomMargin;
                    if (i7 < i8) {
                        i5 = Math.max(0, i5 - (i8 - i7));
                    }
                }
                return paddingTop + i5;
            }
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) a1Var).bottomMargin) - i3;
        }
        return getPaddingTop() - i3;
    }

    public final void m() {
        Iterator it = this.f912H.iterator();
        while (it.hasNext()) {
            getMenu().removeItem(((MenuItem) it.next()).getItemId());
        }
        getMenu();
        ArrayList<MenuItem> currentMenuItems = getCurrentMenuItems();
        getMenuInflater();
        this.f911G.a();
        ArrayList<MenuItem> currentMenuItems2 = getCurrentMenuItems();
        currentMenuItems2.removeAll(currentMenuItems);
        this.f912H = currentMenuItems2;
    }

    public final boolean n(View view) {
        if (view.getParent() != this && !this.f909E.contains(view)) {
            return false;
        }
        return true;
    }

    public final boolean o() {
        C0184k c0184k;
        ActionMenuView actionMenuView = this.f925a;
        if (actionMenuView != null && (c0184k = actionMenuView.f885t) != null && c0184k.i()) {
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        v();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.f924T);
        v();
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f907C = false;
        }
        if (!this.f907C) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.f907C = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f907C = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0298 A[LOOP:0: B:40:0x0296->B:41:0x0298, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x02b5 A[LOOP:1: B:44:0x02b3->B:45:0x02b5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x02d3 A[LOOP:2: B:48:0x02d1->B:49:0x02d3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0321 A[LOOP:3: B:57:0x031f->B:58:0x0321, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0221  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 818
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public final void onMeasure(int i2, int i3) {
        char c2;
        char c3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z2 = m1.f1979a;
        int i11 = 0;
        if (getLayoutDirection() == 1) {
            c3 = 1;
            c2 = 0;
        } else {
            c2 = 1;
            c3 = 0;
        }
        if (t(this.f928d)) {
            s(this.f928d, i2, 0, i3, this.f938o);
            i4 = k(this.f928d) + this.f928d.getMeasuredWidth();
            i5 = Math.max(0, l(this.f928d) + this.f928d.getMeasuredHeight());
            i6 = View.combineMeasuredStates(0, this.f928d.getMeasuredState());
        } else {
            i4 = 0;
            i5 = 0;
            i6 = 0;
        }
        if (t(this.h)) {
            s(this.h, i2, 0, i3, this.f938o);
            i4 = k(this.h) + this.h.getMeasuredWidth();
            i5 = Math.max(i5, l(this.h) + this.h.getMeasuredHeight());
            i6 = View.combineMeasuredStates(i6, this.h.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int max = Math.max(currentContentInsetStart, i4);
        int max2 = Math.max(0, currentContentInsetStart - i4);
        int[] iArr = this.f910F;
        iArr[c3] = max2;
        if (t(this.f925a)) {
            s(this.f925a, i2, max, i3, this.f938o);
            i7 = k(this.f925a) + this.f925a.getMeasuredWidth();
            i5 = Math.max(i5, l(this.f925a) + this.f925a.getMeasuredHeight());
            i6 = View.combineMeasuredStates(i6, this.f925a.getMeasuredState());
        } else {
            i7 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int max3 = max + Math.max(currentContentInsetEnd, i7);
        iArr[c2] = Math.max(0, currentContentInsetEnd - i7);
        if (t(this.f932i)) {
            max3 += r(this.f932i, i2, max3, i3, 0, iArr);
            i5 = Math.max(i5, l(this.f932i) + this.f932i.getMeasuredHeight());
            i6 = View.combineMeasuredStates(i6, this.f932i.getMeasuredState());
        }
        if (t(this.f929e)) {
            max3 += r(this.f929e, i2, max3, i3, 0, iArr);
            i5 = Math.max(i5, l(this.f929e) + this.f929e.getMeasuredHeight());
            i6 = View.combineMeasuredStates(i6, this.f929e.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (((a1) childAt.getLayoutParams()).f1878b == 0 && t(childAt)) {
                max3 += r(childAt, i2, max3, i3, 0, iArr);
                i5 = Math.max(i5, l(childAt) + childAt.getMeasuredHeight());
                i6 = View.combineMeasuredStates(i6, childAt.getMeasuredState());
            }
        }
        int i13 = this.f941r + this.f942s;
        int i14 = this.f939p + this.f940q;
        if (t(this.f926b)) {
            r(this.f926b, i2, max3 + i14, i3, i13, iArr);
            int k2 = k(this.f926b) + this.f926b.getMeasuredWidth();
            i8 = l(this.f926b) + this.f926b.getMeasuredHeight();
            i9 = View.combineMeasuredStates(i6, this.f926b.getMeasuredState());
            i10 = k2;
        } else {
            i8 = 0;
            i9 = i6;
            i10 = 0;
        }
        if (t(this.f927c)) {
            i10 = Math.max(i10, r(this.f927c, i2, max3 + i14, i3, i8 + i13, iArr));
            i8 = l(this.f927c) + this.f927c.getMeasuredHeight() + i8;
            i9 = View.combineMeasuredStates(i9, this.f927c.getMeasuredState());
        }
        int max4 = Math.max(i5, i8);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop() + max4;
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(paddingRight + max3 + i10, getSuggestedMinimumWidth()), i2, (-16777216) & i9);
        int resolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i3, i9 << 16);
        if (this.f920P) {
            int childCount2 = getChildCount();
            for (int i15 = 0; i15 < childCount2; i15++) {
                View childAt2 = getChildAt(i15);
                if (!t(childAt2) || childAt2.getMeasuredWidth() <= 0 || childAt2.getMeasuredHeight() <= 0) {
                }
            }
            setMeasuredDimension(resolveSizeAndState, i11);
        }
        i11 = resolveSizeAndState2;
        setMeasuredDimension(resolveSizeAndState, i11);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        n nVar;
        MenuItem findItem;
        if (!(parcelable instanceof c1)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        c1 c1Var = (c1) parcelable;
        super.onRestoreInstanceState(c1Var.f517a);
        ActionMenuView actionMenuView = this.f925a;
        if (actionMenuView != null) {
            nVar = actionMenuView.f881p;
        } else {
            nVar = null;
        }
        int i2 = c1Var.f1884c;
        if (i2 != 0 && this.f917M != null && nVar != null && (findItem = nVar.findItem(i2)) != null) {
            findItem.expandActionView();
        }
        if (c1Var.f1885d) {
            b bVar = this.f924T;
            removeCallbacks(bVar);
            post(bVar);
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i2) {
        super.onRtlPropertiesChanged(i2);
        d();
        P0 p02 = this.f943t;
        boolean z2 = true;
        if (i2 != 1) {
            z2 = false;
        }
        if (z2 != p02.f1828g) {
            p02.f1828g = z2;
            if (p02.h) {
                if (z2) {
                    int i3 = p02.f1825d;
                    if (i3 == Integer.MIN_VALUE) {
                        i3 = p02.f1826e;
                    }
                    p02.f1822a = i3;
                    int i4 = p02.f1824c;
                    if (i4 == Integer.MIN_VALUE) {
                        i4 = p02.f1827f;
                    }
                    p02.f1823b = i4;
                    return;
                }
                int i5 = p02.f1824c;
                if (i5 == Integer.MIN_VALUE) {
                    i5 = p02.f1826e;
                }
                p02.f1822a = i5;
                int i6 = p02.f1825d;
                if (i6 == Integer.MIN_VALUE) {
                    i6 = p02.f1827f;
                }
                p02.f1823b = i6;
                return;
            }
            p02.f1822a = p02.f1826e;
            p02.f1823b = p02.f1827f;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [L.c, k.c1, android.os.Parcelable] */
    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        p pVar;
        ?? cVar = new c(super.onSaveInstanceState());
        Z0 z02 = this.f917M;
        if (z02 != null && (pVar = z02.f1872b) != null) {
            cVar.f1884c = pVar.f1687a;
        }
        cVar.f1885d = o();
        return cVar;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f906B = false;
        }
        if (!this.f906B) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.f906B = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f906B = false;
        }
        return true;
    }

    public final int p(View view, int i2, int i3, int[] iArr) {
        a1 a1Var = (a1) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) a1Var).leftMargin - iArr[0];
        int max = Math.max(0, i4) + i2;
        iArr[0] = Math.max(0, -i4);
        int j2 = j(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, j2, max + measuredWidth, view.getMeasuredHeight() + j2);
        return measuredWidth + ((ViewGroup.MarginLayoutParams) a1Var).rightMargin + max;
    }

    public final int q(View view, int i2, int i3, int[] iArr) {
        a1 a1Var = (a1) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) a1Var).rightMargin - iArr[1];
        int max = i2 - Math.max(0, i4);
        iArr[1] = Math.max(0, -i4);
        int j2 = j(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, j2, max, view.getMeasuredHeight() + j2);
        return max - (measuredWidth + ((ViewGroup.MarginLayoutParams) a1Var).leftMargin);
    }

    public final int r(View view, int i2, int i3, int i4, int i5, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i6 = marginLayoutParams.leftMargin - iArr[0];
        int i7 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i7) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i6);
        iArr[1] = Math.max(0, -i7);
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + max + i3, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i4, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    public final void s(View view, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i4, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public void setBackInvokedCallbackEnabled(boolean z2) {
        if (this.f923S != z2) {
            this.f923S = z2;
            v();
        }
    }

    public void setCollapseContentDescription(int i2) {
        setCollapseContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setCollapseIcon(int i2) {
        setCollapseIcon(d.v(getContext(), i2));
    }

    public void setCollapsible(boolean z2) {
        this.f920P = z2;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i2) {
        if (i2 < 0) {
            i2 = Integer.MIN_VALUE;
        }
        if (i2 != this.f945v) {
            this.f945v = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i2) {
        if (i2 < 0) {
            i2 = Integer.MIN_VALUE;
        }
        if (i2 != this.f944u) {
            this.f944u = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i2) {
        setLogo(d.v(getContext(), i2));
    }

    public void setLogoDescription(int i2) {
        setLogoDescription(getContext().getText(i2));
    }

    public void setNavigationContentDescription(int i2) {
        setNavigationContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setNavigationIcon(int i2) {
        setNavigationIcon(d.v(getContext(), i2));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        g();
        this.f928d.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(b1 b1Var) {
        this.f913I = b1Var;
    }

    public void setOverflowIcon(Drawable drawable) {
        e();
        this.f925a.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i2) {
        if (this.f934k != i2) {
            this.f934k = i2;
            if (i2 == 0) {
                this.f933j = getContext();
            } else {
                this.f933j = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public void setSubtitle(int i2) {
        setSubtitle(getContext().getText(i2));
    }

    public void setSubtitleTextColor(int i2) {
        setSubtitleTextColor(ColorStateList.valueOf(i2));
    }

    public void setTitle(int i2) {
        setTitle(getContext().getText(i2));
    }

    public void setTitleMarginBottom(int i2) {
        this.f942s = i2;
        requestLayout();
    }

    public void setTitleMarginEnd(int i2) {
        this.f940q = i2;
        requestLayout();
    }

    public void setTitleMarginStart(int i2) {
        this.f939p = i2;
        requestLayout();
    }

    public void setTitleMarginTop(int i2) {
        this.f941r = i2;
        requestLayout();
    }

    public void setTitleTextColor(int i2) {
        setTitleTextColor(ColorStateList.valueOf(i2));
    }

    public final boolean t(View view) {
        if (view != null && view.getParent() == this && view.getVisibility() != 8) {
            return true;
        }
        return false;
    }

    public final boolean u() {
        C0184k c0184k;
        ActionMenuView actionMenuView = this.f925a;
        if (actionMenuView != null && (c0184k = actionMenuView.f885t) != null && c0184k.l()) {
            return true;
        }
        return false;
    }

    public final void v() {
        boolean z2;
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        if (Build.VERSION.SDK_INT >= 33) {
            OnBackInvokedDispatcher a2 = Y0.a(this);
            Z0 z02 = this.f917M;
            if (z02 != null && z02.f1872b != null && a2 != null && isAttachedToWindow() && this.f923S) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2 && this.f922R == null) {
                if (this.f921Q == null) {
                    this.f921Q = Y0.b(new W0(this, 0));
                }
                Y0.c(a2, this.f921Q);
                this.f922R = a2;
                return;
            }
            if (!z2 && (onBackInvokedDispatcher = this.f922R) != null) {
                Y0.d(onBackInvokedDispatcher, this.f921Q);
                this.f922R = null;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [k.a1, android.view.ViewGroup$LayoutParams, android.view.ViewGroup$MarginLayoutParams] */
    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        ?? marginLayoutParams = new ViewGroup.MarginLayoutParams(context, attributeSet);
        marginLayoutParams.f1877a = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0101a.f1263b);
        marginLayoutParams.f1877a = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
        marginLayoutParams.f1878b = 0;
        return marginLayoutParams;
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            c();
        }
        C0211y c0211y = this.h;
        if (c0211y != null) {
            c0211y.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            c();
            this.h.setImageDrawable(drawable);
        } else {
            C0211y c0211y = this.h;
            if (c0211y != null) {
                c0211y.setImageDrawable(this.f930f);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            if (this.f929e == null) {
                this.f929e = new C0153A(getContext(), null, 0);
            }
            if (!n(this.f929e)) {
                b(this.f929e, true);
            }
        } else {
            C0153A c0153a = this.f929e;
            if (c0153a != null && n(c0153a)) {
                removeView(this.f929e);
                this.f909E.remove(this.f929e);
            }
        }
        C0153A c0153a2 = this.f929e;
        if (c0153a2 != null) {
            c0153a2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.f929e == null) {
            this.f929e = new C0153A(getContext(), null, 0);
        }
        C0153A c0153a = this.f929e;
        if (c0153a != null) {
            c0153a.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        C0211y c0211y = this.f928d;
        if (c0211y != null) {
            c0211y.setContentDescription(charSequence);
            d.H(this.f928d, charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            if (!n(this.f928d)) {
                b(this.f928d, true);
            }
        } else {
            C0211y c0211y = this.f928d;
            if (c0211y != null && n(c0211y)) {
                removeView(this.f928d);
                this.f909E.remove(this.f928d);
            }
        }
        C0211y c0211y2 = this.f928d;
        if (c0211y2 != null) {
            c0211y2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.f927c == null) {
                Context context = getContext();
                C0171d0 c0171d0 = new C0171d0(context, null);
                this.f927c = c0171d0;
                c0171d0.setSingleLine();
                this.f927c.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.f936m;
                if (i2 != 0) {
                    this.f927c.setTextAppearance(context, i2);
                }
                ColorStateList colorStateList = this.f905A;
                if (colorStateList != null) {
                    this.f927c.setTextColor(colorStateList);
                }
            }
            if (!n(this.f927c)) {
                b(this.f927c, true);
            }
        } else {
            C0171d0 c0171d02 = this.f927c;
            if (c0171d02 != null && n(c0171d02)) {
                removeView(this.f927c);
                this.f909E.remove(this.f927c);
            }
        }
        C0171d0 c0171d03 = this.f927c;
        if (c0171d03 != null) {
            c0171d03.setText(charSequence);
        }
        this.y = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.f905A = colorStateList;
        C0171d0 c0171d0 = this.f927c;
        if (c0171d0 != null) {
            c0171d0.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.f926b == null) {
                Context context = getContext();
                C0171d0 c0171d0 = new C0171d0(context, null);
                this.f926b = c0171d0;
                c0171d0.setSingleLine();
                this.f926b.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.f935l;
                if (i2 != 0) {
                    this.f926b.setTextAppearance(context, i2);
                }
                ColorStateList colorStateList = this.f948z;
                if (colorStateList != null) {
                    this.f926b.setTextColor(colorStateList);
                }
            }
            if (!n(this.f926b)) {
                b(this.f926b, true);
            }
        } else {
            C0171d0 c0171d02 = this.f926b;
            if (c0171d02 != null && n(c0171d02)) {
                removeView(this.f926b);
                this.f909E.remove(this.f926b);
            }
        }
        C0171d0 c0171d03 = this.f926b;
        if (c0171d03 != null) {
            c0171d03.setText(charSequence);
        }
        this.f947x = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.f948z = colorStateList;
        C0171d0 c0171d0 = this.f926b;
        if (c0171d0 != null) {
            c0171d0.setTextColor(colorStateList);
        }
    }
}

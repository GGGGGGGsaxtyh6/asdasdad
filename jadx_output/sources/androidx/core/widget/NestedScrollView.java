package androidx.core.widget;

import A.f;
import C0.d;
import D.b;
import H.C0008b;
import H.C0015i;
import H.C0026u;
import H.G;
import H.InterfaceC0024s;
import H.InterfaceC0025t;
import H.N;
import H.r;
import K.c;
import K.e;
import K.g;
import K.i;
import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class NestedScrollView extends FrameLayout implements InterfaceC0025t {

    /* renamed from: B, reason: collision with root package name */
    public static final float f986B = (float) (Math.log(0.78d) / Math.log(0.9d));

    /* renamed from: C, reason: collision with root package name */
    public static final e f987C = new C0008b();

    /* renamed from: D, reason: collision with root package name */
    public static final int[] f988D = {R.attr.fillViewport};

    /* renamed from: A, reason: collision with root package name */
    public final C0015i f989A;

    /* renamed from: a, reason: collision with root package name */
    public final float f990a;

    /* renamed from: b, reason: collision with root package name */
    public long f991b;

    /* renamed from: c, reason: collision with root package name */
    public final Rect f992c;

    /* renamed from: d, reason: collision with root package name */
    public final OverScroller f993d;

    /* renamed from: e, reason: collision with root package name */
    public final EdgeEffect f994e;

    /* renamed from: f, reason: collision with root package name */
    public final EdgeEffect f995f;

    /* renamed from: g, reason: collision with root package name */
    public int f996g;
    public boolean h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f997i;

    /* renamed from: j, reason: collision with root package name */
    public View f998j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f999k;

    /* renamed from: l, reason: collision with root package name */
    public VelocityTracker f1000l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f1001m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f1002n;

    /* renamed from: o, reason: collision with root package name */
    public final int f1003o;

    /* renamed from: p, reason: collision with root package name */
    public final int f1004p;

    /* renamed from: q, reason: collision with root package name */
    public final int f1005q;

    /* renamed from: r, reason: collision with root package name */
    public int f1006r;

    /* renamed from: s, reason: collision with root package name */
    public final int[] f1007s;

    /* renamed from: t, reason: collision with root package name */
    public final int[] f1008t;

    /* renamed from: u, reason: collision with root package name */
    public int f1009u;

    /* renamed from: v, reason: collision with root package name */
    public int f1010v;

    /* renamed from: w, reason: collision with root package name */
    public i f1011w;

    /* renamed from: x, reason: collision with root package name */
    public final C0026u f1012x;
    public final r y;

    /* renamed from: z, reason: collision with root package name */
    public float f1013z;

    /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.Object, H.u] */
    public NestedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, org.conscrypt.R.attr.nestedScrollViewStyle);
        EdgeEffect edgeEffect;
        EdgeEffect edgeEffect2;
        this.f992c = new Rect();
        this.h = true;
        this.f997i = false;
        this.f998j = null;
        this.f999k = false;
        this.f1002n = true;
        this.f1006r = -1;
        this.f1007s = new int[2];
        this.f1008t = new int[2];
        this.f989A = new C0015i(getContext(), new f(5, this));
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 31) {
            edgeEffect = c.a(context, attributeSet);
        } else {
            edgeEffect = new EdgeEffect(context);
        }
        this.f994e = edgeEffect;
        if (i2 >= 31) {
            edgeEffect2 = c.a(context, attributeSet);
        } else {
            edgeEffect2 = new EdgeEffect(context);
        }
        this.f995f = edgeEffect2;
        this.f990a = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        this.f993d = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f1003o = viewConfiguration.getScaledTouchSlop();
        this.f1004p = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f1005q = viewConfiguration.getScaledMaximumFlingVelocity();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f988D, org.conscrypt.R.attr.nestedScrollViewStyle, 0);
        setFillViewport(obtainStyledAttributes.getBoolean(0, false));
        obtainStyledAttributes.recycle();
        this.f1012x = new Object();
        this.y = new r(this);
        setNestedScrollingEnabled(true);
        N.h(this, f987C);
    }

    public static boolean m(View view, NestedScrollView nestedScrollView) {
        if (view == nestedScrollView) {
            return true;
        }
        Object parent = view.getParent();
        if ((parent instanceof ViewGroup) && m((View) parent, nestedScrollView)) {
            return true;
        }
        return false;
    }

    @Override // H.InterfaceC0024s
    public final void a(int i2, int i3, int[] iArr, int i4) {
        i(i2, i3, i4, iArr, null);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        if (getChildCount() <= 0) {
            super.addView(view);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // H.InterfaceC0024s
    public final void b(View view, View view2, int i2, int i3) {
        C0026u c0026u = this.f1012x;
        if (i3 == 1) {
            c0026u.f393b = i2;
        } else {
            c0026u.f392a = i2;
        }
        w(2, i3);
    }

    @Override // H.InterfaceC0024s
    public final void c(View view, int i2) {
        C0026u c0026u = this.f1012x;
        if (i2 == 1) {
            c0026u.f393b = 0;
        } else {
            c0026u.f392a = 0;
        }
        y(i2);
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f8  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void computeScroll() {
        /*
            Method dump skipped, instructions count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.computeScroll():void");
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int max = Math.max(0, bottom - height);
        if (scrollY < 0) {
            return bottom - scrollY;
        }
        if (scrollY > max) {
            return bottom + (scrollY - max);
        }
        return bottom;
    }

    @Override // H.InterfaceC0025t
    public final void d(NestedScrollView nestedScrollView, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        o(i5, i6, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!super.dispatchKeyEvent(keyEvent) && !j(keyEvent)) {
            return false;
        }
        return true;
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        ViewParent c2;
        r rVar = this.y;
        if (!rVar.f390d || (c2 = rVar.c(0)) == null) {
            return false;
        }
        try {
            return c2.onNestedFling(rVar.f389c, f2, f3, z2);
        } catch (AbstractMethodError e2) {
            Log.e("ViewParentCompat", "ViewParent " + c2 + " does not implement interface method onNestedFling", e2);
            return false;
        }
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f2, float f3) {
        return this.y.a(f2, f3);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return i(i2, i3, 0, iArr, iArr2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return this.y.b(i2, i3, i4, i5, iArr, 0, null);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int i2;
        super.draw(canvas);
        int scrollY = getScrollY();
        EdgeEffect edgeEffect = this.f994e;
        int i3 = 0;
        if (!edgeEffect.isFinished()) {
            int save = canvas.save();
            int width = getWidth();
            int height = getHeight();
            int min = Math.min(0, scrollY);
            if (getClipToPadding()) {
                width -= getPaddingRight() + getPaddingLeft();
                i2 = getPaddingLeft();
            } else {
                i2 = 0;
            }
            if (getClipToPadding()) {
                height -= getPaddingBottom() + getPaddingTop();
                min += getPaddingTop();
            }
            canvas.translate(i2, min);
            edgeEffect.setSize(width, height);
            if (edgeEffect.draw(canvas)) {
                postInvalidateOnAnimation();
            }
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect2 = this.f995f;
        if (!edgeEffect2.isFinished()) {
            int save2 = canvas.save();
            int width2 = getWidth();
            int height2 = getHeight();
            int max = Math.max(getScrollRange(), scrollY) + height2;
            if (getClipToPadding()) {
                width2 -= getPaddingRight() + getPaddingLeft();
                i3 = getPaddingLeft();
            }
            if (getClipToPadding()) {
                height2 -= getPaddingBottom() + getPaddingTop();
                max -= getPaddingBottom();
            }
            canvas.translate(i3 - width2, max);
            canvas.rotate(180.0f, width2, 0.0f);
            edgeEffect2.setSize(width2, height2);
            if (edgeEffect2.draw(canvas)) {
                postInvalidateOnAnimation();
            }
            canvas.restoreToCount(save2);
        }
    }

    @Override // H.InterfaceC0024s
    public final void e(NestedScrollView nestedScrollView, int i2, int i3, int i4, int i5, int i6) {
        o(i5, i6, null);
    }

    @Override // H.InterfaceC0024s
    public final boolean f(View view, View view2, int i2, int i3) {
        return (i2 & 2) != 0;
    }

    public final boolean g(int i2) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i2);
        int maxScrollAmount = getMaxScrollAmount();
        if (findNextFocus != null && n(findNextFocus, maxScrollAmount, getHeight())) {
            Rect rect = this.f992c;
            findNextFocus.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(findNextFocus, rect);
            t(h(rect), 0, 1, true);
            findNextFocus.requestFocus(i2);
        } else {
            if (i2 == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i2 == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getHeight() + getScrollY()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i2 != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            t(maxScrollAmount, 0, 1, true);
        }
        if (findFocus != null && findFocus.isFocused() && !n(findFocus, 0, getHeight())) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(131072);
            requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
    }

    @Override // android.view.View
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + layoutParams.bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return bottom / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (getHeight() * 0.5f);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        C0026u c0026u = this.f1012x;
        return c0026u.f393b | c0026u.f392a;
    }

    public int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    @Override // android.view.View
    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return scrollY / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public float getVerticalScrollFactorCompat() {
        if (this.f1013z == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                this.f1013z = typedValue.getDimension(context.getResources().getDisplayMetrics());
            } else {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
        }
        return this.f1013z;
    }

    public final int h(Rect rect) {
        int i2;
        int i3;
        int i4;
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i5 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        if (rect.bottom < childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin) {
            i2 = i5 - verticalFadingEdgeLength;
        } else {
            i2 = i5;
        }
        int i6 = rect.bottom;
        if (i6 > i2 && rect.top > scrollY) {
            if (rect.height() > height) {
                i4 = rect.top - scrollY;
            } else {
                i4 = rect.bottom - i2;
            }
            return Math.min(i4, (childAt.getBottom() + layoutParams.bottomMargin) - i5);
        }
        if (rect.top >= scrollY || i6 >= i2) {
            return 0;
        }
        if (rect.height() > height) {
            i3 = 0 - (i2 - rect.bottom);
        } else {
            i3 = 0 - (scrollY - rect.top);
        }
        return Math.max(i3, -getScrollY());
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        if (this.y.c(0) == null) {
            return false;
        }
        return true;
    }

    public final boolean i(int i2, int i3, int i4, int[] iArr, int[] iArr2) {
        ViewParent c2;
        int i5;
        int i6;
        r rVar = this.y;
        if (!rVar.f390d || (c2 = rVar.c(i4)) == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        NestedScrollView nestedScrollView = rVar.f389c;
        if (iArr2 != null) {
            nestedScrollView.getLocationInWindow(iArr2);
            i5 = iArr2[0];
            i6 = iArr2[1];
        } else {
            i5 = 0;
            i6 = 0;
        }
        if (iArr == null) {
            if (rVar.f391e == null) {
                rVar.f391e = new int[2];
            }
            iArr = rVar.f391e;
        }
        iArr[0] = 0;
        iArr[1] = 0;
        if (c2 instanceof InterfaceC0024s) {
            ((InterfaceC0024s) c2).a(i2, i3, iArr, i4);
        } else if (i4 == 0) {
            try {
                c2.onNestedPreScroll(nestedScrollView, i2, i3, iArr);
            } catch (AbstractMethodError e2) {
                Log.e("ViewParentCompat", "ViewParent " + c2 + " does not implement interface method onNestedPreScroll", e2);
            }
        }
        if (iArr2 != null) {
            nestedScrollView.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i5;
            iArr2[1] = iArr2[1] - i6;
        }
        if (iArr[0] == 0 && iArr[1] == 0) {
            return false;
        }
        return true;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return this.y.f390d;
    }

    public final boolean j(KeyEvent keyEvent) {
        this.f992c.setEmpty();
        int i2 = 130;
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            if (childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom()) {
                if (keyEvent.getAction() != 0) {
                    return false;
                }
                int keyCode = keyEvent.getKeyCode();
                if (keyCode != 19) {
                    if (keyCode != 20) {
                        if (keyCode != 62) {
                            if (keyCode != 92) {
                                if (keyCode != 93) {
                                    if (keyCode != 122) {
                                        if (keyCode != 123) {
                                            return false;
                                        }
                                        r(130);
                                        return false;
                                    }
                                    r(33);
                                    return false;
                                }
                                return l(130);
                            }
                            return l(33);
                        }
                        if (keyEvent.isShiftPressed()) {
                            i2 = 33;
                        }
                        r(i2);
                        return false;
                    }
                    if (keyEvent.isAltPressed()) {
                        return l(130);
                    }
                    return g(130);
                }
                if (keyEvent.isAltPressed()) {
                    return l(33);
                }
                return g(33);
            }
        }
        if (!isFocused() || keyEvent.getKeyCode() == 4) {
            return false;
        }
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, 130);
        if (findNextFocus == null || findNextFocus == this || !findNextFocus.requestFocus(130)) {
            return false;
        }
        return true;
    }

    public final void k(int i2) {
        if (getChildCount() > 0) {
            this.f993d.fling(getScrollX(), getScrollY(), 0, i2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            w(2, 1);
            this.f1010v = getScrollY();
            postInvalidateOnAnimation();
            if (b.a()) {
                K.f.a(this, Math.abs(this.f993d.getCurrVelocity()));
            }
        }
    }

    public final boolean l(int i2) {
        boolean z2;
        int childCount;
        if (i2 == 130) {
            z2 = true;
        } else {
            z2 = false;
        }
        int height = getHeight();
        Rect rect = this.f992c;
        rect.top = 0;
        rect.bottom = height;
        if (z2 && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            int paddingBottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            rect.bottom = paddingBottom;
            rect.top = paddingBottom - height;
        }
        return s(i2, rect.top, rect.bottom);
    }

    @Override // android.view.ViewGroup
    public final void measureChild(View view, int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft(), layoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    public final void measureChildWithMargins(View view, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public final boolean n(View view, int i2, int i3) {
        Rect rect = this.f992c;
        view.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(view, rect);
        if (rect.bottom + i2 >= getScrollY() && rect.top - i2 <= getScrollY() + i3) {
            return true;
        }
        return false;
    }

    public final void o(int i2, int i3, int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i2);
        int scrollY2 = getScrollY() - scrollY;
        if (iArr != null) {
            iArr[1] = iArr[1] + scrollY2;
        }
        this.y.b(0, scrollY2, 0, i2 - scrollY2, null, i3, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f997i = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:131:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02b5  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onGenericMotionEvent(android.view.MotionEvent r27) {
        /*
            Method dump skipped, instructions count: 851
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean z2 = true;
        if (action == 2 && this.f999k) {
            return true;
        }
        int i2 = action & 255;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 == 6) {
                            p(motionEvent);
                        }
                    }
                } else {
                    int i3 = this.f1006r;
                    if (i3 != -1) {
                        int findPointerIndex = motionEvent.findPointerIndex(i3);
                        if (findPointerIndex == -1) {
                            Log.e("NestedScrollView", "Invalid pointerId=" + i3 + " in onInterceptTouchEvent");
                        } else {
                            int y = (int) motionEvent.getY(findPointerIndex);
                            if (Math.abs(y - this.f996g) > this.f1003o && (2 & getNestedScrollAxes()) == 0) {
                                this.f999k = true;
                                this.f996g = y;
                                if (this.f1000l == null) {
                                    this.f1000l = VelocityTracker.obtain();
                                }
                                this.f1000l.addMovement(motionEvent);
                                this.f1009u = 0;
                                ViewParent parent = getParent();
                                if (parent != null) {
                                    parent.requestDisallowInterceptTouchEvent(true);
                                }
                            }
                        }
                    }
                }
            }
            this.f999k = false;
            this.f1006r = -1;
            VelocityTracker velocityTracker = this.f1000l;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f1000l = null;
            }
            if (this.f993d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                postInvalidateOnAnimation();
            }
            y(0);
        } else {
            int y2 = (int) motionEvent.getY();
            int x2 = (int) motionEvent.getX();
            if (getChildCount() > 0) {
                int scrollY = getScrollY();
                View childAt = getChildAt(0);
                if (y2 >= childAt.getTop() - scrollY && y2 < childAt.getBottom() - scrollY && x2 >= childAt.getLeft() && x2 < childAt.getRight()) {
                    this.f996g = y2;
                    this.f1006r = motionEvent.getPointerId(0);
                    VelocityTracker velocityTracker2 = this.f1000l;
                    if (velocityTracker2 == null) {
                        this.f1000l = VelocityTracker.obtain();
                    } else {
                        velocityTracker2.clear();
                    }
                    this.f1000l.addMovement(motionEvent);
                    this.f993d.computeScrollOffset();
                    if (!x(motionEvent) && this.f993d.isFinished()) {
                        z2 = false;
                    }
                    this.f999k = z2;
                    w(2, 0);
                }
            }
            if (!x(motionEvent) && this.f993d.isFinished()) {
                z2 = false;
            }
            this.f999k = z2;
            VelocityTracker velocityTracker3 = this.f1000l;
            if (velocityTracker3 != null) {
                velocityTracker3.recycle();
                this.f1000l = null;
            }
        }
        return this.f999k;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6;
        super.onLayout(z2, i2, i3, i4, i5);
        int i7 = 0;
        this.h = false;
        View view = this.f998j;
        if (view != null && m(view, this)) {
            View view2 = this.f998j;
            Rect rect = this.f992c;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int h = h(rect);
            if (h != 0) {
                scrollBy(0, h);
            }
        }
        this.f998j = null;
        if (!this.f997i) {
            if (this.f1011w != null) {
                scrollTo(getScrollX(), this.f1011w.f508a);
                this.f1011w = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                i6 = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            } else {
                i6 = 0;
            }
            int paddingTop = ((i5 - i3) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            if (paddingTop < i6 && scrollY >= 0) {
                i7 = paddingTop + scrollY > i6 ? i6 - paddingTop : scrollY;
            }
            if (i7 != scrollY) {
                scrollTo(getScrollX(), i7);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f997i = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f1001m && View.MeasureSpec.getMode(i3) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        if (!z2) {
            dispatchNestedFling(0.0f, f3, true);
            k((int) f3);
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f2, float f3) {
        return this.y.a(f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        i(i2, i3, 0, iArr, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        o(i5, 0, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i2) {
        b(view, view2, i2, 0);
    }

    @Override // android.view.View
    public final void onOverScrolled(int i2, int i3, boolean z2, boolean z3) {
        super.scrollTo(i2, i3);
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i2, Rect rect) {
        View findNextFocusFromRect;
        if (i2 == 2) {
            i2 = 130;
        } else if (i2 == 1) {
            i2 = 33;
        }
        if (rect == null) {
            findNextFocusFromRect = FocusFinder.getInstance().findNextFocus(this, null, i2);
        } else {
            findNextFocusFromRect = FocusFinder.getInstance().findNextFocusFromRect(this, rect, i2);
        }
        if (findNextFocusFromRect == null || !n(findNextFocusFromRect, 0, getHeight())) {
            return false;
        }
        return findNextFocusFromRect.requestFocus(i2, rect);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof i)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        i iVar = (i) parcelable;
        super.onRestoreInstanceState(iVar.getSuperState());
        this.f1011w = iVar;
        requestLayout();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [android.view.View$BaseSavedState, android.os.Parcelable, K.i] */
    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        ?? baseSavedState = new View.BaseSavedState(super.onSaveInstanceState());
        baseSavedState.f508a = getScrollY();
        return baseSavedState;
    }

    @Override // android.view.View
    public final void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        View findFocus = findFocus();
        if (findFocus != null && this != findFocus && n(findFocus, 0, i5)) {
            Rect rect = this.f992c;
            findFocus.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(findFocus, rect);
            int h = h(rect);
            if (h != 0) {
                if (this.f1002n) {
                    v(0, h, false);
                } else {
                    scrollBy(0, h);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i2) {
        return f(view, view2, i2, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        c(view, 0);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        if (this.f1000l == null) {
            this.f1000l = VelocityTracker.obtain();
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f1009u = 0;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        float f2 = 0.0f;
        obtain.offsetLocation(0.0f, this.f1009u);
        if (actionMasked != 0) {
            EdgeEffect edgeEffect = this.f995f;
            EdgeEffect edgeEffect2 = this.f994e;
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked != 3) {
                        if (actionMasked != 5) {
                            if (actionMasked == 6) {
                                p(motionEvent);
                                this.f996g = (int) motionEvent.getY(motionEvent.findPointerIndex(this.f1006r));
                            }
                        } else {
                            int actionIndex = motionEvent.getActionIndex();
                            this.f996g = (int) motionEvent.getY(actionIndex);
                            this.f1006r = motionEvent.getPointerId(actionIndex);
                        }
                    } else {
                        if (this.f999k && getChildCount() > 0 && this.f993d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                            postInvalidateOnAnimation();
                        }
                        this.f1006r = -1;
                        this.f999k = false;
                        VelocityTracker velocityTracker = this.f1000l;
                        if (velocityTracker != null) {
                            velocityTracker.recycle();
                            this.f1000l = null;
                        }
                        y(0);
                        this.f994e.onRelease();
                        this.f995f.onRelease();
                    }
                } else {
                    int findPointerIndex = motionEvent.findPointerIndex(this.f1006r);
                    if (findPointerIndex == -1) {
                        Log.e("NestedScrollView", "Invalid pointerId=" + this.f1006r + " in onTouchEvent");
                    } else {
                        int y = (int) motionEvent.getY(findPointerIndex);
                        int i2 = this.f996g - y;
                        float x2 = motionEvent.getX(findPointerIndex) / getWidth();
                        float height = i2 / getHeight();
                        if (d.u(edgeEffect2) != 0.0f) {
                            float f3 = -d.B(edgeEffect2, -height, x2);
                            if (d.u(edgeEffect2) == 0.0f) {
                                edgeEffect2.onRelease();
                            }
                            f2 = f3;
                        } else if (d.u(edgeEffect) != 0.0f) {
                            float B2 = d.B(edgeEffect, height, 1.0f - x2);
                            if (d.u(edgeEffect) == 0.0f) {
                                edgeEffect.onRelease();
                            }
                            f2 = B2;
                        }
                        int round = Math.round(f2 * getHeight());
                        if (round != 0) {
                            invalidate();
                        }
                        int i3 = i2 - round;
                        if (!this.f999k && Math.abs(i3) > this.f1003o) {
                            ViewParent parent2 = getParent();
                            if (parent2 != null) {
                                parent2.requestDisallowInterceptTouchEvent(true);
                            }
                            this.f999k = true;
                            i3 = i3 > 0 ? i3 - this.f1003o : i3 + this.f1003o;
                        }
                        if (this.f999k) {
                            int t2 = t(i3, (int) motionEvent.getX(findPointerIndex), 0, false);
                            this.f996g = y - t2;
                            this.f1009u += t2;
                        }
                    }
                }
            } else {
                VelocityTracker velocityTracker2 = this.f1000l;
                velocityTracker2.computeCurrentVelocity(1000, this.f1005q);
                int yVelocity = (int) velocityTracker2.getYVelocity(this.f1006r);
                if (Math.abs(yVelocity) >= this.f1004p) {
                    if (d.u(edgeEffect2) != 0.0f) {
                        if (u(edgeEffect2, yVelocity)) {
                            edgeEffect2.onAbsorb(yVelocity);
                        } else {
                            k(-yVelocity);
                        }
                    } else if (d.u(edgeEffect) != 0.0f) {
                        int i4 = -yVelocity;
                        if (u(edgeEffect, i4)) {
                            edgeEffect.onAbsorb(i4);
                        } else {
                            k(i4);
                        }
                    } else {
                        int i5 = -yVelocity;
                        float f4 = i5;
                        if (!this.y.a(0.0f, f4)) {
                            dispatchNestedFling(0.0f, f4, true);
                            k(i5);
                        }
                    }
                } else if (this.f993d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    postInvalidateOnAnimation();
                }
                this.f1006r = -1;
                this.f999k = false;
                VelocityTracker velocityTracker3 = this.f1000l;
                if (velocityTracker3 != null) {
                    velocityTracker3.recycle();
                    this.f1000l = null;
                }
                y(0);
                this.f994e.onRelease();
                this.f995f.onRelease();
            }
        } else {
            if (getChildCount() == 0) {
                return false;
            }
            if (this.f999k && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.f993d.isFinished()) {
                this.f993d.abortAnimation();
                y(1);
            }
            int y2 = (int) motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            this.f996g = y2;
            this.f1006r = pointerId;
            w(2, 0);
        }
        VelocityTracker velocityTracker4 = this.f1000l;
        if (velocityTracker4 != null) {
            velocityTracker4.addMovement(obtain);
        }
        obtain.recycle();
        return true;
    }

    public final void p(MotionEvent motionEvent) {
        int i2;
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f1006r) {
            if (actionIndex == 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            this.f996g = (int) motionEvent.getY(i2);
            this.f1006r = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.f1000l;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public final boolean q(int i2, int i3, int i4, int i5) {
        boolean z2;
        boolean z3;
        getOverScrollMode();
        super.computeHorizontalScrollRange();
        super.computeHorizontalScrollExtent();
        computeVerticalScrollRange();
        super.computeVerticalScrollExtent();
        int i6 = i4 + i2;
        if (i3 > 0 || i3 < 0) {
            i3 = 0;
            z2 = true;
        } else {
            z2 = false;
        }
        if (i6 <= i5) {
            if (i6 < 0) {
                i5 = 0;
            } else {
                i5 = i6;
                z3 = false;
                if (z3 && this.y.c(1) == null) {
                    this.f993d.springBack(i3, i5, 0, 0, 0, getScrollRange());
                }
                super.scrollTo(i3, i5);
                if (z2 && !z3) {
                    return false;
                }
            }
        }
        z3 = true;
        if (z3) {
            this.f993d.springBack(i3, i5, 0, 0, 0, getScrollRange());
        }
        super.scrollTo(i3, i5);
        return z2 ? true : true;
    }

    public final void r(int i2) {
        boolean z2;
        if (i2 == 130) {
            z2 = true;
        } else {
            z2 = false;
        }
        int height = getHeight();
        Rect rect = this.f992c;
        if (z2) {
            rect.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                int paddingBottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
                if (rect.top + height > paddingBottom) {
                    rect.top = paddingBottom - height;
                }
            }
        } else {
            int scrollY = getScrollY() - height;
            rect.top = scrollY;
            if (scrollY < 0) {
                rect.top = 0;
            }
        }
        int i3 = rect.top;
        int i4 = height + i3;
        rect.bottom = i4;
        s(i2, i3, i4);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        if (!this.h) {
            Rect rect = this.f992c;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int h = h(rect);
            if (h != 0) {
                scrollBy(0, h);
            }
        } else {
            this.f998j = view2;
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        boolean z3;
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        int h = h(rect);
        if (h != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            if (z2) {
                scrollBy(0, h);
            } else {
                v(0, h, false);
            }
        }
        return z3;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z2) {
        VelocityTracker velocityTracker;
        if (z2 && (velocityTracker = this.f1000l) != null) {
            velocityTracker.recycle();
            this.f1000l = null;
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        this.h = true;
        super.requestLayout();
    }

    public final boolean s(int i2, int i3, int i4) {
        boolean z2;
        int i5;
        boolean z3;
        boolean z4;
        boolean z5;
        int height = getHeight();
        int scrollY = getScrollY();
        int i6 = height + scrollY;
        if (i2 == 33) {
            z2 = true;
        } else {
            z2 = false;
        }
        ArrayList<View> focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z6 = false;
        for (int i7 = 0; i7 < size; i7++) {
            View view2 = focusables.get(i7);
            int top = view2.getTop();
            int bottom = view2.getBottom();
            if (i3 < bottom && top < i4) {
                if (i3 < top && bottom < i4) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (view == null) {
                    view = view2;
                    z6 = z4;
                } else {
                    if ((z2 && top < view.getTop()) || (!z2 && bottom > view.getBottom())) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (z6) {
                        if (z4) {
                            if (!z5) {
                            }
                            view = view2;
                        }
                    } else if (z4) {
                        view = view2;
                        z6 = true;
                    } else {
                        if (!z5) {
                        }
                        view = view2;
                    }
                }
            }
        }
        if (view == null) {
            view = this;
        }
        if (i3 >= scrollY && i4 <= i6) {
            z3 = false;
        } else {
            if (z2) {
                i5 = i3 - scrollY;
            } else {
                i5 = i4 - i6;
            }
            t(i5, 0, 1, true);
            z3 = true;
        }
        if (view != findFocus()) {
            view.requestFocus(i2);
        }
        return z3;
    }

    @Override // android.view.View
    public final void scrollTo(int i2, int i3) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int width2 = childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int height2 = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (width < width2 && i2 >= 0) {
                if (width + i2 > width2) {
                    i2 = width2 - width;
                }
            } else {
                i2 = 0;
            }
            if (height < height2 && i3 >= 0) {
                if (height + i3 > height2) {
                    i3 = height2 - height;
                }
            } else {
                i3 = 0;
            }
            if (i2 != getScrollX() || i3 != getScrollY()) {
                super.scrollTo(i2, i3);
            }
        }
    }

    public void setFillViewport(boolean z2) {
        if (z2 != this.f1001m) {
            this.f1001m = z2;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z2) {
        r rVar = this.y;
        if (rVar.f390d) {
            WeakHashMap weakHashMap = N.f327a;
            G.i(rVar.f389c);
        }
        rVar.f390d = z2;
    }

    public void setSmoothScrollingEnabled(boolean z2) {
        this.f1002n = z2;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i2) {
        return w(i2, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        y(0);
    }

    public final int t(int i2, int i3, int i4, boolean z2) {
        int i5;
        int i6;
        boolean z3;
        boolean z4;
        VelocityTracker velocityTracker;
        if (i4 == 1) {
            w(2, i4);
        }
        boolean i7 = i(0, i2, i4, this.f1008t, this.f1007s);
        int[] iArr = this.f1008t;
        int[] iArr2 = this.f1007s;
        if (i7) {
            i5 = i2 - iArr[1];
            i6 = iArr2[1];
        } else {
            i5 = i2;
            i6 = 0;
        }
        int scrollY = getScrollY();
        int scrollRange = getScrollRange();
        int overScrollMode = getOverScrollMode();
        if ((overScrollMode == 0 || (overScrollMode == 1 && getScrollRange() > 0)) && !z2) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (q(i5, 0, scrollY, scrollRange) && this.y.c(i4) == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        int scrollY2 = getScrollY() - scrollY;
        iArr[1] = 0;
        this.y.b(0, scrollY2, 0, i5 - scrollY2, this.f1007s, i4, iArr);
        int i8 = i6 + iArr2[1];
        int i9 = i5 - iArr[1];
        int i10 = scrollY + i9;
        EdgeEffect edgeEffect = this.f995f;
        EdgeEffect edgeEffect2 = this.f994e;
        if (i10 < 0) {
            if (z3) {
                d.B(edgeEffect2, (-i9) / getHeight(), i3 / getWidth());
                if (!edgeEffect.isFinished()) {
                    edgeEffect.onRelease();
                }
            }
        } else if (i10 > scrollRange && z3) {
            d.B(edgeEffect, i9 / getHeight(), 1.0f - (i3 / getWidth()));
            if (!edgeEffect2.isFinished()) {
                edgeEffect2.onRelease();
            }
        }
        if (!edgeEffect2.isFinished() || !edgeEffect.isFinished()) {
            postInvalidateOnAnimation();
            z4 = false;
        }
        if (z4 && i4 == 0 && (velocityTracker = this.f1000l) != null) {
            velocityTracker.clear();
        }
        if (i4 == 1) {
            y(i4);
            edgeEffect2.onRelease();
            edgeEffect.onRelease();
        }
        return i8;
    }

    public final boolean u(EdgeEffect edgeEffect, int i2) {
        if (i2 > 0) {
            return true;
        }
        float u2 = d.u(edgeEffect) * getHeight();
        float abs = Math.abs(-i2) * 0.35f;
        float f2 = this.f990a * 0.015f;
        double log = Math.log(abs / f2);
        double d2 = f986B;
        if (((float) (Math.exp((d2 / (d2 - 1.0d)) * log) * f2)) < u2) {
            return true;
        }
        return false;
    }

    public final void v(int i2, int i3, boolean z2) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.f991b > 250) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            this.f993d.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(i3 + scrollY, Math.max(0, height - height2))) - scrollY, 250);
            if (z2) {
                w(2, 1);
            } else {
                y(1);
            }
            this.f1010v = getScrollY();
            postInvalidateOnAnimation();
        } else {
            if (!this.f993d.isFinished()) {
                this.f993d.abortAnimation();
                y(1);
            }
            scrollBy(i2, i3);
        }
        this.f991b = AnimationUtils.currentAnimationTimeMillis();
    }

    public final boolean w(int i2, int i3) {
        boolean z2;
        boolean onStartNestedScroll;
        r rVar = this.y;
        if (rVar.c(i3) != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            if (!rVar.f390d) {
                return false;
            }
            View view = rVar.f389c;
            View view2 = view;
            for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
                boolean z3 = parent instanceof InterfaceC0024s;
                if (z3) {
                    onStartNestedScroll = ((InterfaceC0024s) parent).f(view2, view, i2, i3);
                } else {
                    if (i3 == 0) {
                        try {
                            onStartNestedScroll = parent.onStartNestedScroll(view2, view, i2);
                        } catch (AbstractMethodError e2) {
                            Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onStartNestedScroll", e2);
                        }
                    }
                    onStartNestedScroll = false;
                }
                if (onStartNestedScroll) {
                    if (i3 != 0) {
                        if (i3 == 1) {
                            rVar.f388b = parent;
                        }
                    } else {
                        rVar.f387a = parent;
                    }
                    if (z3) {
                        ((InterfaceC0024s) parent).b(view2, view, i2, i3);
                    } else if (i3 == 0) {
                        try {
                            parent.onNestedScrollAccepted(view2, view, i2);
                        } catch (AbstractMethodError e3) {
                            Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onNestedScrollAccepted", e3);
                        }
                    }
                } else {
                    if (parent instanceof View) {
                        view2 = parent;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final boolean x(MotionEvent motionEvent) {
        boolean z2;
        EdgeEffect edgeEffect = this.f994e;
        if (d.u(edgeEffect) != 0.0f) {
            d.B(edgeEffect, 0.0f, motionEvent.getX() / getWidth());
            z2 = true;
        } else {
            z2 = false;
        }
        EdgeEffect edgeEffect2 = this.f995f;
        if (d.u(edgeEffect2) != 0.0f) {
            d.B(edgeEffect2, 0.0f, 1.0f - (motionEvent.getX() / getWidth()));
            return true;
        }
        return z2;
    }

    public final void y(int i2) {
        r rVar = this.y;
        ViewParent c2 = rVar.c(i2);
        if (c2 != null) {
            boolean z2 = c2 instanceof InterfaceC0024s;
            NestedScrollView nestedScrollView = rVar.f389c;
            if (z2) {
                ((InterfaceC0024s) c2).c(nestedScrollView, i2);
            } else if (i2 == 0) {
                try {
                    c2.onStopNestedScroll(nestedScrollView);
                } catch (AbstractMethodError e2) {
                    Log.e("ViewParentCompat", "ViewParent " + c2 + " does not implement interface method onStopNestedScroll", e2);
                }
            }
            if (i2 != 0) {
                if (i2 == 1) {
                    rVar.f388b = null;
                    return;
                }
                return;
            }
            rVar.f387a = null;
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i2) {
        if (getChildCount() <= 0) {
            super.addView(view, i2);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i2, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void setOnScrollChangeListener(g gVar) {
    }
}

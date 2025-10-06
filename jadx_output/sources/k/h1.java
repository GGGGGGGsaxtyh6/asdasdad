package k;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import java.util.WeakHashMap;
import org.conscrypt.R;

/* loaded from: classes.dex */
public final class h1 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {

    /* renamed from: k, reason: collision with root package name */
    public static h1 f1914k;

    /* renamed from: l, reason: collision with root package name */
    public static h1 f1915l;

    /* renamed from: a, reason: collision with root package name */
    public final View f1916a;

    /* renamed from: b, reason: collision with root package name */
    public final CharSequence f1917b;

    /* renamed from: c, reason: collision with root package name */
    public final int f1918c;

    /* renamed from: d, reason: collision with root package name */
    public final g1 f1919d;

    /* renamed from: e, reason: collision with root package name */
    public final g1 f1920e;

    /* renamed from: f, reason: collision with root package name */
    public int f1921f;

    /* renamed from: g, reason: collision with root package name */
    public int f1922g;
    public i1 h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f1923i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f1924j;

    /* JADX WARN: Type inference failed for: r0v0, types: [k.g1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [k.g1] */
    public h1(View view, CharSequence charSequence) {
        int scaledTouchSlop;
        final int i2 = 0;
        this.f1919d = new Runnable(this) { // from class: k.g1

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ h1 f1912b;

            {
                this.f1912b = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i2) {
                    case 0:
                        this.f1912b.c(false);
                        return;
                    default:
                        this.f1912b.a();
                        return;
                }
            }
        };
        final int i3 = 1;
        this.f1920e = new Runnable(this) { // from class: k.g1

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ h1 f1912b;

            {
                this.f1912b = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i3) {
                    case 0:
                        this.f1912b.c(false);
                        return;
                    default:
                        this.f1912b.a();
                        return;
                }
            }
        };
        this.f1916a = view;
        this.f1917b = charSequence;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        int i4 = H.O.f332a;
        if (Build.VERSION.SDK_INT >= 28) {
            scaledTouchSlop = B.a.c(viewConfiguration);
        } else {
            scaledTouchSlop = viewConfiguration.getScaledTouchSlop() / 2;
        }
        this.f1918c = scaledTouchSlop;
        this.f1924j = true;
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    public static void b(h1 h1Var) {
        h1 h1Var2 = f1914k;
        if (h1Var2 != null) {
            h1Var2.f1916a.removeCallbacks(h1Var2.f1919d);
        }
        f1914k = h1Var;
        if (h1Var != null) {
            h1Var.f1916a.postDelayed(h1Var.f1919d, ViewConfiguration.getLongPressTimeout());
        }
    }

    public final void a() {
        h1 h1Var = f1915l;
        View view = this.f1916a;
        if (h1Var == this) {
            f1915l = null;
            i1 i1Var = this.h;
            if (i1Var != null) {
                View view2 = i1Var.f1940b;
                if (view2.getParent() != null) {
                    ((WindowManager) i1Var.f1939a.getSystemService("window")).removeView(view2);
                }
                this.h = null;
                this.f1924j = true;
                view.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (f1914k == this) {
            b(null);
        }
        view.removeCallbacks(this.f1920e);
    }

    public final void c(boolean z2) {
        int height;
        int i2;
        int i3;
        String str;
        int i4;
        String str2;
        int i5;
        long longPressTimeout;
        long j2;
        long j3;
        View view = this.f1916a;
        if (!view.isAttachedToWindow()) {
            return;
        }
        b(null);
        h1 h1Var = f1915l;
        if (h1Var != null) {
            h1Var.a();
        }
        f1915l = this;
        this.f1923i = z2;
        i1 i1Var = new i1(view.getContext());
        this.h = i1Var;
        int i6 = this.f1921f;
        int i7 = this.f1922g;
        boolean z3 = this.f1923i;
        View view2 = i1Var.f1940b;
        ViewParent parent = view2.getParent();
        Context context = i1Var.f1939a;
        if (parent != null && view2.getParent() != null) {
            ((WindowManager) context.getSystemService("window")).removeView(view2);
        }
        i1Var.f1941c.setText(this.f1917b);
        WindowManager.LayoutParams layoutParams = i1Var.f1942d;
        layoutParams.token = view.getApplicationWindowToken();
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_threshold);
        if (view.getWidth() < dimensionPixelOffset) {
            i6 = view.getWidth() / 2;
        }
        if (view.getHeight() >= dimensionPixelOffset) {
            int dimensionPixelOffset2 = context.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_extra_offset);
            height = i7 + dimensionPixelOffset2;
            i2 = i7 - dimensionPixelOffset2;
        } else {
            height = view.getHeight();
            i2 = 0;
        }
        layoutParams.gravity = 49;
        Resources resources = context.getResources();
        if (z3) {
            i3 = R.dimen.tooltip_y_offset_touch;
        } else {
            i3 = R.dimen.tooltip_y_offset_non_touch;
        }
        int dimensionPixelOffset3 = resources.getDimensionPixelOffset(i3);
        View rootView = view.getRootView();
        ViewGroup.LayoutParams layoutParams2 = rootView.getLayoutParams();
        if (!(layoutParams2 instanceof WindowManager.LayoutParams) || ((WindowManager.LayoutParams) layoutParams2).type != 2) {
            Context context2 = view.getContext();
            while (true) {
                if (!(context2 instanceof ContextWrapper)) {
                    break;
                }
                if (context2 instanceof Activity) {
                    rootView = ((Activity) context2).getWindow().getDecorView();
                    break;
                }
                context2 = ((ContextWrapper) context2).getBaseContext();
            }
        }
        if (rootView == null) {
            Log.e("TooltipPopup", "Cannot find app view");
            str2 = "window";
        } else {
            Rect rect = i1Var.f1943e;
            rootView.getWindowVisibleDisplayFrame(rect);
            if (rect.left >= 0 || rect.top >= 0) {
                str = "window";
                i4 = 0;
            } else {
                Resources resources2 = context.getResources();
                str = "window";
                int identifier = resources2.getIdentifier("status_bar_height", "dimen", "android");
                if (identifier != 0) {
                    i5 = resources2.getDimensionPixelSize(identifier);
                } else {
                    i5 = 0;
                }
                DisplayMetrics displayMetrics = resources2.getDisplayMetrics();
                i4 = 0;
                rect.set(0, i5, displayMetrics.widthPixels, displayMetrics.heightPixels);
            }
            int[] iArr = i1Var.f1945g;
            rootView.getLocationOnScreen(iArr);
            int[] iArr2 = i1Var.f1944f;
            view.getLocationOnScreen(iArr2);
            int i8 = iArr2[i4] - iArr[i4];
            iArr2[i4] = i8;
            iArr2[1] = iArr2[1] - iArr[1];
            layoutParams.x = (i8 + i6) - (rootView.getWidth() / 2);
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i4, i4);
            view2.measure(makeMeasureSpec, makeMeasureSpec);
            int measuredHeight = view2.getMeasuredHeight();
            int i9 = iArr2[1];
            int i10 = ((i2 + i9) - dimensionPixelOffset3) - measuredHeight;
            int i11 = i9 + height + dimensionPixelOffset3;
            if (z3) {
                if (i10 >= 0) {
                    layoutParams.y = i10;
                } else {
                    layoutParams.y = i11;
                }
            } else if (measuredHeight + i11 <= rect.height()) {
                layoutParams.y = i11;
            } else {
                layoutParams.y = i10;
            }
            str2 = str;
        }
        ((WindowManager) context.getSystemService(str2)).addView(view2, layoutParams);
        view.addOnAttachStateChangeListener(this);
        if (this.f1923i) {
            j3 = 2500;
        } else {
            WeakHashMap weakHashMap = H.N.f327a;
            if ((view.getWindowSystemUiVisibility() & 1) == 1) {
                longPressTimeout = ViewConfiguration.getLongPressTimeout();
                j2 = 3000;
            } else {
                longPressTimeout = ViewConfiguration.getLongPressTimeout();
                j2 = 15000;
            }
            j3 = j2 - longPressTimeout;
        }
        g1 g1Var = this.f1920e;
        view.removeCallbacks(g1Var);
        view.postDelayed(g1Var, j3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0064, code lost:
    
        if (java.lang.Math.abs(r5 - r3.f1922g) <= r2) goto L29;
     */
    @Override // android.view.View.OnHoverListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onHover(android.view.View r4, android.view.MotionEvent r5) {
        /*
            r3 = this;
            k.i1 r4 = r3.h
            r0 = 0
            if (r4 == 0) goto La
            boolean r4 = r3.f1923i
            if (r4 == 0) goto La
            return r0
        La:
            android.view.View r4 = r3.f1916a
            android.content.Context r1 = r4.getContext()
            java.lang.String r2 = "accessibility"
            java.lang.Object r1 = r1.getSystemService(r2)
            android.view.accessibility.AccessibilityManager r1 = (android.view.accessibility.AccessibilityManager) r1
            boolean r2 = r1.isEnabled()
            if (r2 == 0) goto L25
            boolean r1 = r1.isTouchExplorationEnabled()
            if (r1 == 0) goto L25
            return r0
        L25:
            int r1 = r5.getAction()
            r2 = 7
            if (r1 == r2) goto L38
            r4 = 10
            if (r1 == r4) goto L31
            goto L6f
        L31:
            r4 = 1
            r3.f1924j = r4
            r3.a()
            goto L6f
        L38:
            boolean r4 = r4.isEnabled()
            if (r4 == 0) goto L6f
            k.i1 r4 = r3.h
            if (r4 != 0) goto L6f
            float r4 = r5.getX()
            int r4 = (int) r4
            float r5 = r5.getY()
            int r5 = (int) r5
            boolean r1 = r3.f1924j
            if (r1 != 0) goto L66
            int r1 = r3.f1921f
            int r1 = r4 - r1
            int r1 = java.lang.Math.abs(r1)
            int r2 = r3.f1918c
            if (r1 > r2) goto L66
            int r1 = r3.f1922g
            int r1 = r5 - r1
            int r1 = java.lang.Math.abs(r1)
            if (r1 <= r2) goto L6f
        L66:
            r3.f1921f = r4
            r3.f1922g = r5
            r3.f1924j = r0
            b(r3)
        L6f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: k.h1.onHover(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        this.f1921f = view.getWidth() / 2;
        this.f1922g = view.getHeight() / 2;
        c(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        a();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}

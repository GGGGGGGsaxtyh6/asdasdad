package androidx.appcompat.widget;

import C0.d;
import H.N;
import H.S;
import Z.q;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import f.AbstractC0101a;
import i.AbstractC0120b;
import j.InterfaceC0134B;
import j.n;
import k.C0164a;
import k.C0176g;
import k.C0184k;
import k.m1;
import org.conscrypt.R;

/* loaded from: classes.dex */
public class ActionBarContextView extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    public final C0164a f832a;

    /* renamed from: b, reason: collision with root package name */
    public final Context f833b;

    /* renamed from: c, reason: collision with root package name */
    public ActionMenuView f834c;

    /* renamed from: d, reason: collision with root package name */
    public C0184k f835d;

    /* renamed from: e, reason: collision with root package name */
    public int f836e;

    /* renamed from: f, reason: collision with root package name */
    public S f837f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f838g;
    public boolean h;

    /* renamed from: i, reason: collision with root package name */
    public CharSequence f839i;

    /* renamed from: j, reason: collision with root package name */
    public CharSequence f840j;

    /* renamed from: k, reason: collision with root package name */
    public View f841k;

    /* renamed from: l, reason: collision with root package name */
    public View f842l;

    /* renamed from: m, reason: collision with root package name */
    public View f843m;

    /* renamed from: n, reason: collision with root package name */
    public LinearLayout f844n;

    /* renamed from: o, reason: collision with root package name */
    public TextView f845o;

    /* renamed from: p, reason: collision with root package name */
    public TextView f846p;

    /* renamed from: q, reason: collision with root package name */
    public final int f847q;

    /* renamed from: r, reason: collision with root package name */
    public final int f848r;

    /* renamed from: s, reason: collision with root package name */
    public boolean f849s;

    /* renamed from: t, reason: collision with root package name */
    public final int f850t;

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.actionModeStyle);
        Drawable drawable;
        int resourceId;
        this.f832a = new C0164a(this);
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true) && typedValue.resourceId != 0) {
            this.f833b = new ContextThemeWrapper(context, typedValue.resourceId);
        } else {
            this.f833b = context;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0101a.f1265d, R.attr.actionModeStyle, 0);
        if (obtainStyledAttributes.hasValue(0) && (resourceId = obtainStyledAttributes.getResourceId(0, 0)) != 0) {
            drawable = d.v(context, resourceId);
        } else {
            drawable = obtainStyledAttributes.getDrawable(0);
        }
        setBackground(drawable);
        this.f847q = obtainStyledAttributes.getResourceId(5, 0);
        this.f848r = obtainStyledAttributes.getResourceId(4, 0);
        this.f836e = obtainStyledAttributes.getLayoutDimension(3, 0);
        this.f850t = obtainStyledAttributes.getResourceId(2, R.layout.abc_action_mode_close_item_material);
        obtainStyledAttributes.recycle();
    }

    public static int f(View view, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE), i3);
        return Math.max(0, i2 - view.getMeasuredWidth());
    }

    public static int g(View view, int i2, int i3, int i4, boolean z2) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i5 = ((i4 - measuredHeight) / 2) + i3;
        if (z2) {
            view.layout(i2 - measuredWidth, i5, i2, measuredHeight + i5);
        } else {
            view.layout(i2, i5, i2 + measuredWidth, measuredHeight + i5);
        }
        if (z2) {
            return -measuredWidth;
        }
        return measuredWidth;
    }

    public final void c(AbstractC0120b abstractC0120b) {
        View view = this.f841k;
        if (view == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(this.f850t, (ViewGroup) this, false);
            this.f841k = inflate;
            addView(inflate);
        } else if (view.getParent() == null) {
            addView(this.f841k);
        }
        View findViewById = this.f841k.findViewById(R.id.action_mode_close_button);
        this.f842l = findViewById;
        findViewById.setOnClickListener(new q(2, abstractC0120b));
        n c2 = abstractC0120b.c();
        C0184k c0184k = this.f835d;
        if (c0184k != null) {
            c0184k.e();
            C0176g c0176g = c0184k.f1969t;
            if (c0176g != null && c0176g.b()) {
                c0176g.f1730i.dismiss();
            }
        }
        C0184k c0184k2 = new C0184k(getContext());
        this.f835d = c0184k2;
        c0184k2.f1961l = true;
        c0184k2.f1962m = true;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        c2.b(this.f835d, this.f833b);
        C0184k c0184k3 = this.f835d;
        InterfaceC0134B interfaceC0134B = c0184k3.h;
        if (interfaceC0134B == null) {
            InterfaceC0134B interfaceC0134B2 = (InterfaceC0134B) c0184k3.f1954d.inflate(c0184k3.f1956f, (ViewGroup) this, false);
            c0184k3.h = interfaceC0134B2;
            interfaceC0134B2.a(c0184k3.f1953c);
            c0184k3.c();
        }
        InterfaceC0134B interfaceC0134B3 = c0184k3.h;
        if (interfaceC0134B != interfaceC0134B3) {
            ((ActionMenuView) interfaceC0134B3).setPresenter(c0184k3);
        }
        ActionMenuView actionMenuView = (ActionMenuView) interfaceC0134B3;
        this.f834c = actionMenuView;
        actionMenuView.setBackground(null);
        addView(this.f834c, layoutParams);
    }

    public final void d() {
        int i2;
        if (this.f844n == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f844n = linearLayout;
            this.f845o = (TextView) linearLayout.findViewById(R.id.action_bar_title);
            this.f846p = (TextView) this.f844n.findViewById(R.id.action_bar_subtitle);
            int i3 = this.f847q;
            if (i3 != 0) {
                this.f845o.setTextAppearance(getContext(), i3);
            }
            int i4 = this.f848r;
            if (i4 != 0) {
                this.f846p.setTextAppearance(getContext(), i4);
            }
        }
        this.f845o.setText(this.f839i);
        this.f846p.setText(this.f840j);
        boolean isEmpty = TextUtils.isEmpty(this.f839i);
        boolean isEmpty2 = TextUtils.isEmpty(this.f840j);
        TextView textView = this.f846p;
        int i5 = 8;
        if (!isEmpty2) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        textView.setVisibility(i2);
        LinearLayout linearLayout2 = this.f844n;
        if (!isEmpty || !isEmpty2) {
            i5 = 0;
        }
        linearLayout2.setVisibility(i5);
        if (this.f844n.getParent() == null) {
            addView(this.f844n);
        }
    }

    public final void e() {
        removeAllViews();
        this.f843m = null;
        this.f834c = null;
        this.f835d = null;
        View view = this.f842l;
        if (view != null) {
            view.setOnClickListener(null);
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public int getAnimatedVisibility() {
        if (this.f837f != null) {
            return this.f832a.f1875b;
        }
        return getVisibility();
    }

    public int getContentHeight() {
        return this.f836e;
    }

    public CharSequence getSubtitle() {
        return this.f840j;
    }

    public CharSequence getTitle() {
        return this.f839i;
    }

    @Override // android.view.View
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public final void setVisibility(int i2) {
        if (i2 != getVisibility()) {
            S s2 = this.f837f;
            if (s2 != null) {
                s2.b();
            }
            super.setVisibility(i2);
        }
    }

    public final S i(long j2, int i2) {
        S s2 = this.f837f;
        if (s2 != null) {
            s2.b();
        }
        C0164a c0164a = this.f832a;
        if (i2 == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            S a2 = N.a(this);
            a2.a(1.0f);
            a2.c(j2);
            c0164a.f1876c.f837f = a2;
            c0164a.f1875b = i2;
            a2.d(c0164a);
            return a2;
        }
        S a3 = N.a(this);
        a3.a(0.0f);
        a3.c(j2);
        c0164a.f1876c.f837f = a3;
        c0164a.f1875b = i2;
        a3.d(c0164a);
        return a3;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        int i2;
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, AbstractC0101a.f1262a, R.attr.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(13, 0));
        obtainStyledAttributes.recycle();
        C0184k c0184k = this.f835d;
        if (c0184k != null) {
            Configuration configuration2 = c0184k.f1952b.getResources().getConfiguration();
            int i3 = configuration2.screenWidthDp;
            int i4 = configuration2.screenHeightDp;
            if (configuration2.smallestScreenWidthDp <= 600 && i3 <= 600 && ((i3 <= 960 || i4 <= 720) && (i3 <= 720 || i4 <= 960))) {
                if (i3 < 500 && ((i3 <= 640 || i4 <= 480) && (i3 <= 480 || i4 <= 640))) {
                    if (i3 >= 360) {
                        i2 = 3;
                    } else {
                        i2 = 2;
                    }
                } else {
                    i2 = 4;
                }
            } else {
                i2 = 5;
            }
            c0184k.f1965p = i2;
            n nVar = c0184k.f1953c;
            if (nVar != null) {
                nVar.p(true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C0184k c0184k = this.f835d;
        if (c0184k != null) {
            c0184k.e();
            C0176g c0176g = this.f835d.f1969t;
            if (c0176g != null && c0176g.b()) {
                c0176g.f1730i.dismiss();
            }
        }
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.h = false;
        }
        if (!this.h) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.h = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.h = false;
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        boolean z3;
        int paddingLeft;
        int paddingRight;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z4 = m1.f1979a;
        if (getLayoutDirection() == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            paddingLeft = (i4 - i2) - getPaddingRight();
        } else {
            paddingLeft = getPaddingLeft();
        }
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i5 - i3) - getPaddingTop()) - getPaddingBottom();
        View view = this.f841k;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f841k.getLayoutParams();
            if (z3) {
                i6 = marginLayoutParams.rightMargin;
            } else {
                i6 = marginLayoutParams.leftMargin;
            }
            if (z3) {
                i7 = marginLayoutParams.leftMargin;
            } else {
                i7 = marginLayoutParams.rightMargin;
            }
            if (z3) {
                i8 = paddingLeft - i6;
            } else {
                i8 = paddingLeft + i6;
            }
            int g2 = g(this.f841k, i8, paddingTop, paddingTop2, z3) + i8;
            if (z3) {
                i9 = g2 - i7;
            } else {
                i9 = g2 + i7;
            }
            paddingLeft = i9;
        }
        LinearLayout linearLayout = this.f844n;
        if (linearLayout != null && this.f843m == null && linearLayout.getVisibility() != 8) {
            paddingLeft += g(this.f844n, paddingLeft, paddingTop, paddingTop2, z3);
        }
        View view2 = this.f843m;
        if (view2 != null) {
            g(view2, paddingLeft, paddingTop, paddingTop2, z3);
        }
        if (z3) {
            paddingRight = getPaddingLeft();
        } else {
            paddingRight = (i4 - i2) - getPaddingRight();
        }
        ActionMenuView actionMenuView = this.f834c;
        if (actionMenuView != null) {
            g(actionMenuView, paddingRight, paddingTop, paddingTop2, !z3);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i2, int i3) {
        int i4;
        boolean z2;
        int i5;
        int i6 = 1073741824;
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            if (View.MeasureSpec.getMode(i3) != 0) {
                int size = View.MeasureSpec.getSize(i2);
                int i7 = this.f836e;
                if (i7 <= 0) {
                    i7 = View.MeasureSpec.getSize(i3);
                }
                int paddingBottom = getPaddingBottom() + getPaddingTop();
                int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
                int i8 = i7 - paddingBottom;
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8, Integer.MIN_VALUE);
                View view = this.f841k;
                if (view != null) {
                    int f2 = f(view, paddingLeft, makeMeasureSpec);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f841k.getLayoutParams();
                    paddingLeft = f2 - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
                }
                ActionMenuView actionMenuView = this.f834c;
                if (actionMenuView != null && actionMenuView.getParent() == this) {
                    paddingLeft = f(this.f834c, paddingLeft, makeMeasureSpec);
                }
                LinearLayout linearLayout = this.f844n;
                if (linearLayout != null && this.f843m == null) {
                    if (this.f849s) {
                        this.f844n.measure(View.MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                        int measuredWidth = this.f844n.getMeasuredWidth();
                        if (measuredWidth <= paddingLeft) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            paddingLeft -= measuredWidth;
                        }
                        LinearLayout linearLayout2 = this.f844n;
                        if (z2) {
                            i5 = 0;
                        } else {
                            i5 = 8;
                        }
                        linearLayout2.setVisibility(i5);
                    } else {
                        paddingLeft = f(linearLayout, paddingLeft, makeMeasureSpec);
                    }
                }
                View view2 = this.f843m;
                if (view2 != null) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    int i9 = layoutParams.width;
                    if (i9 != -2) {
                        i4 = 1073741824;
                    } else {
                        i4 = Integer.MIN_VALUE;
                    }
                    if (i9 >= 0) {
                        paddingLeft = Math.min(i9, paddingLeft);
                    }
                    int i10 = layoutParams.height;
                    if (i10 == -2) {
                        i6 = Integer.MIN_VALUE;
                    }
                    if (i10 >= 0) {
                        i8 = Math.min(i10, i8);
                    }
                    this.f843m.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i4), View.MeasureSpec.makeMeasureSpec(i8, i6));
                }
                if (this.f836e <= 0) {
                    int childCount = getChildCount();
                    int i11 = 0;
                    for (int i12 = 0; i12 < childCount; i12++) {
                        int measuredHeight = getChildAt(i12).getMeasuredHeight() + paddingBottom;
                        if (measuredHeight > i11) {
                            i11 = measuredHeight;
                        }
                    }
                    setMeasuredDimension(size, i11);
                    return;
                }
                setMeasuredDimension(size, i7);
                return;
            }
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_height=\"wrap_content\""));
        }
        throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)"));
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f838g = false;
        }
        if (!this.f838g) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.f838g = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f838g = false;
        }
        return true;
    }

    public void setContentHeight(int i2) {
        this.f836e = i2;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.f843m;
        if (view2 != null) {
            removeView(view2);
        }
        this.f843m = view;
        if (view != null && (linearLayout = this.f844n) != null) {
            removeView(linearLayout);
            this.f844n = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f840j = charSequence;
        d();
    }

    public void setTitle(CharSequence charSequence) {
        this.f839i = charSequence;
        d();
        N.i(this, charSequence);
    }

    public void setTitleOptional(boolean z2) {
        if (z2 != this.f849s) {
            requestLayout();
        }
        this.f849s = z2;
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}

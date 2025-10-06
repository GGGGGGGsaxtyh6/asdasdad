package k;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import f.AbstractC0101a;
import j.InterfaceC0136D;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public abstract class G0 implements InterfaceC0136D {

    /* renamed from: A, reason: collision with root package name */
    public static final Method f1762A;

    /* renamed from: z, reason: collision with root package name */
    public static final Method f1763z;

    /* renamed from: a, reason: collision with root package name */
    public final Context f1764a;

    /* renamed from: b, reason: collision with root package name */
    public ListAdapter f1765b;

    /* renamed from: c, reason: collision with root package name */
    public C0202t0 f1766c;

    /* renamed from: f, reason: collision with root package name */
    public int f1769f;

    /* renamed from: g, reason: collision with root package name */
    public int f1770g;

    /* renamed from: i, reason: collision with root package name */
    public boolean f1771i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f1772j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f1773k;

    /* renamed from: n, reason: collision with root package name */
    public D0 f1776n;

    /* renamed from: o, reason: collision with root package name */
    public View f1777o;

    /* renamed from: p, reason: collision with root package name */
    public AdapterView.OnItemClickListener f1778p;

    /* renamed from: u, reason: collision with root package name */
    public final Handler f1783u;

    /* renamed from: w, reason: collision with root package name */
    public Rect f1785w;

    /* renamed from: x, reason: collision with root package name */
    public boolean f1786x;
    public final C0155C y;

    /* renamed from: d, reason: collision with root package name */
    public final int f1767d = -2;

    /* renamed from: e, reason: collision with root package name */
    public int f1768e = -2;
    public final int h = 1002;

    /* renamed from: l, reason: collision with root package name */
    public int f1774l = 0;

    /* renamed from: m, reason: collision with root package name */
    public final int f1775m = Integer.MAX_VALUE;

    /* renamed from: q, reason: collision with root package name */
    public final C0 f1779q = new C0(this, 1);

    /* renamed from: r, reason: collision with root package name */
    public final F0 f1780r = new F0(this);

    /* renamed from: s, reason: collision with root package name */
    public final E0 f1781s = new E0(this);

    /* renamed from: t, reason: collision with root package name */
    public final C0 f1782t = new C0(this, 0);

    /* renamed from: v, reason: collision with root package name */
    public final Rect f1784v = new Rect();

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                f1763z = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                f1762A = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v9, types: [k.C, android.widget.PopupWindow] */
    public G0(Context context, AttributeSet attributeSet, int i2) {
        Drawable drawable;
        int resourceId;
        this.f1764a = context;
        this.f1783u = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0101a.f1275o, i2, 0);
        this.f1769f = obtainStyledAttributes.getDimensionPixelOffset(0, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.f1770g = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.f1771i = true;
        }
        obtainStyledAttributes.recycle();
        ?? popupWindow = new PopupWindow(context, attributeSet, i2, 0);
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, AbstractC0101a.f1279s, i2, 0);
        if (obtainStyledAttributes2.hasValue(2)) {
            popupWindow.setOverlapAnchor(obtainStyledAttributes2.getBoolean(2, false));
        }
        if (obtainStyledAttributes2.hasValue(0) && (resourceId = obtainStyledAttributes2.getResourceId(0, 0)) != 0) {
            drawable = C0.d.v(context, resourceId);
        } else {
            drawable = obtainStyledAttributes2.getDrawable(0);
        }
        popupWindow.setBackgroundDrawable(drawable);
        obtainStyledAttributes2.recycle();
        this.y = popupWindow;
        popupWindow.setInputMethodMode(1);
    }

    @Override // j.InterfaceC0136D
    public final boolean b() {
        return this.y.isShowing();
    }

    public final void c(int i2) {
        this.f1769f = i2;
    }

    public final int d() {
        return this.f1769f;
    }

    @Override // j.InterfaceC0136D
    public final void dismiss() {
        C0155C c0155c = this.y;
        c0155c.dismiss();
        c0155c.setContentView(null);
        this.f1766c = null;
        this.f1783u.removeCallbacks(this.f1779q);
    }

    @Override // j.InterfaceC0136D
    public final C0202t0 e() {
        return this.f1766c;
    }

    @Override // j.InterfaceC0136D
    public final void i() {
        int i2;
        boolean z2;
        int makeMeasureSpec;
        int i3;
        int i4;
        boolean z3;
        C0202t0 c0202t0;
        int i5;
        int i6;
        C0202t0 c0202t02 = this.f1766c;
        C0155C c0155c = this.y;
        Context context = this.f1764a;
        if (c0202t02 == null) {
            C0202t0 q2 = q(context, !this.f1786x);
            this.f1766c = q2;
            q2.setAdapter(this.f1765b);
            this.f1766c.setOnItemClickListener(this.f1778p);
            this.f1766c.setFocusable(true);
            this.f1766c.setFocusableInTouchMode(true);
            this.f1766c.setOnItemSelectedListener(new C0214z0(this));
            this.f1766c.setOnScrollListener(this.f1781s);
            c0155c.setContentView(this.f1766c);
        }
        Drawable background = c0155c.getBackground();
        int i7 = 0;
        Rect rect = this.f1784v;
        if (background != null) {
            background.getPadding(rect);
            int i8 = rect.top;
            i2 = rect.bottom + i8;
            if (!this.f1771i) {
                this.f1770g = -i8;
            }
        } else {
            rect.setEmpty();
            i2 = 0;
        }
        if (c0155c.getInputMethodMode() == 2) {
            z2 = true;
        } else {
            z2 = false;
        }
        int a2 = A0.a(c0155c, this.f1777o, this.f1770g, z2);
        int i9 = this.f1767d;
        if (i9 == -1) {
            i4 = a2 + i2;
        } else {
            int i10 = this.f1768e;
            if (i10 != -2) {
                if (i10 != -1) {
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i10, 1073741824);
                } else {
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), 1073741824);
                }
            } else {
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), Integer.MIN_VALUE);
            }
            int a3 = this.f1766c.a(makeMeasureSpec, a2);
            if (a3 > 0) {
                i3 = this.f1766c.getPaddingBottom() + this.f1766c.getPaddingTop() + i2;
            } else {
                i3 = 0;
            }
            i4 = a3 + i3;
        }
        if (this.y.getInputMethodMode() == 2) {
            z3 = true;
        } else {
            z3 = false;
        }
        c0155c.setWindowLayoutType(this.h);
        if (c0155c.isShowing()) {
            if (!this.f1777o.isAttachedToWindow()) {
                return;
            }
            int i11 = this.f1768e;
            if (i11 == -1) {
                i11 = -1;
            } else if (i11 == -2) {
                i11 = this.f1777o.getWidth();
            }
            if (i9 == -1) {
                if (z3) {
                    i9 = i4;
                } else {
                    i9 = -1;
                }
                if (z3) {
                    if (this.f1768e == -1) {
                        i6 = -1;
                    } else {
                        i6 = 0;
                    }
                    c0155c.setWidth(i6);
                    c0155c.setHeight(0);
                } else {
                    if (this.f1768e == -1) {
                        i7 = -1;
                    }
                    c0155c.setWidth(i7);
                    c0155c.setHeight(-1);
                }
            } else if (i9 == -2) {
                i9 = i4;
            }
            c0155c.setOutsideTouchable(true);
            View view = this.f1777o;
            int i12 = this.f1769f;
            int i13 = this.f1770g;
            if (i11 < 0) {
                i11 = -1;
            }
            if (i9 < 0) {
                i5 = -1;
            } else {
                i5 = i9;
            }
            c0155c.update(view, i12, i13, i11, i5);
            return;
        }
        int i14 = this.f1768e;
        if (i14 == -1) {
            i14 = -1;
        } else if (i14 == -2) {
            i14 = this.f1777o.getWidth();
        }
        if (i9 == -1) {
            i9 = -1;
        } else if (i9 == -2) {
            i9 = i4;
        }
        c0155c.setWidth(i14);
        c0155c.setHeight(i9);
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = f1763z;
            if (method != null) {
                try {
                    method.invoke(c0155c, Boolean.TRUE);
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
                }
            }
        } else {
            B0.b(c0155c, true);
        }
        c0155c.setOutsideTouchable(true);
        c0155c.setTouchInterceptor(this.f1780r);
        if (this.f1773k) {
            c0155c.setOverlapAnchor(this.f1772j);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method2 = f1762A;
            if (method2 != null) {
                try {
                    method2.invoke(c0155c, this.f1785w);
                } catch (Exception e2) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
                }
            }
        } else {
            B0.a(c0155c, this.f1785w);
        }
        c0155c.showAsDropDown(this.f1777o, this.f1769f, this.f1770g, this.f1774l);
        this.f1766c.setSelection(-1);
        if ((!this.f1786x || this.f1766c.isInTouchMode()) && (c0202t0 = this.f1766c) != null) {
            c0202t0.setListSelectionHidden(true);
            c0202t0.requestLayout();
        }
        if (!this.f1786x) {
            this.f1783u.post(this.f1782t);
        }
    }

    public final int j() {
        if (!this.f1771i) {
            return 0;
        }
        return this.f1770g;
    }

    public final void k(Drawable drawable) {
        this.y.setBackgroundDrawable(drawable);
    }

    public final void m(int i2) {
        this.f1770g = i2;
        this.f1771i = true;
    }

    public final Drawable n() {
        return this.y.getBackground();
    }

    public void o(ListAdapter listAdapter) {
        D0 d02 = this.f1776n;
        if (d02 == null) {
            this.f1776n = new D0(this);
        } else {
            ListAdapter listAdapter2 = this.f1765b;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(d02);
            }
        }
        this.f1765b = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f1776n);
        }
        C0202t0 c0202t0 = this.f1766c;
        if (c0202t0 != null) {
            c0202t0.setAdapter(this.f1765b);
        }
    }

    public C0202t0 q(Context context, boolean z2) {
        return new C0202t0(context, z2);
    }

    public final void r(int i2) {
        Drawable background = this.y.getBackground();
        if (background != null) {
            Rect rect = this.f1784v;
            background.getPadding(rect);
            this.f1768e = rect.left + rect.right + i2;
            return;
        }
        this.f1768e = i2;
    }
}

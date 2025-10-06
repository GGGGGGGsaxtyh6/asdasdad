package k;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import f.AbstractC0101a;
import java.util.WeakHashMap;

/* renamed from: k.p, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0194p {

    /* renamed from: a, reason: collision with root package name */
    public final View f1988a;

    /* renamed from: d, reason: collision with root package name */
    public U0 f1991d;

    /* renamed from: e, reason: collision with root package name */
    public U0 f1992e;

    /* renamed from: f, reason: collision with root package name */
    public U0 f1993f;

    /* renamed from: c, reason: collision with root package name */
    public int f1990c = -1;

    /* renamed from: b, reason: collision with root package name */
    public final C0203u f1989b = C0203u.a();

    public C0194p(View view) {
        this.f1988a = view;
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object, k.U0] */
    public final void a() {
        View view = this.f1988a;
        Drawable background = view.getBackground();
        if (background != null) {
            if (this.f1991d != null) {
                if (this.f1993f == null) {
                    this.f1993f = new Object();
                }
                U0 u02 = this.f1993f;
                u02.f1851c = null;
                u02.f1850b = false;
                u02.f1852d = null;
                u02.f1849a = false;
                WeakHashMap weakHashMap = H.N.f327a;
                ColorStateList c2 = H.G.c(view);
                if (c2 != null) {
                    u02.f1850b = true;
                    u02.f1851c = c2;
                }
                PorterDuff.Mode d2 = H.G.d(view);
                if (d2 != null) {
                    u02.f1849a = true;
                    u02.f1852d = d2;
                }
                if (u02.f1850b || u02.f1849a) {
                    C0203u.d(background, u02, view.getDrawableState());
                    return;
                }
            }
            U0 u03 = this.f1992e;
            if (u03 != null) {
                C0203u.d(background, u03, view.getDrawableState());
                return;
            }
            U0 u04 = this.f1991d;
            if (u04 != null) {
                C0203u.d(background, u04, view.getDrawableState());
            }
        }
    }

    public final ColorStateList b() {
        U0 u02 = this.f1992e;
        if (u02 != null) {
            return (ColorStateList) u02.f1851c;
        }
        return null;
    }

    public final PorterDuff.Mode c() {
        U0 u02 = this.f1992e;
        if (u02 != null) {
            return (PorterDuff.Mode) u02.f1852d;
        }
        return null;
    }

    public final void d(AttributeSet attributeSet, int i2) {
        ColorStateList f2;
        View view = this.f1988a;
        Context context = view.getContext();
        int[] iArr = AbstractC0101a.y;
        D0.h p2 = D0.h.p(context, attributeSet, iArr, i2);
        TypedArray typedArray = (TypedArray) p2.f259c;
        View view2 = this.f1988a;
        H.N.g(view2, view2.getContext(), iArr, attributeSet, (TypedArray) p2.f259c, i2);
        try {
            if (typedArray.hasValue(0)) {
                this.f1990c = typedArray.getResourceId(0, -1);
                C0203u c0203u = this.f1989b;
                Context context2 = view.getContext();
                int i3 = this.f1990c;
                synchronized (c0203u) {
                    f2 = c0203u.f2031a.f(context2, i3);
                }
                if (f2 != null) {
                    g(f2);
                }
            }
            if (typedArray.hasValue(1)) {
                H.G.e(view, p2.i(1));
            }
            if (typedArray.hasValue(2)) {
                H.G.f(view, AbstractC0191n0.b(typedArray.getInt(2, -1), null));
            }
        } finally {
            p2.r();
        }
    }

    public final void e() {
        this.f1990c = -1;
        g(null);
        a();
    }

    public final void f(int i2) {
        ColorStateList colorStateList;
        this.f1990c = i2;
        C0203u c0203u = this.f1989b;
        if (c0203u != null) {
            Context context = this.f1988a.getContext();
            synchronized (c0203u) {
                colorStateList = c0203u.f2031a.f(context, i2);
            }
        } else {
            colorStateList = null;
        }
        g(colorStateList);
        a();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, k.U0] */
    public final void g(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f1991d == null) {
                this.f1991d = new Object();
            }
            U0 u02 = this.f1991d;
            u02.f1851c = colorStateList;
            u02.f1850b = true;
        } else {
            this.f1991d = null;
        }
        a();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, k.U0] */
    public final void h(ColorStateList colorStateList) {
        if (this.f1992e == null) {
            this.f1992e = new Object();
        }
        U0 u02 = this.f1992e;
        u02.f1851c = colorStateList;
        u02.f1850b = true;
        a();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, k.U0] */
    public final void i(PorterDuff.Mode mode) {
        if (this.f1992e == null) {
            this.f1992e = new Object();
        }
        U0 u02 = this.f1992e;
        u02.f1852d = mode;
        u02.f1849a = true;
        a();
    }
}

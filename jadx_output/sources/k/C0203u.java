package k;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;

/* renamed from: k.u, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0203u {

    /* renamed from: b, reason: collision with root package name */
    public static final PorterDuff.Mode f2029b = PorterDuff.Mode.SRC_IN;

    /* renamed from: c, reason: collision with root package name */
    public static C0203u f2030c;

    /* renamed from: a, reason: collision with root package name */
    public N0 f2031a;

    public static synchronized C0203u a() {
        C0203u c0203u;
        synchronized (C0203u.class) {
            try {
                if (f2030c == null) {
                    c();
                }
                c0203u = f2030c;
            } catch (Throwable th) {
                throw th;
            }
        }
        return c0203u;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [k.u, java.lang.Object] */
    public static synchronized void c() {
        synchronized (C0203u.class) {
            if (f2030c == null) {
                ?? obj = new Object();
                f2030c = obj;
                obj.f2031a = N0.b();
                N0 n02 = f2030c.f2031a;
                B0.h hVar = new B0.h();
                synchronized (n02) {
                    n02.f1815e = hVar;
                }
            }
        }
    }

    public static void d(Drawable drawable, U0 u02, int[] iArr) {
        ColorStateList colorStateList;
        PorterDuff.Mode mode;
        PorterDuff.Mode mode2 = N0.f1809f;
        int[] state = drawable.getState();
        if (drawable.mutate() == drawable) {
            if ((drawable instanceof LayerDrawable) && drawable.isStateful()) {
                drawable.setState(new int[0]);
                drawable.setState(state);
            }
            boolean z2 = u02.f1850b;
            if (!z2 && !u02.f1849a) {
                drawable.clearColorFilter();
                return;
            }
            PorterDuffColorFilter porterDuffColorFilter = null;
            if (z2) {
                colorStateList = (ColorStateList) u02.f1851c;
            } else {
                colorStateList = null;
            }
            if (u02.f1849a) {
                mode = (PorterDuff.Mode) u02.f1852d;
            } else {
                mode = N0.f1809f;
            }
            if (colorStateList != null && mode != null) {
                porterDuffColorFilter = N0.e(colorStateList.getColorForState(iArr, 0), mode);
            }
            drawable.setColorFilter(porterDuffColorFilter);
            return;
        }
        Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
    }

    public final synchronized Drawable b(Context context, int i2) {
        return this.f2031a.c(context, i2);
    }
}

package H;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/* loaded from: classes.dex */
public abstract class Y extends e0 {

    /* renamed from: f, reason: collision with root package name */
    public static boolean f344f = false;

    /* renamed from: g, reason: collision with root package name */
    public static Method f345g;
    public static Class h;

    /* renamed from: i, reason: collision with root package name */
    public static Field f346i;

    /* renamed from: j, reason: collision with root package name */
    public static Field f347j;

    /* renamed from: c, reason: collision with root package name */
    public final WindowInsets f348c;

    /* renamed from: d, reason: collision with root package name */
    public A.c f349d;

    /* renamed from: e, reason: collision with root package name */
    public A.c f350e;

    public Y(f0 f0Var, WindowInsets windowInsets) {
        super(f0Var);
        this.f349d = null;
        this.f348c = windowInsets;
    }

    private A.c n(View view) {
        if (Build.VERSION.SDK_INT < 30) {
            if (!f344f) {
                o();
            }
            Method method = f345g;
            if (method != null && h != null && f346i != null) {
                try {
                    Object invoke = method.invoke(view, null);
                    if (invoke == null) {
                        Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                        return null;
                    }
                    Rect rect = (Rect) f346i.get(f347j.get(invoke));
                    if (rect == null) {
                        return null;
                    }
                    return A.c.a(rect.left, rect.top, rect.right, rect.bottom);
                } catch (ReflectiveOperationException e2) {
                    Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e2.getMessage(), e2);
                }
            }
            return null;
        }
        throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
    }

    private static void o() {
        try {
            f345g = View.class.getDeclaredMethod("getViewRootImpl", null);
            Class<?> cls = Class.forName("android.view.View$AttachInfo");
            h = cls;
            f346i = cls.getDeclaredField("mVisibleInsets");
            f347j = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
            f346i.setAccessible(true);
            f347j.setAccessible(true);
        } catch (ReflectiveOperationException e2) {
            Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e2.getMessage(), e2);
        }
        f344f = true;
    }

    @Override // H.e0
    public void d(View view) {
        A.c n2 = n(view);
        if (n2 == null) {
            n2 = A.c.f1e;
        }
        p(n2);
    }

    @Override // H.e0
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        return Objects.equals(this.f350e, ((Y) obj).f350e);
    }

    @Override // H.e0
    public final A.c g() {
        if (this.f349d == null) {
            WindowInsets windowInsets = this.f348c;
            this.f349d = A.c.a(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        }
        return this.f349d;
    }

    @Override // H.e0
    public f0 h(int i2, int i3, int i4, int i5) {
        X u2;
        f0 c2 = f0.c(this.f348c, null);
        int i6 = Build.VERSION.SDK_INT;
        if (i6 >= 30) {
            u2 = new W(c2);
        } else if (i6 >= 29) {
            u2 = new V(c2);
        } else {
            u2 = new U(c2);
        }
        u2.d(f0.a(g(), i2, i3, i4, i5));
        u2.c(f0.a(f(), i2, i3, i4, i5));
        return u2.b();
    }

    @Override // H.e0
    public boolean j() {
        return this.f348c.isRound();
    }

    public void p(A.c cVar) {
        this.f350e = cVar;
    }

    @Override // H.e0
    public void k(A.c[] cVarArr) {
    }

    @Override // H.e0
    public void l(f0 f0Var) {
    }
}

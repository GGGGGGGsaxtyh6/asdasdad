package H;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.Objects;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class f0 {

    /* renamed from: b, reason: collision with root package name */
    public static final f0 f365b;

    /* renamed from: a, reason: collision with root package name */
    public final e0 f366a;

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            f365b = d0.f356l;
        } else {
            f365b = e0.f363b;
        }
    }

    public f0(WindowInsets windowInsets) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            this.f366a = new d0(this, windowInsets);
            return;
        }
        if (i2 >= 29) {
            this.f366a = new b0(this, windowInsets);
        } else if (i2 >= 28) {
            this.f366a = new a0(this, windowInsets);
        } else {
            this.f366a = new Z(this, windowInsets);
        }
    }

    public static A.c a(A.c cVar, int i2, int i3, int i4, int i5) {
        int max = Math.max(0, cVar.f2a - i2);
        int max2 = Math.max(0, cVar.f3b - i3);
        int max3 = Math.max(0, cVar.f4c - i4);
        int max4 = Math.max(0, cVar.f5d - i5);
        if (max == i2 && max2 == i3 && max3 == i4 && max4 == i5) {
            return cVar;
        }
        return A.c.a(max, max2, max3, max4);
    }

    public static f0 c(WindowInsets windowInsets, View view) {
        windowInsets.getClass();
        f0 f0Var = new f0(windowInsets);
        if (view != null && view.isAttachedToWindow()) {
            WeakHashMap weakHashMap = N.f327a;
            f0 a2 = H.a(view);
            e0 e0Var = f0Var.f366a;
            e0Var.l(a2);
            e0Var.d(view.getRootView());
        }
        return f0Var;
    }

    public final WindowInsets b() {
        e0 e0Var = this.f366a;
        if (e0Var instanceof Y) {
            return ((Y) e0Var).f348c;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f0)) {
            return false;
        }
        return Objects.equals(this.f366a, ((f0) obj).f366a);
    }

    public final int hashCode() {
        e0 e0Var = this.f366a;
        if (e0Var == null) {
            return 0;
        }
        return e0Var.hashCode();
    }

    public f0() {
        this.f366a = new e0(this);
    }
}

package H;

import android.view.View;
import android.view.WindowInsets;

/* loaded from: classes.dex */
public abstract class H {
    public static f0 a(View view) {
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets == null) {
            return null;
        }
        f0 c2 = f0.c(rootWindowInsets, null);
        e0 e0Var = c2.f366a;
        e0Var.l(c2);
        e0Var.d(view.getRootView());
        return c2;
    }

    public static void b(View view, int i2, int i3) {
        view.setScrollIndicators(i2, i3);
    }
}

package H;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import org.conscrypt.R;

/* loaded from: classes.dex */
public abstract class G {
    public static void a(WindowInsets windowInsets, View view) {
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(R.id.tag_window_insets_animation_callback);
        if (onApplyWindowInsetsListener != null) {
            onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
        }
    }

    public static f0 b(View view, f0 f0Var, Rect rect) {
        WindowInsets b2 = f0Var.b();
        if (b2 != null) {
            return f0.c(view.computeSystemWindowInsets(b2, rect), view);
        }
        rect.setEmpty();
        return f0Var;
    }

    public static ColorStateList c(View view) {
        return view.getBackgroundTintList();
    }

    public static PorterDuff.Mode d(View view) {
        return view.getBackgroundTintMode();
    }

    public static void e(View view, ColorStateList colorStateList) {
        view.setBackgroundTintList(colorStateList);
    }

    public static void f(View view, PorterDuff.Mode mode) {
        view.setBackgroundTintMode(mode);
    }

    public static void g(View view, float f2) {
        view.setElevation(f2);
    }

    public static void h(View view, InterfaceC0027v interfaceC0027v) {
        if (Build.VERSION.SDK_INT < 30) {
            view.setTag(R.id.tag_on_apply_window_listener, interfaceC0027v);
        }
        if (interfaceC0027v == null) {
            view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(R.id.tag_window_insets_animation_callback));
        } else {
            view.setOnApplyWindowInsetsListener(new F(view, interfaceC0027v));
        }
    }

    public static void i(View view) {
        view.stopNestedScroll();
    }
}

package H;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.view.MenuItem;

/* renamed from: H.p, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0022p {
    public static void a(MenuItem menuItem, char c2, int i2) {
        menuItem.setAlphabeticShortcut(c2, i2);
    }

    public static void b(MenuItem menuItem, CharSequence charSequence) {
        menuItem.setContentDescription(charSequence);
    }

    public static void c(MenuItem menuItem, ColorStateList colorStateList) {
        menuItem.setIconTintList(colorStateList);
    }

    public static void d(MenuItem menuItem, PorterDuff.Mode mode) {
        menuItem.setIconTintMode(mode);
    }

    public static void e(MenuItem menuItem, char c2, int i2) {
        menuItem.setNumericShortcut(c2, i2);
    }

    public static void f(MenuItem menuItem, CharSequence charSequence) {
        menuItem.setTooltipText(charSequence);
    }
}

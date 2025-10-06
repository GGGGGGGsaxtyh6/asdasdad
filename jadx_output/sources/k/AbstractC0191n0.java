package k;

import android.R;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* renamed from: k.n0, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0191n0 {

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f1982a = {R.attr.state_checked};

    /* renamed from: b, reason: collision with root package name */
    public static final int[] f1983b = new int[0];

    static {
        new Rect();
    }

    public static void a(Drawable drawable) {
        String name = drawable.getClass().getName();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29 && i2 < 31 && "android.graphics.drawable.ColorStateListDrawable".equals(name)) {
            int[] state = drawable.getState();
            if (state != null && state.length != 0) {
                drawable.setState(f1983b);
            } else {
                drawable.setState(f1982a);
            }
            drawable.setState(state);
        }
    }

    public static PorterDuff.Mode b(int i2, PorterDuff.Mode mode) {
        if (i2 != 3) {
            if (i2 != 5) {
                if (i2 != 9) {
                    switch (i2) {
                        case 14:
                            return PorterDuff.Mode.MULTIPLY;
                        case 15:
                            return PorterDuff.Mode.SCREEN;
                        case 16:
                            return PorterDuff.Mode.ADD;
                        default:
                            return mode;
                    }
                }
                return PorterDuff.Mode.SRC_ATOP;
            }
            return PorterDuff.Mode.SRC_IN;
        }
        return PorterDuff.Mode.SRC_OVER;
    }
}

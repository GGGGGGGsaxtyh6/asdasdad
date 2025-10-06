package D;

import android.os.Build;
import j0.AbstractC0150d;
import java.util.Locale;

/* loaded from: classes.dex */
public abstract class b {
    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            a.a(30);
        }
        if (i2 >= 30) {
            a.a(31);
        }
        if (i2 >= 30) {
            a.a(33);
        }
        if (i2 >= 30) {
            a.a(1000000);
        }
    }

    public static final boolean a() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 35) {
            if (i2 >= 34) {
                String str = Build.VERSION.CODENAME;
                AbstractC0150d.d(str, "CODENAME");
                if (!"REL".equals(str)) {
                    Locale locale = Locale.ROOT;
                    String upperCase = str.toUpperCase(locale);
                    AbstractC0150d.d(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                    String upperCase2 = "VanillaIceCream".toUpperCase(locale);
                    AbstractC0150d.d(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                    if (upperCase.compareTo(upperCase2) >= 0) {
                    }
                }
            }
            return false;
        }
        return true;
    }
}

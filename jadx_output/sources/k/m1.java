package k;

import android.os.Build;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public abstract class m1 {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f1979a;

    /* renamed from: b, reason: collision with root package name */
    public static Method f1980b;

    /* renamed from: c, reason: collision with root package name */
    public static final boolean f1981c;

    static {
        boolean z2;
        if (Build.VERSION.SDK_INT >= 27) {
            z2 = true;
        } else {
            z2 = false;
        }
        f1981c = z2;
    }
}

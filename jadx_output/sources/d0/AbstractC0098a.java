package d0;

import j0.AbstractC0150d;
import java.lang.reflect.Method;

/* renamed from: d0.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0098a {

    /* renamed from: a, reason: collision with root package name */
    public static final Method f1260a;

    static {
        Method method;
        Method[] methods = Throwable.class.getMethods();
        AbstractC0150d.b(methods);
        int length = methods.length;
        int i2 = 0;
        while (true) {
            method = null;
            Class<?> cls = null;
            if (i2 >= length) {
                break;
            }
            Method method2 = methods[i2];
            if (AbstractC0150d.a(method2.getName(), "addSuppressed")) {
                Class<?>[] parameterTypes = method2.getParameterTypes();
                AbstractC0150d.d(parameterTypes, "getParameterTypes(...)");
                if (parameterTypes.length == 1) {
                    cls = parameterTypes[0];
                }
                if (AbstractC0150d.a(cls, Throwable.class)) {
                    method = method2;
                    break;
                }
            }
            i2++;
        }
        f1260a = method;
        int length2 = methods.length;
        for (int i3 = 0; i3 < length2 && !AbstractC0150d.a(methods[i3].getName(), "getSuppressed"); i3++) {
        }
    }
}

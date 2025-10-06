package androidx.lifecycle;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* renamed from: androidx.lifecycle.d, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0057d {

    /* renamed from: c, reason: collision with root package name */
    public static final C0057d f1188c = new C0057d();

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f1189a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    public final HashMap f1190b = new HashMap();

    public static void b(HashMap hashMap, C0056c c0056c, EnumC0066m enumC0066m, Class cls) {
        EnumC0066m enumC0066m2 = (EnumC0066m) hashMap.get(c0056c);
        if (enumC0066m2 != null && enumC0066m != enumC0066m2) {
            throw new IllegalArgumentException("Method " + c0056c.f1187b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + enumC0066m2 + ", new value " + enumC0066m);
        }
        if (enumC0066m2 == null) {
            hashMap.put(c0056c, enumC0066m);
        }
    }

    public final C0055b a(Class cls, Method[] methodArr) {
        int i2;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = this.f1189a;
        if (superclass != null) {
            C0055b c0055b = (C0055b) hashMap2.get(superclass);
            if (c0055b == null) {
                c0055b = a(superclass, null);
            }
            hashMap.putAll(c0055b.f1185b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            C0055b c0055b2 = (C0055b) hashMap2.get(cls2);
            if (c0055b2 == null) {
                c0055b2 = a(cls2, null);
            }
            for (Map.Entry entry : c0055b2.f1185b.entrySet()) {
                b(hashMap, (C0056c) entry.getKey(), (EnumC0066m) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            try {
                methodArr = cls.getDeclaredMethods();
            } catch (NoClassDefFoundError e2) {
                throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
            }
        }
        boolean z2 = false;
        for (Method method : methodArr) {
            x xVar = (x) method.getAnnotation(x.class);
            if (xVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length > 0) {
                    if (InterfaceC0072t.class.isAssignableFrom(parameterTypes[0])) {
                        i2 = 1;
                    } else {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                } else {
                    i2 = 0;
                }
                EnumC0066m value = xVar.value();
                if (parameterTypes.length > 1) {
                    if (EnumC0066m.class.isAssignableFrom(parameterTypes[1])) {
                        if (value == EnumC0066m.ON_ANY) {
                            i2 = 2;
                        } else {
                            throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                        }
                    } else {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                }
                if (parameterTypes.length <= 2) {
                    b(hashMap, new C0056c(i2, method), value, cls);
                    z2 = true;
                } else {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
            }
        }
        C0055b c0055b3 = new C0055b(hashMap);
        hashMap2.put(cls, c0055b3);
        this.f1190b.put(cls, Boolean.valueOf(z2));
        return c0055b3;
    }
}

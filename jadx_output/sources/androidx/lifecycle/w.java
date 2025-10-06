package androidx.lifecycle;

import j0.AbstractC0150d;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.conscrypt.BuildConfig;

/* loaded from: classes.dex */
public abstract class w {

    /* renamed from: a, reason: collision with root package name */
    public static final HashMap f1213a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    public static final HashMap f1214b = new HashMap();

    public static void a(Constructor constructor, InterfaceC0071s interfaceC0071s) {
        try {
            AbstractC0150d.d(constructor.newInstance(interfaceC0071s), "{\n            constructo…tance(`object`)\n        }");
            throw new ClassCastException();
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException(e4);
        }
    }

    public static int b(Class cls) {
        Constructor<?> constructor;
        boolean z2;
        boolean z3;
        boolean z4;
        String str;
        HashMap hashMap = f1213a;
        Integer num = (Integer) hashMap.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int i2 = 1;
        if (cls.getCanonicalName() != null) {
            ArrayList arrayList = null;
            try {
                Package r3 = cls.getPackage();
                String canonicalName = cls.getCanonicalName();
                if (r3 != null) {
                    str = r3.getName();
                } else {
                    str = BuildConfig.FLAVOR;
                }
                AbstractC0150d.d(str, "fullPackage");
                if (str.length() != 0) {
                    AbstractC0150d.d(canonicalName, "name");
                    canonicalName = canonicalName.substring(str.length() + 1);
                    AbstractC0150d.d(canonicalName, "this as java.lang.String).substring(startIndex)");
                }
                AbstractC0150d.d(canonicalName, "if (fullPackage.isEmpty(…g(fullPackage.length + 1)");
                String concat = q0.k.z(canonicalName, ".", "_").concat("_LifecycleAdapter");
                if (str.length() != 0) {
                    concat = str + '.' + concat;
                }
                constructor = Class.forName(concat).getDeclaredConstructor(cls);
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
            } catch (ClassNotFoundException unused) {
                constructor = null;
            } catch (NoSuchMethodException e2) {
                throw new RuntimeException(e2);
            }
            HashMap hashMap2 = f1214b;
            if (constructor != null) {
                hashMap2.put(cls, C0.d.y(constructor));
            } else {
                C0057d c0057d = C0057d.f1188c;
                HashMap hashMap3 = c0057d.f1190b;
                Boolean bool = (Boolean) hashMap3.get(cls);
                if (bool != null) {
                    z2 = bool.booleanValue();
                } else {
                    try {
                        Method[] declaredMethods = cls.getDeclaredMethods();
                        int length = declaredMethods.length;
                        int i3 = 0;
                        while (true) {
                            if (i3 < length) {
                                if (((x) declaredMethods[i3].getAnnotation(x.class)) != null) {
                                    c0057d.a(cls, declaredMethods);
                                    z2 = true;
                                    break;
                                }
                                i3++;
                            } else {
                                hashMap3.put(cls, Boolean.FALSE);
                                z2 = false;
                                break;
                            }
                        }
                    } catch (NoClassDefFoundError e3) {
                        throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e3);
                    }
                }
                if (!z2) {
                    Class superclass = cls.getSuperclass();
                    if (superclass != null && InterfaceC0071s.class.isAssignableFrom(superclass)) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (z3) {
                        AbstractC0150d.d(superclass, "superclass");
                        if (b(superclass) != 1) {
                            Object obj = hashMap2.get(superclass);
                            AbstractC0150d.b(obj);
                            arrayList = new ArrayList((Collection) obj);
                        }
                    }
                    Class<?>[] interfaces = cls.getInterfaces();
                    AbstractC0150d.d(interfaces, "klass.interfaces");
                    int length2 = interfaces.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 < length2) {
                            Class<?> cls2 = interfaces[i4];
                            if (cls2 != null && InterfaceC0071s.class.isAssignableFrom(cls2)) {
                                z4 = true;
                            } else {
                                z4 = false;
                            }
                            if (z4) {
                                AbstractC0150d.d(cls2, "intrface");
                                if (b(cls2) == 1) {
                                    break;
                                }
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                }
                                Object obj2 = hashMap2.get(cls2);
                                AbstractC0150d.b(obj2);
                                arrayList.addAll((Collection) obj2);
                            }
                            i4++;
                        } else if (arrayList != null) {
                            hashMap2.put(cls, arrayList);
                        }
                    }
                }
            }
            i2 = 2;
        }
        hashMap.put(cls, Integer.valueOf(i2));
        return i2;
    }
}

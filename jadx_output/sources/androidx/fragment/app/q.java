package androidx.fragment.app;

import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public final class q {

    /* renamed from: b, reason: collision with root package name */
    public static final n.k f1071b = new n.k(0);

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ t f1072a;

    public q(t tVar) {
        this.f1072a = tVar;
    }

    public static Class b(ClassLoader classLoader, String str) {
        n.k kVar = f1071b;
        n.k kVar2 = (n.k) kVar.get(classLoader);
        if (kVar2 == null) {
            kVar2 = new n.k(0);
            kVar.put(classLoader, kVar2);
        }
        Class cls = (Class) kVar2.get(str);
        if (cls == null) {
            Class<?> cls2 = Class.forName(str, false, classLoader);
            kVar2.put(str, cls2);
            return cls2;
        }
        return cls;
    }

    public static Class c(ClassLoader classLoader, String str) {
        try {
            return b(classLoader, str);
        } catch (ClassCastException e2) {
            throw new RuntimeException(A.e.d("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), e2);
        } catch (ClassNotFoundException e3) {
            throw new RuntimeException(A.e.d("Unable to instantiate fragment ", str, ": make sure class name exists"), e3);
        }
    }

    public final void a(String str) {
        try {
            if (c(this.f1072a.f1100s.f1052a.getClassLoader(), str).getConstructor(null).newInstance(null) == null) {
            } else {
                throw new ClassCastException();
            }
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(A.e.d("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException(A.e.d("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException(A.e.d("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e4);
        } catch (InvocationTargetException e5) {
            throw new RuntimeException(A.e.d("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e5);
        }
    }
}

package androidx.lifecycle;

import android.app.Application;
import b0.C0079e;
import b0.C0091q;
import j0.AbstractC0150d;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public abstract class M {

    /* renamed from: a, reason: collision with root package name */
    public static final List f1176a;

    /* renamed from: b, reason: collision with root package name */
    public static final List f1177b;

    static {
        List asList = Arrays.asList(Application.class, E.class);
        AbstractC0150d.d(asList, "asList(...)");
        f1176a = asList;
        f1177b = C0.d.y(E.class);
    }

    public static final Constructor a(Class cls, List list) {
        List list2;
        AbstractC0150d.e(list, "signature");
        Constructor<?>[] constructors = cls.getConstructors();
        AbstractC0150d.d(constructors, "modelClass.constructors");
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            AbstractC0150d.d(parameterTypes, "constructor.parameterTypes");
            int length = parameterTypes.length;
            if (length != 0) {
                if (length != 1) {
                    list2 = new ArrayList(new C0079e(parameterTypes, false));
                } else {
                    list2 = C0.d.y(parameterTypes[0]);
                }
            } else {
                list2 = C0091q.f1234a;
            }
            if (list.equals(list2)) {
                return constructor;
            }
            if (list.size() == list2.size() && list2.containsAll(list)) {
                throw new UnsupportedOperationException("Class " + cls.getSimpleName() + " must have parameters in the proper order: " + list);
            }
        }
        return null;
    }

    public static final N b(Class cls, Constructor constructor, Object... objArr) {
        try {
            return (N) constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Failed to access " + cls, e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException("A " + cls + " cannot be instantiated.", e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException("An exception happened in constructor of " + cls, e4.getCause());
        }
    }
}

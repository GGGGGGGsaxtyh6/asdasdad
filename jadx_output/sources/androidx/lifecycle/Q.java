package androidx.lifecycle;

import j0.AbstractC0150d;
import j0.C0148b;

/* loaded from: classes.dex */
public class Q implements P {

    /* renamed from: a, reason: collision with root package name */
    public static Q f1182a;

    @Override // androidx.lifecycle.P
    public N a(Class cls) {
        try {
            Object newInstance = cls.getDeclaredConstructor(null).newInstance(null);
            AbstractC0150d.d(newInstance, "{\n            modelClass…).newInstance()\n        }");
            return (N) newInstance;
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Cannot create an instance of " + cls, e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException("Cannot create an instance of " + cls, e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("Cannot create an instance of " + cls, e4);
        }
    }

    @Override // androidx.lifecycle.P
    public final N b(C0148b c0148b, Q.c cVar) {
        return c(C0.f.q(c0148b), cVar);
    }

    @Override // androidx.lifecycle.P
    public N c(Class cls, Q.c cVar) {
        return a(cls);
    }
}

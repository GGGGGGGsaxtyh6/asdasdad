package androidx.lifecycle;

import android.app.Application;
import j0.AbstractC0150d;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public final class O extends Q {

    /* renamed from: c, reason: collision with root package name */
    public static O f1179c;

    /* renamed from: d, reason: collision with root package name */
    public static final A.m f1180d = new A.m(18);

    /* renamed from: b, reason: collision with root package name */
    public final Application f1181b;

    public O(Application application) {
        this.f1181b = application;
    }

    @Override // androidx.lifecycle.Q, androidx.lifecycle.P
    public final N a(Class cls) {
        Application application = this.f1181b;
        if (application != null) {
            return d(cls, application);
        }
        throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
    }

    @Override // androidx.lifecycle.Q, androidx.lifecycle.P
    public final N c(Class cls, Q.c cVar) {
        if (this.f1181b != null) {
            return a(cls);
        }
        Application application = (Application) cVar.f610a.get(f1180d);
        if (application != null) {
            return d(cls, application);
        }
        if (!AbstractC0054a.class.isAssignableFrom(cls)) {
            return super.a(cls);
        }
        throw new IllegalArgumentException("CreationExtras must have an application by `APPLICATION_KEY`");
    }

    public final N d(Class cls, Application application) {
        if (AbstractC0054a.class.isAssignableFrom(cls)) {
            try {
                N n2 = (N) cls.getConstructor(Application.class).newInstance(application);
                AbstractC0150d.d(n2, "{\n                try {\n…          }\n            }");
                return n2;
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Cannot create an instance of " + cls, e3);
            } catch (NoSuchMethodException e4) {
                throw new RuntimeException("Cannot create an instance of " + cls, e4);
            } catch (InvocationTargetException e5) {
                throw new RuntimeException("Cannot create an instance of " + cls, e5);
            }
        }
        return super.a(cls);
    }
}

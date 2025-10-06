package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import j0.AbstractC0150d;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public final class L implements P {

    /* renamed from: a, reason: collision with root package name */
    public final Application f1171a;

    /* renamed from: b, reason: collision with root package name */
    public final O f1172b;

    /* renamed from: c, reason: collision with root package name */
    public final Bundle f1173c;

    /* renamed from: d, reason: collision with root package name */
    public final AbstractC0068o f1174d;

    /* renamed from: e, reason: collision with root package name */
    public final U.e f1175e;

    public L(Application application, androidx.fragment.app.i iVar, Bundle bundle) {
        O o2;
        this.f1175e = iVar.getSavedStateRegistry();
        this.f1174d = iVar.getLifecycle();
        this.f1173c = bundle;
        this.f1171a = application;
        if (application != null) {
            if (O.f1179c == null) {
                O.f1179c = new O(application);
            }
            o2 = O.f1179c;
            AbstractC0150d.b(o2);
        } else {
            o2 = new O(null);
        }
        this.f1172b = o2;
    }

    @Override // androidx.lifecycle.P
    public final N a(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return d(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // androidx.lifecycle.P
    public final N c(Class cls, Q.c cVar) {
        Constructor a2;
        R.b bVar = R.b.f615a;
        LinkedHashMap linkedHashMap = cVar.f610a;
        String str = (String) linkedHashMap.get(bVar);
        if (str != null) {
            if (linkedHashMap.get(H.f1161a) != null && linkedHashMap.get(H.f1162b) != null) {
                Application application = (Application) linkedHashMap.get(O.f1180d);
                boolean isAssignableFrom = AbstractC0054a.class.isAssignableFrom(cls);
                if (isAssignableFrom && application != null) {
                    a2 = M.a(cls, M.f1176a);
                } else {
                    a2 = M.a(cls, M.f1177b);
                }
                if (a2 == null) {
                    return this.f1172b.c(cls, cVar);
                }
                if (isAssignableFrom && application != null) {
                    return M.b(cls, a2, application, H.c(cVar));
                }
                return M.b(cls, a2, H.c(cVar));
            }
            if (this.f1174d != null) {
                return d(str, cls);
            }
            throw new IllegalStateException("SAVED_STATE_REGISTRY_OWNER_KEY andVIEW_MODEL_STORE_OWNER_KEY must be provided in the creation extras tosuccessfully create a ViewModel.");
        }
        throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
    }

    /* JADX WARN: Type inference failed for: r8v18, types: [androidx.lifecycle.Q, java.lang.Object] */
    public final N d(String str, Class cls) {
        Constructor a2;
        N b2;
        AutoCloseable autoCloseable;
        Application application;
        AbstractC0068o abstractC0068o = this.f1174d;
        if (abstractC0068o != null) {
            boolean isAssignableFrom = AbstractC0054a.class.isAssignableFrom(cls);
            if (isAssignableFrom && this.f1171a != null) {
                a2 = M.a(cls, M.f1176a);
            } else {
                a2 = M.a(cls, M.f1177b);
            }
            if (a2 == null) {
                if (this.f1171a != null) {
                    return this.f1172b.a(cls);
                }
                if (Q.f1182a == null) {
                    Q.f1182a = new Object();
                }
                Q q2 = Q.f1182a;
                AbstractC0150d.b(q2);
                return q2.a(cls);
            }
            U.e eVar = this.f1175e;
            AbstractC0150d.b(eVar);
            Bundle bundle = this.f1173c;
            Bundle a3 = eVar.a(str);
            Class[] clsArr = E.f1152f;
            E b3 = H.b(a3, bundle);
            F f2 = new F(str, b3);
            f2.h(eVar, abstractC0068o);
            EnumC0067n enumC0067n = ((v) abstractC0068o).f1207c;
            if (enumC0067n != EnumC0067n.f1197b && enumC0067n.compareTo(EnumC0067n.f1199d) < 0) {
                abstractC0068o.a(new C0059f(abstractC0068o, eVar, 1));
            } else {
                eVar.d();
            }
            if (isAssignableFrom && (application = this.f1171a) != null) {
                b2 = M.b(cls, a2, application, b3);
            } else {
                b2 = M.b(cls, a2, b3);
            }
            b2.getClass();
            R.a aVar = b2.f1178a;
            if (aVar != null) {
                if (aVar.f614d) {
                    R.a.a(f2);
                } else {
                    synchronized (aVar.f611a) {
                        autoCloseable = (AutoCloseable) aVar.f612b.put("androidx.lifecycle.savedstate.vm.tag", f2);
                    }
                    R.a.a(autoCloseable);
                }
            }
            return b2;
        }
        throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
    }
}

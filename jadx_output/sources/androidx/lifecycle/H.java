package androidx.lifecycle;

import android.os.Bundle;
import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public abstract class H {

    /* renamed from: a, reason: collision with root package name */
    public static final A.m f1161a = new A.m(16);

    /* renamed from: b, reason: collision with root package name */
    public static final A.m f1162b = new A.m(17);

    /* renamed from: c, reason: collision with root package name */
    public static final A.m f1163c = new A.m(15);

    public static final void a(N n2, U.e eVar, AbstractC0068o abstractC0068o) {
        AutoCloseable autoCloseable;
        AbstractC0150d.e(eVar, "registry");
        AbstractC0150d.e(abstractC0068o, "lifecycle");
        R.a aVar = n2.f1178a;
        if (aVar != null) {
            synchronized (aVar.f611a) {
                autoCloseable = (AutoCloseable) aVar.f612b.get("androidx.lifecycle.savedstate.vm.tag");
            }
        } else {
            autoCloseable = null;
        }
        F f2 = (F) autoCloseable;
        if (f2 != null && !f2.f1160c) {
            f2.h(eVar, abstractC0068o);
            EnumC0067n enumC0067n = ((v) abstractC0068o).f1207c;
            if (enumC0067n != EnumC0067n.f1197b && enumC0067n.compareTo(EnumC0067n.f1199d) < 0) {
                abstractC0068o.a(new C0059f(abstractC0068o, eVar, 1));
            } else {
                eVar.d();
            }
        }
    }

    public static E b(Bundle bundle, Bundle bundle2) {
        if (bundle == null) {
            if (bundle2 == null) {
                return new E();
            }
            HashMap hashMap = new HashMap();
            for (String str : bundle2.keySet()) {
                AbstractC0150d.d(str, "key");
                hashMap.put(str, bundle2.get(str));
            }
            return new E(hashMap);
        }
        ClassLoader classLoader = E.class.getClassLoader();
        AbstractC0150d.b(classLoader);
        bundle.setClassLoader(classLoader);
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
        if (parcelableArrayList != null && parcelableArrayList2 != null && parcelableArrayList.size() == parcelableArrayList2.size()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int size = parcelableArrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                Object obj = parcelableArrayList.get(i2);
                AbstractC0150d.c(obj, "null cannot be cast to non-null type kotlin.String");
                linkedHashMap.put((String) obj, parcelableArrayList2.get(i2));
            }
            return new E(linkedHashMap);
        }
        throw new IllegalStateException("Invalid bundle passed as restored state");
    }

    public static final E c(Q.c cVar) {
        J j2;
        Bundle bundle;
        A.m mVar = f1161a;
        LinkedHashMap linkedHashMap = cVar.f610a;
        U.g gVar = (U.g) linkedHashMap.get(mVar);
        if (gVar != null) {
            T t2 = (T) linkedHashMap.get(f1162b);
            if (t2 != null) {
                Bundle bundle2 = (Bundle) linkedHashMap.get(f1163c);
                String str = (String) linkedHashMap.get(R.b.f615a);
                if (str != null) {
                    U.d b2 = gVar.getSavedStateRegistry().b();
                    if (b2 instanceof J) {
                        j2 = (J) b2;
                    } else {
                        j2 = null;
                    }
                    if (j2 != null) {
                        LinkedHashMap linkedHashMap2 = d(t2).f1170b;
                        E e2 = (E) linkedHashMap2.get(str);
                        if (e2 == null) {
                            Class[] clsArr = E.f1152f;
                            j2.b();
                            Bundle bundle3 = j2.f1168c;
                            if (bundle3 != null) {
                                bundle = bundle3.getBundle(str);
                            } else {
                                bundle = null;
                            }
                            Bundle bundle4 = j2.f1168c;
                            if (bundle4 != null) {
                                bundle4.remove(str);
                            }
                            Bundle bundle5 = j2.f1168c;
                            if (bundle5 != null && bundle5.isEmpty()) {
                                j2.f1168c = null;
                            }
                            E b3 = b(bundle, bundle2);
                            linkedHashMap2.put(str, b3);
                            return b3;
                        }
                        return e2;
                    }
                    throw new IllegalStateException("enableSavedStateHandles() wasn't called prior to createSavedStateHandle() call");
                }
                throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_KEY`");
            }
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_STORE_OWNER_KEY`");
        }
        throw new IllegalArgumentException("CreationExtras must have a value by `SAVED_STATE_REGISTRY_OWNER_KEY`");
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.lifecycle.P, java.lang.Object] */
    public static final K d(T t2) {
        Q.b bVar;
        ?? obj = new Object();
        S viewModelStore = t2.getViewModelStore();
        if (t2 instanceof InterfaceC0062i) {
            bVar = ((InterfaceC0062i) t2).getDefaultViewModelCreationExtras();
        } else {
            bVar = Q.a.f609b;
        }
        AbstractC0150d.e(viewModelStore, "store");
        AbstractC0150d.e(bVar, "defaultCreationExtras");
        return (K) new D0.h(viewModelStore, obj, bVar).m(j0.h.a(K.class), "androidx.lifecycle.internal.SavedStateHandlesVM");
    }
}

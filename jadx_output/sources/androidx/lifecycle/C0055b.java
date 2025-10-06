package androidx.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: androidx.lifecycle.b, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0055b {

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f1184a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    public final HashMap f1185b;

    public C0055b(HashMap hashMap) {
        this.f1185b = hashMap;
        for (Map.Entry entry : hashMap.entrySet()) {
            EnumC0066m enumC0066m = (EnumC0066m) entry.getValue();
            List list = (List) this.f1184a.get(enumC0066m);
            if (list == null) {
                list = new ArrayList();
                this.f1184a.put(enumC0066m, list);
            }
            list.add((C0056c) entry.getKey());
        }
    }

    public static void a(List list, InterfaceC0072t interfaceC0072t, EnumC0066m enumC0066m, InterfaceC0071s interfaceC0071s) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                C0056c c0056c = (C0056c) list.get(size);
                c0056c.getClass();
                try {
                    int i2 = c0056c.f1186a;
                    Method method = c0056c.f1187b;
                    if (i2 != 0) {
                        if (i2 != 1) {
                            if (i2 == 2) {
                                method.invoke(interfaceC0071s, interfaceC0072t, enumC0066m);
                            }
                        } else {
                            method.invoke(interfaceC0071s, interfaceC0072t);
                        }
                    } else {
                        method.invoke(interfaceC0071s, null);
                    }
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException(e2);
                } catch (InvocationTargetException e3) {
                    throw new RuntimeException("Failed to call observer method", e3.getCause());
                }
            }
        }
    }
}

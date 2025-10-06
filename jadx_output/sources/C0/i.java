package C0;

import j0.AbstractC0150d;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class i implements InvocationHandler {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f221a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f222b;

    /* renamed from: c, reason: collision with root package name */
    public String f223c;

    public i(ArrayList arrayList) {
        this.f221a = arrayList;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        AbstractC0150d.e(obj, "proxy");
        AbstractC0150d.e(method, "method");
        if (objArr == null) {
            objArr = new Object[0];
        }
        String name = method.getName();
        Class<?> returnType = method.getReturnType();
        if (AbstractC0150d.a(name, "supports") && AbstractC0150d.a(Boolean.TYPE, returnType)) {
            return Boolean.TRUE;
        }
        if (AbstractC0150d.a(name, "unsupported") && AbstractC0150d.a(Void.TYPE, returnType)) {
            this.f222b = true;
            return null;
        }
        boolean a2 = AbstractC0150d.a(name, "protocols");
        ArrayList arrayList = this.f221a;
        if (a2 && objArr.length == 0) {
            return arrayList;
        }
        if ((AbstractC0150d.a(name, "selectProtocol") || AbstractC0150d.a(name, "select")) && String.class.equals(returnType) && objArr.length == 1) {
            Object obj2 = objArr[0];
            if (obj2 instanceof List) {
                if (obj2 != null) {
                    List list = (List) obj2;
                    int size = list.size();
                    if (size >= 0) {
                        int i2 = 0;
                        while (true) {
                            int i3 = i2 + 1;
                            Object obj3 = list.get(i2);
                            if (obj3 != null) {
                                String str = (String) obj3;
                                if (arrayList.contains(str)) {
                                    this.f223c = str;
                                    return str;
                                }
                                if (i2 == size) {
                                    break;
                                }
                                i2 = i3;
                            } else {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                        }
                    }
                    String str2 = (String) arrayList.get(0);
                    this.f223c = str2;
                    return str2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<*>");
            }
        }
        if ((AbstractC0150d.a(name, "protocolSelected") || AbstractC0150d.a(name, "selected")) && objArr.length == 1) {
            Object obj4 = objArr[0];
            if (obj4 != null) {
                this.f223c = (String) obj4;
                return null;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        return method.invoke(this, Arrays.copyOf(objArr, objArr.length));
    }
}

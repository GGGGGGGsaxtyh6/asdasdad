package C0;

import b0.AbstractC0084j;
import j0.AbstractC0150d;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import u0.r;

/* loaded from: classes.dex */
public final class j extends o {

    /* renamed from: c, reason: collision with root package name */
    public final Method f224c;

    /* renamed from: d, reason: collision with root package name */
    public final Method f225d;

    /* renamed from: e, reason: collision with root package name */
    public final Method f226e;

    /* renamed from: f, reason: collision with root package name */
    public final Class f227f;

    /* renamed from: g, reason: collision with root package name */
    public final Class f228g;

    public j(Method method, Method method2, Method method3, Class cls, Class cls2) {
        this.f224c = method;
        this.f225d = method2;
        this.f226e = method3;
        this.f227f = cls;
        this.f228g = cls2;
    }

    @Override // C0.o
    public final void a(SSLSocket sSLSocket) {
        try {
            this.f226e.invoke(null, sSLSocket);
        } catch (IllegalAccessException e2) {
            throw new AssertionError("failed to remove ALPN", e2);
        } catch (InvocationTargetException e3) {
            throw new AssertionError("failed to remove ALPN", e3);
        }
    }

    @Override // C0.o
    public final void d(SSLSocket sSLSocket, String str, List list) {
        AbstractC0150d.e(list, "protocols");
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((r) obj) != r.HTTP_1_0) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(AbstractC0084j.I(arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((r) it.next()).f2548a);
        }
        try {
            this.f224c.invoke(null, sSLSocket, Proxy.newProxyInstance(o.class.getClassLoader(), new Class[]{this.f227f, this.f228g}, new i(arrayList2)));
        } catch (IllegalAccessException e2) {
            throw new AssertionError("failed to set ALPN", e2);
        } catch (InvocationTargetException e3) {
            throw new AssertionError("failed to set ALPN", e3);
        }
    }

    @Override // C0.o
    public final String f(SSLSocket sSLSocket) {
        try {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(this.f225d.invoke(null, sSLSocket));
            if (invocationHandler != null) {
                i iVar = (i) invocationHandler;
                boolean z2 = iVar.f222b;
                if (!z2 && iVar.f223c == null) {
                    o.i("ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", 4, null);
                    return null;
                }
                if (z2) {
                    return null;
                }
                return iVar.f223c;
            }
            throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.platform.Jdk8WithJettyBootPlatform.AlpnProvider");
        } catch (IllegalAccessException e2) {
            throw new AssertionError("failed to get ALPN selected protocol", e2);
        } catch (InvocationTargetException e3) {
            throw new AssertionError("failed to get ALPN selected protocol", e3);
        }
    }
}

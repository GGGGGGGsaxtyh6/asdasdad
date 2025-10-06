package C0;

import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import b0.AbstractC0081g;
import j0.AbstractC0150d;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes.dex */
public final class c extends o {

    /* renamed from: e, reason: collision with root package name */
    public static final boolean f204e;

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f205c;

    /* renamed from: d, reason: collision with root package name */
    public final D0.h f206d;

    static {
        boolean z2 = false;
        if (g.c() && Build.VERSION.SDK_INT < 30) {
            z2 = true;
        }
        f204e = z2;
    }

    public c() {
        D0.e eVar;
        Method method;
        Method method2;
        int i2 = 0;
        Method method3 = null;
        try {
            Class<?> cls = Class.forName(AbstractC0150d.h(".OpenSSLSocketImpl", "com.android.org.conscrypt"));
            Class.forName(AbstractC0150d.h(".OpenSSLSocketFactoryImpl", "com.android.org.conscrypt"));
            Class.forName(AbstractC0150d.h(".SSLParametersImpl", "com.android.org.conscrypt"));
            eVar = new D0.e(cls);
        } catch (Exception e2) {
            o.f236a.getClass();
            o.i("unable to load android socket classes", 5, e2);
            eVar = null;
        }
        ArrayList B2 = AbstractC0081g.B(new D0.l[]{eVar, new D0.k(D0.e.f249f), new D0.k(D0.i.f261b), new D0.k(D0.g.f255a)});
        ArrayList arrayList = new ArrayList();
        Iterator it = B2.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (((D0.l) next).c()) {
                arrayList.add(next);
            }
        }
        this.f205c = arrayList;
        try {
            Class<?> cls2 = Class.forName("dalvik.system.CloseGuard");
            Method method4 = cls2.getMethod("get", null);
            method2 = cls2.getMethod("open", String.class);
            method = cls2.getMethod("warnIfOpen", null);
            method3 = method4;
        } catch (Exception unused) {
            method = null;
            method2 = null;
        }
        this.f206d = new D0.h(method3, method2, method, i2);
    }

    @Override // C0.o
    public final f b(X509TrustManager x509TrustManager) {
        X509TrustManagerExtensions x509TrustManagerExtensions;
        AbstractC0150d.e(x509TrustManager, "trustManager");
        D0.a aVar = null;
        try {
            x509TrustManagerExtensions = new X509TrustManagerExtensions(x509TrustManager);
        } catch (IllegalArgumentException unused) {
            x509TrustManagerExtensions = null;
        }
        if (x509TrustManagerExtensions != null) {
            aVar = new D0.a(x509TrustManager, x509TrustManagerExtensions);
        }
        if (aVar == null) {
            return super.b(x509TrustManager);
        }
        return aVar;
    }

    @Override // C0.o
    public final G0.d c(X509TrustManager x509TrustManager) {
        AbstractC0150d.e(x509TrustManager, "trustManager");
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new b(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.c(x509TrustManager);
        }
    }

    @Override // C0.o
    public final void d(SSLSocket sSLSocket, String str, List list) {
        Object obj;
        AbstractC0150d.e(list, "protocols");
        Iterator it = this.f205c.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (((D0.l) obj).a(sSLSocket)) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        D0.l lVar = (D0.l) obj;
        if (lVar != null) {
            lVar.d(sSLSocket, str, list);
        }
    }

    @Override // C0.o
    public final void e(Socket socket, InetSocketAddress inetSocketAddress, int i2) {
        AbstractC0150d.e(inetSocketAddress, "address");
        try {
            socket.connect(inetSocketAddress, i2);
        } catch (ClassCastException e2) {
            if (Build.VERSION.SDK_INT == 26) {
                throw new IOException("Exception in connect", e2);
            }
            throw e2;
        }
    }

    @Override // C0.o
    public final String f(SSLSocket sSLSocket) {
        Object obj;
        Iterator it = this.f205c.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (((D0.l) obj).a(sSLSocket)) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        D0.l lVar = (D0.l) obj;
        if (lVar == null) {
            return null;
        }
        return lVar.b(sSLSocket);
    }

    @Override // C0.o
    public final Object g() {
        D0.h hVar = this.f206d;
        hVar.getClass();
        Method method = (Method) hVar.f258b;
        if (method == null) {
            return null;
        }
        try {
            Object invoke = method.invoke(null, null);
            Method method2 = (Method) hVar.f259c;
            AbstractC0150d.b(method2);
            method2.invoke(invoke, "response.body().close()");
            return invoke;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // C0.o
    public final boolean h(String str) {
        AbstractC0150d.e(str, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }

    @Override // C0.o
    public final void j(Object obj, String str) {
        AbstractC0150d.e(str, "message");
        D0.h hVar = this.f206d;
        hVar.getClass();
        if (obj != null) {
            try {
                Method method = (Method) hVar.f260d;
                AbstractC0150d.b(method);
                method.invoke(obj, null);
                return;
            } catch (Exception unused) {
            }
        }
        o.i(str, 5, null);
    }
}

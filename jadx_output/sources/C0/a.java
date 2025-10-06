package C0;

import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import b0.AbstractC0081g;
import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes.dex */
public final class a extends o {

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f200d;

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f201c;

    static {
        boolean z2;
        if (g.c() && Build.VERSION.SDK_INT >= 29) {
            z2 = true;
        } else {
            z2 = false;
        }
        f200d = z2;
    }

    public a() {
        D0.i iVar;
        int i2 = 1;
        if (g.c() && Build.VERSION.SDK_INT >= 29) {
            iVar = new D0.i(i2);
        } else {
            iVar = null;
        }
        ArrayList B2 = AbstractC0081g.B(new D0.l[]{iVar, new D0.k(D0.e.f249f), new D0.k(D0.i.f261b), new D0.k(D0.g.f255a)});
        ArrayList arrayList = new ArrayList();
        Iterator it = B2.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (((D0.l) next).c()) {
                arrayList.add(next);
            }
        }
        this.f201c = arrayList;
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
    public final void d(SSLSocket sSLSocket, String str, List list) {
        Object obj;
        AbstractC0150d.e(list, "protocols");
        Iterator it = this.f201c.iterator();
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
    public final String f(SSLSocket sSLSocket) {
        Object obj;
        Iterator it = this.f201c.iterator();
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
    public final boolean h(String str) {
        AbstractC0150d.e(str, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }
}

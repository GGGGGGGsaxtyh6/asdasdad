package C0;

import j0.AbstractC0150d;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes.dex */
public final class n extends o {

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f234d;

    /* renamed from: c, reason: collision with root package name */
    public final K0.a f235c = new K0.a();

    static {
        boolean z2 = false;
        try {
            Class.forName("K0.a", false, m.class.getClassLoader());
            z2 = true;
        } catch (ClassNotFoundException unused) {
        }
        f234d = z2;
    }

    @Override // C0.o
    public final void d(SSLSocket sSLSocket, String str, List list) {
        AbstractC0150d.e(list, "protocols");
    }

    @Override // C0.o
    public final String f(SSLSocket sSLSocket) {
        return null;
    }

    @Override // C0.o
    public final SSLContext k() {
        SSLContext sSLContext = SSLContext.getInstance("TLSv1.3", this.f235c);
        AbstractC0150d.d(sSLContext, "getInstance(\"TLSv1.3\", provider)");
        return sSLContext;
    }

    @Override // C0.o
    public final X509TrustManager m() {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm(), this.f235c);
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        AbstractC0150d.b(trustManagers);
        if (trustManagers.length == 1) {
            TrustManager trustManager = trustManagers[0];
            if (trustManager instanceof X509TrustManager) {
                if (trustManager != null) {
                    return (X509TrustManager) trustManager;
                }
                throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
            }
        }
        String arrays = Arrays.toString(trustManagers);
        AbstractC0150d.d(arrays, "toString(this)");
        throw new IllegalStateException(AbstractC0150d.h(arrays, "Unexpected default trust managers: ").toString());
    }
}

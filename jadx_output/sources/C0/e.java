package C0;

import j0.AbstractC0150d;
import java.security.KeyStore;
import java.security.Provider;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;

/* loaded from: classes.dex */
public final class e extends o {

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f207d;

    /* renamed from: c, reason: collision with root package name */
    public final Provider f208c = new BouncyCastleJsseProvider();

    static {
        boolean z2 = false;
        try {
            Class.forName("org.bouncycastle.jsse.provider.BouncyCastleJsseProvider", false, d.class.getClassLoader());
            z2 = true;
        } catch (ClassNotFoundException unused) {
        }
        f207d = z2;
    }

    @Override // C0.o
    public final void d(SSLSocket sSLSocket, String str, List list) {
        AbstractC0150d.e(list, "protocols");
        super.d(sSLSocket, str, list);
    }

    @Override // C0.o
    public final String f(SSLSocket sSLSocket) {
        return null;
    }

    @Override // C0.o
    public final SSLContext k() {
        SSLContext sSLContext = SSLContext.getInstance("TLS", this.f208c);
        AbstractC0150d.d(sSLContext, "getInstance(\"TLS\", provider)");
        return sSLContext;
    }

    @Override // C0.o
    public final X509TrustManager m() {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX", "BCJSSE");
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

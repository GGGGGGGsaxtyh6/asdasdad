package C0;

import j0.AbstractC0150d;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes.dex */
public class o {

    /* renamed from: a, reason: collision with root package name */
    public static volatile o f236a;

    /* renamed from: b, reason: collision with root package name */
    public static final Logger f237b;

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0093, code lost:
    
        if (r1 != null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b5, code lost:
    
        if (r1 != null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00d5, code lost:
    
        if (r1 != null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00fb, code lost:
    
        if (java.lang.Integer.parseInt(r3) >= 9) goto L65;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0163  */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [C0.o] */
    static {
        /*
            Method dump skipped, instructions count: 375
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: C0.o.<clinit>():void");
    }

    public static void i(String str, int i2, Throwable th) {
        Level level;
        AbstractC0150d.e(str, "message");
        if (i2 == 5) {
            level = Level.WARNING;
        } else {
            level = Level.INFO;
        }
        f237b.log(level, str, th);
    }

    public f b(X509TrustManager x509TrustManager) {
        AbstractC0150d.e(x509TrustManager, "trustManager");
        return new G0.a(c(x509TrustManager));
    }

    public G0.d c(X509TrustManager x509TrustManager) {
        AbstractC0150d.e(x509TrustManager, "trustManager");
        X509Certificate[] acceptedIssuers = x509TrustManager.getAcceptedIssuers();
        AbstractC0150d.d(acceptedIssuers, "trustManager.acceptedIssuers");
        return new G0.b((X509Certificate[]) Arrays.copyOf(acceptedIssuers, acceptedIssuers.length));
    }

    public void d(SSLSocket sSLSocket, String str, List list) {
        AbstractC0150d.e(list, "protocols");
    }

    public void e(Socket socket, InetSocketAddress inetSocketAddress, int i2) {
        AbstractC0150d.e(inetSocketAddress, "address");
        socket.connect(inetSocketAddress, i2);
    }

    public String f(SSLSocket sSLSocket) {
        return null;
    }

    public Object g() {
        if (f237b.isLoggable(Level.FINE)) {
            return new Throwable("response.body().close()");
        }
        return null;
    }

    public boolean h(String str) {
        AbstractC0150d.e(str, "hostname");
        return true;
    }

    public void j(Object obj, String str) {
        AbstractC0150d.e(str, "message");
        if (obj == null) {
            str = AbstractC0150d.h(" To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);", str);
        }
        i(str, 5, (Throwable) obj);
    }

    public SSLContext k() {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        AbstractC0150d.d(sSLContext, "getInstance(\"TLS\")");
        return sSLContext;
    }

    public SSLSocketFactory l(X509TrustManager x509TrustManager) {
        try {
            SSLContext k2 = k();
            k2.init(null, new TrustManager[]{x509TrustManager}, null);
            SSLSocketFactory socketFactory = k2.getSocketFactory();
            AbstractC0150d.d(socketFactory, "newSSLContext().apply {\n…ll)\n      }.socketFactory");
            return socketFactory;
        } catch (GeneralSecurityException e2) {
            throw new AssertionError(AbstractC0150d.h(e2, "No System TLS: "), e2);
        }
    }

    public X509TrustManager m() {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
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

    public final String toString() {
        return getClass().getSimpleName();
    }

    public void a(SSLSocket sSLSocket) {
    }
}

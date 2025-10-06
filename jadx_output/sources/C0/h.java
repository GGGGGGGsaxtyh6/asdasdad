package C0;

import b0.AbstractC0084j;
import j0.AbstractC0150d;
import java.security.KeyStore;
import java.security.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.conscrypt.Conscrypt;
import u0.r;

/* loaded from: classes.dex */
public final class h extends o {

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f219d;

    /* renamed from: c, reason: collision with root package name */
    public final Provider f220c;

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0036, code lost:
    
        if (r1.patch() >= 0) goto L17;
     */
    static {
        /*
            r0 = 0
            java.lang.String r1 = "org.conscrypt.Conscrypt$Version"
            java.lang.Class<C0.f> r2 = C0.f.class
            java.lang.ClassLoader r2 = r2.getClassLoader()     // Catch: java.lang.Throwable -> L39
            java.lang.Class.forName(r1, r0, r2)     // Catch: java.lang.Throwable -> L39
            boolean r1 = org.conscrypt.Conscrypt.isAvailable()     // Catch: java.lang.Throwable -> L39
            if (r1 == 0) goto L39
            org.conscrypt.Conscrypt$Version r1 = org.conscrypt.Conscrypt.version()     // Catch: java.lang.Throwable -> L39
            int r2 = r1.major()     // Catch: java.lang.Throwable -> L39
            r3 = 1
            r4 = 2
            if (r2 == r4) goto L25
            int r1 = r1.major()     // Catch: java.lang.Throwable -> L39
            if (r1 <= r4) goto L39
            goto L38
        L25:
            int r2 = r1.minor()     // Catch: java.lang.Throwable -> L39
            if (r2 == r3) goto L32
            int r1 = r1.minor()     // Catch: java.lang.Throwable -> L39
            if (r1 <= r3) goto L39
            goto L38
        L32:
            int r1 = r1.patch()     // Catch: java.lang.Throwable -> L39
            if (r1 < 0) goto L39
        L38:
            r0 = r3
        L39:
            C0.h.f219d = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: C0.h.<clinit>():void");
    }

    public h() {
        Provider newProvider = Conscrypt.newProvider();
        AbstractC0150d.d(newProvider, "newProvider()");
        this.f220c = newProvider;
    }

    @Override // C0.o
    public final void d(SSLSocket sSLSocket, String str, List list) {
        AbstractC0150d.e(list, "protocols");
        if (Conscrypt.isConscrypt(sSLSocket)) {
            Conscrypt.setUseSessionTickets(sSLSocket, true);
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
            Object[] array = arrayList2.toArray(new String[0]);
            if (array != null) {
                Conscrypt.setApplicationProtocols(sSLSocket, (String[]) array);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
    }

    @Override // C0.o
    public final String f(SSLSocket sSLSocket) {
        if (Conscrypt.isConscrypt(sSLSocket)) {
            return Conscrypt.getApplicationProtocol(sSLSocket);
        }
        return null;
    }

    @Override // C0.o
    public final SSLContext k() {
        SSLContext sSLContext = SSLContext.getInstance("TLS", this.f220c);
        AbstractC0150d.d(sSLContext, "getInstance(\"TLS\", provider)");
        return sSLContext;
    }

    @Override // C0.o
    public final SSLSocketFactory l(X509TrustManager x509TrustManager) {
        SSLContext k2 = k();
        k2.init(null, new TrustManager[]{x509TrustManager}, null);
        SSLSocketFactory socketFactory = k2.getSocketFactory();
        AbstractC0150d.d(socketFactory, "newSSLContext().apply {\n…null)\n    }.socketFactory");
        return socketFactory;
    }

    @Override // C0.o
    public final X509TrustManager m() {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        AbstractC0150d.b(trustManagers);
        if (trustManagers.length == 1) {
            TrustManager trustManager = trustManagers[0];
            if (trustManager instanceof X509TrustManager) {
                if (trustManager != null) {
                    X509TrustManager x509TrustManager = (X509TrustManager) trustManager;
                    Conscrypt.setHostnameVerifier(x509TrustManager, g.f218a);
                    return x509TrustManager;
                }
                throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
            }
        }
        String arrays = Arrays.toString(trustManagers);
        AbstractC0150d.d(arrays, "toString(this)");
        throw new IllegalStateException(AbstractC0150d.h(arrays, "Unexpected default trust managers: ").toString());
    }
}

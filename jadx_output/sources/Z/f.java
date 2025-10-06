package Z;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes.dex */
public final class f implements X509TrustManager {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f699a;

    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        int i2 = this.f699a;
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        int i2 = this.f699a;
    }

    @Override // javax.net.ssl.X509TrustManager
    public final X509Certificate[] getAcceptedIssuers() {
        switch (this.f699a) {
            case 0:
                return new X509Certificate[0];
            default:
                return new X509Certificate[0];
        }
    }

    private final void a(X509Certificate[] x509CertificateArr, String str) {
    }

    private final void b(X509Certificate[] x509CertificateArr, String str) {
    }

    private final void c(X509Certificate[] x509CertificateArr, String str) {
    }

    private final void d(X509Certificate[] x509CertificateArr, String str) {
    }
}

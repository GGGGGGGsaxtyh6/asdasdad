package D0;

import android.net.http.X509TrustManagerExtensions;
import j0.AbstractC0150d;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes.dex */
public final class a extends C0.f {

    /* renamed from: k, reason: collision with root package name */
    public final X509TrustManager f243k;

    /* renamed from: l, reason: collision with root package name */
    public final X509TrustManagerExtensions f244l;

    public a(X509TrustManager x509TrustManager, X509TrustManagerExtensions x509TrustManagerExtensions) {
        AbstractC0150d.e(x509TrustManager, "trustManager");
        this.f243k = x509TrustManager;
        this.f244l = x509TrustManagerExtensions;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof a) && ((a) obj).f243k == this.f243k) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return System.identityHashCode(this.f243k);
    }

    @Override // C0.f
    public final List j(String str, List list) {
        AbstractC0150d.e(list, "chain");
        AbstractC0150d.e(str, "hostname");
        Object[] array = list.toArray(new X509Certificate[0]);
        if (array != null) {
            try {
                List<X509Certificate> checkServerTrusted = this.f244l.checkServerTrusted((X509Certificate[]) array, "RSA", str);
                AbstractC0150d.d(checkServerTrusted, "x509TrustManagerExtensio…ficates, \"RSA\", hostname)");
                return checkServerTrusted;
            } catch (CertificateException e2) {
                SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e2.getMessage());
                sSLPeerUnverifiedException.initCause(e2);
                throw sSLPeerUnverifiedException;
            }
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }
}

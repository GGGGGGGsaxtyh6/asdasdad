package C0;

import j0.AbstractC0150d;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes.dex */
public final class b implements G0.d {

    /* renamed from: a, reason: collision with root package name */
    public final X509TrustManager f202a;

    /* renamed from: b, reason: collision with root package name */
    public final Method f203b;

    public b(X509TrustManager x509TrustManager, Method method) {
        AbstractC0150d.e(x509TrustManager, "trustManager");
        this.f202a = x509TrustManager;
        this.f203b = method;
    }

    @Override // G0.d
    public final X509Certificate a(X509Certificate x509Certificate) {
        AbstractC0150d.e(x509Certificate, "cert");
        try {
            Object invoke = this.f203b.invoke(this.f202a, x509Certificate);
            if (invoke != null) {
                return ((TrustAnchor) invoke).getTrustedCert();
            }
            throw new NullPointerException("null cannot be cast to non-null type java.security.cert.TrustAnchor");
        } catch (IllegalAccessException e2) {
            throw new AssertionError("unable to get issues and signature", e2);
        } catch (InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (AbstractC0150d.a(this.f202a, bVar.f202a) && AbstractC0150d.a(this.f203b, bVar.f203b)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f203b.hashCode() + (this.f202a.hashCode() * 31);
    }

    public final String toString() {
        return "CustomTrustRootIndex(trustManager=" + this.f202a + ", findByIssuerAndSignatureMethod=" + this.f203b + ')';
    }
}

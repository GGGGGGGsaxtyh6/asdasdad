package G0;

import j0.AbstractC0150d;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes.dex */
public final class b implements d {

    /* renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f312a;

    public b(X509Certificate... x509CertificateArr) {
        AbstractC0150d.e(x509CertificateArr, "caCerts");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int length = x509CertificateArr.length;
        int i2 = 0;
        while (i2 < length) {
            X509Certificate x509Certificate = x509CertificateArr[i2];
            i2++;
            X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
            AbstractC0150d.d(subjectX500Principal, "caCert.subjectX500Principal");
            Object obj = linkedHashMap.get(subjectX500Principal);
            if (obj == null) {
                obj = new LinkedHashSet();
                linkedHashMap.put(subjectX500Principal, obj);
            }
            ((Set) obj).add(x509Certificate);
        }
        this.f312a = linkedHashMap;
    }

    @Override // G0.d
    public final X509Certificate a(X509Certificate x509Certificate) {
        AbstractC0150d.e(x509Certificate, "cert");
        Set set = (Set) this.f312a.get(x509Certificate.getIssuerX500Principal());
        Object obj = null;
        if (set == null) {
            return null;
        }
        Iterator it = set.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            try {
                x509Certificate.verify(((X509Certificate) next).getPublicKey());
                obj = next;
                break;
            } catch (Exception unused) {
            }
        }
        return (X509Certificate) obj;
    }

    public final boolean equals(Object obj) {
        if (obj != this && (!(obj instanceof b) || !AbstractC0150d.a(((b) obj).f312a, this.f312a))) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.f312a.hashCode();
    }
}

package u0;

import a0.C0050d;
import androidx.lifecycle.I;
import b0.AbstractC0084j;
import i0.InterfaceC0131a;
import j0.AbstractC0150d;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class j {

    /* renamed from: a, reason: collision with root package name */
    public final y f2470a;

    /* renamed from: b, reason: collision with root package name */
    public final g f2471b;

    /* renamed from: c, reason: collision with root package name */
    public final List f2472c;

    /* renamed from: d, reason: collision with root package name */
    public final C0050d f2473d;

    public j(y yVar, g gVar, List list, InterfaceC0131a interfaceC0131a) {
        this.f2470a = yVar;
        this.f2471b = gVar;
        this.f2472c = list;
        this.f2473d = new C0050d(new I(interfaceC0131a));
    }

    public final List a() {
        return (List) this.f2473d.a();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof j) {
            j jVar = (j) obj;
            if (jVar.f2470a == this.f2470a && AbstractC0150d.a(jVar.f2471b, this.f2471b) && AbstractC0150d.a(jVar.a(), a()) && AbstractC0150d.a(jVar.f2472c, this.f2472c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f2472c.hashCode() + ((a().hashCode() + ((this.f2471b.hashCode() + ((this.f2470a.hashCode() + 527) * 31)) * 31)) * 31);
    }

    public final String toString() {
        String type;
        String type2;
        List<Certificate> a2 = a();
        ArrayList arrayList = new ArrayList(AbstractC0084j.I(a2));
        for (Certificate certificate : a2) {
            if (certificate instanceof X509Certificate) {
                type2 = ((X509Certificate) certificate).getSubjectDN().toString();
            } else {
                type2 = certificate.getType();
                AbstractC0150d.d(type2, "type");
            }
            arrayList.add(type2);
        }
        String obj = arrayList.toString();
        StringBuilder sb = new StringBuilder("Handshake{tlsVersion=");
        sb.append(this.f2470a);
        sb.append(" cipherSuite=");
        sb.append(this.f2471b);
        sb.append(" peerCertificates=");
        sb.append(obj);
        sb.append(" localCertificates=");
        List<Certificate> list = this.f2472c;
        ArrayList arrayList2 = new ArrayList(AbstractC0084j.I(list));
        for (Certificate certificate2 : list) {
            if (certificate2 instanceof X509Certificate) {
                type = ((X509Certificate) certificate2).getSubjectDN().toString();
            } else {
                type = certificate2.getType();
                AbstractC0150d.d(type, "type");
            }
            arrayList2.add(type);
        }
        sb.append(arrayList2);
        sb.append('}');
        return sb.toString();
    }
}

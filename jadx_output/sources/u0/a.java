package u0;

import j0.AbstractC0150d;
import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public final b f2405a;

    /* renamed from: b, reason: collision with root package name */
    public final SocketFactory f2406b;

    /* renamed from: c, reason: collision with root package name */
    public final SSLSocketFactory f2407c;

    /* renamed from: d, reason: collision with root package name */
    public final HostnameVerifier f2408d;

    /* renamed from: e, reason: collision with root package name */
    public final e f2409e;

    /* renamed from: f, reason: collision with root package name */
    public final b f2410f;

    /* renamed from: g, reason: collision with root package name */
    public final ProxySelector f2411g;
    public final m h;

    /* renamed from: i, reason: collision with root package name */
    public final List f2412i;

    /* renamed from: j, reason: collision with root package name */
    public final List f2413j;

    public a(String str, int i2, b bVar, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, e eVar, b bVar2, List list, List list2, ProxySelector proxySelector) {
        AbstractC0150d.e(str, "uriHost");
        AbstractC0150d.e(bVar, "dns");
        AbstractC0150d.e(socketFactory, "socketFactory");
        AbstractC0150d.e(bVar2, "proxyAuthenticator");
        AbstractC0150d.e(list, "protocols");
        AbstractC0150d.e(list2, "connectionSpecs");
        AbstractC0150d.e(proxySelector, "proxySelector");
        this.f2405a = bVar;
        this.f2406b = socketFactory;
        this.f2407c = sSLSocketFactory;
        this.f2408d = hostnameVerifier;
        this.f2409e = eVar;
        this.f2410f = bVar2;
        this.f2411g = proxySelector;
        l lVar = new l();
        String str2 = sSLSocketFactory != null ? "https" : "http";
        if (str2.equalsIgnoreCase("http")) {
            lVar.f2475a = "http";
        } else if (str2.equalsIgnoreCase("https")) {
            lVar.f2475a = "https";
        } else {
            throw new IllegalArgumentException(AbstractC0150d.h(str2, "unexpected scheme: "));
        }
        String u2 = C0.m.u(b.e(str, 0, 0, 7));
        if (u2 != null) {
            lVar.f2478d = u2;
            if (1 <= i2 && i2 < 65536) {
                lVar.f2479e = i2;
                this.h = lVar.a();
                this.f2412i = v0.b.w(list);
                this.f2413j = v0.b.w(list2);
                return;
            }
            throw new IllegalArgumentException(AbstractC0150d.h(Integer.valueOf(i2), "unexpected port: ").toString());
        }
        throw new IllegalArgumentException(AbstractC0150d.h(str, "unexpected host: "));
    }

    public final boolean a(a aVar) {
        AbstractC0150d.e(aVar, "that");
        if (AbstractC0150d.a(this.f2405a, aVar.f2405a) && AbstractC0150d.a(this.f2410f, aVar.f2410f) && AbstractC0150d.a(this.f2412i, aVar.f2412i) && AbstractC0150d.a(this.f2413j, aVar.f2413j) && AbstractC0150d.a(this.f2411g, aVar.f2411g) && AbstractC0150d.a(null, null) && AbstractC0150d.a(this.f2407c, aVar.f2407c) && AbstractC0150d.a(this.f2408d, aVar.f2408d) && AbstractC0150d.a(this.f2409e, aVar.f2409e) && this.h.f2487e == aVar.h.f2487e) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (AbstractC0150d.a(this.h, aVar.h) && a(aVar)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f2409e) + ((Objects.hashCode(this.f2408d) + ((Objects.hashCode(this.f2407c) + ((this.f2411g.hashCode() + ((this.f2413j.hashCode() + ((this.f2412i.hashCode() + ((this.f2410f.hashCode() + ((this.f2405a.hashCode() + ((this.h.h.hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 961)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Address{");
        m mVar = this.h;
        sb.append(mVar.f2486d);
        sb.append(':');
        sb.append(mVar.f2487e);
        sb.append(", ");
        sb.append(AbstractC0150d.h(this.f2411g, "proxySelector="));
        sb.append('}');
        return sb.toString();
    }
}

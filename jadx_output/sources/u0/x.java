package u0;

import j0.AbstractC0150d;
import java.net.InetSocketAddress;
import java.net.Proxy;

/* loaded from: classes.dex */
public final class x {

    /* renamed from: a, reason: collision with root package name */
    public final a f2580a;

    /* renamed from: b, reason: collision with root package name */
    public final Proxy f2581b;

    /* renamed from: c, reason: collision with root package name */
    public final InetSocketAddress f2582c;

    public x(a aVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        AbstractC0150d.e(inetSocketAddress, "socketAddress");
        this.f2580a = aVar;
        this.f2581b = proxy;
        this.f2582c = inetSocketAddress;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof x) {
            x xVar = (x) obj;
            if (AbstractC0150d.a(xVar.f2580a, this.f2580a) && AbstractC0150d.a(xVar.f2581b, this.f2581b) && AbstractC0150d.a(xVar.f2582c, this.f2582c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f2582c.hashCode() + ((this.f2581b.hashCode() + ((this.f2580a.hashCode() + 527) * 31)) * 31);
    }

    public final String toString() {
        return "Route{" + this.f2582c + '}';
    }
}

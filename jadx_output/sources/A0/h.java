package A0;

import H0.n;
import H0.o;
import H0.s;
import b0.C0091q;
import j0.AbstractC0150d;
import java.io.EOFException;
import java.io.IOException;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import k.C0213z;
import u0.m;
import u0.q;
import u0.r;
import u0.t;
import u0.u;
import y0.k;

/* loaded from: classes.dex */
public final class h implements z0.d {

    /* renamed from: a, reason: collision with root package name */
    public int f46a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f47b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f48c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f49d;

    /* renamed from: e, reason: collision with root package name */
    public final Object f50e;

    /* renamed from: f, reason: collision with root package name */
    public Object f51f;

    /* renamed from: g, reason: collision with root package name */
    public Iterable f52g;

    public h(u0.a aVar, x0.c cVar, y0.h hVar) {
        List k2;
        AbstractC0150d.e(cVar, "routeDatabase");
        AbstractC0150d.e(hVar, "call");
        this.f47b = aVar;
        this.f48c = cVar;
        this.f49d = hVar;
        C0091q c0091q = C0091q.f1234a;
        this.f50e = c0091q;
        this.f51f = c0091q;
        this.f52g = new ArrayList();
        m mVar = aVar.h;
        AbstractC0150d.e(mVar, "url");
        URI g2 = mVar.g();
        if (g2.getHost() == null) {
            k2 = v0.b.k(Proxy.NO_PROXY);
        } else {
            List<Proxy> select = aVar.f2411g.select(g2);
            if (select != null && !select.isEmpty()) {
                k2 = v0.b.w(select);
            } else {
                k2 = v0.b.k(Proxy.NO_PROXY);
            }
        }
        this.f50e = k2;
        this.f46a = 0;
    }

    @Override // z0.d
    public long a(u uVar) {
        if (!z0.e.a(uVar)) {
            return 0L;
        }
        if ("chunked".equalsIgnoreCase(u.f("Transfer-Encoding", uVar))) {
            return -1L;
        }
        return v0.b.j(uVar);
    }

    @Override // z0.d
    public void b() {
        ((n) this.f50e).flush();
    }

    @Override // z0.d
    public void c(B0.h hVar) {
        Proxy.Type type = ((k) this.f48c).f2903b.f2581b.type();
        AbstractC0150d.d(type, "connection.route().proxy.type()");
        StringBuilder sb = new StringBuilder();
        sb.append((String) hVar.f120d);
        sb.append(' ');
        m mVar = (m) hVar.f118b;
        if (!mVar.f2490i && type == Proxy.Type.HTTP) {
            sb.append(mVar);
        } else {
            String b2 = mVar.b();
            String d2 = mVar.d();
            if (d2 != null) {
                b2 = b2 + '?' + ((Object) d2);
            }
            sb.append(b2);
        }
        sb.append(" HTTP/1.1");
        String sb2 = sb.toString();
        AbstractC0150d.d(sb2, "StringBuilder().apply(builderAction).toString()");
        k((u0.k) hVar.f119c, sb2);
    }

    @Override // z0.d
    public void cancel() {
        Socket socket = ((k) this.f48c).f2904c;
        if (socket != null) {
            v0.b.d(socket);
        }
    }

    @Override // z0.d
    public void d() {
        ((n) this.f50e).flush();
    }

    @Override // z0.d
    public s e(B0.h hVar, long j2) {
        if ("chunked".equalsIgnoreCase(((u0.k) hVar.f119c).a("Transfer-Encoding"))) {
            int i2 = this.f46a;
            if (i2 == 1) {
                this.f46a = 2;
                return new c(this);
            }
            throw new IllegalStateException(AbstractC0150d.h(Integer.valueOf(i2), "state: ").toString());
        }
        if (j2 != -1) {
            int i3 = this.f46a;
            if (i3 == 1) {
                this.f46a = 2;
                return new f(this);
            }
            throw new IllegalStateException(AbstractC0150d.h(Integer.valueOf(i3), "state: ").toString());
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    @Override // z0.d
    public t f(boolean z2) {
        a aVar = (a) this.f51f;
        int i2 = this.f46a;
        if (i2 != 1 && i2 != 3) {
            throw new IllegalStateException(AbstractC0150d.h(Integer.valueOf(i2), "state: ").toString());
        }
        try {
            String o2 = aVar.f28a.o(aVar.f29b);
            aVar.f29b -= o2.length();
            C0213z C2 = C0.d.C(o2);
            int i3 = C2.f2069b;
            t tVar = new t();
            tVar.f2555b = (r) C2.f2070c;
            tVar.f2556c = i3;
            tVar.f2557d = (String) C2.f2071d;
            tVar.f2559f = aVar.a().c();
            if (z2 && i3 == 100) {
                return null;
            }
            if (i3 == 100) {
                this.f46a = 3;
                return tVar;
            }
            this.f46a = 4;
            return tVar;
        } catch (EOFException e2) {
            throw new IOException(AbstractC0150d.h(((k) this.f48c).f2903b.f2580a.h.f(), "unexpected end of stream on "), e2);
        }
    }

    @Override // z0.d
    public k g() {
        return (k) this.f48c;
    }

    @Override // z0.d
    public H0.t h(u uVar) {
        if (!z0.e.a(uVar)) {
            return j(0L);
        }
        if ("chunked".equalsIgnoreCase(u.f("Transfer-Encoding", uVar))) {
            m mVar = (m) uVar.f2566a.f118b;
            int i2 = this.f46a;
            if (i2 == 4) {
                this.f46a = 5;
                return new d(this, mVar);
            }
            throw new IllegalStateException(AbstractC0150d.h(Integer.valueOf(i2), "state: ").toString());
        }
        long j2 = v0.b.j(uVar);
        if (j2 != -1) {
            return j(j2);
        }
        int i3 = this.f46a;
        if (i3 == 4) {
            this.f46a = 5;
            ((k) this.f48c).k();
            return new b(this);
        }
        throw new IllegalStateException(AbstractC0150d.h(Integer.valueOf(i3), "state: ").toString());
    }

    public boolean i() {
        if (this.f46a < ((List) this.f50e).size() || !((ArrayList) this.f52g).isEmpty()) {
            return true;
        }
        return false;
    }

    public e j(long j2) {
        int i2 = this.f46a;
        if (i2 == 4) {
            this.f46a = 5;
            return new e(this, j2);
        }
        throw new IllegalStateException(AbstractC0150d.h(Integer.valueOf(i2), "state: ").toString());
    }

    public void k(u0.k kVar, String str) {
        AbstractC0150d.e(str, "requestLine");
        int i2 = this.f46a;
        if (i2 == 0) {
            n nVar = (n) this.f50e;
            nVar.d(str);
            nVar.d("\r\n");
            int size = kVar.size();
            for (int i3 = 0; i3 < size; i3++) {
                nVar.d(kVar.b(i3));
                nVar.d(": ");
                nVar.d(kVar.d(i3));
                nVar.d("\r\n");
            }
            nVar.d("\r\n");
            this.f46a = 1;
            return;
        }
        throw new IllegalStateException(AbstractC0150d.h(Integer.valueOf(i2), "state: ").toString());
    }

    public h(q qVar, k kVar, o oVar, n nVar) {
        AbstractC0150d.e(kVar, "connection");
        AbstractC0150d.e(oVar, "source");
        AbstractC0150d.e(nVar, "sink");
        this.f47b = qVar;
        this.f48c = kVar;
        this.f49d = oVar;
        this.f50e = nVar;
        this.f51f = new a(oVar);
    }
}

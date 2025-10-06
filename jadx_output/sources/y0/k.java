package y0;

import B0.A;
import B0.AbstractC0006g;
import B0.B;
import B0.EnumC0001b;
import B0.F;
import B0.s;
import H0.n;
import H0.o;
import H0.p;
import H0.v;
import j0.AbstractC0150d;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import u0.q;
import u0.r;
import u0.t;
import u0.u;
import u0.x;

/* loaded from: classes.dex */
public final class k extends B0.j {

    /* renamed from: b, reason: collision with root package name */
    public final x f2903b;

    /* renamed from: c, reason: collision with root package name */
    public Socket f2904c;

    /* renamed from: d, reason: collision with root package name */
    public Socket f2905d;

    /* renamed from: e, reason: collision with root package name */
    public u0.j f2906e;

    /* renamed from: f, reason: collision with root package name */
    public r f2907f;

    /* renamed from: g, reason: collision with root package name */
    public s f2908g;
    public o h;

    /* renamed from: i, reason: collision with root package name */
    public n f2909i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f2910j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f2911k;

    /* renamed from: l, reason: collision with root package name */
    public int f2912l;

    /* renamed from: m, reason: collision with root package name */
    public int f2913m;

    /* renamed from: n, reason: collision with root package name */
    public int f2914n;

    /* renamed from: o, reason: collision with root package name */
    public int f2915o;

    /* renamed from: p, reason: collision with root package name */
    public final ArrayList f2916p;

    /* renamed from: q, reason: collision with root package name */
    public long f2917q;

    public k(l lVar, x xVar) {
        AbstractC0150d.e(lVar, "connectionPool");
        AbstractC0150d.e(xVar, "route");
        this.f2903b = xVar;
        this.f2915o = 1;
        this.f2916p = new ArrayList();
        this.f2917q = Long.MAX_VALUE;
    }

    public static void d(q qVar, x xVar, IOException iOException) {
        AbstractC0150d.e(qVar, "client");
        AbstractC0150d.e(xVar, "failedRoute");
        AbstractC0150d.e(iOException, "failure");
        if (xVar.f2581b.type() != Proxy.Type.DIRECT) {
            u0.a aVar = xVar.f2580a;
            aVar.f2411g.connectFailed(aVar.h.g(), xVar.f2581b.address(), iOException);
        }
        x0.c cVar = qVar.y;
        synchronized (cVar) {
            ((LinkedHashSet) cVar.f2846a).add(xVar);
        }
    }

    @Override // B0.j
    public final synchronized void a(s sVar, F f2) {
        int i2;
        AbstractC0150d.e(sVar, "connection");
        AbstractC0150d.e(f2, "settings");
        if ((f2.f79a & 16) != 0) {
            i2 = ((int[]) f2.f80b)[4];
        } else {
            i2 = Integer.MAX_VALUE;
        }
        this.f2915o = i2;
    }

    @Override // B0.j
    public final void b(A a2) {
        a2.c(EnumC0001b.REFUSED_STREAM, null);
    }

    public final void c(int i2, int i3, int i4, boolean z2, h hVar) {
        boolean z3;
        x xVar;
        AbstractC0150d.e(hVar, "call");
        if (this.f2907f == null) {
            List list = this.f2903b.f2580a.f2413j;
            b bVar = new b(list);
            u0.a aVar = this.f2903b.f2580a;
            if (aVar.f2407c == null) {
                if (list.contains(u0.h.f2453f)) {
                    String str = this.f2903b.f2580a.h.f2486d;
                    C0.o oVar = C0.o.f236a;
                    if (!C0.o.f236a.h(str)) {
                        throw new m(new UnknownServiceException(A.e.d("CLEARTEXT communication to ", str, " not permitted by network security policy")));
                    }
                } else {
                    throw new m(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
                }
            } else if (aVar.f2412i.contains(r.H2_PRIOR_KNOWLEDGE)) {
                throw new m(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"));
            }
            m mVar = null;
            do {
                try {
                    x xVar2 = this.f2903b;
                    if (xVar2.f2580a.f2407c != null && xVar2.f2581b.type() == Proxy.Type.HTTP) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (z3) {
                        f(i2, i3, i4, hVar);
                        if (this.f2904c == null) {
                            xVar = this.f2903b;
                            if (xVar.f2580a.f2407c == null && xVar.f2581b.type() == Proxy.Type.HTTP && this.f2904c == null) {
                                throw new m(new ProtocolException("Too many tunnel connections attempted: 21"));
                            }
                            this.f2917q = System.nanoTime();
                            return;
                        }
                    } else {
                        e(i2, i3, hVar);
                    }
                    g(bVar, hVar);
                    AbstractC0150d.e(this.f2903b.f2582c, "inetSocketAddress");
                    xVar = this.f2903b;
                    if (xVar.f2580a.f2407c == null) {
                    }
                    this.f2917q = System.nanoTime();
                    return;
                } catch (IOException e2) {
                    Socket socket = this.f2905d;
                    if (socket != null) {
                        v0.b.d(socket);
                    }
                    Socket socket2 = this.f2904c;
                    if (socket2 != null) {
                        v0.b.d(socket2);
                    }
                    this.f2905d = null;
                    this.f2904c = null;
                    this.h = null;
                    this.f2909i = null;
                    this.f2906e = null;
                    this.f2907f = null;
                    this.f2908g = null;
                    this.f2915o = 1;
                    AbstractC0150d.e(this.f2903b.f2582c, "inetSocketAddress");
                    if (mVar == null) {
                        mVar = new m(e2);
                    } else {
                        C0.d.e(mVar.f2922a, e2);
                        mVar.f2923b = e2;
                    }
                    if (z2) {
                        bVar.f2859d = true;
                        if (bVar.f2858c) {
                            if (!(e2 instanceof ProtocolException)) {
                                if (!(e2 instanceof InterruptedIOException)) {
                                    if (!(e2 instanceof SSLHandshakeException) || !(e2.getCause() instanceof CertificateException)) {
                                        if (e2 instanceof SSLPeerUnverifiedException) {
                                            throw mVar;
                                        }
                                    } else {
                                        throw mVar;
                                    }
                                } else {
                                    throw mVar;
                                }
                            } else {
                                throw mVar;
                            }
                        } else {
                            throw mVar;
                        }
                    } else {
                        throw mVar;
                    }
                }
            } while (e2 instanceof SSLException);
            throw mVar;
        }
        throw new IllegalStateException("already connected");
    }

    public final void e(int i2, int i3, h hVar) {
        int i4;
        Socket createSocket;
        x xVar = this.f2903b;
        Proxy proxy = xVar.f2581b;
        u0.a aVar = xVar.f2580a;
        Proxy.Type type = proxy.type();
        if (type == null) {
            i4 = -1;
        } else {
            i4 = i.f2899a[type.ordinal()];
        }
        if (i4 != 1 && i4 != 2) {
            createSocket = new Socket(proxy);
        } else {
            createSocket = aVar.f2406b.createSocket();
            AbstractC0150d.b(createSocket);
        }
        this.f2904c = createSocket;
        InetSocketAddress inetSocketAddress = this.f2903b.f2582c;
        AbstractC0150d.e(hVar, "call");
        AbstractC0150d.e(inetSocketAddress, "inetSocketAddress");
        createSocket.setSoTimeout(i3);
        try {
            C0.o oVar = C0.o.f236a;
            C0.o.f236a.e(createSocket, this.f2903b.f2582c, i2);
            try {
                this.h = new o(C0.m.s(createSocket));
                this.f2909i = new n(C0.m.r(createSocket));
            } catch (NullPointerException e2) {
                if (!AbstractC0150d.a(e2.getMessage(), "throw with null exception")) {
                } else {
                    throw new IOException(e2);
                }
            }
        } catch (ConnectException e3) {
            ConnectException connectException = new ConnectException(AbstractC0150d.h(this.f2903b.f2582c, "Failed to connect to "));
            connectException.initCause(e3);
            throw connectException;
        }
    }

    public final void f(int i2, int i3, int i4, h hVar) {
        u0.s sVar = new u0.s();
        x xVar = this.f2903b;
        u0.m mVar = xVar.f2580a.h;
        AbstractC0150d.e(mVar, "url");
        sVar.f2549a = mVar;
        sVar.c("CONNECT", null);
        u0.a aVar = xVar.f2580a;
        sVar.b("Host", v0.b.v(aVar.h, true));
        sVar.b("Proxy-Connection", "Keep-Alive");
        sVar.b("User-Agent", "okhttp/4.10.0");
        B0.h a2 = sVar.a();
        A.f fVar = new A.f(29);
        C0.f.d("Proxy-Authenticate");
        C0.f.i("OkHttp-Preemptive", "Proxy-Authenticate");
        fVar.D("Proxy-Authenticate");
        fVar.z("Proxy-Authenticate", "OkHttp-Preemptive");
        fVar.A();
        aVar.f2410f.getClass();
        e(i2, i3, hVar);
        String str = "CONNECT " + v0.b.v((u0.m) a2.f118b, true) + " HTTP/1.1";
        o oVar = this.h;
        AbstractC0150d.b(oVar);
        n nVar = this.f2909i;
        AbstractC0150d.b(nVar);
        A0.h hVar2 = new A0.h(null, this, oVar, nVar);
        v a3 = oVar.f433a.a();
        long j2 = i3;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        a3.g(j2, timeUnit);
        nVar.f430a.a().g(i4, timeUnit);
        hVar2.k((u0.k) a2.f119c, str);
        hVar2.b();
        t f2 = hVar2.f(false);
        AbstractC0150d.b(f2);
        f2.f2554a = a2;
        u a4 = f2.a();
        long j3 = v0.b.j(a4);
        if (j3 != -1) {
            A0.e j4 = hVar2.j(j3);
            v0.b.t(j4, Integer.MAX_VALUE, timeUnit);
            j4.close();
        }
        int i5 = a4.f2569d;
        if (i5 != 200) {
            if (i5 == 407) {
                aVar.f2410f.getClass();
                throw new IOException("Failed to authenticate with proxy");
            }
            throw new IOException(AbstractC0150d.h(Integer.valueOf(i5), "Unexpected response code for CONNECT: "));
        }
        if (oVar.f434b.f() && nVar.f431b.f()) {
        } else {
            throw new IOException("TLS tunnel buffered too many bytes!");
        }
    }

    public final void g(b bVar, h hVar) {
        u0.a aVar = this.f2903b.f2580a;
        SSLSocketFactory sSLSocketFactory = aVar.f2407c;
        r rVar = r.HTTP_1_1;
        if (sSLSocketFactory == null) {
            List list = aVar.f2412i;
            r rVar2 = r.H2_PRIOR_KNOWLEDGE;
            if (list.contains(rVar2)) {
                this.f2905d = this.f2904c;
                this.f2907f = rVar2;
                l();
                return;
            } else {
                this.f2905d = this.f2904c;
                this.f2907f = rVar;
                return;
            }
        }
        AbstractC0150d.e(hVar, "call");
        u0.a aVar2 = this.f2903b.f2580a;
        SSLSocketFactory sSLSocketFactory2 = aVar2.f2407c;
        SSLSocket sSLSocket = null;
        String str = null;
        try {
            AbstractC0150d.b(sSLSocketFactory2);
            Socket socket = this.f2904c;
            u0.m mVar = aVar2.h;
            Socket createSocket = sSLSocketFactory2.createSocket(socket, mVar.f2486d, mVar.f2487e, true);
            if (createSocket != null) {
                SSLSocket sSLSocket2 = (SSLSocket) createSocket;
                try {
                    u0.h a2 = bVar.a(sSLSocket2);
                    if (a2.f2455b) {
                        C0.o oVar = C0.o.f236a;
                        C0.o.f236a.d(sSLSocket2, aVar2.h.f2486d, aVar2.f2412i);
                    }
                    sSLSocket2.startHandshake();
                    SSLSession session = sSLSocket2.getSession();
                    AbstractC0150d.d(session, "sslSocketSession");
                    u0.j r2 = C0.d.r(session);
                    HostnameVerifier hostnameVerifier = aVar2.f2408d;
                    AbstractC0150d.b(hostnameVerifier);
                    if (!hostnameVerifier.verify(aVar2.h.f2486d, session)) {
                        List a3 = r2.a();
                        if (!a3.isEmpty()) {
                            X509Certificate x509Certificate = (X509Certificate) a3.get(0);
                            StringBuilder sb = new StringBuilder("\n              |Hostname ");
                            sb.append(aVar2.h.f2486d);
                            sb.append(" not verified:\n              |    certificate: ");
                            u0.e eVar = u0.e.f2430c;
                            sb.append(C0.f.x(x509Certificate));
                            sb.append("\n              |    DN: ");
                            sb.append(x509Certificate.getSubjectDN().getName());
                            sb.append("\n              |    subjectAltNames: ");
                            List a4 = G0.c.a(x509Certificate, 7);
                            List a5 = G0.c.a(x509Certificate, 2);
                            ArrayList arrayList = new ArrayList(a5.size() + a4.size());
                            arrayList.addAll(a4);
                            arrayList.addAll(a5);
                            sb.append(arrayList);
                            sb.append("\n              ");
                            throw new SSLPeerUnverifiedException(q0.e.w(sb.toString()));
                        }
                        throw new SSLPeerUnverifiedException("Hostname " + aVar2.h.f2486d + " not verified (no certificates)");
                    }
                    u0.e eVar2 = aVar2.f2409e;
                    AbstractC0150d.b(eVar2);
                    this.f2906e = new u0.j(r2.f2470a, r2.f2471b, r2.f2472c, new j(eVar2, r2, aVar2));
                    AbstractC0150d.e(aVar2.h.f2486d, "hostname");
                    Iterator it = eVar2.f2431a.iterator();
                    if (!it.hasNext()) {
                        if (a2.f2455b) {
                            C0.o oVar2 = C0.o.f236a;
                            str = C0.o.f236a.f(sSLSocket2);
                        }
                        this.f2905d = sSLSocket2;
                        this.h = new o(C0.m.s(sSLSocket2));
                        this.f2909i = new n(C0.m.r(sSLSocket2));
                        if (str != null) {
                            rVar = C0.d.s(str);
                        }
                        this.f2907f = rVar;
                        C0.o oVar3 = C0.o.f236a;
                        C0.o.f236a.a(sSLSocket2);
                        if (this.f2907f == r.HTTP_2) {
                            l();
                            return;
                        }
                        return;
                    }
                    it.next().getClass();
                    throw new ClassCastException();
                } catch (Throwable th) {
                    th = th;
                    sSLSocket = sSLSocket2;
                    if (sSLSocket != null) {
                        C0.o oVar4 = C0.o.f236a;
                        C0.o.f236a.a(sSLSocket);
                    }
                    if (sSLSocket != null) {
                        v0.b.d(sSLSocket);
                    }
                    throw th;
                }
            }
            throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.SSLSocket");
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a6, code lost:
    
        if (G0.c.c(r1, (java.security.cert.X509Certificate) r11.get(0)) != false) goto L53;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean h(u0.a r10, java.util.ArrayList r11) {
        /*
            r9 = this;
            java.lang.String r0 = "hostname"
            byte[] r1 = v0.b.f2808a
            java.util.ArrayList r1 = r9.f2916p
            int r1 = r1.size()
            int r2 = r9.f2915o
            r3 = 0
            if (r1 >= r2) goto Ld8
            boolean r1 = r9.f2910j
            if (r1 == 0) goto L15
            goto Ld8
        L15:
            u0.x r1 = r9.f2903b
            u0.a r2 = r1.f2580a
            boolean r2 = r2.a(r10)
            if (r2 != 0) goto L20
            return r3
        L20:
            u0.m r2 = r10.h
            java.lang.String r4 = r2.f2486d
            u0.a r5 = r1.f2580a
            u0.m r6 = r5.h
            java.lang.String r6 = r6.f2486d
            boolean r4 = j0.AbstractC0150d.a(r4, r6)
            r6 = 1
            if (r4 == 0) goto L32
            return r6
        L32:
            B0.s r4 = r9.f2908g
            if (r4 != 0) goto L37
            return r3
        L37:
            if (r11 == 0) goto Ld8
            boolean r4 = r11.isEmpty()
            if (r4 == 0) goto L41
            goto Ld8
        L41:
            java.util.Iterator r11 = r11.iterator()
        L45:
            boolean r4 = r11.hasNext()
            if (r4 == 0) goto Ld8
            java.lang.Object r4 = r11.next()
            u0.x r4 = (u0.x) r4
            java.net.Proxy r7 = r4.f2581b
            java.net.Proxy$Type r7 = r7.type()
            java.net.Proxy$Type r8 = java.net.Proxy.Type.DIRECT
            if (r7 != r8) goto L45
            java.net.Proxy r7 = r1.f2581b
            java.net.Proxy$Type r7 = r7.type()
            if (r7 != r8) goto L45
            java.net.InetSocketAddress r4 = r4.f2582c
            java.net.InetSocketAddress r7 = r1.f2582c
            boolean r4 = j0.AbstractC0150d.a(r7, r4)
            if (r4 == 0) goto L45
            G0.c r11 = G0.c.f313a
            javax.net.ssl.HostnameVerifier r1 = r10.f2408d
            if (r1 == r11) goto L74
            return r3
        L74:
            byte[] r11 = v0.b.f2808a
            u0.m r11 = r5.h
            int r1 = r11.f2487e
            int r4 = r2.f2487e
            if (r4 == r1) goto L7f
            goto Ld8
        L7f:
            java.lang.String r11 = r11.f2486d
            java.lang.String r1 = r2.f2486d
            boolean r11 = j0.AbstractC0150d.a(r1, r11)
            if (r11 == 0) goto L8a
            goto La8
        L8a:
            boolean r11 = r9.f2911k
            if (r11 != 0) goto Ld8
            u0.j r11 = r9.f2906e
            if (r11 == 0) goto Ld8
            java.util.List r11 = r11.a()
            boolean r2 = r11.isEmpty()
            if (r2 != 0) goto Ld8
            java.lang.Object r11 = r11.get(r3)
            java.security.cert.X509Certificate r11 = (java.security.cert.X509Certificate) r11
            boolean r11 = G0.c.c(r1, r11)
            if (r11 == 0) goto Ld8
        La8:
            u0.e r10 = r10.f2409e     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
            j0.AbstractC0150d.b(r10)     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
            u0.j r11 = r9.f2906e     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
            j0.AbstractC0150d.b(r11)     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
            java.util.List r11 = r11.a()     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
            j0.AbstractC0150d.e(r1, r0)     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
            java.lang.String r0 = "peerCertificates"
            j0.AbstractC0150d.e(r11, r0)     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
            java.util.Set r10 = r10.f2431a     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
            java.util.Iterator r10 = r10.iterator()     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
            boolean r11 = r10.hasNext()     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
            if (r11 != 0) goto Lcb
            return r6
        Lcb:
            java.lang.Object r10 = r10.next()     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
            r10.getClass()     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
            java.lang.ClassCastException r10 = new java.lang.ClassCastException     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
            r10.<init>()     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
            throw r10     // Catch: javax.net.ssl.SSLPeerUnverifiedException -> Ld8
        Ld8:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: y0.k.h(u0.a, java.util.ArrayList):boolean");
    }

    public final boolean i(boolean z2) {
        long j2;
        byte[] bArr = v0.b.f2808a;
        long nanoTime = System.nanoTime();
        Socket socket = this.f2904c;
        AbstractC0150d.b(socket);
        Socket socket2 = this.f2905d;
        AbstractC0150d.b(socket2);
        o oVar = this.h;
        AbstractC0150d.b(oVar);
        if (socket.isClosed() || socket2.isClosed() || socket2.isInputShutdown() || socket2.isOutputShutdown()) {
            return false;
        }
        s sVar = this.f2908g;
        if (sVar != null) {
            synchronized (sVar) {
                if (sVar.f153f) {
                    return false;
                }
                if (sVar.f160n < sVar.f159m) {
                    if (nanoTime >= sVar.f161o) {
                        return false;
                    }
                }
                return true;
            }
        }
        synchronized (this) {
            j2 = nanoTime - this.f2917q;
        }
        if (j2 < 10000000000L || !z2) {
            return true;
        }
        try {
            int soTimeout = socket2.getSoTimeout();
            try {
                socket2.setSoTimeout(1);
                boolean z3 = !oVar.f();
                socket2.setSoTimeout(soTimeout);
                return z3;
            } catch (Throwable th) {
                socket2.setSoTimeout(soTimeout);
                throw th;
            }
        } catch (SocketTimeoutException unused) {
            return true;
        } catch (IOException unused2) {
            return false;
        }
    }

    public final z0.d j(q qVar, z0.f fVar) {
        AbstractC0150d.e(qVar, "client");
        Socket socket = this.f2905d;
        AbstractC0150d.b(socket);
        o oVar = this.h;
        AbstractC0150d.b(oVar);
        n nVar = this.f2909i;
        AbstractC0150d.b(nVar);
        s sVar = this.f2908g;
        if (sVar != null) {
            return new B0.t(qVar, this, fVar, sVar);
        }
        int i2 = fVar.f2977g;
        socket.setSoTimeout(i2);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        oVar.f433a.a().g(i2, timeUnit);
        nVar.f430a.a().g(fVar.h, timeUnit);
        return new A0.h(qVar, this, oVar, nVar);
    }

    public final synchronized void k() {
        this.f2910j = true;
    }

    public final void l() {
        int i2;
        int i3;
        Socket socket = this.f2905d;
        AbstractC0150d.b(socket);
        o oVar = this.h;
        AbstractC0150d.b(oVar);
        n nVar = this.f2909i;
        AbstractC0150d.b(nVar);
        boolean z2 = false;
        socket.setSoTimeout(0);
        x0.d dVar = x0.d.h;
        B0.h hVar = new B0.h(dVar);
        String str = this.f2903b.f2580a.h.f2486d;
        AbstractC0150d.e(str, "peerName");
        hVar.f119c = socket;
        String str2 = v0.b.h + ' ' + str;
        AbstractC0150d.e(str2, "<set-?>");
        hVar.f120d = str2;
        hVar.f121e = oVar;
        hVar.f122f = nVar;
        hVar.f123g = this;
        s sVar = new s(hVar);
        this.f2908g = sVar;
        F f2 = s.f147z;
        int i4 = 4;
        if ((f2.f79a & 16) != 0) {
            i2 = ((int[]) f2.f80b)[4];
        } else {
            i2 = Integer.MAX_VALUE;
        }
        this.f2915o = i2;
        B b2 = sVar.f169w;
        synchronized (b2) {
            try {
                if (!b2.f70d) {
                    Logger logger = B.f66f;
                    if (logger.isLoggable(Level.FINE)) {
                        logger.fine(v0.b.h(AbstractC0150d.h(AbstractC0006g.f113a.b(), ">> CONNECTION "), new Object[0]));
                    }
                    n nVar2 = b2.f67a;
                    H0.h hVar2 = AbstractC0006g.f113a;
                    nVar2.getClass();
                    AbstractC0150d.e(hVar2, "byteString");
                    if (!nVar2.f432c) {
                        nVar2.f431b.r(hVar2);
                        nVar2.f();
                        b2.f67a.flush();
                    } else {
                        throw new IllegalStateException("closed");
                    }
                } else {
                    throw new IOException("closed");
                }
            } finally {
            }
        }
        B b3 = sVar.f169w;
        F f3 = sVar.f162p;
        synchronized (b3) {
            try {
                AbstractC0150d.e(f3, "settings");
                if (!b3.f70d) {
                    b3.i(0, Integer.bitCount(f3.f79a) * 6, 4, 0);
                    int i5 = 0;
                    while (i5 < 10) {
                        int i6 = i5 + 1;
                        boolean z3 = true;
                        if (((1 << i5) & f3.f79a) == 0) {
                            z3 = z2;
                        }
                        if (z3) {
                            if (i5 != i4) {
                                if (i5 != 7) {
                                    i3 = i5;
                                } else {
                                    i3 = i4;
                                }
                            } else {
                                i3 = 3;
                            }
                            n nVar3 = b3.f67a;
                            if (!nVar3.f432c) {
                                H0.e eVar = nVar3.f431b;
                                p q2 = eVar.q(2);
                                int i7 = q2.f438c;
                                byte[] bArr = q2.f436a;
                                bArr[i7] = (byte) ((i3 >>> 8) & 255);
                                bArr[i7 + 1] = (byte) (i3 & 255);
                                q2.f438c = i7 + 2;
                                eVar.f412b += 2;
                                nVar3.f();
                                b3.f67a.i(((int[]) f3.f80b)[i5]);
                            } else {
                                throw new IllegalStateException("closed");
                            }
                        }
                        i5 = i6;
                        z2 = false;
                        i4 = 4;
                    }
                    b3.f67a.flush();
                } else {
                    throw new IOException("closed");
                }
            } finally {
            }
        }
        if (sVar.f162p.b() != 65535) {
            sVar.f169w.n(r2 - 65535, 0);
        }
        dVar.e().c(new B0.p(sVar.f150c, sVar.f170x, 1), 0L);
    }

    public final String toString() {
        u0.g gVar;
        StringBuilder sb = new StringBuilder("Connection{");
        x xVar = this.f2903b;
        sb.append(xVar.f2580a.h.f2486d);
        sb.append(':');
        sb.append(xVar.f2580a.h.f2487e);
        sb.append(", proxy=");
        sb.append(xVar.f2581b);
        sb.append(" hostAddress=");
        sb.append(xVar.f2582c);
        sb.append(" cipherSuite=");
        u0.j jVar = this.f2906e;
        Object obj = "none";
        if (jVar != null && (gVar = jVar.f2471b) != null) {
            obj = gVar;
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.f2907f);
        sb.append('}');
        return sb.toString();
    }
}

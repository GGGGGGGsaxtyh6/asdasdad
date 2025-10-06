package z0;

import B0.C0000a;
import B0.F;
import B0.h;
import H0.j;
import M.v;
import b0.C0091q;
import j0.AbstractC0150d;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import k.C0213z;
import u0.k;
import u0.m;
import u0.n;
import u0.o;
import u0.q;
import u0.s;
import u0.t;
import u0.u;
import u0.w;
import u0.x;
import y0.l;

/* loaded from: classes.dex */
public final class a implements n {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2968a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final Object f2969b;

    public a(u0.b bVar) {
        AbstractC0150d.e(bVar, "cookieJar");
        this.f2969b = bVar;
    }

    public static int d(u uVar, int i2) {
        String f2 = u.f("Retry-After", uVar);
        if (f2 == null) {
            return i2;
        }
        Pattern compile = Pattern.compile("\\d+");
        AbstractC0150d.d(compile, "compile(...)");
        if (compile.matcher(f2).matches()) {
            Integer valueOf = Integer.valueOf(f2);
            AbstractC0150d.d(valueOf, "valueOf(header)");
            return valueOf.intValue();
        }
        return Integer.MAX_VALUE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // u0.n
    public final u a(f fVar) {
        w wVar;
        C0091q c0091q;
        int i2;
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifier;
        u0.e eVar;
        switch (this.f2968a) {
            case 0:
                h hVar = fVar.f2975e;
                s e2 = hVar.e();
                C0213z c0213z = (C0213z) hVar.f121e;
                if (c0213z != null) {
                    o oVar = (o) c0213z.f2070c;
                    if (oVar != null) {
                        e2.b("Content-Type", oVar.f2493a);
                    }
                    long j2 = c0213z.f2069b;
                    if (j2 != -1) {
                        e2.b("Content-Length", String.valueOf(j2));
                        e2.f2551c.D("Transfer-Encoding");
                    } else {
                        e2.b("Transfer-Encoding", "chunked");
                        e2.f2551c.D("Content-Length");
                    }
                }
                k kVar = (k) hVar.f119c;
                String a2 = kVar.a("Host");
                boolean z2 = false;
                m mVar = (m) hVar.f118b;
                if (a2 == null) {
                    e2.b("Host", v0.b.v(mVar, false));
                }
                if (kVar.a("Connection") == null) {
                    e2.b("Connection", "Keep-Alive");
                }
                if (kVar.a("Accept-Encoding") == null && kVar.a("Range") == null) {
                    e2.b("Accept-Encoding", "gzip");
                    z2 = true;
                }
                u0.b bVar = (u0.b) this.f2969b;
                bVar.getClass();
                AbstractC0150d.e(mVar, "url");
                if (kVar.a("User-Agent") == null) {
                    e2.b("User-Agent", "okhttp/4.10.0");
                }
                u b2 = fVar.b(e2.a());
                k kVar2 = b2.f2571f;
                e.b(bVar, mVar, kVar2);
                t i3 = b2.i();
                i3.f2554a = hVar;
                if (z2 && "gzip".equalsIgnoreCase(u.f("Content-Encoding", b2)) && e.a(b2) && (wVar = b2.f2572g) != null) {
                    j jVar = new j(wVar.i());
                    A.f c2 = kVar2.c();
                    c2.D("Content-Encoding");
                    c2.D("Content-Length");
                    i3.f2559f = c2.A().c();
                    i3.f2560g = new g(u.f("Content-Type", b2), -1L, new H0.o(jVar));
                }
                return i3.a();
            default:
                h hVar2 = fVar.f2975e;
                y0.h hVar3 = fVar.f2971a;
                C0091q c0091q2 = C0091q.f1234a;
                u uVar = null;
                int i4 = 0;
                h hVar4 = hVar2;
                while (true) {
                    boolean z3 = true;
                    while (true) {
                        hVar3.getClass();
                        if (hVar3.f2892i == null) {
                            synchronized (hVar3) {
                                try {
                                    if (!hVar3.f2894k) {
                                        if (hVar3.f2893j) {
                                            throw new IllegalStateException("Check failed.");
                                        }
                                    } else {
                                        throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()");
                                    }
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                            if (z3) {
                                l lVar = hVar3.f2887c;
                                m mVar2 = (m) hVar4.f118b;
                                boolean z4 = mVar2.f2490i;
                                q qVar = hVar3.f2885a;
                                if (z4) {
                                    SSLSocketFactory sSLSocketFactory2 = qVar.f2532o;
                                    if (sSLSocketFactory2 != null) {
                                        HostnameVerifier hostnameVerifier2 = qVar.f2536s;
                                        eVar = qVar.f2537t;
                                        sSLSocketFactory = sSLSocketFactory2;
                                        hostnameVerifier = hostnameVerifier2;
                                    } else {
                                        throw new IllegalStateException("CLEARTEXT-only client");
                                    }
                                } else {
                                    sSLSocketFactory = null;
                                    hostnameVerifier = null;
                                    eVar = null;
                                }
                                c0091q = c0091q2;
                                i2 = i4;
                                hVar3.f2891g = new y0.e(lVar, new u0.a(mVar2.f2486d, mVar2.f2487e, qVar.f2528k, qVar.f2531n, sSLSocketFactory, hostnameVerifier, eVar, qVar.f2530m, qVar.f2535r, qVar.f2534q, qVar.f2529l), hVar3);
                            } else {
                                c0091q = c0091q2;
                                i2 = i4;
                            }
                            try {
                                if (!hVar3.f2896m) {
                                    try {
                                        u b3 = fVar.b(hVar4);
                                        if (uVar != null) {
                                            t i5 = b3.i();
                                            t i6 = uVar.i();
                                            i6.f2560g = null;
                                            u a3 = i6.a();
                                            if (a3.f2572g == null) {
                                                i5.f2562j = a3;
                                                b3 = i5.a();
                                            } else {
                                                throw new IllegalArgumentException("priorResponse.body != null");
                                            }
                                        }
                                        uVar = b3;
                                        hVar4 = b(uVar, hVar3.f2892i);
                                        if (hVar4 == null) {
                                            hVar3.f(false);
                                            return uVar;
                                        }
                                        w wVar2 = uVar.f2572g;
                                        if (wVar2 != null) {
                                            v0.b.c(wVar2);
                                        }
                                        i4 = i2 + 1;
                                        if (i4 <= 20) {
                                            hVar3.f(true);
                                            c0091q2 = c0091q;
                                        } else {
                                            throw new ProtocolException(AbstractC0150d.h(Integer.valueOf(i4), "Too many follow-up requests: "));
                                        }
                                    } catch (IOException e3) {
                                        if (c(e3, hVar3, hVar4, !(e3 instanceof C0000a))) {
                                            ArrayList arrayList = new ArrayList(c0091q.size() + 1);
                                            arrayList.addAll(c0091q);
                                            arrayList.add(e3);
                                            hVar3.f(true);
                                            c0091q2 = arrayList;
                                            i4 = i2;
                                            z3 = false;
                                        } else {
                                            v0.b.z(e3, c0091q);
                                            throw e3;
                                        }
                                    } catch (y0.m e4) {
                                        C0091q c0091q3 = c0091q;
                                        if (c(e4.f2923b, hVar3, hVar4, false)) {
                                            IOException iOException = e4.f2922a;
                                            ArrayList arrayList2 = new ArrayList(c0091q3.size() + 1);
                                            arrayList2.addAll(c0091q3);
                                            arrayList2.add(iOException);
                                            hVar3.f(true);
                                            c0091q2 = arrayList2;
                                            z3 = false;
                                            i4 = i2;
                                        } else {
                                            IOException iOException2 = e4.f2922a;
                                            v0.b.z(iOException2, c0091q3);
                                            throw iOException2;
                                        }
                                    }
                                } else {
                                    throw new IOException("Canceled");
                                }
                            } catch (Throwable th2) {
                                hVar3.f(true);
                                throw th2;
                            }
                        } else {
                            throw new IllegalStateException("Check failed.");
                        }
                    }
                }
        }
    }

    public h b(u uVar, v vVar) {
        y0.k kVar;
        x xVar;
        String f2;
        u0.l lVar;
        m a2;
        C0213z c0213z = null;
        if (vVar == null || (kVar = (y0.k) vVar.f573d) == null) {
            xVar = null;
        } else {
            xVar = kVar.f2903b;
        }
        int i2 = uVar.f2569d;
        String str = (String) uVar.f2566a.f120d;
        boolean z2 = false;
        if (i2 != 307 && i2 != 308) {
            if (i2 != 401) {
                if (i2 != 421) {
                    if (i2 != 503) {
                        if (i2 != 407) {
                            if (i2 != 408) {
                                switch (i2) {
                                    case 300:
                                    case 301:
                                    case 302:
                                    case 303:
                                        break;
                                    default:
                                        return null;
                                }
                            } else {
                                if (!((q) this.f2969b).f2524f) {
                                    return null;
                                }
                                u uVar2 = uVar.f2574j;
                                if ((uVar2 != null && uVar2.f2569d == 408) || d(uVar, 0) > 0) {
                                    return null;
                                }
                                return uVar.f2566a;
                            }
                        } else {
                            AbstractC0150d.b(xVar);
                            if (xVar.f2581b.type() == Proxy.Type.HTTP) {
                                ((q) this.f2969b).f2530m.getClass();
                                return null;
                            }
                            throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                        }
                    } else {
                        u uVar3 = uVar.f2574j;
                        if ((uVar3 != null && uVar3.f2569d == 503) || d(uVar, Integer.MAX_VALUE) != 0) {
                            return null;
                        }
                        return uVar.f2566a;
                    }
                } else {
                    if (vVar == null || AbstractC0150d.a(((y0.e) vVar.f571b).f2874b.h.f2486d, ((y0.k) vVar.f573d).f2903b.f2580a.h.f2486d)) {
                        return null;
                    }
                    y0.k kVar2 = (y0.k) vVar.f573d;
                    synchronized (kVar2) {
                        kVar2.f2911k = true;
                    }
                    return uVar.f2566a;
                }
            } else {
                ((q) this.f2969b).f2525g.getClass();
                return null;
            }
        }
        q qVar = (q) this.f2969b;
        if (!qVar.h || (f2 = u.f("Location", uVar)) == null) {
            return null;
        }
        h hVar = uVar.f2566a;
        m mVar = (m) hVar.f118b;
        mVar.getClass();
        try {
            lVar = new u0.l();
            lVar.c(mVar, f2);
        } catch (IllegalArgumentException unused) {
            lVar = null;
        }
        if (lVar == null) {
            a2 = null;
        } else {
            a2 = lVar.a();
        }
        if (a2 == null) {
            return null;
        }
        if (!AbstractC0150d.a(a2.f2483a, ((m) hVar.f118b).f2483a) && !qVar.f2526i) {
            return null;
        }
        s e2 = hVar.e();
        if (C0.m.p(str)) {
            boolean equals = str.equals("PROPFIND");
            int i3 = uVar.f2569d;
            if (equals || i3 == 308 || i3 == 307) {
                z2 = true;
            }
            if (!str.equals("PROPFIND") && i3 != 308 && i3 != 307) {
                e2.c("GET", null);
            } else {
                if (z2) {
                    c0213z = (C0213z) hVar.f121e;
                }
                e2.c(str, c0213z);
            }
            if (!z2) {
                e2.f2551c.D("Transfer-Encoding");
                e2.f2551c.D("Content-Length");
                e2.f2551c.D("Content-Type");
            }
        }
        if (!v0.b.a((m) hVar.f118b, a2)) {
            e2.f2551c.D("Authorization");
        }
        e2.f2549a = a2;
        return e2.a();
    }

    public boolean c(IOException iOException, y0.h hVar, h hVar2, boolean z2) {
        A0.h hVar3;
        boolean i2;
        y0.k kVar;
        if (!((q) this.f2969b).f2524f) {
            return false;
        }
        if ((z2 && (iOException instanceof FileNotFoundException)) || (iOException instanceof ProtocolException) || (!(iOException instanceof InterruptedIOException) ? !((!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException)) : !((iOException instanceof SocketTimeoutException) && !z2))) {
            return false;
        }
        y0.e eVar = hVar.f2891g;
        AbstractC0150d.b(eVar);
        int i3 = eVar.f2878f;
        if (i3 == 0 && eVar.f2879g == 0 && eVar.h == 0) {
            i2 = false;
        } else {
            if (eVar.f2880i == null) {
                x xVar = null;
                if (i3 <= 1 && eVar.f2879g <= 1 && eVar.h <= 0 && (kVar = eVar.f2875c.h) != null) {
                    synchronized (kVar) {
                        if (kVar.f2912l == 0) {
                            if (v0.b.a(kVar.f2903b.f2580a.h, eVar.f2874b.h)) {
                                xVar = kVar.f2903b;
                            }
                        }
                    }
                }
                if (xVar != null) {
                    eVar.f2880i = xVar;
                } else {
                    F f2 = eVar.f2876d;
                    if ((f2 == null || !f2.c()) && (hVar3 = eVar.f2877e) != null) {
                        i2 = hVar3.i();
                    }
                }
            }
            i2 = true;
        }
        if (!i2) {
            return false;
        }
        return true;
    }

    public a(q qVar) {
        AbstractC0150d.e(qVar, "client");
        this.f2969b = qVar;
    }
}

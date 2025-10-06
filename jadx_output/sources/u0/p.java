package u0;

import H.C0014h;
import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes.dex */
public final class p {

    /* renamed from: a, reason: collision with root package name */
    public final M.v f2495a = new M.v();

    /* renamed from: b, reason: collision with root package name */
    public final A.f f2496b = new A.f(28);

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f2497c = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    public final ArrayList f2498d = new ArrayList();

    /* renamed from: e, reason: collision with root package name */
    public final C0014h f2499e = new Object();

    /* renamed from: f, reason: collision with root package name */
    public final boolean f2500f = true;

    /* renamed from: g, reason: collision with root package name */
    public final b f2501g;
    public final boolean h;

    /* renamed from: i, reason: collision with root package name */
    public final boolean f2502i;

    /* renamed from: j, reason: collision with root package name */
    public final b f2503j;

    /* renamed from: k, reason: collision with root package name */
    public final b f2504k;

    /* renamed from: l, reason: collision with root package name */
    public final b f2505l;

    /* renamed from: m, reason: collision with root package name */
    public final SocketFactory f2506m;

    /* renamed from: n, reason: collision with root package name */
    public SSLSocketFactory f2507n;

    /* renamed from: o, reason: collision with root package name */
    public X509TrustManager f2508o;

    /* renamed from: p, reason: collision with root package name */
    public final List f2509p;

    /* renamed from: q, reason: collision with root package name */
    public final List f2510q;

    /* renamed from: r, reason: collision with root package name */
    public HostnameVerifier f2511r;

    /* renamed from: s, reason: collision with root package name */
    public final e f2512s;

    /* renamed from: t, reason: collision with root package name */
    public C0.f f2513t;

    /* renamed from: u, reason: collision with root package name */
    public final int f2514u;

    /* renamed from: v, reason: collision with root package name */
    public final int f2515v;

    /* renamed from: w, reason: collision with root package name */
    public final int f2516w;

    /* JADX WARN: Type inference failed for: r0v4, types: [H.h, java.lang.Object] */
    public p() {
        b bVar = b.f2414a;
        this.f2501g = bVar;
        this.h = true;
        this.f2502i = true;
        this.f2503j = b.f2415b;
        this.f2504k = b.f2416c;
        this.f2505l = bVar;
        SocketFactory socketFactory = SocketFactory.getDefault();
        AbstractC0150d.d(socketFactory, "getDefault()");
        this.f2506m = socketFactory;
        this.f2509p = q.f2517A;
        this.f2510q = q.f2518z;
        this.f2511r = G0.c.f313a;
        this.f2512s = e.f2430c;
        this.f2514u = 10000;
        this.f2515v = 10000;
        this.f2516w = 10000;
    }

    public final void a(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
        AbstractC0150d.e(sSLSocketFactory, "sslSocketFactory");
        AbstractC0150d.e(x509TrustManager, "trustManager");
        if (sSLSocketFactory.equals(this.f2507n)) {
            x509TrustManager.equals(this.f2508o);
        }
        this.f2507n = sSLSocketFactory;
        C0.o oVar = C0.o.f236a;
        this.f2513t = C0.o.f236a.b(x509TrustManager);
        this.f2508o = x509TrustManager;
    }
}

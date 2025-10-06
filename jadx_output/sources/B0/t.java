package B0;

import j0.AbstractC0150d;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import k.C0213z;

/* loaded from: classes.dex */
public final class t implements z0.d {

    /* renamed from: g, reason: collision with root package name */
    public static final List f171g = v0.b.k("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", ":method", ":path", ":scheme", ":authority");
    public static final List h = v0.b.k("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade");

    /* renamed from: a, reason: collision with root package name */
    public final y0.k f172a;

    /* renamed from: b, reason: collision with root package name */
    public final z0.f f173b;

    /* renamed from: c, reason: collision with root package name */
    public final s f174c;

    /* renamed from: d, reason: collision with root package name */
    public volatile A f175d;

    /* renamed from: e, reason: collision with root package name */
    public final u0.r f176e;

    /* renamed from: f, reason: collision with root package name */
    public volatile boolean f177f;

    public t(u0.q qVar, y0.k kVar, z0.f fVar, s sVar) {
        AbstractC0150d.e(qVar, "client");
        AbstractC0150d.e(kVar, "connection");
        AbstractC0150d.e(sVar, "http2Connection");
        this.f172a = kVar;
        this.f173b = fVar;
        this.f174c = sVar;
        u0.r rVar = u0.r.H2_PRIOR_KNOWLEDGE;
        this.f176e = qVar.f2535r.contains(rVar) ? rVar : u0.r.HTTP_2;
    }

    @Override // z0.d
    public final long a(u0.u uVar) {
        if (!z0.e.a(uVar)) {
            return 0L;
        }
        return v0.b.j(uVar);
    }

    @Override // z0.d
    public final void b() {
        A a2 = this.f175d;
        AbstractC0150d.b(a2);
        a2.g().close();
    }

    @Override // z0.d
    public final void c(h hVar) {
        boolean z2;
        int i2;
        A a2;
        if (this.f175d != null) {
            return;
        }
        boolean z3 = true;
        if (((C0213z) hVar.f121e) != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        u0.k kVar = (u0.k) hVar.f119c;
        ArrayList arrayList = new ArrayList(kVar.size() + 4);
        arrayList.add(new C0002c(C0002c.f91f, (String) hVar.f120d));
        H0.h hVar2 = C0002c.f92g;
        u0.m mVar = (u0.m) hVar.f118b;
        AbstractC0150d.e(mVar, "url");
        String b2 = mVar.b();
        String d2 = mVar.d();
        if (d2 != null) {
            b2 = b2 + '?' + ((Object) d2);
        }
        arrayList.add(new C0002c(hVar2, b2));
        String a3 = ((u0.k) hVar.f119c).a("Host");
        if (a3 != null) {
            arrayList.add(new C0002c(C0002c.f93i, a3));
        }
        arrayList.add(new C0002c(C0002c.h, mVar.f2483a));
        int size = kVar.size();
        int i3 = 0;
        while (i3 < size) {
            int i4 = i3 + 1;
            String b3 = kVar.b(i3);
            Locale locale = Locale.US;
            AbstractC0150d.d(locale, "US");
            String lowerCase = b3.toLowerCase(locale);
            AbstractC0150d.d(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (!f171g.contains(lowerCase) || (lowerCase.equals("te") && AbstractC0150d.a(kVar.d(i3), "trailers"))) {
                arrayList.add(new C0002c(lowerCase, kVar.d(i3)));
            }
            i3 = i4;
        }
        s sVar = this.f174c;
        sVar.getClass();
        boolean z4 = !z2;
        synchronized (sVar.f169w) {
            synchronized (sVar) {
                try {
                    if (sVar.f152e > 1073741823) {
                        sVar.k(EnumC0001b.REFUSED_STREAM);
                    }
                    if (!sVar.f153f) {
                        i2 = sVar.f152e;
                        sVar.f152e = i2 + 2;
                        a2 = new A(i2, sVar, z4, false, null);
                        if (z2 && sVar.f166t < sVar.f167u && a2.f57e < a2.f58f) {
                            z3 = false;
                        }
                        if (a2.i()) {
                            sVar.f149b.put(Integer.valueOf(i2), a2);
                        }
                    } else {
                        throw new IOException();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            sVar.f169w.k(z4, i2, arrayList);
        }
        if (z3) {
            sVar.f169w.flush();
        }
        this.f175d = a2;
        if (!this.f177f) {
            A a4 = this.f175d;
            AbstractC0150d.b(a4);
            z zVar = a4.f62k;
            long j2 = this.f173b.f2977g;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            zVar.g(j2, timeUnit);
            A a5 = this.f175d;
            AbstractC0150d.b(a5);
            a5.f63l.g(this.f173b.h, timeUnit);
            return;
        }
        A a6 = this.f175d;
        AbstractC0150d.b(a6);
        a6.e(EnumC0001b.CANCEL);
        throw new IOException("Canceled");
    }

    @Override // z0.d
    public final void cancel() {
        this.f177f = true;
        A a2 = this.f175d;
        if (a2 != null) {
            a2.e(EnumC0001b.CANCEL);
        }
    }

    @Override // z0.d
    public final void d() {
        this.f174c.flush();
    }

    @Override // z0.d
    public final H0.s e(h hVar, long j2) {
        A a2 = this.f175d;
        AbstractC0150d.b(a2);
        return a2.g();
    }

    @Override // z0.d
    public final u0.t f(boolean z2) {
        u0.k kVar;
        A a2 = this.f175d;
        AbstractC0150d.b(a2);
        synchronized (a2) {
            a2.f62k.h();
            while (a2.f59g.isEmpty() && a2.f64m == null) {
                try {
                    a2.l();
                } catch (Throwable th) {
                    a2.f62k.k();
                    throw th;
                }
            }
            a2.f62k.k();
            if (!a2.f59g.isEmpty()) {
                Object removeFirst = a2.f59g.removeFirst();
                AbstractC0150d.d(removeFirst, "headersQueue.removeFirst()");
                kVar = (u0.k) removeFirst;
            } else {
                IOException iOException = a2.f65n;
                if (iOException == null) {
                    EnumC0001b enumC0001b = a2.f64m;
                    AbstractC0150d.b(enumC0001b);
                    throw new G(enumC0001b);
                }
                throw iOException;
            }
        }
        u0.r rVar = this.f176e;
        AbstractC0150d.e(rVar, "protocol");
        ArrayList arrayList = new ArrayList(20);
        int size = kVar.size();
        C0213z c0213z = null;
        int i2 = 0;
        while (i2 < size) {
            int i3 = i2 + 1;
            String b2 = kVar.b(i2);
            String d2 = kVar.d(i2);
            if (AbstractC0150d.a(b2, ":status")) {
                c0213z = C0.d.C(AbstractC0150d.h(d2, "HTTP/1.1 "));
            } else if (!h.contains(b2)) {
                AbstractC0150d.e(b2, "name");
                AbstractC0150d.e(d2, "value");
                arrayList.add(b2);
                arrayList.add(q0.d.P(d2).toString());
            }
            i2 = i3;
        }
        if (c0213z != null) {
            u0.t tVar = new u0.t();
            tVar.f2555b = rVar;
            tVar.f2556c = c0213z.f2069b;
            tVar.f2557d = (String) c0213z.f2071d;
            Object[] array = arrayList.toArray(new String[0]);
            if (array != null) {
                A.f fVar = new A.f(29);
                ArrayList arrayList2 = (ArrayList) fVar.f8b;
                AbstractC0150d.e(arrayList2, "<this>");
                List asList = Arrays.asList((String[]) array);
                AbstractC0150d.d(asList, "asList(...)");
                arrayList2.addAll(asList);
                tVar.f2559f = fVar;
                if (z2 && tVar.f2556c == 100) {
                    return null;
                }
                return tVar;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    @Override // z0.d
    public final y0.k g() {
        return this.f172a;
    }

    @Override // z0.d
    public final H0.t h(u0.u uVar) {
        A a2 = this.f175d;
        AbstractC0150d.b(a2);
        return a2.f60i;
    }
}

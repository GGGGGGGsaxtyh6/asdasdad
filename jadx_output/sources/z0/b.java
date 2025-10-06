package z0;

import C0.m;
import H0.o;
import M.v;
import j0.AbstractC0150d;
import java.io.IOException;
import java.net.ProtocolException;
import k.C0213z;
import u0.n;
import u0.t;
import u0.u;
import u0.w;
import y0.h;
import y0.k;

/* loaded from: classes.dex */
public final class b implements n {
    @Override // u0.n
    public final u a(f fVar) {
        Long l2;
        t tVar;
        long f2;
        Long valueOf;
        C0213z c0213z;
        boolean z2;
        v vVar = fVar.f2974d;
        AbstractC0150d.b(vVar);
        h hVar = (h) vVar.f570a;
        d dVar = (d) vVar.f572c;
        B0.h hVar2 = fVar.f2975e;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            AbstractC0150d.e(hVar, "call");
            dVar.c(hVar2);
            boolean p2 = m.p((String) hVar2.f120d);
            boolean z3 = true;
            k kVar = (k) vVar.f573d;
            if (p2 && (c0213z = (C0213z) hVar2.f121e) != null) {
                if ("100-continue".equalsIgnoreCase(((u0.k) hVar2.f119c).a("Expect"))) {
                    try {
                        dVar.d();
                        tVar = vVar.i(true);
                        AbstractC0150d.e(hVar, "call");
                        z2 = false;
                    } catch (IOException e2) {
                        AbstractC0150d.e(hVar, "call");
                        vVar.j(e2);
                        throw e2;
                    }
                } else {
                    z2 = true;
                    tVar = null;
                }
                if (tVar == null) {
                    AbstractC0150d.b(c0213z);
                    long j2 = c0213z.f2069b;
                    AbstractC0150d.e(hVar, "call");
                    H0.n nVar = new H0.n(new y0.c(vVar, dVar.e(hVar2, j2), j2));
                    byte[] bArr = (byte[]) c0213z.f2071d;
                    if (!nVar.f432c) {
                        nVar.f431b.s(bArr, c0213z.f2069b);
                        nVar.f();
                        nVar.close();
                    } else {
                        throw new IllegalStateException("closed");
                    }
                } else {
                    hVar.h(vVar, true, false, null);
                    if (kVar.f2908g == null) {
                        dVar.g().k();
                    }
                }
                z3 = z2;
                l2 = null;
            } else {
                l2 = null;
                hVar.h(vVar, true, false, null);
                tVar = null;
            }
            try {
                dVar.b();
                if (tVar == null) {
                    tVar = vVar.i(false);
                    AbstractC0150d.b(tVar);
                    if (z3) {
                        AbstractC0150d.e(hVar, "call");
                        z3 = false;
                    }
                }
                tVar.f2554a = hVar2;
                tVar.f2558e = kVar.f2906e;
                tVar.f2563k = currentTimeMillis;
                tVar.f2564l = System.currentTimeMillis();
                u a2 = tVar.a();
                int i2 = a2.f2569d;
                if (i2 == 100) {
                    t i3 = vVar.i(false);
                    AbstractC0150d.b(i3);
                    if (z3) {
                        AbstractC0150d.e(hVar, "call");
                    }
                    i3.f2554a = hVar2;
                    i3.f2558e = kVar.f2906e;
                    i3.f2563k = currentTimeMillis;
                    i3.f2564l = System.currentTimeMillis();
                    a2 = i3.a();
                    i2 = a2.f2569d;
                }
                t i4 = a2.i();
                try {
                    String f3 = u.f("Content-Type", a2);
                    long a3 = dVar.a(a2);
                    i4.f2560g = new g(f3, a3, new o(new y0.d(vVar, dVar.h(a2), a3)));
                    u a4 = i4.a();
                    if ("close".equalsIgnoreCase(((u0.k) a4.f2566a.f119c).a("Connection")) || "close".equalsIgnoreCase(u.f("Connection", a4))) {
                        dVar.g().k();
                    }
                    if (i2 == 204 || i2 == 205) {
                        w wVar = a4.f2572g;
                        if (wVar == null) {
                            f2 = -1;
                        } else {
                            f2 = wVar.f();
                        }
                        if (f2 > 0) {
                            StringBuilder sb = new StringBuilder("HTTP ");
                            sb.append(i2);
                            sb.append(" had non-zero Content-Length: ");
                            if (wVar == null) {
                                valueOf = l2;
                            } else {
                                valueOf = Long.valueOf(wVar.f());
                            }
                            sb.append(valueOf);
                            throw new ProtocolException(sb.toString());
                        }
                    }
                    return a4;
                } catch (IOException e3) {
                    vVar.j(e3);
                    throw e3;
                }
            } catch (IOException e4) {
                vVar.j(e4);
                throw e4;
            }
        } catch (IOException e5) {
            AbstractC0150d.e(hVar, "call");
            vVar.j(e5);
            throw e5;
        }
    }
}

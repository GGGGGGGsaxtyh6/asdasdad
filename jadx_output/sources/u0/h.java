package u0;

import b0.AbstractC0082h;
import c0.C0096a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import k.U0;

/* loaded from: classes.dex */
public final class h {

    /* renamed from: e, reason: collision with root package name */
    public static final h f2452e;

    /* renamed from: f, reason: collision with root package name */
    public static final h f2453f;

    /* renamed from: a, reason: collision with root package name */
    public final boolean f2454a;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f2455b;

    /* renamed from: c, reason: collision with root package name */
    public final String[] f2456c;

    /* renamed from: d, reason: collision with root package name */
    public final String[] f2457d;

    static {
        g gVar = g.f2448r;
        g gVar2 = g.f2449s;
        g gVar3 = g.f2450t;
        g gVar4 = g.f2442l;
        g gVar5 = g.f2444n;
        g gVar6 = g.f2443m;
        g gVar7 = g.f2445o;
        g gVar8 = g.f2447q;
        g gVar9 = g.f2446p;
        g[] gVarArr = {gVar, gVar2, gVar3, gVar4, gVar5, gVar6, gVar7, gVar8, gVar9, g.f2440j, g.f2441k, g.h, g.f2439i, g.f2437f, g.f2438g, g.f2436e};
        U0 u02 = new U0();
        u02.c((g[]) Arrays.copyOf(new g[]{gVar, gVar2, gVar3, gVar4, gVar5, gVar6, gVar7, gVar8, gVar9}, 9));
        y yVar = y.TLS_1_3;
        y yVar2 = y.TLS_1_2;
        u02.e(yVar, yVar2);
        if (u02.f1849a) {
            u02.f1850b = true;
            u02.a();
            U0 u03 = new U0();
            u03.c((g[]) Arrays.copyOf(gVarArr, 16));
            u03.e(yVar, yVar2);
            if (u03.f1849a) {
                u03.f1850b = true;
                f2452e = u03.a();
                U0 u04 = new U0();
                u04.c((g[]) Arrays.copyOf(gVarArr, 16));
                u04.e(yVar, yVar2, y.TLS_1_1, y.TLS_1_0);
                if (u04.f1849a) {
                    u04.f1850b = true;
                    u04.a();
                    f2453f = new h(false, false, null, null);
                    return;
                }
                throw new IllegalArgumentException("no TLS extensions for cleartext connections");
            }
            throw new IllegalArgumentException("no TLS extensions for cleartext connections");
        }
        throw new IllegalArgumentException("no TLS extensions for cleartext connections");
    }

    public h(boolean z2, boolean z3, String[] strArr, String[] strArr2) {
        this.f2454a = z2;
        this.f2455b = z3;
        this.f2456c = strArr;
        this.f2457d = strArr2;
    }

    public final List a() {
        String[] strArr = this.f2456c;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(g.f2433b.c(str));
        }
        return AbstractC0082h.L(arrayList);
    }

    public final boolean b(SSLSocket sSLSocket) {
        if (!this.f2454a) {
            return false;
        }
        String[] strArr = this.f2457d;
        if (strArr != null && !v0.b.i(strArr, sSLSocket.getEnabledProtocols(), C0096a.f1257b)) {
            return false;
        }
        String[] strArr2 = this.f2456c;
        if (strArr2 != null && !v0.b.i(strArr2, sSLSocket.getEnabledCipherSuites(), g.f2434c)) {
            return false;
        }
        return true;
    }

    public final List c() {
        String[] strArr = this.f2457d;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(C0.f.o(str));
        }
        return AbstractC0082h.L(arrayList);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof h)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        h hVar = (h) obj;
        boolean z2 = hVar.f2454a;
        boolean z3 = this.f2454a;
        if (z3 != z2) {
            return false;
        }
        if (z3 && (!Arrays.equals(this.f2456c, hVar.f2456c) || !Arrays.equals(this.f2457d, hVar.f2457d) || this.f2455b != hVar.f2455b)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int hashCode;
        if (this.f2454a) {
            int i2 = 0;
            String[] strArr = this.f2456c;
            if (strArr == null) {
                hashCode = 0;
            } else {
                hashCode = Arrays.hashCode(strArr);
            }
            int i3 = (527 + hashCode) * 31;
            String[] strArr2 = this.f2457d;
            if (strArr2 != null) {
                i2 = Arrays.hashCode(strArr2);
            }
            return ((i3 + i2) * 31) + (!this.f2455b ? 1 : 0);
        }
        return 17;
    }

    public final String toString() {
        if (!this.f2454a) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + ((Object) Objects.toString(a(), "[all enabled]")) + ", tlsVersions=" + ((Object) Objects.toString(c(), "[all enabled]")) + ", supportsTlsExtensions=" + this.f2455b + ')';
    }
}

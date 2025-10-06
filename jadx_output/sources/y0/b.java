package y0;

import c0.C0096a;
import j0.AbstractC0150d;
import java.net.UnknownServiceException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* loaded from: classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    public final List f2856a;

    /* renamed from: b, reason: collision with root package name */
    public int f2857b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f2858c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f2859d;

    public b(List list) {
        AbstractC0150d.e(list, "connectionSpecs");
        this.f2856a = list;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, k.U0] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.String[], java.io.Serializable] */
    public final u0.h a(SSLSocket sSLSocket) {
        u0.h hVar;
        int i2;
        boolean z2;
        String[] enabledCipherSuites;
        String[] enabledProtocols;
        int i3 = this.f2857b;
        List list = this.f2856a;
        int size = list.size();
        while (true) {
            if (i3 < size) {
                int i4 = i3 + 1;
                hVar = (u0.h) list.get(i3);
                if (hVar.b(sSLSocket)) {
                    this.f2857b = i4;
                    break;
                }
                i3 = i4;
            } else {
                hVar = null;
                break;
            }
        }
        if (hVar != null) {
            int i5 = this.f2857b;
            int size2 = list.size();
            while (true) {
                i2 = 0;
                if (i5 < size2) {
                    int i6 = i5 + 1;
                    if (((u0.h) list.get(i5)).b(sSLSocket)) {
                        z2 = true;
                        break;
                    }
                    i5 = i6;
                } else {
                    z2 = false;
                    break;
                }
            }
            this.f2858c = z2;
            boolean z3 = this.f2859d;
            String[] strArr = hVar.f2456c;
            if (strArr != null) {
                String[] enabledCipherSuites2 = sSLSocket.getEnabledCipherSuites();
                AbstractC0150d.d(enabledCipherSuites2, "sslSocket.enabledCipherSuites");
                enabledCipherSuites = v0.b.o(enabledCipherSuites2, strArr, u0.g.f2434c);
            } else {
                enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
            }
            ?? r6 = hVar.f2457d;
            if (r6 != 0) {
                String[] enabledProtocols2 = sSLSocket.getEnabledProtocols();
                AbstractC0150d.d(enabledProtocols2, "sslSocket.enabledProtocols");
                enabledProtocols = v0.b.o(enabledProtocols2, r6, C0096a.f1257b);
            } else {
                enabledProtocols = sSLSocket.getEnabledProtocols();
            }
            String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
            AbstractC0150d.d(supportedCipherSuites, "supportedCipherSuites");
            u0.f fVar = u0.g.f2434c;
            byte[] bArr = v0.b.f2808a;
            int length = supportedCipherSuites.length;
            while (true) {
                if (i2 < length) {
                    if (fVar.compare(supportedCipherSuites[i2], "TLS_FALLBACK_SCSV") == 0) {
                        break;
                    }
                    i2++;
                } else {
                    i2 = -1;
                    break;
                }
            }
            if (z3 && i2 != -1) {
                AbstractC0150d.d(enabledCipherSuites, "cipherSuitesIntersection");
                String str = supportedCipherSuites[i2];
                AbstractC0150d.d(str, "supportedCipherSuites[indexOfFallbackScsv]");
                Object[] copyOf = Arrays.copyOf(enabledCipherSuites, enabledCipherSuites.length + 1);
                AbstractC0150d.d(copyOf, "copyOf(this, newSize)");
                enabledCipherSuites = (String[]) copyOf;
                enabledCipherSuites[enabledCipherSuites.length - 1] = str;
            }
            ?? obj = new Object();
            obj.f1849a = hVar.f2454a;
            obj.f1851c = strArr;
            obj.f1852d = r6;
            obj.f1850b = hVar.f2455b;
            AbstractC0150d.d(enabledCipherSuites, "cipherSuitesIntersection");
            obj.b((String[]) Arrays.copyOf(enabledCipherSuites, enabledCipherSuites.length));
            AbstractC0150d.d(enabledProtocols, "tlsVersionsIntersection");
            obj.d((String[]) Arrays.copyOf(enabledProtocols, enabledProtocols.length));
            u0.h a2 = obj.a();
            if (a2.c() != null) {
                sSLSocket.setEnabledProtocols(a2.f2457d);
            }
            if (a2.a() != null) {
                sSLSocket.setEnabledCipherSuites(a2.f2456c);
            }
            return hVar;
        }
        StringBuilder sb = new StringBuilder("Unable to find acceptable protocols. isFallback=");
        sb.append(this.f2859d);
        sb.append(", modes=");
        sb.append(list);
        sb.append(", supported protocols=");
        String[] enabledProtocols3 = sSLSocket.getEnabledProtocols();
        AbstractC0150d.b(enabledProtocols3);
        String arrays = Arrays.toString(enabledProtocols3);
        AbstractC0150d.d(arrays, "toString(this)");
        sb.append(arrays);
        throw new UnknownServiceException(sb.toString());
    }
}

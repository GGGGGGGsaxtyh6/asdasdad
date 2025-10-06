package G0;

import C0.f;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public final class a extends f {

    /* renamed from: k, reason: collision with root package name */
    public final d f311k;

    public a(d dVar) {
        AbstractC0150d.e(dVar, "trustRootIndex");
        this.f311k = dVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof a) && AbstractC0150d.a(((a) obj).f311k, this.f311k)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f311k.hashCode();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0073  */
    @Override // C0.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List j(java.lang.String r8, java.util.List r9) {
        /*
            r7 = this;
            java.lang.String r0 = "chain"
            j0.AbstractC0150d.e(r9, r0)
            java.lang.String r0 = "hostname"
            j0.AbstractC0150d.e(r8, r0)
            java.util.ArrayDeque r8 = new java.util.ArrayDeque
            r8.<init>(r9)
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.lang.Object r0 = r8.removeFirst()
            java.lang.String r1 = "queue.removeFirst()"
            j0.AbstractC0150d.d(r0, r1)
            r9.add(r0)
            r0 = 0
            r1 = r0
        L22:
            r2 = 9
            if (r0 >= r2) goto Laf
            int r0 = r0 + 1
            int r2 = r9.size()
            r3 = 1
            int r2 = r2 - r3
            java.lang.Object r2 = r9.get(r2)
            java.security.cert.X509Certificate r2 = (java.security.cert.X509Certificate) r2
            G0.d r4 = r7.f311k
            java.security.cert.X509Certificate r4 = r4.a(r2)
            if (r4 == 0) goto L64
            int r1 = r9.size()
            if (r1 > r3) goto L48
            boolean r1 = r2.equals(r4)
            if (r1 != 0) goto L4b
        L48:
            r9.add(r4)
        L4b:
            java.security.Principal r1 = r4.getIssuerDN()
            java.security.Principal r2 = r4.getSubjectDN()
            boolean r1 = j0.AbstractC0150d.a(r1, r2)
            if (r1 != 0) goto L5a
            goto L62
        L5a:
            java.security.PublicKey r1 = r4.getPublicKey()     // Catch: java.security.GeneralSecurityException -> L62
            r4.verify(r1)     // Catch: java.security.GeneralSecurityException -> L62
            return r9
        L62:
            r1 = r3
            goto L22
        L64:
            java.util.Iterator r3 = r8.iterator()
            java.lang.String r4 = "queue.iterator()"
            j0.AbstractC0150d.d(r3, r4)
        L6d:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto La0
            java.lang.Object r4 = r3.next()
            if (r4 == 0) goto L98
            java.security.cert.X509Certificate r4 = (java.security.cert.X509Certificate) r4
            java.security.Principal r5 = r2.getIssuerDN()
            java.security.Principal r6 = r4.getSubjectDN()
            boolean r5 = j0.AbstractC0150d.a(r5, r6)
            if (r5 != 0) goto L8a
            goto L6d
        L8a:
            java.security.PublicKey r5 = r4.getPublicKey()     // Catch: java.security.GeneralSecurityException -> L6d
            r2.verify(r5)     // Catch: java.security.GeneralSecurityException -> L6d
            r3.remove()
            r9.add(r4)
            goto L22
        L98:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r9 = "null cannot be cast to non-null type java.security.cert.X509Certificate"
            r8.<init>(r9)
            throw r8
        La0:
            if (r1 == 0) goto La3
            return r9
        La3:
            javax.net.ssl.SSLPeerUnverifiedException r8 = new javax.net.ssl.SSLPeerUnverifiedException
            java.lang.String r9 = "Failed to find a trusted cert that signed "
            java.lang.String r9 = j0.AbstractC0150d.h(r2, r9)
            r8.<init>(r9)
            throw r8
        Laf:
            javax.net.ssl.SSLPeerUnverifiedException r8 = new javax.net.ssl.SSLPeerUnverifiedException
            java.lang.String r0 = "Certificate chain too long: "
            java.lang.String r9 = j0.AbstractC0150d.h(r9, r0)
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: G0.a.j(java.lang.String, java.util.List):java.util.List");
    }
}

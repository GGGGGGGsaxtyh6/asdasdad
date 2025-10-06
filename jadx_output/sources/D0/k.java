package D0;

import j0.AbstractC0150d;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* loaded from: classes.dex */
public final class k implements l {

    /* renamed from: a, reason: collision with root package name */
    public final j f263a;

    /* renamed from: b, reason: collision with root package name */
    public l f264b;

    public k(j jVar) {
        this.f263a = jVar;
    }

    @Override // D0.l
    public final boolean a(SSLSocket sSLSocket) {
        return this.f263a.a(sSLSocket);
    }

    @Override // D0.l
    public final String b(SSLSocket sSLSocket) {
        l e2 = e(sSLSocket);
        if (e2 == null) {
            return null;
        }
        return e2.b(sSLSocket);
    }

    @Override // D0.l
    public final boolean c() {
        return true;
    }

    @Override // D0.l
    public final void d(SSLSocket sSLSocket, String str, List list) {
        AbstractC0150d.e(list, "protocols");
        l e2 = e(sSLSocket);
        if (e2 != null) {
            e2.d(sSLSocket, str, list);
        }
    }

    public final synchronized l e(SSLSocket sSLSocket) {
        try {
            if (this.f264b == null && this.f263a.a(sSLSocket)) {
                this.f264b = this.f263a.b(sSLSocket);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f264b;
    }
}

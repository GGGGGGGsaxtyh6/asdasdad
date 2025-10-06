package D0;

import j0.AbstractC0150d;
import javax.net.ssl.SSLSocket;
import org.conscrypt.Conscrypt;

/* loaded from: classes.dex */
public final class d implements j {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f248a;

    public /* synthetic */ d(int i2) {
        this.f248a = i2;
    }

    @Override // D0.j
    public final boolean a(SSLSocket sSLSocket) {
        switch (this.f248a) {
            case 0:
                return q0.k.B(sSLSocket.getClass().getName(), false, AbstractC0150d.h(".", "com.google.android.gms.org.conscrypt"));
            default:
                if (C0.h.f219d && Conscrypt.isConscrypt(sSLSocket)) {
                    return true;
                }
                return false;
        }
    }

    @Override // D0.j
    public final l b(SSLSocket sSLSocket) {
        switch (this.f248a) {
            case 0:
                Class<?> cls = sSLSocket.getClass();
                Class<?> cls2 = cls;
                while (!cls2.getSimpleName().equals("OpenSSLSocketImpl")) {
                    cls2 = cls2.getSuperclass();
                    if (cls2 == null) {
                        throw new AssertionError(AbstractC0150d.h(cls, "No OpenSSLSocketImpl superclass of socket of type "));
                    }
                }
                return new e(cls2);
            default:
                return new i(0);
        }
    }
}

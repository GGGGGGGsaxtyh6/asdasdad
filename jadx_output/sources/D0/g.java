package D0;

import C0.o;
import j0.AbstractC0150d;
import java.util.List;
import javax.net.ssl.SSLSocket;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;
import org.conscrypt.BuildConfig;

/* loaded from: classes.dex */
public final class g implements l {

    /* renamed from: a, reason: collision with root package name */
    public static final f f255a = new Object();

    @Override // D0.l
    public final boolean a(SSLSocket sSLSocket) {
        return false;
    }

    @Override // D0.l
    public final String b(SSLSocket sSLSocket) {
        boolean a2;
        String applicationProtocol = ((BCSSLSocket) sSLSocket).getApplicationProtocol();
        if (applicationProtocol == null) {
            a2 = true;
        } else {
            a2 = AbstractC0150d.a(applicationProtocol, BuildConfig.FLAVOR);
        }
        if (a2) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // D0.l
    public final boolean c() {
        boolean z2 = C0.e.f207d;
        return C0.e.f207d;
    }

    @Override // D0.l
    public final void d(SSLSocket sSLSocket, String str, List list) {
        AbstractC0150d.e(list, "protocols");
        if (a(sSLSocket)) {
            BCSSLSocket bCSSLSocket = (BCSSLSocket) sSLSocket;
            BCSSLParameters parameters = bCSSLSocket.getParameters();
            o oVar = o.f236a;
            Object[] array = C0.g.a(list).toArray(new String[0]);
            if (array != null) {
                parameters.setApplicationProtocols((String[]) array);
                bCSSLSocket.setParameters(parameters);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
    }
}

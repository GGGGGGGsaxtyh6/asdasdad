package D0;

import C0.o;
import android.net.ssl.SSLSockets;
import android.os.Build;
import j0.AbstractC0150d;
import java.io.IOException;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import org.conscrypt.BuildConfig;
import org.conscrypt.Conscrypt;

/* loaded from: classes.dex */
public final class i implements l {

    /* renamed from: b, reason: collision with root package name */
    public static final d f261b = new d(1);

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f262a;

    public /* synthetic */ i(int i2) {
        this.f262a = i2;
    }

    @Override // D0.l
    public final boolean a(SSLSocket sSLSocket) {
        boolean isSupportedSocket;
        switch (this.f262a) {
            case 0:
                return Conscrypt.isConscrypt(sSLSocket);
            default:
                isSupportedSocket = SSLSockets.isSupportedSocket(sSLSocket);
                return isSupportedSocket;
        }
    }

    @Override // D0.l
    public final String b(SSLSocket sSLSocket) {
        String applicationProtocol;
        boolean equals;
        switch (this.f262a) {
            case 0:
                if (Conscrypt.isConscrypt(sSLSocket)) {
                    return Conscrypt.getApplicationProtocol(sSLSocket);
                }
                return null;
            default:
                applicationProtocol = sSLSocket.getApplicationProtocol();
                if (applicationProtocol == null) {
                    equals = true;
                } else {
                    equals = applicationProtocol.equals(BuildConfig.FLAVOR);
                }
                if (equals) {
                    return null;
                }
                return applicationProtocol;
        }
    }

    @Override // D0.l
    public final boolean c() {
        switch (this.f262a) {
            case 0:
                boolean z2 = C0.h.f219d;
                return C0.h.f219d;
            default:
                o oVar = o.f236a;
                if (C0.g.c() && Build.VERSION.SDK_INT >= 29) {
                    return true;
                }
                return false;
        }
    }

    @Override // D0.l
    public final void d(SSLSocket sSLSocket, String str, List list) {
        int i2 = this.f262a;
        AbstractC0150d.e(list, "protocols");
        switch (i2) {
            case 0:
                if (Conscrypt.isConscrypt(sSLSocket)) {
                    Conscrypt.setUseSessionTickets(sSLSocket, true);
                    o oVar = o.f236a;
                    Object[] array = C0.g.a(list).toArray(new String[0]);
                    if (array != null) {
                        Conscrypt.setApplicationProtocols(sSLSocket, (String[]) array);
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                }
                return;
            default:
                try {
                    SSLSockets.setUseSessionTickets(sSLSocket, true);
                    SSLParameters sSLParameters = sSLSocket.getSSLParameters();
                    o oVar2 = o.f236a;
                    Object[] array2 = C0.g.a(list).toArray(new String[0]);
                    if (array2 != null) {
                        sSLParameters.setApplicationProtocols((String[]) array2);
                        sSLSocket.setSSLParameters(sSLParameters);
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                } catch (IllegalArgumentException e2) {
                    throw new IOException("Android internal error", e2);
                }
        }
    }
}

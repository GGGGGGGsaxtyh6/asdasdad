package Z;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* loaded from: classes.dex */
public final /* synthetic */ class c implements HostnameVerifier {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f694a;

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        switch (this.f694a) {
            case 0:
                u0.o oVar = i.h;
                return true;
            default:
                u0.o oVar2 = u.f731e;
                return true;
        }
    }
}

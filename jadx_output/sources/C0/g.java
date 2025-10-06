package C0;

import b0.AbstractC0084j;
import j0.AbstractC0150d;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSession;
import org.conscrypt.ConscryptHostnameVerifier;
import u0.r;

/* loaded from: classes.dex */
public final class g implements ConscryptHostnameVerifier {

    /* renamed from: a, reason: collision with root package name */
    public static final g f218a = new Object();

    public static ArrayList a(List list) {
        AbstractC0150d.e(list, "protocols");
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((r) obj) != r.HTTP_1_0) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(AbstractC0084j.I(arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((r) it.next()).f2548a);
        }
        return arrayList2;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, H0.e] */
    public static byte[] b(List list) {
        AbstractC0150d.e(list, "protocols");
        ?? obj = new Object();
        Iterator it = a(list).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            obj.u(str.length());
            obj.x(str);
        }
        return obj.j(obj.f412b);
    }

    public static boolean c() {
        return "Dalvik".equals(System.getProperty("java.vm.name"));
    }

    @Override // org.conscrypt.ConscryptHostnameVerifier
    public boolean verify(X509Certificate[] x509CertificateArr, String str, SSLSession sSLSession) {
        return true;
    }
}

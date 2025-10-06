package G0;

import A.e;
import b0.C0091q;
import j0.AbstractC0150d;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

/* loaded from: classes.dex */
public final class c implements HostnameVerifier {

    /* renamed from: a, reason: collision with root package name */
    public static final c f313a = new Object();

    public static List a(X509Certificate x509Certificate, int i2) {
        Object obj;
        C0091q c0091q = C0091q.f1234a;
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return c0091q;
            }
            ArrayList arrayList = new ArrayList();
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2 && AbstractC0150d.a(list.get(0), Integer.valueOf(i2)) && (obj = list.get(1)) != null) {
                    arrayList.add((String) obj);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return c0091q;
        }
    }

    public static boolean b(String str) {
        int i2;
        char c2;
        int length = str.length();
        int length2 = str.length();
        if (length2 >= 0) {
            if (length2 <= str.length()) {
                long j2 = 0;
                int i3 = 0;
                while (i3 < length2) {
                    char charAt = str.charAt(i3);
                    if (charAt < 128) {
                        j2++;
                    } else {
                        if (charAt < 2048) {
                            i2 = 2;
                        } else if (charAt >= 55296 && charAt <= 57343) {
                            int i4 = i3 + 1;
                            if (i4 < length2) {
                                c2 = str.charAt(i4);
                            } else {
                                c2 = 0;
                            }
                            if (charAt <= 56319 && c2 >= 56320 && c2 <= 57343) {
                                j2 += 4;
                                i3 += 2;
                            } else {
                                j2++;
                                i3 = i4;
                            }
                        } else {
                            i2 = 3;
                        }
                        j2 += i2;
                    }
                    i3++;
                }
                if (length != ((int) j2)) {
                    return false;
                }
                return true;
            }
            throw new IllegalArgumentException(("endIndex > string.length: " + length2 + " > " + str.length()).toString());
        }
        throw new IllegalArgumentException(e.b("endIndex < beginIndex: ", length2, " < 0").toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0134 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[LOOP:1: B:23:0x0075->B:54:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean c(java.lang.String r11, java.security.cert.X509Certificate r12) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: G0.c.c(java.lang.String, java.security.cert.X509Certificate):boolean");
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        Certificate certificate;
        AbstractC0150d.e(str, "host");
        AbstractC0150d.e(sSLSession, "session");
        if (b(str)) {
            try {
                certificate = sSLSession.getPeerCertificates()[0];
                if (certificate == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
                }
            } catch (SSLException unused) {
                return false;
            }
        }
        return c(str, (X509Certificate) certificate);
    }
}

package B0;

import j0.AbstractC0150d;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.conscrypt.BuildConfig;

/* renamed from: B0.f, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0005f {

    /* renamed from: a, reason: collision with root package name */
    public static final C0002c[] f111a;

    /* renamed from: b, reason: collision with root package name */
    public static final Map f112b;

    static {
        C0002c c0002c = new C0002c(C0002c.f93i, BuildConfig.FLAVOR);
        H0.h hVar = C0002c.f91f;
        C0002c c0002c2 = new C0002c(hVar, "GET");
        C0002c c0002c3 = new C0002c(hVar, "POST");
        H0.h hVar2 = C0002c.f92g;
        C0002c c0002c4 = new C0002c(hVar2, "/");
        C0002c c0002c5 = new C0002c(hVar2, "/index.html");
        H0.h hVar3 = C0002c.h;
        C0002c c0002c6 = new C0002c(hVar3, "http");
        C0002c c0002c7 = new C0002c(hVar3, "https");
        H0.h hVar4 = C0002c.f90e;
        C0002c[] c0002cArr = {c0002c, c0002c2, c0002c3, c0002c4, c0002c5, c0002c6, c0002c7, new C0002c(hVar4, "200"), new C0002c(hVar4, "204"), new C0002c(hVar4, "206"), new C0002c(hVar4, "304"), new C0002c(hVar4, "400"), new C0002c(hVar4, "404"), new C0002c(hVar4, "500"), new C0002c("accept-charset", BuildConfig.FLAVOR), new C0002c("accept-encoding", "gzip, deflate"), new C0002c("accept-language", BuildConfig.FLAVOR), new C0002c("accept-ranges", BuildConfig.FLAVOR), new C0002c("accept", BuildConfig.FLAVOR), new C0002c("access-control-allow-origin", BuildConfig.FLAVOR), new C0002c("age", BuildConfig.FLAVOR), new C0002c("allow", BuildConfig.FLAVOR), new C0002c("authorization", BuildConfig.FLAVOR), new C0002c("cache-control", BuildConfig.FLAVOR), new C0002c("content-disposition", BuildConfig.FLAVOR), new C0002c("content-encoding", BuildConfig.FLAVOR), new C0002c("content-language", BuildConfig.FLAVOR), new C0002c("content-length", BuildConfig.FLAVOR), new C0002c("content-location", BuildConfig.FLAVOR), new C0002c("content-range", BuildConfig.FLAVOR), new C0002c("content-type", BuildConfig.FLAVOR), new C0002c("cookie", BuildConfig.FLAVOR), new C0002c("date", BuildConfig.FLAVOR), new C0002c("etag", BuildConfig.FLAVOR), new C0002c("expect", BuildConfig.FLAVOR), new C0002c("expires", BuildConfig.FLAVOR), new C0002c("from", BuildConfig.FLAVOR), new C0002c("host", BuildConfig.FLAVOR), new C0002c("if-match", BuildConfig.FLAVOR), new C0002c("if-modified-since", BuildConfig.FLAVOR), new C0002c("if-none-match", BuildConfig.FLAVOR), new C0002c("if-range", BuildConfig.FLAVOR), new C0002c("if-unmodified-since", BuildConfig.FLAVOR), new C0002c("last-modified", BuildConfig.FLAVOR), new C0002c("link", BuildConfig.FLAVOR), new C0002c("location", BuildConfig.FLAVOR), new C0002c("max-forwards", BuildConfig.FLAVOR), new C0002c("proxy-authenticate", BuildConfig.FLAVOR), new C0002c("proxy-authorization", BuildConfig.FLAVOR), new C0002c("range", BuildConfig.FLAVOR), new C0002c("referer", BuildConfig.FLAVOR), new C0002c("refresh", BuildConfig.FLAVOR), new C0002c("retry-after", BuildConfig.FLAVOR), new C0002c("server", BuildConfig.FLAVOR), new C0002c("set-cookie", BuildConfig.FLAVOR), new C0002c("strict-transport-security", BuildConfig.FLAVOR), new C0002c("transfer-encoding", BuildConfig.FLAVOR), new C0002c("user-agent", BuildConfig.FLAVOR), new C0002c("vary", BuildConfig.FLAVOR), new C0002c("via", BuildConfig.FLAVOR), new C0002c("www-authenticate", BuildConfig.FLAVOR)};
        f111a = c0002cArr;
        LinkedHashMap linkedHashMap = new LinkedHashMap(61);
        int i2 = 0;
        while (i2 < 61) {
            int i3 = i2 + 1;
            if (!linkedHashMap.containsKey(c0002cArr[i2].f94a)) {
                linkedHashMap.put(c0002cArr[i2].f94a, Integer.valueOf(i2));
            }
            i2 = i3;
        }
        Map unmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        AbstractC0150d.d(unmodifiableMap, "unmodifiableMap(result)");
        f112b = unmodifiableMap;
    }

    public static void a(H0.h hVar) {
        AbstractC0150d.e(hVar, "name");
        int a2 = hVar.a();
        int i2 = 0;
        while (i2 < a2) {
            int i3 = i2 + 1;
            byte d2 = hVar.d(i2);
            if (65 <= d2 && d2 <= 90) {
                throw new IOException(AbstractC0150d.h(hVar.h(), "PROTOCOL_ERROR response malformed: mixed case name: "));
            }
            i2 = i3;
        }
    }
}

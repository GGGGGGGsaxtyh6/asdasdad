package u0;

import b0.C0092r;
import j0.AbstractC0150d;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import k.C0213z;

/* loaded from: classes.dex */
public final class s {

    /* renamed from: a, reason: collision with root package name */
    public m f2549a;

    /* renamed from: d, reason: collision with root package name */
    public C0213z f2552d;

    /* renamed from: e, reason: collision with root package name */
    public LinkedHashMap f2553e = new LinkedHashMap();

    /* renamed from: b, reason: collision with root package name */
    public String f2550b = "GET";

    /* renamed from: c, reason: collision with root package name */
    public A.f f2551c = new A.f(29);

    public final B0.h a() {
        Map unmodifiableMap;
        m mVar = this.f2549a;
        if (mVar != null) {
            String str = this.f2550b;
            k A2 = this.f2551c.A();
            C0213z c0213z = this.f2552d;
            LinkedHashMap linkedHashMap = this.f2553e;
            byte[] bArr = v0.b.f2808a;
            AbstractC0150d.e(linkedHashMap, "<this>");
            if (linkedHashMap.isEmpty()) {
                unmodifiableMap = C0092r.f1235a;
            } else {
                unmodifiableMap = Collections.unmodifiableMap(new LinkedHashMap(linkedHashMap));
                AbstractC0150d.d(unmodifiableMap, "{\n    Collections.unmodi…(LinkedHashMap(this))\n  }");
            }
            return new B0.h(mVar, str, A2, c0213z, unmodifiableMap);
        }
        throw new IllegalStateException("url == null");
    }

    public final void b(String str, String str2) {
        AbstractC0150d.e(str2, "value");
        A.f fVar = this.f2551c;
        fVar.getClass();
        C0.f.d(str);
        C0.f.i(str2, str);
        fVar.D(str);
        fVar.z(str, str2);
    }

    public final void c(String str, C0213z c0213z) {
        AbstractC0150d.e(str, "method");
        if (str.length() > 0) {
            if (c0213z == null) {
                if (str.equals("POST") || str.equals("PUT") || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT")) {
                    throw new IllegalArgumentException(A.e.d("method ", str, " must have a request body.").toString());
                }
            } else if (!C0.m.p(str)) {
                throw new IllegalArgumentException(A.e.d("method ", str, " must not have a request body.").toString());
            }
            this.f2550b = str;
            this.f2552d = c0213z;
            return;
        }
        throw new IllegalArgumentException("method.isEmpty() == true");
    }

    public final void d(String str) {
        AbstractC0150d.e(str, "url");
        if (q0.k.B(str, true, "ws:")) {
            String substring = str.substring(3);
            AbstractC0150d.d(substring, "this as java.lang.String).substring(startIndex)");
            str = AbstractC0150d.h(substring, "http:");
        } else if (q0.k.B(str, true, "wss:")) {
            String substring2 = str.substring(4);
            AbstractC0150d.d(substring2, "this as java.lang.String).substring(startIndex)");
            str = AbstractC0150d.h(substring2, "https:");
        }
        AbstractC0150d.e(str, "<this>");
        l lVar = new l();
        lVar.c(null, str);
        this.f2549a = lVar.a();
    }
}

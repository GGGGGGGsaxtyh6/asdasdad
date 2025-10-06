package u0;

import j0.AbstractC0150d;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class o {

    /* renamed from: c, reason: collision with root package name */
    public static final Pattern f2491c = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");

    /* renamed from: d, reason: collision with root package name */
    public static final Pattern f2492d = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    /* renamed from: a, reason: collision with root package name */
    public final String f2493a;

    /* renamed from: b, reason: collision with root package name */
    public final String[] f2494b;

    public o(String[] strArr, String str) {
        this.f2493a = str;
        this.f2494b = strArr;
    }

    public final Charset a(Charset charset) {
        String str;
        String[] strArr = this.f2494b;
        int i2 = 0;
        int k2 = C0.m.k(0, strArr.length - 1, 2);
        if (k2 >= 0) {
            while (true) {
                int i3 = i2 + 2;
                if (q0.k.x(strArr[i2], "charset")) {
                    str = strArr[i2 + 1];
                    break;
                }
                if (i2 == k2) {
                    break;
                }
                i2 = i3;
            }
        }
        str = null;
        if (str == null) {
            return charset;
        }
        try {
            return Charset.forName(str);
        } catch (IllegalArgumentException unused) {
            return charset;
        }
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof o) && AbstractC0150d.a(((o) obj).f2493a, this.f2493a)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f2493a.hashCode();
    }

    public final String toString() {
        return this.f2493a;
    }
}

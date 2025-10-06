package A0;

import H0.o;
import j0.AbstractC0150d;
import org.conscrypt.BuildConfig;
import u0.k;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public final o f28a;

    /* renamed from: b, reason: collision with root package name */
    public long f29b;

    public a(o oVar) {
        AbstractC0150d.e(oVar, "source");
        this.f28a = oVar;
        this.f29b = 262144L;
    }

    public final k a() {
        A.f fVar = new A.f(29);
        while (true) {
            String o2 = this.f28a.o(this.f29b);
            this.f29b -= o2.length();
            if (o2.length() == 0) {
                return fVar.A();
            }
            int G2 = q0.d.G(o2, ':', 1, 4);
            if (G2 != -1) {
                String substring = o2.substring(0, G2);
                AbstractC0150d.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                String substring2 = o2.substring(G2 + 1);
                AbstractC0150d.d(substring2, "this as java.lang.String).substring(startIndex)");
                fVar.z(substring, substring2);
            } else if (o2.charAt(0) == ':') {
                String substring3 = o2.substring(1);
                AbstractC0150d.d(substring3, "this as java.lang.String).substring(startIndex)");
                fVar.z(BuildConfig.FLAVOR, substring3);
            } else {
                fVar.z(BuildConfig.FLAVOR, o2);
            }
        }
    }
}

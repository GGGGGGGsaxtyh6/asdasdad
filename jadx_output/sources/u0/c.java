package u0;

import j0.AbstractC0150d;
import java.util.concurrent.TimeUnit;
import org.conscrypt.BuildConfig;

/* loaded from: classes.dex */
public final class c {

    /* renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ int f2417n = 0;

    /* renamed from: a, reason: collision with root package name */
    public final boolean f2418a;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f2419b;

    /* renamed from: c, reason: collision with root package name */
    public final int f2420c;

    /* renamed from: d, reason: collision with root package name */
    public final int f2421d;

    /* renamed from: e, reason: collision with root package name */
    public final boolean f2422e;

    /* renamed from: f, reason: collision with root package name */
    public final boolean f2423f;

    /* renamed from: g, reason: collision with root package name */
    public final boolean f2424g;
    public final int h;

    /* renamed from: i, reason: collision with root package name */
    public final int f2425i;

    /* renamed from: j, reason: collision with root package name */
    public final boolean f2426j;

    /* renamed from: k, reason: collision with root package name */
    public final boolean f2427k;

    /* renamed from: l, reason: collision with root package name */
    public final boolean f2428l;

    /* renamed from: m, reason: collision with root package name */
    public String f2429m;

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        AbstractC0150d.e(timeUnit, "timeUnit");
        timeUnit.toSeconds(Integer.MAX_VALUE);
    }

    public c(boolean z2, boolean z3, int i2, int i3, boolean z4, boolean z5, boolean z6, int i4, int i5, boolean z7, boolean z8, boolean z9, String str) {
        this.f2418a = z2;
        this.f2419b = z3;
        this.f2420c = i2;
        this.f2421d = i3;
        this.f2422e = z4;
        this.f2423f = z5;
        this.f2424g = z6;
        this.h = i4;
        this.f2425i = i5;
        this.f2426j = z7;
        this.f2427k = z8;
        this.f2428l = z9;
        this.f2429m = str;
    }

    public final String toString() {
        String str = this.f2429m;
        if (str == null) {
            StringBuilder sb = new StringBuilder();
            if (this.f2418a) {
                sb.append("no-cache, ");
            }
            if (this.f2419b) {
                sb.append("no-store, ");
            }
            int i2 = this.f2420c;
            if (i2 != -1) {
                sb.append("max-age=");
                sb.append(i2);
                sb.append(", ");
            }
            int i3 = this.f2421d;
            if (i3 != -1) {
                sb.append("s-maxage=");
                sb.append(i3);
                sb.append(", ");
            }
            if (this.f2422e) {
                sb.append("private, ");
            }
            if (this.f2423f) {
                sb.append("public, ");
            }
            if (this.f2424g) {
                sb.append("must-revalidate, ");
            }
            int i4 = this.h;
            if (i4 != -1) {
                sb.append("max-stale=");
                sb.append(i4);
                sb.append(", ");
            }
            int i5 = this.f2425i;
            if (i5 != -1) {
                sb.append("min-fresh=");
                sb.append(i5);
                sb.append(", ");
            }
            if (this.f2426j) {
                sb.append("only-if-cached, ");
            }
            if (this.f2427k) {
                sb.append("no-transform, ");
            }
            if (this.f2428l) {
                sb.append("immutable, ");
            }
            if (sb.length() == 0) {
                return BuildConfig.FLAVOR;
            }
            sb.delete(sb.length() - 2, sb.length());
            String sb2 = sb.toString();
            AbstractC0150d.d(sb2, "StringBuilder().apply(builderAction).toString()");
            this.f2429m = sb2;
            return sb2;
        }
        return str;
    }
}

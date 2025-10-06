package u0;

import j0.AbstractC0150d;
import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class i {

    /* renamed from: j, reason: collision with root package name */
    public static final Pattern f2458j = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* renamed from: k, reason: collision with root package name */
    public static final Pattern f2459k = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* renamed from: l, reason: collision with root package name */
    public static final Pattern f2460l = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* renamed from: m, reason: collision with root package name */
    public static final Pattern f2461m = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* renamed from: a, reason: collision with root package name */
    public final String f2462a;

    /* renamed from: b, reason: collision with root package name */
    public final String f2463b;

    /* renamed from: c, reason: collision with root package name */
    public final long f2464c;

    /* renamed from: d, reason: collision with root package name */
    public final String f2465d;

    /* renamed from: e, reason: collision with root package name */
    public final String f2466e;

    /* renamed from: f, reason: collision with root package name */
    public final boolean f2467f;

    /* renamed from: g, reason: collision with root package name */
    public final boolean f2468g;
    public final boolean h;

    /* renamed from: i, reason: collision with root package name */
    public final boolean f2469i;

    public i(String str, String str2, long j2, String str3, String str4, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.f2462a = str;
        this.f2463b = str2;
        this.f2464c = j2;
        this.f2465d = str3;
        this.f2466e = str4;
        this.f2467f = z2;
        this.f2468g = z3;
        this.h = z4;
        this.f2469i = z5;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof i) {
            i iVar = (i) obj;
            if (AbstractC0150d.a(iVar.f2462a, this.f2462a) && AbstractC0150d.a(iVar.f2463b, this.f2463b) && iVar.f2464c == this.f2464c && AbstractC0150d.a(iVar.f2465d, this.f2465d) && AbstractC0150d.a(iVar.f2466e, this.f2466e) && iVar.f2467f == this.f2467f && iVar.f2468g == this.f2468g && iVar.h == this.h && iVar.f2469i == this.f2469i) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f2469i) + ((Boolean.hashCode(this.h) + ((Boolean.hashCode(this.f2468g) + ((Boolean.hashCode(this.f2467f) + ((this.f2466e.hashCode() + ((this.f2465d.hashCode() + ((Long.hashCode(this.f2464c) + ((this.f2463b.hashCode() + ((this.f2462a.hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f2462a);
        sb.append('=');
        sb.append(this.f2463b);
        if (this.h) {
            long j2 = this.f2464c;
            if (j2 == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                String format = ((DateFormat) z0.c.f2970a.get()).format(new Date(j2));
                AbstractC0150d.d(format, "STANDARD_DATE_FORMAT.get().format(this)");
                sb.append(format);
            }
        }
        if (!this.f2469i) {
            sb.append("; domain=");
            sb.append(this.f2465d);
        }
        sb.append("; path=");
        sb.append(this.f2466e);
        if (this.f2467f) {
            sb.append("; secure");
        }
        if (this.f2468g) {
            sb.append("; httponly");
        }
        String sb2 = sb.toString();
        AbstractC0150d.d(sb2, "toString()");
        return sb2;
    }
}

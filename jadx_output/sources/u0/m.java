package u0;

import j0.AbstractC0150d;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.conscrypt.BuildConfig;

/* loaded from: classes.dex */
public final class m {

    /* renamed from: j, reason: collision with root package name */
    public static final char[] f2482j = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a, reason: collision with root package name */
    public final String f2483a;

    /* renamed from: b, reason: collision with root package name */
    public final String f2484b;

    /* renamed from: c, reason: collision with root package name */
    public final String f2485c;

    /* renamed from: d, reason: collision with root package name */
    public final String f2486d;

    /* renamed from: e, reason: collision with root package name */
    public final int f2487e;

    /* renamed from: f, reason: collision with root package name */
    public final ArrayList f2488f;

    /* renamed from: g, reason: collision with root package name */
    public final String f2489g;
    public final String h;

    /* renamed from: i, reason: collision with root package name */
    public final boolean f2490i;

    public m(String str, String str2, String str3, String str4, int i2, ArrayList arrayList, ArrayList arrayList2, String str5, String str6) {
        AbstractC0150d.e(str, "scheme");
        AbstractC0150d.e(str4, "host");
        this.f2483a = str;
        this.f2484b = str2;
        this.f2485c = str3;
        this.f2486d = str4;
        this.f2487e = i2;
        this.f2488f = arrayList2;
        this.f2489g = str5;
        this.h = str6;
        this.f2490i = str.equals("https");
    }

    public final String a() {
        if (this.f2485c.length() == 0) {
            return BuildConfig.FLAVOR;
        }
        int length = this.f2483a.length() + 3;
        String str = this.h;
        String substring = str.substring(q0.d.G(str, ':', length, 4) + 1, q0.d.G(str, '@', 0, 6));
        AbstractC0150d.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    public final String b() {
        int length = this.f2483a.length() + 3;
        String str = this.h;
        int G2 = q0.d.G(str, '/', length, 4);
        String substring = str.substring(G2, v0.b.f(str, "?#", G2, str.length()));
        AbstractC0150d.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    public final ArrayList c() {
        int length = this.f2483a.length() + 3;
        String str = this.h;
        int G2 = q0.d.G(str, '/', length, 4);
        int f2 = v0.b.f(str, "?#", G2, str.length());
        ArrayList arrayList = new ArrayList();
        while (G2 < f2) {
            int i2 = G2 + 1;
            int e2 = v0.b.e(str, '/', i2, f2);
            String substring = str.substring(i2, e2);
            AbstractC0150d.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            arrayList.add(substring);
            G2 = e2;
        }
        return arrayList;
    }

    public final String d() {
        if (this.f2488f == null) {
            return null;
        }
        String str = this.h;
        int G2 = q0.d.G(str, '?', 0, 6) + 1;
        String substring = str.substring(G2, v0.b.e(str, '#', G2, str.length()));
        AbstractC0150d.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    public final String e() {
        if (this.f2484b.length() == 0) {
            return BuildConfig.FLAVOR;
        }
        int length = this.f2483a.length() + 3;
        String str = this.h;
        String substring = str.substring(length, v0.b.f(str, ":@", length, str.length()));
        AbstractC0150d.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof m) && AbstractC0150d.a(((m) obj).h, this.h)) {
            return true;
        }
        return false;
    }

    public final String f() {
        l lVar;
        try {
            lVar = new l();
            lVar.c(this, "/...");
        } catch (IllegalArgumentException unused) {
            lVar = null;
        }
        AbstractC0150d.b(lVar);
        lVar.f2476b = b.b(BuildConfig.FLAVOR, 0, 0, " \"':;<=>@[]^`{}|/\\?#", 251);
        lVar.f2477c = b.b(BuildConfig.FLAVOR, 0, 0, " \"':;<=>@[]^`{}|/\\?#", 251);
        return lVar.a().h;
    }

    public final URI g() {
        int i2;
        ArrayList f2;
        String substring;
        String replaceAll;
        String b2;
        l lVar = new l();
        String str = this.f2483a;
        lVar.f2475a = str;
        lVar.f2476b = e();
        lVar.f2477c = a();
        lVar.f2478d = this.f2486d;
        AbstractC0150d.e(str, "scheme");
        int i3 = -1;
        if (str.equals("http")) {
            i2 = 80;
        } else if (str.equals("https")) {
            i2 = 443;
        } else {
            i2 = -1;
        }
        int i4 = this.f2487e;
        if (i4 != i2) {
            i3 = i4;
        }
        lVar.f2479e = i3;
        ArrayList arrayList = lVar.f2480f;
        arrayList.clear();
        arrayList.addAll(c());
        String d2 = d();
        String str2 = null;
        if (d2 == null) {
            f2 = null;
        } else {
            f2 = b.f(b.b(d2, 0, 0, " \"'<>#", 211));
        }
        lVar.f2481g = f2;
        if (this.f2489g == null) {
            substring = null;
        } else {
            String str3 = this.h;
            substring = str3.substring(q0.d.G(str3, '#', 0, 6) + 1);
            AbstractC0150d.d(substring, "this as java.lang.String).substring(startIndex)");
        }
        lVar.h = substring;
        String str4 = lVar.f2478d;
        if (str4 == null) {
            replaceAll = null;
        } else {
            Pattern compile = Pattern.compile("[\"<>^`{|}]");
            AbstractC0150d.d(compile, "compile(...)");
            replaceAll = compile.matcher(str4).replaceAll(BuildConfig.FLAVOR);
            AbstractC0150d.d(replaceAll, "replaceAll(...)");
        }
        lVar.f2478d = replaceAll;
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            arrayList.set(i5, b.b((String) arrayList.get(i5), 0, 0, "[]", 227));
        }
        ArrayList arrayList2 = lVar.f2481g;
        if (arrayList2 != null) {
            int size2 = arrayList2.size();
            int i6 = 0;
            while (i6 < size2) {
                int i7 = i6 + 1;
                String str5 = (String) arrayList2.get(i6);
                if (str5 == null) {
                    b2 = null;
                } else {
                    b2 = b.b(str5, 0, 0, "\\^`{|}", 195);
                }
                arrayList2.set(i6, b2);
                i6 = i7;
            }
        }
        String str6 = lVar.h;
        if (str6 != null) {
            str2 = b.b(str6, 0, 0, " \"#<>\\^`{|}", 163);
        }
        lVar.h = str2;
        String lVar2 = lVar.toString();
        try {
            return new URI(lVar2);
        } catch (URISyntaxException e2) {
            try {
                Pattern compile2 = Pattern.compile("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]");
                AbstractC0150d.d(compile2, "compile(...)");
                String replaceAll2 = compile2.matcher(lVar2).replaceAll(BuildConfig.FLAVOR);
                AbstractC0150d.d(replaceAll2, "replaceAll(...)");
                URI create = URI.create(replaceAll2);
                AbstractC0150d.d(create, "{\n      // Unlikely edge…Unexpected!\n      }\n    }");
                return create;
            } catch (Exception unused) {
                throw new RuntimeException(e2);
            }
        }
    }

    public final int hashCode() {
        return this.h.hashCode();
    }

    public final String toString() {
        return this.h;
    }
}

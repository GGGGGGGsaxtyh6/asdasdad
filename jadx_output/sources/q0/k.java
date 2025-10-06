package q0;

import j0.AbstractC0150d;

/* loaded from: classes.dex */
public abstract class k extends j {
    public static boolean A(String str, String str2, int i2, boolean z2) {
        AbstractC0150d.e(str, "<this>");
        if (!z2) {
            return str.startsWith(str2, i2);
        }
        return y(i2, 0, str2.length(), str, str2, z2);
    }

    public static boolean B(String str, boolean z2, String str2) {
        AbstractC0150d.e(str, "<this>");
        AbstractC0150d.e(str2, "prefix");
        if (!z2) {
            return str.startsWith(str2);
        }
        return y(0, 0, str2.length(), str, str2, z2);
    }

    public static boolean x(String str, String str2) {
        if (str == null) {
            if (str2 == null) {
                return true;
            }
            return false;
        }
        return str.equalsIgnoreCase(str2);
    }

    public static final boolean y(int i2, int i3, int i4, String str, String str2, boolean z2) {
        AbstractC0150d.e(str, "<this>");
        AbstractC0150d.e(str2, "other");
        if (!z2) {
            return str.regionMatches(i2, str2, i3, i4);
        }
        return str.regionMatches(z2, i2, str2, i3, i4);
    }

    public static String z(String str, String str2, String str3) {
        AbstractC0150d.e(str, "<this>");
        int F2 = d.F(str, str2, 0, false);
        if (F2 >= 0) {
            int length = str2.length();
            int i2 = 1;
            if (length >= 1) {
                i2 = length;
            }
            int length2 = str3.length() + (str.length() - length);
            if (length2 >= 0) {
                StringBuilder sb = new StringBuilder(length2);
                int i3 = 0;
                do {
                    sb.append((CharSequence) str, i3, F2);
                    sb.append(str3);
                    i3 = F2 + length;
                    if (F2 >= str.length()) {
                        break;
                    }
                    F2 = d.F(str, str2, F2 + i2, false);
                } while (F2 > 0);
                sb.append((CharSequence) str, i3, str.length());
                String sb2 = sb.toString();
                AbstractC0150d.d(sb2, "toString(...)");
                return sb2;
            }
            throw new OutOfMemoryError();
        }
        return str;
    }
}

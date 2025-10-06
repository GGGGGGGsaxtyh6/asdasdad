package A;

import org.conscrypt.OpenSSLProvider;
import org.conscrypt.ct.CTConstants;

/* loaded from: classes.dex */
public abstract /* synthetic */ class e {
    public static String a(String str, int i2) {
        return str + i2;
    }

    public static String b(String str, int i2, String str2) {
        return str + i2 + str2;
    }

    public static String c(String str, String str2) {
        return str + str2;
    }

    public static String d(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static StringBuilder e(OpenSSLProvider openSSLProvider, String str, String str2, String str3, String str4) {
        openSSLProvider.put(str, str2);
        openSSLProvider.put(str3, str4);
        return new StringBuilder();
    }

    public static /* synthetic */ void f(Object obj) {
        if (obj != null) {
            throw new ClassCastException();
        }
    }

    public static void g(StringBuilder sb, String str, String str2, OpenSSLProvider openSSLProvider, String str3) {
        sb.append(str);
        sb.append(str2);
        openSSLProvider.put(str3, sb.toString());
    }

    public static /* synthetic */ String h(int i2) {
        switch (i2) {
            case 1:
                return "NONE";
            case 2:
                return "LEFT";
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                return "TOP";
            case 4:
                return "RIGHT";
            case 5:
                return "BOTTOM";
            case 6:
                return "BASELINE";
            case 7:
                return "CENTER";
            case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                return "CENTER_X";
            case 9:
                return "CENTER_Y";
            default:
                throw null;
        }
    }
}

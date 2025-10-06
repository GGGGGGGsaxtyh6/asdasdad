package B0;

import j0.AbstractC0150d;
import org.conscrypt.BuildConfig;
import org.conscrypt.PSKKeyManager;

/* renamed from: B0.g, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0006g {

    /* renamed from: a, reason: collision with root package name */
    public static final H0.h f113a;

    /* renamed from: b, reason: collision with root package name */
    public static final String[] f114b;

    /* renamed from: c, reason: collision with root package name */
    public static final String[] f115c;

    /* renamed from: d, reason: collision with root package name */
    public static final String[] f116d;

    static {
        H0.h hVar = H0.h.f413d;
        f113a = A.m.e("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
        f114b = new String[]{"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
        f115c = new String[64];
        String[] strArr = new String[PSKKeyManager.MAX_KEY_LENGTH_BYTES];
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            String binaryString = Integer.toBinaryString(i3);
            AbstractC0150d.d(binaryString, "toBinaryString(it)");
            String replace = v0.b.h("%8s", binaryString).replace(' ', '0');
            AbstractC0150d.d(replace, "replace(...)");
            strArr[i3] = replace;
        }
        f116d = strArr;
        String[] strArr2 = f115c;
        strArr2[0] = BuildConfig.FLAVOR;
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        int i4 = iArr[0];
        strArr2[i4 | 8] = AbstractC0150d.h("|PADDED", strArr2[i4]);
        strArr2[4] = "END_HEADERS";
        strArr2[32] = "PRIORITY";
        strArr2[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        int i5 = 0;
        while (i5 < 3) {
            int i6 = iArr2[i5];
            i5++;
            int i7 = iArr[0];
            String[] strArr3 = f115c;
            int i8 = i7 | i6;
            StringBuilder sb = new StringBuilder();
            sb.append((Object) strArr3[i7]);
            sb.append('|');
            sb.append((Object) strArr3[i6]);
            strArr3[i8] = sb.toString();
            strArr3[i8 | 8] = ((Object) strArr3[i7]) + '|' + ((Object) strArr3[i6]) + "|PADDED";
        }
        int length = f115c.length;
        while (i2 < length) {
            int i9 = i2 + 1;
            String[] strArr4 = f115c;
            if (strArr4[i2] == null) {
                strArr4[i2] = f116d[i2];
            }
            i2 = i9;
        }
    }

    public static String a(boolean z2, int i2, int i3, int i4, int i5) {
        String h;
        String str;
        String str2;
        String str3;
        String[] strArr = f114b;
        if (i4 < strArr.length) {
            h = strArr[i4];
        } else {
            h = v0.b.h("0x%02x", Integer.valueOf(i4));
        }
        if (i5 == 0) {
            str = BuildConfig.FLAVOR;
        } else {
            String[] strArr2 = f116d;
            if (i4 != 2 && i4 != 3) {
                if (i4 != 4 && i4 != 6) {
                    if (i4 != 7 && i4 != 8) {
                        String[] strArr3 = f115c;
                        if (i5 < strArr3.length) {
                            str2 = strArr3[i5];
                            AbstractC0150d.b(str2);
                        } else {
                            str2 = strArr2[i5];
                        }
                        if (i4 == 5 && (i5 & 4) != 0) {
                            str = q0.k.z(str2, "HEADERS", "PUSH_PROMISE");
                        } else if (i4 == 0 && (i5 & 32) != 0) {
                            str = q0.k.z(str2, "PRIORITY", "COMPRESSED");
                        } else {
                            str = str2;
                        }
                    }
                } else if (i5 == 1) {
                    str = "ACK";
                } else {
                    str = strArr2[i5];
                }
            }
            str = strArr2[i5];
        }
        if (z2) {
            str3 = "<<";
        } else {
            str3 = ">>";
        }
        return v0.b.h("%s 0x%08x %5d %-13s %s", str3, Integer.valueOf(i2), Integer.valueOf(i3), h, str);
    }
}

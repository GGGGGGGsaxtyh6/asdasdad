package I0;

import H0.r;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    public static final char[] f465a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static final int a(char c2) {
        if ('0' <= c2 && c2 <= '9') {
            return c2 - '0';
        }
        if ('a' <= c2 && c2 <= 'f') {
            return c2 - 'W';
        }
        if ('A' <= c2 && c2 <= 'F') {
            return c2 - '7';
        }
        throw new IllegalArgumentException(AbstractC0150d.h(Character.valueOf(c2), "Unexpected hex digit: "));
    }

    public static final int b(r rVar, int i2) {
        int i3;
        AbstractC0150d.e(rVar, "<this>");
        int i4 = i2 + 1;
        int length = rVar.f446e.length;
        int[] iArr = rVar.f447f;
        AbstractC0150d.e(iArr, "<this>");
        int i5 = length - 1;
        int i6 = 0;
        while (true) {
            if (i6 <= i5) {
                i3 = (i6 + i5) >>> 1;
                int i7 = iArr[i3];
                if (i7 < i4) {
                    i6 = i3 + 1;
                } else {
                    if (i7 <= i4) {
                        break;
                    }
                    i5 = i3 - 1;
                }
            } else {
                i3 = (-i6) - 1;
                break;
            }
        }
        if (i3 < 0) {
            return ~i3;
        }
        return i3;
    }
}

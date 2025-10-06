package org.conscrypt;

/* loaded from: classes.dex */
final class ArrayUtils {
    private ArrayUtils() {
    }

    public static void checkOffsetAndCount(int i2, int i3, int i4) {
        if ((i3 | i4) >= 0 && i3 <= i2 && i2 - i3 >= i4) {
            return;
        }
        throw new ArrayIndexOutOfBoundsException("length=" + i2 + "; regionStart=" + i3 + "; regionLength=" + i4);
    }
}

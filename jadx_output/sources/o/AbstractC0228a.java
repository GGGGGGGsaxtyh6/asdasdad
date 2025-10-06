package o;

import j0.AbstractC0150d;

/* renamed from: o.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0228a {

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f2139a = new int[0];

    /* renamed from: b, reason: collision with root package name */
    public static final Object[] f2140b = new Object[0];

    public static final int a(int i2, int i3, int[] iArr) {
        AbstractC0150d.e(iArr, "array");
        int i4 = i2 - 1;
        int i5 = 0;
        while (i5 <= i4) {
            int i6 = (i5 + i4) >>> 1;
            int i7 = iArr[i6];
            if (i7 < i3) {
                i5 = i6 + 1;
            } else if (i7 > i3) {
                i4 = i6 - 1;
            } else {
                return i6;
            }
        }
        return ~i5;
    }

    public static final int b(long[] jArr, int i2, long j2) {
        AbstractC0150d.e(jArr, "array");
        int i3 = i2 - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            long j3 = jArr[i5];
            if (j3 < j2) {
                i4 = i5 + 1;
            } else if (j3 > j2) {
                i3 = i5 - 1;
            } else {
                return i5;
            }
        }
        return ~i4;
    }
}

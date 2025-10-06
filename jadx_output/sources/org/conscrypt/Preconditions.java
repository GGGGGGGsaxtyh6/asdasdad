package org.conscrypt;

import A.e;

/* loaded from: classes.dex */
final class Preconditions {
    private Preconditions() {
    }

    private static String badPositionIndex(int i2, int i3, String str) {
        if (i2 < 0) {
            return str + " (" + i2 + ") must not be negative";
        }
        if (i3 >= 0) {
            return str + " (" + i2 + ") must not be greater than size (" + i3 + ")";
        }
        throw new IllegalArgumentException(e.a("negative size: ", i3));
    }

    private static String badPositionIndexes(int i2, int i3, int i4) {
        if (i2 >= 0 && i2 <= i4) {
            if (i3 >= 0 && i3 <= i4) {
                return "end index (" + i3 + ") must not be less than start index (" + i2 + ")";
            }
            return badPositionIndex(i3, i4, "end index");
        }
        return badPositionIndex(i2, i4, "start index");
    }

    public static void checkArgument(boolean z2, String str) {
        if (!z2) {
            throw new IllegalArgumentException(str);
        }
    }

    public static <T> T checkNotNull(T t2, String str) {
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException(str);
    }

    public static void checkPositionIndexes(int i2, int i3, int i4) {
        if (i2 >= 0 && i3 >= i2 && i3 <= i4) {
        } else {
            throw new IndexOutOfBoundsException(badPositionIndexes(i2, i3, i4));
        }
    }

    public static void checkArgument(boolean z2, String str, Object obj) {
        if (!z2) {
            throw new IllegalArgumentException(String.format(str, obj));
        }
    }
}

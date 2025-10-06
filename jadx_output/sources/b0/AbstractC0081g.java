package b0;

import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.Arrays;

/* renamed from: b0.g, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0081g extends C0.m {
    public static void A(Object[] objArr, int i2, int i3) {
        AbstractC0150d.e(objArr, "<this>");
        Arrays.fill(objArr, i2, i3, (Object) null);
    }

    public static ArrayList B(Object[] objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static void w(int i2, int i3, int i4, int[] iArr, int[] iArr2) {
        AbstractC0150d.e(iArr, "<this>");
        AbstractC0150d.e(iArr2, "destination");
        System.arraycopy(iArr, i3, iArr2, i2, i4 - i3);
    }

    public static void x(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        AbstractC0150d.e(bArr, "<this>");
        AbstractC0150d.e(bArr2, "destination");
        System.arraycopy(bArr, i3, bArr2, i2, i4 - i3);
    }

    public static void y(Object[] objArr, Object[] objArr2, int i2, int i3, int i4) {
        AbstractC0150d.e(objArr, "<this>");
        AbstractC0150d.e(objArr2, "destination");
        System.arraycopy(objArr, i3, objArr2, i2, i4 - i3);
    }

    public static /* synthetic */ void z(Object[] objArr, Object[] objArr2, int i2, int i3, int i4) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        y(objArr, objArr2, 0, i2, i3);
    }
}

package I0;

import H0.e;
import H0.m;
import H0.p;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public static final byte[] f464a;

    static {
        byte[] bytes = "0123456789abcdef".getBytes(q0.a.f2219a);
        AbstractC0150d.d(bytes, "(this as java.lang.String).getBytes(charset)");
        f464a = bytes;
    }

    public static final String a(long j2, e eVar) {
        AbstractC0150d.e(eVar, "<this>");
        if (j2 > 0) {
            long j3 = j2 - 1;
            if (eVar.h(j3) == ((byte) 13)) {
                String n2 = eVar.n(j3, q0.a.f2219a);
                eVar.o(2L);
                return n2;
            }
        }
        String n3 = eVar.n(j2, q0.a.f2219a);
        eVar.o(1L);
        return n3;
    }

    public static final int b(e eVar, m mVar, boolean z2) {
        int i2;
        int i3;
        boolean z3;
        p pVar;
        byte[] bArr;
        int i4;
        AbstractC0150d.e(eVar, "<this>");
        AbstractC0150d.e(mVar, "options");
        p pVar2 = eVar.f411a;
        int i5 = -2;
        if (pVar2 == null) {
            if (z2) {
                return -2;
            }
            return -1;
        }
        int i6 = pVar2.f437b;
        int i7 = pVar2.f438c;
        byte[] bArr2 = pVar2.f436a;
        p pVar3 = pVar2;
        int i8 = -1;
        int i9 = 0;
        loop0: while (true) {
            int i10 = i9 + 1;
            int[] iArr = mVar.f429b;
            int i11 = iArr[i9];
            int i12 = i9 + 2;
            int i13 = iArr[i10];
            if (i13 != -1) {
                i8 = i13;
            }
            if (pVar3 == null) {
                break;
            }
            if (i11 < 0) {
                int i14 = (i11 * (-1)) + i12;
                while (true) {
                    int i15 = i6 + 1;
                    int i16 = i12 + 1;
                    if ((bArr2[i6] & 255) != iArr[i12]) {
                        return i8;
                    }
                    if (i16 == i14) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (i15 == i7) {
                        AbstractC0150d.b(pVar3);
                        p pVar4 = pVar3.f441f;
                        AbstractC0150d.b(pVar4);
                        i4 = pVar4.f437b;
                        int i17 = pVar4.f438c;
                        bArr = pVar4.f436a;
                        if (pVar4 == pVar2) {
                            if (!z3) {
                                break loop0;
                            }
                            i7 = i17;
                            pVar = null;
                        } else {
                            pVar = pVar4;
                            i7 = i17;
                        }
                    } else {
                        pVar = pVar3;
                        bArr = bArr2;
                        i4 = i15;
                    }
                    if (z3) {
                        i2 = iArr[i16];
                        i3 = i4;
                        bArr2 = bArr;
                        pVar3 = pVar;
                        break;
                    }
                    i6 = i4;
                    bArr2 = bArr;
                    pVar3 = pVar;
                    i12 = i16;
                }
            } else {
                int i18 = i6 + 1;
                int i19 = bArr2[i6] & 255;
                int i20 = i12 + i11;
                while (i12 != i20) {
                    if (i19 == iArr[i12]) {
                        i2 = iArr[i12 + i11];
                        if (i18 == i7) {
                            pVar3 = pVar3.f441f;
                            AbstractC0150d.b(pVar3);
                            i3 = pVar3.f437b;
                            i7 = pVar3.f438c;
                            bArr2 = pVar3.f436a;
                            if (pVar3 == pVar2) {
                                pVar3 = null;
                            }
                        } else {
                            i3 = i18;
                        }
                    } else {
                        i12++;
                    }
                }
                return i8;
            }
            if (i2 >= 0) {
                return i2;
            }
            i9 = -i2;
            i6 = i3;
            i5 = -2;
        }
        if (z2) {
            return i5;
        }
        return i8;
    }
}

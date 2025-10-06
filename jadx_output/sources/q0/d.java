package q0;

import b0.AbstractC0084j;
import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public abstract class d extends k {
    public static boolean C(CharSequence charSequence, char c2) {
        AbstractC0150d.e(charSequence, "<this>");
        if (G(charSequence, c2, 0, 2) < 0) {
            return false;
        }
        return true;
    }

    public static boolean D(CharSequence charSequence, String str) {
        AbstractC0150d.e(charSequence, "<this>");
        if (H(charSequence, str, 0, 2) < 0) {
            return false;
        }
        return true;
    }

    public static final int E(CharSequence charSequence) {
        AbstractC0150d.e(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    /* JADX WARN: Incorrect condition in loop: B:20:0x004c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int F(java.lang.CharSequence r9, java.lang.String r10, int r11, boolean r12) {
        /*
            java.lang.String r0 = "<this>"
            j0.AbstractC0150d.e(r9, r0)
            java.lang.String r0 = "string"
            j0.AbstractC0150d.e(r10, r0)
            if (r12 != 0) goto L19
            boolean r0 = r9 instanceof java.lang.String
            if (r0 != 0) goto L11
            goto L19
        L11:
            java.lang.String r9 = (java.lang.String) r9
            int r9 = r9.indexOf(r10, r11)
            goto L71
        L19:
            int r0 = r9.length()
            n0.c r1 = new n0.c
            if (r11 >= 0) goto L22
            r11 = 0
        L22:
            int r2 = r9.length()
            if (r0 <= r2) goto L29
            r0 = r2
        L29:
            r2 = 1
            r1.<init>(r11, r0, r2)
            boolean r0 = r9 instanceof java.lang.String
            int r2 = r1.f2133c
            int r1 = r1.f2132b
            if (r0 == 0) goto L54
            if (r2 <= 0) goto L39
            if (r11 <= r1) goto L3d
        L39:
            if (r2 >= 0) goto L70
            if (r1 > r11) goto L70
        L3d:
            r7 = r9
            java.lang.String r7 = (java.lang.String) r7
            int r5 = r10.length()
            r3 = 0
            r4 = r11
            r6 = r10
            r8 = r12
            boolean r0 = q0.k.y(r3, r4, r5, r6, r7, r8)
            if (r0 == 0) goto L50
        L4e:
            r9 = r11
            goto L71
        L50:
            if (r11 == r1) goto L70
            int r11 = r11 + r2
            goto L3d
        L54:
            if (r2 <= 0) goto L58
            if (r11 <= r1) goto L5c
        L58:
            if (r2 >= 0) goto L70
            if (r1 > r11) goto L70
        L5c:
            int r7 = r10.length()
            r4 = 0
            r3 = r10
            r5 = r9
            r6 = r11
            r8 = r12
            boolean r0 = K(r3, r4, r5, r6, r7, r8)
            if (r0 == 0) goto L6c
            goto L4e
        L6c:
            if (r11 == r1) goto L70
            int r11 = r11 + r2
            goto L5c
        L70:
            r9 = -1
        L71:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: q0.d.F(java.lang.CharSequence, java.lang.String, int, boolean):int");
    }

    public static int G(CharSequence charSequence, char c2, int i2, int i3) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        AbstractC0150d.e(charSequence, "<this>");
        if (!(charSequence instanceof String)) {
            return I(charSequence, new char[]{c2}, i2, false);
        }
        return ((String) charSequence).indexOf(c2, i2);
    }

    public static /* synthetic */ int H(CharSequence charSequence, String str, int i2, int i3) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return F(charSequence, str, i2, false);
    }

    public static final int I(CharSequence charSequence, char[] cArr, int i2, boolean z2) {
        AbstractC0150d.e(charSequence, "<this>");
        if (!z2 && cArr.length == 1 && (charSequence instanceof String)) {
            int length = cArr.length;
            if (length != 0) {
                if (length == 1) {
                    return ((String) charSequence).indexOf(cArr[0], i2);
                }
                throw new IllegalArgumentException("Array has more than one element.");
            }
            throw new NoSuchElementException("Array is empty.");
        }
        if (i2 < 0) {
            i2 = 0;
        }
        int E2 = E(charSequence);
        if (i2 > E2) {
            return -1;
        }
        while (true) {
            char charAt = charSequence.charAt(i2);
            for (char c2 : cArr) {
                if (C0.f.n(c2, charAt, z2)) {
                    return i2;
                }
            }
            if (i2 != E2) {
                i2++;
            } else {
                return -1;
            }
        }
    }

    public static int J(String str, char c2, int i2, int i3) {
        if ((i3 & 2) != 0) {
            i2 = E(str);
        }
        AbstractC0150d.e(str, "<this>");
        return str.lastIndexOf(c2, i2);
    }

    public static final boolean K(String str, int i2, CharSequence charSequence, int i3, int i4, boolean z2) {
        AbstractC0150d.e(str, "<this>");
        AbstractC0150d.e(charSequence, "other");
        if (i3 < 0 || i2 < 0 || i2 > str.length() - i4 || i3 > charSequence.length() - i4) {
            return false;
        }
        for (int i5 = 0; i5 < i4; i5++) {
            if (!C0.f.n(str.charAt(i2 + i5), charSequence.charAt(i3 + i5), z2)) {
                return false;
            }
        }
        return true;
    }

    public static String L(String str, String str2) {
        if (k.B(str, false, str2)) {
            String substring = str.substring(str2.length());
            AbstractC0150d.d(substring, "substring(...)");
            return substring;
        }
        return str;
    }

    public static List M(String str, char[] cArr) {
        AbstractC0150d.e(str, "<this>");
        if (cArr.length == 1) {
            String valueOf = String.valueOf(cArr[0]);
            int F2 = F(str, valueOf, 0, false);
            if (F2 != -1) {
                ArrayList arrayList = new ArrayList(10);
                int i2 = 0;
                do {
                    arrayList.add(str.subSequence(i2, F2).toString());
                    i2 = valueOf.length() + F2;
                    F2 = F(str, valueOf, i2, false);
                } while (F2 != -1);
                arrayList.add(str.subSequence(i2, str.length()).toString());
                return arrayList;
            }
            return C0.d.y(str.toString());
        }
        p0.f fVar = new p0.f(new c(str, new l(0, cArr)));
        ArrayList arrayList2 = new ArrayList(AbstractC0084j.I(fVar));
        Iterator it = fVar.iterator();
        while (true) {
            b bVar = (b) it;
            if (bVar.hasNext()) {
                n0.c cVar = (n0.c) bVar.next();
                AbstractC0150d.e(cVar, "range");
                arrayList2.add(str.subSequence(cVar.f2131a, cVar.f2132b + 1).toString());
            } else {
                return arrayList2;
            }
        }
    }

    public static String N(String str) {
        AbstractC0150d.e(str, "<this>");
        AbstractC0150d.e(str, "missingDelimiterValue");
        int J2 = J(str, '.', 0, 6);
        if (J2 != -1) {
            String substring = str.substring(J2 + 1, str.length());
            AbstractC0150d.d(substring, "substring(...)");
            return substring;
        }
        return str;
    }

    public static String O(String str, int i2) {
        if (i2 >= 0) {
            int length = str.length();
            if (i2 > length) {
                i2 = length;
            }
            String substring = str.substring(0, i2);
            AbstractC0150d.d(substring, "substring(...)");
            return substring;
        }
        throw new IllegalArgumentException(A.e.b("Requested character count ", i2, " is less than zero.").toString());
    }

    public static CharSequence P(String str) {
        int i2;
        boolean z2;
        AbstractC0150d.e(str, "<this>");
        int length = str.length() - 1;
        int i3 = 0;
        boolean z3 = false;
        while (i3 <= length) {
            if (!z3) {
                i2 = i3;
            } else {
                i2 = length;
            }
            char charAt = str.charAt(i2);
            if (!Character.isWhitespace(charAt) && !Character.isSpaceChar(charAt)) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (!z3) {
                if (!z2) {
                    z3 = true;
                } else {
                    i3++;
                }
            } else {
                if (!z2) {
                    break;
                }
                length--;
            }
        }
        return str.subSequence(i3, length + 1);
    }
}

package s;

import q.C0232c;

/* loaded from: classes.dex */
public abstract class g {

    /* renamed from: a, reason: collision with root package name */
    public static final boolean[] f2351a = new boolean[3];

    /* JADX WARN: Code restructure failed: missing block: B:157:0x0283, code lost:
    
        if (r2.f2258d == r7) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0109, code lost:
    
        if (r4.f2258d == r13) goto L73;
     */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0440 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:256:0x06af A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:262:0x06bb  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x06c8  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x06d1  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x06d8  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x06e8  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x06ec A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0707 A[ADDED_TO_REGION, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:282:0x06d4  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x06cb  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x06bd  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x04aa A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0584  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0586  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x0591 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:365:0x05a4  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x0674  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x069f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0113 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(s.e r36, q.C0232c r37, java.util.ArrayList r38, int r39) {
        /*
            Method dump skipped, instructions count: 1813
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: s.g.a(s.e, q.c, java.util.ArrayList, int):void");
    }

    public static void b(e eVar, C0232c c0232c, C0237d c0237d) {
        c0237d.f2314o = -1;
        c0237d.f2316p = -1;
        int i2 = eVar.f2315o0[0];
        int[] iArr = c0237d.f2315o0;
        if (i2 != 2 && iArr[0] == 4) {
            C0236c c0236c = c0237d.f2270H;
            int i3 = c0236c.f2261g;
            int o2 = eVar.o();
            C0236c c0236c2 = c0237d.f2272J;
            int i4 = o2 - c0236c2.f2261g;
            c0236c.f2262i = c0232c.k(c0236c);
            c0236c2.f2262i = c0232c.k(c0236c2);
            c0232c.d(c0236c.f2262i, i3);
            c0232c.d(c0236c2.f2262i, i4);
            c0237d.f2314o = 2;
            c0237d.f2286X = i3;
            int i5 = i4 - i3;
            c0237d.f2282T = i5;
            int i6 = c0237d.f2290a0;
            if (i5 < i6) {
                c0237d.f2282T = i6;
            }
        }
        if (eVar.f2315o0[1] != 2 && iArr[1] == 4) {
            C0236c c0236c3 = c0237d.f2271I;
            int i7 = c0236c3.f2261g;
            int i8 = eVar.i();
            C0236c c0236c4 = c0237d.f2273K;
            int i9 = i8 - c0236c4.f2261g;
            c0236c3.f2262i = c0232c.k(c0236c3);
            c0236c4.f2262i = c0232c.k(c0236c4);
            c0232c.d(c0236c3.f2262i, i7);
            c0232c.d(c0236c4.f2262i, i9);
            if (c0237d.f2288Z > 0 || c0237d.f2300f0 == 8) {
                C0236c c0236c5 = c0237d.f2274L;
                q.g k2 = c0232c.k(c0236c5);
                c0236c5.f2262i = k2;
                c0232c.d(k2, c0237d.f2288Z + i7);
            }
            c0237d.f2316p = 2;
            c0237d.f2287Y = i7;
            int i10 = i9 - i7;
            c0237d.f2283U = i10;
            int i11 = c0237d.f2292b0;
            if (i10 < i11) {
                c0237d.f2283U = i11;
            }
        }
    }

    public static final boolean c(int i2, int i3) {
        if ((i2 & i3) == i3) {
            return true;
        }
        return false;
    }
}

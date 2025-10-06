package z0;

import A.m;
import H0.h;
import j0.AbstractC0150d;
import u0.u;

/* loaded from: classes.dex */
public abstract class e {
    static {
        h hVar = h.f413d;
        m.e("\"\\");
        m.e("\t ,=");
    }

    public static final boolean a(u uVar) {
        if (AbstractC0150d.a((String) uVar.f2566a.f120d, "HEAD")) {
            return false;
        }
        int i2 = uVar.f2569d;
        if (((i2 >= 100 && i2 < 200) || i2 == 204 || i2 == 304) && v0.b.j(uVar) == -1 && !"chunked".equalsIgnoreCase(u.f("Transfer-Encoding", uVar))) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:134:0x0209, code lost:
    
        if (((java.util.regex.Pattern) r2.f1739b).matcher(r0).matches() == false) goto L113;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0259 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v48, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r26v0 */
    /* JADX WARN: Type inference failed for: r26v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r26v2 */
    /* JADX WARN: Type inference failed for: r26v3 */
    /* JADX WARN: Type inference failed for: r26v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void b(u0.b r36, u0.m r37, u0.k r38) {
        /*
            Method dump skipped, instructions count: 638
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.e.b(u0.b, u0.m, u0.k):void");
    }
}

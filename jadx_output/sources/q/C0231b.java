package q;

import D0.h;
import java.util.ArrayList;

/* renamed from: q.b, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0231b {

    /* renamed from: d, reason: collision with root package name */
    public final C0230a f2187d;

    /* renamed from: a, reason: collision with root package name */
    public g f2184a = null;

    /* renamed from: b, reason: collision with root package name */
    public float f2185b = 0.0f;

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f2186c = new ArrayList();

    /* renamed from: e, reason: collision with root package name */
    public boolean f2188e = false;

    public C0231b(h hVar) {
        this.f2187d = new C0230a(this, hVar);
    }

    public final void a(C0232c c0232c, int i2) {
        this.f2187d.g(c0232c.j(i2), 1.0f);
        this.f2187d.g(c0232c.j(i2), -1.0f);
    }

    public final void b(g gVar, g gVar2, g gVar3, int i2) {
        boolean z2 = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z2 = true;
            }
            this.f2185b = i2;
        }
        if (!z2) {
            this.f2187d.g(gVar, -1.0f);
            this.f2187d.g(gVar2, 1.0f);
            this.f2187d.g(gVar3, 1.0f);
        } else {
            this.f2187d.g(gVar, 1.0f);
            this.f2187d.g(gVar2, -1.0f);
            this.f2187d.g(gVar3, -1.0f);
        }
    }

    public final void c(g gVar, g gVar2, g gVar3, int i2) {
        boolean z2 = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z2 = true;
            }
            this.f2185b = i2;
        }
        if (!z2) {
            this.f2187d.g(gVar, -1.0f);
            this.f2187d.g(gVar2, 1.0f);
            this.f2187d.g(gVar3, -1.0f);
        } else {
            this.f2187d.g(gVar, 1.0f);
            this.f2187d.g(gVar2, -1.0f);
            this.f2187d.g(gVar3, 1.0f);
        }
    }

    public g d(boolean[] zArr) {
        return f(zArr, null);
    }

    public boolean e() {
        if (this.f2184a == null && this.f2185b == 0.0f && this.f2187d.d() == 0) {
            return true;
        }
        return false;
    }

    public final g f(boolean[] zArr, g gVar) {
        int i2;
        int d2 = this.f2187d.d();
        g gVar2 = null;
        float f2 = 0.0f;
        for (int i3 = 0; i3 < d2; i3++) {
            float f3 = this.f2187d.f(i3);
            if (f3 < 0.0f) {
                g e2 = this.f2187d.e(i3);
                if ((zArr == null || !zArr[e2.f2209b]) && e2 != gVar && (((i2 = e2.f2218l) == 3 || i2 == 4) && f3 < f2)) {
                    f2 = f3;
                    gVar2 = e2;
                }
            }
        }
        return gVar2;
    }

    public final void g(g gVar) {
        g gVar2 = this.f2184a;
        if (gVar2 != null) {
            this.f2187d.g(gVar2, -1.0f);
            this.f2184a.f2210c = -1;
            this.f2184a = null;
        }
        float h = this.f2187d.h(gVar, true) * (-1.0f);
        this.f2184a = gVar;
        if (h == 1.0f) {
            return;
        }
        this.f2185b /= h;
        C0230a c0230a = this.f2187d;
        int i2 = c0230a.h;
        for (int i3 = 0; i2 != -1 && i3 < c0230a.f2175a; i3++) {
            float[] fArr = c0230a.f2181g;
            fArr[i2] = fArr[i2] / h;
            i2 = c0230a.f2180f[i2];
        }
    }

    public final void h(C0232c c0232c, g gVar, boolean z2) {
        if (gVar != null && gVar.f2213f) {
            float c2 = this.f2187d.c(gVar);
            this.f2185b = (gVar.f2212e * c2) + this.f2185b;
            this.f2187d.h(gVar, z2);
            if (z2) {
                gVar.b(this);
            }
            if (this.f2187d.d() == 0) {
                this.f2188e = true;
                c0232c.f2191a = true;
            }
        }
    }

    public void i(C0232c c0232c, C0231b c0231b, boolean z2) {
        C0230a c0230a = this.f2187d;
        c0230a.getClass();
        float c2 = c0230a.c(c0231b.f2184a);
        c0230a.h(c0231b.f2184a, z2);
        C0230a c0230a2 = c0231b.f2187d;
        int d2 = c0230a2.d();
        for (int i2 = 0; i2 < d2; i2++) {
            g e2 = c0230a2.e(i2);
            c0230a.a(e2, c0230a2.c(e2) * c2, z2);
        }
        this.f2185b = (c0231b.f2185b * c2) + this.f2185b;
        if (z2) {
            c0231b.f2184a.b(this);
        }
        if (this.f2184a != null && this.f2187d.d() == 0) {
            this.f2188e = true;
            c0232c.f2191a = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r10 = this;
            q.g r0 = r10.f2184a
            if (r0 != 0) goto L7
            java.lang.String r0 = "0"
            goto L17
        L7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = ""
            r0.<init>(r1)
            q.g r1 = r10.f2184a
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L17:
            java.lang.String r1 = " = "
            java.lang.String r0 = A.e.c(r0, r1)
            float r1 = r10.f2185b
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L39
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            float r0 = r10.f2185b
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r1 = r3
            goto L3a
        L39:
            r1 = r4
        L3a:
            q.a r5 = r10.f2187d
            int r5 = r5.d()
        L40:
            if (r4 >= r5) goto La0
            q.a r6 = r10.f2187d
            q.g r6 = r6.e(r4)
            if (r6 != 0) goto L4b
            goto L9d
        L4b:
            q.a r7 = r10.f2187d
            float r7 = r7.f(r4)
            int r8 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r8 != 0) goto L56
            goto L9d
        L56:
            java.lang.String r6 = r6.toString()
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r1 != 0) goto L6a
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 >= 0) goto L7a
            java.lang.String r1 = "- "
            java.lang.String r0 = A.e.c(r0, r1)
        L68:
            float r7 = r7 * r9
            goto L7a
        L6a:
            if (r8 <= 0) goto L73
            java.lang.String r1 = " + "
            java.lang.String r0 = A.e.c(r0, r1)
            goto L7a
        L73:
            java.lang.String r1 = " - "
            java.lang.String r0 = A.e.c(r0, r1)
            goto L68
        L7a:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 != 0) goto L85
            java.lang.String r0 = A.e.c(r0, r6)
            goto L9c
        L85:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r7)
            java.lang.String r0 = " "
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
        L9c:
            r1 = r3
        L9d:
            int r4 = r4 + 1
            goto L40
        La0:
            if (r1 != 0) goto La8
            java.lang.String r1 = "0.0"
            java.lang.String r0 = A.e.c(r0, r1)
        La8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: q.C0231b.toString():java.lang.String");
    }
}

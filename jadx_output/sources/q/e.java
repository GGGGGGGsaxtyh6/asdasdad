package q;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class e extends C0231b {

    /* renamed from: f, reason: collision with root package name */
    public g[] f2205f;

    /* renamed from: g, reason: collision with root package name */
    public g[] f2206g;
    public int h;

    /* renamed from: i, reason: collision with root package name */
    public E.c f2207i;

    @Override // q.C0231b
    public final g d(boolean[] zArr) {
        int i2 = -1;
        for (int i3 = 0; i3 < this.h; i3++) {
            g[] gVarArr = this.f2205f;
            g gVar = gVarArr[i3];
            if (!zArr[gVar.f2209b]) {
                E.c cVar = this.f2207i;
                cVar.f271b = gVar;
                int i4 = 8;
                if (i2 == -1) {
                    while (i4 >= 0) {
                        float f2 = ((g) cVar.f271b).h[i4];
                        if (f2 <= 0.0f) {
                            if (f2 < 0.0f) {
                                i2 = i3;
                                break;
                            }
                            i4--;
                        }
                    }
                } else {
                    g gVar2 = gVarArr[i2];
                    while (true) {
                        if (i4 >= 0) {
                            float f3 = gVar2.h[i4];
                            float f4 = ((g) cVar.f271b).h[i4];
                            if (f4 == f3) {
                                i4--;
                            } else if (f4 >= f3) {
                            }
                        }
                    }
                }
            }
        }
        if (i2 == -1) {
            return null;
        }
        return this.f2205f[i2];
    }

    @Override // q.C0231b
    public final boolean e() {
        if (this.h == 0) {
            return true;
        }
        return false;
    }

    @Override // q.C0231b
    public final void i(C0232c c0232c, C0231b c0231b, boolean z2) {
        g gVar = c0231b.f2184a;
        if (gVar == null) {
            return;
        }
        C0230a c0230a = c0231b.f2187d;
        int d2 = c0230a.d();
        for (int i2 = 0; i2 < d2; i2++) {
            g e2 = c0230a.e(i2);
            float f2 = c0230a.f(i2);
            E.c cVar = this.f2207i;
            cVar.f271b = e2;
            boolean z3 = e2.f2208a;
            float[] fArr = gVar.h;
            if (z3) {
                boolean z4 = true;
                for (int i3 = 0; i3 < 9; i3++) {
                    float[] fArr2 = ((g) cVar.f271b).h;
                    float f3 = (fArr[i3] * f2) + fArr2[i3];
                    fArr2[i3] = f3;
                    if (Math.abs(f3) < 1.0E-4f) {
                        ((g) cVar.f271b).h[i3] = 0.0f;
                    } else {
                        z4 = false;
                    }
                }
                if (z4) {
                    ((e) cVar.f272c).k((g) cVar.f271b);
                }
            } else {
                for (int i4 = 0; i4 < 9; i4++) {
                    float f4 = fArr[i4];
                    if (f4 != 0.0f) {
                        float f5 = f4 * f2;
                        if (Math.abs(f5) < 1.0E-4f) {
                            f5 = 0.0f;
                        }
                        ((g) cVar.f271b).h[i4] = f5;
                    } else {
                        ((g) cVar.f271b).h[i4] = 0.0f;
                    }
                }
                j(e2);
            }
            this.f2185b = (c0231b.f2185b * f2) + this.f2185b;
        }
        k(gVar);
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.Object, java.util.Comparator] */
    public final void j(g gVar) {
        int i2;
        int i3 = this.h + 1;
        g[] gVarArr = this.f2205f;
        if (i3 > gVarArr.length) {
            g[] gVarArr2 = (g[]) Arrays.copyOf(gVarArr, gVarArr.length * 2);
            this.f2205f = gVarArr2;
            this.f2206g = (g[]) Arrays.copyOf(gVarArr2, gVarArr2.length * 2);
        }
        g[] gVarArr3 = this.f2205f;
        int i4 = this.h;
        gVarArr3[i4] = gVar;
        int i5 = i4 + 1;
        this.h = i5;
        if (i5 > 1 && gVarArr3[i4].f2209b > gVar.f2209b) {
            int i6 = 0;
            while (true) {
                i2 = this.h;
                if (i6 >= i2) {
                    break;
                }
                this.f2206g[i6] = this.f2205f[i6];
                i6++;
            }
            Arrays.sort(this.f2206g, 0, i2, new Object());
            for (int i7 = 0; i7 < this.h; i7++) {
                this.f2205f[i7] = this.f2206g[i7];
            }
        }
        gVar.f2208a = true;
        gVar.a(this);
    }

    public final void k(g gVar) {
        int i2 = 0;
        while (i2 < this.h) {
            if (this.f2205f[i2] == gVar) {
                while (true) {
                    int i3 = this.h;
                    if (i2 < i3 - 1) {
                        g[] gVarArr = this.f2205f;
                        int i4 = i2 + 1;
                        gVarArr[i2] = gVarArr[i4];
                        i2 = i4;
                    } else {
                        this.h = i3 - 1;
                        gVar.f2208a = false;
                        return;
                    }
                }
            } else {
                i2++;
            }
        }
    }

    @Override // q.C0231b
    public final String toString() {
        String str = " goal -> (" + this.f2185b + ") : ";
        for (int i2 = 0; i2 < this.h; i2++) {
            g gVar = this.f2205f[i2];
            E.c cVar = this.f2207i;
            cVar.f271b = gVar;
            str = str + cVar + " ";
        }
        return str;
    }
}

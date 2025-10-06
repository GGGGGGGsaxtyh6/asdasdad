package q;

import D0.h;
import java.util.Arrays;
import org.conscrypt.BuildConfig;

/* renamed from: q.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0230a {

    /* renamed from: b, reason: collision with root package name */
    public final C0231b f2176b;

    /* renamed from: c, reason: collision with root package name */
    public final h f2177c;

    /* renamed from: a, reason: collision with root package name */
    public int f2175a = 0;

    /* renamed from: d, reason: collision with root package name */
    public int f2178d = 8;

    /* renamed from: e, reason: collision with root package name */
    public int[] f2179e = new int[8];

    /* renamed from: f, reason: collision with root package name */
    public int[] f2180f = new int[8];

    /* renamed from: g, reason: collision with root package name */
    public float[] f2181g = new float[8];
    public int h = -1;

    /* renamed from: i, reason: collision with root package name */
    public int f2182i = -1;

    /* renamed from: j, reason: collision with root package name */
    public boolean f2183j = false;

    public C0230a(C0231b c0231b, h hVar) {
        this.f2176b = c0231b;
        this.f2177c = hVar;
    }

    public final void a(g gVar, float f2, boolean z2) {
        if (f2 > -0.001f && f2 < 0.001f) {
            return;
        }
        int i2 = this.h;
        C0231b c0231b = this.f2176b;
        if (i2 == -1) {
            this.h = 0;
            this.f2181g[0] = f2;
            this.f2179e[0] = gVar.f2209b;
            this.f2180f[0] = -1;
            gVar.f2217k++;
            gVar.a(c0231b);
            this.f2175a++;
            if (!this.f2183j) {
                int i3 = this.f2182i + 1;
                this.f2182i = i3;
                int[] iArr = this.f2179e;
                if (i3 >= iArr.length) {
                    this.f2183j = true;
                    this.f2182i = iArr.length - 1;
                    return;
                }
                return;
            }
            return;
        }
        int i4 = -1;
        for (int i5 = 0; i2 != -1 && i5 < this.f2175a; i5++) {
            int i6 = this.f2179e[i2];
            int i7 = gVar.f2209b;
            if (i6 == i7) {
                float[] fArr = this.f2181g;
                float f3 = fArr[i2] + f2;
                if (f3 > -0.001f && f3 < 0.001f) {
                    f3 = 0.0f;
                }
                fArr[i2] = f3;
                if (f3 == 0.0f) {
                    if (i2 == this.h) {
                        this.h = this.f2180f[i2];
                    } else {
                        int[] iArr2 = this.f2180f;
                        iArr2[i4] = iArr2[i2];
                    }
                    if (z2) {
                        gVar.b(c0231b);
                    }
                    if (this.f2183j) {
                        this.f2182i = i2;
                    }
                    gVar.f2217k--;
                    this.f2175a--;
                    return;
                }
                return;
            }
            if (i6 < i7) {
                i4 = i2;
            }
            i2 = this.f2180f[i2];
        }
        int i8 = this.f2182i;
        int i9 = i8 + 1;
        if (this.f2183j) {
            int[] iArr3 = this.f2179e;
            if (iArr3[i8] != -1) {
                i8 = iArr3.length;
            }
        } else {
            i8 = i9;
        }
        int[] iArr4 = this.f2179e;
        if (i8 >= iArr4.length && this.f2175a < iArr4.length) {
            int i10 = 0;
            while (true) {
                int[] iArr5 = this.f2179e;
                if (i10 >= iArr5.length) {
                    break;
                }
                if (iArr5[i10] == -1) {
                    i8 = i10;
                    break;
                }
                i10++;
            }
        }
        int[] iArr6 = this.f2179e;
        if (i8 >= iArr6.length) {
            i8 = iArr6.length;
            int i11 = this.f2178d * 2;
            this.f2178d = i11;
            this.f2183j = false;
            this.f2182i = i8 - 1;
            this.f2181g = Arrays.copyOf(this.f2181g, i11);
            this.f2179e = Arrays.copyOf(this.f2179e, this.f2178d);
            this.f2180f = Arrays.copyOf(this.f2180f, this.f2178d);
        }
        this.f2179e[i8] = gVar.f2209b;
        this.f2181g[i8] = f2;
        if (i4 != -1) {
            int[] iArr7 = this.f2180f;
            iArr7[i8] = iArr7[i4];
            iArr7[i4] = i8;
        } else {
            this.f2180f[i8] = this.h;
            this.h = i8;
        }
        gVar.f2217k++;
        gVar.a(c0231b);
        this.f2175a++;
        if (!this.f2183j) {
            this.f2182i++;
        }
        int i12 = this.f2182i;
        int[] iArr8 = this.f2179e;
        if (i12 >= iArr8.length) {
            this.f2183j = true;
            this.f2182i = iArr8.length - 1;
        }
    }

    public final void b() {
        int i2 = this.h;
        for (int i3 = 0; i2 != -1 && i3 < this.f2175a; i3++) {
            g gVar = ((g[]) this.f2177c.f260d)[this.f2179e[i2]];
            if (gVar != null) {
                gVar.b(this.f2176b);
            }
            i2 = this.f2180f[i2];
        }
        this.h = -1;
        this.f2182i = -1;
        this.f2183j = false;
        this.f2175a = 0;
    }

    public final float c(g gVar) {
        int i2 = this.h;
        for (int i3 = 0; i2 != -1 && i3 < this.f2175a; i3++) {
            if (this.f2179e[i2] == gVar.f2209b) {
                return this.f2181g[i2];
            }
            i2 = this.f2180f[i2];
        }
        return 0.0f;
    }

    public final int d() {
        return this.f2175a;
    }

    public final g e(int i2) {
        int i3 = this.h;
        for (int i4 = 0; i3 != -1 && i4 < this.f2175a; i4++) {
            if (i4 == i2) {
                return ((g[]) this.f2177c.f260d)[this.f2179e[i3]];
            }
            i3 = this.f2180f[i3];
        }
        return null;
    }

    public final float f(int i2) {
        int i3 = this.h;
        for (int i4 = 0; i3 != -1 && i4 < this.f2175a; i4++) {
            if (i4 == i2) {
                return this.f2181g[i3];
            }
            i3 = this.f2180f[i3];
        }
        return 0.0f;
    }

    public final void g(g gVar, float f2) {
        if (f2 == 0.0f) {
            h(gVar, true);
            return;
        }
        int i2 = this.h;
        C0231b c0231b = this.f2176b;
        if (i2 == -1) {
            this.h = 0;
            this.f2181g[0] = f2;
            this.f2179e[0] = gVar.f2209b;
            this.f2180f[0] = -1;
            gVar.f2217k++;
            gVar.a(c0231b);
            this.f2175a++;
            if (!this.f2183j) {
                int i3 = this.f2182i + 1;
                this.f2182i = i3;
                int[] iArr = this.f2179e;
                if (i3 >= iArr.length) {
                    this.f2183j = true;
                    this.f2182i = iArr.length - 1;
                    return;
                }
                return;
            }
            return;
        }
        int i4 = -1;
        for (int i5 = 0; i2 != -1 && i5 < this.f2175a; i5++) {
            int i6 = this.f2179e[i2];
            int i7 = gVar.f2209b;
            if (i6 == i7) {
                this.f2181g[i2] = f2;
                return;
            }
            if (i6 < i7) {
                i4 = i2;
            }
            i2 = this.f2180f[i2];
        }
        int i8 = this.f2182i;
        int i9 = i8 + 1;
        if (this.f2183j) {
            int[] iArr2 = this.f2179e;
            if (iArr2[i8] != -1) {
                i8 = iArr2.length;
            }
        } else {
            i8 = i9;
        }
        int[] iArr3 = this.f2179e;
        if (i8 >= iArr3.length && this.f2175a < iArr3.length) {
            int i10 = 0;
            while (true) {
                int[] iArr4 = this.f2179e;
                if (i10 >= iArr4.length) {
                    break;
                }
                if (iArr4[i10] == -1) {
                    i8 = i10;
                    break;
                }
                i10++;
            }
        }
        int[] iArr5 = this.f2179e;
        if (i8 >= iArr5.length) {
            i8 = iArr5.length;
            int i11 = this.f2178d * 2;
            this.f2178d = i11;
            this.f2183j = false;
            this.f2182i = i8 - 1;
            this.f2181g = Arrays.copyOf(this.f2181g, i11);
            this.f2179e = Arrays.copyOf(this.f2179e, this.f2178d);
            this.f2180f = Arrays.copyOf(this.f2180f, this.f2178d);
        }
        this.f2179e[i8] = gVar.f2209b;
        this.f2181g[i8] = f2;
        if (i4 != -1) {
            int[] iArr6 = this.f2180f;
            iArr6[i8] = iArr6[i4];
            iArr6[i4] = i8;
        } else {
            this.f2180f[i8] = this.h;
            this.h = i8;
        }
        gVar.f2217k++;
        gVar.a(c0231b);
        int i12 = this.f2175a + 1;
        this.f2175a = i12;
        if (!this.f2183j) {
            this.f2182i++;
        }
        int[] iArr7 = this.f2179e;
        if (i12 >= iArr7.length) {
            this.f2183j = true;
        }
        if (this.f2182i >= iArr7.length) {
            this.f2183j = true;
            this.f2182i = iArr7.length - 1;
        }
    }

    public final float h(g gVar, boolean z2) {
        int i2 = this.h;
        if (i2 == -1) {
            return 0.0f;
        }
        int i3 = 0;
        int i4 = -1;
        while (i2 != -1 && i3 < this.f2175a) {
            if (this.f2179e[i2] == gVar.f2209b) {
                if (i2 == this.h) {
                    this.h = this.f2180f[i2];
                } else {
                    int[] iArr = this.f2180f;
                    iArr[i4] = iArr[i2];
                }
                if (z2) {
                    gVar.b(this.f2176b);
                }
                gVar.f2217k--;
                this.f2175a--;
                this.f2179e[i2] = -1;
                if (this.f2183j) {
                    this.f2182i = i2;
                }
                return this.f2181g[i2];
            }
            i3++;
            i4 = i2;
            i2 = this.f2180f[i2];
        }
        return 0.0f;
    }

    public final String toString() {
        int i2 = this.h;
        String str = BuildConfig.FLAVOR;
        for (int i3 = 0; i2 != -1 && i3 < this.f2175a; i3++) {
            str = (A.e.c(str, " -> ") + this.f2181g[i2] + " : ") + ((g[]) this.f2177c.f260d)[this.f2179e[i2]];
            i2 = this.f2180f[i2];
        }
        return str;
    }
}

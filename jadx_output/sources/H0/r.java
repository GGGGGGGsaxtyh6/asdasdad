package H0;

import b0.AbstractC0081g;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public final class r extends h {

    /* renamed from: e, reason: collision with root package name */
    public final transient byte[][] f446e;

    /* renamed from: f, reason: collision with root package name */
    public final transient int[] f447f;

    public r(byte[][] bArr, int[] iArr) {
        super(h.f413d.f414a);
        this.f446e = bArr;
        this.f447f = iArr;
    }

    @Override // H0.h
    public final int a() {
        return this.f447f[this.f446e.length - 1];
    }

    @Override // H0.h
    public final String b() {
        return new h(j()).b();
    }

    @Override // H0.h
    public final byte[] c() {
        return j();
    }

    @Override // H0.h
    public final byte d(int i2) {
        int i3;
        byte[][] bArr = this.f446e;
        int length = bArr.length - 1;
        int[] iArr = this.f447f;
        C0.f.e(iArr[length], i2, 1L);
        int b2 = I0.b.b(this, i2);
        if (b2 == 0) {
            i3 = 0;
        } else {
            i3 = iArr[b2 - 1];
        }
        return bArr[b2][(i2 - i3) + iArr[bArr.length + b2]];
    }

    @Override // H0.h
    public final boolean e(int i2, byte[] bArr, int i3, int i4) {
        int i5;
        AbstractC0150d.e(bArr, "other");
        if (i2 < 0 || i2 > a() - i4 || i3 < 0 || i3 > bArr.length - i4) {
            return false;
        }
        int i6 = i4 + i2;
        int b2 = I0.b.b(this, i2);
        while (i2 < i6) {
            int[] iArr = this.f447f;
            if (b2 == 0) {
                i5 = 0;
            } else {
                i5 = iArr[b2 - 1];
            }
            int i7 = iArr[b2] - i5;
            byte[][] bArr2 = this.f446e;
            int i8 = iArr[bArr2.length + b2];
            int min = Math.min(i6, i7 + i5) - i2;
            if (!C0.f.a(bArr2[b2], (i2 - i5) + i8, i3, bArr, min)) {
                return false;
            }
            i3 += min;
            i2 += min;
            b2++;
        }
        return true;
    }

    @Override // H0.h
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof h) {
            h hVar = (h) obj;
            if (hVar.a() == a() && f(hVar, a())) {
                return true;
            }
        }
        return false;
    }

    @Override // H0.h
    public final boolean f(h hVar, int i2) {
        int i3;
        AbstractC0150d.e(hVar, "other");
        if (a() - i2 < 0) {
            return false;
        }
        int b2 = I0.b.b(this, 0);
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            int[] iArr = this.f447f;
            if (b2 == 0) {
                i3 = 0;
            } else {
                i3 = iArr[b2 - 1];
            }
            int i6 = iArr[b2] - i3;
            byte[][] bArr = this.f446e;
            int i7 = iArr[bArr.length + b2];
            int min = Math.min(i2, i6 + i3) - i4;
            if (!hVar.e(i5, bArr[b2], (i4 - i3) + i7, min)) {
                return false;
            }
            i5 += min;
            i4 += min;
            b2++;
        }
        return true;
    }

    @Override // H0.h
    public final h g() {
        return new h(j()).g();
    }

    @Override // H0.h
    public final int hashCode() {
        int i2 = this.f415b;
        if (i2 == 0) {
            byte[][] bArr = this.f446e;
            int length = bArr.length;
            int i3 = 0;
            int i4 = 1;
            int i5 = 0;
            while (i3 < length) {
                int[] iArr = this.f447f;
                int i6 = iArr[length + i3];
                int i7 = iArr[i3];
                byte[] bArr2 = bArr[i3];
                int i8 = (i7 - i5) + i6;
                while (i6 < i8) {
                    i4 = (i4 * 31) + bArr2[i6];
                    i6++;
                }
                i3++;
                i5 = i7;
            }
            this.f415b = i4;
            return i4;
        }
        return i2;
    }

    @Override // H0.h
    public final void i(e eVar, int i2) {
        int i3;
        AbstractC0150d.e(eVar, "buffer");
        int b2 = I0.b.b(this, 0);
        int i4 = 0;
        while (i4 < i2) {
            int[] iArr = this.f447f;
            if (b2 == 0) {
                i3 = 0;
            } else {
                i3 = iArr[b2 - 1];
            }
            int i5 = iArr[b2] - i3;
            byte[][] bArr = this.f446e;
            int i6 = iArr[bArr.length + b2];
            int min = Math.min(i2, i5 + i3) - i4;
            int i7 = (i4 - i3) + i6;
            p pVar = new p(bArr[b2], i7, i7 + min, true);
            p pVar2 = eVar.f411a;
            if (pVar2 == null) {
                pVar.f442g = pVar;
                pVar.f441f = pVar;
                eVar.f411a = pVar;
            } else {
                p pVar3 = pVar2.f442g;
                AbstractC0150d.b(pVar3);
                pVar3.b(pVar);
            }
            i4 += min;
            b2++;
        }
        eVar.f412b += i2;
    }

    public final byte[] j() {
        byte[] bArr = new byte[a()];
        byte[][] bArr2 = this.f446e;
        int length = bArr2.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            int[] iArr = this.f447f;
            int i5 = iArr[length + i2];
            int i6 = iArr[i2];
            int i7 = i6 - i3;
            AbstractC0081g.x(bArr2[i2], i4, i5, bArr, i5 + i7);
            i4 += i7;
            i2++;
            i3 = i6;
        }
        return bArr;
    }

    @Override // H0.h
    public final String toString() {
        return new h(j()).toString();
    }
}

package B0;

import b0.AbstractC0081g;
import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.Arrays;

/* renamed from: B0.e, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0004e {

    /* renamed from: a, reason: collision with root package name */
    public final H0.e f104a;

    /* renamed from: c, reason: collision with root package name */
    public boolean f106c;

    /* renamed from: g, reason: collision with root package name */
    public int f110g;
    public int h;

    /* renamed from: b, reason: collision with root package name */
    public int f105b = Integer.MAX_VALUE;

    /* renamed from: d, reason: collision with root package name */
    public int f107d = 4096;

    /* renamed from: e, reason: collision with root package name */
    public C0002c[] f108e = new C0002c[8];

    /* renamed from: f, reason: collision with root package name */
    public int f109f = 7;

    public C0004e(H0.e eVar) {
        this.f104a = eVar;
    }

    public final void a(int i2) {
        int i3;
        if (i2 > 0) {
            int length = this.f108e.length - 1;
            int i4 = 0;
            while (true) {
                i3 = this.f109f;
                if (length < i3 || i2 <= 0) {
                    break;
                }
                C0002c c0002c = this.f108e[length];
                AbstractC0150d.b(c0002c);
                i2 -= c0002c.f96c;
                int i5 = this.h;
                C0002c c0002c2 = this.f108e[length];
                AbstractC0150d.b(c0002c2);
                this.h = i5 - c0002c2.f96c;
                this.f110g--;
                i4++;
                length--;
            }
            C0002c[] c0002cArr = this.f108e;
            int i6 = i3 + 1;
            System.arraycopy(c0002cArr, i6, c0002cArr, i6 + i4, this.f110g);
            C0002c[] c0002cArr2 = this.f108e;
            int i7 = this.f109f + 1;
            Arrays.fill(c0002cArr2, i7, i7 + i4, (Object) null);
            this.f109f += i4;
        }
    }

    public final void b(C0002c c0002c) {
        int i2 = this.f107d;
        int i3 = c0002c.f96c;
        if (i3 > i2) {
            C0002c[] c0002cArr = this.f108e;
            AbstractC0081g.A(c0002cArr, 0, c0002cArr.length);
            this.f109f = this.f108e.length - 1;
            this.f110g = 0;
            this.h = 0;
            return;
        }
        a((this.h + i3) - i2);
        int i4 = this.f110g + 1;
        C0002c[] c0002cArr2 = this.f108e;
        if (i4 > c0002cArr2.length) {
            C0002c[] c0002cArr3 = new C0002c[c0002cArr2.length * 2];
            System.arraycopy(c0002cArr2, 0, c0002cArr3, c0002cArr2.length, c0002cArr2.length);
            this.f109f = this.f108e.length - 1;
            this.f108e = c0002cArr3;
        }
        int i5 = this.f109f;
        this.f109f = i5 - 1;
        this.f108e[i5] = c0002c;
        this.f110g++;
        this.h += i3;
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object, H0.e] */
    public final void c(H0.h hVar) {
        AbstractC0150d.e(hVar, "data");
        H0.e eVar = this.f104a;
        int[] iArr = D.f75a;
        int a2 = hVar.a();
        int i2 = 0;
        int i3 = 0;
        long j2 = 0;
        while (i3 < a2) {
            int i4 = i3 + 1;
            byte d2 = hVar.d(i3);
            byte[] bArr = v0.b.f2808a;
            j2 += D.f76b[d2 & 255];
            i3 = i4;
        }
        if (((int) ((j2 + 7) >> 3)) < hVar.a()) {
            ?? obj = new Object();
            int[] iArr2 = D.f75a;
            int a3 = hVar.a();
            long j3 = 0;
            int i5 = 0;
            while (i2 < a3) {
                int i6 = i2 + 1;
                byte d3 = hVar.d(i2);
                byte[] bArr2 = v0.b.f2808a;
                int i7 = d3 & 255;
                int i8 = D.f75a[i7];
                byte b2 = D.f76b[i7];
                j3 = (j3 << b2) | i8;
                i5 += b2;
                while (i5 >= 8) {
                    i5 -= 8;
                    obj.u((int) (j3 >> i5));
                }
                i2 = i6;
            }
            if (i5 > 0) {
                obj.u((int) ((255 >>> i5) | (j3 << (8 - i5))));
            }
            H0.h k2 = obj.k(obj.f412b);
            e(k2.a(), 127, 128);
            eVar.r(k2);
            return;
        }
        e(hVar.a(), 127, 0);
        eVar.r(hVar);
    }

    public final void d(ArrayList arrayList) {
        int i2;
        int i3;
        if (this.f106c) {
            int i4 = this.f105b;
            if (i4 < this.f107d) {
                e(i4, 31, 32);
            }
            this.f106c = false;
            this.f105b = Integer.MAX_VALUE;
            e(this.f107d, 31, 32);
        }
        int size = arrayList.size();
        int i5 = 0;
        while (i5 < size) {
            int i6 = i5 + 1;
            C0002c c0002c = (C0002c) arrayList.get(i5);
            H0.h g2 = c0002c.f94a.g();
            Integer num = (Integer) AbstractC0005f.f112b.get(g2);
            H0.h hVar = c0002c.f95b;
            if (num != null) {
                int intValue = num.intValue();
                i3 = intValue + 1;
                if (2 <= i3 && i3 < 8) {
                    C0002c[] c0002cArr = AbstractC0005f.f111a;
                    if (AbstractC0150d.a(c0002cArr[intValue].f95b, hVar)) {
                        i2 = i3;
                    } else if (AbstractC0150d.a(c0002cArr[i3].f95b, hVar)) {
                        i3 = intValue + 2;
                        i2 = i3;
                    }
                }
                i2 = i3;
                i3 = -1;
            } else {
                i2 = -1;
                i3 = -1;
            }
            if (i3 == -1) {
                int i7 = this.f109f + 1;
                int length = this.f108e.length;
                while (true) {
                    if (i7 >= length) {
                        break;
                    }
                    int i8 = i7 + 1;
                    C0002c c0002c2 = this.f108e[i7];
                    AbstractC0150d.b(c0002c2);
                    if (AbstractC0150d.a(c0002c2.f94a, g2)) {
                        C0002c c0002c3 = this.f108e[i7];
                        AbstractC0150d.b(c0002c3);
                        if (AbstractC0150d.a(c0002c3.f95b, hVar)) {
                            i3 = AbstractC0005f.f111a.length + (i7 - this.f109f);
                            break;
                        } else if (i2 == -1) {
                            i2 = AbstractC0005f.f111a.length + (i7 - this.f109f);
                        }
                    }
                    i7 = i8;
                }
            }
            if (i3 != -1) {
                e(i3, 127, 128);
            } else if (i2 == -1) {
                this.f104a.u(64);
                c(g2);
                c(hVar);
                b(c0002c);
            } else {
                H0.h hVar2 = C0002c.f89d;
                g2.getClass();
                AbstractC0150d.e(hVar2, "prefix");
                if (g2.f(hVar2, hVar2.a()) && !AbstractC0150d.a(C0002c.f93i, g2)) {
                    e(i2, 15, 0);
                    c(hVar);
                } else {
                    e(i2, 63, 64);
                    c(hVar);
                    b(c0002c);
                }
            }
            i5 = i6;
        }
    }

    public final void e(int i2, int i3, int i4) {
        H0.e eVar = this.f104a;
        if (i2 < i3) {
            eVar.u(i2 | i4);
            return;
        }
        eVar.u(i4 | i3);
        int i5 = i2 - i3;
        while (i5 >= 128) {
            eVar.u(128 | (i5 & 127));
            i5 >>>= 7;
        }
        eVar.u(i5);
    }
}

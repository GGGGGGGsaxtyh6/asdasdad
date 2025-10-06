package v;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class h {

    /* renamed from: a, reason: collision with root package name */
    public int[] f2683a;

    /* renamed from: b, reason: collision with root package name */
    public int[] f2684b;

    /* renamed from: c, reason: collision with root package name */
    public int f2685c;

    /* renamed from: d, reason: collision with root package name */
    public int[] f2686d;

    /* renamed from: e, reason: collision with root package name */
    public float[] f2687e;

    /* renamed from: f, reason: collision with root package name */
    public int f2688f;

    /* renamed from: g, reason: collision with root package name */
    public int[] f2689g;
    public String[] h;

    /* renamed from: i, reason: collision with root package name */
    public int f2690i;

    /* renamed from: j, reason: collision with root package name */
    public int[] f2691j;

    /* renamed from: k, reason: collision with root package name */
    public boolean[] f2692k;

    /* renamed from: l, reason: collision with root package name */
    public int f2693l;

    public final void a(int i2, float f2) {
        int i3 = this.f2688f;
        int[] iArr = this.f2686d;
        if (i3 >= iArr.length) {
            this.f2686d = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f2687e;
            this.f2687e = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.f2686d;
        int i4 = this.f2688f;
        iArr2[i4] = i2;
        float[] fArr2 = this.f2687e;
        this.f2688f = i4 + 1;
        fArr2[i4] = f2;
    }

    public final void b(int i2, int i3) {
        int i4 = this.f2685c;
        int[] iArr = this.f2683a;
        if (i4 >= iArr.length) {
            this.f2683a = Arrays.copyOf(iArr, iArr.length * 2);
            int[] iArr2 = this.f2684b;
            this.f2684b = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f2683a;
        int i5 = this.f2685c;
        iArr3[i5] = i2;
        int[] iArr4 = this.f2684b;
        this.f2685c = i5 + 1;
        iArr4[i5] = i3;
    }

    public final void c(int i2, boolean z2) {
        int i3 = this.f2693l;
        int[] iArr = this.f2691j;
        if (i3 >= iArr.length) {
            this.f2691j = Arrays.copyOf(iArr, iArr.length * 2);
            boolean[] zArr = this.f2692k;
            this.f2692k = Arrays.copyOf(zArr, zArr.length * 2);
        }
        int[] iArr2 = this.f2691j;
        int i4 = this.f2693l;
        iArr2[i4] = i2;
        boolean[] zArr2 = this.f2692k;
        this.f2693l = i4 + 1;
        zArr2[i4] = z2;
    }

    public final void d(String str, int i2) {
        int i3 = this.f2690i;
        int[] iArr = this.f2689g;
        if (i3 >= iArr.length) {
            this.f2689g = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.h;
            this.h = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
        }
        int[] iArr2 = this.f2689g;
        int i4 = this.f2690i;
        iArr2[i4] = i2;
        String[] strArr2 = this.h;
        this.f2690i = i4 + 1;
        strArr2[i4] = str;
    }
}

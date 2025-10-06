package q;

import java.util.Arrays;
import org.conscrypt.BuildConfig;

/* loaded from: classes.dex */
public final class g implements Comparable {

    /* renamed from: a, reason: collision with root package name */
    public boolean f2208a;

    /* renamed from: e, reason: collision with root package name */
    public float f2212e;

    /* renamed from: l, reason: collision with root package name */
    public int f2218l;

    /* renamed from: b, reason: collision with root package name */
    public int f2209b = -1;

    /* renamed from: c, reason: collision with root package name */
    public int f2210c = -1;

    /* renamed from: d, reason: collision with root package name */
    public int f2211d = 0;

    /* renamed from: f, reason: collision with root package name */
    public boolean f2213f = false;

    /* renamed from: g, reason: collision with root package name */
    public final float[] f2214g = new float[9];
    public final float[] h = new float[9];

    /* renamed from: i, reason: collision with root package name */
    public C0231b[] f2215i = new C0231b[16];

    /* renamed from: j, reason: collision with root package name */
    public int f2216j = 0;

    /* renamed from: k, reason: collision with root package name */
    public int f2217k = 0;

    public g(int i2) {
        this.f2218l = i2;
    }

    public final void a(C0231b c0231b) {
        int i2 = 0;
        while (true) {
            int i3 = this.f2216j;
            if (i2 < i3) {
                if (this.f2215i[i2] == c0231b) {
                    return;
                } else {
                    i2++;
                }
            } else {
                C0231b[] c0231bArr = this.f2215i;
                if (i3 >= c0231bArr.length) {
                    this.f2215i = (C0231b[]) Arrays.copyOf(c0231bArr, c0231bArr.length * 2);
                }
                C0231b[] c0231bArr2 = this.f2215i;
                int i4 = this.f2216j;
                c0231bArr2[i4] = c0231b;
                this.f2216j = i4 + 1;
                return;
            }
        }
    }

    public final void b(C0231b c0231b) {
        int i2 = this.f2216j;
        int i3 = 0;
        while (i3 < i2) {
            if (this.f2215i[i3] == c0231b) {
                while (i3 < i2 - 1) {
                    C0231b[] c0231bArr = this.f2215i;
                    int i4 = i3 + 1;
                    c0231bArr[i3] = c0231bArr[i4];
                    i3 = i4;
                }
                this.f2216j--;
                return;
            }
            i3++;
        }
    }

    public final void c() {
        this.f2218l = 5;
        this.f2211d = 0;
        this.f2209b = -1;
        this.f2210c = -1;
        this.f2212e = 0.0f;
        this.f2213f = false;
        int i2 = this.f2216j;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f2215i[i3] = null;
        }
        this.f2216j = 0;
        this.f2217k = 0;
        this.f2208a = false;
        Arrays.fill(this.h, 0.0f);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f2209b - ((g) obj).f2209b;
    }

    public final void d(C0232c c0232c, float f2) {
        this.f2212e = f2;
        this.f2213f = true;
        int i2 = this.f2216j;
        this.f2210c = -1;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f2215i[i3].h(c0232c, this, false);
        }
        this.f2216j = 0;
    }

    public final void e(C0232c c0232c, C0231b c0231b) {
        int i2 = this.f2216j;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f2215i[i3].i(c0232c, c0231b, false);
        }
        this.f2216j = 0;
    }

    public final String toString() {
        return BuildConfig.FLAVOR + this.f2209b;
    }
}

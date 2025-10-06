package z;

/* renamed from: z.l, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0267l {

    /* renamed from: k, reason: collision with root package name */
    public static final C0267l f2958k;

    /* renamed from: a, reason: collision with root package name */
    public final float f2959a;

    /* renamed from: b, reason: collision with root package name */
    public final float f2960b;

    /* renamed from: c, reason: collision with root package name */
    public final float f2961c;

    /* renamed from: d, reason: collision with root package name */
    public final float f2962d;

    /* renamed from: e, reason: collision with root package name */
    public final float f2963e;

    /* renamed from: f, reason: collision with root package name */
    public final float f2964f;

    /* renamed from: g, reason: collision with root package name */
    public final float[] f2965g;
    public final float h;

    /* renamed from: i, reason: collision with root package name */
    public final float f2966i;

    /* renamed from: j, reason: collision with root package name */
    public final float f2967j;

    static {
        float f2;
        float[] fArr = AbstractC0257b.f2932c;
        float f3 = (float) ((AbstractC0257b.f() * 63.66197723675813d) / 100.0d);
        float[][] fArr2 = AbstractC0257b.f2930a;
        float f4 = fArr[0];
        float[] fArr3 = fArr2[0];
        float f5 = fArr3[0] * f4;
        float f6 = fArr[1];
        float f7 = (fArr3[1] * f6) + f5;
        float f8 = fArr[2];
        float f9 = (fArr3[2] * f8) + f7;
        float[] fArr4 = fArr2[1];
        float f10 = (fArr4[2] * f8) + (fArr4[1] * f6) + (fArr4[0] * f4);
        float[] fArr5 = fArr2[2];
        float f11 = (f8 * fArr5[2]) + (f6 * fArr5[1]) + (f4 * fArr5[0]);
        if (1.0f >= 0.9d) {
            f2 = 0.69f;
        } else {
            f2 = 0.655f;
        }
        float f12 = f2;
        float exp = (1.0f - (((float) Math.exp(((-f3) - 42.0f) / 92.0f)) * 0.2777778f)) * 1.0f;
        double d2 = exp;
        if (d2 > 1.0d) {
            exp = 1.0f;
        } else if (d2 < 0.0d) {
            exp = 0.0f;
        }
        float[] fArr6 = {(((100.0f / f9) * exp) + 1.0f) - exp, (((100.0f / f10) * exp) + 1.0f) - exp, (((100.0f / f11) * exp) + 1.0f) - exp};
        float f13 = 1.0f / ((5.0f * f3) + 1.0f);
        float f14 = f13 * f13 * f13 * f13;
        float f15 = 1.0f - f14;
        float cbrt = (0.1f * f15 * f15 * ((float) Math.cbrt(f3 * 5.0d))) + (f14 * f3);
        float f16 = AbstractC0257b.f() / fArr[1];
        double d3 = f16;
        float sqrt = ((float) Math.sqrt(d3)) + 1.48f;
        float pow = 0.725f / ((float) Math.pow(d3, 0.2d));
        float[] fArr7 = {(float) Math.pow(((fArr6[0] * cbrt) * f9) / 100.0d, 0.42d), (float) Math.pow(((fArr6[1] * cbrt) * f10) / 100.0d, 0.42d), (float) Math.pow(((fArr6[2] * cbrt) * f11) / 100.0d, 0.42d)};
        float f17 = fArr7[0];
        float f18 = (f17 * 400.0f) / (f17 + 27.13f);
        float f19 = fArr7[1];
        float f20 = (f19 * 400.0f) / (f19 + 27.13f);
        float f21 = fArr7[2];
        float[] fArr8 = {f18, f20, (400.0f * f21) / (f21 + 27.13f)};
        f2958k = new C0267l(f16, ((fArr8[2] * 0.05f) + (fArr8[0] * 2.0f) + fArr8[1]) * pow, pow, pow, f12, 1.0f, fArr6, cbrt, (float) Math.pow(cbrt, 0.25d), sqrt);
    }

    public C0267l(float f2, float f3, float f4, float f5, float f6, float f7, float[] fArr, float f8, float f9, float f10) {
        this.f2964f = f2;
        this.f2959a = f3;
        this.f2960b = f4;
        this.f2961c = f5;
        this.f2962d = f6;
        this.f2963e = f7;
        this.f2965g = fArr;
        this.h = f8;
        this.f2966i = f9;
        this.f2967j = f10;
    }
}

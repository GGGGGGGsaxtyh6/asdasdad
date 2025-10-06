package z;

import android.graphics.Color;

/* renamed from: z.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0256a {

    /* renamed from: a, reason: collision with root package name */
    public final float f2924a;

    /* renamed from: b, reason: collision with root package name */
    public final float f2925b;

    /* renamed from: c, reason: collision with root package name */
    public final float f2926c;

    /* renamed from: d, reason: collision with root package name */
    public final float f2927d;

    /* renamed from: e, reason: collision with root package name */
    public final float f2928e;

    /* renamed from: f, reason: collision with root package name */
    public final float f2929f;

    public C0256a(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.f2924a = f2;
        this.f2925b = f3;
        this.f2926c = f4;
        this.f2927d = f5;
        this.f2928e = f6;
        this.f2929f = f7;
    }

    public static C0256a a(int i2) {
        float f2;
        C0267l c0267l = C0267l.f2958k;
        float b2 = AbstractC0257b.b(Color.red(i2));
        float b3 = AbstractC0257b.b(Color.green(i2));
        float b4 = AbstractC0257b.b(Color.blue(i2));
        float[][] fArr = AbstractC0257b.f2933d;
        float[] fArr2 = fArr[0];
        float f3 = (fArr2[2] * b4) + (fArr2[1] * b3) + (fArr2[0] * b2);
        float[] fArr3 = fArr[1];
        float f4 = (fArr3[2] * b4) + (fArr3[1] * b3) + (fArr3[0] * b2);
        float[] fArr4 = fArr[2];
        float f5 = (b4 * fArr4[2]) + (b3 * fArr4[1]) + (b2 * fArr4[0]);
        float[][] fArr5 = AbstractC0257b.f2930a;
        float[] fArr6 = fArr5[0];
        float f6 = (fArr6[2] * f5) + (fArr6[1] * f4) + (fArr6[0] * f3);
        float[] fArr7 = fArr5[1];
        float f7 = (fArr7[2] * f5) + (fArr7[1] * f4) + (fArr7[0] * f3);
        float[] fArr8 = fArr5[2];
        float f8 = (f5 * fArr8[2]) + (f4 * fArr8[1]) + (f3 * fArr8[0]);
        float[] fArr9 = c0267l.f2965g;
        float f9 = fArr9[0] * f6;
        float f10 = fArr9[1] * f7;
        float f11 = fArr9[2] * f8;
        float abs = Math.abs(f9);
        float f12 = c0267l.h;
        float pow = (float) Math.pow((abs * f12) / 100.0d, 0.42d);
        float pow2 = (float) Math.pow((Math.abs(f10) * f12) / 100.0d, 0.42d);
        float pow3 = (float) Math.pow((Math.abs(f11) * f12) / 100.0d, 0.42d);
        float signum = ((Math.signum(f9) * 400.0f) * pow) / (pow + 27.13f);
        float signum2 = ((Math.signum(f10) * 400.0f) * pow2) / (pow2 + 27.13f);
        float signum3 = ((Math.signum(f11) * 400.0f) * pow3) / (pow3 + 27.13f);
        double d2 = signum3;
        float f13 = ((float) (((signum2 * (-12.0d)) + (signum * 11.0d)) + d2)) / 11.0f;
        float f14 = ((float) ((signum + signum2) - (d2 * 2.0d))) / 9.0f;
        float f15 = signum2 * 20.0f;
        float f16 = ((21.0f * signum3) + ((signum * 20.0f) + f15)) / 20.0f;
        float f17 = (((signum * 40.0f) + f15) + signum3) / 20.0f;
        float atan2 = (((float) Math.atan2(f14, f13)) * 180.0f) / 3.1415927f;
        if (atan2 < 0.0f) {
            atan2 += 360.0f;
        } else if (atan2 >= 360.0f) {
            atan2 -= 360.0f;
        }
        float f18 = atan2;
        float f19 = (3.1415927f * f18) / 180.0f;
        float f20 = f17 * c0267l.f2960b;
        float f21 = c0267l.f2959a;
        float f22 = c0267l.f2962d;
        float pow4 = ((float) Math.pow(f20 / f21, c0267l.f2967j * f22)) * 100.0f;
        Math.sqrt(pow4 / 100.0f);
        float f23 = f21 + 4.0f;
        if (f18 < 20.14d) {
            f2 = 360.0f + f18;
        } else {
            f2 = f18;
        }
        float pow5 = ((float) Math.pow(1.64d - Math.pow(0.29d, c0267l.f2964f), 0.73d)) * ((float) Math.pow((((((((float) (Math.cos(((f2 * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f) * 3846.1538f) * c0267l.f2963e) * c0267l.f2961c) * ((float) Math.sqrt((f14 * f14) + (f13 * f13)))) / (f16 + 0.305f), 0.9d)) * ((float) Math.sqrt(pow4 / 100.0d));
        float f24 = c0267l.f2966i * pow5;
        Math.sqrt((r3 * f22) / f23);
        float f25 = (1.7f * pow4) / ((0.007f * pow4) + 1.0f);
        float log = ((float) Math.log((f24 * 0.0228f) + 1.0f)) * 43.85965f;
        double d3 = f19;
        return new C0256a(f18, pow5, pow4, f25, log * ((float) Math.cos(d3)), log * ((float) Math.sin(d3)));
    }

    public static C0256a b(float f2, float f3, float f4) {
        C0267l c0267l = C0267l.f2958k;
        float f5 = c0267l.f2962d;
        Math.sqrt(f2 / 100.0d);
        float f6 = c0267l.f2959a + 4.0f;
        float f7 = c0267l.f2966i * f3;
        Math.sqrt(((f3 / ((float) Math.sqrt(r1))) * c0267l.f2962d) / f6);
        float f8 = (1.7f * f2) / ((0.007f * f2) + 1.0f);
        float log = ((float) Math.log((f7 * 0.0228d) + 1.0d)) * 43.85965f;
        double d2 = (3.1415927f * f4) / 180.0f;
        return new C0256a(f4, f3, f2, f8, log * ((float) Math.cos(d2)), log * ((float) Math.sin(d2)));
    }

    public final int c(C0267l c0267l) {
        float f2;
        float f3 = this.f2925b;
        double d2 = f3;
        float f4 = this.f2926c;
        if (d2 != 0.0d) {
            double d3 = f4;
            if (d3 != 0.0d) {
                f2 = f3 / ((float) Math.sqrt(d3 / 100.0d));
                float pow = (float) Math.pow(f2 / Math.pow(1.64d - Math.pow(0.29d, c0267l.f2964f), 0.73d), 1.1111111111111112d);
                double d4 = (this.f2924a * 3.1415927f) / 180.0f;
                float cos = ((float) (Math.cos(2.0d + d4) + 3.8d)) * 0.25f;
                float pow2 = c0267l.f2959a * ((float) Math.pow(f4 / 100.0d, (1.0d / c0267l.f2962d) / c0267l.f2967j));
                float f5 = cos * 3846.1538f * c0267l.f2963e * c0267l.f2961c;
                float f6 = pow2 / c0267l.f2960b;
                float sin = (float) Math.sin(d4);
                float cos2 = (float) Math.cos(d4);
                float f7 = (((0.305f + f6) * 23.0f) * pow) / (((pow * 108.0f) * sin) + (((11.0f * pow) * cos2) + (f5 * 23.0f)));
                float f8 = cos2 * f7;
                float f9 = f7 * sin;
                float f10 = f6 * 460.0f;
                float f11 = ((288.0f * f9) + ((451.0f * f8) + f10)) / 1403.0f;
                float f12 = ((f10 - (891.0f * f8)) - (261.0f * f9)) / 1403.0f;
                float f13 = ((f10 - (f8 * 220.0f)) - (f9 * 6300.0f)) / 1403.0f;
                float max = (float) Math.max(0.0d, (Math.abs(f11) * 27.13d) / (400.0d - Math.abs(f11)));
                float signum = Math.signum(f11);
                float f14 = 100.0f / c0267l.h;
                float pow3 = signum * f14 * ((float) Math.pow(max, 2.380952380952381d));
                float signum2 = Math.signum(f12) * f14 * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f12) * 27.13d) / (400.0d - Math.abs(f12))), 2.380952380952381d));
                float signum3 = Math.signum(f13) * f14 * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f13) * 27.13d) / (400.0d - Math.abs(f13))), 2.380952380952381d));
                float[] fArr = c0267l.f2965g;
                float f15 = pow3 / fArr[0];
                float f16 = signum2 / fArr[1];
                float f17 = signum3 / fArr[2];
                float[][] fArr2 = AbstractC0257b.f2931b;
                float[] fArr3 = fArr2[0];
                float f18 = (fArr3[2] * f17) + (fArr3[1] * f16) + (fArr3[0] * f15);
                float[] fArr4 = fArr2[1];
                float f19 = (fArr4[2] * f17) + (fArr4[1] * f16) + (fArr4[0] * f15);
                float[] fArr5 = fArr2[2];
                return A.a.a(f18, f19, (f17 * fArr5[2]) + (f16 * fArr5[1]) + (f15 * fArr5[0]));
            }
        }
        f2 = 0.0f;
        float pow4 = (float) Math.pow(f2 / Math.pow(1.64d - Math.pow(0.29d, c0267l.f2964f), 0.73d), 1.1111111111111112d);
        double d42 = (this.f2924a * 3.1415927f) / 180.0f;
        float cos3 = ((float) (Math.cos(2.0d + d42) + 3.8d)) * 0.25f;
        float pow22 = c0267l.f2959a * ((float) Math.pow(f4 / 100.0d, (1.0d / c0267l.f2962d) / c0267l.f2967j));
        float f52 = cos3 * 3846.1538f * c0267l.f2963e * c0267l.f2961c;
        float f62 = pow22 / c0267l.f2960b;
        float sin2 = (float) Math.sin(d42);
        float cos22 = (float) Math.cos(d42);
        float f72 = (((0.305f + f62) * 23.0f) * pow4) / (((pow4 * 108.0f) * sin2) + (((11.0f * pow4) * cos22) + (f52 * 23.0f)));
        float f82 = cos22 * f72;
        float f92 = f72 * sin2;
        float f102 = f62 * 460.0f;
        float f112 = ((288.0f * f92) + ((451.0f * f82) + f102)) / 1403.0f;
        float f122 = ((f102 - (891.0f * f82)) - (261.0f * f92)) / 1403.0f;
        float f132 = ((f102 - (f82 * 220.0f)) - (f92 * 6300.0f)) / 1403.0f;
        float max2 = (float) Math.max(0.0d, (Math.abs(f112) * 27.13d) / (400.0d - Math.abs(f112)));
        float signum4 = Math.signum(f112);
        float f142 = 100.0f / c0267l.h;
        float pow32 = signum4 * f142 * ((float) Math.pow(max2, 2.380952380952381d));
        float signum22 = Math.signum(f122) * f142 * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f122) * 27.13d) / (400.0d - Math.abs(f122))), 2.380952380952381d));
        float signum32 = Math.signum(f132) * f142 * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f132) * 27.13d) / (400.0d - Math.abs(f132))), 2.380952380952381d));
        float[] fArr6 = c0267l.f2965g;
        float f152 = pow32 / fArr6[0];
        float f162 = signum22 / fArr6[1];
        float f172 = signum32 / fArr6[2];
        float[][] fArr22 = AbstractC0257b.f2931b;
        float[] fArr32 = fArr22[0];
        float f182 = (fArr32[2] * f172) + (fArr32[1] * f162) + (fArr32[0] * f152);
        float[] fArr42 = fArr22[1];
        float f192 = (fArr42[2] * f172) + (fArr42[1] * f162) + (fArr42[0] * f152);
        float[] fArr52 = fArr22[2];
        return A.a.a(f182, f192, (f172 * fArr52[2]) + (f162 * fArr52[1]) + (f152 * fArr52[0]));
    }
}

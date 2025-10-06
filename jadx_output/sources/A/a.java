package A;

import android.graphics.Color;

/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f0a = 0;

    static {
        new ThreadLocal();
    }

    public static int a(double d2, double d3, double d4) {
        double d5;
        double d6;
        double d7;
        int min;
        int min2;
        double d8 = (((-0.4986d) * d4) + (((-1.5372d) * d3) + (3.2406d * d2))) / 100.0d;
        double d9 = ((0.0415d * d4) + ((1.8758d * d3) + ((-0.9689d) * d2))) / 100.0d;
        double d10 = ((1.057d * d4) + (((-0.204d) * d3) + (0.0557d * d2))) / 100.0d;
        if (d8 > 0.0031308d) {
            d5 = (Math.pow(d8, 0.4166666666666667d) * 1.055d) - 0.055d;
        } else {
            d5 = d8 * 12.92d;
        }
        if (d9 > 0.0031308d) {
            d6 = (Math.pow(d9, 0.4166666666666667d) * 1.055d) - 0.055d;
        } else {
            d6 = d9 * 12.92d;
        }
        if (d10 > 0.0031308d) {
            d7 = (Math.pow(d10, 0.4166666666666667d) * 1.055d) - 0.055d;
        } else {
            d7 = d10 * 12.92d;
        }
        int round = (int) Math.round(d5 * 255.0d);
        int i2 = 0;
        if (round < 0) {
            min = 0;
        } else {
            min = Math.min(round, 255);
        }
        int round2 = (int) Math.round(d6 * 255.0d);
        if (round2 < 0) {
            min2 = 0;
        } else {
            min2 = Math.min(round2, 255);
        }
        int round3 = (int) Math.round(d7 * 255.0d);
        if (round3 >= 0) {
            i2 = Math.min(round3, 255);
        }
        return Color.rgb(min, min2, i2);
    }

    public static int b(int i2, int i3) {
        int alpha = Color.alpha(i3);
        int alpha2 = Color.alpha(i2);
        int i4 = 255 - (((255 - alpha2) * (255 - alpha)) / 255);
        return Color.argb(i4, c(Color.red(i2), alpha2, Color.red(i3), alpha, i4), c(Color.green(i2), alpha2, Color.green(i3), alpha, i4), c(Color.blue(i2), alpha2, Color.blue(i3), alpha, i4));
    }

    public static int c(int i2, int i3, int i4, int i5, int i6) {
        if (i6 == 0) {
            return 0;
        }
        return (((255 - i3) * (i4 * i5)) + ((i2 * 255) * i3)) / (i6 * 255);
    }
}

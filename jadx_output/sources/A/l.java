package A;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import z.C0260e;
import z.C0261f;

/* loaded from: classes.dex */
public final class l extends C0.d {
    public static Font I(FontFamily fontFamily, int i2) {
        int i3;
        int i4;
        if ((i2 & 1) != 0) {
            i3 = 700;
        } else {
            i3 = 400;
        }
        if ((i2 & 2) != 0) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        FontStyle fontStyle = new FontStyle(i3, i4);
        Font font = fontFamily.getFont(0);
        int K2 = K(fontStyle, font.getStyle());
        for (int i5 = 1; i5 < fontFamily.getSize(); i5++) {
            Font font2 = fontFamily.getFont(i5);
            int K3 = K(fontStyle, font2.getStyle());
            if (K3 < K2) {
                font = font2;
                K2 = K3;
            }
        }
        return font;
    }

    public static FontFamily J(E.l[] lVarArr, ContentResolver contentResolver) {
        ParcelFileDescriptor openFileDescriptor;
        FontFamily.Builder builder = null;
        for (E.l lVar : lVarArr) {
            try {
                openFileDescriptor = contentResolver.openFileDescriptor(lVar.f296a, "r", null);
            } catch (IOException e2) {
                Log.w("TypefaceCompatApi29Impl", "Font load failed", e2);
            }
            if (openFileDescriptor == null) {
                if (openFileDescriptor == null) {
                }
            } else {
                try {
                    Font build = new Font.Builder(openFileDescriptor).setWeight(lVar.f298c).setSlant(lVar.f299d ? 1 : 0).setTtcIndex(lVar.f297b).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(build);
                    } else {
                        builder.addFont(build);
                    }
                } catch (Throwable th) {
                    try {
                        openFileDescriptor.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                    break;
                }
            }
            openFileDescriptor.close();
        }
        if (builder == null) {
            return null;
        }
        return builder.build();
    }

    public static int K(FontStyle fontStyle, FontStyle fontStyle2) {
        int i2;
        int abs = Math.abs(fontStyle.getWeight() - fontStyle2.getWeight()) / 100;
        if (fontStyle.getSlant() == fontStyle2.getSlant()) {
            i2 = 0;
        } else {
            i2 = 2;
        }
        return abs + i2;
    }

    @Override // C0.d
    public final Typeface k(Context context, C0260e c0260e, Resources resources, int i2) {
        try {
            FontFamily.Builder builder = null;
            for (C0261f c0261f : c0260e.f2938a) {
                try {
                    Font build = new Font.Builder(resources, c0261f.f2944f).setWeight(c0261f.f2940b).setSlant(c0261f.f2941c ? 1 : 0).setTtcIndex(c0261f.f2943e).setFontVariationSettings(c0261f.f2942d).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(build);
                    } else {
                        builder.addFont(build);
                    }
                } catch (IOException unused) {
                }
            }
            if (builder == null) {
                return null;
            }
            FontFamily build2 = builder.build();
            return new Typeface.CustomFallbackBuilder(build2).setStyle(I(build2, i2).getStyle()).build();
        } catch (Exception e2) {
            Log.w("TypefaceCompatApi29Impl", "Font load failed", e2);
            return null;
        }
    }

    @Override // C0.d
    public final Typeface l(Context context, E.l[] lVarArr, int i2) {
        try {
            FontFamily J2 = J(lVarArr, context.getContentResolver());
            if (J2 == null) {
                return null;
            }
            return new Typeface.CustomFallbackBuilder(J2).setStyle(I(J2, i2).getStyle()).build();
        } catch (Exception e2) {
            Log.w("TypefaceCompatApi29Impl", "Font load failed", e2);
            return null;
        }
    }

    @Override // C0.d
    public final Typeface m(Context context, List list, int i2) {
        ContentResolver contentResolver = context.getContentResolver();
        try {
            FontFamily J2 = J((E.l[]) list.get(0), contentResolver);
            if (J2 == null) {
                return null;
            }
            Typeface.CustomFallbackBuilder customFallbackBuilder = new Typeface.CustomFallbackBuilder(J2);
            for (int i3 = 1; i3 < list.size(); i3++) {
                FontFamily J3 = J((E.l[]) list.get(i3), contentResolver);
                if (J3 != null) {
                    customFallbackBuilder.addCustomFallback(J3);
                }
            }
            return customFallbackBuilder.setStyle(I(J2, i2).getStyle()).build();
        } catch (Exception e2) {
            Log.w("TypefaceCompatApi29Impl", "Font load failed", e2);
            return null;
        }
    }

    @Override // C0.d
    public final Typeface n(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // C0.d
    public final Typeface o(Context context, Resources resources, int i2, String str, int i3) {
        try {
            Font build = new Font.Builder(resources, i2).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(build).build()).setStyle(build.getStyle()).build();
        } catch (Exception e2) {
            Log.w("TypefaceCompatApi29Impl", "Font load failed", e2);
            return null;
        }
    }

    @Override // C0.d
    public final E.l p(E.l[] lVarArr, int i2) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }
}

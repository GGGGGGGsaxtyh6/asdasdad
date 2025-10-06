package z;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Base64;
import android.util.Xml;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
import w.AbstractC0254a;

/* renamed from: z.b, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0257b {

    /* renamed from: a, reason: collision with root package name */
    public static final float[][] f2930a = {new float[]{0.401288f, 0.650173f, -0.051461f}, new float[]{-0.250268f, 1.204414f, 0.045854f}, new float[]{-0.002079f, 0.048952f, 0.953127f}};

    /* renamed from: b, reason: collision with root package name */
    public static final float[][] f2931b = {new float[]{1.8620678f, -1.0112547f, 0.14918678f}, new float[]{0.38752654f, 0.62144744f, -0.00897398f}, new float[]{-0.0158415f, -0.03412294f, 1.0499644f}};

    /* renamed from: c, reason: collision with root package name */
    public static final float[] f2932c = {95.047f, 100.0f, 108.883f};

    /* renamed from: d, reason: collision with root package name */
    public static final float[][] f2933d = {new float[]{0.41233894f, 0.35762063f, 0.18051042f}, new float[]{0.2126f, 0.7152f, 0.0722f}, new float[]{0.01932141f, 0.11916382f, 0.9503448f}};

    /* renamed from: e, reason: collision with root package name */
    public static final Object f2934e = new Object();

    /* renamed from: f, reason: collision with root package name */
    public static Method f2935f;

    /* renamed from: g, reason: collision with root package name */
    public static boolean f2936g;

    public static int a(float f2) {
        float f3;
        boolean z2;
        float f4;
        if (f2 < 1.0f) {
            return -16777216;
        }
        if (f2 > 99.0f) {
            return -1;
        }
        float f5 = (f2 + 16.0f) / 116.0f;
        if (f2 > 8.0f) {
            f3 = f5 * f5 * f5;
        } else {
            f3 = f2 / 903.2963f;
        }
        float f6 = f5 * f5 * f5;
        if (f6 > 0.008856452f) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            f4 = f6;
        } else {
            f4 = ((f5 * 116.0f) - 16.0f) / 903.2963f;
        }
        if (!z2) {
            f6 = ((f5 * 116.0f) - 16.0f) / 903.2963f;
        }
        float[] fArr = f2932c;
        return A.a.a(f4 * fArr[0], f3 * fArr[1], f6 * fArr[2]);
    }

    public static float b(int i2) {
        float pow;
        float f2 = i2 / 255.0f;
        if (f2 <= 0.04045f) {
            pow = f2 / 12.92f;
        } else {
            pow = (float) Math.pow((f2 + 0.055f) / 1.055f, 2.4000000953674316d);
        }
        return pow * 100.0f;
    }

    public static InterfaceC0259d c(XmlResourceParser xmlResourceParser, Resources resources) {
        int next;
        int i2;
        boolean z2;
        int i3;
        int i4;
        E.g gVar;
        do {
            next = xmlResourceParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            xmlResourceParser.require(2, null, "font-family");
            if (xmlResourceParser.getName().equals("font-family")) {
                TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), AbstractC0254a.f2816b);
                String string = obtainAttributes.getString(0);
                String string2 = obtainAttributes.getString(5);
                String string3 = obtainAttributes.getString(6);
                String string4 = obtainAttributes.getString(2);
                int resourceId = obtainAttributes.getResourceId(1, 0);
                int integer = obtainAttributes.getInteger(3, 1);
                int integer2 = obtainAttributes.getInteger(4, 500);
                String string5 = obtainAttributes.getString(7);
                obtainAttributes.recycle();
                if (string != null && string2 != null && string3 != null) {
                    while (xmlResourceParser.next() != 3) {
                        e(xmlResourceParser);
                    }
                    List d2 = d(resources, resourceId);
                    if (string4 != null) {
                        gVar = new E.g(string, string2, string4, d2);
                    } else {
                        gVar = null;
                    }
                    return new C0262g(new E.g(string, string2, string3, d2), gVar, integer, integer2, string5);
                }
                ArrayList arrayList = new ArrayList();
                while (xmlResourceParser.next() != 3) {
                    if (xmlResourceParser.getEventType() == 2) {
                        if (xmlResourceParser.getName().equals("font")) {
                            TypedArray obtainAttributes2 = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), AbstractC0254a.f2817c);
                            int i5 = 8;
                            if (!obtainAttributes2.hasValue(8)) {
                                i5 = 1;
                            }
                            int i6 = obtainAttributes2.getInt(i5, 400);
                            if (obtainAttributes2.hasValue(6)) {
                                i2 = 6;
                            } else {
                                i2 = 2;
                            }
                            if (1 == obtainAttributes2.getInt(i2, 0)) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            int i7 = 9;
                            if (!obtainAttributes2.hasValue(9)) {
                                i7 = 3;
                            }
                            if (obtainAttributes2.hasValue(7)) {
                                i3 = 7;
                            } else {
                                i3 = 4;
                            }
                            String string6 = obtainAttributes2.getString(i3);
                            int i8 = obtainAttributes2.getInt(i7, 0);
                            if (obtainAttributes2.hasValue(5)) {
                                i4 = 5;
                            } else {
                                i4 = 0;
                            }
                            int resourceId2 = obtainAttributes2.getResourceId(i4, 0);
                            String string7 = obtainAttributes2.getString(i4);
                            obtainAttributes2.recycle();
                            while (xmlResourceParser.next() != 3) {
                                e(xmlResourceParser);
                            }
                            arrayList.add(new C0261f(i6, i8, resourceId2, string7, string6, z2));
                        } else {
                            e(xmlResourceParser);
                        }
                    }
                }
                if (!arrayList.isEmpty()) {
                    return new C0260e((C0261f[]) arrayList.toArray(new C0261f[0]));
                }
            } else {
                e(xmlResourceParser);
            }
            return null;
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static List d(Resources resources, int i2) {
        if (i2 == 0) {
            return Collections.emptyList();
        }
        TypedArray obtainTypedArray = resources.obtainTypedArray(i2);
        try {
            if (obtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (obtainTypedArray.getType(0) == 1) {
                for (int i3 = 0; i3 < obtainTypedArray.length(); i3++) {
                    int resourceId = obtainTypedArray.getResourceId(i3, 0);
                    if (resourceId != 0) {
                        String[] stringArray = resources.getStringArray(resourceId);
                        ArrayList arrayList2 = new ArrayList();
                        for (String str : stringArray) {
                            arrayList2.add(Base64.decode(str, 0));
                        }
                        arrayList.add(arrayList2);
                    }
                }
            } else {
                String[] stringArray2 = resources.getStringArray(i2);
                ArrayList arrayList3 = new ArrayList();
                for (String str2 : stringArray2) {
                    arrayList3.add(Base64.decode(str2, 0));
                }
                arrayList.add(arrayList3);
            }
            return arrayList;
        } finally {
            obtainTypedArray.recycle();
        }
    }

    public static void e(XmlResourceParser xmlResourceParser) {
        int i2 = 1;
        while (i2 > 0) {
            int next = xmlResourceParser.next();
            if (next != 2) {
                if (next == 3) {
                    i2--;
                }
            } else {
                i2++;
            }
        }
    }

    public static float f() {
        return ((float) Math.pow((50.0f + 16.0d) / 116.0d, 3.0d)) * 100.0f;
    }
}

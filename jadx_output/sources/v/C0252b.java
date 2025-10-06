package v;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.TypedValue;
import android.util.Xml;
import java.util.HashMap;
import org.conscrypt.ct.CTConstants;

/* renamed from: v.b, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0252b {

    /* renamed from: a, reason: collision with root package name */
    public boolean f2592a = false;

    /* renamed from: b, reason: collision with root package name */
    public int f2593b;

    /* renamed from: c, reason: collision with root package name */
    public int f2594c;

    /* renamed from: d, reason: collision with root package name */
    public float f2595d;

    /* renamed from: e, reason: collision with root package name */
    public String f2596e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f2597f;

    /* renamed from: g, reason: collision with root package name */
    public int f2598g;

    public C0252b(C0252b c0252b, Object obj) {
        c0252b.getClass();
        this.f2593b = c0252b.f2593b;
        b(obj);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, v.b] */
    public static void a(Context context, XmlResourceParser xmlResourceParser, HashMap hashMap) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), r.f2800d);
        int indexCount = obtainStyledAttributes.getIndexCount();
        String str = null;
        int i2 = 0;
        boolean z2 = false;
        Object obj = null;
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = obtainStyledAttributes.getIndex(i3);
            int i4 = 1;
            if (index == 0) {
                str = obtainStyledAttributes.getString(index);
                if (str != null && str.length() > 0) {
                    str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
                }
            } else if (index == 10) {
                str = obtainStyledAttributes.getString(index);
                z2 = true;
            } else if (index == 1) {
                obj = Boolean.valueOf(obtainStyledAttributes.getBoolean(index, false));
                i2 = 6;
            } else {
                int i5 = 3;
                if (index == 3) {
                    obj = Integer.valueOf(obtainStyledAttributes.getColor(index, 0));
                } else {
                    i5 = 4;
                    if (index == 2) {
                        obj = Integer.valueOf(obtainStyledAttributes.getColor(index, 0));
                    } else {
                        if (index == 7) {
                            obj = Float.valueOf(TypedValue.applyDimension(1, obtainStyledAttributes.getDimension(index, 0.0f), context.getResources().getDisplayMetrics()));
                        } else if (index == 4) {
                            obj = Float.valueOf(obtainStyledAttributes.getDimension(index, 0.0f));
                        } else {
                            i5 = 5;
                            if (index == 5) {
                                obj = Float.valueOf(obtainStyledAttributes.getFloat(index, Float.NaN));
                                i2 = 2;
                            } else {
                                if (index == 6) {
                                    obj = Integer.valueOf(obtainStyledAttributes.getInteger(index, -1));
                                } else if (index == 9) {
                                    obj = obtainStyledAttributes.getString(index);
                                } else {
                                    i4 = 8;
                                    if (index == 8) {
                                        int resourceId = obtainStyledAttributes.getResourceId(index, -1);
                                        if (resourceId == -1) {
                                            resourceId = obtainStyledAttributes.getInt(index, -1);
                                        }
                                        obj = Integer.valueOf(resourceId);
                                    }
                                }
                                i2 = i4;
                            }
                        }
                        i2 = 7;
                    }
                }
                i2 = i5;
            }
        }
        if (str != null && obj != null) {
            ?? obj2 = new Object();
            obj2.f2593b = i2;
            obj2.f2592a = z2;
            obj2.b(obj);
            hashMap.put(str, obj2);
        }
        obtainStyledAttributes.recycle();
    }

    public final void b(Object obj) {
        switch (q.f.a(this.f2593b)) {
            case 0:
            case 7:
                this.f2594c = ((Integer) obj).intValue();
                return;
            case 1:
                this.f2595d = ((Float) obj).floatValue();
                return;
            case 2:
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                this.f2598g = ((Integer) obj).intValue();
                return;
            case 4:
                this.f2596e = (String) obj;
                return;
            case 5:
                this.f2597f = ((Boolean) obj).booleanValue();
                return;
            case 6:
                this.f2595d = ((Float) obj).floatValue();
                return;
            default:
                return;
        }
    }
}

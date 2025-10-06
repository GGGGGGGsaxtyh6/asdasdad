package k;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: k.i0, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0181i0 {

    /* renamed from: l, reason: collision with root package name */
    public static final RectF f1927l = new RectF();

    /* renamed from: m, reason: collision with root package name */
    public static final ConcurrentHashMap f1928m = new ConcurrentHashMap();

    /* renamed from: a, reason: collision with root package name */
    public int f1929a = 0;

    /* renamed from: b, reason: collision with root package name */
    public boolean f1930b = false;

    /* renamed from: c, reason: collision with root package name */
    public float f1931c = -1.0f;

    /* renamed from: d, reason: collision with root package name */
    public float f1932d = -1.0f;

    /* renamed from: e, reason: collision with root package name */
    public float f1933e = -1.0f;

    /* renamed from: f, reason: collision with root package name */
    public int[] f1934f = new int[0];

    /* renamed from: g, reason: collision with root package name */
    public boolean f1935g = false;
    public TextPaint h;

    /* renamed from: i, reason: collision with root package name */
    public final TextView f1936i;

    /* renamed from: j, reason: collision with root package name */
    public final Context f1937j;

    /* renamed from: k, reason: collision with root package name */
    public final C0175f0 f1938k;

    public C0181i0(TextView textView) {
        this.f1936i = textView;
        this.f1937j = textView.getContext();
        if (Build.VERSION.SDK_INT >= 29) {
            this.f1938k = new C0177g0();
        } else {
            this.f1938k = new C0175f0();
        }
    }

    public static int[] b(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            if (i2 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i2)) < 0) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr2[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
        return iArr2;
    }

    public static Method d(String str) {
        try {
            ConcurrentHashMap concurrentHashMap = f1928m;
            Method method = (Method) concurrentHashMap.get(str);
            if (method == null && (method = TextView.class.getDeclaredMethod(str, null)) != null) {
                method.setAccessible(true);
                concurrentHashMap.put(str, method);
            }
            return method;
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e2);
            return null;
        }
    }

    public static Object e(Object obj, String str, Object obj2) {
        try {
            return d(str).invoke(obj, null);
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e2);
            return obj2;
        }
    }

    public final void a() {
        int measuredWidth;
        if (!f()) {
            return;
        }
        if (this.f1930b) {
            if (this.f1936i.getMeasuredHeight() > 0 && this.f1936i.getMeasuredWidth() > 0) {
                if (this.f1938k.b(this.f1936i)) {
                    measuredWidth = 1048576;
                } else {
                    measuredWidth = (this.f1936i.getMeasuredWidth() - this.f1936i.getTotalPaddingLeft()) - this.f1936i.getTotalPaddingRight();
                }
                int height = (this.f1936i.getHeight() - this.f1936i.getCompoundPaddingBottom()) - this.f1936i.getCompoundPaddingTop();
                if (measuredWidth > 0 && height > 0) {
                    RectF rectF = f1927l;
                    synchronized (rectF) {
                        try {
                            rectF.setEmpty();
                            rectF.right = measuredWidth;
                            rectF.bottom = height;
                            float c2 = c(rectF);
                            if (c2 != this.f1936i.getTextSize()) {
                                g(0, c2);
                            }
                        } finally {
                        }
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        this.f1930b = true;
    }

    public final int c(RectF rectF) {
        CharSequence transformation;
        int length = this.f1934f.length;
        if (length != 0) {
            int i2 = length - 1;
            int i3 = 0;
            int i4 = 1;
            while (i4 <= i2) {
                int i5 = (i4 + i2) / 2;
                int i6 = this.f1934f[i5];
                TextView textView = this.f1936i;
                CharSequence text = textView.getText();
                TransformationMethod transformationMethod = textView.getTransformationMethod();
                if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, textView)) != null) {
                    text = transformation;
                }
                int maxLines = textView.getMaxLines();
                TextPaint textPaint = this.h;
                if (textPaint == null) {
                    this.h = new TextPaint();
                } else {
                    textPaint.reset();
                }
                this.h.set(textView.getPaint());
                this.h.setTextSize(i6);
                StaticLayout a2 = AbstractC0173e0.a(text, (Layout.Alignment) e(textView, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL), Math.round(rectF.right), maxLines, this.f1936i, this.h, this.f1938k);
                if ((maxLines != -1 && (a2.getLineCount() > maxLines || a2.getLineEnd(a2.getLineCount() - 1) != text.length())) || a2.getHeight() > rectF.bottom) {
                    i3 = i5 - 1;
                    i2 = i3;
                } else {
                    int i7 = i5 + 1;
                    i3 = i4;
                    i4 = i7;
                }
            }
            return this.f1934f[i3];
        }
        throw new IllegalStateException("No available text sizes to choose from.");
    }

    public final boolean f() {
        if (j() && this.f1929a != 0) {
            return true;
        }
        return false;
    }

    public final void g(int i2, float f2) {
        Resources resources;
        Context context = this.f1937j;
        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        float applyDimension = TypedValue.applyDimension(i2, f2, resources.getDisplayMetrics());
        TextView textView = this.f1936i;
        if (applyDimension != textView.getPaint().getTextSize()) {
            textView.getPaint().setTextSize(applyDimension);
            boolean isInLayout = textView.isInLayout();
            if (textView.getLayout() != null) {
                this.f1930b = false;
                try {
                    Method d2 = d("nullLayouts");
                    if (d2 != null) {
                        d2.invoke(textView, null);
                    }
                } catch (Exception e2) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e2);
                }
                if (!isInLayout) {
                    textView.requestLayout();
                } else {
                    textView.forceLayout();
                }
                textView.invalidate();
            }
        }
    }

    public final boolean h() {
        if (j() && this.f1929a == 1) {
            if (!this.f1935g || this.f1934f.length == 0) {
                int floor = ((int) Math.floor((this.f1933e - this.f1932d) / this.f1931c)) + 1;
                int[] iArr = new int[floor];
                for (int i2 = 0; i2 < floor; i2++) {
                    iArr[i2] = Math.round((i2 * this.f1931c) + this.f1932d);
                }
                this.f1934f = b(iArr);
            }
            this.f1930b = true;
        } else {
            this.f1930b = false;
        }
        return this.f1930b;
    }

    public final boolean i() {
        boolean z2;
        if (this.f1934f.length > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f1935g = z2;
        if (z2) {
            this.f1929a = 1;
            this.f1932d = r0[0];
            this.f1933e = r0[r1 - 1];
            this.f1931c = -1.0f;
        }
        return z2;
    }

    public final boolean j() {
        return !(this.f1936i instanceof C0207w);
    }

    public final void k(float f2, float f3, float f4) {
        if (f2 > 0.0f) {
            if (f3 > f2) {
                if (f4 > 0.0f) {
                    this.f1929a = 1;
                    this.f1932d = f2;
                    this.f1933e = f3;
                    this.f1931c = f4;
                    this.f1935g = false;
                    return;
                }
                throw new IllegalArgumentException("The auto-size step granularity (" + f4 + "px) is less or equal to (0px)");
            }
            throw new IllegalArgumentException("Maximum auto-size text size (" + f3 + "px) is less or equal to minimum auto-size text size (" + f2 + "px)");
        }
        throw new IllegalArgumentException("Minimum auto-size text size (" + f2 + "px) is less or equal to (0px)");
    }
}

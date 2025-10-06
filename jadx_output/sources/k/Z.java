package k;

import H.AbstractC0029x;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import f.AbstractC0101a;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class Z {

    /* renamed from: a, reason: collision with root package name */
    public final TextView f1859a;

    /* renamed from: b, reason: collision with root package name */
    public U0 f1860b;

    /* renamed from: c, reason: collision with root package name */
    public U0 f1861c;

    /* renamed from: d, reason: collision with root package name */
    public U0 f1862d;

    /* renamed from: e, reason: collision with root package name */
    public U0 f1863e;

    /* renamed from: f, reason: collision with root package name */
    public U0 f1864f;

    /* renamed from: g, reason: collision with root package name */
    public U0 f1865g;
    public U0 h;

    /* renamed from: i, reason: collision with root package name */
    public final C0181i0 f1866i;

    /* renamed from: j, reason: collision with root package name */
    public int f1867j = 0;

    /* renamed from: k, reason: collision with root package name */
    public int f1868k = -1;

    /* renamed from: l, reason: collision with root package name */
    public Typeface f1869l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f1870m;

    public Z(TextView textView) {
        this.f1859a = textView;
        this.f1866i = new C0181i0(textView);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, k.U0] */
    public static U0 c(Context context, C0203u c0203u, int i2) {
        ColorStateList f2;
        synchronized (c0203u) {
            f2 = c0203u.f2031a.f(context, i2);
        }
        if (f2 != null) {
            ?? obj = new Object();
            obj.f1850b = true;
            obj.f1851c = f2;
            return obj;
        }
        return null;
    }

    public static void h(EditorInfo editorInfo, InputConnection inputConnection, TextView textView) {
        int i2;
        int i3;
        CharSequence subSequence;
        int i4 = Build.VERSION.SDK_INT;
        if (i4 < 30 && inputConnection != null) {
            CharSequence text = textView.getText();
            if (i4 >= 30) {
                J.b.a(editorInfo, text);
                return;
            }
            text.getClass();
            if (i4 >= 30) {
                J.b.a(editorInfo, text);
                return;
            }
            int i5 = editorInfo.initialSelStart;
            int i6 = editorInfo.initialSelEnd;
            if (i5 > i6) {
                i2 = i6;
            } else {
                i2 = i5;
            }
            if (i5 <= i6) {
                i5 = i6;
            }
            int length = text.length();
            if (i2 >= 0 && i5 <= length) {
                int i7 = editorInfo.inputType & 4095;
                if (i7 != 129 && i7 != 225 && i7 != 18) {
                    if (length <= 2048) {
                        J.c.a(editorInfo, text, i2, i5);
                        return;
                    }
                    int i8 = i5 - i2;
                    if (i8 > 1024) {
                        i3 = 0;
                    } else {
                        i3 = i8;
                    }
                    int i9 = 2048 - i3;
                    int min = Math.min(text.length() - i5, i9 - Math.min(i2, (int) (i9 * 0.8d)));
                    int min2 = Math.min(i2, i9 - min);
                    int i10 = i2 - min2;
                    if (Character.isLowSurrogate(text.charAt(i10))) {
                        i10++;
                        min2--;
                    }
                    if (Character.isHighSurrogate(text.charAt((i5 + min) - 1))) {
                        min--;
                    }
                    int i11 = min2 + i3;
                    int i12 = i11 + min;
                    if (i3 != i8) {
                        subSequence = TextUtils.concat(text.subSequence(i10, i10 + min2), text.subSequence(i5, min + i5));
                    } else {
                        subSequence = text.subSequence(i10, i12 + i10);
                    }
                    J.c.a(editorInfo, subSequence, min2, i11);
                    return;
                }
                J.c.a(editorInfo, null, 0, 0);
                return;
            }
            J.c.a(editorInfo, null, 0, 0);
        }
    }

    public final void a(Drawable drawable, U0 u02) {
        if (drawable != null && u02 != null) {
            C0203u.d(drawable, u02, this.f1859a.getDrawableState());
        }
    }

    public final void b() {
        U0 u02 = this.f1860b;
        TextView textView = this.f1859a;
        if (u02 != null || this.f1861c != null || this.f1862d != null || this.f1863e != null) {
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            a(compoundDrawables[0], this.f1860b);
            a(compoundDrawables[1], this.f1861c);
            a(compoundDrawables[2], this.f1862d);
            a(compoundDrawables[3], this.f1863e);
        }
        if (this.f1864f != null || this.f1865g != null) {
            Drawable[] compoundDrawablesRelative = textView.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f1864f);
            a(compoundDrawablesRelative[2], this.f1865g);
        }
    }

    public final ColorStateList d() {
        U0 u02 = this.h;
        if (u02 != null) {
            return (ColorStateList) u02.f1851c;
        }
        return null;
    }

    public final PorterDuff.Mode e() {
        U0 u02 = this.h;
        if (u02 != null) {
            return (PorterDuff.Mode) u02.f1852d;
        }
        return null;
    }

    public final void f(AttributeSet attributeSet, int i2) {
        boolean z2;
        boolean z3;
        String str;
        String str2;
        boolean z4;
        float f2;
        float f3;
        float f4;
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        Drawable drawable4;
        Drawable drawable5;
        Drawable drawable6;
        int i3;
        int i4;
        int i5;
        float f5;
        ColorStateList colorStateList;
        int resourceId;
        int i6;
        int resourceId2;
        int i7;
        TextView textView = this.f1859a;
        Context context = textView.getContext();
        C0203u a2 = C0203u.a();
        int[] iArr = AbstractC0101a.h;
        D0.h p2 = D0.h.p(context, attributeSet, iArr, i2);
        H.N.g(textView, textView.getContext(), iArr, attributeSet, (TypedArray) p2.f259c, i2);
        TypedArray typedArray = (TypedArray) p2.f259c;
        int resourceId3 = typedArray.getResourceId(0, -1);
        if (typedArray.hasValue(3)) {
            this.f1860b = c(context, a2, typedArray.getResourceId(3, 0));
        }
        if (typedArray.hasValue(1)) {
            this.f1861c = c(context, a2, typedArray.getResourceId(1, 0));
        }
        if (typedArray.hasValue(4)) {
            this.f1862d = c(context, a2, typedArray.getResourceId(4, 0));
        }
        if (typedArray.hasValue(2)) {
            this.f1863e = c(context, a2, typedArray.getResourceId(2, 0));
        }
        if (typedArray.hasValue(5)) {
            this.f1864f = c(context, a2, typedArray.getResourceId(5, 0));
        }
        if (typedArray.hasValue(6)) {
            this.f1865g = c(context, a2, typedArray.getResourceId(6, 0));
        }
        p2.r();
        boolean z5 = textView.getTransformationMethod() instanceof PasswordTransformationMethod;
        int[] iArr2 = AbstractC0101a.f1282v;
        if (resourceId3 != -1) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(resourceId3, iArr2);
            D0.h hVar = new D0.h(context, obtainStyledAttributes);
            if (!z5 && obtainStyledAttributes.hasValue(14)) {
                z2 = obtainStyledAttributes.getBoolean(14, false);
                z3 = true;
            } else {
                z2 = false;
                z3 = false;
            }
            n(context, hVar);
            int i8 = Build.VERSION.SDK_INT;
            if (obtainStyledAttributes.hasValue(15)) {
                str2 = obtainStyledAttributes.getString(15);
                i7 = 26;
            } else {
                i7 = 26;
                str2 = null;
            }
            if (i8 >= i7 && obtainStyledAttributes.hasValue(13)) {
                str = obtainStyledAttributes.getString(13);
            } else {
                str = null;
            }
            hVar.r();
        } else {
            z2 = false;
            z3 = false;
            str = null;
            str2 = null;
        }
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i2, 0);
        D0.h hVar2 = new D0.h(context, obtainStyledAttributes2);
        if (!z5 && obtainStyledAttributes2.hasValue(14)) {
            z4 = obtainStyledAttributes2.getBoolean(14, false);
            z3 = true;
        } else {
            z4 = z2;
        }
        int i9 = Build.VERSION.SDK_INT;
        if (obtainStyledAttributes2.hasValue(15)) {
            str2 = obtainStyledAttributes2.getString(15);
        }
        if (i9 >= 26 && obtainStyledAttributes2.hasValue(13)) {
            str = obtainStyledAttributes2.getString(13);
        }
        if (i9 >= 28 && obtainStyledAttributes2.hasValue(0) && obtainStyledAttributes2.getDimensionPixelSize(0, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        n(context, hVar2);
        hVar2.r();
        if (!z5 && z3) {
            textView.setAllCaps(z4);
        }
        Typeface typeface = this.f1869l;
        if (typeface != null) {
            if (this.f1868k == -1) {
                textView.setTypeface(typeface, this.f1867j);
            } else {
                textView.setTypeface(typeface);
            }
        }
        if (str != null) {
            X.d(textView, str);
        }
        if (str2 != null) {
            W.b(textView, W.a(str2));
        }
        int[] iArr3 = AbstractC0101a.f1269i;
        C0181i0 c0181i0 = this.f1866i;
        Context context2 = c0181i0.f1937j;
        TypedArray obtainStyledAttributes3 = context2.obtainStyledAttributes(attributeSet, iArr3, i2, 0);
        TextView textView2 = c0181i0.f1936i;
        H.N.g(textView2, textView2.getContext(), iArr3, attributeSet, obtainStyledAttributes3, i2);
        if (obtainStyledAttributes3.hasValue(5)) {
            c0181i0.f1929a = obtainStyledAttributes3.getInt(5, 0);
        }
        if (obtainStyledAttributes3.hasValue(4)) {
            f2 = obtainStyledAttributes3.getDimension(4, -1.0f);
        } else {
            f2 = -1.0f;
        }
        if (obtainStyledAttributes3.hasValue(2)) {
            f3 = obtainStyledAttributes3.getDimension(2, -1.0f);
        } else {
            f3 = -1.0f;
        }
        if (obtainStyledAttributes3.hasValue(1)) {
            f4 = obtainStyledAttributes3.getDimension(1, -1.0f);
        } else {
            f4 = -1.0f;
        }
        if (obtainStyledAttributes3.hasValue(3) && (resourceId2 = obtainStyledAttributes3.getResourceId(3, 0)) > 0) {
            TypedArray obtainTypedArray = obtainStyledAttributes3.getResources().obtainTypedArray(resourceId2);
            int length = obtainTypedArray.length();
            int[] iArr4 = new int[length];
            if (length > 0) {
                for (int i10 = 0; i10 < length; i10++) {
                    iArr4[i10] = obtainTypedArray.getDimensionPixelSize(i10, -1);
                }
                c0181i0.f1934f = C0181i0.b(iArr4);
                c0181i0.i();
            }
            obtainTypedArray.recycle();
        }
        obtainStyledAttributes3.recycle();
        if (c0181i0.j()) {
            if (c0181i0.f1929a == 1) {
                if (!c0181i0.f1935g) {
                    DisplayMetrics displayMetrics = context2.getResources().getDisplayMetrics();
                    if (f3 == -1.0f) {
                        i6 = 2;
                        f3 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                    } else {
                        i6 = 2;
                    }
                    if (f4 == -1.0f) {
                        f4 = TypedValue.applyDimension(i6, 112.0f, displayMetrics);
                    }
                    if (f2 == -1.0f) {
                        f2 = 1.0f;
                    }
                    c0181i0.k(f3, f4, f2);
                }
                c0181i0.h();
            }
        } else {
            c0181i0.f1929a = 0;
        }
        if (m1.f1981c && c0181i0.f1929a != 0) {
            int[] iArr5 = c0181i0.f1934f;
            if (iArr5.length > 0) {
                if (X.a(textView) != -1.0f) {
                    X.b(textView, Math.round(c0181i0.f1932d), Math.round(c0181i0.f1933e), Math.round(c0181i0.f1931c), 0);
                } else {
                    X.c(textView, iArr5, 0);
                }
            }
        }
        TypedArray obtainStyledAttributes4 = context.obtainStyledAttributes(attributeSet, iArr3);
        int resourceId4 = obtainStyledAttributes4.getResourceId(8, -1);
        if (resourceId4 != -1) {
            drawable = a2.b(context, resourceId4);
        } else {
            drawable = null;
        }
        int resourceId5 = obtainStyledAttributes4.getResourceId(13, -1);
        if (resourceId5 != -1) {
            drawable2 = a2.b(context, resourceId5);
        } else {
            drawable2 = null;
        }
        int resourceId6 = obtainStyledAttributes4.getResourceId(9, -1);
        if (resourceId6 != -1) {
            drawable3 = a2.b(context, resourceId6);
        } else {
            drawable3 = null;
        }
        int resourceId7 = obtainStyledAttributes4.getResourceId(6, -1);
        if (resourceId7 != -1) {
            drawable4 = a2.b(context, resourceId7);
        } else {
            drawable4 = null;
        }
        int resourceId8 = obtainStyledAttributes4.getResourceId(10, -1);
        if (resourceId8 != -1) {
            drawable5 = a2.b(context, resourceId8);
        } else {
            drawable5 = null;
        }
        int resourceId9 = obtainStyledAttributes4.getResourceId(7, -1);
        if (resourceId9 != -1) {
            drawable6 = a2.b(context, resourceId9);
        } else {
            drawable6 = null;
        }
        if (drawable5 == null && drawable6 == null) {
            if (drawable != null || drawable2 != null || drawable3 != null || drawable4 != null) {
                Drawable[] compoundDrawablesRelative = textView.getCompoundDrawablesRelative();
                Drawable drawable7 = compoundDrawablesRelative[0];
                if (drawable7 == null && compoundDrawablesRelative[2] == null) {
                    Drawable[] compoundDrawables = textView.getCompoundDrawables();
                    if (drawable == null) {
                        drawable = compoundDrawables[0];
                    }
                    if (drawable2 == null) {
                        drawable2 = compoundDrawables[1];
                    }
                    if (drawable3 == null) {
                        drawable3 = compoundDrawables[2];
                    }
                    if (drawable4 == null) {
                        drawable4 = compoundDrawables[3];
                    }
                    textView.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
                } else {
                    if (drawable2 == null) {
                        drawable2 = compoundDrawablesRelative[1];
                    }
                    if (drawable4 == null) {
                        drawable4 = compoundDrawablesRelative[3];
                    }
                    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, compoundDrawablesRelative[2], drawable4);
                }
            }
        } else {
            Drawable[] compoundDrawablesRelative2 = textView.getCompoundDrawablesRelative();
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative2[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative2[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative2[2];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative2[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
        }
        if (obtainStyledAttributes4.hasValue(11)) {
            if (!obtainStyledAttributes4.hasValue(11) || (resourceId = obtainStyledAttributes4.getResourceId(11, 0)) == 0 || (colorStateList = C0.d.t(context, resourceId)) == null) {
                colorStateList = obtainStyledAttributes4.getColorStateList(11);
            }
            textView.setCompoundDrawableTintList(colorStateList);
        }
        if (obtainStyledAttributes4.hasValue(12)) {
            i3 = -1;
            textView.setCompoundDrawableTintMode(AbstractC0191n0.b(obtainStyledAttributes4.getInt(12, -1), null));
        } else {
            i3 = -1;
        }
        int dimensionPixelSize = obtainStyledAttributes4.getDimensionPixelSize(15, i3);
        int dimensionPixelSize2 = obtainStyledAttributes4.getDimensionPixelSize(18, i3);
        if (obtainStyledAttributes4.hasValue(19)) {
            TypedValue peekValue = obtainStyledAttributes4.peekValue(19);
            if (peekValue != null && peekValue.type == 5) {
                int i11 = peekValue.data;
                int i12 = i11 & 15;
                f5 = TypedValue.complexToFloat(i11);
                i5 = i12;
                i4 = -1;
            } else {
                i4 = -1;
                f5 = obtainStyledAttributes4.getDimensionPixelSize(19, -1);
                i5 = -1;
            }
        } else {
            i4 = -1;
            i5 = -1;
            f5 = -1.0f;
        }
        obtainStyledAttributes4.recycle();
        if (dimensionPixelSize != i4) {
            C0.f.y(textView, dimensionPixelSize);
        }
        if (dimensionPixelSize2 != i4) {
            C0.f.z(textView, dimensionPixelSize2);
        }
        if (f5 != -1.0f) {
            if (i5 == i4) {
                C0.f.A(textView, (int) f5);
            } else if (Build.VERSION.SDK_INT >= 34) {
                AbstractC0029x.i(textView, i5, f5);
            } else {
                C0.f.A(textView, Math.round(TypedValue.applyDimension(i5, f5, textView.getResources().getDisplayMetrics())));
            }
        }
    }

    public final void g(Context context, int i2) {
        String string;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i2, AbstractC0101a.f1282v);
        D0.h hVar = new D0.h(context, obtainStyledAttributes);
        boolean hasValue = obtainStyledAttributes.hasValue(14);
        TextView textView = this.f1859a;
        if (hasValue) {
            textView.setAllCaps(obtainStyledAttributes.getBoolean(14, false));
        }
        int i3 = Build.VERSION.SDK_INT;
        if (obtainStyledAttributes.hasValue(0) && obtainStyledAttributes.getDimensionPixelSize(0, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        n(context, hVar);
        if (i3 >= 26 && obtainStyledAttributes.hasValue(13) && (string = obtainStyledAttributes.getString(13)) != null) {
            X.d(textView, string);
        }
        hVar.r();
        Typeface typeface = this.f1869l;
        if (typeface != null) {
            textView.setTypeface(typeface, this.f1867j);
        }
    }

    public final void i(int i2, int i3, int i4, int i5) {
        C0181i0 c0181i0 = this.f1866i;
        if (c0181i0.j()) {
            DisplayMetrics displayMetrics = c0181i0.f1937j.getResources().getDisplayMetrics();
            c0181i0.k(TypedValue.applyDimension(i5, i2, displayMetrics), TypedValue.applyDimension(i5, i3, displayMetrics), TypedValue.applyDimension(i5, i4, displayMetrics));
            if (c0181i0.h()) {
                c0181i0.a();
            }
        }
    }

    public final void j(int[] iArr, int i2) {
        C0181i0 c0181i0 = this.f1866i;
        if (c0181i0.j()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i2 == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = c0181i0.f1937j.getResources().getDisplayMetrics();
                    for (int i3 = 0; i3 < length; i3++) {
                        iArr2[i3] = Math.round(TypedValue.applyDimension(i2, iArr[i3], displayMetrics));
                    }
                }
                c0181i0.f1934f = C0181i0.b(iArr2);
                if (!c0181i0.i()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                c0181i0.f1935g = false;
            }
            if (c0181i0.h()) {
                c0181i0.a();
            }
        }
    }

    public final void k(int i2) {
        C0181i0 c0181i0 = this.f1866i;
        if (c0181i0.j()) {
            if (i2 != 0) {
                if (i2 == 1) {
                    DisplayMetrics displayMetrics = c0181i0.f1937j.getResources().getDisplayMetrics();
                    c0181i0.k(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
                    if (c0181i0.h()) {
                        c0181i0.a();
                        return;
                    }
                    return;
                }
                throw new IllegalArgumentException(A.e.a("Unknown auto-size text type: ", i2));
            }
            c0181i0.f1929a = 0;
            c0181i0.f1932d = -1.0f;
            c0181i0.f1933e = -1.0f;
            c0181i0.f1931c = -1.0f;
            c0181i0.f1934f = new int[0];
            c0181i0.f1930b = false;
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, k.U0] */
    public final void l(ColorStateList colorStateList) {
        boolean z2;
        if (this.h == null) {
            this.h = new Object();
        }
        U0 u02 = this.h;
        u02.f1851c = colorStateList;
        if (colorStateList != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        u02.f1850b = z2;
        this.f1860b = u02;
        this.f1861c = u02;
        this.f1862d = u02;
        this.f1863e = u02;
        this.f1864f = u02;
        this.f1865g = u02;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, k.U0] */
    public final void m(PorterDuff.Mode mode) {
        boolean z2;
        if (this.h == null) {
            this.h = new Object();
        }
        U0 u02 = this.h;
        u02.f1852d = mode;
        if (mode != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        u02.f1849a = z2;
        this.f1860b = u02;
        this.f1861c = u02;
        this.f1862d = u02;
        this.f1863e = u02;
        this.f1864f = u02;
        this.f1865g = u02;
    }

    public final void n(Context context, D0.h hVar) {
        String string;
        boolean z2;
        boolean z3;
        int i2 = this.f1867j;
        TypedArray typedArray = (TypedArray) hVar.f259c;
        this.f1867j = typedArray.getInt(2, i2);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 28) {
            int i4 = typedArray.getInt(11, -1);
            this.f1868k = i4;
            if (i4 != -1) {
                this.f1867j &= 2;
            }
        }
        int i5 = 10;
        boolean z4 = false;
        if (!typedArray.hasValue(10) && !typedArray.hasValue(12)) {
            if (typedArray.hasValue(1)) {
                this.f1870m = false;
                int i6 = typedArray.getInt(1, 1);
                if (i6 != 1) {
                    if (i6 != 2) {
                        if (i6 == 3) {
                            this.f1869l = Typeface.MONOSPACE;
                            return;
                        }
                        return;
                    }
                    this.f1869l = Typeface.SERIF;
                    return;
                }
                this.f1869l = Typeface.SANS_SERIF;
                return;
            }
            return;
        }
        this.f1869l = null;
        if (typedArray.hasValue(12)) {
            i5 = 12;
        }
        int i7 = this.f1868k;
        int i8 = this.f1867j;
        if (!context.isRestricted()) {
            try {
                Typeface l2 = hVar.l(i5, this.f1867j, new U(this, i7, i8, new WeakReference(this.f1859a)));
                if (l2 != null) {
                    if (i3 >= 28 && this.f1868k != -1) {
                        Typeface create = Typeface.create(l2, 0);
                        int i9 = this.f1868k;
                        if ((this.f1867j & 2) != 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        this.f1869l = Y.a(create, i9, z3);
                    } else {
                        this.f1869l = l2;
                    }
                }
                if (this.f1869l == null) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                this.f1870m = z2;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.f1869l == null && (string = typedArray.getString(i5)) != null) {
            if (Build.VERSION.SDK_INT >= 28 && this.f1868k != -1) {
                Typeface create2 = Typeface.create(string, 0);
                int i10 = this.f1868k;
                if ((this.f1867j & 2) != 0) {
                    z4 = true;
                }
                this.f1869l = Y.a(create2, i10, z4);
                return;
            }
            this.f1869l = Typeface.create(string, this.f1867j);
        }
    }
}

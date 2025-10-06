package androidx.constraintlayout.widget;

import B0.C;
import D0.h;
import E.c;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.conscrypt.FileClientSessionCache;
import org.conscrypt.ct.CTConstants;
import org.xmlpull.v1.XmlPullParserException;
import q.C0232c;
import s.C0234a;
import s.C0235b;
import s.C0237d;
import s.e;
import v.AbstractC0253c;
import v.C0251a;
import v.C0252b;
import v.d;
import v.f;
import v.g;
import v.i;
import v.j;
import v.l;
import v.m;
import v.n;
import v.o;
import v.p;
import v.r;
import v.s;

/* loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {

    /* renamed from: r, reason: collision with root package name */
    public static s f953r;

    /* renamed from: a, reason: collision with root package name */
    public final SparseArray f954a;

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f955b;

    /* renamed from: c, reason: collision with root package name */
    public final e f956c;

    /* renamed from: d, reason: collision with root package name */
    public int f957d;

    /* renamed from: e, reason: collision with root package name */
    public int f958e;

    /* renamed from: f, reason: collision with root package name */
    public int f959f;

    /* renamed from: g, reason: collision with root package name */
    public int f960g;
    public boolean h;

    /* renamed from: i, reason: collision with root package name */
    public int f961i;

    /* renamed from: j, reason: collision with root package name */
    public n f962j;

    /* renamed from: k, reason: collision with root package name */
    public c f963k;

    /* renamed from: l, reason: collision with root package name */
    public final int f964l;

    /* renamed from: m, reason: collision with root package name */
    public HashMap f965m;

    /* renamed from: n, reason: collision with root package name */
    public final SparseArray f966n;

    /* renamed from: o, reason: collision with root package name */
    public final f f967o;

    /* renamed from: p, reason: collision with root package name */
    public int f968p;

    /* renamed from: q, reason: collision with root package name */
    public int f969q;

    /* JADX WARN: Type inference failed for: r0v1, types: [s.d, s.e] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object, t.e] */
    /* JADX WARN: Type inference failed for: r5v0, types: [t.b, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v3, types: [t.b, java.lang.Object] */
    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        SparseArray sparseArray = new SparseArray();
        this.f954a = sparseArray;
        this.f955b = new ArrayList(4);
        ?? c0237d = new C0237d();
        c0237d.f2336p0 = new ArrayList();
        c0237d.f2337q0 = new h(c0237d);
        ?? obj = new Object();
        obj.f2365b = true;
        obj.f2366c = true;
        obj.f2368e = new ArrayList();
        new ArrayList();
        obj.f2369f = null;
        obj.f2370g = new Object();
        obj.h = new ArrayList();
        obj.f2364a = c0237d;
        obj.f2367d = c0237d;
        c0237d.f2338r0 = obj;
        c0237d.t0 = null;
        c0237d.f2340u0 = false;
        c0237d.f2341v0 = new C0232c();
        c0237d.f2344y0 = 0;
        c0237d.f2345z0 = 0;
        c0237d.f2326A0 = new C0235b[4];
        c0237d.f2327B0 = new C0235b[4];
        c0237d.f2328C0 = 257;
        c0237d.f2329D0 = false;
        c0237d.f2330E0 = false;
        c0237d.F0 = null;
        c0237d.f2331G0 = null;
        c0237d.f2332H0 = null;
        c0237d.f2333I0 = null;
        c0237d.f2334J0 = new HashSet();
        c0237d.f2335K0 = new Object();
        this.f956c = c0237d;
        this.f957d = 0;
        this.f958e = 0;
        this.f959f = Integer.MAX_VALUE;
        this.f960g = Integer.MAX_VALUE;
        this.h = true;
        this.f961i = 257;
        this.f962j = null;
        this.f963k = null;
        this.f964l = -1;
        this.f965m = new HashMap();
        this.f966n = new SparseArray();
        f fVar = new f(this, this);
        this.f967o = fVar;
        this.f968p = 0;
        this.f969q = 0;
        c0237d.f2298e0 = this;
        c0237d.t0 = fVar;
        obj.f2369f = fVar;
        sparseArray.put(getId(), this);
        this.f962j = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, r.f2798b, 0, 0);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == 16) {
                    this.f957d = obtainStyledAttributes.getDimensionPixelOffset(index, this.f957d);
                } else if (index == 17) {
                    this.f958e = obtainStyledAttributes.getDimensionPixelOffset(index, this.f958e);
                } else if (index == 14) {
                    this.f959f = obtainStyledAttributes.getDimensionPixelOffset(index, this.f959f);
                } else if (index == 15) {
                    this.f960g = obtainStyledAttributes.getDimensionPixelOffset(index, this.f960g);
                } else if (index == 113) {
                    this.f961i = obtainStyledAttributes.getInt(index, this.f961i);
                } else if (index == 56) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            j(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.f963k = null;
                        }
                    }
                } else if (index == 34) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, 0);
                    try {
                        n nVar = new n();
                        this.f962j = nVar;
                        nVar.d(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.f962j = null;
                    }
                    this.f964l = resourceId2;
                }
            }
            obtainStyledAttributes.recycle();
        }
        c0237d.f2328C0 = this.f961i;
        C0232c.f2189p = c0237d.S(512);
    }

    private int getPaddingWidth() {
        int max = Math.max(0, getPaddingRight()) + Math.max(0, getPaddingLeft());
        int max2 = Math.max(0, getPaddingEnd()) + Math.max(0, getPaddingStart());
        if (max2 > 0) {
            return max2;
        }
        return max;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, v.s] */
    public static s getSharedValues() {
        if (f953r == null) {
            ?? obj = new Object();
            new SparseIntArray();
            new HashMap();
            f953r = obj;
        }
        return f953r;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.ViewGroup$MarginLayoutParams, v.e] */
    public static v.e h() {
        ?? marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
        marginLayoutParams.f2633a = -1;
        marginLayoutParams.f2635b = -1;
        marginLayoutParams.f2637c = -1.0f;
        marginLayoutParams.f2639d = true;
        marginLayoutParams.f2641e = -1;
        marginLayoutParams.f2643f = -1;
        marginLayoutParams.f2645g = -1;
        marginLayoutParams.h = -1;
        marginLayoutParams.f2646i = -1;
        marginLayoutParams.f2648j = -1;
        marginLayoutParams.f2650k = -1;
        marginLayoutParams.f2652l = -1;
        marginLayoutParams.f2654m = -1;
        marginLayoutParams.f2656n = -1;
        marginLayoutParams.f2658o = -1;
        marginLayoutParams.f2660p = -1;
        marginLayoutParams.f2662q = 0;
        marginLayoutParams.f2663r = 0.0f;
        marginLayoutParams.f2664s = -1;
        marginLayoutParams.f2665t = -1;
        marginLayoutParams.f2666u = -1;
        marginLayoutParams.f2667v = -1;
        marginLayoutParams.f2668w = Integer.MIN_VALUE;
        marginLayoutParams.f2669x = Integer.MIN_VALUE;
        marginLayoutParams.y = Integer.MIN_VALUE;
        marginLayoutParams.f2670z = Integer.MIN_VALUE;
        marginLayoutParams.f2607A = Integer.MIN_VALUE;
        marginLayoutParams.f2608B = Integer.MIN_VALUE;
        marginLayoutParams.f2609C = Integer.MIN_VALUE;
        marginLayoutParams.f2610D = 0;
        marginLayoutParams.f2611E = 0.5f;
        marginLayoutParams.f2612F = 0.5f;
        marginLayoutParams.f2613G = null;
        marginLayoutParams.f2614H = -1.0f;
        marginLayoutParams.f2615I = -1.0f;
        marginLayoutParams.f2616J = 0;
        marginLayoutParams.f2617K = 0;
        marginLayoutParams.f2618L = 0;
        marginLayoutParams.f2619M = 0;
        marginLayoutParams.f2620N = 0;
        marginLayoutParams.f2621O = 0;
        marginLayoutParams.f2622P = 0;
        marginLayoutParams.f2623Q = 0;
        marginLayoutParams.f2624R = 1.0f;
        marginLayoutParams.f2625S = 1.0f;
        marginLayoutParams.f2626T = -1;
        marginLayoutParams.f2627U = -1;
        marginLayoutParams.f2628V = -1;
        marginLayoutParams.f2629W = false;
        marginLayoutParams.f2630X = false;
        marginLayoutParams.f2631Y = null;
        marginLayoutParams.f2632Z = 0;
        marginLayoutParams.f2634a0 = true;
        marginLayoutParams.f2636b0 = true;
        marginLayoutParams.f2638c0 = false;
        marginLayoutParams.f2640d0 = false;
        marginLayoutParams.f2642e0 = false;
        marginLayoutParams.f2644f0 = -1;
        marginLayoutParams.g0 = -1;
        marginLayoutParams.h0 = -1;
        marginLayoutParams.f2647i0 = -1;
        marginLayoutParams.f2649j0 = Integer.MIN_VALUE;
        marginLayoutParams.f2651k0 = Integer.MIN_VALUE;
        marginLayoutParams.f2653l0 = 0.5f;
        marginLayoutParams.f2661p0 = new C0237d();
        return marginLayoutParams;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof v.e;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList arrayList = this.f955b;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                ((AbstractC0253c) arrayList.get(i2)).getClass();
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            float width = getWidth();
            float height = getHeight();
            int childCount = getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] split = ((String) tag).split(",");
                    if (split.length == 4) {
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        int parseInt3 = Integer.parseInt(split[2]);
                        int i4 = (int) ((parseInt / 1080.0f) * width);
                        int i5 = (int) ((parseInt2 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f2 = i4;
                        float f3 = i5;
                        float f4 = i4 + ((int) ((parseInt3 / 1080.0f) * width));
                        canvas.drawLine(f2, f3, f4, f3, paint);
                        float parseInt4 = i5 + ((int) ((Integer.parseInt(split[3]) / 1920.0f) * height));
                        canvas.drawLine(f4, f3, f4, parseInt4, paint);
                        canvas.drawLine(f4, parseInt4, f2, parseInt4, paint);
                        canvas.drawLine(f2, parseInt4, f2, f3, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f2, f3, f4, parseInt4, paint);
                        canvas.drawLine(f2, parseInt4, f4, f3, paint);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public final void forceLayout() {
        this.h = true;
        super.forceLayout();
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:109:0x02e9 -> B:80:0x02d8). Please report as a decompilation issue!!! */
    public final void g(boolean z2, View view, C0237d c0237d, v.e eVar, SparseArray sparseArray) {
        int i2;
        float f2;
        C0237d c0237d2;
        C0237d c0237d3;
        C0237d c0237d4;
        C0237d c0237d5;
        int i3;
        int i4;
        int i5;
        float f3;
        int i6;
        int i7;
        eVar.a();
        c0237d.f2300f0 = view.getVisibility();
        c0237d.f2298e0 = view;
        if (view instanceof AbstractC0253c) {
            boolean z3 = this.f956c.f2340u0;
            C0251a c0251a = (C0251a) ((AbstractC0253c) view);
            int i8 = c0251a.h;
            c0251a.f2590i = i8;
            if (z3) {
                if (i8 == 5) {
                    c0251a.f2590i = 1;
                } else if (i8 == 6) {
                    c0251a.f2590i = 0;
                }
            } else if (i8 == 5) {
                c0251a.f2590i = 0;
            } else if (i8 == 6) {
                c0251a.f2590i = 1;
            }
            if (c0237d instanceof C0234a) {
                ((C0234a) c0237d).f2236r0 = c0251a.f2590i;
            }
        }
        int i9 = -1;
        if (eVar.f2640d0) {
            s.f fVar = (s.f) c0237d;
            int i10 = eVar.f2655m0;
            int i11 = eVar.f2657n0;
            float f4 = eVar.f2659o0;
            if (f4 != -1.0f) {
                if (f4 > -1.0f) {
                    fVar.f2346p0 = f4;
                    fVar.f2347q0 = -1;
                    fVar.f2348r0 = -1;
                    return;
                }
                return;
            }
            if (i10 != -1) {
                if (i10 > -1) {
                    fVar.f2346p0 = -1.0f;
                    fVar.f2347q0 = i10;
                    fVar.f2348r0 = -1;
                    return;
                }
                return;
            }
            if (i11 != -1 && i11 > -1) {
                fVar.f2346p0 = -1.0f;
                fVar.f2347q0 = -1;
                fVar.f2348r0 = i11;
                return;
            }
            return;
        }
        int i12 = eVar.f2644f0;
        int i13 = eVar.g0;
        int i14 = eVar.h0;
        int i15 = eVar.f2647i0;
        int i16 = eVar.f2649j0;
        int i17 = eVar.f2651k0;
        float f5 = eVar.f2653l0;
        int i18 = eVar.f2660p;
        if (i18 != -1) {
            C0237d c0237d6 = (C0237d) sparseArray.get(i18);
            if (c0237d6 != null) {
                float f6 = eVar.f2663r;
                i7 = 4;
                c0237d.t(7, 7, eVar.f2662q, 0, c0237d6);
                c0237d.f2266D = f6;
            } else {
                i7 = 4;
            }
            i2 = i7;
        } else {
            if (i12 != -1) {
                C0237d c0237d7 = (C0237d) sparseArray.get(i12);
                if (c0237d7 != null) {
                    i2 = 4;
                    f2 = f5;
                    c0237d.t(2, 2, ((ViewGroup.MarginLayoutParams) eVar).leftMargin, i16, c0237d7);
                } else {
                    i2 = 4;
                    f2 = f5;
                }
            } else {
                i2 = 4;
                f2 = f5;
                if (i13 != -1 && (c0237d2 = (C0237d) sparseArray.get(i13)) != null) {
                    c0237d.t(2, 4, ((ViewGroup.MarginLayoutParams) eVar).leftMargin, i16, c0237d2);
                }
            }
            if (i14 != -1) {
                C0237d c0237d8 = (C0237d) sparseArray.get(i14);
                if (c0237d8 != null) {
                    c0237d.t(i2, 2, ((ViewGroup.MarginLayoutParams) eVar).rightMargin, i17, c0237d8);
                }
            } else if (i15 != -1 && (c0237d3 = (C0237d) sparseArray.get(i15)) != null) {
                c0237d.t(i2, i2, ((ViewGroup.MarginLayoutParams) eVar).rightMargin, i17, c0237d3);
            }
            int i19 = eVar.f2646i;
            if (i19 != -1) {
                C0237d c0237d9 = (C0237d) sparseArray.get(i19);
                if (c0237d9 != null) {
                    c0237d.t(3, 3, ((ViewGroup.MarginLayoutParams) eVar).topMargin, eVar.f2669x, c0237d9);
                }
            } else {
                int i20 = eVar.f2648j;
                if (i20 != -1 && (c0237d4 = (C0237d) sparseArray.get(i20)) != null) {
                    c0237d.t(3, 5, ((ViewGroup.MarginLayoutParams) eVar).topMargin, eVar.f2669x, c0237d4);
                }
            }
            int i21 = eVar.f2650k;
            if (i21 != -1) {
                C0237d c0237d10 = (C0237d) sparseArray.get(i21);
                if (c0237d10 != null) {
                    c0237d.t(5, 3, ((ViewGroup.MarginLayoutParams) eVar).bottomMargin, eVar.f2670z, c0237d10);
                }
            } else {
                int i22 = eVar.f2652l;
                if (i22 != -1 && (c0237d5 = (C0237d) sparseArray.get(i22)) != null) {
                    c0237d.t(5, 5, ((ViewGroup.MarginLayoutParams) eVar).bottomMargin, eVar.f2670z, c0237d5);
                }
            }
            int i23 = eVar.f2654m;
            if (i23 != -1) {
                l(c0237d, eVar, sparseArray, i23, 6);
            } else {
                int i24 = eVar.f2656n;
                if (i24 != -1) {
                    l(c0237d, eVar, sparseArray, i24, 3);
                } else {
                    int i25 = eVar.f2658o;
                    if (i25 != -1) {
                        l(c0237d, eVar, sparseArray, i25, 5);
                    }
                }
            }
            float f7 = f2;
            if (f7 >= 0.0f) {
                c0237d.f2294c0 = f7;
            }
            float f8 = eVar.f2612F;
            if (f8 >= 0.0f) {
                c0237d.f2296d0 = f8;
            }
        }
        if (z2 && ((i6 = eVar.f2626T) != -1 || eVar.f2627U != -1)) {
            int i26 = eVar.f2627U;
            c0237d.f2286X = i6;
            c0237d.f2287Y = i26;
        }
        if (!eVar.f2634a0) {
            if (((ViewGroup.MarginLayoutParams) eVar).width == -1) {
                if (eVar.f2629W) {
                    c0237d.I(3);
                } else {
                    c0237d.I(4);
                }
                c0237d.g(2).f2261g = ((ViewGroup.MarginLayoutParams) eVar).leftMargin;
                c0237d.g(i2).f2261g = ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
            } else {
                c0237d.I(3);
                c0237d.K(0);
            }
        } else {
            c0237d.I(1);
            c0237d.K(((ViewGroup.MarginLayoutParams) eVar).width);
            if (((ViewGroup.MarginLayoutParams) eVar).width == -2) {
                c0237d.I(2);
            }
        }
        if (!eVar.f2636b0) {
            if (((ViewGroup.MarginLayoutParams) eVar).height == -1) {
                if (eVar.f2630X) {
                    c0237d.J(3);
                } else {
                    c0237d.J(4);
                }
                c0237d.g(3).f2261g = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                c0237d.g(5).f2261g = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
            } else {
                c0237d.J(3);
                c0237d.H(0);
            }
        } else {
            c0237d.J(1);
            c0237d.H(((ViewGroup.MarginLayoutParams) eVar).height);
            if (((ViewGroup.MarginLayoutParams) eVar).height == -2) {
                c0237d.J(2);
            }
        }
        String str = eVar.f2613G;
        if (str != null && str.length() != 0) {
            int length = str.length();
            int indexOf = str.indexOf(44);
            if (indexOf > 0 && indexOf < length - 1) {
                String substring = str.substring(0, indexOf);
                if (substring.equalsIgnoreCase("W")) {
                    i4 = 1;
                    i9 = 0;
                } else if (substring.equalsIgnoreCase("H")) {
                    i4 = 1;
                    i9 = 1;
                } else {
                    i4 = 1;
                }
                i5 = indexOf + i4;
            } else {
                i4 = 1;
                i5 = 0;
            }
            int indexOf2 = str.indexOf(58);
            if (indexOf2 >= 0 && indexOf2 < length - i4) {
                String substring2 = str.substring(i5, indexOf2);
                String substring3 = str.substring(indexOf2 + i4);
                if (substring2.length() > 0 && substring3.length() > 0) {
                    float parseFloat = Float.parseFloat(substring2);
                    float parseFloat2 = Float.parseFloat(substring3);
                    if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                        if (i9 == 1) {
                            f3 = Math.abs(parseFloat2 / parseFloat);
                        } else {
                            f3 = Math.abs(parseFloat / parseFloat2);
                        }
                    }
                }
                f3 = 0.0f;
            } else {
                String substring4 = str.substring(i5);
                if (substring4.length() > 0) {
                    f3 = Float.parseFloat(substring4);
                }
                f3 = 0.0f;
            }
            if (f3 > 0.0f) {
                c0237d.f2284V = f3;
                c0237d.f2285W = i9;
            }
        } else {
            c0237d.f2284V = 0.0f;
        }
        float f9 = eVar.f2614H;
        float[] fArr = c0237d.f2305j0;
        fArr[0] = f9;
        fArr[1] = eVar.f2615I;
        c0237d.h0 = eVar.f2616J;
        c0237d.f2303i0 = eVar.f2617K;
        int i27 = eVar.f2632Z;
        if (i27 >= 0 && i27 <= 3) {
            c0237d.f2317q = i27;
        }
        int i28 = eVar.f2618L;
        int i29 = eVar.f2620N;
        int i30 = eVar.f2622P;
        float f10 = eVar.f2624R;
        c0237d.f2318r = i28;
        c0237d.f2321u = i29;
        if (i30 == Integer.MAX_VALUE) {
            i30 = 0;
        }
        c0237d.f2322v = i30;
        c0237d.f2323w = f10;
        if (f10 > 0.0f && f10 < 1.0f && i28 == 0) {
            c0237d.f2318r = 2;
        }
        int i31 = eVar.f2619M;
        int i32 = eVar.f2621O;
        int i33 = eVar.f2623Q;
        float f11 = eVar.f2625S;
        c0237d.f2319s = i31;
        c0237d.f2324x = i32;
        if (i33 == Integer.MAX_VALUE) {
            i3 = 0;
        } else {
            i3 = i33;
        }
        c0237d.y = i3;
        c0237d.f2325z = f11;
        if (f11 > 0.0f && f11 < 1.0f && i31 == 0) {
            c0237d.f2319s = 2;
        }
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return h();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.ViewGroup$LayoutParams, android.view.ViewGroup$MarginLayoutParams, java.lang.Object, v.e] */
    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        ?? marginLayoutParams = new ViewGroup.MarginLayoutParams(context, attributeSet);
        marginLayoutParams.f2633a = -1;
        marginLayoutParams.f2635b = -1;
        marginLayoutParams.f2637c = -1.0f;
        marginLayoutParams.f2639d = true;
        marginLayoutParams.f2641e = -1;
        marginLayoutParams.f2643f = -1;
        marginLayoutParams.f2645g = -1;
        marginLayoutParams.h = -1;
        marginLayoutParams.f2646i = -1;
        marginLayoutParams.f2648j = -1;
        marginLayoutParams.f2650k = -1;
        marginLayoutParams.f2652l = -1;
        marginLayoutParams.f2654m = -1;
        marginLayoutParams.f2656n = -1;
        marginLayoutParams.f2658o = -1;
        marginLayoutParams.f2660p = -1;
        marginLayoutParams.f2662q = 0;
        marginLayoutParams.f2663r = 0.0f;
        marginLayoutParams.f2664s = -1;
        marginLayoutParams.f2665t = -1;
        marginLayoutParams.f2666u = -1;
        marginLayoutParams.f2667v = -1;
        marginLayoutParams.f2668w = Integer.MIN_VALUE;
        marginLayoutParams.f2669x = Integer.MIN_VALUE;
        marginLayoutParams.y = Integer.MIN_VALUE;
        marginLayoutParams.f2670z = Integer.MIN_VALUE;
        marginLayoutParams.f2607A = Integer.MIN_VALUE;
        marginLayoutParams.f2608B = Integer.MIN_VALUE;
        marginLayoutParams.f2609C = Integer.MIN_VALUE;
        marginLayoutParams.f2610D = 0;
        marginLayoutParams.f2611E = 0.5f;
        marginLayoutParams.f2612F = 0.5f;
        marginLayoutParams.f2613G = null;
        marginLayoutParams.f2614H = -1.0f;
        marginLayoutParams.f2615I = -1.0f;
        marginLayoutParams.f2616J = 0;
        marginLayoutParams.f2617K = 0;
        marginLayoutParams.f2618L = 0;
        marginLayoutParams.f2619M = 0;
        marginLayoutParams.f2620N = 0;
        marginLayoutParams.f2621O = 0;
        marginLayoutParams.f2622P = 0;
        marginLayoutParams.f2623Q = 0;
        marginLayoutParams.f2624R = 1.0f;
        marginLayoutParams.f2625S = 1.0f;
        marginLayoutParams.f2626T = -1;
        marginLayoutParams.f2627U = -1;
        marginLayoutParams.f2628V = -1;
        marginLayoutParams.f2629W = false;
        marginLayoutParams.f2630X = false;
        marginLayoutParams.f2631Y = null;
        marginLayoutParams.f2632Z = 0;
        marginLayoutParams.f2634a0 = true;
        marginLayoutParams.f2636b0 = true;
        marginLayoutParams.f2638c0 = false;
        marginLayoutParams.f2640d0 = false;
        marginLayoutParams.f2642e0 = false;
        marginLayoutParams.f2644f0 = -1;
        marginLayoutParams.g0 = -1;
        marginLayoutParams.h0 = -1;
        marginLayoutParams.f2647i0 = -1;
        marginLayoutParams.f2649j0 = Integer.MIN_VALUE;
        marginLayoutParams.f2651k0 = Integer.MIN_VALUE;
        marginLayoutParams.f2653l0 = 0.5f;
        marginLayoutParams.f2661p0 = new C0237d();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, r.f2798b);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            int i3 = d.f2606a.get(index);
            switch (i3) {
                case 1:
                    marginLayoutParams.f2628V = obtainStyledAttributes.getInt(index, marginLayoutParams.f2628V);
                    break;
                case 2:
                    int resourceId = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2660p);
                    marginLayoutParams.f2660p = resourceId;
                    if (resourceId == -1) {
                        marginLayoutParams.f2660p = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                    marginLayoutParams.f2662q = obtainStyledAttributes.getDimensionPixelSize(index, marginLayoutParams.f2662q);
                    break;
                case 4:
                    float f2 = obtainStyledAttributes.getFloat(index, marginLayoutParams.f2663r) % 360.0f;
                    marginLayoutParams.f2663r = f2;
                    if (f2 < 0.0f) {
                        marginLayoutParams.f2663r = (360.0f - f2) % 360.0f;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    marginLayoutParams.f2633a = obtainStyledAttributes.getDimensionPixelOffset(index, marginLayoutParams.f2633a);
                    break;
                case 6:
                    marginLayoutParams.f2635b = obtainStyledAttributes.getDimensionPixelOffset(index, marginLayoutParams.f2635b);
                    break;
                case 7:
                    marginLayoutParams.f2637c = obtainStyledAttributes.getFloat(index, marginLayoutParams.f2637c);
                    break;
                case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2641e);
                    marginLayoutParams.f2641e = resourceId2;
                    if (resourceId2 == -1) {
                        marginLayoutParams.f2641e = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    int resourceId3 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2643f);
                    marginLayoutParams.f2643f = resourceId3;
                    if (resourceId3 == -1) {
                        marginLayoutParams.f2643f = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 10:
                    int resourceId4 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2645g);
                    marginLayoutParams.f2645g = resourceId4;
                    if (resourceId4 == -1) {
                        marginLayoutParams.f2645g = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    int resourceId5 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.h);
                    marginLayoutParams.h = resourceId5;
                    if (resourceId5 == -1) {
                        marginLayoutParams.h = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case FileClientSessionCache.MAX_SIZE /* 12 */:
                    int resourceId6 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2646i);
                    marginLayoutParams.f2646i = resourceId6;
                    if (resourceId6 == -1) {
                        marginLayoutParams.f2646i = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    int resourceId7 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2648j);
                    marginLayoutParams.f2648j = resourceId7;
                    if (resourceId7 == -1) {
                        marginLayoutParams.f2648j = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    int resourceId8 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2650k);
                    marginLayoutParams.f2650k = resourceId8;
                    if (resourceId8 == -1) {
                        marginLayoutParams.f2650k = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    int resourceId9 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2652l);
                    marginLayoutParams.f2652l = resourceId9;
                    if (resourceId9 == -1) {
                        marginLayoutParams.f2652l = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    int resourceId10 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2654m);
                    marginLayoutParams.f2654m = resourceId10;
                    if (resourceId10 == -1) {
                        marginLayoutParams.f2654m = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    int resourceId11 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2664s);
                    marginLayoutParams.f2664s = resourceId11;
                    if (resourceId11 == -1) {
                        marginLayoutParams.f2664s = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 18:
                    int resourceId12 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2665t);
                    marginLayoutParams.f2665t = resourceId12;
                    if (resourceId12 == -1) {
                        marginLayoutParams.f2665t = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 19:
                    int resourceId13 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2666u);
                    marginLayoutParams.f2666u = resourceId13;
                    if (resourceId13 == -1) {
                        marginLayoutParams.f2666u = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 20:
                    int resourceId14 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2667v);
                    marginLayoutParams.f2667v = resourceId14;
                    if (resourceId14 == -1) {
                        marginLayoutParams.f2667v = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 21:
                    marginLayoutParams.f2668w = obtainStyledAttributes.getDimensionPixelSize(index, marginLayoutParams.f2668w);
                    break;
                case 22:
                    marginLayoutParams.f2669x = obtainStyledAttributes.getDimensionPixelSize(index, marginLayoutParams.f2669x);
                    break;
                case 23:
                    marginLayoutParams.y = obtainStyledAttributes.getDimensionPixelSize(index, marginLayoutParams.y);
                    break;
                case 24:
                    marginLayoutParams.f2670z = obtainStyledAttributes.getDimensionPixelSize(index, marginLayoutParams.f2670z);
                    break;
                case 25:
                    marginLayoutParams.f2607A = obtainStyledAttributes.getDimensionPixelSize(index, marginLayoutParams.f2607A);
                    break;
                case 26:
                    marginLayoutParams.f2608B = obtainStyledAttributes.getDimensionPixelSize(index, marginLayoutParams.f2608B);
                    break;
                case 27:
                    marginLayoutParams.f2629W = obtainStyledAttributes.getBoolean(index, marginLayoutParams.f2629W);
                    break;
                case 28:
                    marginLayoutParams.f2630X = obtainStyledAttributes.getBoolean(index, marginLayoutParams.f2630X);
                    break;
                case 29:
                    marginLayoutParams.f2611E = obtainStyledAttributes.getFloat(index, marginLayoutParams.f2611E);
                    break;
                case 30:
                    marginLayoutParams.f2612F = obtainStyledAttributes.getFloat(index, marginLayoutParams.f2612F);
                    break;
                case 31:
                    int i4 = obtainStyledAttributes.getInt(index, 0);
                    marginLayoutParams.f2618L = i4;
                    if (i4 == 1) {
                        Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                        break;
                    } else {
                        break;
                    }
                case 32:
                    int i5 = obtainStyledAttributes.getInt(index, 0);
                    marginLayoutParams.f2619M = i5;
                    if (i5 == 1) {
                        Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                        break;
                    } else {
                        break;
                    }
                case 33:
                    try {
                        marginLayoutParams.f2620N = obtainStyledAttributes.getDimensionPixelSize(index, marginLayoutParams.f2620N);
                        break;
                    } catch (Exception unused) {
                        if (obtainStyledAttributes.getInt(index, marginLayoutParams.f2620N) == -2) {
                            marginLayoutParams.f2620N = -2;
                            break;
                        } else {
                            break;
                        }
                    }
                case 34:
                    try {
                        marginLayoutParams.f2622P = obtainStyledAttributes.getDimensionPixelSize(index, marginLayoutParams.f2622P);
                        break;
                    } catch (Exception unused2) {
                        if (obtainStyledAttributes.getInt(index, marginLayoutParams.f2622P) == -2) {
                            marginLayoutParams.f2622P = -2;
                            break;
                        } else {
                            break;
                        }
                    }
                case 35:
                    marginLayoutParams.f2624R = Math.max(0.0f, obtainStyledAttributes.getFloat(index, marginLayoutParams.f2624R));
                    marginLayoutParams.f2618L = 2;
                    break;
                case 36:
                    try {
                        marginLayoutParams.f2621O = obtainStyledAttributes.getDimensionPixelSize(index, marginLayoutParams.f2621O);
                        break;
                    } catch (Exception unused3) {
                        if (obtainStyledAttributes.getInt(index, marginLayoutParams.f2621O) == -2) {
                            marginLayoutParams.f2621O = -2;
                            break;
                        } else {
                            break;
                        }
                    }
                case 37:
                    try {
                        marginLayoutParams.f2623Q = obtainStyledAttributes.getDimensionPixelSize(index, marginLayoutParams.f2623Q);
                        break;
                    } catch (Exception unused4) {
                        if (obtainStyledAttributes.getInt(index, marginLayoutParams.f2623Q) == -2) {
                            marginLayoutParams.f2623Q = -2;
                            break;
                        } else {
                            break;
                        }
                    }
                case 38:
                    marginLayoutParams.f2625S = Math.max(0.0f, obtainStyledAttributes.getFloat(index, marginLayoutParams.f2625S));
                    marginLayoutParams.f2619M = 2;
                    break;
                default:
                    switch (i3) {
                        case 44:
                            n.g(marginLayoutParams, obtainStyledAttributes.getString(index));
                            break;
                        case 45:
                            marginLayoutParams.f2614H = obtainStyledAttributes.getFloat(index, marginLayoutParams.f2614H);
                            break;
                        case 46:
                            marginLayoutParams.f2615I = obtainStyledAttributes.getFloat(index, marginLayoutParams.f2615I);
                            break;
                        case 47:
                            marginLayoutParams.f2616J = obtainStyledAttributes.getInt(index, 0);
                            break;
                        case 48:
                            marginLayoutParams.f2617K = obtainStyledAttributes.getInt(index, 0);
                            break;
                        case 49:
                            marginLayoutParams.f2626T = obtainStyledAttributes.getDimensionPixelOffset(index, marginLayoutParams.f2626T);
                            break;
                        case 50:
                            marginLayoutParams.f2627U = obtainStyledAttributes.getDimensionPixelOffset(index, marginLayoutParams.f2627U);
                            break;
                        case 51:
                            marginLayoutParams.f2631Y = obtainStyledAttributes.getString(index);
                            break;
                        case 52:
                            int resourceId15 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2656n);
                            marginLayoutParams.f2656n = resourceId15;
                            if (resourceId15 == -1) {
                                marginLayoutParams.f2656n = obtainStyledAttributes.getInt(index, -1);
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            int resourceId16 = obtainStyledAttributes.getResourceId(index, marginLayoutParams.f2658o);
                            marginLayoutParams.f2658o = resourceId16;
                            if (resourceId16 == -1) {
                                marginLayoutParams.f2658o = obtainStyledAttributes.getInt(index, -1);
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            marginLayoutParams.f2610D = obtainStyledAttributes.getDimensionPixelSize(index, marginLayoutParams.f2610D);
                            break;
                        case 55:
                            marginLayoutParams.f2609C = obtainStyledAttributes.getDimensionPixelSize(index, marginLayoutParams.f2609C);
                            break;
                        default:
                            switch (i3) {
                                case 64:
                                    n.f(marginLayoutParams, obtainStyledAttributes, index, 0);
                                    break;
                                case 65:
                                    n.f(marginLayoutParams, obtainStyledAttributes, index, 1);
                                    break;
                                case 66:
                                    marginLayoutParams.f2632Z = obtainStyledAttributes.getInt(index, marginLayoutParams.f2632Z);
                                    break;
                                case 67:
                                    marginLayoutParams.f2639d = obtainStyledAttributes.getBoolean(index, marginLayoutParams.f2639d);
                                    break;
                            }
                    }
            }
        }
        obtainStyledAttributes.recycle();
        marginLayoutParams.a();
        return marginLayoutParams;
    }

    public int getMaxHeight() {
        return this.f960g;
    }

    public int getMaxWidth() {
        return this.f959f;
    }

    public int getMinHeight() {
        return this.f958e;
    }

    public int getMinWidth() {
        return this.f957d;
    }

    public int getOptimizationLevel() {
        return this.f956c.f2328C0;
    }

    public String getSceneString() {
        int id;
        StringBuilder sb = new StringBuilder();
        e eVar = this.f956c;
        if (eVar.f2304j == null) {
            int id2 = getId();
            if (id2 != -1) {
                eVar.f2304j = getContext().getResources().getResourceEntryName(id2);
            } else {
                eVar.f2304j = "parent";
            }
        }
        if (eVar.g0 == null) {
            eVar.g0 = eVar.f2304j;
            Log.v("ConstraintLayout", " setDebugName " + eVar.g0);
        }
        Iterator it = eVar.f2336p0.iterator();
        while (it.hasNext()) {
            C0237d c0237d = (C0237d) it.next();
            View view = c0237d.f2298e0;
            if (view != null) {
                if (c0237d.f2304j == null && (id = view.getId()) != -1) {
                    c0237d.f2304j = getContext().getResources().getResourceEntryName(id);
                }
                if (c0237d.g0 == null) {
                    c0237d.g0 = c0237d.f2304j;
                    Log.v("ConstraintLayout", " setDebugName " + c0237d.g0);
                }
            }
        }
        eVar.l(sb);
        return sb.toString();
    }

    public final C0237d i(View view) {
        if (view == this) {
            return this.f956c;
        }
        if (view != null) {
            if (view.getLayoutParams() instanceof v.e) {
                return ((v.e) view.getLayoutParams()).f2661p0;
            }
            view.setLayoutParams(generateLayoutParams(view.getLayoutParams()));
            if (view.getLayoutParams() instanceof v.e) {
                return ((v.e) view.getLayoutParams()).f2661p0;
            }
            return null;
        }
        return null;
    }

    public final void j(int i2) {
        int eventType;
        C c2;
        Context context = getContext();
        c cVar = new c();
        cVar.f271b = new SparseArray();
        cVar.f272c = new SparseArray();
        XmlResourceParser xml = context.getResources().getXml(i2);
        try {
            eventType = xml.getEventType();
            c2 = null;
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
        while (true) {
            char c3 = 1;
            if (eventType != 1) {
                if (eventType != 0) {
                    if (eventType == 2) {
                        String name = xml.getName();
                        switch (name.hashCode()) {
                            case -1349929691:
                                if (name.equals("ConstraintSet")) {
                                    c3 = 4;
                                    break;
                                }
                                break;
                            case 80204913:
                                if (name.equals("State")) {
                                    c3 = 2;
                                    break;
                                }
                                break;
                            case 1382829617:
                                if (name.equals("StateSet")) {
                                    break;
                                }
                                break;
                            case 1657696882:
                                if (name.equals("layoutDescription")) {
                                    c3 = 0;
                                    break;
                                }
                                break;
                            case 1901439077:
                                if (name.equals("Variant")) {
                                    c3 = 3;
                                    break;
                                }
                                break;
                        }
                        c3 = 65535;
                        if (c3 != 2) {
                            if (c3 != 3) {
                                if (c3 == 4) {
                                    cVar.g(context, xml);
                                }
                            } else {
                                g gVar = new g(context, xml);
                                if (c2 != null) {
                                    ((ArrayList) c2.f74c).add(gVar);
                                }
                            }
                        } else {
                            c2 = new C(context, xml);
                            ((SparseArray) cVar.f271b).put(c2.f72a, c2);
                        }
                    }
                } else {
                    xml.getName();
                }
                eventType = xml.next();
            } else {
                this.f963k = cVar;
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x04c6  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x04ca A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0326  */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v9, types: [int] */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v54 */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.util.ArrayList] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k(s.e r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instructions count: 1603
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.k(s.e, int, int, int):void");
    }

    public final void l(C0237d c0237d, v.e eVar, SparseArray sparseArray, int i2, int i3) {
        View view = (View) this.f954a.get(i2);
        C0237d c0237d2 = (C0237d) sparseArray.get(i2);
        if (c0237d2 != null && view != null && (view.getLayoutParams() instanceof v.e)) {
            eVar.f2638c0 = true;
            if (i3 == 6) {
                v.e eVar2 = (v.e) view.getLayoutParams();
                eVar2.f2638c0 = true;
                eVar2.f2661p0.f2267E = true;
            }
            c0237d.g(6).a(c0237d2.g(i3), eVar.f2610D, eVar.f2609C);
            c0237d.f2267E = true;
            c0237d.g(3).g();
            c0237d.g(5).g();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            v.e eVar = (v.e) childAt.getLayoutParams();
            C0237d c0237d = eVar.f2661p0;
            if (childAt.getVisibility() != 8 || eVar.f2640d0 || eVar.f2642e0 || isInEditMode) {
                int p2 = c0237d.p();
                int q2 = c0237d.q();
                childAt.layout(p2, q2, c0237d.o() + p2, c0237d.i() + q2);
            }
        }
        ArrayList arrayList = this.f955b;
        int size = arrayList.size();
        if (size > 0) {
            for (int i7 = 0; i7 < size; i7++) {
                ((AbstractC0253c) arrayList.get(i7)).getClass();
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:124:0x024a. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v13, types: [v.a, android.view.View, v.c] */
    /* JADX WARN: Type inference failed for: r5v9, types: [s.a, s.d] */
    @Override // android.view.View
    public final void onMeasure(int i2, int i3) {
        boolean z2;
        e eVar;
        boolean z3;
        boolean z4;
        boolean z5;
        int i4;
        int i5;
        n nVar;
        int i6;
        e eVar2;
        boolean z6;
        boolean z7;
        int i7;
        int i8;
        e eVar3;
        String str;
        int i9;
        String str2;
        String resourceName;
        int id;
        C0237d c0237d;
        String str3;
        if (this.f968p == i2) {
            int i10 = this.f969q;
        }
        if (!this.h) {
            int childCount = getChildCount();
            int i11 = 0;
            while (true) {
                if (i11 >= childCount) {
                    break;
                }
                if (getChildAt(i11).isLayoutRequested()) {
                    this.h = true;
                    break;
                }
                i11++;
            }
        }
        this.f968p = i2;
        this.f969q = i3;
        if ((getContext().getApplicationInfo().flags & 4194304) != 0 && 1 == getLayoutDirection()) {
            z2 = true;
        } else {
            z2 = false;
        }
        e eVar4 = this.f956c;
        eVar4.f2340u0 = z2;
        if (this.h) {
            this.h = false;
            int childCount2 = getChildCount();
            int i12 = 0;
            while (true) {
                if (i12 < childCount2) {
                    if (getChildAt(i12).isLayoutRequested()) {
                        z3 = true;
                        break;
                    }
                    i12++;
                } else {
                    z3 = false;
                    break;
                }
            }
            if (z3) {
                boolean isInEditMode = isInEditMode();
                int childCount3 = getChildCount();
                for (int i13 = 0; i13 < childCount3; i13++) {
                    C0237d i14 = i(getChildAt(i13));
                    if (i14 != null) {
                        i14.A();
                    }
                }
                if (isInEditMode) {
                    for (int i15 = 0; i15 < childCount3; i15++) {
                        View childAt = getChildAt(i15);
                        try {
                            resourceName = getResources().getResourceName(childAt.getId());
                            Integer valueOf = Integer.valueOf(childAt.getId());
                            if (resourceName != null) {
                                if (this.f965m == null) {
                                    this.f965m = new HashMap();
                                }
                                int indexOf = resourceName.indexOf("/");
                                if (indexOf != -1) {
                                    str3 = resourceName.substring(indexOf + 1);
                                } else {
                                    str3 = resourceName;
                                }
                                this.f965m.put(str3, valueOf);
                            }
                            int indexOf2 = resourceName.indexOf(47);
                            if (indexOf2 != -1) {
                                resourceName = resourceName.substring(indexOf2 + 1);
                            }
                            id = childAt.getId();
                        } catch (Resources.NotFoundException unused) {
                        }
                        if (id != 0) {
                            View view = (View) this.f954a.get(id);
                            if (view == null && (view = findViewById(id)) != null && view != this && view.getParent() == this) {
                                onViewAdded(view);
                            }
                            if (view != this) {
                                if (view == null) {
                                    c0237d = null;
                                } else {
                                    c0237d = ((v.e) view.getLayoutParams()).f2661p0;
                                }
                                c0237d.g0 = resourceName;
                            }
                        }
                        c0237d = eVar4;
                        c0237d.g0 = resourceName;
                    }
                }
                if (this.f964l != -1) {
                    for (int i16 = 0; i16 < childCount3; i16++) {
                        getChildAt(i16).getId();
                    }
                }
                n nVar2 = this.f962j;
                if (nVar2 != null) {
                    int childCount4 = getChildCount();
                    HashMap hashMap = nVar2.f2795c;
                    HashSet hashSet = new HashSet(hashMap.keySet());
                    int i17 = 0;
                    while (i17 < childCount4) {
                        View childAt2 = getChildAt(i17);
                        int id2 = childAt2.getId();
                        if (!hashMap.containsKey(Integer.valueOf(id2))) {
                            StringBuilder sb = new StringBuilder("id unknown ");
                            try {
                                str2 = childAt2.getContext().getResources().getResourceEntryName(childAt2.getId());
                            } catch (Exception unused2) {
                                str2 = "UNKNOWN";
                            }
                            sb.append(str2);
                            Log.w("ConstraintSet", sb.toString());
                        } else {
                            if (nVar2.f2794b) {
                                i5 = -1;
                                if (id2 == -1) {
                                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                                }
                            } else {
                                i5 = -1;
                            }
                            if (id2 == i5) {
                                nVar = nVar2;
                                i6 = childCount4;
                                eVar2 = eVar4;
                                z6 = z3;
                                z7 = isInEditMode;
                                i7 = childCount3;
                                i8 = i5;
                            } else if (hashMap.containsKey(Integer.valueOf(id2))) {
                                hashSet.remove(Integer.valueOf(id2));
                                i iVar = (i) hashMap.get(Integer.valueOf(id2));
                                if (iVar != null) {
                                    if (childAt2 instanceof C0251a) {
                                        j jVar = iVar.f2697d;
                                        nVar = nVar2;
                                        jVar.h0 = 1;
                                        C0251a c0251a = (C0251a) childAt2;
                                        c0251a.setId(id2);
                                        c0251a.setType(jVar.f2738f0);
                                        c0251a.setMargin(jVar.g0);
                                        c0251a.setAllowsGoneWidget(jVar.f2751n0);
                                        int[] iArr = jVar.f2741i0;
                                        if (iArr != null) {
                                            c0251a.setReferencedIds(iArr);
                                        } else {
                                            String str4 = jVar.f2743j0;
                                            if (str4 != null) {
                                                int[] b2 = n.b(c0251a, str4);
                                                jVar.f2741i0 = b2;
                                                c0251a.setReferencedIds(b2);
                                            }
                                        }
                                    } else {
                                        nVar = nVar2;
                                    }
                                    v.e eVar5 = (v.e) childAt2.getLayoutParams();
                                    eVar5.a();
                                    iVar.a(eVar5);
                                    HashMap hashMap2 = iVar.f2699f;
                                    z6 = z3;
                                    z7 = isInEditMode;
                                    Class<?> cls = childAt2.getClass();
                                    for (String str5 : hashMap2.keySet()) {
                                        int i18 = childCount3;
                                        C0252b c0252b = (C0252b) hashMap2.get(str5);
                                        HashMap hashMap3 = hashMap2;
                                        if (!c0252b.f2592a) {
                                            eVar3 = eVar4;
                                            str = "set" + str5;
                                        } else {
                                            eVar3 = eVar4;
                                            str = str5;
                                        }
                                        try {
                                            switch (q.f.a(c0252b.f2593b)) {
                                                case 0:
                                                    i9 = childCount4;
                                                    cls.getMethod(str, Integer.TYPE).invoke(childAt2, Integer.valueOf(c0252b.f2594c));
                                                    break;
                                                case 1:
                                                    i9 = childCount4;
                                                    cls.getMethod(str, Float.TYPE).invoke(childAt2, Float.valueOf(c0252b.f2595d));
                                                    break;
                                                case 2:
                                                    i9 = childCount4;
                                                    cls.getMethod(str, Integer.TYPE).invoke(childAt2, Integer.valueOf(c0252b.f2598g));
                                                    break;
                                                case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                                                    Method method = cls.getMethod(str, Drawable.class);
                                                    i9 = childCount4;
                                                    try {
                                                        ColorDrawable colorDrawable = new ColorDrawable();
                                                        colorDrawable.setColor(c0252b.f2598g);
                                                        method.invoke(childAt2, colorDrawable);
                                                    } catch (IllegalAccessException e2) {
                                                        e = e2;
                                                        Log.e("TransitionLayout", " Custom Attribute \"" + str5 + "\" not found on " + cls.getName());
                                                        e.printStackTrace();
                                                        childCount3 = i18;
                                                        hashMap2 = hashMap3;
                                                        eVar4 = eVar3;
                                                        childCount4 = i9;
                                                    } catch (NoSuchMethodException e3) {
                                                        e = e3;
                                                        Log.e("TransitionLayout", e.getMessage());
                                                        Log.e("TransitionLayout", " Custom Attribute \"" + str5 + "\" not found on " + cls.getName());
                                                        Log.e("TransitionLayout", cls.getName() + " must have a method " + str);
                                                        childCount3 = i18;
                                                        hashMap2 = hashMap3;
                                                        eVar4 = eVar3;
                                                        childCount4 = i9;
                                                    } catch (InvocationTargetException e4) {
                                                        e = e4;
                                                        Log.e("TransitionLayout", " Custom Attribute \"" + str5 + "\" not found on " + cls.getName());
                                                        e.printStackTrace();
                                                        childCount3 = i18;
                                                        hashMap2 = hashMap3;
                                                        eVar4 = eVar3;
                                                        childCount4 = i9;
                                                    }
                                                case 4:
                                                    cls.getMethod(str, CharSequence.class).invoke(childAt2, c0252b.f2596e);
                                                    i9 = childCount4;
                                                    break;
                                                case 5:
                                                    cls.getMethod(str, Boolean.TYPE).invoke(childAt2, Boolean.valueOf(c0252b.f2597f));
                                                    i9 = childCount4;
                                                    break;
                                                case 6:
                                                    cls.getMethod(str, Float.TYPE).invoke(childAt2, Float.valueOf(c0252b.f2595d));
                                                    i9 = childCount4;
                                                    break;
                                                case 7:
                                                    cls.getMethod(str, Integer.TYPE).invoke(childAt2, Integer.valueOf(c0252b.f2594c));
                                                    i9 = childCount4;
                                                    break;
                                                default:
                                                    i9 = childCount4;
                                                    break;
                                            }
                                        } catch (IllegalAccessException e5) {
                                            e = e5;
                                            i9 = childCount4;
                                        } catch (NoSuchMethodException e6) {
                                            e = e6;
                                            i9 = childCount4;
                                        } catch (InvocationTargetException e7) {
                                            e = e7;
                                            i9 = childCount4;
                                        }
                                        childCount3 = i18;
                                        hashMap2 = hashMap3;
                                        eVar4 = eVar3;
                                        childCount4 = i9;
                                    }
                                    i6 = childCount4;
                                    eVar2 = eVar4;
                                    i7 = childCount3;
                                    childAt2.setLayoutParams(eVar5);
                                    l lVar = iVar.f2695b;
                                    if (lVar.f2774b == 0) {
                                        childAt2.setVisibility(lVar.f2773a);
                                    }
                                    childAt2.setAlpha(lVar.f2775c);
                                    m mVar = iVar.f2698e;
                                    childAt2.setRotation(mVar.f2778a);
                                    childAt2.setRotationX(mVar.f2779b);
                                    childAt2.setRotationY(mVar.f2780c);
                                    childAt2.setScaleX(mVar.f2781d);
                                    childAt2.setScaleY(mVar.f2782e);
                                    i8 = -1;
                                    if (mVar.h != -1) {
                                        if (((View) childAt2.getParent()).findViewById(mVar.h) != null) {
                                            float bottom = (r1.getBottom() + r1.getTop()) / 2.0f;
                                            float right = (r1.getRight() + r1.getLeft()) / 2.0f;
                                            if (childAt2.getRight() - childAt2.getLeft() > 0 && childAt2.getBottom() - childAt2.getTop() > 0) {
                                                childAt2.setPivotX(right - childAt2.getLeft());
                                                childAt2.setPivotY(bottom - childAt2.getTop());
                                            }
                                        }
                                    } else {
                                        if (!Float.isNaN(mVar.f2783f)) {
                                            childAt2.setPivotX(mVar.f2783f);
                                        }
                                        if (!Float.isNaN(mVar.f2784g)) {
                                            childAt2.setPivotY(mVar.f2784g);
                                        }
                                    }
                                    childAt2.setTranslationX(mVar.f2785i);
                                    childAt2.setTranslationY(mVar.f2786j);
                                    childAt2.setTranslationZ(mVar.f2787k);
                                    if (mVar.f2788l) {
                                        childAt2.setElevation(mVar.f2789m);
                                    }
                                }
                            } else {
                                nVar = nVar2;
                                i6 = childCount4;
                                eVar2 = eVar4;
                                z6 = z3;
                                z7 = isInEditMode;
                                i7 = childCount3;
                                i8 = -1;
                                Log.v("ConstraintSet", "WARNING NO CONSTRAINTS for view " + id2);
                            }
                            i17++;
                            nVar2 = nVar;
                            z3 = z6;
                            isInEditMode = z7;
                            childCount3 = i7;
                            eVar4 = eVar2;
                            childCount4 = i6;
                        }
                        nVar = nVar2;
                        i6 = childCount4;
                        eVar2 = eVar4;
                        z6 = z3;
                        z7 = isInEditMode;
                        i7 = childCount3;
                        i8 = -1;
                        i17++;
                        nVar2 = nVar;
                        z3 = z6;
                        isInEditMode = z7;
                        childCount3 = i7;
                        eVar4 = eVar2;
                        childCount4 = i6;
                    }
                    int i19 = childCount4;
                    e eVar6 = eVar4;
                    z4 = z3;
                    z5 = isInEditMode;
                    i4 = childCount3;
                    Iterator it = hashSet.iterator();
                    while (it.hasNext()) {
                        Integer num = (Integer) it.next();
                        i iVar2 = (i) hashMap.get(num);
                        if (iVar2 != null) {
                            j jVar2 = iVar2.f2697d;
                            if (jVar2.h0 == 1) {
                                Context context = getContext();
                                ?? view2 = new View(context);
                                view2.f2599a = new int[32];
                                view2.f2605g = new HashMap();
                                view2.f2601c = context;
                                ?? c0237d2 = new C0237d();
                                c0237d2.f2234p0 = new C0237d[4];
                                c0237d2.f2235q0 = 0;
                                c0237d2.f2236r0 = 0;
                                c0237d2.f2237s0 = true;
                                c0237d2.t0 = 0;
                                c0237d2.f2238u0 = false;
                                view2.f2591j = c0237d2;
                                view2.f2602d = c0237d2;
                                view2.e();
                                view2.setVisibility(8);
                                view2.setId(num.intValue());
                                int[] iArr2 = jVar2.f2741i0;
                                if (iArr2 != null) {
                                    view2.setReferencedIds(iArr2);
                                } else {
                                    String str6 = jVar2.f2743j0;
                                    if (str6 != null) {
                                        int[] b3 = n.b(view2, str6);
                                        jVar2.f2741i0 = b3;
                                        view2.setReferencedIds(b3);
                                    }
                                }
                                view2.setType(jVar2.f2738f0);
                                view2.setMargin(jVar2.g0);
                                v.e h = h();
                                view2.e();
                                iVar2.a(h);
                                addView((View) view2, h);
                            }
                            if (jVar2.f2727a) {
                                p pVar = new p(getContext());
                                pVar.setId(num.intValue());
                                v.e h2 = h();
                                iVar2.a(h2);
                                addView(pVar, h2);
                            }
                        }
                    }
                    for (int i20 = 0; i20 < i19; i20++) {
                        View childAt3 = getChildAt(i20);
                        if (childAt3 instanceof AbstractC0253c) {
                            ((AbstractC0253c) childAt3).getClass();
                        }
                    }
                    eVar = eVar6;
                } else {
                    z4 = z3;
                    z5 = isInEditMode;
                    i4 = childCount3;
                    eVar = eVar4;
                }
                eVar.f2336p0.clear();
                ArrayList arrayList = this.f955b;
                int size = arrayList.size();
                if (size > 0) {
                    for (int i21 = 0; i21 < size; i21++) {
                        AbstractC0253c abstractC0253c = (AbstractC0253c) arrayList.get(i21);
                        if (abstractC0253c.isInEditMode()) {
                            abstractC0253c.setIds(abstractC0253c.f2603e);
                        }
                        C0234a c0234a = abstractC0253c.f2602d;
                        if (c0234a != null) {
                            c0234a.f2235q0 = 0;
                            Arrays.fill(c0234a.f2234p0, (Object) null);
                            for (int i22 = 0; i22 < abstractC0253c.f2600b; i22++) {
                                int i23 = abstractC0253c.f2599a[i22];
                                View view3 = (View) this.f954a.get(i23);
                                if (view3 == null) {
                                    HashMap hashMap4 = abstractC0253c.f2605g;
                                    String str7 = (String) hashMap4.get(Integer.valueOf(i23));
                                    int d2 = abstractC0253c.d(this, str7);
                                    if (d2 != 0) {
                                        abstractC0253c.f2599a[i22] = d2;
                                        hashMap4.put(Integer.valueOf(d2), str7);
                                        view3 = (View) this.f954a.get(d2);
                                    }
                                }
                                if (view3 != null) {
                                    C0234a c0234a2 = abstractC0253c.f2602d;
                                    C0237d i24 = i(view3);
                                    c0234a2.getClass();
                                    if (i24 != c0234a2 && i24 != null) {
                                        int i25 = c0234a2.f2235q0 + 1;
                                        C0237d[] c0237dArr = c0234a2.f2234p0;
                                        if (i25 > c0237dArr.length) {
                                            c0234a2.f2234p0 = (C0237d[]) Arrays.copyOf(c0237dArr, c0237dArr.length * 2);
                                        }
                                        C0237d[] c0237dArr2 = c0234a2.f2234p0;
                                        int i26 = c0234a2.f2235q0;
                                        c0237dArr2[i26] = i24;
                                        c0234a2.f2235q0 = i26 + 1;
                                    }
                                }
                            }
                            abstractC0253c.f2602d.getClass();
                        }
                    }
                }
                int i27 = i4;
                for (int i28 = 0; i28 < i27; i28++) {
                    getChildAt(i28);
                }
                SparseArray sparseArray = this.f966n;
                sparseArray.clear();
                sparseArray.put(0, eVar);
                sparseArray.put(getId(), eVar);
                for (int i29 = 0; i29 < i27; i29++) {
                    View childAt4 = getChildAt(i29);
                    sparseArray.put(childAt4.getId(), i(childAt4));
                }
                for (int i30 = 0; i30 < i27; i30++) {
                    View childAt5 = getChildAt(i30);
                    C0237d i31 = i(childAt5);
                    if (i31 != null) {
                        v.e eVar7 = (v.e) childAt5.getLayoutParams();
                        eVar.f2336p0.add(i31);
                        C0237d c0237d3 = i31.f2281S;
                        if (c0237d3 != null) {
                            ((e) c0237d3).f2336p0.remove(i31);
                            i31.A();
                        }
                        i31.f2281S = eVar;
                        g(z5, childAt5, i31, eVar7, sparseArray);
                    }
                }
            } else {
                eVar = eVar4;
                z4 = z3;
            }
            if (z4) {
                eVar.f2337q0.u(eVar);
            }
        } else {
            eVar = eVar4;
        }
        k(eVar, this.f961i, i2, i3);
        int o2 = eVar.o();
        int i32 = eVar.i();
        boolean z8 = eVar.f2329D0;
        boolean z9 = eVar.f2330E0;
        f fVar = this.f967o;
        int i33 = fVar.f2675e;
        int resolveSizeAndState = View.resolveSizeAndState(o2 + fVar.f2674d, i2, 0);
        int resolveSizeAndState2 = View.resolveSizeAndState(i32 + i33, i3, 0) & 16777215;
        int min = Math.min(this.f959f, resolveSizeAndState & 16777215);
        int min2 = Math.min(this.f960g, resolveSizeAndState2);
        if (z8) {
            min |= 16777216;
        }
        if (z9) {
            min2 |= 16777216;
        }
        setMeasuredDimension(min, min2);
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        super.onViewAdded(view);
        C0237d i2 = i(view);
        if ((view instanceof p) && !(i2 instanceof s.f)) {
            v.e eVar = (v.e) view.getLayoutParams();
            s.f fVar = new s.f();
            eVar.f2661p0 = fVar;
            eVar.f2640d0 = true;
            fVar.O(eVar.f2628V);
        }
        if (view instanceof AbstractC0253c) {
            AbstractC0253c abstractC0253c = (AbstractC0253c) view;
            abstractC0253c.e();
            ((v.e) view.getLayoutParams()).f2642e0 = true;
            ArrayList arrayList = this.f955b;
            if (!arrayList.contains(abstractC0253c)) {
                arrayList.add(abstractC0253c);
            }
        }
        this.f954a.put(view.getId(), view);
        this.h = true;
    }

    @Override // android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.f954a.remove(view.getId());
        C0237d i2 = i(view);
        this.f956c.f2336p0.remove(i2);
        i2.A();
        this.f955b.remove(view);
        this.h = true;
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        this.h = true;
        super.requestLayout();
    }

    public void setConstraintSet(n nVar) {
        this.f962j = nVar;
    }

    @Override // android.view.View
    public void setId(int i2) {
        SparseArray sparseArray = this.f954a;
        sparseArray.remove(getId());
        super.setId(i2);
        sparseArray.put(getId(), this);
    }

    public void setMaxHeight(int i2) {
        if (i2 == this.f960g) {
            return;
        }
        this.f960g = i2;
        requestLayout();
    }

    public void setMaxWidth(int i2) {
        if (i2 == this.f959f) {
            return;
        }
        this.f959f = i2;
        requestLayout();
    }

    public void setMinHeight(int i2) {
        if (i2 == this.f958e) {
            return;
        }
        this.f958e = i2;
        requestLayout();
    }

    public void setMinWidth(int i2) {
        if (i2 == this.f957d) {
            return;
        }
        this.f957d = i2;
        requestLayout();
    }

    public void setOnConstraintsChanged(o oVar) {
        c cVar = this.f963k;
        if (cVar != null) {
            cVar.getClass();
        }
    }

    public void setOptimizationLevel(int i2) {
        this.f961i = i2;
        e eVar = this.f956c;
        eVar.f2328C0 = i2;
        C0232c.f2189p = eVar.S(512);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.ViewGroup$LayoutParams, android.view.ViewGroup$MarginLayoutParams, v.e] */
    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        ?? marginLayoutParams = new ViewGroup.MarginLayoutParams(layoutParams);
        marginLayoutParams.f2633a = -1;
        marginLayoutParams.f2635b = -1;
        marginLayoutParams.f2637c = -1.0f;
        marginLayoutParams.f2639d = true;
        marginLayoutParams.f2641e = -1;
        marginLayoutParams.f2643f = -1;
        marginLayoutParams.f2645g = -1;
        marginLayoutParams.h = -1;
        marginLayoutParams.f2646i = -1;
        marginLayoutParams.f2648j = -1;
        marginLayoutParams.f2650k = -1;
        marginLayoutParams.f2652l = -1;
        marginLayoutParams.f2654m = -1;
        marginLayoutParams.f2656n = -1;
        marginLayoutParams.f2658o = -1;
        marginLayoutParams.f2660p = -1;
        marginLayoutParams.f2662q = 0;
        marginLayoutParams.f2663r = 0.0f;
        marginLayoutParams.f2664s = -1;
        marginLayoutParams.f2665t = -1;
        marginLayoutParams.f2666u = -1;
        marginLayoutParams.f2667v = -1;
        marginLayoutParams.f2668w = Integer.MIN_VALUE;
        marginLayoutParams.f2669x = Integer.MIN_VALUE;
        marginLayoutParams.y = Integer.MIN_VALUE;
        marginLayoutParams.f2670z = Integer.MIN_VALUE;
        marginLayoutParams.f2607A = Integer.MIN_VALUE;
        marginLayoutParams.f2608B = Integer.MIN_VALUE;
        marginLayoutParams.f2609C = Integer.MIN_VALUE;
        marginLayoutParams.f2610D = 0;
        marginLayoutParams.f2611E = 0.5f;
        marginLayoutParams.f2612F = 0.5f;
        marginLayoutParams.f2613G = null;
        marginLayoutParams.f2614H = -1.0f;
        marginLayoutParams.f2615I = -1.0f;
        marginLayoutParams.f2616J = 0;
        marginLayoutParams.f2617K = 0;
        marginLayoutParams.f2618L = 0;
        marginLayoutParams.f2619M = 0;
        marginLayoutParams.f2620N = 0;
        marginLayoutParams.f2621O = 0;
        marginLayoutParams.f2622P = 0;
        marginLayoutParams.f2623Q = 0;
        marginLayoutParams.f2624R = 1.0f;
        marginLayoutParams.f2625S = 1.0f;
        marginLayoutParams.f2626T = -1;
        marginLayoutParams.f2627U = -1;
        marginLayoutParams.f2628V = -1;
        marginLayoutParams.f2629W = false;
        marginLayoutParams.f2630X = false;
        marginLayoutParams.f2631Y = null;
        marginLayoutParams.f2632Z = 0;
        marginLayoutParams.f2634a0 = true;
        marginLayoutParams.f2636b0 = true;
        marginLayoutParams.f2638c0 = false;
        marginLayoutParams.f2640d0 = false;
        marginLayoutParams.f2642e0 = false;
        marginLayoutParams.f2644f0 = -1;
        marginLayoutParams.g0 = -1;
        marginLayoutParams.h0 = -1;
        marginLayoutParams.f2647i0 = -1;
        marginLayoutParams.f2649j0 = Integer.MIN_VALUE;
        marginLayoutParams.f2651k0 = Integer.MIN_VALUE;
        marginLayoutParams.f2653l0 = 0.5f;
        marginLayoutParams.f2661p0 = new C0237d();
        return marginLayoutParams;
    }
}

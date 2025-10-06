package v;

import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import org.conscrypt.PSKKeyManager;
import s.C0236c;
import s.C0237d;
import t.C0239b;

/* loaded from: classes.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public final ConstraintLayout f2671a;

    /* renamed from: b, reason: collision with root package name */
    public int f2672b;

    /* renamed from: c, reason: collision with root package name */
    public int f2673c;

    /* renamed from: d, reason: collision with root package name */
    public int f2674d;

    /* renamed from: e, reason: collision with root package name */
    public int f2675e;

    /* renamed from: f, reason: collision with root package name */
    public int f2676f;

    /* renamed from: g, reason: collision with root package name */
    public int f2677g;
    public final /* synthetic */ ConstraintLayout h;

    public f(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2) {
        this.h = constraintLayout;
        this.f2671a = constraintLayout2;
    }

    public static boolean a(int i2, int i3, int i4) {
        if (i2 == i3) {
            return true;
        }
        int mode = View.MeasureSpec.getMode(i2);
        View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        if (mode2 == 1073741824) {
            if ((mode == Integer.MIN_VALUE || mode == 0) && i4 == size) {
                return true;
            }
            return false;
        }
        return false;
    }

    public final void b(C0237d c0237d, C0239b c0239b) {
        int makeMeasureSpec;
        int makeMeasureSpec2;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z8;
        int measuredWidth;
        int baseline;
        int i6;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        int i7;
        boolean z13;
        boolean z14;
        int i8;
        if (c0237d == null) {
            return;
        }
        if (c0237d.f2300f0 == 8) {
            c0239b.f2357e = 0;
            c0239b.f2358f = 0;
            c0239b.f2359g = 0;
            return;
        }
        if (c0237d.f2281S == null) {
            return;
        }
        int i9 = c0239b.f2353a;
        int i10 = c0239b.f2354b;
        int i11 = c0239b.f2355c;
        int i12 = c0239b.f2356d;
        int i13 = this.f2672b + this.f2673c;
        int i14 = this.f2674d;
        View view = c0237d.f2298e0;
        int a2 = q.f.a(i9);
        C0236c c0236c = c0237d.f2272J;
        C0236c c0236c2 = c0237d.f2270H;
        if (a2 != 0) {
            if (a2 != 1) {
                if (a2 != 2) {
                    if (a2 != 3) {
                        makeMeasureSpec = 0;
                    } else {
                        int i15 = this.f2676f;
                        if (c0236c2 != null) {
                            i8 = c0236c2.f2261g;
                        } else {
                            i8 = 0;
                        }
                        if (c0236c != null) {
                            i8 += c0236c.f2261g;
                        }
                        makeMeasureSpec = ViewGroup.getChildMeasureSpec(i15, i14 + i8, -1);
                    }
                } else {
                    makeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f2676f, i14, -2);
                    if (c0237d.f2318r == 1) {
                        z13 = true;
                    } else {
                        z13 = false;
                    }
                    int i16 = c0239b.f2361j;
                    if (i16 == 1 || i16 == 2) {
                        if (view.getMeasuredHeight() == c0237d.i()) {
                            z14 = true;
                        } else {
                            z14 = false;
                        }
                        if (c0239b.f2361j == 2 || !z13 || ((z13 && z14) || c0237d.y())) {
                            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(c0237d.o(), 1073741824);
                        }
                    }
                }
            } else {
                makeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f2676f, i14, -2);
            }
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i11, 1073741824);
        }
        int a3 = q.f.a(i10);
        if (a3 != 0) {
            if (a3 != 1) {
                if (a3 != 2) {
                    if (a3 != 3) {
                        makeMeasureSpec2 = 0;
                    } else {
                        int i17 = this.f2677g;
                        if (c0236c2 != null) {
                            i7 = c0237d.f2271I.f2261g;
                        } else {
                            i7 = 0;
                        }
                        if (c0236c != null) {
                            i7 += c0237d.f2273K.f2261g;
                        }
                        makeMeasureSpec2 = ViewGroup.getChildMeasureSpec(i17, i13 + i7, -1);
                    }
                } else {
                    makeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.f2677g, i13, -2);
                    if (c0237d.f2319s == 1) {
                        z11 = true;
                    } else {
                        z11 = false;
                    }
                    int i18 = c0239b.f2361j;
                    if (i18 == 1 || i18 == 2) {
                        if (view.getMeasuredWidth() == c0237d.o()) {
                            z12 = true;
                        } else {
                            z12 = false;
                        }
                        if (c0239b.f2361j == 2 || !z11 || ((z11 && z12) || c0237d.z())) {
                            makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(c0237d.i(), 1073741824);
                        }
                    }
                }
            } else {
                makeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.f2677g, i13, -2);
            }
        } else {
            makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i12, 1073741824);
        }
        s.e eVar = (s.e) c0237d.f2281S;
        ConstraintLayout constraintLayout = this.h;
        if (eVar != null && s.g.c(constraintLayout.f961i, PSKKeyManager.MAX_KEY_LENGTH_BYTES) && view.getMeasuredWidth() == c0237d.o() && view.getMeasuredWidth() < eVar.o() && view.getMeasuredHeight() == c0237d.i() && view.getMeasuredHeight() < eVar.i() && view.getBaseline() == c0237d.f2288Z && !c0237d.x() && a(c0237d.f2268F, makeMeasureSpec, c0237d.o()) && a(c0237d.f2269G, makeMeasureSpec2, c0237d.i())) {
            c0239b.f2357e = c0237d.o();
            c0239b.f2358f = c0237d.i();
            c0239b.f2359g = c0237d.f2288Z;
            return;
        }
        if (i9 == 3) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (i10 == 3) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (i10 != 4 && i10 != 1) {
            z4 = false;
        } else {
            z4 = true;
        }
        if (i9 != 4 && i9 != 1) {
            z5 = false;
        } else {
            z5 = true;
        }
        if (z2 && c0237d.f2284V > 0.0f) {
            z6 = true;
        } else {
            z6 = false;
        }
        if (z3 && c0237d.f2284V > 0.0f) {
            z7 = true;
        } else {
            z7 = false;
        }
        if (view == null) {
            return;
        }
        e eVar2 = (e) view.getLayoutParams();
        int i19 = c0239b.f2361j;
        if (i19 != 1 && i19 != 2 && z2 && c0237d.f2318r == 0 && z3 && c0237d.f2319s == 0) {
            z8 = false;
            measuredWidth = 0;
            i6 = -1;
            baseline = 0;
            i3 = 0;
        } else {
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            c0237d.f2268F = makeMeasureSpec;
            c0237d.f2269G = makeMeasureSpec2;
            c0237d.f2301g = false;
            int measuredWidth2 = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            int baseline2 = view.getBaseline();
            int i20 = c0237d.f2321u;
            if (i20 > 0) {
                i2 = Math.max(i20, measuredWidth2);
            } else {
                i2 = measuredWidth2;
            }
            int i21 = c0237d.f2322v;
            if (i21 > 0) {
                i2 = Math.min(i21, i2);
            }
            int i22 = c0237d.f2324x;
            if (i22 > 0) {
                i3 = Math.max(i22, measuredHeight);
            } else {
                i3 = measuredHeight;
            }
            int i23 = makeMeasureSpec;
            int i24 = c0237d.y;
            if (i24 > 0) {
                i3 = Math.min(i24, i3);
            }
            if (!s.g.c(constraintLayout.f961i, 1)) {
                if (z6 && z4) {
                    i2 = (int) ((i3 * c0237d.f2284V) + 0.5f);
                } else if (z7 && z5) {
                    i3 = (int) ((i2 / c0237d.f2284V) + 0.5f);
                }
            }
            if (measuredWidth2 == i2 && measuredHeight == i3) {
                baseline = baseline2;
                measuredWidth = i2;
                z8 = false;
            } else {
                if (measuredWidth2 != i2) {
                    i4 = 1073741824;
                    i5 = View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
                } else {
                    i4 = 1073741824;
                    i5 = i23;
                }
                if (measuredHeight != i3) {
                    makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i3, i4);
                }
                view.measure(i5, makeMeasureSpec2);
                c0237d.f2268F = i5;
                c0237d.f2269G = makeMeasureSpec2;
                z8 = false;
                c0237d.f2301g = false;
                measuredWidth = view.getMeasuredWidth();
                int measuredHeight2 = view.getMeasuredHeight();
                baseline = view.getBaseline();
                i3 = measuredHeight2;
            }
            i6 = -1;
        }
        if (baseline != i6) {
            z9 = true;
        } else {
            z9 = z8;
        }
        if (measuredWidth != c0239b.f2355c || i3 != c0239b.f2356d) {
            z8 = true;
        }
        c0239b.f2360i = z8;
        if (eVar2.f2638c0) {
            z10 = true;
        } else {
            z10 = z9;
        }
        if (z10 && baseline != -1 && c0237d.f2288Z != baseline) {
            c0239b.f2360i = true;
        }
        c0239b.f2357e = measuredWidth;
        c0239b.f2358f = i3;
        c0239b.h = z10;
        c0239b.f2359g = baseline;
    }
}

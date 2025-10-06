package H;

import android.util.Log;
import android.view.ViewParent;
import androidx.core.widget.NestedScrollView;

/* loaded from: classes.dex */
public final class r {

    /* renamed from: a, reason: collision with root package name */
    public ViewParent f387a;

    /* renamed from: b, reason: collision with root package name */
    public ViewParent f388b;

    /* renamed from: c, reason: collision with root package name */
    public final NestedScrollView f389c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f390d;

    /* renamed from: e, reason: collision with root package name */
    public int[] f391e;

    public r(NestedScrollView nestedScrollView) {
        this.f389c = nestedScrollView;
    }

    public final boolean a(float f2, float f3) {
        ViewParent c2;
        if (!this.f390d || (c2 = c(0)) == null) {
            return false;
        }
        try {
            return c2.onNestedPreFling(this.f389c, f2, f3);
        } catch (AbstractMethodError e2) {
            Log.e("ViewParentCompat", "ViewParent " + c2 + " does not implement interface method onNestedPreFling", e2);
            return false;
        }
    }

    public final boolean b(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        ViewParent c2;
        int i7;
        int i8;
        int[] iArr3;
        if (!this.f390d || (c2 = c(i6)) == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        NestedScrollView nestedScrollView = this.f389c;
        if (iArr != null) {
            nestedScrollView.getLocationInWindow(iArr);
            i7 = iArr[0];
            i8 = iArr[1];
        } else {
            i7 = 0;
            i8 = 0;
        }
        if (iArr2 == null) {
            if (this.f391e == null) {
                this.f391e = new int[2];
            }
            int[] iArr4 = this.f391e;
            iArr4[0] = 0;
            iArr4[1] = 0;
            iArr3 = iArr4;
        } else {
            iArr3 = iArr2;
        }
        if (c2 instanceof InterfaceC0025t) {
            ((InterfaceC0025t) c2).d(nestedScrollView, i2, i3, i4, i5, i6, iArr3);
        } else {
            iArr3[0] = iArr3[0] + i4;
            iArr3[1] = iArr3[1] + i5;
            if (c2 instanceof InterfaceC0024s) {
                ((InterfaceC0024s) c2).e(nestedScrollView, i2, i3, i4, i5, i6);
            } else if (i6 == 0) {
                try {
                    c2.onNestedScroll(nestedScrollView, i2, i3, i4, i5);
                } catch (AbstractMethodError e2) {
                    Log.e("ViewParentCompat", "ViewParent " + c2 + " does not implement interface method onNestedScroll", e2);
                }
            }
        }
        if (iArr != null) {
            nestedScrollView.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i7;
            iArr[1] = iArr[1] - i8;
        }
        return true;
    }

    public final ViewParent c(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                return null;
            }
            return this.f388b;
        }
        return this.f387a;
    }
}

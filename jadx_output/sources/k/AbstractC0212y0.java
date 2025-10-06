package k;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import f.AbstractC0101a;

/* renamed from: k.y0, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0212y0 extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    public boolean f2054a;

    /* renamed from: b, reason: collision with root package name */
    public int f2055b;

    /* renamed from: c, reason: collision with root package name */
    public int f2056c;

    /* renamed from: d, reason: collision with root package name */
    public int f2057d;

    /* renamed from: e, reason: collision with root package name */
    public int f2058e;

    /* renamed from: f, reason: collision with root package name */
    public int f2059f;

    /* renamed from: g, reason: collision with root package name */
    public float f2060g;
    public boolean h;

    /* renamed from: i, reason: collision with root package name */
    public int[] f2061i;

    /* renamed from: j, reason: collision with root package name */
    public int[] f2062j;

    /* renamed from: k, reason: collision with root package name */
    public Drawable f2063k;

    /* renamed from: l, reason: collision with root package name */
    public int f2064l;

    /* renamed from: m, reason: collision with root package name */
    public int f2065m;

    /* renamed from: n, reason: collision with root package name */
    public int f2066n;

    /* renamed from: o, reason: collision with root package name */
    public int f2067o;

    public AbstractC0212y0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f2054a = true;
        this.f2055b = -1;
        this.f2056c = 0;
        this.f2058e = 8388659;
        int[] iArr = AbstractC0101a.f1274n;
        D0.h p2 = D0.h.p(context, attributeSet, iArr, 0);
        H.N.g(this, context, iArr, attributeSet, (TypedArray) p2.f259c, 0);
        TypedArray typedArray = (TypedArray) p2.f259c;
        int i2 = typedArray.getInt(1, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = typedArray.getInt(0, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        boolean z2 = typedArray.getBoolean(2, true);
        if (!z2) {
            setBaselineAligned(z2);
        }
        this.f2060g = typedArray.getFloat(4, -1.0f);
        this.f2055b = typedArray.getInt(3, -1);
        this.h = typedArray.getBoolean(7, false);
        setDividerDrawable(p2.j(5));
        this.f2066n = typedArray.getInt(8, 0);
        this.f2067o = typedArray.getDimensionPixelSize(6, 0);
        p2.r();
    }

    public final void c(Canvas canvas, int i2) {
        this.f2063k.setBounds(getPaddingLeft() + this.f2067o, i2, (getWidth() - getPaddingRight()) - this.f2067o, this.f2065m + i2);
        this.f2063k.draw(canvas);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0210x0;
    }

    public final void d(Canvas canvas, int i2) {
        this.f2063k.setBounds(i2, getPaddingTop() + this.f2067o, this.f2064l + i2, (getHeight() - getPaddingBottom()) - this.f2067o);
        this.f2063k.draw(canvas);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [android.widget.LinearLayout$LayoutParams, k.x0] */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.widget.LinearLayout$LayoutParams, k.x0] */
    @Override // android.view.ViewGroup
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public C0210x0 generateDefaultLayoutParams() {
        int i2 = this.f2057d;
        if (i2 == 0) {
            return new LinearLayout.LayoutParams(-2, -2);
        }
        if (i2 == 1) {
            return new LinearLayout.LayoutParams(-1, -2);
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.widget.LinearLayout$LayoutParams, k.x0] */
    @Override // android.view.ViewGroup
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public C0210x0 generateLayoutParams(AttributeSet attributeSet) {
        return new LinearLayout.LayoutParams(getContext(), attributeSet);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [android.widget.LinearLayout$LayoutParams, k.x0] */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.widget.LinearLayout$LayoutParams, k.x0] */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.widget.LinearLayout$LayoutParams, k.x0] */
    @Override // android.view.ViewGroup
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public C0210x0 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof C0210x0) {
            return new LinearLayout.LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LinearLayout.LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LinearLayout.LayoutParams(layoutParams);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i2;
        if (this.f2055b < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i3 = this.f2055b;
        if (childCount > i3) {
            View childAt = getChildAt(i3);
            int baseline = childAt.getBaseline();
            if (baseline == -1) {
                if (this.f2055b == 0) {
                    return -1;
                }
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
            int i4 = this.f2056c;
            if (this.f2057d == 1 && (i2 = this.f2058e & 112) != 48) {
                if (i2 != 16) {
                    if (i2 == 80) {
                        i4 = ((getBottom() - getTop()) - getPaddingBottom()) - this.f2059f;
                    }
                } else {
                    i4 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f2059f) / 2;
                }
            }
            return i4 + ((LinearLayout.LayoutParams) ((C0210x0) childAt.getLayoutParams())).topMargin + baseline;
        }
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
    }

    public int getBaselineAlignedChildIndex() {
        return this.f2055b;
    }

    public Drawable getDividerDrawable() {
        return this.f2063k;
    }

    public int getDividerPadding() {
        return this.f2067o;
    }

    public int getDividerWidth() {
        return this.f2064l;
    }

    public int getGravity() {
        return this.f2058e;
    }

    public int getOrientation() {
        return this.f2057d;
    }

    public int getShowDividers() {
        return this.f2066n;
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.f2060g;
    }

    public final boolean h(int i2) {
        if (i2 == 0) {
            if ((this.f2066n & 1) == 0) {
                return false;
            }
            return true;
        }
        if (i2 == getChildCount()) {
            if ((this.f2066n & 4) == 0) {
                return false;
            }
            return true;
        }
        if ((this.f2066n & 2) == 0) {
            return false;
        }
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            if (getChildAt(i3).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        boolean z2;
        int right;
        int left;
        int i2;
        int left2;
        int bottom;
        if (this.f2063k == null) {
            return;
        }
        int i3 = 0;
        if (this.f2057d == 1) {
            int virtualChildCount = getVirtualChildCount();
            while (i3 < virtualChildCount) {
                View childAt = getChildAt(i3);
                if (childAt != null && childAt.getVisibility() != 8 && h(i3)) {
                    c(canvas, (childAt.getTop() - ((LinearLayout.LayoutParams) ((C0210x0) childAt.getLayoutParams())).topMargin) - this.f2065m);
                }
                i3++;
            }
            if (h(virtualChildCount)) {
                View childAt2 = getChildAt(virtualChildCount - 1);
                if (childAt2 == null) {
                    bottom = (getHeight() - getPaddingBottom()) - this.f2065m;
                } else {
                    bottom = childAt2.getBottom() + ((LinearLayout.LayoutParams) ((C0210x0) childAt2.getLayoutParams())).bottomMargin;
                }
                c(canvas, bottom);
                return;
            }
            return;
        }
        int virtualChildCount2 = getVirtualChildCount();
        boolean z3 = m1.f1979a;
        if (getLayoutDirection() == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        while (i3 < virtualChildCount2) {
            View childAt3 = getChildAt(i3);
            if (childAt3 != null && childAt3.getVisibility() != 8 && h(i3)) {
                C0210x0 c0210x0 = (C0210x0) childAt3.getLayoutParams();
                if (z2) {
                    left2 = childAt3.getRight() + ((LinearLayout.LayoutParams) c0210x0).rightMargin;
                } else {
                    left2 = (childAt3.getLeft() - ((LinearLayout.LayoutParams) c0210x0).leftMargin) - this.f2064l;
                }
                d(canvas, left2);
            }
            i3++;
        }
        if (h(virtualChildCount2)) {
            View childAt4 = getChildAt(virtualChildCount2 - 1);
            if (childAt4 == null) {
                if (z2) {
                    right = getPaddingLeft();
                } else {
                    left = getWidth() - getPaddingRight();
                    i2 = this.f2064l;
                    right = left - i2;
                }
            } else {
                C0210x0 c0210x02 = (C0210x0) childAt4.getLayoutParams();
                if (z2) {
                    left = childAt4.getLeft() - ((LinearLayout.LayoutParams) c0210x02).leftMargin;
                    i2 = this.f2064l;
                    right = left - i2;
                } else {
                    right = childAt4.getRight() + ((LinearLayout.LayoutParams) c0210x02).rightMargin;
                }
            }
            d(canvas, right);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x018f  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayout(boolean r23, int r24, int r25, int r26, int r27) {
        /*
            Method dump skipped, instructions count: 461
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: k.AbstractC0212y0.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:173:0x02e0, code lost:
    
        if (((android.widget.LinearLayout.LayoutParams) r13).width == (-1)) goto L148;
     */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0550  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0486  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x04b3  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x04de  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x04cc  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x05e4  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0860  */
    /* JADX WARN: Removed duplicated region for block: B:352:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:356:0x0698  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x06b4  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r38, int r39) {
        /*
            Method dump skipped, instructions count: 2208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: k.AbstractC0212y0.onMeasure(int, int):void");
    }

    public void setBaselineAligned(boolean z2) {
        this.f2054a = z2;
    }

    public void setBaselineAlignedChildIndex(int i2) {
        if (i2 >= 0 && i2 < getChildCount()) {
            this.f2055b = i2;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.f2063k) {
            return;
        }
        this.f2063k = drawable;
        boolean z2 = false;
        if (drawable != null) {
            this.f2064l = drawable.getIntrinsicWidth();
            this.f2065m = drawable.getIntrinsicHeight();
        } else {
            this.f2064l = 0;
            this.f2065m = 0;
        }
        if (drawable == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
        requestLayout();
    }

    public void setDividerPadding(int i2) {
        this.f2067o = i2;
    }

    public void setGravity(int i2) {
        if (this.f2058e != i2) {
            if ((8388615 & i2) == 0) {
                i2 |= 8388611;
            }
            if ((i2 & 112) == 0) {
                i2 |= 48;
            }
            this.f2058e = i2;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i2) {
        int i3 = i2 & 8388615;
        int i4 = this.f2058e;
        if ((8388615 & i4) != i3) {
            this.f2058e = i3 | ((-8388616) & i4);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z2) {
        this.h = z2;
    }

    public void setOrientation(int i2) {
        if (this.f2057d != i2) {
            this.f2057d = i2;
            requestLayout();
        }
    }

    public void setShowDividers(int i2) {
        if (i2 != this.f2066n) {
            requestLayout();
        }
        this.f2066n = i2;
    }

    public void setVerticalGravity(int i2) {
        int i3 = i2 & 112;
        int i4 = this.f2058e;
        if ((i4 & 112) != i3) {
            this.f2058e = i3 | (i4 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f2) {
        this.f2060g = Math.max(0.0f, f2);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}

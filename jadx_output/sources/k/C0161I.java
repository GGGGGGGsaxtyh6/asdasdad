package k;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import f.AbstractC0101a;
import org.conscrypt.R;

/* renamed from: k.I, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0161I extends C0156D {

    /* renamed from: e, reason: collision with root package name */
    public final C0160H f1788e;

    /* renamed from: f, reason: collision with root package name */
    public Drawable f1789f;

    /* renamed from: g, reason: collision with root package name */
    public ColorStateList f1790g;
    public PorterDuff.Mode h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f1791i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f1792j;

    public C0161I(C0160H c0160h) {
        super(c0160h);
        this.f1790g = null;
        this.h = null;
        this.f1791i = false;
        this.f1792j = false;
        this.f1788e = c0160h;
    }

    @Override // k.C0156D
    public final void b(AttributeSet attributeSet, int i2) {
        super.b(attributeSet, R.attr.seekBarStyle);
        C0160H c0160h = this.f1788e;
        Context context = c0160h.getContext();
        int[] iArr = AbstractC0101a.f1268g;
        D0.h p2 = D0.h.p(context, attributeSet, iArr, R.attr.seekBarStyle);
        H.N.g(c0160h, c0160h.getContext(), iArr, attributeSet, (TypedArray) p2.f259c, R.attr.seekBarStyle);
        Drawable k2 = p2.k(0);
        if (k2 != null) {
            c0160h.setThumb(k2);
        }
        Drawable j2 = p2.j(1);
        Drawable drawable = this.f1789f;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.f1789f = j2;
        if (j2 != null) {
            j2.setCallback(c0160h);
            j2.setLayoutDirection(c0160h.getLayoutDirection());
            if (j2.isStateful()) {
                j2.setState(c0160h.getDrawableState());
            }
            f();
        }
        c0160h.invalidate();
        TypedArray typedArray = (TypedArray) p2.f259c;
        if (typedArray.hasValue(3)) {
            this.h = AbstractC0191n0.b(typedArray.getInt(3, -1), this.h);
            this.f1792j = true;
        }
        if (typedArray.hasValue(2)) {
            this.f1790g = p2.i(2);
            this.f1791i = true;
        }
        p2.r();
        f();
    }

    public final void f() {
        Drawable drawable = this.f1789f;
        if (drawable != null) {
            if (this.f1791i || this.f1792j) {
                Drawable mutate = drawable.mutate();
                this.f1789f = mutate;
                if (this.f1791i) {
                    mutate.setTintList(this.f1790g);
                }
                if (this.f1792j) {
                    this.f1789f.setTintMode(this.h);
                }
                if (this.f1789f.isStateful()) {
                    this.f1789f.setState(this.f1788e.getDrawableState());
                }
            }
        }
    }

    public final void g(Canvas canvas) {
        int i2;
        if (this.f1789f != null) {
            int max = this.f1788e.getMax();
            int i3 = 1;
            if (max > 1) {
                int intrinsicWidth = this.f1789f.getIntrinsicWidth();
                int intrinsicHeight = this.f1789f.getIntrinsicHeight();
                if (intrinsicWidth >= 0) {
                    i2 = intrinsicWidth / 2;
                } else {
                    i2 = 1;
                }
                if (intrinsicHeight >= 0) {
                    i3 = intrinsicHeight / 2;
                }
                this.f1789f.setBounds(-i2, -i3, i2, i3);
                float width = ((r0.getWidth() - r0.getPaddingLeft()) - r0.getPaddingRight()) / max;
                int save = canvas.save();
                canvas.translate(r0.getPaddingLeft(), r0.getHeight() / 2);
                for (int i4 = 0; i4 <= max; i4++) {
                    this.f1789f.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }
}

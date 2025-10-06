package k;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/* renamed from: k.A, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0153A extends ImageView {

    /* renamed from: a, reason: collision with root package name */
    public final C0194p f1741a;

    /* renamed from: b, reason: collision with root package name */
    public final C0213z f1742b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f1743c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0153A(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        T0.a(context);
        this.f1743c = false;
        S0.a(this, getContext());
        C0194p c0194p = new C0194p(this);
        this.f1741a = c0194p;
        c0194p.d(attributeSet, i2);
        C0213z c0213z = new C0213z(this);
        this.f1742b = c0213z;
        c0213z.c(attributeSet, i2);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0194p c0194p = this.f1741a;
        if (c0194p != null) {
            c0194p.a();
        }
        C0213z c0213z = this.f1742b;
        if (c0213z != null) {
            c0213z.a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0194p c0194p = this.f1741a;
        if (c0194p != null) {
            return c0194p.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0194p c0194p = this.f1741a;
        if (c0194p != null) {
            return c0194p.c();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        U0 u02;
        C0213z c0213z = this.f1742b;
        if (c0213z == null || (u02 = (U0) c0213z.f2071d) == null) {
            return null;
        }
        return (ColorStateList) u02.f1851c;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        U0 u02;
        C0213z c0213z = this.f1742b;
        if (c0213z == null || (u02 = (U0) c0213z.f2071d) == null) {
            return null;
        }
        return (PorterDuff.Mode) u02.f1852d;
    }

    @Override // android.widget.ImageView, android.view.View
    public final boolean hasOverlappingRendering() {
        if (!(((ImageView) this.f1742b.f2070c).getBackground() instanceof RippleDrawable) && super.hasOverlappingRendering()) {
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0194p c0194p = this.f1741a;
        if (c0194p != null) {
            c0194p.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        C0194p c0194p = this.f1741a;
        if (c0194p != null) {
            c0194p.f(i2);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        C0213z c0213z = this.f1742b;
        if (c0213z != null) {
            c0213z.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        C0213z c0213z = this.f1742b;
        if (c0213z != null && drawable != null && !this.f1743c) {
            c0213z.f2069b = drawable.getLevel();
        }
        super.setImageDrawable(drawable);
        if (c0213z != null) {
            c0213z.a();
            if (!this.f1743c) {
                ImageView imageView = (ImageView) c0213z.f2070c;
                if (imageView.getDrawable() != null) {
                    imageView.getDrawable().setLevel(c0213z.f2069b);
                }
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageLevel(int i2) {
        super.setImageLevel(i2);
        this.f1743c = true;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i2) {
        C0213z c0213z = this.f1742b;
        if (c0213z != null) {
            ImageView imageView = (ImageView) c0213z.f2070c;
            if (i2 != 0) {
                Drawable v2 = C0.d.v(imageView.getContext(), i2);
                if (v2 != null) {
                    AbstractC0191n0.a(v2);
                }
                imageView.setImageDrawable(v2);
            } else {
                imageView.setImageDrawable(null);
            }
            c0213z.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        C0213z c0213z = this.f1742b;
        if (c0213z != null) {
            c0213z.a();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0194p c0194p = this.f1741a;
        if (c0194p != null) {
            c0194p.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0194p c0194p = this.f1741a;
        if (c0194p != null) {
            c0194p.i(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        C0213z c0213z = this.f1742b;
        if (c0213z != null) {
            if (((U0) c0213z.f2071d) == null) {
                c0213z.f2071d = new Object();
            }
            U0 u02 = (U0) c0213z.f2071d;
            u02.f1851c = colorStateList;
            u02.f1850b = true;
            c0213z.a();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        C0213z c0213z = this.f1742b;
        if (c0213z != null) {
            if (((U0) c0213z.f2071d) == null) {
                c0213z.f2071d = new Object();
            }
            U0 u02 = (U0) c0213z.f2071d;
            u02.f1852d = mode;
            u02.f1849a = true;
            c0213z.a();
        }
    }
}

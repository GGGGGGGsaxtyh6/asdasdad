package k;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;

/* renamed from: k.s, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0199s extends CheckedTextView {

    /* renamed from: a, reason: collision with root package name */
    public final C0201t f2007a;

    /* renamed from: b, reason: collision with root package name */
    public final C0194p f2008b;

    /* renamed from: c, reason: collision with root package name */
    public final Z f2009c;

    /* renamed from: d, reason: collision with root package name */
    public C0209x f2010d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0086 A[Catch: all -> 0x0066, TryCatch #1 {all -> 0x0066, blocks: (B:3:0x004d, B:5:0x0054, B:8:0x005a, B:9:0x007f, B:11:0x0086, B:12:0x008d, B:14:0x0094, B:21:0x0068, B:23:0x006e, B:25:0x0074), top: B:2:0x004d }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0094 A[Catch: all -> 0x0066, TRY_LEAVE, TryCatch #1 {all -> 0x0066, blocks: (B:3:0x004d, B:5:0x0054, B:8:0x005a, B:9:0x007f, B:11:0x0086, B:12:0x008d, B:14:0x0094, B:21:0x0068, B:23:0x006e, B:25:0x0074), top: B:2:0x004d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public C0199s(android.content.Context r9, android.util.AttributeSet r10) {
        /*
            r8 = this;
            k.T0.a(r9)
            r6 = 2130903143(0x7f030067, float:1.7413096E38)
            r8.<init>(r9, r10, r6)
            android.content.Context r9 = r8.getContext()
            k.S0.a(r8, r9)
            k.Z r9 = new k.Z
            r9.<init>(r8)
            r8.f2009c = r9
            r9.f(r10, r6)
            r9.b()
            k.p r9 = new k.p
            r9.<init>(r8)
            r8.f2008b = r9
            r9.d(r10, r6)
            k.t r9 = new k.t
            r9.<init>(r8)
            r8.f2007a = r9
            android.content.Context r9 = r8.getContext()
            int[] r2 = f.AbstractC0101a.f1272l
            D0.h r9 = D0.h.p(r9, r10, r2, r6)
            java.lang.Object r0 = r9.f259c
            r7 = r0
            android.content.res.TypedArray r7 = (android.content.res.TypedArray) r7
            android.content.Context r1 = r8.getContext()
            java.lang.Object r0 = r9.f259c
            r4 = r0
            android.content.res.TypedArray r4 = (android.content.res.TypedArray) r4
            r0 = r8
            r3 = r10
            r5 = r6
            H.N.g(r0, r1, r2, r3, r4, r5)
            r0 = 1
            boolean r1 = r7.hasValue(r0)     // Catch: java.lang.Throwable -> L66
            r2 = 0
            if (r1 == 0) goto L68
            int r0 = r7.getResourceId(r0, r2)     // Catch: java.lang.Throwable -> L66
            if (r0 == 0) goto L68
            android.content.Context r1 = r8.getContext()     // Catch: java.lang.Throwable -> L66 android.content.res.Resources.NotFoundException -> L68
            android.graphics.drawable.Drawable r0 = C0.d.v(r1, r0)     // Catch: java.lang.Throwable -> L66 android.content.res.Resources.NotFoundException -> L68
            r8.setCheckMarkDrawable(r0)     // Catch: java.lang.Throwable -> L66 android.content.res.Resources.NotFoundException -> L68
            goto L7f
        L66:
            r10 = move-exception
            goto Lac
        L68:
            boolean r0 = r7.hasValue(r2)     // Catch: java.lang.Throwable -> L66
            if (r0 == 0) goto L7f
            int r0 = r7.getResourceId(r2, r2)     // Catch: java.lang.Throwable -> L66
            if (r0 == 0) goto L7f
            android.content.Context r1 = r8.getContext()     // Catch: java.lang.Throwable -> L66
            android.graphics.drawable.Drawable r0 = C0.d.v(r1, r0)     // Catch: java.lang.Throwable -> L66
            r8.setCheckMarkDrawable(r0)     // Catch: java.lang.Throwable -> L66
        L7f:
            r0 = 2
            boolean r1 = r7.hasValue(r0)     // Catch: java.lang.Throwable -> L66
            if (r1 == 0) goto L8d
            android.content.res.ColorStateList r0 = r9.i(r0)     // Catch: java.lang.Throwable -> L66
            r8.setCheckMarkTintList(r0)     // Catch: java.lang.Throwable -> L66
        L8d:
            r0 = 3
            boolean r1 = r7.hasValue(r0)     // Catch: java.lang.Throwable -> L66
            if (r1 == 0) goto La1
            r1 = -1
            int r0 = r7.getInt(r0, r1)     // Catch: java.lang.Throwable -> L66
            r1 = 0
            android.graphics.PorterDuff$Mode r0 = k.AbstractC0191n0.b(r0, r1)     // Catch: java.lang.Throwable -> L66
            r8.setCheckMarkTintMode(r0)     // Catch: java.lang.Throwable -> L66
        La1:
            r9.r()
            k.x r9 = r8.getEmojiTextViewHelper()
            r9.a(r10, r6)
            return
        Lac:
            r9.r()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: k.C0199s.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    private C0209x getEmojiTextViewHelper() {
        if (this.f2010d == null) {
            this.f2010d = new C0209x(this);
        }
        return this.f2010d;
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Z z2 = this.f2009c;
        if (z2 != null) {
            z2.b();
        }
        C0194p c0194p = this.f2008b;
        if (c0194p != null) {
            c0194p.a();
        }
        C0201t c0201t = this.f2007a;
        if (c0201t != null) {
            c0201t.b();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return C0.f.B(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0194p c0194p = this.f2008b;
        if (c0194p != null) {
            return c0194p.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0194p c0194p = this.f2008b;
        if (c0194p != null) {
            return c0194p.c();
        }
        return null;
    }

    public ColorStateList getSupportCheckMarkTintList() {
        C0201t c0201t = this.f2007a;
        if (c0201t != null) {
            return c0201t.f2012a;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCheckMarkTintMode() {
        C0201t c0201t = this.f2007a;
        if (c0201t != null) {
            return c0201t.f2013b;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f2009c.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f2009c.e();
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C0.m.m(editorInfo, onCreateInputConnection, this);
        return onCreateInputConnection;
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z2) {
        super.setAllCaps(z2);
        getEmojiTextViewHelper().b(z2);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0194p c0194p = this.f2008b;
        if (c0194p != null) {
            c0194p.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        C0194p c0194p = this.f2008b;
        if (c0194p != null) {
            c0194p.f(i2);
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(Drawable drawable) {
        super.setCheckMarkDrawable(drawable);
        C0201t c0201t = this.f2007a;
        if (c0201t != null) {
            if (c0201t.f2016e) {
                c0201t.f2016e = false;
            } else {
                c0201t.f2016e = true;
                c0201t.b();
            }
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        Z z2 = this.f2009c;
        if (z2 != null) {
            z2.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        Z z2 = this.f2009c;
        if (z2 != null) {
            z2.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C0.f.C(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z2) {
        getEmojiTextViewHelper().c(z2);
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0194p c0194p = this.f2008b;
        if (c0194p != null) {
            c0194p.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0194p c0194p = this.f2008b;
        if (c0194p != null) {
            c0194p.i(mode);
        }
    }

    public void setSupportCheckMarkTintList(ColorStateList colorStateList) {
        C0201t c0201t = this.f2007a;
        if (c0201t != null) {
            c0201t.f2012a = colorStateList;
            c0201t.f2014c = true;
            c0201t.b();
        }
    }

    public void setSupportCheckMarkTintMode(PorterDuff.Mode mode) {
        C0201t c0201t = this.f2007a;
        if (c0201t != null) {
            c0201t.f2013b = mode;
            c0201t.f2015d = true;
            c0201t.b();
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        Z z2 = this.f2009c;
        z2.l(colorStateList);
        z2.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        Z z2 = this.f2009c;
        z2.m(mode);
        z2.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        Z z2 = this.f2009c;
        if (z2 != null) {
            z2.g(context, i2);
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i2) {
        setCheckMarkDrawable(C0.d.v(getContext(), i2));
    }
}

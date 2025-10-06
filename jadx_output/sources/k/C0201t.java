package k;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import android.widget.TextView;

/* renamed from: k.t, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0201t {

    /* renamed from: a, reason: collision with root package name */
    public ColorStateList f2012a = null;

    /* renamed from: b, reason: collision with root package name */
    public PorterDuff.Mode f2013b = null;

    /* renamed from: c, reason: collision with root package name */
    public boolean f2014c = false;

    /* renamed from: d, reason: collision with root package name */
    public boolean f2015d = false;

    /* renamed from: e, reason: collision with root package name */
    public boolean f2016e;

    /* renamed from: f, reason: collision with root package name */
    public final TextView f2017f;

    public /* synthetic */ C0201t(TextView textView) {
        this.f2017f = textView;
    }

    public void a() {
        CompoundButton compoundButton = (CompoundButton) this.f2017f;
        Drawable buttonDrawable = compoundButton.getButtonDrawable();
        if (buttonDrawable != null) {
            if (this.f2014c || this.f2015d) {
                Drawable mutate = buttonDrawable.mutate();
                if (this.f2014c) {
                    mutate.setTintList(this.f2012a);
                }
                if (this.f2015d) {
                    mutate.setTintMode(this.f2013b);
                }
                if (mutate.isStateful()) {
                    mutate.setState(compoundButton.getDrawableState());
                }
                compoundButton.setButtonDrawable(mutate);
            }
        }
    }

    public void b() {
        C0199s c0199s = (C0199s) this.f2017f;
        Drawable checkMarkDrawable = c0199s.getCheckMarkDrawable();
        if (checkMarkDrawable != null) {
            if (this.f2014c || this.f2015d) {
                Drawable mutate = checkMarkDrawable.mutate();
                if (this.f2014c) {
                    mutate.setTintList(this.f2012a);
                }
                if (this.f2015d) {
                    mutate.setTintMode(this.f2013b);
                }
                if (mutate.isStateful()) {
                    mutate.setState(c0199s.getDrawableState());
                }
                c0199s.setCheckMarkDrawable(mutate);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x005c A[Catch: all -> 0x003c, TryCatch #1 {all -> 0x003c, blocks: (B:3:0x0023, B:5:0x002a, B:8:0x0030, B:9:0x0055, B:11:0x005c, B:12:0x0063, B:14:0x006a, B:21:0x003e, B:23:0x0044, B:25:0x004a), top: B:2:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006a A[Catch: all -> 0x003c, TRY_LEAVE, TryCatch #1 {all -> 0x003c, blocks: (B:3:0x0023, B:5:0x002a, B:8:0x0030, B:9:0x0055, B:11:0x005c, B:12:0x0063, B:14:0x006a, B:21:0x003e, B:23:0x0044, B:25:0x004a), top: B:2:0x0023 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(android.util.AttributeSet r10, int r11) {
        /*
            r9 = this;
            android.widget.TextView r0 = r9.f2017f
            android.widget.CompoundButton r0 = (android.widget.CompoundButton) r0
            android.content.Context r1 = r0.getContext()
            int[] r3 = f.AbstractC0101a.f1273m
            D0.h r7 = D0.h.p(r1, r10, r3, r11)
            java.lang.Object r1 = r7.f259c
            r8 = r1
            android.content.res.TypedArray r8 = (android.content.res.TypedArray) r8
            android.content.Context r2 = r0.getContext()
            java.lang.Object r1 = r7.f259c
            r5 = r1
            android.content.res.TypedArray r5 = (android.content.res.TypedArray) r5
            r1 = r0
            r4 = r10
            r6 = r11
            H.N.g(r1, r2, r3, r4, r5, r6)
            r10 = 1
            boolean r11 = r8.hasValue(r10)     // Catch: java.lang.Throwable -> L3c
            r1 = 0
            if (r11 == 0) goto L3e
            int r10 = r8.getResourceId(r10, r1)     // Catch: java.lang.Throwable -> L3c
            if (r10 == 0) goto L3e
            android.content.Context r11 = r0.getContext()     // Catch: java.lang.Throwable -> L3c android.content.res.Resources.NotFoundException -> L3e
            android.graphics.drawable.Drawable r10 = C0.d.v(r11, r10)     // Catch: java.lang.Throwable -> L3c android.content.res.Resources.NotFoundException -> L3e
            r0.setButtonDrawable(r10)     // Catch: java.lang.Throwable -> L3c android.content.res.Resources.NotFoundException -> L3e
            goto L55
        L3c:
            r10 = move-exception
            goto L7b
        L3e:
            boolean r10 = r8.hasValue(r1)     // Catch: java.lang.Throwable -> L3c
            if (r10 == 0) goto L55
            int r10 = r8.getResourceId(r1, r1)     // Catch: java.lang.Throwable -> L3c
            if (r10 == 0) goto L55
            android.content.Context r11 = r0.getContext()     // Catch: java.lang.Throwable -> L3c
            android.graphics.drawable.Drawable r10 = C0.d.v(r11, r10)     // Catch: java.lang.Throwable -> L3c
            r0.setButtonDrawable(r10)     // Catch: java.lang.Throwable -> L3c
        L55:
            r10 = 2
            boolean r11 = r8.hasValue(r10)     // Catch: java.lang.Throwable -> L3c
            if (r11 == 0) goto L63
            android.content.res.ColorStateList r10 = r7.i(r10)     // Catch: java.lang.Throwable -> L3c
            r0.setButtonTintList(r10)     // Catch: java.lang.Throwable -> L3c
        L63:
            r10 = 3
            boolean r11 = r8.hasValue(r10)     // Catch: java.lang.Throwable -> L3c
            if (r11 == 0) goto L77
            r11 = -1
            int r10 = r8.getInt(r10, r11)     // Catch: java.lang.Throwable -> L3c
            r11 = 0
            android.graphics.PorterDuff$Mode r10 = k.AbstractC0191n0.b(r10, r11)     // Catch: java.lang.Throwable -> L3c
            r0.setButtonTintMode(r10)     // Catch: java.lang.Throwable -> L3c
        L77:
            r7.r()
            return
        L7b:
            r7.r()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: k.C0201t.c(android.util.AttributeSet, int):void");
    }
}

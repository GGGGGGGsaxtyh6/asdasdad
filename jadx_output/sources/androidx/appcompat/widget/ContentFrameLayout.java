package androidx.appcompat.widget;

import H.S;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import g.C;
import g.r;
import j.n;
import k.C0176g;
import k.C0184k;
import k.InterfaceC0185k0;
import k.InterfaceC0187l0;
import k.e1;

/* loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    public TypedValue f895a;

    /* renamed from: b, reason: collision with root package name */
    public TypedValue f896b;

    /* renamed from: c, reason: collision with root package name */
    public TypedValue f897c;

    /* renamed from: d, reason: collision with root package name */
    public TypedValue f898d;

    /* renamed from: e, reason: collision with root package name */
    public TypedValue f899e;

    /* renamed from: f, reason: collision with root package name */
    public TypedValue f900f;

    /* renamed from: g, reason: collision with root package name */
    public final Rect f901g;
    public InterfaceC0185k0 h;

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f901g = new Rect();
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f899e == null) {
            this.f899e = new TypedValue();
        }
        return this.f899e;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f900f == null) {
            this.f900f = new TypedValue();
        }
        return this.f900f;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f897c == null) {
            this.f897c = new TypedValue();
        }
        return this.f897c;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f898d == null) {
            this.f898d = new TypedValue();
        }
        return this.f898d;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f895a == null) {
            this.f895a = new TypedValue();
        }
        return this.f895a;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f896b == null) {
            this.f896b = new TypedValue();
        }
        return this.f896b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        InterfaceC0185k0 interfaceC0185k0 = this.h;
        if (interfaceC0185k0 != null) {
            interfaceC0185k0.getClass();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        C0184k c0184k;
        super.onDetachedFromWindow();
        InterfaceC0185k0 interfaceC0185k0 = this.h;
        if (interfaceC0185k0 != null) {
            C c2 = ((r) interfaceC0185k0).f1469b;
            InterfaceC0187l0 interfaceC0187l0 = c2.f1345r;
            if (interfaceC0187l0 != null) {
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) interfaceC0187l0;
                actionBarOverlayLayout.k();
                ActionMenuView actionMenuView = ((e1) actionBarOverlayLayout.f860e).f1895a.f925a;
                if (actionMenuView != null && (c0184k = actionMenuView.f885t) != null) {
                    c0184k.e();
                    C0176g c0176g = c0184k.f1969t;
                    if (c0176g != null && c0176g.b()) {
                        c0176g.f1730i.dismiss();
                    }
                }
            }
            if (c2.f1350w != null) {
                c2.f1339l.getDecorView().removeCallbacks(c2.f1351x);
                if (c2.f1350w.isShowing()) {
                    try {
                        c2.f1350w.dismiss();
                    } catch (IllegalArgumentException unused) {
                    }
                }
                c2.f1350w = null;
            }
            S s2 = c2.y;
            if (s2 != null) {
                s2.b();
            }
            n nVar = c2.A(0).h;
            if (nVar != null) {
                nVar.c(true);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ac A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00b3  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasure(int r17, int r18) {
        /*
            Method dump skipped, instructions count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public void setAttachListener(InterfaceC0185k0 interfaceC0185k0) {
        this.h = interfaceC0185k0;
    }
}

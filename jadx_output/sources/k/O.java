package k;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import j.ViewTreeObserverOnGlobalLayoutListenerC0142d;
import org.conscrypt.R;

/* loaded from: classes.dex */
public final class O extends G0 implements Q {

    /* renamed from: B, reason: collision with root package name */
    public CharSequence f1816B;

    /* renamed from: C, reason: collision with root package name */
    public M f1817C;

    /* renamed from: D, reason: collision with root package name */
    public final Rect f1818D;

    /* renamed from: E, reason: collision with root package name */
    public int f1819E;

    /* renamed from: F, reason: collision with root package name */
    public final /* synthetic */ S f1820F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public O(S s2, Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.spinnerStyle);
        this.f1820F = s2;
        this.f1818D = new Rect();
        this.f1777o = s2;
        this.f1786x = true;
        this.y.setFocusable(true);
        this.f1778p = new Z.o(1, this);
    }

    @Override // k.Q
    public final CharSequence a() {
        return this.f1816B;
    }

    @Override // k.Q
    public final void g(int i2, int i3) {
        ViewTreeObserver viewTreeObserver;
        C0155C c0155c = this.y;
        boolean isShowing = c0155c.isShowing();
        s();
        this.y.setInputMethodMode(2);
        i();
        C0202t0 c0202t0 = this.f1766c;
        c0202t0.setChoiceMode(1);
        c0202t0.setTextDirection(i2);
        c0202t0.setTextAlignment(i3);
        S s2 = this.f1820F;
        int selectedItemPosition = s2.getSelectedItemPosition();
        C0202t0 c0202t02 = this.f1766c;
        if (c0155c.isShowing() && c0202t02 != null) {
            c0202t02.setListSelectionHidden(false);
            c0202t02.setSelection(selectedItemPosition);
            if (c0202t02.getChoiceMode() != 0) {
                c0202t02.setItemChecked(selectedItemPosition, true);
            }
        }
        if (!isShowing && (viewTreeObserver = s2.getViewTreeObserver()) != null) {
            ViewTreeObserverOnGlobalLayoutListenerC0142d viewTreeObserverOnGlobalLayoutListenerC0142d = new ViewTreeObserverOnGlobalLayoutListenerC0142d(3, this);
            viewTreeObserver.addOnGlobalLayoutListener(viewTreeObserverOnGlobalLayoutListenerC0142d);
            this.y.setOnDismissListener(new N(this, viewTreeObserverOnGlobalLayoutListenerC0142d));
        }
    }

    @Override // k.Q
    public final void h(CharSequence charSequence) {
        this.f1816B = charSequence;
    }

    @Override // k.G0, k.Q
    public final void o(ListAdapter listAdapter) {
        super.o(listAdapter);
        this.f1817C = (M) listAdapter;
    }

    @Override // k.Q
    public final void p(int i2) {
        this.f1819E = i2;
    }

    public final void s() {
        int i2;
        int i3;
        C0155C c0155c = this.y;
        Drawable background = c0155c.getBackground();
        S s2 = this.f1820F;
        if (background != null) {
            background.getPadding(s2.h);
            boolean z2 = m1.f1979a;
            int layoutDirection = s2.getLayoutDirection();
            Rect rect = s2.h;
            if (layoutDirection == 1) {
                i2 = rect.right;
            } else {
                i2 = -rect.left;
            }
        } else {
            Rect rect2 = s2.h;
            rect2.right = 0;
            rect2.left = 0;
            i2 = 0;
        }
        int paddingLeft = s2.getPaddingLeft();
        int paddingRight = s2.getPaddingRight();
        int width = s2.getWidth();
        int i4 = s2.f1836g;
        if (i4 == -2) {
            int a2 = s2.a(this.f1817C, c0155c.getBackground());
            int i5 = s2.getContext().getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = s2.h;
            int i6 = (i5 - rect3.left) - rect3.right;
            if (a2 > i6) {
                a2 = i6;
            }
            r(Math.max(a2, (width - paddingLeft) - paddingRight));
        } else if (i4 == -1) {
            r((width - paddingLeft) - paddingRight);
        } else {
            r(i4);
        }
        boolean z3 = m1.f1979a;
        if (s2.getLayoutDirection() == 1) {
            i3 = (((width - paddingRight) - this.f1768e) - this.f1819E) + i2;
        } else {
            i3 = paddingLeft + this.f1819E + i2;
        }
        this.f1769f = i3;
    }
}

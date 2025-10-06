package g;

import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.appcompat.widget.ContentFrameLayout;
import i.C0122d;

/* renamed from: g.A, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0102A extends ContentFrameLayout {

    /* renamed from: i, reason: collision with root package name */
    public final /* synthetic */ C f1287i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0102A(C c2, C0122d c0122d) {
        super(c0122d, null);
        this.f1287i = c2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!this.f1287i.v(keyEvent) && !super.dispatchKeyEvent(keyEvent)) {
            return false;
        }
        return true;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            int x2 = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (x2 < -5 || y < -5 || x2 > getWidth() + 5 || y > getHeight() + 5) {
                C c2 = this.f1287i;
                c2.t(c2.A(0), true);
                return true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public final void setBackgroundResource(int i2) {
        setBackgroundDrawable(C0.d.v(getContext(), i2));
    }
}

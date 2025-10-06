package k;

import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes.dex */
public final class F0 implements View.OnTouchListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ G0 f1761a;

    public F0(G0 g0) {
        this.f1761a = g0;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        C0155C c0155c;
        int action = motionEvent.getAction();
        int x2 = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        G0 g0 = this.f1761a;
        if (action == 0 && (c0155c = g0.y) != null && c0155c.isShowing() && x2 >= 0 && x2 < g0.y.getWidth() && y >= 0 && y < g0.y.getHeight()) {
            g0.f1783u.postDelayed(g0.f1779q, 250L);
            return false;
        }
        if (action == 1) {
            g0.f1783u.removeCallbacks(g0.f1779q);
            return false;
        }
        return false;
    }
}

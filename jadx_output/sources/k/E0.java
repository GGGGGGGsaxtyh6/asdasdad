package k;

import android.os.Handler;
import android.widget.AbsListView;

/* loaded from: classes.dex */
public final class E0 implements AbsListView.OnScrollListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ G0 f1759a;

    public E0(G0 g0) {
        this.f1759a = g0;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScrollStateChanged(AbsListView absListView, int i2) {
        if (i2 == 1) {
            G0 g0 = this.f1759a;
            if (g0.y.getInputMethodMode() != 2 && g0.y.getContentView() != null) {
                Handler handler = g0.f1783u;
                C0 c02 = g0.f1779q;
                handler.removeCallbacks(c02);
                c02.run();
            }
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScroll(AbsListView absListView, int i2, int i3, int i4) {
    }
}

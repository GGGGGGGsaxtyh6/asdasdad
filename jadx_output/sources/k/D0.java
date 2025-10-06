package k;

import android.database.DataSetObserver;

/* loaded from: classes.dex */
public final class D0 extends DataSetObserver {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ G0 f1754a;

    public D0(G0 g0) {
        this.f1754a = g0;
    }

    @Override // android.database.DataSetObserver
    public final void onChanged() {
        G0 g0 = this.f1754a;
        if (g0.y.isShowing()) {
            g0.i();
        }
    }

    @Override // android.database.DataSetObserver
    public final void onInvalidated() {
        this.f1754a.dismiss();
    }
}

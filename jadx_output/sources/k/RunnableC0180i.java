package k;

import android.view.View;

/* renamed from: k.i, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class RunnableC0180i implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final C0176g f1925a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0184k f1926b;

    public RunnableC0180i(C0184k c0184k, C0176g c0176g) {
        this.f1926b = c0184k;
        this.f1925a = c0176g;
    }

    @Override // java.lang.Runnable
    public final void run() {
        j.l lVar;
        C0184k c0184k = this.f1926b;
        j.n nVar = c0184k.f1953c;
        if (nVar != null && (lVar = nVar.f1662e) != null) {
            lVar.k(nVar);
        }
        View view = (View) c0184k.h;
        if (view != null && view.getWindowToken() != null) {
            C0176g c0176g = this.f1925a;
            if (!c0176g.b()) {
                if (c0176g.f1727e != null) {
                    c0176g.d(0, 0, false, false);
                }
            }
            c0184k.f1968s = c0176g;
        }
        c0184k.f1970u = null;
    }
}

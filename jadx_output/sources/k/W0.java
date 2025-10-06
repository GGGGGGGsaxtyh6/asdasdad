package k;

import androidx.appcompat.widget.Toolbar;

/* loaded from: classes.dex */
public final /* synthetic */ class W0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1856a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Toolbar f1857b;

    public /* synthetic */ W0(Toolbar toolbar, int i2) {
        this.f1856a = i2;
        this.f1857b = toolbar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        j.p pVar;
        switch (this.f1856a) {
            case 0:
                Z0 z02 = this.f1857b.f917M;
                if (z02 == null) {
                    pVar = null;
                } else {
                    pVar = z02.f1872b;
                }
                if (pVar != null) {
                    pVar.collapseActionView();
                    return;
                }
                return;
            default:
                this.f1857b.m();
                return;
        }
    }
}

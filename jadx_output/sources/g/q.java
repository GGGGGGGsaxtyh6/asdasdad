package g;

import H.S;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public final class q implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1466a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C f1467b;

    public /* synthetic */ q(C c2, int i2) {
        this.f1466a = i2;
        this.f1467b = c2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z2;
        ViewGroup viewGroup;
        switch (this.f1466a) {
            case 0:
                C c2 = this.f1467b;
                if ((c2.f1330Z & 1) != 0) {
                    c2.w(0);
                }
                if ((c2.f1330Z & 4096) != 0) {
                    c2.w(108);
                }
                c2.f1329Y = false;
                c2.f1330Z = 0;
                return;
            default:
                C c3 = this.f1467b;
                c3.f1350w.showAtLocation(c3.f1349v, 55, 0, 0);
                S s2 = c3.y;
                if (s2 != null) {
                    s2.b();
                }
                if (c3.f1352z && (viewGroup = c3.f1305A) != null && viewGroup.isLaidOut()) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    c3.f1349v.setAlpha(0.0f);
                    S a2 = H.N.a(c3.f1349v);
                    a2.a(1.0f);
                    c3.y = a2;
                    a2.d(new s(0, this));
                    return;
                }
                c3.f1349v.setAlpha(1.0f);
                c3.f1349v.setVisibility(0);
                return;
        }
    }
}

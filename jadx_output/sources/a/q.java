package a;

import android.window.OnBackInvokedCallback;
import g.C;

/* loaded from: classes.dex */
public final /* synthetic */ class q implements OnBackInvokedCallback {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f762a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f763b;

    public /* synthetic */ q(int i2, Object obj) {
        this.f762a = i2;
        this.f763b = obj;
    }

    public final void onBackInvoked() {
        switch (this.f762a) {
            case 0:
                ((p) this.f763b).a();
                return;
            case 1:
                ((C) this.f763b).E();
                return;
            default:
                ((Runnable) this.f763b).run();
                return;
        }
    }
}

package k;

import androidx.appcompat.widget.ActionBarOverlayLayout;

/* renamed from: k.c, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class RunnableC0168c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1881a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ActionBarOverlayLayout f1882b;

    public /* synthetic */ RunnableC0168c(ActionBarOverlayLayout actionBarOverlayLayout, int i2) {
        this.f1881a = i2;
        this.f1882b = actionBarOverlayLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1881a) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = this.f1882b;
                actionBarOverlayLayout.h();
                actionBarOverlayLayout.f877w = actionBarOverlayLayout.f859d.animate().translationY(0.0f).setListener(actionBarOverlayLayout.f878x);
                return;
            default:
                ActionBarOverlayLayout actionBarOverlayLayout2 = this.f1882b;
                actionBarOverlayLayout2.h();
                actionBarOverlayLayout2.f877w = actionBarOverlayLayout2.f859d.animate().translationY(-actionBarOverlayLayout2.f859d.getHeight()).setListener(actionBarOverlayLayout2.f878x);
                return;
        }
    }
}

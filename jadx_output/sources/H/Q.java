package H;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import androidx.appcompat.widget.ActionBarOverlayLayout;

/* loaded from: classes.dex */
public final class Q extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f334a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f335b;

    public Q(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.f335b = actionBarOverlayLayout;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        switch (this.f334a) {
            case 0:
                ((T) this.f335b).b();
                return;
            default:
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.f335b;
                actionBarOverlayLayout.f877w = null;
                actionBarOverlayLayout.f864j = false;
                return;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.f334a) {
            case 0:
                ((T) this.f335b).a();
                return;
            default:
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.f335b;
                actionBarOverlayLayout.f877w = null;
                actionBarOverlayLayout.f864j = false;
                return;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        switch (this.f334a) {
            case 0:
                ((T) this.f335b).c();
                return;
            default:
                super.onAnimationStart(animator);
                return;
        }
    }

    public Q(T t2, View view) {
        this.f335b = t2;
    }
}

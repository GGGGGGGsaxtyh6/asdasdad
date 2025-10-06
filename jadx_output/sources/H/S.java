package H;

import android.view.View;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class S {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f336a;

    public S(View view) {
        this.f336a = new WeakReference(view);
    }

    public final void a(float f2) {
        View view = (View) this.f336a.get();
        if (view != null) {
            view.animate().alpha(f2);
        }
    }

    public final void b() {
        View view = (View) this.f336a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public final void c(long j2) {
        View view = (View) this.f336a.get();
        if (view != null) {
            view.animate().setDuration(j2);
        }
    }

    public final void d(T t2) {
        View view = (View) this.f336a.get();
        if (view != null) {
            if (t2 != null) {
                view.animate().setListener(new Q(t2, view));
            } else {
                view.animate().setListener(null);
            }
        }
    }

    public final void e(float f2) {
        View view = (View) this.f336a.get();
        if (view != null) {
            view.animate().translationY(f2);
        }
    }
}

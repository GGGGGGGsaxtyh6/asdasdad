package a;

import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;
import j0.AbstractC0150d;

/* renamed from: a.j, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class ViewTreeObserverOnDrawListenerC0043j implements InterfaceExecutorC0042i, ViewTreeObserver.OnDrawListener, Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final long f749a = SystemClock.uptimeMillis() + 10000;

    /* renamed from: b, reason: collision with root package name */
    public Runnable f750b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f751c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.i f752d;

    public ViewTreeObserverOnDrawListenerC0043j(androidx.fragment.app.i iVar) {
        this.f752d = iVar;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        AbstractC0150d.e(runnable, "runnable");
        this.f750b = runnable;
        View decorView = this.f752d.getWindow().getDecorView();
        AbstractC0150d.d(decorView, "window.decorView");
        if (this.f751c) {
            if (AbstractC0150d.a(Looper.myLooper(), Looper.getMainLooper())) {
                decorView.invalidate();
                return;
            } else {
                decorView.postInvalidate();
                return;
            }
        }
        decorView.postOnAnimation(new M.r(2, this));
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public final void onDraw() {
        boolean z2;
        Runnable runnable = this.f750b;
        if (runnable != null) {
            runnable.run();
            this.f750b = null;
            n fullyDrawnReporter = this.f752d.getFullyDrawnReporter();
            synchronized (fullyDrawnReporter.f755a) {
                z2 = fullyDrawnReporter.f756b;
            }
            if (z2) {
                this.f751c = false;
                this.f752d.getWindow().getDecorView().post(this);
                return;
            }
            return;
        }
        if (SystemClock.uptimeMillis() > this.f749a) {
            this.f751c = false;
            this.f752d.getWindow().getDecorView().post(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f752d.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
    }
}

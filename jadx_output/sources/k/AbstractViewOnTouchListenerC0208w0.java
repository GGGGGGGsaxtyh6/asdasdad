package k;

import android.view.View;
import android.view.ViewConfiguration;
import j.InterfaceC0136D;

/* renamed from: k.w0, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractViewOnTouchListenerC0208w0 implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final float f2041a;

    /* renamed from: b, reason: collision with root package name */
    public final int f2042b;

    /* renamed from: c, reason: collision with root package name */
    public final int f2043c;

    /* renamed from: d, reason: collision with root package name */
    public final View f2044d;

    /* renamed from: e, reason: collision with root package name */
    public RunnableC0206v0 f2045e;

    /* renamed from: f, reason: collision with root package name */
    public RunnableC0206v0 f2046f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f2047g;
    public int h;

    /* renamed from: i, reason: collision with root package name */
    public final int[] f2048i = new int[2];

    public AbstractViewOnTouchListenerC0208w0(View view) {
        this.f2044d = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f2041a = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f2042b = tapTimeout;
        this.f2043c = (ViewConfiguration.getLongPressTimeout() + tapTimeout) / 2;
    }

    public final void a() {
        RunnableC0206v0 runnableC0206v0 = this.f2046f;
        View view = this.f2044d;
        if (runnableC0206v0 != null) {
            view.removeCallbacks(runnableC0206v0);
        }
        RunnableC0206v0 runnableC0206v02 = this.f2045e;
        if (runnableC0206v02 != null) {
            view.removeCallbacks(runnableC0206v02);
        }
    }

    public abstract InterfaceC0136D b();

    public abstract boolean c();

    public boolean d() {
        InterfaceC0136D b2 = b();
        if (b2 != null && b2.b()) {
            b2.dismiss();
            return true;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0059, code lost:
    
        if (r14 != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007b, code lost:
    
        if (r4 != 3) goto L58;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0100  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouch(android.view.View r13, android.view.MotionEvent r14) {
        /*
            Method dump skipped, instructions count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: k.AbstractViewOnTouchListenerC0208w0.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.f2047g = false;
        this.h = -1;
        RunnableC0206v0 runnableC0206v0 = this.f2045e;
        if (runnableC0206v0 != null) {
            this.f2044d.removeCallbacks(runnableC0206v0);
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}

package H;

import android.view.WindowInsets;

/* loaded from: classes.dex */
public class Z extends Y {

    /* renamed from: k, reason: collision with root package name */
    public A.c f351k;

    public Z(f0 f0Var, WindowInsets windowInsets) {
        super(f0Var, windowInsets);
        this.f351k = null;
    }

    @Override // H.e0
    public f0 b() {
        return f0.c(this.f348c.consumeStableInsets(), null);
    }

    @Override // H.e0
    public f0 c() {
        return f0.c(this.f348c.consumeSystemWindowInsets(), null);
    }

    @Override // H.e0
    public final A.c f() {
        if (this.f351k == null) {
            WindowInsets windowInsets = this.f348c;
            this.f351k = A.c.a(windowInsets.getStableInsetLeft(), windowInsets.getStableInsetTop(), windowInsets.getStableInsetRight(), windowInsets.getStableInsetBottom());
        }
        return this.f351k;
    }

    @Override // H.e0
    public boolean i() {
        return this.f348c.isConsumed();
    }

    @Override // H.e0
    public void m(A.c cVar) {
        this.f351k = cVar;
    }
}

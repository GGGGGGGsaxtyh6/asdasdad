package H;

import android.view.WindowInsets;

/* loaded from: classes.dex */
public class V extends X {

    /* renamed from: a, reason: collision with root package name */
    public final WindowInsets.Builder f343a;

    public V() {
        this.f343a = C0.k.b();
    }

    @Override // H.X
    public f0 b() {
        WindowInsets build;
        a();
        build = this.f343a.build();
        f0 c2 = f0.c(build, null);
        c2.f366a.k(null);
        return c2;
    }

    @Override // H.X
    public void c(A.c cVar) {
        this.f343a.setStableInsets(cVar.b());
    }

    @Override // H.X
    public void d(A.c cVar) {
        this.f343a.setSystemWindowInsets(cVar.b());
    }

    public V(f0 f0Var) {
        super(f0Var);
        WindowInsets.Builder b2;
        WindowInsets b3 = f0Var.b();
        if (b3 != null) {
            b2 = C0.k.c(b3);
        } else {
            b2 = C0.k.b();
        }
        this.f343a = b2;
    }
}

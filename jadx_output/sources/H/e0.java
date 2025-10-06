package H;

import android.os.Build;
import android.view.View;
import java.util.Objects;

/* loaded from: classes.dex */
public class e0 {

    /* renamed from: b, reason: collision with root package name */
    public static final f0 f363b;

    /* renamed from: a, reason: collision with root package name */
    public final f0 f364a;

    static {
        X u2;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            u2 = new W();
        } else if (i2 >= 29) {
            u2 = new V();
        } else {
            u2 = new U();
        }
        f363b = u2.b().f366a.a().f366a.b().f366a.c();
    }

    public e0(f0 f0Var) {
        this.f364a = f0Var;
    }

    public f0 a() {
        return this.f364a;
    }

    public f0 b() {
        return this.f364a;
    }

    public f0 c() {
        return this.f364a;
    }

    public C0016j e() {
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e0)) {
            return false;
        }
        e0 e0Var = (e0) obj;
        if (j() == e0Var.j() && i() == e0Var.i() && Objects.equals(g(), e0Var.g()) && Objects.equals(f(), e0Var.f()) && Objects.equals(e(), e0Var.e())) {
            return true;
        }
        return false;
    }

    public A.c f() {
        return A.c.f1e;
    }

    public A.c g() {
        return A.c.f1e;
    }

    public f0 h(int i2, int i3, int i4, int i5) {
        return f363b;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(j()), Boolean.valueOf(i()), g(), f(), e());
    }

    public boolean i() {
        return false;
    }

    public boolean j() {
        return false;
    }

    public void d(View view) {
    }

    public void k(A.c[] cVarArr) {
    }

    public void l(f0 f0Var) {
    }

    public void m(A.c cVar) {
    }
}

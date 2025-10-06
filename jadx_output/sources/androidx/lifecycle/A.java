package androidx.lifecycle;

import android.os.Handler;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public final class A implements InterfaceC0072t {

    /* renamed from: i, reason: collision with root package name */
    public static final A f1142i = new A();

    /* renamed from: a, reason: collision with root package name */
    public int f1143a;

    /* renamed from: b, reason: collision with root package name */
    public int f1144b;

    /* renamed from: e, reason: collision with root package name */
    public Handler f1147e;

    /* renamed from: c, reason: collision with root package name */
    public boolean f1145c = true;

    /* renamed from: d, reason: collision with root package name */
    public boolean f1146d = true;

    /* renamed from: f, reason: collision with root package name */
    public final v f1148f = new v(this);

    /* renamed from: g, reason: collision with root package name */
    public final M.r f1149g = new M.r(4, this);
    public final A.f h = new A.f(19, this);

    public final void a() {
        int i2 = this.f1144b + 1;
        this.f1144b = i2;
        if (i2 == 1) {
            if (this.f1145c) {
                this.f1148f.e(EnumC0066m.ON_RESUME);
                this.f1145c = false;
            } else {
                Handler handler = this.f1147e;
                AbstractC0150d.b(handler);
                handler.removeCallbacks(this.f1149g);
            }
        }
    }

    @Override // androidx.lifecycle.InterfaceC0072t
    public final AbstractC0068o getLifecycle() {
        return this.f1148f;
    }
}

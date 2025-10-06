package H;

import a.AbstractActivityC0046m;
import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.EnumC0066m;
import androidx.lifecycle.InterfaceC0072t;

/* renamed from: H.m, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final /* synthetic */ class C0019m implements androidx.lifecycle.r {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f379a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f380b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f381c;

    public /* synthetic */ C0019m(Object obj, Object obj2, int i2) {
        this.f379a = i2;
        this.f380b = obj;
        this.f381c = obj2;
    }

    @Override // androidx.lifecycle.r
    public final void f(InterfaceC0072t interfaceC0072t, EnumC0066m enumC0066m) {
        switch (this.f379a) {
            case 0:
                EnumC0066m enumC0066m2 = EnumC0066m.ON_DESTROY;
                C0021o c0021o = (C0021o) this.f380b;
                if (enumC0066m == enumC0066m2) {
                    c0021o.d((InterfaceC0023q) this.f381c);
                    return;
                } else {
                    c0021o.getClass();
                    return;
                }
            default:
                if (enumC0066m == EnumC0066m.ON_CREATE) {
                    OnBackInvokedDispatcher a2 = D.c.a((AbstractActivityC0046m) this.f381c);
                    a.v vVar = (a.v) this.f380b;
                    vVar.f783e = a2;
                    vVar.c(vVar.f785g);
                    return;
                }
                return;
        }
    }
}

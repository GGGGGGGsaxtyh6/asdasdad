package a;

import android.view.View;
import android.view.Window;
import androidx.lifecycle.EnumC0066m;
import androidx.lifecycle.InterfaceC0072t;
import j0.AbstractC0150d;

/* renamed from: a.d, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final /* synthetic */ class C0037d implements androidx.lifecycle.r {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f742a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f743b;

    public /* synthetic */ C0037d(int i2, Object obj) {
        this.f742a = i2;
        this.f743b = obj;
    }

    @Override // androidx.lifecycle.r
    public final void f(InterfaceC0072t interfaceC0072t, EnumC0066m enumC0066m) {
        Window window;
        View peekDecorView;
        switch (this.f742a) {
            case 0:
                if (enumC0066m == EnumC0066m.ON_STOP && (window = ((androidx.fragment.app.i) this.f743b).getWindow()) != null && (peekDecorView = window.peekDecorView()) != null) {
                    peekDecorView.cancelPendingInputEvents();
                    return;
                }
                return;
            case 1:
                AbstractActivityC0046m.b((androidx.fragment.app.i) this.f743b, interfaceC0072t, enumC0066m);
                return;
            default:
                U.e eVar = (U.e) this.f743b;
                AbstractC0150d.e(eVar, "this$0");
                if (enumC0066m == EnumC0066m.ON_START) {
                    eVar.f670f = true;
                    return;
                } else {
                    if (enumC0066m == EnumC0066m.ON_STOP) {
                        eVar.f670f = false;
                        return;
                    }
                    return;
                }
        }
    }
}

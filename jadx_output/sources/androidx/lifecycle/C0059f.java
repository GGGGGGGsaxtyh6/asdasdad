package androidx.lifecycle;

import M.AbstractC0033b;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.List;

/* renamed from: androidx.lifecycle.f, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0059f implements r {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1192a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f1193b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f1194c;

    public /* synthetic */ C0059f(Object obj, Object obj2, int i2) {
        this.f1192a = i2;
        this.f1193b = obj;
        this.f1194c = obj2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, java.lang.Runnable] */
    @Override // androidx.lifecycle.r
    public final void f(InterfaceC0072t interfaceC0072t, EnumC0066m enumC0066m) {
        Handler handler;
        switch (this.f1192a) {
            case 0:
                int i2 = AbstractC0058e.f1191a[enumC0066m.ordinal()];
                M.k kVar = (M.k) this.f1193b;
                if (i2 != 3) {
                    if (i2 == 7) {
                        throw new IllegalArgumentException("ON_ANY must not been send by anybody");
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= 28) {
                        handler = AbstractC0033b.a(Looper.getMainLooper());
                    } else {
                        handler = new Handler(Looper.getMainLooper());
                    }
                    handler.postDelayed(new Object(), 500L);
                    kVar.f542a.b(kVar);
                }
                r rVar = (r) this.f1194c;
                if (rVar != null) {
                    rVar.f(interfaceC0072t, enumC0066m);
                    return;
                }
                return;
            case 1:
                if (enumC0066m == EnumC0066m.ON_START) {
                    ((AbstractC0068o) this.f1193b).b(this);
                    ((U.e) this.f1194c).d();
                    return;
                }
                return;
            default:
                HashMap hashMap = ((C0055b) this.f1194c).f1184a;
                List list = (List) hashMap.get(enumC0066m);
                InterfaceC0071s interfaceC0071s = (InterfaceC0071s) this.f1193b;
                C0055b.a(list, interfaceC0072t, enumC0066m, interfaceC0071s);
                C0055b.a((List) hashMap.get(EnumC0066m.ON_ANY), interfaceC0072t, enumC0066m, interfaceC0071s);
                return;
        }
    }

    public C0059f(InterfaceC0071s interfaceC0071s) {
        this.f1192a = 2;
        this.f1193b = interfaceC0071s;
        C0057d c0057d = C0057d.f1188c;
        Class<?> cls = interfaceC0071s.getClass();
        C0055b c0055b = (C0055b) c0057d.f1189a.get(cls);
        this.f1194c = c0055b == null ? c0057d.a(cls, null) : c0055b;
    }
}

package androidx.lifecycle;

import j0.AbstractC0150d;
import java.io.Closeable;

/* loaded from: classes.dex */
public final class F implements r, Closeable {

    /* renamed from: a, reason: collision with root package name */
    public final String f1158a;

    /* renamed from: b, reason: collision with root package name */
    public final E f1159b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f1160c;

    public F(String str, E e2) {
        this.f1158a = str;
        this.f1159b = e2;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // androidx.lifecycle.r
    public final void f(InterfaceC0072t interfaceC0072t, EnumC0066m enumC0066m) {
        if (enumC0066m == EnumC0066m.ON_DESTROY) {
            this.f1160c = false;
            interfaceC0072t.getLifecycle().b(this);
        }
    }

    public final void h(U.e eVar, AbstractC0068o abstractC0068o) {
        AbstractC0150d.e(eVar, "registry");
        AbstractC0150d.e(abstractC0068o, "lifecycle");
        if (!this.f1160c) {
            this.f1160c = true;
            abstractC0068o.a(this);
            eVar.c(this.f1158a, this.f1159b.f1157e);
            return;
        }
        throw new IllegalStateException("Already attached to lifecycleOwner");
    }
}

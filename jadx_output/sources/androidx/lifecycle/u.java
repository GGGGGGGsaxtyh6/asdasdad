package androidx.lifecycle;

import j0.AbstractC0150d;

/* loaded from: classes.dex */
public final class u {

    /* renamed from: a, reason: collision with root package name */
    public EnumC0067n f1203a;

    /* renamed from: b, reason: collision with root package name */
    public r f1204b;

    public final void a(InterfaceC0072t interfaceC0072t, EnumC0066m enumC0066m) {
        EnumC0067n a2 = enumC0066m.a();
        EnumC0067n enumC0067n = this.f1203a;
        AbstractC0150d.e(enumC0067n, "state1");
        if (a2.compareTo(enumC0067n) < 0) {
            enumC0067n = a2;
        }
        this.f1203a = enumC0067n;
        this.f1204b.f(interfaceC0072t, enumC0066m);
        this.f1203a = a2;
    }
}

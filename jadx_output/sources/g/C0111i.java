package g;

import a.AbstractActivityC0046m;
import b.InterfaceC0074b;

/* renamed from: g.i, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0111i implements InterfaceC0074b {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AbstractActivityC0112j f1453a;

    public C0111i(AbstractActivityC0112j abstractActivityC0112j) {
        this.f1453a = abstractActivityC0112j;
    }

    @Override // b.InterfaceC0074b
    public final void a(AbstractActivityC0046m abstractActivityC0046m) {
        AbstractActivityC0112j abstractActivityC0112j = this.f1453a;
        AbstractC0118p delegate = abstractActivityC0112j.getDelegate();
        delegate.a();
        abstractActivityC0112j.getSavedStateRegistry().a("androidx:appcompat");
        delegate.d();
    }
}

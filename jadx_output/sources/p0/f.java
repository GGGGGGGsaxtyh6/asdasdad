package p0;

import java.util.Iterator;
import k0.InterfaceC0215a;

/* loaded from: classes.dex */
public final class f implements Iterable, InterfaceC0215a {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ q0.c f2174a;

    public f(q0.c cVar) {
        this.f2174a = cVar;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new q0.b(this.f2174a);
    }
}

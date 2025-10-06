package p0;

import b0.C0089o;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class a implements d {

    /* renamed from: a, reason: collision with root package name */
    public final AtomicReference f2168a;

    public a(C0089o c0089o) {
        this.f2168a = new AtomicReference(c0089o);
    }

    @Override // p0.d
    public final Iterator iterator() {
        d dVar = (d) this.f2168a.getAndSet(null);
        if (dVar != null) {
            return dVar.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}

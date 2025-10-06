package p;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* loaded from: classes.dex */
public final class d extends C0.d {

    /* renamed from: a, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f2153a;

    /* renamed from: b, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f2154b;

    /* renamed from: c, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f2155c;

    /* renamed from: d, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f2156d;

    /* renamed from: e, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f2157e;

    public d(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.f2153a = atomicReferenceFieldUpdater;
        this.f2154b = atomicReferenceFieldUpdater2;
        this.f2155c = atomicReferenceFieldUpdater3;
        this.f2156d = atomicReferenceFieldUpdater4;
        this.f2157e = atomicReferenceFieldUpdater5;
    }

    @Override // C0.d
    public final void D(f fVar, f fVar2) {
        this.f2154b.lazySet(fVar, fVar2);
    }

    @Override // C0.d
    public final void E(f fVar, Thread thread) {
        this.f2153a.lazySet(fVar, thread);
    }

    @Override // C0.d
    public final boolean g(g gVar, c cVar) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        c cVar2 = c.f2151b;
        do {
            atomicReferenceFieldUpdater = this.f2156d;
            if (atomicReferenceFieldUpdater.compareAndSet(gVar, cVar, cVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(gVar) == cVar);
        return false;
    }

    @Override // C0.d
    public final boolean h(g gVar, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f2157e;
            if (atomicReferenceFieldUpdater.compareAndSet(gVar, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(gVar) == obj);
        return false;
    }

    @Override // C0.d
    public final boolean i(g gVar, f fVar, f fVar2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f2155c;
            if (atomicReferenceFieldUpdater.compareAndSet(gVar, fVar, fVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(gVar) == fVar);
        return false;
    }
}

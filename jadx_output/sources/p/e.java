package p;

/* loaded from: classes.dex */
public final class e extends C0.d {
    @Override // C0.d
    public final void D(f fVar, f fVar2) {
        fVar.f2160b = fVar2;
    }

    @Override // C0.d
    public final void E(f fVar, Thread thread) {
        fVar.f2159a = thread;
    }

    @Override // C0.d
    public final boolean g(g gVar, c cVar) {
        c cVar2 = c.f2151b;
        synchronized (gVar) {
            try {
                if (gVar.f2166b == cVar) {
                    gVar.f2166b = cVar2;
                    return true;
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // C0.d
    public final boolean h(g gVar, Object obj, Object obj2) {
        synchronized (gVar) {
            try {
                if (gVar.f2165a == obj) {
                    gVar.f2165a = obj2;
                    return true;
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // C0.d
    public final boolean i(g gVar, f fVar, f fVar2) {
        synchronized (gVar) {
            try {
                if (gVar.f2167c == fVar) {
                    gVar.f2167c = fVar2;
                    return true;
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

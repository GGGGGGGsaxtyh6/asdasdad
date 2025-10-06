package H0;

import j0.AbstractC0150d;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public abstract class q {

    /* renamed from: a, reason: collision with root package name */
    public static final p f443a = new p(new byte[0], 0, 0, false);

    /* renamed from: b, reason: collision with root package name */
    public static final int f444b;

    /* renamed from: c, reason: collision with root package name */
    public static final AtomicReference[] f445c;

    static {
        int highestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        f444b = highestOneBit;
        AtomicReference[] atomicReferenceArr = new AtomicReference[highestOneBit];
        for (int i2 = 0; i2 < highestOneBit; i2++) {
            atomicReferenceArr[i2] = new AtomicReference();
        }
        f445c = atomicReferenceArr;
    }

    public static final void a(p pVar) {
        int i2;
        AbstractC0150d.e(pVar, "segment");
        if (pVar.f441f == null && pVar.f442g == null) {
            if (pVar.f439d) {
                return;
            }
            AtomicReference atomicReference = f445c[(int) (Thread.currentThread().getId() & (f444b - 1))];
            p pVar2 = (p) atomicReference.get();
            if (pVar2 == f443a) {
                return;
            }
            if (pVar2 == null) {
                i2 = 0;
            } else {
                i2 = pVar2.f438c;
            }
            if (i2 >= 65536) {
                return;
            }
            pVar.f441f = pVar2;
            pVar.f437b = 0;
            pVar.f438c = i2 + 8192;
            while (!atomicReference.compareAndSet(pVar2, pVar)) {
                if (atomicReference.get() != pVar2) {
                    pVar.f441f = null;
                    return;
                }
            }
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public static final p b() {
        AtomicReference atomicReference = f445c[(int) (Thread.currentThread().getId() & (f444b - 1))];
        p pVar = f443a;
        p pVar2 = (p) atomicReference.getAndSet(pVar);
        if (pVar2 == pVar) {
            return new p();
        }
        if (pVar2 == null) {
            atomicReference.set(null);
            return new p();
        }
        atomicReference.set(pVar2.f441f);
        pVar2.f441f = null;
        pVar2.f438c = 0;
        return pVar2;
    }
}

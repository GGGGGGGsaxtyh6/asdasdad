package r0;

import j0.AbstractC0150d;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* loaded from: classes.dex */
public final class b extends s0.a implements a {

    /* renamed from: c, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f2232c = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "_state");
    private volatile Object _state;

    /* renamed from: b, reason: collision with root package name */
    public int f2233b;

    public b(Object obj) {
        this._state = obj;
    }

    public final void a(Object obj) {
        int i2;
        if (obj == null) {
            obj = s0.a.f2352a;
        }
        synchronized (this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2232c;
            if (!AbstractC0150d.a(atomicReferenceFieldUpdater.get(this), obj)) {
                atomicReferenceFieldUpdater.set(this, obj);
                int i3 = this.f2233b;
                if ((i3 & 1) == 0) {
                    int i4 = i3 + 1;
                    this.f2233b = i4;
                    while (true) {
                        synchronized (this) {
                            i2 = this.f2233b;
                            if (i2 == i4) {
                                this.f2233b = i4 + 1;
                                return;
                            }
                        }
                        i4 = i2;
                    }
                } else {
                    this.f2233b = i3 + 2;
                }
            }
        }
    }
}

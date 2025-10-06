package x0;

import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;

/* loaded from: classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    public final d f2840a;

    /* renamed from: b, reason: collision with root package name */
    public final String f2841b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f2842c;

    /* renamed from: d, reason: collision with root package name */
    public a f2843d;

    /* renamed from: e, reason: collision with root package name */
    public final ArrayList f2844e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f2845f;

    public b(d dVar, String str) {
        AbstractC0150d.e(dVar, "taskRunner");
        AbstractC0150d.e(str, "name");
        this.f2840a = dVar;
        this.f2841b = str;
        this.f2844e = new ArrayList();
    }

    public final void a() {
        byte[] bArr = v0.b.f2808a;
        synchronized (this.f2840a) {
            if (b()) {
                this.f2840a.d(this);
            }
        }
    }

    public final boolean b() {
        a aVar = this.f2843d;
        if (aVar != null && aVar.f2837b) {
            this.f2845f = true;
        }
        ArrayList arrayList = this.f2844e;
        int size = arrayList.size() - 1;
        boolean z2 = false;
        if (size >= 0) {
            while (true) {
                int i2 = size - 1;
                if (((a) arrayList.get(size)).f2837b) {
                    a aVar2 = (a) arrayList.get(size);
                    if (d.f2847i.isLoggable(Level.FINE)) {
                        C0.d.d(aVar2, this, "canceled");
                    }
                    arrayList.remove(size);
                    z2 = true;
                }
                if (i2 < 0) {
                    break;
                }
                size = i2;
            }
        }
        return z2;
    }

    public final void c(a aVar, long j2) {
        AbstractC0150d.e(aVar, "task");
        synchronized (this.f2840a) {
            if (this.f2842c) {
                if (aVar.f2837b) {
                    if (d.f2847i.isLoggable(Level.FINE)) {
                        C0.d.d(aVar, this, "schedule canceled (queue is shutdown)");
                    }
                    return;
                } else {
                    if (d.f2847i.isLoggable(Level.FINE)) {
                        C0.d.d(aVar, this, "schedule failed (queue is shutdown)");
                    }
                    throw new RejectedExecutionException();
                }
            }
            if (d(aVar, j2, false)) {
                this.f2840a.d(this);
            }
        }
    }

    public final boolean d(a aVar, long j2, boolean z2) {
        String h;
        AbstractC0150d.e(aVar, "task");
        b bVar = aVar.f2838c;
        if (bVar != this) {
            if (bVar == null) {
                aVar.f2838c = this;
            } else {
                throw new IllegalStateException("task is in multiple queues");
            }
        }
        c cVar = this.f2840a.f2848a;
        long nanoTime = System.nanoTime();
        long j3 = nanoTime + j2;
        ArrayList arrayList = this.f2844e;
        int indexOf = arrayList.indexOf(aVar);
        if (indexOf != -1) {
            if (aVar.f2839d <= j3) {
                if (d.f2847i.isLoggable(Level.FINE)) {
                    C0.d.d(aVar, this, "already scheduled");
                }
                return false;
            }
            arrayList.remove(indexOf);
        }
        aVar.f2839d = j3;
        if (d.f2847i.isLoggable(Level.FINE)) {
            if (z2) {
                h = AbstractC0150d.h(C0.d.q(j3 - nanoTime), "run again after ");
            } else {
                h = AbstractC0150d.h(C0.d.q(j3 - nanoTime), "scheduled after ");
            }
            C0.d.d(aVar, this, h);
        }
        Iterator it = arrayList.iterator();
        int i2 = 0;
        while (true) {
            if (it.hasNext()) {
                if (((a) it.next()).f2839d - nanoTime > j2) {
                    break;
                }
                i2++;
            } else {
                i2 = -1;
                break;
            }
        }
        if (i2 == -1) {
            i2 = arrayList.size();
        }
        arrayList.add(i2, aVar);
        if (i2 != 0) {
            return false;
        }
        return true;
    }

    public final void e() {
        byte[] bArr = v0.b.f2808a;
        synchronized (this.f2840a) {
            this.f2842c = true;
            if (b()) {
                this.f2840a.d(this);
            }
        }
    }

    public final String toString() {
        return this.f2841b;
    }
}

package y0;

import B0.p;
import C0.o;
import j0.AbstractC0150d;
import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class l {

    /* renamed from: a, reason: collision with root package name */
    public final long f2918a;

    /* renamed from: b, reason: collision with root package name */
    public final x0.b f2919b;

    /* renamed from: c, reason: collision with root package name */
    public final p f2920c;

    /* renamed from: d, reason: collision with root package name */
    public final ConcurrentLinkedQueue f2921d;

    public l(x0.d dVar, TimeUnit timeUnit) {
        AbstractC0150d.e(dVar, "taskRunner");
        this.f2918a = timeUnit.toNanos(5L);
        this.f2919b = dVar.e();
        this.f2920c = new p(this, AbstractC0150d.h(" ConnectionPool", v0.b.h));
        this.f2921d = new ConcurrentLinkedQueue();
    }

    public final boolean a(u0.a aVar, h hVar, ArrayList arrayList, boolean z2) {
        AbstractC0150d.e(hVar, "call");
        Iterator it = this.f2921d.iterator();
        while (true) {
            boolean z3 = false;
            if (!it.hasNext()) {
                return false;
            }
            k kVar = (k) it.next();
            AbstractC0150d.d(kVar, "connection");
            synchronized (kVar) {
                if (z2) {
                    if (kVar.f2908g != null) {
                        z3 = true;
                    }
                    if (!z3) {
                    }
                }
                if (kVar.h(aVar, arrayList)) {
                    hVar.b(kVar);
                    return true;
                }
            }
        }
    }

    public final int b(k kVar, long j2) {
        byte[] bArr = v0.b.f2808a;
        ArrayList arrayList = kVar.f2916p;
        int i2 = 0;
        while (i2 < arrayList.size()) {
            Reference reference = (Reference) arrayList.get(i2);
            if (reference.get() != null) {
                i2++;
            } else {
                String str = "A connection to " + kVar.f2903b.f2580a.h + " was leaked. Did you forget to close a response body?";
                o oVar = o.f236a;
                o.f236a.j(((g) reference).f2884a, str);
                arrayList.remove(i2);
                kVar.f2910j = true;
                if (arrayList.isEmpty()) {
                    kVar.f2917q = j2 - this.f2918a;
                    return 0;
                }
            }
        }
        return arrayList.size();
    }
}

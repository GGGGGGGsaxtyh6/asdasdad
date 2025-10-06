package B0;

import j0.AbstractC0150d;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class p extends x0.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f139e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f140f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p(String str, Object obj, int i2) {
        super(true, str);
        this.f139e = i2;
        this.f140f = obj;
    }

    @Override // x0.a
    public final long a() {
        switch (this.f139e) {
            case 0:
                s sVar = (s) this.f140f;
                sVar.getClass();
                try {
                    sVar.f169w.l(2, 0, false);
                    return -1L;
                } catch (IOException e2) {
                    sVar.h(e2);
                    return -1L;
                }
            case 1:
                ((m) this.f140f).a();
                return -1L;
            default:
                y0.l lVar = (y0.l) this.f140f;
                long nanoTime = System.nanoTime();
                Iterator it = lVar.f2921d.iterator();
                int i2 = 0;
                long j2 = Long.MIN_VALUE;
                y0.k kVar = null;
                int i3 = 0;
                while (it.hasNext()) {
                    y0.k kVar2 = (y0.k) it.next();
                    AbstractC0150d.d(kVar2, "connection");
                    synchronized (kVar2) {
                        if (lVar.b(kVar2, nanoTime) > 0) {
                            i3++;
                        } else {
                            i2++;
                            long j3 = nanoTime - kVar2.f2917q;
                            if (j3 > j2) {
                                kVar = kVar2;
                                j2 = j3;
                            }
                        }
                    }
                }
                long j4 = lVar.f2918a;
                if (j2 < j4 && i2 <= 5) {
                    if (i2 > 0) {
                        return j4 - j2;
                    }
                    if (i3 <= 0) {
                        return -1L;
                    }
                    return j4;
                }
                AbstractC0150d.b(kVar);
                synchronized (kVar) {
                    if (!kVar.f2916p.isEmpty()) {
                        return 0L;
                    }
                    if (kVar.f2917q + j2 != nanoTime) {
                        return 0L;
                    }
                    kVar.f2910j = true;
                    lVar.f2921d.remove(kVar);
                    Socket socket = kVar.f2905d;
                    AbstractC0150d.b(socket);
                    v0.b.d(socket);
                    if (!lVar.f2921d.isEmpty()) {
                        return 0L;
                    }
                    lVar.f2919b.a();
                    return 0L;
                }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(y0.l lVar, String str) {
        super(true, str);
        this.f139e = 2;
        this.f140f = lVar;
    }
}

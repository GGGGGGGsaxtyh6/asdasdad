package B0;

import j0.AbstractC0150d;
import java.io.IOException;

/* loaded from: classes.dex */
public final class k extends x0.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f125e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f126f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f127g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(String str, Object obj, Object obj2, int i2) {
        super(true, str);
        this.f125e = i2;
        this.f126f = obj;
        this.f127g = obj2;
    }

    @Override // x0.a
    public final long a() {
        long b2;
        A[] aArr;
        int i2 = 0;
        switch (this.f125e) {
            case 0:
                s sVar = (s) this.f126f;
                sVar.f148a.a(sVar, (F) ((j0.g) this.f127g).f1739b);
                return -1L;
            case 1:
                try {
                    ((s) this.f126f).f148a.b((A) this.f127g);
                } catch (IOException e2) {
                    C0.o oVar = C0.o.f236a;
                    C0.o oVar2 = C0.o.f236a;
                    String h = AbstractC0150d.h(((s) this.f126f).f150c, "Http2Connection.Listener failure for ");
                    oVar2.getClass();
                    C0.o.i(h, 4, e2);
                    try {
                        ((A) this.f127g).c(EnumC0001b.PROTOCOL_ERROR, e2);
                    } catch (IOException unused) {
                    }
                }
                return -1L;
            default:
                m mVar = (m) this.f126f;
                F f2 = (F) this.f127g;
                mVar.getClass();
                j0.g gVar = new j0.g(0);
                s sVar2 = mVar.f132b;
                synchronized (sVar2.f169w) {
                    synchronized (sVar2) {
                        try {
                            F f3 = sVar2.f163q;
                            F f4 = new F(0);
                            f4.d(f3);
                            f4.d(f2);
                            gVar.f1739b = f4;
                            b2 = f4.b() - f3.b();
                            if (b2 != 0 && !sVar2.f149b.isEmpty()) {
                                Object[] array = sVar2.f149b.values().toArray(new A[0]);
                                if (array != null) {
                                    aArr = (A[]) array;
                                    F f5 = (F) gVar.f1739b;
                                    AbstractC0150d.e(f5, "<set-?>");
                                    sVar2.f163q = f5;
                                    sVar2.f156j.c(new k(AbstractC0150d.h(" onSettings", sVar2.f150c), sVar2, gVar, i2), 0L);
                                } else {
                                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                                }
                            }
                            aArr = null;
                            F f52 = (F) gVar.f1739b;
                            AbstractC0150d.e(f52, "<set-?>");
                            sVar2.f163q = f52;
                            sVar2.f156j.c(new k(AbstractC0150d.h(" onSettings", sVar2.f150c), sVar2, gVar, i2), 0L);
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    try {
                        sVar2.f169w.f((F) gVar.f1739b);
                    } catch (IOException e3) {
                        sVar2.h(e3);
                    }
                }
                if (aArr != null) {
                    int length = aArr.length;
                    while (i2 < length) {
                        A a2 = aArr[i2];
                        i2++;
                        synchronized (a2) {
                            a2.f58f += b2;
                            if (b2 > 0) {
                                a2.notifyAll();
                            }
                        }
                    }
                }
                return -1L;
        }
    }
}

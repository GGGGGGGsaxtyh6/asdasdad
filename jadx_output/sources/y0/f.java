package y0;

import C0.o;
import M.v;
import j0.AbstractC0150d;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class f implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final u0.d f2881a;

    /* renamed from: b, reason: collision with root package name */
    public volatile AtomicInteger f2882b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ h f2883c;

    public f(h hVar, u0.d dVar) {
        AbstractC0150d.e(hVar, "this$0");
        this.f2883c = hVar;
        this.f2881a = dVar;
        this.f2882b = new AtomicInteger(0);
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z2;
        Throwable th;
        IOException e2;
        v vVar;
        String h = AbstractC0150d.h(((u0.m) this.f2883c.f2886b.f118b).f(), "OkHttp ");
        h hVar = this.f2883c;
        Thread currentThread = Thread.currentThread();
        String name = currentThread.getName();
        currentThread.setName(h);
        try {
            hVar.f2888d.h();
            try {
                try {
                    z2 = true;
                    try {
                        this.f2881a.s(hVar.g());
                        vVar = hVar.f2885a.f2519a;
                    } catch (IOException e3) {
                        e2 = e3;
                        if (z2) {
                            o oVar = o.f236a;
                            o oVar2 = o.f236a;
                            String h2 = AbstractC0150d.h(h.a(hVar), "Callback failure for ");
                            oVar2.getClass();
                            o.i(h2, 4, e2);
                        } else {
                            this.f2881a.w(e2);
                        }
                        vVar = hVar.f2885a.f2519a;
                        vVar.f(this);
                    } catch (Throwable th2) {
                        th = th2;
                        hVar.d();
                        if (!z2) {
                            IOException iOException = new IOException(AbstractC0150d.h(th, "canceled due to "));
                            C0.d.e(iOException, th);
                            this.f2881a.w(iOException);
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    hVar.f2885a.f2519a.f(this);
                    throw th3;
                }
            } catch (IOException e4) {
                z2 = false;
                e2 = e4;
            } catch (Throwable th4) {
                z2 = false;
                th = th4;
            }
            vVar.f(this);
        } finally {
            currentThread.setName(name);
        }
    }
}

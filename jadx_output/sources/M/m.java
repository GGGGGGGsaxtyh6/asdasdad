package M;

import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: classes.dex */
public final class m extends C0.d {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0.d f547a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ThreadPoolExecutor f548b;

    public m(C0.d dVar, ThreadPoolExecutor threadPoolExecutor) {
        this.f547a = dVar;
        this.f548b = threadPoolExecutor;
    }

    @Override // C0.d
    public final void A(v vVar) {
        ThreadPoolExecutor threadPoolExecutor = this.f548b;
        try {
            this.f547a.A(vVar);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    @Override // C0.d
    public final void z(Throwable th) {
        ThreadPoolExecutor threadPoolExecutor = this.f548b;
        try {
            this.f547a.z(th);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}

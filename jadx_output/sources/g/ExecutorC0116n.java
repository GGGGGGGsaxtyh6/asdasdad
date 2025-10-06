package g;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* renamed from: g.n, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class ExecutorC0116n implements Executor {

    /* renamed from: a, reason: collision with root package name */
    public final Object f1454a = new Object();

    /* renamed from: b, reason: collision with root package name */
    public final ArrayDeque f1455b = new ArrayDeque();

    /* renamed from: c, reason: collision with root package name */
    public final ExecutorC0117o f1456c;

    /* renamed from: d, reason: collision with root package name */
    public Runnable f1457d;

    public ExecutorC0116n(ExecutorC0117o executorC0117o) {
        this.f1456c = executorC0117o;
    }

    public final void a() {
        synchronized (this.f1454a) {
            try {
                Runnable runnable = (Runnable) this.f1455b.poll();
                this.f1457d = runnable;
                if (runnable != null) {
                    this.f1456c.execute(runnable);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        synchronized (this.f1454a) {
            try {
                this.f1455b.add(new Z.b(this, runnable, 8));
                if (this.f1457d == null) {
                    a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

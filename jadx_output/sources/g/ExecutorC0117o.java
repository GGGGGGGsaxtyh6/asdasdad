package g;

import java.util.concurrent.Executor;

/* renamed from: g.o, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class ExecutorC0117o implements Executor {
    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        new Thread(runnable).start();
    }
}

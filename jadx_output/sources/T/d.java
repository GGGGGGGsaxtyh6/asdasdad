package T;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final /* synthetic */ class d implements Executor {
    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}

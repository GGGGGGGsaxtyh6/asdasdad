package E;

import java.util.concurrent.ThreadFactory;

/* loaded from: classes.dex */
public final class n implements ThreadFactory {
    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return new m(runnable);
    }
}

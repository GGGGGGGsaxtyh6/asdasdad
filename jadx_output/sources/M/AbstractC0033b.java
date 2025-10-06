package M;

import android.os.Handler;
import android.os.Looper;

/* renamed from: M.b, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0033b {
    public static Handler a(Looper looper) {
        Handler createAsync;
        createAsync = Handler.createAsync(looper);
        return createAsync;
    }
}

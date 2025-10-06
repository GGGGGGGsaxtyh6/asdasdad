package M;

import android.os.Trace;

/* loaded from: classes.dex */
public final class n implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        boolean z2;
        try {
            int i2 = D.g.f242a;
            Trace.beginSection("EmojiCompat.EmojiCompatInitializer.run");
            if (j.f533k != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                j.a().c();
            }
            Trace.endSection();
        } catch (Throwable th) {
            int i3 = D.g.f242a;
            Trace.endSection();
            throw th;
        }
    }
}

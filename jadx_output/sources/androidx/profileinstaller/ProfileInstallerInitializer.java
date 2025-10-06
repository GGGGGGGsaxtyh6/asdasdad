package androidx.profileinstaller;

import A.m;
import V.b;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import androidx.profileinstaller.ProfileInstallerInitializer;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/* loaded from: classes.dex */
public class ProfileInstallerInitializer implements b {
    @Override // V.b
    public final List a() {
        return Collections.emptyList();
    }

    @Override // V.b
    public final Object b(Context context) {
        final Context applicationContext = context.getApplicationContext();
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() { // from class: T.g
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                Handler handler;
                ProfileInstallerInitializer.this.getClass();
                if (Build.VERSION.SDK_INT >= 28) {
                    handler = Handler.createAsync(Looper.getMainLooper());
                } else {
                    handler = new Handler(Looper.getMainLooper());
                }
                handler.postDelayed(new h(applicationContext, 0), new Random().nextInt(Math.max(1000, 1)) + 5000);
            }
        });
        return new m(13);
    }
}

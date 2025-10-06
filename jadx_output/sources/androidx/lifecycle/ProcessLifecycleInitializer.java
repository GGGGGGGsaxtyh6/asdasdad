package androidx.lifecycle;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import b0.C0091q;
import j0.AbstractC0150d;
import java.util.List;

/* loaded from: classes.dex */
public final class ProcessLifecycleInitializer implements V.b {
    @Override // V.b
    public final List a() {
        return C0091q.f1234a;
    }

    @Override // V.b
    public final Object b(Context context) {
        AbstractC0150d.e(context, "context");
        V.a c2 = V.a.c(context);
        AbstractC0150d.d(c2, "getInstance(context)");
        if (c2.f677b.contains(ProcessLifecycleInitializer.class)) {
            if (!AbstractC0070q.f1202a.getAndSet(true)) {
                Context applicationContext = context.getApplicationContext();
                AbstractC0150d.c(applicationContext, "null cannot be cast to non-null type android.app.Application");
                ((Application) applicationContext).registerActivityLifecycleCallbacks(new C0069p());
            }
            A a2 = A.f1142i;
            a2.getClass();
            a2.f1147e = new Handler();
            a2.f1148f.e(EnumC0066m.ON_CREATE);
            Context applicationContext2 = context.getApplicationContext();
            AbstractC0150d.c(applicationContext2, "null cannot be cast to non-null type android.app.Application");
            ((Application) applicationContext2).registerActivityLifecycleCallbacks(new z(a2));
            return a2;
        }
        throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml");
    }
}

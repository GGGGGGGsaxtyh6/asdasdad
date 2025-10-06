package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public abstract class y {
    public static final void a(Activity activity, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        AbstractC0150d.e(activity, "activity");
        AbstractC0150d.e(activityLifecycleCallbacks, "callback");
        activity.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }
}

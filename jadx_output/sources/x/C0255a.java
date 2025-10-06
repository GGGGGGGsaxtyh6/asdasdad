package x;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/* renamed from: x.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0255a implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a, reason: collision with root package name */
    public Object f2818a;

    /* renamed from: b, reason: collision with root package name */
    public Activity f2819b;

    /* renamed from: c, reason: collision with root package name */
    public final int f2820c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f2821d = false;

    /* renamed from: e, reason: collision with root package name */
    public boolean f2822e = false;

    /* renamed from: f, reason: collision with root package name */
    public boolean f2823f = false;

    public C0255a(Activity activity) {
        this.f2819b = activity;
        this.f2820c = activity.hashCode();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        if (this.f2819b == activity) {
            this.f2819b = null;
            this.f2822e = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        if (this.f2822e && !this.f2823f && !this.f2821d) {
            Object obj = this.f2818a;
            try {
                Object obj2 = b.f2826c.get(activity);
                if (obj2 == obj && activity.hashCode() == this.f2820c) {
                    b.f2830g.postAtFrontOfQueue(new E.a(b.f2825b.get(activity), obj2, 14));
                    this.f2823f = true;
                    this.f2818a = null;
                }
            } catch (Throwable th) {
                Log.e("ActivityRecreator", "Exception while fetching field values", th);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        if (this.f2819b == activity) {
            this.f2821d = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }
}

package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public class D extends Fragment {

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f1150b = 0;

    /* renamed from: a, reason: collision with root package name */
    public A.f f1151a;

    /* loaded from: classes.dex */
    public static final class a implements Application.ActivityLifecycleCallbacks {
        public static final C Companion = new Object();

        public static final void registerIn(Activity activity) {
            Companion.getClass();
            AbstractC0150d.e(activity, "activity");
            activity.registerActivityLifecycleCallbacks(new a());
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            AbstractC0150d.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            AbstractC0150d.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            AbstractC0150d.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostCreated(Activity activity, Bundle bundle) {
            AbstractC0150d.e(activity, "activity");
            int i2 = D.f1150b;
            B.a(activity, EnumC0066m.ON_CREATE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            AbstractC0150d.e(activity, "activity");
            int i2 = D.f1150b;
            B.a(activity, EnumC0066m.ON_RESUME);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            AbstractC0150d.e(activity, "activity");
            int i2 = D.f1150b;
            B.a(activity, EnumC0066m.ON_START);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreDestroyed(Activity activity) {
            AbstractC0150d.e(activity, "activity");
            int i2 = D.f1150b;
            B.a(activity, EnumC0066m.ON_DESTROY);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPrePaused(Activity activity) {
            AbstractC0150d.e(activity, "activity");
            int i2 = D.f1150b;
            B.a(activity, EnumC0066m.ON_PAUSE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreStopped(Activity activity) {
            AbstractC0150d.e(activity, "activity");
            int i2 = D.f1150b;
            B.a(activity, EnumC0066m.ON_STOP);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            AbstractC0150d.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            AbstractC0150d.e(activity, "activity");
            AbstractC0150d.e(bundle, "bundle");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            AbstractC0150d.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            AbstractC0150d.e(activity, "activity");
        }
    }

    public final void a(EnumC0066m enumC0066m) {
        if (Build.VERSION.SDK_INT < 29) {
            Activity activity = getActivity();
            AbstractC0150d.d(activity, "activity");
            B.a(activity, enumC0066m);
        }
    }

    @Override // android.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(EnumC0066m.ON_CREATE);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        a(EnumC0066m.ON_DESTROY);
        this.f1151a = null;
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        a(EnumC0066m.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        A.f fVar = this.f1151a;
        if (fVar != null) {
            ((A) fVar.f8b).a();
        }
        a(EnumC0066m.ON_RESUME);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        A.f fVar = this.f1151a;
        if (fVar != null) {
            A a2 = (A) fVar.f8b;
            int i2 = a2.f1143a + 1;
            a2.f1143a = i2;
            if (i2 == 1 && a2.f1146d) {
                a2.f1148f.e(EnumC0066m.ON_START);
                a2.f1146d = false;
            }
        }
        a(EnumC0066m.ON_START);
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        a(EnumC0066m.ON_STOP);
    }
}

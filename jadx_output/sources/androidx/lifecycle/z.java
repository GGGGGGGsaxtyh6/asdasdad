package androidx.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public final class z extends AbstractC0060g {
    final /* synthetic */ A this$0;

    /* loaded from: classes.dex */
    public static final class a extends AbstractC0060g {
        final /* synthetic */ A this$0;

        public a(A a2) {
            this.this$0 = a2;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            AbstractC0150d.e(activity, "activity");
            this.this$0.a();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            AbstractC0150d.e(activity, "activity");
            A a2 = this.this$0;
            int i2 = a2.f1143a + 1;
            a2.f1143a = i2;
            if (i2 == 1 && a2.f1146d) {
                a2.f1148f.e(EnumC0066m.ON_START);
                a2.f1146d = false;
            }
        }
    }

    public z(A a2) {
        this.this$0 = a2;
    }

    @Override // androidx.lifecycle.AbstractC0060g, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        AbstractC0150d.e(activity, "activity");
        if (Build.VERSION.SDK_INT < 29) {
            int i2 = D.f1150b;
            Fragment findFragmentByTag = activity.getFragmentManager().findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag");
            AbstractC0150d.c(findFragmentByTag, "null cannot be cast to non-null type androidx.lifecycle.ReportFragment");
            ((D) findFragmentByTag).f1151a = this.this$0.h;
        }
    }

    @Override // androidx.lifecycle.AbstractC0060g, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        AbstractC0150d.e(activity, "activity");
        A a2 = this.this$0;
        int i2 = a2.f1144b - 1;
        a2.f1144b = i2;
        if (i2 == 0) {
            Handler handler = a2.f1147e;
            AbstractC0150d.b(handler);
            handler.postDelayed(a2.f1149g, 700L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        AbstractC0150d.e(activity, "activity");
        y.a(activity, new a(this.this$0));
    }

    @Override // androidx.lifecycle.AbstractC0060g, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        AbstractC0150d.e(activity, "activity");
        A a2 = this.this$0;
        int i2 = a2.f1143a - 1;
        a2.f1143a = i2;
        if (i2 == 0 && a2.f1145c) {
            a2.f1148f.e(EnumC0066m.ON_STOP);
            a2.f1146d = true;
        }
    }
}

package androidx.lifecycle;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Build;
import androidx.lifecycle.D;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public abstract class B {
    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Activity activity, EnumC0066m enumC0066m) {
        AbstractC0150d.e(enumC0066m, "event");
        if (activity instanceof InterfaceC0072t) {
            AbstractC0068o lifecycle = ((InterfaceC0072t) activity).getLifecycle();
            if (lifecycle instanceof v) {
                ((v) lifecycle).e(enumC0066m);
            }
        }
    }

    public static void b(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            D.a.Companion.getClass();
            activity.registerActivityLifecycleCallbacks(new D.a());
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new D(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }
}

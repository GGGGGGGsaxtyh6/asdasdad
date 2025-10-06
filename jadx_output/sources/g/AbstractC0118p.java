package g;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import i.AbstractC0120b;
import i.InterfaceC0119a;
import java.lang.ref.WeakReference;
import n.C0227b;

/* renamed from: g.p, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0118p {

    /* renamed from: a, reason: collision with root package name */
    public static final ExecutorC0116n f1458a = new ExecutorC0116n(new Object());

    /* renamed from: b, reason: collision with root package name */
    public static final int f1459b = -100;

    /* renamed from: c, reason: collision with root package name */
    public static D.e f1460c = null;

    /* renamed from: d, reason: collision with root package name */
    public static D.e f1461d = null;

    /* renamed from: e, reason: collision with root package name */
    public static Boolean f1462e = null;

    /* renamed from: f, reason: collision with root package name */
    public static boolean f1463f = false;

    /* renamed from: g, reason: collision with root package name */
    public static final n.g f1464g = new n.g();
    public static final Object h = new Object();

    /* renamed from: i, reason: collision with root package name */
    public static final Object f1465i = new Object();

    public static boolean c(Context context) {
        if (f1462e == null) {
            try {
                int i2 = H.f1365a;
                Bundle bundle = context.getPackageManager().getServiceInfo(new ComponentName(context, (Class<?>) H.class), G.a() | 128).metaData;
                if (bundle != null) {
                    f1462e = Boolean.valueOf(bundle.getBoolean("autoStoreLocales"));
                }
            } catch (PackageManager.NameNotFoundException unused) {
                Log.d("AppCompatDelegate", "Checking for metadata for AppLocalesMetadataHolderService : Service not found");
                f1462e = Boolean.FALSE;
            }
        }
        return f1462e.booleanValue();
    }

    public static void f(C c2) {
        synchronized (h) {
            try {
                n.g gVar = f1464g;
                gVar.getClass();
                C0227b c0227b = new C0227b(gVar);
                while (c0227b.hasNext()) {
                    AbstractC0118p abstractC0118p = (AbstractC0118p) ((WeakReference) c0227b.next()).get();
                    if (abstractC0118p == c2 || abstractC0118p == null) {
                        c0227b.remove();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public abstract void a();

    public abstract void b();

    public abstract void d();

    public abstract void e();

    public abstract boolean g(int i2);

    public abstract void h(int i2);

    public abstract void i(View view);

    public abstract void j(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void l(CharSequence charSequence);

    public abstract AbstractC0120b n(InterfaceC0119a interfaceC0119a);
}

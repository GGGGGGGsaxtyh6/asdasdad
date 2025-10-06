package androidx.lifecycle;

import a.AbstractActivityC0046m;
import a0.C0050d;
import android.os.Bundle;
import j0.AbstractC0150d;
import java.util.Map;

/* loaded from: classes.dex */
public final class J implements U.d {

    /* renamed from: a, reason: collision with root package name */
    public final U.e f1166a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f1167b;

    /* renamed from: c, reason: collision with root package name */
    public Bundle f1168c;

    /* renamed from: d, reason: collision with root package name */
    public final C0050d f1169d;

    public J(U.e eVar, AbstractActivityC0046m abstractActivityC0046m) {
        AbstractC0150d.e(eVar, "savedStateRegistry");
        this.f1166a = eVar;
        this.f1169d = new C0050d(new I(0, abstractActivityC0046m));
    }

    @Override // U.d
    public final Bundle a() {
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f1168c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        for (Map.Entry entry : ((K) this.f1169d.a()).f1170b.entrySet()) {
            String str = (String) entry.getKey();
            Bundle a2 = ((E) entry.getValue()).f1157e.a();
            if (!AbstractC0150d.a(a2, Bundle.EMPTY)) {
                bundle.putBundle(str, a2);
            }
        }
        this.f1167b = false;
        return bundle;
    }

    public final void b() {
        if (!this.f1167b) {
            Bundle a2 = this.f1166a.a("androidx.lifecycle.internal.SavedStateHandlesProvider");
            Bundle bundle = new Bundle();
            Bundle bundle2 = this.f1168c;
            if (bundle2 != null) {
                bundle.putAll(bundle2);
            }
            if (a2 != null) {
                bundle.putAll(a2);
            }
            this.f1168c = bundle;
            this.f1167b = true;
        }
    }
}

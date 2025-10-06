package U;

import a.C0037d;
import android.os.Bundle;
import androidx.lifecycle.AbstractC0068o;
import androidx.lifecycle.EnumC0067n;
import androidx.lifecycle.v;
import java.util.Map;
import m.C0223d;
import m.C0225f;

/* loaded from: classes.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public final Object f671a;

    /* renamed from: b, reason: collision with root package name */
    public final e f672b = new e();

    /* renamed from: c, reason: collision with root package name */
    public boolean f673c;

    public f(g gVar) {
        this.f671a = gVar;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.lifecycle.t, java.lang.Object] */
    public final void a() {
        ?? r02 = this.f671a;
        AbstractC0068o lifecycle = r02.getLifecycle();
        if (((v) lifecycle).f1207c == EnumC0067n.f1197b) {
            lifecycle.a(new b(0, r02));
            e eVar = this.f672b;
            eVar.getClass();
            if (!eVar.f666b) {
                lifecycle.a(new C0037d(2, eVar));
                eVar.f666b = true;
                this.f673c = true;
                return;
            }
            throw new IllegalStateException("SavedStateRegistry was already attached.");
        }
        throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.t, java.lang.Object] */
    public final void b(Bundle bundle) {
        Bundle bundle2;
        if (!this.f673c) {
            a();
        }
        v vVar = (v) this.f671a.getLifecycle();
        if (vVar.f1207c.compareTo(EnumC0067n.f1199d) < 0) {
            e eVar = this.f672b;
            if (eVar.f666b) {
                if (!eVar.f668d) {
                    if (bundle != null) {
                        bundle2 = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
                    } else {
                        bundle2 = null;
                    }
                    eVar.f667c = bundle2;
                    eVar.f668d = true;
                    return;
                }
                throw new IllegalStateException("SavedStateRegistry was already restored.");
            }
            throw new IllegalStateException("You must call performAttach() before calling performRestore(Bundle).");
        }
        throw new IllegalStateException(("performRestore cannot be called when owner is " + vVar.f1207c).toString());
    }

    public final void c(Bundle bundle) {
        e eVar = this.f672b;
        eVar.getClass();
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = eVar.f667c;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        C0225f c0225f = eVar.f665a;
        c0225f.getClass();
        C0223d c0223d = new C0223d(c0225f);
        c0225f.f2093c.put(c0223d, Boolean.FALSE);
        while (c0223d.hasNext()) {
            Map.Entry entry = (Map.Entry) c0223d.next();
            bundle2.putBundle((String) entry.getKey(), ((d) entry.getValue()).a());
        }
        if (!bundle2.isEmpty()) {
            bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
        }
    }
}

package androidx.fragment.app;

import android.os.Handler;
import androidx.lifecycle.AbstractC0068o;
import androidx.lifecycle.InterfaceC0072t;
import androidx.lifecycle.S;
import androidx.lifecycle.T;
import g.AbstractActivityC0112j;

/* loaded from: classes.dex */
public final class h implements T, InterfaceC0072t, U.g {

    /* renamed from: a, reason: collision with root package name */
    public final i f1052a;

    /* renamed from: b, reason: collision with root package name */
    public final Handler f1053b;

    /* renamed from: c, reason: collision with root package name */
    public final u f1054c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ AbstractActivityC0112j f1055d;

    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.fragment.app.t, androidx.fragment.app.u] */
    public h(AbstractActivityC0112j abstractActivityC0112j) {
        this.f1055d = abstractActivityC0112j;
        Handler handler = new Handler();
        this.f1054c = new t();
        this.f1052a = abstractActivityC0112j;
        this.f1053b = handler;
    }

    @Override // androidx.lifecycle.InterfaceC0072t
    public final AbstractC0068o getLifecycle() {
        return this.f1055d.mFragmentLifecycleRegistry;
    }

    @Override // U.g
    public final U.e getSavedStateRegistry() {
        return this.f1055d.getSavedStateRegistry();
    }

    @Override // androidx.lifecycle.T
    public final S getViewModelStore() {
        return this.f1055d.getViewModelStore();
    }
}

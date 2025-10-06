package a;

import H.C0019m;
import a0.C0051e;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.L;
import i0.InterfaceC0131a;
import j0.AbstractC0150d;
import j0.AbstractC0151e;

/* renamed from: a.l, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0045l extends AbstractC0151e implements InterfaceC0131a {

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f753b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.i f754c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0045l(androidx.fragment.app.i iVar, int i2) {
        super(0);
        this.f753b = i2;
        this.f754c = iVar;
    }

    @Override // i0.InterfaceC0131a
    public final Object a() {
        Bundle bundle;
        InterfaceExecutorC0042i interfaceExecutorC0042i;
        switch (this.f753b) {
            case 0:
                androidx.fragment.app.i iVar = this.f754c;
                Application application = iVar.getApplication();
                if (iVar.getIntent() != null) {
                    bundle = iVar.getIntent().getExtras();
                } else {
                    bundle = null;
                }
                return new L(application, iVar, bundle);
            case 1:
                this.f754c.reportFullyDrawn();
                return C0051e.f792c;
            case 2:
                androidx.fragment.app.i iVar2 = this.f754c;
                interfaceExecutorC0042i = ((AbstractActivityC0046m) iVar2).reportFullyDrawnExecutor;
                return new n(interfaceExecutorC0042i, new C0045l(iVar2, 1));
            default:
                androidx.fragment.app.i iVar3 = this.f754c;
                v vVar = new v(new RunnableC0036c(iVar3, 1));
                if (Build.VERSION.SDK_INT >= 33) {
                    if (AbstractC0150d.a(Looper.myLooper(), Looper.getMainLooper())) {
                        iVar3.getLifecycle().a(new C0019m(vVar, iVar3, 1));
                    } else {
                        new Handler(Looper.getMainLooper()).post(new Z.b(iVar3, vVar, 7));
                    }
                }
                return vVar;
        }
    }
}

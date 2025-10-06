package a;

import j0.AbstractC0150d;

/* renamed from: a.c, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC0036c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f740a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.i f741b;

    public /* synthetic */ RunnableC0036c(androidx.fragment.app.i iVar, int i2) {
        this.f740a = i2;
        this.f741b = iVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f740a) {
            case 0:
                this.f741b.invalidateMenu();
                return;
            default:
                try {
                    super/*android.app.Activity*/.onBackPressed();
                    return;
                } catch (IllegalStateException e2) {
                    if (AbstractC0150d.a(e2.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                        return;
                    } else {
                        throw e2;
                    }
                } catch (NullPointerException e3) {
                    if (!AbstractC0150d.a(e3.getMessage(), "Attempt to invoke virtual method 'android.os.Handler android.app.FragmentHostCallback.getHandler()' on a null object reference")) {
                        throw e3;
                    }
                    return;
                }
        }
    }
}

package k;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public final class L0 extends G0 implements H0 {

    /* renamed from: C, reason: collision with root package name */
    public static final Method f1803C;

    /* renamed from: B, reason: collision with root package name */
    public A.f f1804B;

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                f1803C = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    @Override // k.H0
    public final void f(j.n nVar, j.p pVar) {
        A.f fVar = this.f1804B;
        if (fVar != null) {
            fVar.f(nVar, pVar);
        }
    }

    @Override // k.H0
    public final void l(j.n nVar, j.p pVar) {
        A.f fVar = this.f1804B;
        if (fVar != null) {
            fVar.l(nVar, pVar);
        }
    }

    @Override // k.G0
    public final C0202t0 q(Context context, boolean z2) {
        K0 k02 = new K0(context, z2);
        k02.setHoverListener(this);
        return k02;
    }
}

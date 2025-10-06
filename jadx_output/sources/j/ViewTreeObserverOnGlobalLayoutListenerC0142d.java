package j;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.Iterator;
import k.L0;
import k.O;
import k.S;

/* renamed from: j.d, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class ViewTreeObserverOnGlobalLayoutListenerC0142d implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1610a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1611b;

    public /* synthetic */ ViewTreeObserverOnGlobalLayoutListenerC0142d(int i2, Object obj) {
        this.f1610a = i2;
        this.f1611b = obj;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        switch (this.f1610a) {
            case 0:
                ViewOnKeyListenerC0146h viewOnKeyListenerC0146h = (ViewOnKeyListenerC0146h) this.f1611b;
                if (viewOnKeyListenerC0146h.b()) {
                    ArrayList arrayList = viewOnKeyListenerC0146h.h;
                    if (arrayList.size() > 0 && !((C0145g) arrayList.get(0)).f1618a.f1786x) {
                        View view = viewOnKeyListenerC0146h.f1633o;
                        if (view != null && view.isShown()) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                ((C0145g) it.next()).f1618a.i();
                            }
                            return;
                        }
                        viewOnKeyListenerC0146h.dismiss();
                        return;
                    }
                    return;
                }
                return;
            case 1:
                ViewOnKeyListenerC0137E viewOnKeyListenerC0137E = (ViewOnKeyListenerC0137E) this.f1611b;
                if (viewOnKeyListenerC0137E.b()) {
                    L0 l02 = viewOnKeyListenerC0137E.h;
                    if (!l02.f1786x) {
                        View view2 = viewOnKeyListenerC0137E.f1583m;
                        if (view2 != null && view2.isShown()) {
                            l02.i();
                            return;
                        } else {
                            viewOnKeyListenerC0137E.dismiss();
                            return;
                        }
                    }
                    return;
                }
                return;
            case 2:
                S s2 = (S) this.f1611b;
                if (!s2.getInternalPopup().b()) {
                    s2.f1835f.g(s2.getTextDirection(), s2.getTextAlignment());
                }
                ViewTreeObserver viewTreeObserver = s2.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                    return;
                }
                return;
            default:
                O o2 = (O) this.f1611b;
                S s3 = o2.f1820F;
                o2.getClass();
                if (s3.isAttachedToWindow() && s3.getGlobalVisibleRect(o2.f1818D)) {
                    o2.s();
                    o2.i();
                    return;
                } else {
                    o2.dismiss();
                    return;
                }
        }
    }
}

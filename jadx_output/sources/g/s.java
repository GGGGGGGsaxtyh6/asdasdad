package g;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class s extends C0.d {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1470a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1471b;

    public /* synthetic */ s(int i2, Object obj) {
        this.f1470a = i2;
        this.f1471b = obj;
    }

    @Override // H.T
    public final void a() {
        Object obj = this.f1471b;
        switch (this.f1470a) {
            case 0:
                C c2 = ((q) obj).f1467b;
                c2.f1349v.setAlpha(1.0f);
                c2.y.d(null);
                c2.y = null;
                return;
            case 1:
                C c3 = (C) obj;
                c3.f1349v.setAlpha(1.0f);
                c3.y.d(null);
                c3.y = null;
                return;
            default:
                E.c cVar = (E.c) obj;
                ((C) cVar.f272c).f1349v.setVisibility(8);
                C c4 = (C) cVar.f272c;
                PopupWindow popupWindow = c4.f1350w;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (c4.f1349v.getParent() instanceof View) {
                    View view = (View) c4.f1349v.getParent();
                    WeakHashMap weakHashMap = H.N.f327a;
                    H.E.c(view);
                }
                c4.f1349v.e();
                c4.y.d(null);
                c4.y = null;
                ViewGroup viewGroup = c4.f1305A;
                WeakHashMap weakHashMap2 = H.N.f327a;
                H.E.c(viewGroup);
                return;
        }
    }

    @Override // C0.d, H.T
    public void c() {
        Object obj = this.f1471b;
        switch (this.f1470a) {
            case 0:
                ((q) obj).f1467b.f1349v.setVisibility(0);
                return;
            case 1:
                C c2 = (C) obj;
                c2.f1349v.setVisibility(0);
                if (c2.f1349v.getParent() instanceof View) {
                    View view = (View) c2.f1349v.getParent();
                    WeakHashMap weakHashMap = H.N.f327a;
                    H.E.c(view);
                    return;
                }
                return;
            default:
                return;
        }
    }
}

package g;

import android.view.View;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class N extends C0.d {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1382a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ P f1383b;

    public /* synthetic */ N(P p2, int i2) {
        this.f1382a = i2;
        this.f1383b = p2;
    }

    @Override // H.T
    public final void a() {
        View view;
        P p2 = this.f1383b;
        switch (this.f1382a) {
            case 0:
                if (p2.f1403o && (view = p2.f1396g) != null) {
                    view.setTranslationY(0.0f);
                    p2.f1393d.setTranslationY(0.0f);
                }
                p2.f1393d.setVisibility(8);
                p2.f1393d.setTransitioning(false);
                p2.f1407s = null;
                E.c cVar = p2.f1399k;
                if (cVar != null) {
                    cVar.d(p2.f1398j);
                    p2.f1398j = null;
                    p2.f1399k = null;
                }
                ActionBarOverlayLayout actionBarOverlayLayout = p2.f1392c;
                if (actionBarOverlayLayout != null) {
                    WeakHashMap weakHashMap = H.N.f327a;
                    H.E.c(actionBarOverlayLayout);
                    return;
                }
                return;
            default:
                p2.f1407s = null;
                p2.f1393d.requestLayout();
                return;
        }
    }
}

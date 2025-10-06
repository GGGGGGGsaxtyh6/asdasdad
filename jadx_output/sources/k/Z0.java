package k;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.Toolbar;
import i.InterfaceC0121c;
import j.SubMenuC0138F;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class Z0 implements j.z {

    /* renamed from: a, reason: collision with root package name */
    public j.n f1871a;

    /* renamed from: b, reason: collision with root package name */
    public j.p f1872b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Toolbar f1873c;

    public Z0(Toolbar toolbar) {
        this.f1873c = toolbar;
    }

    @Override // j.z
    public final void c() {
        if (this.f1872b != null) {
            j.n nVar = this.f1871a;
            if (nVar != null) {
                int size = nVar.f1663f.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (this.f1871a.getItem(i2) == this.f1872b) {
                        return;
                    }
                }
            }
            d(this.f1872b);
        }
    }

    @Override // j.z
    public final boolean d(j.p pVar) {
        Toolbar toolbar = this.f1873c;
        KeyEvent.Callback callback = toolbar.f932i;
        if (callback instanceof InterfaceC0121c) {
            ((j.r) ((InterfaceC0121c) callback)).f1714a.onActionViewCollapsed();
        }
        toolbar.removeView(toolbar.f932i);
        toolbar.removeView(toolbar.h);
        toolbar.f932i = null;
        ArrayList arrayList = toolbar.f909E;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            toolbar.addView((View) arrayList.get(size));
        }
        arrayList.clear();
        this.f1872b = null;
        toolbar.requestLayout();
        pVar.f1686C = false;
        pVar.f1699n.p(false);
        toolbar.v();
        return true;
    }

    @Override // j.z
    public final void f(Context context, j.n nVar) {
        j.p pVar;
        j.n nVar2 = this.f1871a;
        if (nVar2 != null && (pVar = this.f1872b) != null) {
            nVar2.d(pVar);
        }
        this.f1871a = nVar;
    }

    @Override // j.z
    public final boolean g() {
        return false;
    }

    @Override // j.z
    public final boolean j(SubMenuC0138F subMenuC0138F) {
        return false;
    }

    @Override // j.z
    public final boolean k(j.p pVar) {
        Toolbar toolbar = this.f1873c;
        toolbar.c();
        ViewParent parent = toolbar.h.getParent();
        if (parent != toolbar) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(toolbar.h);
            }
            toolbar.addView(toolbar.h);
        }
        View actionView = pVar.getActionView();
        toolbar.f932i = actionView;
        this.f1872b = pVar;
        ViewParent parent2 = actionView.getParent();
        if (parent2 != toolbar) {
            if (parent2 instanceof ViewGroup) {
                ((ViewGroup) parent2).removeView(toolbar.f932i);
            }
            a1 h = Toolbar.h();
            h.f1877a = (toolbar.f937n & 112) | 8388611;
            h.f1878b = 2;
            toolbar.f932i.setLayoutParams(h);
            toolbar.addView(toolbar.f932i);
        }
        for (int childCount = toolbar.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = toolbar.getChildAt(childCount);
            if (((a1) childAt.getLayoutParams()).f1878b != 2 && childAt != toolbar.f925a) {
                toolbar.removeViewAt(childCount);
                toolbar.f909E.add(childAt);
            }
        }
        toolbar.requestLayout();
        pVar.f1686C = true;
        pVar.f1699n.p(false);
        KeyEvent.Callback callback = toolbar.f932i;
        if (callback instanceof InterfaceC0121c) {
            ((j.r) ((InterfaceC0121c) callback)).f1714a.onActionViewExpanded();
        }
        toolbar.v();
        return true;
    }

    @Override // j.z
    public final void a(j.n nVar, boolean z2) {
    }
}

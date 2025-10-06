package j;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import java.util.ArrayList;
import java.util.Iterator;
import k.C0202t0;
import k.I0;
import k.L0;
import org.conscrypt.R;

/* renamed from: j.h, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class ViewOnKeyListenerC0146h extends v implements View.OnKeyListener, PopupWindow.OnDismissListener {

    /* renamed from: b, reason: collision with root package name */
    public final Context f1621b;

    /* renamed from: c, reason: collision with root package name */
    public final int f1622c;

    /* renamed from: d, reason: collision with root package name */
    public final int f1623d;

    /* renamed from: e, reason: collision with root package name */
    public final boolean f1624e;

    /* renamed from: f, reason: collision with root package name */
    public final Handler f1625f;

    /* renamed from: i, reason: collision with root package name */
    public final ViewTreeObserverOnGlobalLayoutListenerC0142d f1627i;

    /* renamed from: j, reason: collision with root package name */
    public final ViewOnAttachStateChangeListenerC0143e f1628j;

    /* renamed from: n, reason: collision with root package name */
    public View f1632n;

    /* renamed from: o, reason: collision with root package name */
    public View f1633o;

    /* renamed from: p, reason: collision with root package name */
    public int f1634p;

    /* renamed from: q, reason: collision with root package name */
    public boolean f1635q;

    /* renamed from: r, reason: collision with root package name */
    public boolean f1636r;

    /* renamed from: s, reason: collision with root package name */
    public int f1637s;

    /* renamed from: t, reason: collision with root package name */
    public int f1638t;

    /* renamed from: v, reason: collision with root package name */
    public boolean f1640v;

    /* renamed from: w, reason: collision with root package name */
    public y f1641w;

    /* renamed from: x, reason: collision with root package name */
    public ViewTreeObserver f1642x;
    public w y;

    /* renamed from: z, reason: collision with root package name */
    public boolean f1643z;

    /* renamed from: g, reason: collision with root package name */
    public final ArrayList f1626g = new ArrayList();
    public final ArrayList h = new ArrayList();

    /* renamed from: k, reason: collision with root package name */
    public final A.f f1629k = new A.f(22, this);

    /* renamed from: l, reason: collision with root package name */
    public int f1630l = 0;

    /* renamed from: m, reason: collision with root package name */
    public int f1631m = 0;

    /* renamed from: u, reason: collision with root package name */
    public boolean f1639u = false;

    public ViewOnKeyListenerC0146h(Context context, View view, int i2, boolean z2) {
        int i3 = 0;
        this.f1627i = new ViewTreeObserverOnGlobalLayoutListenerC0142d(i3, this);
        this.f1628j = new ViewOnAttachStateChangeListenerC0143e(this, i3);
        this.f1621b = context;
        this.f1632n = view;
        this.f1623d = i2;
        this.f1624e = z2;
        this.f1634p = view.getLayoutDirection() != 1 ? 1 : 0;
        Resources resources = context.getResources();
        this.f1622c = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.f1625f = new Handler();
    }

    @Override // j.z
    public final void a(n nVar, boolean z2) {
        int i2;
        ArrayList arrayList = this.h;
        int size = arrayList.size();
        int i3 = 0;
        while (true) {
            if (i3 < size) {
                if (nVar == ((C0145g) arrayList.get(i3)).f1619b) {
                    break;
                } else {
                    i3++;
                }
            } else {
                i3 = -1;
                break;
            }
        }
        if (i3 < 0) {
            return;
        }
        int i4 = i3 + 1;
        if (i4 < arrayList.size()) {
            ((C0145g) arrayList.get(i4)).f1619b.c(false);
        }
        C0145g c0145g = (C0145g) arrayList.remove(i3);
        c0145g.f1619b.r(this);
        boolean z3 = this.f1643z;
        L0 l02 = c0145g.f1618a;
        if (z3) {
            I0.b(l02.y, null);
            l02.y.setAnimationStyle(0);
        }
        l02.dismiss();
        int size2 = arrayList.size();
        if (size2 > 0) {
            this.f1634p = ((C0145g) arrayList.get(size2 - 1)).f1620c;
        } else {
            if (this.f1632n.getLayoutDirection() == 1) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            this.f1634p = i2;
        }
        if (size2 == 0) {
            dismiss();
            y yVar = this.f1641w;
            if (yVar != null) {
                yVar.a(nVar, true);
            }
            ViewTreeObserver viewTreeObserver = this.f1642x;
            if (viewTreeObserver != null) {
                if (viewTreeObserver.isAlive()) {
                    this.f1642x.removeGlobalOnLayoutListener(this.f1627i);
                }
                this.f1642x = null;
            }
            this.f1633o.removeOnAttachStateChangeListener(this.f1628j);
            this.y.onDismiss();
            return;
        }
        if (z2) {
            ((C0145g) arrayList.get(0)).f1619b.c(false);
        }
    }

    @Override // j.InterfaceC0136D
    public final boolean b() {
        ArrayList arrayList = this.h;
        if (arrayList.size() <= 0 || !((C0145g) arrayList.get(0)).f1618a.y.isShowing()) {
            return false;
        }
        return true;
    }

    @Override // j.z
    public final void c() {
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            ListAdapter adapter = ((C0145g) it.next()).f1618a.f1766c.getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((k) adapter).notifyDataSetChanged();
        }
    }

    @Override // j.InterfaceC0136D
    public final void dismiss() {
        ArrayList arrayList = this.h;
        int size = arrayList.size();
        if (size > 0) {
            C0145g[] c0145gArr = (C0145g[]) arrayList.toArray(new C0145g[size]);
            for (int i2 = size - 1; i2 >= 0; i2--) {
                C0145g c0145g = c0145gArr[i2];
                if (c0145g.f1618a.y.isShowing()) {
                    c0145g.f1618a.dismiss();
                }
            }
        }
    }

    @Override // j.InterfaceC0136D
    public final C0202t0 e() {
        ArrayList arrayList = this.h;
        if (arrayList.isEmpty()) {
            return null;
        }
        return ((C0145g) arrayList.get(arrayList.size() - 1)).f1618a.f1766c;
    }

    @Override // j.z
    public final boolean g() {
        return false;
    }

    @Override // j.z
    public final void h(y yVar) {
        this.f1641w = yVar;
    }

    @Override // j.InterfaceC0136D
    public final void i() {
        boolean z2;
        if (b()) {
            return;
        }
        ArrayList arrayList = this.f1626g;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            v((n) it.next());
        }
        arrayList.clear();
        View view = this.f1632n;
        this.f1633o = view;
        if (view != null) {
            if (this.f1642x == null) {
                z2 = true;
            } else {
                z2 = false;
            }
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.f1642x = viewTreeObserver;
            if (z2) {
                viewTreeObserver.addOnGlobalLayoutListener(this.f1627i);
            }
            this.f1633o.addOnAttachStateChangeListener(this.f1628j);
        }
    }

    @Override // j.z
    public final boolean j(SubMenuC0138F subMenuC0138F) {
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            C0145g c0145g = (C0145g) it.next();
            if (subMenuC0138F == c0145g.f1619b) {
                c0145g.f1618a.f1766c.requestFocus();
                return true;
            }
        }
        if (subMenuC0138F.hasVisibleItems()) {
            l(subMenuC0138F);
            y yVar = this.f1641w;
            if (yVar != null) {
                yVar.c(subMenuC0138F);
            }
            return true;
        }
        return false;
    }

    @Override // j.v
    public final void l(n nVar) {
        nVar.b(this, this.f1621b);
        if (b()) {
            v(nVar);
        } else {
            this.f1626g.add(nVar);
        }
    }

    @Override // j.v
    public final void n(View view) {
        if (this.f1632n != view) {
            this.f1632n = view;
            this.f1631m = Gravity.getAbsoluteGravity(this.f1630l, view.getLayoutDirection());
        }
    }

    @Override // j.v
    public final void o(boolean z2) {
        this.f1639u = z2;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        C0145g c0145g;
        ArrayList arrayList = this.h;
        int size = arrayList.size();
        int i2 = 0;
        while (true) {
            if (i2 < size) {
                c0145g = (C0145g) arrayList.get(i2);
                if (!c0145g.f1618a.y.isShowing()) {
                    break;
                } else {
                    i2++;
                }
            } else {
                c0145g = null;
                break;
            }
        }
        if (c0145g != null) {
            c0145g.f1619b.c(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 && i2 == 82) {
            dismiss();
            return true;
        }
        return false;
    }

    @Override // j.v
    public final void p(int i2) {
        if (this.f1630l != i2) {
            this.f1630l = i2;
            this.f1631m = Gravity.getAbsoluteGravity(i2, this.f1632n.getLayoutDirection());
        }
    }

    @Override // j.v
    public final void q(int i2) {
        this.f1635q = true;
        this.f1637s = i2;
    }

    @Override // j.v
    public final void r(PopupWindow.OnDismissListener onDismissListener) {
        this.y = (w) onDismissListener;
    }

    @Override // j.v
    public final void s(boolean z2) {
        this.f1640v = z2;
    }

    @Override // j.v
    public final void t(int i2) {
        this.f1636r = true;
        this.f1638t = i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x013f, code lost:
    
        if (((r8.getWidth() + r11[0]) + r5) > r9.right) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0141, code lost:
    
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0144, code lost:
    
        r8 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0149, code lost:
    
        if ((r11[0] - r5) < 0) goto L60;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01b2  */
    /* JADX WARN: Type inference failed for: r7v0, types: [k.L0, k.G0] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void v(j.n r17) {
        /*
            Method dump skipped, instructions count: 526
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j.ViewOnKeyListenerC0146h.v(j.n):void");
    }
}

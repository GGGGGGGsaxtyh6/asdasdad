package j;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import k.C0202t0;
import k.G0;
import k.L0;
import org.conscrypt.R;

/* renamed from: j.E, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class ViewOnKeyListenerC0137E extends v implements PopupWindow.OnDismissListener, View.OnKeyListener {

    /* renamed from: b, reason: collision with root package name */
    public final Context f1573b;

    /* renamed from: c, reason: collision with root package name */
    public final n f1574c;

    /* renamed from: d, reason: collision with root package name */
    public final k f1575d;

    /* renamed from: e, reason: collision with root package name */
    public final boolean f1576e;

    /* renamed from: f, reason: collision with root package name */
    public final int f1577f;

    /* renamed from: g, reason: collision with root package name */
    public final int f1578g;
    public final L0 h;

    /* renamed from: i, reason: collision with root package name */
    public final ViewTreeObserverOnGlobalLayoutListenerC0142d f1579i;

    /* renamed from: j, reason: collision with root package name */
    public final ViewOnAttachStateChangeListenerC0143e f1580j;

    /* renamed from: k, reason: collision with root package name */
    public w f1581k;

    /* renamed from: l, reason: collision with root package name */
    public View f1582l;

    /* renamed from: m, reason: collision with root package name */
    public View f1583m;

    /* renamed from: n, reason: collision with root package name */
    public y f1584n;

    /* renamed from: o, reason: collision with root package name */
    public ViewTreeObserver f1585o;

    /* renamed from: p, reason: collision with root package name */
    public boolean f1586p;

    /* renamed from: q, reason: collision with root package name */
    public boolean f1587q;

    /* renamed from: r, reason: collision with root package name */
    public int f1588r;

    /* renamed from: s, reason: collision with root package name */
    public int f1589s = 0;

    /* renamed from: t, reason: collision with root package name */
    public boolean f1590t;

    /* JADX WARN: Type inference failed for: r6v1, types: [k.L0, k.G0] */
    public ViewOnKeyListenerC0137E(int i2, Context context, View view, n nVar, boolean z2) {
        int i3 = 1;
        this.f1579i = new ViewTreeObserverOnGlobalLayoutListenerC0142d(i3, this);
        this.f1580j = new ViewOnAttachStateChangeListenerC0143e(this, i3);
        this.f1573b = context;
        this.f1574c = nVar;
        this.f1576e = z2;
        this.f1575d = new k(nVar, LayoutInflater.from(context), z2, R.layout.abc_popup_menu_item_layout);
        this.f1578g = i2;
        Resources resources = context.getResources();
        this.f1577f = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.f1582l = view;
        this.h = new G0(context, null, i2);
        nVar.b(this, context);
    }

    @Override // j.z
    public final void a(n nVar, boolean z2) {
        if (nVar != this.f1574c) {
            return;
        }
        dismiss();
        y yVar = this.f1584n;
        if (yVar != null) {
            yVar.a(nVar, z2);
        }
    }

    @Override // j.InterfaceC0136D
    public final boolean b() {
        if (!this.f1586p && this.h.y.isShowing()) {
            return true;
        }
        return false;
    }

    @Override // j.z
    public final void c() {
        this.f1587q = false;
        k kVar = this.f1575d;
        if (kVar != null) {
            kVar.notifyDataSetChanged();
        }
    }

    @Override // j.InterfaceC0136D
    public final void dismiss() {
        if (b()) {
            this.h.dismiss();
        }
    }

    @Override // j.InterfaceC0136D
    public final C0202t0 e() {
        return this.h.f1766c;
    }

    @Override // j.z
    public final boolean g() {
        return false;
    }

    @Override // j.z
    public final void h(y yVar) {
        this.f1584n = yVar;
    }

    @Override // j.InterfaceC0136D
    public final void i() {
        View view;
        boolean z2;
        Rect rect;
        if (!b()) {
            if (!this.f1586p && (view = this.f1582l) != null) {
                this.f1583m = view;
                L0 l02 = this.h;
                l02.y.setOnDismissListener(this);
                l02.f1778p = this;
                l02.f1786x = true;
                l02.y.setFocusable(true);
                View view2 = this.f1583m;
                if (this.f1585o == null) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
                this.f1585o = viewTreeObserver;
                if (z2) {
                    viewTreeObserver.addOnGlobalLayoutListener(this.f1579i);
                }
                view2.addOnAttachStateChangeListener(this.f1580j);
                l02.f1777o = view2;
                l02.f1774l = this.f1589s;
                boolean z3 = this.f1587q;
                Context context = this.f1573b;
                k kVar = this.f1575d;
                if (!z3) {
                    this.f1588r = v.m(kVar, context, this.f1577f);
                    this.f1587q = true;
                }
                l02.r(this.f1588r);
                l02.y.setInputMethodMode(2);
                Rect rect2 = this.f1721a;
                if (rect2 != null) {
                    rect = new Rect(rect2);
                } else {
                    rect = null;
                }
                l02.f1785w = rect;
                l02.i();
                C0202t0 c0202t0 = l02.f1766c;
                c0202t0.setOnKeyListener(this);
                if (this.f1590t) {
                    n nVar = this.f1574c;
                    if (nVar.f1669m != null) {
                        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) c0202t0, false);
                        TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
                        if (textView != null) {
                            textView.setText(nVar.f1669m);
                        }
                        frameLayout.setEnabled(false);
                        c0202t0.addHeaderView(frameLayout, null, false);
                    }
                }
                l02.o(kVar);
                l02.i();
                return;
            }
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // j.z
    public final boolean j(SubMenuC0138F subMenuC0138F) {
        if (subMenuC0138F.hasVisibleItems()) {
            View view = this.f1583m;
            x xVar = new x(this.f1578g, this.f1573b, view, subMenuC0138F, this.f1576e);
            y yVar = this.f1584n;
            xVar.h = yVar;
            v vVar = xVar.f1730i;
            if (vVar != null) {
                vVar.h(yVar);
            }
            boolean u2 = v.u(subMenuC0138F);
            xVar.f1729g = u2;
            v vVar2 = xVar.f1730i;
            if (vVar2 != null) {
                vVar2.o(u2);
            }
            xVar.f1731j = this.f1581k;
            this.f1581k = null;
            this.f1574c.c(false);
            L0 l02 = this.h;
            int i2 = l02.f1769f;
            int j2 = l02.j();
            if ((Gravity.getAbsoluteGravity(this.f1589s, this.f1582l.getLayoutDirection()) & 7) == 5) {
                i2 += this.f1582l.getWidth();
            }
            if (!xVar.b()) {
                if (xVar.f1727e != null) {
                    xVar.d(i2, j2, true, true);
                }
            }
            y yVar2 = this.f1584n;
            if (yVar2 != null) {
                yVar2.c(subMenuC0138F);
            }
            return true;
        }
        return false;
    }

    @Override // j.v
    public final void n(View view) {
        this.f1582l = view;
    }

    @Override // j.v
    public final void o(boolean z2) {
        this.f1575d.f1654c = z2;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.f1586p = true;
        this.f1574c.c(true);
        ViewTreeObserver viewTreeObserver = this.f1585o;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f1585o = this.f1583m.getViewTreeObserver();
            }
            this.f1585o.removeGlobalOnLayoutListener(this.f1579i);
            this.f1585o = null;
        }
        this.f1583m.removeOnAttachStateChangeListener(this.f1580j);
        w wVar = this.f1581k;
        if (wVar != null) {
            wVar.onDismiss();
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
        this.f1589s = i2;
    }

    @Override // j.v
    public final void q(int i2) {
        this.h.f1769f = i2;
    }

    @Override // j.v
    public final void r(PopupWindow.OnDismissListener onDismissListener) {
        this.f1581k = (w) onDismissListener;
    }

    @Override // j.v
    public final void s(boolean z2) {
        this.f1590t = z2;
    }

    @Override // j.v
    public final void t(int i2) {
        this.h.m(i2);
    }

    @Override // j.v
    public final void l(n nVar) {
    }
}

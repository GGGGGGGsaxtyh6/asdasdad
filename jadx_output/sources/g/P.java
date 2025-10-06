package g;

import H.S;
import android.R;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import f.AbstractC0101a;
import i.AbstractC0120b;
import i.C0128j;
import i.C0129k;
import i.InterfaceC0119a;
import java.util.ArrayList;
import java.util.WeakHashMap;
import k.InterfaceC0170d;
import k.InterfaceC0189m0;
import k.Z0;
import k.e1;

/* loaded from: classes.dex */
public final class P extends AbstractC0103a implements InterfaceC0170d {
    public static final AccelerateInterpolator y = new AccelerateInterpolator();

    /* renamed from: z, reason: collision with root package name */
    public static final DecelerateInterpolator f1389z = new DecelerateInterpolator();

    /* renamed from: a, reason: collision with root package name */
    public Context f1390a;

    /* renamed from: b, reason: collision with root package name */
    public Context f1391b;

    /* renamed from: c, reason: collision with root package name */
    public ActionBarOverlayLayout f1392c;

    /* renamed from: d, reason: collision with root package name */
    public ActionBarContainer f1393d;

    /* renamed from: e, reason: collision with root package name */
    public InterfaceC0189m0 f1394e;

    /* renamed from: f, reason: collision with root package name */
    public ActionBarContextView f1395f;

    /* renamed from: g, reason: collision with root package name */
    public final View f1396g;
    public boolean h;

    /* renamed from: i, reason: collision with root package name */
    public O f1397i;

    /* renamed from: j, reason: collision with root package name */
    public O f1398j;

    /* renamed from: k, reason: collision with root package name */
    public E.c f1399k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f1400l;

    /* renamed from: m, reason: collision with root package name */
    public final ArrayList f1401m;

    /* renamed from: n, reason: collision with root package name */
    public int f1402n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f1403o;

    /* renamed from: p, reason: collision with root package name */
    public boolean f1404p;

    /* renamed from: q, reason: collision with root package name */
    public boolean f1405q;

    /* renamed from: r, reason: collision with root package name */
    public boolean f1406r;

    /* renamed from: s, reason: collision with root package name */
    public C0129k f1407s;

    /* renamed from: t, reason: collision with root package name */
    public boolean f1408t;

    /* renamed from: u, reason: collision with root package name */
    public boolean f1409u;

    /* renamed from: v, reason: collision with root package name */
    public final N f1410v;

    /* renamed from: w, reason: collision with root package name */
    public final N f1411w;

    /* renamed from: x, reason: collision with root package name */
    public final A.f f1412x;

    public P(Activity activity, boolean z2) {
        new ArrayList();
        this.f1401m = new ArrayList();
        this.f1402n = 0;
        this.f1403o = true;
        this.f1406r = true;
        this.f1410v = new N(this, 0);
        this.f1411w = new N(this, 1);
        this.f1412x = new A.f(21, this);
        View decorView = activity.getWindow().getDecorView();
        q(decorView);
        if (z2) {
            return;
        }
        this.f1396g = decorView.findViewById(R.id.content);
    }

    @Override // g.AbstractC0103a
    public final boolean b() {
        Z0 z02;
        j.p pVar;
        InterfaceC0189m0 interfaceC0189m0 = this.f1394e;
        if (interfaceC0189m0 != null && (z02 = ((e1) interfaceC0189m0).f1895a.f917M) != null && z02.f1872b != null) {
            Z0 z03 = ((e1) interfaceC0189m0).f1895a.f917M;
            if (z03 == null) {
                pVar = null;
            } else {
                pVar = z03.f1872b;
            }
            if (pVar != null) {
                pVar.collapseActionView();
                return true;
            }
            return true;
        }
        return false;
    }

    @Override // g.AbstractC0103a
    public final void c(boolean z2) {
        if (z2 == this.f1400l) {
            return;
        }
        this.f1400l = z2;
        ArrayList arrayList = this.f1401m;
        if (arrayList.size() <= 0) {
            return;
        }
        arrayList.get(0).getClass();
        throw new ClassCastException();
    }

    @Override // g.AbstractC0103a
    public final int d() {
        return ((e1) this.f1394e).f1896b;
    }

    @Override // g.AbstractC0103a
    public final Context e() {
        if (this.f1391b == null) {
            TypedValue typedValue = new TypedValue();
            this.f1390a.getTheme().resolveAttribute(org.conscrypt.R.attr.actionBarWidgetTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.f1391b = new ContextThemeWrapper(this.f1390a, i2);
            } else {
                this.f1391b = this.f1390a;
            }
        }
        return this.f1391b;
    }

    @Override // g.AbstractC0103a
    public final void g() {
        r(this.f1390a.getResources().getBoolean(org.conscrypt.R.bool.abc_action_bar_embed_tabs));
    }

    @Override // g.AbstractC0103a
    public final boolean i(int i2, KeyEvent keyEvent) {
        j.n nVar;
        O o2 = this.f1397i;
        if (o2 == null || (nVar = o2.f1385d) == null) {
            return false;
        }
        boolean z2 = true;
        if (KeyCharacterMap.load(keyEvent.getDeviceId()).getKeyboardType() == 1) {
            z2 = false;
        }
        nVar.setQwertyMode(z2);
        return nVar.performShortcut(i2, keyEvent, 0);
    }

    @Override // g.AbstractC0103a
    public final void l(boolean z2) {
        int i2;
        if (!this.h) {
            if (z2) {
                i2 = 4;
            } else {
                i2 = 0;
            }
            e1 e1Var = (e1) this.f1394e;
            int i3 = e1Var.f1896b;
            this.h = true;
            e1Var.a((i2 & 4) | (i3 & (-5)));
        }
    }

    @Override // g.AbstractC0103a
    public final void m(boolean z2) {
        C0129k c0129k;
        this.f1408t = z2;
        if (!z2 && (c0129k = this.f1407s) != null) {
            c0129k.a();
        }
    }

    @Override // g.AbstractC0103a
    public final void n(CharSequence charSequence) {
        e1 e1Var = (e1) this.f1394e;
        if (!e1Var.f1901g) {
            e1Var.h = charSequence;
            if ((e1Var.f1896b & 8) != 0) {
                Toolbar toolbar = e1Var.f1895a;
                toolbar.setTitle(charSequence);
                if (e1Var.f1901g) {
                    H.N.i(toolbar.getRootView(), charSequence);
                }
            }
        }
    }

    @Override // g.AbstractC0103a
    public final AbstractC0120b o(E.c cVar) {
        O o2 = this.f1397i;
        if (o2 != null) {
            o2.a();
        }
        this.f1392c.setHideOnContentScrollEnabled(false);
        this.f1395f.e();
        O o3 = new O(this, this.f1395f.getContext(), cVar);
        j.n nVar = o3.f1385d;
        nVar.w();
        try {
            if (((InterfaceC0119a) o3.f1386e.f271b).c(o3, nVar)) {
                this.f1397i = o3;
                o3.g();
                this.f1395f.c(o3);
                p(true);
                return o3;
            }
            return null;
        } finally {
            nVar.v();
        }
    }

    public final void p(boolean z2) {
        S i2;
        S s2;
        long j2;
        if (z2) {
            if (!this.f1405q) {
                this.f1405q = true;
                ActionBarOverlayLayout actionBarOverlayLayout = this.f1392c;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.setShowingForActionMode(true);
                }
                s(false);
            }
        } else if (this.f1405q) {
            this.f1405q = false;
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.f1392c;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.setShowingForActionMode(false);
            }
            s(false);
        }
        if (this.f1393d.isLaidOut()) {
            if (z2) {
                e1 e1Var = (e1) this.f1394e;
                i2 = H.N.a(e1Var.f1895a);
                i2.a(0.0f);
                i2.c(100L);
                i2.d(new C0128j(e1Var, 4));
                s2 = this.f1395f.i(200L, 0);
            } else {
                e1 e1Var2 = (e1) this.f1394e;
                S a2 = H.N.a(e1Var2.f1895a);
                a2.a(1.0f);
                a2.c(200L);
                a2.d(new C0128j(e1Var2, 0));
                i2 = this.f1395f.i(100L, 8);
                s2 = a2;
            }
            C0129k c0129k = new C0129k();
            ArrayList arrayList = c0129k.f1566a;
            arrayList.add(i2);
            View view = (View) i2.f336a.get();
            if (view != null) {
                j2 = view.animate().getDuration();
            } else {
                j2 = 0;
            }
            View view2 = (View) s2.f336a.get();
            if (view2 != null) {
                view2.animate().setStartDelay(j2);
            }
            arrayList.add(s2);
            c0129k.b();
            return;
        }
        if (z2) {
            ((e1) this.f1394e).f1895a.setVisibility(4);
            this.f1395f.setVisibility(0);
        } else {
            ((e1) this.f1394e).f1895a.setVisibility(0);
            this.f1395f.setVisibility(8);
        }
    }

    public final void q(View view) {
        String str;
        InterfaceC0189m0 wrapper;
        boolean z2;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(org.conscrypt.R.id.decor_content_parent);
        this.f1392c = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        KeyEvent.Callback findViewById = view.findViewById(org.conscrypt.R.id.action_bar);
        if (findViewById instanceof InterfaceC0189m0) {
            wrapper = (InterfaceC0189m0) findViewById;
        } else if (findViewById instanceof Toolbar) {
            wrapper = ((Toolbar) findViewById).getWrapper();
        } else {
            if (findViewById != null) {
                str = findViewById.getClass().getSimpleName();
            } else {
                str = "null";
            }
            throw new IllegalStateException("Can't make a decor toolbar out of ".concat(str));
        }
        this.f1394e = wrapper;
        this.f1395f = (ActionBarContextView) view.findViewById(org.conscrypt.R.id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(org.conscrypt.R.id.action_bar_container);
        this.f1393d = actionBarContainer;
        InterfaceC0189m0 interfaceC0189m0 = this.f1394e;
        if (interfaceC0189m0 != null && this.f1395f != null && actionBarContainer != null) {
            Context context = ((e1) interfaceC0189m0).f1895a.getContext();
            this.f1390a = context;
            if ((((e1) this.f1394e).f1896b & 4) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                this.h = true;
            }
            int i2 = context.getApplicationInfo().targetSdkVersion;
            this.f1394e.getClass();
            r(context.getResources().getBoolean(org.conscrypt.R.bool.abc_action_bar_embed_tabs));
            TypedArray obtainStyledAttributes = this.f1390a.obtainStyledAttributes(null, AbstractC0101a.f1262a, org.conscrypt.R.attr.actionBarStyle, 0);
            if (obtainStyledAttributes.getBoolean(14, false)) {
                ActionBarOverlayLayout actionBarOverlayLayout2 = this.f1392c;
                if (actionBarOverlayLayout2.f862g) {
                    this.f1409u = true;
                    actionBarOverlayLayout2.setHideOnContentScrollEnabled(true);
                } else {
                    throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
                }
            }
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(12, 0);
            if (dimensionPixelSize != 0) {
                ActionBarContainer actionBarContainer2 = this.f1393d;
                WeakHashMap weakHashMap = H.N.f327a;
                H.G.g(actionBarContainer2, dimensionPixelSize);
            }
            obtainStyledAttributes.recycle();
            return;
        }
        throw new IllegalStateException(P.class.getSimpleName().concat(" can only be used with a compatible window decor layout"));
    }

    public final void r(boolean z2) {
        if (!z2) {
            ((e1) this.f1394e).getClass();
            this.f1393d.setTabContainer(null);
        } else {
            this.f1393d.setTabContainer(null);
            ((e1) this.f1394e).getClass();
        }
        this.f1394e.getClass();
        ((e1) this.f1394e).f1895a.setCollapsible(false);
        this.f1392c.setHasNonEmbeddedTabs(false);
    }

    public final void s(boolean z2) {
        boolean z3;
        boolean z4 = this.f1404p;
        if (this.f1405q || !z4) {
            z3 = true;
        } else {
            z3 = false;
        }
        View view = this.f1396g;
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = null;
        final A.f fVar = this.f1412x;
        if (z3) {
            if (!this.f1406r) {
                this.f1406r = true;
                C0129k c0129k = this.f1407s;
                if (c0129k != null) {
                    c0129k.a();
                }
                this.f1393d.setVisibility(0);
                int i2 = this.f1402n;
                N n2 = this.f1411w;
                if (i2 == 0 && (this.f1408t || z2)) {
                    this.f1393d.setTranslationY(0.0f);
                    float f2 = -this.f1393d.getHeight();
                    if (z2) {
                        this.f1393d.getLocationInWindow(new int[]{0, 0});
                        f2 -= r12[1];
                    }
                    this.f1393d.setTranslationY(f2);
                    C0129k c0129k2 = new C0129k();
                    S a2 = H.N.a(this.f1393d);
                    a2.e(0.0f);
                    final View view2 = (View) a2.f336a.get();
                    if (view2 != null) {
                        if (fVar != null) {
                            animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener(view2) { // from class: H.P
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    ((View) ((g.P) A.f.this.f8b).f1393d.getParent()).invalidate();
                                }
                            };
                        }
                        view2.animate().setUpdateListener(animatorUpdateListener);
                    }
                    boolean z5 = c0129k2.f1570e;
                    ArrayList arrayList = c0129k2.f1566a;
                    if (!z5) {
                        arrayList.add(a2);
                    }
                    if (this.f1403o && view != null) {
                        view.setTranslationY(f2);
                        S a3 = H.N.a(view);
                        a3.e(0.0f);
                        if (!c0129k2.f1570e) {
                            arrayList.add(a3);
                        }
                    }
                    DecelerateInterpolator decelerateInterpolator = f1389z;
                    boolean z6 = c0129k2.f1570e;
                    if (!z6) {
                        c0129k2.f1568c = decelerateInterpolator;
                    }
                    if (!z6) {
                        c0129k2.f1567b = 250L;
                    }
                    if (!z6) {
                        c0129k2.f1569d = n2;
                    }
                    this.f1407s = c0129k2;
                    c0129k2.b();
                } else {
                    this.f1393d.setAlpha(1.0f);
                    this.f1393d.setTranslationY(0.0f);
                    if (this.f1403o && view != null) {
                        view.setTranslationY(0.0f);
                    }
                    n2.a();
                }
                ActionBarOverlayLayout actionBarOverlayLayout = this.f1392c;
                if (actionBarOverlayLayout != null) {
                    WeakHashMap weakHashMap = H.N.f327a;
                    H.E.c(actionBarOverlayLayout);
                    return;
                }
                return;
            }
            return;
        }
        if (this.f1406r) {
            this.f1406r = false;
            C0129k c0129k3 = this.f1407s;
            if (c0129k3 != null) {
                c0129k3.a();
            }
            int i3 = this.f1402n;
            N n3 = this.f1410v;
            if (i3 == 0 && (this.f1408t || z2)) {
                this.f1393d.setAlpha(1.0f);
                this.f1393d.setTransitioning(true);
                C0129k c0129k4 = new C0129k();
                float f3 = -this.f1393d.getHeight();
                if (z2) {
                    this.f1393d.getLocationInWindow(new int[]{0, 0});
                    f3 -= r12[1];
                }
                S a4 = H.N.a(this.f1393d);
                a4.e(f3);
                final View view3 = (View) a4.f336a.get();
                if (view3 != null) {
                    if (fVar != null) {
                        animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener(view3) { // from class: H.P
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                ((View) ((g.P) A.f.this.f8b).f1393d.getParent()).invalidate();
                            }
                        };
                    }
                    view3.animate().setUpdateListener(animatorUpdateListener);
                }
                boolean z7 = c0129k4.f1570e;
                ArrayList arrayList2 = c0129k4.f1566a;
                if (!z7) {
                    arrayList2.add(a4);
                }
                if (this.f1403o && view != null) {
                    S a5 = H.N.a(view);
                    a5.e(f3);
                    if (!c0129k4.f1570e) {
                        arrayList2.add(a5);
                    }
                }
                AccelerateInterpolator accelerateInterpolator = y;
                boolean z8 = c0129k4.f1570e;
                if (!z8) {
                    c0129k4.f1568c = accelerateInterpolator;
                }
                if (!z8) {
                    c0129k4.f1567b = 250L;
                }
                if (!z8) {
                    c0129k4.f1569d = n3;
                }
                this.f1407s = c0129k4;
                c0129k4.b();
                return;
            }
            n3.a();
        }
    }

    public P(Dialog dialog) {
        new ArrayList();
        this.f1401m = new ArrayList();
        this.f1402n = 0;
        this.f1403o = true;
        this.f1406r = true;
        this.f1410v = new N(this, 0);
        this.f1411w = new N(this, 1);
        this.f1412x = new A.f(21, this);
        q(dialog.getWindow().getDecorView());
    }
}

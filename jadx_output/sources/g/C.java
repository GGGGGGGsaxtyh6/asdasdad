package g;

import H.S;
import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.ContentFrameLayout;
import f.AbstractC0101a;
import i.AbstractC0120b;
import i.C0122d;
import i.C0127i;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.WeakHashMap;
import k.C0176g;
import k.C0184k;
import k.C0203u;
import k.InterfaceC0187l0;
import k.e1;
import k.m1;

/* loaded from: classes.dex */
public final class C extends AbstractC0118p implements j.l, LayoutInflater.Factory2 {
    public static final n.k h0 = new n.k(0);

    /* renamed from: i0, reason: collision with root package name */
    public static final int[] f1303i0 = {R.attr.windowBackground};

    /* renamed from: j0, reason: collision with root package name */
    public static final boolean f1304j0 = !"robolectric".equals(Build.FINGERPRINT);

    /* renamed from: A, reason: collision with root package name */
    public ViewGroup f1305A;

    /* renamed from: B, reason: collision with root package name */
    public TextView f1306B;

    /* renamed from: C, reason: collision with root package name */
    public View f1307C;

    /* renamed from: D, reason: collision with root package name */
    public boolean f1308D;

    /* renamed from: E, reason: collision with root package name */
    public boolean f1309E;

    /* renamed from: F, reason: collision with root package name */
    public boolean f1310F;

    /* renamed from: G, reason: collision with root package name */
    public boolean f1311G;

    /* renamed from: H, reason: collision with root package name */
    public boolean f1312H;

    /* renamed from: I, reason: collision with root package name */
    public boolean f1313I;

    /* renamed from: J, reason: collision with root package name */
    public boolean f1314J;

    /* renamed from: K, reason: collision with root package name */
    public boolean f1315K;

    /* renamed from: L, reason: collision with root package name */
    public B[] f1316L;

    /* renamed from: M, reason: collision with root package name */
    public B f1317M;

    /* renamed from: N, reason: collision with root package name */
    public boolean f1318N;

    /* renamed from: O, reason: collision with root package name */
    public boolean f1319O;

    /* renamed from: P, reason: collision with root package name */
    public boolean f1320P;

    /* renamed from: Q, reason: collision with root package name */
    public boolean f1321Q;

    /* renamed from: R, reason: collision with root package name */
    public Configuration f1322R;

    /* renamed from: S, reason: collision with root package name */
    public final int f1323S;

    /* renamed from: T, reason: collision with root package name */
    public int f1324T;

    /* renamed from: U, reason: collision with root package name */
    public int f1325U;

    /* renamed from: V, reason: collision with root package name */
    public boolean f1326V;

    /* renamed from: W, reason: collision with root package name */
    public x f1327W;

    /* renamed from: X, reason: collision with root package name */
    public x f1328X;

    /* renamed from: Y, reason: collision with root package name */
    public boolean f1329Y;

    /* renamed from: Z, reason: collision with root package name */
    public int f1330Z;

    /* renamed from: b0, reason: collision with root package name */
    public boolean f1332b0;

    /* renamed from: c0, reason: collision with root package name */
    public Rect f1333c0;

    /* renamed from: d0, reason: collision with root package name */
    public Rect f1334d0;

    /* renamed from: e0, reason: collision with root package name */
    public F f1335e0;

    /* renamed from: f0, reason: collision with root package name */
    public OnBackInvokedDispatcher f1336f0;
    public OnBackInvokedCallback g0;

    /* renamed from: j, reason: collision with root package name */
    public final Object f1337j;

    /* renamed from: k, reason: collision with root package name */
    public final Context f1338k;

    /* renamed from: l, reason: collision with root package name */
    public Window f1339l;

    /* renamed from: m, reason: collision with root package name */
    public w f1340m;

    /* renamed from: n, reason: collision with root package name */
    public final Object f1341n;

    /* renamed from: o, reason: collision with root package name */
    public AbstractC0103a f1342o;

    /* renamed from: p, reason: collision with root package name */
    public C0127i f1343p;

    /* renamed from: q, reason: collision with root package name */
    public CharSequence f1344q;

    /* renamed from: r, reason: collision with root package name */
    public InterfaceC0187l0 f1345r;

    /* renamed from: s, reason: collision with root package name */
    public r f1346s;

    /* renamed from: t, reason: collision with root package name */
    public r f1347t;

    /* renamed from: u, reason: collision with root package name */
    public AbstractC0120b f1348u;

    /* renamed from: v, reason: collision with root package name */
    public ActionBarContextView f1349v;

    /* renamed from: w, reason: collision with root package name */
    public PopupWindow f1350w;

    /* renamed from: x, reason: collision with root package name */
    public q f1351x;

    /* renamed from: z, reason: collision with root package name */
    public boolean f1352z;
    public S y = null;

    /* renamed from: a0, reason: collision with root package name */
    public final q f1331a0 = new q(this, 0);

    public C(Context context, Window window, InterfaceC0113k interfaceC0113k, Object obj) {
        AbstractActivityC0112j abstractActivityC0112j = null;
        this.f1323S = -100;
        this.f1338k = context;
        this.f1341n = interfaceC0113k;
        this.f1337j = obj;
        if (obj instanceof Dialog) {
            while (true) {
                if (context != null) {
                    if (context instanceof AbstractActivityC0112j) {
                        abstractActivityC0112j = (AbstractActivityC0112j) context;
                        break;
                    } else if (!(context instanceof ContextWrapper)) {
                        break;
                    } else {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                } else {
                    break;
                }
            }
            if (abstractActivityC0112j != null) {
                this.f1323S = ((C) abstractActivityC0112j.getDelegate()).f1323S;
            }
        }
        if (this.f1323S == -100) {
            n.k kVar = h0;
            Integer num = (Integer) kVar.get(this.f1337j.getClass().getName());
            if (num != null) {
                this.f1323S = num.intValue();
                kVar.remove(this.f1337j.getClass().getName());
            }
        }
        if (window != null) {
            p(window);
        }
        C0203u.c();
    }

    public static D.e q(Context context) {
        D.e eVar;
        D.e eVar2;
        Locale locale;
        if (Build.VERSION.SDK_INT >= 33 || (eVar = AbstractC0118p.f1460c) == null) {
            return null;
        }
        D.e b2 = u.b(context.getApplicationContext().getResources().getConfiguration());
        D.f fVar = eVar.f240a;
        if (fVar.f241a.isEmpty()) {
            eVar2 = D.e.f239b;
        } else {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (int i2 = 0; i2 < b2.f240a.f241a.size() + fVar.f241a.size(); i2++) {
                if (i2 < fVar.f241a.size()) {
                    locale = fVar.f241a.get(i2);
                } else {
                    locale = b2.f240a.f241a.get(i2 - fVar.f241a.size());
                }
                if (locale != null) {
                    linkedHashSet.add(locale);
                }
            }
            eVar2 = new D.e(new D.f(new LocaleList((Locale[]) linkedHashSet.toArray(new Locale[linkedHashSet.size()]))));
        }
        if (!eVar2.f240a.f241a.isEmpty()) {
            return eVar2;
        }
        return b2;
    }

    public static Configuration u(Context context, int i2, D.e eVar, Configuration configuration, boolean z2) {
        int i3;
        if (i2 != 1) {
            if (i2 != 2) {
                if (z2) {
                    i3 = 0;
                } else {
                    i3 = context.getApplicationContext().getResources().getConfiguration().uiMode & 48;
                }
            } else {
                i3 = 32;
            }
        } else {
            i3 = 16;
        }
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i3 | (configuration2.uiMode & (-49));
        if (eVar != null) {
            u.d(configuration2, eVar);
        }
        return configuration2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0006, code lost:
    
        if (r2 <= r5) goto L6;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, g.B] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final g.B A(int r5) {
        /*
            r4 = this;
            g.B[] r0 = r4.f1316L
            r1 = 0
            if (r0 == 0) goto L8
            int r2 = r0.length
            if (r2 > r5) goto L15
        L8:
            int r2 = r5 + 1
            g.B[] r2 = new g.B[r2]
            if (r0 == 0) goto L12
            int r3 = r0.length
            java.lang.System.arraycopy(r0, r1, r2, r1, r3)
        L12:
            r4.f1316L = r2
            r0 = r2
        L15:
            r2 = r0[r5]
            if (r2 != 0) goto L24
            g.B r2 = new g.B
            r2.<init>()
            r2.f1288a = r5
            r2.f1300n = r1
            r0[r5] = r2
        L24:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: g.C.A(int):g.B");
    }

    public final void B() {
        x();
        if (this.f1310F && this.f1342o == null) {
            Object obj = this.f1337j;
            if (obj instanceof Activity) {
                this.f1342o = new P((Activity) obj, this.f1311G);
            } else if (obj instanceof Dialog) {
                this.f1342o = new P((Dialog) obj);
            }
            AbstractC0103a abstractC0103a = this.f1342o;
            if (abstractC0103a != null) {
                abstractC0103a.l(this.f1332b0);
            }
        }
    }

    public final void C(int i2) {
        this.f1330Z = (1 << i2) | this.f1330Z;
        if (!this.f1329Y) {
            View decorView = this.f1339l.getDecorView();
            q qVar = this.f1331a0;
            WeakHashMap weakHashMap = H.N.f327a;
            decorView.postOnAnimation(qVar);
            this.f1329Y = true;
        }
    }

    public final int D(Context context, int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 != -1) {
            if (i2 != 0) {
                if (i2 != 1 && i2 != 2) {
                    if (i2 == 3) {
                        if (this.f1328X == null) {
                            this.f1328X = new x(this, context);
                        }
                        return this.f1328X.e();
                    }
                    throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                }
            } else {
                if (((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() == 0) {
                    return -1;
                }
                return z(context).e();
            }
        }
        return i2;
    }

    public final boolean E() {
        boolean z2 = this.f1318N;
        this.f1318N = false;
        B A2 = A(0);
        if (A2.f1299m) {
            if (!z2) {
                t(A2, true);
            }
            return true;
        }
        AbstractC0120b abstractC0120b = this.f1348u;
        if (abstractC0120b != null) {
            abstractC0120b.a();
            return true;
        }
        B();
        AbstractC0103a abstractC0103a = this.f1342o;
        if (abstractC0103a == null || !abstractC0103a.b()) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0173, code lost:
    
        if (r3.f1651f.getCount() > 0) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0153, code lost:
    
        if (r3 != null) goto L77;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void F(g.B r18, android.view.KeyEvent r19) {
        /*
            Method dump skipped, instructions count: 472
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: g.C.F(g.B, android.view.KeyEvent):void");
    }

    public final boolean G(B b2, int i2, KeyEvent keyEvent) {
        j.n nVar;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((!b2.f1297k && !H(b2, keyEvent)) || (nVar = b2.h) == null) {
            return false;
        }
        return nVar.performShortcut(i2, keyEvent, 1);
    }

    public final boolean H(B b2, KeyEvent keyEvent) {
        boolean z2;
        InterfaceC0187l0 interfaceC0187l0;
        InterfaceC0187l0 interfaceC0187l02;
        Resources.Theme theme;
        int i2;
        boolean z3;
        InterfaceC0187l0 interfaceC0187l03;
        InterfaceC0187l0 interfaceC0187l04;
        if (this.f1321Q) {
            return false;
        }
        if (b2.f1297k) {
            return true;
        }
        B b3 = this.f1317M;
        if (b3 != null && b3 != b2) {
            t(b3, false);
        }
        Window.Callback callback = this.f1339l.getCallback();
        int i3 = b2.f1288a;
        if (callback != null) {
            b2.f1294g = callback.onCreatePanelView(i3);
        }
        if (i3 != 0 && i3 != 108) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z2 && (interfaceC0187l04 = this.f1345r) != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) interfaceC0187l04;
            actionBarOverlayLayout.k();
            ((e1) actionBarOverlayLayout.f860e).f1905l = true;
        }
        if (b2.f1294g == null && (!z2 || !(this.f1342o instanceof K))) {
            j.n nVar = b2.h;
            if (nVar == null || b2.f1301o) {
                if (nVar == null) {
                    Context context = this.f1338k;
                    if ((i3 == 0 || i3 == 108) && this.f1345r != null) {
                        TypedValue typedValue = new TypedValue();
                        Resources.Theme theme2 = context.getTheme();
                        theme2.resolveAttribute(org.conscrypt.R.attr.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            theme = context.getResources().newTheme();
                            theme.setTo(theme2);
                            theme.applyStyle(typedValue.resourceId, true);
                            theme.resolveAttribute(org.conscrypt.R.attr.actionBarWidgetTheme, typedValue, true);
                        } else {
                            theme2.resolveAttribute(org.conscrypt.R.attr.actionBarWidgetTheme, typedValue, true);
                            theme = null;
                        }
                        if (typedValue.resourceId != 0) {
                            if (theme == null) {
                                theme = context.getResources().newTheme();
                                theme.setTo(theme2);
                            }
                            theme.applyStyle(typedValue.resourceId, true);
                        }
                        if (theme != null) {
                            C0122d c0122d = new C0122d(context, 0);
                            c0122d.getTheme().setTo(theme);
                            context = c0122d;
                        }
                    }
                    j.n nVar2 = new j.n(context);
                    nVar2.f1662e = this;
                    j.n nVar3 = b2.h;
                    if (nVar2 != nVar3) {
                        if (nVar3 != null) {
                            nVar3.r(b2.f1295i);
                        }
                        b2.h = nVar2;
                        j.j jVar = b2.f1295i;
                        if (jVar != null) {
                            nVar2.b(jVar, nVar2.f1658a);
                        }
                    }
                    if (b2.h == null) {
                        return false;
                    }
                }
                if (z2 && (interfaceC0187l02 = this.f1345r) != null) {
                    if (this.f1346s == null) {
                        this.f1346s = new r(this, 2);
                    }
                    ((ActionBarOverlayLayout) interfaceC0187l02).l(b2.h, this.f1346s);
                }
                b2.h.w();
                if (!callback.onCreatePanelMenu(i3, b2.h)) {
                    j.n nVar4 = b2.h;
                    if (nVar4 != null) {
                        if (nVar4 != null) {
                            nVar4.r(b2.f1295i);
                        }
                        b2.h = null;
                    }
                    if (z2 && (interfaceC0187l0 = this.f1345r) != null) {
                        ((ActionBarOverlayLayout) interfaceC0187l0).l(null, this.f1346s);
                    }
                    return false;
                }
                b2.f1301o = false;
            }
            b2.h.w();
            Bundle bundle = b2.f1302p;
            if (bundle != null) {
                b2.h.s(bundle);
                b2.f1302p = null;
            }
            if (!callback.onPreparePanel(0, b2.f1294g, b2.h)) {
                if (z2 && (interfaceC0187l03 = this.f1345r) != null) {
                    ((ActionBarOverlayLayout) interfaceC0187l03).l(null, this.f1346s);
                }
                b2.h.v();
                return false;
            }
            if (keyEvent != null) {
                i2 = keyEvent.getDeviceId();
            } else {
                i2 = -1;
            }
            if (KeyCharacterMap.load(i2).getKeyboardType() != 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            b2.h.setQwertyMode(z3);
            b2.h.v();
        }
        b2.f1297k = true;
        b2.f1298l = false;
        this.f1317M = b2;
        return true;
    }

    public final void I() {
        if (!this.f1352z) {
        } else {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final void J() {
        OnBackInvokedCallback onBackInvokedCallback;
        if (Build.VERSION.SDK_INT >= 33) {
            boolean z2 = false;
            if (this.f1336f0 != null && (A(0).f1299m || this.f1348u != null)) {
                z2 = true;
            }
            if (z2 && this.g0 == null) {
                this.g0 = v.b(this.f1336f0, this);
            } else if (!z2 && (onBackInvokedCallback = this.g0) != null) {
                v.c(this.f1336f0, onBackInvokedCallback);
                this.g0 = null;
            }
        }
    }

    @Override // g.AbstractC0118p
    public final void a() {
        LayoutInflater from = LayoutInflater.from(this.f1338k);
        if (from.getFactory() == null) {
            from.setFactory2(this);
        } else if (!(from.getFactory2() instanceof C)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    @Override // g.AbstractC0118p
    public final void b() {
        if (this.f1342o != null) {
            B();
            if (!this.f1342o.f()) {
                C(0);
            }
        }
    }

    @Override // g.AbstractC0118p
    public final void d() {
        String str;
        this.f1319O = true;
        o(false, true);
        y();
        Object obj = this.f1337j;
        if (obj instanceof Activity) {
            try {
                Activity activity = (Activity) obj;
                try {
                    str = x.c.c(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e2) {
                    throw new IllegalArgumentException(e2);
                }
            } catch (IllegalArgumentException unused) {
                str = null;
            }
            if (str != null) {
                AbstractC0103a abstractC0103a = this.f1342o;
                if (abstractC0103a == null) {
                    this.f1332b0 = true;
                } else {
                    abstractC0103a.l(true);
                }
            }
            synchronized (AbstractC0118p.h) {
                AbstractC0118p.f(this);
                AbstractC0118p.f1464g.add(new WeakReference(this));
            }
        }
        this.f1322R = new Configuration(this.f1338k.getResources().getConfiguration());
        this.f1320P = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    @Override // g.AbstractC0118p
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f1337j
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L11
            java.lang.Object r0 = g.AbstractC0118p.h
            monitor-enter(r0)
            g.AbstractC0118p.f(r3)     // Catch: java.lang.Throwable -> Le
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            goto L11
        Le:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            throw r1
        L11:
            boolean r0 = r3.f1329Y
            if (r0 == 0) goto L20
            android.view.Window r0 = r3.f1339l
            android.view.View r0 = r0.getDecorView()
            g.q r1 = r3.f1331a0
            r0.removeCallbacks(r1)
        L20:
            r0 = 1
            r3.f1321Q = r0
            int r0 = r3.f1323S
            r1 = -100
            if (r0 == r1) goto L4d
            java.lang.Object r0 = r3.f1337j
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L4d
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L4d
            n.k r0 = g.C.h0
            java.lang.Object r1 = r3.f1337j
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.f1323S
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L5c
        L4d:
            n.k r0 = g.C.h0
            java.lang.Object r1 = r3.f1337j
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L5c:
            g.a r0 = r3.f1342o
            if (r0 == 0) goto L63
            r0.h()
        L63:
            g.x r0 = r3.f1327W
            if (r0 == 0) goto L6a
            r0.c()
        L6a:
            g.x r0 = r3.f1328X
            if (r0 == 0) goto L71
            r0.c()
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: g.C.e():void");
    }

    @Override // g.AbstractC0118p
    public final boolean g(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            i2 = 108;
        } else if (i2 == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            i2 = 109;
        }
        if (this.f1314J && i2 == 108) {
            return false;
        }
        if (this.f1310F && i2 == 1) {
            this.f1310F = false;
        }
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 5) {
                    if (i2 != 10) {
                        if (i2 != 108) {
                            if (i2 != 109) {
                                return this.f1339l.requestFeature(i2);
                            }
                            I();
                            this.f1311G = true;
                            return true;
                        }
                        I();
                        this.f1310F = true;
                        return true;
                    }
                    I();
                    this.f1312H = true;
                    return true;
                }
                I();
                this.f1309E = true;
                return true;
            }
            I();
            this.f1308D = true;
            return true;
        }
        I();
        this.f1314J = true;
        return true;
    }

    @Override // g.AbstractC0118p
    public final void h(int i2) {
        x();
        ViewGroup viewGroup = (ViewGroup) this.f1305A.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f1338k).inflate(i2, viewGroup);
        this.f1340m.a(this.f1339l.getCallback());
    }

    @Override // g.AbstractC0118p
    public final void i(View view) {
        x();
        ViewGroup viewGroup = (ViewGroup) this.f1305A.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f1340m.a(this.f1339l.getCallback());
    }

    @Override // g.AbstractC0118p
    public final void j(View view, ViewGroup.LayoutParams layoutParams) {
        x();
        ViewGroup viewGroup = (ViewGroup) this.f1305A.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f1340m.a(this.f1339l.getCallback());
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0048, code lost:
    
        if (r6.i() != false) goto L20;
     */
    @Override // j.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k(j.n r6) {
        /*
            r5 = this;
            k.l0 r6 = r5.f1345r
            r0 = 1
            r1 = 0
            if (r6 == 0) goto Ld3
            androidx.appcompat.widget.ActionBarOverlayLayout r6 = (androidx.appcompat.widget.ActionBarOverlayLayout) r6
            r6.k()
            k.m0 r6 = r6.f860e
            k.e1 r6 = (k.e1) r6
            androidx.appcompat.widget.Toolbar r6 = r6.f1895a
            int r2 = r6.getVisibility()
            if (r2 != 0) goto Ld3
            androidx.appcompat.widget.ActionMenuView r6 = r6.f925a
            if (r6 == 0) goto Ld3
            boolean r6 = r6.f884s
            if (r6 == 0) goto Ld3
            android.content.Context r6 = r5.f1338k
            android.view.ViewConfiguration r6 = android.view.ViewConfiguration.get(r6)
            boolean r6 = r6.hasPermanentMenuKey()
            if (r6 == 0) goto L4a
            k.l0 r6 = r5.f1345r
            androidx.appcompat.widget.ActionBarOverlayLayout r6 = (androidx.appcompat.widget.ActionBarOverlayLayout) r6
            r6.k()
            k.m0 r6 = r6.f860e
            k.e1 r6 = (k.e1) r6
            androidx.appcompat.widget.Toolbar r6 = r6.f1895a
            androidx.appcompat.widget.ActionMenuView r6 = r6.f925a
            if (r6 == 0) goto Ld3
            k.k r6 = r6.f885t
            if (r6 == 0) goto Ld3
            k.i r2 = r6.f1970u
            if (r2 != 0) goto L4a
            boolean r6 = r6.i()
            if (r6 == 0) goto Ld3
        L4a:
            android.view.Window r6 = r5.f1339l
            android.view.Window$Callback r6 = r6.getCallback()
            k.l0 r2 = r5.f1345r
            androidx.appcompat.widget.ActionBarOverlayLayout r2 = (androidx.appcompat.widget.ActionBarOverlayLayout) r2
            r2.k()
            k.m0 r2 = r2.f860e
            k.e1 r2 = (k.e1) r2
            androidx.appcompat.widget.Toolbar r2 = r2.f1895a
            boolean r2 = r2.o()
            r3 = 108(0x6c, float:1.51E-43)
            if (r2 == 0) goto L8c
            k.l0 r0 = r5.f1345r
            androidx.appcompat.widget.ActionBarOverlayLayout r0 = (androidx.appcompat.widget.ActionBarOverlayLayout) r0
            r0.k()
            k.m0 r0 = r0.f860e
            k.e1 r0 = (k.e1) r0
            androidx.appcompat.widget.Toolbar r0 = r0.f1895a
            androidx.appcompat.widget.ActionMenuView r0 = r0.f925a
            if (r0 == 0) goto L7e
            k.k r0 = r0.f885t
            if (r0 == 0) goto L7e
            boolean r0 = r0.e()
        L7e:
            boolean r0 = r5.f1321Q
            if (r0 != 0) goto Le0
            g.B r0 = r5.A(r1)
            j.n r0 = r0.h
            r6.onPanelClosed(r3, r0)
            goto Le0
        L8c:
            if (r6 == 0) goto Le0
            boolean r2 = r5.f1321Q
            if (r2 != 0) goto Le0
            boolean r2 = r5.f1329Y
            if (r2 == 0) goto La9
            int r2 = r5.f1330Z
            r0 = r0 & r2
            if (r0 == 0) goto La9
            android.view.Window r0 = r5.f1339l
            android.view.View r0 = r0.getDecorView()
            g.q r2 = r5.f1331a0
            r0.removeCallbacks(r2)
            r2.run()
        La9:
            g.B r0 = r5.A(r1)
            j.n r2 = r0.h
            if (r2 == 0) goto Le0
            boolean r4 = r0.f1301o
            if (r4 != 0) goto Le0
            android.view.View r4 = r0.f1294g
            boolean r1 = r6.onPreparePanel(r1, r4, r2)
            if (r1 == 0) goto Le0
            j.n r0 = r0.h
            r6.onMenuOpened(r3, r0)
            k.l0 r6 = r5.f1345r
            androidx.appcompat.widget.ActionBarOverlayLayout r6 = (androidx.appcompat.widget.ActionBarOverlayLayout) r6
            r6.k()
            k.m0 r6 = r6.f860e
            k.e1 r6 = (k.e1) r6
            androidx.appcompat.widget.Toolbar r6 = r6.f1895a
            r6.u()
            goto Le0
        Ld3:
            g.B r6 = r5.A(r1)
            r6.f1300n = r0
            r5.t(r6, r1)
            r0 = 0
            r5.F(r6, r0)
        Le0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: g.C.k(j.n):void");
    }

    @Override // g.AbstractC0118p
    public final void l(CharSequence charSequence) {
        this.f1344q = charSequence;
        InterfaceC0187l0 interfaceC0187l0 = this.f1345r;
        if (interfaceC0187l0 != null) {
            interfaceC0187l0.setWindowTitle(charSequence);
            return;
        }
        AbstractC0103a abstractC0103a = this.f1342o;
        if (abstractC0103a != null) {
            abstractC0103a.n(charSequence);
            return;
        }
        TextView textView = this.f1306B;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    @Override // j.l
    public final boolean m(j.n nVar, MenuItem menuItem) {
        int i2;
        B b2;
        Window.Callback callback = this.f1339l.getCallback();
        if (callback != null && !this.f1321Q) {
            j.n k2 = nVar.k();
            B[] bArr = this.f1316L;
            if (bArr != null) {
                i2 = bArr.length;
            } else {
                i2 = 0;
            }
            int i3 = 0;
            while (true) {
                if (i3 < i2) {
                    b2 = bArr[i3];
                    if (b2 != null && b2.h == k2) {
                        break;
                    }
                    i3++;
                } else {
                    b2 = null;
                    break;
                }
            }
            if (b2 != null) {
                return callback.onMenuItemSelected(b2.f1288a, menuItem);
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0048  */
    /* JADX WARN: Type inference failed for: r3v1, types: [g.k, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v14, types: [i.e, j.l, java.lang.Object, i.b] */
    @Override // g.AbstractC0118p
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final i.AbstractC0120b n(i.InterfaceC0119a r9) {
        /*
            Method dump skipped, instructions count: 448
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: g.C.n(i.a):i.b");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ff A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x019d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean o(boolean r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 650
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: g.C.o(boolean, boolean):boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0115, code lost:
    
        if (r2.equals("ImageButton") == false) goto L24;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r8v3 */
    @Override // android.view.LayoutInflater.Factory2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View onCreateView(android.view.View r18, java.lang.String r19, android.content.Context r20, android.util.AttributeSet r21) {
        /*
            Method dump skipped, instructions count: 748
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: g.C.onCreateView(android.view.View, java.lang.String, android.content.Context, android.util.AttributeSet):android.view.View");
    }

    public final void p(Window window) {
        Drawable drawable;
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        OnBackInvokedCallback onBackInvokedCallback;
        int resourceId;
        if (this.f1339l == null) {
            Window.Callback callback = window.getCallback();
            if (!(callback instanceof w)) {
                w wVar = new w(this, callback);
                this.f1340m = wVar;
                window.setCallback(wVar);
                int[] iArr = f1303i0;
                Context context = this.f1338k;
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, iArr);
                if (obtainStyledAttributes.hasValue(0) && (resourceId = obtainStyledAttributes.getResourceId(0, 0)) != 0) {
                    C0203u a2 = C0203u.a();
                    synchronized (a2) {
                        drawable = a2.f2031a.d(context, resourceId, true);
                    }
                } else {
                    drawable = null;
                }
                if (drawable != null) {
                    window.setBackgroundDrawable(drawable);
                }
                obtainStyledAttributes.recycle();
                this.f1339l = window;
                if (Build.VERSION.SDK_INT >= 33 && (onBackInvokedDispatcher = this.f1336f0) == null) {
                    if (onBackInvokedDispatcher != null && (onBackInvokedCallback = this.g0) != null) {
                        v.c(onBackInvokedDispatcher, onBackInvokedCallback);
                        this.g0 = null;
                    }
                    Object obj = this.f1337j;
                    if (obj instanceof Activity) {
                        Activity activity = (Activity) obj;
                        if (activity.getWindow() != null) {
                            this.f1336f0 = v.a(activity);
                            J();
                            return;
                        }
                    }
                    this.f1336f0 = null;
                    J();
                    return;
                }
                return;
            }
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    public final void r(int i2, B b2, j.n nVar) {
        if (nVar == null) {
            if (b2 == null && i2 >= 0) {
                B[] bArr = this.f1316L;
                if (i2 < bArr.length) {
                    b2 = bArr[i2];
                }
            }
            if (b2 != null) {
                nVar = b2.h;
            }
        }
        if ((b2 == null || b2.f1299m) && !this.f1321Q) {
            w wVar = this.f1340m;
            Window.Callback callback = this.f1339l.getCallback();
            wVar.getClass();
            try {
                wVar.f1476e = true;
                callback.onPanelClosed(i2, nVar);
            } finally {
                wVar.f1476e = false;
            }
        }
    }

    public final void s(j.n nVar) {
        C0184k c0184k;
        if (this.f1315K) {
            return;
        }
        this.f1315K = true;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.f1345r;
        actionBarOverlayLayout.k();
        ActionMenuView actionMenuView = ((e1) actionBarOverlayLayout.f860e).f1895a.f925a;
        if (actionMenuView != null && (c0184k = actionMenuView.f885t) != null) {
            c0184k.e();
            C0176g c0176g = c0184k.f1969t;
            if (c0176g != null && c0176g.b()) {
                c0176g.f1730i.dismiss();
            }
        }
        Window.Callback callback = this.f1339l.getCallback();
        if (callback != null && !this.f1321Q) {
            callback.onPanelClosed(108, nVar);
        }
        this.f1315K = false;
    }

    public final void t(B b2, boolean z2) {
        C0102A c0102a;
        InterfaceC0187l0 interfaceC0187l0;
        if (z2 && b2.f1288a == 0 && (interfaceC0187l0 = this.f1345r) != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) interfaceC0187l0;
            actionBarOverlayLayout.k();
            if (((e1) actionBarOverlayLayout.f860e).f1895a.o()) {
                s(b2.h);
                return;
            }
        }
        WindowManager windowManager = (WindowManager) this.f1338k.getSystemService("window");
        if (windowManager != null && b2.f1299m && (c0102a = b2.f1292e) != null) {
            windowManager.removeView(c0102a);
            if (z2) {
                r(b2.f1288a, b2, null);
            }
        }
        b2.f1297k = false;
        b2.f1298l = false;
        b2.f1299m = false;
        b2.f1293f = null;
        b2.f1300n = true;
        if (this.f1317M == b2) {
            this.f1317M = null;
        }
        if (b2.f1288a == 0) {
            J();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x00ef, code lost:
    
        if (r7.e() != false) goto L80;
     */
    /* JADX WARN: Removed duplicated region for block: B:58:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean v(android.view.KeyEvent r7) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: g.C.v(android.view.KeyEvent):boolean");
    }

    public final void w(int i2) {
        B A2 = A(i2);
        if (A2.h != null) {
            Bundle bundle = new Bundle();
            A2.h.t(bundle);
            if (bundle.size() > 0) {
                A2.f1302p = bundle;
            }
            A2.h.w();
            A2.h.clear();
        }
        A2.f1301o = true;
        A2.f1300n = true;
        if ((i2 == 108 || i2 == 0) && this.f1345r != null) {
            B A3 = A(0);
            A3.f1297k = false;
            H(A3, null);
        }
    }

    public final void x() {
        ViewGroup viewGroup;
        CharSequence charSequence;
        Context context;
        int i2 = 1;
        int i3 = 0;
        if (!this.f1352z) {
            int[] iArr = AbstractC0101a.f1270j;
            Context context2 = this.f1338k;
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(iArr);
            if (obtainStyledAttributes.hasValue(117)) {
                if (obtainStyledAttributes.getBoolean(126, false)) {
                    g(1);
                } else if (obtainStyledAttributes.getBoolean(117, false)) {
                    g(108);
                }
                if (obtainStyledAttributes.getBoolean(118, false)) {
                    g(109);
                }
                if (obtainStyledAttributes.getBoolean(119, false)) {
                    g(10);
                }
                this.f1313I = obtainStyledAttributes.getBoolean(0, false);
                obtainStyledAttributes.recycle();
                y();
                this.f1339l.getDecorView();
                LayoutInflater from = LayoutInflater.from(context2);
                if (!this.f1314J) {
                    if (this.f1313I) {
                        viewGroup = (ViewGroup) from.inflate(org.conscrypt.R.layout.abc_dialog_title_material, (ViewGroup) null);
                        this.f1311G = false;
                        this.f1310F = false;
                    } else if (this.f1310F) {
                        TypedValue typedValue = new TypedValue();
                        context2.getTheme().resolveAttribute(org.conscrypt.R.attr.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            context = new C0122d(context2, typedValue.resourceId);
                        } else {
                            context = context2;
                        }
                        viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(org.conscrypt.R.layout.abc_screen_toolbar, (ViewGroup) null);
                        InterfaceC0187l0 interfaceC0187l0 = (InterfaceC0187l0) viewGroup.findViewById(org.conscrypt.R.id.decor_content_parent);
                        this.f1345r = interfaceC0187l0;
                        interfaceC0187l0.setWindowCallback(this.f1339l.getCallback());
                        if (this.f1311G) {
                            ((ActionBarOverlayLayout) this.f1345r).j(109);
                        }
                        if (this.f1308D) {
                            ((ActionBarOverlayLayout) this.f1345r).j(2);
                        }
                        if (this.f1309E) {
                            ((ActionBarOverlayLayout) this.f1345r).j(5);
                        }
                    } else {
                        viewGroup = null;
                    }
                } else {
                    viewGroup = this.f1312H ? (ViewGroup) from.inflate(org.conscrypt.R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) from.inflate(org.conscrypt.R.layout.abc_screen_simple, (ViewGroup) null);
                }
                if (viewGroup != null) {
                    r rVar = new r(this, i3);
                    WeakHashMap weakHashMap = H.N.f327a;
                    H.G.h(viewGroup, rVar);
                    if (this.f1345r == null) {
                        this.f1306B = (TextView) viewGroup.findViewById(org.conscrypt.R.id.title);
                    }
                    boolean z2 = m1.f1979a;
                    try {
                        Method method = viewGroup.getClass().getMethod("makeOptionalFitsSystemWindows", null);
                        if (!method.isAccessible()) {
                            method.setAccessible(true);
                        }
                        method.invoke(viewGroup, null);
                    } catch (IllegalAccessException e2) {
                        Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e2);
                    } catch (NoSuchMethodException unused) {
                        Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
                    } catch (InvocationTargetException e3) {
                        Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e3);
                    }
                    ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(org.conscrypt.R.id.action_bar_activity_content);
                    ViewGroup viewGroup2 = (ViewGroup) this.f1339l.findViewById(R.id.content);
                    if (viewGroup2 != null) {
                        while (viewGroup2.getChildCount() > 0) {
                            View childAt = viewGroup2.getChildAt(0);
                            viewGroup2.removeViewAt(0);
                            contentFrameLayout.addView(childAt);
                        }
                        viewGroup2.setId(-1);
                        contentFrameLayout.setId(R.id.content);
                        if (viewGroup2 instanceof FrameLayout) {
                            ((FrameLayout) viewGroup2).setForeground(null);
                        }
                    }
                    this.f1339l.setContentView(viewGroup);
                    contentFrameLayout.setAttachListener(new r(this, i2));
                    this.f1305A = viewGroup;
                    Object obj = this.f1337j;
                    if (obj instanceof Activity) {
                        charSequence = ((Activity) obj).getTitle();
                    } else {
                        charSequence = this.f1344q;
                    }
                    if (!TextUtils.isEmpty(charSequence)) {
                        InterfaceC0187l0 interfaceC0187l02 = this.f1345r;
                        if (interfaceC0187l02 != null) {
                            interfaceC0187l02.setWindowTitle(charSequence);
                        } else {
                            AbstractC0103a abstractC0103a = this.f1342o;
                            if (abstractC0103a != null) {
                                abstractC0103a.n(charSequence);
                            } else {
                                TextView textView = this.f1306B;
                                if (textView != null) {
                                    textView.setText(charSequence);
                                }
                            }
                        }
                    }
                    ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.f1305A.findViewById(R.id.content);
                    View decorView = this.f1339l.getDecorView();
                    contentFrameLayout2.f901g.set(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
                    if (contentFrameLayout2.isLaidOut()) {
                        contentFrameLayout2.requestLayout();
                    }
                    TypedArray obtainStyledAttributes2 = context2.obtainStyledAttributes(iArr);
                    obtainStyledAttributes2.getValue(124, contentFrameLayout2.getMinWidthMajor());
                    obtainStyledAttributes2.getValue(125, contentFrameLayout2.getMinWidthMinor());
                    if (obtainStyledAttributes2.hasValue(122)) {
                        obtainStyledAttributes2.getValue(122, contentFrameLayout2.getFixedWidthMajor());
                    }
                    if (obtainStyledAttributes2.hasValue(123)) {
                        obtainStyledAttributes2.getValue(123, contentFrameLayout2.getFixedWidthMinor());
                    }
                    if (obtainStyledAttributes2.hasValue(120)) {
                        obtainStyledAttributes2.getValue(120, contentFrameLayout2.getFixedHeightMajor());
                    }
                    if (obtainStyledAttributes2.hasValue(121)) {
                        obtainStyledAttributes2.getValue(121, contentFrameLayout2.getFixedHeightMinor());
                    }
                    obtainStyledAttributes2.recycle();
                    contentFrameLayout2.requestLayout();
                    this.f1352z = true;
                    B A2 = A(0);
                    if (!this.f1321Q && A2.h == null) {
                        C(108);
                        return;
                    }
                    return;
                }
                throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.f1310F + ", windowActionBarOverlay: " + this.f1311G + ", android:windowIsFloating: " + this.f1313I + ", windowActionModeOverlay: " + this.f1312H + ", windowNoTitle: " + this.f1314J + " }");
            }
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
    }

    public final void y() {
        if (this.f1339l == null) {
            Object obj = this.f1337j;
            if (obj instanceof Activity) {
                p(((Activity) obj).getWindow());
            }
        }
        if (this.f1339l != null) {
        } else {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public final z z(Context context) {
        if (this.f1327W == null) {
            if (D0.h.f256e == null) {
                Context applicationContext = context.getApplicationContext();
                D0.h.f256e = new D0.h(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            }
            this.f1327W = new x(this, D0.h.f256e);
        }
        return this.f1327W;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}

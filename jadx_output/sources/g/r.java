package g;

import H.InterfaceC0027v;
import H.U;
import H.V;
import H.W;
import H.X;
import H.e0;
import H.f0;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import k.InterfaceC0185k0;
import k.l1;
import k.m1;
import org.conscrypt.R;

/* loaded from: classes.dex */
public final class r implements InterfaceC0027v, InterfaceC0185k0, j.y {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1468a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C f1469b;

    public /* synthetic */ r(C c2, int i2) {
        this.f1468a = i2;
        this.f1469b = c2;
    }

    @Override // j.y
    public void a(j.n nVar, boolean z2) {
        boolean z3;
        int i2;
        B b2;
        switch (this.f1468a) {
            case 2:
                this.f1469b.s(nVar);
                return;
            default:
                j.n k2 = nVar.k();
                int i3 = 0;
                if (k2 != nVar) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    nVar = k2;
                }
                C c2 = this.f1469b;
                B[] bArr = c2.f1316L;
                if (bArr != null) {
                    i2 = bArr.length;
                } else {
                    i2 = 0;
                }
                while (true) {
                    if (i3 < i2) {
                        b2 = bArr[i3];
                        if (b2 == null || b2.h != nVar) {
                            i3++;
                        }
                    } else {
                        b2 = null;
                    }
                }
                if (b2 != null) {
                    if (z3) {
                        c2.r(b2.f1288a, b2, k2);
                        c2.t(b2, true);
                        return;
                    } else {
                        c2.t(b2, z2);
                        return;
                    }
                }
                return;
        }
    }

    public f0 b(View view, f0 f0Var) {
        int i2;
        boolean z2;
        f0 f0Var2;
        X u2;
        int i3;
        boolean z3;
        int i4;
        int i5;
        boolean z4;
        boolean z5;
        int color;
        e0 e0Var = f0Var.f366a;
        int i6 = e0Var.g().f3b;
        C c2 = this.f1469b;
        c2.getClass();
        int i7 = e0Var.g().f3b;
        ActionBarContextView actionBarContextView = c2.f1349v;
        if (actionBarContextView != null && (actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) c2.f1349v.getLayoutParams();
            if (c2.f1349v.isShown()) {
                if (c2.f1333c0 == null) {
                    c2.f1333c0 = new Rect();
                    c2.f1334d0 = new Rect();
                }
                Rect rect = c2.f1333c0;
                Rect rect2 = c2.f1334d0;
                rect.set(e0Var.g().f2a, e0Var.g().f3b, e0Var.g().f4c, e0Var.g().f5d);
                ViewGroup viewGroup = c2.f1305A;
                if (Build.VERSION.SDK_INT >= 29) {
                    boolean z6 = m1.f1979a;
                    l1.a(viewGroup, rect, rect2);
                } else {
                    if (!m1.f1979a) {
                        m1.f1979a = true;
                        try {
                            Method declaredMethod = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
                            m1.f1980b = declaredMethod;
                            if (!declaredMethod.isAccessible()) {
                                m1.f1980b.setAccessible(true);
                            }
                        } catch (NoSuchMethodException unused) {
                            Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
                        }
                    }
                    Method method = m1.f1980b;
                    if (method != null) {
                        try {
                            method.invoke(viewGroup, rect, rect2);
                        } catch (Exception e2) {
                            Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", e2);
                        }
                    }
                }
                int i8 = rect.top;
                int i9 = rect.left;
                int i10 = rect.right;
                ViewGroup viewGroup2 = c2.f1305A;
                WeakHashMap weakHashMap = H.N.f327a;
                f0 a2 = H.H.a(viewGroup2);
                if (a2 == null) {
                    i4 = 0;
                } else {
                    i4 = a2.f366a.g().f2a;
                }
                if (a2 == null) {
                    i5 = 0;
                } else {
                    i5 = a2.f366a.g().f4c;
                }
                if (marginLayoutParams.topMargin == i8 && marginLayoutParams.leftMargin == i9 && marginLayoutParams.rightMargin == i10) {
                    z4 = false;
                } else {
                    marginLayoutParams.topMargin = i8;
                    marginLayoutParams.leftMargin = i9;
                    marginLayoutParams.rightMargin = i10;
                    z4 = true;
                }
                Context context = c2.f1338k;
                if (i8 > 0 && c2.f1307C == null) {
                    View view2 = new View(context);
                    c2.f1307C = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = i4;
                    layoutParams.rightMargin = i5;
                    c2.f1305A.addView(c2.f1307C, -1, layoutParams);
                } else {
                    View view3 = c2.f1307C;
                    if (view3 != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view3.getLayoutParams();
                        int i11 = marginLayoutParams2.height;
                        int i12 = marginLayoutParams.topMargin;
                        if (i11 != i12 || marginLayoutParams2.leftMargin != i4 || marginLayoutParams2.rightMargin != i5) {
                            marginLayoutParams2.height = i12;
                            marginLayoutParams2.leftMargin = i4;
                            marginLayoutParams2.rightMargin = i5;
                            c2.f1307C.setLayoutParams(marginLayoutParams2);
                        }
                    }
                }
                View view4 = c2.f1307C;
                if (view4 != null) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (z5 && view4.getVisibility() != 0) {
                    View view5 = c2.f1307C;
                    if ((view5.getWindowSystemUiVisibility() & 8192) != 0) {
                        color = context.getColor(R.color.abc_decor_view_status_guard_light);
                    } else {
                        color = context.getColor(R.color.abc_decor_view_status_guard);
                    }
                    view5.setBackgroundColor(color);
                }
                if (!c2.f1312H && z5) {
                    i7 = 0;
                }
                z3 = z4;
                z2 = z5;
                i2 = 0;
            } else {
                i2 = 0;
                if (marginLayoutParams.topMargin != 0) {
                    marginLayoutParams.topMargin = 0;
                    z2 = false;
                    z3 = true;
                } else {
                    z2 = false;
                    z3 = false;
                }
            }
            if (z3) {
                c2.f1349v.setLayoutParams(marginLayoutParams);
            }
        } else {
            i2 = 0;
            z2 = false;
        }
        View view6 = c2.f1307C;
        if (view6 != null) {
            if (z2) {
                i3 = i2;
            } else {
                i3 = 8;
            }
            view6.setVisibility(i3);
        }
        if (i6 != i7) {
            int i13 = e0Var.g().f2a;
            int i14 = e0Var.g().f4c;
            int i15 = e0Var.g().f5d;
            int i16 = Build.VERSION.SDK_INT;
            if (i16 >= 30) {
                u2 = new W(f0Var);
            } else if (i16 >= 29) {
                u2 = new V(f0Var);
            } else {
                u2 = new U(f0Var);
            }
            u2.d(A.c.a(i13, i7, i14, i15));
            f0Var2 = u2.b();
        } else {
            f0Var2 = f0Var;
        }
        WeakHashMap weakHashMap2 = H.N.f327a;
        WindowInsets b2 = f0Var2.b();
        if (b2 != null) {
            WindowInsets b3 = H.E.b(view, b2);
            if (!b3.equals(b2)) {
                return f0.c(b3, view);
            }
            return f0Var2;
        }
        return f0Var2;
    }

    @Override // j.y
    public boolean c(j.n nVar) {
        Window.Callback callback;
        switch (this.f1468a) {
            case 2:
                Window.Callback callback2 = this.f1469b.f1339l.getCallback();
                if (callback2 != null) {
                    callback2.onMenuOpened(108, nVar);
                    return true;
                }
                return true;
            default:
                if (nVar == nVar.k()) {
                    C c2 = this.f1469b;
                    if (c2.f1310F && (callback = c2.f1339l.getCallback()) != null && !c2.f1321Q) {
                        callback.onMenuOpened(108, nVar);
                        return true;
                    }
                    return true;
                }
                return true;
        }
    }
}

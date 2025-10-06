package g;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.app.AlertController$RecycleListView;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.AbstractC0068o;
import androidx.lifecycle.EnumC0066m;
import androidx.lifecycle.InterfaceC0072t;
import i.AbstractC0120b;
import i.InterfaceC0119a;
import j0.AbstractC0150d;
import java.util.WeakHashMap;
import org.conscrypt.R;

/* renamed from: g.h, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class DialogC0110h extends Dialog implements DialogInterface, InterfaceC0113k, InterfaceC0072t, U.g {

    /* renamed from: a, reason: collision with root package name */
    public androidx.lifecycle.v f1447a;

    /* renamed from: b, reason: collision with root package name */
    public final U.f f1448b;

    /* renamed from: c, reason: collision with root package name */
    public final a.v f1449c;

    /* renamed from: d, reason: collision with root package name */
    public C f1450d;

    /* renamed from: e, reason: collision with root package name */
    public final D f1451e;

    /* renamed from: f, reason: collision with root package name */
    public final C0109g f1452f;

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r2v4, types: [g.D] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DialogC0110h(android.view.ContextThemeWrapper r6, int r7) {
        /*
            r5 = this;
            int r7 = h(r6, r7)
            r0 = 1
            r1 = 2130903206(0x7f0300a6, float:1.7413223E38)
            if (r7 != 0) goto L19
            android.util.TypedValue r2 = new android.util.TypedValue
            r2.<init>()
            android.content.res.Resources$Theme r3 = r6.getTheme()
            r3.resolveAttribute(r1, r2, r0)
            int r2 = r2.resourceId
            goto L1a
        L19:
            r2 = r7
        L1a:
            r5.<init>(r6, r2)
            U.f r2 = new U.f
            r2.<init>(r5)
            r5.f1448b = r2
            a.v r2 = new a.v
            M.r r3 = new M.r
            r4 = 3
            r3.<init>(r4, r5)
            r2.<init>(r3)
            r5.f1449c = r2
            g.D r2 = new g.D
            r2.<init>()
            r5.f1451e = r2
            g.p r2 = r5.b()
            if (r7 != 0) goto L4c
            android.util.TypedValue r7 = new android.util.TypedValue
            r7.<init>()
            android.content.res.Resources$Theme r6 = r6.getTheme()
            r6.resolveAttribute(r1, r7, r0)
            int r7 = r7.resourceId
        L4c:
            r6 = r2
            g.C r6 = (g.C) r6
            r6.f1324T = r7
            r2.d()
            g.g r6 = new g.g
            android.content.Context r7 = r5.getContext()
            android.view.Window r0 = r5.getWindow()
            r6.<init>(r7, r5, r0)
            r5.f1452f = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: g.DialogC0110h.<init>(android.view.ContextThemeWrapper, int):void");
    }

    public static void a(DialogC0110h dialogC0110h) {
        super.onBackPressed();
    }

    public static int h(Context context, int i2) {
        if (((i2 >>> 24) & 255) >= 1) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    @Override // android.app.Dialog
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        C c2 = (C) b();
        c2.x();
        ((ViewGroup) c2.f1305A.findViewById(android.R.id.content)).addView(view, layoutParams);
        c2.f1340m.a(c2.f1339l.getCallback());
    }

    public final AbstractC0118p b() {
        if (this.f1450d == null) {
            ExecutorC0116n executorC0116n = AbstractC0118p.f1458a;
            this.f1450d = new C(getContext(), getWindow(), this, this);
        }
        return this.f1450d;
    }

    public final androidx.lifecycle.v c() {
        androidx.lifecycle.v vVar = this.f1447a;
        if (vVar == null) {
            androidx.lifecycle.v vVar2 = new androidx.lifecycle.v(this);
            this.f1447a = vVar2;
            return vVar2;
        }
        return vVar;
    }

    public final void d() {
        View decorView = getWindow().getDecorView();
        AbstractC0150d.e(decorView, "<this>");
        decorView.setTag(R.id.view_tree_lifecycle_owner, this);
        View decorView2 = getWindow().getDecorView();
        AbstractC0150d.e(decorView2, "<this>");
        decorView2.setTag(R.id.view_tree_saved_state_registry_owner, this);
        View decorView3 = getWindow().getDecorView();
        AbstractC0150d.e(decorView3, "<this>");
        decorView3.setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, this);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public final void dismiss() {
        super.dismiss();
        b().e();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return C0.m.h(this.f1451e, getWindow().getDecorView(), this, keyEvent);
    }

    public final void e(Bundle bundle) {
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher = getOnBackInvokedDispatcher();
            AbstractC0150d.d(onBackInvokedDispatcher, "onBackInvokedDispatcher");
            a.v vVar = this.f1449c;
            vVar.f783e = onBackInvokedDispatcher;
            vVar.c(vVar.f785g);
        }
        this.f1448b.b(bundle);
        c().e(EnumC0066m.ON_CREATE);
    }

    public final void f(Bundle bundle) {
        b().a();
        e(bundle);
        b().d();
    }

    @Override // android.app.Dialog
    public final View findViewById(int i2) {
        C c2 = (C) b();
        c2.x();
        return c2.f1339l.findViewById(i2);
    }

    public final void g() {
        c().e(EnumC0066m.ON_DESTROY);
        this.f1447a = null;
        super.onStop();
    }

    @Override // androidx.lifecycle.InterfaceC0072t
    public final AbstractC0068o getLifecycle() {
        return c();
    }

    @Override // U.g
    public final U.e getSavedStateRegistry() {
        return this.f1448b.f672b;
    }

    public final void i(CharSequence charSequence) {
        super.setTitle(charSequence);
        b().l(charSequence);
    }

    @Override // android.app.Dialog
    public final void invalidateOptionsMenu() {
        b().b();
    }

    public final boolean j(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Dialog
    public final void onBackPressed() {
        this.f1449c.b();
    }

    @Override // android.app.Dialog
    public final void onCreate(Bundle bundle) {
        int i2;
        boolean z2;
        int i3;
        boolean z3;
        ListAdapter listAdapter;
        int i4;
        int i5;
        View view;
        View findViewById;
        int i6 = 2;
        f(bundle);
        C0109g c0109g = this.f1452f;
        c0109g.f1426b.setContentView(c0109g.f1440q);
        Window window = c0109g.f1427c;
        View findViewById2 = window.findViewById(R.id.parentPanel);
        View findViewById3 = findViewById2.findViewById(R.id.topPanel);
        View findViewById4 = findViewById2.findViewById(R.id.contentPanel);
        View findViewById5 = findViewById2.findViewById(R.id.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById2.findViewById(R.id.customPanel);
        window.setFlags(131072, 131072);
        viewGroup.setVisibility(8);
        View findViewById6 = viewGroup.findViewById(R.id.topPanel);
        View findViewById7 = viewGroup.findViewById(R.id.contentPanel);
        View findViewById8 = viewGroup.findViewById(R.id.buttonPanel);
        ViewGroup a2 = C0109g.a(findViewById6, findViewById3);
        ViewGroup a3 = C0109g.a(findViewById7, findViewById4);
        ViewGroup a4 = C0109g.a(findViewById8, findViewById5);
        NestedScrollView nestedScrollView = (NestedScrollView) window.findViewById(R.id.scrollView);
        c0109g.f1432i = nestedScrollView;
        nestedScrollView.setFocusable(false);
        c0109g.f1432i.setNestedScrollingEnabled(false);
        TextView textView = (TextView) a3.findViewById(android.R.id.message);
        c0109g.f1436m = textView;
        if (textView != null) {
            textView.setVisibility(8);
            c0109g.f1432i.removeView(c0109g.f1436m);
            if (c0109g.f1429e != null) {
                ViewGroup viewGroup2 = (ViewGroup) c0109g.f1432i.getParent();
                int indexOfChild = viewGroup2.indexOfChild(c0109g.f1432i);
                viewGroup2.removeViewAt(indexOfChild);
                viewGroup2.addView(c0109g.f1429e, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
            } else {
                a3.setVisibility(8);
            }
        }
        Button button = (Button) a4.findViewById(android.R.id.button1);
        c0109g.f1430f = button;
        Z.q qVar = c0109g.f1446w;
        button.setOnClickListener(qVar);
        if (TextUtils.isEmpty(null)) {
            c0109g.f1430f.setVisibility(8);
            i2 = 0;
        } else {
            c0109g.f1430f.setText((CharSequence) null);
            c0109g.f1430f.setVisibility(0);
            i2 = 1;
        }
        Button button2 = (Button) a4.findViewById(android.R.id.button2);
        c0109g.f1431g = button2;
        button2.setOnClickListener(qVar);
        if (TextUtils.isEmpty(null)) {
            c0109g.f1431g.setVisibility(8);
        } else {
            c0109g.f1431g.setText((CharSequence) null);
            c0109g.f1431g.setVisibility(0);
            i2 |= 2;
        }
        Button button3 = (Button) a4.findViewById(android.R.id.button3);
        c0109g.h = button3;
        button3.setOnClickListener(qVar);
        if (TextUtils.isEmpty(null)) {
            c0109g.h.setVisibility(8);
        } else {
            c0109g.h.setText((CharSequence) null);
            c0109g.h.setVisibility(0);
            i2 |= 4;
        }
        TypedValue typedValue = new TypedValue();
        c0109g.f1425a.getTheme().resolveAttribute(R.attr.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            if (i2 == 1) {
                Button button4 = c0109g.f1430f;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button4.getLayoutParams();
                layoutParams.gravity = 1;
                layoutParams.weight = 0.5f;
                button4.setLayoutParams(layoutParams);
            } else if (i2 == 2) {
                Button button5 = c0109g.f1431g;
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) button5.getLayoutParams();
                layoutParams2.gravity = 1;
                layoutParams2.weight = 0.5f;
                button5.setLayoutParams(layoutParams2);
            } else if (i2 == 4) {
                Button button6 = c0109g.h;
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) button6.getLayoutParams();
                layoutParams3.gravity = 1;
                layoutParams3.weight = 0.5f;
                button6.setLayoutParams(layoutParams3);
            }
        }
        if (i2 == 0) {
            a4.setVisibility(8);
        }
        if (c0109g.f1437n != null) {
            a2.addView(c0109g.f1437n, 0, new ViewGroup.LayoutParams(-1, -2));
            window.findViewById(R.id.title_template).setVisibility(8);
        } else {
            c0109g.f1434k = (ImageView) window.findViewById(android.R.id.icon);
            if (!TextUtils.isEmpty(c0109g.f1428d) && c0109g.f1444u) {
                TextView textView2 = (TextView) window.findViewById(R.id.alertTitle);
                c0109g.f1435l = textView2;
                textView2.setText(c0109g.f1428d);
                Drawable drawable = c0109g.f1433j;
                if (drawable != null) {
                    c0109g.f1434k.setImageDrawable(drawable);
                } else {
                    c0109g.f1435l.setPadding(c0109g.f1434k.getPaddingLeft(), c0109g.f1434k.getPaddingTop(), c0109g.f1434k.getPaddingRight(), c0109g.f1434k.getPaddingBottom());
                    c0109g.f1434k.setVisibility(8);
                }
            } else {
                window.findViewById(R.id.title_template).setVisibility(8);
                c0109g.f1434k.setVisibility(8);
                a2.setVisibility(8);
            }
        }
        if (viewGroup.getVisibility() != 8) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (a2 != null && a2.getVisibility() != 8) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        if (a4.getVisibility() != 8) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z3 && (findViewById = a3.findViewById(R.id.textSpacerNoButtons)) != null) {
            findViewById.setVisibility(0);
        }
        if (i3 != 0) {
            NestedScrollView nestedScrollView2 = c0109g.f1432i;
            if (nestedScrollView2 != null) {
                nestedScrollView2.setClipToPadding(true);
            }
            if (c0109g.f1429e != null) {
                view = a2.findViewById(R.id.titleDividerNoCustom);
            } else {
                view = null;
            }
            if (view != null) {
                view.setVisibility(0);
            }
        } else {
            View findViewById9 = a3.findViewById(R.id.textSpacerNoTitle);
            if (findViewById9 != null) {
                findViewById9.setVisibility(0);
            }
        }
        AlertController$RecycleListView alertController$RecycleListView = c0109g.f1429e;
        if (alertController$RecycleListView != null) {
            alertController$RecycleListView.getClass();
            if (!z3 || i3 == 0) {
                int paddingLeft = alertController$RecycleListView.getPaddingLeft();
                if (i3 != 0) {
                    i4 = alertController$RecycleListView.getPaddingTop();
                } else {
                    i4 = alertController$RecycleListView.f794a;
                }
                int paddingRight = alertController$RecycleListView.getPaddingRight();
                if (z3) {
                    i5 = alertController$RecycleListView.getPaddingBottom();
                } else {
                    i5 = alertController$RecycleListView.f795b;
                }
                alertController$RecycleListView.setPadding(paddingLeft, i4, paddingRight, i5);
            }
        }
        if (!z2) {
            View view2 = c0109g.f1429e;
            if (view2 == null) {
                view2 = c0109g.f1432i;
            }
            if (view2 != null) {
                if (!z3) {
                    i6 = 0;
                }
                int i7 = i6 | i3;
                View findViewById10 = window.findViewById(R.id.scrollIndicatorUp);
                View findViewById11 = window.findViewById(R.id.scrollIndicatorDown);
                WeakHashMap weakHashMap = H.N.f327a;
                H.H.b(view2, i7, 3);
                if (findViewById10 != null) {
                    a3.removeView(findViewById10);
                }
                if (findViewById11 != null) {
                    a3.removeView(findViewById11);
                }
            }
        }
        AlertController$RecycleListView alertController$RecycleListView2 = c0109g.f1429e;
        if (alertController$RecycleListView2 != null && (listAdapter = c0109g.f1438o) != null) {
            alertController$RecycleListView2.setAdapter(listAdapter);
            int i8 = c0109g.f1439p;
            if (i8 > -1) {
                alertController$RecycleListView2.setItemChecked(i8, true);
                alertController$RecycleListView2.setSelection(i8);
            }
        }
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i2, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f1452f.f1432i;
        if (nestedScrollView != null && nestedScrollView.j(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i2, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f1452f.f1432i;
        if (nestedScrollView != null && nestedScrollView.j(keyEvent)) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // android.app.Dialog
    public final Bundle onSaveInstanceState() {
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        AbstractC0150d.d(onSaveInstanceState, "super.onSaveInstanceState()");
        this.f1448b.c(onSaveInstanceState);
        return onSaveInstanceState;
    }

    @Override // android.app.Dialog
    public final void onStart() {
        super.onStart();
        c().e(EnumC0066m.ON_RESUME);
    }

    @Override // android.app.Dialog
    public final void onStop() {
        g();
        C c2 = (C) b();
        c2.B();
        AbstractC0103a abstractC0103a = c2.f1342o;
        if (abstractC0103a != null) {
            abstractC0103a.m(false);
        }
    }

    @Override // g.InterfaceC0113k
    public final AbstractC0120b onWindowStartingSupportActionMode(InterfaceC0119a interfaceC0119a) {
        return null;
    }

    @Override // android.app.Dialog
    public final void setContentView(int i2) {
        d();
        b().h(i2);
    }

    @Override // android.app.Dialog
    public final void setTitle(int i2) {
        super.setTitle(i2);
        b().l(getContext().getString(i2));
    }

    @Override // android.app.Dialog
    public final void setContentView(View view) {
        d();
        b().i(view);
    }

    @Override // android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        i(charSequence);
        C0109g c0109g = this.f1452f;
        c0109g.f1428d = charSequence;
        TextView textView = c0109g.f1435l;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    @Override // android.app.Dialog
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        d();
        b().j(view, layoutParams);
    }

    @Override // g.InterfaceC0113k
    public final void onSupportActionModeFinished(AbstractC0120b abstractC0120b) {
    }

    @Override // g.InterfaceC0113k
    public final void onSupportActionModeStarted(AbstractC0120b abstractC0120b) {
    }
}

package j;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import org.conscrypt.R;

/* loaded from: classes.dex */
public class x {

    /* renamed from: a, reason: collision with root package name */
    public final Context f1723a;

    /* renamed from: b, reason: collision with root package name */
    public final n f1724b;

    /* renamed from: c, reason: collision with root package name */
    public final boolean f1725c;

    /* renamed from: d, reason: collision with root package name */
    public final int f1726d;

    /* renamed from: e, reason: collision with root package name */
    public View f1727e;

    /* renamed from: g, reason: collision with root package name */
    public boolean f1729g;
    public y h;

    /* renamed from: i, reason: collision with root package name */
    public v f1730i;

    /* renamed from: j, reason: collision with root package name */
    public w f1731j;

    /* renamed from: f, reason: collision with root package name */
    public int f1728f = 8388611;

    /* renamed from: k, reason: collision with root package name */
    public final w f1732k = new w(this);

    public x(int i2, Context context, View view, n nVar, boolean z2) {
        this.f1723a = context;
        this.f1724b = nVar;
        this.f1727e = view;
        this.f1725c = z2;
        this.f1726d = i2;
    }

    public final v a() {
        v viewOnKeyListenerC0137E;
        if (this.f1730i == null) {
            Context context = this.f1723a;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            if (Math.min(point.x, point.y) >= context.getResources().getDimensionPixelSize(R.dimen.abc_cascading_menus_min_smallest_width)) {
                viewOnKeyListenerC0137E = new ViewOnKeyListenerC0146h(context, this.f1727e, this.f1726d, this.f1725c);
            } else {
                View view = this.f1727e;
                Context context2 = this.f1723a;
                boolean z2 = this.f1725c;
                viewOnKeyListenerC0137E = new ViewOnKeyListenerC0137E(this.f1726d, context2, view, this.f1724b, z2);
            }
            viewOnKeyListenerC0137E.l(this.f1724b);
            viewOnKeyListenerC0137E.r(this.f1732k);
            viewOnKeyListenerC0137E.n(this.f1727e);
            viewOnKeyListenerC0137E.h(this.h);
            viewOnKeyListenerC0137E.o(this.f1729g);
            viewOnKeyListenerC0137E.p(this.f1728f);
            this.f1730i = viewOnKeyListenerC0137E;
        }
        return this.f1730i;
    }

    public final boolean b() {
        v vVar = this.f1730i;
        if (vVar != null && vVar.b()) {
            return true;
        }
        return false;
    }

    public void c() {
        this.f1730i = null;
        w wVar = this.f1731j;
        if (wVar != null) {
            wVar.onDismiss();
        }
    }

    public final void d(int i2, int i3, boolean z2, boolean z3) {
        v a2 = a();
        a2.s(z3);
        if (z2) {
            if ((Gravity.getAbsoluteGravity(this.f1728f, this.f1727e.getLayoutDirection()) & 7) == 5) {
                i2 -= this.f1727e.getWidth();
            }
            a2.q(i2);
            a2.t(i3);
            int i4 = (int) ((this.f1723a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            a2.f1721a = new Rect(i2 - i4, i3 - i4, i2 + i4, i3 + i4);
        }
        a2.i();
    }
}

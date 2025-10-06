package i;

import H.S;
import android.view.View;
import android.view.animation.BaseInterpolator;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: i.k, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0129k {

    /* renamed from: c, reason: collision with root package name */
    public BaseInterpolator f1568c;

    /* renamed from: d, reason: collision with root package name */
    public C0.d f1569d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f1570e;

    /* renamed from: b, reason: collision with root package name */
    public long f1567b = -1;

    /* renamed from: f, reason: collision with root package name */
    public final C0128j f1571f = new C0128j(this);

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f1566a = new ArrayList();

    public final void a() {
        if (!this.f1570e) {
            return;
        }
        Iterator it = this.f1566a.iterator();
        while (it.hasNext()) {
            ((S) it.next()).b();
        }
        this.f1570e = false;
    }

    public final void b() {
        View view;
        if (this.f1570e) {
            return;
        }
        Iterator it = this.f1566a.iterator();
        while (it.hasNext()) {
            S s2 = (S) it.next();
            long j2 = this.f1567b;
            if (j2 >= 0) {
                s2.c(j2);
            }
            BaseInterpolator baseInterpolator = this.f1568c;
            if (baseInterpolator != null && (view = (View) s2.f336a.get()) != null) {
                view.animate().setInterpolator(baseInterpolator);
            }
            if (this.f1569d != null) {
                s2.d(this.f1571f);
            }
            View view2 = (View) s2.f336a.get();
            if (view2 != null) {
                view2.animate().start();
            }
        }
        this.f1570e = true;
    }
}

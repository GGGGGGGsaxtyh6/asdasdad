package a;

import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.fragment.app.C0052a;
import b0.C0080f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

/* loaded from: classes.dex */
public final class v {

    /* renamed from: a, reason: collision with root package name */
    public final Runnable f779a;

    /* renamed from: b, reason: collision with root package name */
    public final C0080f f780b = new C0080f();

    /* renamed from: c, reason: collision with root package name */
    public androidx.fragment.app.o f781c;

    /* renamed from: d, reason: collision with root package name */
    public final OnBackInvokedCallback f782d;

    /* renamed from: e, reason: collision with root package name */
    public OnBackInvokedDispatcher f783e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f784f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f785g;

    public v(Runnable runnable) {
        OnBackInvokedCallback qVar;
        this.f779a = runnable;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 33) {
            if (i2 >= 34) {
                qVar = new r(new o(0, this), new o(1, this), new p(this, 0), new p(this, 1));
            } else {
                qVar = new q(0, new p(this, 2));
            }
            this.f782d = qVar;
        }
    }

    public final void a() {
        Object obj;
        if (this.f781c == null) {
            C0080f c0080f = this.f780b;
            ListIterator<E> listIterator = c0080f.listIterator(c0080f.size());
            while (true) {
                if (listIterator.hasPrevious()) {
                    obj = listIterator.previous();
                    if (((androidx.fragment.app.o) obj).f1066a) {
                        break;
                    }
                } else {
                    obj = null;
                    break;
                }
            }
        }
        this.f781c = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.Object] */
    public final void b() {
        int i2;
        androidx.fragment.app.o oVar;
        androidx.fragment.app.o oVar2 = this.f781c;
        if (oVar2 == null) {
            C0080f c0080f = this.f780b;
            c0080f.getClass();
            ListIterator listIterator = c0080f.listIterator(c0080f.f1230c);
            while (true) {
                if (listIterator.hasPrevious()) {
                    oVar = listIterator.previous();
                    if (((androidx.fragment.app.o) oVar).f1066a) {
                        break;
                    }
                } else {
                    oVar = 0;
                    break;
                }
            }
            oVar2 = oVar;
        }
        this.f781c = null;
        if (oVar2 != null) {
            androidx.fragment.app.t tVar = oVar2.f1069d;
            tVar.e(true);
            if (tVar.f1089g.f1066a) {
                tVar.e(false);
                tVar.d(true);
                ArrayList arrayList = tVar.f1078C;
                ArrayList arrayList2 = tVar.f1079D;
                ArrayList arrayList3 = tVar.f1086d;
                if (arrayList3 != null && !arrayList3.isEmpty()) {
                    i2 = tVar.f1086d.size() - 1;
                } else {
                    i2 = -1;
                }
                if (i2 >= 0) {
                    for (int size = tVar.f1086d.size() - 1; size >= i2; size--) {
                        arrayList.add((C0052a) tVar.f1086d.remove(size));
                        arrayList2.add(Boolean.TRUE);
                    }
                    tVar.f1084b = true;
                    try {
                        tVar.i(tVar.f1078C, tVar.f1079D);
                    } finally {
                        tVar.a();
                    }
                }
                tVar.j();
                tVar.f1085c.f1132b.values().removeAll(Collections.singleton(null));
                return;
            }
            tVar.f1088f.b();
            return;
        }
        this.f779a.run();
    }

    public final void c(boolean z2) {
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.f783e;
        OnBackInvokedCallback onBackInvokedCallback = this.f782d;
        if (onBackInvokedDispatcher != null && onBackInvokedCallback != null) {
            if (z2 && !this.f784f) {
                D.c.e(onBackInvokedDispatcher, onBackInvokedCallback);
                this.f784f = true;
            } else if (!z2 && this.f784f) {
                D.c.f(onBackInvokedDispatcher, onBackInvokedCallback);
                this.f784f = false;
            }
        }
    }

    public final void d() {
        boolean z2 = this.f785g;
        boolean z3 = false;
        C0080f c0080f = this.f780b;
        if (c0080f == null || !c0080f.isEmpty()) {
            Iterator<E> it = c0080f.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((androidx.fragment.app.o) it.next()).f1066a) {
                    z3 = true;
                    break;
                }
            }
        }
        this.f785g = z3;
        if (z3 != z2 && Build.VERSION.SDK_INT >= 33) {
            c(z3);
        }
    }
}

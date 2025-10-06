package H;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: H.o, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0021o {

    /* renamed from: a, reason: collision with root package name */
    public final Runnable f384a;

    /* renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArrayList f385b = new CopyOnWriteArrayList();

    /* renamed from: c, reason: collision with root package name */
    public final HashMap f386c = new HashMap();

    public C0021o(Runnable runnable) {
        this.f384a = runnable;
    }

    public final void a() {
        Iterator it = this.f385b.iterator();
        while (it.hasNext()) {
            androidx.fragment.app.t tVar = ((androidx.fragment.app.p) ((InterfaceC0023q) it.next())).f1070a;
            if (tVar.f1099r >= 1) {
                Iterator it2 = tVar.f1085c.c().iterator();
                while (it2.hasNext()) {
                    if (it2.next() != null) {
                        throw new ClassCastException();
                    }
                }
            }
        }
    }

    public final void b() {
        Iterator it = this.f385b.iterator();
        while (it.hasNext()) {
            androidx.fragment.app.t tVar = ((androidx.fragment.app.p) ((InterfaceC0023q) it.next())).f1070a;
            if (tVar.f1099r >= 1) {
                Iterator it2 = tVar.f1085c.c().iterator();
                while (it2.hasNext()) {
                    if (it2.next() != null) {
                        throw new ClassCastException();
                    }
                }
            }
        }
    }

    public final void c() {
        Iterator it = this.f385b.iterator();
        while (it.hasNext()) {
            androidx.fragment.app.t tVar = ((androidx.fragment.app.p) ((InterfaceC0023q) it.next())).f1070a;
            if (tVar.f1099r >= 1) {
                Iterator it2 = tVar.f1085c.c().iterator();
                while (it2.hasNext()) {
                    if (it2.next() != null) {
                        throw new ClassCastException();
                    }
                }
            }
        }
    }

    public final void d(InterfaceC0023q interfaceC0023q) {
        this.f385b.remove(interfaceC0023q);
        C0020n c0020n = (C0020n) this.f386c.remove(interfaceC0023q);
        if (c0020n != null) {
            c0020n.f382a.b(c0020n.f383b);
            c0020n.f383b = null;
        }
        this.f384a.run();
    }
}

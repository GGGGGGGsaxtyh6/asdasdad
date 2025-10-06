package androidx.lifecycle;

import android.os.Looper;
import j0.AbstractC0150d;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;
import l.C0216a;
import m.AbstractC0224e;
import m.C0220a;
import m.C0222c;

/* loaded from: classes.dex */
public final class v extends AbstractC0068o {

    /* renamed from: a, reason: collision with root package name */
    public final boolean f1205a;

    /* renamed from: b, reason: collision with root package name */
    public C0220a f1206b;

    /* renamed from: c, reason: collision with root package name */
    public EnumC0067n f1207c;

    /* renamed from: d, reason: collision with root package name */
    public final WeakReference f1208d;

    /* renamed from: e, reason: collision with root package name */
    public int f1209e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f1210f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f1211g;
    public final ArrayList h;

    /* renamed from: i, reason: collision with root package name */
    public final r0.b f1212i;

    public v(InterfaceC0072t interfaceC0072t) {
        new AtomicReference(null);
        this.f1205a = true;
        this.f1206b = new C0220a();
        EnumC0067n enumC0067n = EnumC0067n.f1197b;
        this.f1207c = enumC0067n;
        this.h = new ArrayList();
        this.f1208d = new WeakReference(interfaceC0072t);
        this.f1212i = new r0.b(enumC0067n);
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [androidx.lifecycle.u, java.lang.Object] */
    @Override // androidx.lifecycle.AbstractC0068o
    public final void a(InterfaceC0071s interfaceC0071s) {
        r c0059f;
        InterfaceC0072t interfaceC0072t;
        ArrayList arrayList = this.h;
        int i2 = 2;
        int i3 = 0;
        d("addObserver");
        EnumC0067n enumC0067n = this.f1207c;
        EnumC0067n enumC0067n2 = EnumC0067n.f1196a;
        if (enumC0067n != enumC0067n2) {
            enumC0067n2 = EnumC0067n.f1197b;
        }
        ?? obj = new Object();
        HashMap hashMap = w.f1213a;
        boolean z2 = interfaceC0071s instanceof r;
        boolean z3 = interfaceC0071s instanceof M.k;
        Object obj2 = null;
        if (z2 && z3) {
            c0059f = new C0059f((M.k) interfaceC0071s, (r) interfaceC0071s, i3);
        } else if (z3) {
            c0059f = new C0059f((M.k) interfaceC0071s, obj2, i3);
        } else if (z2) {
            c0059f = (r) interfaceC0071s;
        } else {
            Class<?> cls = interfaceC0071s.getClass();
            if (w.b(cls) == 2) {
                Object obj3 = w.f1214b.get(cls);
                AbstractC0150d.b(obj3);
                List list = (List) obj3;
                if (list.size() != 1) {
                    int size = list.size();
                    InterfaceC0061h[] interfaceC0061hArr = new InterfaceC0061h[size];
                    if (size <= 0) {
                        c0059f = new U.b(i2, interfaceC0061hArr);
                    } else {
                        w.a((Constructor) list.get(0), interfaceC0071s);
                        throw null;
                    }
                } else {
                    w.a((Constructor) list.get(0), interfaceC0071s);
                    throw null;
                }
            } else {
                c0059f = new C0059f(interfaceC0071s);
            }
        }
        obj.f1204b = c0059f;
        obj.f1203a = enumC0067n2;
        C0220a c0220a = this.f1206b;
        C0222c a2 = c0220a.a(interfaceC0071s);
        if (a2 != null) {
            obj2 = a2.f2085b;
        } else {
            HashMap hashMap2 = c0220a.f2080e;
            C0222c c0222c = new C0222c(interfaceC0071s, obj);
            c0220a.f2094d++;
            C0222c c0222c2 = c0220a.f2092b;
            if (c0222c2 == null) {
                c0220a.f2091a = c0222c;
                c0220a.f2092b = c0222c;
            } else {
                c0222c2.f2086c = c0222c;
                c0222c.f2087d = c0222c2;
                c0220a.f2092b = c0222c;
            }
            hashMap2.put(interfaceC0071s, c0222c);
        }
        if (((u) obj2) != null || (interfaceC0072t = (InterfaceC0072t) this.f1208d.get()) == null) {
            return;
        }
        if (this.f1209e != 0 || this.f1210f) {
            i3 = 1;
        }
        EnumC0067n c2 = c(interfaceC0071s);
        this.f1209e++;
        while (obj.f1203a.compareTo(c2) < 0 && this.f1206b.f2080e.containsKey(interfaceC0071s)) {
            arrayList.add(obj.f1203a);
            C0064k c0064k = EnumC0066m.Companion;
            EnumC0067n enumC0067n3 = obj.f1203a;
            c0064k.getClass();
            EnumC0066m b2 = C0064k.b(enumC0067n3);
            if (b2 != null) {
                obj.a(interfaceC0072t, b2);
                arrayList.remove(arrayList.size() - 1);
                c2 = c(interfaceC0071s);
            } else {
                throw new IllegalStateException("no event up from " + obj.f1203a);
            }
        }
        if (i3 == 0) {
            g();
        }
        this.f1209e--;
    }

    @Override // androidx.lifecycle.AbstractC0068o
    public final void b(InterfaceC0071s interfaceC0071s) {
        AbstractC0150d.e(interfaceC0071s, "observer");
        d("removeObserver");
        C0220a c0220a = this.f1206b;
        C0222c a2 = c0220a.a(interfaceC0071s);
        if (a2 != null) {
            c0220a.f2094d--;
            WeakHashMap weakHashMap = c0220a.f2093c;
            if (!weakHashMap.isEmpty()) {
                Iterator it = weakHashMap.keySet().iterator();
                while (it.hasNext()) {
                    ((AbstractC0224e) it.next()).a(a2);
                }
            }
            C0222c c0222c = a2.f2087d;
            if (c0222c != null) {
                c0222c.f2086c = a2.f2086c;
            } else {
                c0220a.f2091a = a2.f2086c;
            }
            C0222c c0222c2 = a2.f2086c;
            if (c0222c2 != null) {
                c0222c2.f2087d = c0222c;
            } else {
                c0220a.f2092b = c0222c;
            }
            a2.f2086c = null;
            a2.f2087d = null;
        }
        c0220a.f2080e.remove(interfaceC0071s);
    }

    public final EnumC0067n c(InterfaceC0071s interfaceC0071s) {
        C0222c c0222c;
        EnumC0067n enumC0067n;
        u uVar;
        HashMap hashMap = this.f1206b.f2080e;
        EnumC0067n enumC0067n2 = null;
        if (hashMap.containsKey(interfaceC0071s)) {
            c0222c = ((C0222c) hashMap.get(interfaceC0071s)).f2087d;
        } else {
            c0222c = null;
        }
        if (c0222c != null && (uVar = (u) c0222c.f2085b) != null) {
            enumC0067n = uVar.f1203a;
        } else {
            enumC0067n = null;
        }
        ArrayList arrayList = this.h;
        if (!arrayList.isEmpty()) {
            enumC0067n2 = (EnumC0067n) arrayList.get(arrayList.size() - 1);
        }
        EnumC0067n enumC0067n3 = this.f1207c;
        AbstractC0150d.e(enumC0067n3, "state1");
        if (enumC0067n == null || enumC0067n.compareTo(enumC0067n3) >= 0) {
            enumC0067n = enumC0067n3;
        }
        if (enumC0067n2 == null || enumC0067n2.compareTo(enumC0067n) >= 0) {
            return enumC0067n;
        }
        return enumC0067n2;
    }

    public final void d(String str) {
        C0216a c0216a;
        if (this.f1205a) {
            if (C0216a.f2073l != null) {
                c0216a = C0216a.f2073l;
            } else {
                synchronized (C0216a.class) {
                    try {
                        if (C0216a.f2073l == null) {
                            C0216a.f2073l = new C0216a(0);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                c0216a = C0216a.f2073l;
            }
            ((C0216a) c0216a.f2074k).getClass();
            if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
                throw new IllegalStateException(A.e.d("Method ", str, " must be called on the main thread").toString());
            }
        }
    }

    public final void e(EnumC0066m enumC0066m) {
        AbstractC0150d.e(enumC0066m, "event");
        d("handleLifecycleEvent");
        f(enumC0066m.a());
    }

    public final void f(EnumC0067n enumC0067n) {
        EnumC0067n enumC0067n2 = this.f1207c;
        if (enumC0067n2 == enumC0067n) {
            return;
        }
        EnumC0067n enumC0067n3 = EnumC0067n.f1197b;
        EnumC0067n enumC0067n4 = EnumC0067n.f1196a;
        if (enumC0067n2 == enumC0067n3 && enumC0067n == enumC0067n4) {
            throw new IllegalStateException(("State must be at least CREATED to move to " + enumC0067n + ", but was " + this.f1207c + " in component " + this.f1208d.get()).toString());
        }
        this.f1207c = enumC0067n;
        if (!this.f1210f && this.f1209e == 0) {
            this.f1210f = true;
            g();
            this.f1210f = false;
            if (this.f1207c == enumC0067n4) {
                this.f1206b = new C0220a();
                return;
            }
            return;
        }
        this.f1211g = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0030, code lost:
    
        r7.f1211g = false;
        r7.f1212i.a(r7.f1207c);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0039, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g() {
        /*
            Method dump skipped, instructions count: 374
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.v.g():void");
    }
}

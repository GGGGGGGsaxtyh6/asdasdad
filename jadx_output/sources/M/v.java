package M;

import B0.C0000a;
import B0.EnumC0001b;
import B0.G;
import android.content.Context;
import android.graphics.Typeface;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import i.AbstractC0120b;
import i.C0124f;
import i.InterfaceC0119a;
import j.MenuC0135C;
import j0.AbstractC0150d;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class v implements InterfaceC0119a {

    /* renamed from: a, reason: collision with root package name */
    public Object f570a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f571b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f572c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f573d;

    public v() {
        this.f571b = new ArrayDeque();
        this.f572c = new ArrayDeque();
        this.f573d = new ArrayDeque();
    }

    @Override // i.InterfaceC0119a
    public boolean a(AbstractC0120b abstractC0120b, MenuItem menuItem) {
        return ((ActionMode.Callback) this.f570a).onActionItemClicked(g(abstractC0120b), new j.u((Context) this.f571b, (C.a) menuItem));
    }

    @Override // i.InterfaceC0119a
    public boolean b(AbstractC0120b abstractC0120b, j.n nVar) {
        C0124f g2 = g(abstractC0120b);
        n.k kVar = (n.k) this.f573d;
        Menu menu = (Menu) kVar.get(nVar);
        if (menu == null) {
            menu = new MenuC0135C((Context) this.f571b, nVar);
            kVar.put(nVar, menu);
        }
        return ((ActionMode.Callback) this.f570a).onPrepareActionMode(g2, menu);
    }

    @Override // i.InterfaceC0119a
    public boolean c(AbstractC0120b abstractC0120b, j.n nVar) {
        C0124f g2 = g(abstractC0120b);
        n.k kVar = (n.k) this.f573d;
        Menu menu = (Menu) kVar.get(nVar);
        if (menu == null) {
            menu = new MenuC0135C((Context) this.f571b, nVar);
            kVar.put(nVar, menu);
        }
        return ((ActionMode.Callback) this.f570a).onCreateActionMode(g2, menu);
    }

    @Override // i.InterfaceC0119a
    public void d(AbstractC0120b abstractC0120b) {
        ((ActionMode.Callback) this.f570a).onDestroyActionMode(g(abstractC0120b));
    }

    public IOException e(boolean z2, boolean z3, IOException iOException) {
        if (iOException != null) {
            j(iOException);
        }
        y0.h hVar = (y0.h) this.f570a;
        if (z3) {
            if (iOException != null) {
                AbstractC0150d.e(hVar, "call");
            } else {
                AbstractC0150d.e(hVar, "call");
            }
        }
        if (z2) {
            if (iOException != null) {
                AbstractC0150d.e(hVar, "call");
            } else {
                AbstractC0150d.e(hVar, "call");
            }
        }
        return hVar.h(this, z3, z2, iOException);
    }

    public void f(y0.f fVar) {
        fVar.f2882b.decrementAndGet();
        ArrayDeque arrayDeque = (ArrayDeque) this.f572c;
        synchronized (this) {
            if (arrayDeque.remove(fVar)) {
                synchronized (this) {
                }
                h();
            }
            throw new AssertionError("Call wasn't in-flight!");
        }
        h();
    }

    public C0124f g(AbstractC0120b abstractC0120b) {
        ArrayList arrayList = (ArrayList) this.f572c;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0124f c0124f = (C0124f) arrayList.get(i2);
            if (c0124f != null && c0124f.f1523b == abstractC0120b) {
                return c0124f;
            }
        }
        C0124f c0124f2 = new C0124f((Context) this.f571b, abstractC0120b);
        arrayList.add(c0124f2);
        return c0124f2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0039, code lost:
    
        if (r3 < 5) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003c, code lost:
    
        r1.remove();
        r2.f2882b.incrementAndGet();
        r0.add(r2);
        ((java.util.ArrayDeque) r14.f572c).add(r2);
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void h() {
        /*
            Method dump skipped, instructions count: 232
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: M.v.h():void");
    }

    public u0.t i(boolean z2) {
        try {
            u0.t f2 = ((z0.d) this.f572c).f(z2);
            if (f2 != null) {
                f2.f2565m = this;
            }
            return f2;
        } catch (IOException e2) {
            AbstractC0150d.e((y0.h) this.f570a, "call");
            j(e2);
            throw e2;
        }
    }

    public void j(IOException iOException) {
        boolean z2;
        ((y0.e) this.f571b).c(iOException);
        y0.k g2 = ((z0.d) this.f572c).g();
        y0.h hVar = (y0.h) this.f570a;
        synchronized (g2) {
            try {
                AbstractC0150d.e(hVar, "call");
                if (iOException instanceof G) {
                    if (((G) iOException).f81a == EnumC0001b.REFUSED_STREAM) {
                        int i2 = g2.f2914n + 1;
                        g2.f2914n = i2;
                        if (i2 > 1) {
                            g2.f2910j = true;
                            g2.f2912l++;
                        }
                    } else if (((G) iOException).f81a != EnumC0001b.CANCEL || !hVar.f2896m) {
                        g2.f2910j = true;
                        g2.f2912l++;
                    }
                } else {
                    if (g2.f2908g != null) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (!z2 || (iOException instanceof C0000a)) {
                        g2.f2910j = true;
                        if (g2.f2913m == 0) {
                            y0.k.d(hVar.f2885a, g2.f2903b, iOException);
                            g2.f2912l++;
                        }
                    }
                }
            } finally {
            }
        }
    }

    public v(y0.h hVar, y0.e eVar, z0.d dVar) {
        AbstractC0150d.e(hVar, "call");
        AbstractC0150d.e(eVar, "finder");
        this.f570a = hVar;
        this.f571b = eVar;
        this.f572c = dVar;
        this.f573d = dVar.g();
    }

    public v(Typeface typeface, N.b bVar) {
        int i2;
        int i3;
        this.f573d = typeface;
        this.f570a = bVar;
        this.f572c = new u(1024);
        int a2 = bVar.a(6);
        if (a2 != 0) {
            int i4 = a2 + bVar.f316a;
            i2 = ((ByteBuffer) bVar.f319d).getInt(((ByteBuffer) bVar.f319d).getInt(i4) + i4);
        } else {
            i2 = 0;
        }
        this.f571b = new char[i2 * 2];
        int a3 = bVar.a(6);
        if (a3 != 0) {
            int i5 = a3 + bVar.f316a;
            i3 = ((ByteBuffer) bVar.f319d).getInt(((ByteBuffer) bVar.f319d).getInt(i5) + i5);
        } else {
            i3 = 0;
        }
        for (int i6 = 0; i6 < i3; i6++) {
            y yVar = new y(this, i6);
            N.a c2 = yVar.c();
            int a4 = c2.a(4);
            Character.toChars(a4 != 0 ? ((ByteBuffer) c2.f319d).getInt(a4 + c2.f316a) : 0, (char[]) this.f571b, i6 * 2);
            if (yVar.b() > 0) {
                ((u) this.f572c).a(yVar, 0, yVar.b() - 1);
            } else {
                throw new IllegalArgumentException("invalid metadata codepoint length");
            }
        }
    }

    public v(Context context, ActionMode.Callback callback) {
        this.f571b = context;
        this.f570a = callback;
        this.f572c = new ArrayList();
        this.f573d = new n.k(0);
    }
}

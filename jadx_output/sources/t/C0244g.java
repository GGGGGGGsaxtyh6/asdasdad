package t;

import java.util.Iterator;

/* renamed from: t.g, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0244g extends C0243f {

    /* renamed from: m, reason: collision with root package name */
    public int f2382m;

    public C0244g(o oVar) {
        super(oVar);
        if (oVar instanceof C0248k) {
            this.f2375e = 2;
        } else {
            this.f2375e = 3;
        }
    }

    @Override // t.C0243f
    public final void d(int i2) {
        if (this.f2379j) {
            return;
        }
        this.f2379j = true;
        this.f2377g = i2;
        Iterator it = this.f2380k.iterator();
        while (it.hasNext()) {
            InterfaceC0241d interfaceC0241d = (InterfaceC0241d) it.next();
            interfaceC0241d.a(interfaceC0241d);
        }
    }
}

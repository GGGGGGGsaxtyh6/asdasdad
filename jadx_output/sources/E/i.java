package E;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class i implements G.a {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f288a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f289b;

    public /* synthetic */ i(int i2, Object obj) {
        this.f288a = i2;
        this.f289b = obj;
    }

    @Override // G.a
    public final void a(Object obj) {
        switch (this.f288a) {
            case 0:
                j jVar = (j) obj;
                if (jVar == null) {
                    jVar = new j(-3);
                }
                ((c) this.f289b).f(jVar);
                return;
            default:
                j jVar2 = (j) obj;
                synchronized (k.f294c) {
                    try {
                        n.k kVar = k.f295d;
                        ArrayList arrayList = (ArrayList) kVar.get((String) this.f289b);
                        if (arrayList != null) {
                            kVar.remove((String) this.f289b);
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                ((G.a) arrayList.get(i2)).a(jVar2);
                            }
                            return;
                        }
                        return;
                    } finally {
                    }
                }
        }
    }
}

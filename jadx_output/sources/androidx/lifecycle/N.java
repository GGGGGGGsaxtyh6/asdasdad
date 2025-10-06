package androidx.lifecycle;

import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class N {

    /* renamed from: a, reason: collision with root package name */
    public final R.a f1178a = new R.a();

    public final void a() {
        R.a aVar = this.f1178a;
        if (aVar != null && !aVar.f614d) {
            aVar.f614d = true;
            synchronized (aVar.f611a) {
                try {
                    Iterator it = aVar.f612b.values().iterator();
                    while (it.hasNext()) {
                        R.a.a((AutoCloseable) it.next());
                    }
                    Iterator it2 = aVar.f613c.iterator();
                    while (it2.hasNext()) {
                        R.a.a((AutoCloseable) it2.next());
                    }
                    aVar.f613c.clear();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        b();
    }

    public void b() {
    }
}

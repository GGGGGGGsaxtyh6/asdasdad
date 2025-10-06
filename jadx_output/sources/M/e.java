package M;

import android.os.Build;
import java.util.ArrayList;
import java.util.Set;

/* loaded from: classes.dex */
public final class e extends C0.d {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f f526a;

    public e(f fVar) {
        this.f526a = fVar;
    }

    @Override // C0.d
    public final void A(v vVar) {
        Set<int[]> p2;
        f fVar = this.f526a;
        fVar.f529c = vVar;
        v vVar2 = fVar.f529c;
        j jVar = fVar.f527a;
        A.m mVar = jVar.f540g;
        d dVar = jVar.f541i;
        if (Build.VERSION.SDK_INT >= 34) {
            p2 = o.a();
        } else {
            p2 = C0.f.p();
        }
        fVar.f528b = new D0.h(vVar2, mVar, dVar, p2);
        j jVar2 = fVar.f527a;
        jVar2.getClass();
        ArrayList arrayList = new ArrayList();
        jVar2.f534a.writeLock().lock();
        try {
            jVar2.f536c = 1;
            arrayList.addAll(jVar2.f535b);
            jVar2.f535b.clear();
            jVar2.f534a.writeLock().unlock();
            jVar2.f537d.post(new h(arrayList, jVar2.f536c, null));
        } catch (Throwable th) {
            jVar2.f534a.writeLock().unlock();
            throw th;
        }
    }

    @Override // C0.d
    public final void z(Throwable th) {
        this.f526a.f527a.d(th);
    }
}

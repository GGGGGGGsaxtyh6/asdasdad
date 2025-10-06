package M;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class h implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f530a;

    /* renamed from: b, reason: collision with root package name */
    public final int f531b;

    public h(List list, int i2, Throwable th) {
        C0.d.j(list, "initCallbacks cannot be null");
        this.f530a = new ArrayList(list);
        this.f531b = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayList = this.f530a;
        int size = arrayList.size();
        int i2 = 0;
        if (this.f531b != 1) {
            while (i2 < size) {
                ((g) arrayList.get(i2)).getClass();
                i2++;
            }
        } else {
            while (i2 < size) {
                ((g) arrayList.get(i2)).a();
                i2++;
            }
        }
    }
}

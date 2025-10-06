package androidx.fragment.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class y {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f1131a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    public final HashMap f1132b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    public final HashMap f1133c = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    public w f1134d;

    public final void a() {
        Iterator it = this.f1132b.values().iterator();
        while (it.hasNext()) {
            A.e.f(it.next());
        }
    }

    public final ArrayList b() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f1132b.values().iterator();
        while (it.hasNext()) {
            A.e.f(it.next());
        }
        return arrayList;
    }

    public final List c() {
        ArrayList arrayList;
        if (this.f1131a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f1131a) {
            arrayList = new ArrayList(this.f1131a);
        }
        return arrayList;
    }
}

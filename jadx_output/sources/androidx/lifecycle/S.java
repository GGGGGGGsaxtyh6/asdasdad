package androidx.lifecycle;

import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public final class S {

    /* renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f1183a = new LinkedHashMap();

    public final void a() {
        LinkedHashMap linkedHashMap = this.f1183a;
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((N) it.next()).a();
        }
        linkedHashMap.clear();
    }
}

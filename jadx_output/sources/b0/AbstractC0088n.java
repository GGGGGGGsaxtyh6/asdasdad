package b0;

import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: b0.n, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0088n extends AbstractC0087m {
    public static void J(ArrayList arrayList, Iterable iterable) {
        AbstractC0150d.e(iterable, "elements");
        if (iterable instanceof Collection) {
            arrayList.addAll((Collection) iterable);
            return;
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
    }
}

package b0;

import a0.C0049c;
import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: b0.t, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0094t extends C0.f {
    public static int D(int i2) {
        if (i2 >= 0) {
            if (i2 < 3) {
                return i2 + 1;
            }
            if (i2 < 1073741824) {
                return (int) ((i2 / 0.75f) + 1.0f);
            }
            return Integer.MAX_VALUE;
        }
        return i2;
    }

    public static Map E(ArrayList arrayList) {
        C0092r c0092r = C0092r.f1235a;
        int size = arrayList.size();
        if (size != 0) {
            if (size != 1) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(D(arrayList.size()));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    C0049c c0049c = (C0049c) it.next();
                    linkedHashMap.put(c0049c.f786a, c0049c.f787b);
                }
                return linkedHashMap;
            }
            C0049c c0049c2 = (C0049c) arrayList.get(0);
            AbstractC0150d.e(c0049c2, "pair");
            Map singletonMap = Collections.singletonMap(c0049c2.f786a, c0049c2.f787b);
            AbstractC0150d.d(singletonMap, "singletonMap(...)");
            return singletonMap;
        }
        return c0092r;
    }

    public static final Map F(LinkedHashMap linkedHashMap) {
        AbstractC0150d.e(linkedHashMap, "<this>");
        Map.Entry entry = (Map.Entry) linkedHashMap.entrySet().iterator().next();
        Map singletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
        AbstractC0150d.d(singletonMap, "with(...)");
        return singletonMap;
    }
}

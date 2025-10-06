package b0;

import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: b0.h, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0082h extends AbstractC0088n {
    public static final void K(Collection collection, StringBuilder sb, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, a.o oVar) {
        sb.append(charSequence2);
        int i2 = 0;
        for (Object obj : collection) {
            i2++;
            if (i2 > 1) {
                sb.append(charSequence);
            }
            C0.m.a(sb, obj, oVar);
        }
        sb.append(charSequence3);
    }

    public static List L(List list) {
        AbstractC0150d.e(list, "<this>");
        int size = list.size();
        if (size != 0) {
            if (size != 1) {
                return new ArrayList(list);
            }
            return C0.d.y(list.get(0));
        }
        return C0091q.f1234a;
    }
}

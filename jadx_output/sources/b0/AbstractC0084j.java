package b0;

import j0.AbstractC0150d;
import java.util.Collection;

/* renamed from: b0.j, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0084j extends AbstractC0083i {
    public static int I(Iterable iterable) {
        AbstractC0150d.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        return 10;
    }
}

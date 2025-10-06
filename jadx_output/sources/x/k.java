package x;

import g.AbstractActivityC0112j;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class k implements Iterable {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f2834a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    public final AbstractActivityC0112j f2835b;

    public k(AbstractActivityC0112j abstractActivityC0112j) {
        this.f2835b = abstractActivityC0112j;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.f2834a.iterator();
    }
}

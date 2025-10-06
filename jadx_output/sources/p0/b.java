package p0;

import b0.C0075a;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class b implements d {

    /* renamed from: a, reason: collision with root package name */
    public final d f2169a;

    /* renamed from: b, reason: collision with root package name */
    public final int f2170b;

    public b(d dVar, int i2) {
        this.f2169a = dVar;
        this.f2170b = i2;
        if (i2 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("count must be non-negative, but was " + i2 + '.').toString());
    }

    @Override // p0.d
    public final Iterator iterator() {
        return new C0075a(this);
    }
}

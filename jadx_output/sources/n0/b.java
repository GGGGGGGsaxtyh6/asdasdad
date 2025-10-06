package n0;

import java.util.Iterator;
import java.util.NoSuchElementException;
import k0.InterfaceC0215a;

/* loaded from: classes.dex */
public final class b implements Iterator, InterfaceC0215a {

    /* renamed from: a, reason: collision with root package name */
    public final int f2134a;

    /* renamed from: b, reason: collision with root package name */
    public final int f2135b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f2136c;

    /* renamed from: d, reason: collision with root package name */
    public int f2137d;

    public b(int i2, int i3, int i4) {
        this.f2134a = i4;
        this.f2135b = i3;
        boolean z2 = false;
        if (i4 <= 0 ? i2 >= i3 : i2 <= i3) {
            z2 = true;
        }
        this.f2136c = z2;
        this.f2137d = z2 ? i2 : i3;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f2136c;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i2 = this.f2137d;
        if (i2 == this.f2135b) {
            if (this.f2136c) {
                this.f2136c = false;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            this.f2137d = this.f2134a + i2;
        }
        return Integer.valueOf(i2);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}

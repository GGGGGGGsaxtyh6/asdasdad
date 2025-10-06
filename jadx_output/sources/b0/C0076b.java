package b0;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/* renamed from: b0.b, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0076b extends C0075a implements ListIterator {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ AbstractC0078d f1221d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0076b(AbstractC0078d abstractC0078d, int i2) {
        super(abstractC0078d);
        this.f1221d = abstractC0078d;
        int a2 = abstractC0078d.a();
        if (i2 >= 0 && i2 <= a2) {
            this.f1219b = i2;
            return;
        }
        throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + a2);
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        if (this.f1219b > 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f1219b;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (hasPrevious()) {
            int i2 = this.f1219b - 1;
            this.f1219b = i2;
            return this.f1221d.get(i2);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f1219b - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}

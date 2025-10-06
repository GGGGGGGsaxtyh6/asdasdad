package b0;

import j0.AbstractC0150d;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import k0.InterfaceC0215a;

/* renamed from: b0.d, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0078d implements List, Collection, InterfaceC0215a {
    public abstract int a();

    @Override // java.util.List
    public final void add(int i2, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final boolean addAll(int i2, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        if (isEmpty()) {
            return false;
        }
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (AbstractC0150d.a(it.next(), obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        AbstractC0150d.e(collection, "elements");
        if (collection.isEmpty()) {
            return true;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        Collection collection = (Collection) obj;
        AbstractC0150d.e(collection, "other");
        if (size() == collection.size()) {
            Iterator it = collection.iterator();
            Iterator<E> it2 = iterator();
            while (it2.hasNext()) {
                if (!AbstractC0150d.a(it2.next(), it.next())) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public final int hashCode() {
        int i2;
        int i3 = 1;
        for (Object obj : this) {
            int i4 = i3 * 31;
            if (obj != null) {
                i2 = obj.hashCode();
            } else {
                i2 = 0;
            }
            i3 = i4 + i2;
        }
        return i3;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        Iterator it = iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (!AbstractC0150d.a(it.next(), obj)) {
                i2++;
            } else {
                return i2;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        if (a() == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new C0075a(this);
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        ListIterator listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (AbstractC0150d.a(listIterator.previous(), obj)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return new C0076b(this, 0);
    }

    @Override // java.util.List
    public final Object remove(int i2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final Object set(int i2, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return a();
    }

    @Override // java.util.List
    public final List subList(int i2, int i3) {
        return new C0077c(this, i2, i3);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return AbstractC0150d.j(this);
    }

    public final String toString() {
        a.o oVar = new a.o(2, this);
        StringBuilder sb = new StringBuilder();
        AbstractC0082h.K(this, sb, ", ", "[", "]", "...", oVar);
        String sb2 = sb.toString();
        AbstractC0150d.d(sb2, "toString(...)");
        return sb2;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i2) {
        return new C0076b(this, i2);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        AbstractC0150d.e(objArr, "array");
        return AbstractC0150d.k(this, objArr);
    }
}

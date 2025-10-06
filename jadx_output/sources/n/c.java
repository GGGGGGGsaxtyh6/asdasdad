package n;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public final class c implements Set {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f f2101a;

    public c(f fVar) {
        this.f2101a = fVar;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        this.f2101a.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        return this.f2101a.containsKey(obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.f2101a.i(collection);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            f fVar = this.f2101a;
            try {
                if (fVar.f2127c == set.size()) {
                    if (fVar.i(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public final int hashCode() {
        int hashCode;
        f fVar = this.f2101a;
        int i2 = 0;
        for (int i3 = fVar.f2127c - 1; i3 >= 0; i3--) {
            Object e2 = fVar.e(i3);
            if (e2 == null) {
                hashCode = 0;
            } else {
                hashCode = e2.hashCode();
            }
            i2 += hashCode;
        }
        return i2;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        return this.f2101a.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new C0227b(this.f2101a, 0);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        f fVar = this.f2101a;
        int c2 = fVar.c(obj);
        if (c2 >= 0) {
            fVar.f(c2);
            return true;
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        return this.f2101a.j(collection);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        f fVar = this.f2101a;
        int i2 = fVar.f2127c;
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            if (!collection.contains(fVar.e(i3))) {
                fVar.f(i3);
            }
        }
        if (i2 != fVar.f2127c) {
            return true;
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        return this.f2101a.f2127c;
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        f fVar = this.f2101a;
        int i2 = fVar.f2127c;
        Object[] objArr = new Object[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = fVar.e(i3);
        }
        return objArr;
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        f fVar = this.f2101a;
        int i2 = fVar.f2127c;
        if (objArr.length < i2) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i2);
        }
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = fVar.e(i3);
        }
        if (objArr.length > i2) {
            objArr[i2] = null;
        }
        return objArr;
    }
}

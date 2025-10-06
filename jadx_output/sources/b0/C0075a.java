package b0;

import j0.AbstractC0150d;
import java.util.Iterator;
import java.util.NoSuchElementException;
import k0.InterfaceC0215a;

/* renamed from: b0.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0075a implements Iterator, InterfaceC0215a {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1218a = 0;

    /* renamed from: b, reason: collision with root package name */
    public int f1219b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f1220c;

    public C0075a(Object[] objArr) {
        AbstractC0150d.e(objArr, "array");
        this.f1220c = objArr;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        Iterator it;
        switch (this.f1218a) {
            case 0:
                if (this.f1219b < ((AbstractC0078d) this.f1220c).a()) {
                    return true;
                }
                return false;
            case 1:
                if (this.f1219b < ((Object[]) this.f1220c).length) {
                    return true;
                }
                return false;
        }
        while (true) {
            int i2 = this.f1219b;
            it = (Iterator) this.f1220c;
            if (i2 > 0 && it.hasNext()) {
                it.next();
                this.f1219b--;
            }
        }
        return it.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Iterator it;
        switch (this.f1218a) {
            case 0:
                if (hasNext()) {
                    int i2 = this.f1219b;
                    this.f1219b = i2 + 1;
                    return ((AbstractC0078d) this.f1220c).get(i2);
                }
                throw new NoSuchElementException();
            case 1:
                try {
                    Object[] objArr = (Object[]) this.f1220c;
                    int i3 = this.f1219b;
                    this.f1219b = i3 + 1;
                    return objArr[i3];
                } catch (ArrayIndexOutOfBoundsException e2) {
                    this.f1219b--;
                    throw new NoSuchElementException(e2.getMessage());
                }
        }
        while (true) {
            int i4 = this.f1219b;
            it = (Iterator) this.f1220c;
            if (i4 > 0 && it.hasNext()) {
                it.next();
                this.f1219b--;
            }
        }
        return it.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.f1218a) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public C0075a(AbstractC0078d abstractC0078d) {
        this.f1220c = abstractC0078d;
    }

    public C0075a(p0.b bVar) {
        this.f1220c = bVar.f2169a.iterator();
        this.f1219b = bVar.f2170b;
    }
}

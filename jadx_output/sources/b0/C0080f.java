package b0;

import j0.AbstractC0150d;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import k0.InterfaceC0215a;

/* renamed from: b0.f, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0080f extends AbstractList implements List, InterfaceC0215a {

    /* renamed from: d, reason: collision with root package name */
    public static final Object[] f1227d = new Object[0];

    /* renamed from: a, reason: collision with root package name */
    public int f1228a;

    /* renamed from: b, reason: collision with root package name */
    public Object[] f1229b = f1227d;

    /* renamed from: c, reason: collision with root package name */
    public int f1230c;

    public final void a(int i2, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.f1229b.length;
        while (i2 < length && it.hasNext()) {
            this.f1229b[i2] = it.next();
            i2++;
        }
        int i3 = this.f1228a;
        for (int i4 = 0; i4 < i3 && it.hasNext(); i4++) {
            this.f1229b[i4] = it.next();
        }
        this.f1230c = collection.size() + this.f1230c;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i2, Object obj) {
        int i3;
        int i4 = this.f1230c;
        if (i2 < 0 || i2 > i4) {
            throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i4);
        }
        if (i2 == i4) {
            addLast(obj);
            return;
        }
        if (i2 == 0) {
            addFirst(obj);
            return;
        }
        g();
        b(this.f1230c + 1);
        int f2 = f(this.f1228a + i2);
        int i5 = this.f1230c;
        if (i2 < ((i5 + 1) >> 1)) {
            if (f2 == 0) {
                Object[] objArr = this.f1229b;
                AbstractC0150d.e(objArr, "<this>");
                f2 = objArr.length;
            }
            int i6 = f2 - 1;
            int i7 = this.f1228a;
            if (i7 == 0) {
                Object[] objArr2 = this.f1229b;
                AbstractC0150d.e(objArr2, "<this>");
                i3 = objArr2.length - 1;
            } else {
                i3 = i7 - 1;
            }
            int i8 = this.f1228a;
            if (i6 >= i8) {
                Object[] objArr3 = this.f1229b;
                objArr3[i3] = objArr3[i8];
                AbstractC0081g.y(objArr3, objArr3, i8, i8 + 1, i6 + 1);
            } else {
                Object[] objArr4 = this.f1229b;
                AbstractC0081g.y(objArr4, objArr4, i8 - 1, i8, objArr4.length);
                Object[] objArr5 = this.f1229b;
                objArr5[objArr5.length - 1] = objArr5[0];
                AbstractC0081g.y(objArr5, objArr5, 0, 1, i6 + 1);
            }
            this.f1229b[i6] = obj;
            this.f1228a = i3;
        } else {
            int f3 = f(this.f1228a + i5);
            if (f2 < f3) {
                Object[] objArr6 = this.f1229b;
                AbstractC0081g.y(objArr6, objArr6, f2 + 1, f2, f3);
            } else {
                Object[] objArr7 = this.f1229b;
                AbstractC0081g.y(objArr7, objArr7, 1, 0, f3);
                Object[] objArr8 = this.f1229b;
                objArr8[0] = objArr8[objArr8.length - 1];
                AbstractC0081g.y(objArr8, objArr8, f2 + 1, f2, objArr8.length - 1);
            }
            this.f1229b[f2] = obj;
        }
        this.f1230c++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i2, Collection collection) {
        AbstractC0150d.e(collection, "elements");
        int i3 = this.f1230c;
        if (i2 >= 0 && i2 <= i3) {
            if (collection.isEmpty()) {
                return false;
            }
            if (i2 == this.f1230c) {
                return addAll(collection);
            }
            g();
            b(collection.size() + this.f1230c);
            int f2 = f(this.f1228a + this.f1230c);
            int f3 = f(this.f1228a + i2);
            int size = collection.size();
            if (i2 < ((this.f1230c + 1) >> 1)) {
                int i4 = this.f1228a;
                int i5 = i4 - size;
                if (f3 < i4) {
                    Object[] objArr = this.f1229b;
                    AbstractC0081g.y(objArr, objArr, i5, i4, objArr.length);
                    if (size >= f3) {
                        Object[] objArr2 = this.f1229b;
                        AbstractC0081g.y(objArr2, objArr2, objArr2.length - size, 0, f3);
                    } else {
                        Object[] objArr3 = this.f1229b;
                        AbstractC0081g.y(objArr3, objArr3, objArr3.length - size, 0, size);
                        Object[] objArr4 = this.f1229b;
                        AbstractC0081g.y(objArr4, objArr4, 0, size, f3);
                    }
                } else if (i5 >= 0) {
                    Object[] objArr5 = this.f1229b;
                    AbstractC0081g.y(objArr5, objArr5, i5, i4, f3);
                } else {
                    Object[] objArr6 = this.f1229b;
                    i5 += objArr6.length;
                    int i6 = f3 - i4;
                    int length = objArr6.length - i5;
                    if (length >= i6) {
                        AbstractC0081g.y(objArr6, objArr6, i5, i4, f3);
                    } else {
                        AbstractC0081g.y(objArr6, objArr6, i5, i4, i4 + length);
                        Object[] objArr7 = this.f1229b;
                        AbstractC0081g.y(objArr7, objArr7, 0, this.f1228a + length, f3);
                    }
                }
                this.f1228a = i5;
                a(d(f3 - size), collection);
            } else {
                int i7 = f3 + size;
                if (f3 < f2) {
                    int i8 = size + f2;
                    Object[] objArr8 = this.f1229b;
                    if (i8 <= objArr8.length) {
                        AbstractC0081g.y(objArr8, objArr8, i7, f3, f2);
                    } else if (i7 >= objArr8.length) {
                        AbstractC0081g.y(objArr8, objArr8, i7 - objArr8.length, f3, f2);
                    } else {
                        int length2 = f2 - (i8 - objArr8.length);
                        AbstractC0081g.y(objArr8, objArr8, 0, length2, f2);
                        Object[] objArr9 = this.f1229b;
                        AbstractC0081g.y(objArr9, objArr9, i7, f3, length2);
                    }
                } else {
                    Object[] objArr10 = this.f1229b;
                    AbstractC0081g.y(objArr10, objArr10, size, 0, f2);
                    Object[] objArr11 = this.f1229b;
                    if (i7 >= objArr11.length) {
                        AbstractC0081g.y(objArr11, objArr11, i7 - objArr11.length, f3, objArr11.length);
                    } else {
                        AbstractC0081g.y(objArr11, objArr11, 0, objArr11.length - size, objArr11.length);
                        Object[] objArr12 = this.f1229b;
                        AbstractC0081g.y(objArr12, objArr12, i7, f3, objArr12.length - size);
                    }
                }
                a(f3, collection);
            }
            return true;
        }
        throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
    }

    public final void addFirst(Object obj) {
        g();
        b(this.f1230c + 1);
        int i2 = this.f1228a;
        if (i2 == 0) {
            Object[] objArr = this.f1229b;
            AbstractC0150d.e(objArr, "<this>");
            i2 = objArr.length;
        }
        int i3 = i2 - 1;
        this.f1228a = i3;
        this.f1229b[i3] = obj;
        this.f1230c++;
    }

    public final void addLast(Object obj) {
        g();
        b(this.f1230c + 1);
        this.f1229b[f(this.f1228a + this.f1230c)] = obj;
        this.f1230c++;
    }

    public final void b(int i2) {
        if (i2 >= 0) {
            Object[] objArr = this.f1229b;
            if (i2 <= objArr.length) {
                return;
            }
            if (objArr == f1227d) {
                if (i2 < 10) {
                    i2 = 10;
                }
                this.f1229b = new Object[i2];
                return;
            }
            int length = objArr.length;
            int i3 = length + (length >> 1);
            if (i3 - i2 < 0) {
                i3 = i2;
            }
            if (i3 - 2147483639 > 0) {
                if (i2 > 2147483639) {
                    i3 = Integer.MAX_VALUE;
                } else {
                    i3 = 2147483639;
                }
            }
            Object[] objArr2 = new Object[i3];
            AbstractC0081g.y(objArr, objArr2, 0, this.f1228a, objArr.length);
            Object[] objArr3 = this.f1229b;
            int length2 = objArr3.length;
            int i4 = this.f1228a;
            AbstractC0081g.y(objArr3, objArr2, length2 - i4, 0, i4);
            this.f1228a = 0;
            this.f1229b = objArr2;
            return;
        }
        throw new IllegalStateException("Deque is too big.");
    }

    public final int c(int i2) {
        AbstractC0150d.e(this.f1229b, "<this>");
        if (i2 == r0.length - 1) {
            return 0;
        }
        return i2 + 1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        if (!isEmpty()) {
            g();
            e(this.f1228a, f(this.f1228a + this.f1230c));
        }
        this.f1228a = 0;
        this.f1230c = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (indexOf(obj) != -1) {
            return true;
        }
        return false;
    }

    public final int d(int i2) {
        if (i2 < 0) {
            return i2 + this.f1229b.length;
        }
        return i2;
    }

    public final void e(int i2, int i3) {
        if (i2 < i3) {
            AbstractC0081g.A(this.f1229b, i2, i3);
            return;
        }
        Object[] objArr = this.f1229b;
        AbstractC0081g.A(objArr, i2, objArr.length);
        AbstractC0081g.A(this.f1229b, 0, i3);
    }

    public final int f(int i2) {
        Object[] objArr = this.f1229b;
        if (i2 >= objArr.length) {
            return i2 - objArr.length;
        }
        return i2;
    }

    public final void g() {
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i2) {
        int i3 = this.f1230c;
        if (i2 >= 0 && i2 < i3) {
            return this.f1229b[f(this.f1228a + i2)];
        }
        throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i2;
        int f2 = f(this.f1228a + this.f1230c);
        int i3 = this.f1228a;
        if (i3 < f2) {
            while (i3 < f2) {
                if (AbstractC0150d.a(obj, this.f1229b[i3])) {
                    i2 = this.f1228a;
                } else {
                    i3++;
                }
            }
            return -1;
        }
        if (i3 >= f2) {
            int length = this.f1229b.length;
            while (true) {
                if (i3 < length) {
                    if (AbstractC0150d.a(obj, this.f1229b[i3])) {
                        i2 = this.f1228a;
                        break;
                    }
                    i3++;
                } else {
                    for (int i4 = 0; i4 < f2; i4++) {
                        if (AbstractC0150d.a(obj, this.f1229b[i4])) {
                            i3 = i4 + this.f1229b.length;
                            i2 = this.f1228a;
                        }
                    }
                    return -1;
                }
            }
        } else {
            return -1;
        }
        return i3 - i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        if (this.f1230c == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int length;
        int i2;
        int f2 = f(this.f1228a + this.f1230c);
        int i3 = this.f1228a;
        if (i3 < f2) {
            length = f2 - 1;
            if (i3 <= length) {
                while (!AbstractC0150d.a(obj, this.f1229b[length])) {
                    if (length != i3) {
                        length--;
                    }
                }
                i2 = this.f1228a;
                return length - i2;
            }
            return -1;
        }
        if (i3 > f2) {
            int i4 = f2 - 1;
            while (true) {
                if (-1 < i4) {
                    if (AbstractC0150d.a(obj, this.f1229b[i4])) {
                        length = i4 + this.f1229b.length;
                        i2 = this.f1228a;
                        break;
                    }
                    i4--;
                } else {
                    Object[] objArr = this.f1229b;
                    AbstractC0150d.e(objArr, "<this>");
                    length = objArr.length - 1;
                    int i5 = this.f1228a;
                    if (i5 <= length) {
                        while (!AbstractC0150d.a(obj, this.f1229b[length])) {
                            if (length != i5) {
                                length--;
                            }
                        }
                        i2 = this.f1228a;
                    }
                }
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i2) {
        int i3 = this.f1230c;
        if (i2 >= 0 && i2 < i3) {
            if (i2 == size() - 1) {
                return removeLast();
            }
            if (i2 == 0) {
                return removeFirst();
            }
            g();
            int f2 = f(this.f1228a + i2);
            Object[] objArr = this.f1229b;
            Object obj = objArr[f2];
            if (i2 < (this.f1230c >> 1)) {
                int i4 = this.f1228a;
                if (f2 >= i4) {
                    AbstractC0081g.y(objArr, objArr, i4 + 1, i4, f2);
                } else {
                    AbstractC0081g.y(objArr, objArr, 1, 0, f2);
                    Object[] objArr2 = this.f1229b;
                    objArr2[0] = objArr2[objArr2.length - 1];
                    int i5 = this.f1228a;
                    AbstractC0081g.y(objArr2, objArr2, i5 + 1, i5, objArr2.length - 1);
                }
                Object[] objArr3 = this.f1229b;
                int i6 = this.f1228a;
                objArr3[i6] = null;
                this.f1228a = c(i6);
            } else {
                int f3 = f((size() - 1) + this.f1228a);
                if (f2 <= f3) {
                    Object[] objArr4 = this.f1229b;
                    AbstractC0081g.y(objArr4, objArr4, f2, f2 + 1, f3 + 1);
                } else {
                    Object[] objArr5 = this.f1229b;
                    AbstractC0081g.y(objArr5, objArr5, f2, f2 + 1, objArr5.length);
                    Object[] objArr6 = this.f1229b;
                    objArr6[objArr6.length - 1] = objArr6[0];
                    AbstractC0081g.y(objArr6, objArr6, 0, 1, f3 + 1);
                }
                this.f1229b[f3] = null;
            }
            this.f1230c--;
            return obj;
        }
        throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int f2;
        AbstractC0150d.e(collection, "elements");
        boolean z2 = false;
        z2 = false;
        z2 = false;
        if (!isEmpty() && this.f1229b.length != 0) {
            int f3 = f(this.f1228a + this.f1230c);
            int i2 = this.f1228a;
            if (i2 < f3) {
                f2 = i2;
                while (i2 < f3) {
                    Object obj = this.f1229b[i2];
                    if (!collection.contains(obj)) {
                        this.f1229b[f2] = obj;
                        f2++;
                    } else {
                        z2 = true;
                    }
                    i2++;
                }
                AbstractC0081g.A(this.f1229b, f2, f3);
            } else {
                int length = this.f1229b.length;
                boolean z3 = false;
                int i3 = i2;
                while (i2 < length) {
                    Object[] objArr = this.f1229b;
                    Object obj2 = objArr[i2];
                    objArr[i2] = null;
                    if (!collection.contains(obj2)) {
                        this.f1229b[i3] = obj2;
                        i3++;
                    } else {
                        z3 = true;
                    }
                    i2++;
                }
                f2 = f(i3);
                for (int i4 = 0; i4 < f3; i4++) {
                    Object[] objArr2 = this.f1229b;
                    Object obj3 = objArr2[i4];
                    objArr2[i4] = null;
                    if (!collection.contains(obj3)) {
                        this.f1229b[f2] = obj3;
                        f2 = c(f2);
                    } else {
                        z3 = true;
                    }
                }
                z2 = z3;
            }
            if (z2) {
                g();
                this.f1230c = d(f2 - this.f1228a);
            }
        }
        return z2;
    }

    public final Object removeFirst() {
        if (!isEmpty()) {
            g();
            Object[] objArr = this.f1229b;
            int i2 = this.f1228a;
            Object obj = objArr[i2];
            objArr[i2] = null;
            this.f1228a = c(i2);
            this.f1230c--;
            return obj;
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    public final Object removeLast() {
        if (!isEmpty()) {
            g();
            int f2 = f((size() - 1) + this.f1228a);
            Object[] objArr = this.f1229b;
            Object obj = objArr[f2];
            objArr[f2] = null;
            this.f1230c--;
            return obj;
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i2, int i3) {
        C0.f.g(i2, i3, this.f1230c);
        int i4 = i3 - i2;
        if (i4 == 0) {
            return;
        }
        if (i4 == this.f1230c) {
            clear();
            return;
        }
        if (i4 == 1) {
            remove(i2);
            return;
        }
        g();
        if (i2 < this.f1230c - i3) {
            int f2 = f((i2 - 1) + this.f1228a);
            int f3 = f((i3 - 1) + this.f1228a);
            while (i2 > 0) {
                int i5 = f2 + 1;
                int min = Math.min(i2, Math.min(i5, f3 + 1));
                Object[] objArr = this.f1229b;
                int i6 = f3 - min;
                int i7 = f2 - min;
                AbstractC0081g.y(objArr, objArr, i6 + 1, i7 + 1, i5);
                f2 = d(i7);
                f3 = d(i6);
                i2 -= min;
            }
            int f4 = f(this.f1228a + i4);
            e(this.f1228a, f4);
            this.f1228a = f4;
        } else {
            int f5 = f(this.f1228a + i3);
            int f6 = f(this.f1228a + i2);
            int i8 = this.f1230c;
            while (true) {
                i8 -= i3;
                if (i8 <= 0) {
                    break;
                }
                Object[] objArr2 = this.f1229b;
                i3 = Math.min(i8, Math.min(objArr2.length - f5, objArr2.length - f6));
                Object[] objArr3 = this.f1229b;
                int i9 = f5 + i3;
                AbstractC0081g.y(objArr3, objArr3, f6, f5, i9);
                f5 = f(i9);
                f6 = f(f6 + i3);
            }
            int f7 = f(this.f1228a + this.f1230c);
            e(d(f7 - i4), f7);
        }
        this.f1230c -= i4;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int f2;
        AbstractC0150d.e(collection, "elements");
        boolean z2 = false;
        z2 = false;
        z2 = false;
        if (!isEmpty() && this.f1229b.length != 0) {
            int f3 = f(this.f1228a + this.f1230c);
            int i2 = this.f1228a;
            if (i2 < f3) {
                f2 = i2;
                while (i2 < f3) {
                    Object obj = this.f1229b[i2];
                    if (collection.contains(obj)) {
                        this.f1229b[f2] = obj;
                        f2++;
                    } else {
                        z2 = true;
                    }
                    i2++;
                }
                AbstractC0081g.A(this.f1229b, f2, f3);
            } else {
                int length = this.f1229b.length;
                boolean z3 = false;
                int i3 = i2;
                while (i2 < length) {
                    Object[] objArr = this.f1229b;
                    Object obj2 = objArr[i2];
                    objArr[i2] = null;
                    if (collection.contains(obj2)) {
                        this.f1229b[i3] = obj2;
                        i3++;
                    } else {
                        z3 = true;
                    }
                    i2++;
                }
                f2 = f(i3);
                for (int i4 = 0; i4 < f3; i4++) {
                    Object[] objArr2 = this.f1229b;
                    Object obj3 = objArr2[i4];
                    objArr2[i4] = null;
                    if (collection.contains(obj3)) {
                        this.f1229b[f2] = obj3;
                        f2 = c(f2);
                    } else {
                        z3 = true;
                    }
                }
                z2 = z3;
            }
            if (z2) {
                g();
                this.f1230c = d(f2 - this.f1228a);
            }
        }
        return z2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i2, Object obj) {
        int i3 = this.f1230c;
        if (i2 >= 0 && i2 < i3) {
            int f2 = f(this.f1228a + i2);
            Object[] objArr = this.f1229b;
            Object obj2 = objArr[f2];
            objArr[f2] = obj;
            return obj2;
        }
        throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f1230c;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[this.f1230c]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        AbstractC0150d.e(objArr, "array");
        int length = objArr.length;
        int i2 = this.f1230c;
        if (length < i2) {
            Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), i2);
            AbstractC0150d.c(newInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
            objArr = (Object[]) newInstance;
        }
        int f2 = f(this.f1228a + this.f1230c);
        int i3 = this.f1228a;
        if (i3 < f2) {
            AbstractC0081g.z(this.f1229b, objArr, i3, f2, 2);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.f1229b;
            AbstractC0081g.y(objArr2, objArr, 0, this.f1228a, objArr2.length);
            Object[] objArr3 = this.f1229b;
            AbstractC0081g.y(objArr3, objArr, objArr3.length - this.f1228a, 0, f2);
        }
        int i4 = this.f1230c;
        if (i4 < objArr.length) {
            objArr[i4] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        AbstractC0150d.e(collection, "elements");
        if (collection.isEmpty()) {
            return false;
        }
        g();
        b(collection.size() + this.f1230c);
        a(f(this.f1228a + this.f1230c), collection);
        return true;
    }
}

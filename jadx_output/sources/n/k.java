package n;

import b0.AbstractC0081g;
import j0.AbstractC0150d;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;
import o.AbstractC0228a;

/* loaded from: classes.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    public int[] f2125a;

    /* renamed from: b, reason: collision with root package name */
    public Object[] f2126b;

    /* renamed from: c, reason: collision with root package name */
    public int f2127c;

    public k(int i2) {
        int[] iArr;
        Object[] objArr;
        if (i2 == 0) {
            iArr = AbstractC0228a.f2139a;
        } else {
            iArr = new int[i2];
        }
        this.f2125a = iArr;
        if (i2 == 0) {
            objArr = AbstractC0228a.f2140b;
        } else {
            objArr = new Object[i2 << 1];
        }
        this.f2126b = objArr;
    }

    public final int a(Object obj) {
        int i2 = this.f2127c * 2;
        Object[] objArr = this.f2126b;
        if (obj == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
            return -1;
        }
        for (int i4 = 1; i4 < i2; i4 += 2) {
            if (obj.equals(objArr[i4])) {
                return i4 >> 1;
            }
        }
        return -1;
    }

    public final int b(int i2, Object obj) {
        int i3 = this.f2127c;
        if (i3 == 0) {
            return -1;
        }
        int a2 = AbstractC0228a.a(i3, i2, this.f2125a);
        if (a2 < 0) {
            return a2;
        }
        if (AbstractC0150d.a(obj, this.f2126b[a2 << 1])) {
            return a2;
        }
        int i4 = a2 + 1;
        while (i4 < i3 && this.f2125a[i4] == i2) {
            if (AbstractC0150d.a(obj, this.f2126b[i4 << 1])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = a2 - 1; i5 >= 0 && this.f2125a[i5] == i2; i5--) {
            if (AbstractC0150d.a(obj, this.f2126b[i5 << 1])) {
                return i5;
            }
        }
        return ~i4;
    }

    public final int c(Object obj) {
        if (obj == null) {
            return d();
        }
        return b(obj.hashCode(), obj);
    }

    public final void clear() {
        if (this.f2127c > 0) {
            this.f2125a = AbstractC0228a.f2139a;
            this.f2126b = AbstractC0228a.f2140b;
            this.f2127c = 0;
        }
        if (this.f2127c <= 0) {
        } else {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        if (c(obj) >= 0) {
            return true;
        }
        return false;
    }

    public boolean containsValue(Object obj) {
        if (a(obj) >= 0) {
            return true;
        }
        return false;
    }

    public final int d() {
        int i2 = this.f2127c;
        if (i2 == 0) {
            return -1;
        }
        int a2 = AbstractC0228a.a(i2, 0, this.f2125a);
        if (a2 < 0) {
            return a2;
        }
        if (this.f2126b[a2 << 1] == null) {
            return a2;
        }
        int i3 = a2 + 1;
        while (i3 < i2 && this.f2125a[i3] == 0) {
            if (this.f2126b[i3 << 1] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = a2 - 1; i4 >= 0 && this.f2125a[i4] == 0; i4--) {
            if (this.f2126b[i4 << 1] == null) {
                return i4;
            }
        }
        return ~i3;
    }

    public final Object e(int i2) {
        if (i2 >= 0 && i2 < this.f2127c) {
            return this.f2126b[i2 << 1];
        }
        throw new IllegalArgumentException(A.e.a("Expected index to be within 0..size()-1, but was ", i2).toString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof k) {
                int i2 = this.f2127c;
                if (i2 != ((k) obj).f2127c) {
                    return false;
                }
                k kVar = (k) obj;
                for (int i3 = 0; i3 < i2; i3++) {
                    Object e2 = e(i3);
                    Object h = h(i3);
                    Object obj2 = kVar.get(e2);
                    if (h == null) {
                        if (obj2 != null || !kVar.containsKey(e2)) {
                            return false;
                        }
                    } else if (!h.equals(obj2)) {
                        return false;
                    }
                }
                return true;
            }
            if (!(obj instanceof Map) || this.f2127c != ((Map) obj).size()) {
                return false;
            }
            int i4 = this.f2127c;
            for (int i5 = 0; i5 < i4; i5++) {
                Object e3 = e(i5);
                Object h2 = h(i5);
                Object obj3 = ((Map) obj).get(e3);
                if (h2 == null) {
                    if (obj3 != null || !((Map) obj).containsKey(e3)) {
                        return false;
                    }
                } else if (!h2.equals(obj3)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public final Object f(int i2) {
        int i3;
        if (i2 >= 0 && i2 < (i3 = this.f2127c)) {
            Object[] objArr = this.f2126b;
            int i4 = i2 << 1;
            Object obj = objArr[i4 + 1];
            if (i3 <= 1) {
                clear();
            } else {
                int i5 = i3 - 1;
                int[] iArr = this.f2125a;
                int i6 = 8;
                if (iArr.length > 8 && i3 < iArr.length / 3) {
                    if (i3 > 8) {
                        i6 = i3 + (i3 >> 1);
                    }
                    int[] copyOf = Arrays.copyOf(iArr, i6);
                    AbstractC0150d.d(copyOf, "copyOf(this, newSize)");
                    this.f2125a = copyOf;
                    Object[] copyOf2 = Arrays.copyOf(this.f2126b, i6 << 1);
                    AbstractC0150d.d(copyOf2, "copyOf(this, newSize)");
                    this.f2126b = copyOf2;
                    if (i3 == this.f2127c) {
                        if (i2 > 0) {
                            AbstractC0081g.w(0, 0, i2, iArr, this.f2125a);
                            AbstractC0081g.y(objArr, this.f2126b, 0, 0, i4);
                        }
                        if (i2 < i5) {
                            int i7 = i2 + 1;
                            AbstractC0081g.w(i2, i7, i3, iArr, this.f2125a);
                            AbstractC0081g.y(objArr, this.f2126b, i4, i7 << 1, i3 << 1);
                        }
                    } else {
                        throw new ConcurrentModificationException();
                    }
                } else {
                    if (i2 < i5) {
                        int i8 = i2 + 1;
                        AbstractC0081g.w(i2, i8, i3, iArr, iArr);
                        Object[] objArr2 = this.f2126b;
                        AbstractC0081g.y(objArr2, objArr2, i4, i8 << 1, i3 << 1);
                    }
                    Object[] objArr3 = this.f2126b;
                    int i9 = i5 << 1;
                    objArr3[i9] = null;
                    objArr3[i9 + 1] = null;
                }
                if (i3 == this.f2127c) {
                    this.f2127c = i5;
                } else {
                    throw new ConcurrentModificationException();
                }
            }
            return obj;
        }
        throw new IllegalArgumentException(A.e.a("Expected index to be within 0..size()-1, but was ", i2).toString());
    }

    public final Object g(int i2, Object obj) {
        if (i2 >= 0 && i2 < this.f2127c) {
            int i3 = (i2 << 1) + 1;
            Object[] objArr = this.f2126b;
            Object obj2 = objArr[i3];
            objArr[i3] = obj;
            return obj2;
        }
        throw new IllegalArgumentException(A.e.a("Expected index to be within 0..size()-1, but was ", i2).toString());
    }

    public Object get(Object obj) {
        int c2 = c(obj);
        if (c2 >= 0) {
            return this.f2126b[(c2 << 1) + 1];
        }
        return null;
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        int c2 = c(obj);
        if (c2 >= 0) {
            return this.f2126b[(c2 << 1) + 1];
        }
        return obj2;
    }

    public final Object h(int i2) {
        if (i2 >= 0 && i2 < this.f2127c) {
            return this.f2126b[(i2 << 1) + 1];
        }
        throw new IllegalArgumentException(A.e.a("Expected index to be within 0..size()-1, but was ", i2).toString());
    }

    public final int hashCode() {
        int i2;
        int[] iArr = this.f2125a;
        Object[] objArr = this.f2126b;
        int i3 = this.f2127c;
        int i4 = 1;
        int i5 = 0;
        int i6 = 0;
        while (i5 < i3) {
            Object obj = objArr[i4];
            int i7 = iArr[i5];
            if (obj != null) {
                i2 = obj.hashCode();
            } else {
                i2 = 0;
            }
            i6 += i2 ^ i7;
            i5++;
            i4 += 2;
        }
        return i6;
    }

    public final boolean isEmpty() {
        if (this.f2127c <= 0) {
            return true;
        }
        return false;
    }

    public final Object put(Object obj, Object obj2) {
        int i2;
        int d2;
        int i3 = this.f2127c;
        if (obj != null) {
            i2 = obj.hashCode();
        } else {
            i2 = 0;
        }
        if (obj != null) {
            d2 = b(i2, obj);
        } else {
            d2 = d();
        }
        if (d2 >= 0) {
            int i4 = (d2 << 1) + 1;
            Object[] objArr = this.f2126b;
            Object obj3 = objArr[i4];
            objArr[i4] = obj2;
            return obj3;
        }
        int i5 = ~d2;
        int[] iArr = this.f2125a;
        if (i3 >= iArr.length) {
            int i6 = 8;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 < 4) {
                i6 = 4;
            }
            int[] copyOf = Arrays.copyOf(iArr, i6);
            AbstractC0150d.d(copyOf, "copyOf(this, newSize)");
            this.f2125a = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.f2126b, i6 << 1);
            AbstractC0150d.d(copyOf2, "copyOf(this, newSize)");
            this.f2126b = copyOf2;
            if (i3 != this.f2127c) {
                throw new ConcurrentModificationException();
            }
        }
        if (i5 < i3) {
            int[] iArr2 = this.f2125a;
            int i7 = i5 + 1;
            AbstractC0081g.w(i7, i5, i3, iArr2, iArr2);
            Object[] objArr2 = this.f2126b;
            AbstractC0081g.y(objArr2, objArr2, i7 << 1, i5 << 1, this.f2127c << 1);
        }
        int i8 = this.f2127c;
        if (i3 == i8) {
            int[] iArr3 = this.f2125a;
            if (i5 < iArr3.length) {
                iArr3[i5] = i2;
                Object[] objArr3 = this.f2126b;
                int i9 = i5 << 1;
                objArr3[i9] = obj;
                objArr3[i9 + 1] = obj2;
                this.f2127c = i8 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final Object putIfAbsent(Object obj, Object obj2) {
        Object obj3 = get(obj);
        if (obj3 == null) {
            return put(obj, obj2);
        }
        return obj3;
    }

    public Object remove(Object obj) {
        int c2 = c(obj);
        if (c2 >= 0) {
            return f(c2);
        }
        return null;
    }

    public final Object replace(Object obj, Object obj2) {
        int c2 = c(obj);
        if (c2 >= 0) {
            return g(c2, obj2);
        }
        return null;
    }

    public final int size() {
        return this.f2127c;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f2127c * 28);
        sb.append('{');
        int i2 = this.f2127c;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            Object e2 = e(i3);
            if (e2 != sb) {
                sb.append(e2);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object h = h(i3);
            if (h != sb) {
                sb.append(h);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        AbstractC0150d.d(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    public final boolean remove(Object obj, Object obj2) {
        int c2 = c(obj);
        if (c2 < 0 || !AbstractC0150d.a(obj2, h(c2))) {
            return false;
        }
        f(c2);
        return true;
    }

    public final boolean replace(Object obj, Object obj2, Object obj3) {
        int c2 = c(obj);
        if (c2 < 0 || !AbstractC0150d.a(obj2, h(c2))) {
            return false;
        }
        g(c2, obj3);
        return true;
    }
}

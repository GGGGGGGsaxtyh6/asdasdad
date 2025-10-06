package n;

import b0.AbstractC0081g;
import j0.AbstractC0150d;
import java.util.Arrays;
import o.AbstractC0228a;

/* loaded from: classes.dex */
public final class l implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    public /* synthetic */ int[] f2128a;

    /* renamed from: b, reason: collision with root package name */
    public /* synthetic */ Object[] f2129b;

    /* renamed from: c, reason: collision with root package name */
    public /* synthetic */ int f2130c;

    public l() {
        int i2;
        int i3 = 4;
        while (true) {
            i2 = 40;
            if (i3 >= 32) {
                break;
            }
            int i4 = (1 << i3) - 12;
            if (40 <= i4) {
                i2 = i4;
                break;
            }
            i3++;
        }
        int i5 = i2 / 4;
        this.f2128a = new int[i5];
        this.f2129b = new Object[i5];
    }

    public final void a(int i2, Object obj) {
        int i3 = this.f2130c;
        if (i3 != 0 && i2 <= this.f2128a[i3 - 1]) {
            int a2 = AbstractC0228a.a(this.f2130c, i2, this.f2128a);
            if (a2 >= 0) {
                this.f2129b[a2] = obj;
                return;
            }
            int i4 = ~a2;
            int i5 = this.f2130c;
            if (i4 < i5) {
                Object[] objArr = this.f2129b;
                if (objArr[i4] == i.f2118b) {
                    this.f2128a[i4] = i2;
                    objArr[i4] = obj;
                    return;
                }
            }
            if (i5 >= this.f2128a.length) {
                int i6 = (i5 + 1) * 4;
                int i7 = 4;
                while (true) {
                    if (i7 >= 32) {
                        break;
                    }
                    int i8 = (1 << i7) - 12;
                    if (i6 <= i8) {
                        i6 = i8;
                        break;
                    }
                    i7++;
                }
                int i9 = i6 / 4;
                int[] copyOf = Arrays.copyOf(this.f2128a, i9);
                AbstractC0150d.d(copyOf, "copyOf(this, newSize)");
                this.f2128a = copyOf;
                Object[] copyOf2 = Arrays.copyOf(this.f2129b, i9);
                AbstractC0150d.d(copyOf2, "copyOf(this, newSize)");
                this.f2129b = copyOf2;
            }
            int i10 = this.f2130c;
            if (i10 - i4 != 0) {
                int[] iArr = this.f2128a;
                int i11 = i4 + 1;
                AbstractC0081g.w(i11, i4, i10, iArr, iArr);
                Object[] objArr2 = this.f2129b;
                AbstractC0081g.y(objArr2, objArr2, i11, i4, this.f2130c);
            }
            this.f2128a[i4] = i2;
            this.f2129b[i4] = obj;
            this.f2130c++;
            return;
        }
        if (i3 >= this.f2128a.length) {
            int i12 = (i3 + 1) * 4;
            int i13 = 4;
            while (true) {
                if (i13 >= 32) {
                    break;
                }
                int i14 = (1 << i13) - 12;
                if (i12 <= i14) {
                    i12 = i14;
                    break;
                }
                i13++;
            }
            int i15 = i12 / 4;
            int[] copyOf3 = Arrays.copyOf(this.f2128a, i15);
            AbstractC0150d.d(copyOf3, "copyOf(this, newSize)");
            this.f2128a = copyOf3;
            Object[] copyOf4 = Arrays.copyOf(this.f2129b, i15);
            AbstractC0150d.d(copyOf4, "copyOf(this, newSize)");
            this.f2129b = copyOf4;
        }
        this.f2128a[i3] = i2;
        this.f2129b[i3] = obj;
        this.f2130c = i3 + 1;
    }

    public final Object clone() {
        Object clone = super.clone();
        AbstractC0150d.c(clone, "null cannot be cast to non-null type androidx.collection.SparseArrayCompat<E of androidx.collection.SparseArrayCompat>");
        l lVar = (l) clone;
        lVar.f2128a = (int[]) this.f2128a.clone();
        lVar.f2129b = (Object[]) this.f2129b.clone();
        return lVar;
    }

    public final String toString() {
        int i2 = this.f2130c;
        if (i2 <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i2 * 28);
        sb.append('{');
        int i3 = this.f2130c;
        for (int i4 = 0; i4 < i3; i4++) {
            if (i4 > 0) {
                sb.append(", ");
            }
            sb.append(this.f2128a[i4]);
            sb.append('=');
            Object obj = this.f2129b[i4];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        AbstractC0150d.d(sb2, "buffer.toString()");
        return sb2;
    }
}

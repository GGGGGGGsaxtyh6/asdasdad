package n0;

import C0.m;
import java.util.Iterator;
import k0.InterfaceC0215a;

/* loaded from: classes.dex */
public class a implements Iterable, InterfaceC0215a {

    /* renamed from: a, reason: collision with root package name */
    public final int f2131a;

    /* renamed from: b, reason: collision with root package name */
    public final int f2132b;

    /* renamed from: c, reason: collision with root package name */
    public final int f2133c;

    public a(int i2, int i3, int i4) {
        if (i4 != 0) {
            if (i4 != Integer.MIN_VALUE) {
                this.f2131a = i2;
                this.f2132b = m.k(i2, i3, i4);
                this.f2133c = i4;
                return;
            }
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        throw new IllegalArgumentException("Step must be non-zero.");
    }

    public boolean equals(Object obj) {
        if (obj instanceof a) {
            if (!isEmpty() || !((a) obj).isEmpty()) {
                a aVar = (a) obj;
                if (this.f2131a != aVar.f2131a || this.f2132b != aVar.f2132b || this.f2133c != aVar.f2133c) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.f2131a * 31) + this.f2132b) * 31) + this.f2133c;
    }

    public boolean isEmpty() {
        int i2 = this.f2133c;
        int i3 = this.f2132b;
        int i4 = this.f2131a;
        if (i2 > 0) {
            if (i4 <= i3) {
                return false;
            }
        } else if (i4 >= i3) {
            return false;
        }
        return true;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new b(this.f2131a, this.f2132b, this.f2133c);
    }

    public String toString() {
        StringBuilder sb;
        int i2 = this.f2132b;
        int i3 = this.f2131a;
        int i4 = this.f2133c;
        if (i4 > 0) {
            sb = new StringBuilder();
            sb.append(i3);
            sb.append("..");
            sb.append(i2);
            sb.append(" step ");
            sb.append(i4);
        } else {
            sb = new StringBuilder();
            sb.append(i3);
            sb.append(" downTo ");
            sb.append(i2);
            sb.append(" step ");
            sb.append(-i4);
        }
        return sb.toString();
    }
}

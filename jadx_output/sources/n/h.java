package n;

import b0.AbstractC0081g;
import j0.AbstractC0150d;
import java.util.Arrays;
import o.AbstractC0228a;

/* loaded from: classes.dex */
public final class h implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    public /* synthetic */ boolean f2113a;

    /* renamed from: b, reason: collision with root package name */
    public /* synthetic */ long[] f2114b;

    /* renamed from: c, reason: collision with root package name */
    public /* synthetic */ Object[] f2115c;

    /* renamed from: d, reason: collision with root package name */
    public /* synthetic */ int f2116d;

    public final void a(long j2, Object obj) {
        int b2 = AbstractC0228a.b(this.f2114b, this.f2116d, j2);
        if (b2 >= 0) {
            this.f2115c[b2] = obj;
            return;
        }
        int i2 = ~b2;
        int i3 = this.f2116d;
        Object obj2 = i.f2117a;
        if (i2 < i3) {
            Object[] objArr = this.f2115c;
            if (objArr[i2] == obj2) {
                this.f2114b[i2] = j2;
                objArr[i2] = obj;
                return;
            }
        }
        if (this.f2113a) {
            long[] jArr = this.f2114b;
            if (i3 >= jArr.length) {
                Object[] objArr2 = this.f2115c;
                int i4 = 0;
                for (int i5 = 0; i5 < i3; i5++) {
                    Object obj3 = objArr2[i5];
                    if (obj3 != obj2) {
                        if (i5 != i4) {
                            jArr[i4] = jArr[i5];
                            objArr2[i4] = obj3;
                            objArr2[i5] = null;
                        }
                        i4++;
                    }
                }
                this.f2113a = false;
                this.f2116d = i4;
                i2 = ~AbstractC0228a.b(this.f2114b, i4, j2);
            }
        }
        int i6 = this.f2116d;
        if (i6 >= this.f2114b.length) {
            int i7 = (i6 + 1) * 8;
            int i8 = 4;
            while (true) {
                if (i8 >= 32) {
                    break;
                }
                int i9 = (1 << i8) - 12;
                if (i7 <= i9) {
                    i7 = i9;
                    break;
                }
                i8++;
            }
            int i10 = i7 / 8;
            long[] copyOf = Arrays.copyOf(this.f2114b, i10);
            AbstractC0150d.d(copyOf, "copyOf(this, newSize)");
            this.f2114b = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.f2115c, i10);
            AbstractC0150d.d(copyOf2, "copyOf(this, newSize)");
            this.f2115c = copyOf2;
        }
        int i11 = this.f2116d - i2;
        if (i11 != 0) {
            long[] jArr2 = this.f2114b;
            int i12 = i2 + 1;
            AbstractC0150d.e(jArr2, "<this>");
            System.arraycopy(jArr2, i2, jArr2, i12, i11);
            Object[] objArr3 = this.f2115c;
            AbstractC0081g.y(objArr3, objArr3, i12, i2, this.f2116d);
        }
        this.f2114b[i2] = j2;
        this.f2115c[i2] = obj;
        this.f2116d++;
    }

    public final Object clone() {
        Object clone = super.clone();
        AbstractC0150d.c(clone, "null cannot be cast to non-null type androidx.collection.LongSparseArray<E of androidx.collection.LongSparseArray>");
        h hVar = (h) clone;
        hVar.f2114b = (long[]) this.f2114b.clone();
        hVar.f2115c = (Object[]) this.f2115c.clone();
        return hVar;
    }

    public final String toString() {
        int i2;
        int i3;
        boolean z2 = this.f2113a;
        Object obj = i.f2117a;
        if (z2) {
            int i4 = this.f2116d;
            long[] jArr = this.f2114b;
            Object[] objArr = this.f2115c;
            int i5 = 0;
            for (int i6 = 0; i6 < i4; i6++) {
                Object obj2 = objArr[i6];
                if (obj2 != obj) {
                    if (i6 != i5) {
                        jArr[i5] = jArr[i6];
                        objArr[i5] = obj2;
                        objArr[i6] = null;
                    }
                    i5++;
                }
            }
            this.f2113a = false;
            this.f2116d = i5;
        }
        int i7 = this.f2116d;
        if (i7 <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i7 * 28);
        sb.append('{');
        int i8 = this.f2116d;
        for (int i9 = 0; i9 < i8; i9++) {
            if (i9 > 0) {
                sb.append(", ");
            }
            if (i9 >= 0 && i9 < (i2 = this.f2116d)) {
                if (this.f2113a) {
                    long[] jArr2 = this.f2114b;
                    Object[] objArr2 = this.f2115c;
                    int i10 = 0;
                    for (int i11 = 0; i11 < i2; i11++) {
                        Object obj3 = objArr2[i11];
                        if (obj3 != obj) {
                            if (i11 != i10) {
                                jArr2[i10] = jArr2[i11];
                                objArr2[i10] = obj3;
                                objArr2[i11] = null;
                            }
                            i10++;
                        }
                    }
                    this.f2113a = false;
                    this.f2116d = i10;
                }
                sb.append(this.f2114b[i9]);
                sb.append('=');
                if (i9 >= 0 && i9 < (i3 = this.f2116d)) {
                    if (this.f2113a) {
                        long[] jArr3 = this.f2114b;
                        Object[] objArr3 = this.f2115c;
                        int i12 = 0;
                        for (int i13 = 0; i13 < i3; i13++) {
                            Object obj4 = objArr3[i13];
                            if (obj4 != obj) {
                                if (i13 != i12) {
                                    jArr3[i12] = jArr3[i13];
                                    objArr3[i12] = obj4;
                                    objArr3[i13] = null;
                                }
                                i12++;
                            }
                        }
                        this.f2113a = false;
                        this.f2116d = i12;
                    }
                    Object obj5 = this.f2115c[i9];
                    if (obj5 != sb) {
                        sb.append(obj5);
                    } else {
                        sb.append("(this Map)");
                    }
                } else {
                    throw new IllegalArgumentException(A.e.a("Expected index to be within 0..size()-1, but was ", i9).toString());
                }
            } else {
                throw new IllegalArgumentException(A.e.a("Expected index to be within 0..size()-1, but was ", i9).toString());
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        AbstractC0150d.d(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }
}

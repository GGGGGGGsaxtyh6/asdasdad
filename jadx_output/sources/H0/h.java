package H0;

import j0.AbstractC0150d;
import java.io.Serializable;
import java.util.Arrays;

/* loaded from: classes.dex */
public class h implements Serializable, Comparable {

    /* renamed from: d, reason: collision with root package name */
    public static final h f413d = new h(new byte[0]);

    /* renamed from: a, reason: collision with root package name */
    public final byte[] f414a;

    /* renamed from: b, reason: collision with root package name */
    public transient int f415b;

    /* renamed from: c, reason: collision with root package name */
    public transient String f416c;

    public h(byte[] bArr) {
        AbstractC0150d.e(bArr, "data");
        this.f414a = bArr;
    }

    public int a() {
        return this.f414a.length;
    }

    public String b() {
        byte[] bArr = this.f414a;
        char[] cArr = new char[bArr.length * 2];
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            byte b2 = bArr[i2];
            i2++;
            int i4 = i3 + 1;
            char[] cArr2 = I0.b.f465a;
            cArr[i3] = cArr2[(b2 >> 4) & 15];
            i3 += 2;
            cArr[i4] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }

    public byte[] c() {
        return this.f414a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0036, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:?, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0033, code lost:
    
        if (r0 < r1) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002a, code lost:
    
        if (r7 < r8) goto L9;
     */
    @Override // java.lang.Comparable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int compareTo(java.lang.Object r10) {
        /*
            r9 = this;
            H0.h r10 = (H0.h) r10
            java.lang.String r0 = "other"
            j0.AbstractC0150d.e(r10, r0)
            int r0 = r9.a()
            int r1 = r10.a()
            int r2 = java.lang.Math.min(r0, r1)
            r3 = 0
            r4 = r3
        L15:
            r5 = 1
            r6 = -1
            if (r4 >= r2) goto L30
            byte r7 = r9.d(r4)
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r8 = r10.d(r4)
            r8 = r8 & 255(0xff, float:3.57E-43)
            if (r7 != r8) goto L2a
            int r4 = r4 + 1
            goto L15
        L2a:
            if (r7 >= r8) goto L2e
        L2c:
            r3 = r6
            goto L36
        L2e:
            r3 = r5
            goto L36
        L30:
            if (r0 != r1) goto L33
            goto L36
        L33:
            if (r0 >= r1) goto L2e
            goto L2c
        L36:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: H0.h.compareTo(java.lang.Object):int");
    }

    public byte d(int i2) {
        return this.f414a[i2];
    }

    public boolean e(int i2, byte[] bArr, int i3, int i4) {
        AbstractC0150d.e(bArr, "other");
        if (i2 >= 0) {
            byte[] bArr2 = this.f414a;
            if (i2 <= bArr2.length - i4 && i3 >= 0 && i3 <= bArr.length - i4 && C0.f.a(bArr2, i2, i3, bArr, i4)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof h) {
            h hVar = (h) obj;
            int a2 = hVar.a();
            byte[] bArr = this.f414a;
            if (a2 == bArr.length && hVar.e(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public boolean f(h hVar, int i2) {
        AbstractC0150d.e(hVar, "other");
        return hVar.e(0, this.f414a, 0, i2);
    }

    public h g() {
        byte b2;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f414a;
            if (i2 < bArr.length) {
                byte b3 = bArr[i2];
                byte b4 = (byte) 65;
                if (b3 >= b4 && b3 <= (b2 = (byte) 90)) {
                    byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
                    AbstractC0150d.d(copyOf, "java.util.Arrays.copyOf(this, size)");
                    copyOf[i2] = (byte) (b3 + 32);
                    for (int i3 = i2 + 1; i3 < copyOf.length; i3++) {
                        byte b5 = copyOf[i3];
                        if (b5 >= b4 && b5 <= b2) {
                            copyOf[i3] = (byte) (b5 + 32);
                        }
                    }
                    return new h(copyOf);
                }
                i2++;
            } else {
                return this;
            }
        }
    }

    public final String h() {
        String str = this.f416c;
        if (str == null) {
            byte[] c2 = c();
            AbstractC0150d.e(c2, "<this>");
            String str2 = new String(c2, q0.a.f2219a);
            this.f416c = str2;
            return str2;
        }
        return str;
    }

    public int hashCode() {
        int i2 = this.f415b;
        if (i2 == 0) {
            int hashCode = Arrays.hashCode(this.f414a);
            this.f415b = hashCode;
            return hashCode;
        }
        return i2;
    }

    public void i(e eVar, int i2) {
        AbstractC0150d.e(eVar, "buffer");
        eVar.s(this.f414a, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:0x00e8, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0120, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0124, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x00c8, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0163, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x016a, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x015c, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x019b, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x019e, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x01a1, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0130, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x01a4, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x008b, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00b6, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x007a, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00f0, code lost:
    
        if (r6 == 64) goto L180;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            Method dump skipped, instructions count: 630
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: H0.h.toString():java.lang.String");
    }
}

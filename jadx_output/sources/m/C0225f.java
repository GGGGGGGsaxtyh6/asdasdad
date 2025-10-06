package m;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: m.f, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0225f implements Iterable {

    /* renamed from: a, reason: collision with root package name */
    public C0222c f2091a;

    /* renamed from: b, reason: collision with root package name */
    public C0222c f2092b;

    /* renamed from: c, reason: collision with root package name */
    public final WeakHashMap f2093c = new WeakHashMap();

    /* renamed from: d, reason: collision with root package name */
    public int f2094d = 0;

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0048, code lost:
    
        if (r3.hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0050, code lost:
    
        if (((m.C0221b) r7).hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0054, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            if (r7 != r6) goto L4
            return r0
        L4:
            boolean r1 = r7 instanceof m.C0225f
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            m.f r7 = (m.C0225f) r7
            int r1 = r6.f2094d
            int r3 = r7.f2094d
            if (r1 == r3) goto L13
            return r2
        L13:
            java.util.Iterator r1 = r6.iterator()
            java.util.Iterator r7 = r7.iterator()
        L1b:
            r3 = r1
            m.b r3 = (m.C0221b) r3
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L44
            r4 = r7
            m.b r4 = (m.C0221b) r4
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L44
            java.lang.Object r3 = r3.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r4.next()
            if (r3 != 0) goto L3b
            if (r4 != 0) goto L43
        L3b:
            if (r3 == 0) goto L1b
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L1b
        L43:
            return r2
        L44:
            boolean r1 = r3.hasNext()
            if (r1 != 0) goto L53
            m.b r7 = (m.C0221b) r7
            boolean r7 = r7.hasNext()
            if (r7 != 0) goto L53
            goto L54
        L53:
            r0 = r2
        L54:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m.C0225f.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Iterator it = iterator();
        int i2 = 0;
        while (true) {
            C0221b c0221b = (C0221b) it;
            if (c0221b.hasNext()) {
                i2 += ((Map.Entry) c0221b.next()).hashCode();
            } else {
                return i2;
            }
        }
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        C0221b c0221b = new C0221b(this.f2091a, this.f2092b, 0);
        this.f2093c.put(c0221b, Boolean.FALSE);
        return c0221b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator it = iterator();
        while (true) {
            C0221b c0221b = (C0221b) it;
            if (c0221b.hasNext()) {
                sb.append(((Map.Entry) c0221b.next()).toString());
                if (c0221b.hasNext()) {
                    sb.append(", ");
                }
            } else {
                sb.append("]");
                return sb.toString();
            }
        }
    }
}

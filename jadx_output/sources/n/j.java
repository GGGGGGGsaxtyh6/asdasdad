package n;

import A.m;
import j0.AbstractC0150d;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    public final int f2119a;

    /* renamed from: b, reason: collision with root package name */
    public final A.f f2120b;

    /* renamed from: c, reason: collision with root package name */
    public final m f2121c;

    /* renamed from: d, reason: collision with root package name */
    public int f2122d;

    /* renamed from: e, reason: collision with root package name */
    public int f2123e;

    /* renamed from: f, reason: collision with root package name */
    public int f2124f;

    public j(int i2) {
        this.f2119a = i2;
        if (i2 > 0) {
            this.f2120b = new A.f(27);
            this.f2121c = new m(21);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public final Object a(Object obj) {
        AbstractC0150d.e(obj, "key");
        synchronized (this.f2121c) {
            A.f fVar = this.f2120b;
            fVar.getClass();
            Object obj2 = ((LinkedHashMap) fVar.f8b).get(obj);
            if (obj2 != null) {
                this.f2123e++;
                return obj2;
            }
            this.f2124f++;
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b6, code lost:
    
        return r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object b(java.lang.Object r6, java.lang.Object r7) {
        /*
            r5 = this;
            java.lang.String r0 = "key"
            j0.AbstractC0150d.e(r6, r0)
            A.m r0 = r5.f2121c
            monitor-enter(r0)
            int r1 = r5.f2122d     // Catch: java.lang.Throwable -> L24
            int r1 = r1 + 1
            r5.f2122d = r1     // Catch: java.lang.Throwable -> L24
            A.f r1 = r5.f2120b     // Catch: java.lang.Throwable -> L24
            r1.getClass()     // Catch: java.lang.Throwable -> L24
            java.lang.Object r1 = r1.f8b     // Catch: java.lang.Throwable -> L24
            java.util.LinkedHashMap r1 = (java.util.LinkedHashMap) r1     // Catch: java.lang.Throwable -> L24
            java.lang.Object r6 = r1.put(r6, r7)     // Catch: java.lang.Throwable -> L24
            if (r6 == 0) goto L27
            int r7 = r5.f2122d     // Catch: java.lang.Throwable -> L24
            int r7 = r7 + (-1)
            r5.f2122d = r7     // Catch: java.lang.Throwable -> L24
            goto L27
        L24:
            r6 = move-exception
            goto Lc1
        L27:
            monitor-exit(r0)
            int r7 = r5.f2119a
        L2a:
            A.m r0 = r5.f2121c
            monitor-enter(r0)
            int r1 = r5.f2122d     // Catch: java.lang.Throwable -> L42
            if (r1 < 0) goto Lb7
            A.f r1 = r5.f2120b     // Catch: java.lang.Throwable -> L42
            java.lang.Object r1 = r1.f8b     // Catch: java.lang.Throwable -> L42
            java.util.LinkedHashMap r1 = (java.util.LinkedHashMap) r1     // Catch: java.lang.Throwable -> L42
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L42
            if (r1 == 0) goto L45
            int r1 = r5.f2122d     // Catch: java.lang.Throwable -> L42
            if (r1 != 0) goto Lb7
            goto L45
        L42:
            r6 = move-exception
            goto Lbf
        L45:
            int r1 = r5.f2122d     // Catch: java.lang.Throwable -> L42
            if (r1 <= r7) goto Lb5
            A.f r1 = r5.f2120b     // Catch: java.lang.Throwable -> L42
            java.lang.Object r1 = r1.f8b     // Catch: java.lang.Throwable -> L42
            java.util.LinkedHashMap r1 = (java.util.LinkedHashMap) r1     // Catch: java.lang.Throwable -> L42
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L42
            if (r1 == 0) goto L56
            goto Lb5
        L56:
            A.f r1 = r5.f2120b     // Catch: java.lang.Throwable -> L42
            java.lang.Object r1 = r1.f8b     // Catch: java.lang.Throwable -> L42
            java.util.LinkedHashMap r1 = (java.util.LinkedHashMap) r1     // Catch: java.lang.Throwable -> L42
            java.util.Set r1 = r1.entrySet()     // Catch: java.lang.Throwable -> L42
            java.lang.String r2 = "map.entries"
            j0.AbstractC0150d.d(r1, r2)     // Catch: java.lang.Throwable -> L42
            boolean r2 = r1 instanceof java.util.List     // Catch: java.lang.Throwable -> L42
            r3 = 0
            if (r2 == 0) goto L79
            java.util.List r1 = (java.util.List) r1     // Catch: java.lang.Throwable -> L42
            boolean r2 = r1.isEmpty()     // Catch: java.lang.Throwable -> L42
            if (r2 == 0) goto L73
            goto L88
        L73:
            r2 = 0
            java.lang.Object r3 = r1.get(r2)     // Catch: java.lang.Throwable -> L42
            goto L88
        L79:
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L42
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L42
            if (r2 != 0) goto L84
            goto L88
        L84:
            java.lang.Object r3 = r1.next()     // Catch: java.lang.Throwable -> L42
        L88:
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch: java.lang.Throwable -> L42
            if (r3 != 0) goto L8e
            monitor-exit(r0)
            goto Lb6
        L8e:
            java.lang.Object r1 = r3.getKey()     // Catch: java.lang.Throwable -> L42
            java.lang.Object r2 = r3.getValue()     // Catch: java.lang.Throwable -> L42
            A.f r3 = r5.f2120b     // Catch: java.lang.Throwable -> L42
            r3.getClass()     // Catch: java.lang.Throwable -> L42
            java.lang.String r4 = "key"
            j0.AbstractC0150d.e(r1, r4)     // Catch: java.lang.Throwable -> L42
            java.lang.Object r3 = r3.f8b     // Catch: java.lang.Throwable -> L42
            java.util.LinkedHashMap r3 = (java.util.LinkedHashMap) r3     // Catch: java.lang.Throwable -> L42
            r3.remove(r1)     // Catch: java.lang.Throwable -> L42
            int r1 = r5.f2122d     // Catch: java.lang.Throwable -> L42
            java.lang.String r3 = "value"
            j0.AbstractC0150d.e(r2, r3)     // Catch: java.lang.Throwable -> L42
            int r1 = r1 + (-1)
            r5.f2122d = r1     // Catch: java.lang.Throwable -> L42
            monitor-exit(r0)
            goto L2a
        Lb5:
            monitor-exit(r0)
        Lb6:
            return r6
        Lb7:
            java.lang.String r6 = "LruCache.sizeOf() is reporting inconsistent results!"
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L42
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L42
            throw r7     // Catch: java.lang.Throwable -> L42
        Lbf:
            monitor-exit(r0)
            throw r6
        Lc1:
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: n.j.b(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public final String toString() {
        int i2;
        String str;
        synchronized (this.f2121c) {
            try {
                int i3 = this.f2123e;
                int i4 = this.f2124f + i3;
                if (i4 != 0) {
                    i2 = (i3 * 100) / i4;
                } else {
                    i2 = 0;
                }
                str = "LruCache[maxSize=" + this.f2119a + ",hits=" + this.f2123e + ",misses=" + this.f2124f + ",hitRate=" + i2 + "%]";
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }
}

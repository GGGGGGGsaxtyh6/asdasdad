package J0;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class a implements Iterable {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f471a;

    public a() {
        this.f471a = new ArrayList();
    }

    public final void a(Collection collection) {
        ArrayList arrayList = this.f471a;
        arrayList.ensureCapacity(collection.size() + arrayList.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Object m2 = c.m(it.next());
            c.l(m2);
            arrayList.add(m2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final J0.c b(int r5) {
        /*
            r4 = this;
            r0 = 0
            if (r5 < 0) goto L11
            java.util.ArrayList r1 = r4.f471a
            int r2 = r1.size()
            if (r5 < r2) goto Lc
            goto L11
        Lc:
            java.lang.Object r1 = r1.get(r5)
            goto L12
        L11:
            r1 = r0
        L12:
            java.lang.String r2 = "JSONArray["
            if (r1 == 0) goto L29
            boolean r3 = r1 instanceof J0.c
            if (r3 == 0) goto L1d
            J0.c r1 = (J0.c) r1
            return r1
        L1d:
            J0.b r1 = new J0.b
            java.lang.String r3 = "] is not a JSONObject."
            java.lang.String r5 = A.e.b(r2, r5, r3)
            r1.<init>(r5, r0)
            throw r1
        L29:
            J0.b r0 = new J0.b
            java.lang.String r1 = "] not found."
            java.lang.String r5 = A.e.b(r2, r5, r1)
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: J0.a.b(int):J0.c");
    }

    public final void c(StringWriter stringWriter, int i2) {
        ArrayList arrayList = this.f471a;
        try {
            int size = arrayList.size();
            stringWriter.write(91);
            int i3 = 0;
            if (size == 1) {
                try {
                    c.o(stringWriter, arrayList.get(0), i2);
                    stringWriter.write(93);
                } catch (Exception e2) {
                    throw new RuntimeException("Unable to write JSONArray value at index: 0", e2);
                }
            }
            if (size != 0) {
                boolean z2 = false;
                while (i3 < size) {
                    if (z2) {
                        stringWriter.write(44);
                    }
                    c.f(stringWriter, i2);
                    try {
                        c.o(stringWriter, arrayList.get(i3), i2);
                        i3++;
                        z2 = true;
                    } catch (Exception e3) {
                        throw new RuntimeException("Unable to write JSONArray value at index: " + i3, e3);
                    }
                }
                c.f(stringWriter, i2);
            }
            stringWriter.write(93);
        } catch (IOException e4) {
            throw new RuntimeException(e4.getMessage(), e4);
        }
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.f471a.iterator();
    }

    public final String toString() {
        String obj;
        try {
            StringWriter stringWriter = new StringWriter();
            synchronized (stringWriter.getBuffer()) {
                c(stringWriter, 0);
                obj = stringWriter.toString();
            }
            return obj;
        } catch (Exception unused) {
            return null;
        }
    }

    public a(Collection collection) {
        if (collection == null) {
            this.f471a = new ArrayList();
        } else {
            this.f471a = new ArrayList(collection.size());
            a(collection);
        }
    }

    public a(Object obj) {
        this();
        if (obj.getClass().isArray()) {
            boolean isArray = obj.getClass().isArray();
            ArrayList arrayList = this.f471a;
            if (isArray) {
                int length = Array.getLength(obj);
                arrayList.ensureCapacity(arrayList.size() + length);
                for (int i2 = 0; i2 < length; i2++) {
                    Object m2 = c.m(Array.get(obj, i2));
                    c.l(m2);
                    this.f471a.add(m2);
                }
                return;
            }
            if (obj instanceof a) {
                arrayList.addAll(((a) obj).f471a);
                return;
            }
            if (obj instanceof Collection) {
                a((Collection) obj);
                return;
            }
            if (obj instanceof Iterable) {
                Iterator it = ((Iterable) obj).iterator();
                while (it.hasNext()) {
                    Object m3 = c.m(it.next());
                    c.l(m3);
                    this.f471a.add(m3);
                }
                return;
            }
            throw new RuntimeException("JSONArray initial value should be a string or collection or array.");
        }
        throw new RuntimeException("JSONArray initial value should be a string or collection or array.");
    }
}

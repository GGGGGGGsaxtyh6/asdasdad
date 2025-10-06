package q0;

import j0.AbstractC0150d;
import java.util.Iterator;
import java.util.NoSuchElementException;
import k0.InterfaceC0215a;

/* loaded from: classes.dex */
public final class b implements Iterator, InterfaceC0215a {

    /* renamed from: a, reason: collision with root package name */
    public int f2222a = -1;

    /* renamed from: b, reason: collision with root package name */
    public int f2223b;

    /* renamed from: c, reason: collision with root package name */
    public int f2224c;

    /* renamed from: d, reason: collision with root package name */
    public n0.c f2225d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ c f2226e;

    public b(c cVar) {
        this.f2226e = cVar;
        cVar.getClass();
        int length = cVar.f2227a.length();
        if (length >= 0) {
            length = length >= 0 ? 0 : length;
            this.f2223b = length;
            this.f2224c = length;
            return;
        }
        throw new IllegalArgumentException(A.e.b("Cannot coerce value to an empty range: maximum ", length, " is less than minimum 0."));
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0135  */
    /* JADX WARN: Type inference failed for: r1v13, types: [n0.c, n0.a] */
    /* JADX WARN: Type inference failed for: r1v26, types: [n0.c, n0.a] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            Method dump skipped, instructions count: 432
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: q0.b.a():void");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f2222a == -1) {
            a();
        }
        if (this.f2222a == 1) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f2222a == -1) {
            a();
        }
        if (this.f2222a != 0) {
            n0.c cVar = this.f2225d;
            AbstractC0150d.c(cVar, "null cannot be cast to non-null type kotlin.ranges.IntRange");
            this.f2225d = null;
            this.f2222a = -1;
            return cVar;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}

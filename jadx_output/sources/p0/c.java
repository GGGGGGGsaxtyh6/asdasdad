package p0;

import b0.C0089o;
import c.g;
import j0.AbstractC0150d;
import java.util.Iterator;
import java.util.NoSuchElementException;
import k0.InterfaceC0215a;

/* loaded from: classes.dex */
public final class c implements Iterator, InterfaceC0215a {

    /* renamed from: a, reason: collision with root package name */
    public Object f2171a;

    /* renamed from: b, reason: collision with root package name */
    public int f2172b = -2;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ C0089o f2173c;

    public c(C0089o c0089o) {
        this.f2173c = c0089o;
    }

    public final void a() {
        Object a2;
        int i2 = this.f2172b;
        C0089o c0089o = this.f2173c;
        if (i2 == -2) {
            c0089o.getClass();
            a2 = g.f1247b.a();
        } else {
            e eVar = (e) c0089o.f1232b;
            Object obj = this.f2171a;
            AbstractC0150d.b(obj);
            eVar.getClass();
            AbstractC0150d.e(obj, "it");
            a2 = g.f1247b.a();
        }
        this.f2171a = a2;
        this.f2172b = 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f2172b < 0) {
            a();
        }
        if (this.f2172b == 1) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f2172b < 0) {
            a();
        }
        if (this.f2172b != 0) {
            Object obj = this.f2171a;
            AbstractC0150d.c(obj, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
            this.f2172b = -1;
            return obj;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}

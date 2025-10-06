package n;

import java.util.Iterator;
import java.util.NoSuchElementException;
import k0.InterfaceC0215a;

/* renamed from: n.b, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0227b implements Iterator, InterfaceC0215a {

    /* renamed from: a, reason: collision with root package name */
    public int f2096a;

    /* renamed from: b, reason: collision with root package name */
    public int f2097b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f2098c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2099d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2100e;

    public C0227b(int i2) {
        this.f2096a = i2;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f2097b < this.f2096a) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object e2;
        if (hasNext()) {
            int i2 = this.f2097b;
            switch (this.f2099d) {
                case 0:
                    e2 = ((f) this.f2100e).e(i2);
                    break;
                case 1:
                    e2 = ((f) this.f2100e).h(i2);
                    break;
                default:
                    e2 = ((g) this.f2100e).f2111b[i2];
                    break;
            }
            this.f2097b++;
            this.f2098c = true;
            return e2;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (this.f2098c) {
            int i2 = this.f2097b - 1;
            this.f2097b = i2;
            switch (this.f2099d) {
                case 0:
                    ((f) this.f2100e).f(i2);
                    break;
                case 1:
                    ((f) this.f2100e).f(i2);
                    break;
                default:
                    ((g) this.f2100e).a(i2);
                    break;
            }
            this.f2096a--;
            this.f2098c = false;
            return;
        }
        throw new IllegalStateException("Call next() before removing an element.");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C0227b(g gVar) {
        this(gVar.f2112c);
        this.f2099d = 2;
        this.f2100e = gVar;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C0227b(f fVar, int i2) {
        this(fVar.f2127c);
        this.f2099d = i2;
        switch (i2) {
            case 1:
                this.f2100e = fVar;
                this(fVar.f2127c);
                return;
            default:
                this.f2100e = fVar;
                return;
        }
    }
}

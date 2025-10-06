package m;

import java.util.Iterator;

/* renamed from: m.b, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0221b extends AbstractC0224e implements Iterator {

    /* renamed from: a, reason: collision with root package name */
    public C0222c f2081a;

    /* renamed from: b, reason: collision with root package name */
    public C0222c f2082b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f2083c;

    public C0221b(C0222c c0222c, C0222c c0222c2, int i2) {
        this.f2083c = i2;
        this.f2081a = c0222c2;
        this.f2082b = c0222c;
    }

    @Override // m.AbstractC0224e
    public final void a(C0222c c0222c) {
        C0222c c0222c2;
        C0222c c0222c3 = null;
        if (this.f2081a == c0222c && c0222c == this.f2082b) {
            this.f2082b = null;
            this.f2081a = null;
        }
        C0222c c0222c4 = this.f2081a;
        if (c0222c4 == c0222c) {
            switch (this.f2083c) {
                case 0:
                    c0222c2 = c0222c4.f2087d;
                    break;
                default:
                    c0222c2 = c0222c4.f2086c;
                    break;
            }
            this.f2081a = c0222c2;
        }
        C0222c c0222c5 = this.f2082b;
        if (c0222c5 == c0222c) {
            C0222c c0222c6 = this.f2081a;
            if (c0222c5 != c0222c6 && c0222c6 != null) {
                c0222c3 = b(c0222c5);
            }
            this.f2082b = c0222c3;
        }
    }

    public final C0222c b(C0222c c0222c) {
        switch (this.f2083c) {
            case 0:
                return c0222c.f2086c;
            default:
                return c0222c.f2087d;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f2082b != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        C0222c c0222c;
        C0222c c0222c2 = this.f2082b;
        C0222c c0222c3 = this.f2081a;
        if (c0222c2 != c0222c3 && c0222c3 != null) {
            c0222c = b(c0222c2);
        } else {
            c0222c = null;
        }
        this.f2082b = c0222c;
        return c0222c2;
    }
}

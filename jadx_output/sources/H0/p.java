package H0;

import b0.AbstractC0081g;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public final class p {

    /* renamed from: a, reason: collision with root package name */
    public final byte[] f436a;

    /* renamed from: b, reason: collision with root package name */
    public int f437b;

    /* renamed from: c, reason: collision with root package name */
    public int f438c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f439d;

    /* renamed from: e, reason: collision with root package name */
    public final boolean f440e;

    /* renamed from: f, reason: collision with root package name */
    public p f441f;

    /* renamed from: g, reason: collision with root package name */
    public p f442g;

    public p() {
        this.f436a = new byte[8192];
        this.f440e = true;
        this.f439d = false;
    }

    public final p a() {
        p pVar = this.f441f;
        if (pVar == this) {
            pVar = null;
        }
        p pVar2 = this.f442g;
        AbstractC0150d.b(pVar2);
        pVar2.f441f = this.f441f;
        p pVar3 = this.f441f;
        AbstractC0150d.b(pVar3);
        pVar3.f442g = this.f442g;
        this.f441f = null;
        this.f442g = null;
        return pVar;
    }

    public final void b(p pVar) {
        AbstractC0150d.e(pVar, "segment");
        pVar.f442g = this;
        pVar.f441f = this.f441f;
        p pVar2 = this.f441f;
        AbstractC0150d.b(pVar2);
        pVar2.f442g = pVar;
        this.f441f = pVar;
    }

    public final p c() {
        this.f439d = true;
        return new p(this.f436a, this.f437b, this.f438c, true);
    }

    public final void d(p pVar, int i2) {
        AbstractC0150d.e(pVar, "sink");
        if (pVar.f440e) {
            int i3 = pVar.f438c;
            int i4 = i3 + i2;
            byte[] bArr = pVar.f436a;
            if (i4 > 8192) {
                if (!pVar.f439d) {
                    int i5 = pVar.f437b;
                    if (i4 - i5 <= 8192) {
                        AbstractC0081g.x(bArr, 0, i5, bArr, i3);
                        pVar.f438c -= pVar.f437b;
                        pVar.f437b = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            int i6 = pVar.f438c;
            int i7 = this.f437b;
            AbstractC0081g.x(this.f436a, i6, i7, bArr, i7 + i2);
            pVar.f438c += i2;
            this.f437b += i2;
            return;
        }
        throw new IllegalStateException("only owner can write");
    }

    public p(byte[] bArr, int i2, int i3, boolean z2) {
        AbstractC0150d.e(bArr, "data");
        this.f436a = bArr;
        this.f437b = i2;
        this.f438c = i3;
        this.f439d = z2;
        this.f440e = false;
    }
}

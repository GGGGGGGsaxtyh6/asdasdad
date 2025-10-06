package b0;

import java.util.RandomAccess;

/* renamed from: b0.c, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0077c extends AbstractC0078d implements RandomAccess {

    /* renamed from: a, reason: collision with root package name */
    public final AbstractC0078d f1222a;

    /* renamed from: b, reason: collision with root package name */
    public final int f1223b;

    /* renamed from: c, reason: collision with root package name */
    public final int f1224c;

    public C0077c(AbstractC0078d abstractC0078d, int i2, int i3) {
        this.f1222a = abstractC0078d;
        this.f1223b = i2;
        C0.f.g(i2, i3, abstractC0078d.a());
        this.f1224c = i3 - i2;
    }

    @Override // b0.AbstractC0078d
    public final int a() {
        return this.f1224c;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        int i3 = this.f1224c;
        if (i2 >= 0 && i2 < i3) {
            return this.f1222a.get(this.f1223b + i2);
        }
        throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
    }
}

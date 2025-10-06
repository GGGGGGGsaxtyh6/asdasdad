package H0;

import b0.AbstractC0078d;
import java.util.RandomAccess;

/* loaded from: classes.dex */
public final class m extends AbstractC0078d implements RandomAccess {

    /* renamed from: a, reason: collision with root package name */
    public final h[] f428a;

    /* renamed from: b, reason: collision with root package name */
    public final int[] f429b;

    public m(h[] hVarArr, int[] iArr) {
        this.f428a = hVarArr;
        this.f429b = iArr;
    }

    @Override // b0.AbstractC0078d
    public final int a() {
        return this.f428a.length;
    }

    @Override // b0.AbstractC0078d, java.util.List, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof h)) {
            return false;
        }
        return super.contains((h) obj);
    }

    @Override // java.util.List
    public final Object get(int i2) {
        return this.f428a[i2];
    }

    @Override // b0.AbstractC0078d, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof h)) {
            return -1;
        }
        return super.indexOf((h) obj);
    }

    @Override // b0.AbstractC0078d, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof h)) {
            return -1;
        }
        return super.lastIndexOf((h) obj);
    }
}

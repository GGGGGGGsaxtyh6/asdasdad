package m;

import java.util.Map;

/* renamed from: m.c, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0222c implements Map.Entry {

    /* renamed from: a, reason: collision with root package name */
    public final Object f2084a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f2085b;

    /* renamed from: c, reason: collision with root package name */
    public C0222c f2086c;

    /* renamed from: d, reason: collision with root package name */
    public C0222c f2087d;

    public C0222c(Object obj, Object obj2) {
        this.f2084a = obj;
        this.f2085b = obj2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0222c)) {
            return false;
        }
        C0222c c0222c = (C0222c) obj;
        if (this.f2084a.equals(c0222c.f2084a) && this.f2085b.equals(c0222c.f2085b)) {
            return true;
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f2084a;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f2085b;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return this.f2084a.hashCode() ^ this.f2085b.hashCode();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public final String toString() {
        return this.f2084a + "=" + this.f2085b;
    }
}

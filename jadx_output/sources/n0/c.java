package n0;

/* loaded from: classes.dex */
public final class c extends a {

    /* renamed from: d, reason: collision with root package name */
    public static final c f2138d = new a(1, 0, 1);

    @Override // n0.a
    public final boolean equals(Object obj) {
        if (obj instanceof c) {
            if (!isEmpty() || !((c) obj).isEmpty()) {
                c cVar = (c) obj;
                if (this.f2131a == cVar.f2131a) {
                    if (this.f2132b == cVar.f2132b) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // n0.a
    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.f2131a * 31) + this.f2132b;
    }

    @Override // n0.a
    public final boolean isEmpty() {
        if (this.f2131a > this.f2132b) {
            return true;
        }
        return false;
    }

    @Override // n0.a
    public final String toString() {
        return this.f2131a + ".." + this.f2132b;
    }
}

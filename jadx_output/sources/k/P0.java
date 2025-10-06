package k;

/* loaded from: classes.dex */
public final class P0 {

    /* renamed from: a, reason: collision with root package name */
    public int f1822a;

    /* renamed from: b, reason: collision with root package name */
    public int f1823b;

    /* renamed from: c, reason: collision with root package name */
    public int f1824c;

    /* renamed from: d, reason: collision with root package name */
    public int f1825d;

    /* renamed from: e, reason: collision with root package name */
    public int f1826e;

    /* renamed from: f, reason: collision with root package name */
    public int f1827f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f1828g;
    public boolean h;

    public final void a(int i2, int i3) {
        this.f1824c = i2;
        this.f1825d = i3;
        this.h = true;
        if (this.f1828g) {
            if (i3 != Integer.MIN_VALUE) {
                this.f1822a = i3;
            }
            if (i2 != Integer.MIN_VALUE) {
                this.f1823b = i2;
                return;
            }
            return;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f1822a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f1823b = i3;
        }
    }
}

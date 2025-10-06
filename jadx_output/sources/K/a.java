package K;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public int f482a;

    /* renamed from: b, reason: collision with root package name */
    public int f483b;

    /* renamed from: c, reason: collision with root package name */
    public float f484c;

    /* renamed from: d, reason: collision with root package name */
    public float f485d;

    /* renamed from: e, reason: collision with root package name */
    public long f486e;

    /* renamed from: f, reason: collision with root package name */
    public long f487f;

    /* renamed from: g, reason: collision with root package name */
    public long f488g;
    public float h;

    /* renamed from: i, reason: collision with root package name */
    public int f489i;

    public final float a(long j2) {
        long j3 = this.f486e;
        if (j2 < j3) {
            return 0.0f;
        }
        long j4 = this.f488g;
        if (j4 >= 0 && j2 >= j4) {
            float f2 = this.h;
            return (d.b(((float) (j2 - j4)) / this.f489i, 0.0f, 1.0f) * f2) + (1.0f - f2);
        }
        return d.b(((float) (j2 - j3)) / this.f482a, 0.0f, 1.0f) * 0.5f;
    }
}

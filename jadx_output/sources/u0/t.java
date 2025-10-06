package u0;

import j0.AbstractC0150d;

/* loaded from: classes.dex */
public final class t {

    /* renamed from: a, reason: collision with root package name */
    public B0.h f2554a;

    /* renamed from: b, reason: collision with root package name */
    public r f2555b;

    /* renamed from: d, reason: collision with root package name */
    public String f2557d;

    /* renamed from: e, reason: collision with root package name */
    public j f2558e;

    /* renamed from: g, reason: collision with root package name */
    public w f2560g;
    public u h;

    /* renamed from: i, reason: collision with root package name */
    public u f2561i;

    /* renamed from: j, reason: collision with root package name */
    public u f2562j;

    /* renamed from: k, reason: collision with root package name */
    public long f2563k;

    /* renamed from: l, reason: collision with root package name */
    public long f2564l;

    /* renamed from: m, reason: collision with root package name */
    public M.v f2565m;

    /* renamed from: c, reason: collision with root package name */
    public int f2556c = -1;

    /* renamed from: f, reason: collision with root package name */
    public A.f f2559f = new A.f(29);

    public static void b(String str, u uVar) {
        if (uVar != null) {
            if (uVar.f2572g == null) {
                if (uVar.h == null) {
                    if (uVar.f2573i == null) {
                        if (uVar.f2574j == null) {
                            return;
                        } else {
                            throw new IllegalArgumentException(AbstractC0150d.h(".priorResponse != null", str).toString());
                        }
                    }
                    throw new IllegalArgumentException(AbstractC0150d.h(".cacheResponse != null", str).toString());
                }
                throw new IllegalArgumentException(AbstractC0150d.h(".networkResponse != null", str).toString());
            }
            throw new IllegalArgumentException(AbstractC0150d.h(".body != null", str).toString());
        }
    }

    public final u a() {
        int i2 = this.f2556c;
        if (i2 >= 0) {
            B0.h hVar = this.f2554a;
            if (hVar != null) {
                r rVar = this.f2555b;
                if (rVar != null) {
                    String str = this.f2557d;
                    if (str != null) {
                        return new u(hVar, rVar, str, i2, this.f2558e, this.f2559f.A(), this.f2560g, this.h, this.f2561i, this.f2562j, this.f2563k, this.f2564l, this.f2565m);
                    }
                    throw new IllegalStateException("message == null");
                }
                throw new IllegalStateException("protocol == null");
            }
            throw new IllegalStateException("request == null");
        }
        throw new IllegalStateException(AbstractC0150d.h(Integer.valueOf(i2), "code < 0: ").toString());
    }
}

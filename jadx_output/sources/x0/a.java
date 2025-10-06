package x0;

import j0.AbstractC0150d;

/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public final String f2836a;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f2837b;

    /* renamed from: c, reason: collision with root package name */
    public b f2838c;

    /* renamed from: d, reason: collision with root package name */
    public long f2839d;

    public a(boolean z2, String str) {
        AbstractC0150d.e(str, "name");
        this.f2836a = str;
        this.f2837b = z2;
        this.f2839d = -1L;
    }

    public abstract long a();

    public final String toString() {
        return this.f2836a;
    }
}

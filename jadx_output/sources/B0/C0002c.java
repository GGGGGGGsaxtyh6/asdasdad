package B0;

import j0.AbstractC0150d;

/* renamed from: B0.c, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0002c {

    /* renamed from: d, reason: collision with root package name */
    public static final H0.h f89d;

    /* renamed from: e, reason: collision with root package name */
    public static final H0.h f90e;

    /* renamed from: f, reason: collision with root package name */
    public static final H0.h f91f;

    /* renamed from: g, reason: collision with root package name */
    public static final H0.h f92g;
    public static final H0.h h;

    /* renamed from: i, reason: collision with root package name */
    public static final H0.h f93i;

    /* renamed from: a, reason: collision with root package name */
    public final H0.h f94a;

    /* renamed from: b, reason: collision with root package name */
    public final H0.h f95b;

    /* renamed from: c, reason: collision with root package name */
    public final int f96c;

    static {
        H0.h hVar = H0.h.f413d;
        f89d = A.m.e(":");
        f90e = A.m.e(":status");
        f91f = A.m.e(":method");
        f92g = A.m.e(":path");
        h = A.m.e(":scheme");
        f93i = A.m.e(":authority");
    }

    public C0002c(H0.h hVar, H0.h hVar2) {
        AbstractC0150d.e(hVar, "name");
        AbstractC0150d.e(hVar2, "value");
        this.f94a = hVar;
        this.f95b = hVar2;
        this.f96c = hVar2.a() + hVar.a() + 32;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0002c)) {
            return false;
        }
        C0002c c0002c = (C0002c) obj;
        if (AbstractC0150d.a(this.f94a, c0002c.f94a) && AbstractC0150d.a(this.f95b, c0002c.f95b)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f95b.hashCode() + (this.f94a.hashCode() * 31);
    }

    public final String toString() {
        return this.f94a.h() + ": " + this.f95b.h();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C0002c(String str, String str2) {
        this(A.m.e(str), A.m.e(str2));
        AbstractC0150d.e(str, "name");
        AbstractC0150d.e(str2, "value");
        H0.h hVar = H0.h.f413d;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C0002c(H0.h hVar, String str) {
        this(hVar, A.m.e(str));
        AbstractC0150d.e(hVar, "name");
        AbstractC0150d.e(str, "value");
        H0.h hVar2 = H0.h.f413d;
    }
}

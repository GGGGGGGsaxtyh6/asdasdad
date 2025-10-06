package androidx.lifecycle;

import java.lang.reflect.Method;

/* renamed from: androidx.lifecycle.c, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0056c {

    /* renamed from: a, reason: collision with root package name */
    public final int f1186a;

    /* renamed from: b, reason: collision with root package name */
    public final Method f1187b;

    public C0056c(int i2, Method method) {
        this.f1186a = i2;
        this.f1187b = method;
        method.setAccessible(true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0056c)) {
            return false;
        }
        C0056c c0056c = (C0056c) obj;
        if (this.f1186a == c0056c.f1186a && this.f1187b.getName().equals(c0056c.f1187b.getName())) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f1187b.getName().hashCode() + (this.f1186a * 31);
    }
}

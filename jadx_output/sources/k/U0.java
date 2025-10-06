package k;

import j0.AbstractC0150d;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class U0 {

    /* renamed from: a, reason: collision with root package name */
    public boolean f1849a = true;

    /* renamed from: b, reason: collision with root package name */
    public boolean f1850b;

    /* renamed from: c, reason: collision with root package name */
    public Object f1851c;

    /* renamed from: d, reason: collision with root package name */
    public Serializable f1852d;

    public u0.h a() {
        return new u0.h(this.f1849a, this.f1850b, (String[]) this.f1851c, (String[]) this.f1852d);
    }

    public void b(String... strArr) {
        AbstractC0150d.e(strArr, "cipherSuites");
        if (this.f1849a) {
            if (strArr.length != 0) {
                this.f1851c = (String[]) strArr.clone();
                return;
            }
            throw new IllegalArgumentException("At least one cipher suite is required");
        }
        throw new IllegalArgumentException("no cipher suites for cleartext connections");
    }

    public void c(u0.g... gVarArr) {
        AbstractC0150d.e(gVarArr, "cipherSuites");
        if (this.f1849a) {
            ArrayList arrayList = new ArrayList(gVarArr.length);
            for (u0.g gVar : gVarArr) {
                arrayList.add(gVar.f2451a);
            }
            Object[] array = arrayList.toArray(new String[0]);
            if (array != null) {
                String[] strArr = (String[]) array;
                b((String[]) Arrays.copyOf(strArr, strArr.length));
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        throw new IllegalArgumentException("no cipher suites for cleartext connections");
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.String[], java.io.Serializable] */
    public void d(String... strArr) {
        AbstractC0150d.e(strArr, "tlsVersions");
        if (this.f1849a) {
            if (strArr.length != 0) {
                this.f1852d = (String[]) strArr.clone();
                return;
            }
            throw new IllegalArgumentException("At least one TLS version is required");
        }
        throw new IllegalArgumentException("no TLS versions for cleartext connections");
    }

    public void e(u0.y... yVarArr) {
        if (this.f1849a) {
            ArrayList arrayList = new ArrayList(yVarArr.length);
            for (u0.y yVar : yVarArr) {
                arrayList.add(yVar.f2589a);
            }
            Object[] array = arrayList.toArray(new String[0]);
            if (array != null) {
                String[] strArr = (String[]) array;
                d((String[]) Arrays.copyOf(strArr, strArr.length));
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        throw new IllegalArgumentException("no TLS versions for cleartext connections");
    }
}

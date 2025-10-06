package a0;

import i0.InterfaceC0131a;
import j0.AbstractC0150d;
import j0.AbstractC0151e;
import java.io.Serializable;

/* renamed from: a0.d, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0050d implements InterfaceC0048b, Serializable {

    /* renamed from: a, reason: collision with root package name */
    public AbstractC0151e f788a;

    /* renamed from: b, reason: collision with root package name */
    public volatile Object f789b = C0051e.f791b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f790c = this;

    /* JADX WARN: Multi-variable type inference failed */
    public C0050d(InterfaceC0131a interfaceC0131a) {
        this.f788a = (AbstractC0151e) interfaceC0131a;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, i0.a, j0.e] */
    public final Object a() {
        Object obj;
        Object obj2 = this.f789b;
        C0051e c0051e = C0051e.f791b;
        if (obj2 != c0051e) {
            return obj2;
        }
        synchronized (this.f790c) {
            obj = this.f789b;
            if (obj == c0051e) {
                ?? r1 = this.f788a;
                AbstractC0150d.b(r1);
                obj = r1.a();
                this.f789b = obj;
                this.f788a = null;
            }
        }
        return obj;
    }

    public final String toString() {
        if (this.f789b != C0051e.f791b) {
            return String.valueOf(a());
        }
        return "Lazy value not initialized yet.";
    }
}

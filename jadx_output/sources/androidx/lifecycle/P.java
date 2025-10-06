package androidx.lifecycle;

import j0.C0148b;

/* loaded from: classes.dex */
public interface P {
    default N a(Class cls) {
        throw new UnsupportedOperationException("`Factory.create(String, CreationExtras)` is not implemented. You may need to override the method and provide a custom implementation. Note that using `Factory.create(String)` is not supported and considered an error.");
    }

    default N b(C0148b c0148b, Q.c cVar) {
        return c(C0.f.q(c0148b), cVar);
    }

    default N c(Class cls, Q.c cVar) {
        return a(cls);
    }
}

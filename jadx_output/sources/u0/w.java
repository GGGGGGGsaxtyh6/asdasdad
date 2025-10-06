package u0;

import java.io.Closeable;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public abstract class w implements Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        v0.b.c(i());
    }

    public abstract long f();

    public abstract o h();

    public abstract H0.g i();

    public final String j() {
        Charset a2;
        H0.g i2 = i();
        try {
            o h = h();
            if (h == null) {
                a2 = null;
            } else {
                a2 = h.a(q0.a.f2219a);
            }
            if (a2 == null) {
                a2 = q0.a.f2219a;
            }
            String e2 = i2.e(v0.b.r(i2, a2));
            C0.m.b(i2, null);
            return e2;
        } finally {
        }
    }
}

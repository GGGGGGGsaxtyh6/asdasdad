package H0;

import java.io.Closeable;
import java.io.Flushable;

/* loaded from: classes.dex */
public interface s extends Closeable, Flushable {
    v a();

    void c(long j2, e eVar);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void flush();
}

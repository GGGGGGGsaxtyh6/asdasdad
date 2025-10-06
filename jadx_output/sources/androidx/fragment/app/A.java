package androidx.fragment.app;

import android.util.Log;
import java.io.Writer;

/* loaded from: classes.dex */
public final class A extends Writer {

    /* renamed from: b, reason: collision with root package name */
    public final StringBuilder f1015b = new StringBuilder(128);

    /* renamed from: a, reason: collision with root package name */
    public final String f1014a = "FragmentManager";

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        f();
    }

    public final void f() {
        StringBuilder sb = this.f1015b;
        if (sb.length() > 0) {
            Log.d(this.f1014a, sb.toString());
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
        f();
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            char c2 = cArr[i2 + i4];
            if (c2 == '\n') {
                f();
            } else {
                this.f1015b.append(c2);
            }
        }
    }
}

package J0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* loaded from: classes.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public long f475a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f476b;

    /* renamed from: c, reason: collision with root package name */
    public long f477c;

    /* renamed from: d, reason: collision with root package name */
    public long f478d;

    /* renamed from: e, reason: collision with root package name */
    public char f479e;

    /* renamed from: f, reason: collision with root package name */
    public final Reader f480f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f481g;
    public long h;

    public f(String str) {
        Reader stringReader = new StringReader(str);
        this.f480f = stringReader.markSupported() ? stringReader : new BufferedReader(stringReader);
        this.f476b = false;
        this.f481g = false;
        this.f479e = (char) 0;
        this.f477c = 0L;
        this.f475a = 1L;
        this.h = 0L;
        this.f478d = 1L;
    }

    public final void a() {
        if (!this.f481g) {
            long j2 = this.f477c;
            if (j2 > 0) {
                this.f477c = j2 - 1;
                char c2 = this.f479e;
                if (c2 != '\r' && c2 != '\n') {
                    long j3 = this.f475a;
                    if (j3 > 0) {
                        this.f475a = j3 - 1;
                    }
                } else {
                    this.f478d--;
                    this.f475a = this.h;
                }
                this.f481g = true;
                this.f476b = false;
                return;
            }
        }
        throw new RuntimeException("Stepping back two steps is not supported");
    }

    public final char b() {
        int read;
        if (this.f481g) {
            this.f481g = false;
            read = this.f479e;
        } else {
            try {
                read = this.f480f.read();
            } catch (IOException e2) {
                throw new RuntimeException(e2.getMessage(), e2);
            }
        }
        if (read <= 0) {
            this.f476b = true;
            return (char) 0;
        }
        if (read > 0) {
            this.f477c++;
            if (read == 13) {
                this.f478d++;
                this.h = this.f475a;
                this.f475a = 0L;
            } else if (read == 10) {
                if (this.f479e != '\r') {
                    this.f478d++;
                    this.h = this.f475a;
                }
                this.f475a = 0L;
            } else {
                this.f475a++;
            }
        }
        char c2 = (char) read;
        this.f479e = c2;
        return c2;
    }

    public final char c() {
        char b2;
        do {
            b2 = b();
            if (b2 == 0) {
                break;
            }
        } while (b2 <= ' ');
        return b2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:154:0x0165, code lost:
    
        throw e("Substring bounds error");
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x01bf, code lost:
    
        throw e("Unterminated string");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object d() {
        /*
            Method dump skipped, instructions count: 448
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: J0.f.d():java.lang.Object");
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [J0.b, java.lang.RuntimeException] */
    public final b e(String str) {
        return new RuntimeException(str + toString());
    }

    public final String toString() {
        return " at " + this.f477c + " [character " + this.f475a + " line " + this.f478d + "]";
    }
}

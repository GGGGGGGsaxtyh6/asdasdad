package B0;

import b0.AbstractC0081g;
import j0.AbstractC0150d;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public final class B implements Closeable {

    /* renamed from: f, reason: collision with root package name */
    public static final Logger f66f = Logger.getLogger(AbstractC0006g.class.getName());

    /* renamed from: a, reason: collision with root package name */
    public final H0.n f67a;

    /* renamed from: b, reason: collision with root package name */
    public final H0.e f68b;

    /* renamed from: c, reason: collision with root package name */
    public int f69c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f70d;

    /* renamed from: e, reason: collision with root package name */
    public final C0004e f71e;

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, H0.e] */
    public B(H0.n nVar) {
        AbstractC0150d.e(nVar, "sink");
        this.f67a = nVar;
        ?? obj = new Object();
        this.f68b = obj;
        this.f69c = 16384;
        this.f71e = new C0004e(obj);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.f70d = true;
        this.f67a.close();
    }

    public final synchronized void f(F f2) {
        int i2;
        try {
            AbstractC0150d.e(f2, "peerSettings");
            if (!this.f70d) {
                int i3 = this.f69c;
                int i4 = f2.f79a;
                if ((i4 & 32) != 0) {
                    i3 = ((int[]) f2.f80b)[5];
                }
                this.f69c = i3;
                int i5 = -1;
                if ((i4 & 2) != 0) {
                    i2 = ((int[]) f2.f80b)[1];
                } else {
                    i2 = -1;
                }
                if (i2 != -1) {
                    C0004e c0004e = this.f71e;
                    if ((i4 & 2) != 0) {
                        i5 = ((int[]) f2.f80b)[1];
                    }
                    c0004e.getClass();
                    int min = Math.min(i5, 16384);
                    int i6 = c0004e.f107d;
                    if (i6 != min) {
                        if (min < i6) {
                            c0004e.f105b = Math.min(c0004e.f105b, min);
                        }
                        c0004e.f106c = true;
                        c0004e.f107d = min;
                        int i7 = c0004e.h;
                        if (min < i7) {
                            if (min == 0) {
                                C0002c[] c0002cArr = c0004e.f108e;
                                AbstractC0081g.A(c0002cArr, 0, c0002cArr.length);
                                c0004e.f109f = c0004e.f108e.length - 1;
                                c0004e.f110g = 0;
                                c0004e.h = 0;
                            } else {
                                c0004e.a(i7 - min);
                            }
                        }
                    }
                }
                i(0, 0, 4, 1);
                this.f67a.flush();
            } else {
                throw new IOException("closed");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void flush() {
        if (!this.f70d) {
            this.f67a.flush();
        } else {
            throw new IOException("closed");
        }
    }

    public final synchronized void h(boolean z2, int i2, H0.e eVar, int i3) {
        if (!this.f70d) {
            i(i2, i3, 0, z2 ? 1 : 0);
            if (i3 > 0) {
                AbstractC0150d.b(eVar);
                this.f67a.c(i3, eVar);
            }
        } else {
            throw new IOException("closed");
        }
    }

    public final void i(int i2, int i3, int i4, int i5) {
        Level level = Level.FINE;
        Logger logger = f66f;
        if (logger.isLoggable(level)) {
            logger.fine(AbstractC0006g.a(false, i2, i3, i4, i5));
        }
        if (i3 <= this.f69c) {
            if ((Integer.MIN_VALUE & i2) == 0) {
                byte[] bArr = v0.b.f2808a;
                H0.n nVar = this.f67a;
                AbstractC0150d.e(nVar, "<this>");
                nVar.h((i3 >>> 16) & 255);
                nVar.h((i3 >>> 8) & 255);
                nVar.h(i3 & 255);
                nVar.h(i4 & 255);
                nVar.h(i5 & 255);
                nVar.i(i2 & Integer.MAX_VALUE);
                return;
            }
            throw new IllegalArgumentException(AbstractC0150d.h(Integer.valueOf(i2), "reserved bit set: ").toString());
        }
        throw new IllegalArgumentException(("FRAME_SIZE_ERROR length > " + this.f69c + ": " + i3).toString());
    }

    public final synchronized void j(int i2, EnumC0001b enumC0001b, byte[] bArr) {
        if (!this.f70d) {
            if (enumC0001b.f88a != -1) {
                i(0, bArr.length + 8, 7, 0);
                this.f67a.i(i2);
                this.f67a.i(enumC0001b.f88a);
                if (bArr.length != 0) {
                    H0.n nVar = this.f67a;
                    if (!nVar.f432c) {
                        nVar.f431b.s(bArr, bArr.length);
                        nVar.f();
                    } else {
                        throw new IllegalStateException("closed");
                    }
                }
                this.f67a.flush();
            } else {
                throw new IllegalArgumentException("errorCode.httpCode == -1");
            }
        } else {
            throw new IOException("closed");
        }
    }

    public final synchronized void k(boolean z2, int i2, ArrayList arrayList) {
        int i3;
        int i4;
        if (!this.f70d) {
            this.f71e.d(arrayList);
            long j2 = this.f68b.f412b;
            long min = Math.min(this.f69c, j2);
            if (j2 == min) {
                i3 = 4;
            } else {
                i3 = 0;
            }
            if (z2) {
                i3 |= 1;
            }
            i(i2, (int) min, 1, i3);
            this.f67a.c(min, this.f68b);
            if (j2 > min) {
                long j3 = j2 - min;
                while (j3 > 0) {
                    long min2 = Math.min(this.f69c, j3);
                    j3 -= min2;
                    int i5 = (int) min2;
                    if (j3 == 0) {
                        i4 = 4;
                    } else {
                        i4 = 0;
                    }
                    i(i2, i5, 9, i4);
                    this.f67a.c(min2, this.f68b);
                }
            }
        } else {
            throw new IOException("closed");
        }
    }

    public final synchronized void l(int i2, int i3, boolean z2) {
        if (!this.f70d) {
            i(0, 8, 6, z2 ? 1 : 0);
            this.f67a.i(i2);
            this.f67a.i(i3);
            this.f67a.flush();
        } else {
            throw new IOException("closed");
        }
    }

    public final synchronized void m(int i2, EnumC0001b enumC0001b) {
        if (!this.f70d) {
            if (enumC0001b.f88a != -1) {
                i(i2, 4, 3, 0);
                this.f67a.i(enumC0001b.f88a);
                this.f67a.flush();
            } else {
                throw new IllegalArgumentException("Failed requirement.");
            }
        } else {
            throw new IOException("closed");
        }
    }

    public final synchronized void n(long j2, int i2) {
        if (!this.f70d) {
            if (j2 != 0 && j2 <= 2147483647L) {
                i(i2, 4, 8, 0);
                this.f67a.i((int) j2);
                this.f67a.flush();
            } else {
                throw new IllegalArgumentException(AbstractC0150d.h(Long.valueOf(j2), "windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: ").toString());
            }
        } else {
            throw new IOException("closed");
        }
    }
}

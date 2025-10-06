package H0;

import j0.AbstractC0150d;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public final class o implements g {

    /* renamed from: a, reason: collision with root package name */
    public final t f433a;

    /* renamed from: b, reason: collision with root package name */
    public final e f434b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f435c;

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, H0.e] */
    public o(t tVar) {
        AbstractC0150d.e(tVar, "source");
        this.f433a = tVar;
        this.f434b = new Object();
    }

    @Override // H0.t
    public final v a() {
        return this.f433a.a();
    }

    @Override // H0.t
    public final long b(long j2, e eVar) {
        AbstractC0150d.e(eVar, "sink");
        if (j2 >= 0) {
            if (!this.f435c) {
                e eVar2 = this.f434b;
                if (eVar2.f412b == 0 && this.f433a.b(8192L, eVar2) == -1) {
                    return -1L;
                }
                return eVar2.b(Math.min(j2, eVar2.f412b), eVar);
            }
            throw new IllegalStateException("closed");
        }
        throw new IllegalArgumentException(AbstractC0150d.h(Long.valueOf(j2), "byteCount < 0: ").toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() {
        if (!this.f435c) {
            this.f435c = true;
            this.f433a.close();
            e eVar = this.f434b;
            eVar.o(eVar.f412b);
        }
    }

    @Override // H0.g
    public final String e(Charset charset) {
        e eVar = this.f434b;
        eVar.t(this.f433a);
        return eVar.n(eVar.f412b, charset);
    }

    public final boolean f() {
        if (!this.f435c) {
            e eVar = this.f434b;
            if (eVar.f() && this.f433a.b(8192L, eVar) == -1) {
                return true;
            }
            return false;
        }
        throw new IllegalStateException("closed");
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:?, code lost:
    
        return -1;
     */
    @Override // H0.g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int g(H0.m r7) {
        /*
            r6 = this;
            java.lang.String r0 = "options"
            j0.AbstractC0150d.e(r7, r0)
            boolean r0 = r6.f435c
            if (r0 != 0) goto L35
        L9:
            H0.e r0 = r6.f434b
            r1 = 1
            int r1 = I0.a.b(r0, r7, r1)
            r2 = -2
            r3 = -1
            if (r1 == r2) goto L25
            if (r1 == r3) goto L23
            H0.h[] r7 = r7.f428a
            r7 = r7[r1]
            int r7 = r7.a()
            long r2 = (long) r7
            r0.o(r2)
            goto L34
        L23:
            r1 = r3
            goto L34
        L25:
            H0.t r1 = r6.f433a
            r4 = 8192(0x2000, double:4.0474E-320)
            long r0 = r1.b(r4, r0)
            r4 = -1
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 != 0) goto L9
            goto L23
        L34:
            return r1
        L35:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "closed"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: H0.o.g(H0.m):int");
    }

    public final long h(byte b2, long j2, long j3) {
        long j4;
        p pVar;
        o oVar = this;
        long j5 = j3;
        if (!oVar.f435c) {
            long j6 = 0;
            if (0 <= j5) {
                while (j6 < j5) {
                    e eVar = oVar.f434b;
                    eVar.getClass();
                    long j7 = 0;
                    boolean z2 = false;
                    if (0 <= j6 && j6 <= j5) {
                        z2 = true;
                    }
                    if (z2) {
                        long j8 = eVar.f412b;
                        if (j5 > j8) {
                            j4 = j8;
                        } else {
                            j4 = j5;
                        }
                        long j9 = -1;
                        if (j6 != j4 && (pVar = eVar.f411a) != null) {
                            if (j8 - j6 < j6) {
                                while (j8 > j6) {
                                    pVar = pVar.f442g;
                                    AbstractC0150d.b(pVar);
                                    j8 -= pVar.f438c - pVar.f437b;
                                }
                                long j10 = j6;
                                while (true) {
                                    if (j8 >= j4) {
                                        break;
                                    }
                                    int min = (int) Math.min(pVar.f438c, (pVar.f437b + j4) - j8);
                                    for (int i2 = (int) ((pVar.f437b + j10) - j8); i2 < min; i2++) {
                                        if (pVar.f436a[i2] == b2) {
                                            j9 = (i2 - pVar.f437b) + j8;
                                            break;
                                        }
                                    }
                                    j10 = j8 + (pVar.f438c - pVar.f437b);
                                    pVar = pVar.f441f;
                                    AbstractC0150d.b(pVar);
                                    j8 = j10;
                                }
                            } else {
                                while (true) {
                                    long j11 = (pVar.f438c - pVar.f437b) + j7;
                                    if (j11 > j6) {
                                        break;
                                    }
                                    pVar = pVar.f441f;
                                    AbstractC0150d.b(pVar);
                                    j7 = j11;
                                }
                                long j12 = j6;
                                while (true) {
                                    if (j7 >= j4) {
                                        break;
                                    }
                                    int min2 = (int) Math.min(pVar.f438c, (pVar.f437b + j4) - j7);
                                    for (int i3 = (int) ((pVar.f437b + j12) - j7); i3 < min2; i3++) {
                                        if (pVar.f436a[i3] == b2) {
                                            j9 = (i3 - pVar.f437b) + j7;
                                            break;
                                        }
                                    }
                                    j12 = (pVar.f438c - pVar.f437b) + j7;
                                    pVar = pVar.f441f;
                                    AbstractC0150d.b(pVar);
                                    j7 = j12;
                                }
                            }
                        }
                        if (j9 != -1) {
                            return j9;
                        }
                        long j13 = eVar.f412b;
                        if (j13 >= j3) {
                            return -1L;
                        }
                        oVar = this;
                        if (oVar.f433a.b(8192L, eVar) == -1) {
                            return -1L;
                        }
                        j6 = Math.max(j6, j13);
                        j5 = j3;
                    } else {
                        throw new IllegalArgumentException(("size=" + eVar.f412b + " fromIndex=" + j6 + " toIndex=" + j5).toString());
                    }
                }
                return -1L;
            }
            throw new IllegalArgumentException(("fromIndex=0 toIndex=" + j5).toString());
        }
        throw new IllegalStateException("closed");
    }

    public final byte i() {
        q(1L);
        return this.f434b.i();
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.f435c;
    }

    public final h j(long j2) {
        q(j2);
        return this.f434b.k(j2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x0123, code lost:
    
        r14.f412b -= r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0129, code lost:
    
        return r9;
     */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0112  */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Object, H0.e] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long k() {
        /*
            Method dump skipped, instructions count: 304
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: H0.o.k():long");
    }

    public final int l() {
        q(4L);
        return this.f434b.l();
    }

    public final int m() {
        q(4L);
        int l2 = this.f434b.l();
        return ((l2 & 255) << 24) | (((-16777216) & l2) >>> 24) | ((16711680 & l2) >>> 8) | ((65280 & l2) << 8);
    }

    public final short n() {
        q(2L);
        return this.f434b.m();
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Object, H0.e] */
    public final String o(long j2) {
        long j3;
        if (j2 >= 0) {
            if (j2 == Long.MAX_VALUE) {
                j3 = Long.MAX_VALUE;
            } else {
                j3 = j2 + 1;
            }
            byte b2 = (byte) 10;
            long h = h(b2, 0L, j3);
            e eVar = this.f434b;
            if (h != -1) {
                return I0.a.a(h, eVar);
            }
            if (j3 < Long.MAX_VALUE && p(j3) && eVar.h(j3 - 1) == ((byte) 13) && p(1 + j3) && eVar.h(j3) == b2) {
                return I0.a.a(j3, eVar);
            }
            ?? obj = new Object();
            long min = Math.min(32, eVar.f412b);
            long j4 = 0;
            eVar.getClass();
            AbstractC0150d.e(obj, "out");
            C0.f.e(eVar.f412b, 0L, min);
            if (min != 0) {
                obj.f412b += min;
                p pVar = eVar.f411a;
                while (true) {
                    AbstractC0150d.b(pVar);
                    long j5 = pVar.f438c - pVar.f437b;
                    if (j4 < j5) {
                        break;
                    }
                    j4 -= j5;
                    pVar = pVar.f441f;
                }
                while (min > 0) {
                    AbstractC0150d.b(pVar);
                    p c2 = pVar.c();
                    int i2 = c2.f437b + ((int) j4);
                    c2.f437b = i2;
                    c2.f438c = Math.min(i2 + ((int) min), c2.f438c);
                    p pVar2 = obj.f411a;
                    if (pVar2 == null) {
                        c2.f442g = c2;
                        c2.f441f = c2;
                        obj.f411a = c2;
                    } else {
                        p pVar3 = pVar2.f442g;
                        AbstractC0150d.b(pVar3);
                        pVar3.b(c2);
                    }
                    min -= c2.f438c - c2.f437b;
                    pVar = pVar.f441f;
                    j4 = 0;
                }
            }
            throw new EOFException("\\n not found: limit=" + Math.min(eVar.f412b, j2) + " content=" + obj.k(obj.f412b).b() + (char) 8230);
        }
        throw new IllegalArgumentException(AbstractC0150d.h(Long.valueOf(j2), "limit < 0: ").toString());
    }

    public final boolean p(long j2) {
        e eVar;
        if (j2 >= 0) {
            if (this.f435c) {
                throw new IllegalStateException("closed");
            }
            do {
                eVar = this.f434b;
                if (eVar.f412b >= j2) {
                    return true;
                }
            } while (this.f433a.b(8192L, eVar) != -1);
            return false;
        }
        throw new IllegalArgumentException(AbstractC0150d.h(Long.valueOf(j2), "byteCount < 0: ").toString());
    }

    public final void q(long j2) {
        if (p(j2)) {
        } else {
            throw new EOFException();
        }
    }

    public final void r(long j2) {
        if (!this.f435c) {
            while (j2 > 0) {
                e eVar = this.f434b;
                if (eVar.f412b == 0 && this.f433a.b(8192L, eVar) == -1) {
                    throw new EOFException();
                }
                long min = Math.min(j2, eVar.f412b);
                eVar.o(min);
                j2 -= min;
            }
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        AbstractC0150d.e(byteBuffer, "sink");
        e eVar = this.f434b;
        if (eVar.f412b == 0 && this.f433a.b(8192L, eVar) == -1) {
            return -1;
        }
        return eVar.read(byteBuffer);
    }

    public final String toString() {
        return "buffer(" + this.f433a + ')';
    }
}

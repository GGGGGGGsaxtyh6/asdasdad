package B0;

import j0.AbstractC0150d;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.conscrypt.ct.CTConstants;

/* loaded from: classes.dex */
public final class w implements Closeable {

    /* renamed from: d, reason: collision with root package name */
    public static final Logger f184d;

    /* renamed from: a, reason: collision with root package name */
    public final H0.o f185a;

    /* renamed from: b, reason: collision with root package name */
    public final v f186b;

    /* renamed from: c, reason: collision with root package name */
    public final C0003d f187c;

    static {
        Logger logger = Logger.getLogger(AbstractC0006g.class.getName());
        AbstractC0150d.d(logger, "getLogger(Http2::class.java.name)");
        f184d = logger;
    }

    public w(H0.o oVar) {
        AbstractC0150d.e(oVar, "source");
        this.f185a = oVar;
        v vVar = new v(oVar);
        this.f186b = vVar;
        this.f187c = new C0003d(vVar);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f185a.close();
    }

    public final boolean f(boolean z2, m mVar) {
        EnumC0001b enumC0001b;
        int l2;
        Object[] array;
        String h;
        int i2 = 2;
        int i3 = 0;
        AbstractC0150d.e(mVar, "handler");
        try {
            this.f185a.q(9L);
            int s2 = v0.b.s(this.f185a);
            if (s2 <= 16384) {
                int i4 = this.f185a.i() & 255;
                byte i5 = this.f185a.i();
                int i6 = i5 & 255;
                int l3 = this.f185a.l();
                int i7 = Integer.MAX_VALUE & l3;
                Logger logger = f184d;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(AbstractC0006g.a(true, i7, s2, i4, i6));
                }
                if (z2 && i4 != 4) {
                    String[] strArr = AbstractC0006g.f114b;
                    if (i4 < strArr.length) {
                        h = strArr[i4];
                    } else {
                        h = v0.b.h("0x%02x", Integer.valueOf(i4));
                    }
                    throw new IOException(AbstractC0150d.h(h, "Expected a SETTINGS frame but was "));
                }
                EnumC0001b enumC0001b2 = null;
                switch (i4) {
                    case 0:
                        h(mVar, s2, i6, i7);
                        return true;
                    case 1:
                        j(mVar, s2, i6, i7);
                        return true;
                    case 2:
                        if (s2 == 5) {
                            if (i7 != 0) {
                                H0.o oVar = this.f185a;
                                oVar.l();
                                oVar.i();
                                return true;
                            }
                            throw new IOException("TYPE_PRIORITY streamId == 0");
                        }
                        throw new IOException(A.e.b("TYPE_PRIORITY length: ", s2, " != 5"));
                    case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                        if (s2 == 4) {
                            if (i7 != 0) {
                                int l4 = this.f185a.l();
                                EnumC0001b[] values = EnumC0001b.values();
                                int length = values.length;
                                while (true) {
                                    if (i3 < length) {
                                        enumC0001b = values[i3];
                                        if (enumC0001b.f88a != l4) {
                                            i3++;
                                        }
                                    } else {
                                        enumC0001b = null;
                                    }
                                }
                                if (enumC0001b != null) {
                                    s sVar = mVar.f132b;
                                    sVar.getClass();
                                    if (i7 != 0 && (l3 & 1) == 0) {
                                        sVar.f155i.c(new o(sVar.f150c + '[' + i7 + "] onReset", sVar, i7, enumC0001b, 2), 0L);
                                    } else {
                                        A j2 = sVar.j(i7);
                                        if (j2 != null) {
                                            j2.k(enumC0001b);
                                        }
                                    }
                                    return true;
                                }
                                throw new IOException(AbstractC0150d.h(Integer.valueOf(l4), "TYPE_RST_STREAM unexpected error code: "));
                            }
                            throw new IOException("TYPE_RST_STREAM streamId == 0");
                        }
                        throw new IOException(A.e.b("TYPE_RST_STREAM length: ", s2, " != 4"));
                    case 4:
                        if (i7 == 0) {
                            if ((i5 & 1) != 0) {
                                if (s2 != 0) {
                                    throw new IOException("FRAME_SIZE_ERROR ack frame should be empty!");
                                }
                            } else if (s2 % 6 == 0) {
                                F f2 = new F(0);
                                n0.a t2 = C0.m.t(C0.m.v(0, s2), 6);
                                int i8 = t2.f2131a;
                                int i9 = t2.f2132b;
                                int i10 = t2.f2133c;
                                if ((i10 > 0 && i8 <= i9) || (i10 < 0 && i9 <= i8)) {
                                    while (true) {
                                        int i11 = i8 + i10;
                                        H0.o oVar2 = this.f185a;
                                        short n2 = oVar2.n();
                                        byte[] bArr = v0.b.f2808a;
                                        int i12 = n2 & 65535;
                                        l2 = oVar2.l();
                                        if (i12 != 2) {
                                            if (i12 != 3) {
                                                if (i12 != 4) {
                                                    if (i12 == 5 && (l2 < 16384 || l2 > 16777215)) {
                                                    }
                                                } else if (l2 >= 0) {
                                                    i12 = 7;
                                                } else {
                                                    throw new IOException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1");
                                                }
                                            } else {
                                                i12 = 4;
                                            }
                                        } else if (l2 != 0 && l2 != 1) {
                                            throw new IOException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1");
                                        }
                                        f2.f(i12, l2);
                                        if (i8 != i9) {
                                            i8 = i11;
                                        }
                                    }
                                    throw new IOException(AbstractC0150d.h(Integer.valueOf(l2), "PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: "));
                                }
                                s sVar2 = mVar.f132b;
                                sVar2.h.c(new k(AbstractC0150d.h(" applyAndAckSettings", sVar2.f150c), mVar, f2, i2), 0L);
                            } else {
                                throw new IOException(AbstractC0150d.h(Integer.valueOf(s2), "TYPE_SETTINGS length % 6 != 0: "));
                            }
                            return true;
                        }
                        throw new IOException("TYPE_SETTINGS streamId != 0");
                    case 5:
                        k(mVar, s2, i6, i7);
                        return true;
                    case 6:
                        if (s2 == 8) {
                            if (i7 == 0) {
                                int l5 = this.f185a.l();
                                int l6 = this.f185a.l();
                                if ((i5 & 1) != 0) {
                                    s sVar3 = mVar.f132b;
                                    synchronized (sVar3) {
                                        try {
                                            if (l5 != 1) {
                                                if (l5 != 2) {
                                                    if (l5 == 3) {
                                                        sVar3.notifyAll();
                                                    }
                                                } else {
                                                    sVar3.f160n++;
                                                }
                                            } else {
                                                sVar3.f158l++;
                                            }
                                        } catch (Throwable th) {
                                            throw th;
                                        }
                                    }
                                } else {
                                    s sVar4 = mVar.f132b;
                                    sVar4.h.c(new l(AbstractC0150d.h(" ping", sVar4.f150c), mVar.f132b, l5, l6), 0L);
                                }
                                return true;
                            }
                            throw new IOException("TYPE_PING streamId != 0");
                        }
                        throw new IOException(AbstractC0150d.h(Integer.valueOf(s2), "TYPE_PING length != 8: "));
                    case 7:
                        if (s2 >= 8) {
                            if (i7 == 0) {
                                int l7 = this.f185a.l();
                                int l8 = this.f185a.l();
                                int i13 = s2 - 8;
                                EnumC0001b[] values2 = EnumC0001b.values();
                                int length2 = values2.length;
                                int i14 = 0;
                                while (true) {
                                    if (i14 < length2) {
                                        EnumC0001b enumC0001b3 = values2[i14];
                                        if (enumC0001b3.f88a == l8) {
                                            enumC0001b2 = enumC0001b3;
                                        } else {
                                            i14++;
                                        }
                                    }
                                }
                                if (enumC0001b2 != null) {
                                    H0.h hVar = H0.h.f413d;
                                    if (i13 > 0) {
                                        hVar = this.f185a.j(i13);
                                    }
                                    AbstractC0150d.e(hVar, "debugData");
                                    hVar.a();
                                    s sVar5 = mVar.f132b;
                                    synchronized (sVar5) {
                                        array = sVar5.f149b.values().toArray(new A[0]);
                                        if (array != null) {
                                            sVar5.f153f = true;
                                        } else {
                                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                                        }
                                    }
                                    A[] aArr = (A[]) array;
                                    int length3 = aArr.length;
                                    while (i3 < length3) {
                                        A a2 = aArr[i3];
                                        i3++;
                                        if (a2.f53a > l7 && a2.h()) {
                                            a2.k(EnumC0001b.REFUSED_STREAM);
                                            mVar.f132b.j(a2.f53a);
                                        }
                                    }
                                    return true;
                                }
                                throw new IOException(AbstractC0150d.h(Integer.valueOf(l8), "TYPE_GOAWAY unexpected error code: "));
                            }
                            throw new IOException("TYPE_GOAWAY streamId != 0");
                        }
                        throw new IOException(AbstractC0150d.h(Integer.valueOf(s2), "TYPE_GOAWAY length < 8: "));
                    case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                        if (s2 == 4) {
                            long l9 = this.f185a.l() & 2147483647L;
                            if (l9 != 0) {
                                if (i7 == 0) {
                                    s sVar6 = mVar.f132b;
                                    synchronized (sVar6) {
                                        sVar6.f167u += l9;
                                        sVar6.notifyAll();
                                    }
                                } else {
                                    A i15 = mVar.f132b.i(i7);
                                    if (i15 != null) {
                                        synchronized (i15) {
                                            i15.f58f += l9;
                                            if (l9 > 0) {
                                                i15.notifyAll();
                                            }
                                        }
                                    }
                                }
                                return true;
                            }
                            throw new IOException("windowSizeIncrement was 0");
                        }
                        throw new IOException(AbstractC0150d.h(Integer.valueOf(s2), "TYPE_WINDOW_UPDATE length !=4: "));
                    default:
                        this.f185a.r(s2);
                        return true;
                }
            }
            throw new IOException(AbstractC0150d.h(Integer.valueOf(s2), "FRAME_SIZE_ERROR: "));
        } catch (EOFException unused) {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.Object, H0.e] */
    public final void h(m mVar, int i2, int i3, int i4) {
        boolean z2;
        int i5;
        int i6;
        boolean z3;
        A a2;
        boolean z4;
        boolean z5;
        boolean z6;
        long j2;
        if (i4 != 0) {
            if ((i3 & 1) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if ((i3 & 32) == 0) {
                if ((i3 & 8) != 0) {
                    byte i7 = this.f185a.i();
                    byte[] bArr = v0.b.f2808a;
                    i6 = i7 & 255;
                    i5 = i2;
                } else {
                    i5 = i2;
                    i6 = 0;
                }
                int a3 = u.a(i5, i3, i6);
                H0.o oVar = this.f185a;
                mVar.getClass();
                AbstractC0150d.e(oVar, "source");
                mVar.f132b.getClass();
                if (i4 != 0 && (i4 & 1) == 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    s sVar = mVar.f132b;
                    sVar.getClass();
                    ?? obj = new Object();
                    long j3 = a3;
                    oVar.q(j3);
                    oVar.b(j3, obj);
                    sVar.f155i.c(new n(sVar.f150c + '[' + i4 + "] onData", sVar, i4, obj, a3, z2), 0L);
                } else {
                    A i8 = mVar.f132b.i(i4);
                    if (i8 == null) {
                        mVar.f132b.n(i4, EnumC0001b.PROTOCOL_ERROR);
                        long j4 = a3;
                        mVar.f132b.l(j4);
                        oVar.r(j4);
                    } else {
                        byte[] bArr2 = v0.b.f2808a;
                        y yVar = i8.f60i;
                        long j5 = a3;
                        yVar.getClass();
                        while (true) {
                            if (j5 > 0) {
                                synchronized (yVar.f197f) {
                                    z4 = yVar.f193b;
                                    a2 = i8;
                                    if (yVar.f195d.f412b + j5 > yVar.f192a) {
                                        z5 = true;
                                    } else {
                                        z5 = false;
                                    }
                                }
                                if (z5) {
                                    oVar.r(j5);
                                    yVar.f197f.e(EnumC0001b.FLOW_CONTROL_ERROR);
                                    break;
                                }
                                if (z4) {
                                    oVar.r(j5);
                                    break;
                                }
                                long b2 = oVar.b(j5, yVar.f194c);
                                if (b2 != -1) {
                                    j5 -= b2;
                                    A a4 = yVar.f197f;
                                    synchronized (a4) {
                                        if (yVar.f196e) {
                                            H0.e eVar = yVar.f194c;
                                            j2 = eVar.f412b;
                                            eVar.o(j2);
                                        } else {
                                            H0.e eVar2 = yVar.f195d;
                                            if (eVar2.f412b == 0) {
                                                z6 = true;
                                            } else {
                                                z6 = false;
                                            }
                                            eVar2.t(yVar.f194c);
                                            if (z6) {
                                                a4.notifyAll();
                                            }
                                            j2 = 0;
                                        }
                                    }
                                    if (j2 > 0) {
                                        yVar.f(j2);
                                    }
                                    i8 = a2;
                                } else {
                                    throw new EOFException();
                                }
                            } else {
                                a2 = i8;
                                break;
                            }
                        }
                        if (z2) {
                            a2.j(v0.b.f2809b, true);
                        }
                    }
                }
                this.f185a.r(i6);
                return;
            }
            throw new IOException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA");
        }
        throw new IOException("PROTOCOL_ERROR: TYPE_DATA streamId == 0");
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00e4, code lost:
    
        throw new java.io.IOException(j0.AbstractC0150d.h(java.lang.Integer.valueOf(r6.f97a), "Invalid dynamic table size update "));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List i(int r6, int r7, int r8, int r9) {
        /*
            Method dump skipped, instructions count: 301
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: B0.w.i(int, int, int, int):java.util.List");
    }

    public final void j(m mVar, int i2, int i3, int i4) {
        boolean z2;
        int i5;
        int i6 = 1;
        if (i4 != 0) {
            boolean z3 = false;
            if ((i3 & 1) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if ((i3 & 8) != 0) {
                byte i7 = this.f185a.i();
                byte[] bArr = v0.b.f2808a;
                i5 = i7 & 255;
            } else {
                i5 = 0;
            }
            if ((i3 & 32) != 0) {
                H0.o oVar = this.f185a;
                oVar.l();
                oVar.i();
                byte[] bArr2 = v0.b.f2808a;
                mVar.getClass();
                i2 -= 5;
            }
            List i8 = i(u.a(i2, i3, i5), i5, i3, i4);
            mVar.getClass();
            mVar.f132b.getClass();
            if (i4 != 0 && (i4 & 1) == 0) {
                z3 = true;
            }
            if (z3) {
                s sVar = mVar.f132b;
                sVar.getClass();
                sVar.f155i.c(new o(sVar.f150c + '[' + i4 + "] onHeaders", sVar, i4, i8, z2), 0L);
                return;
            }
            s sVar2 = mVar.f132b;
            synchronized (sVar2) {
                A i9 = sVar2.i(i4);
                if (i9 == null) {
                    if (!sVar2.f153f) {
                        if (i4 > sVar2.f151d) {
                            if (i4 % 2 != sVar2.f152e % 2) {
                                A a2 = new A(i4, sVar2, false, z2, v0.b.u(i8));
                                sVar2.f151d = i4;
                                sVar2.f149b.put(Integer.valueOf(i4), a2);
                                sVar2.f154g.e().c(new k(sVar2.f150c + '[' + i4 + "] onStream", sVar2, a2, i6), 0L);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                i9.j(v0.b.u(i8), z2);
                return;
            }
        }
        throw new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
    }

    public final void k(m mVar, int i2, int i3, int i4) {
        int i5;
        if (i4 != 0) {
            if ((i3 & 8) != 0) {
                byte i6 = this.f185a.i();
                byte[] bArr = v0.b.f2808a;
                i5 = i6 & 255;
            } else {
                i5 = 0;
            }
            int l2 = this.f185a.l() & Integer.MAX_VALUE;
            List i7 = i(u.a(i2 - 4, i3, i5), i5, i3, i4);
            mVar.getClass();
            s sVar = mVar.f132b;
            sVar.getClass();
            synchronized (sVar) {
                if (sVar.y.contains(Integer.valueOf(l2))) {
                    sVar.n(l2, EnumC0001b.PROTOCOL_ERROR);
                    return;
                }
                sVar.y.add(Integer.valueOf(l2));
                sVar.f155i.c(new o(sVar.f150c + '[' + l2 + "] onRequest", sVar, l2, i7, 1), 0L);
                return;
            }
        }
        throw new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
    }
}

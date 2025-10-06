package H0;

import j0.AbstractC0150d;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* loaded from: classes.dex */
public final class j implements t {

    /* renamed from: a, reason: collision with root package name */
    public byte f418a;

    /* renamed from: b, reason: collision with root package name */
    public final o f419b;

    /* renamed from: c, reason: collision with root package name */
    public final Inflater f420c;

    /* renamed from: d, reason: collision with root package name */
    public final k f421d;

    /* renamed from: e, reason: collision with root package name */
    public final CRC32 f422e;

    public j(t tVar) {
        AbstractC0150d.e(tVar, "source");
        o oVar = new o(tVar);
        this.f419b = oVar;
        Inflater inflater = new Inflater(true);
        this.f420c = inflater;
        this.f421d = new k(oVar, inflater);
        this.f422e = new CRC32();
    }

    public static void f(String str, int i2, int i3) {
        if (i3 == i2) {
        } else {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(new Object[]{str, Integer.valueOf(i3), Integer.valueOf(i2)}, 3)));
        }
    }

    @Override // H0.t
    public final v a() {
        return this.f419b.f433a.a();
    }

    @Override // H0.t
    public final long b(long j2, e eVar) {
        o oVar;
        boolean z2;
        e eVar2;
        long j3;
        byte b2 = this.f418a;
        CRC32 crc32 = this.f422e;
        o oVar2 = this.f419b;
        if (b2 == 0) {
            oVar2.q(10L);
            e eVar3 = oVar2.f434b;
            byte h = eVar3.h(3L);
            if (((h >> 1) & 1) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                h(eVar3, 0L, 10L);
            }
            f("ID1ID2", 8075, oVar2.n());
            oVar2.r(8L);
            if (((h >> 2) & 1) == 1) {
                oVar2.q(2L);
                if (z2) {
                    h(eVar3, 0L, 2L);
                }
                short m2 = eVar3.m();
                long j4 = (short) (((m2 & 255) << 8) | ((m2 & 65280) >>> 8));
                oVar2.q(j4);
                if (z2) {
                    h(eVar3, 0L, j4);
                    j3 = j4;
                } else {
                    j3 = j4;
                }
                oVar2.r(j3);
            }
            if (((h >> 3) & 1) == 1) {
                eVar2 = eVar3;
                long h2 = oVar2.h((byte) 0, 0L, Long.MAX_VALUE);
                if (h2 != -1) {
                    if (z2) {
                        oVar = oVar2;
                        h(eVar2, 0L, h2 + 1);
                    } else {
                        oVar = oVar2;
                    }
                    oVar.r(h2 + 1);
                } else {
                    throw new EOFException();
                }
            } else {
                oVar = oVar2;
                eVar2 = eVar3;
            }
            if (((h >> 4) & 1) == 1) {
                long h3 = oVar.h((byte) 0, 0L, Long.MAX_VALUE);
                if (h3 != -1) {
                    if (z2) {
                        h(eVar2, 0L, h3 + 1);
                    }
                    oVar.r(h3 + 1);
                } else {
                    throw new EOFException();
                }
            }
            if (z2) {
                oVar.q(2L);
                short m3 = eVar2.m();
                f("FHCRC", (short) (((m3 & 255) << 8) | ((m3 & 65280) >>> 8)), (short) crc32.getValue());
                crc32.reset();
            }
            this.f418a = (byte) 1;
        } else {
            oVar = oVar2;
        }
        if (this.f418a == 1) {
            long j5 = eVar.f412b;
            long b3 = this.f421d.b(8192L, eVar);
            if (b3 != -1) {
                h(eVar, j5, b3);
                return b3;
            }
            this.f418a = (byte) 2;
        }
        if (this.f418a == 2) {
            f("CRC", oVar.m(), (int) crc32.getValue());
            f("ISIZE", oVar.m(), (int) this.f420c.getBytesWritten());
            this.f418a = (byte) 3;
            if (oVar.f()) {
                return -1L;
            }
            throw new IOException("gzip finished without exhausting source");
        }
        return -1L;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f421d.close();
    }

    public final void h(e eVar, long j2, long j3) {
        p pVar = eVar.f411a;
        AbstractC0150d.b(pVar);
        while (true) {
            int i2 = pVar.f438c;
            int i3 = pVar.f437b;
            if (j2 < i2 - i3) {
                break;
            }
            j2 -= i2 - i3;
            pVar = pVar.f441f;
            AbstractC0150d.b(pVar);
        }
        while (j3 > 0) {
            int min = (int) Math.min(pVar.f438c - r6, j3);
            this.f422e.update(pVar.f436a, (int) (pVar.f437b + j2), min);
            j3 -= min;
            pVar = pVar.f441f;
            AbstractC0150d.b(pVar);
            j2 = 0;
        }
    }
}

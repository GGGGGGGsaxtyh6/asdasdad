package H0;

import j0.AbstractC0150d;
import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* loaded from: classes.dex */
public final class k implements t {

    /* renamed from: a, reason: collision with root package name */
    public final o f423a;

    /* renamed from: b, reason: collision with root package name */
    public final Inflater f424b;

    /* renamed from: c, reason: collision with root package name */
    public int f425c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f426d;

    public k(o oVar, Inflater inflater) {
        this.f423a = oVar;
        this.f424b = inflater;
    }

    @Override // H0.t
    public final v a() {
        return this.f423a.f433a.a();
    }

    @Override // H0.t
    public final long b(long j2, e eVar) {
        long j3;
        while (!this.f426d) {
            o oVar = this.f423a;
            Inflater inflater = this.f424b;
            try {
                p q2 = eVar.q(1);
                int min = (int) Math.min(8192L, 8192 - q2.f438c);
                if (inflater.needsInput() && !oVar.f()) {
                    p pVar = oVar.f434b.f411a;
                    AbstractC0150d.b(pVar);
                    int i2 = pVar.f438c;
                    int i3 = pVar.f437b;
                    int i4 = i2 - i3;
                    this.f425c = i4;
                    inflater.setInput(pVar.f436a, i3, i4);
                }
                int inflate = inflater.inflate(q2.f436a, q2.f438c, min);
                int i5 = this.f425c;
                if (i5 != 0) {
                    int remaining = i5 - inflater.getRemaining();
                    this.f425c -= remaining;
                    oVar.r(remaining);
                }
                if (inflate > 0) {
                    q2.f438c += inflate;
                    j3 = inflate;
                    eVar.f412b += j3;
                } else {
                    if (q2.f437b == q2.f438c) {
                        eVar.f411a = q2.a();
                        q.a(q2);
                    }
                    j3 = 0;
                }
                if (j3 > 0) {
                    return j3;
                }
                if (!inflater.finished() && !inflater.needsDictionary()) {
                    if (oVar.f()) {
                        throw new EOFException("source exhausted prematurely");
                    }
                } else {
                    return -1L;
                }
            } catch (DataFormatException e2) {
                throw new IOException(e2);
            }
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f426d) {
            return;
        }
        this.f424b.end();
        this.f426d = true;
        this.f423a.close();
    }
}

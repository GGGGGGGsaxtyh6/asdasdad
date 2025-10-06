package y0;

import H0.s;
import M.v;
import j0.AbstractC0150d;
import java.io.IOException;
import java.net.ProtocolException;

/* loaded from: classes.dex */
public final class c implements s {

    /* renamed from: a, reason: collision with root package name */
    public final s f2860a;

    /* renamed from: b, reason: collision with root package name */
    public final long f2861b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f2862c;

    /* renamed from: d, reason: collision with root package name */
    public long f2863d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f2864e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ v f2865f;

    public c(v vVar, s sVar, long j2) {
        AbstractC0150d.e(vVar, "this$0");
        AbstractC0150d.e(sVar, "delegate");
        this.f2865f = vVar;
        this.f2860a = sVar;
        this.f2861b = j2;
    }

    @Override // H0.s
    public final H0.v a() {
        return this.f2860a.a();
    }

    @Override // H0.s
    public final void c(long j2, H0.e eVar) {
        if (!this.f2864e) {
            long j3 = this.f2861b;
            if (j3 != -1 && this.f2863d + j2 > j3) {
                throw new ProtocolException("expected " + j3 + " bytes but received " + (this.f2863d + j2));
            }
            try {
                this.f2860a.c(j2, eVar);
                this.f2863d += j2;
                return;
            } catch (IOException e2) {
                throw h(e2);
            }
        }
        throw new IllegalStateException("closed");
    }

    @Override // H0.s, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f2864e) {
            return;
        }
        this.f2864e = true;
        long j2 = this.f2861b;
        if (j2 != -1 && this.f2863d != j2) {
            throw new ProtocolException("unexpected end of stream");
        }
        try {
            f();
            h(null);
        } catch (IOException e2) {
            throw h(e2);
        }
    }

    public final void f() {
        this.f2860a.close();
    }

    @Override // H0.s, java.io.Flushable
    public final void flush() {
        try {
            i();
        } catch (IOException e2) {
            throw h(e2);
        }
    }

    public final IOException h(IOException iOException) {
        if (this.f2862c) {
            return iOException;
        }
        this.f2862c = true;
        return this.f2865f.e(false, true, iOException);
    }

    public final void i() {
        this.f2860a.flush();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((Object) c.class.getSimpleName());
        sb.append('(');
        sb.append(this.f2860a);
        sb.append(')');
        return sb.toString();
    }
}

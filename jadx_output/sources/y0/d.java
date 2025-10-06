package y0;

import H0.t;
import M.v;
import j0.AbstractC0150d;
import java.io.IOException;
import java.net.ProtocolException;

/* loaded from: classes.dex */
public final class d implements t {

    /* renamed from: a, reason: collision with root package name */
    public final t f2866a;

    /* renamed from: b, reason: collision with root package name */
    public final long f2867b;

    /* renamed from: c, reason: collision with root package name */
    public long f2868c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f2869d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f2870e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f2871f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ v f2872g;

    public d(v vVar, t tVar, long j2) {
        AbstractC0150d.e(vVar, "this$0");
        AbstractC0150d.e(tVar, "delegate");
        this.f2872g = vVar;
        this.f2866a = tVar;
        this.f2867b = j2;
        this.f2869d = true;
        if (j2 == 0) {
            h(null);
        }
    }

    @Override // H0.t
    public final H0.v a() {
        return this.f2866a.a();
    }

    @Override // H0.t
    public final long b(long j2, H0.e eVar) {
        if (!this.f2871f) {
            try {
                long b2 = this.f2866a.b(8192L, eVar);
                if (this.f2869d) {
                    this.f2869d = false;
                    v vVar = this.f2872g;
                    vVar.getClass();
                    AbstractC0150d.e((h) vVar.f570a, "call");
                }
                if (b2 == -1) {
                    h(null);
                    return -1L;
                }
                long j3 = this.f2868c + b2;
                long j4 = this.f2867b;
                if (j4 != -1 && j3 > j4) {
                    throw new ProtocolException("expected " + j4 + " bytes but received " + j3);
                }
                this.f2868c = j3;
                if (j3 == j4) {
                    h(null);
                }
                return b2;
            } catch (IOException e2) {
                throw h(e2);
            }
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f2871f) {
            return;
        }
        this.f2871f = true;
        try {
            f();
            h(null);
        } catch (IOException e2) {
            throw h(e2);
        }
    }

    public final void f() {
        this.f2866a.close();
    }

    public final IOException h(IOException iOException) {
        if (this.f2870e) {
            return iOException;
        }
        this.f2870e = true;
        v vVar = this.f2872g;
        if (iOException == null && this.f2869d) {
            this.f2869d = false;
            vVar.getClass();
            AbstractC0150d.e((h) vVar.f570a, "call");
        }
        return vVar.e(true, false, iOException);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((Object) d.class.getSimpleName());
        sb.append('(');
        sb.append(this.f2866a);
        sb.append(')');
        return sb.toString();
    }
}

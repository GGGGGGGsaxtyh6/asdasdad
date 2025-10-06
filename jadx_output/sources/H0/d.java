package H0;

import j0.AbstractC0150d;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class d extends v {
    public static final long h;

    /* renamed from: i, reason: collision with root package name */
    public static final long f406i;

    /* renamed from: j, reason: collision with root package name */
    public static d f407j;

    /* renamed from: e, reason: collision with root package name */
    public boolean f408e;

    /* renamed from: f, reason: collision with root package name */
    public d f409f;

    /* renamed from: g, reason: collision with root package name */
    public long f410g;

    static {
        long millis = TimeUnit.SECONDS.toMillis(60L);
        h = millis;
        f406i = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.Object, H0.d] */
    public final void h() {
        d dVar;
        long j2 = this.f451c;
        boolean z2 = this.f449a;
        if (j2 == 0 && !z2) {
            return;
        }
        synchronized (d.class) {
            try {
                if (!this.f408e) {
                    this.f408e = true;
                    if (f407j == null) {
                        f407j = new Object();
                        Thread thread = new Thread("Okio Watchdog");
                        thread.setDaemon(true);
                        thread.start();
                    }
                    long nanoTime = System.nanoTime();
                    if (j2 != 0 && z2) {
                        this.f410g = Math.min(j2, c() - nanoTime) + nanoTime;
                    } else if (j2 != 0) {
                        this.f410g = j2 + nanoTime;
                    } else if (z2) {
                        this.f410g = c();
                    } else {
                        throw new AssertionError();
                    }
                    long j3 = this.f410g - nanoTime;
                    d dVar2 = f407j;
                    AbstractC0150d.b(dVar2);
                    while (true) {
                        dVar = dVar2.f409f;
                        if (dVar == null || j3 < dVar.f410g - nanoTime) {
                            break;
                        } else {
                            dVar2 = dVar;
                        }
                    }
                    this.f409f = dVar;
                    dVar2.f409f = this;
                    if (dVar2 == f407j) {
                        d.class.notify();
                    }
                } else {
                    throw new IllegalStateException("Unbalanced enter/exit");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean i() {
        synchronized (d.class) {
            if (!this.f408e) {
                return false;
            }
            this.f408e = false;
            d dVar = f407j;
            while (dVar != null) {
                d dVar2 = dVar.f409f;
                if (dVar2 == this) {
                    dVar.f409f = this.f409f;
                    this.f409f = null;
                    return false;
                }
                dVar = dVar2;
            }
            return true;
        }
    }

    public void j() {
    }
}

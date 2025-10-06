package p;

import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public abstract class g implements Future {

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f2161d = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));

    /* renamed from: e, reason: collision with root package name */
    public static final Logger f2162e = Logger.getLogger(g.class.getName());

    /* renamed from: f, reason: collision with root package name */
    public static final C0.d f2163f;

    /* renamed from: g, reason: collision with root package name */
    public static final Object f2164g;

    /* renamed from: a, reason: collision with root package name */
    public volatile Object f2165a;

    /* renamed from: b, reason: collision with root package name */
    public volatile c f2166b;

    /* renamed from: c, reason: collision with root package name */
    public volatile f f2167c;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [C0.d] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    static {
        ?? r4;
        try {
            th = null;
            r4 = new d(AtomicReferenceFieldUpdater.newUpdater(f.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(f.class, f.class, "b"), AtomicReferenceFieldUpdater.newUpdater(g.class, f.class, "c"), AtomicReferenceFieldUpdater.newUpdater(g.class, c.class, "b"), AtomicReferenceFieldUpdater.newUpdater(g.class, Object.class, "a"));
        } catch (Throwable th) {
            th = th;
            r4 = new Object();
        }
        f2163f = r4;
        if (th != null) {
            f2162e.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        f2164g = new Object();
    }

    public static void b(g gVar) {
        f fVar;
        c cVar;
        do {
            fVar = gVar.f2167c;
        } while (!f2163f.i(gVar, fVar, f.f2158c));
        while (fVar != null) {
            Thread thread = fVar.f2159a;
            if (thread != null) {
                fVar.f2159a = null;
                LockSupport.unpark(thread);
            }
            fVar = fVar.f2160b;
        }
        do {
            cVar = gVar.f2166b;
        } while (!f2163f.g(gVar, cVar));
        c cVar2 = null;
        while (cVar != null) {
            c cVar3 = cVar.f2152a;
            cVar.f2152a = cVar2;
            cVar2 = cVar;
            cVar = cVar3;
        }
        while (cVar2 != null) {
            cVar2 = cVar2.f2152a;
            try {
                throw null;
                break;
            } catch (RuntimeException e2) {
                f2162e.log(Level.SEVERE, "RuntimeException while executing runnable null with executor null", (Throwable) e2);
            }
        }
    }

    public static Object c(Object obj) {
        if (!(obj instanceof C0229a)) {
            if (!(obj instanceof b)) {
                if (obj == f2164g) {
                    return null;
                }
                return obj;
            }
            ((b) obj).getClass();
            throw new ExecutionException((Throwable) null);
        }
        CancellationException cancellationException = ((C0229a) obj).f2150a;
        CancellationException cancellationException2 = new CancellationException("Task was cancelled.");
        cancellationException2.initCause(cancellationException);
        throw cancellationException2;
    }

    public static Object d(g gVar) {
        Object obj;
        boolean z2 = false;
        while (true) {
            try {
                obj = gVar.get();
                break;
            } catch (InterruptedException unused) {
                z2 = true;
            } catch (Throwable th) {
                if (z2) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z2) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public final void a(StringBuilder sb) {
        String valueOf;
        try {
            Object d2 = d(this);
            sb.append("SUCCESS, result=[");
            if (d2 == this) {
                valueOf = "this future";
            } else {
                valueOf = String.valueOf(d2);
            }
            sb.append(valueOf);
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e3) {
            sb.append("FAILURE, cause=[");
            sb.append(e3.getCause());
            sb.append("]");
        }
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z2) {
        C0229a c0229a;
        Object obj = this.f2165a;
        if (obj != null) {
            return false;
        }
        if (f2161d) {
            c0229a = new C0229a(z2, new CancellationException("Future.cancel() was called."));
        } else if (z2) {
            c0229a = C0229a.f2148b;
        } else {
            c0229a = C0229a.f2149c;
        }
        if (!f2163f.h(this, obj, c0229a)) {
            return false;
        }
        b(this);
        return true;
    }

    public final void e(f fVar) {
        fVar.f2159a = null;
        while (true) {
            f fVar2 = this.f2167c;
            if (fVar2 == f.f2158c) {
                return;
            }
            f fVar3 = null;
            while (fVar2 != null) {
                f fVar4 = fVar2.f2160b;
                if (fVar2.f2159a != null) {
                    fVar3 = fVar2;
                } else if (fVar3 != null) {
                    fVar3.f2160b = fVar4;
                    if (fVar3.f2159a == null) {
                        break;
                    }
                } else if (!f2163f.i(this, fVar2, fVar4)) {
                    break;
                }
                fVar2 = fVar4;
            }
            return;
        }
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j2, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j2);
        if (!Thread.interrupted()) {
            Object obj = this.f2165a;
            if (obj != null) {
                return c(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
            if (nanos >= 1000) {
                f fVar = this.f2167c;
                f fVar2 = f.f2158c;
                if (fVar != fVar2) {
                    f fVar3 = new f();
                    do {
                        C0.d dVar = f2163f;
                        dVar.D(fVar3, fVar);
                        if (dVar.i(this, fVar, fVar3)) {
                            do {
                                LockSupport.parkNanos(this, nanos);
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.f2165a;
                                    if (obj2 != null) {
                                        return c(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    e(fVar3);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            e(fVar3);
                        } else {
                            fVar = this.f2167c;
                        }
                    } while (fVar != fVar2);
                }
                return c(this.f2165a);
            }
            while (nanos > 0) {
                Object obj3 = this.f2165a;
                if (obj3 != null) {
                    return c(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String gVar = toString();
            String obj4 = timeUnit.toString();
            Locale locale = Locale.ROOT;
            String lowerCase = obj4.toLowerCase(locale);
            String str = "Waited " + j2 + " " + timeUnit.toString().toLowerCase(locale);
            if (nanos + 1000 < 0) {
                String c2 = A.e.c(str, " (plus ");
                long j3 = -nanos;
                long convert = timeUnit.convert(j3, TimeUnit.NANOSECONDS);
                long nanos2 = j3 - timeUnit.toNanos(convert);
                boolean z2 = convert == 0 || nanos2 > 1000;
                if (convert > 0) {
                    String str2 = c2 + convert + " " + lowerCase;
                    if (z2) {
                        str2 = A.e.c(str2, ",");
                    }
                    c2 = A.e.c(str2, " ");
                }
                if (z2) {
                    c2 = c2 + nanos2 + " nanoseconds ";
                }
                str = A.e.c(c2, "delay)");
            }
            if (isDone()) {
                throw new TimeoutException(A.e.c(str, " but future completed as timeout expired"));
            }
            throw new TimeoutException(str + " for " + gVar);
        }
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f2165a instanceof C0229a;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        if (this.f2165a != null) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (this.f2165a instanceof C0229a) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            a(sb);
        } else {
            try {
                if (this instanceof ScheduledFuture) {
                    str = "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
                } else {
                    str = null;
                }
            } catch (RuntimeException e2) {
                str = "Exception thrown from implementation: " + e2.getClass();
            }
            if (str != null && !str.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(str);
                sb.append("]");
            } else if (isDone()) {
                a(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.f2165a;
            if (obj2 != null) {
                return c(obj2);
            }
            f fVar = this.f2167c;
            f fVar2 = f.f2158c;
            if (fVar != fVar2) {
                f fVar3 = new f();
                do {
                    C0.d dVar = f2163f;
                    dVar.D(fVar3, fVar);
                    if (dVar.i(this, fVar, fVar3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.f2165a;
                            } else {
                                e(fVar3);
                                throw new InterruptedException();
                            }
                        } while (obj == null);
                        return c(obj);
                    }
                    fVar = this.f2167c;
                } while (fVar != fVar2);
            }
            return c(this.f2165a);
        }
        throw new InterruptedException();
    }
}

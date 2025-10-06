package y0;

import B0.z;
import C0.o;
import M.v;
import j0.AbstractC0150d;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.conscrypt.BuildConfig;
import u0.q;

/* loaded from: classes.dex */
public final class h implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    public final q f2885a;

    /* renamed from: b, reason: collision with root package name */
    public final B0.h f2886b;

    /* renamed from: c, reason: collision with root package name */
    public final l f2887c;

    /* renamed from: d, reason: collision with root package name */
    public final z f2888d;

    /* renamed from: e, reason: collision with root package name */
    public final AtomicBoolean f2889e;

    /* renamed from: f, reason: collision with root package name */
    public Object f2890f;

    /* renamed from: g, reason: collision with root package name */
    public e f2891g;
    public k h;

    /* renamed from: i, reason: collision with root package name */
    public v f2892i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f2893j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f2894k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f2895l;

    /* renamed from: m, reason: collision with root package name */
    public volatile boolean f2896m;

    /* renamed from: n, reason: collision with root package name */
    public volatile v f2897n;

    /* renamed from: o, reason: collision with root package name */
    public volatile k f2898o;

    public h(q qVar, B0.h hVar) {
        AbstractC0150d.e(qVar, "client");
        this.f2885a = qVar;
        this.f2886b = hVar;
        this.f2887c = (l) qVar.f2520b.f8b;
        qVar.f2523e.getClass();
        z zVar = new z(2, this);
        zVar.g(0, TimeUnit.MILLISECONDS);
        this.f2888d = zVar;
        this.f2889e = new AtomicBoolean();
        this.f2895l = true;
    }

    public static final String a(h hVar) {
        String str;
        StringBuilder sb = new StringBuilder();
        if (hVar.f2896m) {
            str = "canceled ";
        } else {
            str = BuildConfig.FLAVOR;
        }
        sb.append(str);
        sb.append("call");
        sb.append(" to ");
        sb.append(((u0.m) hVar.f2886b.f118b).f());
        return sb.toString();
    }

    public final void b(k kVar) {
        byte[] bArr = v0.b.f2808a;
        if (this.h == null) {
            this.h = kVar;
            kVar.f2916p.add(new g(this, this.f2890f));
            return;
        }
        throw new IllegalStateException("Check failed.");
    }

    public final IOException c(IOException iOException) {
        IOException interruptedIOException;
        Socket j2;
        byte[] bArr = v0.b.f2808a;
        k kVar = this.h;
        if (kVar != null) {
            synchronized (kVar) {
                j2 = j();
            }
            if (this.h == null) {
                if (j2 != null) {
                    v0.b.d(j2);
                }
            } else if (j2 != null) {
                throw new IllegalStateException("Check failed.");
            }
        }
        if (!this.f2888d.i()) {
            interruptedIOException = iOException;
        } else {
            interruptedIOException = new InterruptedIOException("timeout");
            if (iOException != null) {
                interruptedIOException.initCause(iOException);
            }
        }
        if (iOException != null) {
            AbstractC0150d.b(interruptedIOException);
        }
        return interruptedIOException;
    }

    public final Object clone() {
        return new h(this.f2885a, this.f2886b);
    }

    public final void d() {
        Socket socket;
        if (this.f2896m) {
            return;
        }
        this.f2896m = true;
        v vVar = this.f2897n;
        if (vVar != null) {
            ((z0.d) vVar.f572c).cancel();
        }
        k kVar = this.f2898o;
        if (kVar != null && (socket = kVar.f2904c) != null) {
            v0.b.d(socket);
        }
    }

    public final void e(u0.d dVar) {
        f fVar;
        if (this.f2889e.compareAndSet(false, true)) {
            o oVar = o.f236a;
            this.f2890f = o.f236a.g();
            v vVar = this.f2885a.f2519a;
            f fVar2 = new f(this, dVar);
            vVar.getClass();
            synchronized (vVar) {
                ((ArrayDeque) vVar.f571b).add(fVar2);
                String str = ((u0.m) this.f2886b.f118b).f2486d;
                Iterator it = ((ArrayDeque) vVar.f572c).iterator();
                while (true) {
                    if (it.hasNext()) {
                        fVar = (f) it.next();
                        if (AbstractC0150d.a(((u0.m) fVar.f2883c.f2886b.f118b).f2486d, str)) {
                            break;
                        }
                    } else {
                        Iterator it2 = ((ArrayDeque) vVar.f571b).iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                fVar = (f) it2.next();
                                if (AbstractC0150d.a(((u0.m) fVar.f2883c.f2886b.f118b).f2486d, str)) {
                                    break;
                                }
                            } else {
                                fVar = null;
                                break;
                            }
                        }
                    }
                }
                if (fVar != null) {
                    fVar2.f2882b = fVar.f2882b;
                }
            }
            vVar.h();
            return;
        }
        throw new IllegalStateException("Already Executed");
    }

    public final void f(boolean z2) {
        v vVar;
        synchronized (this) {
            if (!this.f2895l) {
                throw new IllegalStateException("released");
            }
        }
        if (z2 && (vVar = this.f2897n) != null) {
            ((z0.d) vVar.f572c).cancel();
            ((h) vVar.f570a).h(vVar, true, true, null);
        }
        this.f2892i = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final u0.u g() {
        /*
            r11 = this;
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            u0.q r0 = r11.f2885a
            java.util.List r0 = r0.f2521c
            b0.AbstractC0088n.J(r2, r0)
            z0.a r0 = new z0.a
            u0.q r1 = r11.f2885a
            r0.<init>(r1)
            r2.add(r0)
            z0.a r0 = new z0.a
            u0.q r1 = r11.f2885a
            u0.b r1 = r1.f2527j
            r0.<init>(r1)
            r2.add(r0)
            w0.b r0 = new w0.b
            u0.q r1 = r11.f2885a
            r1.getClass()
            r0.<init>()
            r2.add(r0)
            y0.a r0 = y0.a.f2855a
            r2.add(r0)
            u0.q r0 = r11.f2885a
            java.util.List r0 = r0.f2522d
            b0.AbstractC0088n.J(r2, r0)
            z0.b r0 = new z0.b
            r0.<init>()
            r2.add(r0)
            z0.f r9 = new z0.f
            B0.h r5 = r11.f2886b
            u0.q r0 = r11.f2885a
            int r6 = r0.f2539v
            int r7 = r0.f2540w
            int r8 = r0.f2541x
            r3 = 0
            r4 = 0
            r0 = r9
            r1 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            r0 = 0
            r1 = 0
            B0.h r2 = r11.f2886b     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            u0.u r2 = r9.b(r2)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            boolean r3 = r11.f2896m     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            if (r3 != 0) goto L66
            r11.i(r0)
            return r2
        L66:
            v0.b.c(r2)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            java.io.IOException r2 = new java.io.IOException     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            java.lang.String r3 = "Canceled"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            throw r2     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
        L71:
            r2 = move-exception
            goto L89
        L73:
            r1 = move-exception
            r2 = 1
            java.io.IOException r1 = r11.i(r1)     // Catch: java.lang.Throwable -> L83
            if (r1 != 0) goto L88
            java.lang.NullPointerException r1 = new java.lang.NullPointerException     // Catch: java.lang.Throwable -> L83
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.Throwable"
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L83
            throw r1     // Catch: java.lang.Throwable -> L83
        L83:
            r1 = move-exception
            r10 = r2
            r2 = r1
            r1 = r10
            goto L89
        L88:
            throw r1     // Catch: java.lang.Throwable -> L83
        L89:
            if (r1 != 0) goto L8e
            r11.i(r0)
        L8e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: y0.h.g():u0.u");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0022 A[Catch: all -> 0x0018, TryCatch #1 {all -> 0x0018, blocks: (B:50:0x0013, B:12:0x0022, B:14:0x0026, B:15:0x0028, B:17:0x002c, B:21:0x0035, B:23:0x0039, B:9:0x001c), top: B:49:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0026 A[Catch: all -> 0x0018, TryCatch #1 {all -> 0x0018, blocks: (B:50:0x0013, B:12:0x0022, B:14:0x0026, B:15:0x0028, B:17:0x002c, B:21:0x0035, B:23:0x0039, B:9:0x001c), top: B:49:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.io.IOException h(M.v r3, boolean r4, boolean r5, java.io.IOException r6) {
        /*
            r2 = this;
            java.lang.String r0 = "exchange"
            j0.AbstractC0150d.e(r3, r0)
            M.v r0 = r2.f2897n
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto Le
            return r6
        Le:
            monitor-enter(r2)
            r3 = 1
            r0 = 0
            if (r4 == 0) goto L1a
            boolean r1 = r2.f2893j     // Catch: java.lang.Throwable -> L18
            if (r1 != 0) goto L20
            goto L1a
        L18:
            r3 = move-exception
            goto L41
        L1a:
            if (r5 == 0) goto L43
            boolean r1 = r2.f2894k     // Catch: java.lang.Throwable -> L18
            if (r1 == 0) goto L43
        L20:
            if (r4 == 0) goto L24
            r2.f2893j = r0     // Catch: java.lang.Throwable -> L18
        L24:
            if (r5 == 0) goto L28
            r2.f2894k = r0     // Catch: java.lang.Throwable -> L18
        L28:
            boolean r4 = r2.f2893j     // Catch: java.lang.Throwable -> L18
            if (r4 != 0) goto L32
            boolean r5 = r2.f2894k     // Catch: java.lang.Throwable -> L18
            if (r5 != 0) goto L32
            r5 = r3
            goto L33
        L32:
            r5 = r0
        L33:
            if (r4 != 0) goto L3e
            boolean r4 = r2.f2894k     // Catch: java.lang.Throwable -> L18
            if (r4 != 0) goto L3e
            boolean r4 = r2.f2895l     // Catch: java.lang.Throwable -> L18
            if (r4 != 0) goto L3e
            r0 = r3
        L3e:
            r4 = r0
            r0 = r5
            goto L44
        L41:
            monitor-exit(r2)
            throw r3
        L43:
            r4 = r0
        L44:
            monitor-exit(r2)
            if (r0 == 0) goto L5a
            r5 = 0
            r2.f2897n = r5
            y0.k r5 = r2.h
            if (r5 != 0) goto L4f
            goto L5a
        L4f:
            monitor-enter(r5)
            int r0 = r5.f2913m     // Catch: java.lang.Throwable -> L57
            int r0 = r0 + r3
            r5.f2913m = r0     // Catch: java.lang.Throwable -> L57
            monitor-exit(r5)
            goto L5a
        L57:
            r3 = move-exception
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L57
            throw r3
        L5a:
            if (r4 == 0) goto L61
            java.io.IOException r3 = r2.c(r6)
            return r3
        L61:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: y0.h.h(M.v, boolean, boolean, java.io.IOException):java.io.IOException");
    }

    public final IOException i(IOException iOException) {
        boolean z2;
        synchronized (this) {
            z2 = false;
            if (this.f2895l) {
                this.f2895l = false;
                if (!this.f2893j) {
                    if (!this.f2894k) {
                        z2 = true;
                    }
                }
            }
        }
        if (z2) {
            return c(iOException);
        }
        return iOException;
    }

    public final Socket j() {
        k kVar = this.h;
        AbstractC0150d.b(kVar);
        byte[] bArr = v0.b.f2808a;
        ArrayList arrayList = kVar.f2916p;
        Iterator it = arrayList.iterator();
        int i2 = 0;
        while (true) {
            if (it.hasNext()) {
                if (AbstractC0150d.a(((Reference) it.next()).get(), this)) {
                    break;
                }
                i2++;
            } else {
                i2 = -1;
                break;
            }
        }
        if (i2 != -1) {
            arrayList.remove(i2);
            this.h = null;
            if (arrayList.isEmpty()) {
                kVar.f2917q = System.nanoTime();
                l lVar = this.f2887c;
                lVar.getClass();
                byte[] bArr2 = v0.b.f2808a;
                boolean z2 = kVar.f2910j;
                x0.b bVar = lVar.f2919b;
                if (!z2) {
                    bVar.c(lVar.f2920c, 0L);
                } else {
                    kVar.f2910j = true;
                    ConcurrentLinkedQueue concurrentLinkedQueue = lVar.f2921d;
                    concurrentLinkedQueue.remove(kVar);
                    if (concurrentLinkedQueue.isEmpty()) {
                        bVar.a();
                    }
                    Socket socket = kVar.f2905d;
                    AbstractC0150d.b(socket);
                    return socket;
                }
            }
            return null;
        }
        throw new IllegalStateException("Check failed.");
    }
}

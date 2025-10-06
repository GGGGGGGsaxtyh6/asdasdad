package M;

import B0.F;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class s implements i {

    /* renamed from: a, reason: collision with root package name */
    public final Context f557a;

    /* renamed from: b, reason: collision with root package name */
    public final E.g f558b;

    /* renamed from: c, reason: collision with root package name */
    public final A.m f559c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f560d;

    /* renamed from: e, reason: collision with root package name */
    public Handler f561e;

    /* renamed from: f, reason: collision with root package name */
    public ThreadPoolExecutor f562f;

    /* renamed from: g, reason: collision with root package name */
    public ThreadPoolExecutor f563g;
    public C0.d h;

    public s(Context context, E.g gVar) {
        A.m mVar = t.f564d;
        this.f560d = new Object();
        C0.d.j(context, "Context cannot be null");
        this.f557a = context.getApplicationContext();
        this.f558b = gVar;
        this.f559c = mVar;
    }

    public final void a() {
        synchronized (this.f560d) {
            try {
                this.h = null;
                Handler handler = this.f561e;
                if (handler != null) {
                    handler.removeCallbacks(null);
                }
                this.f561e = null;
                ThreadPoolExecutor threadPoolExecutor = this.f563g;
                if (threadPoolExecutor != null) {
                    threadPoolExecutor.shutdown();
                }
                this.f562f = null;
                this.f563g = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final E.l b() {
        try {
            A.m mVar = this.f559c;
            Context context = this.f557a;
            E.g gVar = this.f558b;
            mVar.getClass();
            Object[] objArr = {gVar};
            ArrayList arrayList = new ArrayList(1);
            Object obj = objArr[0];
            Objects.requireNonNull(obj);
            arrayList.add(obj);
            F a2 = E.f.a(context, Collections.unmodifiableList(arrayList));
            int i2 = a2.f79a;
            if (i2 == 0) {
                E.l[] lVarArr = (E.l[]) ((List) a2.f80b).get(0);
                if (lVarArr != null && lVarArr.length != 0) {
                    return lVarArr[0];
                }
                throw new RuntimeException("fetchFonts failed (empty result)");
            }
            throw new RuntimeException(A.e.b("fetchFonts failed (", i2, ")"));
        } catch (PackageManager.NameNotFoundException e2) {
            throw new RuntimeException("provider not found", e2);
        }
    }

    @Override // M.i
    public final void n(C0.d dVar) {
        synchronized (this.f560d) {
            this.h = dVar;
        }
        synchronized (this.f560d) {
            try {
                if (this.h != null) {
                    if (this.f562f == null) {
                        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new ThreadFactoryC0032a("emojiCompat"));
                        threadPoolExecutor.allowCoreThreadTimeOut(true);
                        this.f563g = threadPoolExecutor;
                        this.f562f = threadPoolExecutor;
                    }
                    this.f562f.execute(new r(0, this));
                }
            } finally {
            }
        }
    }
}

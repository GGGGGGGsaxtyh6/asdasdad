package Z;

import H.C0019m;
import a.v;
import android.graphics.Typeface;
import g.ExecutorC0116n;
import java.io.IOException;
import k.U;
import org.conscrypt.ct.CTConstants;

/* loaded from: classes.dex */
public final /* synthetic */ class b implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f691a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f692b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f693c;

    public /* synthetic */ b(Object obj, Object obj2, int i2) {
        this.f691a = i2;
        this.f692b = obj;
        this.f693c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f691a) {
            case 0:
                ((h) this.f693c).h(((i) this.f692b).f707e.get());
                return;
            case 1:
                ((l) this.f692b).a("Server error: " + ((u0.u) this.f693c).f2569d);
                return;
            case 2:
                ((l) this.f692b).a((String) this.f693c);
                return;
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                ((l) this.f692b).a("Network error: " + ((IOException) this.f693c).getMessage());
                return;
            case 4:
                ((l) this.f692b).a((String) this.f693c);
                return;
            case 5:
                ((l) this.f692b).a("Registration failed: Server error " + ((u0.u) this.f693c).f2569d);
                return;
            case 6:
                ((l) this.f692b).a("Network error: " + ((IOException) this.f693c).getMessage());
                return;
            case 7:
                r0.getLifecycle().a(new C0019m((v) this.f693c, (androidx.fragment.app.i) this.f692b, 1));
                return;
            case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                Runnable runnable = (Runnable) this.f693c;
                ExecutorC0116n executorC0116n = (ExecutorC0116n) this.f692b;
                executorC0116n.getClass();
                try {
                    runnable.run();
                    return;
                } finally {
                    executorC0116n.a();
                }
            default:
                ((U) this.f692b).b((Typeface) this.f693c);
                return;
        }
    }
}

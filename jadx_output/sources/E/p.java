package E;

import android.os.Handler;

/* loaded from: classes.dex */
public final class p implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public h f303a;

    /* renamed from: b, reason: collision with root package name */
    public i f304b;

    /* renamed from: c, reason: collision with root package name */
    public Handler f305c;

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        try {
            obj = this.f303a.call();
        } catch (Exception unused) {
            obj = null;
        }
        this.f305c.post(new a(this.f304b, obj, 1));
    }
}

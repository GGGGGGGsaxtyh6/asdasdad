package v0;

import j0.AbstractC0150d;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes.dex */
public final /* synthetic */ class a implements ThreadFactory {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f2806a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f2807b;

    public /* synthetic */ a(boolean z2, String str) {
        this.f2806a = str;
        this.f2807b = z2;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        String str = this.f2806a;
        AbstractC0150d.e(str, "$name");
        Thread thread = new Thread(runnable, str);
        thread.setDaemon(this.f2807b);
        return thread;
    }
}

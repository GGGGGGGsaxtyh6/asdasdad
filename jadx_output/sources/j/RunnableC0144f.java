package j;

/* renamed from: j.f, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class RunnableC0144f implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0145g f1614a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ p f1615b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ n f1616c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ A.f f1617d;

    public RunnableC0144f(A.f fVar, C0145g c0145g, p pVar, n nVar) {
        this.f1617d = fVar;
        this.f1614a = c0145g;
        this.f1615b = pVar;
        this.f1616c = nVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0145g c0145g = this.f1614a;
        if (c0145g != null) {
            A.f fVar = this.f1617d;
            ((ViewOnKeyListenerC0146h) fVar.f8b).f1643z = true;
            c0145g.f1619b.c(false);
            ((ViewOnKeyListenerC0146h) fVar.f8b).f1643z = false;
        }
        p pVar = this.f1615b;
        if (pVar.isEnabled() && pVar.hasSubMenu()) {
            this.f1616c.q(pVar, null, 4);
        }
    }
}

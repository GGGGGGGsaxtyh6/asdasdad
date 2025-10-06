package Z;

/* loaded from: classes.dex */
public final /* synthetic */ class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f695a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l f696b;

    public /* synthetic */ d(l lVar, int i2) {
        this.f695a = i2;
        this.f696b = lVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f695a) {
            case 0:
                this.f696b.a("Server not configured");
                return;
            case 1:
                this.f696b.a("Error preparing request");
                return;
            case 2:
                this.f696b.b();
                return;
            default:
                this.f696b.a("Error parsing server response");
                return;
        }
    }
}

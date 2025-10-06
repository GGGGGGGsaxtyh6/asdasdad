package Z;

import org.conscrypt.ct.CTConstants;

/* loaded from: classes.dex */
public final /* synthetic */ class e implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f697a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l f698b;

    public /* synthetic */ e(l lVar, int i2) {
        this.f697a = i2;
        this.f698b = lVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f697a) {
            case 0:
                this.f698b.a("Server not configured");
                return;
            case 1:
                this.f698b.a("Error preparing request");
                return;
            case 2:
                this.f698b.b();
                return;
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                this.f698b.a("User with this email already exists");
                return;
            default:
                this.f698b.a("User with this email may already exist");
                return;
        }
    }
}

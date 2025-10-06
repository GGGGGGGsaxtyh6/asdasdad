package Z;

import htb.d3vnu11.securenotes.LoginActivity;

/* loaded from: classes.dex */
public final class l {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f714a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LoginActivity f715b;

    public /* synthetic */ l(LoginActivity loginActivity, int i2) {
        this.f714a = i2;
        this.f715b = loginActivity;
    }

    public final void a(String str) {
        switch (this.f714a) {
            case 0:
                this.f715b.runOnUiThread(new E.a(this, str, 4, false));
                return;
            default:
                this.f715b.runOnUiThread(new E.a(this, str, 5, false));
                return;
        }
    }

    public final void b() {
        switch (this.f714a) {
            case 0:
                this.f715b.runOnUiThread(new E.b(2, this));
                return;
            default:
                this.f715b.runOnUiThread(new E.b(3, this));
                return;
        }
    }
}

package a;

import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public final class r implements OnBackAnimationCallback {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ o f764a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ o f765b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ p f766c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ p f767d;

    public r(o oVar, o oVar2, p pVar, p pVar2) {
        this.f764a = oVar;
        this.f765b = oVar2;
        this.f766c = pVar;
        this.f767d = pVar2;
    }

    public final void onBackCancelled() {
        this.f767d.a();
    }

    public final void onBackInvoked() {
        this.f766c.a();
    }

    public final void onBackProgressed(BackEvent backEvent) {
        AbstractC0150d.e(backEvent, "backEvent");
        this.f765b.c(new C0034a(backEvent));
    }

    public final void onBackStarted(BackEvent backEvent) {
        AbstractC0150d.e(backEvent, "backEvent");
        this.f764a.c(new C0034a(backEvent));
    }
}

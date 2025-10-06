package k;

/* loaded from: classes.dex */
public final class C0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1748a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ G0 f1749b;

    public /* synthetic */ C0(G0 g0, int i2) {
        this.f1748a = i2;
        this.f1749b = g0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1748a) {
            case 0:
                C0202t0 c0202t0 = this.f1749b.f1766c;
                if (c0202t0 != null) {
                    c0202t0.setListSelectionHidden(true);
                    c0202t0.requestLayout();
                    return;
                }
                return;
            default:
                G0 g0 = this.f1749b;
                C0202t0 c0202t02 = g0.f1766c;
                if (c0202t02 != null && c0202t02.isAttachedToWindow() && g0.f1766c.getCount() > g0.f1766c.getChildCount() && g0.f1766c.getChildCount() <= g0.f1775m) {
                    g0.y.setInputMethodMode(2);
                    g0.i();
                    return;
                }
                return;
        }
    }
}

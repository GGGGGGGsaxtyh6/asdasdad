package k;

import j.InterfaceC0136D;

/* renamed from: k.J, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0162J extends AbstractViewOnTouchListenerC0208w0 {

    /* renamed from: j, reason: collision with root package name */
    public final /* synthetic */ O f1793j;

    /* renamed from: k, reason: collision with root package name */
    public final /* synthetic */ S f1794k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0162J(S s2, S s3, O o2) {
        super(s3);
        this.f1794k = s2;
        this.f1793j = o2;
    }

    @Override // k.AbstractViewOnTouchListenerC0208w0
    public final InterfaceC0136D b() {
        return this.f1793j;
    }

    @Override // k.AbstractViewOnTouchListenerC0208w0
    public final boolean c() {
        S s2 = this.f1794k;
        if (!s2.getInternalPopup().b()) {
            s2.f1835f.g(s2.getTextDirection(), s2.getTextAlignment());
            return true;
        }
        return true;
    }
}

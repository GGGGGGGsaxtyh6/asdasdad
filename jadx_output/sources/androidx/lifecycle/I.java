package androidx.lifecycle;

import a.AbstractActivityC0046m;
import b0.C0091q;
import i0.InterfaceC0131a;
import j0.AbstractC0151e;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;

/* loaded from: classes.dex */
public final class I extends AbstractC0151e implements InterfaceC0131a {

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1164b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1165c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ I(int i2, Object obj) {
        super(0);
        this.f1164b = i2;
        this.f1165c = obj;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [i0.a, j0.e] */
    @Override // i0.InterfaceC0131a
    public final Object a() {
        switch (this.f1164b) {
            case 0:
                return H.d((AbstractActivityC0046m) this.f1165c);
            case 1:
                return (List) this.f1165c;
            default:
                try {
                    return (List) ((AbstractC0151e) this.f1165c).a();
                } catch (SSLPeerUnverifiedException unused) {
                    return C0091q.f1234a;
                }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public I(InterfaceC0131a interfaceC0131a) {
        super(0);
        this.f1164b = 2;
        this.f1165c = (AbstractC0151e) interfaceC0131a;
    }
}

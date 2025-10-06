package a;

import a0.C0051e;
import i0.InterfaceC0131a;
import j0.AbstractC0151e;

/* loaded from: classes.dex */
public final class p extends AbstractC0151e implements InterfaceC0131a {

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f760b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ v f761c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p(v vVar, int i2) {
        super(0);
        this.f760b = i2;
        this.f761c = vVar;
    }

    @Override // i0.InterfaceC0131a
    public final Object a() {
        switch (this.f760b) {
            case 0:
                this.f761c.b();
                return C0051e.f792c;
            case 1:
                this.f761c.a();
                return C0051e.f792c;
            default:
                this.f761c.b();
                return C0051e.f792c;
        }
    }
}

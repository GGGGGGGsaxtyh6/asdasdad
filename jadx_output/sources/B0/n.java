package B0;

import java.io.IOException;

/* loaded from: classes.dex */
public final class n extends x0.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ s f133e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f134f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ H0.e f135g;
    public final /* synthetic */ int h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(String str, s sVar, int i2, H0.e eVar, int i3, boolean z2) {
        super(true, str);
        this.f133e = sVar;
        this.f134f = i2;
        this.f135g = eVar;
        this.h = i3;
    }

    @Override // x0.a
    public final long a() {
        try {
            E e2 = this.f133e.f157k;
            H0.e eVar = this.f135g;
            int i2 = this.h;
            e2.getClass();
            eVar.o(i2);
            this.f133e.f169w.m(this.f134f, EnumC0001b.CANCEL);
            synchronized (this.f133e) {
                this.f133e.y.remove(Integer.valueOf(this.f134f));
            }
            return -1L;
        } catch (IOException unused) {
            return -1L;
        }
    }
}

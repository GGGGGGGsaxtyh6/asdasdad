package B0;

import java.io.IOException;

/* loaded from: classes.dex */
public final class q extends x0.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ s f141e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f142f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ EnumC0001b f143g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(String str, s sVar, int i2, EnumC0001b enumC0001b) {
        super(true, str);
        this.f141e = sVar;
        this.f142f = i2;
        this.f143g = enumC0001b;
    }

    @Override // x0.a
    public final long a() {
        s sVar = this.f141e;
        try {
            sVar.f169w.m(this.f142f, this.f143g);
            return -1L;
        } catch (IOException e2) {
            sVar.h(e2);
            return -1L;
        }
    }
}

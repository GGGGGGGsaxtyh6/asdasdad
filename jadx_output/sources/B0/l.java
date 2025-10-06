package B0;

import java.io.IOException;

/* loaded from: classes.dex */
public final class l extends x0.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ s f128e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f129f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f130g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(String str, s sVar, int i2, int i3) {
        super(true, str);
        this.f128e = sVar;
        this.f129f = i2;
        this.f130g = i3;
    }

    @Override // x0.a
    public final long a() {
        int i2 = this.f129f;
        int i3 = this.f130g;
        s sVar = this.f128e;
        sVar.getClass();
        try {
            sVar.f169w.l(i2, i3, true);
            return -1L;
        } catch (IOException e2) {
            sVar.h(e2);
            return -1L;
        }
    }
}

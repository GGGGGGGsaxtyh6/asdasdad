package B0;

import java.io.IOException;

/* loaded from: classes.dex */
public final class r extends x0.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ s f144e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f145f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ long f146g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(String str, s sVar, int i2, long j2) {
        super(true, str);
        this.f144e = sVar;
        this.f145f = i2;
        this.f146g = j2;
    }

    @Override // x0.a
    public final long a() {
        s sVar = this.f144e;
        try {
            sVar.f169w.n(this.f146g, this.f145f);
            return -1L;
        } catch (IOException e2) {
            sVar.h(e2);
            return -1L;
        }
    }
}

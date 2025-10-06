package z0;

import C0.m;
import H0.o;
import java.util.regex.Pattern;
import u0.w;

/* loaded from: classes.dex */
public final class g extends w {

    /* renamed from: a, reason: collision with root package name */
    public final String f2979a;

    /* renamed from: b, reason: collision with root package name */
    public final long f2980b;

    /* renamed from: c, reason: collision with root package name */
    public final o f2981c;

    public g(String str, long j2, o oVar) {
        this.f2979a = str;
        this.f2980b = j2;
        this.f2981c = oVar;
    }

    @Override // u0.w
    public final long f() {
        return this.f2980b;
    }

    @Override // u0.w
    public final u0.o h() {
        String str = this.f2979a;
        if (str == null) {
            return null;
        }
        Pattern pattern = u0.o.f2491c;
        return m.n(str);
    }

    @Override // u0.w
    public final H0.g i() {
        return this.f2981c;
    }
}

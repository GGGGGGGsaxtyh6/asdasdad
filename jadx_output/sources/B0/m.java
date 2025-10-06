package B0;

import a0.C0051e;
import i0.InterfaceC0131a;
import j0.AbstractC0150d;
import java.io.IOException;

/* loaded from: classes.dex */
public final class m implements InterfaceC0131a {

    /* renamed from: a, reason: collision with root package name */
    public final w f131a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ s f132b;

    public m(s sVar, w wVar) {
        AbstractC0150d.e(sVar, "this$0");
        this.f132b = sVar;
        this.f131a = wVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [B0.s] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [B0.b] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8 */
    @Override // i0.InterfaceC0131a
    public final Object a() {
        Throwable th;
        EnumC0001b enumC0001b;
        ?? r02 = this.f132b;
        w wVar = this.f131a;
        EnumC0001b enumC0001b2 = EnumC0001b.INTERNAL_ERROR;
        ?? r3 = 1;
        IOException e2 = null;
        try {
            try {
                try {
                } catch (Throwable th2) {
                    th = th2;
                    r02.f(r3, enumC0001b2, e2);
                    v0.b.c(wVar);
                    throw th;
                }
            } catch (IOException e3) {
                e2 = e3;
                enumC0001b = enumC0001b2;
            }
            if (wVar.f(true, this)) {
                do {
                    try {
                    } catch (Throwable th3) {
                        th = th3;
                        r3 = enumC0001b2;
                        r02.f(r3, enumC0001b2, e2);
                        v0.b.c(wVar);
                        throw th;
                    }
                } while (wVar.f(false, this));
                enumC0001b = EnumC0001b.NO_ERROR;
                try {
                    enumC0001b2 = EnumC0001b.CANCEL;
                    r02.f(enumC0001b, enumC0001b2, null);
                    r3 = enumC0001b;
                } catch (IOException e4) {
                    e2 = e4;
                    enumC0001b2 = EnumC0001b.PROTOCOL_ERROR;
                    r02.f(enumC0001b2, enumC0001b2, e2);
                    r3 = enumC0001b;
                    v0.b.c(wVar);
                    return C0051e.f792c;
                }
                v0.b.c(wVar);
                return C0051e.f792c;
            }
            throw new IOException("Required SETTINGS preface not received");
        } catch (Throwable th4) {
            th = th4;
        }
    }
}

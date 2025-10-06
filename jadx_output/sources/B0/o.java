package B0;

import java.io.IOException;
import java.util.List;

/* loaded from: classes.dex */
public final class o extends x0.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f136e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ s f137f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f138g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o(String str, s sVar, int i2, Object obj, int i3) {
        super(true, str);
        this.f136e = i3;
        this.f137f = sVar;
        this.f138g = i2;
    }

    @Override // x0.a
    public final long a() {
        switch (this.f136e) {
            case 0:
                this.f137f.f157k.getClass();
                try {
                    this.f137f.f169w.m(this.f138g, EnumC0001b.CANCEL);
                    synchronized (this.f137f) {
                        this.f137f.y.remove(Integer.valueOf(this.f138g));
                    }
                    return -1L;
                } catch (IOException unused) {
                    return -1L;
                }
            case 1:
                this.f137f.f157k.getClass();
                try {
                    this.f137f.f169w.m(this.f138g, EnumC0001b.CANCEL);
                    synchronized (this.f137f) {
                        this.f137f.y.remove(Integer.valueOf(this.f138g));
                    }
                    return -1L;
                } catch (IOException unused2) {
                    return -1L;
                }
            default:
                this.f137f.f157k.getClass();
                synchronized (this.f137f) {
                    this.f137f.y.remove(Integer.valueOf(this.f138g));
                }
                return -1L;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(String str, s sVar, int i2, List list, boolean z2) {
        super(true, str);
        this.f136e = 0;
        this.f137f = sVar;
        this.f138g = i2;
    }
}

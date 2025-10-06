package B0;

import j0.AbstractC0150d;
import java.io.IOException;

/* loaded from: classes.dex */
public final class G extends IOException {

    /* renamed from: a, reason: collision with root package name */
    public final EnumC0001b f81a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public G(EnumC0001b enumC0001b) {
        super(AbstractC0150d.h(enumC0001b, "stream was reset: "));
        AbstractC0150d.e(enumC0001b, "errorCode");
        this.f81a = enumC0001b;
    }
}

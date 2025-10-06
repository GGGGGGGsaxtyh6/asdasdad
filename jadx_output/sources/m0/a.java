package m0;

import j0.AbstractC0150d;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import l0.AbstractC0218a;

/* loaded from: classes.dex */
public final class a extends AbstractC0218a {
    @Override // l0.AbstractC0218a
    public final Random a() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        AbstractC0150d.d(current, "current(...)");
        return current;
    }
}

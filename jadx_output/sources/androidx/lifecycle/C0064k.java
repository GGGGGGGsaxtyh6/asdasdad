package androidx.lifecycle;

import j0.AbstractC0150d;

/* renamed from: androidx.lifecycle.k, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0064k {
    public static EnumC0066m a(EnumC0067n enumC0067n) {
        AbstractC0150d.e(enumC0067n, "state");
        int ordinal = enumC0067n.ordinal();
        if (ordinal != 2) {
            if (ordinal != 3) {
                if (ordinal != 4) {
                    return null;
                }
                return EnumC0066m.ON_PAUSE;
            }
            return EnumC0066m.ON_STOP;
        }
        return EnumC0066m.ON_DESTROY;
    }

    public static EnumC0066m b(EnumC0067n enumC0067n) {
        AbstractC0150d.e(enumC0067n, "state");
        int ordinal = enumC0067n.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal != 3) {
                    return null;
                }
                return EnumC0066m.ON_RESUME;
            }
            return EnumC0066m.ON_START;
        }
        return EnumC0066m.ON_CREATE;
    }
}

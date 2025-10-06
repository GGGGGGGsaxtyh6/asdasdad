package androidx.lifecycle;

import org.conscrypt.ct.CTConstants;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* renamed from: androidx.lifecycle.m, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class EnumC0066m {
    private static final /* synthetic */ EnumC0066m[] $VALUES;
    public static final C0064k Companion;
    public static final EnumC0066m ON_ANY;
    public static final EnumC0066m ON_CREATE;
    public static final EnumC0066m ON_DESTROY;
    public static final EnumC0066m ON_PAUSE;
    public static final EnumC0066m ON_RESUME;
    public static final EnumC0066m ON_START;
    public static final EnumC0066m ON_STOP;

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Enum, androidx.lifecycle.m] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, androidx.lifecycle.k] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Enum, androidx.lifecycle.m] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Enum, androidx.lifecycle.m] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Enum, androidx.lifecycle.m] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Enum, androidx.lifecycle.m] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.Enum, androidx.lifecycle.m] */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Enum, androidx.lifecycle.m] */
    static {
        ?? r02 = new Enum("ON_CREATE", 0);
        ON_CREATE = r02;
        ?? r1 = new Enum("ON_START", 1);
        ON_START = r1;
        ?? r2 = new Enum("ON_RESUME", 2);
        ON_RESUME = r2;
        ?? r3 = new Enum("ON_PAUSE", 3);
        ON_PAUSE = r3;
        ?? r4 = new Enum("ON_STOP", 4);
        ON_STOP = r4;
        ?? r5 = new Enum("ON_DESTROY", 5);
        ON_DESTROY = r5;
        ?? r6 = new Enum("ON_ANY", 6);
        ON_ANY = r6;
        $VALUES = new EnumC0066m[]{r02, r1, r2, r3, r4, r5, r6};
        Companion = new Object();
    }

    public static EnumC0066m valueOf(String str) {
        return (EnumC0066m) Enum.valueOf(EnumC0066m.class, str);
    }

    public static EnumC0066m[] values() {
        return (EnumC0066m[]) $VALUES.clone();
    }

    public final EnumC0067n a() {
        switch (AbstractC0065l.f1195a[ordinal()]) {
            case 1:
            case 2:
                return EnumC0067n.f1198c;
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
            case 4:
                return EnumC0067n.f1199d;
            case 5:
                return EnumC0067n.f1200e;
            case 6:
                return EnumC0067n.f1196a;
            default:
                throw new IllegalArgumentException(this + " has no target state");
        }
    }
}

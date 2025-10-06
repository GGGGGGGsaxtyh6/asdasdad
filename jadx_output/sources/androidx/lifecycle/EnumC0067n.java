package androidx.lifecycle;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* renamed from: androidx.lifecycle.n, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class EnumC0067n {

    /* renamed from: a, reason: collision with root package name */
    public static final EnumC0067n f1196a;

    /* renamed from: b, reason: collision with root package name */
    public static final EnumC0067n f1197b;

    /* renamed from: c, reason: collision with root package name */
    public static final EnumC0067n f1198c;

    /* renamed from: d, reason: collision with root package name */
    public static final EnumC0067n f1199d;

    /* renamed from: e, reason: collision with root package name */
    public static final EnumC0067n f1200e;

    /* renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ EnumC0067n[] f1201f;

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Enum, androidx.lifecycle.n] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Enum, androidx.lifecycle.n] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Enum, androidx.lifecycle.n] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Enum, androidx.lifecycle.n] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Enum, androidx.lifecycle.n] */
    static {
        ?? r02 = new Enum("DESTROYED", 0);
        f1196a = r02;
        ?? r1 = new Enum("INITIALIZED", 1);
        f1197b = r1;
        ?? r2 = new Enum("CREATED", 2);
        f1198c = r2;
        ?? r3 = new Enum("STARTED", 3);
        f1199d = r3;
        ?? r4 = new Enum("RESUMED", 4);
        f1200e = r4;
        f1201f = new EnumC0067n[]{r02, r1, r2, r3, r4};
    }

    public static EnumC0067n valueOf(String str) {
        return (EnumC0067n) Enum.valueOf(EnumC0067n.class, str);
    }

    public static EnumC0067n[] values() {
        return (EnumC0067n[]) f1201f.clone();
    }
}

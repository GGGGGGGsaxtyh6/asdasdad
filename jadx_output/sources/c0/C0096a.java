package c0;

import j0.AbstractC0150d;
import java.util.Comparator;

/* renamed from: c0.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0096a implements Comparator {

    /* renamed from: b, reason: collision with root package name */
    public static final C0096a f1257b = new C0096a(0);

    /* renamed from: c, reason: collision with root package name */
    public static final C0096a f1258c = new C0096a(1);

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1259a;

    public /* synthetic */ C0096a(int i2) {
        this.f1259a = i2;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f1259a) {
            case 0:
                Comparable comparable = (Comparable) obj;
                Comparable comparable2 = (Comparable) obj2;
                AbstractC0150d.e(comparable, "a");
                AbstractC0150d.e(comparable2, "b");
                return comparable.compareTo(comparable2);
            default:
                Comparable comparable3 = (Comparable) obj;
                Comparable comparable4 = (Comparable) obj2;
                AbstractC0150d.e(comparable3, "a");
                AbstractC0150d.e(comparable4, "b");
                return comparable4.compareTo(comparable3);
        }
    }

    @Override // java.util.Comparator
    public final Comparator reversed() {
        switch (this.f1259a) {
            case 0:
                return f1258c;
            default:
                return f1257b;
        }
    }
}

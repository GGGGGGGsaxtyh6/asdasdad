package p;

import java.util.concurrent.CancellationException;

/* renamed from: p.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0229a {

    /* renamed from: b, reason: collision with root package name */
    public static final C0229a f2148b;

    /* renamed from: c, reason: collision with root package name */
    public static final C0229a f2149c;

    /* renamed from: a, reason: collision with root package name */
    public final CancellationException f2150a;

    static {
        if (g.f2161d) {
            f2149c = null;
            f2148b = null;
        } else {
            f2149c = new C0229a(false, null);
            f2148b = new C0229a(true, null);
        }
    }

    public C0229a(boolean z2, CancellationException cancellationException) {
        this.f2150a = cancellationException;
    }
}

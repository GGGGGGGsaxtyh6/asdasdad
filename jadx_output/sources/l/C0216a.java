package l;

import C0.f;
import java.util.concurrent.Executors;

/* renamed from: l.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0216a extends f {

    /* renamed from: l, reason: collision with root package name */
    public static volatile C0216a f2073l;

    /* renamed from: k, reason: collision with root package name */
    public final Object f2074k;

    public C0216a(int i2) {
        switch (i2) {
            case 1:
                this.f2074k = new Object();
                Executors.newFixedThreadPool(4, new ThreadFactoryC0217b());
                return;
            default:
                this.f2074k = new C0216a(1);
                return;
        }
    }
}

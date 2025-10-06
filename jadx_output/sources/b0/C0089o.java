package b0;

import java.util.Iterator;
import java.util.List;

/* renamed from: b0.o, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0089o implements p0.d {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1231a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f1232b;

    public /* synthetic */ C0089o(int i2, Object obj) {
        this.f1231a = i2;
        this.f1232b = obj;
    }

    @Override // p0.d
    public final Iterator iterator() {
        switch (this.f1231a) {
            case 0:
                return ((List) this.f1232b).iterator();
            default:
                return new p0.c(this);
        }
    }
}

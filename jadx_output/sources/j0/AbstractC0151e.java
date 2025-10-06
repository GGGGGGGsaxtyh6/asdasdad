package j0;

import java.io.Serializable;

/* renamed from: j0.e, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0151e implements InterfaceC0149c, Serializable {

    /* renamed from: a, reason: collision with root package name */
    public final int f1737a;

    public AbstractC0151e(int i2) {
        this.f1737a = i2;
    }

    @Override // j0.InterfaceC0149c
    public final int b() {
        return this.f1737a;
    }

    public final String toString() {
        h.f1740a.getClass();
        String obj = getClass().getGenericInterfaces()[0].toString();
        if (obj.startsWith("kotlin.jvm.functions.")) {
            obj = obj.substring(21);
        }
        AbstractC0150d.d(obj, "renderLambdaToString(...)");
        return obj;
    }
}

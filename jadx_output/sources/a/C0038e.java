package a;

import android.os.Bundle;
import androidx.lifecycle.E;

/* renamed from: a.e, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final /* synthetic */ class C0038e implements U.d {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f744a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f745b;

    public /* synthetic */ C0038e(int i2, Object obj) {
        this.f744a = i2;
        this.f745b = obj;
    }

    @Override // U.d
    public final Bundle a() {
        switch (this.f744a) {
            case 0:
                return AbstractActivityC0046m.c((androidx.fragment.app.i) this.f745b);
            default:
                return E.a((E) this.f745b);
        }
    }
}

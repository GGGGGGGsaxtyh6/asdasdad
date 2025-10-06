package J;

import H.C0011e;
import H.InterfaceC0010d;
import H.N;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import k.C0207w;

/* loaded from: classes.dex */
public final /* synthetic */ class d {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0207w f467a;

    public /* synthetic */ d(C0207w c0207w) {
        this.f467a = c0207w;
    }

    public final boolean a(A.f fVar, int i2, Bundle bundle) {
        InterfaceC0010d interfaceC0010d;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 25 && (i2 & 1) != 0) {
            try {
                ((h) fVar.f8b).c();
                Parcelable parcelable = (Parcelable) ((h) fVar.f8b).e();
                if (bundle == null) {
                    bundle = new Bundle();
                } else {
                    bundle = new Bundle(bundle);
                }
                bundle.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", parcelable);
            } catch (Exception e2) {
                Log.w("InputConnectionCompat", "Can't insert content from IME; requestPermission() failed", e2);
                return false;
            }
        }
        ClipDescription b2 = ((h) fVar.f8b).b();
        h hVar = (h) fVar.f8b;
        ClipData clipData = new ClipData(b2, new ClipData.Item(hVar.g()));
        if (i3 >= 31) {
            interfaceC0010d = new A.f(clipData, 2);
        } else {
            C0011e c0011e = new C0011e();
            c0011e.f358b = clipData;
            c0011e.f359c = 2;
            interfaceC0010d = c0011e;
        }
        interfaceC0010d.e(hVar.d());
        interfaceC0010d.b(bundle);
        if (N.f(this.f467a, interfaceC0010d.o()) != null) {
            return false;
        }
        return true;
    }
}

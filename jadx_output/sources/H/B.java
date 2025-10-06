package H;

import android.text.TextUtils;
import android.view.View;

/* loaded from: classes.dex */
public final class B extends D {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f314e;

    public B(int i2, Class cls, int i3, int i4, int i5) {
        this.f314e = i5;
        this.f316a = i2;
        this.f319d = cls;
        this.f318c = i3;
        this.f317b = i4;
    }

    @Override // H.D
    public final Object b(View view) {
        switch (this.f314e) {
            case 0:
                return Boolean.valueOf(I.c(view));
            case 1:
                return I.a(view);
            default:
                return Boolean.valueOf(I.b(view));
        }
    }

    @Override // H.D
    public final void c(View view, Object obj) {
        switch (this.f314e) {
            case 0:
                I.f(view, ((Boolean) obj).booleanValue());
                return;
            case 1:
                I.e(view, (CharSequence) obj);
                return;
            default:
                I.d(view, ((Boolean) obj).booleanValue());
                return;
        }
    }

    @Override // H.D
    public final boolean e(Object obj, Object obj2) {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        switch (this.f314e) {
            case 0:
                Boolean bool = (Boolean) obj;
                Boolean bool2 = (Boolean) obj2;
                boolean z6 = false;
                if (bool != null && bool.booleanValue()) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (bool2 != null && bool2.booleanValue()) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z2 == z3) {
                    z6 = true;
                }
                return !z6;
            case 1:
                return !TextUtils.equals((CharSequence) obj, (CharSequence) obj2);
            default:
                Boolean bool3 = (Boolean) obj;
                Boolean bool4 = (Boolean) obj2;
                boolean z7 = false;
                if (bool3 != null && bool3.booleanValue()) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (bool4 != null && bool4.booleanValue()) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (z4 == z5) {
                    z7 = true;
                }
                return !z7;
        }
    }
}

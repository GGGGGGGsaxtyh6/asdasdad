package k;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class U {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1845a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1846b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ WeakReference f1847c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Z f1848d;

    public U(Z z2, int i2, int i3, WeakReference weakReference) {
        this.f1848d = z2;
        this.f1845a = i2;
        this.f1846b = i3;
        this.f1847c = weakReference;
    }

    public final void a() {
        new Handler(Looper.getMainLooper()).post(new M.r(6, this));
    }

    public final void b(Typeface typeface) {
        int i2;
        boolean z2;
        if (Build.VERSION.SDK_INT >= 28 && (i2 = this.f1845a) != -1) {
            if ((this.f1846b & 2) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            typeface = Y.a(typeface, i2, z2);
        }
        Z z3 = this.f1848d;
        if (z3.f1870m) {
            z3.f1869l = typeface;
            TextView textView = (TextView) this.f1847c.get();
            if (textView != null) {
                if (textView.isAttachedToWindow()) {
                    textView.post(new V(textView, typeface, z3.f1867j));
                } else {
                    textView.setTypeface(typeface, z3.f1867j);
                }
            }
        }
    }
}

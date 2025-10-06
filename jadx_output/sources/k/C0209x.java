package k;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import f.AbstractC0101a;

/* renamed from: k.x, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0209x {

    /* renamed from: a, reason: collision with root package name */
    public final TextView f2049a;

    /* renamed from: b, reason: collision with root package name */
    public final A.f f2050b;

    public C0209x(TextView textView) {
        this.f2049a = textView;
        this.f2050b = new A.f(textView);
    }

    public final void a(AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = this.f2049a.getContext().obtainStyledAttributes(attributeSet, AbstractC0101a.f1269i, i2, 0);
        try {
            boolean z2 = true;
            if (obtainStyledAttributes.hasValue(14)) {
                z2 = obtainStyledAttributes.getBoolean(14, true);
            }
            obtainStyledAttributes.recycle();
            c(z2);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void b(boolean z2) {
        ((C0.d) this.f2050b.f8b).F(z2);
    }

    public final void c(boolean z2) {
        ((C0.d) this.f2050b.f8b).G(z2);
    }
}

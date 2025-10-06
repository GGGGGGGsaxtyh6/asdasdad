package k;

import android.graphics.Typeface;
import android.widget.TextView;

/* loaded from: classes.dex */
public final class V implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ TextView f1853a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Typeface f1854b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f1855c;

    public V(TextView textView, Typeface typeface, int i2) {
        this.f1853a = textView;
        this.f1854b = typeface;
        this.f1855c = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f1853a.setTypeface(this.f1854b, this.f1855c);
    }
}

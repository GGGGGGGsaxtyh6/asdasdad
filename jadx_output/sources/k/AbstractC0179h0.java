package k;

import android.text.StaticLayout;
import android.widget.TextView;

/* renamed from: k.h0, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0179h0 {
    public abstract void a(StaticLayout.Builder builder, TextView textView);

    public boolean b(TextView textView) {
        return ((Boolean) C0181i0.e(textView, "getHorizontallyScrolling", Boolean.FALSE)).booleanValue();
    }
}

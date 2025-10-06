package k;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import org.conscrypt.R;

/* renamed from: k.F, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0158F extends RatingBar {

    /* renamed from: a, reason: collision with root package name */
    public final C0156D f1760a;

    public C0158F(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.ratingBarStyle);
        S0.a(this, getContext());
        C0156D c0156d = new C0156D(this);
        this.f1760a = c0156d;
        c0156d.b(attributeSet, R.attr.ratingBarStyle);
    }

    @Override // android.widget.RatingBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final synchronized void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        Bitmap bitmap = (Bitmap) this.f1760a.f1753c;
        if (bitmap != null) {
            setMeasuredDimension(View.resolveSizeAndState(bitmap.getWidth() * getNumStars(), i2, 0), getMeasuredHeight());
        }
    }
}

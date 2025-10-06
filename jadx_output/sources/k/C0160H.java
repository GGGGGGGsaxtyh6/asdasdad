package k;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import org.conscrypt.R;

/* renamed from: k.H, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0160H extends SeekBar {

    /* renamed from: a, reason: collision with root package name */
    public final C0161I f1787a;

    public C0160H(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.seekBarStyle);
        S0.a(this, getContext());
        C0161I c0161i = new C0161I(this);
        this.f1787a = c0161i;
        c0161i.b(attributeSet, R.attr.seekBarStyle);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0161I c0161i = this.f1787a;
        Drawable drawable = c0161i.f1789f;
        if (drawable != null && drawable.isStateful()) {
            C0160H c0160h = c0161i.f1788e;
            if (drawable.setState(c0160h.getDrawableState())) {
                c0160h.invalidateDrawable(drawable);
            }
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f1787a.f1789f;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f1787a.g(canvas);
    }
}

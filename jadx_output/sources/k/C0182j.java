package k;

import android.content.Context;
import android.graphics.drawable.Drawable;
import j.C0140b;
import org.conscrypt.R;

/* renamed from: k.j, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0182j extends C0153A implements InterfaceC0186l {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ C0184k f1946d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0182j(C0184k c0184k, Context context) {
        super(context, null, R.attr.actionOverflowButtonStyle);
        this.f1946d = c0184k;
        setClickable(true);
        setFocusable(true);
        setVisibility(0);
        setEnabled(true);
        C0.d.H(this, getContentDescription());
        setOnTouchListener(new C0140b(this, this));
    }

    @Override // k.InterfaceC0186l
    public final boolean a() {
        return false;
    }

    @Override // k.InterfaceC0186l
    public final boolean b() {
        return false;
    }

    @Override // android.view.View
    public final boolean performClick() {
        if (super.performClick()) {
            return true;
        }
        playSoundEffect(0);
        this.f1946d.l();
        return true;
    }

    @Override // android.widget.ImageView
    public final boolean setFrame(int i2, int i3, int i4, int i5) {
        boolean frame = super.setFrame(i2, i3, i4, i5);
        Drawable drawable = getDrawable();
        Drawable background = getBackground();
        if (drawable != null && background != null) {
            int width = getWidth();
            int height = getHeight();
            int max = Math.max(width, height) / 2;
            int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
            int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
            background.setHotspotBounds(paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
        }
        return frame;
    }
}

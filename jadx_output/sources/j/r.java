package j;

import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.FrameLayout;
import i.InterfaceC0121c;

/* loaded from: classes.dex */
public final class r extends FrameLayout implements InterfaceC0121c {

    /* renamed from: a, reason: collision with root package name */
    public final CollapsibleActionView f1714a;

    /* JADX WARN: Multi-variable type inference failed */
    public r(View view) {
        super(view.getContext());
        this.f1714a = (CollapsibleActionView) view;
        addView(view);
    }
}

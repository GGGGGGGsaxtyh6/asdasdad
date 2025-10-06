package j;

import android.widget.PopupWindow;

/* loaded from: classes.dex */
public final class w implements PopupWindow.OnDismissListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ x f1722a;

    public w(x xVar) {
        this.f1722a = xVar;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.f1722a.c();
    }
}

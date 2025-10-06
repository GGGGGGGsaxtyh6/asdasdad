package k;

import android.content.Context;
import android.view.View;
import android.view.Window;
import j.C0139a;

/* loaded from: classes.dex */
public final class d1 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final C0139a f1893a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e1 f1894b;

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, j.a] */
    public d1(e1 e1Var) {
        this.f1894b = e1Var;
        Context context = e1Var.f1895a.getContext();
        CharSequence charSequence = e1Var.h;
        ?? obj = new Object();
        obj.f1597e = 4096;
        obj.f1599g = 4096;
        obj.f1603l = null;
        obj.f1604m = null;
        obj.f1605n = false;
        obj.f1606o = false;
        obj.f1607p = 16;
        obj.f1600i = context;
        obj.f1593a = charSequence;
        this.f1893a = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        e1 e1Var = this.f1894b;
        Window.Callback callback = e1Var.f1904k;
        if (callback != null && e1Var.f1905l) {
            callback.onMenuItemSelected(0, this.f1893a);
        }
    }
}

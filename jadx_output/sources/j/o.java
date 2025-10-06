package j;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import g.DialogC0110h;

/* loaded from: classes.dex */
public final class o implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, y {

    /* renamed from: a, reason: collision with root package name */
    public SubMenuC0138F f1681a;

    /* renamed from: b, reason: collision with root package name */
    public DialogC0110h f1682b;

    /* renamed from: c, reason: collision with root package name */
    public j f1683c;

    @Override // j.y
    public final void a(n nVar, boolean z2) {
        DialogC0110h dialogC0110h;
        if ((z2 || nVar == this.f1681a) && (dialogC0110h = this.f1682b) != null) {
            dialogC0110h.dismiss();
        }
    }

    @Override // j.y
    public final boolean c(n nVar) {
        return false;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i2) {
        j jVar = this.f1683c;
        if (jVar.f1651f == null) {
            jVar.f1651f = new i(jVar);
        }
        this.f1681a.q(jVar.f1651f.getItem(i2), null, 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        this.f1683c.a(this.f1681a, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public final boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        SubMenuC0138F subMenuC0138F = this.f1681a;
        if (i2 == 82 || i2 == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f1682b.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f1682b.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                subMenuC0138F.c(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return subMenuC0138F.performShortcut(i2, keyEvent, 0);
    }
}

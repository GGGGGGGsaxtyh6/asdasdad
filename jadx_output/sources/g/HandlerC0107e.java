package g;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* renamed from: g.e, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class HandlerC0107e extends Handler {

    /* renamed from: a, reason: collision with root package name */
    public WeakReference f1424a;

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i2 = message.what;
        if (i2 != -3 && i2 != -2 && i2 != -1) {
            if (i2 == 1) {
                ((DialogInterface) message.obj).dismiss();
                return;
            }
            return;
        }
        ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.f1424a.get(), message.what);
    }
}

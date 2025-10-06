package O;

import android.widget.EditText;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class h extends M.g {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f602a;

    public h(EditText editText) {
        this.f602a = new WeakReference(editText);
    }

    @Override // M.g
    public final void a() {
        i.a((EditText) this.f602a.get(), 1);
    }
}

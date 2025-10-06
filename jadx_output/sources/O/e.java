package O;

import A.m;
import android.text.Editable;
import android.text.method.KeyListener;
import android.text.method.MetaKeyKeyListener;
import android.view.KeyEvent;
import android.view.View;

/* loaded from: classes.dex */
public final class e implements KeyListener {

    /* renamed from: a, reason: collision with root package name */
    public final KeyListener f596a;

    /* renamed from: b, reason: collision with root package name */
    public final m f597b;

    public e(KeyListener keyListener) {
        m mVar = new m(10);
        this.f596a = keyListener;
        this.f597b = mVar;
    }

    @Override // android.text.method.KeyListener
    public final void clearMetaKeyState(View view, Editable editable, int i2) {
        this.f596a.clearMetaKeyState(view, editable, i2);
    }

    @Override // android.text.method.KeyListener
    public final int getInputType() {
        return this.f596a.getInputType();
    }

    @Override // android.text.method.KeyListener
    public final boolean onKeyDown(View view, Editable editable, int i2, KeyEvent keyEvent) {
        boolean h;
        boolean z2;
        this.f597b.getClass();
        if (i2 != 67) {
            if (i2 != 112) {
                h = false;
            } else {
                h = D0.h.h(editable, keyEvent, true);
            }
        } else {
            h = D0.h.h(editable, keyEvent, false);
        }
        if (h) {
            MetaKeyKeyListener.adjustMetaAfterKeypress(editable);
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2 && !this.f596a.onKeyDown(view, editable, i2, keyEvent)) {
            return false;
        }
        return true;
    }

    @Override // android.text.method.KeyListener
    public final boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
        return this.f596a.onKeyOther(view, editable, keyEvent);
    }

    @Override // android.text.method.KeyListener
    public final boolean onKeyUp(View view, Editable editable, int i2, KeyEvent keyEvent) {
        return this.f596a.onKeyUp(view, editable, i2, keyEvent);
    }
}

package g;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import i.AbstractC0120b;
import i.AbstractC0130l;
import java.util.List;

/* loaded from: classes.dex */
public final class w implements Window.Callback {

    /* renamed from: a, reason: collision with root package name */
    public final Window.Callback f1472a;

    /* renamed from: b, reason: collision with root package name */
    public I f1473b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f1474c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f1475d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f1476e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ C f1477f;

    public w(C c2, Window.Callback callback) {
        this.f1477f = c2;
        if (callback != null) {
            this.f1472a = callback;
            return;
        }
        throw new IllegalArgumentException("Window callback may not be null");
    }

    public final void a(Window.Callback callback) {
        try {
            this.f1474c = true;
            callback.onContentChanged();
        } finally {
            this.f1474c = false;
        }
    }

    public final boolean b(int i2, Menu menu) {
        return this.f1472a.onMenuOpened(i2, menu);
    }

    public final void c(int i2, Menu menu) {
        this.f1472a.onPanelClosed(i2, menu);
    }

    public final void d(List list, Menu menu, int i2) {
        i.m.a(this.f1472a, list, menu, i2);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.f1472a.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean z2 = this.f1475d;
        Window.Callback callback = this.f1472a;
        if (z2) {
            return callback.dispatchKeyEvent(keyEvent);
        }
        if (!this.f1477f.v(keyEvent) && !callback.dispatchKeyEvent(keyEvent)) {
            return false;
        }
        return true;
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        if (this.f1472a.dispatchKeyShortcutEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        C c2 = this.f1477f;
        c2.B();
        AbstractC0103a abstractC0103a = c2.f1342o;
        if (abstractC0103a != null && abstractC0103a.i(keyCode, keyEvent)) {
            return true;
        }
        B b2 = c2.f1317M;
        if (b2 != null && c2.G(b2, keyEvent.getKeyCode(), keyEvent)) {
            B b3 = c2.f1317M;
            if (b3 == null) {
                return true;
            }
            b3.f1298l = true;
            return true;
        }
        if (c2.f1317M == null) {
            B A2 = c2.A(0);
            c2.H(A2, keyEvent);
            boolean G2 = c2.G(A2, keyEvent.getKeyCode(), keyEvent);
            A2.f1297k = false;
            if (G2) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.f1472a.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.f1472a.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.f1472a.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final void onActionModeFinished(ActionMode actionMode) {
        this.f1472a.onActionModeFinished(actionMode);
    }

    @Override // android.view.Window.Callback
    public final void onActionModeStarted(ActionMode actionMode) {
        this.f1472a.onActionModeStarted(actionMode);
    }

    @Override // android.view.Window.Callback
    public final void onAttachedToWindow() {
        this.f1472a.onAttachedToWindow();
    }

    @Override // android.view.Window.Callback
    public final void onContentChanged() {
        if (this.f1474c) {
            this.f1472a.onContentChanged();
        }
    }

    @Override // android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i2, Menu menu) {
        if (i2 == 0 && !(menu instanceof j.n)) {
            return false;
        }
        return this.f1472a.onCreatePanelMenu(i2, menu);
    }

    @Override // android.view.Window.Callback
    public final View onCreatePanelView(int i2) {
        View view;
        I i3 = this.f1473b;
        if (i3 != null) {
            if (i2 == 0) {
                view = new View(i3.f1366a.f1369a.f1895a.getContext());
            } else {
                view = null;
            }
            if (view != null) {
                return view;
            }
        }
        return this.f1472a.onCreatePanelView(i2);
    }

    @Override // android.view.Window.Callback
    public final void onDetachedFromWindow() {
        this.f1472a.onDetachedFromWindow();
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        return this.f1472a.onMenuItemSelected(i2, menuItem);
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuOpened(int i2, Menu menu) {
        b(i2, menu);
        C c2 = this.f1477f;
        if (i2 == 108) {
            c2.B();
            AbstractC0103a abstractC0103a = c2.f1342o;
            if (abstractC0103a != null) {
                abstractC0103a.c(true);
            }
        } else {
            c2.getClass();
        }
        return true;
    }

    @Override // android.view.Window.Callback
    public final void onPanelClosed(int i2, Menu menu) {
        if (this.f1476e) {
            this.f1472a.onPanelClosed(i2, menu);
            return;
        }
        c(i2, menu);
        C c2 = this.f1477f;
        if (i2 == 108) {
            c2.B();
            AbstractC0103a abstractC0103a = c2.f1342o;
            if (abstractC0103a != null) {
                abstractC0103a.c(false);
                return;
            }
            return;
        }
        if (i2 == 0) {
            B A2 = c2.A(i2);
            if (A2.f1299m) {
                c2.t(A2, false);
                return;
            }
            return;
        }
        c2.getClass();
    }

    @Override // android.view.Window.Callback
    public final void onPointerCaptureChanged(boolean z2) {
        i.n.a(this.f1472a, z2);
    }

    @Override // android.view.Window.Callback
    public final boolean onPreparePanel(int i2, View view, Menu menu) {
        j.n nVar;
        if (menu instanceof j.n) {
            nVar = (j.n) menu;
        } else {
            nVar = null;
        }
        if (i2 == 0 && nVar == null) {
            return false;
        }
        if (nVar != null) {
            nVar.f1680x = true;
        }
        I i3 = this.f1473b;
        if (i3 != null && i2 == 0) {
            K k2 = i3.f1366a;
            if (!k2.f1372d) {
                k2.f1369a.f1905l = true;
                k2.f1372d = true;
            }
        }
        boolean onPreparePanel = this.f1472a.onPreparePanel(i2, view, menu);
        if (nVar != null) {
            nVar.f1680x = false;
        }
        return onPreparePanel;
    }

    @Override // android.view.Window.Callback
    public final void onProvideKeyboardShortcuts(List list, Menu menu, int i2) {
        j.n nVar = this.f1477f.A(0).h;
        if (nVar != null) {
            d(list, nVar, i2);
        } else {
            d(list, menu, i2);
        }
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested(SearchEvent searchEvent) {
        return AbstractC0130l.a(this.f1472a, searchEvent);
    }

    @Override // android.view.Window.Callback
    public final void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.f1472a.onWindowAttributesChanged(layoutParams);
    }

    @Override // android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z2) {
        this.f1472a.onWindowFocusChanged(z2);
    }

    @Override // android.view.Window.Callback
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i2) {
        C c2 = this.f1477f;
        c2.getClass();
        if (i2 != 0) {
            return AbstractC0130l.b(this.f1472a, callback, i2);
        }
        M.v vVar = new M.v(c2.f1338k, callback);
        AbstractC0120b n2 = c2.n(vVar);
        if (n2 != null) {
            return vVar.g(n2);
        }
        return null;
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested() {
        return this.f1472a.onSearchRequested();
    }

    @Override // android.view.Window.Callback
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }
}

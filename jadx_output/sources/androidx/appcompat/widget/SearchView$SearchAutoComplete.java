package androidx.appcompat.widget;

import E.b;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import k.C0192o;
import k.R0;
import org.conscrypt.PSKKeyManager;

/* loaded from: classes.dex */
public class SearchView$SearchAutoComplete extends C0192o {

    /* renamed from: e, reason: collision with root package name */
    public int f902e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f903f;

    /* renamed from: g, reason: collision with root package name */
    public final b f904g;

    public SearchView$SearchAutoComplete(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f904g = new b(9, this);
        this.f902e = getThreshold();
    }

    private int getSearchViewTextMinWidthDp() {
        Configuration configuration = getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        int i3 = configuration.screenHeightDp;
        if (i2 >= 960 && i3 >= 720 && configuration.orientation == 2) {
            return PSKKeyManager.MAX_KEY_LENGTH_BYTES;
        }
        if (i2 < 600) {
            if (i2 < 640 || i3 < 480) {
                return 160;
            }
            return 192;
        }
        return 192;
    }

    @Override // android.widget.AutoCompleteTextView
    public final boolean enoughToFilter() {
        if (this.f902e > 0 && !super.enoughToFilter()) {
            return false;
        }
        return true;
    }

    @Override // k.C0192o, android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (this.f903f) {
            b bVar = this.f904g;
            removeCallbacks(bVar);
            post(bVar);
        }
        return onCreateInputConnection;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final void onFocusChanged(boolean z2, int i2, Rect rect) {
        super.onFocusChanged(z2, i2, rect);
        throw null;
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final boolean onKeyPreIme(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                if (keyDispatcherState != null) {
                    keyDispatcherState.startTracking(keyEvent, this);
                }
                return true;
            }
            if (keyEvent.getAction() == 1) {
                KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.handleUpEvent(keyEvent);
                }
                if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                    throw null;
                }
            }
        }
        return super.onKeyPreIme(i2, keyEvent);
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        if (!z2) {
        } else {
            throw null;
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public final void performCompletion() {
    }

    @Override // android.widget.AutoCompleteTextView
    public final void replaceText(CharSequence charSequence) {
    }

    public void setImeVisibility(boolean z2) {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        b bVar = this.f904g;
        if (!z2) {
            this.f903f = false;
            removeCallbacks(bVar);
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        } else {
            if (inputMethodManager.isActive(this)) {
                this.f903f = false;
                removeCallbacks(bVar);
                inputMethodManager.showSoftInput(this, 0);
                return;
            }
            this.f903f = true;
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setThreshold(int i2) {
        super.setThreshold(i2);
        this.f902e = i2;
    }

    public void setSearchView(R0 r02) {
    }
}

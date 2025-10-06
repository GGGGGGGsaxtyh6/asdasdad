package O;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;

/* loaded from: classes.dex */
public final class i implements TextWatcher {

    /* renamed from: a, reason: collision with root package name */
    public final EditText f603a;

    /* renamed from: b, reason: collision with root package name */
    public h f604b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f605c = true;

    public i(EditText editText) {
        this.f603a = editText;
    }

    public static void a(EditText editText, int i2) {
        int length;
        if (i2 == 1 && editText != null && editText.isAttachedToWindow()) {
            Editable editableText = editText.getEditableText();
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            M.j a2 = M.j.a();
            if (editableText == null) {
                length = 0;
            } else {
                a2.getClass();
                length = editableText.length();
            }
            a2.e(editableText, 0, length);
            if (selectionStart >= 0 && selectionEnd >= 0) {
                Selection.setSelection(editableText, selectionStart, selectionEnd);
            } else if (selectionStart >= 0) {
                Selection.setSelection(editableText, selectionStart);
            } else if (selectionEnd >= 0) {
                Selection.setSelection(editableText, selectionEnd);
            }
        }
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        EditText editText = this.f603a;
        if (!editText.isInEditMode() && this.f605c && M.j.f533k != null && i3 <= i4 && (charSequence instanceof Spannable)) {
            int b2 = M.j.a().b();
            if (b2 != 0) {
                if (b2 != 1) {
                    if (b2 != 3) {
                        return;
                    }
                } else {
                    M.j.a().e((Spannable) charSequence, i2, i4 + i2);
                    return;
                }
            }
            M.j a2 = M.j.a();
            if (this.f604b == null) {
                this.f604b = new h(editText);
            }
            a2.f(this.f604b);
        }
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }
}

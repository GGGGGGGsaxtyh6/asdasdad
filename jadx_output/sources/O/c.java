package O;

import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class c extends M.g {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f592a;

    /* renamed from: b, reason: collision with root package name */
    public final WeakReference f593b;

    public c(TextView textView, d dVar) {
        this.f592a = new WeakReference(textView);
        this.f593b = new WeakReference(dVar);
    }

    @Override // M.g
    public final void a() {
        InputFilter[] filters;
        int length;
        TextView textView = (TextView) this.f592a.get();
        InputFilter inputFilter = (InputFilter) this.f593b.get();
        if (inputFilter != null && textView != null && (filters = textView.getFilters()) != null) {
            for (InputFilter inputFilter2 : filters) {
                if (inputFilter2 == inputFilter) {
                    if (textView.isAttachedToWindow()) {
                        CharSequence text = textView.getText();
                        M.j a2 = M.j.a();
                        if (text == null) {
                            length = 0;
                        } else {
                            a2.getClass();
                            length = text.length();
                        }
                        CharSequence e2 = a2.e(text, 0, length);
                        if (text == e2) {
                            return;
                        }
                        int selectionStart = Selection.getSelectionStart(e2);
                        int selectionEnd = Selection.getSelectionEnd(e2);
                        textView.setText(e2);
                        if (e2 instanceof Spannable) {
                            Spannable spannable = (Spannable) e2;
                            if (selectionStart >= 0 && selectionEnd >= 0) {
                                Selection.setSelection(spannable, selectionStart, selectionEnd);
                                return;
                            } else if (selectionStart >= 0) {
                                Selection.setSelection(spannable, selectionStart);
                                return;
                            } else {
                                if (selectionEnd >= 0) {
                                    Selection.setSelection(spannable, selectionEnd);
                                    return;
                                }
                                return;
                            }
                        }
                        return;
                    }
                    return;
                }
            }
        }
    }
}

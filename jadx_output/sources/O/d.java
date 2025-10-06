package O;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.TextView;

/* loaded from: classes.dex */
public final class d implements InputFilter {

    /* renamed from: a, reason: collision with root package name */
    public final TextView f594a;

    /* renamed from: b, reason: collision with root package name */
    public c f595b;

    public d(TextView textView) {
        this.f594a = textView;
    }

    @Override // android.text.InputFilter
    public final CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
        TextView textView = this.f594a;
        if (textView.isInEditMode()) {
            return charSequence;
        }
        int b2 = M.j.a().b();
        if (b2 != 0) {
            if (b2 != 1) {
                if (b2 != 3) {
                    return charSequence;
                }
            } else {
                if ((i5 != 0 || i4 != 0 || spanned.length() != 0 || charSequence != textView.getText()) && charSequence != null) {
                    if (i2 != 0 || i3 != charSequence.length()) {
                        charSequence = charSequence.subSequence(i2, i3);
                    }
                    return M.j.a().e(charSequence, 0, charSequence.length());
                }
                return charSequence;
            }
        }
        M.j a2 = M.j.a();
        if (this.f595b == null) {
            this.f595b = new c(textView, this);
        }
        a2.f(this.f595b);
        return charSequence;
    }
}

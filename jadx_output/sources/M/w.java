package M;

import android.os.Build;
import android.text.Editable;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.TextWatcher;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class w implements TextWatcher, SpanWatcher {

    /* renamed from: a, reason: collision with root package name */
    public final Object f574a;

    /* renamed from: b, reason: collision with root package name */
    public final AtomicInteger f575b = new AtomicInteger(0);

    public w(Object obj) {
        this.f574a = obj;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        ((TextWatcher) this.f574a).afterTextChanged(editable);
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        ((TextWatcher) this.f574a).beforeTextChanged(charSequence, i2, i3, i4);
    }

    @Override // android.text.SpanWatcher
    public final void onSpanAdded(Spannable spannable, Object obj, int i2, int i3) {
        if (this.f575b.get() > 0 && (obj instanceof z)) {
            return;
        }
        ((SpanWatcher) this.f574a).onSpanAdded(spannable, obj, i2, i3);
    }

    @Override // android.text.SpanWatcher
    public final void onSpanChanged(Spannable spannable, Object obj, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        if (this.f575b.get() > 0 && (obj instanceof z)) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28) {
            if (i2 > i3) {
                i2 = 0;
            }
            if (i4 > i5) {
                i6 = i2;
                i7 = 0;
                ((SpanWatcher) this.f574a).onSpanChanged(spannable, obj, i6, i3, i7, i5);
            }
        }
        i6 = i2;
        i7 = i4;
        ((SpanWatcher) this.f574a).onSpanChanged(spannable, obj, i6, i3, i7, i5);
    }

    @Override // android.text.SpanWatcher
    public final void onSpanRemoved(Spannable spannable, Object obj, int i2, int i3) {
        if (this.f575b.get() > 0 && (obj instanceof z)) {
            return;
        }
        ((SpanWatcher) this.f574a).onSpanRemoved(spannable, obj, i2, i3);
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        ((TextWatcher) this.f574a).onTextChanged(charSequence, i2, i3, i4);
    }
}

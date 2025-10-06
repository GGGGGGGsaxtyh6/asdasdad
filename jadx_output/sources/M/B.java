package M;

import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import java.util.stream.IntStream;

/* loaded from: classes.dex */
public final class B implements Spannable {

    /* renamed from: a, reason: collision with root package name */
    public boolean f521a = false;

    /* renamed from: b, reason: collision with root package name */
    public Spannable f522b;

    public B(Spannable spannable) {
        this.f522b = spannable;
    }

    public final void a() {
        A.m mVar;
        Spannable spannable = this.f522b;
        if (!this.f521a) {
            if (Build.VERSION.SDK_INT < 28) {
                mVar = new A.m(7);
            } else {
                mVar = new A.m(7);
            }
            if (mVar.h(spannable)) {
                this.f522b = new SpannableString(spannable);
            }
        }
        this.f521a = true;
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i2) {
        return this.f522b.charAt(i2);
    }

    @Override // java.lang.CharSequence
    public final IntStream chars() {
        return this.f522b.chars();
    }

    @Override // java.lang.CharSequence
    public final IntStream codePoints() {
        return this.f522b.codePoints();
    }

    @Override // android.text.Spanned
    public final int getSpanEnd(Object obj) {
        return this.f522b.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public final int getSpanFlags(Object obj) {
        return this.f522b.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public final int getSpanStart(Object obj) {
        return this.f522b.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public final Object[] getSpans(int i2, int i3, Class cls) {
        return this.f522b.getSpans(i2, i3, cls);
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.f522b.length();
    }

    @Override // android.text.Spanned
    public final int nextSpanTransition(int i2, int i3, Class cls) {
        return this.f522b.nextSpanTransition(i2, i3, cls);
    }

    @Override // android.text.Spannable
    public final void removeSpan(Object obj) {
        a();
        this.f522b.removeSpan(obj);
    }

    @Override // android.text.Spannable
    public final void setSpan(Object obj, int i2, int i3, int i4) {
        a();
        this.f522b.setSpan(obj, i2, i3, i4);
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int i2, int i3) {
        return this.f522b.subSequence(i2, i3);
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        return this.f522b.toString();
    }
}

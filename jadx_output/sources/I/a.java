package I;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* renamed from: a, reason: collision with root package name */
    public final int f453a;

    /* renamed from: b, reason: collision with root package name */
    public final e f454b;

    /* renamed from: c, reason: collision with root package name */
    public final int f455c;

    public a(int i2, e eVar, int i3) {
        this.f453a = i2;
        this.f454b = eVar;
        this.f455c = i3;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f453a);
        this.f454b.f463a.performAction(this.f455c, bundle);
    }
}

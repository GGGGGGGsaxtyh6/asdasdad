package H;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;

/* loaded from: classes.dex */
public final class F implements View.OnApplyWindowInsetsListener {

    /* renamed from: a, reason: collision with root package name */
    public f0 f320a = null;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f321b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0027v f322c;

    public F(View view, InterfaceC0027v interfaceC0027v) {
        this.f321b = view;
        this.f322c = interfaceC0027v;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        f0 c2 = f0.c(windowInsets, view);
        int i2 = Build.VERSION.SDK_INT;
        InterfaceC0027v interfaceC0027v = this.f322c;
        if (i2 < 30) {
            G.a(windowInsets, this.f321b);
            if (c2.equals(this.f320a)) {
                return ((g.r) interfaceC0027v).b(view, c2).b();
            }
        }
        this.f320a = c2;
        f0 b2 = ((g.r) interfaceC0027v).b(view, c2);
        if (i2 >= 30) {
            return b2.b();
        }
        E.c(view);
        return b2.b();
    }
}

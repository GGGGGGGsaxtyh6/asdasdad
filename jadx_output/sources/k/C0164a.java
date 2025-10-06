package k;

import androidx.appcompat.widget.ActionBarContextView;

/* renamed from: k.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0164a implements H.T {

    /* renamed from: a, reason: collision with root package name */
    public boolean f1874a = false;

    /* renamed from: b, reason: collision with root package name */
    public int f1875b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ ActionBarContextView f1876c;

    public C0164a(ActionBarContextView actionBarContextView) {
        this.f1876c = actionBarContextView;
    }

    @Override // H.T
    public final void a() {
        if (this.f1874a) {
            return;
        }
        ActionBarContextView actionBarContextView = this.f1876c;
        actionBarContextView.f837f = null;
        super/*android.view.View*/.setVisibility(this.f1875b);
    }

    @Override // H.T
    public final void b() {
        this.f1874a = true;
    }

    @Override // H.T
    public final void c() {
        super/*android.view.View*/.setVisibility(0);
        this.f1874a = false;
    }
}

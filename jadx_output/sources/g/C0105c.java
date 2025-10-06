package g;

import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

/* renamed from: g.c, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0105c implements AdapterView.OnItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0109g f1413a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0106d f1414b;

    public C0105c(C0106d c0106d, C0109g c0109g) {
        this.f1414b = c0106d;
        this.f1413a = c0109g;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        C0106d c0106d = this.f1414b;
        DialogInterface.OnClickListener onClickListener = c0106d.h;
        C0109g c0109g = this.f1413a;
        onClickListener.onClick(c0109g.f1426b, i2);
        if (!c0106d.f1422i) {
            c0109g.f1426b.dismiss();
        }
    }
}

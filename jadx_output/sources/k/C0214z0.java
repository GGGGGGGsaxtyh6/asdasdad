package k;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: k.z0, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0214z0 implements AdapterView.OnItemSelectedListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ G0 f2072a;

    public C0214z0(G0 g0) {
        this.f2072a = g0;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView adapterView, View view, int i2, long j2) {
        C0202t0 c0202t0;
        if (i2 != -1 && (c0202t0 = this.f2072a.f1766c) != null) {
            c0202t0.setListSelectionHidden(false);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView adapterView) {
    }
}

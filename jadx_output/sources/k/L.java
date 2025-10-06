package k;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ListAdapter;
import androidx.appcompat.app.AlertController$RecycleListView;
import g.C0106d;
import g.DialogC0110h;

/* loaded from: classes.dex */
public final class L implements Q, DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public DialogC0110h f1799a;

    /* renamed from: b, reason: collision with root package name */
    public M f1800b;

    /* renamed from: c, reason: collision with root package name */
    public CharSequence f1801c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ S f1802d;

    public L(S s2) {
        this.f1802d = s2;
    }

    @Override // k.Q
    public final CharSequence a() {
        return this.f1801c;
    }

    @Override // k.Q
    public final boolean b() {
        DialogC0110h dialogC0110h = this.f1799a;
        if (dialogC0110h != null) {
            return dialogC0110h.isShowing();
        }
        return false;
    }

    @Override // k.Q
    public final void c(int i2) {
        Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
    }

    @Override // k.Q
    public final int d() {
        return 0;
    }

    @Override // k.Q
    public final void dismiss() {
        DialogC0110h dialogC0110h = this.f1799a;
        if (dialogC0110h != null) {
            dialogC0110h.dismiss();
            this.f1799a = null;
        }
    }

    @Override // k.Q
    public final void g(int i2, int i3) {
        if (this.f1800b == null) {
            return;
        }
        S s2 = this.f1802d;
        B0.F f2 = new B0.F(s2.getPopupContext());
        CharSequence charSequence = this.f1801c;
        C0106d c0106d = (C0106d) f2.f80b;
        if (charSequence != null) {
            c0106d.f1418d = charSequence;
        }
        M m2 = this.f1800b;
        int selectedItemPosition = s2.getSelectedItemPosition();
        c0106d.f1421g = m2;
        c0106d.h = this;
        c0106d.f1423j = selectedItemPosition;
        c0106d.f1422i = true;
        DialogC0110h a2 = f2.a();
        this.f1799a = a2;
        AlertController$RecycleListView alertController$RecycleListView = a2.f1452f.f1429e;
        alertController$RecycleListView.setTextDirection(i2);
        alertController$RecycleListView.setTextAlignment(i3);
        this.f1799a.show();
    }

    @Override // k.Q
    public final void h(CharSequence charSequence) {
        this.f1801c = charSequence;
    }

    @Override // k.Q
    public final int j() {
        return 0;
    }

    @Override // k.Q
    public final void k(Drawable drawable) {
        Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
    }

    @Override // k.Q
    public final void m(int i2) {
        Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
    }

    @Override // k.Q
    public final Drawable n() {
        return null;
    }

    @Override // k.Q
    public final void o(ListAdapter listAdapter) {
        this.f1800b = (M) listAdapter;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i2) {
        S s2 = this.f1802d;
        s2.setSelection(i2);
        if (s2.getOnItemClickListener() != null) {
            s2.performItemClick(null, i2, this.f1800b.getItemId(i2));
        }
        dismiss();
    }

    @Override // k.Q
    public final void p(int i2) {
        Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
    }
}

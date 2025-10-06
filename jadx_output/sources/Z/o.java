package Z;

import android.view.View;
import android.widget.AdapterView;
import htb.d3vnu11.securenotes.MainActivity;
import java.util.List;
import k.O;
import k.S;

/* loaded from: classes.dex */
public final class o implements AdapterView.OnItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f721a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f722b;

    public /* synthetic */ o(int i2, Object obj) {
        this.f721a = i2;
        this.f722b = obj;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        List list;
        List list2;
        switch (this.f721a) {
            case 0:
                MainActivity mainActivity = (MainActivity) this.f722b;
                list = mainActivity.notes;
                if (i2 < list.size()) {
                    list2 = mainActivity.notes;
                    mainActivity.openNote((r) list2.get(i2));
                    return;
                }
                return;
            default:
                O o2 = (O) this.f722b;
                o2.f1820F.setSelection(i2);
                S s2 = o2.f1820F;
                if (s2.getOnItemClickListener() != null) {
                    s2.performItemClick(view, i2, o2.f1817C.getItemId(i2));
                }
                o2.dismiss();
                return;
        }
    }
}

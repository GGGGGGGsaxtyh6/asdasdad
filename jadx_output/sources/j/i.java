package j;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import org.conscrypt.R;

/* loaded from: classes.dex */
public final class i extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    public int f1644a = -1;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j f1645b;

    public i(j jVar) {
        this.f1645b = jVar;
        a();
    }

    public final void a() {
        n nVar = this.f1645b.f1648c;
        p pVar = nVar.f1678v;
        if (pVar != null) {
            nVar.i();
            ArrayList arrayList = nVar.f1666j;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((p) arrayList.get(i2)) == pVar) {
                    this.f1644a = i2;
                    return;
                }
            }
        }
        this.f1644a = -1;
    }

    @Override // android.widget.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final p getItem(int i2) {
        j jVar = this.f1645b;
        n nVar = jVar.f1648c;
        nVar.i();
        ArrayList arrayList = nVar.f1666j;
        jVar.getClass();
        int i3 = this.f1644a;
        if (i3 >= 0 && i2 >= i3) {
            i2++;
        }
        return (p) arrayList.get(i2);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        j jVar = this.f1645b;
        n nVar = jVar.f1648c;
        nVar.i();
        int size = nVar.f1666j.size();
        jVar.getClass();
        if (this.f1644a < 0) {
            return size;
        }
        return size - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public final View getView(int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f1645b.f1647b.inflate(R.layout.abc_list_menu_item_layout, viewGroup, false);
        }
        ((InterfaceC0133A) view).c(getItem(i2));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}

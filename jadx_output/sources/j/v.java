package j;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
public abstract class v implements InterfaceC0136D, z, AdapterView.OnItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    public Rect f1721a;

    public static int m(ListAdapter listAdapter, Context context, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        int i3 = 0;
        int i4 = 0;
        FrameLayout frameLayout = null;
        View view = null;
        for (int i5 = 0; i5 < count; i5++) {
            int itemViewType = listAdapter.getItemViewType(i5);
            if (itemViewType != i4) {
                view = null;
                i4 = itemViewType;
            }
            if (frameLayout == null) {
                frameLayout = new FrameLayout(context);
            }
            view = listAdapter.getView(i5, view, frameLayout);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i2) {
                return i2;
            }
            if (measuredWidth > i3) {
                i3 = measuredWidth;
            }
        }
        return i3;
    }

    public static boolean u(n nVar) {
        int size = nVar.f1663f.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = nVar.getItem(i2);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    @Override // j.z
    public final boolean d(p pVar) {
        return false;
    }

    @Override // j.z
    public final boolean k(p pVar) {
        return false;
    }

    public abstract void l(n nVar);

    public abstract void n(View view);

    public abstract void o(boolean z2);

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        k kVar;
        int i3;
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        if (listAdapter instanceof HeaderViewListAdapter) {
            kVar = (k) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter();
        } else {
            kVar = (k) listAdapter;
        }
        n nVar = kVar.f1652a;
        MenuItem menuItem = (MenuItem) listAdapter.getItem(i2);
        if (!(this instanceof ViewOnKeyListenerC0146h)) {
            i3 = 0;
        } else {
            i3 = 4;
        }
        nVar.q(menuItem, this, i3);
    }

    public abstract void p(int i2);

    public abstract void q(int i2);

    public abstract void r(PopupWindow.OnDismissListener onDismissListener);

    public abstract void s(boolean z2);

    public abstract void t(int i2);

    @Override // j.z
    public final void f(Context context, n nVar) {
    }
}

package k;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;

/* loaded from: classes.dex */
public final class K0 extends C0202t0 {

    /* renamed from: m, reason: collision with root package name */
    public final int f1795m;

    /* renamed from: n, reason: collision with root package name */
    public final int f1796n;

    /* renamed from: o, reason: collision with root package name */
    public H0 f1797o;

    /* renamed from: p, reason: collision with root package name */
    public j.p f1798p;

    public K0(Context context, boolean z2) {
        super(context, z2);
        if (1 == context.getResources().getConfiguration().getLayoutDirection()) {
            this.f1795m = 21;
            this.f1796n = 22;
        } else {
            this.f1795m = 22;
            this.f1796n = 21;
        }
    }

    @Override // k.C0202t0, android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        j.k kVar;
        int i2;
        j.p pVar;
        int pointToPosition;
        int i3;
        if (this.f1797o != null) {
            ListAdapter adapter = getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                i2 = headerViewListAdapter.getHeadersCount();
                kVar = (j.k) headerViewListAdapter.getWrappedAdapter();
            } else {
                kVar = (j.k) adapter;
                i2 = 0;
            }
            if (motionEvent.getAction() != 10 && (pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i3 = pointToPosition - i2) >= 0 && i3 < kVar.getCount()) {
                pVar = kVar.getItem(i3);
            } else {
                pVar = null;
            }
            j.p pVar2 = this.f1798p;
            if (pVar2 != pVar) {
                j.n nVar = kVar.f1652a;
                if (pVar2 != null) {
                    this.f1797o.l(nVar, pVar2);
                }
                this.f1798p = pVar;
                if (pVar != null) {
                    this.f1797o.f(nVar, pVar);
                }
            }
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i2, KeyEvent keyEvent) {
        j.k kVar;
        ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
        if (listMenuItemView != null && i2 == this.f1795m) {
            if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
            }
            return true;
        }
        if (listMenuItemView != null && i2 == this.f1796n) {
            setSelection(-1);
            ListAdapter adapter = getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                kVar = (j.k) ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            } else {
                kVar = (j.k) adapter;
            }
            kVar.f1652a.c(false);
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    public void setHoverListener(H0 h0) {
        this.f1797o = h0;
    }

    @Override // k.C0202t0, android.widget.AbsListView
    public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
        super.setSelector(drawable);
    }
}

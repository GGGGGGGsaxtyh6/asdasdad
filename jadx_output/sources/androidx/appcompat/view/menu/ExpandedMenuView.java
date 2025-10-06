package androidx.appcompat.view.menu;

import D0.h;
import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import j.InterfaceC0134B;
import j.m;
import j.n;
import j.p;

/* loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements m, InterfaceC0134B, AdapterView.OnItemClickListener {

    /* renamed from: b, reason: collision with root package name */
    public static final int[] f806b = {R.attr.background, R.attr.divider};

    /* renamed from: a, reason: collision with root package name */
    public n f807a;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        h p2 = h.p(context, attributeSet, f806b, R.attr.listViewStyle);
        TypedArray typedArray = (TypedArray) p2.f259c;
        if (typedArray.hasValue(0)) {
            setBackgroundDrawable(p2.j(0));
        }
        if (typedArray.hasValue(1)) {
            setDivider(p2.j(1));
        }
        p2.r();
    }

    @Override // j.InterfaceC0134B
    public final void a(n nVar) {
        this.f807a = nVar;
    }

    @Override // j.m
    public final boolean b(p pVar) {
        return this.f807a.q(pVar, null, 0);
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        b((p) getAdapter().getItem(i2));
    }
}

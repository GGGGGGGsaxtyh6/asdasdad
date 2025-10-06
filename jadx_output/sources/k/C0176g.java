package k;

import android.content.Context;
import android.view.View;
import j.SubMenuC0138F;
import org.conscrypt.R;

/* renamed from: k.g, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0176g extends j.x {

    /* renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f1909l = 0;

    /* renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0184k f1910m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0176g(C0184k c0184k, Context context, j.n nVar, View view) {
        super(R.attr.actionOverflowMenuStyle, context, view, nVar, true);
        this.f1910m = c0184k;
        this.f1728f = 8388613;
        A.f fVar = c0184k.f1972w;
        this.h = fVar;
        j.v vVar = this.f1730i;
        if (vVar != null) {
            vVar.h(fVar);
        }
    }

    @Override // j.x
    public final void c() {
        switch (this.f1909l) {
            case 0:
                C0184k c0184k = this.f1910m;
                c0184k.f1969t = null;
                c0184k.getClass();
                super.c();
                return;
            default:
                C0184k c0184k2 = this.f1910m;
                j.n nVar = c0184k2.f1953c;
                if (nVar != null) {
                    nVar.c(true);
                }
                c0184k2.f1968s = null;
                super.c();
                return;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0176g(C0184k c0184k, Context context, SubMenuC0138F subMenuC0138F, View view) {
        super(R.attr.actionOverflowMenuStyle, context, view, subMenuC0138F, false);
        this.f1910m = c0184k;
        if (!subMenuC0138F.f1591A.f()) {
            View view2 = c0184k.f1958i;
            this.f1727e = view2 == null ? (View) c0184k.h : view2;
        }
        A.f fVar = c0184k.f1972w;
        this.h = fVar;
        j.v vVar = this.f1730i;
        if (vVar != null) {
            vVar.h(fVar);
        }
    }
}

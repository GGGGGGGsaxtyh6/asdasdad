package j;

import android.view.View;
import androidx.appcompat.view.menu.ActionMenuItemView;
import k.AbstractViewOnTouchListenerC0208w0;
import k.C0176g;
import k.C0178h;
import k.C0182j;
import k.C0184k;

/* renamed from: j.b, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0140b extends AbstractViewOnTouchListenerC0208w0 {

    /* renamed from: j, reason: collision with root package name */
    public final /* synthetic */ int f1608j = 0;

    /* renamed from: k, reason: collision with root package name */
    public final /* synthetic */ View f1609k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0140b(ActionMenuItemView actionMenuItemView) {
        super(actionMenuItemView);
        this.f1609k = actionMenuItemView;
    }

    @Override // k.AbstractViewOnTouchListenerC0208w0
    public final InterfaceC0136D b() {
        C0176g c0176g;
        switch (this.f1608j) {
            case 0:
                AbstractC0141c abstractC0141c = ((ActionMenuItemView) this.f1609k).f800m;
                if (abstractC0141c == null || (c0176g = ((C0178h) abstractC0141c).f1913a.f1969t) == null) {
                    return null;
                }
                return c0176g.a();
            default:
                C0176g c0176g2 = ((C0182j) this.f1609k).f1946d.f1968s;
                if (c0176g2 == null) {
                    return null;
                }
                return c0176g2.a();
        }
    }

    @Override // k.AbstractViewOnTouchListenerC0208w0
    public final boolean c() {
        InterfaceC0136D b2;
        switch (this.f1608j) {
            case 0:
                ActionMenuItemView actionMenuItemView = (ActionMenuItemView) this.f1609k;
                m mVar = actionMenuItemView.f798k;
                if (mVar == null || !mVar.b(actionMenuItemView.h) || (b2 = b()) == null || !b2.b()) {
                    return false;
                }
                return true;
            default:
                ((C0182j) this.f1609k).f1946d.l();
                return true;
        }
    }

    @Override // k.AbstractViewOnTouchListenerC0208w0
    public boolean d() {
        switch (this.f1608j) {
            case 1:
                C0184k c0184k = ((C0182j) this.f1609k).f1946d;
                if (c0184k.f1970u != null) {
                    return false;
                }
                c0184k.e();
                return true;
            default:
                return super.d();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0140b(C0182j c0182j, C0182j c0182j2) {
        super(c0182j2);
        this.f1609k = c0182j;
    }
}

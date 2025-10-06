package a;

import a0.C0051e;
import b0.AbstractC0078d;
import b0.C0080f;
import j0.AbstractC0150d;
import j0.AbstractC0151e;
import java.util.ListIterator;

/* loaded from: classes.dex */
public final class o extends AbstractC0151e implements i0.l {

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f758b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f759c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o(int i2, Object obj) {
        super(1);
        this.f758b = i2;
        this.f759c = obj;
    }

    public final Object c(Object obj) {
        Object obj2;
        Object obj3;
        switch (this.f758b) {
            case 0:
                AbstractC0150d.e((C0034a) obj, "backEvent");
                v vVar = (v) this.f759c;
                C0080f c0080f = vVar.f780b;
                ListIterator listIterator = c0080f.listIterator(c0080f.size());
                while (true) {
                    if (listIterator.hasPrevious()) {
                        obj2 = listIterator.previous();
                        if (((androidx.fragment.app.o) obj2).f1066a) {
                        }
                    } else {
                        obj2 = null;
                    }
                }
                androidx.fragment.app.o oVar = (androidx.fragment.app.o) obj2;
                if (vVar.f781c != null) {
                    vVar.a();
                }
                vVar.f781c = oVar;
                return C0051e.f792c;
            case 1:
                AbstractC0150d.e((C0034a) obj, "backEvent");
                v vVar2 = (v) this.f759c;
                if (vVar2.f781c == null) {
                    C0080f c0080f2 = vVar2.f780b;
                    ListIterator listIterator2 = c0080f2.listIterator(c0080f2.size());
                    while (true) {
                        if (listIterator2.hasPrevious()) {
                            obj3 = listIterator2.previous();
                            if (((androidx.fragment.app.o) obj3).f1066a) {
                            }
                        } else {
                            obj3 = null;
                        }
                    }
                }
                return C0051e.f792c;
            default:
                if (obj == ((AbstractC0078d) this.f759c)) {
                    return "(this Collection)";
                }
                return String.valueOf(obj);
        }
    }
}

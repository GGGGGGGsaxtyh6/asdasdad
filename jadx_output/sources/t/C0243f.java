package t;

import java.util.ArrayList;
import java.util.Iterator;
import org.conscrypt.ct.CTConstants;

/* renamed from: t.f, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0243f implements InterfaceC0241d {

    /* renamed from: d, reason: collision with root package name */
    public final o f2374d;

    /* renamed from: f, reason: collision with root package name */
    public int f2376f;

    /* renamed from: g, reason: collision with root package name */
    public int f2377g;

    /* renamed from: a, reason: collision with root package name */
    public o f2371a = null;

    /* renamed from: b, reason: collision with root package name */
    public boolean f2372b = false;

    /* renamed from: c, reason: collision with root package name */
    public boolean f2373c = false;

    /* renamed from: e, reason: collision with root package name */
    public int f2375e = 1;
    public int h = 1;

    /* renamed from: i, reason: collision with root package name */
    public C0244g f2378i = null;

    /* renamed from: j, reason: collision with root package name */
    public boolean f2379j = false;

    /* renamed from: k, reason: collision with root package name */
    public final ArrayList f2380k = new ArrayList();

    /* renamed from: l, reason: collision with root package name */
    public final ArrayList f2381l = new ArrayList();

    public C0243f(o oVar) {
        this.f2374d = oVar;
    }

    @Override // t.InterfaceC0241d
    public final void a(InterfaceC0241d interfaceC0241d) {
        ArrayList arrayList = this.f2381l;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!((C0243f) it.next()).f2379j) {
                return;
            }
        }
        this.f2373c = true;
        o oVar = this.f2371a;
        if (oVar != null) {
            oVar.a(this);
        }
        if (this.f2372b) {
            this.f2374d.a(this);
            return;
        }
        Iterator it2 = arrayList.iterator();
        C0243f c0243f = null;
        int i2 = 0;
        while (it2.hasNext()) {
            C0243f c0243f2 = (C0243f) it2.next();
            if (!(c0243f2 instanceof C0244g)) {
                i2++;
                c0243f = c0243f2;
            }
        }
        if (c0243f != null && i2 == 1 && c0243f.f2379j) {
            C0244g c0244g = this.f2378i;
            if (c0244g != null) {
                if (c0244g.f2379j) {
                    this.f2376f = this.h * c0244g.f2377g;
                } else {
                    return;
                }
            }
            d(c0243f.f2377g + this.f2376f);
        }
        o oVar2 = this.f2371a;
        if (oVar2 != null) {
            oVar2.a(this);
        }
    }

    public final void b(o oVar) {
        this.f2380k.add(oVar);
        if (this.f2379j) {
            oVar.a(oVar);
        }
    }

    public final void c() {
        this.f2381l.clear();
        this.f2380k.clear();
        this.f2379j = false;
        this.f2377g = 0;
        this.f2373c = false;
        this.f2372b = false;
    }

    public void d(int i2) {
        if (this.f2379j) {
            return;
        }
        this.f2379j = true;
        this.f2377g = i2;
        Iterator it = this.f2380k.iterator();
        while (it.hasNext()) {
            InterfaceC0241d interfaceC0241d = (InterfaceC0241d) it.next();
            interfaceC0241d.a(interfaceC0241d);
        }
    }

    public final String toString() {
        String str;
        Object obj;
        StringBuilder sb = new StringBuilder();
        sb.append(this.f2374d.f2396b.g0);
        sb.append(":");
        switch (this.f2375e) {
            case 1:
                str = "UNKNOWN";
                break;
            case 2:
                str = "HORIZONTAL_DIMENSION";
                break;
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                str = "VERTICAL_DIMENSION";
                break;
            case 4:
                str = "LEFT";
                break;
            case 5:
                str = "RIGHT";
                break;
            case 6:
                str = "TOP";
                break;
            case 7:
                str = "BOTTOM";
                break;
            case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                str = "BASELINE";
                break;
            default:
                str = "null";
                break;
        }
        sb.append(str);
        sb.append("(");
        if (this.f2379j) {
            obj = Integer.valueOf(this.f2377g);
        } else {
            obj = "unresolved";
        }
        sb.append(obj);
        sb.append(") <t=");
        sb.append(this.f2381l.size());
        sb.append(":d=");
        sb.append(this.f2380k.size());
        sb.append(">");
        return sb.toString();
    }
}

package i;

import k.e1;

/* renamed from: i.j, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0128j extends C0.d {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1562a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f1563b;

    /* renamed from: c, reason: collision with root package name */
    public int f1564c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f1565d;

    public C0128j(C0129k c0129k) {
        this.f1562a = 0;
        this.f1565d = c0129k;
        this.f1563b = false;
        this.f1564c = 0;
    }

    @Override // H.T
    public final void a() {
        switch (this.f1562a) {
            case 0:
                int i2 = this.f1564c + 1;
                this.f1564c = i2;
                C0129k c0129k = (C0129k) this.f1565d;
                if (i2 == c0129k.f1566a.size()) {
                    C0.d dVar = c0129k.f1569d;
                    if (dVar != null) {
                        dVar.a();
                    }
                    this.f1564c = 0;
                    this.f1563b = false;
                    c0129k.f1570e = false;
                    return;
                }
                return;
            default:
                if (!this.f1563b) {
                    ((e1) this.f1565d).f1895a.setVisibility(this.f1564c);
                    return;
                }
                return;
        }
    }

    @Override // C0.d, H.T
    public void b() {
        switch (this.f1562a) {
            case 1:
                this.f1563b = true;
                return;
            default:
                return;
        }
    }

    @Override // C0.d, H.T
    public final void c() {
        switch (this.f1562a) {
            case 0:
                if (!this.f1563b) {
                    this.f1563b = true;
                    C0.d dVar = ((C0129k) this.f1565d).f1569d;
                    if (dVar != null) {
                        dVar.c();
                        return;
                    }
                    return;
                }
                return;
            default:
                ((e1) this.f1565d).f1895a.setVisibility(0);
                return;
        }
    }

    public C0128j(e1 e1Var, int i2) {
        this.f1562a = 1;
        this.f1565d = e1Var;
        this.f1564c = i2;
        this.f1563b = false;
    }
}

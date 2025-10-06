package v;

import s.C0234a;

/* renamed from: v.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0251a extends AbstractC0253c {
    public int h;

    /* renamed from: i, reason: collision with root package name */
    public int f2590i;

    /* renamed from: j, reason: collision with root package name */
    public C0234a f2591j;

    public boolean getAllowsGoneWidget() {
        return this.f2591j.f2237s0;
    }

    public int getMargin() {
        return this.f2591j.t0;
    }

    public int getType() {
        return this.h;
    }

    public void setAllowsGoneWidget(boolean z2) {
        this.f2591j.f2237s0 = z2;
    }

    public void setDpMargin(int i2) {
        this.f2591j.t0 = (int) ((i2 * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setMargin(int i2) {
        this.f2591j.t0 = i2;
    }

    public void setType(int i2) {
        this.h = i2;
    }
}

package v;

import android.view.ViewGroup;
import s.C0237d;

/* loaded from: classes.dex */
public final class e extends ViewGroup.MarginLayoutParams {

    /* renamed from: A, reason: collision with root package name */
    public int f2607A;

    /* renamed from: B, reason: collision with root package name */
    public int f2608B;

    /* renamed from: C, reason: collision with root package name */
    public int f2609C;

    /* renamed from: D, reason: collision with root package name */
    public int f2610D;

    /* renamed from: E, reason: collision with root package name */
    public float f2611E;

    /* renamed from: F, reason: collision with root package name */
    public float f2612F;

    /* renamed from: G, reason: collision with root package name */
    public String f2613G;

    /* renamed from: H, reason: collision with root package name */
    public float f2614H;

    /* renamed from: I, reason: collision with root package name */
    public float f2615I;

    /* renamed from: J, reason: collision with root package name */
    public int f2616J;

    /* renamed from: K, reason: collision with root package name */
    public int f2617K;

    /* renamed from: L, reason: collision with root package name */
    public int f2618L;

    /* renamed from: M, reason: collision with root package name */
    public int f2619M;

    /* renamed from: N, reason: collision with root package name */
    public int f2620N;

    /* renamed from: O, reason: collision with root package name */
    public int f2621O;

    /* renamed from: P, reason: collision with root package name */
    public int f2622P;

    /* renamed from: Q, reason: collision with root package name */
    public int f2623Q;

    /* renamed from: R, reason: collision with root package name */
    public float f2624R;

    /* renamed from: S, reason: collision with root package name */
    public float f2625S;

    /* renamed from: T, reason: collision with root package name */
    public int f2626T;

    /* renamed from: U, reason: collision with root package name */
    public int f2627U;

    /* renamed from: V, reason: collision with root package name */
    public int f2628V;

    /* renamed from: W, reason: collision with root package name */
    public boolean f2629W;

    /* renamed from: X, reason: collision with root package name */
    public boolean f2630X;

    /* renamed from: Y, reason: collision with root package name */
    public String f2631Y;

    /* renamed from: Z, reason: collision with root package name */
    public int f2632Z;

    /* renamed from: a, reason: collision with root package name */
    public int f2633a;

    /* renamed from: a0, reason: collision with root package name */
    public boolean f2634a0;

    /* renamed from: b, reason: collision with root package name */
    public int f2635b;

    /* renamed from: b0, reason: collision with root package name */
    public boolean f2636b0;

    /* renamed from: c, reason: collision with root package name */
    public float f2637c;

    /* renamed from: c0, reason: collision with root package name */
    public boolean f2638c0;

    /* renamed from: d, reason: collision with root package name */
    public boolean f2639d;

    /* renamed from: d0, reason: collision with root package name */
    public boolean f2640d0;

    /* renamed from: e, reason: collision with root package name */
    public int f2641e;

    /* renamed from: e0, reason: collision with root package name */
    public boolean f2642e0;

    /* renamed from: f, reason: collision with root package name */
    public int f2643f;

    /* renamed from: f0, reason: collision with root package name */
    public int f2644f0;

    /* renamed from: g, reason: collision with root package name */
    public int f2645g;
    public int g0;
    public int h;
    public int h0;

    /* renamed from: i, reason: collision with root package name */
    public int f2646i;

    /* renamed from: i0, reason: collision with root package name */
    public int f2647i0;

    /* renamed from: j, reason: collision with root package name */
    public int f2648j;

    /* renamed from: j0, reason: collision with root package name */
    public int f2649j0;

    /* renamed from: k, reason: collision with root package name */
    public int f2650k;

    /* renamed from: k0, reason: collision with root package name */
    public int f2651k0;

    /* renamed from: l, reason: collision with root package name */
    public int f2652l;

    /* renamed from: l0, reason: collision with root package name */
    public float f2653l0;

    /* renamed from: m, reason: collision with root package name */
    public int f2654m;

    /* renamed from: m0, reason: collision with root package name */
    public int f2655m0;

    /* renamed from: n, reason: collision with root package name */
    public int f2656n;

    /* renamed from: n0, reason: collision with root package name */
    public int f2657n0;

    /* renamed from: o, reason: collision with root package name */
    public int f2658o;

    /* renamed from: o0, reason: collision with root package name */
    public float f2659o0;

    /* renamed from: p, reason: collision with root package name */
    public int f2660p;

    /* renamed from: p0, reason: collision with root package name */
    public C0237d f2661p0;

    /* renamed from: q, reason: collision with root package name */
    public int f2662q;

    /* renamed from: r, reason: collision with root package name */
    public float f2663r;

    /* renamed from: s, reason: collision with root package name */
    public int f2664s;

    /* renamed from: t, reason: collision with root package name */
    public int f2665t;

    /* renamed from: u, reason: collision with root package name */
    public int f2666u;

    /* renamed from: v, reason: collision with root package name */
    public int f2667v;

    /* renamed from: w, reason: collision with root package name */
    public int f2668w;

    /* renamed from: x, reason: collision with root package name */
    public int f2669x;
    public int y;

    /* renamed from: z, reason: collision with root package name */
    public int f2670z;

    public final void a() {
        this.f2640d0 = false;
        this.f2634a0 = true;
        this.f2636b0 = true;
        int i2 = ((ViewGroup.MarginLayoutParams) this).width;
        if (i2 == -2 && this.f2629W) {
            this.f2634a0 = false;
            if (this.f2618L == 0) {
                this.f2618L = 1;
            }
        }
        int i3 = ((ViewGroup.MarginLayoutParams) this).height;
        if (i3 == -2 && this.f2630X) {
            this.f2636b0 = false;
            if (this.f2619M == 0) {
                this.f2619M = 1;
            }
        }
        if (i2 == 0 || i2 == -1) {
            this.f2634a0 = false;
            if (i2 == 0 && this.f2618L == 1) {
                ((ViewGroup.MarginLayoutParams) this).width = -2;
                this.f2629W = true;
            }
        }
        if (i3 == 0 || i3 == -1) {
            this.f2636b0 = false;
            if (i3 == 0 && this.f2619M == 1) {
                ((ViewGroup.MarginLayoutParams) this).height = -2;
                this.f2630X = true;
            }
        }
        if (this.f2637c != -1.0f || this.f2633a != -1 || this.f2635b != -1) {
            this.f2640d0 = true;
            this.f2634a0 = true;
            this.f2636b0 = true;
            if (!(this.f2661p0 instanceof s.f)) {
                this.f2661p0 = new s.f();
            }
            ((s.f) this.f2661p0).O(this.f2628V);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0082  */
    @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void resolveLayoutDirection(int r11) {
        /*
            Method dump skipped, instructions count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: v.e.resolveLayoutDirection(int):void");
    }
}

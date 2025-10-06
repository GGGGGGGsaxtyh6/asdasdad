package v;

import android.view.ViewGroup;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class i {

    /* renamed from: a, reason: collision with root package name */
    public int f2694a;

    /* renamed from: b, reason: collision with root package name */
    public final l f2695b;

    /* renamed from: c, reason: collision with root package name */
    public final k f2696c;

    /* renamed from: d, reason: collision with root package name */
    public final j f2697d;

    /* renamed from: e, reason: collision with root package name */
    public final m f2698e;

    /* renamed from: f, reason: collision with root package name */
    public HashMap f2699f;

    /* JADX WARN: Type inference failed for: r0v0, types: [v.l, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v1, types: [v.k, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v2, types: [v.j, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3, types: [v.m, java.lang.Object] */
    public i() {
        ?? obj = new Object();
        obj.f2773a = 0;
        obj.f2774b = 0;
        obj.f2775c = 1.0f;
        obj.f2776d = Float.NaN;
        this.f2695b = obj;
        ?? obj2 = new Object();
        obj2.f2765a = -1;
        obj2.f2766b = 0;
        obj2.f2767c = -1;
        obj2.f2768d = Float.NaN;
        obj2.f2769e = Float.NaN;
        obj2.f2770f = Float.NaN;
        obj2.f2771g = -1;
        obj2.h = null;
        obj2.f2772i = -1;
        this.f2696c = obj2;
        ?? obj3 = new Object();
        obj3.f2727a = false;
        obj3.f2733d = -1;
        obj3.f2735e = -1;
        obj3.f2737f = -1.0f;
        obj3.f2739g = true;
        obj3.h = -1;
        obj3.f2740i = -1;
        obj3.f2742j = -1;
        obj3.f2744k = -1;
        obj3.f2746l = -1;
        obj3.f2748m = -1;
        obj3.f2750n = -1;
        obj3.f2752o = -1;
        obj3.f2754p = -1;
        obj3.f2755q = -1;
        obj3.f2756r = -1;
        obj3.f2757s = -1;
        obj3.f2758t = -1;
        obj3.f2759u = -1;
        obj3.f2760v = -1;
        obj3.f2761w = 0.5f;
        obj3.f2762x = 0.5f;
        obj3.y = null;
        obj3.f2763z = -1;
        obj3.f2701A = 0;
        obj3.f2702B = 0.0f;
        obj3.f2703C = -1;
        obj3.f2704D = -1;
        obj3.f2705E = -1;
        obj3.f2706F = 0;
        obj3.f2707G = 0;
        obj3.f2708H = 0;
        obj3.f2709I = 0;
        obj3.f2710J = 0;
        obj3.f2711K = 0;
        obj3.f2712L = 0;
        obj3.f2713M = Integer.MIN_VALUE;
        obj3.f2714N = Integer.MIN_VALUE;
        obj3.f2715O = Integer.MIN_VALUE;
        obj3.f2716P = Integer.MIN_VALUE;
        obj3.f2717Q = Integer.MIN_VALUE;
        obj3.f2718R = Integer.MIN_VALUE;
        obj3.f2719S = Integer.MIN_VALUE;
        obj3.f2720T = -1.0f;
        obj3.f2721U = -1.0f;
        obj3.f2722V = 0;
        obj3.f2723W = 0;
        obj3.f2724X = 0;
        obj3.f2725Y = 0;
        obj3.f2726Z = 0;
        obj3.f2728a0 = 0;
        obj3.f2730b0 = 0;
        obj3.f2732c0 = 0;
        obj3.f2734d0 = 1.0f;
        obj3.f2736e0 = 1.0f;
        obj3.f2738f0 = -1;
        obj3.g0 = 0;
        obj3.h0 = -1;
        obj3.f2747l0 = false;
        obj3.f2749m0 = false;
        obj3.f2751n0 = true;
        obj3.f2753o0 = 0;
        this.f2697d = obj3;
        ?? obj4 = new Object();
        obj4.f2778a = 0.0f;
        obj4.f2779b = 0.0f;
        obj4.f2780c = 0.0f;
        obj4.f2781d = 1.0f;
        obj4.f2782e = 1.0f;
        obj4.f2783f = Float.NaN;
        obj4.f2784g = Float.NaN;
        obj4.h = -1;
        obj4.f2785i = 0.0f;
        obj4.f2786j = 0.0f;
        obj4.f2787k = 0.0f;
        obj4.f2788l = false;
        obj4.f2789m = 0.0f;
        this.f2698e = obj4;
        this.f2699f = new HashMap();
    }

    public final void a(e eVar) {
        j jVar = this.f2697d;
        eVar.f2641e = jVar.h;
        eVar.f2643f = jVar.f2740i;
        eVar.f2645g = jVar.f2742j;
        eVar.h = jVar.f2744k;
        eVar.f2646i = jVar.f2746l;
        eVar.f2648j = jVar.f2748m;
        eVar.f2650k = jVar.f2750n;
        eVar.f2652l = jVar.f2752o;
        eVar.f2654m = jVar.f2754p;
        eVar.f2656n = jVar.f2755q;
        eVar.f2658o = jVar.f2756r;
        eVar.f2664s = jVar.f2757s;
        eVar.f2665t = jVar.f2758t;
        eVar.f2666u = jVar.f2759u;
        eVar.f2667v = jVar.f2760v;
        ((ViewGroup.MarginLayoutParams) eVar).leftMargin = jVar.f2706F;
        ((ViewGroup.MarginLayoutParams) eVar).rightMargin = jVar.f2707G;
        ((ViewGroup.MarginLayoutParams) eVar).topMargin = jVar.f2708H;
        ((ViewGroup.MarginLayoutParams) eVar).bottomMargin = jVar.f2709I;
        eVar.f2607A = jVar.f2718R;
        eVar.f2608B = jVar.f2717Q;
        eVar.f2669x = jVar.f2714N;
        eVar.f2670z = jVar.f2716P;
        eVar.f2611E = jVar.f2761w;
        eVar.f2612F = jVar.f2762x;
        eVar.f2660p = jVar.f2763z;
        eVar.f2662q = jVar.f2701A;
        eVar.f2663r = jVar.f2702B;
        eVar.f2613G = jVar.y;
        eVar.f2626T = jVar.f2703C;
        eVar.f2627U = jVar.f2704D;
        eVar.f2615I = jVar.f2720T;
        eVar.f2614H = jVar.f2721U;
        eVar.f2617K = jVar.f2723W;
        eVar.f2616J = jVar.f2722V;
        eVar.f2629W = jVar.f2747l0;
        eVar.f2630X = jVar.f2749m0;
        eVar.f2618L = jVar.f2724X;
        eVar.f2619M = jVar.f2725Y;
        eVar.f2622P = jVar.f2726Z;
        eVar.f2623Q = jVar.f2728a0;
        eVar.f2620N = jVar.f2730b0;
        eVar.f2621O = jVar.f2732c0;
        eVar.f2624R = jVar.f2734d0;
        eVar.f2625S = jVar.f2736e0;
        eVar.f2628V = jVar.f2705E;
        eVar.f2637c = jVar.f2737f;
        eVar.f2633a = jVar.f2733d;
        eVar.f2635b = jVar.f2735e;
        ((ViewGroup.MarginLayoutParams) eVar).width = jVar.f2729b;
        ((ViewGroup.MarginLayoutParams) eVar).height = jVar.f2731c;
        String str = jVar.f2745k0;
        if (str != null) {
            eVar.f2631Y = str;
        }
        eVar.f2632Z = jVar.f2753o0;
        eVar.setMarginStart(jVar.f2711K);
        eVar.setMarginEnd(jVar.f2710J);
        eVar.a();
    }

    public final Object clone() {
        i iVar = new i();
        j jVar = iVar.f2697d;
        jVar.getClass();
        j jVar2 = this.f2697d;
        jVar.f2727a = jVar2.f2727a;
        jVar.f2729b = jVar2.f2729b;
        jVar.f2731c = jVar2.f2731c;
        jVar.f2733d = jVar2.f2733d;
        jVar.f2735e = jVar2.f2735e;
        jVar.f2737f = jVar2.f2737f;
        jVar.f2739g = jVar2.f2739g;
        jVar.h = jVar2.h;
        jVar.f2740i = jVar2.f2740i;
        jVar.f2742j = jVar2.f2742j;
        jVar.f2744k = jVar2.f2744k;
        jVar.f2746l = jVar2.f2746l;
        jVar.f2748m = jVar2.f2748m;
        jVar.f2750n = jVar2.f2750n;
        jVar.f2752o = jVar2.f2752o;
        jVar.f2754p = jVar2.f2754p;
        jVar.f2755q = jVar2.f2755q;
        jVar.f2756r = jVar2.f2756r;
        jVar.f2757s = jVar2.f2757s;
        jVar.f2758t = jVar2.f2758t;
        jVar.f2759u = jVar2.f2759u;
        jVar.f2760v = jVar2.f2760v;
        jVar.f2761w = jVar2.f2761w;
        jVar.f2762x = jVar2.f2762x;
        jVar.y = jVar2.y;
        jVar.f2763z = jVar2.f2763z;
        jVar.f2701A = jVar2.f2701A;
        jVar.f2702B = jVar2.f2702B;
        jVar.f2703C = jVar2.f2703C;
        jVar.f2704D = jVar2.f2704D;
        jVar.f2705E = jVar2.f2705E;
        jVar.f2706F = jVar2.f2706F;
        jVar.f2707G = jVar2.f2707G;
        jVar.f2708H = jVar2.f2708H;
        jVar.f2709I = jVar2.f2709I;
        jVar.f2710J = jVar2.f2710J;
        jVar.f2711K = jVar2.f2711K;
        jVar.f2712L = jVar2.f2712L;
        jVar.f2713M = jVar2.f2713M;
        jVar.f2714N = jVar2.f2714N;
        jVar.f2715O = jVar2.f2715O;
        jVar.f2716P = jVar2.f2716P;
        jVar.f2717Q = jVar2.f2717Q;
        jVar.f2718R = jVar2.f2718R;
        jVar.f2719S = jVar2.f2719S;
        jVar.f2720T = jVar2.f2720T;
        jVar.f2721U = jVar2.f2721U;
        jVar.f2722V = jVar2.f2722V;
        jVar.f2723W = jVar2.f2723W;
        jVar.f2724X = jVar2.f2724X;
        jVar.f2725Y = jVar2.f2725Y;
        jVar.f2726Z = jVar2.f2726Z;
        jVar.f2728a0 = jVar2.f2728a0;
        jVar.f2730b0 = jVar2.f2730b0;
        jVar.f2732c0 = jVar2.f2732c0;
        jVar.f2734d0 = jVar2.f2734d0;
        jVar.f2736e0 = jVar2.f2736e0;
        jVar.f2738f0 = jVar2.f2738f0;
        jVar.g0 = jVar2.g0;
        jVar.h0 = jVar2.h0;
        jVar.f2745k0 = jVar2.f2745k0;
        int[] iArr = jVar2.f2741i0;
        if (iArr != null && jVar2.f2743j0 == null) {
            jVar.f2741i0 = Arrays.copyOf(iArr, iArr.length);
        } else {
            jVar.f2741i0 = null;
        }
        jVar.f2743j0 = jVar2.f2743j0;
        jVar.f2747l0 = jVar2.f2747l0;
        jVar.f2749m0 = jVar2.f2749m0;
        jVar.f2751n0 = jVar2.f2751n0;
        jVar.f2753o0 = jVar2.f2753o0;
        k kVar = iVar.f2696c;
        kVar.getClass();
        k kVar2 = this.f2696c;
        kVar2.getClass();
        kVar.f2765a = kVar2.f2765a;
        kVar.f2767c = kVar2.f2767c;
        kVar.f2769e = kVar2.f2769e;
        kVar.f2768d = kVar2.f2768d;
        l lVar = iVar.f2695b;
        l lVar2 = this.f2695b;
        lVar.f2773a = lVar2.f2773a;
        lVar.f2775c = lVar2.f2775c;
        lVar.f2776d = lVar2.f2776d;
        lVar.f2774b = lVar2.f2774b;
        m mVar = iVar.f2698e;
        mVar.getClass();
        m mVar2 = this.f2698e;
        mVar2.getClass();
        mVar.f2778a = mVar2.f2778a;
        mVar.f2779b = mVar2.f2779b;
        mVar.f2780c = mVar2.f2780c;
        mVar.f2781d = mVar2.f2781d;
        mVar.f2782e = mVar2.f2782e;
        mVar.f2783f = mVar2.f2783f;
        mVar.f2784g = mVar2.f2784g;
        mVar.h = mVar2.h;
        mVar.f2785i = mVar2.f2785i;
        mVar.f2786j = mVar2.f2786j;
        mVar.f2787k = mVar2.f2787k;
        mVar.f2788l = mVar2.f2788l;
        mVar.f2789m = mVar2.f2789m;
        iVar.f2694a = this.f2694a;
        return iVar;
    }
}

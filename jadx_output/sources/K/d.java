package K;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import k.C0202t0;

/* loaded from: classes.dex */
public final class d implements View.OnTouchListener {

    /* renamed from: r, reason: collision with root package name */
    public static final int f490r = ViewConfiguration.getTapTimeout();

    /* renamed from: a, reason: collision with root package name */
    public final a f491a;

    /* renamed from: b, reason: collision with root package name */
    public final AccelerateInterpolator f492b;

    /* renamed from: c, reason: collision with root package name */
    public final ListView f493c;

    /* renamed from: d, reason: collision with root package name */
    public E.b f494d;

    /* renamed from: e, reason: collision with root package name */
    public final float[] f495e;

    /* renamed from: f, reason: collision with root package name */
    public final float[] f496f;

    /* renamed from: g, reason: collision with root package name */
    public final int f497g;
    public final int h;

    /* renamed from: i, reason: collision with root package name */
    public final float[] f498i;

    /* renamed from: j, reason: collision with root package name */
    public final float[] f499j;

    /* renamed from: k, reason: collision with root package name */
    public final float[] f500k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f501l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f502m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f503n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f504o;

    /* renamed from: p, reason: collision with root package name */
    public boolean f505p;

    /* renamed from: q, reason: collision with root package name */
    public final C0202t0 f506q;

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, K.a] */
    public d(C0202t0 c0202t0) {
        ?? obj = new Object();
        obj.f486e = Long.MIN_VALUE;
        obj.f488g = -1L;
        obj.f487f = 0L;
        this.f491a = obj;
        this.f492b = new AccelerateInterpolator();
        float[] fArr = {0.0f, 0.0f};
        this.f495e = fArr;
        float[] fArr2 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.f496f = fArr2;
        float[] fArr3 = {0.0f, 0.0f};
        this.f498i = fArr3;
        float[] fArr4 = {0.0f, 0.0f};
        this.f499j = fArr4;
        float[] fArr5 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.f500k = fArr5;
        this.f493c = c0202t0;
        float f2 = Resources.getSystem().getDisplayMetrics().density;
        float f3 = ((int) ((1575.0f * f2) + 0.5f)) / 1000.0f;
        fArr5[0] = f3;
        fArr5[1] = f3;
        float f4 = ((int) ((f2 * 315.0f) + 0.5f)) / 1000.0f;
        fArr4[0] = f4;
        fArr4[1] = f4;
        this.f497g = 1;
        fArr2[0] = Float.MAX_VALUE;
        fArr2[1] = Float.MAX_VALUE;
        fArr[0] = 0.2f;
        fArr[1] = 0.2f;
        fArr3[0] = 0.001f;
        fArr3[1] = 0.001f;
        this.h = f490r;
        obj.f482a = 500;
        obj.f483b = 500;
        this.f506q = c0202t0;
    }

    public static float b(float f2, float f3, float f4) {
        if (f2 > f4) {
            return f4;
        }
        if (f2 < f3) {
            return f3;
        }
        return f2;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x003b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float a(int r4, float r5, float r6, float r7) {
        /*
            r3 = this;
            float[] r0 = r3.f495e
            r0 = r0[r4]
            float[] r1 = r3.f496f
            r1 = r1[r4]
            float r0 = r0 * r6
            r2 = 0
            float r0 = b(r0, r2, r1)
            float r1 = r3.c(r5, r0)
            float r6 = r6 - r5
            float r5 = r3.c(r6, r0)
            float r5 = r5 - r1
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            android.view.animation.AccelerateInterpolator r0 = r3.f492b
            if (r6 >= 0) goto L25
            float r5 = -r5
            float r5 = r0.getInterpolation(r5)
            float r5 = -r5
            goto L2d
        L25:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 <= 0) goto L36
            float r5 = r0.getInterpolation(r5)
        L2d:
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0 = 1065353216(0x3f800000, float:1.0)
            float r5 = b(r5, r6, r0)
            goto L37
        L36:
            r5 = r2
        L37:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 != 0) goto L3c
            return r2
        L3c:
            float[] r0 = r3.f498i
            r0 = r0[r4]
            float[] r1 = r3.f499j
            r1 = r1[r4]
            float[] r2 = r3.f500k
            r4 = r2[r4]
            float r0 = r0 * r7
            if (r6 <= 0) goto L51
            float r5 = r5 * r0
            float r4 = b(r5, r1, r4)
            return r4
        L51:
            float r5 = -r5
            float r5 = r5 * r0
            float r4 = b(r5, r1, r4)
            float r4 = -r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: K.d.a(int, float, float, float):float");
    }

    public final float c(float f2, float f3) {
        if (f3 == 0.0f) {
            return 0.0f;
        }
        int i2 = this.f497g;
        if (i2 != 0 && i2 != 1) {
            if (i2 == 2 && f2 < 0.0f) {
                return f2 / (-f3);
            }
        } else if (f2 < f3) {
            if (f2 >= 0.0f) {
                return 1.0f - (f2 / f3);
            }
            if (this.f504o && i2 == 1) {
                return 1.0f;
            }
        }
        return 0.0f;
    }

    public final void d() {
        int i2 = 0;
        if (this.f502m) {
            this.f504o = false;
            return;
        }
        a aVar = this.f491a;
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        int i3 = (int) (currentAnimationTimeMillis - aVar.f486e);
        int i4 = aVar.f483b;
        if (i3 > i4) {
            i2 = i4;
        } else if (i3 >= 0) {
            i2 = i3;
        }
        aVar.f489i = i2;
        aVar.h = aVar.a(currentAnimationTimeMillis);
        aVar.f488g = currentAnimationTimeMillis;
    }

    public final boolean e() {
        C0202t0 c0202t0;
        int count;
        a aVar = this.f491a;
        float f2 = aVar.f485d;
        int abs = (int) (f2 / Math.abs(f2));
        Math.abs(aVar.f484c);
        if (abs == 0 || (count = (c0202t0 = this.f506q).getCount()) == 0) {
            return false;
        }
        int childCount = c0202t0.getChildCount();
        int firstVisiblePosition = c0202t0.getFirstVisiblePosition();
        int i2 = firstVisiblePosition + childCount;
        if (abs > 0) {
            if (i2 >= count && c0202t0.getChildAt(childCount - 1).getBottom() <= c0202t0.getHeight()) {
                return false;
            }
        } else {
            if (abs >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && c0202t0.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0013, code lost:
    
        if (r1 != 3) goto L29;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouch(android.view.View r8, android.view.MotionEvent r9) {
        /*
            r7 = this;
            r0 = 1
            boolean r1 = r7.f505p
            r2 = 0
            if (r1 != 0) goto L7
            return r2
        L7:
            int r1 = r9.getActionMasked()
            if (r1 == 0) goto L1a
            if (r1 == r0) goto L16
            r3 = 2
            if (r1 == r3) goto L1e
            r8 = 3
            if (r1 == r8) goto L16
            goto L7b
        L16:
            r7.d()
            goto L7b
        L1a:
            r7.f503n = r0
            r7.f501l = r2
        L1e:
            float r1 = r9.getX()
            int r3 = r8.getWidth()
            float r3 = (float) r3
            android.widget.ListView r4 = r7.f493c
            int r5 = r4.getWidth()
            float r5 = (float) r5
            float r1 = r7.a(r2, r1, r3, r5)
            float r9 = r9.getY()
            int r8 = r8.getHeight()
            float r8 = (float) r8
            int r3 = r4.getHeight()
            float r3 = (float) r3
            float r8 = r7.a(r0, r9, r8, r3)
            K.a r9 = r7.f491a
            r9.f484c = r1
            r9.f485d = r8
            boolean r8 = r7.f504o
            if (r8 != 0) goto L7b
            boolean r8 = r7.e()
            if (r8 == 0) goto L7b
            E.b r8 = r7.f494d
            if (r8 != 0) goto L5f
            E.b r8 = new E.b
            r8.<init>(r0, r7)
            r7.f494d = r8
        L5f:
            r7.f504o = r0
            r7.f502m = r0
            boolean r8 = r7.f501l
            if (r8 != 0) goto L74
            int r8 = r7.h
            if (r8 <= 0) goto L74
            E.b r9 = r7.f494d
            long r5 = (long) r8
            java.util.WeakHashMap r8 = H.N.f327a
            r4.postOnAnimationDelayed(r9, r5)
            goto L79
        L74:
            E.b r8 = r7.f494d
            r8.run()
        L79:
            r7.f501l = r0
        L7b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: K.d.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}

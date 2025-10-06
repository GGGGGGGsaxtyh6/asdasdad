package T;

import android.content.Context;

/* loaded from: classes.dex */
public final /* synthetic */ class h implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f649a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Context f650b;

    public /* synthetic */ h(Context context, int i2) {
        this.f649a = i2;
        this.f650b = context;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0060, code lost:
    
        if (r2 != null) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006f  */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.concurrent.Executor, java.lang.Object] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r11 = this;
            int r0 = r11.f649a
            switch(r0) {
                case 0: goto L98;
                case 1: goto L8a;
                default: goto L5;
            }
        L5:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 1
            r2 = 33
            if (r0 < r2) goto L87
            android.content.ComponentName r3 = new android.content.ComponentName
            android.content.Context r4 = r11.f650b
            java.lang.String r5 = "androidx.appcompat.app.AppLocalesMetadataHolderService"
            r3.<init>(r4, r5)
            android.content.pm.PackageManager r5 = r4.getPackageManager()
            int r5 = r5.getComponentEnabledSetting(r3)
            if (r5 == r1) goto L87
            java.lang.String r5 = "locale"
            if (r0 < r2) goto L5e
            n.g r0 = g.AbstractC0118p.f1464g
            r0.getClass()
            n.b r2 = new n.b
            r2.<init>(r0)
        L2d:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L4c
            java.lang.Object r0 = r2.next()
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0
            java.lang.Object r0 = r0.get()
            g.p r0 = (g.AbstractC0118p) r0
            if (r0 == 0) goto L2d
            g.C r0 = (g.C) r0
            android.content.Context r0 = r0.f1338k
            if (r0 == 0) goto L2d
            java.lang.Object r0 = r0.getSystemService(r5)
            goto L4d
        L4c:
            r0 = 0
        L4d:
            if (r0 == 0) goto L63
            android.os.LocaleList r0 = g.AbstractC0115m.a(r0)
            D.e r2 = new D.e
            D.f r6 = new D.f
            r6.<init>(r0)
            r2.<init>(r6)
            goto L65
        L5e:
            D.e r2 = g.AbstractC0118p.f1460c
            if (r2 == 0) goto L63
            goto L65
        L63:
            D.e r2 = D.e.f239b
        L65:
            D.f r0 = r2.f240a
            android.os.LocaleList r0 = r0.f241a
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L80
            java.lang.String r0 = x.c.e(r4)
            java.lang.Object r2 = r4.getSystemService(r5)
            if (r2 == 0) goto L80
            android.os.LocaleList r0 = g.AbstractC0114l.a(r0)
            g.AbstractC0115m.b(r2, r0)
        L80:
            android.content.pm.PackageManager r0 = r4.getPackageManager()
            r0.setComponentEnabledSetting(r3, r1, r1)
        L87:
            g.AbstractC0118p.f1463f = r1
            return
        L8a:
            T.d r0 = new T.d
            r0.<init>()
            A.m r1 = T.f.f638a
            r2 = 0
            android.content.Context r3 = r11.f650b
            T.f.t(r3, r0, r1, r2)
            return
        L98:
            java.util.concurrent.ThreadPoolExecutor r0 = new java.util.concurrent.ThreadPoolExecutor
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.MILLISECONDS
            java.util.concurrent.LinkedBlockingQueue r10 = new java.util.concurrent.LinkedBlockingQueue
            r10.<init>()
            r6 = 1
            r7 = 0
            r5 = 0
            r4 = r0
            r4.<init>(r5, r6, r7, r9, r10)
            T.h r1 = new T.h
            android.content.Context r2 = r11.f650b
            r3 = 1
            r1.<init>(r2, r3)
            r0.execute(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: T.h.run():void");
    }
}

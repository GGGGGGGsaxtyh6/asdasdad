package T;

import A.m;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/* loaded from: classes.dex */
public abstract class k {

    /* renamed from: a, reason: collision with root package name */
    public static final p.h f655a = new Object();

    /* renamed from: b, reason: collision with root package name */
    public static final Object f656b = new Object();

    /* renamed from: c, reason: collision with root package name */
    public static m f657c = null;

    public static long a(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        if (Build.VERSION.SDK_INT >= 33) {
            return i.a(packageManager, context).lastUpdateTime;
        }
        return packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    public static m b() {
        m mVar = new m(14);
        f657c = mVar;
        p.h hVar = f655a;
        hVar.getClass();
        if (p.g.f2163f.h(hVar, null, mVar)) {
            p.g.b(hVar);
        }
        return f657c;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:33|34|35|(2:75|76)(1:37)|38|(8:45|(1:49)|(1:68)(1:56)|57|(2:64|65)|61|62|63)|(1:72)(1:(1:74))|(1:49)|(1:51)|68|57|(1:59)|64|65|61|62|63) */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00c5, code lost:
    
        r5 = 327680;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void c(android.content.Context r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: T.k.c(android.content.Context, boolean):void");
    }
}

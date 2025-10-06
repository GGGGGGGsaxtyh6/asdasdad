package A;

import android.content.res.Resources;
import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public abstract class g {

    /* renamed from: a, reason: collision with root package name */
    public static final C0.d f9a;

    /* renamed from: b, reason: collision with root package name */
    public static final n.j f10b;

    static {
        C0.f.c("TypefaceCompat static init");
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            f9a = new C0.d();
        } else if (i2 >= 28) {
            f9a = new j();
        } else if (i2 >= 26) {
            f9a = new j();
        } else {
            Method method = i.f18c;
            if (method == null) {
                Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
            }
            if (method != null) {
                f9a = new C0.d();
            } else {
                f9a = new C0.d();
            }
        }
        f10b = new n.j(16);
        Trace.endSection();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002c, code lost:
    
        if (r5.equals(r6) == false) goto L15;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v8, types: [E.p, java.lang.Object, java.lang.Runnable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Typeface a(android.content.Context r14, z.InterfaceC0259d r15, android.content.res.Resources r16, int r17, java.lang.String r18, int r19, int r20, k.U r21) {
        /*
            Method dump skipped, instructions count: 504
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: A.g.a(android.content.Context, z.d, android.content.res.Resources, int, java.lang.String, int, int, k.U):android.graphics.Typeface");
    }

    public static String b(Resources resources, int i2, String str, int i3, int i4) {
        return resources.getResourcePackageName(i2) + '-' + str + '-' + i3 + '-' + i2 + '-' + i4;
    }
}

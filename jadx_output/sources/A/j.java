package A;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import z.C0260e;
import z.C0261f;

/* loaded from: classes.dex */
public class j extends h {

    /* renamed from: f, reason: collision with root package name */
    public final Class f20f;

    /* renamed from: g, reason: collision with root package name */
    public final Constructor f21g;
    public final Method h;

    /* renamed from: i, reason: collision with root package name */
    public final Method f22i;

    /* renamed from: j, reason: collision with root package name */
    public final Method f23j;

    /* renamed from: k, reason: collision with root package name */
    public final Method f24k;

    /* renamed from: l, reason: collision with root package name */
    public final Method f25l;

    public j() {
        Method method;
        Constructor<?> constructor;
        Method method2;
        Method method3;
        Method method4;
        Method method5;
        Class<?> cls = null;
        try {
            Class<?> cls2 = Class.forName("android.graphics.FontFamily");
            constructor = cls2.getConstructor(null);
            method2 = Q(cls2);
            Class cls3 = Integer.TYPE;
            method3 = cls2.getMethod("addFontFromBuffer", ByteBuffer.class, cls3, FontVariationAxis[].class, cls3, cls3);
            method4 = cls2.getMethod("freeze", null);
            method5 = cls2.getMethod("abortCreation", null);
            method = R(cls2);
            cls = cls2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class ".concat(e2.getClass().getName()), e2);
            method = null;
            constructor = null;
            method2 = null;
            method3 = null;
            method4 = null;
            method5 = null;
        }
        this.f20f = cls;
        this.f21g = constructor;
        this.h = method2;
        this.f22i = method3;
        this.f23j = method4;
        this.f24k = method5;
        this.f25l = method;
    }

    public static Method Q(Class cls) {
        Class cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls2, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    public final void K(Object obj) {
        try {
            this.f24k.invoke(obj, null);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    public final boolean L(Context context, Object obj, String str, int i2, int i3, int i4, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.h.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public Typeface M(Object obj) {
        try {
            Object newInstance = Array.newInstance((Class<?>) this.f20f, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.f25l.invoke(null, newInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean N(Object obj) {
        try {
            return ((Boolean) this.f23j.invoke(obj, null)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public final boolean O() {
        Method method = this.h;
        if (method == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        if (method != null) {
            return true;
        }
        return false;
    }

    public final Object P() {
        try {
            return this.f21g.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    public Method R(Class cls) {
        Class<?> cls2 = Array.newInstance((Class<?>) cls, 1).getClass();
        Class cls3 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", cls2, cls3, cls3);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    @Override // A.h, C0.d
    public final Typeface k(Context context, C0260e c0260e, Resources resources, int i2) {
        if (!O()) {
            return super.k(context, c0260e, resources, i2);
        }
        Object P2 = P();
        if (P2 == null) {
            return null;
        }
        for (C0261f c0261f : c0260e.f2938a) {
            if (!L(context, P2, c0261f.f2939a, c0261f.f2943e, c0261f.f2940b, c0261f.f2941c ? 1 : 0, FontVariationAxis.fromFontVariationSettings(c0261f.f2942d))) {
                K(P2);
                return null;
            }
        }
        if (!N(P2)) {
            return null;
        }
        return M(P2);
    }

    @Override // A.h, C0.d
    public final Typeface l(Context context, E.l[] lVarArr, int i2) {
        Typeface M2;
        boolean z2;
        if (lVarArr.length < 1) {
            return null;
        }
        if (!O()) {
            E.l p2 = p(lVarArr, i2);
            try {
                ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(p2.f296a, "r", null);
                if (openFileDescriptor == null) {
                    if (openFileDescriptor != null) {
                        openFileDescriptor.close();
                    }
                    return null;
                }
                try {
                    Typeface build = new Typeface.Builder(openFileDescriptor.getFileDescriptor()).setWeight(p2.f298c).setItalic(p2.f299d).build();
                    openFileDescriptor.close();
                    return build;
                } finally {
                }
            } catch (IOException unused) {
                return null;
            }
        }
        HashMap hashMap = new HashMap();
        for (E.l lVar : lVarArr) {
            if (lVar.f300e == 0) {
                Uri uri = lVar.f296a;
                if (!hashMap.containsKey(uri)) {
                    hashMap.put(uri, C0.f.v(context, uri));
                }
            }
        }
        Map unmodifiableMap = Collections.unmodifiableMap(hashMap);
        Object P2 = P();
        if (P2 == null) {
            return null;
        }
        int length = lVarArr.length;
        int i3 = 0;
        boolean z3 = false;
        while (i3 < length) {
            E.l lVar2 = lVarArr[i3];
            ByteBuffer byteBuffer = (ByteBuffer) unmodifiableMap.get(lVar2.f296a);
            if (byteBuffer != null) {
                try {
                    z2 = ((Boolean) this.f22i.invoke(P2, byteBuffer, Integer.valueOf(lVar2.f297b), null, Integer.valueOf(lVar2.f298c), Integer.valueOf(lVar2.f299d ? 1 : 0))).booleanValue();
                } catch (IllegalAccessException | InvocationTargetException unused2) {
                    z2 = false;
                }
                if (!z2) {
                    K(P2);
                    return null;
                }
                z3 = true;
            }
            i3++;
            z3 = z3;
        }
        if (!z3) {
            K(P2);
            return null;
        }
        if (!N(P2) || (M2 = M(P2)) == null) {
            return null;
        }
        return Typeface.create(M2, i2);
    }

    @Override // C0.d
    public final Typeface o(Context context, Resources resources, int i2, String str, int i3) {
        if (!O()) {
            return super.o(context, resources, i2, str, i3);
        }
        Object P2 = P();
        if (P2 == null) {
            return null;
        }
        if (!L(context, P2, str, 0, -1, -1, null)) {
            K(P2);
            return null;
        }
        if (!N(P2)) {
            return null;
        }
        return M(P2);
    }
}

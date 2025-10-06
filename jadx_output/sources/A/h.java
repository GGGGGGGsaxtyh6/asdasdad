package A;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import z.C0260e;
import z.C0261f;

/* loaded from: classes.dex */
public class h extends C0.d {

    /* renamed from: a, reason: collision with root package name */
    public static Class f11a = null;

    /* renamed from: b, reason: collision with root package name */
    public static Constructor f12b = null;

    /* renamed from: c, reason: collision with root package name */
    public static Method f13c = null;

    /* renamed from: d, reason: collision with root package name */
    public static Method f14d = null;

    /* renamed from: e, reason: collision with root package name */
    public static boolean f15e = false;

    public static boolean I(Object obj, String str, int i2, boolean z2) {
        J();
        try {
            return ((Boolean) f13c.invoke(obj, str, Integer.valueOf(i2), Boolean.valueOf(z2))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static void J() {
        Method method;
        Class<?> cls;
        Method method2;
        if (f15e) {
            return;
        }
        f15e = true;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(null);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi21Impl", e2.getClass().getName(), e2);
            method = null;
            cls = null;
            method2 = null;
        }
        f12b = constructor;
        f11a = cls;
        f13c = method2;
        f14d = method;
    }

    @Override // C0.d
    public Typeface k(Context context, C0260e c0260e, Resources resources, int i2) {
        J();
        try {
            Object newInstance = f12b.newInstance(null);
            for (C0261f c0261f : c0260e.f2938a) {
                File s2 = C0.f.s(context);
                if (s2 == null) {
                    return null;
                }
                try {
                    if (!C0.f.l(s2, resources, c0261f.f2944f)) {
                        return null;
                    }
                    if (!I(newInstance, s2.getPath(), c0261f.f2940b, c0261f.f2941c)) {
                        return null;
                    }
                    s2.delete();
                } catch (RuntimeException unused) {
                    return null;
                } finally {
                    s2.delete();
                }
            }
            J();
            try {
                Object newInstance2 = Array.newInstance((Class<?>) f11a, 1);
                Array.set(newInstance2, 0, newInstance);
                return (Typeface) f14d.invoke(null, newInstance2);
            } catch (IllegalAccessException | InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    @Override // C0.d
    public Typeface l(Context context, E.l[] lVarArr, int i2) {
        File file;
        String readlink;
        if (lVarArr.length < 1) {
            return null;
        }
        try {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(p(lVarArr, i2).f296a, "r", null);
            if (openFileDescriptor == null) {
                if (openFileDescriptor != null) {
                    openFileDescriptor.close();
                }
                return null;
            }
            try {
                try {
                    readlink = Os.readlink("/proc/self/fd/" + openFileDescriptor.getFd());
                } catch (ErrnoException unused) {
                }
                try {
                    if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                        file = new File(readlink);
                        if (file != null && file.canRead()) {
                            Typeface createFromFile = Typeface.createFromFile(file);
                            openFileDescriptor.close();
                            return createFromFile;
                        }
                        FileInputStream fileInputStream = new FileInputStream(openFileDescriptor.getFileDescriptor());
                        Typeface n2 = n(context, fileInputStream);
                        fileInputStream.close();
                        openFileDescriptor.close();
                        return n2;
                    }
                    Typeface n22 = n(context, fileInputStream);
                    fileInputStream.close();
                    openFileDescriptor.close();
                    return n22;
                } finally {
                }
                file = null;
                if (file != null) {
                    Typeface createFromFile2 = Typeface.createFromFile(file);
                    openFileDescriptor.close();
                    return createFromFile2;
                }
                FileInputStream fileInputStream2 = new FileInputStream(openFileDescriptor.getFileDescriptor());
            } catch (Throwable th) {
                try {
                    openFileDescriptor.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused2) {
            return null;
        }
    }
}

package H;

import android.graphics.Rect;
import android.util.Log;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public final class U extends X {

    /* renamed from: c, reason: collision with root package name */
    public static Field f337c = null;

    /* renamed from: d, reason: collision with root package name */
    public static boolean f338d = false;

    /* renamed from: e, reason: collision with root package name */
    public static Constructor f339e = null;

    /* renamed from: f, reason: collision with root package name */
    public static boolean f340f = false;

    /* renamed from: a, reason: collision with root package name */
    public WindowInsets f341a;

    /* renamed from: b, reason: collision with root package name */
    public A.c f342b;

    public U() {
        this.f341a = e();
    }

    private static WindowInsets e() {
        if (!f338d) {
            try {
                f337c = WindowInsets.class.getDeclaredField("CONSUMED");
            } catch (ReflectiveOperationException e2) {
                Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
            }
            f338d = true;
        }
        Field field = f337c;
        if (field != null) {
            try {
                WindowInsets windowInsets = (WindowInsets) field.get(null);
                if (windowInsets != null) {
                    return new WindowInsets(windowInsets);
                }
            } catch (ReflectiveOperationException e3) {
                Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e3);
            }
        }
        if (!f340f) {
            try {
                f339e = WindowInsets.class.getConstructor(Rect.class);
            } catch (ReflectiveOperationException e4) {
                Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
            }
            f340f = true;
        }
        Constructor constructor = f339e;
        if (constructor != null) {
            try {
                return (WindowInsets) constructor.newInstance(new Rect());
            } catch (ReflectiveOperationException e5) {
                Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
            }
        }
        return null;
    }

    @Override // H.X
    public f0 b() {
        a();
        f0 c2 = f0.c(this.f341a, null);
        e0 e0Var = c2.f366a;
        e0Var.k(null);
        e0Var.m(this.f342b);
        return c2;
    }

    @Override // H.X
    public void c(A.c cVar) {
        this.f342b = cVar;
    }

    @Override // H.X
    public void d(A.c cVar) {
        WindowInsets windowInsets = this.f341a;
        if (windowInsets != null) {
            this.f341a = windowInsets.replaceSystemWindowInsets(cVar.f2a, cVar.f3b, cVar.f4c, cVar.f5d);
        }
    }

    public U(f0 f0Var) {
        super(f0Var);
        this.f341a = f0Var.b();
    }
}

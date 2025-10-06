package H;

import android.os.Build;
import android.view.View;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public abstract class D {

    /* renamed from: a, reason: collision with root package name */
    public int f316a;

    /* renamed from: b, reason: collision with root package name */
    public int f317b;

    /* renamed from: c, reason: collision with root package name */
    public int f318c;

    /* renamed from: d, reason: collision with root package name */
    public Object f319d;

    public D() {
        if (A.m.f26b == null) {
            A.m.f26b = new A.m(8);
        }
    }

    public int a(int i2) {
        if (i2 < this.f318c) {
            return ((ByteBuffer) this.f319d).getShort(this.f317b + i2);
        }
        return 0;
    }

    public abstract Object b(View view);

    public abstract void c(View view, Object obj);

    public void d(View view, Object obj) {
        Object tag;
        C0008b c0008b;
        if (Build.VERSION.SDK_INT >= this.f317b) {
            c(view, obj);
            return;
        }
        if (Build.VERSION.SDK_INT >= this.f317b) {
            tag = b(view);
        } else {
            tag = view.getTag(this.f316a);
            if (!((Class) this.f319d).isInstance(tag)) {
                tag = null;
            }
        }
        if (e(tag, obj)) {
            View.AccessibilityDelegate c2 = N.c(view);
            if (c2 == null) {
                c0008b = null;
            } else if (c2 instanceof C0007a) {
                c0008b = ((C0007a) c2).f352a;
            } else {
                c0008b = new C0008b(c2);
            }
            if (c0008b == null) {
                c0008b = new C0008b();
            }
            N.h(view, c0008b);
            view.setTag(this.f316a, obj);
            N.e(view, this.f318c);
        }
    }

    public abstract boolean e(Object obj, Object obj2);
}

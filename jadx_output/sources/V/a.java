package V;

import C0.f;
import android.content.Context;
import android.os.Bundle;
import android.os.Trace;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.conscrypt.R;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: d, reason: collision with root package name */
    public static volatile a f674d;

    /* renamed from: e, reason: collision with root package name */
    public static final Object f675e = new Object();

    /* renamed from: c, reason: collision with root package name */
    public final Context f678c;

    /* renamed from: b, reason: collision with root package name */
    public final HashSet f677b = new HashSet();

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f676a = new HashMap();

    public a(Context context) {
        this.f678c = context.getApplicationContext();
    }

    public static a c(Context context) {
        if (f674d == null) {
            synchronized (f675e) {
                try {
                    if (f674d == null) {
                        f674d = new a(context);
                    }
                } finally {
                }
            }
        }
        return f674d;
    }

    public final void a(Bundle bundle) {
        HashSet hashSet;
        String string = this.f678c.getString(R.string.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet2 = new HashSet();
                Iterator<String> it = bundle.keySet().iterator();
                while (true) {
                    boolean hasNext = it.hasNext();
                    hashSet = this.f677b;
                    if (!hasNext) {
                        break;
                    }
                    String next = it.next();
                    if (string.equals(bundle.getString(next, null))) {
                        Class<?> cls = Class.forName(next);
                        if (b.class.isAssignableFrom(cls)) {
                            hashSet.add(cls);
                        }
                    }
                }
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    b((Class) it2.next(), hashSet2);
                }
            } catch (ClassNotFoundException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    public final Object b(Class cls, HashSet hashSet) {
        Object obj;
        if (f.u()) {
            try {
                f.c(cls.getSimpleName());
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        if (!hashSet.contains(cls)) {
            HashMap hashMap = this.f676a;
            if (!hashMap.containsKey(cls)) {
                hashSet.add(cls);
                try {
                    b bVar = (b) cls.getDeclaredConstructor(null).newInstance(null);
                    List<Class> a2 = bVar.a();
                    if (!a2.isEmpty()) {
                        for (Class cls2 : a2) {
                            if (!hashMap.containsKey(cls2)) {
                                b(cls2, hashSet);
                            }
                        }
                    }
                    obj = bVar.b(this.f678c);
                    hashSet.remove(cls);
                    hashMap.put(cls, obj);
                } catch (Throwable th2) {
                    throw new RuntimeException(th2);
                }
            } else {
                obj = hashMap.get(cls);
            }
            Trace.endSection();
            return obj;
        }
        throw new IllegalStateException("Cannot initialize " + cls.getName() + ". Cycle detected.");
    }
}

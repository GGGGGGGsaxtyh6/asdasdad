package U;

import android.os.Bundle;
import androidx.lifecycle.C0063j;
import j0.AbstractC0150d;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import m.C0221b;
import m.C0222c;
import m.C0225f;

/* loaded from: classes.dex */
public final class e {

    /* renamed from: b, reason: collision with root package name */
    public boolean f666b;

    /* renamed from: c, reason: collision with root package name */
    public Bundle f667c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f668d;

    /* renamed from: e, reason: collision with root package name */
    public a f669e;

    /* renamed from: a, reason: collision with root package name */
    public final C0225f f665a = new C0225f();

    /* renamed from: f, reason: collision with root package name */
    public boolean f670f = true;

    public final Bundle a(String str) {
        if (this.f668d) {
            Bundle bundle = this.f667c;
            if (bundle == null) {
                return null;
            }
            Bundle bundle2 = bundle.getBundle(str);
            Bundle bundle3 = this.f667c;
            if (bundle3 != null) {
                bundle3.remove(str);
            }
            Bundle bundle4 = this.f667c;
            if (bundle4 == null || bundle4.isEmpty()) {
                this.f667c = null;
            }
            return bundle2;
        }
        throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
    }

    public final d b() {
        String str;
        d dVar;
        Iterator it = this.f665a.iterator();
        do {
            C0221b c0221b = (C0221b) it;
            if (c0221b.hasNext()) {
                Map.Entry entry = (Map.Entry) c0221b.next();
                AbstractC0150d.d(entry, "components");
                str = (String) entry.getKey();
                dVar = (d) entry.getValue();
            } else {
                return null;
            }
        } while (!AbstractC0150d.a(str, "androidx.lifecycle.internal.SavedStateHandlesProvider"));
        return dVar;
    }

    public final void c(String str, d dVar) {
        Object obj;
        AbstractC0150d.e(dVar, "provider");
        C0225f c0225f = this.f665a;
        C0222c c0222c = c0225f.f2091a;
        while (c0222c != null && !c0222c.f2084a.equals(str)) {
            c0222c = c0222c.f2086c;
        }
        if (c0222c != null) {
            obj = c0222c.f2085b;
        } else {
            C0222c c0222c2 = new C0222c(str, dVar);
            c0225f.f2094d++;
            C0222c c0222c3 = c0225f.f2092b;
            if (c0222c3 == null) {
                c0225f.f2091a = c0222c2;
                c0225f.f2092b = c0222c2;
            } else {
                c0222c3.f2086c = c0222c2;
                c0222c2.f2087d = c0222c3;
                c0225f.f2092b = c0222c2;
            }
            obj = null;
        }
        if (((d) obj) == null) {
        } else {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    public final void d() {
        if (this.f670f) {
            a aVar = this.f669e;
            if (aVar == null) {
                aVar = new a(this);
            }
            this.f669e = aVar;
            try {
                C0063j.class.getDeclaredConstructor(null);
                a aVar2 = this.f669e;
                if (aVar2 != null) {
                    ((LinkedHashSet) aVar2.f662b).add(C0063j.class.getName());
                    return;
                }
                return;
            } catch (NoSuchMethodException e2) {
                throw new IllegalArgumentException("Class " + C0063j.class.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
            }
        }
        throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
    }
}

package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.N;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class w extends N {

    /* renamed from: f, reason: collision with root package name */
    public static final S.b f1114f = new S.b(1);

    /* renamed from: b, reason: collision with root package name */
    public final HashMap f1115b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    public final HashMap f1116c = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    public final HashMap f1117d = new HashMap();

    /* renamed from: e, reason: collision with root package name */
    public boolean f1118e = false;

    public w(boolean z2) {
    }

    @Override // androidx.lifecycle.N
    public final void b() {
        if (Log.isLoggable("FragmentManager", 3)) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.f1118e = true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || w.class != obj.getClass()) {
            return false;
        }
        w wVar = (w) obj;
        if (this.f1115b.equals(wVar.f1115b) && this.f1116c.equals(wVar.f1116c) && this.f1117d.equals(wVar.f1117d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f1117d.hashCode() + ((this.f1116c.hashCode() + (this.f1115b.hashCode() * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator it = this.f1115b.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator it2 = this.f1116c.keySet().iterator();
        while (it2.hasNext()) {
            sb.append((String) it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator it3 = this.f1117d.keySet().iterator();
        while (it3.hasNext()) {
            sb.append((String) it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}

package c;

import C0.m;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.r;
import b0.C0089o;
import d.AbstractC0097a;
import j0.AbstractC0150d;
import j0.AbstractC0151e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public abstract class j {

    /* renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f1250a = new LinkedHashMap();

    /* renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f1251b = new LinkedHashMap();

    /* renamed from: c, reason: collision with root package name */
    public final LinkedHashMap f1252c = new LinkedHashMap();

    /* renamed from: d, reason: collision with root package name */
    public final ArrayList f1253d = new ArrayList();

    /* renamed from: e, reason: collision with root package name */
    public final transient LinkedHashMap f1254e = new LinkedHashMap();

    /* renamed from: f, reason: collision with root package name */
    public final LinkedHashMap f1255f = new LinkedHashMap();

    /* renamed from: g, reason: collision with root package name */
    public final Bundle f1256g = new Bundle();

    public final boolean a(int i2, int i3, Intent intent) {
        b bVar;
        String str = (String) this.f1250a.get(Integer.valueOf(i2));
        if (str == null) {
            return false;
        }
        e eVar = (e) this.f1254e.get(str);
        if (eVar != null) {
            bVar = eVar.f1243a;
        } else {
            bVar = null;
        }
        if (bVar != null) {
            ArrayList arrayList = this.f1253d;
            if (arrayList.contains(str)) {
                eVar.f1243a.a(eVar.f1244b.a(intent, i3));
                arrayList.remove(str);
                return true;
            }
        }
        this.f1255f.remove(str);
        this.f1256g.putParcelable(str, new C0095a(intent, i3));
        return true;
    }

    public final i b(String str, AbstractC0097a abstractC0097a, b bVar) {
        AbstractC0150d.e(str, "key");
        c(str);
        this.f1254e.put(str, new e(abstractC0097a, bVar));
        LinkedHashMap linkedHashMap = this.f1255f;
        if (linkedHashMap.containsKey(str)) {
            Object obj = linkedHashMap.get(str);
            linkedHashMap.remove(str);
            bVar.a(obj);
        }
        Bundle bundle = this.f1256g;
        C0095a c0095a = (C0095a) m.j(str, bundle);
        if (c0095a != null) {
            bundle.remove(str);
            bVar.a(abstractC0097a.a(c0095a.f1238b, c0095a.f1237a));
        }
        return new i(this, str);
    }

    public final void c(String str) {
        LinkedHashMap linkedHashMap = this.f1251b;
        if (((Integer) linkedHashMap.get(str)) != null) {
            return;
        }
        for (Number number : new p0.a(new C0089o(1, new AbstractC0151e(1)))) {
            int intValue = number.intValue();
            LinkedHashMap linkedHashMap2 = this.f1250a;
            if (!linkedHashMap2.containsKey(Integer.valueOf(intValue))) {
                int intValue2 = number.intValue();
                linkedHashMap2.put(Integer.valueOf(intValue2), str);
                linkedHashMap.put(str, Integer.valueOf(intValue2));
                return;
            }
        }
        throw new NoSuchElementException("Sequence contains no element matching the predicate.");
    }

    public final void d(String str) {
        Integer num;
        AbstractC0150d.e(str, "key");
        if (!this.f1253d.contains(str) && (num = (Integer) this.f1251b.remove(str)) != null) {
            this.f1250a.remove(num);
        }
        this.f1254e.remove(str);
        LinkedHashMap linkedHashMap = this.f1255f;
        if (linkedHashMap.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + linkedHashMap.get(str));
            linkedHashMap.remove(str);
        }
        Bundle bundle = this.f1256g;
        if (bundle.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + ((C0095a) m.j(str, bundle)));
            bundle.remove(str);
        }
        LinkedHashMap linkedHashMap2 = this.f1252c;
        f fVar = (f) linkedHashMap2.get(str);
        if (fVar != null) {
            ArrayList arrayList = fVar.f1246b;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                fVar.f1245a.b((r) it.next());
            }
            arrayList.clear();
            linkedHashMap2.remove(str);
        }
    }
}

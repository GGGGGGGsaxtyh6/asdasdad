package androidx.fragment.app;

import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.EnumC0066m;
import g.AbstractActivityC0112j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public final /* synthetic */ class e implements U.d {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1047a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1048b;

    public /* synthetic */ e(int i2, Object obj) {
        this.f1047a = i2;
        this.f1048b = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v18, types: [android.os.Parcelable, java.lang.Object, androidx.fragment.app.v] */
    @Override // U.d
    public final Bundle a() {
        ArrayList arrayList;
        C0053b[] c0053bArr;
        int size;
        switch (this.f1047a) {
            case 0:
                AbstractActivityC0112j abstractActivityC0112j = (AbstractActivityC0112j) this.f1048b;
                abstractActivityC0112j.markFragmentsCreated();
                abstractActivityC0112j.mFragmentLifecycleRegistry.e(EnumC0066m.ON_STOP);
                return new Bundle();
            default:
                u uVar = (u) this.f1048b;
                uVar.getClass();
                Bundle bundle = new Bundle();
                Iterator it = uVar.b().iterator();
                while (it.hasNext()) {
                    ((B) it.next()).getClass();
                }
                Iterator it2 = uVar.b().iterator();
                if (!it2.hasNext()) {
                    uVar.e(true);
                    uVar.f1106z = true;
                    uVar.f1081F.getClass();
                    y yVar = uVar.f1085c;
                    yVar.getClass();
                    HashMap hashMap = yVar.f1132b;
                    ArrayList arrayList2 = new ArrayList(hashMap.size());
                    Iterator it3 = hashMap.values().iterator();
                    while (it3.hasNext()) {
                        if (it3.next() != null) {
                            throw new ClassCastException();
                        }
                    }
                    y yVar2 = uVar.f1085c;
                    yVar2.getClass();
                    ArrayList arrayList3 = new ArrayList(yVar2.f1133c.values());
                    if (arrayList3.isEmpty()) {
                        if (Log.isLoggable("FragmentManager", 2)) {
                            Log.v("FragmentManager", "saveAllState: no fragments!");
                        }
                    } else {
                        y yVar3 = uVar.f1085c;
                        synchronized (yVar3.f1131a) {
                            try {
                                if (yVar3.f1131a.isEmpty()) {
                                    arrayList = null;
                                } else {
                                    arrayList = new ArrayList(yVar3.f1131a.size());
                                    Iterator it4 = yVar3.f1131a.iterator();
                                    if (it4.hasNext()) {
                                        if (it4.next() == null) {
                                            throw null;
                                        }
                                        throw new ClassCastException();
                                    }
                                }
                            } finally {
                            }
                        }
                        ArrayList arrayList4 = uVar.f1086d;
                        if (arrayList4 != null && (size = arrayList4.size()) > 0) {
                            c0053bArr = new C0053b[size];
                            for (int i2 = 0; i2 < size; i2++) {
                                c0053bArr[i2] = new C0053b((C0052a) uVar.f1086d.get(i2));
                                if (Log.isLoggable("FragmentManager", 2)) {
                                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + uVar.f1086d.get(i2));
                                }
                            }
                        } else {
                            c0053bArr = null;
                        }
                        ?? obj = new Object();
                        obj.f1111e = null;
                        ArrayList arrayList5 = new ArrayList();
                        obj.f1112f = arrayList5;
                        ArrayList arrayList6 = new ArrayList();
                        obj.f1113g = arrayList6;
                        obj.f1107a = arrayList2;
                        obj.f1108b = arrayList;
                        obj.f1109c = c0053bArr;
                        obj.f1110d = uVar.h.get();
                        arrayList5.addAll(uVar.f1090i.keySet());
                        arrayList6.addAll(uVar.f1090i.values());
                        obj.h = new ArrayList(uVar.y);
                        bundle.putParcelable("state", obj);
                        for (String str : uVar.f1091j.keySet()) {
                            bundle.putBundle("result_" + str, (Bundle) uVar.f1091j.get(str));
                        }
                        Iterator it5 = arrayList3.iterator();
                        while (it5.hasNext()) {
                            x xVar = (x) it5.next();
                            Bundle bundle2 = new Bundle();
                            bundle2.putParcelable("state", xVar);
                            bundle.putBundle("fragment_" + xVar.f1120b, bundle2);
                        }
                    }
                    return bundle;
                }
                ((B) it2.next()).a();
                throw null;
        }
    }
}

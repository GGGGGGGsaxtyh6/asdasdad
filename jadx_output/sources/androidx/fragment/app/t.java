package androidx.fragment.app;

import android.os.Looper;
import androidx.lifecycle.EnumC0067n;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.conscrypt.ct.CTConstants;

/* loaded from: classes.dex */
public abstract class t {

    /* renamed from: A, reason: collision with root package name */
    public boolean f1076A;

    /* renamed from: B, reason: collision with root package name */
    public boolean f1077B;

    /* renamed from: C, reason: collision with root package name */
    public ArrayList f1078C;

    /* renamed from: D, reason: collision with root package name */
    public ArrayList f1079D;

    /* renamed from: E, reason: collision with root package name */
    public ArrayList f1080E;

    /* renamed from: F, reason: collision with root package name */
    public w f1081F;

    /* renamed from: G, reason: collision with root package name */
    public final E.b f1082G;

    /* renamed from: b, reason: collision with root package name */
    public boolean f1084b;

    /* renamed from: d, reason: collision with root package name */
    public ArrayList f1086d;

    /* renamed from: f, reason: collision with root package name */
    public a.v f1088f;

    /* renamed from: l, reason: collision with root package name */
    public final CopyOnWriteArrayList f1093l;

    /* renamed from: m, reason: collision with root package name */
    public final m f1094m;

    /* renamed from: n, reason: collision with root package name */
    public final m f1095n;

    /* renamed from: o, reason: collision with root package name */
    public final m f1096o;

    /* renamed from: p, reason: collision with root package name */
    public final m f1097p;

    /* renamed from: q, reason: collision with root package name */
    public final p f1098q;

    /* renamed from: r, reason: collision with root package name */
    public int f1099r;

    /* renamed from: s, reason: collision with root package name */
    public h f1100s;

    /* renamed from: t, reason: collision with root package name */
    public h f1101t;

    /* renamed from: u, reason: collision with root package name */
    public final q f1102u;

    /* renamed from: v, reason: collision with root package name */
    public c.i f1103v;

    /* renamed from: w, reason: collision with root package name */
    public c.i f1104w;

    /* renamed from: x, reason: collision with root package name */
    public c.i f1105x;
    public ArrayDeque y;

    /* renamed from: z, reason: collision with root package name */
    public boolean f1106z;

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f1083a = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    public final y f1085c = new y();

    /* renamed from: e, reason: collision with root package name */
    public final l f1087e = new l(this);

    /* renamed from: g, reason: collision with root package name */
    public final o f1089g = new o(this);
    public final AtomicInteger h = new AtomicInteger();

    /* renamed from: i, reason: collision with root package name */
    public final Map f1090i = Collections.synchronizedMap(new HashMap());

    /* renamed from: j, reason: collision with root package name */
    public final Map f1091j = Collections.synchronizedMap(new HashMap());

    /* renamed from: k, reason: collision with root package name */
    public final Map f1092k = Collections.synchronizedMap(new HashMap());

    /* JADX WARN: Type inference failed for: r0v13, types: [androidx.fragment.app.m] */
    /* JADX WARN: Type inference failed for: r0v14, types: [androidx.fragment.app.m] */
    /* JADX WARN: Type inference failed for: r0v15, types: [androidx.fragment.app.m] */
    /* JADX WARN: Type inference failed for: r0v16, types: [androidx.fragment.app.m] */
    public t() {
        new CopyOnWriteArrayList();
        this.f1093l = new CopyOnWriteArrayList();
        final int i2 = 0;
        this.f1094m = new G.a(this) { // from class: androidx.fragment.app.m

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ t f1063b;

            {
                this.f1063b = this;
            }

            @Override // G.a
            public final void a(Object obj) {
                switch (i2) {
                    case 0:
                        Iterator it = this.f1063b.f1085c.c().iterator();
                        while (it.hasNext()) {
                            if (it.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    case 1:
                        t tVar = this.f1063b;
                        tVar.getClass();
                        if (((Integer) obj).intValue() == 80) {
                            Iterator it2 = tVar.f1085c.c().iterator();
                            while (it2.hasNext()) {
                                if (it2.next() != null) {
                                    throw new ClassCastException();
                                }
                            }
                            return;
                        }
                        return;
                    case 2:
                        t tVar2 = this.f1063b;
                        tVar2.getClass();
                        boolean z2 = ((x.g) obj).f2832a;
                        Iterator it3 = tVar2.f1085c.c().iterator();
                        while (it3.hasNext()) {
                            if (it3.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    default:
                        t tVar3 = this.f1063b;
                        tVar3.getClass();
                        boolean z3 = ((x.h) obj).f2833a;
                        Iterator it4 = tVar3.f1085c.c().iterator();
                        while (it4.hasNext()) {
                            if (it4.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                }
            }
        };
        final int i3 = 1;
        this.f1095n = new G.a(this) { // from class: androidx.fragment.app.m

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ t f1063b;

            {
                this.f1063b = this;
            }

            @Override // G.a
            public final void a(Object obj) {
                switch (i3) {
                    case 0:
                        Iterator it = this.f1063b.f1085c.c().iterator();
                        while (it.hasNext()) {
                            if (it.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    case 1:
                        t tVar = this.f1063b;
                        tVar.getClass();
                        if (((Integer) obj).intValue() == 80) {
                            Iterator it2 = tVar.f1085c.c().iterator();
                            while (it2.hasNext()) {
                                if (it2.next() != null) {
                                    throw new ClassCastException();
                                }
                            }
                            return;
                        }
                        return;
                    case 2:
                        t tVar2 = this.f1063b;
                        tVar2.getClass();
                        boolean z2 = ((x.g) obj).f2832a;
                        Iterator it3 = tVar2.f1085c.c().iterator();
                        while (it3.hasNext()) {
                            if (it3.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    default:
                        t tVar3 = this.f1063b;
                        tVar3.getClass();
                        boolean z3 = ((x.h) obj).f2833a;
                        Iterator it4 = tVar3.f1085c.c().iterator();
                        while (it4.hasNext()) {
                            if (it4.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                }
            }
        };
        final int i4 = 2;
        this.f1096o = new G.a(this) { // from class: androidx.fragment.app.m

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ t f1063b;

            {
                this.f1063b = this;
            }

            @Override // G.a
            public final void a(Object obj) {
                switch (i4) {
                    case 0:
                        Iterator it = this.f1063b.f1085c.c().iterator();
                        while (it.hasNext()) {
                            if (it.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    case 1:
                        t tVar = this.f1063b;
                        tVar.getClass();
                        if (((Integer) obj).intValue() == 80) {
                            Iterator it2 = tVar.f1085c.c().iterator();
                            while (it2.hasNext()) {
                                if (it2.next() != null) {
                                    throw new ClassCastException();
                                }
                            }
                            return;
                        }
                        return;
                    case 2:
                        t tVar2 = this.f1063b;
                        tVar2.getClass();
                        boolean z2 = ((x.g) obj).f2832a;
                        Iterator it3 = tVar2.f1085c.c().iterator();
                        while (it3.hasNext()) {
                            if (it3.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    default:
                        t tVar3 = this.f1063b;
                        tVar3.getClass();
                        boolean z3 = ((x.h) obj).f2833a;
                        Iterator it4 = tVar3.f1085c.c().iterator();
                        while (it4.hasNext()) {
                            if (it4.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                }
            }
        };
        final int i5 = 3;
        this.f1097p = new G.a(this) { // from class: androidx.fragment.app.m

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ t f1063b;

            {
                this.f1063b = this;
            }

            @Override // G.a
            public final void a(Object obj) {
                switch (i5) {
                    case 0:
                        Iterator it = this.f1063b.f1085c.c().iterator();
                        while (it.hasNext()) {
                            if (it.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    case 1:
                        t tVar = this.f1063b;
                        tVar.getClass();
                        if (((Integer) obj).intValue() == 80) {
                            Iterator it2 = tVar.f1085c.c().iterator();
                            while (it2.hasNext()) {
                                if (it2.next() != null) {
                                    throw new ClassCastException();
                                }
                            }
                            return;
                        }
                        return;
                    case 2:
                        t tVar2 = this.f1063b;
                        tVar2.getClass();
                        boolean z2 = ((x.g) obj).f2832a;
                        Iterator it3 = tVar2.f1085c.c().iterator();
                        while (it3.hasNext()) {
                            if (it3.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    default:
                        t tVar3 = this.f1063b;
                        tVar3.getClass();
                        boolean z3 = ((x.h) obj).f2833a;
                        Iterator it4 = tVar3.f1085c.c().iterator();
                        while (it4.hasNext()) {
                            if (it4.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                }
            }
        };
        this.f1098q = new p(this);
        this.f1099r = -1;
        this.f1102u = new q(this);
        this.y = new ArrayDeque();
        this.f1082G = new E.b(6, this);
    }

    public final void a() {
        this.f1084b = false;
        this.f1079D.clear();
        this.f1078C.clear();
    }

    public final HashSet b() {
        HashSet hashSet = new HashSet();
        Iterator it = this.f1085c.b().iterator();
        if (!it.hasNext()) {
            return hashSet;
        }
        A.e.f(it.next());
        throw null;
    }

    public final void c(int i2) {
        try {
            this.f1084b = true;
            Iterator it = this.f1085c.f1132b.values().iterator();
            while (it.hasNext()) {
                if (it.next() != null) {
                    throw new ClassCastException();
                }
            }
            h(i2, false);
            Iterator it2 = b().iterator();
            if (!it2.hasNext()) {
                this.f1084b = false;
                e(true);
            } else {
                ((B) it2.next()).a();
                throw null;
            }
        } catch (Throwable th) {
            this.f1084b = false;
            throw th;
        }
    }

    public final void d(boolean z2) {
        if (!this.f1084b) {
            if (this.f1100s == null) {
                if (this.f1077B) {
                    throw new IllegalStateException("FragmentManager has been destroyed");
                }
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            if (Looper.myLooper() == this.f1100s.f1053b.getLooper()) {
                if (!z2 && (this.f1106z || this.f1076A)) {
                    throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
                }
                if (this.f1078C == null) {
                    this.f1078C = new ArrayList();
                    this.f1079D = new ArrayList();
                    return;
                }
                return;
            }
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        throw new IllegalStateException("FragmentManager is already executing transactions");
    }

    public final boolean e(boolean z2) {
        boolean z3;
        d(z2);
        boolean z4 = false;
        while (true) {
            ArrayList arrayList = this.f1078C;
            ArrayList arrayList2 = this.f1079D;
            synchronized (this.f1083a) {
                if (this.f1083a.isEmpty()) {
                    z3 = false;
                } else {
                    try {
                        int size = this.f1083a.size();
                        int i2 = 0;
                        z3 = false;
                        while (i2 < size) {
                            ((C0052a) this.f1083a.get(i2)).c(arrayList, arrayList2);
                            i2++;
                            z3 = true;
                        }
                    } finally {
                    }
                }
            }
            if (z3) {
                this.f1084b = true;
                try {
                    i(this.f1078C, this.f1079D);
                    a();
                    z4 = true;
                } catch (Throwable th) {
                    a();
                    throw th;
                }
            } else {
                j();
                this.f1085c.f1132b.values().removeAll(Collections.singleton(null));
                return z4;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r6v26, types: [java.lang.Object, androidx.fragment.app.z] */
    /* JADX WARN: Type inference failed for: r9v18, types: [java.lang.Object, androidx.fragment.app.z] */
    public final void f(ArrayList arrayList, ArrayList arrayList2, int i2, int i3) {
        boolean z2;
        int i4;
        int i5 = i2;
        boolean z3 = ((C0052a) arrayList.get(i5)).f1029o;
        ArrayList arrayList3 = this.f1080E;
        if (arrayList3 == null) {
            this.f1080E = new ArrayList();
        } else {
            arrayList3.clear();
        }
        this.f1080E.addAll(this.f1085c.c());
        int i6 = i5;
        boolean z4 = false;
        while (true) {
            int i7 = 1;
            if (i6 < i3) {
                C0052a c0052a = (C0052a) arrayList.get(i6);
                int i8 = 3;
                if (!((Boolean) arrayList2.get(i6)).booleanValue()) {
                    ArrayList arrayList4 = this.f1080E;
                    int i9 = 0;
                    while (true) {
                        ArrayList arrayList5 = c0052a.f1016a;
                        if (i9 < arrayList5.size()) {
                            z zVar = (z) arrayList5.get(i9);
                            int i10 = zVar.f1135a;
                            if (i10 != i7) {
                                if (i10 != 2) {
                                    if (i10 != i8 && i10 != 6) {
                                        if (i10 != 7) {
                                            if (i10 == 8) {
                                                ?? obj = new Object();
                                                obj.f1135a = 9;
                                                obj.f1136b = true;
                                                EnumC0067n enumC0067n = EnumC0067n.f1200e;
                                                obj.f1141g = enumC0067n;
                                                obj.h = enumC0067n;
                                                arrayList5.add(i9, obj);
                                                zVar.f1136b = true;
                                                i9++;
                                                i4 = 1;
                                                i9 += i4;
                                                i7 = i4;
                                                i8 = 3;
                                            }
                                        }
                                    } else {
                                        arrayList4.remove((Object) null);
                                        ?? obj2 = new Object();
                                        obj2.f1135a = 9;
                                        obj2.f1136b = false;
                                        EnumC0067n enumC0067n2 = EnumC0067n.f1200e;
                                        obj2.f1141g = enumC0067n2;
                                        obj2.h = enumC0067n2;
                                        arrayList5.add(i9, obj2);
                                        i9++;
                                    }
                                    i4 = 1;
                                    i9 += i4;
                                    i7 = i4;
                                    i8 = 3;
                                } else {
                                    throw null;
                                }
                            }
                            arrayList4.add(null);
                            i4 = 1;
                            i9 += i4;
                            i7 = i4;
                            i8 = 3;
                        } else {
                            z2 = false;
                        }
                    }
                } else {
                    int i11 = 1;
                    z2 = false;
                    ArrayList arrayList6 = this.f1080E;
                    ArrayList arrayList7 = c0052a.f1016a;
                    int size = arrayList7.size() - 1;
                    while (size >= 0) {
                        z zVar2 = (z) arrayList7.get(size);
                        int i12 = zVar2.f1135a;
                        if (i12 != i11) {
                            if (i12 != 3) {
                                switch (i12) {
                                    case 10:
                                        zVar2.h = zVar2.f1141g;
                                        break;
                                }
                                size--;
                                i11 = 1;
                            }
                            arrayList6.add(null);
                            size--;
                            i11 = 1;
                        }
                        arrayList6.remove((Object) null);
                        size--;
                        i11 = 1;
                    }
                }
                if (!z4 && !c0052a.f1022g) {
                    z4 = z2;
                } else {
                    z4 = true;
                }
                i6++;
            } else {
                this.f1080E.clear();
                if (!z3 && this.f1099r >= 1) {
                    for (int i13 = i5; i13 < i3; i13++) {
                        Iterator it = ((C0052a) arrayList.get(i13)).f1016a.iterator();
                        while (it.hasNext()) {
                            ((z) it.next()).getClass();
                        }
                    }
                }
                for (int i14 = i5; i14 < i3; i14++) {
                    C0052a c0052a2 = (C0052a) arrayList.get(i14);
                    if (((Boolean) arrayList2.get(i14)).booleanValue()) {
                        c0052a2.a(-1);
                        ArrayList arrayList8 = c0052a2.f1016a;
                        for (int size2 = arrayList8.size() - 1; size2 >= 0; size2--) {
                            z zVar3 = (z) arrayList8.get(size2);
                            zVar3.getClass();
                            int i15 = zVar3.f1135a;
                            u uVar = c0052a2.f1030p;
                            switch (i15) {
                                case 1:
                                    throw null;
                                case 2:
                                default:
                                    throw new IllegalArgumentException("Unknown cmd: " + zVar3.f1135a);
                                case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                                    throw null;
                                case 4:
                                    throw null;
                                case 5:
                                    throw null;
                                case 6:
                                    throw null;
                                case 7:
                                    throw null;
                                case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                                    uVar.getClass();
                                    break;
                                case 9:
                                    uVar.getClass();
                                    break;
                                case 10:
                                    uVar.getClass();
                                    throw null;
                            }
                        }
                    } else {
                        c0052a2.a(1);
                        ArrayList arrayList9 = c0052a2.f1016a;
                        int size3 = arrayList9.size();
                        for (int i16 = 0; i16 < size3; i16++) {
                            z zVar4 = (z) arrayList9.get(i16);
                            zVar4.getClass();
                            int i17 = zVar4.f1135a;
                            u uVar2 = c0052a2.f1030p;
                            switch (i17) {
                                case 1:
                                    throw null;
                                case 2:
                                default:
                                    throw new IllegalArgumentException("Unknown cmd: " + zVar4.f1135a);
                                case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                                    throw null;
                                case 4:
                                    throw null;
                                case 5:
                                    throw null;
                                case 6:
                                    throw null;
                                case 7:
                                    throw null;
                                case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                                    uVar2.getClass();
                                    break;
                                case 9:
                                    uVar2.getClass();
                                    break;
                                case 10:
                                    uVar2.getClass();
                                    throw null;
                            }
                        }
                    }
                }
                boolean booleanValue = ((Boolean) arrayList2.get(i3 - 1)).booleanValue();
                for (int i18 = i5; i18 < i3; i18++) {
                    C0052a c0052a3 = (C0052a) arrayList.get(i18);
                    if (booleanValue) {
                        for (int size4 = c0052a3.f1016a.size() - 1; size4 >= 0; size4--) {
                            ((z) c0052a3.f1016a.get(size4)).getClass();
                        }
                    } else {
                        Iterator it2 = c0052a3.f1016a.iterator();
                        while (it2.hasNext()) {
                            ((z) it2.next()).getClass();
                        }
                    }
                }
                h(this.f1099r, true);
                HashSet hashSet = new HashSet();
                for (int i19 = i5; i19 < i3; i19++) {
                    Iterator it3 = ((C0052a) arrayList.get(i19)).f1016a.iterator();
                    while (it3.hasNext()) {
                        ((z) it3.next()).getClass();
                    }
                }
                Iterator it4 = hashSet.iterator();
                if (!it4.hasNext()) {
                    while (i5 < i3) {
                        C0052a c0052a4 = (C0052a) arrayList.get(i5);
                        if (((Boolean) arrayList2.get(i5)).booleanValue() && c0052a4.f1031q >= 0) {
                            c0052a4.f1031q = -1;
                        }
                        c0052a4.getClass();
                        i5++;
                    }
                    return;
                }
                ((B) it4.next()).getClass();
                throw null;
            }
        }
    }

    public final void g() {
        y yVar = this.f1085c;
        ArrayList arrayList = yVar.f1131a;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) != null) {
                throw new ClassCastException();
            }
        }
        Iterator it = yVar.f1132b.values().iterator();
        while (it.hasNext()) {
            A.e.f(it.next());
        }
    }

    public final void h(int i2, boolean z2) {
        if (this.f1100s == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (!z2 && i2 == this.f1099r) {
            return;
        }
        this.f1099r = i2;
        y yVar = this.f1085c;
        Iterator it = yVar.f1131a.iterator();
        if (!it.hasNext()) {
            Iterator it2 = yVar.f1132b.values().iterator();
            while (it2.hasNext()) {
                if (it2.next() != null) {
                    throw new ClassCastException();
                }
            }
            Iterator it3 = yVar.b().iterator();
            if (!it3.hasNext()) {
                return;
            }
            it3.next().getClass();
            throw new ClassCastException();
        }
        it.next().getClass();
        throw new ClassCastException();
    }

    public final void i(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() == arrayList2.size()) {
            int size = arrayList.size();
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                if (!((C0052a) arrayList.get(i2)).f1029o) {
                    if (i3 != i2) {
                        f(arrayList, arrayList2, i3, i2);
                    }
                    i3 = i2 + 1;
                    if (((Boolean) arrayList2.get(i2)).booleanValue()) {
                        while (i3 < size && ((Boolean) arrayList2.get(i3)).booleanValue() && !((C0052a) arrayList.get(i3)).f1029o) {
                            i3++;
                        }
                    }
                    f(arrayList, arrayList2, i2, i3);
                    i2 = i3 - 1;
                }
                i2++;
            }
            if (i3 != size) {
                f(arrayList, arrayList2, i3, size);
                return;
            }
            return;
        }
        throw new IllegalStateException("Internal error with the back stack records");
    }

    public final void j() {
        int i2;
        synchronized (this.f1083a) {
            try {
                boolean z2 = true;
                if (!this.f1083a.isEmpty()) {
                    o oVar = this.f1089g;
                    oVar.f1066a = true;
                    a.u uVar = oVar.f1068c;
                    if (uVar != null) {
                        uVar.a();
                    }
                    return;
                }
                o oVar2 = this.f1089g;
                ArrayList arrayList = this.f1086d;
                if (arrayList != null) {
                    i2 = arrayList.size();
                } else {
                    i2 = 0;
                }
                if (i2 <= 0) {
                    z2 = false;
                }
                oVar2.f1066a = z2;
                a.u uVar2 = oVar2.f1068c;
                if (uVar2 != null) {
                    uVar2.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        h hVar = this.f1100s;
        if (hVar != null) {
            sb.append(hVar.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.f1100s)));
            sb.append("}");
        } else {
            sb.append("null");
        }
        sb.append("}}");
        return sb.toString();
    }
}

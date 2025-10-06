package j0;

import a0.C0049c;
import b0.AbstractC0084j;
import b0.AbstractC0094t;
import i0.InterfaceC0131a;
import i0.InterfaceC0132b;
import i0.k;
import i0.l;
import i0.m;
import i0.n;
import i0.o;
import i0.p;
import i0.q;
import i0.r;
import i0.s;
import i0.t;
import i0.u;
import i0.v;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* renamed from: j0.b, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0148b implements o0.a, InterfaceC0147a {

    /* renamed from: b, reason: collision with root package name */
    public static final Map f1733b;

    /* renamed from: c, reason: collision with root package name */
    public static final HashMap f1734c;

    /* renamed from: a, reason: collision with root package name */
    public final Class f1735a;

    static {
        List asList = Arrays.asList(InterfaceC0131a.class, l.class, q0.l.class, p.class, q.class, r.class, s.class, t.class, u.class, v.class, InterfaceC0132b.class, i0.c.class, i0.d.class, i0.e.class, i0.f.class, i0.g.class, i0.h.class, i0.i.class, i0.j.class, k.class, m.class, n.class, o.class);
        AbstractC0150d.d(asList, "asList(...)");
        ArrayList arrayList = new ArrayList(AbstractC0084j.I(asList));
        int i2 = 0;
        for (Object obj : asList) {
            int i3 = i2 + 1;
            if (i2 >= 0) {
                arrayList.add(new C0049c((Class) obj, Integer.valueOf(i2)));
                i2 = i3;
            } else {
                throw new ArithmeticException("Index overflow has happened.");
            }
        }
        f1733b = AbstractC0094t.E(arrayList);
        HashMap hashMap = new HashMap();
        hashMap.put("boolean", "kotlin.Boolean");
        hashMap.put("char", "kotlin.Char");
        hashMap.put("byte", "kotlin.Byte");
        hashMap.put("short", "kotlin.Short");
        hashMap.put("int", "kotlin.Int");
        hashMap.put("float", "kotlin.Float");
        hashMap.put("long", "kotlin.Long");
        hashMap.put("double", "kotlin.Double");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("java.lang.Boolean", "kotlin.Boolean");
        hashMap2.put("java.lang.Character", "kotlin.Char");
        hashMap2.put("java.lang.Byte", "kotlin.Byte");
        hashMap2.put("java.lang.Short", "kotlin.Short");
        hashMap2.put("java.lang.Integer", "kotlin.Int");
        hashMap2.put("java.lang.Float", "kotlin.Float");
        hashMap2.put("java.lang.Long", "kotlin.Long");
        hashMap2.put("java.lang.Double", "kotlin.Double");
        HashMap hashMap3 = new HashMap();
        hashMap3.put("java.lang.Object", "kotlin.Any");
        hashMap3.put("java.lang.String", "kotlin.String");
        hashMap3.put("java.lang.CharSequence", "kotlin.CharSequence");
        hashMap3.put("java.lang.Throwable", "kotlin.Throwable");
        hashMap3.put("java.lang.Cloneable", "kotlin.Cloneable");
        hashMap3.put("java.lang.Number", "kotlin.Number");
        hashMap3.put("java.lang.Comparable", "kotlin.Comparable");
        hashMap3.put("java.lang.Enum", "kotlin.Enum");
        hashMap3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        hashMap3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        hashMap3.put("java.util.Iterator", "kotlin.collections.Iterator");
        hashMap3.put("java.util.Collection", "kotlin.collections.Collection");
        hashMap3.put("java.util.List", "kotlin.collections.List");
        hashMap3.put("java.util.Set", "kotlin.collections.Set");
        hashMap3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        hashMap3.put("java.util.Map", "kotlin.collections.Map");
        hashMap3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        hashMap3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        hashMap3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        hashMap3.putAll(hashMap);
        hashMap3.putAll(hashMap2);
        Collection<String> values = hashMap.values();
        AbstractC0150d.d(values, "<get-values>(...)");
        for (String str : values) {
            StringBuilder sb = new StringBuilder("kotlin.jvm.internal.");
            AbstractC0150d.b(str);
            sb.append(q0.d.N(str));
            sb.append("CompanionObject");
            hashMap3.put(sb.toString(), str.concat(".Companion"));
        }
        for (Map.Entry entry : f1733b.entrySet()) {
            Class cls = (Class) entry.getKey();
            int intValue = ((Number) entry.getValue()).intValue();
            hashMap3.put(cls.getName(), "kotlin.Function" + intValue);
        }
        f1734c = hashMap3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(AbstractC0094t.D(hashMap3.size()));
        for (Map.Entry entry2 : hashMap3.entrySet()) {
            linkedHashMap.put(entry2.getKey(), q0.d.N((String) entry2.getValue()));
        }
    }

    public C0148b(Class cls) {
        this.f1735a = cls;
    }

    @Override // j0.InterfaceC0147a
    public final Class a() {
        return this.f1735a;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof C0148b) && C0.f.r(this).equals(C0.f.r((o0.a) obj))) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return C0.f.r(this).hashCode();
    }

    public final String toString() {
        return this.f1735a.toString() + " (Kotlin reflection is not available)";
    }
}

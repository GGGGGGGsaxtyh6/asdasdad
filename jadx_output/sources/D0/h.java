package D0;

import M.p;
import M.v;
import M.y;
import M.z;
import Z.t;
import Z.u;
import a0.InterfaceC0047a;
import android.content.ClipDescription;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.Selection;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import androidx.lifecycle.AbstractC0068o;
import androidx.lifecycle.H;
import androidx.lifecycle.L;
import androidx.lifecycle.N;
import androidx.lifecycle.P;
import androidx.lifecycle.S;
import i0.InterfaceC0131a;
import j0.AbstractC0150d;
import j0.C0148b;
import j0.InterfaceC0149c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import k.C0203u;
import k.U;
import org.xmlpull.v1.XmlPullParserException;
import s.C0237d;
import t.C0239b;
import u0.q;
import u0.s;
import z.AbstractC0257b;
import z.AbstractC0266k;
import z.InterfaceC0259d;

/* loaded from: classes.dex */
public final class h implements J.h, u0.d, t {

    /* renamed from: e, reason: collision with root package name */
    public static h f256e;

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f257a;

    /* renamed from: b, reason: collision with root package name */
    public Object f258b;

    /* renamed from: c, reason: collision with root package name */
    public Object f259c;

    /* renamed from: d, reason: collision with root package name */
    public Object f260d;

    public /* synthetic */ h() {
        this.f257a = 9;
    }

    public static boolean h(Editable editable, KeyEvent keyEvent, boolean z2) {
        z[] zVarArr;
        if (!KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState())) {
            return false;
        }
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        if (selectionStart != -1 && selectionEnd != -1 && selectionStart == selectionEnd && (zVarArr = (z[]) editable.getSpans(selectionStart, selectionEnd, z.class)) != null && zVarArr.length > 0) {
            for (z zVar : zVarArr) {
                int spanStart = editable.getSpanStart(zVar);
                int spanEnd = editable.getSpanEnd(zVar);
                if ((z2 && spanStart == selectionStart) || ((!z2 && spanEnd == selectionStart) || (selectionStart > spanStart && selectionStart < spanEnd))) {
                    editable.delete(spanStart, spanEnd);
                    return true;
                }
            }
        }
        return false;
    }

    public static h p(Context context, AttributeSet attributeSet, int[] iArr, int i2) {
        return new h(context, context.obtainStyledAttributes(attributeSet, iArr, i2, 0));
    }

    @Override // Z.t
    public void a() {
        u uVar = (u) this.f260d;
        uVar.getClass();
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e2) {
            Log.e("NoteManager", "Sleep interrupted", e2);
        }
        E.c cVar = uVar.f734c.f705c;
        String str = uVar.f735d + "/notes/" + ((String) this.f258b);
        s sVar = new s();
        sVar.d(str);
        sVar.b("Authorization", "Bearer ".concat((String) cVar.f272c));
        B0.h a2 = sVar.a();
        q qVar = uVar.f733b;
        qVar.getClass();
        new y0.h(qVar, a2).e(new A.f(16, (A.f) this.f259c));
    }

    @Override // J.h
    public ClipDescription b() {
        return (ClipDescription) this.f259c;
    }

    @Override // J.h
    public Uri d() {
        return (Uri) this.f260d;
    }

    @Override // J.h
    public Object e() {
        return null;
    }

    @Override // Z.t
    public void f(String str) {
        ((A.f) this.f259c).C("Permission denied: " + str);
    }

    @Override // J.h
    public Uri g() {
        return (Uri) this.f258b;
    }

    public ColorStateList i(int i2) {
        int resourceId;
        ColorStateList t2;
        TypedArray typedArray = (TypedArray) this.f259c;
        if (typedArray.hasValue(i2) && (resourceId = typedArray.getResourceId(i2, 0)) != 0 && (t2 = C0.d.t((Context) this.f258b, resourceId)) != null) {
            return t2;
        }
        return typedArray.getColorStateList(i2);
    }

    public Drawable j(int i2) {
        int resourceId;
        TypedArray typedArray = (TypedArray) this.f259c;
        if (typedArray.hasValue(i2) && (resourceId = typedArray.getResourceId(i2, 0)) != 0) {
            return C0.d.v((Context) this.f258b, resourceId);
        }
        return typedArray.getDrawable(i2);
    }

    public Drawable k(int i2) {
        int resourceId;
        Drawable d2;
        if (((TypedArray) this.f259c).hasValue(i2) && (resourceId = ((TypedArray) this.f259c).getResourceId(i2, 0)) != 0) {
            C0203u a2 = C0203u.a();
            Context context = (Context) this.f258b;
            synchronized (a2) {
                d2 = a2.f2031a.d(context, resourceId, true);
            }
            return d2;
        }
        return null;
    }

    public Typeface l(int i2, int i3, U u2) {
        int i4 = 9;
        int resourceId = ((TypedArray) this.f259c).getResourceId(i2, 0);
        if (resourceId == 0) {
            return null;
        }
        if (((TypedValue) this.f260d) == null) {
            this.f260d = new TypedValue();
        }
        TypedValue typedValue = (TypedValue) this.f260d;
        ThreadLocal threadLocal = AbstractC0266k.f2955a;
        Context context = (Context) this.f258b;
        if (context.isRestricted()) {
            return null;
        }
        Resources resources = context.getResources();
        resources.getValue(resourceId, typedValue, true);
        CharSequence charSequence = typedValue.string;
        if (charSequence != null) {
            String charSequence2 = charSequence.toString();
            if (!charSequence2.startsWith("res/")) {
                u2.a();
                return null;
            }
            int i5 = typedValue.assetCookie;
            n.j jVar = A.g.f10b;
            Typeface typeface = (Typeface) jVar.a(A.g.b(resources, resourceId, charSequence2, i5, i3));
            if (typeface != null) {
                new Handler(Looper.getMainLooper()).post(new Z.b(u2, typeface, i4));
                return typeface;
            }
            try {
                if (charSequence2.toLowerCase().endsWith(".xml")) {
                    InterfaceC0259d c2 = AbstractC0257b.c(resources.getXml(resourceId), resources);
                    if (c2 == null) {
                        Log.e("ResourcesCompat", "Failed to find font-family tag");
                        u2.a();
                        return null;
                    }
                    return A.g.a(context, c2, resources, resourceId, charSequence2, typedValue.assetCookie, i3, u2);
                }
                int i6 = typedValue.assetCookie;
                Typeface o2 = A.g.f9a.o(context, resources, resourceId, charSequence2, i3);
                if (o2 != null) {
                    jVar.b(A.g.b(resources, resourceId, charSequence2, i6, i3), o2);
                }
                if (o2 != null) {
                    new Handler(Looper.getMainLooper()).post(new Z.b(u2, o2, i4));
                } else {
                    u2.a();
                }
                return o2;
            } catch (IOException e2) {
                Log.e("ResourcesCompat", "Failed to read xml resource ".concat(charSequence2), e2);
                u2.a();
                return null;
            } catch (XmlPullParserException e3) {
                Log.e("ResourcesCompat", "Failed to parse xml resource ".concat(charSequence2), e3);
                u2.a();
                return null;
            }
        }
        throw new Resources.NotFoundException("Resource \"" + resources.getResourceName(resourceId) + "\" (" + Integer.toHexString(resourceId) + ") is not a Font: " + typedValue);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public N m(C0148b c0148b, String str) {
        boolean isInstance;
        N a2;
        int i2;
        AbstractC0150d.e(str, "key");
        S s2 = (S) this.f258b;
        s2.getClass();
        LinkedHashMap linkedHashMap = s2.f1183a;
        N n2 = (N) linkedHashMap.get(str);
        Class cls = c0148b.f1735a;
        Map map = C0148b.f1733b;
        AbstractC0150d.c(map, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
        Integer num = (Integer) map.get(cls);
        if (num != null) {
            int intValue = num.intValue();
            isInstance = false;
            if (n2 instanceof InterfaceC0047a) {
                if (n2 instanceof InterfaceC0149c) {
                    i2 = ((InterfaceC0149c) n2).b();
                } else if (n2 instanceof InterfaceC0131a) {
                    i2 = 0;
                } else if (n2 instanceof i0.l) {
                    i2 = 1;
                } else {
                    i2 = -1;
                }
                if (i2 == intValue) {
                    isInstance = true;
                }
            }
        } else {
            boolean isPrimitive = cls.isPrimitive();
            Class cls2 = cls;
            if (isPrimitive) {
                cls2 = C0.f.r(j0.h.a(cls));
            }
            isInstance = cls2.isInstance(n2);
        }
        P p2 = (P) this.f259c;
        if (isInstance) {
            if (p2 instanceof L) {
                L l2 = (L) p2;
                AbstractC0150d.b(n2);
                AbstractC0068o abstractC0068o = l2.f1174d;
                if (abstractC0068o != null) {
                    U.e eVar = l2.f1175e;
                    AbstractC0150d.b(eVar);
                    H.a(n2, eVar, abstractC0068o);
                }
            }
            AbstractC0150d.c(n2, "null cannot be cast to non-null type T of androidx.lifecycle.viewmodel.ViewModelProviderImpl.getViewModel");
            return n2;
        }
        Q.c cVar = new Q.c((Q.b) this.f260d);
        cVar.f610a.put(R.b.f615a, str);
        try {
            try {
                a2 = p2.b(c0148b, cVar);
            } catch (AbstractMethodError unused) {
                a2 = p2.a(C0.f.q(c0148b));
            }
        } catch (AbstractMethodError unused2) {
            a2 = p2.c(C0.f.q(c0148b), cVar);
        }
        AbstractC0150d.e(a2, "viewModel");
        N n3 = (N) linkedHashMap.put(str, a2);
        if (n3 != null) {
            n3.a();
        }
        return a2;
    }

    public boolean n(CharSequence charSequence, int i2, int i3, y yVar) {
        int i4;
        if ((yVar.f581c & 3) == 0) {
            M.d dVar = (M.d) this.f260d;
            N.a c2 = yVar.c();
            int a2 = c2.a(8);
            if (a2 != 0) {
                ((ByteBuffer) c2.f319d).getShort(a2 + c2.f316a);
            }
            dVar.getClass();
            ThreadLocal threadLocal = M.d.f524b;
            if (threadLocal.get() == null) {
                threadLocal.set(new StringBuilder());
            }
            StringBuilder sb = (StringBuilder) threadLocal.get();
            sb.setLength(0);
            while (i2 < i3) {
                sb.append(charSequence.charAt(i2));
                i2++;
            }
            TextPaint textPaint = dVar.f525a;
            String sb2 = sb.toString();
            int i5 = A.d.f6a;
            boolean hasGlyph = textPaint.hasGlyph(sb2);
            int i6 = yVar.f581c & 4;
            if (hasGlyph) {
                i4 = i6 | 2;
            } else {
                i4 = i6 | 1;
            }
            yVar.f581c = i4;
        }
        if ((yVar.f581c & 3) == 2) {
            return true;
        }
        return false;
    }

    public boolean o(int i2, C0237d c0237d, v.f fVar) {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        int[] iArr = c0237d.f2315o0;
        int i3 = iArr[0];
        C0239b c0239b = (C0239b) this.f259c;
        c0239b.f2353a = i3;
        boolean z6 = true;
        c0239b.f2354b = iArr[1];
        c0239b.f2355c = c0237d.o();
        c0239b.f2356d = c0237d.i();
        c0239b.f2360i = false;
        c0239b.f2361j = i2;
        if (c0239b.f2353a == 3) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (c0239b.f2354b == 3) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z2 && c0237d.f2284V > 0.0f) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z3 && c0237d.f2284V > 0.0f) {
            z5 = true;
        } else {
            z5 = false;
        }
        int[] iArr2 = c0237d.f2320t;
        if (z4 && iArr2[0] == 4) {
            c0239b.f2353a = 1;
        }
        if (z5 && iArr2[1] == 4) {
            c0239b.f2354b = 1;
        }
        fVar.b(c0237d, c0239b);
        c0237d.K(c0239b.f2357e);
        c0237d.H(c0239b.f2358f);
        c0237d.f2267E = c0239b.h;
        int i4 = c0239b.f2359g;
        c0237d.f2288Z = i4;
        if (i4 <= 0) {
            z6 = false;
        }
        c0237d.f2267E = z6;
        c0239b.f2361j = 0;
        return c0239b.f2360i;
    }

    public Object q(CharSequence charSequence, int i2, int i3, int i4, boolean z2, p pVar) {
        int i5;
        M.u uVar;
        char c2;
        M.q qVar = new M.q((M.u) ((v) this.f259c).f572c);
        int codePointAt = Character.codePointAt(charSequence, i2);
        boolean z3 = true;
        int i6 = 0;
        int i7 = i2;
        loop0: while (true) {
            i5 = i7;
            while (i7 < i3 && i6 < i4 && z3) {
                SparseArray sparseArray = qVar.f551c.f568a;
                if (sparseArray == null) {
                    uVar = null;
                } else {
                    uVar = (M.u) sparseArray.get(codePointAt);
                }
                if (qVar.f549a != 2) {
                    if (uVar == null) {
                        qVar.a();
                        c2 = 1;
                    } else {
                        qVar.f549a = 2;
                        qVar.f551c = uVar;
                        qVar.f554f = 1;
                        c2 = 2;
                    }
                } else {
                    if (uVar != null) {
                        qVar.f551c = uVar;
                        qVar.f554f++;
                    } else {
                        if (codePointAt == 65038) {
                            qVar.a();
                        } else if (codePointAt != 65039) {
                            M.u uVar2 = qVar.f551c;
                            if (uVar2.f569b != null) {
                                if (qVar.f554f == 1) {
                                    if (qVar.b()) {
                                        qVar.f552d = qVar.f551c;
                                        qVar.a();
                                    } else {
                                        qVar.a();
                                    }
                                } else {
                                    qVar.f552d = uVar2;
                                    qVar.a();
                                }
                                c2 = 3;
                            } else {
                                qVar.a();
                            }
                        }
                        c2 = 1;
                    }
                    c2 = 2;
                }
                qVar.f553e = codePointAt;
                if (c2 != 1) {
                    if (c2 != 2) {
                        if (c2 == 3) {
                            if (z2 || !n(charSequence, i5, i7, qVar.f552d.f569b)) {
                                z3 = pVar.u(charSequence, i5, i7, qVar.f552d.f569b);
                                i6++;
                            }
                        }
                    } else {
                        int charCount = Character.charCount(codePointAt) + i7;
                        if (charCount < i3) {
                            codePointAt = Character.codePointAt(charSequence, charCount);
                        }
                        i7 = charCount;
                    }
                } else {
                    i7 = Character.charCount(Character.codePointAt(charSequence, i5)) + i5;
                    if (i7 < i3) {
                        codePointAt = Character.codePointAt(charSequence, i7);
                    }
                }
            }
        }
        if (qVar.f549a == 2 && qVar.f551c.f569b != null && ((qVar.f554f > 1 || qVar.b()) && i6 < i4 && z3 && (z2 || !n(charSequence, i5, i7, qVar.f551c.f569b)))) {
            pVar.u(charSequence, i5, i7, qVar.f551c.f569b);
        }
        return pVar.i();
    }

    public void r() {
        ((TypedArray) this.f259c).recycle();
    }

    @Override // u0.d
    public void s(u0.u uVar) {
        switch (this.f257a) {
            case 4:
                String j2 = uVar.f2572g.j();
                boolean h = uVar.h();
                Z.l lVar = (Z.l) this.f258b;
                Z.i iVar = (Z.i) this.f260d;
                if (!h) {
                    iVar.f709g.post(new Z.b(lVar, uVar, 1));
                    return;
                }
                try {
                    J0.c cVar = new J0.c(j2);
                    if (cVar.f474a.containsKey("success") && cVar.d()) {
                        E.c cVar2 = new E.c((String) this.f259c, cVar.e("token"), 5);
                        iVar.f705c = cVar2;
                        iVar.d(cVar2);
                        iVar.f707e.set(true);
                        iVar.f709g.post(new Z.d(lVar, 2));
                    } else {
                        iVar.f709g.post(new Z.b(lVar, cVar.g("Invalid credentials"), 2));
                    }
                    return;
                } catch (J0.b unused) {
                    iVar.f709g.post(new Z.d(lVar, 3));
                    return;
                }
            default:
                Z.l lVar2 = (Z.l) this.f258b;
                Z.i iVar2 = (Z.i) this.f260d;
                int i2 = uVar.f2569d;
                try {
                    J0.c cVar3 = new J0.c(uVar.f2572g.j());
                    if (cVar3.f474a.containsKey("success") && cVar3.d()) {
                        E.c cVar4 = new E.c((String) this.f259c, cVar3.e("token"), 5);
                        iVar2.f705c = cVar4;
                        iVar2.d(cVar4);
                        iVar2.f707e.set(true);
                        iVar2.f709g.post(new Z.e(lVar2, 2));
                    } else {
                        String g2 = cVar3.g("Registration failed");
                        if (i2 == 400 && g2.contains("exists")) {
                            iVar2.f709g.post(new Z.e(lVar2, 3));
                        } else {
                            iVar2.f709g.post(new Z.b(lVar2, g2, 4));
                        }
                    }
                    return;
                } catch (J0.b unused2) {
                    if (i2 == 400) {
                        iVar2.f709g.post(new Z.e(lVar2, 4));
                        return;
                    } else {
                        iVar2.f709g.post(new Z.b(lVar2, uVar, 5));
                        return;
                    }
                }
        }
    }

    public void t(s.e eVar, int i2, int i3, int i4) {
        int i5 = eVar.f2290a0;
        int i6 = eVar.f2292b0;
        eVar.f2290a0 = 0;
        eVar.f2292b0 = 0;
        eVar.K(i3);
        eVar.H(i4);
        if (i5 < 0) {
            eVar.f2290a0 = 0;
        } else {
            eVar.f2290a0 = i5;
        }
        if (i6 < 0) {
            eVar.f2292b0 = 0;
        } else {
            eVar.f2292b0 = i6;
        }
        s.e eVar2 = (s.e) this.f260d;
        eVar2.f2339s0 = i2;
        eVar2.Q();
    }

    public void u(s.e eVar) {
        ArrayList arrayList = (ArrayList) this.f258b;
        arrayList.clear();
        int size = eVar.f2336p0.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0237d c0237d = (C0237d) eVar.f2336p0.get(i2);
            int[] iArr = c0237d.f2315o0;
            if (iArr[0] == 3 || iArr[1] == 3) {
                arrayList.add(c0237d);
            }
        }
        eVar.f2338r0.f2365b = true;
    }

    @Override // u0.d
    public void w(IOException iOException) {
        switch (this.f257a) {
            case 4:
                ((Z.i) this.f260d).f709g.post(new Z.b((Z.l) this.f258b, iOException, 3));
                return;
            default:
                ((Z.i) this.f260d).f709g.post(new Z.b((Z.l) this.f258b, iOException, 6));
                return;
        }
    }

    public /* synthetic */ h(Object obj, Object obj2, Object obj3, int i2) {
        this.f257a = i2;
        this.f258b = obj;
        this.f259c = obj2;
        this.f260d = obj3;
    }

    public /* synthetic */ h(Object obj, Object obj2, Object obj3, int i2, boolean z2) {
        this.f257a = i2;
        this.f260d = obj;
        this.f258b = obj2;
        this.f259c = obj3;
    }

    public h(S s2, P p2, Q.b bVar) {
        this.f257a = 3;
        AbstractC0150d.e(s2, "store");
        AbstractC0150d.e(bVar, "extras");
        this.f258b = s2;
        this.f259c = p2;
        this.f260d = bVar;
    }

    public h(Context context, TypedArray typedArray) {
        this.f257a = 8;
        this.f258b = context;
        this.f259c = typedArray;
    }

    public h(Context context, LocationManager locationManager) {
        this.f257a = 7;
        this.f260d = new Object();
        this.f258b = context;
        this.f259c = locationManager;
    }

    public h(s.e eVar) {
        this.f257a = 10;
        this.f258b = new ArrayList();
        this.f259c = new Object();
        this.f260d = eVar;
    }

    public h(v vVar, A.m mVar, M.d dVar, Set set) {
        this.f257a = 2;
        this.f258b = mVar;
        this.f259c = vVar;
        this.f260d = dVar;
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            int[] iArr = (int[]) it.next();
            String str = new String(iArr, 0, iArr.length);
            q(str, 0, str.length(), 1, true, new A.f(7, str));
        }
    }

    @Override // J.h
    public void c() {
    }
}

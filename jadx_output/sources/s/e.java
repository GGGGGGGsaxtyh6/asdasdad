package s;

import D0.h;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import q.C0232c;
import t.C0239b;
import t.C0240c;
import t.C0242e;
import t.o;

/* loaded from: classes.dex */
public final class e extends C0237d {

    /* renamed from: A0, reason: collision with root package name */
    public C0235b[] f2326A0;

    /* renamed from: B0, reason: collision with root package name */
    public C0235b[] f2327B0;

    /* renamed from: C0, reason: collision with root package name */
    public int f2328C0;

    /* renamed from: D0, reason: collision with root package name */
    public boolean f2329D0;

    /* renamed from: E0, reason: collision with root package name */
    public boolean f2330E0;
    public WeakReference F0;

    /* renamed from: G0, reason: collision with root package name */
    public WeakReference f2331G0;

    /* renamed from: H0, reason: collision with root package name */
    public WeakReference f2332H0;

    /* renamed from: I0, reason: collision with root package name */
    public WeakReference f2333I0;

    /* renamed from: J0, reason: collision with root package name */
    public HashSet f2334J0;

    /* renamed from: K0, reason: collision with root package name */
    public C0239b f2335K0;

    /* renamed from: p0, reason: collision with root package name */
    public ArrayList f2336p0;

    /* renamed from: q0, reason: collision with root package name */
    public h f2337q0;

    /* renamed from: r0, reason: collision with root package name */
    public C0242e f2338r0;

    /* renamed from: s0, reason: collision with root package name */
    public int f2339s0;
    public v.f t0;

    /* renamed from: u0, reason: collision with root package name */
    public boolean f2340u0;

    /* renamed from: v0, reason: collision with root package name */
    public C0232c f2341v0;

    /* renamed from: w0, reason: collision with root package name */
    public int f2342w0;

    /* renamed from: x0, reason: collision with root package name */
    public int f2343x0;

    /* renamed from: y0, reason: collision with root package name */
    public int f2344y0;

    /* renamed from: z0, reason: collision with root package name */
    public int f2345z0;

    public static void R(C0237d c0237d, v.f fVar, C0239b c0239b) {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        int i2;
        int i3;
        if (fVar == null) {
            return;
        }
        if (c0237d.f2300f0 != 8 && !(c0237d instanceof f) && !(c0237d instanceof C0234a)) {
            int[] iArr = c0237d.f2315o0;
            c0239b.f2353a = iArr[0];
            boolean z6 = true;
            c0239b.f2354b = iArr[1];
            c0239b.f2355c = c0237d.o();
            c0239b.f2356d = c0237d.i();
            c0239b.f2360i = false;
            c0239b.f2361j = 0;
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
            if (z2 && c0237d.r(0) && c0237d.f2318r == 0 && !z4) {
                c0239b.f2353a = 2;
                if (z3 && c0237d.f2319s == 0) {
                    c0239b.f2353a = 1;
                }
                z2 = false;
            }
            if (z3 && c0237d.r(1) && c0237d.f2319s == 0 && !z5) {
                c0239b.f2354b = 2;
                if (z2 && c0237d.f2318r == 0) {
                    c0239b.f2354b = 1;
                }
                z3 = false;
            }
            if (c0237d.y()) {
                c0239b.f2353a = 1;
                z2 = false;
            }
            if (c0237d.z()) {
                c0239b.f2354b = 1;
                z3 = false;
            }
            int[] iArr2 = c0237d.f2320t;
            if (z4) {
                if (iArr2[0] == 4) {
                    c0239b.f2353a = 1;
                } else if (!z3) {
                    if (c0239b.f2354b == 1) {
                        i3 = c0239b.f2356d;
                    } else {
                        c0239b.f2353a = 2;
                        fVar.b(c0237d, c0239b);
                        i3 = c0239b.f2358f;
                    }
                    c0239b.f2353a = 1;
                    c0239b.f2355c = (int) (c0237d.f2284V * i3);
                }
            }
            if (z5) {
                if (iArr2[1] == 4) {
                    c0239b.f2354b = 1;
                } else if (!z2) {
                    if (c0239b.f2353a == 1) {
                        i2 = c0239b.f2355c;
                    } else {
                        c0239b.f2354b = 2;
                        fVar.b(c0237d, c0239b);
                        i2 = c0239b.f2357e;
                    }
                    c0239b.f2354b = 1;
                    if (c0237d.f2285W == -1) {
                        c0239b.f2356d = (int) (i2 / c0237d.f2284V);
                    } else {
                        c0239b.f2356d = (int) (c0237d.f2284V * i2);
                    }
                }
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
            return;
        }
        c0239b.f2357e = 0;
        c0239b.f2358f = 0;
    }

    @Override // s.C0237d
    public final void A() {
        this.f2341v0.t();
        this.f2342w0 = 0;
        this.f2343x0 = 0;
        this.f2336p0.clear();
        super.A();
    }

    @Override // s.C0237d
    public final void C(h hVar) {
        super.C(hVar);
        int size = this.f2336p0.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((C0237d) this.f2336p0.get(i2)).C(hVar);
        }
    }

    @Override // s.C0237d
    public final void L(boolean z2, boolean z3) {
        super.L(z2, z3);
        int size = this.f2336p0.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((C0237d) this.f2336p0.get(i2)).L(z2, z3);
        }
    }

    public final void N(C0237d c0237d, int i2) {
        if (i2 == 0) {
            int i3 = this.f2344y0 + 1;
            C0235b[] c0235bArr = this.f2327B0;
            if (i3 >= c0235bArr.length) {
                this.f2327B0 = (C0235b[]) Arrays.copyOf(c0235bArr, c0235bArr.length * 2);
            }
            C0235b[] c0235bArr2 = this.f2327B0;
            int i4 = this.f2344y0;
            c0235bArr2[i4] = new C0235b(c0237d, 0, this.f2340u0);
            this.f2344y0 = i4 + 1;
            return;
        }
        if (i2 == 1) {
            int i5 = this.f2345z0 + 1;
            C0235b[] c0235bArr3 = this.f2326A0;
            if (i5 >= c0235bArr3.length) {
                this.f2326A0 = (C0235b[]) Arrays.copyOf(c0235bArr3, c0235bArr3.length * 2);
            }
            C0235b[] c0235bArr4 = this.f2326A0;
            int i6 = this.f2345z0;
            c0235bArr4[i6] = new C0235b(c0237d, 1, this.f2340u0);
            this.f2345z0 = i6 + 1;
        }
    }

    public final void O(C0232c c0232c) {
        int i2;
        boolean S2 = S(64);
        b(c0232c, S2);
        int size = this.f2336p0.size();
        boolean z2 = false;
        for (int i3 = 0; i3 < size; i3++) {
            C0237d c0237d = (C0237d) this.f2336p0.get(i3);
            boolean[] zArr = c0237d.f2280R;
            zArr[0] = false;
            zArr[1] = false;
            if (c0237d instanceof C0234a) {
                z2 = true;
            }
        }
        if (z2) {
            for (int i4 = 0; i4 < size; i4++) {
                C0237d c0237d2 = (C0237d) this.f2336p0.get(i4);
                if (c0237d2 instanceof C0234a) {
                    C0234a c0234a = (C0234a) c0237d2;
                    for (int i5 = 0; i5 < c0234a.f2235q0; i5++) {
                        C0237d c0237d3 = c0234a.f2234p0[i5];
                        if (c0234a.f2237s0 || c0237d3.c()) {
                            int i6 = c0234a.f2236r0;
                            if (i6 != 0 && i6 != 1) {
                                if (i6 == 2 || i6 == 3) {
                                    c0237d3.f2280R[1] = true;
                                }
                            } else {
                                c0237d3.f2280R[0] = true;
                            }
                        }
                    }
                }
            }
        }
        HashSet hashSet = this.f2334J0;
        hashSet.clear();
        for (int i7 = 0; i7 < size; i7++) {
            C0237d c0237d4 = (C0237d) this.f2336p0.get(i7);
            c0237d4.getClass();
            if (c0237d4 instanceof f) {
                c0237d4.b(c0232c, S2);
            }
        }
        while (hashSet.size() > 0) {
            int size2 = hashSet.size();
            Iterator it = hashSet.iterator();
            if (!it.hasNext()) {
                if (size2 == hashSet.size()) {
                    Iterator it2 = hashSet.iterator();
                    while (it2.hasNext()) {
                        ((C0237d) it2.next()).b(c0232c, S2);
                    }
                    hashSet.clear();
                }
            } else {
                ((C0237d) it.next()).getClass();
                throw new ClassCastException();
            }
        }
        if (C0232c.f2189p) {
            HashSet hashSet2 = new HashSet();
            for (int i8 = 0; i8 < size; i8++) {
                C0237d c0237d5 = (C0237d) this.f2336p0.get(i8);
                c0237d5.getClass();
                if (!(c0237d5 instanceof f)) {
                    hashSet2.add(c0237d5);
                }
            }
            if (this.f2315o0[0] == 2) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            a(this, c0232c, hashSet2, i2, false);
            Iterator it3 = hashSet2.iterator();
            while (it3.hasNext()) {
                C0237d c0237d6 = (C0237d) it3.next();
                g.b(this, c0232c, c0237d6);
                c0237d6.b(c0232c, S2);
            }
        } else {
            for (int i9 = 0; i9 < size; i9++) {
                C0237d c0237d7 = (C0237d) this.f2336p0.get(i9);
                if (c0237d7 instanceof e) {
                    int[] iArr = c0237d7.f2315o0;
                    int i10 = iArr[0];
                    int i11 = iArr[1];
                    if (i10 == 2) {
                        c0237d7.I(1);
                    }
                    if (i11 == 2) {
                        c0237d7.J(1);
                    }
                    c0237d7.b(c0232c, S2);
                    if (i10 == 2) {
                        c0237d7.I(i10);
                    }
                    if (i11 == 2) {
                        c0237d7.J(i11);
                    }
                } else {
                    g.b(this, c0232c, c0237d7);
                    if (!(c0237d7 instanceof f)) {
                        c0237d7.b(c0232c, S2);
                    }
                }
            }
        }
        if (this.f2344y0 > 0) {
            g.a(this, c0232c, null, 0);
        }
        if (this.f2345z0 > 0) {
            g.a(this, c0232c, null, 1);
        }
    }

    public final boolean P(int i2, boolean z2) {
        boolean z3;
        C0242e c0242e = this.f2338r0;
        e eVar = c0242e.f2364a;
        boolean z4 = false;
        int h = eVar.h(0);
        int h2 = eVar.h(1);
        int p2 = eVar.p();
        int q2 = eVar.q();
        ArrayList arrayList = c0242e.f2368e;
        if (z2 && (h == 2 || h2 == 2)) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                o oVar = (o) it.next();
                if (oVar.f2400f == i2 && !oVar.k()) {
                    z2 = false;
                    break;
                }
            }
            if (i2 == 0) {
                if (z2 && h == 2) {
                    eVar.I(1);
                    eVar.K(c0242e.d(eVar, 0));
                    eVar.f2295d.f2399e.d(eVar.o());
                }
            } else if (z2 && h2 == 2) {
                eVar.J(1);
                eVar.H(c0242e.d(eVar, 1));
                eVar.f2297e.f2399e.d(eVar.i());
            }
        }
        int[] iArr = eVar.f2315o0;
        if (i2 == 0) {
            int i3 = iArr[0];
            if (i3 == 1 || i3 == 4) {
                int o2 = eVar.o() + p2;
                eVar.f2295d.f2402i.d(o2);
                eVar.f2295d.f2399e.d(o2 - p2);
                z3 = true;
            }
            z3 = false;
        } else {
            int i4 = iArr[1];
            if (i4 == 1 || i4 == 4) {
                int i5 = eVar.i() + q2;
                eVar.f2297e.f2402i.d(i5);
                eVar.f2297e.f2399e.d(i5 - q2);
                z3 = true;
            }
            z3 = false;
        }
        c0242e.g();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            o oVar2 = (o) it2.next();
            if (oVar2.f2400f == i2 && (oVar2.f2396b != eVar || oVar2.f2401g)) {
                oVar2.e();
            }
        }
        Iterator it3 = arrayList.iterator();
        while (true) {
            if (it3.hasNext()) {
                o oVar3 = (o) it3.next();
                if (oVar3.f2400f == i2 && (z3 || oVar3.f2396b != eVar)) {
                    if (!oVar3.h.f2379j) {
                        break;
                    }
                    if (!oVar3.f2402i.f2379j) {
                        break;
                    }
                    if (!(oVar3 instanceof C0240c) && !oVar3.f2399e.f2379j) {
                        break;
                    }
                }
            } else {
                z4 = true;
                break;
            }
        }
        eVar.I(h);
        eVar.J(h2);
        return z4;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(26:219|(7:220|221|(1:223)|224|225|226|227)|(3:345|346|(28:348|349|350|351|352|353|354|230|231|(1:235)|236|(6:240|241|242|243|244|245)|317|(1:342)(9:321|322|323|324|325|326|327|328|329)|330|331|252|(4:254|(3:256|(2:262|263)(1:260)|261)|264|265)(4:312|(1:314)|315|316)|266|(6:271|(1:273)|274|275|(1:279)|(1:283))|284|(1:286)(1:311)|287|(1:289)(1:310)|(1:309)(4:291|(1:296)|297|(3:302|(2:304|305)(1:307)|306))|308|(0)(0)|306))|229|230|231|(2:233|235)|236|(7:238|240|241|242|243|244|245)|317|(1:319)|342|330|331|252|(0)(0)|266|(7:269|271|(0)|274|275|(2:277|279)|(2:281|283))|284|(0)(0)|287|(0)(0)|(0)(0)|308|(0)(0)|306) */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x05f4, code lost:
    
        r6 = r21;
        r5 = r27;
        r4 = r28;
        r3 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:344:0x077c, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0679  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0696  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x07a5  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x07fd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0809 A[LOOP:14: B:272:0x0807->B:273:0x0809, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:286:0x086f  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x088e  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x089b  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x08d5  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x08d7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:309:0x08d1  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0897  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x087b  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x07e2  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x08e4  */
    /* JADX WARN: Removed duplicated region for block: B:594:0x05c0  */
    /* JADX WARN: Removed duplicated region for block: B:613:0x05ee A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:616:0x05ff  */
    /* JADX WARN: Removed duplicated region for block: B:623:0x061e  */
    /* JADX WARN: Removed duplicated region for block: B:630:0x0634  */
    /* JADX WARN: Removed duplicated region for block: B:632:0x0618  */
    /* JADX WARN: Type inference failed for: r5v69, types: [t.b, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void Q() {
        /*
            Method dump skipped, instructions count: 2288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: s.e.Q():void");
    }

    public final boolean S(int i2) {
        if ((this.f2328C0 & i2) == i2) {
            return true;
        }
        return false;
    }

    @Override // s.C0237d
    public final void l(StringBuilder sb) {
        sb.append(this.f2304j + ":{\n");
        StringBuilder sb2 = new StringBuilder("  actualWidth:");
        sb2.append(this.f2282T);
        sb.append(sb2.toString());
        sb.append("\n");
        sb.append("  actualHeight:" + this.f2283U);
        sb.append("\n");
        Iterator it = this.f2336p0.iterator();
        while (it.hasNext()) {
            ((C0237d) it.next()).l(sb);
            sb.append(",\n");
        }
        sb.append("}");
    }
}

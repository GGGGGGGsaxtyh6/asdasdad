package s;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.conscrypt.ct.CTConstants;
import t.AbstractC0245h;
import t.n;

/* renamed from: s.c, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0236c {

    /* renamed from: b, reason: collision with root package name */
    public int f2256b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f2257c;

    /* renamed from: d, reason: collision with root package name */
    public final C0237d f2258d;

    /* renamed from: e, reason: collision with root package name */
    public final int f2259e;

    /* renamed from: f, reason: collision with root package name */
    public C0236c f2260f;

    /* renamed from: i, reason: collision with root package name */
    public q.g f2262i;

    /* renamed from: a, reason: collision with root package name */
    public HashSet f2255a = null;

    /* renamed from: g, reason: collision with root package name */
    public int f2261g = 0;
    public int h = Integer.MIN_VALUE;

    public C0236c(C0237d c0237d, int i2) {
        this.f2258d = c0237d;
        this.f2259e = i2;
    }

    public final void a(C0236c c0236c, int i2, int i3) {
        if (c0236c == null) {
            g();
            return;
        }
        this.f2260f = c0236c;
        if (c0236c.f2255a == null) {
            c0236c.f2255a = new HashSet();
        }
        HashSet hashSet = this.f2260f.f2255a;
        if (hashSet != null) {
            hashSet.add(this);
        }
        this.f2261g = i2;
        this.h = i3;
    }

    public final void b(int i2, ArrayList arrayList, n nVar) {
        HashSet hashSet = this.f2255a;
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                AbstractC0245h.b(((C0236c) it.next()).f2258d, i2, arrayList, nVar);
            }
        }
    }

    public final int c() {
        if (!this.f2257c) {
            return 0;
        }
        return this.f2256b;
    }

    public final int d() {
        C0236c c0236c;
        if (this.f2258d.f2300f0 == 8) {
            return 0;
        }
        int i2 = this.h;
        if (i2 != Integer.MIN_VALUE && (c0236c = this.f2260f) != null && c0236c.f2258d.f2300f0 == 8) {
            return i2;
        }
        return this.f2261g;
    }

    public final boolean e() {
        C0236c c0236c;
        HashSet hashSet = this.f2255a;
        if (hashSet == null) {
            return false;
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            C0236c c0236c2 = (C0236c) it.next();
            int i2 = c0236c2.f2259e;
            int a2 = q.f.a(i2);
            C0237d c0237d = c0236c2.f2258d;
            switch (a2) {
                case 0:
                case 5:
                case 6:
                case 7:
                case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                    c0236c = null;
                    break;
                case 1:
                    c0236c = c0237d.f2272J;
                    break;
                case 2:
                    c0236c = c0237d.f2273K;
                    break;
                case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                    c0236c = c0237d.f2270H;
                    break;
                case 4:
                    c0236c = c0237d.f2271I;
                    break;
                default:
                    throw new AssertionError(A.e.h(i2));
            }
            if (c0236c.f()) {
                return true;
            }
        }
        return false;
    }

    public final boolean f() {
        if (this.f2260f != null) {
            return true;
        }
        return false;
    }

    public final void g() {
        HashSet hashSet;
        C0236c c0236c = this.f2260f;
        if (c0236c != null && (hashSet = c0236c.f2255a) != null) {
            hashSet.remove(this);
            if (this.f2260f.f2255a.size() == 0) {
                this.f2260f.f2255a = null;
            }
        }
        this.f2255a = null;
        this.f2260f = null;
        this.f2261g = 0;
        this.h = Integer.MIN_VALUE;
        this.f2257c = false;
        this.f2256b = 0;
    }

    public final void h() {
        q.g gVar = this.f2262i;
        if (gVar == null) {
            this.f2262i = new q.g(1);
        } else {
            gVar.c();
        }
    }

    public final void i(int i2) {
        this.f2256b = i2;
        this.f2257c = true;
    }

    public final String toString() {
        return this.f2258d.g0 + ":" + A.e.h(this.f2259e);
    }
}

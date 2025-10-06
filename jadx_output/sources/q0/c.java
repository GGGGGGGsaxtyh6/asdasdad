package q0;

import j0.AbstractC0150d;
import j0.AbstractC0151e;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class c implements p0.d {

    /* renamed from: a, reason: collision with root package name */
    public final CharSequence f2227a;

    /* renamed from: b, reason: collision with root package name */
    public final AbstractC0151e f2228b;

    public c(CharSequence charSequence, l lVar) {
        AbstractC0150d.e(charSequence, "input");
        this.f2227a = charSequence;
        this.f2228b = lVar;
    }

    @Override // p0.d
    public final Iterator iterator() {
        return new b(this);
    }
}

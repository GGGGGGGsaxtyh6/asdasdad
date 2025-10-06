package n;

import java.util.AbstractSet;
import java.util.Iterator;

/* renamed from: n.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0226a extends AbstractSet {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f f2095a;

    public C0226a(f fVar) {
        this.f2095a = fVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new d(this.f2095a);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f2095a.f2127c;
    }
}

package U;

import android.os.Bundle;
import g.AbstractActivityC0112j;
import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
public final class a implements d {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f661a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final Object f662b;

    public a(e eVar) {
        AbstractC0150d.e(eVar, "registry");
        this.f662b = new LinkedHashSet();
        eVar.c("androidx.savedstate.Restarter", this);
    }

    @Override // U.d
    public final Bundle a() {
        switch (this.f661a) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("classes_to_restore", new ArrayList<>((LinkedHashSet) this.f662b));
                return bundle;
            default:
                Bundle bundle2 = new Bundle();
                ((AbstractActivityC0112j) this.f662b).getDelegate().getClass();
                return bundle2;
        }
    }

    public a(AbstractActivityC0112j abstractActivityC0112j) {
        this.f662b = abstractActivityC0112j;
    }
}

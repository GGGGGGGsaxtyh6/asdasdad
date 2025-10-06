package androidx.fragment.app;

import a0.C0049c;
import android.content.Intent;
import b0.AbstractC0081g;
import b0.AbstractC0084j;
import b0.AbstractC0094t;
import b0.C0092r;
import c.C0095a;
import d.AbstractC0097a;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class r extends AbstractC0097a {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1073a;

    @Override // d.AbstractC0097a
    public final Object a(Intent intent, int i2) {
        boolean z2;
        switch (this.f1073a) {
            case 0:
                return new C0095a(intent, i2);
            case 1:
                C0092r c0092r = C0092r.f1235a;
                if (i2 == -1 && intent != null) {
                    String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                    int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
                    if (intArrayExtra != null && stringArrayExtra != null) {
                        ArrayList arrayList = new ArrayList(intArrayExtra.length);
                        for (int i3 : intArrayExtra) {
                            if (i3 == 0) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            arrayList.add(Boolean.valueOf(z2));
                        }
                        ArrayList B2 = AbstractC0081g.B(stringArrayExtra);
                        Iterator it = B2.iterator();
                        Iterator it2 = arrayList.iterator();
                        ArrayList arrayList2 = new ArrayList(Math.min(AbstractC0084j.I(B2), AbstractC0084j.I(arrayList)));
                        while (it.hasNext() && it2.hasNext()) {
                            arrayList2.add(new C0049c(it.next(), it2.next()));
                        }
                        return AbstractC0094t.E(arrayList2);
                    }
                    return c0092r;
                }
                return c0092r;
            default:
                return new C0095a(intent, i2);
        }
    }
}

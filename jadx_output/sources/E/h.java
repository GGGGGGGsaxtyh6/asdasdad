package E;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public final class h implements Callable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f283a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ String f284b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Context f285c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f286d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f287e;

    public /* synthetic */ h(String str, Context context, Object obj, int i2, int i3) {
        this.f283a = i3;
        this.f284b = str;
        this.f285c = context;
        this.f287e = obj;
        this.f286d = i2;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f283a) {
            case 0:
                Object[] objArr = {(g) this.f287e};
                ArrayList arrayList = new ArrayList(1);
                Object obj = objArr[0];
                Objects.requireNonNull(obj);
                arrayList.add(obj);
                return k.b(this.f284b, this.f285c, Collections.unmodifiableList(arrayList), this.f286d);
            default:
                try {
                    return k.b(this.f284b, this.f285c, (List) this.f287e, this.f286d);
                } catch (Throwable unused) {
                    return new j(-3);
                }
        }
    }
}

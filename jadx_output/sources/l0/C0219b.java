package l0;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

/* renamed from: l0.b, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0219b extends ThreadLocal {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2076a;

    @Override // java.lang.ThreadLocal
    public final Object initialValue() {
        switch (this.f2076a) {
            case 0:
                return new Random();
            default:
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
                simpleDateFormat.setLenient(false);
                simpleDateFormat.setTimeZone(v0.b.f2813f);
                return simpleDateFormat;
        }
    }
}

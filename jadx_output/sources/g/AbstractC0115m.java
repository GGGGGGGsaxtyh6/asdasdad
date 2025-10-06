package g;

import android.app.LocaleManager;
import android.os.LocaleList;

/* renamed from: g.m, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0115m {
    public static LocaleList a(Object obj) {
        return ((LocaleManager) obj).getApplicationLocales();
    }

    public static void b(Object obj, LocaleList localeList) {
        ((LocaleManager) obj).setApplicationLocales(localeList);
    }
}

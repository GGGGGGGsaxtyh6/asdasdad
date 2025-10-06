package g;

import android.content.res.Configuration;
import android.os.LocaleList;

/* loaded from: classes.dex */
public abstract class u {
    public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
        LocaleList locales = configuration.getLocales();
        LocaleList locales2 = configuration2.getLocales();
        if (!locales.equals(locales2)) {
            configuration3.setLocales(locales2);
            configuration3.locale = configuration2.locale;
        }
    }

    public static D.e b(Configuration configuration) {
        return D.e.a(configuration.getLocales().toLanguageTags());
    }

    public static void c(D.e eVar) {
        LocaleList.setDefault(LocaleList.forLanguageTags(eVar.f240a.f241a.toLanguageTags()));
    }

    public static void d(Configuration configuration, D.e eVar) {
        configuration.setLocales(LocaleList.forLanguageTags(eVar.f240a.f241a.toLanguageTags()));
    }
}

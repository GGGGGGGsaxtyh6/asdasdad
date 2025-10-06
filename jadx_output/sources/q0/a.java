package q0;

import j0.AbstractC0150d;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public static final Charset f2219a;

    /* renamed from: b, reason: collision with root package name */
    public static volatile Charset f2220b;

    /* renamed from: c, reason: collision with root package name */
    public static volatile Charset f2221c;

    static {
        Charset forName = Charset.forName("UTF-8");
        AbstractC0150d.d(forName, "forName(...)");
        f2219a = forName;
        AbstractC0150d.d(Charset.forName("UTF-16"), "forName(...)");
        AbstractC0150d.d(Charset.forName("UTF-16BE"), "forName(...)");
        AbstractC0150d.d(Charset.forName("UTF-16LE"), "forName(...)");
        AbstractC0150d.d(Charset.forName("US-ASCII"), "forName(...)");
        AbstractC0150d.d(Charset.forName("ISO-8859-1"), "forName(...)");
    }
}

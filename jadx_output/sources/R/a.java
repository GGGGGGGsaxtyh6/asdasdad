package R;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public final b f611a = new Object();

    /* renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f612b = new LinkedHashMap();

    /* renamed from: c, reason: collision with root package name */
    public final LinkedHashSet f613c = new LinkedHashSet();

    /* renamed from: d, reason: collision with root package name */
    public volatile boolean f614d;

    public static void a(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }
}

package D0;

import B0.AbstractC0006g;
import b0.AbstractC0094t;
import b0.C0092r;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import u0.q;

/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    public static final CopyOnWriteArraySet f245a = new CopyOnWriteArraySet();

    /* renamed from: b, reason: collision with root package name */
    public static final Map f246b;

    static {
        String name;
        Map map;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Package r2 = q.class.getPackage();
        if (r2 == null) {
            name = null;
        } else {
            name = r2.getName();
        }
        if (name != null) {
            linkedHashMap.put(name, "OkHttp");
        }
        linkedHashMap.put(q.class.getName(), "okhttp.OkHttpClient");
        linkedHashMap.put(AbstractC0006g.class.getName(), "okhttp.Http2");
        linkedHashMap.put(x0.d.class.getName(), "okhttp.TaskRunner");
        linkedHashMap.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        int size = linkedHashMap.size();
        if (size != 0) {
            if (size != 1) {
                map = new LinkedHashMap(linkedHashMap);
            } else {
                map = AbstractC0094t.F(linkedHashMap);
            }
        } else {
            map = C0092r.f1235a;
        }
        f246b = map;
    }
}

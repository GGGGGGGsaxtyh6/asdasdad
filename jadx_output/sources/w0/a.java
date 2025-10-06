package w0;

import u0.t;
import u0.u;
import u0.w;

/* loaded from: classes.dex */
public final class a {
    public static final u a(u uVar) {
        w wVar;
        if (uVar == null) {
            wVar = null;
        } else {
            wVar = uVar.f2572g;
        }
        if (wVar != null) {
            t i2 = uVar.i();
            i2.f2560g = null;
            return i2.a();
        }
        return uVar;
    }

    public static boolean b(String str) {
        if (!"Connection".equalsIgnoreCase(str) && !"Keep-Alive".equalsIgnoreCase(str) && !"Proxy-Authenticate".equalsIgnoreCase(str) && !"Proxy-Authorization".equalsIgnoreCase(str) && !"TE".equalsIgnoreCase(str) && !"Trailers".equalsIgnoreCase(str) && !"Transfer-Encoding".equalsIgnoreCase(str) && !"Upgrade".equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }
}

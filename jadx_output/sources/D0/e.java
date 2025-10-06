package D0;

import C0.o;
import j0.AbstractC0150d;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* loaded from: classes.dex */
public class e implements l {

    /* renamed from: f, reason: collision with root package name */
    public static final d f249f = new d(0);

    /* renamed from: a, reason: collision with root package name */
    public final Class f250a;

    /* renamed from: b, reason: collision with root package name */
    public final Method f251b;

    /* renamed from: c, reason: collision with root package name */
    public final Method f252c;

    /* renamed from: d, reason: collision with root package name */
    public final Method f253d;

    /* renamed from: e, reason: collision with root package name */
    public final Method f254e;

    public e(Class cls) {
        this.f250a = cls;
        Method declaredMethod = cls.getDeclaredMethod("setUseSessionTickets", Boolean.TYPE);
        AbstractC0150d.d(declaredMethod, "sslSocketClass.getDeclar…:class.javaPrimitiveType)");
        this.f251b = declaredMethod;
        this.f252c = cls.getMethod("setHostname", String.class);
        this.f253d = cls.getMethod("getAlpnSelectedProtocol", null);
        this.f254e = cls.getMethod("setAlpnProtocols", byte[].class);
    }

    @Override // D0.l
    public final boolean a(SSLSocket sSLSocket) {
        return this.f250a.isInstance(sSLSocket);
    }

    @Override // D0.l
    public final String b(SSLSocket sSLSocket) {
        if (!this.f250a.isInstance(sSLSocket)) {
            return null;
        }
        try {
            byte[] bArr = (byte[]) this.f253d.invoke(sSLSocket, null);
            if (bArr == null) {
                return null;
            }
            return new String(bArr, q0.a.f2219a);
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if ((cause instanceof NullPointerException) && AbstractC0150d.a(((NullPointerException) cause).getMessage(), "ssl == null")) {
                return null;
            }
            throw new AssertionError(e3);
        }
    }

    @Override // D0.l
    public final boolean c() {
        boolean z2 = C0.c.f204e;
        return C0.c.f204e;
    }

    @Override // D0.l
    public final void d(SSLSocket sSLSocket, String str, List list) {
        AbstractC0150d.e(list, "protocols");
        if (this.f250a.isInstance(sSLSocket)) {
            try {
                this.f251b.invoke(sSLSocket, Boolean.TRUE);
                if (str != null) {
                    this.f252c.invoke(sSLSocket, str);
                }
                Method method = this.f254e;
                o oVar = o.f236a;
                method.invoke(sSLSocket, C0.g.b(list));
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InvocationTargetException e3) {
                throw new AssertionError(e3);
            }
        }
    }
}

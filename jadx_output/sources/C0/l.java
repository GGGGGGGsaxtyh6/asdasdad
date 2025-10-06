package C0;

import b0.AbstractC0084j;
import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import org.conscrypt.BuildConfig;
import u0.r;

/* loaded from: classes.dex */
public final class l extends o {

    /* renamed from: c, reason: collision with root package name */
    public static final boolean f229c;

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0076, code lost:
    
        if (r1.intValue() >= 9) goto L43;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:5:0x0070  */
    static {
        /*
            r0 = 1
            r1 = 10
            java.lang.String r2 = "java.specification.version"
            java.lang.String r2 = java.lang.System.getProperty(r2)
            r3 = 0
            r4 = 0
            if (r2 != 0) goto L10
        Ld:
            r1 = r4
            goto L6e
        L10:
            C0.f.f(r1)
            int r5 = r2.length()
            if (r5 != 0) goto L1a
            goto Ld
        L1a:
            char r6 = r2.charAt(r3)
            r7 = 48
            int r7 = j0.AbstractC0150d.f(r6, r7)
            r8 = -2147483647(0xffffffff80000001, float:-1.4E-45)
            if (r7 >= 0) goto L3c
            if (r5 != r0) goto L2c
            goto Ld
        L2c:
            r7 = 45
            if (r6 != r7) goto L35
            r8 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r0
        L33:
            r7 = r6
            goto L3e
        L35:
            r7 = 43
            if (r6 != r7) goto Ld
            r6 = r0
            r7 = r3
            goto L3e
        L3c:
            r6 = r3
            goto L33
        L3e:
            r9 = -59652323(0xfffffffffc71c71d, float:-5.0215282E36)
            r10 = r3
            r11 = r9
        L43:
            if (r6 >= r5) goto L62
            char r12 = r2.charAt(r6)
            int r12 = java.lang.Character.digit(r12, r1)
            if (r12 >= 0) goto L50
            goto Ld
        L50:
            if (r10 >= r11) goto L59
            if (r11 != r9) goto Ld
            int r11 = r8 / 10
            if (r10 >= r11) goto L59
            goto Ld
        L59:
            int r10 = r10 * r1
            int r13 = r8 + r12
            if (r10 >= r13) goto L5f
            goto Ld
        L5f:
            int r10 = r10 - r12
            int r6 = r6 + r0
            goto L43
        L62:
            if (r7 == 0) goto L69
            java.lang.Integer r1 = java.lang.Integer.valueOf(r10)
            goto L6e
        L69:
            int r1 = -r10
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
        L6e:
            if (r1 == 0) goto L7b
            int r1 = r1.intValue()
            r2 = 9
            if (r1 < r2) goto L79
            goto L82
        L79:
            r0 = r3
            goto L82
        L7b:
            java.lang.Class<javax.net.ssl.SSLSocket> r1 = javax.net.ssl.SSLSocket.class
            java.lang.String r2 = "getApplicationProtocol"
            r1.getMethod(r2, r4)     // Catch: java.lang.NoSuchMethodException -> L79
        L82:
            C0.l.f229c = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: C0.l.<clinit>():void");
    }

    @Override // C0.o
    public final void d(SSLSocket sSLSocket, String str, List list) {
        AbstractC0150d.e(list, "protocols");
        SSLParameters sSLParameters = sSLSocket.getSSLParameters();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((r) obj) != r.HTTP_1_0) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(AbstractC0084j.I(arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((r) it.next()).f2548a);
        }
        Object[] array = arrayList2.toArray(new String[0]);
        if (array != null) {
            sSLParameters.setApplicationProtocols((String[]) array);
            sSLSocket.setSSLParameters(sSLParameters);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }

    @Override // C0.o
    public final String f(SSLSocket sSLSocket) {
        String applicationProtocol;
        boolean equals;
        try {
            applicationProtocol = sSLSocket.getApplicationProtocol();
            if (applicationProtocol == null) {
                equals = true;
            } else {
                equals = applicationProtocol.equals(BuildConfig.FLAVOR);
            }
            if (equals) {
                return null;
            }
            return applicationProtocol;
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }
}

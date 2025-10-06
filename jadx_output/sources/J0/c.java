package J0;

import A.m;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.conscrypt.BuildConfig;
import org.conscrypt.ct.CTConstants;

/* loaded from: classes.dex */
public final class c {

    /* renamed from: b, reason: collision with root package name */
    public static final Pattern f472b = Pattern.compile("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?");

    /* renamed from: c, reason: collision with root package name */
    public static final m f473c = new m(3);

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f474a;

    public c() {
        this.f474a = new HashMap();
    }

    public static Annotation b(Method method) {
        if (method != null) {
            if (method.isAnnotationPresent(e.class)) {
                return method.getAnnotation(e.class);
            }
            Class<?> declaringClass = method.getDeclaringClass();
            if (declaringClass.getSuperclass() == null) {
                return null;
            }
            for (Class<?> cls : declaringClass.getInterfaces()) {
                try {
                    return b(cls.getMethod(method.getName(), method.getParameterTypes()));
                } catch (NoSuchMethodException | SecurityException unused) {
                }
            }
            try {
                return b(declaringClass.getSuperclass().getMethod(method.getName(), method.getParameterTypes()));
            } catch (NoSuchMethodException | SecurityException unused2) {
            }
        }
        return null;
    }

    public static int c(Method method, Class cls) {
        int c2;
        if (method != null) {
            if (method.isAnnotationPresent(cls)) {
                return 1;
            }
            Class<?> declaringClass = method.getDeclaringClass();
            if (declaringClass.getSuperclass() == null) {
                return -1;
            }
            for (Class<?> cls2 : declaringClass.getInterfaces()) {
                try {
                    c2 = c(cls2.getMethod(method.getName(), method.getParameterTypes()), cls);
                } catch (NoSuchMethodException | SecurityException unused) {
                }
                if (c2 > 0) {
                    return c2 + 1;
                }
            }
            try {
                int c3 = c(declaringClass.getSuperclass().getMethod(method.getName(), method.getParameterTypes()), cls);
                if (c3 > 0) {
                    return c3 + 1;
                }
            } catch (NoSuchMethodException | SecurityException unused2) {
            }
        }
        return -1;
    }

    public static final void f(StringWriter stringWriter, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            stringWriter.write(32);
        }
    }

    public static Writer i(String str, StringWriter stringWriter) {
        if (str != null && !str.isEmpty()) {
            int length = str.length();
            stringWriter.write(34);
            int i2 = 0;
            char c2 = 0;
            while (i2 < length) {
                char charAt = str.charAt(i2);
                if (charAt != '\f') {
                    if (charAt != '\r') {
                        if (charAt != '\"') {
                            if (charAt != '/') {
                                if (charAt != '\\') {
                                    switch (charAt) {
                                        case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                                            stringWriter.write("\\b");
                                            break;
                                        case '\t':
                                            stringWriter.write("\\t");
                                            break;
                                        case '\n':
                                            stringWriter.write("\\n");
                                            break;
                                        default:
                                            if (charAt >= ' ' && ((charAt < 128 || charAt >= 160) && (charAt < 8192 || charAt >= 8448))) {
                                                stringWriter.write(charAt);
                                                break;
                                            } else {
                                                stringWriter.write("\\u");
                                                String hexString = Integer.toHexString(charAt);
                                                stringWriter.write("0000", 0, 4 - hexString.length());
                                                stringWriter.write(hexString);
                                                break;
                                            }
                                    }
                                }
                            } else {
                                if (c2 == '<') {
                                    stringWriter.write(92);
                                }
                                stringWriter.write(charAt);
                            }
                        }
                        stringWriter.write(92);
                        stringWriter.write(charAt);
                    } else {
                        stringWriter.write("\\r");
                    }
                } else {
                    stringWriter.write("\\f");
                }
                i2++;
                c2 = charAt;
            }
            stringWriter.write(34);
            return stringWriter;
        }
        stringWriter.write("\"\"");
        return stringWriter;
    }

    public static String j(String str) {
        String obj;
        StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            try {
                try {
                    i(str, stringWriter);
                    obj = stringWriter.toString();
                } catch (IOException unused) {
                    return BuildConfig.FLAVOR;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    public static Number k(String str) {
        char charAt = str.charAt(0);
        if ((charAt >= '0' && charAt <= '9') || charAt == '-') {
            if (str.indexOf(46) <= -1 && str.indexOf(101) <= -1 && str.indexOf(69) <= -1 && !"-0".equals(str)) {
                if (charAt == '0' && str.length() > 1) {
                    char charAt2 = str.charAt(1);
                    if (charAt2 >= '0' && charAt2 <= '9') {
                        throw new NumberFormatException(A.e.d("val [", str, "] is not a valid number."));
                    }
                } else if (charAt == '-' && str.length() > 2) {
                    char charAt3 = str.charAt(1);
                    char charAt4 = str.charAt(2);
                    if (charAt3 == '0' && charAt4 >= '0' && charAt4 <= '9') {
                        throw new NumberFormatException(A.e.d("val [", str, "] is not a valid number."));
                    }
                }
                BigInteger bigInteger = new BigInteger(str);
                if (bigInteger.bitLength() <= 31) {
                    return Integer.valueOf(bigInteger.intValue());
                }
                if (bigInteger.bitLength() <= 63) {
                    return Long.valueOf(bigInteger.longValue());
                }
                return bigInteger;
            }
            try {
                try {
                    BigDecimal bigDecimal = new BigDecimal(str);
                    if (charAt == '-' && BigDecimal.ZERO.compareTo(bigDecimal) == 0) {
                        return Double.valueOf(-0.0d);
                    }
                    return bigDecimal;
                } catch (NumberFormatException unused) {
                    Double valueOf = Double.valueOf(str);
                    if (!valueOf.isNaN() && !valueOf.isInfinite()) {
                        return valueOf;
                    }
                    throw new NumberFormatException("val [" + str + "] is not a valid number.");
                }
            } catch (NumberFormatException unused2) {
                throw new NumberFormatException(A.e.d("val [", str, "] is not a valid number."));
            }
        }
        throw new NumberFormatException(A.e.d("val [", str, "] is not a valid number."));
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
    
        if (r0.isNaN() == false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void l(java.lang.Object r2) {
        /*
            boolean r0 = r2 instanceof java.lang.Number
            if (r0 == 0) goto L34
            java.lang.Number r2 = (java.lang.Number) r2
            boolean r0 = r2 instanceof java.lang.Double
            if (r0 == 0) goto L19
            r0 = r2
            java.lang.Double r0 = (java.lang.Double) r0
            boolean r1 = r0.isInfinite()
            if (r1 != 0) goto L2c
            boolean r0 = r0.isNaN()
            if (r0 != 0) goto L2c
        L19:
            boolean r0 = r2 instanceof java.lang.Float
            if (r0 == 0) goto L34
            java.lang.Float r2 = (java.lang.Float) r2
            boolean r0 = r2.isInfinite()
            if (r0 != 0) goto L2c
            boolean r2 = r2.isNaN()
            if (r2 != 0) goto L2c
            goto L34
        L2c:
            J0.b r2 = new J0.b
            java.lang.String r0 = "JSON does not allow non-finite numbers."
            r2.<init>(r0)
            throw r2
        L34:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: J0.c.l(java.lang.Object):void");
    }

    public static Object m(Object obj) {
        String str;
        try {
            m mVar = f473c;
            if (mVar.equals(obj)) {
                return mVar;
            }
            if (!(obj instanceof c) && !(obj instanceof a) && !mVar.equals(obj) && !(obj instanceof Byte) && !(obj instanceof Character) && !(obj instanceof Short) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Boolean) && !(obj instanceof Float) && !(obj instanceof Double) && !(obj instanceof String) && !(obj instanceof BigInteger) && !(obj instanceof BigDecimal) && !(obj instanceof Enum)) {
                if (obj instanceof Collection) {
                    return new a((Collection) obj);
                }
                if (obj.getClass().isArray()) {
                    return new a(obj);
                }
                if (obj instanceof Map) {
                    return new c((Map) obj);
                }
                Package r02 = obj.getClass().getPackage();
                if (r02 != null) {
                    str = r02.getName();
                } else {
                    str = BuildConfig.FLAVOR;
                }
                if (!str.startsWith("java.") && !str.startsWith("javax.") && obj.getClass().getClassLoader() != null) {
                    return new c(obj);
                }
                return obj.toString();
            }
            return obj;
        } catch (Exception unused) {
            return null;
        }
    }

    public static final void o(StringWriter stringWriter, Object obj, int i2) {
        if (obj != null && !obj.equals(null)) {
            if (obj instanceof Number) {
                Number number = (Number) obj;
                l(number);
                String obj2 = number.toString();
                if (obj2.indexOf(46) > 0 && obj2.indexOf(101) < 0 && obj2.indexOf(69) < 0) {
                    while (obj2.endsWith("0")) {
                        obj2 = obj2.substring(0, obj2.length() - 1);
                    }
                    if (obj2.endsWith(".")) {
                        obj2 = obj2.substring(0, obj2.length() - 1);
                    }
                }
                if (f472b.matcher(obj2).matches()) {
                    stringWriter.write(obj2);
                    return;
                } else {
                    i(obj2, stringWriter);
                    return;
                }
            }
            if (obj instanceof Boolean) {
                stringWriter.write(obj.toString());
                return;
            }
            if (obj instanceof Enum) {
                stringWriter.write(j(((Enum) obj).name()));
                return;
            }
            if (obj instanceof c) {
                ((c) obj).n(stringWriter, i2);
                return;
            }
            if (obj instanceof a) {
                ((a) obj).c(stringWriter, i2);
                return;
            }
            if (obj instanceof Map) {
                new c((Map) obj).n(stringWriter, i2);
                return;
            }
            if (obj instanceof Collection) {
                new a((Collection) obj).c(stringWriter, i2);
                return;
            } else if (obj.getClass().isArray()) {
                new a(obj).c(stringWriter, i2);
                return;
            } else {
                i(obj.toString(), stringWriter);
                return;
            }
        }
        stringWriter.write("null");
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [J0.b, java.lang.RuntimeException] */
    public static b p(String str, String str2) {
        return new RuntimeException("JSONObject[" + j(str) + "] is not a " + str2 + ".", null);
    }

    public final Object a(String str) {
        Object obj = this.f474a.get(str);
        if (obj != null) {
            return obj;
        }
        throw new RuntimeException("JSONObject[" + j(str) + "] not found.");
    }

    public final boolean d() {
        Object a2 = a("success");
        if (!a2.equals(Boolean.FALSE)) {
            boolean z2 = a2 instanceof String;
            if (!z2 || !((String) a2).equalsIgnoreCase("false")) {
                if (!a2.equals(Boolean.TRUE)) {
                    if (!z2 || !((String) a2).equalsIgnoreCase("true")) {
                        throw p("success", "Boolean");
                    }
                    return true;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public final String e(String str) {
        Object a2 = a(str);
        if (a2 instanceof String) {
            return (String) a2;
        }
        throw p(str, "string");
    }

    public final String g(String str) {
        Object obj = this.f474a.get("error");
        if (!f473c.equals(obj)) {
            return obj.toString();
        }
        return str;
    }

    public final void h(Object obj, String str) {
        HashMap hashMap = this.f474a;
        if (obj != null) {
            l(obj);
            hashMap.put(str, obj);
        } else {
            hashMap.remove(str);
        }
    }

    public final void n(StringWriter stringWriter, int i2) {
        HashMap hashMap = this.f474a;
        try {
            int size = hashMap.size();
            stringWriter.write(123);
            if (size == 1) {
                Map.Entry entry = (Map.Entry) hashMap.entrySet().iterator().next();
                String str = (String) entry.getKey();
                stringWriter.write(j(str));
                stringWriter.write(58);
                try {
                    o(stringWriter, entry.getValue(), i2);
                    stringWriter.write(125);
                } catch (Exception e2) {
                    throw new RuntimeException("Unable to write JSONObject value for key: " + str, e2);
                }
            }
            if (size != 0) {
                boolean z2 = false;
                for (Map.Entry entry2 : hashMap.entrySet()) {
                    if (z2) {
                        stringWriter.write(44);
                    }
                    f(stringWriter, i2);
                    String str2 = (String) entry2.getKey();
                    stringWriter.write(j(str2));
                    stringWriter.write(58);
                    try {
                        o(stringWriter, entry2.getValue(), i2);
                        z2 = true;
                    } catch (Exception e3) {
                        throw new RuntimeException("Unable to write JSONObject value for key: " + str2, e3);
                    }
                }
                f(stringWriter, i2);
            }
            stringWriter.write(125);
        } catch (IOException e4) {
            throw new RuntimeException(e4.getMessage(), e4);
        }
    }

    public final String toString() {
        String obj;
        try {
            StringWriter stringWriter = new StringWriter();
            synchronized (stringWriter.getBuffer()) {
                n(stringWriter, 0);
                obj = stringWriter.toString();
            }
            return obj;
        } catch (Exception unused) {
            return null;
        }
    }

    public c(f fVar) {
        this();
        if (fVar.c() != '{') {
            throw fVar.e("A JSONObject text must begin with '{'");
        }
        while (true) {
            char c2 = fVar.c();
            if (c2 == 0) {
                throw fVar.e("A JSONObject text must end with '}'");
            }
            if (c2 == '}') {
                return;
            }
            fVar.a();
            String obj = fVar.d().toString();
            if (fVar.c() == ':') {
                if (obj != null) {
                    if (this.f474a.get(obj) == null) {
                        Object d2 = fVar.d();
                        if (d2 != null) {
                            h(d2, obj);
                        }
                    } else {
                        throw fVar.e("Duplicate key \"" + obj + "\"");
                    }
                }
                char c3 = fVar.c();
                if (c3 != ',' && c3 != ';') {
                    if (c3 != '}') {
                        throw fVar.e("Expected a ',' or '}'");
                    }
                    return;
                } else if (fVar.c() == '}') {
                    return;
                } else {
                    fVar.a();
                }
            } else {
                throw fVar.e("Expected a ':' after a key");
            }
        }
    }

    public c(Map map) {
        if (map == null) {
            this.f474a = new HashMap();
            return;
        }
        this.f474a = new HashMap(map.size());
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getKey() != null) {
                Object value = entry.getValue();
                if (value != null) {
                    this.f474a.put(String.valueOf(entry.getKey()), m(value));
                }
            } else {
                throw new NullPointerException("Null key.");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0109 A[Catch: IOException | IllegalAccessException | IllegalArgumentException | InvocationTargetException -> 0x011b, TRY_LEAVE, TryCatch #0 {IOException | IllegalAccessException | IllegalArgumentException | InvocationTargetException -> 0x011b, blocks: (B:30:0x0103, B:32:0x0109, B:34:0x0116, B:34:0x0116, B:34:0x0116), top: B:29:0x0103 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x011b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public c(java.lang.Object r12) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: J0.c.<init>(java.lang.Object):void");
    }

    public c(String str) {
        this(new f(str));
    }
}

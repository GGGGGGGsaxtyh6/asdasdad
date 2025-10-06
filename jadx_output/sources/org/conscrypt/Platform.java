package org.conscrypt;

import android.os.Build;
import android.util.Log;
import dalvik.system.BlockGuard;
import dalvik.system.CloseGuard;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;
import java.security.AlgorithmParameters;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIMatcher;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import org.conscrypt.ct.CTLogStore;
import org.conscrypt.ct.CTPolicy;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class Platform {
    private static final String TAG = "Conscrypt";
    private static Method m_getCurveName;

    static {
        try {
            Method declaredMethod = ECParameterSpec.class.getDeclaredMethod("getCurveName", null);
            m_getCurveName = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (Exception unused) {
        }
    }

    private Platform() {
    }

    public static void blockGuardOnNetwork() {
        BlockGuard.getThreadPolicy().onNetwork();
    }

    public static void checkClientTrusted(X509TrustManager x509TrustManager, X509Certificate[] x509CertificateArr, String str, AbstractConscryptSocket abstractConscryptSocket) {
        if (checkTrusted("checkClientTrusted", x509TrustManager, x509CertificateArr, str, Socket.class, abstractConscryptSocket) || checkTrusted("checkClientTrusted", x509TrustManager, x509CertificateArr, str, String.class, abstractConscryptSocket.getHandshakeSession().getPeerHost())) {
            return;
        }
        x509TrustManager.checkClientTrusted(x509CertificateArr, str);
    }

    public static void checkServerTrusted(X509TrustManager x509TrustManager, X509Certificate[] x509CertificateArr, String str, AbstractConscryptSocket abstractConscryptSocket) {
        if (checkTrusted("checkServerTrusted", x509TrustManager, x509CertificateArr, str, Socket.class, abstractConscryptSocket) || checkTrusted("checkServerTrusted", x509TrustManager, x509CertificateArr, str, String.class, abstractConscryptSocket.getHandshakeSession().getPeerHost())) {
            return;
        }
        x509TrustManager.checkServerTrusted(x509CertificateArr, str);
    }

    private static boolean checkTrusted(String str, X509TrustManager x509TrustManager, X509Certificate[] x509CertificateArr, String str2, Class<?> cls, Object obj) {
        try {
            x509TrustManager.getClass().getMethod(str, X509Certificate[].class, String.class, cls).invoke(x509TrustManager, x509CertificateArr, str2, obj);
            return true;
        } catch (IllegalAccessException | NoSuchMethodException unused) {
            return false;
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof CertificateException) {
                throw ((CertificateException) e2.getCause());
            }
            throw new RuntimeException(e2.getCause());
        }
    }

    public static void closeGuardClose(Object obj) {
        ((CloseGuard) obj).close();
    }

    public static CloseGuard closeGuardGet() {
        return CloseGuard.get();
    }

    public static void closeGuardOpen(Object obj, String str) {
        ((CloseGuard) obj).open(str);
    }

    public static void closeGuardWarnIfOpen(Object obj) {
        ((CloseGuard) obj).warnIfOpen();
    }

    public static ConscryptEngineSocket createEngineSocket(SSLParametersImpl sSLParametersImpl) {
        return new Java8EngineSocket(sSLParametersImpl);
    }

    public static ConscryptFileDescriptorSocket createFileDescriptorSocket(SSLParametersImpl sSLParametersImpl) {
        return new Java8FileDescriptorSocket(sSLParametersImpl);
    }

    public static GCMParameters fromGCMParameterSpec(AlgorithmParameterSpec algorithmParameterSpec) {
        Class<?> cls;
        try {
            cls = Class.forName("javax.crypto.spec.GCMParameterSpec");
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls == null || !cls.isAssignableFrom(algorithmParameterSpec.getClass())) {
            return null;
        }
        try {
            return new GCMParameters(((Integer) cls.getMethod("getTLen", null).invoke(algorithmParameterSpec, null)).intValue(), (byte[]) cls.getMethod("getIV", null).invoke(algorithmParameterSpec, null));
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("GCMParameterSpec lacks expected methods", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("GCMParameterSpec lacks expected methods", e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException("Could not fetch GCM parameters", e4.getTargetException());
        }
    }

    public static AlgorithmParameterSpec fromGCMParameters(AlgorithmParameters algorithmParameters) {
        Class<?> cls;
        try {
            cls = Class.forName("javax.crypto.spec.GCMParameterSpec");
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls != null) {
            try {
                return algorithmParameters.getParameterSpec(cls);
            } catch (InvalidParameterSpecException unused2) {
            }
        }
        return null;
    }

    private static Class<?> getClass(String... strArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            try {
                return Class.forName(strArr[i2]);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static String getCurveName(ECParameterSpec eCParameterSpec) {
        Method method = m_getCurveName;
        if (method == null) {
            return null;
        }
        try {
            return (String) method.invoke(eCParameterSpec, null);
        } catch (Exception unused) {
            return null;
        }
    }

    public static KeyStore getDefaultCertKeyStore() {
        KeyStore keyStore = KeyStore.getInstance("AndroidCAStore");
        try {
            keyStore.load(null, null);
            return keyStore;
        } catch (IOException e2) {
            throw new KeyStoreException(e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new KeyStoreException(e3);
        } catch (CertificateException e4) {
            throw new KeyStoreException(e4);
        }
    }

    public static ConscryptHostnameVerifier getDefaultHostnameVerifier() {
        return OkHostnameVerifier.strictInstance();
    }

    public static String getDefaultProviderName() {
        return TAG;
    }

    public static String getEndpointIdentificationAlgorithm(SSLParameters sSLParameters) {
        return null;
    }

    public static FileDescriptor getFileDescriptor(Socket socket) {
        try {
            Field declaredField = Socket.class.getDeclaredField("impl");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(socket);
            Field declaredField2 = SocketImpl.class.getDeclaredField("fd");
            declaredField2.setAccessible(true);
            return (FileDescriptor) declaredField2.get(obj);
        } catch (Exception e2) {
            throw new RuntimeException("Can't get FileDescriptor from socket", e2);
        }
    }

    public static FileDescriptor getFileDescriptorFromSSLSocket(AbstractConscryptSocket abstractConscryptSocket) {
        return getFileDescriptor(abstractConscryptSocket);
    }

    public static String getHostStringFromInetSocketAddress(InetSocketAddress inetSocketAddress) {
        try {
            return (String) InetSocketAddress.class.getDeclaredMethod("getHostString", null).invoke(inetSocketAddress, null);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getOriginalHostNameFromInetAddress(InetAddress inetAddress) {
        if (Build.VERSION.SDK_INT > 27) {
            try {
                Method declaredMethod = InetAddress.class.getDeclaredMethod("holder", null);
                declaredMethod.setAccessible(true);
                Method declaredMethod2 = Class.forName("java.net.InetAddress$InetAddressHolder").getDeclaredMethod("getOriginalHostName", null);
                declaredMethod2.setAccessible(true);
                String str = (String) declaredMethod2.invoke(declaredMethod.invoke(inetAddress, null), null);
                if (str == null) {
                    return inetAddress.getHostAddress();
                }
                return str;
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException unused) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException("Failed to get originalHostName", e2);
            }
        }
        return inetAddress.getHostAddress();
    }

    public static void getSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, AbstractConscryptSocket abstractConscryptSocket) {
        try {
            getSSLParametersFromImpl(sSLParameters, sSLParametersImpl);
            setParametersSniHostname(sSLParameters, sSLParametersImpl, abstractConscryptSocket);
        } catch (IllegalAccessException | NoSuchMethodException unused) {
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2.getCause());
        }
    }

    private static void getSSLParametersFromImpl(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl) {
        sSLParameters.getClass().getMethod("setEndpointIdentificationAlgorithm", String.class).invoke(sSLParameters, sSLParametersImpl.getEndpointIdentificationAlgorithm());
        sSLParameters.getClass().getMethod("setUseCipherSuitesOrder", Boolean.TYPE).invoke(sSLParameters, Boolean.valueOf(sSLParametersImpl.getUseCipherSuitesOrder()));
    }

    private static String getSniHostnameFromParams(SSLParameters sSLParameters) {
        List<SNIServerName> list = (List) sSLParameters.getClass().getMethod("getServerNames", null).invoke(sSLParameters, null);
        if (list != null) {
            for (SNIServerName sNIServerName : list) {
                if (sNIServerName.getType() == 0) {
                    return ((SNIHostName) sNIServerName).getAsciiName();
                }
            }
        }
        return null;
    }

    public static boolean isCTVerificationRequired(String str) {
        String property;
        boolean z2 = false;
        if (str == null || (property = Security.getProperty("conscrypt.ct.enable")) == null || !Boolean.valueOf(property).booleanValue()) {
            return false;
        }
        List<String> asList = Arrays.asList(str.split("\\."));
        Collections.reverse(asList);
        String str2 = "conscrypt.ct.enforce";
        for (String str3 : asList) {
            String property2 = Security.getProperty(str2 + ".*");
            if (property2 != null) {
                z2 = Boolean.valueOf(property2).booleanValue();
            }
            str2 = str2 + "." + str3;
        }
        String property3 = Security.getProperty(str2);
        if (property3 != null) {
            return Boolean.valueOf(property3).booleanValue();
        }
        return z2;
    }

    public static void logEvent(String str) {
        try {
            Class<?> cls = Class.forName("android.os.Process");
            Integer num = (Integer) cls.getMethod("myUid", null).invoke(cls.getDeclaredConstructor(null).newInstance(null), null);
            num.intValue();
            Class<?> cls2 = Class.forName("android.util.EventLog");
            cls2.getMethod("writeEvent", Integer.TYPE, Object[].class).invoke(cls2.getDeclaredConstructor(null).newInstance(null), 1397638484, new Object[]{"conscrypt", num, str});
        } catch (Exception unused) {
        }
    }

    private static void logStackTraceSnippet(String str, Throwable th) {
        Log.w(TAG, str);
        StackTraceElement[] stackTrace = th.getStackTrace();
        for (int i2 = 0; i2 < 2 && i2 < stackTrace.length; i2++) {
            Log.w(TAG, "\tat " + stackTrace[i2].toString());
        }
    }

    public static CertBlocklist newDefaultBlocklist() {
        return null;
    }

    public static ConscryptCertStore newDefaultCertStore() {
        return null;
    }

    public static CTLogStore newDefaultLogStore() {
        return null;
    }

    public static CTPolicy newDefaultPolicy(CTLogStore cTLogStore) {
        return null;
    }

    public static String oidToAlgorithmName(String str) {
        try {
            try {
                Method declaredMethod = Class.forName("org.apache.harmony.security.utils.AlgNameMapper").getDeclaredMethod("map2AlgName", String.class);
                declaredMethod.setAccessible(true);
                return (String) declaredMethod.invoke(null, str);
            } catch (InvocationTargetException e2) {
                Throwable cause = e2.getCause();
                if (!(cause instanceof RuntimeException)) {
                    if (cause instanceof Error) {
                        throw ((Error) cause);
                    }
                    throw new RuntimeException(e2);
                }
                throw ((RuntimeException) cause);
            } catch (Exception unused) {
                return str;
            }
        } catch (InvocationTargetException e3) {
            Throwable cause2 = e3.getCause();
            if (!(cause2 instanceof RuntimeException)) {
                if (cause2 instanceof Error) {
                    throw ((Error) cause2);
                }
                throw new RuntimeException(e3);
            }
            throw ((RuntimeException) cause2);
        } catch (Exception unused2) {
            Class<?> cls = Class.forName("sun.security.x509.AlgorithmId");
            Method declaredMethod2 = cls.getDeclaredMethod("get", String.class);
            declaredMethod2.setAccessible(true);
            Method declaredMethod3 = cls.getDeclaredMethod("getName", null);
            declaredMethod3.setAccessible(true);
            return (String) declaredMethod3.invoke(declaredMethod2.invoke(null, str), null);
        }
    }

    public static boolean provideTrustManagerByDefault() {
        return false;
    }

    public static boolean serverNamePermitted(SSLParametersImpl sSLParametersImpl, String str) {
        return serverNamePermittedInternal(sSLParametersImpl, str);
    }

    private static boolean serverNamePermittedInternal(SSLParametersImpl sSLParametersImpl, String str) {
        Collection<SNIMatcher> sNIMatchers = sSLParametersImpl.getSNIMatchers();
        if (sNIMatchers == null || sNIMatchers.isEmpty()) {
            return true;
        }
        Iterator<SNIMatcher> it = sNIMatchers.iterator();
        while (it.hasNext()) {
            if (it.next().matches(new SNIHostName(str))) {
                return true;
            }
        }
        return false;
    }

    public static void setCurveName(ECParameterSpec eCParameterSpec, String str) {
        try {
            eCParameterSpec.getClass().getDeclaredMethod("setCurveName", String.class).invoke(eCParameterSpec, str);
        } catch (Exception unused) {
        }
    }

    public static void setEndpointIdentificationAlgorithm(SSLParameters sSLParameters, String str) {
    }

    private static void setParametersSniHostname(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, AbstractConscryptSocket abstractConscryptSocket) {
        if (sSLParametersImpl.getUseSni() && AddressUtils.isValidSniHostname(abstractConscryptSocket.getHostname())) {
            sSLParameters.getClass().getMethod("setServerNames", List.class).invoke(sSLParameters, Collections.singletonList(new SNIHostName(abstractConscryptSocket.getHostname())));
        }
    }

    public static void setSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, AbstractConscryptSocket abstractConscryptSocket) {
        try {
            setSSLParametersOnImpl(sSLParameters, sSLParametersImpl);
            String sniHostnameFromParams = getSniHostnameFromParams(sSLParameters);
            if (sniHostnameFromParams != null) {
                abstractConscryptSocket.setHostname(sniHostnameFromParams);
            }
        } catch (IllegalAccessException | NoSuchMethodException unused) {
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2.getCause());
        }
    }

    private static void setSSLParametersOnImpl(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl) {
        sSLParametersImpl.setEndpointIdentificationAlgorithm((String) sSLParameters.getClass().getMethod("getEndpointIdentificationAlgorithm", null).invoke(sSLParameters, null));
        sSLParametersImpl.setUseCipherSuitesOrder(((Boolean) sSLParameters.getClass().getMethod("getUseCipherSuitesOrder", null).invoke(sSLParameters, null)).booleanValue());
    }

    public static void setSocketWriteTimeout(Socket socket, long j2) {
        try {
            FileDescriptor fileDescriptor = getFileDescriptor(socket);
            if (fileDescriptor != null && fileDescriptor.valid()) {
                Class<?> cls = getClass("android.system.StructTimeval", "libcore.io.StructTimeval");
                if (cls == null) {
                    Log.w(TAG, "StructTimeval == null; not setting socket write timeout");
                    return;
                }
                Method declaredMethod = cls.getDeclaredMethod("fromMillis", Long.TYPE);
                if (declaredMethod == null) {
                    Log.w(TAG, "fromMillis == null; not setting socket write timeout");
                    return;
                }
                Object invoke = declaredMethod.invoke(null, Long.valueOf(j2));
                Field field = Class.forName("libcore.io.Libcore").getField("os");
                if (field == null) {
                    Log.w(TAG, "os == null; not setting socket write timeout");
                    return;
                }
                Object obj = field.get(null);
                if (obj == null) {
                    Log.w(TAG, "instance_os == null; not setting socket write timeout");
                    return;
                }
                Class<?> cls2 = getClass("android.system.OsConstants", "libcore.io.OsConstants");
                if (cls2 == null) {
                    Log.w(TAG, "OsConstants == null; not setting socket write timeout");
                    return;
                }
                Field field2 = cls2.getField("SOL_SOCKET");
                if (field2 == null) {
                    Log.w(TAG, "SOL_SOCKET == null; not setting socket write timeout");
                    return;
                }
                Field field3 = cls2.getField("SO_SNDTIMEO");
                if (field3 == null) {
                    Log.w(TAG, "SO_SNDTIMEO == null; not setting socket write timeout");
                    return;
                }
                Class<?> cls3 = obj.getClass();
                Class cls4 = Integer.TYPE;
                Method method = cls3.getMethod("setsockoptTimeval", FileDescriptor.class, cls4, cls4, cls);
                if (method == null) {
                    Log.w(TAG, "setsockoptTimeval == null; not setting socket write timeout");
                    return;
                } else {
                    method.invoke(obj, fileDescriptor, field2.get(null), field3.get(null), invoke);
                    return;
                }
            }
            throw new SocketException("Socket closed");
        } catch (Exception e2) {
            logStackTraceSnippet("Could not set socket write timeout: " + e2, e2);
            for (Throwable cause = e2.getCause(); cause != null; cause = cause.getCause()) {
                logStackTraceSnippet("Caused by: " + cause, cause);
            }
        }
    }

    public static void setup() {
    }

    public static boolean supportsConscryptCertStore() {
        return false;
    }

    public static boolean supportsX509ExtendedTrustManager() {
        return true;
    }

    public static AlgorithmParameterSpec toGCMParameterSpec(int i2, byte[] bArr) {
        Class<?> cls;
        try {
            cls = Class.forName("javax.crypto.spec.GCMParameterSpec");
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls != null) {
            try {
                return (AlgorithmParameterSpec) cls.getConstructor(Integer.TYPE, byte[].class).newInstance(Integer.valueOf(i2), bArr);
            } catch (IllegalAccessException e2) {
                e = e2;
                logStackTraceSnippet("Can't find GCMParameterSpec class", e);
                return null;
            } catch (IllegalArgumentException e3) {
                e = e3;
                logStackTraceSnippet("Can't find GCMParameterSpec class", e);
                return null;
            } catch (InstantiationException e4) {
                e = e4;
                logStackTraceSnippet("Can't find GCMParameterSpec class", e);
                return null;
            } catch (NoSuchMethodException e5) {
                e = e5;
                logStackTraceSnippet("Can't find GCMParameterSpec class", e);
                return null;
            } catch (InvocationTargetException e6) {
                logStackTraceSnippet("Can't find GCMParameterSpec class", e6.getCause());
            }
        }
        return null;
    }

    public static SSLEngine unwrapEngine(SSLEngine sSLEngine) {
        return sSLEngine;
    }

    public static SSLEngine wrapEngine(ConscryptEngine conscryptEngine) {
        return conscryptEngine;
    }

    public static OpenSSLKey wrapRsaKey(PrivateKey privateKey) {
        return null;
    }

    public static SSLSession wrapSSLSession(ExternalSession externalSession) {
        return new Java8ExtendedSSLSession(externalSession);
    }

    public static SSLSocketFactory wrapSocketFactoryIfNeeded(OpenSSLSocketFactoryImpl openSSLSocketFactoryImpl) {
        return openSSLSocketFactoryImpl;
    }

    public static ConscryptEngineSocket createEngineSocket(String str, int i2, SSLParametersImpl sSLParametersImpl) {
        return new Java8EngineSocket(str, i2, sSLParametersImpl);
    }

    public static ConscryptFileDescriptorSocket createFileDescriptorSocket(String str, int i2, SSLParametersImpl sSLParametersImpl) {
        return new Java8FileDescriptorSocket(str, i2, sSLParametersImpl);
    }

    public static ConscryptEngineSocket createEngineSocket(InetAddress inetAddress, int i2, SSLParametersImpl sSLParametersImpl) {
        return new Java8EngineSocket(inetAddress, i2, sSLParametersImpl);
    }

    public static ConscryptFileDescriptorSocket createFileDescriptorSocket(InetAddress inetAddress, int i2, SSLParametersImpl sSLParametersImpl) {
        return new Java8FileDescriptorSocket(inetAddress, i2, sSLParametersImpl);
    }

    public static ConscryptEngineSocket createEngineSocket(String str, int i2, InetAddress inetAddress, int i3, SSLParametersImpl sSLParametersImpl) {
        return new Java8EngineSocket(str, i2, inetAddress, i3, sSLParametersImpl);
    }

    public static ConscryptFileDescriptorSocket createFileDescriptorSocket(String str, int i2, InetAddress inetAddress, int i3, SSLParametersImpl sSLParametersImpl) {
        return new Java8FileDescriptorSocket(str, i2, inetAddress, i3, sSLParametersImpl);
    }

    public static void getSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, ConscryptEngine conscryptEngine) {
        try {
            getSSLParametersFromImpl(sSLParameters, sSLParametersImpl);
            setParametersSniHostname(sSLParameters, sSLParametersImpl, conscryptEngine);
        } catch (IllegalAccessException | NoSuchMethodException unused) {
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2.getCause());
        }
    }

    public static void checkClientTrusted(X509TrustManager x509TrustManager, X509Certificate[] x509CertificateArr, String str, ConscryptEngine conscryptEngine) {
        if (checkTrusted("checkClientTrusted", x509TrustManager, x509CertificateArr, str, SSLEngine.class, conscryptEngine) || checkTrusted("checkClientTrusted", x509TrustManager, x509CertificateArr, str, String.class, conscryptEngine.getHandshakeSession().getPeerHost())) {
            return;
        }
        x509TrustManager.checkClientTrusted(x509CertificateArr, str);
    }

    public static void checkServerTrusted(X509TrustManager x509TrustManager, X509Certificate[] x509CertificateArr, String str, ConscryptEngine conscryptEngine) {
        if (checkTrusted("checkServerTrusted", x509TrustManager, x509CertificateArr, str, SSLEngine.class, conscryptEngine) || checkTrusted("checkServerTrusted", x509TrustManager, x509CertificateArr, str, String.class, conscryptEngine.getHandshakeSession().getPeerHost())) {
            return;
        }
        x509TrustManager.checkServerTrusted(x509CertificateArr, str);
    }

    public static ConscryptEngineSocket createEngineSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3, SSLParametersImpl sSLParametersImpl) {
        return new Java8EngineSocket(inetAddress, i2, inetAddress2, i3, sSLParametersImpl);
    }

    public static ConscryptFileDescriptorSocket createFileDescriptorSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3, SSLParametersImpl sSLParametersImpl) {
        return new Java8FileDescriptorSocket(inetAddress, i2, inetAddress2, i3, sSLParametersImpl);
    }

    public static void setSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, ConscryptEngine conscryptEngine) {
        try {
            setSSLParametersOnImpl(sSLParameters, sSLParametersImpl);
            String sniHostnameFromParams = getSniHostnameFromParams(sSLParameters);
            if (sniHostnameFromParams != null) {
                conscryptEngine.setHostname(sniHostnameFromParams);
            }
        } catch (IllegalAccessException | NoSuchMethodException unused) {
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2.getCause());
        }
    }

    public static ConscryptEngineSocket createEngineSocket(Socket socket, String str, int i2, boolean z2, SSLParametersImpl sSLParametersImpl) {
        return new Java8EngineSocket(socket, str, i2, z2, sSLParametersImpl);
    }

    public static ConscryptFileDescriptorSocket createFileDescriptorSocket(Socket socket, String str, int i2, boolean z2, SSLParametersImpl sSLParametersImpl) {
        return new Java8FileDescriptorSocket(socket, str, i2, z2, sSLParametersImpl);
    }

    private static void setParametersSniHostname(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, ConscryptEngine conscryptEngine) {
        if (sSLParametersImpl.getUseSni() && AddressUtils.isValidSniHostname(conscryptEngine.getHostname())) {
            sSLParameters.getClass().getMethod("setServerNames", List.class).invoke(sSLParameters, Collections.singletonList(new SNIHostName(conscryptEngine.getHostname())));
        }
    }
}

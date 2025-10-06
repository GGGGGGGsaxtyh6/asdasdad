package L0;

import java.security.PrivilegedAction;

/* loaded from: classes.dex */
public final class a implements PrivilegedAction {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ K0.a f518a;

    public a(K0.a aVar) {
        this.f518a = aVar;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        K0.a aVar = this.f518a;
        aVar.put("KeyFactory.RSA", "sun.security.rsa.RSAKeyFactory$Legacy");
        aVar.put("Alg.Alias.KeyFactory.1.2.840.113549.1.1", "RSA");
        aVar.put("Alg.Alias.KeyFactory.OID.1.2.840.113549.1.1", "RSA");
        aVar.put("KeyPairGenerator.RSA", "sun.security.rsa.RSAKeyPairGenerator$Legacy");
        aVar.put("Alg.Alias.KeyPairGenerator.1.2.840.113549.1.1", "RSA");
        aVar.put("Alg.Alias.KeyPairGenerator.OID.1.2.840.113549.1.1", "RSA");
        aVar.put("Signature.MD2withRSA", "sun.security.rsa.RSASignature$MD2withRSA");
        aVar.put("Alg.Alias.Signature.1.2.840.113549.1.1.2", "MD2withRSA");
        aVar.put("Alg.Alias.Signature.OID.1.2.840.113549.1.1.2", "MD2withRSA");
        aVar.put("Signature.MD5withRSA", "sun.security.rsa.RSASignature$MD5withRSA");
        aVar.put("Alg.Alias.Signature.1.2.840.113549.1.1.4", "MD5withRSA");
        aVar.put("Alg.Alias.Signature.OID.1.2.840.113549.1.1.4", "MD5withRSA");
        aVar.put("Signature.SHA1withRSA", "sun.security.rsa.RSASignature$SHA1withRSA");
        aVar.put("Alg.Alias.Signature.1.2.840.113549.1.1.5", "SHA1withRSA");
        aVar.put("Alg.Alias.Signature.OID.1.2.840.113549.1.1.5", "SHA1withRSA");
        aVar.put("Alg.Alias.Signature.1.3.14.3.2.29", "SHA1withRSA");
        aVar.put("Alg.Alias.Signature.OID.1.3.14.3.2.29", "SHA1withRSA");
        aVar.put("Signature.MD5andSHA1withRSA", "sun.security.ssl.RSASignature");
        aVar.put("Cipher.ChaCha20", "org.openjsse.com.sun.crypto.provider.ChaCha20Cipher$ChaCha20Only");
        aVar.put("Cipher.ChaCha20 SupportedKeyFormats", "RAW");
        aVar.put("Cipher.ChaCha20-Poly1305", "org.openjsse.com.sun.crypto.provider.ChaCha20Cipher$ChaCha20Poly1305");
        aVar.put("Cipher.ChaCha20-Poly1305 SupportedKeyFormats", "RAW");
        aVar.put("Alg.Alias.Cipher.1.2.840.113549.1.9.16.3.18", "ChaCha20-Poly1305");
        aVar.put("Alg.Alias.Cipher.OID.1.2.840.113549.1.9.16.3.18", "ChaCha20-Poly1305");
        aVar.put("KeyGenerator.ChaCha20", "org.openjsse.com.sun.crypto.provider.KeyGeneratorCore$ChaCha20KeyGenerator");
        aVar.put("AlgorithmParameters.ChaCha20-Poly1305", "org.openjsse.com.sun.crypto.provider.ChaCha20Poly1305Parameters");
        aVar.put("KeyManagerFactory.SunX509", "org.openjsse.sun.security.ssl.KeyManagerFactoryImpl$SunX509");
        aVar.put("KeyManagerFactory.NewSunX509", "org.openjsse.sun.security.ssl.KeyManagerFactoryImpl$X509");
        aVar.put("Alg.Alias.KeyManagerFactory.PKIX", "NewSunX509");
        aVar.put("TrustManagerFactory.SunX509", "org.openjsse.sun.security.ssl.TrustManagerFactoryImpl$SimpleFactory");
        aVar.put("TrustManagerFactory.PKIX", "org.openjsse.sun.security.ssl.TrustManagerFactoryImpl$PKIXFactory");
        aVar.put("Alg.Alias.TrustManagerFactory.SunPKIX", "PKIX");
        aVar.put("Alg.Alias.TrustManagerFactory.X509", "PKIX");
        aVar.put("Alg.Alias.TrustManagerFactory.X.509", "PKIX");
        aVar.put("SSLContext.TLSv1", "org.openjsse.sun.security.ssl.SSLContextImpl$TLS10Context");
        aVar.put("SSLContext.TLSv1.1", "org.openjsse.sun.security.ssl.SSLContextImpl$TLS11Context");
        aVar.put("SSLContext.TLSv1.2", "org.openjsse.sun.security.ssl.SSLContextImpl$TLS12Context");
        aVar.put("SSLContext.TLSv1.3", "org.openjsse.sun.security.ssl.SSLContextImpl$TLS13Context");
        aVar.put("SSLContext.TLS", "org.openjsse.sun.security.ssl.SSLContextImpl$TLSContext");
        aVar.put("Alg.Alias.SSLContext.SSL", "TLS");
        aVar.put("Alg.Alias.SSLContext.SSLv3", "TLSv1");
        aVar.put("SSLContext.Default", "org.openjsse.sun.security.ssl.SSLContextImpl$DefaultSSLContext");
        aVar.put("KeyStore.PKCS12", "sun.security.pkcs12.PKCS12KeyStore");
        aVar.put("KeyGenerator.SunTlsPrf", "org.openjsse.com.sun.crypto.provider.TlsPrfGenerator$V10");
        aVar.put("KeyGenerator.SunTls12Prf", "org.openjsse.com.sun.crypto.provider.TlsPrfGenerator$V12");
        aVar.put("KeyGenerator.SunTlsMasterSecret", "org.openjsse.com.sun.crypto.provider.TlsMasterSecretGenerator");
        aVar.put("Alg.Alias.KeyGenerator.SunTls12MasterSecret", "SunTlsMasterSecret");
        aVar.put("Alg.Alias.KeyGenerator.SunTlsExtendedMasterSecret", "SunTlsMasterSecret");
        aVar.put("KeyGenerator.SunTlsKeyMaterial", "org.openjsse.com.sun.crypto.provider.TlsKeyMaterialGenerator");
        aVar.put("Alg.Alias.KeyGenerator.SunTls12KeyMaterial", "SunTlsKeyMaterial");
        aVar.put("KeyGenerator.SunTlsRsaPremasterSecret", "org.openjsse.com.sun.crypto.provider.TlsRsaPremasterSecretGenerator");
        aVar.put("Alg.Alias.KeyGenerator.SunTls12RsaPremasterSecret", "SunTlsRsaPremasterSecret");
        if (b.f519a == 1.8d) {
            aVar.put("MessageDigest.SHA3-224", "org.openjsse.sun.security.provider.SHA3$SHA224");
            aVar.put("MessageDigest.SHA3-256", "org.openjsse.sun.security.provider.SHA3$SHA256");
            aVar.put("MessageDigest.SHA3-384", "org.openjsse.sun.security.provider.SHA3$SHA384");
            aVar.put("MessageDigest.SHA3-512", "org.openjsse.sun.security.provider.SHA3$SHA512");
        }
        aVar.put("Alg.Alias.MessageDigest.2.16.840.1.101.3.4.2.7", "SHA3-224");
        aVar.put("Alg.Alias.MessageDigest.OID.2.16.840.1.101.3.4.2.7", "SHA3-224");
        aVar.put("Alg.Alias.MessageDigest.2.16.840.1.101.3.4.2.8", "SHA3-256");
        aVar.put("Alg.Alias.MessageDigest.OID.2.16.840.1.101.3.4.2.8", "SHA3-256");
        aVar.put("Alg.Alias.MessageDigest.2.16.840.1.101.3.4.2.9", "SHA3-384");
        aVar.put("Alg.Alias.MessageDigest.OID.2.16.840.1.101.3.4.2.9", "SHA3-384");
        aVar.put("Alg.Alias.MessageDigest.2.16.840.1.101.3.4.2.10", "SHA3-512");
        aVar.put("Alg.Alias.MessageDigest.OID.2.16.840.1.101.3.4.2.10", "SHA3-512");
        return null;
    }
}

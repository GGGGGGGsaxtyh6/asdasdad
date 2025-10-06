package org.conscrypt;

import A.e;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import org.conscrypt.EvpMdRef;
import org.conscrypt.NativeRef;

/* loaded from: classes.dex */
public abstract class OpenSSLCipherRSA extends CipherSpi {
    private byte[] buffer;
    private int bufferOffset;
    boolean encrypting;
    private boolean inputTooLarge;
    OpenSSLKey key;
    int padding;
    boolean usingPrivateKey;

    /* loaded from: classes.dex */
    public static abstract class DirectRSA extends OpenSSLCipherRSA {
        public DirectRSA(int i2) {
            super(i2);
        }

        @Override // org.conscrypt.OpenSSLCipherRSA
        public int doCryptoOperation(byte[] bArr, byte[] bArr2) {
            int RSA_public_decrypt;
            if (this.encrypting) {
                if (this.usingPrivateKey) {
                    return NativeCrypto.RSA_private_encrypt(bArr.length, bArr, bArr2, this.key.getNativeRef(), this.padding);
                }
                return NativeCrypto.RSA_public_encrypt(bArr.length, bArr, bArr2, this.key.getNativeRef(), this.padding);
            }
            try {
                if (this.usingPrivateKey) {
                    RSA_public_decrypt = NativeCrypto.RSA_private_decrypt(bArr.length, bArr, bArr2, this.key.getNativeRef(), this.padding);
                } else {
                    RSA_public_decrypt = NativeCrypto.RSA_public_decrypt(bArr.length, bArr, bArr2, this.key.getNativeRef(), this.padding);
                }
                return RSA_public_decrypt;
            } catch (SignatureException e2) {
                IllegalBlockSizeException illegalBlockSizeException = new IllegalBlockSizeException();
                illegalBlockSizeException.initCause(e2);
                throw illegalBlockSizeException;
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class PKCS1 extends DirectRSA {
        public PKCS1() {
            super(1);
        }
    }

    /* loaded from: classes.dex */
    public static final class Raw extends DirectRSA {
        public Raw() {
            super(3);
        }
    }

    public OpenSSLCipherRSA(int i2) {
        this.padding = i2;
    }

    public void doCryptoInit(AlgorithmParameterSpec algorithmParameterSpec) {
    }

    public abstract int doCryptoOperation(byte[] bArr, byte[] bArr2);

    @Override // javax.crypto.CipherSpi
    public byte[] engineDoFinal(byte[] bArr, int i2, int i3) {
        if (bArr != null) {
            engineUpdate(bArr, i2, i3);
        }
        if (!this.inputTooLarge) {
            int i4 = this.bufferOffset;
            byte[] bArr2 = this.buffer;
            if (i4 != bArr2.length) {
                if (this.padding == 3) {
                    byte[] bArr3 = new byte[bArr2.length];
                    System.arraycopy(bArr2, 0, bArr3, bArr2.length - i4, i4);
                    bArr2 = bArr3;
                } else {
                    bArr2 = Arrays.copyOf(bArr2, i4);
                }
            }
            int length = this.buffer.length;
            byte[] bArr4 = new byte[length];
            int doCryptoOperation = doCryptoOperation(bArr2, bArr4);
            if (!this.encrypting && doCryptoOperation != length) {
                bArr4 = Arrays.copyOf(bArr4, doCryptoOperation);
            }
            this.bufferOffset = 0;
            return bArr4;
        }
        throw new IllegalBlockSizeException("input must be under " + this.buffer.length + " bytes");
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetBlockSize() {
        if (this.encrypting) {
            return paddedBlockSizeBytes();
        }
        return keySizeBytes();
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineGetIV() {
        return null;
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetKeySize(Key key) {
        if (key instanceof OpenSSLRSAPrivateKey) {
            return ((OpenSSLRSAPrivateKey) key).getModulus().bitLength();
        }
        if (key instanceof RSAPrivateCrtKey) {
            return ((RSAPrivateCrtKey) key).getModulus().bitLength();
        }
        if (key instanceof RSAPrivateKey) {
            return ((RSAPrivateKey) key).getModulus().bitLength();
        }
        if (key instanceof OpenSSLRSAPublicKey) {
            return ((OpenSSLRSAPublicKey) key).getModulus().bitLength();
        }
        if (key instanceof RSAPublicKey) {
            return ((RSAPublicKey) key).getModulus().bitLength();
        }
        if (key == null) {
            throw new InvalidKeyException("RSA private or public key is null");
        }
        throw new InvalidKeyException("Need RSA private or public key");
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetOutputSize(int i2) {
        if (this.encrypting) {
            return keySizeBytes();
        }
        return paddedBlockSizeBytes();
    }

    @Override // javax.crypto.CipherSpi
    public AlgorithmParameters engineGetParameters() {
        return null;
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i2, Key key, SecureRandom secureRandom) {
        try {
            engineInitInternal(i2, key, null);
        } catch (InvalidAlgorithmParameterException e2) {
            throw new InvalidKeyException("Algorithm parameters rejected when none supplied", e2);
        }
    }

    public void engineInitInternal(int i2, Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        if (i2 != 1 && i2 != 3) {
            if (i2 != 2 && i2 != 4) {
                throw new InvalidParameterException(e.a("Unsupported opmode ", i2));
            }
            this.encrypting = false;
        } else {
            this.encrypting = true;
        }
        if (key instanceof OpenSSLRSAPrivateKey) {
            this.usingPrivateKey = true;
            this.key = ((OpenSSLRSAPrivateKey) key).getOpenSSLKey();
        } else if (key instanceof RSAPrivateCrtKey) {
            this.usingPrivateKey = true;
            this.key = OpenSSLRSAPrivateCrtKey.getInstance((RSAPrivateCrtKey) key);
        } else if (key instanceof RSAPrivateKey) {
            this.usingPrivateKey = true;
            this.key = OpenSSLRSAPrivateKey.getInstance((RSAPrivateKey) key);
        } else if (key instanceof OpenSSLRSAPublicKey) {
            this.usingPrivateKey = false;
            this.key = ((OpenSSLRSAPublicKey) key).getOpenSSLKey();
        } else if (key instanceof RSAPublicKey) {
            this.usingPrivateKey = false;
            this.key = OpenSSLRSAPublicKey.getInstance((RSAPublicKey) key);
        } else {
            if (key == null) {
                throw new InvalidKeyException("RSA private or public key is null");
            }
            throw new InvalidKeyException("Need RSA private or public key");
        }
        this.buffer = new byte[NativeCrypto.RSA_size(this.key.getNativeRef())];
        this.bufferOffset = 0;
        this.inputTooLarge = false;
        doCryptoInit(algorithmParameterSpec);
    }

    @Override // javax.crypto.CipherSpi
    public void engineSetMode(String str) {
        String upperCase = str.toUpperCase(Locale.ROOT);
        if (!"NONE".equals(upperCase) && !"ECB".equals(upperCase)) {
            throw new NoSuchAlgorithmException("mode not supported: ".concat(str));
        }
    }

    @Override // javax.crypto.CipherSpi
    public void engineSetPadding(String str) {
        String upperCase = str.toUpperCase(Locale.ROOT);
        if ("PKCS1PADDING".equals(upperCase)) {
            this.padding = 1;
        } else {
            if ("NOPADDING".equals(upperCase)) {
                this.padding = 3;
                return;
            }
            throw new NoSuchPaddingException("padding not supported: ".concat(str));
        }
    }

    @Override // javax.crypto.CipherSpi
    public Key engineUnwrap(byte[] bArr, String str, int i2) {
        try {
            byte[] engineDoFinal = engineDoFinal(bArr, 0, bArr.length);
            if (i2 == 1) {
                return KeyFactory.getInstance(str).generatePublic(new X509EncodedKeySpec(engineDoFinal));
            }
            if (i2 == 2) {
                return KeyFactory.getInstance(str).generatePrivate(new PKCS8EncodedKeySpec(engineDoFinal));
            }
            if (i2 == 3) {
                return new SecretKeySpec(engineDoFinal, str);
            }
            throw new UnsupportedOperationException("wrappedKeyType == " + i2);
        } catch (InvalidKeySpecException e2) {
            throw new InvalidKeyException(e2);
        } catch (BadPaddingException e3) {
            throw new InvalidKeyException(e3);
        } catch (IllegalBlockSizeException e4) {
            throw new InvalidKeyException(e4);
        }
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineUpdate(byte[] bArr, int i2, int i3) {
        int i4 = this.bufferOffset;
        int i5 = i4 + i3;
        byte[] bArr2 = this.buffer;
        if (i5 > bArr2.length) {
            this.inputTooLarge = true;
            return EmptyArray.BYTE;
        }
        System.arraycopy(bArr, i2, bArr2, i4, i3);
        this.bufferOffset += i3;
        return EmptyArray.BYTE;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineWrap(Key key) {
        try {
            byte[] encoded = key.getEncoded();
            return engineDoFinal(encoded, 0, encoded.length);
        } catch (BadPaddingException e2) {
            IllegalBlockSizeException illegalBlockSizeException = new IllegalBlockSizeException();
            illegalBlockSizeException.initCause(e2);
            throw illegalBlockSizeException;
        }
    }

    public boolean isInitialized() {
        if (this.key != null) {
            return true;
        }
        return false;
    }

    public int keySizeBytes() {
        if (isInitialized()) {
            return NativeCrypto.RSA_size(this.key.getNativeRef());
        }
        throw new IllegalStateException("cipher is not initialized");
    }

    public int paddedBlockSizeBytes() {
        int keySizeBytes = keySizeBytes();
        if (this.padding == 1) {
            return keySizeBytes - 11;
        }
        return keySizeBytes;
    }

    /* loaded from: classes.dex */
    public static class OAEP extends OpenSSLCipherRSA {
        private byte[] label;
        private long mgf1Md;
        private long oaepMd;
        private int oaepMdSizeBytes;
        private NativeRef.EVP_PKEY_CTX pkeyCtx;

        /* loaded from: classes.dex */
        public static final class SHA1 extends OAEP {
            public SHA1() {
                super(EvpMdRef.SHA1.EVP_MD, EvpMdRef.SHA1.SIZE_BYTES);
            }
        }

        /* loaded from: classes.dex */
        public static final class SHA224 extends OAEP {
            public SHA224() {
                super(EvpMdRef.SHA224.EVP_MD, EvpMdRef.SHA224.SIZE_BYTES);
            }
        }

        /* loaded from: classes.dex */
        public static final class SHA256 extends OAEP {
            public SHA256() {
                super(EvpMdRef.SHA256.EVP_MD, EvpMdRef.SHA256.SIZE_BYTES);
            }
        }

        /* loaded from: classes.dex */
        public static final class SHA384 extends OAEP {
            public SHA384() {
                super(EvpMdRef.SHA384.EVP_MD, EvpMdRef.SHA384.SIZE_BYTES);
            }
        }

        /* loaded from: classes.dex */
        public static final class SHA512 extends OAEP {
            public SHA512() {
                super(EvpMdRef.SHA512.EVP_MD, EvpMdRef.SHA512.SIZE_BYTES);
            }
        }

        public OAEP(long j2, int i2) {
            super(4);
            this.mgf1Md = j2;
            this.oaepMd = j2;
            this.oaepMdSizeBytes = i2;
        }

        private void readOAEPParameters(OAEPParameterSpec oAEPParameterSpec) {
            String mGFAlgorithm = oAEPParameterSpec.getMGFAlgorithm();
            Locale locale = Locale.US;
            String upperCase = mGFAlgorithm.toUpperCase(locale);
            AlgorithmParameterSpec mGFParameters = oAEPParameterSpec.getMGFParameters();
            if (("MGF1".equals(upperCase) || "1.2.840.113549.1.1.8".equals(upperCase)) && (mGFParameters instanceof MGF1ParameterSpec)) {
                MGF1ParameterSpec mGF1ParameterSpec = (MGF1ParameterSpec) mGFParameters;
                String upperCase2 = oAEPParameterSpec.getDigestAlgorithm().toUpperCase(locale);
                try {
                    this.oaepMd = EvpMdRef.getEVP_MDByJcaDigestAlgorithmStandardName(upperCase2);
                    this.oaepMdSizeBytes = EvpMdRef.getDigestSizeBytesByJcaDigestAlgorithmStandardName(upperCase2);
                    this.mgf1Md = EvpMdRef.getEVP_MDByJcaDigestAlgorithmStandardName(mGF1ParameterSpec.getDigestAlgorithm());
                    PSource pSource = oAEPParameterSpec.getPSource();
                    if ("PSpecified".equals(pSource.getAlgorithm()) && (pSource instanceof PSource.PSpecified)) {
                        this.label = ((PSource.PSpecified) pSource).getValue();
                        return;
                    }
                    throw new InvalidAlgorithmParameterException("Only PSpecified accepted for PSource");
                } catch (NoSuchAlgorithmException e2) {
                    throw new InvalidAlgorithmParameterException(e2);
                }
            }
            throw new InvalidAlgorithmParameterException("Only MGF1 supported as mask generation function");
        }

        @Override // org.conscrypt.OpenSSLCipherRSA
        public void doCryptoInit(AlgorithmParameterSpec algorithmParameterSpec) {
            long EVP_PKEY_decrypt_init;
            if (this.encrypting) {
                EVP_PKEY_decrypt_init = NativeCrypto.EVP_PKEY_encrypt_init(this.key.getNativeRef());
            } else {
                EVP_PKEY_decrypt_init = NativeCrypto.EVP_PKEY_decrypt_init(this.key.getNativeRef());
            }
            this.pkeyCtx = new NativeRef.EVP_PKEY_CTX(EVP_PKEY_decrypt_init);
            if (algorithmParameterSpec instanceof OAEPParameterSpec) {
                readOAEPParameters((OAEPParameterSpec) algorithmParameterSpec);
            }
            NativeCrypto.EVP_PKEY_CTX_set_rsa_padding(this.pkeyCtx.address, 4);
            NativeCrypto.EVP_PKEY_CTX_set_rsa_oaep_md(this.pkeyCtx.address, this.oaepMd);
            NativeCrypto.EVP_PKEY_CTX_set_rsa_mgf1_md(this.pkeyCtx.address, this.mgf1Md);
            byte[] bArr = this.label;
            if (bArr != null && bArr.length > 0) {
                NativeCrypto.EVP_PKEY_CTX_set_rsa_oaep_label(this.pkeyCtx.address, bArr);
            }
        }

        @Override // org.conscrypt.OpenSSLCipherRSA
        public int doCryptoOperation(byte[] bArr, byte[] bArr2) {
            if (this.encrypting) {
                return NativeCrypto.EVP_PKEY_encrypt(this.pkeyCtx, bArr2, 0, bArr, 0, bArr.length);
            }
            return NativeCrypto.EVP_PKEY_decrypt(this.pkeyCtx, bArr2, 0, bArr, 0, bArr.length);
        }

        @Override // org.conscrypt.OpenSSLCipherRSA, javax.crypto.CipherSpi
        public AlgorithmParameters engineGetParameters() {
            PSource.PSpecified pSpecified;
            if (!isInitialized()) {
                return null;
            }
            try {
                AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("OAEP");
                if (this.label == null) {
                    pSpecified = PSource.PSpecified.DEFAULT;
                } else {
                    pSpecified = new PSource.PSpecified(this.label);
                }
                algorithmParameters.init(new OAEPParameterSpec(EvpMdRef.getJcaDigestAlgorithmStandardNameFromEVP_MD(this.oaepMd), "MGF1", new MGF1ParameterSpec(EvpMdRef.getJcaDigestAlgorithmStandardNameFromEVP_MD(this.mgf1Md)), pSpecified));
                return algorithmParameters;
            } catch (NoSuchAlgorithmException e2) {
                throw ((Error) new AssertionError("OAEP not supported").initCause(e2));
            } catch (InvalidParameterSpecException unused) {
                throw new RuntimeException("No providers of AlgorithmParameters.OAEP available");
            }
        }

        @Override // org.conscrypt.OpenSSLCipherRSA, javax.crypto.CipherSpi
        public void engineInit(int i2, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            if (algorithmParameterSpec != null && !(algorithmParameterSpec instanceof OAEPParameterSpec)) {
                throw new InvalidAlgorithmParameterException("Only OAEPParameterSpec accepted in OAEP mode");
            }
            engineInitInternal(i2, key, algorithmParameterSpec);
        }

        @Override // org.conscrypt.OpenSSLCipherRSA
        public void engineInitInternal(int i2, Key key, AlgorithmParameterSpec algorithmParameterSpec) {
            if (i2 != 1 && i2 != 3) {
                if ((i2 == 2 || i2 == 4) && !(key instanceof PrivateKey)) {
                    throw new InvalidKeyException("Only private keys may be used to decrypt");
                }
            } else if (!(key instanceof PublicKey)) {
                throw new InvalidKeyException("Only public keys may be used to encrypt");
            }
            super.engineInitInternal(i2, key, algorithmParameterSpec);
        }

        @Override // org.conscrypt.OpenSSLCipherRSA, javax.crypto.CipherSpi
        public void engineSetPadding(String str) {
            if (str.toUpperCase(Locale.US).equals("OAEPPADDING")) {
                this.padding = 4;
                return;
            }
            throw new NoSuchPaddingException("Only OAEP padding is supported");
        }

        @Override // org.conscrypt.OpenSSLCipherRSA
        public int paddedBlockSizeBytes() {
            return keySizeBytes() - ((this.oaepMdSizeBytes * 2) + 2);
        }

        @Override // org.conscrypt.OpenSSLCipherRSA, javax.crypto.CipherSpi
        public void engineInit(int i2, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) {
            OAEPParameterSpec oAEPParameterSpec;
            if (algorithmParameters != null) {
                try {
                    oAEPParameterSpec = (OAEPParameterSpec) algorithmParameters.getParameterSpec(OAEPParameterSpec.class);
                } catch (InvalidParameterSpecException e2) {
                    throw new InvalidAlgorithmParameterException("Only OAEP parameters are supported", e2);
                }
            } else {
                oAEPParameterSpec = null;
            }
            engineInitInternal(i2, key, oAEPParameterSpec);
        }
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i2, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (algorithmParameterSpec == null) {
            engineInitInternal(i2, key, algorithmParameterSpec);
            return;
        }
        throw new InvalidAlgorithmParameterException("unknown param type: ".concat(algorithmParameterSpec.getClass().getName()));
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i2, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) {
        if (algorithmParameters == null) {
            engineInitInternal(i2, key, null);
            return;
        }
        throw new InvalidAlgorithmParameterException("unknown param type: ".concat(algorithmParameters.getClass().getName()));
    }

    @Override // javax.crypto.CipherSpi
    public int engineUpdate(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        engineUpdate(bArr, i2, i3);
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    public int engineDoFinal(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        byte[] engineDoFinal = engineDoFinal(bArr, i2, i3);
        int length = engineDoFinal.length + i4;
        if (length <= bArr2.length) {
            System.arraycopy(engineDoFinal, 0, bArr2, i4, engineDoFinal.length);
            return engineDoFinal.length;
        }
        throw new ShortBufferWithoutStackTraceException("output buffer is too small " + bArr2.length + " < " + length);
    }
}

package org.conscrypt;

import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;

/* loaded from: classes.dex */
class OpenSSLRSAPrivateKey implements RSAPrivateKey, OpenSSLKeyHolder {
    private static final long serialVersionUID = 4872170254439578735L;
    transient boolean fetchedParams;
    transient OpenSSLKey key;
    BigInteger modulus;
    BigInteger privateExponent;

    public OpenSSLRSAPrivateKey(OpenSSLKey openSSLKey) {
        this.key = openSSLKey;
    }

    public static OpenSSLRSAPrivateKey getInstance(OpenSSLKey openSSLKey) {
        byte[][] bArr = NativeCrypto.get_RSA_private_params(openSSLKey.getNativeRef());
        if (bArr[1] != null) {
            return new OpenSSLRSAPrivateCrtKey(openSSLKey, bArr);
        }
        return new OpenSSLRSAPrivateKey(openSSLKey, bArr);
    }

    private static OpenSSLKey init(RSAPrivateKeySpec rSAPrivateKeySpec) {
        BigInteger modulus = rSAPrivateKeySpec.getModulus();
        BigInteger privateExponent = rSAPrivateKeySpec.getPrivateExponent();
        if (modulus != null) {
            if (privateExponent != null) {
                try {
                    return new OpenSSLKey(NativeCrypto.EVP_PKEY_new_RSA(modulus.toByteArray(), null, privateExponent.toByteArray(), null, null, null, null, null));
                } catch (Exception e2) {
                    throw new InvalidKeySpecException(e2);
                }
            }
            throw new InvalidKeySpecException("privateExponent == null");
        }
        throw new InvalidKeySpecException("modulus == null");
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.key = new OpenSSLKey(NativeCrypto.EVP_PKEY_new_RSA(this.modulus.toByteArray(), null, this.privateExponent.toByteArray(), null, null, null, null, null));
        this.fetchedParams = true;
    }

    public static OpenSSLKey wrapJCAPrivateKeyForTLSStackOnly(PrivateKey privateKey, PublicKey publicKey) {
        BigInteger bigInteger;
        if (privateKey instanceof RSAKey) {
            bigInteger = ((RSAKey) privateKey).getModulus();
        } else if (publicKey instanceof RSAKey) {
            bigInteger = ((RSAKey) publicKey).getModulus();
        } else {
            bigInteger = null;
        }
        if (bigInteger != null) {
            return new OpenSSLKey(NativeCrypto.getRSAPrivateKeyWrapper(privateKey, bigInteger.toByteArray()), true);
        }
        throw new InvalidKeyException("RSA modulus not available. Private: " + privateKey + ", public: " + publicKey);
    }

    public static OpenSSLKey wrapPlatformKey(RSAPrivateKey rSAPrivateKey) {
        OpenSSLKey wrapRsaKey = Platform.wrapRsaKey(rSAPrivateKey);
        if (wrapRsaKey != null) {
            return wrapRsaKey;
        }
        return new OpenSSLKey(NativeCrypto.getRSAPrivateKeyWrapper(rSAPrivateKey, rSAPrivateKey.getModulus().toByteArray()), true);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        if (!this.key.isHardwareBacked()) {
            ensureReadParams();
            objectOutputStream.defaultWriteObject();
            return;
        }
        throw new NotSerializableException("Hardware backed keys can not be serialized");
    }

    public final synchronized void ensureReadParams() {
        if (this.fetchedParams) {
            return;
        }
        readParams(NativeCrypto.get_RSA_private_params(this.key.getNativeRef()));
        this.fetchedParams = true;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof OpenSSLRSAPrivateKey) {
            return this.key.equals(((OpenSSLRSAPrivateKey) obj).getOpenSSLKey());
        }
        if (!(obj instanceof RSAPrivateKey)) {
            return false;
        }
        ensureReadParams();
        RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) obj;
        if (this.modulus.equals(rSAPrivateKey.getModulus()) && this.privateExponent.equals(rSAPrivateKey.getPrivateExponent())) {
            return true;
        }
        return false;
    }

    @Override // java.security.Key
    public final String getAlgorithm() {
        return "RSA";
    }

    @Override // java.security.Key
    public final byte[] getEncoded() {
        if (this.key.isHardwareBacked()) {
            return null;
        }
        return NativeCrypto.EVP_marshal_private_key(this.key.getNativeRef());
    }

    @Override // java.security.Key
    public final String getFormat() {
        if (this.key.isHardwareBacked()) {
            return null;
        }
        return "PKCS#8";
    }

    @Override // java.security.interfaces.RSAKey
    public final BigInteger getModulus() {
        ensureReadParams();
        return this.modulus;
    }

    @Override // org.conscrypt.OpenSSLKeyHolder
    public OpenSSLKey getOpenSSLKey() {
        return this.key;
    }

    @Override // java.security.interfaces.RSAPrivateKey
    public final BigInteger getPrivateExponent() {
        if (!this.key.isHardwareBacked()) {
            ensureReadParams();
            return this.privateExponent;
        }
        throw new UnsupportedOperationException("Private exponent cannot be extracted");
    }

    public int hashCode() {
        ensureReadParams();
        int hashCode = this.modulus.hashCode() + 3;
        BigInteger bigInteger = this.privateExponent;
        if (bigInteger != null) {
            return (hashCode * 7) + bigInteger.hashCode();
        }
        return hashCode;
    }

    public void readParams(byte[][] bArr) {
        if (bArr[0] != null) {
            if (bArr[2] == null && !this.key.isHardwareBacked()) {
                throw new NullPointerException("privateExponent == null");
            }
            this.modulus = new BigInteger(bArr[0]);
            if (bArr[2] != null) {
                this.privateExponent = new BigInteger(bArr[2]);
                return;
            }
            return;
        }
        throw new NullPointerException("modulus == null");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("OpenSSLRSAPrivateKey{modulus=");
        ensureReadParams();
        sb.append(this.modulus.toString(16));
        return sb.toString();
    }

    public OpenSSLRSAPrivateKey(OpenSSLKey openSSLKey, byte[][] bArr) {
        this(openSSLKey);
        readParams(bArr);
        this.fetchedParams = true;
    }

    public static OpenSSLKey getInstance(RSAPrivateKey rSAPrivateKey) {
        if (rSAPrivateKey.getFormat() == null) {
            return wrapPlatformKey(rSAPrivateKey);
        }
        BigInteger modulus = rSAPrivateKey.getModulus();
        BigInteger privateExponent = rSAPrivateKey.getPrivateExponent();
        if (modulus == null) {
            throw new InvalidKeyException("modulus == null");
        }
        if (privateExponent != null) {
            try {
                return new OpenSSLKey(NativeCrypto.EVP_PKEY_new_RSA(modulus.toByteArray(), null, privateExponent.toByteArray(), null, null, null, null, null));
            } catch (Exception e2) {
                throw new InvalidKeyException(e2);
            }
        }
        throw new InvalidKeyException("privateExponent == null");
    }

    public OpenSSLRSAPrivateKey(RSAPrivateKeySpec rSAPrivateKeySpec) {
        this(init(rSAPrivateKeySpec));
    }
}

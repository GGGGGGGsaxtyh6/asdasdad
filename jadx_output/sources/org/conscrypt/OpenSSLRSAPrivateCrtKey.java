package org.conscrypt;

import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class OpenSSLRSAPrivateCrtKey extends OpenSSLRSAPrivateKey implements RSAPrivateCrtKey {
    private static final long serialVersionUID = 3785291944868707197L;
    private BigInteger crtCoefficient;
    private BigInteger primeExponentP;
    private BigInteger primeExponentQ;
    private BigInteger primeP;
    private BigInteger primeQ;
    private BigInteger publicExponent;

    public OpenSSLRSAPrivateCrtKey(OpenSSLKey openSSLKey) {
        super(openSSLKey);
    }

    public static OpenSSLKey getInstance(RSAPrivateCrtKey rSAPrivateCrtKey) {
        byte[] byteArray;
        byte[] byteArray2;
        byte[] byteArray3;
        byte[] byteArray4;
        byte[] byteArray5;
        if (rSAPrivateCrtKey.getFormat() == null) {
            return OpenSSLRSAPrivateKey.wrapPlatformKey(rSAPrivateCrtKey);
        }
        BigInteger modulus = rSAPrivateCrtKey.getModulus();
        BigInteger privateExponent = rSAPrivateCrtKey.getPrivateExponent();
        if (modulus != null) {
            if (privateExponent != null) {
                try {
                    BigInteger publicExponent = rSAPrivateCrtKey.getPublicExponent();
                    BigInteger primeP = rSAPrivateCrtKey.getPrimeP();
                    BigInteger primeQ = rSAPrivateCrtKey.getPrimeQ();
                    BigInteger primeExponentP = rSAPrivateCrtKey.getPrimeExponentP();
                    BigInteger primeExponentQ = rSAPrivateCrtKey.getPrimeExponentQ();
                    BigInteger crtCoefficient = rSAPrivateCrtKey.getCrtCoefficient();
                    byte[] byteArray6 = modulus.toByteArray();
                    byte[] bArr = null;
                    if (publicExponent == null) {
                        byteArray = null;
                    } else {
                        byteArray = publicExponent.toByteArray();
                    }
                    byte[] byteArray7 = privateExponent.toByteArray();
                    if (primeP == null) {
                        byteArray2 = null;
                    } else {
                        byteArray2 = primeP.toByteArray();
                    }
                    if (primeQ == null) {
                        byteArray3 = null;
                    } else {
                        byteArray3 = primeQ.toByteArray();
                    }
                    if (primeExponentP == null) {
                        byteArray4 = null;
                    } else {
                        byteArray4 = primeExponentP.toByteArray();
                    }
                    if (primeExponentQ == null) {
                        byteArray5 = null;
                    } else {
                        byteArray5 = primeExponentQ.toByteArray();
                    }
                    if (crtCoefficient != null) {
                        bArr = crtCoefficient.toByteArray();
                    }
                    return new OpenSSLKey(NativeCrypto.EVP_PKEY_new_RSA(byteArray6, byteArray, byteArray7, byteArray2, byteArray3, byteArray4, byteArray5, bArr));
                } catch (Exception e2) {
                    throw new InvalidKeyException(e2);
                }
            }
            throw new InvalidKeyException("privateExponent == null");
        }
        throw new InvalidKeyException("modulus == null");
    }

    private static OpenSSLKey init(RSAPrivateCrtKeySpec rSAPrivateCrtKeySpec) {
        byte[] byteArray;
        byte[] byteArray2;
        byte[] byteArray3;
        byte[] byteArray4;
        byte[] byteArray5;
        BigInteger modulus = rSAPrivateCrtKeySpec.getModulus();
        BigInteger privateExponent = rSAPrivateCrtKeySpec.getPrivateExponent();
        if (modulus != null) {
            if (privateExponent != null) {
                try {
                    BigInteger publicExponent = rSAPrivateCrtKeySpec.getPublicExponent();
                    BigInteger primeP = rSAPrivateCrtKeySpec.getPrimeP();
                    BigInteger primeQ = rSAPrivateCrtKeySpec.getPrimeQ();
                    BigInteger primeExponentP = rSAPrivateCrtKeySpec.getPrimeExponentP();
                    BigInteger primeExponentQ = rSAPrivateCrtKeySpec.getPrimeExponentQ();
                    BigInteger crtCoefficient = rSAPrivateCrtKeySpec.getCrtCoefficient();
                    byte[] byteArray6 = modulus.toByteArray();
                    byte[] bArr = null;
                    if (publicExponent == null) {
                        byteArray = null;
                    } else {
                        byteArray = publicExponent.toByteArray();
                    }
                    byte[] byteArray7 = privateExponent.toByteArray();
                    if (primeP == null) {
                        byteArray2 = null;
                    } else {
                        byteArray2 = primeP.toByteArray();
                    }
                    if (primeQ == null) {
                        byteArray3 = null;
                    } else {
                        byteArray3 = primeQ.toByteArray();
                    }
                    if (primeExponentP == null) {
                        byteArray4 = null;
                    } else {
                        byteArray4 = primeExponentP.toByteArray();
                    }
                    if (primeExponentQ == null) {
                        byteArray5 = null;
                    } else {
                        byteArray5 = primeExponentQ.toByteArray();
                    }
                    if (crtCoefficient != null) {
                        bArr = crtCoefficient.toByteArray();
                    }
                    return new OpenSSLKey(NativeCrypto.EVP_PKEY_new_RSA(byteArray6, byteArray, byteArray7, byteArray2, byteArray3, byteArray4, byteArray5, bArr));
                } catch (Exception e2) {
                    throw new InvalidKeySpecException(e2);
                }
            }
            throw new InvalidKeySpecException("privateExponent == null");
        }
        throw new InvalidKeySpecException("modulus == null");
    }

    private void readObject(ObjectInputStream objectInputStream) {
        byte[] byteArray;
        byte[] byteArray2;
        byte[] byteArray3;
        byte[] byteArray4;
        byte[] byteArray5;
        objectInputStream.defaultReadObject();
        byte[] byteArray6 = this.modulus.toByteArray();
        BigInteger bigInteger = this.publicExponent;
        byte[] bArr = null;
        if (bigInteger == null) {
            byteArray = null;
        } else {
            byteArray = bigInteger.toByteArray();
        }
        byte[] byteArray7 = this.privateExponent.toByteArray();
        BigInteger bigInteger2 = this.primeP;
        if (bigInteger2 == null) {
            byteArray2 = null;
        } else {
            byteArray2 = bigInteger2.toByteArray();
        }
        BigInteger bigInteger3 = this.primeQ;
        if (bigInteger3 == null) {
            byteArray3 = null;
        } else {
            byteArray3 = bigInteger3.toByteArray();
        }
        BigInteger bigInteger4 = this.primeExponentP;
        if (bigInteger4 == null) {
            byteArray4 = null;
        } else {
            byteArray4 = bigInteger4.toByteArray();
        }
        BigInteger bigInteger5 = this.primeExponentQ;
        if (bigInteger5 == null) {
            byteArray5 = null;
        } else {
            byteArray5 = bigInteger5.toByteArray();
        }
        BigInteger bigInteger6 = this.crtCoefficient;
        if (bigInteger6 != null) {
            bArr = bigInteger6.toByteArray();
        }
        this.key = new OpenSSLKey(NativeCrypto.EVP_PKEY_new_RSA(byteArray6, byteArray, byteArray7, byteArray2, byteArray3, byteArray4, byteArray5, bArr));
        this.fetchedParams = true;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        if (!getOpenSSLKey().isHardwareBacked()) {
            ensureReadParams();
            objectOutputStream.defaultWriteObject();
            return;
        }
        throw new NotSerializableException("Hardware backed keys cannot be serialized");
    }

    @Override // org.conscrypt.OpenSSLRSAPrivateKey
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof OpenSSLRSAPrivateKey) {
            return getOpenSSLKey().equals(((OpenSSLRSAPrivateKey) obj).getOpenSSLKey());
        }
        if (obj instanceof RSAPrivateCrtKey) {
            ensureReadParams();
            RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) obj;
            if (getOpenSSLKey().isHardwareBacked()) {
                if (getModulus().equals(rSAPrivateCrtKey.getModulus()) && this.publicExponent.equals(rSAPrivateCrtKey.getPublicExponent())) {
                    return true;
                }
                return false;
            }
            if (getModulus().equals(rSAPrivateCrtKey.getModulus()) && this.publicExponent.equals(rSAPrivateCrtKey.getPublicExponent()) && getPrivateExponent().equals(rSAPrivateCrtKey.getPrivateExponent()) && this.primeP.equals(rSAPrivateCrtKey.getPrimeP()) && this.primeQ.equals(rSAPrivateCrtKey.getPrimeQ()) && this.primeExponentP.equals(rSAPrivateCrtKey.getPrimeExponentP()) && this.primeExponentQ.equals(rSAPrivateCrtKey.getPrimeExponentQ()) && this.crtCoefficient.equals(rSAPrivateCrtKey.getCrtCoefficient())) {
                return true;
            }
            return false;
        }
        if (!(obj instanceof RSAPrivateKey)) {
            return false;
        }
        ensureReadParams();
        RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) obj;
        if (getOpenSSLKey().isHardwareBacked()) {
            return getModulus().equals(rSAPrivateKey.getModulus());
        }
        if (getModulus().equals(rSAPrivateKey.getModulus()) && getPrivateExponent().equals(rSAPrivateKey.getPrivateExponent())) {
            return true;
        }
        return false;
    }

    @Override // java.security.interfaces.RSAPrivateCrtKey
    public BigInteger getCrtCoefficient() {
        ensureReadParams();
        return this.crtCoefficient;
    }

    @Override // java.security.interfaces.RSAPrivateCrtKey
    public BigInteger getPrimeExponentP() {
        ensureReadParams();
        return this.primeExponentP;
    }

    @Override // java.security.interfaces.RSAPrivateCrtKey
    public BigInteger getPrimeExponentQ() {
        ensureReadParams();
        return this.primeExponentQ;
    }

    @Override // java.security.interfaces.RSAPrivateCrtKey
    public BigInteger getPrimeP() {
        ensureReadParams();
        return this.primeP;
    }

    @Override // java.security.interfaces.RSAPrivateCrtKey
    public BigInteger getPrimeQ() {
        ensureReadParams();
        return this.primeQ;
    }

    @Override // java.security.interfaces.RSAPrivateCrtKey
    public BigInteger getPublicExponent() {
        ensureReadParams();
        return this.publicExponent;
    }

    @Override // org.conscrypt.OpenSSLRSAPrivateKey
    public final int hashCode() {
        int hashCode = super.hashCode();
        BigInteger bigInteger = this.publicExponent;
        if (bigInteger != null) {
            return hashCode ^ bigInteger.hashCode();
        }
        return hashCode;
    }

    @Override // org.conscrypt.OpenSSLRSAPrivateKey
    public synchronized void readParams(byte[][] bArr) {
        try {
            super.readParams(bArr);
            if (bArr[1] != null) {
                this.publicExponent = new BigInteger(bArr[1]);
            }
            if (bArr[3] != null) {
                this.primeP = new BigInteger(bArr[3]);
            }
            if (bArr[4] != null) {
                this.primeQ = new BigInteger(bArr[4]);
            }
            if (bArr[5] != null) {
                this.primeExponentP = new BigInteger(bArr[5]);
            }
            if (bArr[6] != null) {
                this.primeExponentQ = new BigInteger(bArr[6]);
            }
            if (bArr[7] != null) {
                this.crtCoefficient = new BigInteger(bArr[7]);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // org.conscrypt.OpenSSLRSAPrivateKey
    public String toString() {
        StringBuilder sb = new StringBuilder("OpenSSLRSAPrivateCrtKey{modulus=");
        ensureReadParams();
        sb.append(getModulus().toString(16));
        if (this.publicExponent != null) {
            sb.append(",publicExponent=");
            sb.append(this.publicExponent.toString(16));
        }
        sb.append('}');
        return sb.toString();
    }

    public OpenSSLRSAPrivateCrtKey(OpenSSLKey openSSLKey, byte[][] bArr) {
        super(openSSLKey, bArr);
    }

    public OpenSSLRSAPrivateCrtKey(RSAPrivateCrtKeySpec rSAPrivateCrtKeySpec) {
        super(init(rSAPrivateCrtKeySpec));
    }
}

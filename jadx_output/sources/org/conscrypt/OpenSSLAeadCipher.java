package org.conscrypt;

import A.e;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import org.conscrypt.OpenSSLCipher;

/* loaded from: classes.dex */
public abstract class OpenSSLAeadCipher extends OpenSSLCipher {
    static final int DEFAULT_TAG_SIZE_BITS = 128;
    private static final boolean ENABLE_BYTEBUFFER_OPTIMIZATIONS = true;
    private static int lastGlobalMessageSize = 32;
    private byte[] aad;
    byte[] buf;
    int bufCount;
    long evpAead;
    private boolean mustInitialize;
    private byte[] previousIv;
    private byte[] previousKey;
    int tagLengthInBytes;

    public OpenSSLAeadCipher(OpenSSLCipher.Mode mode) {
        super(mode, OpenSSLCipher.Padding.NOPADDING);
    }

    private boolean arraysAreEqual(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i2 |= bArr[i3] ^ bArr2[i3];
        }
        if (i2 != 0) {
            return false;
        }
        return ENABLE_BYTEBUFFER_OPTIMIZATIONS;
    }

    private void checkInitialization() {
        if (!this.mustInitialize) {
        } else {
            throw new IllegalStateException("Cannot re-use same key and IV for multiple encryptions");
        }
    }

    private void expand(int i2) {
        int i3 = this.bufCount;
        int i4 = i3 + i2;
        byte[] bArr = this.buf;
        if (i4 <= bArr.length) {
            return;
        }
        byte[] bArr2 = new byte[(i2 + i3) * 2];
        System.arraycopy(bArr, 0, bArr2, 0, i3);
        this.buf = bArr2;
    }

    private void reset() {
        this.aad = null;
        int i2 = lastGlobalMessageSize;
        byte[] bArr = this.buf;
        if (bArr == null) {
            this.buf = new byte[i2];
        } else {
            int i3 = this.bufCount;
            if (i3 > 0 && i3 != i2) {
                lastGlobalMessageSize = i3;
                if (bArr.length != i3) {
                    this.buf = new byte[i3];
                }
            }
        }
        this.bufCount = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0036 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void throwAEADBadTagExceptionIfAvailable(java.lang.String r3, java.lang.Throwable r4) {
        /*
            r2 = this;
            java.lang.String r0 = "javax.crypto.AEADBadTagException"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.Exception -> L38
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            java.lang.Class[] r1 = new java.lang.Class[]{r1}     // Catch: java.lang.Exception -> L38
            java.lang.reflect.Constructor r0 = r0.getConstructor(r1)     // Catch: java.lang.Exception -> L38
            r1 = 0
            java.lang.Object[] r3 = new java.lang.Object[]{r3}     // Catch: java.lang.reflect.InvocationTargetException -> L1f java.lang.Throwable -> L33
            java.lang.Object r3 = r0.newInstance(r3)     // Catch: java.lang.reflect.InvocationTargetException -> L1f java.lang.Throwable -> L33
            javax.crypto.BadPaddingException r3 = (javax.crypto.BadPaddingException) r3     // Catch: java.lang.reflect.InvocationTargetException -> L1f java.lang.Throwable -> L33
            r3.initCause(r4)     // Catch: java.lang.reflect.InvocationTargetException -> L1f java.lang.Throwable -> L21
            goto L34
        L1f:
            r3 = move-exception
            goto L23
        L21:
            r1 = r3
            goto L33
        L23:
            javax.crypto.BadPaddingException r4 = new javax.crypto.BadPaddingException
            r4.<init>()
            java.lang.Throwable r3 = r3.getTargetException()
            java.lang.Throwable r3 = r4.initCause(r3)
            javax.crypto.BadPaddingException r3 = (javax.crypto.BadPaddingException) r3
            throw r3
        L33:
            r3 = r1
        L34:
            if (r3 != 0) goto L37
            return
        L37:
            throw r3
        L38:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.conscrypt.OpenSSLAeadCipher.throwAEADBadTagExceptionIfAvailable(java.lang.String, java.lang.Throwable):void");
    }

    public boolean allowsNonceReuse() {
        return false;
    }

    @Override // org.conscrypt.OpenSSLCipher
    public void checkSupportedPadding(OpenSSLCipher.Padding padding) {
        if (padding == OpenSSLCipher.Padding.NOPADDING) {
        } else {
            throw new NoSuchPaddingException("Must be NoPadding for AEAD ciphers");
        }
    }

    public void checkSupportedTagLength(int i2) {
        if (i2 % 8 == 0) {
        } else {
            throw new InvalidAlgorithmParameterException(e.a("Tag length must be a multiple of 8; was ", i2));
        }
    }

    public int doFinalInternal(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int EVP_AEAD_CTX_open_buf;
        checkInitialization();
        try {
            if (isEncrypting()) {
                EVP_AEAD_CTX_open_buf = NativeCrypto.EVP_AEAD_CTX_seal_buf(this.evpAead, this.encodedKey, this.tagLengthInBytes, byteBuffer2, this.iv, byteBuffer, this.aad);
            } else {
                EVP_AEAD_CTX_open_buf = NativeCrypto.EVP_AEAD_CTX_open_buf(this.evpAead, this.encodedKey, this.tagLengthInBytes, byteBuffer2, this.iv, byteBuffer, this.aad);
            }
            if (isEncrypting()) {
                this.mustInitialize = ENABLE_BYTEBUFFER_OPTIMIZATIONS;
            }
            return EVP_AEAD_CTX_open_buf;
        } catch (BadPaddingException e2) {
            throwAEADBadTagExceptionIfAvailable(e2.getMessage(), e2.getCause());
            throw e2;
        }
    }

    @Override // javax.crypto.CipherSpi
    public int engineDoFinal(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        if (byteBuffer != null && byteBuffer2 != null) {
            if (getOutputSizeForFinal(byteBuffer.remaining()) <= byteBuffer2.remaining()) {
                if (!byteBuffer2.isReadOnly()) {
                    if (this.bufCount != 0) {
                        return super.engineDoFinal(byteBuffer, byteBuffer2);
                    }
                    if (!byteBuffer.isDirect()) {
                        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(byteBuffer.remaining());
                        allocateDirect.mark();
                        allocateDirect.put(byteBuffer);
                        allocateDirect.reset();
                        byteBuffer = allocateDirect;
                    }
                    if (!byteBuffer2.isDirect()) {
                        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(getOutputSizeForFinal(byteBuffer.remaining()));
                        int doFinalInternal = doFinalInternal(byteBuffer, allocateDirect2);
                        byteBuffer2.put(allocateDirect2);
                        byteBuffer.position(byteBuffer.limit());
                        return doFinalInternal;
                    }
                    int doFinalInternal2 = doFinalInternal(byteBuffer, byteBuffer2);
                    byteBuffer2.position(byteBuffer2.position() + doFinalInternal2);
                    byteBuffer.position(byteBuffer.limit());
                    return doFinalInternal2;
                }
                throw new IllegalArgumentException("Cannot write to Read Only ByteBuffer");
            }
            throw new ShortBufferWithoutStackTraceException("Insufficient Bytes for Output Buffer");
        }
        throw new NullPointerException("Null ByteBuffer Error");
    }

    @Override // org.conscrypt.OpenSSLCipher
    public void engineInitInternal(byte[] bArr, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        byte[] bArr2 = null;
        int i2 = 128;
        if (algorithmParameterSpec != null) {
            GCMParameters fromGCMParameterSpec = Platform.fromGCMParameterSpec(algorithmParameterSpec);
            if (fromGCMParameterSpec != null) {
                bArr2 = fromGCMParameterSpec.getIV();
                i2 = fromGCMParameterSpec.getTLen();
            } else if (algorithmParameterSpec instanceof IvParameterSpec) {
                bArr2 = ((IvParameterSpec) algorithmParameterSpec).getIV();
            }
        }
        checkSupportedTagLength(i2);
        this.tagLengthInBytes = i2 / 8;
        boolean isEncrypting = isEncrypting();
        long evp_aead = getEVP_AEAD(bArr.length);
        this.evpAead = evp_aead;
        int EVP_AEAD_nonce_length = NativeCrypto.EVP_AEAD_nonce_length(evp_aead);
        if (bArr2 == null && EVP_AEAD_nonce_length != 0) {
            if (isEncrypting) {
                bArr2 = new byte[EVP_AEAD_nonce_length];
                if (secureRandom != null) {
                    secureRandom.nextBytes(bArr2);
                } else {
                    NativeCrypto.RAND_bytes(bArr2);
                }
            } else {
                throw new InvalidAlgorithmParameterException("IV must be specified in " + this.mode + " mode");
            }
        } else {
            if (EVP_AEAD_nonce_length == 0 && bArr2 != null) {
                throw new InvalidAlgorithmParameterException("IV not used in " + this.mode + " mode");
            }
            if (bArr2 != null && bArr2.length != EVP_AEAD_nonce_length) {
                throw new InvalidAlgorithmParameterException("Expected IV length of " + EVP_AEAD_nonce_length + " but was " + bArr2.length);
            }
        }
        if (isEncrypting() && bArr2 != null && !allowsNonceReuse()) {
            byte[] bArr3 = this.previousKey;
            if (bArr3 != null && this.previousIv != null && arraysAreEqual(bArr3, bArr) && arraysAreEqual(this.previousIv, bArr2)) {
                this.mustInitialize = ENABLE_BYTEBUFFER_OPTIMIZATIONS;
                throw new InvalidAlgorithmParameterException("When using AEAD key and IV must not be re-used");
            }
            this.previousKey = bArr;
            this.previousIv = bArr2;
        }
        this.mustInitialize = false;
        this.iv = bArr2;
        reset();
    }

    @Override // javax.crypto.CipherSpi
    public void engineUpdateAAD(byte[] bArr, int i2, int i3) {
        checkInitialization();
        byte[] bArr2 = this.aad;
        if (bArr2 == null) {
            this.aad = Arrays.copyOfRange(bArr, i2, i3 + i2);
            return;
        }
        byte[] bArr3 = new byte[bArr2.length + i3];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, i2, bArr3, this.aad.length, i3);
        this.aad = bArr3;
    }

    public abstract long getEVP_AEAD(int i2);

    @Override // org.conscrypt.OpenSSLCipher
    public int getOutputSizeForFinal(int i2) {
        int i3;
        int i4 = this.bufCount + i2;
        if (isEncrypting()) {
            i3 = NativeCrypto.EVP_AEAD_max_overhead(this.evpAead);
        } else {
            i3 = 0;
        }
        return i4 + i3;
    }

    @Override // org.conscrypt.OpenSSLCipher
    public int getOutputSizeForUpdate(int i2) {
        return 0;
    }

    @Override // org.conscrypt.OpenSSLCipher
    public int updateInternal(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5) {
        checkInitialization();
        if (this.buf != null) {
            ArrayUtils.checkOffsetAndCount(bArr.length, i2, i3);
            if (i3 > 0) {
                expand(i3);
                System.arraycopy(bArr, i2, this.buf, this.bufCount, i3);
                this.bufCount += i3;
                return 0;
            }
            return 0;
        }
        throw new IllegalStateException("Cipher not initialized");
    }

    @Override // org.conscrypt.OpenSSLCipher
    public int doFinalInternal(byte[] bArr, int i2, int i3) {
        int EVP_AEAD_CTX_open;
        checkInitialization();
        try {
            if (isEncrypting()) {
                EVP_AEAD_CTX_open = NativeCrypto.EVP_AEAD_CTX_seal(this.evpAead, this.encodedKey, this.tagLengthInBytes, bArr, i2, this.iv, this.buf, 0, this.bufCount, this.aad);
            } else {
                EVP_AEAD_CTX_open = NativeCrypto.EVP_AEAD_CTX_open(this.evpAead, this.encodedKey, this.tagLengthInBytes, bArr, i2, this.iv, this.buf, 0, this.bufCount, this.aad);
            }
            if (isEncrypting()) {
                this.mustInitialize = ENABLE_BYTEBUFFER_OPTIMIZATIONS;
            }
            reset();
            return EVP_AEAD_CTX_open;
        } catch (BadPaddingException e2) {
            throwAEADBadTagExceptionIfAvailable(e2.getMessage(), e2.getCause());
            throw e2;
        }
    }

    @Override // javax.crypto.CipherSpi
    public void engineUpdateAAD(ByteBuffer byteBuffer) {
        checkInitialization();
        byte[] bArr = this.aad;
        if (bArr == null) {
            byte[] bArr2 = new byte[byteBuffer.remaining()];
            this.aad = bArr2;
            byteBuffer.get(bArr2);
        } else {
            byte[] bArr3 = new byte[byteBuffer.remaining() + bArr.length];
            byte[] bArr4 = this.aad;
            System.arraycopy(bArr4, 0, bArr3, 0, bArr4.length);
            byteBuffer.get(bArr3, this.aad.length, byteBuffer.remaining());
            this.aad = bArr3;
        }
    }

    @Override // org.conscrypt.OpenSSLCipher, javax.crypto.CipherSpi
    public int engineDoFinal(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (bArr2 != null && getOutputSizeForFinal(i3) > bArr2.length - i4) {
            throw new ShortBufferWithoutStackTraceException("Insufficient output space");
        }
        return super.engineDoFinal(bArr, i2, i3, bArr2, i4);
    }
}

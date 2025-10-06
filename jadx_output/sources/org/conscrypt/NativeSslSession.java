package org.conscrypt;

import A.e;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import org.conscrypt.NativeRef;
import org.conscrypt.SSLUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class NativeSslSession {
    private static final Logger logger = Logger.getLogger(NativeSslSession.class.getName());

    /* loaded from: classes.dex */
    public static final class Impl extends NativeSslSession {
        private final String cipherSuite;
        private final AbstractSessionContext context;
        private final String host;
        private final X509Certificate[] peerCertificates;
        private final byte[] peerOcspStapledResponse;
        private final byte[] peerSignedCertificateTimestamp;
        private final int port;
        private final String protocol;
        private final NativeRef.SSL_SESSION ref;

        /* JADX INFO: Access modifiers changed from: private */
        public long getCreationTime() {
            return NativeCrypto.SSL_SESSION_get_time(this.ref.address);
        }

        @Override // org.conscrypt.NativeSslSession
        public String getCipherSuite() {
            return this.cipherSuite;
        }

        @Override // org.conscrypt.NativeSslSession
        public byte[] getId() {
            return NativeCrypto.SSL_SESSION_session_id(this.ref.address);
        }

        @Override // org.conscrypt.NativeSslSession
        public String getPeerHost() {
            return this.host;
        }

        @Override // org.conscrypt.NativeSslSession
        public byte[] getPeerOcspStapledResponse() {
            return this.peerOcspStapledResponse;
        }

        @Override // org.conscrypt.NativeSslSession
        public int getPeerPort() {
            return this.port;
        }

        @Override // org.conscrypt.NativeSslSession
        public byte[] getPeerSignedCertificateTimestamp() {
            return this.peerSignedCertificateTimestamp;
        }

        @Override // org.conscrypt.NativeSslSession
        public String getProtocol() {
            return this.protocol;
        }

        @Override // org.conscrypt.NativeSslSession
        public boolean isSingleUse() {
            return NativeCrypto.SSL_SESSION_should_be_single_use(this.ref.address);
        }

        @Override // org.conscrypt.NativeSslSession
        public boolean isValid() {
            if (System.currentTimeMillis() - (Math.max(0L, Math.min(this.context.getSessionTimeout(), NativeCrypto.SSL_SESSION_get_timeout(this.ref.address))) * 1000) < getCreationTime()) {
                return true;
            }
            return false;
        }

        @Override // org.conscrypt.NativeSslSession
        public void offerToResume(NativeSsl nativeSsl) {
            nativeSsl.offerToResumeSession(this.ref.address);
        }

        @Override // org.conscrypt.NativeSslSession
        public byte[] toBytes() {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeInt(SSLUtils.SessionType.OPEN_SSL_WITH_TLS_SCT.value);
                byte[] i2d_SSL_SESSION = NativeCrypto.i2d_SSL_SESSION(this.ref.address);
                dataOutputStream.writeInt(i2d_SSL_SESSION.length);
                dataOutputStream.write(i2d_SSL_SESSION);
                dataOutputStream.writeInt(this.peerCertificates.length);
                for (X509Certificate x509Certificate : this.peerCertificates) {
                    byte[] encoded = x509Certificate.getEncoded();
                    dataOutputStream.writeInt(encoded.length);
                    dataOutputStream.write(encoded);
                }
                if (this.peerOcspStapledResponse != null) {
                    dataOutputStream.writeInt(1);
                    dataOutputStream.writeInt(this.peerOcspStapledResponse.length);
                    dataOutputStream.write(this.peerOcspStapledResponse);
                } else {
                    dataOutputStream.writeInt(0);
                }
                byte[] bArr = this.peerSignedCertificateTimestamp;
                if (bArr != null) {
                    dataOutputStream.writeInt(bArr.length);
                    dataOutputStream.write(this.peerSignedCertificateTimestamp);
                } else {
                    dataOutputStream.writeInt(0);
                }
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e2) {
                NativeSslSession.logger.log(Level.WARNING, "Failed to convert saved SSL Session: ", (Throwable) e2);
                return null;
            } catch (CertificateEncodingException e3) {
                NativeSslSession.log(e3);
                return null;
            }
        }

        @Override // org.conscrypt.NativeSslSession
        public SSLSession toSSLSession() {
            return new SSLSession() { // from class: org.conscrypt.NativeSslSession.Impl.1
                @Override // javax.net.ssl.SSLSession
                public int getApplicationBufferSize() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public String getCipherSuite() {
                    return Impl.this.getCipherSuite();
                }

                @Override // javax.net.ssl.SSLSession
                public long getCreationTime() {
                    return Impl.this.getCreationTime();
                }

                @Override // javax.net.ssl.SSLSession
                public byte[] getId() {
                    return Impl.this.getId();
                }

                @Override // javax.net.ssl.SSLSession
                public long getLastAccessedTime() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public Certificate[] getLocalCertificates() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public Principal getLocalPrincipal() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public int getPacketBufferSize() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public javax.security.cert.X509Certificate[] getPeerCertificateChain() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public Certificate[] getPeerCertificates() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public String getPeerHost() {
                    return Impl.this.getPeerHost();
                }

                @Override // javax.net.ssl.SSLSession
                public int getPeerPort() {
                    return Impl.this.getPeerPort();
                }

                @Override // javax.net.ssl.SSLSession
                public Principal getPeerPrincipal() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public String getProtocol() {
                    return Impl.this.getProtocol();
                }

                @Override // javax.net.ssl.SSLSession
                public SSLSessionContext getSessionContext() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public Object getValue(String str) {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public String[] getValueNames() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public void invalidate() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public boolean isValid() {
                    return Impl.this.isValid();
                }

                @Override // javax.net.ssl.SSLSession
                public void putValue(String str, Object obj) {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public void removeValue(String str) {
                    throw new UnsupportedOperationException();
                }
            };
        }

        private Impl(AbstractSessionContext abstractSessionContext, NativeRef.SSL_SESSION ssl_session, String str, int i2, X509Certificate[] x509CertificateArr, byte[] bArr, byte[] bArr2) {
            this.context = abstractSessionContext;
            this.host = str;
            this.port = i2;
            this.peerCertificates = x509CertificateArr;
            this.peerOcspStapledResponse = bArr;
            this.peerSignedCertificateTimestamp = bArr2;
            this.protocol = NativeCrypto.SSL_SESSION_get_version(ssl_session.address);
            this.cipherSuite = NativeCrypto.cipherSuiteToJava(NativeCrypto.SSL_SESSION_cipher(ssl_session.address));
            this.ref = ssl_session;
        }
    }

    private static void checkRemaining(ByteBuffer byteBuffer, int i2) {
        if (i2 >= 0) {
            if (i2 <= byteBuffer.remaining()) {
                return;
            }
            throw new IOException("Length of blob is longer than available: " + i2 + " > " + byteBuffer.remaining());
        }
        throw new IOException(e.a("Length is negative: ", i2));
    }

    private static byte[] getOcspResponse(ConscryptSession conscryptSession) {
        List<byte[]> statusResponses = conscryptSession.getStatusResponses();
        if (statusResponses.size() >= 1) {
            return statusResponses.get(0);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(Throwable th) {
        String name;
        Logger logger2 = logger;
        Level level = Level.INFO;
        if (th.getMessage() != null) {
            name = th.getMessage();
        } else {
            name = th.getClass().getName();
        }
        logger2.log(level, "Error inflating SSL session: {0}", name);
    }

    public static NativeSslSession newInstance(NativeRef.SSL_SESSION ssl_session, ConscryptSession conscryptSession) {
        AbstractSessionContext abstractSessionContext = (AbstractSessionContext) conscryptSession.getSessionContext();
        if (abstractSessionContext instanceof ClientSessionContext) {
            return new Impl(abstractSessionContext, ssl_session, conscryptSession.getPeerHost(), conscryptSession.getPeerPort(), conscryptSession.getPeerCertificates(), getOcspResponse(conscryptSession), conscryptSession.getPeerSignedCertificateTimestamp());
        }
        return new Impl(abstractSessionContext, ssl_session, null, -1, null, null, null);
    }

    public abstract String getCipherSuite();

    public abstract byte[] getId();

    public abstract String getPeerHost();

    public abstract byte[] getPeerOcspStapledResponse();

    public abstract int getPeerPort();

    public abstract byte[] getPeerSignedCertificateTimestamp();

    public abstract String getProtocol();

    public abstract boolean isSingleUse();

    public abstract boolean isValid();

    public abstract void offerToResume(NativeSsl nativeSsl);

    public abstract byte[] toBytes();

    public abstract SSLSession toSSLSession();

    /* JADX WARN: Removed duplicated region for block: B:27:0x009c A[Catch: BufferUnderflowException -> 0x005d, IOException -> 0x0060, TryCatch #3 {IOException -> 0x0060, BufferUnderflowException -> 0x005d, blocks: (B:3:0x0007, B:5:0x0011, B:7:0x0029, B:12:0x003e, B:13:0x005c, B:16:0x0063, B:18:0x0069, B:20:0x0073, B:22:0x0081, B:25:0x0096, B:27:0x009c, B:29:0x00a5, B:30:0x00ad, B:32:0x00b3, B:34:0x00be, B:38:0x00d4, B:39:0x00e5, B:9:0x0035), top: B:2:0x0007, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b3 A[Catch: BufferUnderflowException -> 0x005d, IOException -> 0x0060, TryCatch #3 {IOException -> 0x0060, BufferUnderflowException -> 0x005d, blocks: (B:3:0x0007, B:5:0x0011, B:7:0x0029, B:12:0x003e, B:13:0x005c, B:16:0x0063, B:18:0x0069, B:20:0x0073, B:22:0x0081, B:25:0x0096, B:27:0x009c, B:29:0x00a5, B:30:0x00ad, B:32:0x00b3, B:34:0x00be, B:38:0x00d4, B:39:0x00e5, B:9:0x0035), top: B:2:0x0007, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00be A[Catch: BufferUnderflowException -> 0x005d, IOException -> 0x0060, TryCatch #3 {IOException -> 0x0060, BufferUnderflowException -> 0x005d, blocks: (B:3:0x0007, B:5:0x0011, B:7:0x0029, B:12:0x003e, B:13:0x005c, B:16:0x0063, B:18:0x0069, B:20:0x0073, B:22:0x0081, B:25:0x0096, B:27:0x009c, B:29:0x00a5, B:30:0x00ad, B:32:0x00b3, B:34:0x00be, B:38:0x00d4, B:39:0x00e5, B:9:0x0035), top: B:2:0x0007, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.conscrypt.NativeSslSession newInstance(org.conscrypt.AbstractSessionContext r14, byte[] r15, java.lang.String r16, int r17) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.conscrypt.NativeSslSession.newInstance(org.conscrypt.AbstractSessionContext, byte[], java.lang.String, int):org.conscrypt.NativeSslSession");
    }
}

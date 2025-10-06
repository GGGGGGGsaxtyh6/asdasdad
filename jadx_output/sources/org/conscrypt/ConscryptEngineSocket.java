package org.conscrypt;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import org.conscrypt.SSLParametersImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ConscryptEngineSocket extends OpenSSLSocketImpl implements SSLParametersImpl.AliasChooser {
    private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocate(0);
    private BufferAllocator bufferAllocator;
    private final ConscryptEngine engine;
    private final Object handshakeLock;
    private SSLInputStream in;
    private SSLOutputStream out;
    private int state;
    private final Object stateLock;

    /* renamed from: org.conscrypt.ConscryptEngineSocket$3, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus;
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$Status;

        static {
            int[] iArr = new int[SSLEngineResult.Status.values().length];
            $SwitchMap$javax$net$ssl$SSLEngineResult$Status = iArr;
            try {
                iArr[SSLEngineResult.Status.BUFFER_UNDERFLOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[SSLEngineResult.Status.OK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[SSLEngineResult.Status.CLOSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[SSLEngineResult.HandshakeStatus.values().length];
            $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus = iArr2;
            try {
                iArr2[SSLEngineResult.HandshakeStatus.NEED_UNWRAP.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_WRAP.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_TASK.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.FINISHED.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public ConscryptEngineSocket(SSLParametersImpl sSLParametersImpl) {
        this.stateLock = new Object();
        this.handshakeLock = new Object();
        this.bufferAllocator = ConscryptEngine.getDefaultBufferAllocator();
        this.state = 0;
        this.engine = newEngine(sSLParametersImpl, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHandshake() {
        boolean z2 = false;
        while (!z2) {
            try {
                int i2 = AnonymousClass3.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[this.engine.getHandshakeStatus().ordinal()];
                if (i2 != 1) {
                    if (i2 == 2) {
                        this.out.writeInternal(EMPTY_BUFFER);
                        this.out.flushInternal();
                    } else if (i2 != 3) {
                        if (i2 != 4 && i2 != 5) {
                            throw new IllegalStateException("Unknown handshake status: " + this.engine.getHandshakeStatus());
                        }
                        z2 = true;
                    } else {
                        throw new IllegalStateException("Engine tasks are unsupported");
                    }
                } else if (this.in.processDataFromSocket(EmptyArray.BYTE, 0, 0) < 0) {
                    throw SSLUtils.toSSLHandshakeException(new EOFException("connection closed"));
                }
            } catch (SSLException e2) {
                drainOutgoingQueue();
                close();
                throw e2;
            } catch (IOException e3) {
                close();
                throw e3;
            } catch (Exception e4) {
                close();
                throw SSLUtils.toSSLHandshakeException(e4);
            }
        }
    }

    private void drainOutgoingQueue() {
        while (this.engine.pendingOutboundEncryptedBytes() > 0) {
            try {
                this.out.writeInternal(EMPTY_BUFFER);
                this.out.flushInternal();
            } catch (IOException unused) {
                return;
            }
        }
    }

    private static X509TrustManager getDelegatingTrustManager(X509TrustManager x509TrustManager, final ConscryptEngineSocket conscryptEngineSocket) {
        if (x509TrustManager instanceof X509ExtendedTrustManager) {
            final X509ExtendedTrustManager x509ExtendedTrustManager = (X509ExtendedTrustManager) x509TrustManager;
            return new X509ExtendedTrustManager() { // from class: org.conscrypt.ConscryptEngineSocket.2
                @Override // javax.net.ssl.X509ExtendedTrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) {
                    throw new AssertionError("Should not be called");
                }

                @Override // javax.net.ssl.X509ExtendedTrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) {
                    throw new AssertionError("Should not be called");
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return x509ExtendedTrustManager.getAcceptedIssuers();
                }

                @Override // javax.net.ssl.X509ExtendedTrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) {
                    x509ExtendedTrustManager.checkClientTrusted(x509CertificateArr, str, conscryptEngineSocket);
                }

                @Override // javax.net.ssl.X509ExtendedTrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) {
                    x509ExtendedTrustManager.checkServerTrusted(x509CertificateArr, str, conscryptEngineSocket);
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
                    x509ExtendedTrustManager.checkClientTrusted(x509CertificateArr, str);
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
                    x509ExtendedTrustManager.checkServerTrusted(x509CertificateArr, str);
                }
            };
        }
        return x509TrustManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputStream getUnderlyingInputStream() {
        return super.getInputStream();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OutputStream getUnderlyingOutputStream() {
        return super.getOutputStream();
    }

    private static ConscryptEngine newEngine(SSLParametersImpl sSLParametersImpl, ConscryptEngineSocket conscryptEngineSocket) {
        SSLParametersImpl sSLParametersImpl2;
        if (Platform.supportsX509ExtendedTrustManager()) {
            sSLParametersImpl2 = sSLParametersImpl.cloneWithTrustManager(getDelegatingTrustManager(sSLParametersImpl.getX509TrustManager(), conscryptEngineSocket));
        } else {
            sSLParametersImpl2 = sSLParametersImpl;
        }
        ConscryptEngine conscryptEngine = new ConscryptEngine(sSLParametersImpl2, conscryptEngineSocket.peerInfoProvider(), conscryptEngineSocket);
        conscryptEngine.setHandshakeListener(new HandshakeListener() { // from class: org.conscrypt.ConscryptEngineSocket.1
            @Override // org.conscrypt.HandshakeListener
            public void onHandshakeFinished() {
                ConscryptEngineSocket.this.onHandshakeFinished();
            }
        });
        conscryptEngine.setUseClientMode(sSLParametersImpl.getUseClientMode());
        return conscryptEngine;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHandshakeFinished() {
        boolean z2;
        synchronized (this.stateLock) {
            try {
                int i2 = this.state;
                if (i2 != 8) {
                    if (i2 == 2) {
                        this.state = 4;
                    } else if (i2 == 3) {
                        this.state = 5;
                    }
                    this.stateLock.notifyAll();
                    z2 = true;
                } else {
                    z2 = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z2) {
            notifyHandshakeCompletedListeners();
        }
    }

    private void waitForHandshake() {
        startHandshake();
        synchronized (this.stateLock) {
            while (true) {
                int i2 = this.state;
                if (i2 == 5 || i2 == 4 || i2 == 8) {
                    break;
                }
                try {
                    this.stateLock.wait();
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Interrupted waiting for handshake", e2);
                }
            }
            throw new SocketException("Socket is closed");
        }
    }

    @Override // org.conscrypt.SSLParametersImpl.AliasChooser
    public final String chooseClientAlias(X509KeyManager x509KeyManager, X500Principal[] x500PrincipalArr, String[] strArr) {
        return x509KeyManager.chooseClientAlias(strArr, x500PrincipalArr, this);
    }

    @Override // org.conscrypt.SSLParametersImpl.AliasChooser
    public final String chooseServerAlias(X509KeyManager x509KeyManager, String str) {
        return x509KeyManager.chooseServerAlias(str, null, this);
    }

    @Override // org.conscrypt.OpenSSLSocketImpl, org.conscrypt.AbstractConscryptSocket, java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Object obj = this.stateLock;
        if (obj == null) {
            return;
        }
        synchronized (obj) {
            try {
                int i2 = this.state;
                if (i2 == 8) {
                    return;
                }
                this.state = 8;
                this.stateLock.notifyAll();
                try {
                    this.engine.closeInbound();
                    this.engine.closeOutbound();
                    if (i2 >= 2) {
                        drainOutgoingQueue();
                        this.engine.closeOutbound();
                    }
                    try {
                        super.close();
                        SSLInputStream sSLInputStream = this.in;
                        if (sSLInputStream != null) {
                            sSLInputStream.release();
                        }
                    } finally {
                    }
                } catch (Throwable th) {
                    try {
                        super.close();
                        SSLInputStream sSLInputStream2 = this.in;
                        if (sSLInputStream2 != null) {
                            sSLInputStream2.release();
                        }
                        throw th;
                    } finally {
                    }
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Override // org.conscrypt.AbstractConscryptSocket
    public byte[] exportKeyingMaterial(String str, byte[] bArr, int i2) {
        return this.engine.exportKeyingMaterial(str, bArr, i2);
    }

    @Override // org.conscrypt.AbstractConscryptSocket
    public final SSLSession getActiveSession() {
        return this.engine.getSession();
    }

    @Override // org.conscrypt.AbstractConscryptSocket, javax.net.ssl.SSLSocket
    public final String getApplicationProtocol() {
        return this.engine.getApplicationProtocol();
    }

    @Override // org.conscrypt.AbstractConscryptSocket
    public final String[] getApplicationProtocols() {
        return this.engine.getApplicationProtocols();
    }

    @Override // org.conscrypt.OpenSSLSocketImpl, org.conscrypt.AbstractConscryptSocket
    public final byte[] getChannelId() {
        return this.engine.getChannelId();
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getEnableSessionCreation() {
        return this.engine.getEnableSessionCreation();
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getEnabledCipherSuites() {
        return this.engine.getEnabledCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getEnabledProtocols() {
        return this.engine.getEnabledProtocols();
    }

    @Override // org.conscrypt.AbstractConscryptSocket, javax.net.ssl.SSLSocket
    public final String getHandshakeApplicationProtocol() {
        return this.engine.getHandshakeApplicationProtocol();
    }

    @Override // org.conscrypt.OpenSSLSocketImpl, org.conscrypt.AbstractConscryptSocket, javax.net.ssl.SSLSocket
    public final SSLSession getHandshakeSession() {
        return this.engine.handshakeSession();
    }

    @Override // org.conscrypt.OpenSSLSocketImpl, org.conscrypt.AbstractConscryptSocket, java.net.Socket
    public final InputStream getInputStream() {
        checkOpen();
        waitForHandshake();
        return this.in;
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getNeedClientAuth() {
        return this.engine.getNeedClientAuth();
    }

    @Override // org.conscrypt.OpenSSLSocketImpl, org.conscrypt.AbstractConscryptSocket, java.net.Socket
    public final OutputStream getOutputStream() {
        checkOpen();
        waitForHandshake();
        return this.out;
    }

    @Override // javax.net.ssl.SSLSocket
    public final SSLParameters getSSLParameters() {
        return this.engine.getSSLParameters();
    }

    @Override // javax.net.ssl.SSLSocket
    public final SSLSession getSession() {
        if (isConnected()) {
            try {
                waitForHandshake();
            } catch (IOException unused) {
            }
        }
        return this.engine.getSession();
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getSupportedCipherSuites() {
        return this.engine.getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getSupportedProtocols() {
        return this.engine.getSupportedProtocols();
    }

    @Override // org.conscrypt.AbstractConscryptSocket
    public byte[] getTlsUnique() {
        return this.engine.getTlsUnique();
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getUseClientMode() {
        return this.engine.getUseClientMode();
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getWantClientAuth() {
        return this.engine.getWantClientAuth();
    }

    @Override // org.conscrypt.AbstractConscryptSocket
    public final void setApplicationProtocolSelector(ApplicationProtocolSelector applicationProtocolSelector) {
        setApplicationProtocolSelector(applicationProtocolSelector == null ? null : new ApplicationProtocolSelectorAdapter(this, applicationProtocolSelector));
    }

    @Override // org.conscrypt.AbstractConscryptSocket
    public final void setApplicationProtocols(String[] strArr) {
        this.engine.setApplicationProtocols(strArr);
    }

    public void setBufferAllocator(BufferAllocator bufferAllocator) {
        this.engine.setBufferAllocator(bufferAllocator);
        this.bufferAllocator = bufferAllocator;
    }

    @Override // org.conscrypt.OpenSSLSocketImpl, org.conscrypt.AbstractConscryptSocket
    public final void setChannelIdEnabled(boolean z2) {
        this.engine.setChannelIdEnabled(z2);
    }

    @Override // org.conscrypt.OpenSSLSocketImpl, org.conscrypt.AbstractConscryptSocket
    public final void setChannelIdPrivateKey(PrivateKey privateKey) {
        this.engine.setChannelIdPrivateKey(privateKey);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setEnableSessionCreation(boolean z2) {
        this.engine.setEnableSessionCreation(z2);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setEnabledCipherSuites(String[] strArr) {
        this.engine.setEnabledCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setEnabledProtocols(String[] strArr) {
        this.engine.setEnabledProtocols(strArr);
    }

    @Override // org.conscrypt.OpenSSLSocketImpl, org.conscrypt.AbstractConscryptSocket
    public void setHandshakeTimeout(int i2) {
    }

    @Override // org.conscrypt.OpenSSLSocketImpl, org.conscrypt.AbstractConscryptSocket
    public final void setHostname(String str) {
        this.engine.setHostname(str);
        super.setHostname(str);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setNeedClientAuth(boolean z2) {
        this.engine.setNeedClientAuth(z2);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setSSLParameters(SSLParameters sSLParameters) {
        this.engine.setSSLParameters(sSLParameters);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setUseClientMode(boolean z2) {
        this.engine.setUseClientMode(z2);
    }

    @Override // org.conscrypt.OpenSSLSocketImpl, org.conscrypt.AbstractConscryptSocket
    public final void setUseSessionTickets(boolean z2) {
        this.engine.setUseSessionTickets(z2);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setWantClientAuth(boolean z2) {
        this.engine.setWantClientAuth(z2);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void startHandshake() {
        checkOpen();
        try {
            synchronized (this.handshakeLock) {
                synchronized (this.stateLock) {
                    if (this.state == 0) {
                        this.state = 2;
                        this.engine.beginHandshake();
                        this.in = new SSLInputStream();
                        this.out = new SSLOutputStream();
                        doHandshake();
                    }
                }
            }
        } catch (SSLException e2) {
            close();
            throw e2;
        } catch (IOException e3) {
            close();
            throw e3;
        } catch (Exception e4) {
            close();
            throw SSLUtils.toSSLHandshakeException(e4);
        }
    }

    @Override // org.conscrypt.AbstractConscryptSocket
    public final void setApplicationProtocolSelector(ApplicationProtocolSelectorAdapter applicationProtocolSelectorAdapter) {
        this.engine.setApplicationProtocolSelector(applicationProtocolSelectorAdapter);
    }

    /* loaded from: classes.dex */
    public final class SSLOutputStream extends OutputStream {
        private OutputStream socketOutputStream;
        private final ByteBuffer target;
        private final int targetArrayOffset;
        private final Object writeLock = new Object();

        public SSLOutputStream() {
            ByteBuffer allocate = ByteBuffer.allocate(ConscryptEngineSocket.this.engine.getSession().getPacketBufferSize());
            this.target = allocate;
            this.targetArrayOffset = allocate.arrayOffset();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void flushInternal() {
            ConscryptEngineSocket.this.checkOpen();
            init();
            this.socketOutputStream.flush();
        }

        private void init() {
            if (this.socketOutputStream == null) {
                this.socketOutputStream = ConscryptEngineSocket.this.getUnderlyingOutputStream();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void writeInternal(ByteBuffer byteBuffer) {
            Platform.blockGuardOnNetwork();
            ConscryptEngineSocket.this.checkOpen();
            init();
            int remaining = byteBuffer.remaining();
            do {
                this.target.clear();
                SSLEngineResult wrap = ConscryptEngineSocket.this.engine.wrap(byteBuffer, this.target);
                if (wrap.getStatus() != SSLEngineResult.Status.OK && wrap.getStatus() != SSLEngineResult.Status.CLOSED) {
                    throw new SSLException("Unexpected engine result " + wrap.getStatus());
                }
                if (this.target.position() == wrap.bytesProduced()) {
                    remaining -= wrap.bytesConsumed();
                    if (remaining == byteBuffer.remaining()) {
                        if (wrap.getStatus() == SSLEngineResult.Status.CLOSED && wrap.bytesProduced() == 0) {
                            if (remaining > 0) {
                                throw new SocketException("Socket closed");
                            }
                            return;
                        } else {
                            this.target.flip();
                            writeToSocket();
                        }
                    } else {
                        throw new SSLException("Engine did not read the correct number of bytes");
                    }
                } else {
                    throw new SSLException("Engine bytesProduced " + wrap.bytesProduced() + " does not match bytes written " + this.target.position());
                }
            } while (remaining > 0);
        }

        private void writeToSocket() {
            this.socketOutputStream.write(this.target.array(), this.targetArrayOffset, this.target.limit());
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            ConscryptEngineSocket.this.close();
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() {
            ConscryptEngineSocket.this.startHandshake();
            synchronized (this.writeLock) {
                flushInternal();
            }
        }

        @Override // java.io.OutputStream
        public void write(int i2) {
            ConscryptEngineSocket.this.startHandshake();
            synchronized (this.writeLock) {
                write(new byte[]{(byte) i2});
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) {
            ConscryptEngineSocket.this.startHandshake();
            synchronized (this.writeLock) {
                writeInternal(ByteBuffer.wrap(bArr));
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            ConscryptEngineSocket.this.startHandshake();
            synchronized (this.writeLock) {
                writeInternal(ByteBuffer.wrap(bArr, i2, i3));
            }
        }
    }

    /* loaded from: classes.dex */
    public final class SSLInputStream extends InputStream {
        private final AllocatedBuffer allocatedBuffer;
        private final ByteBuffer fromEngine;
        private final ByteBuffer fromSocket;
        private final int fromSocketArrayOffset;
        private final Object readLock = new Object();
        private final byte[] singleByte = new byte[1];
        private InputStream socketInputStream;

        public SSLInputStream() {
            if (ConscryptEngineSocket.this.bufferAllocator != null) {
                AllocatedBuffer allocateDirectBuffer = ConscryptEngineSocket.this.bufferAllocator.allocateDirectBuffer(ConscryptEngineSocket.this.engine.getSession().getApplicationBufferSize());
                this.allocatedBuffer = allocateDirectBuffer;
                this.fromEngine = allocateDirectBuffer.nioBuffer();
            } else {
                this.allocatedBuffer = null;
                this.fromEngine = ByteBuffer.allocateDirect(ConscryptEngineSocket.this.engine.getSession().getApplicationBufferSize());
            }
            this.fromEngine.flip();
            ByteBuffer allocate = ByteBuffer.allocate(ConscryptEngineSocket.this.engine.getSession().getPacketBufferSize());
            this.fromSocket = allocate;
            this.fromSocketArrayOffset = allocate.arrayOffset();
        }

        private void init() {
            if (this.socketInputStream == null) {
                this.socketInputStream = ConscryptEngineSocket.this.getUnderlyingInputStream();
            }
        }

        private boolean isHandshakeFinished() {
            boolean z2;
            synchronized (ConscryptEngineSocket.this.stateLock) {
                if (ConscryptEngineSocket.this.state >= 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
            }
            return z2;
        }

        private boolean isHandshaking(SSLEngineResult.HandshakeStatus handshakeStatus) {
            int i2 = AnonymousClass3.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[handshakeStatus.ordinal()];
            if (i2 == 1 || i2 == 2 || i2 == 3) {
                return true;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x009f, code lost:
        
            if (r1.bytesProduced() == 0) goto L26;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int processDataFromSocket(byte[] r7, int r8, int r9) {
            /*
                r6 = this;
                org.conscrypt.Platform.blockGuardOnNetwork()
                org.conscrypt.ConscryptEngineSocket r0 = org.conscrypt.ConscryptEngineSocket.this
                r0.checkOpen()
                r6.init()
            Lb:
                java.nio.ByteBuffer r0 = r6.fromEngine
                int r0 = r0.remaining()
                if (r0 <= 0) goto L23
                java.nio.ByteBuffer r0 = r6.fromEngine
                int r0 = r0.remaining()
                int r9 = java.lang.Math.min(r0, r9)
                java.nio.ByteBuffer r0 = r6.fromEngine
                r0.get(r7, r8, r9)
                return r9
            L23:
                java.nio.ByteBuffer r0 = r6.fromSocket
                r0.flip()
                java.nio.ByteBuffer r0 = r6.fromEngine
                r0.clear()
                org.conscrypt.ConscryptEngineSocket r0 = org.conscrypt.ConscryptEngineSocket.this
                org.conscrypt.ConscryptEngine r0 = org.conscrypt.ConscryptEngineSocket.access$400(r0)
                javax.net.ssl.SSLEngineResult$HandshakeStatus r0 = r0.getHandshakeStatus()
                boolean r0 = r6.isHandshaking(r0)
                org.conscrypt.ConscryptEngineSocket r1 = org.conscrypt.ConscryptEngineSocket.this
                org.conscrypt.ConscryptEngine r1 = org.conscrypt.ConscryptEngineSocket.access$400(r1)
                java.nio.ByteBuffer r2 = r6.fromSocket
                java.nio.ByteBuffer r3 = r6.fromEngine
                javax.net.ssl.SSLEngineResult r1 = r1.unwrap(r2, r3)
                java.nio.ByteBuffer r2 = r6.fromSocket
                r2.compact()
                java.nio.ByteBuffer r2 = r6.fromEngine
                r2.flip()
                int[] r2 = org.conscrypt.ConscryptEngineSocket.AnonymousClass3.$SwitchMap$javax$net$ssl$SSLEngineResult$Status
                javax.net.ssl.SSLEngineResult$Status r3 = r1.getStatus()
                int r3 = r3.ordinal()
                r2 = r2[r3]
                r3 = 1
                r4 = -1
                r5 = 0
                if (r2 == r3) goto L9b
                r3 = 2
                if (r2 == r3) goto L83
                r7 = 3
                if (r2 != r7) goto L6b
                return r4
            L6b:
                javax.net.ssl.SSLException r7 = new javax.net.ssl.SSLException
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                java.lang.String r9 = "Unexpected engine result "
                r8.<init>(r9)
                javax.net.ssl.SSLEngineResult$Status r9 = r1.getStatus()
                r8.append(r9)
                java.lang.String r8 = r8.toString()
                r7.<init>(r8)
                throw r7
            L83:
                if (r0 != 0) goto L99
                javax.net.ssl.SSLEngineResult$HandshakeStatus r0 = r1.getHandshakeStatus()
                boolean r0 = r6.isHandshaking(r0)
                if (r0 == 0) goto L99
                boolean r0 = r6.isHandshakeFinished()
                if (r0 == 0) goto L99
                r6.renegotiate()
                return r5
            L99:
                r3 = r5
                goto La1
            L9b:
                int r0 = r1.bytesProduced()
                if (r0 != 0) goto L99
            La1:
                if (r3 != 0) goto Laa
                int r0 = r1.bytesProduced()
                if (r0 != 0) goto Laa
                return r5
            Laa:
                if (r3 == 0) goto Lb
                int r0 = r6.readFromSocket()
                if (r0 != r4) goto Lb
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: org.conscrypt.ConscryptEngineSocket.SSLInputStream.processDataFromSocket(byte[], int, int):int");
        }

        private int readFromSocket() {
            try {
                int position = this.fromSocket.position();
                int read = this.socketInputStream.read(this.fromSocket.array(), this.fromSocketArrayOffset + position, this.fromSocket.limit() - position);
                if (read > 0) {
                    this.fromSocket.position(position + read);
                }
                return read;
            } catch (EOFException unused) {
                return -1;
            }
        }

        private int readUntilDataAvailable(byte[] bArr, int i2, int i3) {
            int processDataFromSocket;
            do {
                processDataFromSocket = processDataFromSocket(bArr, i2, i3);
            } while (processDataFromSocket == 0);
            return processDataFromSocket;
        }

        private void renegotiate() {
            synchronized (ConscryptEngineSocket.this.handshakeLock) {
                ConscryptEngineSocket.this.doHandshake();
            }
        }

        @Override // java.io.InputStream
        public int available() {
            int remaining;
            ConscryptEngineSocket.this.startHandshake();
            synchronized (this.readLock) {
                init();
                remaining = this.fromEngine.remaining();
            }
            return remaining;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            ConscryptEngineSocket.this.close();
        }

        @Override // java.io.InputStream
        public int read() {
            ConscryptEngineSocket.this.startHandshake();
            synchronized (this.readLock) {
                try {
                    int read = read(this.singleByte, 0, 1);
                    if (read == -1) {
                        return -1;
                    }
                    if (read != 1) {
                        throw new SSLException("read incorrect number of bytes " + read);
                    }
                    return this.singleByte[0] & 255;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void release() {
            synchronized (this.readLock) {
                try {
                    AllocatedBuffer allocatedBuffer = this.allocatedBuffer;
                    if (allocatedBuffer != null) {
                        allocatedBuffer.release();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) {
            int read;
            ConscryptEngineSocket.this.startHandshake();
            synchronized (this.readLock) {
                read = read(bArr, 0, bArr.length);
            }
            return read;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            int readUntilDataAvailable;
            ConscryptEngineSocket.this.startHandshake();
            synchronized (this.readLock) {
                readUntilDataAvailable = readUntilDataAvailable(bArr, i2, i3);
            }
            return readUntilDataAvailable;
        }
    }

    public ConscryptEngineSocket(String str, int i2, SSLParametersImpl sSLParametersImpl) {
        super(str, i2);
        this.stateLock = new Object();
        this.handshakeLock = new Object();
        this.bufferAllocator = ConscryptEngine.getDefaultBufferAllocator();
        this.state = 0;
        this.engine = newEngine(sSLParametersImpl, this);
    }

    public ConscryptEngineSocket(InetAddress inetAddress, int i2, SSLParametersImpl sSLParametersImpl) {
        super(inetAddress, i2);
        this.stateLock = new Object();
        this.handshakeLock = new Object();
        this.bufferAllocator = ConscryptEngine.getDefaultBufferAllocator();
        this.state = 0;
        this.engine = newEngine(sSLParametersImpl, this);
    }

    public ConscryptEngineSocket(String str, int i2, InetAddress inetAddress, int i3, SSLParametersImpl sSLParametersImpl) {
        super(str, i2, inetAddress, i3);
        this.stateLock = new Object();
        this.handshakeLock = new Object();
        this.bufferAllocator = ConscryptEngine.getDefaultBufferAllocator();
        this.state = 0;
        this.engine = newEngine(sSLParametersImpl, this);
    }

    public ConscryptEngineSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3, SSLParametersImpl sSLParametersImpl) {
        super(inetAddress, i2, inetAddress2, i3);
        this.stateLock = new Object();
        this.handshakeLock = new Object();
        this.bufferAllocator = ConscryptEngine.getDefaultBufferAllocator();
        this.state = 0;
        this.engine = newEngine(sSLParametersImpl, this);
    }

    public ConscryptEngineSocket(Socket socket, String str, int i2, boolean z2, SSLParametersImpl sSLParametersImpl) {
        super(socket, str, i2, z2);
        this.stateLock = new Object();
        this.handshakeLock = new Object();
        this.bufferAllocator = ConscryptEngine.getDefaultBufferAllocator();
        this.state = 0;
        this.engine = newEngine(sSLParametersImpl, this);
    }
}

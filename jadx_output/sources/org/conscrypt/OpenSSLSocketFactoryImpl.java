package org.conscrypt;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.KeyManagementException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes.dex */
final class OpenSSLSocketFactoryImpl extends SSLSocketFactory {
    private static boolean useEngineSocketByDefault = SSLUtils.USE_ENGINE_SOCKET_BY_DEFAULT;
    private final IOException instantiationException;
    private final SSLParametersImpl sslParameters;
    private boolean useEngineSocket;

    public OpenSSLSocketFactoryImpl() {
        IOException iOException;
        this.useEngineSocket = useEngineSocketByDefault;
        SSLParametersImpl sSLParametersImpl = null;
        try {
            iOException = null;
            sSLParametersImpl = SSLParametersImpl.getDefault();
        } catch (KeyManagementException e2) {
            iOException = new IOException("Delayed instantiation exception:", e2);
        }
        this.sslParameters = sSLParametersImpl;
        this.instantiationException = iOException;
    }

    private boolean hasFileDescriptor(Socket socket) {
        try {
            Platform.getFileDescriptor(socket);
            return true;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    public static void setUseEngineSocketByDefault(boolean z2) {
        useEngineSocketByDefault = z2;
        SocketFactory socketFactory = SSLSocketFactory.getDefault();
        if (socketFactory instanceof OpenSSLSocketFactoryImpl) {
            ((OpenSSLSocketFactoryImpl) socketFactory).setUseEngineSocket(z2);
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() {
        IOException iOException = this.instantiationException;
        if (iOException == null) {
            if (this.useEngineSocket) {
                return Platform.createEngineSocket((SSLParametersImpl) this.sslParameters.clone());
            }
            return Platform.createFileDescriptorSocket((SSLParametersImpl) this.sslParameters.clone());
        }
        throw iOException;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.sslParameters.getEnabledCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return NativeCrypto.getSupportedCipherSuites();
    }

    public void setUseEngineSocket(boolean z2) {
        this.useEngineSocket = z2;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2) {
        if (this.useEngineSocket) {
            return Platform.createEngineSocket(str, i2, (SSLParametersImpl) this.sslParameters.clone());
        }
        return Platform.createFileDescriptorSocket(str, i2, (SSLParametersImpl) this.sslParameters.clone());
    }

    public OpenSSLSocketFactoryImpl(SSLParametersImpl sSLParametersImpl) {
        this.useEngineSocket = useEngineSocketByDefault;
        this.sslParameters = sSLParametersImpl;
        this.instantiationException = null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2, InetAddress inetAddress, int i3) {
        if (this.useEngineSocket) {
            return Platform.createEngineSocket(str, i2, inetAddress, i3, (SSLParametersImpl) this.sslParameters.clone());
        }
        return Platform.createFileDescriptorSocket(str, i2, inetAddress, i3, (SSLParametersImpl) this.sslParameters.clone());
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2) {
        if (this.useEngineSocket) {
            return Platform.createEngineSocket(inetAddress, i2, (SSLParametersImpl) this.sslParameters.clone());
        }
        return Platform.createFileDescriptorSocket(inetAddress, i2, (SSLParametersImpl) this.sslParameters.clone());
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) {
        if (this.useEngineSocket) {
            return Platform.createEngineSocket(inetAddress, i2, inetAddress2, i3, (SSLParametersImpl) this.sslParameters.clone());
        }
        return Platform.createFileDescriptorSocket(inetAddress, i2, inetAddress2, i3, (SSLParametersImpl) this.sslParameters.clone());
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i2, boolean z2) {
        Preconditions.checkNotNull(socket, "socket");
        if (socket.isConnected()) {
            if (!this.useEngineSocket && hasFileDescriptor(socket)) {
                return Platform.createFileDescriptorSocket(socket, str, i2, z2, (SSLParametersImpl) this.sslParameters.clone());
            }
            return Platform.createEngineSocket(socket, str, i2, z2, (SSLParametersImpl) this.sslParameters.clone());
        }
        throw new SocketException("Socket is not connected.");
    }
}

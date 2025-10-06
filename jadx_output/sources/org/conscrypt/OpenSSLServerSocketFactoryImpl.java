package org.conscrypt;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.security.KeyManagementException;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;

/* loaded from: classes.dex */
final class OpenSSLServerSocketFactoryImpl extends SSLServerSocketFactory {
    private static boolean useEngineSocketByDefault = SSLUtils.USE_ENGINE_SOCKET_BY_DEFAULT;
    private IOException instantiationException;
    private SSLParametersImpl sslParameters;
    private boolean useEngineSocket = useEngineSocketByDefault;

    public OpenSSLServerSocketFactoryImpl() {
        try {
            SSLParametersImpl sSLParametersImpl = SSLParametersImpl.getDefault();
            this.sslParameters = sSLParametersImpl;
            sSLParametersImpl.setUseClientMode(false);
        } catch (KeyManagementException e2) {
            IOException iOException = new IOException("Delayed instantiation exception:");
            this.instantiationException = iOException;
            iOException.initCause(e2);
        }
    }

    public static void setUseEngineSocketByDefault(boolean z2) {
        useEngineSocketByDefault = z2;
        ServerSocketFactory serverSocketFactory = SSLServerSocketFactory.getDefault();
        if (serverSocketFactory instanceof OpenSSLServerSocketFactoryImpl) {
            ((OpenSSLServerSocketFactoryImpl) serverSocketFactory).setUseEngineSocket(z2);
        }
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket() {
        return new ConscryptServerSocket((SSLParametersImpl) this.sslParameters.clone()).setUseEngineSocket(this.useEngineSocket);
    }

    @Override // javax.net.ssl.SSLServerSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.sslParameters.getEnabledCipherSuites();
    }

    @Override // javax.net.ssl.SSLServerSocketFactory
    public String[] getSupportedCipherSuites() {
        return NativeCrypto.getSupportedCipherSuites();
    }

    public void setUseEngineSocket(boolean z2) {
        this.useEngineSocket = z2;
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket(int i2) {
        return new ConscryptServerSocket(i2, (SSLParametersImpl) this.sslParameters.clone()).setUseEngineSocket(this.useEngineSocket);
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket(int i2, int i3) {
        return new ConscryptServerSocket(i2, i3, (SSLParametersImpl) this.sslParameters.clone()).setUseEngineSocket(this.useEngineSocket);
    }

    public OpenSSLServerSocketFactoryImpl(SSLParametersImpl sSLParametersImpl) {
        SSLParametersImpl sSLParametersImpl2 = (SSLParametersImpl) sSLParametersImpl.clone();
        this.sslParameters = sSLParametersImpl2;
        sSLParametersImpl2.setUseClientMode(false);
    }

    @Override // javax.net.ServerSocketFactory
    public ServerSocket createServerSocket(int i2, int i3, InetAddress inetAddress) {
        return new ConscryptServerSocket(i2, i3, inetAddress, (SSLParametersImpl) this.sslParameters.clone()).setUseEngineSocket(this.useEngineSocket);
    }
}

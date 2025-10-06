package org.conscrypt;

import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLServerSocket;

/* loaded from: classes.dex */
final class ConscryptServerSocket extends SSLServerSocket {
    private boolean channelIdEnabled;
    private final SSLParametersImpl sslParameters;
    private boolean useEngineSocket;

    public ConscryptServerSocket(SSLParametersImpl sSLParametersImpl) {
        this.sslParameters = sSLParametersImpl;
    }

    @Override // java.net.ServerSocket
    public Socket accept() {
        AbstractConscryptSocket createFileDescriptorSocket;
        if (this.useEngineSocket) {
            createFileDescriptorSocket = Platform.createEngineSocket(this.sslParameters);
        } else {
            createFileDescriptorSocket = Platform.createFileDescriptorSocket(this.sslParameters);
        }
        createFileDescriptorSocket.setChannelIdEnabled(this.channelIdEnabled);
        implAccept(createFileDescriptorSocket);
        return createFileDescriptorSocket;
    }

    @Override // javax.net.ssl.SSLServerSocket
    public boolean getEnableSessionCreation() {
        return this.sslParameters.getEnableSessionCreation();
    }

    @Override // javax.net.ssl.SSLServerSocket
    public String[] getEnabledCipherSuites() {
        return this.sslParameters.getEnabledCipherSuites();
    }

    @Override // javax.net.ssl.SSLServerSocket
    public String[] getEnabledProtocols() {
        return this.sslParameters.getEnabledProtocols();
    }

    @Override // javax.net.ssl.SSLServerSocket
    public boolean getNeedClientAuth() {
        return this.sslParameters.getNeedClientAuth();
    }

    @Override // javax.net.ssl.SSLServerSocket
    public String[] getSupportedCipherSuites() {
        return NativeCrypto.getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLServerSocket
    public String[] getSupportedProtocols() {
        return NativeCrypto.getSupportedProtocols();
    }

    @Override // javax.net.ssl.SSLServerSocket
    public boolean getUseClientMode() {
        return this.sslParameters.getUseClientMode();
    }

    @Override // javax.net.ssl.SSLServerSocket
    public boolean getWantClientAuth() {
        return this.sslParameters.getWantClientAuth();
    }

    public boolean isChannelIdEnabled() {
        return this.channelIdEnabled;
    }

    public void setChannelIdEnabled(boolean z2) {
        this.channelIdEnabled = z2;
    }

    @Override // javax.net.ssl.SSLServerSocket
    public void setEnableSessionCreation(boolean z2) {
        this.sslParameters.setEnableSessionCreation(z2);
    }

    @Override // javax.net.ssl.SSLServerSocket
    public void setEnabledCipherSuites(String[] strArr) {
        this.sslParameters.setEnabledCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLServerSocket
    public void setEnabledProtocols(String[] strArr) {
        this.sslParameters.setEnabledProtocols(strArr);
    }

    @Override // javax.net.ssl.SSLServerSocket
    public void setNeedClientAuth(boolean z2) {
        this.sslParameters.setNeedClientAuth(z2);
    }

    @Override // javax.net.ssl.SSLServerSocket
    public void setUseClientMode(boolean z2) {
        this.sslParameters.setUseClientMode(z2);
    }

    public ConscryptServerSocket setUseEngineSocket(boolean z2) {
        this.useEngineSocket = z2;
        return this;
    }

    @Override // javax.net.ssl.SSLServerSocket
    public void setWantClientAuth(boolean z2) {
        this.sslParameters.setWantClientAuth(z2);
    }

    public ConscryptServerSocket(int i2, SSLParametersImpl sSLParametersImpl) {
        super(i2);
        this.sslParameters = sSLParametersImpl;
    }

    public ConscryptServerSocket(int i2, int i3, SSLParametersImpl sSLParametersImpl) {
        super(i2, i3);
        this.sslParameters = sSLParametersImpl;
    }

    public ConscryptServerSocket(int i2, int i3, InetAddress inetAddress, SSLParametersImpl sSLParametersImpl) {
        super(i2, i3, inetAddress);
        this.sslParameters = sSLParametersImpl;
    }
}

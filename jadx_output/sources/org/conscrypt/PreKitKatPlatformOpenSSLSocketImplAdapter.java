package org.conscrypt;

import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.security.PrivateKey;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;

/* loaded from: classes.dex */
public class PreKitKatPlatformOpenSSLSocketImplAdapter extends org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl {
    private final AbstractConscryptSocket delegate;

    public PreKitKatPlatformOpenSSLSocketImplAdapter(AbstractConscryptSocket abstractConscryptSocket) {
        super((org.apache.harmony.xnet.provider.jsse.SSLParametersImpl) null);
        this.delegate = abstractConscryptSocket;
    }

    public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.delegate.addHandshakeCompletedListener(handshakeCompletedListener);
    }

    public void bind(SocketAddress socketAddress) {
        this.delegate.bind(socketAddress);
    }

    public void clientCertificateRequested(byte[] bArr, byte[][] bArr2) {
        throw new RuntimeException("Shouldn't be here!");
    }

    public void close() {
        this.delegate.close();
    }

    public void connect(SocketAddress socketAddress, int i2) {
        this.delegate.connect(socketAddress, i2);
    }

    public byte[] getAlpnSelectedProtocol() {
        return this.delegate.getAlpnSelectedProtocol();
    }

    public SocketChannel getChannel() {
        return this.delegate.getChannel();
    }

    public byte[] getChannelId() {
        return this.delegate.getChannelId();
    }

    public boolean getEnableSessionCreation() {
        return this.delegate.getEnableSessionCreation();
    }

    public String[] getEnabledCipherSuites() {
        return this.delegate.getEnabledCipherSuites();
    }

    public String[] getEnabledProtocols() {
        return this.delegate.getEnabledProtocols();
    }

    public FileDescriptor getFileDescriptor$() {
        return this.delegate.getFileDescriptor$();
    }

    public InetAddress getInetAddress() {
        return this.delegate.getInetAddress();
    }

    public InputStream getInputStream() {
        return this.delegate.getInputStream();
    }

    public boolean getKeepAlive() {
        return this.delegate.getKeepAlive();
    }

    public InetAddress getLocalAddress() {
        return this.delegate.getLocalAddress();
    }

    public int getLocalPort() {
        return this.delegate.getLocalPort();
    }

    public SocketAddress getLocalSocketAddress() {
        return this.delegate.getLocalSocketAddress();
    }

    public boolean getNeedClientAuth() {
        return this.delegate.getNeedClientAuth();
    }

    public byte[] getNpnSelectedProtocol() {
        return this.delegate.getNpnSelectedProtocol();
    }

    public boolean getOOBInline() {
        return this.delegate.getOOBInline();
    }

    public OutputStream getOutputStream() {
        return this.delegate.getOutputStream();
    }

    public int getPort() {
        return this.delegate.getPort();
    }

    public int getReceiveBufferSize() {
        return this.delegate.getReceiveBufferSize();
    }

    public SocketAddress getRemoteSocketAddress() {
        return this.delegate.getRemoteSocketAddress();
    }

    public boolean getReuseAddress() {
        return this.delegate.getReuseAddress();
    }

    public SSLParameters getSSLParameters() {
        return this.delegate.getSSLParameters();
    }

    public int getSendBufferSize() {
        return this.delegate.getSendBufferSize();
    }

    public SSLSession getSession() {
        return this.delegate.getSession();
    }

    public int getSoLinger() {
        return this.delegate.getSoLinger();
    }

    public int getSoTimeout() {
        return this.delegate.getSoTimeout();
    }

    public int getSoWriteTimeout() {
        return this.delegate.getSoWriteTimeout();
    }

    public String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

    public String[] getSupportedProtocols() {
        return this.delegate.getSupportedProtocols();
    }

    public boolean getTcpNoDelay() {
        return this.delegate.getTcpNoDelay();
    }

    public int getTrafficClass() {
        return this.delegate.getTrafficClass();
    }

    public boolean getUseClientMode() {
        return this.delegate.getUseClientMode();
    }

    public boolean getWantClientAuth() {
        return this.delegate.getWantClientAuth();
    }

    public void handshakeCompleted() {
        throw new RuntimeException("Shouldn't be here!");
    }

    public boolean isBound() {
        return this.delegate.isBound();
    }

    public boolean isClosed() {
        return this.delegate.isClosed();
    }

    public boolean isConnected() {
        return this.delegate.isConnected();
    }

    public boolean isInputShutdown() {
        return this.delegate.isInputShutdown();
    }

    public boolean isOutputShutdown() {
        return this.delegate.isOutputShutdown();
    }

    public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.delegate.removeHandshakeCompletedListener(handshakeCompletedListener);
    }

    public void sendUrgentData(int i2) {
        this.delegate.sendUrgentData(i2);
    }

    public void setAlpnProtocols(byte[] bArr) {
        this.delegate.setAlpnProtocols(bArr);
    }

    public void setChannelIdEnabled(boolean z2) {
        this.delegate.setChannelIdEnabled(z2);
    }

    public void setChannelIdPrivateKey(PrivateKey privateKey) {
        this.delegate.setChannelIdPrivateKey(privateKey);
    }

    public void setEnableSessionCreation(boolean z2) {
        this.delegate.setEnableSessionCreation(z2);
    }

    public void setEnabledCipherSuites(String[] strArr) {
        this.delegate.setEnabledCipherSuites(strArr);
    }

    public void setEnabledProtocols(String[] strArr) {
        this.delegate.setEnabledProtocols(strArr);
    }

    public void setHandshakeTimeout(int i2) {
        this.delegate.setHandshakeTimeout(i2);
    }

    public void setHostname(String str) {
        this.delegate.setHostname(str);
    }

    public void setKeepAlive(boolean z2) {
        this.delegate.setKeepAlive(z2);
    }

    public void setNeedClientAuth(boolean z2) {
        this.delegate.setNeedClientAuth(z2);
    }

    public void setNpnProtocols(byte[] bArr) {
        this.delegate.setNpnProtocols(bArr);
    }

    public void setOOBInline(boolean z2) {
        this.delegate.setOOBInline(z2);
    }

    public void setPerformancePreferences(int i2, int i3, int i4) {
        this.delegate.setPerformancePreferences(i2, i3, i4);
    }

    public void setReceiveBufferSize(int i2) {
        this.delegate.setReceiveBufferSize(i2);
    }

    public void setReuseAddress(boolean z2) {
        this.delegate.setReuseAddress(z2);
    }

    public void setSSLParameters(SSLParameters sSLParameters) {
        this.delegate.setSSLParameters(sSLParameters);
    }

    public void setSendBufferSize(int i2) {
        this.delegate.setSendBufferSize(i2);
    }

    public void setSoLinger(boolean z2, int i2) {
        this.delegate.setSoLinger(z2, i2);
    }

    public void setSoTimeout(int i2) {
        this.delegate.setSoTimeout(i2);
    }

    public void setSoWriteTimeout(int i2) {
        this.delegate.setSoWriteTimeout(i2);
    }

    public void setTcpNoDelay(boolean z2) {
        this.delegate.setTcpNoDelay(z2);
    }

    public void setTrafficClass(int i2) {
        this.delegate.setTrafficClass(i2);
    }

    public void setUseClientMode(boolean z2) {
        this.delegate.setUseClientMode(z2);
    }

    public void setUseSessionTickets(boolean z2) {
        this.delegate.setUseSessionTickets(z2);
    }

    public void setWantClientAuth(boolean z2) {
        this.delegate.setWantClientAuth(z2);
    }

    public void shutdownInput() {
        this.delegate.shutdownInput();
    }

    public void shutdownOutput() {
        this.delegate.shutdownOutput();
    }

    public void startHandshake() {
        this.delegate.startHandshake();
    }

    public String toString() {
        return this.delegate.toString();
    }

    public void verifyCertificateChain(byte[][] bArr, String str) {
        throw new RuntimeException("Shouldn't be here!");
    }

    public void connect(SocketAddress socketAddress) {
        this.delegate.connect(socketAddress);
    }
}

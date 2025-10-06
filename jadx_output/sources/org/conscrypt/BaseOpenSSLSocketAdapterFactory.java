package org.conscrypt;

import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes.dex */
public abstract class BaseOpenSSLSocketAdapterFactory extends SSLSocketFactory {
    private final OpenSSLSocketFactoryImpl delegate;

    public BaseOpenSSLSocketAdapterFactory(OpenSSLSocketFactoryImpl openSSLSocketFactoryImpl) {
        this.delegate = openSSLSocketFactoryImpl;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() {
        return wrap((OpenSSLSocketImpl) this.delegate.createSocket());
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

    public abstract Socket wrap(OpenSSLSocketImpl openSSLSocketImpl);

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2) {
        return wrap((OpenSSLSocketImpl) this.delegate.createSocket(str, i2));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2, InetAddress inetAddress, int i3) {
        return wrap((OpenSSLSocketImpl) this.delegate.createSocket(str, i2, inetAddress, i3));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2) {
        return wrap((OpenSSLSocketImpl) this.delegate.createSocket(inetAddress, i2));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) {
        return wrap((OpenSSLSocketImpl) this.delegate.createSocket(inetAddress, i2, inetAddress2, i3));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i2, boolean z2) {
        return wrap((OpenSSLSocketImpl) this.delegate.createSocket(socket, str, i2, z2));
    }
}

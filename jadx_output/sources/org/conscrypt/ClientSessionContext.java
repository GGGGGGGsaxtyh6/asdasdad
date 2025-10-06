package org.conscrypt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class ClientSessionContext extends AbstractSessionContext {
    private SSLClientSessionCache persistentCache;
    private final Map<HostAndPort, List<NativeSslSession>> sessionsByHostAndPort;

    /* loaded from: classes.dex */
    public static final class HostAndPort {
        final String host;
        final int port;

        public HostAndPort(String str, int i2) {
            this.host = str;
            this.port = i2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof HostAndPort)) {
                return false;
            }
            HostAndPort hostAndPort = (HostAndPort) obj;
            if (!this.host.equals(hostAndPort.host) || this.port != hostAndPort.port) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.host.hashCode() * 31) + this.port;
        }
    }

    public ClientSessionContext() {
        super(10);
        this.sessionsByHostAndPort = new HashMap();
    }

    private NativeSslSession getSession(String str, int i2) {
        NativeSslSession nativeSslSession;
        byte[] sessionData;
        NativeSslSession newInstance;
        if (str == null) {
            return null;
        }
        HostAndPort hostAndPort = new HostAndPort(str, i2);
        synchronized (this.sessionsByHostAndPort) {
            try {
                List<NativeSslSession> list = this.sessionsByHostAndPort.get(hostAndPort);
                if (list != null && list.size() > 0) {
                    nativeSslSession = list.get(0);
                } else {
                    nativeSslSession = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (nativeSslSession != null && nativeSslSession.isValid()) {
            return nativeSslSession;
        }
        SSLClientSessionCache sSLClientSessionCache = this.persistentCache;
        if (sSLClientSessionCache == null || (sessionData = sSLClientSessionCache.getSessionData(str, i2)) == null || (newInstance = NativeSslSession.newInstance(this, sessionData, str, i2)) == null || !newInstance.isValid()) {
            return null;
        }
        putSession(hostAndPort, newInstance);
        return newInstance;
    }

    private void putSession(HostAndPort hostAndPort, NativeSslSession nativeSslSession) {
        synchronized (this.sessionsByHostAndPort) {
            try {
                List<NativeSslSession> list = this.sessionsByHostAndPort.get(hostAndPort);
                if (list == null) {
                    list = new ArrayList<>();
                    this.sessionsByHostAndPort.put(hostAndPort, list);
                }
                if (list.size() > 0 && list.get(0).isSingleUse() != nativeSslSession.isSingleUse()) {
                    while (!list.isEmpty()) {
                        removeSession(list.get(0));
                    }
                    this.sessionsByHostAndPort.put(hostAndPort, list);
                }
                list.add(nativeSslSession);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void removeSession(HostAndPort hostAndPort, NativeSslSession nativeSslSession) {
        synchronized (this.sessionsByHostAndPort) {
            try {
                List<NativeSslSession> list = this.sessionsByHostAndPort.get(hostAndPort);
                if (list != null) {
                    list.remove(nativeSslSession);
                    if (list.isEmpty()) {
                        this.sessionsByHostAndPort.remove(hostAndPort);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public synchronized NativeSslSession getCachedSession(String str, int i2, SSLParametersImpl sSLParametersImpl) {
        if (str == null) {
            return null;
        }
        NativeSslSession session = getSession(str, i2);
        if (session == null) {
            return null;
        }
        String protocol = session.getProtocol();
        for (String str2 : sSLParametersImpl.enabledProtocols) {
            if (protocol.equals(str2)) {
                String cipherSuite = session.getCipherSuite();
                for (String str3 : sSLParametersImpl.getEnabledCipherSuites()) {
                    if (cipherSuite.equals(str3)) {
                        if (session.isSingleUse()) {
                            removeSession(session);
                        }
                        return session;
                    }
                }
                return null;
            }
        }
        return null;
    }

    @Override // org.conscrypt.AbstractSessionContext
    public NativeSslSession getSessionFromPersistentCache(byte[] bArr) {
        return null;
    }

    @Override // org.conscrypt.AbstractSessionContext
    public void onBeforeAddSession(NativeSslSession nativeSslSession) {
        byte[] bytes;
        String peerHost = nativeSslSession.getPeerHost();
        int peerPort = nativeSslSession.getPeerPort();
        if (peerHost == null) {
            return;
        }
        putSession(new HostAndPort(peerHost, peerPort), nativeSslSession);
        if (this.persistentCache != null && !nativeSslSession.isSingleUse() && (bytes = nativeSslSession.toBytes()) != null) {
            this.persistentCache.putSessionData(nativeSslSession.toSSLSession(), bytes);
        }
    }

    @Override // org.conscrypt.AbstractSessionContext
    public void onBeforeRemoveSession(NativeSslSession nativeSslSession) {
        String peerHost = nativeSslSession.getPeerHost();
        if (peerHost == null) {
            return;
        }
        removeSession(new HostAndPort(peerHost, nativeSslSession.getPeerPort()), nativeSslSession);
    }

    public void setPersistentCache(SSLClientSessionCache sSLClientSessionCache) {
        this.persistentCache = sSLClientSessionCache;
    }

    public int size() {
        int i2;
        synchronized (this.sessionsByHostAndPort) {
            try {
                Iterator<List<NativeSslSession>> it = this.sessionsByHostAndPort.values().iterator();
                i2 = 0;
                while (it.hasNext()) {
                    i2 += it.next().size();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i2;
    }
}

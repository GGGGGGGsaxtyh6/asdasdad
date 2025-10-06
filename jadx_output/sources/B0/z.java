package B0;

import j0.AbstractC0150d;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

/* loaded from: classes.dex */
public final class z extends H0.d {

    /* renamed from: k, reason: collision with root package name */
    public final /* synthetic */ int f198k;

    /* renamed from: l, reason: collision with root package name */
    public final Object f199l;

    public /* synthetic */ z(int i2, Object obj) {
        this.f198k = i2;
        this.f199l = obj;
    }

    @Override // H0.d
    public final void j() {
        switch (this.f198k) {
            case 0:
                ((A) this.f199l).e(EnumC0001b.CANCEL);
                s sVar = ((A) this.f199l).f54b;
                synchronized (sVar) {
                    long j2 = sVar.f160n;
                    long j3 = sVar.f159m;
                    if (j2 >= j3) {
                        sVar.f159m = j3 + 1;
                        sVar.f161o = System.nanoTime() + 1000000000;
                        sVar.h.c(new p(AbstractC0150d.h(" ping", sVar.f150c), sVar, 0), 0L);
                        return;
                    }
                    return;
                }
            case 1:
                Socket socket = (Socket) this.f199l;
                try {
                    socket.close();
                    return;
                } catch (AssertionError e2) {
                    if (C0.m.l(e2)) {
                        H0.l.f427a.log(Level.WARNING, AbstractC0150d.h(socket, "Failed to close timed out socket "), (Throwable) e2);
                        return;
                    }
                    throw e2;
                } catch (Exception e3) {
                    H0.l.f427a.log(Level.WARNING, AbstractC0150d.h(socket, "Failed to close timed out socket "), (Throwable) e3);
                    return;
                }
            default:
                ((y0.h) this.f199l).d();
                return;
        }
    }

    public void k() {
        if (!i()) {
        } else {
            throw l(null);
        }
    }

    public IOException l(IOException iOException) {
        switch (this.f198k) {
            case 0:
                SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            default:
                SocketTimeoutException socketTimeoutException2 = new SocketTimeoutException("timeout");
                if (iOException != null) {
                    socketTimeoutException2.initCause(iOException);
                }
                return socketTimeoutException2;
        }
    }

    public z(A a2) {
        this.f198k = 0;
        AbstractC0150d.e(a2, "this$0");
        this.f199l = a2;
    }
}

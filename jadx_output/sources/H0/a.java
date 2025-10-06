package H0;

/* loaded from: classes.dex */
public final class a extends Thread {
    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        d b2;
        while (true) {
            try {
                synchronized (d.class) {
                    d dVar = d.f407j;
                    b2 = C0.f.b();
                    if (b2 == d.f407j) {
                        d.f407j = null;
                        return;
                    }
                }
                if (b2 != null) {
                    b2.j();
                }
            } catch (InterruptedException unused) {
            }
        }
    }
}

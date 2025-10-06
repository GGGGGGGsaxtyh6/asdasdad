package M;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes.dex */
public final class j {

    /* renamed from: j, reason: collision with root package name */
    public static final Object f532j = new Object();

    /* renamed from: k, reason: collision with root package name */
    public static volatile j f533k;

    /* renamed from: a, reason: collision with root package name */
    public final ReentrantReadWriteLock f534a;

    /* renamed from: b, reason: collision with root package name */
    public final n.g f535b;

    /* renamed from: c, reason: collision with root package name */
    public volatile int f536c;

    /* renamed from: d, reason: collision with root package name */
    public final Handler f537d;

    /* renamed from: e, reason: collision with root package name */
    public final f f538e;

    /* renamed from: f, reason: collision with root package name */
    public final i f539f;

    /* renamed from: g, reason: collision with root package name */
    public final A.m f540g;
    public final int h;

    /* renamed from: i, reason: collision with root package name */
    public final d f541i;

    public j(t tVar) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.f534a = reentrantReadWriteLock;
        this.f536c = 3;
        i iVar = tVar.f565a;
        this.f539f = iVar;
        int i2 = tVar.f566b;
        this.h = i2;
        this.f541i = tVar.f567c;
        this.f537d = new Handler(Looper.getMainLooper());
        this.f535b = new n.g();
        this.f540g = new A.m(5);
        f fVar = new f(this);
        this.f538e = fVar;
        reentrantReadWriteLock.writeLock().lock();
        if (i2 == 0) {
            try {
                this.f536c = 0;
            } catch (Throwable th) {
                this.f534a.writeLock().unlock();
                throw th;
            }
        }
        reentrantReadWriteLock.writeLock().unlock();
        if (b() == 0) {
            try {
                iVar.n(new e(fVar));
            } catch (Throwable th2) {
                d(th2);
            }
        }
    }

    public static j a() {
        j jVar;
        boolean z2;
        synchronized (f532j) {
            try {
                jVar = f533k;
                if (jVar != null) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (!z2) {
                    throw new IllegalStateException("EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");
                }
            } finally {
            }
        }
        return jVar;
    }

    public final int b() {
        this.f534a.readLock().lock();
        try {
            return this.f536c;
        } finally {
            this.f534a.readLock().unlock();
        }
    }

    public final void c() {
        boolean z2;
        if (this.h == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (b() == 1) {
                return;
            }
            this.f534a.writeLock().lock();
            try {
                if (this.f536c == 0) {
                    return;
                }
                this.f536c = 0;
                this.f534a.writeLock().unlock();
                f fVar = this.f538e;
                j jVar = fVar.f527a;
                try {
                    jVar.f539f.n(new e(fVar));
                    return;
                } catch (Throwable th) {
                    jVar.d(th);
                    return;
                }
            } finally {
                this.f534a.writeLock().unlock();
            }
        }
        throw new IllegalStateException("Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
    }

    public final void d(Throwable th) {
        ArrayList arrayList = new ArrayList();
        this.f534a.writeLock().lock();
        try {
            this.f536c = 2;
            arrayList.addAll(this.f535b);
            this.f535b.clear();
            this.f534a.writeLock().unlock();
            this.f537d.post(new h(arrayList, this.f536c, th));
        } catch (Throwable th2) {
            this.f534a.writeLock().unlock();
            throw th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0092 A[Catch: all -> 0x0075, TryCatch #0 {all -> 0x0075, blocks: (B:70:0x0050, B:73:0x0055, B:75:0x0059, B:77:0x0066, B:32:0x0082, B:34:0x008c, B:36:0x008f, B:38:0x0092, B:40:0x00a2, B:42:0x00a5, B:47:0x00b4, B:50:0x00bb, B:52:0x00d2, B:30:0x0078), top: B:69:0x0050 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00d2 A[Catch: all -> 0x0075, TRY_LEAVE, TryCatch #0 {all -> 0x0075, blocks: (B:70:0x0050, B:73:0x0055, B:75:0x0059, B:77:0x0066, B:32:0x0082, B:34:0x008c, B:36:0x008f, B:38:0x0092, B:40:0x00a2, B:42:0x00a5, B:47:0x00b4, B:50:0x00bb, B:52:0x00d2, B:30:0x0078), top: B:69:0x0050 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00dd  */
    /* JADX WARN: Type inference failed for: r0v6, types: [M.B, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.CharSequence e(java.lang.CharSequence r11, int r12, int r13) {
        /*
            Method dump skipped, instructions count: 291
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: M.j.e(java.lang.CharSequence, int, int):java.lang.CharSequence");
    }

    public final void f(g gVar) {
        C0.d.j(gVar, "initCallback cannot be null");
        this.f534a.writeLock().lock();
        try {
            if (this.f536c != 1 && this.f536c != 2) {
                this.f535b.add(gVar);
                this.f534a.writeLock().unlock();
            }
            this.f537d.post(new h(Arrays.asList(gVar), this.f536c, null));
            this.f534a.writeLock().unlock();
        } catch (Throwable th) {
            this.f534a.writeLock().unlock();
            throw th;
        }
    }
}

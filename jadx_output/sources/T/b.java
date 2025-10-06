package T;

import android.content.res.AssetManager;
import android.os.Build;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    public final Executor f623a;

    /* renamed from: b, reason: collision with root package name */
    public final e f624b;

    /* renamed from: c, reason: collision with root package name */
    public final byte[] f625c;

    /* renamed from: d, reason: collision with root package name */
    public final File f626d;

    /* renamed from: e, reason: collision with root package name */
    public final String f627e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f628f = false;

    /* renamed from: g, reason: collision with root package name */
    public c[] f629g;
    public byte[] h;

    public b(AssetManager assetManager, Executor executor, e eVar, String str, File file) {
        byte[] bArr;
        this.f623a = executor;
        this.f624b = eVar;
        this.f627e = str;
        this.f626d = file;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 31) {
            bArr = f.f641d;
        } else {
            switch (i2) {
                case 24:
                case 25:
                    bArr = f.h;
                    break;
                case 26:
                    bArr = f.f644g;
                    break;
                case 27:
                    bArr = f.f643f;
                    break;
                case 28:
                case 29:
                case 30:
                    bArr = f.f642e;
                    break;
                default:
                    bArr = null;
                    break;
            }
        }
        this.f625c = bArr;
    }

    public final FileInputStream a(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e2) {
            String message = e2.getMessage();
            if (message != null && message.contains("compressed")) {
                this.f624b.t();
            }
            return null;
        }
    }

    public final void b(final int i2, final Serializable serializable) {
        this.f623a.execute(new Runnable() { // from class: T.a
            @Override // java.lang.Runnable
            public final void run() {
                b.this.f624b.p(i2, serializable);
            }
        });
    }
}

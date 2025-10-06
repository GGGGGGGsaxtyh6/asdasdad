package M;

import android.content.Context;
import android.widget.Toast;
import htb.d3vnu11.securenotes.EditNoteActivity;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: classes.dex */
public final /* synthetic */ class l implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f543a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f544b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f545c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f546d;

    public /* synthetic */ l(Object obj, Object obj2, Object obj3, int i2) {
        this.f543a = i2;
        this.f544b = obj;
        this.f545c = obj2;
        this.f546d = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f543a) {
            case 0:
                A.f fVar = (A.f) this.f544b;
                C0.d dVar = (C0.d) this.f545c;
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) this.f546d;
                fVar.getClass();
                try {
                    t d2 = C0.m.d((Context) fVar.f8b);
                    if (d2 != null) {
                        s sVar = (s) d2.f565a;
                        synchronized (sVar.f560d) {
                            sVar.f562f = threadPoolExecutor;
                        }
                        d2.f565a.n(new m(dVar, threadPoolExecutor));
                        return;
                    }
                    throw new RuntimeException("EmojiCompat font provider not available on this device.");
                } catch (Throwable th) {
                    dVar.z(th);
                    threadPoolExecutor.shutdown();
                    return;
                }
            default:
                E.c cVar = (E.c) this.f544b;
                cVar.getClass();
                int i2 = EditNoteActivity.f1484j;
                EditNoteActivity editNoteActivity = (EditNoteActivity) cVar.f272c;
                editNoteActivity.getClass();
                Toast.makeText(editNoteActivity, "Failed to save to cloud: " + ((String) this.f545c), 1).show();
                if (editNoteActivity.f1489e.f((Z.r) this.f546d)) {
                    Toast.makeText(editNoteActivity, "Saved locally instead", 0).show();
                    editNoteActivity.setResult(-1);
                    editNoteActivity.finish();
                    return;
                }
                return;
        }
    }
}

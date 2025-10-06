package M;

import a.ViewTreeObserverOnDrawListenerC0043j;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Trace;
import android.widget.Toast;
import androidx.lifecycle.EnumC0066m;
import g.DialogC0110h;
import htb.d3vnu11.securenotes.EditNoteActivity;
import j0.AbstractC0150d;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import k.U;
import org.conscrypt.ct.CTConstants;
import x.C0255a;

/* loaded from: classes.dex */
public final /* synthetic */ class r implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f555a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f556b;

    public /* synthetic */ r(int i2, Object obj) {
        this.f555a = i2;
        this.f556b = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10, types: [android.os.Handler] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v5, types: [int] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v6 */
    @Override // java.lang.Runnable
    public final void run() {
        boolean z2;
        Object obj;
        boolean z3 = true;
        switch (this.f555a) {
            case 0:
                s sVar = (s) this.f556b;
                synchronized (sVar.f560d) {
                    try {
                        if (sVar.h != null) {
                            try {
                                E.l b2 = sVar.b();
                                int i2 = b2.f300e;
                                if (i2 == 2) {
                                    synchronized (sVar.f560d) {
                                    }
                                }
                                if (i2 == 0) {
                                    try {
                                        int i3 = D.g.f242a;
                                        Trace.beginSection("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
                                        A.m mVar = sVar.f559c;
                                        Context context = sVar.f557a;
                                        mVar.getClass();
                                        E.l[] lVarArr = {b2};
                                        C0.d dVar = A.g.f9a;
                                        C0.f.c("TypefaceCompat.createFromFontInfo");
                                        try {
                                            Typeface l2 = A.g.f9a.l(context, lVarArr, 0);
                                            Trace.endSection();
                                            MappedByteBuffer v2 = C0.f.v(sVar.f557a, b2.f296a);
                                            if (v2 != null && l2 != null) {
                                                try {
                                                    Trace.beginSection("EmojiCompat.MetadataRepo.create");
                                                    v vVar = new v(l2, C0.m.q(v2));
                                                    Trace.endSection();
                                                    synchronized (sVar.f560d) {
                                                        try {
                                                            C0.d dVar2 = sVar.h;
                                                            if (dVar2 != null) {
                                                                dVar2.A(vVar);
                                                            }
                                                        } finally {
                                                        }
                                                    }
                                                    sVar.a();
                                                    return;
                                                } finally {
                                                    int i4 = D.g.f242a;
                                                }
                                            }
                                            throw new RuntimeException("Unable to open file.");
                                        } finally {
                                            Trace.endSection();
                                        }
                                    } finally {
                                    }
                                }
                                throw new RuntimeException("fetchFonts result is not OK. (" + i2 + ")");
                            } catch (Throwable th) {
                                synchronized (sVar.f560d) {
                                    try {
                                        C0.d dVar3 = sVar.h;
                                        if (dVar3 != null) {
                                            dVar3.z(th);
                                        }
                                        sVar.a();
                                        return;
                                    } finally {
                                    }
                                }
                            }
                        }
                        return;
                    } finally {
                    }
                }
            case 1:
                E.c cVar = (E.c) this.f556b;
                cVar.getClass();
                int i5 = EditNoteActivity.f1484j;
                EditNoteActivity editNoteActivity = (EditNoteActivity) cVar.f272c;
                editNoteActivity.getClass();
                Toast.makeText(editNoteActivity, "Note saved to cloud", 0).show();
                editNoteActivity.setResult(-1);
                editNoteActivity.finish();
                return;
            case 2:
                ViewTreeObserverOnDrawListenerC0043j viewTreeObserverOnDrawListenerC0043j = (ViewTreeObserverOnDrawListenerC0043j) this.f556b;
                Runnable runnable = viewTreeObserverOnDrawListenerC0043j.f750b;
                if (runnable != null) {
                    runnable.run();
                    viewTreeObserverOnDrawListenerC0043j.f750b = null;
                    return;
                }
                return;
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                DialogC0110h.a((DialogC0110h) this.f556b);
                return;
            case 4:
                androidx.lifecycle.A a2 = (androidx.lifecycle.A) this.f556b;
                AbstractC0150d.e(a2, "this$0");
                int i6 = a2.f1144b;
                androidx.lifecycle.v vVar2 = a2.f1148f;
                if (i6 == 0) {
                    a2.f1145c = true;
                    vVar2.e(EnumC0066m.ON_PAUSE);
                }
                if (a2.f1143a == 0 && a2.f1145c) {
                    vVar2.e(EnumC0066m.ON_STOP);
                    a2.f1146d = true;
                    return;
                }
                return;
            case 5:
                Activity activity = (Activity) this.f556b;
                if (!activity.isFinishing()) {
                    ?? r5 = Build.VERSION.SDK_INT;
                    if (r5 >= 28) {
                        Class cls = x.b.f2824a;
                        activity.recreate();
                        return;
                    }
                    Class cls2 = x.b.f2824a;
                    ?? r6 = 27;
                    ?? r7 = 26;
                    if (r5 != 26 && r5 != 27) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    Method method = x.b.f2829f;
                    if ((!z2 || method != null) && (x.b.f2828e != null || x.b.f2827d != null)) {
                        try {
                            Object obj2 = x.b.f2826c.get(activity);
                            if (obj2 != null && (obj = x.b.f2825b.get(activity)) != null) {
                                Application application = activity.getApplication();
                                C0255a c0255a = new C0255a(activity);
                                application.registerActivityLifecycleCallbacks(c0255a);
                                Handler handler = x.b.f2830g;
                                handler.post(new E.a(c0255a, obj2, 12));
                                if (r5 != 26 && r5 != 27) {
                                    z3 = false;
                                }
                                try {
                                    if (z3) {
                                        try {
                                            Boolean bool = Boolean.FALSE;
                                            r5 = handler;
                                            r6 = c0255a;
                                            r7 = application;
                                            method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                                        } catch (Throwable th2) {
                                            th = th2;
                                            Handler handler2 = handler;
                                            C0255a c0255a2 = c0255a;
                                            Application application2 = application;
                                            handler2.post(new E.a(application2, c0255a2, 13));
                                            throw th;
                                        }
                                    } else {
                                        r5 = handler;
                                        r6 = c0255a;
                                        r7 = application;
                                        activity.recreate();
                                    }
                                    r5.post(new E.a(r7, r6, 13));
                                    return;
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                            }
                        } catch (Throwable unused) {
                        }
                    }
                    activity.recreate();
                    return;
                }
                return;
            default:
                ((U) this.f556b).getClass();
                return;
        }
    }
}

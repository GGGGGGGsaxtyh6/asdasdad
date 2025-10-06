package E;

import H.E;
import H.N;
import H.S;
import M.B;
import M.y;
import M.z;
import Z.r;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import g.C;
import g.s;
import htb.d3vnu11.securenotes.EditNoteActivity;
import i.AbstractC0120b;
import i.InterfaceC0119a;
import java.io.IOException;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class c implements M.p, u0.d, InterfaceC0119a {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f270a;

    /* renamed from: b, reason: collision with root package name */
    public Object f271b;

    /* renamed from: c, reason: collision with root package name */
    public Object f272c;

    public /* synthetic */ c() {
        this.f270a = 8;
    }

    @Override // i.InterfaceC0119a
    public boolean a(AbstractC0120b abstractC0120b, MenuItem menuItem) {
        return ((InterfaceC0119a) this.f271b).a(abstractC0120b, menuItem);
    }

    @Override // i.InterfaceC0119a
    public boolean b(AbstractC0120b abstractC0120b, j.n nVar) {
        ViewGroup viewGroup = ((C) this.f272c).f1305A;
        WeakHashMap weakHashMap = N.f327a;
        E.c(viewGroup);
        return ((InterfaceC0119a) this.f271b).b(abstractC0120b, nVar);
    }

    @Override // i.InterfaceC0119a
    public boolean c(AbstractC0120b abstractC0120b, j.n nVar) {
        return ((InterfaceC0119a) this.f271b).c(abstractC0120b, nVar);
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [g.k, java.lang.Object] */
    @Override // i.InterfaceC0119a
    public void d(AbstractC0120b abstractC0120b) {
        ((InterfaceC0119a) this.f271b).d(abstractC0120b);
        C c2 = (C) this.f272c;
        if (c2.f1350w != null) {
            c2.f1339l.getDecorView().removeCallbacks(c2.f1351x);
        }
        if (c2.f1349v != null) {
            S s2 = c2.y;
            if (s2 != null) {
                s2.b();
            }
            S a2 = N.a(c2.f1349v);
            a2.a(0.0f);
            c2.y = a2;
            a2.d(new s(2, this));
        }
        c2.f1341n.onSupportActionModeFinished(c2.f1348u);
        c2.f1348u = null;
        ViewGroup viewGroup = c2.f1305A;
        WeakHashMap weakHashMap = N.f327a;
        E.c(viewGroup);
        c2.J();
    }

    public void e(String str) {
        ((EditNoteActivity) this.f272c).runOnUiThread(new M.l(this, str, (r) this.f271b, 1));
    }

    public void f(j jVar) {
        int i2 = jVar.f291b;
        o oVar = (o) this.f272c;
        A.f fVar = (A.f) this.f271b;
        if (i2 == 0) {
            oVar.execute(new a(fVar, jVar.f290a, 0));
        } else {
            oVar.execute(new b(fVar, i2));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:99:0x022b, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void g(android.content.Context r12, android.content.res.XmlResourceParser r13) {
        /*
            Method dump skipped, instructions count: 666
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: E.c.g(android.content.Context, android.content.res.XmlResourceParser):void");
    }

    @Override // M.p
    public Object i() {
        return (B) this.f271b;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:9:? A[RETURN, SYNTHETIC] */
    @Override // u0.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void s(u0.u r6) {
        /*
            r5 = this;
            java.lang.String r0 = "success"
            java.lang.Object r1 = r5.f272c
            Z.i r1 = (Z.i) r1
            java.util.concurrent.atomic.AtomicBoolean r2 = r1.f706d
            r3 = 0
            r2.set(r3)
            boolean r2 = r6.h()
            java.lang.String r4 = "AccountManager"
            if (r2 == 0) goto L58
            u0.w r6 = r6.f2572g     // Catch: J0.b -> L3b
            java.lang.String r6 = r6.j()     // Catch: J0.b -> L3b
            J0.c r2 = new J0.c     // Catch: J0.b -> L3b
            r2.<init>(r6)     // Catch: J0.b -> L3b
            java.util.HashMap r6 = r2.f474a     // Catch: J0.b -> L3b
            boolean r6 = r6.containsKey(r0)     // Catch: J0.b -> L3b
            if (r6 == 0) goto L3e
            boolean r6 = r2.d()     // Catch: J0.b -> L3b
            if (r6 == 0) goto L3e
            java.util.concurrent.atomic.AtomicBoolean r6 = r1.f707e     // Catch: J0.b -> L3b
            r0 = 1
            r6.set(r0)     // Catch: J0.b -> L3b
            java.lang.String r6 = "Token verified successfully"
            android.util.Log.d(r4, r6)     // Catch: J0.b -> L39
            goto L56
        L39:
            r6 = move-exception
            goto L4c
        L3b:
            r6 = move-exception
            r0 = r3
            goto L4c
        L3e:
            java.util.concurrent.atomic.AtomicBoolean r6 = r1.f707e     // Catch: J0.b -> L3b
            r6.set(r3)     // Catch: J0.b -> L3b
            java.lang.String r6 = "Token verification failed: Server returned unsuccessful"
            android.util.Log.d(r4, r6)     // Catch: J0.b -> L3b
            r1.c()     // Catch: J0.b -> L3b
            goto L73
        L4c:
            java.util.concurrent.atomic.AtomicBoolean r2 = r1.f707e
            r2.set(r3)
            java.lang.String r2 = "Token verification failed: JSON parsing error"
            android.util.Log.e(r4, r2, r6)
        L56:
            r3 = r0
            goto L73
        L58:
            java.util.concurrent.atomic.AtomicBoolean r0 = r1.f707e
            r0.set(r3)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Token verification failed: "
            r0.<init>(r2)
            int r6 = r6.f2569d
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            android.util.Log.d(r4, r6)
            r1.c()
        L73:
            java.lang.Object r6 = r5.f271b
            Z.h r6 = (Z.h) r6
            if (r6 == 0) goto L83
            android.os.Handler r0 = r1.f709g
            Z.g r1 = new Z.g
            r1.<init>()
            r0.post(r1)
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: E.c.s(u0.u):void");
    }

    public String toString() {
        switch (this.f270a) {
            case 7:
                String str = "[ ";
                if (((q.g) this.f271b) != null) {
                    for (int i2 = 0; i2 < 9; i2++) {
                        str = str + ((q.g) this.f271b).h[i2] + " ";
                    }
                }
                return str + "] " + ((q.g) this.f271b);
            default:
                return super.toString();
        }
    }

    @Override // M.p
    public boolean u(CharSequence charSequence, int i2, int i3, y yVar) {
        Spannable spannableString;
        if ((yVar.f581c & 4) > 0) {
            return true;
        }
        if (((B) this.f271b) == null) {
            if (charSequence instanceof Spannable) {
                spannableString = (Spannable) charSequence;
            } else {
                spannableString = new SpannableString(charSequence);
            }
            this.f271b = new B(spannableString);
        }
        ((A.m) this.f272c).getClass();
        ((B) this.f271b).setSpan(new z(yVar), i2, i3, 33);
        return true;
    }

    @Override // u0.d
    public void w(IOException iOException) {
        Z.i iVar = (Z.i) this.f272c;
        iVar.f706d.set(false);
        iVar.f707e.set(false);
        Log.e("AccountManager", "Token verification failed due to network error: " + iOException.getMessage());
        Z.h hVar = (Z.h) this.f271b;
        if (hVar != null) {
            iVar.f709g.post(new Z.a(hVar, 2));
        }
    }

    public /* synthetic */ c(Object obj, Object obj2, int i2) {
        this.f270a = i2;
        this.f271b = obj;
        this.f272c = obj2;
    }

    public /* synthetic */ c(Object obj, Object obj2, int i2, boolean z2) {
        this.f270a = i2;
        this.f272c = obj;
        this.f271b = obj2;
    }

    public c(q.e eVar) {
        this.f270a = 7;
        this.f272c = eVar;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [android.text.Editable$Factory, O.a] */
    public c(EditText editText) {
        this.f270a = 2;
        this.f271b = editText;
        O.i iVar = new O.i(editText);
        this.f272c = iVar;
        editText.addTextChangedListener(iVar);
        if (O.a.f588b == null) {
            synchronized (O.a.f587a) {
                try {
                    if (O.a.f588b == null) {
                        ?? factory = new Editable.Factory();
                        try {
                            O.a.f589c = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, O.a.class.getClassLoader());
                        } catch (Throwable unused) {
                        }
                        O.a.f588b = factory;
                    }
                } finally {
                }
            }
        }
        editText.setEditableFactory(O.a.f588b);
    }
}

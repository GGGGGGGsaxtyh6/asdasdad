package A;

import H.AbstractC0009c;
import H.C0013g;
import H.InterfaceC0010d;
import H.InterfaceC0012f;
import M.ThreadFactoryC0032a;
import M.p;
import Z.r;
import Z.s;
import Z.t;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContentInfo;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.N;
import androidx.lifecycle.P;
import androidx.lifecycle.S;
import androidx.profileinstaller.ProfileInstallReceiver;
import g.I;
import htb.d3vnu11.securenotes.EditNoteActivity;
import htb.d3vnu11.securenotes.MainActivity;
import j.C0145g;
import j.RunnableC0144f;
import j.SubMenuC0138F;
import j.ViewOnKeyListenerC0146h;
import j.n;
import j.y;
import j0.AbstractC0150d;
import j0.C0148b;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import k.C0184k;
import k.H0;
import k.InterfaceC0165a0;
import k.InterfaceC0190n;
import k.X0;
import k.b1;
import org.conscrypt.BuildConfig;
import org.conscrypt.ct.CTConstants;
import u0.u;

/* loaded from: classes.dex */
public class f implements InterfaceC0010d, InterfaceC0012f, M.i, p, T.e, Z.h, u0.d, H0, y, j.l, InterfaceC0165a0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7a;

    /* renamed from: b, reason: collision with root package name */
    public Object f8b;

    public /* synthetic */ f(int i2, Object obj) {
        this.f7a = i2;
        this.f8b = obj;
    }

    public u0.k A() {
        Object[] array = ((ArrayList) this.f8b).toArray(new String[0]);
        if (array != null) {
            return new u0.k((String[]) array);
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }

    public N B(Class cls) {
        String str;
        C0148b a2 = j0.h.a(cls);
        Class cls2 = a2.f1735a;
        String str2 = null;
        if (!cls2.isAnonymousClass() && !cls2.isLocalClass()) {
            boolean isArray = cls2.isArray();
            HashMap hashMap = C0148b.f1734c;
            if (isArray) {
                Class<?> componentType = cls2.getComponentType();
                if (componentType.isPrimitive() && (str = (String) hashMap.get(componentType.getName())) != null) {
                    str2 = str.concat("Array");
                }
                if (str2 == null) {
                    str2 = "kotlin.Array";
                }
            } else {
                str2 = (String) hashMap.get(cls2.getName());
                if (str2 == null) {
                    str2 = cls2.getCanonicalName();
                }
            }
        }
        if (str2 != null) {
            return ((D0.h) this.f8b).m(a2, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(str2));
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public void C(String str) {
        switch (this.f7a) {
            case 11:
                ((EditNoteActivity) this.f8b).runOnUiThread(new E.a(this, str, 3, false));
                return;
            default:
                ((s) ((E.b) this.f8b).f269b).f730a.runOnUiThread(new E.a(this, str, 9, false));
                return;
        }
    }

    public void D(String str) {
        int i2 = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) this.f8b;
            if (i2 < arrayList.size()) {
                if (str.equalsIgnoreCase((String) arrayList.get(i2))) {
                    arrayList.remove(i2);
                    arrayList.remove(i2);
                    i2 -= 2;
                }
                i2 += 2;
            } else {
                return;
            }
        }
    }

    @Override // j.y
    public void a(n nVar, boolean z2) {
        if (nVar instanceof SubMenuC0138F) {
            ((SubMenuC0138F) nVar).f1592z.k().c(false);
        }
        y yVar = ((C0184k) this.f8b).f1955e;
        if (yVar != null) {
            yVar.a(nVar, z2);
        }
    }

    @Override // H.InterfaceC0010d
    public void b(Bundle bundle) {
        ((ContentInfo.Builder) this.f8b).setExtras(bundle);
    }

    @Override // j.y
    public boolean c(n nVar) {
        C0184k c0184k = (C0184k) this.f8b;
        if (nVar == c0184k.f1953c) {
            return false;
        }
        ((SubMenuC0138F) nVar).f1591A.getClass();
        c0184k.getClass();
        y yVar = c0184k.f1955e;
        if (yVar == null) {
            return false;
        }
        return yVar.c(nVar);
    }

    @Override // H.InterfaceC0010d
    public void e(Uri uri) {
        ((ContentInfo.Builder) this.f8b).setLinkUri(uri);
    }

    @Override // k.H0
    public void f(n nVar, j.p pVar) {
        ViewOnKeyListenerC0146h viewOnKeyListenerC0146h = (ViewOnKeyListenerC0146h) this.f8b;
        C0145g c0145g = null;
        viewOnKeyListenerC0146h.f1625f.removeCallbacksAndMessages(null);
        ArrayList arrayList = viewOnKeyListenerC0146h.h;
        int size = arrayList.size();
        int i2 = 0;
        while (true) {
            if (i2 < size) {
                if (nVar == ((C0145g) arrayList.get(i2)).f1619b) {
                    break;
                } else {
                    i2++;
                }
            } else {
                i2 = -1;
                break;
            }
        }
        if (i2 == -1) {
            return;
        }
        int i3 = i2 + 1;
        if (i3 < arrayList.size()) {
            c0145g = (C0145g) arrayList.get(i3);
        }
        viewOnKeyListenerC0146h.f1625f.postAtTime(new RunnableC0144f(this, c0145g, pVar, nVar), nVar, SystemClock.uptimeMillis() + 200);
    }

    @Override // H.InterfaceC0012f
    public int g() {
        int source;
        source = ((ContentInfo) this.f8b).getSource();
        return source;
    }

    @Override // Z.h
    public void h(boolean z2) {
        MainActivity mainActivity = ((Z.n) this.f8b).f720b;
        MainActivity.l(mainActivity, false);
        if (!z2) {
            MainActivity.f(mainActivity).c();
        }
        MainActivity.n(mainActivity);
        MainActivity.j(mainActivity);
    }

    @Override // H.InterfaceC0012f
    public ClipData j() {
        ClipData clip;
        clip = ((ContentInfo) this.f8b).getClip();
        return clip;
    }

    @Override // j.l
    public void k(n nVar) {
        j.l lVar = ((ActionMenuView) this.f8b).f887v;
        if (lVar != null) {
            lVar.k(nVar);
        }
    }

    @Override // k.H0
    public void l(n nVar, j.p pVar) {
        ((ViewOnKeyListenerC0146h) this.f8b).f1625f.removeCallbacksAndMessages(nVar);
    }

    @Override // j.l
    public boolean m(n nVar, MenuItem menuItem) {
        boolean z2;
        InterfaceC0190n interfaceC0190n = ((ActionMenuView) this.f8b).f880A;
        if (interfaceC0190n == null) {
            return false;
        }
        Toolbar toolbar = ((X0) interfaceC0190n).f1858a;
        toolbar.f911G.b();
        b1 b1Var = toolbar.f913I;
        if (b1Var != null) {
            z2 = ((I) b1Var).f1366a.f1370b.f1472a.onMenuItemSelected(0, menuItem);
        } else {
            z2 = false;
        }
        if (!z2) {
            return false;
        }
        return true;
    }

    @Override // M.i
    public void n(C0.d dVar) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new ThreadFactoryC0032a("EmojiCompatInitializer"));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor.execute(new M.l(this, dVar, threadPoolExecutor, 0));
    }

    @Override // H.InterfaceC0010d
    public C0013g o() {
        ContentInfo build;
        build = ((ContentInfo.Builder) this.f8b).build();
        return new C0013g(new f(build));
    }

    @Override // T.e
    public void p(int i2, Serializable serializable) {
        String str;
        switch (i2) {
            case 1:
                str = "RESULT_INSTALL_SUCCESS";
                break;
            case 2:
                str = "RESULT_ALREADY_INSTALLED";
                break;
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                str = "RESULT_UNSUPPORTED_ART_VERSION";
                break;
            case 4:
                str = "RESULT_NOT_WRITABLE";
                break;
            case 5:
                str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                break;
            case 6:
                str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                break;
            case 7:
                str = "RESULT_IO_EXCEPTION";
                break;
            case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                str = "RESULT_PARSE_EXCEPTION";
                break;
            case 9:
            default:
                str = BuildConfig.FLAVOR;
                break;
            case 10:
                str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                break;
            case 11:
                str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                break;
        }
        if (i2 != 6 && i2 != 7 && i2 != 8) {
            Log.d("ProfileInstaller", str);
        } else {
            Log.e("ProfileInstaller", str, (Throwable) serializable);
        }
        ((ProfileInstallReceiver) this.f8b).setResultCode(i2);
    }

    @Override // H.InterfaceC0012f
    public int q() {
        int flags;
        flags = ((ContentInfo) this.f8b).getFlags();
        return flags;
    }

    @Override // u0.d
    public void s(u uVar) {
        switch (this.f7a) {
            case 14:
                String j2 = uVar.f2572g.j();
                boolean h = uVar.h();
                Z.p pVar = (Z.p) this.f8b;
                if (!h) {
                    pVar.a("Server error: " + uVar.f2569d);
                    return;
                }
                try {
                    J0.c cVar = new J0.c(j2);
                    if (cVar.f474a.containsKey("success") && cVar.d()) {
                        Object a2 = cVar.a("notes");
                        if (a2 instanceof J0.a) {
                            J0.a aVar = (J0.a) a2;
                            ArrayList arrayList = new ArrayList();
                            for (int i2 = 0; i2 < aVar.f471a.size(); i2++) {
                                J0.c b2 = aVar.b(i2);
                                arrayList.add(new r(b2.e("id"), b2.e("title"), BuildConfig.FLAVOR, true));
                            }
                            pVar.f723a.runOnUiThread(new E.a(pVar, arrayList, 6, false));
                            return;
                        }
                        throw J0.c.p("notes", "JSONArray");
                    }
                    pVar.a(cVar.g("Unknown error"));
                    return;
                } catch (J0.b unused) {
                    pVar.a("Error parsing server response");
                    return;
                }
            case 15:
                String j3 = uVar.f2572g.j();
                boolean h2 = uVar.h();
                t tVar = (t) this.f8b;
                if (!h2) {
                    tVar.f("Server error: " + uVar.f2569d);
                    return;
                }
                try {
                    J0.c cVar2 = new J0.c(j3);
                    if (cVar2.f474a.containsKey("success") && cVar2.d()) {
                        tVar.a();
                    } else {
                        tVar.f(cVar2.g("Access denied"));
                    }
                    return;
                } catch (J0.b unused2) {
                    tVar.f("Error parsing server response");
                    return;
                }
            case 16:
                String j4 = uVar.f2572g.j();
                boolean h3 = uVar.h();
                f fVar = (f) this.f8b;
                if (!h3) {
                    fVar.C("Server error: " + uVar.f2569d);
                    return;
                }
                try {
                    J0.c cVar3 = new J0.c(j4);
                    if (cVar3.f474a.containsKey("success") && cVar3.d()) {
                        Object a3 = cVar3.a("note");
                        if (a3 instanceof J0.c) {
                            J0.c cVar4 = (J0.c) a3;
                            r rVar = new r(cVar4.e("id"), cVar4.e("title"), cVar4.e("content"), true);
                            switch (fVar.f7a) {
                                case 11:
                                    ((EditNoteActivity) fVar.f8b).runOnUiThread(new E.a(fVar, rVar, 2, false));
                                    return;
                                default:
                                    ((s) ((E.b) fVar.f8b).f269b).f730a.runOnUiThread(new E.a(fVar, rVar, 8, false));
                                    return;
                            }
                        }
                        throw J0.c.p("note", "JSONObject");
                    }
                    fVar.C(cVar3.g("Unknown error"));
                    return;
                } catch (J0.b unused3) {
                    fVar.C("Error parsing server response");
                    return;
                }
            case 17:
                String j5 = uVar.f2572g.j();
                boolean h4 = uVar.h();
                E.c cVar5 = (E.c) this.f8b;
                if (!h4) {
                    cVar5.e("Server error: " + uVar.f2569d);
                    return;
                }
                try {
                    J0.c cVar6 = new J0.c(j5);
                    if (cVar6.f474a.containsKey("success") && cVar6.d()) {
                        ((EditNoteActivity) cVar5.f272c).runOnUiThread(new M.r(1, cVar5));
                    } else {
                        cVar5.e(cVar6.g("Unknown error"));
                    }
                    return;
                } catch (J0.b unused4) {
                    cVar5.e("Error parsing server response");
                    return;
                }
            default:
                String j6 = uVar.f2572g.j();
                boolean h5 = uVar.h();
                s sVar = (s) this.f8b;
                if (!h5) {
                    sVar.b("Server error: " + uVar.f2569d);
                    return;
                }
                try {
                    J0.c cVar7 = new J0.c(j6);
                    if (cVar7.f474a.containsKey("success") && cVar7.d()) {
                        sVar.f730a.runOnUiThread(new E.b(5, sVar));
                    } else {
                        sVar.b(cVar7.g("Unknown error"));
                    }
                    return;
                } catch (J0.b unused5) {
                    sVar.b("Error parsing server response");
                    return;
                }
        }
    }

    @Override // T.e
    public void t() {
        Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
    }

    public String toString() {
        switch (this.f7a) {
            case 2:
                return "ContentInfoCompat{" + ((ContentInfo) this.f8b) + "}";
            default:
                return super.toString();
        }
    }

    @Override // M.p
    public boolean u(CharSequence charSequence, int i2, int i3, M.y yVar) {
        if (TextUtils.equals(charSequence.subSequence(i2, i3), (String) this.f8b)) {
            yVar.f581c = (yVar.f581c & 3) | 4;
            return false;
        }
        return true;
    }

    @Override // H.InterfaceC0012f
    public ContentInfo v() {
        return (ContentInfo) this.f8b;
    }

    @Override // u0.d
    public void w(IOException iOException) {
        switch (this.f7a) {
            case 14:
                ((Z.p) this.f8b).a("Network error: " + iOException.getMessage());
                return;
            case 15:
                ((t) this.f8b).f("Network error: " + iOException.getMessage());
                return;
            case 16:
                ((f) this.f8b).C("Network error: " + iOException.getMessage());
                return;
            case 17:
                ((E.c) this.f8b).e("Network error: " + iOException.getMessage());
                return;
            default:
                ((s) this.f8b).b("Network error: " + iOException.getMessage());
                return;
        }
    }

    @Override // H.InterfaceC0010d
    public void y(int i2) {
        ((ContentInfo.Builder) this.f8b).setFlags(i2);
    }

    public void z(String str, String str2) {
        AbstractC0150d.e(str, "name");
        AbstractC0150d.e(str2, "value");
        ArrayList arrayList = (ArrayList) this.f8b;
        arrayList.add(str);
        arrayList.add(q0.d.P(str2).toString());
    }

    public f(S s2, P p2) {
        this.f7a = 20;
        AbstractC0150d.e(s2, "store");
        Q.a aVar = Q.a.f609b;
        AbstractC0150d.e(aVar, "defaultCreationExtras");
        this.f8b = new D0.h(s2, p2, aVar);
    }

    public f(TextView textView) {
        this.f7a = 9;
        this.f8b = new O.g(textView);
    }

    public f(EditText editText) {
        this.f7a = 8;
        this.f8b = new E.c(editText);
    }

    public f(Context context) {
        this.f7a = 6;
        this.f8b = context.getApplicationContext();
    }

    public f(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.f7a = 4;
        if (Build.VERSION.SDK_INT >= 25) {
            this.f8b = new J.g(uri, clipDescription, uri2);
        } else {
            this.f8b = new D0.h(uri, clipDescription, uri2, 1);
        }
    }

    public f(int i2) {
        this.f7a = i2;
        switch (i2) {
            case 27:
                this.f8b = new LinkedHashMap(0, 0.75f, true);
                return;
            case 28:
                TimeUnit timeUnit = TimeUnit.MINUTES;
                AbstractC0150d.e(timeUnit, "timeUnit");
                this.f8b = new y0.l(x0.d.h, timeUnit);
                return;
            case 29:
                this.f8b = new ArrayList(20);
                return;
            default:
                return;
        }
    }

    public f(ContentInfo contentInfo) {
        this.f7a = 2;
        contentInfo.getClass();
        this.f8b = AbstractC0009c.e(contentInfo);
    }

    public f(ClipData clipData, int i2) {
        this.f7a = 1;
        this.f8b = AbstractC0009c.c(clipData, i2);
    }

    @Override // M.p
    public Object i() {
        return this;
    }

    @Override // k.InterfaceC0165a0
    public void d(int i2) {
    }

    @Override // k.InterfaceC0165a0
    public void x(int i2) {
    }

    @Override // k.InterfaceC0165a0
    public void r(int i2, float f2) {
    }
}

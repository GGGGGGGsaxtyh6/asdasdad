package Z;

import android.content.SharedPreferences;
import android.util.Log;
import g.AbstractActivityC0112j;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.conscrypt.BuildConfig;

/* loaded from: classes.dex */
public final class u {

    /* renamed from: e, reason: collision with root package name */
    public static final u0.o f731e;

    /* renamed from: a, reason: collision with root package name */
    public final AbstractActivityC0112j f732a;

    /* renamed from: b, reason: collision with root package name */
    public final u0.q f733b;

    /* renamed from: c, reason: collision with root package name */
    public final i f734c;

    /* renamed from: d, reason: collision with root package name */
    public String f735d;

    static {
        Pattern pattern = u0.o.f2491c;
        f731e = C0.m.n("application/json; charset=utf-8");
    }

    public u(AbstractActivityC0112j abstractActivityC0112j) {
        u0.q qVar;
        this.f732a = abstractActivityC0112j;
        try {
            TrustManager[] trustManagerArr = {new f(1)};
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
            u0.p pVar = new u0.p();
            pVar.a(socketFactory, (X509TrustManager) trustManagerArr[0]);
            pVar.f2511r = new c(1);
            qVar = new u0.q(pVar);
        } catch (Exception unused) {
            qVar = new u0.q();
        }
        this.f733b = qVar;
        this.f734c = i.a(abstractActivityC0112j);
        g();
    }

    public final void a(String str, t tVar) {
        if (!e()) {
            tVar.f("Server not configured");
            return;
        }
        i iVar = this.f734c;
        if (!iVar.b()) {
            tVar.f("User not logged in");
            return;
        }
        E.c cVar = iVar.f705c;
        String str2 = this.f735d + "/notes/" + str + "/check-permission";
        u0.s sVar = new u0.s();
        sVar.d(str2);
        sVar.b("Authorization", "Bearer ".concat((String) cVar.f272c));
        B0.h a2 = sVar.a();
        u0.q qVar = this.f733b;
        qVar.getClass();
        new y0.h(qVar, a2).e(new A.f(15, tVar));
    }

    public final void b(String str, A.f fVar) {
        if (!e()) {
            fVar.C("Server not configured");
        } else if (!this.f734c.b()) {
            fVar.C("User not logged in");
        } else {
            a(str, new D0.h(this, str, fVar, 6, false));
        }
    }

    public final r c(String str) {
        File file = new File(this.f732a.getFilesDir(), A.e.d("notes/", str, ".note"));
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[(int) file.length()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            J0.c cVar = new J0.c(new String(bArr, "UTF-8"));
            return new r(str, cVar.e("title"), cVar.e("content"), false);
        } catch (Exception unused) {
            return null;
        }
    }

    public final ArrayList d() {
        ArrayList arrayList = new ArrayList();
        File file = new File(this.f732a.getFilesDir(), "notes");
        if (!file.exists()) {
            file.mkdirs();
            return arrayList;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.getName().endsWith(".note")) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(file2);
                        byte[] bArr = new byte[(int) file2.length()];
                        fileInputStream.read(bArr);
                        fileInputStream.close();
                        String str = new String(bArr, "UTF-8");
                        String replace = file2.getName().replace(".note", BuildConfig.FLAVOR);
                        J0.c cVar = new J0.c(str);
                        arrayList.add(new r(replace, cVar.e("title"), cVar.e("content"), false));
                    } catch (Exception e2) {
                        Log.e("Error", "Error reading note file: " + file2.getName(), e2);
                    }
                }
            }
        }
        return arrayList;
    }

    public final boolean e() {
        String str = this.f735d;
        if (str != null && !str.isEmpty()) {
            return true;
        }
        return false;
    }

    public final boolean f(r rVar) {
        File file = new File(this.f732a.getFilesDir(), "notes");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, rVar.f726a + ".note");
        try {
            J0.c cVar = new J0.c();
            cVar.h(rVar.f726a, "id");
            cVar.h(rVar.f727b, "title");
            cVar.h(rVar.f728c, "content");
            String cVar2 = cVar.toString();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write(cVar2.getBytes("UTF-8"));
            fileOutputStream.close();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public final void g() {
        SharedPreferences sharedPreferences = this.f732a.getSharedPreferences("ServerConfig", 0);
        String string = sharedPreferences.getString("SERVER_IP", BuildConfig.FLAVOR);
        String string2 = sharedPreferences.getString("SERVER_PORT", BuildConfig.FLAVOR);
        if (!string.isEmpty()) {
            this.f735d = "http://".concat(string);
            if (!string2.isEmpty()) {
                this.f735d += ":" + string2;
            }
            this.f735d += "/api";
        } else {
            this.f735d = BuildConfig.FLAVOR;
        }
        Log.d("NoteManager", "Server URL updated to: " + this.f735d);
    }
}

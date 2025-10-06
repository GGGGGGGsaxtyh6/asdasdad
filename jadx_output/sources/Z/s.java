package Z;

import android.os.Handler;
import android.os.Looper;
import htb.d3vnu11.securenotes.NoteActivity;

/* loaded from: classes.dex */
public final class s implements t {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ NoteActivity f730a;

    public /* synthetic */ s(NoteActivity noteActivity) {
        this.f730a = noteActivity;
    }

    @Override // Z.t
    public void a() {
        new Handler(Looper.getMainLooper()).postDelayed(new E.b(4, this), 500L);
    }

    public void b(String str) {
        this.f730a.runOnUiThread(new E.a(this, str, 11, false));
    }

    @Override // Z.t
    public void f(String str) {
        this.f730a.runOnUiThread(new E.a(this, str, 10, false));
    }
}

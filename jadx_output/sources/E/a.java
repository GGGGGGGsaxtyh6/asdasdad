package E;

import Z.r;
import Z.s;
import android.app.Application;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;
import htb.d3vnu11.securenotes.EditNoteActivity;
import htb.d3vnu11.securenotes.MainActivity;
import htb.d3vnu11.securenotes.NoteActivity;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import k.U;
import org.conscrypt.FileClientSessionCache;
import org.conscrypt.ct.CTConstants;
import x.C0255a;

/* loaded from: classes.dex */
public final class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f265a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f266b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f267c;

    public /* synthetic */ a(Object obj, Object obj2, int i2) {
        this.f265a = i2;
        this.f266b = obj;
        this.f267c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj = this.f266b;
        Object obj2 = this.f267c;
        switch (this.f265a) {
            case 0:
                U u2 = (U) ((A.f) obj).f8b;
                if (u2 != null) {
                    u2.b((Typeface) obj2);
                    return;
                }
                return;
            case 1:
                ((i) obj).a(obj2);
                return;
            case 2:
                A.f fVar = (A.f) obj2;
                EditNoteActivity editNoteActivity = (EditNoteActivity) fVar.f8b;
                int i2 = EditNoteActivity.f1484j;
                editNoteActivity.getClass();
                r rVar = (r) obj;
                ((EditNoteActivity) fVar.f8b).f1485a.setText(rVar.f727b);
                ((EditNoteActivity) fVar.f8b).f1486b.setText(rVar.f728c);
                CheckBox checkBox = ((EditNoteActivity) fVar.f8b).f1492i;
                if (checkBox != null) {
                    checkBox.setChecked(true);
                    return;
                }
                return;
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                A.f fVar2 = (A.f) obj2;
                EditNoteActivity editNoteActivity2 = (EditNoteActivity) fVar2.f8b;
                int i3 = EditNoteActivity.f1484j;
                editNoteActivity2.getClass();
                Toast.makeText((EditNoteActivity) fVar2.f8b, "Error loading cloud note: " + ((String) obj), 1).show();
                ((EditNoteActivity) fVar2.f8b).finish();
                return;
            case 4:
                Z.l lVar = (Z.l) obj2;
                lVar.f715b.h.setVisibility(8);
                lVar.f715b.f1497e.setEnabled(true);
                lVar.f715b.f1498f.setEnabled(true);
                lVar.f715b.f1499g.setEnabled(true);
                Toast.makeText(lVar.f715b, "Login failed: " + ((String) obj), 1).show();
                return;
            case 5:
                Z.l lVar2 = (Z.l) obj2;
                lVar2.f715b.h.setVisibility(8);
                lVar2.f715b.f1497e.setEnabled(true);
                lVar2.f715b.f1498f.setEnabled(true);
                lVar2.f715b.f1499g.setEnabled(true);
                Toast.makeText(lVar2.f715b, "Registration failed: " + ((String) obj), 1).show();
                return;
            case 6:
                Iterator it = ((ArrayList) obj).iterator();
                while (true) {
                    boolean hasNext = it.hasNext();
                    MainActivity mainActivity = ((Z.p) obj2).f723a;
                    if (hasNext) {
                        r rVar2 = (r) it.next();
                        Iterator it2 = MainActivity.i(mainActivity).iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                r rVar3 = (r) it2.next();
                                if (!rVar3.f726a.equals(rVar2.f726a) || rVar3.f729d != rVar2.f729d) {
                                }
                            } else {
                                MainActivity.i(mainActivity).add(rVar2);
                            }
                        }
                    } else {
                        MainActivity.o(mainActivity);
                        MainActivity.l(mainActivity, false);
                        return;
                    }
                }
                break;
            case 7:
                Z.p pVar = (Z.p) obj2;
                Toast.makeText(pVar.f723a, "Error: " + ((String) obj), 0).show();
                MainActivity mainActivity2 = pVar.f723a;
                MainActivity.o(mainActivity2);
                MainActivity.l(mainActivity2, false);
                return;
            case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                A.f fVar3 = (A.f) obj2;
                ((s) ((b) fVar3.f8b).f269b).f730a.f1506e.setVisibility(8);
                NoteActivity noteActivity = ((s) ((b) fVar3.f8b).f269b).f730a;
                r rVar4 = (r) obj;
                noteActivity.f1508g = rVar4;
                noteActivity.f1504c.setText(rVar4.f727b);
                noteActivity.f1505d.setText(rVar4.f728c);
                return;
            case 9:
                A.f fVar4 = (A.f) obj2;
                ((s) ((b) fVar4.f8b).f269b).f730a.f1506e.setVisibility(8);
                ((s) ((b) fVar4.f8b).f269b).f730a.e("Error: " + ((String) obj));
                return;
            case 10:
                s sVar = (s) obj2;
                sVar.f730a.f1506e.setVisibility(8);
                sVar.f730a.e("Access denied: " + ((String) obj));
                sVar.f730a.finish();
                return;
            case 11:
                s sVar2 = (s) obj2;
                NoteActivity noteActivity2 = sVar2.f730a;
                int i4 = NoteActivity.h;
                noteActivity2.f(false);
                sVar2.f730a.e("Failed to delete note: " + ((String) obj));
                return;
            case FileClientSessionCache.MAX_SIZE /* 12 */:
                ((C0255a) obj).f2818a = obj2;
                return;
            case 13:
                ((Application) obj).unregisterActivityLifecycleCallbacks((C0255a) obj2);
                return;
            default:
                try {
                    Method method = x.b.f2827d;
                    if (method != null) {
                        method.invoke(obj, obj2, Boolean.FALSE, "AppCompat recreation");
                    } else {
                        x.b.f2828e.invoke(obj, obj2, Boolean.FALSE);
                    }
                    return;
                } catch (RuntimeException e2) {
                    if (e2.getClass() == RuntimeException.class && e2.getMessage() != null && e2.getMessage().startsWith("Unable to stop")) {
                        throw e2;
                    }
                    return;
                } catch (Throwable th) {
                    Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
                    return;
                }
        }
    }

    public /* synthetic */ a(Object obj, Object obj2, int i2, boolean z2) {
        this.f265a = i2;
        this.f267c = obj;
        this.f266b = obj2;
    }
}

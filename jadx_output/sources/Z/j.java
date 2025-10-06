package Z;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import htb.d3vnu11.securenotes.EditNoteActivity;
import java.util.UUID;
import k.C0213z;

/* loaded from: classes.dex */
public final class j implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f710a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ EditNoteActivity f711b;

    public /* synthetic */ j(EditNoteActivity editNoteActivity, int i2) {
        this.f710a = i2;
        this.f711b = editNoteActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean z2;
        String str;
        switch (this.f710a) {
            case 0:
                EditNoteActivity editNoteActivity = this.f711b;
                String trim = editNoteActivity.f1485a.getText().toString().trim();
                String trim2 = editNoteActivity.f1486b.getText().toString().trim();
                CheckBox checkBox = editNoteActivity.f1492i;
                if (checkBox != null) {
                    z2 = checkBox.isChecked();
                } else {
                    z2 = false;
                }
                if (trim.isEmpty()) {
                    Toast.makeText(editNoteActivity, "Title cannot be empty", 0).show();
                    return;
                }
                if (editNoteActivity.h) {
                    editNoteActivity.f1489e.getClass();
                    str = UUID.randomUUID().toString();
                } else {
                    str = editNoteActivity.f1491g;
                }
                r rVar = new r(str, trim, trim2, z2);
                if (z2 && !editNoteActivity.f1490f.b()) {
                    Toast.makeText(editNoteActivity, "Please register or login to save notes to the cloud", 1).show();
                    if (editNoteActivity.f1489e.f(rVar)) {
                        Toast.makeText(editNoteActivity, "Note saved locally instead", 0).show();
                        editNoteActivity.setResult(-1);
                        editNoteActivity.finish();
                        return;
                    }
                    Toast.makeText(editNoteActivity, "Error saving note", 0).show();
                    return;
                }
                if (z2 && editNoteActivity.f1490f.b()) {
                    E.c cVar = editNoteActivity.f1490f.f705c;
                    if (cVar != null) {
                        Log.d("EditNoteActivity", "Attempting to save with token: ".concat((String) cVar.f272c));
                    } else {
                        Log.d("EditNoteActivity", "Current user is null!");
                    }
                    u uVar = editNoteActivity.f1489e;
                    E.c cVar2 = new E.c(editNoteActivity, rVar, 4, false);
                    if (!uVar.e()) {
                        cVar2.e("Server not configured");
                        return;
                    }
                    i iVar = uVar.f734c;
                    if (!iVar.b()) {
                        cVar2.e("User not logged in");
                        return;
                    }
                    E.c cVar3 = iVar.f705c;
                    String str2 = uVar.f735d + "/notes";
                    try {
                        J0.c cVar4 = new J0.c();
                        cVar4.h(str, "id");
                        cVar4.h(trim, "title");
                        cVar4.h(trim2, "content");
                        C0213z b2 = C0213z.b(u.f731e, cVar4.toString());
                        u0.s sVar = new u0.s();
                        sVar.d(str2);
                        sVar.b("Authorization", "Bearer ".concat((String) cVar3.f272c));
                        sVar.c("POST", b2);
                        B0.h a2 = sVar.a();
                        u0.q qVar = uVar.f733b;
                        qVar.getClass();
                        new y0.h(qVar, a2).e(new A.f(17, cVar2));
                        return;
                    } catch (J0.b unused) {
                        cVar2.e("Error preparing request");
                        return;
                    }
                }
                if (editNoteActivity.f1489e.f(rVar)) {
                    Toast.makeText(editNoteActivity, "Note saved locally", 0).show();
                    editNoteActivity.setResult(-1);
                    editNoteActivity.finish();
                    return;
                }
                Toast.makeText(editNoteActivity, "Error saving note", 0).show();
                return;
            default:
                this.f711b.finish();
                return;
        }
    }
}

package Z;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import htb.d3vnu11.securenotes.EditNoteActivity;
import htb.d3vnu11.securenotes.LoginActivity;
import htb.d3vnu11.securenotes.MainActivity;
import org.conscrypt.ct.CTConstants;

/* loaded from: classes.dex */
public final class n implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f719a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MainActivity f720b;

    public /* synthetic */ n(MainActivity mainActivity, int i2) {
        this.f719a = i2;
        this.f720b = mainActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        i iVar;
        u uVar;
        i iVar2;
        i iVar3;
        switch (this.f719a) {
            case 0:
                MainActivity mainActivity = this.f720b;
                iVar = mainActivity.accountManager;
                if (!iVar.b()) {
                    uVar = mainActivity.noteManager;
                    if (uVar.d().size() >= 3) {
                        Toast.makeText(mainActivity, "Please log in to create more notes", 1).show();
                        return;
                    }
                }
                mainActivity.startActivityForResult(new Intent(mainActivity, (Class<?>) EditNoteActivity.class), 2);
                return;
            case 1:
                this.f720b.showServerConfigDialog();
                return;
            case 2:
                MainActivity mainActivity2 = this.f720b;
                iVar2 = mainActivity2.accountManager;
                iVar2.c();
                mainActivity2.updateLoginButton();
                mainActivity2.loadNotes();
                return;
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                MainActivity mainActivity3 = this.f720b;
                mainActivity3.showLoadingState(true);
                iVar3 = mainActivity3.accountManager;
                iVar3.f(new A.f(12, this));
                return;
            default:
                MainActivity mainActivity4 = this.f720b;
                mainActivity4.startActivityForResult(new Intent(mainActivity4, (Class<?>) LoginActivity.class), 1);
                return;
        }
    }
}

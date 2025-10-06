package htb.d3vnu11.securenotes;

import A.e;
import A.f;
import B0.h;
import E.c;
import Z.i;
import Z.r;
import Z.s;
import Z.u;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import g.AbstractActivityC0112j;
import java.io.File;
import org.conscrypt.R;
import u0.q;
import v0.b;

/* loaded from: classes.dex */
public class NoteActivity extends AbstractActivityC0112j {
    public static final /* synthetic */ int h = 0;

    /* renamed from: a, reason: collision with root package name */
    public String f1502a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f1503b;

    /* renamed from: c, reason: collision with root package name */
    public TextView f1504c;

    /* renamed from: d, reason: collision with root package name */
    public TextView f1505d;

    /* renamed from: e, reason: collision with root package name */
    public ProgressBar f1506e;

    /* renamed from: f, reason: collision with root package name */
    public u f1507f;

    /* renamed from: g, reason: collision with root package name */
    public r f1508g = null;

    public final void e(String str) {
        Toast.makeText(this, str, 1).show();
    }

    public final void f(boolean z2) {
        int i2;
        ProgressBar progressBar = this.f1506e;
        if (progressBar != null) {
            if (z2) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            progressBar.setVisibility(i2);
        }
    }

    @Override // androidx.fragment.app.i, a.AbstractActivityC0046m, x.f, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_note);
        this.f1504c = (TextView) findViewById(R.id.noteTitleTextView);
        this.f1505d = (TextView) findViewById(R.id.noteContentTextView);
        this.f1506e = (ProgressBar) findViewById(R.id.loadingProgressBar);
        this.f1507f = new u(this);
        this.f1502a = getIntent().getStringExtra("noteId");
        boolean booleanExtra = getIntent().getBooleanExtra("isCloudNote", false);
        this.f1503b = booleanExtra;
        String str = this.f1502a;
        if (str == null) {
            Toast.makeText(this, "Error: Note not found", 1).show();
            finish();
            return;
        }
        if (booleanExtra) {
            this.f1506e.setVisibility(0);
            this.f1507f.a(this.f1502a, new s(this));
            return;
        }
        r c2 = this.f1507f.c(str);
        if (c2 != null) {
            this.f1508g = c2;
            this.f1504c.setText(c2.f727b);
            this.f1505d.setText(c2.f728c);
        } else {
            e("Error: Note not found");
            finish();
        }
    }

    @Override // android.app.Activity
    public final boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_edit) {
            Intent intent = new Intent(this, (Class<?>) EditNoteActivity.class);
            intent.putExtra("noteId", this.f1502a);
            intent.putExtra("isCloudNote", this.f1503b);
            startActivity(intent);
            return true;
        }
        if (itemId == R.id.action_delete) {
            if (this.f1508g == null) {
                e("No note to delete");
            } else {
                f(true);
                if (this.f1503b) {
                    u uVar = this.f1507f;
                    String str = this.f1502a;
                    s sVar = new s(this);
                    if (!uVar.e()) {
                        sVar.b("Server not configured");
                    } else {
                        i iVar = uVar.f734c;
                        if (!iVar.b()) {
                            sVar.b("User not logged in");
                        } else {
                            c cVar = iVar.f705c;
                            String str2 = uVar.f735d + "/notes/" + str;
                            u0.s sVar2 = new u0.s();
                            sVar2.d(str2);
                            sVar2.b("Authorization", "Bearer ".concat((String) cVar.f272c));
                            sVar2.c("DELETE", b.f2811d);
                            h a2 = sVar2.a();
                            q qVar = uVar.f733b;
                            qVar.getClass();
                            new y0.h(qVar, a2).e(new f(18, sVar));
                        }
                    }
                } else {
                    u uVar2 = this.f1507f;
                    String str3 = this.f1502a;
                    uVar2.getClass();
                    boolean delete = new File(uVar2.f732a.getFilesDir(), e.d("notes/", str3, ".note")).delete();
                    f(false);
                    if (delete) {
                        Toast.makeText(this, "Note deleted successfully", 0).show();
                        setResult(-1);
                        finish();
                    } else {
                        e("Failed to delete local note");
                    }
                }
            }
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}

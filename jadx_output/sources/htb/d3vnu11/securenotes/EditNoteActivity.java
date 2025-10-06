package htb.d3vnu11.securenotes;

import A.f;
import Z.i;
import Z.j;
import Z.r;
import Z.u;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import g.AbstractActivityC0112j;
import org.conscrypt.R;

/* loaded from: classes.dex */
public class EditNoteActivity extends AbstractActivityC0112j {

    /* renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ int f1484j = 0;

    /* renamed from: a, reason: collision with root package name */
    public EditText f1485a;

    /* renamed from: b, reason: collision with root package name */
    public EditText f1486b;

    /* renamed from: c, reason: collision with root package name */
    public Button f1487c;

    /* renamed from: d, reason: collision with root package name */
    public Button f1488d;

    /* renamed from: e, reason: collision with root package name */
    public u f1489e;

    /* renamed from: f, reason: collision with root package name */
    public i f1490f;

    /* renamed from: g, reason: collision with root package name */
    public String f1491g;
    public boolean h;

    /* renamed from: i, reason: collision with root package name */
    public CheckBox f1492i;

    @Override // androidx.fragment.app.i, a.AbstractActivityC0046m, x.f, android.app.Activity
    public final void onCreate(Bundle bundle) {
        boolean z2;
        super.onCreate(bundle);
        setContentView(R.layout.activity_edit_note);
        this.f1489e = new u(this);
        this.f1490f = new i(this);
        this.f1485a = (EditText) findViewById(R.id.editNoteTitleEditText);
        this.f1486b = (EditText) findViewById(R.id.editNoteContentEditText);
        this.f1487c = (Button) findViewById(R.id.saveButton);
        this.f1488d = (Button) findViewById(R.id.cancelButton);
        this.f1492i = (CheckBox) findViewById(R.id.cloudSaveCheckBox);
        String stringExtra = getIntent().getStringExtra("noteId");
        this.f1491g = stringExtra;
        if (stringExtra == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.h = z2;
        if (z2) {
            setTitle("Create New Note");
        } else {
            setTitle("Edit Note");
            if (!this.h) {
                if (getIntent().getBooleanExtra("isCloudNote", false)) {
                    this.f1489e.b(this.f1491g, new f(11, this));
                } else {
                    r c2 = this.f1489e.c(this.f1491g);
                    if (c2 != null) {
                        this.f1485a.setText(c2.f727b);
                        this.f1486b.setText(c2.f728c);
                    } else {
                        Toast.makeText(this, "Error: Could not load note", 1).show();
                        finish();
                    }
                }
            }
        }
        this.f1487c.setOnClickListener(new j(this, 0));
        this.f1488d.setOnClickListener(new j(this, 1));
    }
}

package htb.d3vnu11.securenotes;

import A.f;
import B0.h;
import E.c;
import Z.i;
import Z.n;
import Z.o;
import Z.p;
import Z.r;
import Z.u;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import g.AbstractActivityC0112j;
import htb.d3vnu11.securenotes.MainActivity;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.conscrypt.BuildConfig;
import org.conscrypt.R;
import u0.q;
import u0.s;

/* loaded from: classes.dex */
public class MainActivity extends AbstractActivityC0112j {
    private static final String KEY_SERVER_IP = "SERVER_IP";
    private static final String KEY_SERVER_PORT = "SERVER_PORT";
    private static final String SERVER_CONFIG_PREFS = "ServerConfig";
    private static final String TAG = "MainActivity";
    private i accountManager;
    private ArrayAdapter<String> adapter;
    private Button createNoteButton;
    private ProgressBar loadingIndicator;
    private Button loginButton;
    private ListView noteListView;
    private u noteManager;
    private List<r> notes = new ArrayList();
    private Button serverConfigButton;

    private void checkServerConfig() {
        if (getSharedPreferences(SERVER_CONFIG_PREFS, 0).getString(KEY_SERVER_IP, BuildConfig.FLAVOR).isEmpty()) {
            showServerConfigDialog();
        }
    }

    public /* synthetic */ void lambda$showServerConfigDialog$0(EditText editText, EditText editText2, DialogInterface dialogInterface, int i2) {
        String trim = editText.getText().toString().trim();
        String trim2 = editText2.getText().toString().trim();
        if (trim.isEmpty()) {
            Toast.makeText(this, "Please enter server IP", 0).show();
            return;
        }
        saveServerConfig(trim, trim2);
        updateLoginButton();
        loadNotes();
    }

    public void loadNotes() {
        showLoadingState(true);
        this.notes.clear();
        this.notes.addAll(this.noteManager.d());
        if (!this.accountManager.b()) {
            updateNotesList();
            showLoadingState(false);
            return;
        }
        u uVar = this.noteManager;
        p pVar = new p(this);
        if (!uVar.e()) {
            pVar.a("Server not configured");
            return;
        }
        i iVar = uVar.f734c;
        if (!iVar.b()) {
            pVar.a("User not logged in");
            return;
        }
        c cVar = iVar.f705c;
        String str = uVar.f735d + "/notes";
        s sVar = new s();
        sVar.d(str);
        sVar.b("Authorization", "Bearer ".concat((String) cVar.f272c));
        h a2 = sVar.a();
        q qVar = uVar.f733b;
        qVar.getClass();
        new y0.h(qVar, a2).e(new f(14, pVar));
    }

    public void openNote(r rVar) {
        Intent intent = new Intent(this, (Class<?>) NoteActivity.class);
        intent.putExtra("noteId", rVar.f726a);
        intent.putExtra("isCloudNote", rVar.f729d);
        startActivityForResult(intent, 3);
    }

    private void saveServerConfig(String str, String str2) {
        SharedPreferences.Editor edit = getSharedPreferences(SERVER_CONFIG_PREFS, 0).edit();
        edit.putString(KEY_SERVER_IP, str);
        edit.putString(KEY_SERVER_PORT, str2);
        edit.apply();
        this.accountManager.e();
        this.noteManager.g();
        Toast.makeText(this, "Server configuration updated", 0).show();
    }

    public void showLoadingState(boolean z2) {
        int i2;
        ProgressBar progressBar = this.loadingIndicator;
        if (progressBar != null) {
            if (z2) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            progressBar.setVisibility(i2);
        }
        this.loginButton.setEnabled(!z2);
        this.createNoteButton.setEnabled(!z2);
        this.noteListView.setEnabled(!z2);
        Button button = this.serverConfigButton;
        if (button != null) {
            button.setEnabled(!z2);
        }
    }

    public void showServerConfigDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(R.layout.dialog_server_config, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(R.id.serverIpEditText);
        final EditText editText2 = (EditText) inflate.findViewById(R.id.serverPortEditText);
        SharedPreferences sharedPreferences = getSharedPreferences(SERVER_CONFIG_PREFS, 0);
        String string = sharedPreferences.getString(KEY_SERVER_IP, BuildConfig.FLAVOR);
        String string2 = sharedPreferences.getString(KEY_SERVER_PORT, "3000");
        editText.setText(string);
        editText2.setText(string2);
        builder.setView(inflate).setTitle("Server Configuration").setPositiveButton("Save", new DialogInterface.OnClickListener() { // from class: Z.m
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                MainActivity.this.lambda$showServerConfigDialog$0(editText, editText2, dialogInterface, i2);
            }
        }).setNegativeButton("Cancel", (DialogInterface.OnClickListener) null);
        builder.create().show();
    }

    public void updateLoginButton() {
        String str;
        i a2 = i.a(getApplicationContext());
        this.accountManager = a2;
        boolean b2 = a2.b();
        c cVar = this.accountManager.f705c;
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder("updateLoginButton - isLoggedIn: ");
        sb.append(b2);
        sb.append(", currentUser: ");
        if (cVar != null) {
            str = (String) cVar.f271b;
        } else {
            str = "null";
        }
        sb.append(str);
        printStream.println(sb.toString());
        if (b2 && cVar != null) {
            this.loginButton.setText("Logout (" + ((String) cVar.f271b) + ")");
            this.loginButton.setOnClickListener(new n(this, 2));
            return;
        }
        if (cVar != null) {
            this.loginButton.setText("RECONNECT (" + ((String) cVar.f271b) + ")");
            this.loginButton.setOnClickListener(new n(this, 3));
            return;
        }
        this.loginButton.setText("LOGIN / REGISTER");
        this.loginButton.setOnClickListener(new n(this, 4));
    }

    public void updateNotesList() {
        String str;
        ArrayList arrayList = new ArrayList();
        for (r rVar : this.notes) {
            if (rVar.f729d) {
                str = "☁️ ";
            } else {
                str = "📱 ";
            }
            arrayList.add(str + rVar.f727b);
        }
        ArrayAdapter<String> arrayAdapter = this.adapter;
        if (arrayAdapter == null) {
            ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
            this.adapter = arrayAdapter2;
            this.noteListView.setAdapter((ListAdapter) arrayAdapter2);
        } else {
            arrayAdapter.clear();
            this.adapter.addAll(arrayList);
            this.adapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.fragment.app.i, a.AbstractActivityC0046m, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        updateLoginButton();
        loadNotes();
    }

    @Override // androidx.fragment.app.i, a.AbstractActivityC0046m, x.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        this.accountManager = i.a(getApplicationContext());
        this.noteManager = new u(this);
        this.noteListView = (ListView) findViewById(R.id.noteListView);
        this.createNoteButton = (Button) findViewById(R.id.createNoteButton);
        this.loginButton = (Button) findViewById(R.id.loginButton);
        this.serverConfigButton = (Button) findViewById(R.id.serverConfigButton);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadingIndicator);
        this.loadingIndicator = progressBar;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        checkServerConfig();
        loadNotes();
        this.createNoteButton.setOnClickListener(new n(this, 0));
        this.noteListView.setOnItemClickListener(new o(0, this));
        Button button = this.serverConfigButton;
        if (button != null) {
            button.setOnClickListener(new n(this, 1));
        }
        updateLoginButton();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_server_settings) {
            showServerConfigDialog();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // androidx.fragment.app.i, android.app.Activity
    public void onResume() {
        super.onResume();
        i a2 = i.a(getApplicationContext());
        this.accountManager = a2;
        if (a2.f705c != null) {
            showLoadingState(true);
            this.loginButton.setText("Verifying...");
            this.accountManager.f(new p(this));
        } else {
            updateLoginButton();
            loadNotes();
        }
    }
}

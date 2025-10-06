package htb.d3vnu11.securenotes;

import Z.i;
import Z.k;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import g.AbstractActivityC0112j;
import org.conscrypt.BuildConfig;
import org.conscrypt.R;

/* loaded from: classes.dex */
public class LoginActivity extends AbstractActivityC0112j {

    /* renamed from: a, reason: collision with root package name */
    public EditText f1493a;

    /* renamed from: b, reason: collision with root package name */
    public EditText f1494b;

    /* renamed from: c, reason: collision with root package name */
    public EditText f1495c;

    /* renamed from: d, reason: collision with root package name */
    public EditText f1496d;

    /* renamed from: e, reason: collision with root package name */
    public Button f1497e;

    /* renamed from: f, reason: collision with root package name */
    public Button f1498f;

    /* renamed from: g, reason: collision with root package name */
    public TextView f1499g;
    public ProgressBar h;

    /* renamed from: i, reason: collision with root package name */
    public i f1500i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f1501j = true;

    public static boolean e(LoginActivity loginActivity) {
        String trim = loginActivity.f1495c.getText().toString().trim();
        String trim2 = loginActivity.f1496d.getText().toString().trim();
        if (trim.isEmpty()) {
            Toast.makeText(loginActivity, "Please enter server IP", 0).show();
            return false;
        }
        SharedPreferences.Editor edit = loginActivity.getSharedPreferences("ServerConfig", 0).edit();
        edit.putString("SERVER_IP", trim);
        edit.putString("SERVER_PORT", trim2);
        edit.apply();
        loginActivity.f1500i.e();
        return true;
    }

    public final void f() {
        if (this.f1501j) {
            setTitle("Login");
            this.f1497e.setVisibility(0);
            this.f1498f.setVisibility(8);
            this.f1499g.setText("Don't have an account? Register");
            return;
        }
        setTitle("Register");
        this.f1497e.setVisibility(8);
        this.f1498f.setVisibility(0);
        this.f1499g.setText("Already have an account? Login");
    }

    @Override // androidx.fragment.app.i, a.AbstractActivityC0046m, x.f, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        this.f1500i = i.a(this);
        this.f1493a = (EditText) findViewById(R.id.emailEditText);
        this.f1494b = (EditText) findViewById(R.id.passwordEditText);
        this.f1495c = (EditText) findViewById(R.id.serverIpEditText);
        this.f1496d = (EditText) findViewById(R.id.serverPortEditText);
        this.f1497e = (Button) findViewById(R.id.loginButton);
        this.f1498f = (Button) findViewById(R.id.registerButton);
        this.f1499g = (TextView) findViewById(R.id.switchModeTextView);
        this.h = (ProgressBar) findViewById(R.id.loginProgressBar);
        SharedPreferences sharedPreferences = getSharedPreferences("ServerConfig", 0);
        String string = sharedPreferences.getString("SERVER_IP", BuildConfig.FLAVOR);
        String string2 = sharedPreferences.getString("SERVER_PORT", "3000");
        if (!string.isEmpty()) {
            this.f1495c.setText(string);
        }
        if (!string2.isEmpty()) {
            this.f1496d.setText(string2);
        }
        f();
        this.f1497e.setOnClickListener(new k(this, 0));
        this.f1498f.setOnClickListener(new k(this, 1));
        this.f1499g.setOnClickListener(new k(this, 2));
    }
}

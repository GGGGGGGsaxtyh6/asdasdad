package i;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;
import org.conscrypt.R;

/* renamed from: i.d, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0122d extends ContextWrapper {

    /* renamed from: f, reason: collision with root package name */
    public static Configuration f1511f;

    /* renamed from: a, reason: collision with root package name */
    public int f1512a;

    /* renamed from: b, reason: collision with root package name */
    public Resources.Theme f1513b;

    /* renamed from: c, reason: collision with root package name */
    public LayoutInflater f1514c;

    /* renamed from: d, reason: collision with root package name */
    public Configuration f1515d;

    /* renamed from: e, reason: collision with root package name */
    public Resources f1516e;

    public C0122d(Context context, int i2) {
        super(context);
        this.f1512a = i2;
    }

    public final void a(Configuration configuration) {
        if (this.f1516e == null) {
            if (this.f1515d == null) {
                this.f1515d = new Configuration(configuration);
                return;
            }
            throw new IllegalStateException("Override configuration has already been set");
        }
        throw new IllegalStateException("getResources() or getAssets() has already been called");
    }

    @Override // android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public final void b() {
        if (this.f1513b == null) {
            this.f1513b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f1513b.setTo(theme);
            }
        }
        this.f1513b.applyStyle(this.f1512a, true);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final AssetManager getAssets() {
        return getResources().getAssets();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0022, code lost:
    
        if (r0.equals(i.C0122d.f1511f) != false) goto L15;
     */
    @Override // android.content.ContextWrapper, android.content.Context
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.content.res.Resources getResources() {
        /*
            r3 = this;
            android.content.res.Resources r0 = r3.f1516e
            if (r0 != 0) goto L38
            android.content.res.Configuration r0 = r3.f1515d
            if (r0 == 0) goto L32
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 26
            if (r1 < r2) goto L25
            android.content.res.Configuration r1 = i.C0122d.f1511f
            if (r1 != 0) goto L1c
            android.content.res.Configuration r1 = new android.content.res.Configuration
            r1.<init>()
            r2 = 0
            r1.fontScale = r2
            i.C0122d.f1511f = r1
        L1c:
            android.content.res.Configuration r1 = i.C0122d.f1511f
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L25
            goto L32
        L25:
            android.content.res.Configuration r0 = r3.f1515d
            android.content.Context r0 = r3.createConfigurationContext(r0)
            android.content.res.Resources r0 = r0.getResources()
            r3.f1516e = r0
            goto L38
        L32:
            android.content.res.Resources r0 = super.getResources()
            r3.f1516e = r0
        L38:
            android.content.res.Resources r0 = r3.f1516e
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: i.C0122d.getResources():android.content.res.Resources");
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        if ("layout_inflater".equals(str)) {
            if (this.f1514c == null) {
                this.f1514c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
            }
            return this.f1514c;
        }
        return getBaseContext().getSystemService(str);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources.Theme getTheme() {
        Resources.Theme theme = this.f1513b;
        if (theme != null) {
            return theme;
        }
        if (this.f1512a == 0) {
            this.f1512a = R.style.Theme_AppCompat_Light;
        }
        b();
        return this.f1513b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i2) {
        if (this.f1512a != i2) {
            this.f1512a = i2;
            b();
        }
    }
}

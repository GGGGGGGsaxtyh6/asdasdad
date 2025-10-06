package x;

import C0.m;
import H.InterfaceC0017k;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.lifecycle.AbstractC0068o;
import androidx.lifecycle.B;
import androidx.lifecycle.D;
import androidx.lifecycle.EnumC0067n;
import androidx.lifecycle.InterfaceC0072t;
import androidx.lifecycle.v;
import j0.AbstractC0150d;

/* loaded from: classes.dex */
public abstract class f extends Activity implements InterfaceC0072t, InterfaceC0017k {
    private final n.k extraDataMap = new n.k(0);
    private final v lifecycleRegistry = new v(this);

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        AbstractC0150d.e(keyEvent, "event");
        View decorView = getWindow().getDecorView();
        AbstractC0150d.d(decorView, "window.decorView");
        if (m.g(decorView, keyEvent)) {
            return true;
        }
        return m.h(this, decorView, this, keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        AbstractC0150d.e(keyEvent, "event");
        View decorView = getWindow().getDecorView();
        AbstractC0150d.d(decorView, "window.decorView");
        if (m.g(decorView, keyEvent)) {
            return true;
        }
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    public <T extends e> T getExtraData(Class<T> cls) {
        AbstractC0150d.e(cls, "extraDataClass");
        if (this.extraDataMap.get(cls) == null) {
            return null;
        }
        throw new ClassCastException();
    }

    public AbstractC0068o getLifecycle() {
        return this.lifecycleRegistry;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i2 = D.f1150b;
        B.b(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        AbstractC0150d.e(bundle, "outState");
        v vVar = this.lifecycleRegistry;
        EnumC0067n enumC0067n = EnumC0067n.f1198c;
        vVar.d("setCurrentState");
        vVar.f(enumC0067n);
        super.onSaveInstanceState(bundle);
    }

    public void putExtraData(e eVar) {
        AbstractC0150d.e(eVar, "extraData");
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0021, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0030, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 29) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0039, code lost:
    
        if (r4.equals("--list-dumpables") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0049, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 33) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0042, code lost:
    
        if (r4.equals("--dump-dumpable") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0059, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 31) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean shouldDumpInternalState(java.lang.String[] r4) {
        /*
            r3 = this;
            r0 = 1
            r1 = 0
            if (r4 == 0) goto L5c
            int r2 = r4.length
            if (r2 != 0) goto L8
            goto L5c
        L8:
            r4 = r4[r1]
            int r2 = r4.hashCode()
            switch(r2) {
                case -645125871: goto L4c;
                case 100470631: goto L3c;
                case 472614934: goto L33;
                case 1159329357: goto L23;
                case 1455016274: goto L12;
                default: goto L11;
            }
        L11:
            goto L5c
        L12:
            java.lang.String r2 = "--autofill"
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L1b
            goto L5c
        L1b:
            int r4 = android.os.Build.VERSION.SDK_INT
            r2 = 26
            if (r4 < r2) goto L5c
        L21:
            r1 = r0
            goto L5c
        L23:
            java.lang.String r2 = "--contentcapture"
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L2c
            goto L5c
        L2c:
            int r4 = android.os.Build.VERSION.SDK_INT
            r2 = 29
            if (r4 < r2) goto L5c
            goto L21
        L33:
            java.lang.String r2 = "--list-dumpables"
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L45
            goto L5c
        L3c:
            java.lang.String r2 = "--dump-dumpable"
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L45
            goto L5c
        L45:
            int r4 = android.os.Build.VERSION.SDK_INT
            r2 = 33
            if (r4 < r2) goto L5c
            goto L21
        L4c:
            java.lang.String r2 = "--translation"
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L55
            goto L5c
        L55:
            int r4 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r4 < r2) goto L5c
            goto L21
        L5c:
            r4 = r1 ^ 1
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: x.f.shouldDumpInternalState(java.lang.String[]):boolean");
    }

    @Override // H.InterfaceC0017k
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        AbstractC0150d.e(keyEvent, "event");
        return super.dispatchKeyEvent(keyEvent);
    }
}

package androidx.profileinstaller;

import T.f;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import java.io.File;
import org.conscrypt.BuildConfig;

/* loaded from: classes.dex */
public class ProfileInstallReceiver extends BroadcastReceiver {
    /* JADX WARN: Type inference failed for: r7v10, types: [java.util.concurrent.Executor, java.lang.Object] */
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Bundle extras;
        File codeCacheDir;
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if ("androidx.profileinstaller.action.INSTALL_PROFILE".equals(action)) {
            f.t(context, new Object(), new A.f(10, this), true);
            return;
        }
        if ("androidx.profileinstaller.action.SKIP_FILE".equals(action)) {
            Bundle extras2 = intent.getExtras();
            if (extras2 != null) {
                String string = extras2.getString("EXTRA_SKIP_FILE_OPERATION");
                if ("WRITE_SKIP_FILE".equals(string)) {
                    A.f fVar = new A.f(10, this);
                    try {
                        f.e(context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0), context.getFilesDir());
                        fVar.p(10, null);
                        return;
                    } catch (PackageManager.NameNotFoundException e2) {
                        fVar.p(7, e2);
                        return;
                    }
                }
                if ("DELETE_SKIP_FILE".equals(string)) {
                    new File(context.getFilesDir(), "profileinstaller_profileWrittenFor_lastUpdateTime.dat").delete();
                    Log.d("ProfileInstaller", "RESULT_DELETE_SKIP_FILE_SUCCESS");
                    setResultCode(11);
                    return;
                }
                return;
            }
            return;
        }
        if ("androidx.profileinstaller.action.SAVE_PROFILE".equals(action)) {
            Process.sendSignal(Process.myPid(), 10);
            Log.d("ProfileInstaller", BuildConfig.FLAVOR);
            setResultCode(12);
            return;
        }
        if ("androidx.profileinstaller.action.BENCHMARK_OPERATION".equals(action) && (extras = intent.getExtras()) != null) {
            String string2 = extras.getString("EXTRA_BENCHMARK_OPERATION");
            A.f fVar2 = new A.f(10, this);
            if ("DROP_SHADER_CACHE".equals(string2)) {
                if (Build.VERSION.SDK_INT >= 34) {
                    codeCacheDir = context.createDeviceProtectedStorageContext().getCacheDir();
                } else {
                    codeCacheDir = context.createDeviceProtectedStorageContext().getCodeCacheDir();
                }
                if (f.c(codeCacheDir)) {
                    fVar2.p(14, null);
                    return;
                } else {
                    fVar2.p(15, null);
                    return;
                }
            }
            fVar2.p(16, null);
        }
    }
}

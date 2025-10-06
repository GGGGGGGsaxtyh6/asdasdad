package J;

import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;

/* loaded from: classes.dex */
public final class e extends InputConnectionWrapper {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d f468a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(InputConnection inputConnection, d dVar) {
        super(inputConnection, false);
        this.f468a = dVar;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i2, Bundle bundle) {
        A.f fVar = null;
        if (inputContentInfo != null && Build.VERSION.SDK_INT >= 25) {
            fVar = new A.f(4, new g(inputContentInfo));
        }
        if (this.f468a.a(fVar, i2, bundle)) {
            return true;
        }
        return super.commitContent(inputContentInfo, i2, bundle);
    }
}

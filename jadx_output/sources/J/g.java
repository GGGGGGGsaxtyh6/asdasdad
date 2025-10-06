package J;

import android.content.ClipDescription;
import android.net.Uri;
import android.view.inputmethod.InputContentInfo;

/* loaded from: classes.dex */
public final class g implements h {

    /* renamed from: a, reason: collision with root package name */
    public final InputContentInfo f470a;

    public g(Object obj) {
        this.f470a = (InputContentInfo) obj;
    }

    @Override // J.h
    public final ClipDescription b() {
        return this.f470a.getDescription();
    }

    @Override // J.h
    public final void c() {
        this.f470a.requestPermission();
    }

    @Override // J.h
    public final Uri d() {
        return this.f470a.getLinkUri();
    }

    @Override // J.h
    public final Object e() {
        return this.f470a;
    }

    @Override // J.h
    public final Uri g() {
        return this.f470a.getContentUri();
    }

    public g(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.f470a = new InputContentInfo(uri, clipDescription, uri2);
    }
}

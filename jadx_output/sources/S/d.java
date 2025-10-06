package S;

import androidx.fragment.app.i;
import androidx.lifecycle.S;

/* loaded from: classes.dex */
public final class d extends a {

    /* renamed from: a, reason: collision with root package name */
    public final i f619a;

    public d(i iVar, S s2) {
        this.f619a = iVar;
    }

    public final String toString() {
        int lastIndexOf;
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        i iVar = this.f619a;
        String simpleName = iVar.getClass().getSimpleName();
        if (simpleName.length() <= 0 && (lastIndexOf = (simpleName = iVar.getClass().getName()).lastIndexOf(46)) > 0) {
            simpleName = simpleName.substring(lastIndexOf + 1);
        }
        sb.append(simpleName);
        sb.append('{');
        sb.append(Integer.toHexString(System.identityHashCode(iVar)));
        sb.append("}}");
        return sb.toString();
    }
}

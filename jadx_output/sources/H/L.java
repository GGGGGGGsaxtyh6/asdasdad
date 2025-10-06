package H;

import android.view.ContentInfo;
import android.view.View;
import java.util.Objects;

/* loaded from: classes.dex */
public abstract class L {
    public static String[] a(View view) {
        return view.getReceiveContentMimeTypes();
    }

    public static C0013g b(View view, C0013g c0013g) {
        ContentInfo v2 = c0013g.f367a.v();
        Objects.requireNonNull(v2);
        ContentInfo performReceiveContent = view.performReceiveContent(v2);
        if (performReceiveContent == null) {
            return null;
        }
        if (performReceiveContent == v2) {
            return c0013g;
        }
        return new C0013g(new A.f(performReceiveContent));
    }
}

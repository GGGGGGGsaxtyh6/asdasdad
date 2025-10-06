package K;

import androidx.core.widget.NestedScrollView;

/* loaded from: classes.dex */
public abstract class f {
    public static void a(NestedScrollView nestedScrollView, float f2) {
        try {
            nestedScrollView.setFrameContentVelocity(f2);
        } catch (LinkageError unused) {
        }
    }
}

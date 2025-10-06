package J;

import android.view.inputmethod.EditorInfo;

/* loaded from: classes.dex */
public abstract class b {
    public static void a(EditorInfo editorInfo, CharSequence charSequence) {
        editorInfo.setInitialSurroundingSubText(charSequence, 0);
    }
}

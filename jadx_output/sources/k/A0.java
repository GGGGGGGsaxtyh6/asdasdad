package k;

import android.view.View;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
public abstract class A0 {
    public static int a(PopupWindow popupWindow, View view, int i2, boolean z2) {
        return popupWindow.getMaxAvailableHeight(view, i2, z2);
    }
}

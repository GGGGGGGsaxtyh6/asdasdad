package H;

import android.graphics.Rect;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import android.window.BackEvent;

/* renamed from: H.x, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0029x {
    public static AccessibilityNodeInfo.AccessibilityAction a() {
        return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_IN_DIRECTION;
    }

    public static float b(VelocityTracker velocityTracker, int i2) {
        return velocityTracker.getAxisVelocity(i2);
    }

    public static void c(AccessibilityNodeInfo accessibilityNodeInfo, Rect rect) {
        accessibilityNodeInfo.getBoundsInWindow(rect);
    }

    public static CharSequence d(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.getContainerTitle();
    }

    public static int e(ViewConfiguration viewConfiguration, int i2, int i3, int i4) {
        return viewConfiguration.getScaledMaximumFlingVelocity(i2, i3, i4);
    }

    public static int f(ViewConfiguration viewConfiguration, int i2, int i3, int i4) {
        return viewConfiguration.getScaledMinimumFlingVelocity(i2, i3, i4);
    }

    public static boolean g(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.isAccessibilityDataSensitive();
    }

    public static float h(BackEvent backEvent) {
        return backEvent.getProgress();
    }

    public static void i(TextView textView, int i2, float f2) {
        textView.setLineHeight(i2, f2);
    }

    public static int j(BackEvent backEvent) {
        return backEvent.getSwipeEdge();
    }

    public static float k(BackEvent backEvent) {
        return backEvent.getTouchX();
    }

    public static float l(BackEvent backEvent) {
        return backEvent.getTouchY();
    }
}

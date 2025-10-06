package I;

import H.AbstractC0029x;
import android.R;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;
import org.conscrypt.PSKKeyManager;

/* loaded from: classes.dex */
public final class d {

    /* renamed from: c, reason: collision with root package name */
    public static final d f456c;

    /* renamed from: d, reason: collision with root package name */
    public static final d f457d;

    /* renamed from: e, reason: collision with root package name */
    public static final d f458e;

    /* renamed from: f, reason: collision with root package name */
    public static final d f459f;

    /* renamed from: a, reason: collision with root package name */
    public final Object f460a;

    /* renamed from: b, reason: collision with root package name */
    public final int f461b;

    static {
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction2;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction3;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction4;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction5;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction6;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction7;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction8;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction9;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction10;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction11;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction12;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction13;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction14;
        new d(null, 1, null);
        new d(null, 2, null);
        new d(null, 4, null);
        new d(null, 8, null);
        new d(null, 16, null);
        new d(null, 32, null);
        new d(null, 64, null);
        new d(null, 128, null);
        new d(null, PSKKeyManager.MAX_KEY_LENGTH_BYTES, f.class);
        new d(null, 512, f.class);
        new d(null, 1024, g.class);
        new d(null, 2048, g.class);
        f456c = new d(null, 4096, null);
        f457d = new d(null, 8192, null);
        new d(null, 16384, null);
        new d(null, 32768, null);
        new d(null, 65536, null);
        new d(null, 131072, k.class);
        new d(null, 262144, null);
        new d(null, 524288, null);
        new d(null, 1048576, null);
        new d(null, 2097152, l.class);
        int i2 = Build.VERSION.SDK_INT;
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, R.id.accessibilityActionShowOnScreen, null);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, R.id.accessibilityActionScrollToPosition, i.class);
        f458e = new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, R.id.accessibilityActionScrollUp, null);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, R.id.accessibilityActionScrollLeft, null);
        f459f = new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, R.id.accessibilityActionScrollDown, null);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, R.id.accessibilityActionScrollRight, null);
        if (i2 >= 29) {
            accessibilityAction = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP;
        } else {
            accessibilityAction = null;
        }
        new d(accessibilityAction, R.id.accessibilityActionPageUp, null);
        if (i2 >= 29) {
            accessibilityAction2 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN;
        } else {
            accessibilityAction2 = null;
        }
        new d(accessibilityAction2, R.id.accessibilityActionPageDown, null);
        if (i2 >= 29) {
            accessibilityAction3 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT;
        } else {
            accessibilityAction3 = null;
        }
        new d(accessibilityAction3, R.id.accessibilityActionPageLeft, null);
        if (i2 >= 29) {
            accessibilityAction4 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT;
        } else {
            accessibilityAction4 = null;
        }
        new d(accessibilityAction4, R.id.accessibilityActionPageRight, null);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, R.id.accessibilityActionContextClick, null);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, R.id.accessibilityActionSetProgress, j.class);
        if (i2 >= 26) {
            accessibilityAction5 = AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW;
        } else {
            accessibilityAction5 = null;
        }
        new d(accessibilityAction5, R.id.accessibilityActionMoveWindow, h.class);
        if (i2 >= 28) {
            accessibilityAction6 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP;
        } else {
            accessibilityAction6 = null;
        }
        new d(accessibilityAction6, R.id.accessibilityActionShowTooltip, null);
        if (i2 >= 28) {
            accessibilityAction7 = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
        } else {
            accessibilityAction7 = null;
        }
        new d(accessibilityAction7, R.id.accessibilityActionHideTooltip, null);
        if (i2 >= 30) {
            accessibilityAction8 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD;
        } else {
            accessibilityAction8 = null;
        }
        new d(accessibilityAction8, R.id.accessibilityActionPressAndHold, null);
        if (i2 >= 30) {
            accessibilityAction9 = AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER;
        } else {
            accessibilityAction9 = null;
        }
        new d(accessibilityAction9, R.id.accessibilityActionImeEnter, null);
        if (i2 >= 32) {
            accessibilityAction10 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_START;
        } else {
            accessibilityAction10 = null;
        }
        new d(accessibilityAction10, R.id.ALT, null);
        if (i2 >= 32) {
            accessibilityAction11 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_DROP;
        } else {
            accessibilityAction11 = null;
        }
        new d(accessibilityAction11, R.id.CTRL, null);
        if (i2 >= 32) {
            accessibilityAction12 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_CANCEL;
        } else {
            accessibilityAction12 = null;
        }
        new d(accessibilityAction12, R.id.FUNCTION, null);
        if (i2 >= 33) {
            accessibilityAction13 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TEXT_SUGGESTIONS;
        } else {
            accessibilityAction13 = null;
        }
        new d(accessibilityAction13, R.id.KEYCODE_0, null);
        if (i2 >= 34) {
            accessibilityAction14 = AbstractC0029x.a();
        } else {
            accessibilityAction14 = null;
        }
        new d(accessibilityAction14, R.id.KEYCODE_3D_MODE, null);
    }

    public d(Object obj, int i2, Class cls) {
        this.f461b = i2;
        if (obj == null) {
            this.f460a = new AccessibilityNodeInfo.AccessibilityAction(i2, null);
        } else {
            this.f460a = obj;
        }
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof d)) {
            return false;
        }
        Object obj2 = ((d) obj).f460a;
        Object obj3 = this.f460a;
        if (obj3 == null) {
            if (obj2 != null) {
                return false;
            }
            return true;
        }
        if (!obj3.equals(obj2)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        Object obj = this.f460a;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AccessibilityActionCompat: ");
        String b2 = e.b(this.f461b);
        if (b2.equals("ACTION_UNKNOWN")) {
            Object obj = this.f460a;
            if (((AccessibilityNodeInfo.AccessibilityAction) obj).getLabel() != null) {
                b2 = ((AccessibilityNodeInfo.AccessibilityAction) obj).getLabel().toString();
            }
        }
        sb.append(b2);
        return sb.toString();
    }
}

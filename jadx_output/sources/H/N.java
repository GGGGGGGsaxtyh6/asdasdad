package H;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import k.C0207w;
import org.conscrypt.R;

/* loaded from: classes.dex */
public abstract class N {

    /* renamed from: a, reason: collision with root package name */
    public static WeakHashMap f327a = null;

    /* renamed from: b, reason: collision with root package name */
    public static Field f328b = null;

    /* renamed from: c, reason: collision with root package name */
    public static boolean f329c = false;

    /* renamed from: d, reason: collision with root package name */
    public static final A f330d = new Object();

    /* renamed from: e, reason: collision with root package name */
    public static final C f331e = new C();

    public static S a(View view) {
        if (f327a == null) {
            f327a = new WeakHashMap();
        }
        S s2 = (S) f327a.get(view);
        if (s2 == null) {
            S s3 = new S(view);
            f327a.put(view, s3);
            return s3;
        }
        return s2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [H.M, java.lang.Object] */
    public static boolean b(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList = M.f323d;
        M m2 = (M) view.getTag(R.id.tag_unhandled_key_event_manager);
        M m3 = m2;
        if (m2 == null) {
            ?? obj = new Object();
            obj.f324a = null;
            obj.f325b = null;
            obj.f326c = null;
            view.setTag(R.id.tag_unhandled_key_event_manager, obj);
            m3 = obj;
        }
        if (keyEvent.getAction() == 0) {
            WeakHashMap weakHashMap = m3.f324a;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList arrayList2 = M.f323d;
            if (!arrayList2.isEmpty()) {
                synchronized (arrayList2) {
                    try {
                        if (m3.f324a == null) {
                            m3.f324a = new WeakHashMap();
                        }
                        for (int size = arrayList2.size() - 1; size >= 0; size--) {
                            ArrayList arrayList3 = M.f323d;
                            View view2 = (View) ((WeakReference) arrayList3.get(size)).get();
                            if (view2 == null) {
                                arrayList3.remove(size);
                            } else {
                                m3.f324a.put(view2, Boolean.TRUE);
                                for (ViewParent parent = view2.getParent(); parent instanceof View; parent = parent.getParent()) {
                                    m3.f324a.put((View) parent, Boolean.TRUE);
                                }
                            }
                        }
                    } finally {
                    }
                }
            }
        }
        View a2 = m3.a(view);
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (a2 != null && !KeyEvent.isModifierKey(keyCode)) {
                if (m3.f325b == null) {
                    m3.f325b = new SparseArray();
                }
                m3.f325b.put(keyCode, new WeakReference(a2));
            }
        }
        if (a2 == null) {
            return false;
        }
        return true;
    }

    public static View.AccessibilityDelegate c(View view) {
        if (Build.VERSION.SDK_INT >= 29) {
            return J.a(view);
        }
        if (f329c) {
            return null;
        }
        if (f328b == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                f328b = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                f329c = true;
                return null;
            }
        }
        try {
            Object obj = f328b.get(view);
            if (!(obj instanceof View.AccessibilityDelegate)) {
                return null;
            }
            return (View.AccessibilityDelegate) obj;
        } catch (Throwable unused2) {
            f329c = true;
            return null;
        }
    }

    public static String[] d(C0207w c0207w) {
        if (Build.VERSION.SDK_INT >= 31) {
            return L.a(c0207w);
        }
        return (String[]) c0207w.getTag(R.id.tag_on_receive_content_mime_types);
    }

    public static void e(View view, int i2) {
        Object tag;
        boolean z2;
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (!accessibilityManager.isEnabled()) {
            return;
        }
        int i3 = Build.VERSION.SDK_INT;
        CharSequence charSequence = null;
        if (i3 >= 28) {
            tag = I.a(view);
        } else {
            tag = view.getTag(R.id.tag_accessibility_pane_title);
            if (!CharSequence.class.isInstance(tag)) {
                tag = null;
            }
        }
        if (((CharSequence) tag) != null && view.isShown() && view.getWindowVisibility() == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        int i4 = 32;
        if (view.getAccessibilityLiveRegion() == 0 && !z2) {
            if (i2 == 32) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain();
                view.onInitializeAccessibilityEvent(obtain);
                obtain.setEventType(32);
                obtain.setContentChangeTypes(i2);
                obtain.setSource(view);
                view.onPopulateAccessibilityEvent(obtain);
                List<CharSequence> text = obtain.getText();
                if (i3 >= 28) {
                    charSequence = I.a(view);
                } else {
                    Object tag2 = view.getTag(R.id.tag_accessibility_pane_title);
                    if (CharSequence.class.isInstance(tag2)) {
                        charSequence = tag2;
                    }
                }
                text.add(charSequence);
                accessibilityManager.sendAccessibilityEvent(obtain);
                return;
            }
            if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i2);
                    return;
                } catch (AbstractMethodError e2) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName().concat(" does not fully implement ViewParent"), e2);
                    return;
                }
            }
            return;
        }
        AccessibilityEvent obtain2 = AccessibilityEvent.obtain();
        if (!z2) {
            i4 = 2048;
        }
        obtain2.setEventType(i4);
        obtain2.setContentChangeTypes(i2);
        if (z2) {
            List<CharSequence> text2 = obtain2.getText();
            if (i3 >= 28) {
                charSequence = I.a(view);
            } else {
                Object tag3 = view.getTag(R.id.tag_accessibility_pane_title);
                if (CharSequence.class.isInstance(tag3)) {
                    charSequence = tag3;
                }
            }
            text2.add(charSequence);
            if (view.getImportantForAccessibility() == 0) {
                view.setImportantForAccessibility(1);
            }
        }
        view.sendAccessibilityEventUnchecked(obtain2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static C0013g f(View view, C0013g c0013g) {
        if (Log.isLoggable("ViewCompat", 3)) {
            Log.d("ViewCompat", "performReceiveContent: " + c0013g + ", view=" + view.getClass().getSimpleName() + "[" + view.getId() + "]");
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return L.b(view, c0013g);
        }
        K.k kVar = (K.k) view.getTag(R.id.tag_on_receive_content_listener);
        InterfaceC0028w interfaceC0028w = f330d;
        if (kVar != null) {
            C0013g a2 = K.k.a(view, c0013g);
            if (a2 == null) {
                return null;
            }
            if (view instanceof InterfaceC0028w) {
                interfaceC0028w = (InterfaceC0028w) view;
            }
            return interfaceC0028w.a(a2);
        }
        if (view instanceof InterfaceC0028w) {
            interfaceC0028w = (InterfaceC0028w) view;
        }
        return interfaceC0028w.a(c0013g);
    }

    public static void g(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i2) {
        if (Build.VERSION.SDK_INT >= 29) {
            J.b(view, context, iArr, attributeSet, typedArray, i2, 0);
        }
    }

    public static void h(View view, C0008b c0008b) {
        C0007a c0007a;
        if (c0008b == null && (c(view) instanceof C0007a)) {
            c0008b = new C0008b();
        }
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
        }
        if (c0008b == null) {
            c0007a = null;
        } else {
            c0007a = c0008b.f355b;
        }
        view.setAccessibilityDelegate(c0007a);
    }

    public static void i(View view, CharSequence charSequence) {
        boolean z2;
        new B(R.id.tag_accessibility_pane_title, CharSequence.class, 8, 28, 1).d(view, charSequence);
        C c2 = f331e;
        if (charSequence != null) {
            WeakHashMap weakHashMap = c2.f315a;
            if (view.isShown() && view.getWindowVisibility() == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            weakHashMap.put(view, Boolean.valueOf(z2));
            view.addOnAttachStateChangeListener(c2);
            if (view.isAttachedToWindow()) {
                view.getViewTreeObserver().addOnGlobalLayoutListener(c2);
                return;
            }
            return;
        }
        c2.f315a.remove(view);
        view.removeOnAttachStateChangeListener(c2);
        view.getViewTreeObserver().removeOnGlobalLayoutListener(c2);
    }
}

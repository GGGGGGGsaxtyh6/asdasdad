package k;

import H.C0011e;
import H.InterfaceC0010d;
import android.app.Activity;
import android.content.ClipData;
import android.os.Build;
import android.text.Selection;
import android.text.Spannable;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

/* renamed from: k.G, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0159G {
    public static boolean a(DragEvent dragEvent, TextView textView, Activity activity) {
        InterfaceC0010d interfaceC0010d;
        activity.requestDragAndDropPermissions(dragEvent);
        int offsetForPosition = textView.getOffsetForPosition(dragEvent.getX(), dragEvent.getY());
        textView.beginBatchEdit();
        try {
            Selection.setSelection((Spannable) textView.getText(), offsetForPosition);
            ClipData clipData = dragEvent.getClipData();
            if (Build.VERSION.SDK_INT >= 31) {
                interfaceC0010d = new A.f(clipData, 3);
            } else {
                C0011e c0011e = new C0011e();
                c0011e.f358b = clipData;
                c0011e.f359c = 3;
                interfaceC0010d = c0011e;
            }
            H.N.f(textView, interfaceC0010d.o());
            textView.endBatchEdit();
            return true;
        } catch (Throwable th) {
            textView.endBatchEdit();
            throw th;
        }
    }

    public static boolean b(DragEvent dragEvent, View view, Activity activity) {
        InterfaceC0010d interfaceC0010d;
        activity.requestDragAndDropPermissions(dragEvent);
        ClipData clipData = dragEvent.getClipData();
        if (Build.VERSION.SDK_INT >= 31) {
            interfaceC0010d = new A.f(clipData, 3);
        } else {
            C0011e c0011e = new C0011e();
            c0011e.f358b = clipData;
            c0011e.f359c = 3;
            interfaceC0010d = c0011e;
        }
        H.N.f(view, interfaceC0010d.o());
        return true;
    }
}

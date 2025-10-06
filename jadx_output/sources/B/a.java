package B;

import android.graphics.drawable.Icon;
import android.icu.text.DecimalFormatSymbols;
import android.text.PrecomputedText;
import android.view.ViewConfiguration;
import android.widget.TextView;
import k.C0171d0;

/* loaded from: classes.dex */
public abstract class a {
    public static String[] a(DecimalFormatSymbols decimalFormatSymbols) {
        return decimalFormatSymbols.getDigitStrings();
    }

    public static int b(Object obj) {
        return ((Icon) obj).getResId();
    }

    public static int c(ViewConfiguration viewConfiguration) {
        return viewConfiguration.getScaledHoverSlop();
    }

    public static PrecomputedText.Params d(C0171d0 c0171d0) {
        return c0171d0.getTextMetricsParams();
    }

    public static void e(TextView textView, int i2) {
        textView.setFirstBaselineToTopHeight(i2);
    }

    public static boolean f(ViewConfiguration viewConfiguration) {
        return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
    }
}

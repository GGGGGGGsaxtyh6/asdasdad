package k;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import org.conscrypt.R;

/* loaded from: classes.dex */
public final class i1 {

    /* renamed from: a, reason: collision with root package name */
    public final Context f1939a;

    /* renamed from: b, reason: collision with root package name */
    public final View f1940b;

    /* renamed from: c, reason: collision with root package name */
    public final TextView f1941c;

    /* renamed from: d, reason: collision with root package name */
    public final WindowManager.LayoutParams f1942d;

    /* renamed from: e, reason: collision with root package name */
    public final Rect f1943e;

    /* renamed from: f, reason: collision with root package name */
    public final int[] f1944f;

    /* renamed from: g, reason: collision with root package name */
    public final int[] f1945g;

    public i1(Context context) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.f1942d = layoutParams;
        this.f1943e = new Rect();
        this.f1944f = new int[2];
        this.f1945g = new int[2];
        this.f1939a = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.abc_tooltip, (ViewGroup) null);
        this.f1940b = inflate;
        this.f1941c = (TextView) inflate.findViewById(R.id.message);
        layoutParams.setTitle(i1.class.getSimpleName());
        layoutParams.packageName = context.getPackageName();
        layoutParams.type = 1002;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = R.style.Animation_AppCompat_Tooltip;
        layoutParams.flags = 24;
    }
}

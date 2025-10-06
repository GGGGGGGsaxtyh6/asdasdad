package g;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertController$RecycleListView;
import androidx.core.widget.NestedScrollView;
import f.AbstractC0101a;
import java.lang.ref.WeakReference;
import org.conscrypt.R;

/* renamed from: g.g, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0109g {

    /* renamed from: a, reason: collision with root package name */
    public final Context f1425a;

    /* renamed from: b, reason: collision with root package name */
    public final DialogC0110h f1426b;

    /* renamed from: c, reason: collision with root package name */
    public final Window f1427c;

    /* renamed from: d, reason: collision with root package name */
    public CharSequence f1428d;

    /* renamed from: e, reason: collision with root package name */
    public AlertController$RecycleListView f1429e;

    /* renamed from: f, reason: collision with root package name */
    public Button f1430f;

    /* renamed from: g, reason: collision with root package name */
    public Button f1431g;
    public Button h;

    /* renamed from: i, reason: collision with root package name */
    public NestedScrollView f1432i;

    /* renamed from: j, reason: collision with root package name */
    public Drawable f1433j;

    /* renamed from: k, reason: collision with root package name */
    public ImageView f1434k;

    /* renamed from: l, reason: collision with root package name */
    public TextView f1435l;

    /* renamed from: m, reason: collision with root package name */
    public TextView f1436m;

    /* renamed from: n, reason: collision with root package name */
    public View f1437n;

    /* renamed from: o, reason: collision with root package name */
    public ListAdapter f1438o;

    /* renamed from: q, reason: collision with root package name */
    public final int f1440q;

    /* renamed from: r, reason: collision with root package name */
    public final int f1441r;

    /* renamed from: s, reason: collision with root package name */
    public final int f1442s;

    /* renamed from: t, reason: collision with root package name */
    public final int f1443t;

    /* renamed from: u, reason: collision with root package name */
    public final boolean f1444u;

    /* renamed from: v, reason: collision with root package name */
    public final HandlerC0107e f1445v;

    /* renamed from: p, reason: collision with root package name */
    public int f1439p = -1;

    /* renamed from: w, reason: collision with root package name */
    public final Z.q f1446w = new Z.q(1, this);

    /* JADX WARN: Type inference failed for: r6v1, types: [android.os.Handler, g.e] */
    public C0109g(Context context, DialogC0110h dialogC0110h, Window window) {
        this.f1425a = context;
        this.f1426b = dialogC0110h;
        this.f1427c = window;
        ?? handler = new Handler();
        handler.f1424a = new WeakReference(dialogC0110h);
        this.f1445v = handler;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, AbstractC0101a.f1266e, R.attr.alertDialogStyle, 0);
        this.f1440q = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.getResourceId(2, 0);
        this.f1441r = obtainStyledAttributes.getResourceId(4, 0);
        obtainStyledAttributes.getResourceId(5, 0);
        this.f1442s = obtainStyledAttributes.getResourceId(7, 0);
        this.f1443t = obtainStyledAttributes.getResourceId(3, 0);
        this.f1444u = obtainStyledAttributes.getBoolean(6, true);
        obtainStyledAttributes.getDimensionPixelSize(1, 0);
        obtainStyledAttributes.recycle();
        dialogC0110h.b().g(1);
    }

    public static ViewGroup a(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }
}

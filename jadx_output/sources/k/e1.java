package k;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import f.AbstractC0101a;
import org.conscrypt.R;

/* loaded from: classes.dex */
public final class e1 implements InterfaceC0189m0 {

    /* renamed from: a, reason: collision with root package name */
    public final Toolbar f1895a;

    /* renamed from: b, reason: collision with root package name */
    public int f1896b;

    /* renamed from: c, reason: collision with root package name */
    public final View f1897c;

    /* renamed from: d, reason: collision with root package name */
    public Drawable f1898d;

    /* renamed from: e, reason: collision with root package name */
    public Drawable f1899e;

    /* renamed from: f, reason: collision with root package name */
    public final Drawable f1900f;

    /* renamed from: g, reason: collision with root package name */
    public final boolean f1901g;
    public CharSequence h;

    /* renamed from: i, reason: collision with root package name */
    public final CharSequence f1902i;

    /* renamed from: j, reason: collision with root package name */
    public final CharSequence f1903j;

    /* renamed from: k, reason: collision with root package name */
    public Window.Callback f1904k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f1905l;

    /* renamed from: m, reason: collision with root package name */
    public C0184k f1906m;

    /* renamed from: n, reason: collision with root package name */
    public final int f1907n;

    /* renamed from: o, reason: collision with root package name */
    public final Drawable f1908o;

    public e1(Toolbar toolbar, boolean z2) {
        boolean z3;
        Drawable drawable;
        this.f1907n = 0;
        this.f1895a = toolbar;
        this.h = toolbar.getTitle();
        this.f1902i = toolbar.getSubtitle();
        if (this.h != null) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.f1901g = z3;
        this.f1900f = toolbar.getNavigationIcon();
        D0.h p2 = D0.h.p(toolbar.getContext(), null, AbstractC0101a.f1262a, R.attr.actionBarStyle);
        int i2 = 15;
        this.f1908o = p2.j(15);
        if (z2) {
            TypedArray typedArray = (TypedArray) p2.f259c;
            CharSequence text = typedArray.getText(27);
            if (!TextUtils.isEmpty(text)) {
                this.f1901g = true;
                this.h = text;
                if ((this.f1896b & 8) != 0) {
                    Toolbar toolbar2 = this.f1895a;
                    toolbar2.setTitle(text);
                    if (this.f1901g) {
                        H.N.i(toolbar2.getRootView(), text);
                    }
                }
            }
            CharSequence text2 = typedArray.getText(25);
            if (!TextUtils.isEmpty(text2)) {
                this.f1902i = text2;
                if ((this.f1896b & 8) != 0) {
                    toolbar.setSubtitle(text2);
                }
            }
            Drawable j2 = p2.j(20);
            if (j2 != null) {
                this.f1899e = j2;
                c();
            }
            Drawable j3 = p2.j(17);
            if (j3 != null) {
                this.f1898d = j3;
                c();
            }
            if (this.f1900f == null && (drawable = this.f1908o) != null) {
                this.f1900f = drawable;
                int i3 = this.f1896b & 4;
                Toolbar toolbar3 = this.f1895a;
                if (i3 != 0) {
                    toolbar3.setNavigationIcon(drawable);
                } else {
                    toolbar3.setNavigationIcon((Drawable) null);
                }
            }
            a(typedArray.getInt(10, 0));
            int resourceId = typedArray.getResourceId(9, 0);
            if (resourceId != 0) {
                View inflate = LayoutInflater.from(toolbar.getContext()).inflate(resourceId, (ViewGroup) toolbar, false);
                View view = this.f1897c;
                if (view != null && (this.f1896b & 16) != 0) {
                    toolbar.removeView(view);
                }
                this.f1897c = inflate;
                if (inflate != null && (this.f1896b & 16) != 0) {
                    toolbar.addView(inflate);
                }
                a(this.f1896b | 16);
            }
            int layoutDimension = typedArray.getLayoutDimension(13, 0);
            if (layoutDimension > 0) {
                ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
                layoutParams.height = layoutDimension;
                toolbar.setLayoutParams(layoutParams);
            }
            int dimensionPixelOffset = typedArray.getDimensionPixelOffset(7, -1);
            int dimensionPixelOffset2 = typedArray.getDimensionPixelOffset(3, -1);
            if (dimensionPixelOffset >= 0 || dimensionPixelOffset2 >= 0) {
                int max = Math.max(dimensionPixelOffset, 0);
                int max2 = Math.max(dimensionPixelOffset2, 0);
                toolbar.d();
                toolbar.f943t.a(max, max2);
            }
            int resourceId2 = typedArray.getResourceId(28, 0);
            if (resourceId2 != 0) {
                Context context = toolbar.getContext();
                toolbar.f935l = resourceId2;
                C0171d0 c0171d0 = toolbar.f926b;
                if (c0171d0 != null) {
                    c0171d0.setTextAppearance(context, resourceId2);
                }
            }
            int resourceId3 = typedArray.getResourceId(26, 0);
            if (resourceId3 != 0) {
                Context context2 = toolbar.getContext();
                toolbar.f936m = resourceId3;
                C0171d0 c0171d02 = toolbar.f927c;
                if (c0171d02 != null) {
                    c0171d02.setTextAppearance(context2, resourceId3);
                }
            }
            int resourceId4 = typedArray.getResourceId(22, 0);
            if (resourceId4 != 0) {
                toolbar.setPopupTheme(resourceId4);
            }
        } else {
            if (toolbar.getNavigationIcon() != null) {
                this.f1908o = toolbar.getNavigationIcon();
            } else {
                i2 = 11;
            }
            this.f1896b = i2;
        }
        p2.r();
        if (R.string.abc_action_bar_up_description != this.f1907n) {
            this.f1907n = R.string.abc_action_bar_up_description;
            if (TextUtils.isEmpty(toolbar.getNavigationContentDescription())) {
                int i4 = this.f1907n;
                this.f1903j = i4 != 0 ? toolbar.getContext().getString(i4) : null;
                b();
            }
        }
        this.f1903j = toolbar.getNavigationContentDescription();
        toolbar.setNavigationOnClickListener(new d1(this));
    }

    public final void a(int i2) {
        View view;
        int i3 = this.f1896b ^ i2;
        this.f1896b = i2;
        if (i3 != 0) {
            if ((i3 & 4) != 0) {
                if ((i2 & 4) != 0) {
                    b();
                }
                int i4 = this.f1896b & 4;
                Toolbar toolbar = this.f1895a;
                if (i4 != 0) {
                    Drawable drawable = this.f1900f;
                    if (drawable == null) {
                        drawable = this.f1908o;
                    }
                    toolbar.setNavigationIcon(drawable);
                } else {
                    toolbar.setNavigationIcon((Drawable) null);
                }
            }
            if ((i3 & 3) != 0) {
                c();
            }
            int i5 = i3 & 8;
            Toolbar toolbar2 = this.f1895a;
            if (i5 != 0) {
                if ((i2 & 8) != 0) {
                    toolbar2.setTitle(this.h);
                    toolbar2.setSubtitle(this.f1902i);
                } else {
                    toolbar2.setTitle((CharSequence) null);
                    toolbar2.setSubtitle((CharSequence) null);
                }
            }
            if ((i3 & 16) != 0 && (view = this.f1897c) != null) {
                if ((i2 & 16) != 0) {
                    toolbar2.addView(view);
                } else {
                    toolbar2.removeView(view);
                }
            }
        }
    }

    public final void b() {
        if ((this.f1896b & 4) != 0) {
            boolean isEmpty = TextUtils.isEmpty(this.f1903j);
            Toolbar toolbar = this.f1895a;
            if (isEmpty) {
                toolbar.setNavigationContentDescription(this.f1907n);
            } else {
                toolbar.setNavigationContentDescription(this.f1903j);
            }
        }
    }

    public final void c() {
        Drawable drawable;
        int i2 = this.f1896b;
        if ((i2 & 2) != 0) {
            if ((i2 & 1) != 0) {
                drawable = this.f1899e;
                if (drawable == null) {
                    drawable = this.f1898d;
                }
            } else {
                drawable = this.f1898d;
            }
        } else {
            drawable = null;
        }
        this.f1895a.setLogo(drawable);
    }
}

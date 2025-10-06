package androidx.appcompat.view.menu;

import D0.h;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import f.AbstractC0101a;
import j.InterfaceC0133A;
import j.p;
import org.conscrypt.R;

/* loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements InterfaceC0133A, AbsListView.SelectionBoundsAdjuster {

    /* renamed from: a, reason: collision with root package name */
    public p f808a;

    /* renamed from: b, reason: collision with root package name */
    public ImageView f809b;

    /* renamed from: c, reason: collision with root package name */
    public RadioButton f810c;

    /* renamed from: d, reason: collision with root package name */
    public TextView f811d;

    /* renamed from: e, reason: collision with root package name */
    public CheckBox f812e;

    /* renamed from: f, reason: collision with root package name */
    public TextView f813f;

    /* renamed from: g, reason: collision with root package name */
    public ImageView f814g;
    public ImageView h;

    /* renamed from: i, reason: collision with root package name */
    public LinearLayout f815i;

    /* renamed from: j, reason: collision with root package name */
    public final Drawable f816j;

    /* renamed from: k, reason: collision with root package name */
    public final int f817k;

    /* renamed from: l, reason: collision with root package name */
    public final Context f818l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f819m;

    /* renamed from: n, reason: collision with root package name */
    public final Drawable f820n;

    /* renamed from: o, reason: collision with root package name */
    public final boolean f821o;

    /* renamed from: p, reason: collision with root package name */
    public LayoutInflater f822p;

    /* renamed from: q, reason: collision with root package name */
    public boolean f823q;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        h p2 = h.p(getContext(), attributeSet, AbstractC0101a.f1278r, R.attr.listMenuViewStyle);
        this.f816j = p2.j(5);
        TypedArray typedArray = (TypedArray) p2.f259c;
        this.f817k = typedArray.getResourceId(1, -1);
        this.f819m = typedArray.getBoolean(7, false);
        this.f818l = context;
        this.f820n = p2.j(8);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{android.R.attr.divider}, R.attr.dropDownListViewStyle, 0);
        this.f821o = obtainStyledAttributes.hasValue(0);
        p2.r();
        obtainStyledAttributes.recycle();
    }

    private LayoutInflater getInflater() {
        if (this.f822p == null) {
            this.f822p = LayoutInflater.from(getContext());
        }
        return this.f822p;
    }

    private void setSubMenuArrowVisible(boolean z2) {
        int i2;
        ImageView imageView = this.f814g;
        if (imageView != null) {
            if (z2) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            imageView.setVisibility(i2);
        }
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public final void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.h;
        if (imageView != null && imageView.getVisibility() == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.h.getLayoutParams();
            rect.top = this.h.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin + rect.top;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
    
        if (r0 == false) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0125  */
    @Override // j.InterfaceC0133A
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(j.p r11) {
        /*
            Method dump skipped, instructions count: 325
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.ListMenuItemView.c(j.p):void");
    }

    @Override // j.InterfaceC0133A
    public p getItemData() {
        return this.f808a;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setBackground(this.f816j);
        TextView textView = (TextView) findViewById(R.id.title);
        this.f811d = textView;
        int i2 = this.f817k;
        if (i2 != -1) {
            textView.setTextAppearance(this.f818l, i2);
        }
        this.f813f = (TextView) findViewById(R.id.shortcut);
        ImageView imageView = (ImageView) findViewById(R.id.submenuarrow);
        this.f814g = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.f820n);
        }
        this.h = (ImageView) findViewById(R.id.group_divider);
        this.f815i = (LinearLayout) findViewById(R.id.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i2, int i3) {
        if (this.f809b != null && this.f819m) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f809b.getLayoutParams();
            int i4 = layoutParams.height;
            if (i4 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i4;
            }
        }
        super.onMeasure(i2, i3);
    }

    public void setCheckable(boolean z2) {
        CompoundButton compoundButton;
        View view;
        if (!z2 && this.f810c == null && this.f812e == null) {
            return;
        }
        if ((this.f808a.f1709x & 4) != 0) {
            if (this.f810c == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(R.layout.abc_list_menu_item_radio, (ViewGroup) this, false);
                this.f810c = radioButton;
                LinearLayout linearLayout = this.f815i;
                if (linearLayout != null) {
                    linearLayout.addView(radioButton, -1);
                } else {
                    addView(radioButton, -1);
                }
            }
            compoundButton = this.f810c;
            view = this.f812e;
        } else {
            if (this.f812e == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(R.layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                this.f812e = checkBox;
                LinearLayout linearLayout2 = this.f815i;
                if (linearLayout2 != null) {
                    linearLayout2.addView(checkBox, -1);
                } else {
                    addView(checkBox, -1);
                }
            }
            compoundButton = this.f812e;
            view = this.f810c;
        }
        if (z2) {
            compoundButton.setChecked(this.f808a.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (view != null && view.getVisibility() != 8) {
                view.setVisibility(8);
                return;
            }
            return;
        }
        CheckBox checkBox2 = this.f812e;
        if (checkBox2 != null) {
            checkBox2.setVisibility(8);
        }
        RadioButton radioButton2 = this.f810c;
        if (radioButton2 != null) {
            radioButton2.setVisibility(8);
        }
    }

    public void setChecked(boolean z2) {
        CompoundButton compoundButton;
        if ((this.f808a.f1709x & 4) != 0) {
            if (this.f810c == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(R.layout.abc_list_menu_item_radio, (ViewGroup) this, false);
                this.f810c = radioButton;
                LinearLayout linearLayout = this.f815i;
                if (linearLayout != null) {
                    linearLayout.addView(radioButton, -1);
                } else {
                    addView(radioButton, -1);
                }
            }
            compoundButton = this.f810c;
        } else {
            if (this.f812e == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(R.layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                this.f812e = checkBox;
                LinearLayout linearLayout2 = this.f815i;
                if (linearLayout2 != null) {
                    linearLayout2.addView(checkBox, -1);
                } else {
                    addView(checkBox, -1);
                }
            }
            compoundButton = this.f812e;
        }
        compoundButton.setChecked(z2);
    }

    public void setForceShowIcon(boolean z2) {
        this.f823q = z2;
        this.f819m = z2;
    }

    public void setGroupDividerEnabled(boolean z2) {
        int i2;
        ImageView imageView = this.h;
        if (imageView != null) {
            if (!this.f821o && z2) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            imageView.setVisibility(i2);
        }
    }

    public void setIcon(Drawable drawable) {
        this.f808a.f1699n.getClass();
        boolean z2 = this.f823q;
        if (!z2 && !this.f819m) {
            return;
        }
        ImageView imageView = this.f809b;
        if (imageView == null && drawable == null && !this.f819m) {
            return;
        }
        if (imageView == null) {
            ImageView imageView2 = (ImageView) getInflater().inflate(R.layout.abc_list_menu_item_icon, (ViewGroup) this, false);
            this.f809b = imageView2;
            LinearLayout linearLayout = this.f815i;
            if (linearLayout != null) {
                linearLayout.addView(imageView2, 0);
            } else {
                addView(imageView2, 0);
            }
        }
        if (drawable == null && !this.f819m) {
            this.f809b.setVisibility(8);
            return;
        }
        ImageView imageView3 = this.f809b;
        if (!z2) {
            drawable = null;
        }
        imageView3.setImageDrawable(drawable);
        if (this.f809b.getVisibility() != 0) {
            this.f809b.setVisibility(0);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence != null) {
            this.f811d.setText(charSequence);
            if (this.f811d.getVisibility() != 0) {
                this.f811d.setVisibility(0);
                return;
            }
            return;
        }
        if (this.f811d.getVisibility() != 8) {
            this.f811d.setVisibility(8);
        }
    }
}

package B0;

import a0.C0049c;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import b0.C0075a;
import j0.AbstractC0150d;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import k.C0203u;
import k.C0213z;
import k.N0;
import k.S0;
import org.conscrypt.R;

/* loaded from: classes.dex */
public final class h {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f117a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f118b;

    /* renamed from: c, reason: collision with root package name */
    public Object f119c;

    /* renamed from: d, reason: collision with root package name */
    public Serializable f120d;

    /* renamed from: e, reason: collision with root package name */
    public Object f121e;

    /* renamed from: f, reason: collision with root package name */
    public Object f122f;

    /* renamed from: g, reason: collision with root package name */
    public Object f123g;

    public h(u0.m mVar, String str, u0.k kVar, C0213z c0213z, Map map) {
        this.f117a = 2;
        AbstractC0150d.e(mVar, "url");
        AbstractC0150d.e(str, "method");
        this.f118b = mVar;
        this.f120d = str;
        this.f119c = kVar;
        this.f121e = c0213z;
        this.f122f = map;
    }

    public static boolean a(int[] iArr, int i2) {
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    public static ColorStateList b(Context context, int i2) {
        int c2 = S0.c(context, R.attr.colorControlHighlight);
        int b2 = S0.b(context, R.attr.colorButtonNormal);
        int[] iArr = S0.f1838b;
        int[] iArr2 = S0.f1840d;
        int b3 = A.a.b(c2, i2);
        return new ColorStateList(new int[][]{iArr, iArr2, S0.f1839c, S0.f1842f}, new int[]{b2, b3, A.a.b(c2, i2), i2});
    }

    public static LayerDrawable c(N0 n02, Context context, int i2) {
        BitmapDrawable bitmapDrawable;
        BitmapDrawable bitmapDrawable2;
        BitmapDrawable bitmapDrawable3;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(i2);
        Drawable c2 = n02.c(context, R.drawable.abc_star_black_48dp);
        Drawable c3 = n02.c(context, R.drawable.abc_star_half_black_48dp);
        if ((c2 instanceof BitmapDrawable) && c2.getIntrinsicWidth() == dimensionPixelSize && c2.getIntrinsicHeight() == dimensionPixelSize) {
            bitmapDrawable = (BitmapDrawable) c2;
            bitmapDrawable2 = new BitmapDrawable(bitmapDrawable.getBitmap());
        } else {
            Bitmap createBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            c2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            c2.draw(canvas);
            bitmapDrawable = new BitmapDrawable(createBitmap);
            bitmapDrawable2 = new BitmapDrawable(createBitmap);
        }
        bitmapDrawable2.setTileModeX(Shader.TileMode.REPEAT);
        if ((c3 instanceof BitmapDrawable) && c3.getIntrinsicWidth() == dimensionPixelSize && c3.getIntrinsicHeight() == dimensionPixelSize) {
            bitmapDrawable3 = (BitmapDrawable) c3;
        } else {
            Bitmap createBitmap2 = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap2);
            c3.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            c3.draw(canvas2);
            bitmapDrawable3 = new BitmapDrawable(createBitmap2);
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable3, bitmapDrawable2});
        layerDrawable.setId(0, android.R.id.background);
        layerDrawable.setId(1, android.R.id.secondaryProgress);
        layerDrawable.setId(2, android.R.id.progress);
        return layerDrawable;
    }

    public static void f(Drawable drawable, int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter e2;
        Drawable mutate = drawable.mutate();
        if (mode == null) {
            mode = C0203u.f2029b;
        }
        PorterDuff.Mode mode2 = C0203u.f2029b;
        synchronized (C0203u.class) {
            e2 = N0.e(i2, mode);
        }
        mutate.setColorFilter(e2);
    }

    public ColorStateList d(Context context, int i2) {
        if (i2 == R.drawable.abc_edit_text_material) {
            return C0.d.t(context, R.color.abc_tint_edittext);
        }
        if (i2 == R.drawable.abc_switch_track_mtrl_alpha) {
            return C0.d.t(context, R.color.abc_tint_switch_track);
        }
        if (i2 == R.drawable.abc_switch_thumb_material) {
            int[][] iArr = new int[3];
            int[] iArr2 = new int[3];
            ColorStateList d2 = S0.d(context, R.attr.colorSwitchThumbNormal);
            if (d2 != null && d2.isStateful()) {
                int[] iArr3 = S0.f1838b;
                iArr[0] = iArr3;
                iArr2[0] = d2.getColorForState(iArr3, 0);
                iArr[1] = S0.f1841e;
                iArr2[1] = S0.c(context, R.attr.colorControlActivated);
                iArr[2] = S0.f1842f;
                iArr2[2] = d2.getDefaultColor();
            } else {
                iArr[0] = S0.f1838b;
                iArr2[0] = S0.b(context, R.attr.colorSwitchThumbNormal);
                iArr[1] = S0.f1841e;
                iArr2[1] = S0.c(context, R.attr.colorControlActivated);
                iArr[2] = S0.f1842f;
                iArr2[2] = S0.c(context, R.attr.colorSwitchThumbNormal);
            }
            return new ColorStateList(iArr, iArr2);
        }
        if (i2 == R.drawable.abc_btn_default_mtrl_shape) {
            return b(context, S0.c(context, R.attr.colorButtonNormal));
        }
        if (i2 == R.drawable.abc_btn_borderless_material) {
            return b(context, 0);
        }
        if (i2 == R.drawable.abc_btn_colored_material) {
            return b(context, S0.c(context, R.attr.colorAccent));
        }
        if (i2 != R.drawable.abc_spinner_mtrl_am_alpha && i2 != R.drawable.abc_spinner_textfield_background_material) {
            if (a((int[]) this.f119c, i2)) {
                return S0.d(context, R.attr.colorControlNormal);
            }
            if (a((int[]) this.f122f, i2)) {
                return C0.d.t(context, R.color.abc_tint_default);
            }
            if (a((int[]) this.f123g, i2)) {
                return C0.d.t(context, R.color.abc_tint_btn_checkable);
            }
            if (i2 == R.drawable.abc_seekbar_thumb_material) {
                return C0.d.t(context, R.color.abc_tint_seek_thumb);
            }
            return null;
        }
        return C0.d.t(context, R.color.abc_tint_spinner);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [u0.s, java.lang.Object] */
    public u0.s e() {
        LinkedHashMap linkedHashMap;
        ?? obj = new Object();
        obj.f2553e = new LinkedHashMap();
        obj.f2549a = (u0.m) this.f118b;
        obj.f2550b = (String) this.f120d;
        obj.f2552d = (C0213z) this.f121e;
        Map map = (Map) this.f122f;
        if (map.isEmpty()) {
            linkedHashMap = new LinkedHashMap();
        } else {
            linkedHashMap = new LinkedHashMap(map);
        }
        obj.f2553e = linkedHashMap;
        obj.f2551c = ((u0.k) this.f119c).c();
        return obj;
    }

    public String toString() {
        switch (this.f117a) {
            case 2:
                StringBuilder sb = new StringBuilder("Request{method=");
                sb.append((String) this.f120d);
                sb.append(", url=");
                sb.append((u0.m) this.f118b);
                u0.k kVar = (u0.k) this.f119c;
                if (kVar.size() != 0) {
                    sb.append(", headers=[");
                    Iterator it = kVar.iterator();
                    int i2 = 0;
                    while (true) {
                        C0075a c0075a = (C0075a) it;
                        if (c0075a.hasNext()) {
                            Object next = c0075a.next();
                            int i3 = i2 + 1;
                            if (i2 >= 0) {
                                C0049c c0049c = (C0049c) next;
                                String str = (String) c0049c.f786a;
                                String str2 = (String) c0049c.f787b;
                                if (i2 > 0) {
                                    sb.append(", ");
                                }
                                sb.append(str);
                                sb.append(':');
                                sb.append(str2);
                                i2 = i3;
                            } else {
                                throw new ArithmeticException("Index overflow has happened.");
                            }
                        } else {
                            sb.append(']');
                        }
                    }
                }
                Map map = (Map) this.f122f;
                if (!map.isEmpty()) {
                    sb.append(", tags=");
                    sb.append(map);
                }
                sb.append('}');
                String sb2 = sb.toString();
                AbstractC0150d.d(sb2, "StringBuilder().apply(builderAction).toString()");
                return sb2;
            default:
                return super.toString();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [int[], java.io.Serializable] */
    public h() {
        this.f117a = 1;
        this.f118b = new int[]{R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};
        this.f119c = new int[]{R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
        this.f120d = new int[]{R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl, R.drawable.abc_text_select_handle_middle_mtrl, R.drawable.abc_text_select_handle_right_mtrl};
        this.f121e = new int[]{R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};
        this.f122f = new int[]{R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};
        this.f123g = new int[]{R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material, R.drawable.abc_btn_check_material_anim, R.drawable.abc_btn_radio_material_anim};
    }

    public h(x0.d dVar) {
        this.f117a = 0;
        AbstractC0150d.e(dVar, "taskRunner");
        this.f118b = dVar;
        this.f123g = j.f124a;
    }
}

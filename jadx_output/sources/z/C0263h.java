package z;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;

/* renamed from: z.h, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0263h {

    /* renamed from: a, reason: collision with root package name */
    public final ColorStateList f2950a;

    /* renamed from: b, reason: collision with root package name */
    public final Configuration f2951b;

    /* renamed from: c, reason: collision with root package name */
    public final int f2952c;

    public C0263h(ColorStateList colorStateList, Configuration configuration, Resources.Theme theme) {
        int hashCode;
        this.f2950a = colorStateList;
        this.f2951b = configuration;
        if (theme == null) {
            hashCode = 0;
        } else {
            hashCode = theme.hashCode();
        }
        this.f2952c = hashCode;
    }
}

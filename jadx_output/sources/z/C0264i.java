package z;

import android.content.res.Resources;
import java.util.Objects;

/* renamed from: z.i, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0264i {

    /* renamed from: a, reason: collision with root package name */
    public final Resources f2953a;

    /* renamed from: b, reason: collision with root package name */
    public final Resources.Theme f2954b;

    public C0264i(Resources resources, Resources.Theme theme) {
        this.f2953a = resources;
        this.f2954b = theme;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0264i.class != obj.getClass()) {
            return false;
        }
        C0264i c0264i = (C0264i) obj;
        if (this.f2953a.equals(c0264i.f2953a) && Objects.equals(this.f2954b, c0264i.f2954b)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.f2953a, this.f2954b);
    }
}

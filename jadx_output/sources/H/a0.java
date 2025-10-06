package H;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import java.util.Objects;

/* loaded from: classes.dex */
public class a0 extends Z {
    public a0(f0 f0Var, WindowInsets windowInsets) {
        super(f0Var, windowInsets);
    }

    @Override // H.e0
    public f0 a() {
        WindowInsets consumeDisplayCutout;
        consumeDisplayCutout = this.f348c.consumeDisplayCutout();
        return f0.c(consumeDisplayCutout, null);
    }

    @Override // H.e0
    public C0016j e() {
        DisplayCutout displayCutout;
        displayCutout = this.f348c.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new C0016j(displayCutout);
    }

    @Override // H.Y, H.e0
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a0)) {
            return false;
        }
        a0 a0Var = (a0) obj;
        if (Objects.equals(this.f348c, a0Var.f348c) && Objects.equals(this.f350e, a0Var.f350e)) {
            return true;
        }
        return false;
    }

    @Override // H.e0
    public int hashCode() {
        return this.f348c.hashCode();
    }
}

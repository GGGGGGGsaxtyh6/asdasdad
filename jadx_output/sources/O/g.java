package O;

import android.text.InputFilter;
import android.widget.TextView;

/* loaded from: classes.dex */
public final class g extends C0.d {

    /* renamed from: a, reason: collision with root package name */
    public final f f601a;

    public g(TextView textView) {
        this.f601a = new f(textView);
    }

    @Override // C0.d
    public final void F(boolean z2) {
        boolean z3;
        if (M.j.f533k != null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z3) {
            return;
        }
        this.f601a.F(z2);
    }

    @Override // C0.d
    public final void G(boolean z2) {
        boolean z3;
        if (M.j.f533k != null) {
            z3 = true;
        } else {
            z3 = false;
        }
        f fVar = this.f601a;
        if (!z3) {
            fVar.f600c = z2;
        } else {
            fVar.G(z2);
        }
    }

    @Override // C0.d
    public final InputFilter[] w(InputFilter[] inputFilterArr) {
        boolean z2;
        if (M.j.f533k != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            return inputFilterArr;
        }
        return this.f601a.w(inputFilterArr);
    }
}

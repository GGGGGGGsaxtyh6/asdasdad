package v;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/* loaded from: classes.dex */
public final class p extends View {

    /* renamed from: a, reason: collision with root package name */
    public boolean f2796a;

    public p(Context context) {
        super(context);
        this.f2796a = true;
        super.setVisibility(8);
    }

    @Override // android.view.View
    public final void onMeasure(int i2, int i3) {
        setMeasuredDimension(0, 0);
    }

    public void setFilterRedundantCalls(boolean z2) {
        this.f2796a = z2;
    }

    public void setGuidelineBegin(int i2) {
        e eVar = (e) getLayoutParams();
        if (this.f2796a && eVar.f2633a == i2) {
            return;
        }
        eVar.f2633a = i2;
        setLayoutParams(eVar);
    }

    public void setGuidelineEnd(int i2) {
        e eVar = (e) getLayoutParams();
        if (this.f2796a && eVar.f2635b == i2) {
            return;
        }
        eVar.f2635b = i2;
        setLayoutParams(eVar);
    }

    public void setGuidelinePercent(float f2) {
        e eVar = (e) getLayoutParams();
        if (this.f2796a && eVar.f2637c == f2) {
            return;
        }
        eVar.f2637c = f2;
        setLayoutParams(eVar);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
    }
}

package androidx.appcompat.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;
import f.AbstractC0101a;

/* loaded from: classes.dex */
public class AlertController$RecycleListView extends ListView {

    /* renamed from: a, reason: collision with root package name */
    public final int f794a;

    /* renamed from: b, reason: collision with root package name */
    public final int f795b;

    public AlertController$RecycleListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0101a.f1280t);
        this.f795b = obtainStyledAttributes.getDimensionPixelOffset(0, -1);
        this.f794a = obtainStyledAttributes.getDimensionPixelOffset(1, -1);
    }
}

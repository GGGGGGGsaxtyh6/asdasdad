package v;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import org.conscrypt.FileClientSessionCache;
import org.conscrypt.ct.CTConstants;

/* loaded from: classes.dex */
public final class m {

    /* renamed from: n, reason: collision with root package name */
    public static final SparseIntArray f2777n;

    /* renamed from: a, reason: collision with root package name */
    public float f2778a;

    /* renamed from: b, reason: collision with root package name */
    public float f2779b;

    /* renamed from: c, reason: collision with root package name */
    public float f2780c;

    /* renamed from: d, reason: collision with root package name */
    public float f2781d;

    /* renamed from: e, reason: collision with root package name */
    public float f2782e;

    /* renamed from: f, reason: collision with root package name */
    public float f2783f;

    /* renamed from: g, reason: collision with root package name */
    public float f2784g;
    public int h;

    /* renamed from: i, reason: collision with root package name */
    public float f2785i;

    /* renamed from: j, reason: collision with root package name */
    public float f2786j;

    /* renamed from: k, reason: collision with root package name */
    public float f2787k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f2788l;

    /* renamed from: m, reason: collision with root package name */
    public float f2789m;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f2777n = sparseIntArray;
        sparseIntArray.append(6, 1);
        sparseIntArray.append(7, 2);
        sparseIntArray.append(8, 3);
        sparseIntArray.append(4, 4);
        sparseIntArray.append(5, 5);
        sparseIntArray.append(0, 6);
        sparseIntArray.append(1, 7);
        sparseIntArray.append(2, 8);
        sparseIntArray.append(3, 9);
        sparseIntArray.append(9, 10);
        sparseIntArray.append(10, 11);
        sparseIntArray.append(11, 12);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, r.f2804i);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            switch (f2777n.get(index)) {
                case 1:
                    this.f2778a = obtainStyledAttributes.getFloat(index, this.f2778a);
                    break;
                case 2:
                    this.f2779b = obtainStyledAttributes.getFloat(index, this.f2779b);
                    break;
                case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                    this.f2780c = obtainStyledAttributes.getFloat(index, this.f2780c);
                    break;
                case 4:
                    this.f2781d = obtainStyledAttributes.getFloat(index, this.f2781d);
                    break;
                case 5:
                    this.f2782e = obtainStyledAttributes.getFloat(index, this.f2782e);
                    break;
                case 6:
                    this.f2783f = obtainStyledAttributes.getDimension(index, this.f2783f);
                    break;
                case 7:
                    this.f2784g = obtainStyledAttributes.getDimension(index, this.f2784g);
                    break;
                case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                    this.f2785i = obtainStyledAttributes.getDimension(index, this.f2785i);
                    break;
                case 9:
                    this.f2786j = obtainStyledAttributes.getDimension(index, this.f2786j);
                    break;
                case 10:
                    this.f2787k = obtainStyledAttributes.getDimension(index, this.f2787k);
                    break;
                case 11:
                    this.f2788l = true;
                    this.f2789m = obtainStyledAttributes.getDimension(index, this.f2789m);
                    break;
                case FileClientSessionCache.MAX_SIZE /* 12 */:
                    this.h = n.e(obtainStyledAttributes, index, this.h);
                    break;
            }
        }
        obtainStyledAttributes.recycle();
    }
}

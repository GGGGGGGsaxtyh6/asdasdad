package v;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import org.conscrypt.ct.CTConstants;
import r.AbstractC0233a;

/* loaded from: classes.dex */
public final class k {

    /* renamed from: j, reason: collision with root package name */
    public static final SparseIntArray f2764j;

    /* renamed from: a, reason: collision with root package name */
    public int f2765a;

    /* renamed from: b, reason: collision with root package name */
    public int f2766b;

    /* renamed from: c, reason: collision with root package name */
    public int f2767c;

    /* renamed from: d, reason: collision with root package name */
    public float f2768d;

    /* renamed from: e, reason: collision with root package name */
    public float f2769e;

    /* renamed from: f, reason: collision with root package name */
    public float f2770f;

    /* renamed from: g, reason: collision with root package name */
    public int f2771g;
    public String h;

    /* renamed from: i, reason: collision with root package name */
    public int f2772i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f2764j = sparseIntArray;
        sparseIntArray.append(3, 1);
        sparseIntArray.append(5, 2);
        sparseIntArray.append(9, 3);
        sparseIntArray.append(2, 4);
        sparseIntArray.append(1, 5);
        sparseIntArray.append(0, 6);
        sparseIntArray.append(4, 7);
        sparseIntArray.append(8, 8);
        sparseIntArray.append(7, 9);
        sparseIntArray.append(6, 10);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, r.f2802f);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            switch (f2764j.get(index)) {
                case 1:
                    this.f2769e = obtainStyledAttributes.getFloat(index, this.f2769e);
                    break;
                case 2:
                    this.f2767c = obtainStyledAttributes.getInt(index, this.f2767c);
                    break;
                case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                    if (obtainStyledAttributes.peekValue(index).type == 3) {
                        obtainStyledAttributes.getString(index);
                        break;
                    } else {
                        String str = AbstractC0233a.f2231a[obtainStyledAttributes.getInteger(index, 0)];
                        break;
                    }
                case 4:
                    obtainStyledAttributes.getInt(index, 0);
                    break;
                case 5:
                    this.f2765a = n.e(obtainStyledAttributes, index, this.f2765a);
                    break;
                case 6:
                    this.f2766b = obtainStyledAttributes.getInteger(index, this.f2766b);
                    break;
                case 7:
                    this.f2768d = obtainStyledAttributes.getFloat(index, this.f2768d);
                    break;
                case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                    this.f2771g = obtainStyledAttributes.getInteger(index, this.f2771g);
                    break;
                case 9:
                    this.f2770f = obtainStyledAttributes.getFloat(index, this.f2770f);
                    break;
                case 10:
                    int i3 = obtainStyledAttributes.peekValue(index).type;
                    if (i3 == 1) {
                        this.f2772i = obtainStyledAttributes.getResourceId(index, -1);
                        break;
                    } else if (i3 == 3) {
                        String string = obtainStyledAttributes.getString(index);
                        this.h = string;
                        if (string.indexOf("/") > 0) {
                            this.f2772i = obtainStyledAttributes.getResourceId(index, -1);
                            break;
                        } else {
                            break;
                        }
                    } else {
                        obtainStyledAttributes.getInteger(index, this.f2772i);
                        break;
                    }
            }
        }
        obtainStyledAttributes.recycle();
    }
}

package O;

import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;

/* loaded from: classes.dex */
public final class f extends C0.d {

    /* renamed from: a, reason: collision with root package name */
    public final TextView f598a;

    /* renamed from: b, reason: collision with root package name */
    public final d f599b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f600c = true;

    public f(TextView textView) {
        this.f598a = textView;
        this.f599b = new d(textView);
    }

    @Override // C0.d
    public final void F(boolean z2) {
        if (z2) {
            I();
        }
    }

    @Override // C0.d
    public final void G(boolean z2) {
        this.f600c = z2;
        I();
        TextView textView = this.f598a;
        textView.setFilters(w(textView.getFilters()));
    }

    public final void I() {
        TextView textView = this.f598a;
        TransformationMethod transformationMethod = textView.getTransformationMethod();
        if (this.f600c) {
            if (!(transformationMethod instanceof j) && !(transformationMethod instanceof PasswordTransformationMethod)) {
                transformationMethod = new j(transformationMethod);
            }
        } else if (transformationMethod instanceof j) {
            transformationMethod = ((j) transformationMethod).f606a;
        }
        textView.setTransformationMethod(transformationMethod);
    }

    @Override // C0.d
    public final InputFilter[] w(InputFilter[] inputFilterArr) {
        if (!this.f600c) {
            SparseArray sparseArray = new SparseArray(1);
            for (int i2 = 0; i2 < inputFilterArr.length; i2++) {
                InputFilter inputFilter = inputFilterArr[i2];
                if (inputFilter instanceof d) {
                    sparseArray.put(i2, inputFilter);
                }
            }
            if (sparseArray.size() != 0) {
                int length = inputFilterArr.length;
                InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length - sparseArray.size()];
                int i3 = 0;
                for (int i4 = 0; i4 < length; i4++) {
                    if (sparseArray.indexOfKey(i4) < 0) {
                        inputFilterArr2[i3] = inputFilterArr[i4];
                        i3++;
                    }
                }
                return inputFilterArr2;
            }
            return inputFilterArr;
        }
        int length2 = inputFilterArr.length;
        int i5 = 0;
        while (true) {
            d dVar = this.f599b;
            if (i5 < length2) {
                if (inputFilterArr[i5] != dVar) {
                    i5++;
                } else {
                    return inputFilterArr;
                }
            } else {
                InputFilter[] inputFilterArr3 = new InputFilter[inputFilterArr.length + 1];
                System.arraycopy(inputFilterArr, 0, inputFilterArr3, 0, length2);
                inputFilterArr3[length2] = dVar;
                return inputFilterArr3;
            }
        }
    }
}

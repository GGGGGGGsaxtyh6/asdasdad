package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class c implements Parcelable {
    public static final Parcelable.Creator<c> CREATOR = new K.h(3);

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f1045a;

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f1046b;

    public c(Parcel parcel) {
        this.f1045a = parcel.createStringArrayList();
        this.f1046b = parcel.createTypedArrayList(C0053b.CREATOR);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeStringList(this.f1045a);
        parcel.writeTypedList(this.f1046b);
    }
}

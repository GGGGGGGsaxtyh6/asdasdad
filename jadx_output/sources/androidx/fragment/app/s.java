package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class s implements Parcelable {
    public static final Parcelable.Creator<s> CREATOR = new K.h(4);

    /* renamed from: a, reason: collision with root package name */
    public String f1074a;

    /* renamed from: b, reason: collision with root package name */
    public int f1075b;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f1074a);
        parcel.writeInt(this.f1075b);
    }
}

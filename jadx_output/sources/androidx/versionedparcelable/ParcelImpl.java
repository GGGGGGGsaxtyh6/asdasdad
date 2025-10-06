package androidx.versionedparcelable;

import K.h;
import Y.b;
import Y.c;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new h(1);

    /* renamed from: a, reason: collision with root package name */
    public final c f1215a;

    public ParcelImpl(Parcel parcel) {
        this.f1215a = new b(parcel).g();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        new b(parcel).i(this.f1215a);
    }
}

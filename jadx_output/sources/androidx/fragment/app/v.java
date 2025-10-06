package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class v implements Parcelable {
    public static final Parcelable.Creator<v> CREATOR = new K.h(5);

    /* renamed from: a, reason: collision with root package name */
    public ArrayList f1107a;

    /* renamed from: b, reason: collision with root package name */
    public ArrayList f1108b;

    /* renamed from: c, reason: collision with root package name */
    public C0053b[] f1109c;

    /* renamed from: d, reason: collision with root package name */
    public int f1110d;

    /* renamed from: e, reason: collision with root package name */
    public String f1111e;

    /* renamed from: f, reason: collision with root package name */
    public ArrayList f1112f;

    /* renamed from: g, reason: collision with root package name */
    public ArrayList f1113g;
    public ArrayList h;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeStringList(this.f1107a);
        parcel.writeStringList(this.f1108b);
        parcel.writeTypedArray(this.f1109c, i2);
        parcel.writeInt(this.f1110d);
        parcel.writeString(this.f1111e);
        parcel.writeStringList(this.f1112f);
        parcel.writeTypedList(this.f1113g);
        parcel.writeTypedList(this.h);
    }
}

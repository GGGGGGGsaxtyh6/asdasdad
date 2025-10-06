package k;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class c1 extends L.c {
    public static final Parcelable.Creator<c1> CREATOR = new L.b(1);

    /* renamed from: c, reason: collision with root package name */
    public int f1884c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f1885d;

    public c1(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        boolean z2;
        this.f1884c = parcel.readInt();
        if (parcel.readInt() != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f1885d = z2;
    }

    @Override // L.c, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.f1884c);
        parcel.writeInt(this.f1885d ? 1 : 0);
    }
}

package L;

import android.os.Parcel;
import android.os.Parcelable;
import k.c1;

/* loaded from: classes.dex */
public final class b implements Parcelable.ClassLoaderCreator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f515a;

    public /* synthetic */ b(int i2) {
        this.f515a = i2;
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        switch (this.f515a) {
            case 0:
                if (parcel.readParcelable(classLoader) == null) {
                    return c.f516b;
                }
                throw new IllegalStateException("superState must be null");
            default:
                return new c1(parcel, classLoader);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i2) {
        switch (this.f515a) {
            case 0:
                return new c[i2];
            default:
                return new c1[i2];
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f515a) {
            case 0:
                if (parcel.readParcelable(null) == null) {
                    return c.f516b;
                }
                throw new IllegalStateException("superState must be null");
            default:
                return new c1(parcel, null);
        }
    }
}

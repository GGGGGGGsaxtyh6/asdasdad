package k;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* loaded from: classes.dex */
public final class P extends View.BaseSavedState {
    public static final Parcelable.Creator<P> CREATOR = new K.h(8);

    /* renamed from: a, reason: collision with root package name */
    public boolean f1821a;

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeByte(this.f1821a ? (byte) 1 : (byte) 0);
    }
}

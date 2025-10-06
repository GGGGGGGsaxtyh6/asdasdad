package c;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import j0.AbstractC0150d;

/* renamed from: c.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0095a implements Parcelable {
    public static final Parcelable.Creator<C0095a> CREATOR = new K.h(7);

    /* renamed from: a, reason: collision with root package name */
    public final int f1237a;

    /* renamed from: b, reason: collision with root package name */
    public final Intent f1238b;

    public C0095a(Intent intent, int i2) {
        this.f1237a = i2;
        this.f1238b = intent;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("ActivityResult{resultCode=");
        int i2 = this.f1237a;
        if (i2 != -1) {
            if (i2 != 0) {
                str = String.valueOf(i2);
            } else {
                str = "RESULT_CANCELED";
            }
        } else {
            str = "RESULT_OK";
        }
        sb.append(str);
        sb.append(", data=");
        sb.append(this.f1238b);
        sb.append('}');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3;
        AbstractC0150d.e(parcel, "dest");
        parcel.writeInt(this.f1237a);
        Intent intent = this.f1238b;
        if (intent == null) {
            i3 = 0;
        } else {
            i3 = 1;
        }
        parcel.writeInt(i3);
        if (intent != null) {
            intent.writeToParcel(parcel, i2);
        }
    }
}

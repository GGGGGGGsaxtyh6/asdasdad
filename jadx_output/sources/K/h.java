package K;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import androidx.fragment.app.C0053b;
import androidx.fragment.app.s;
import androidx.fragment.app.v;
import androidx.fragment.app.x;
import androidx.versionedparcelable.ParcelImpl;
import c.C0095a;
import j0.AbstractC0150d;
import java.util.ArrayList;
import k.P;
import org.conscrypt.ct.CTConstants;

/* loaded from: classes.dex */
public final class h implements Parcelable.Creator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f507a;

    public /* synthetic */ h(int i2) {
        this.f507a = i2;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [android.view.View$BaseSavedState, K.i, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v10, types: [android.view.View$BaseSavedState, java.lang.Object, k.P] */
    /* JADX WARN: Type inference failed for: r0v5, types: [androidx.fragment.app.s, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Object, androidx.fragment.app.v] */
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        Intent intent;
        boolean z2;
        switch (this.f507a) {
            case 0:
                ?? baseSavedState = new View.BaseSavedState(parcel);
                baseSavedState.f508a = parcel.readInt();
                return baseSavedState;
            case 1:
                return new ParcelImpl(parcel);
            case 2:
                return new C0053b(parcel);
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                return new androidx.fragment.app.c(parcel);
            case 4:
                ?? obj = new Object();
                obj.f1074a = parcel.readString();
                obj.f1075b = parcel.readInt();
                return obj;
            case 5:
                ?? obj2 = new Object();
                obj2.f1111e = null;
                obj2.f1112f = new ArrayList();
                obj2.f1113g = new ArrayList();
                obj2.f1107a = parcel.createStringArrayList();
                obj2.f1108b = parcel.createStringArrayList();
                obj2.f1109c = (C0053b[]) parcel.createTypedArray(C0053b.CREATOR);
                obj2.f1110d = parcel.readInt();
                obj2.f1111e = parcel.readString();
                obj2.f1112f = parcel.createStringArrayList();
                obj2.f1113g = parcel.createTypedArrayList(androidx.fragment.app.c.CREATOR);
                obj2.h = parcel.createTypedArrayList(s.CREATOR);
                return obj2;
            case 6:
                return new x(parcel);
            case 7:
                AbstractC0150d.e(parcel, "parcel");
                int readInt = parcel.readInt();
                if (parcel.readInt() == 0) {
                    intent = null;
                } else {
                    intent = (Intent) Intent.CREATOR.createFromParcel(parcel);
                }
                return new C0095a(intent, readInt);
            default:
                ?? baseSavedState2 = new View.BaseSavedState(parcel);
                if (parcel.readByte() != 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                baseSavedState2.f1821a = z2;
                return baseSavedState2;
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i2) {
        switch (this.f507a) {
            case 0:
                return new i[i2];
            case 1:
                return new ParcelImpl[i2];
            case 2:
                return new C0053b[i2];
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                return new androidx.fragment.app.c[i2];
            case 4:
                return new s[i2];
            case 5:
                return new v[i2];
            case 6:
                return new x[i2];
            case 7:
                return new C0095a[i2];
            default:
                return new P[i2];
        }
    }
}

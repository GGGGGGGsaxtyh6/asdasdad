package androidx.core.app;

import Y.a;
import Y.b;
import Y.c;
import android.app.PendingIntent;
import android.os.Parcel;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;

/* loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        c cVar = remoteActionCompat.f970a;
        boolean z2 = true;
        if (aVar.e(1)) {
            cVar = aVar.g();
        }
        remoteActionCompat.f970a = (IconCompat) cVar;
        CharSequence charSequence = remoteActionCompat.f971b;
        if (aVar.e(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((b) aVar).f683e);
        }
        remoteActionCompat.f971b = charSequence;
        CharSequence charSequence2 = remoteActionCompat.f972c;
        if (aVar.e(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((b) aVar).f683e);
        }
        remoteActionCompat.f972c = charSequence2;
        remoteActionCompat.f973d = (PendingIntent) aVar.f(remoteActionCompat.f973d, 4);
        boolean z3 = remoteActionCompat.f974e;
        if (aVar.e(5)) {
            if (((b) aVar).f683e.readInt() != 0) {
                z3 = true;
            } else {
                z3 = false;
            }
        }
        remoteActionCompat.f974e = z3;
        boolean z4 = remoteActionCompat.f975f;
        if (aVar.e(6)) {
            if (((b) aVar).f683e.readInt() == 0) {
                z2 = false;
            }
            z4 = z2;
        }
        remoteActionCompat.f975f = z4;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, a aVar) {
        aVar.getClass();
        IconCompat iconCompat = remoteActionCompat.f970a;
        aVar.h(1);
        aVar.i(iconCompat);
        CharSequence charSequence = remoteActionCompat.f971b;
        aVar.h(2);
        Parcel parcel = ((b) aVar).f683e;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.f972c;
        aVar.h(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        PendingIntent pendingIntent = remoteActionCompat.f973d;
        aVar.h(4);
        parcel.writeParcelable(pendingIntent, 0);
        boolean z2 = remoteActionCompat.f974e;
        aVar.h(5);
        parcel.writeInt(z2 ? 1 : 0);
        boolean z3 = remoteActionCompat.f975f;
        aVar.h(6);
        parcel.writeInt(z3 ? 1 : 0);
    }
}

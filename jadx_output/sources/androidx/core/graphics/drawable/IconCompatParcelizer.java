package androidx.core.graphics.drawable;

import Y.a;
import Y.b;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.os.Parcelable;
import java.nio.charset.Charset;
import org.conscrypt.ct.CTConstants;

/* loaded from: classes.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x00ac. Please report as an issue. */
    public static IconCompat read(a aVar) {
        IconCompat iconCompat = new IconCompat();
        int i2 = iconCompat.f977a;
        if (aVar.e(1)) {
            i2 = ((b) aVar).f683e.readInt();
        }
        iconCompat.f977a = i2;
        byte[] bArr = iconCompat.f979c;
        if (aVar.e(2)) {
            Parcel parcel = ((b) aVar).f683e;
            int readInt = parcel.readInt();
            if (readInt < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[readInt];
                parcel.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.f979c = bArr;
        iconCompat.f980d = aVar.f(iconCompat.f980d, 3);
        int i3 = iconCompat.f981e;
        if (aVar.e(4)) {
            i3 = ((b) aVar).f683e.readInt();
        }
        iconCompat.f981e = i3;
        int i4 = iconCompat.f982f;
        if (aVar.e(5)) {
            i4 = ((b) aVar).f683e.readInt();
        }
        iconCompat.f982f = i4;
        iconCompat.f983g = (ColorStateList) aVar.f(iconCompat.f983g, 6);
        String str = iconCompat.f984i;
        if (aVar.e(7)) {
            str = ((b) aVar).f683e.readString();
        }
        iconCompat.f984i = str;
        String str2 = iconCompat.f985j;
        if (aVar.e(8)) {
            str2 = ((b) aVar).f683e.readString();
        }
        iconCompat.f985j = str2;
        iconCompat.h = PorterDuff.Mode.valueOf(iconCompat.f984i);
        switch (iconCompat.f977a) {
            case -1:
                Parcelable parcelable = iconCompat.f980d;
                if (parcelable != null) {
                    iconCompat.f978b = parcelable;
                    return iconCompat;
                }
                throw new IllegalArgumentException("Invalid icon");
            case 0:
            default:
                return iconCompat;
            case 1:
            case 5:
                Parcelable parcelable2 = iconCompat.f980d;
                if (parcelable2 != null) {
                    iconCompat.f978b = parcelable2;
                } else {
                    byte[] bArr3 = iconCompat.f979c;
                    iconCompat.f978b = bArr3;
                    iconCompat.f977a = 3;
                    iconCompat.f981e = 0;
                    iconCompat.f982f = bArr3.length;
                }
                return iconCompat;
            case 2:
            case 4:
            case 6:
                String str3 = new String(iconCompat.f979c, Charset.forName("UTF-16"));
                iconCompat.f978b = str3;
                if (iconCompat.f977a == 2 && iconCompat.f985j == null) {
                    iconCompat.f985j = str3.split(":", -1)[0];
                }
                return iconCompat;
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                iconCompat.f978b = iconCompat.f979c;
                return iconCompat;
        }
    }

    public static void write(IconCompat iconCompat, a aVar) {
        aVar.getClass();
        iconCompat.f984i = iconCompat.h.name();
        switch (iconCompat.f977a) {
            case -1:
                iconCompat.f980d = (Parcelable) iconCompat.f978b;
                break;
            case 1:
            case 5:
                iconCompat.f980d = (Parcelable) iconCompat.f978b;
                break;
            case 2:
                iconCompat.f979c = ((String) iconCompat.f978b).getBytes(Charset.forName("UTF-16"));
                break;
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                iconCompat.f979c = (byte[]) iconCompat.f978b;
                break;
            case 4:
            case 6:
                iconCompat.f979c = iconCompat.f978b.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i2 = iconCompat.f977a;
        if (-1 != i2) {
            aVar.h(1);
            ((b) aVar).f683e.writeInt(i2);
        }
        byte[] bArr = iconCompat.f979c;
        if (bArr != null) {
            aVar.h(2);
            int length = bArr.length;
            Parcel parcel = ((b) aVar).f683e;
            parcel.writeInt(length);
            parcel.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.f980d;
        if (parcelable != null) {
            aVar.h(3);
            ((b) aVar).f683e.writeParcelable(parcelable, 0);
        }
        int i3 = iconCompat.f981e;
        if (i3 != 0) {
            aVar.h(4);
            ((b) aVar).f683e.writeInt(i3);
        }
        int i4 = iconCompat.f982f;
        if (i4 != 0) {
            aVar.h(5);
            ((b) aVar).f683e.writeInt(i4);
        }
        ColorStateList colorStateList = iconCompat.f983g;
        if (colorStateList != null) {
            aVar.h(6);
            ((b) aVar).f683e.writeParcelable(colorStateList, 0);
        }
        String str = iconCompat.f984i;
        if (str != null) {
            aVar.h(7);
            ((b) aVar).f683e.writeString(str);
        }
        String str2 = iconCompat.f985j;
        if (str2 != null) {
            aVar.h(8);
            ((b) aVar).f683e.writeString(str2);
        }
    }
}

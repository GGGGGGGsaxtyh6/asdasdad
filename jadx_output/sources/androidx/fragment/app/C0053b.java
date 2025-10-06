package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

/* renamed from: androidx.fragment.app.b, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0053b implements Parcelable {
    public static final Parcelable.Creator<C0053b> CREATOR = new K.h(2);

    /* renamed from: a, reason: collision with root package name */
    public final int[] f1032a;

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f1033b;

    /* renamed from: c, reason: collision with root package name */
    public final int[] f1034c;

    /* renamed from: d, reason: collision with root package name */
    public final int[] f1035d;

    /* renamed from: e, reason: collision with root package name */
    public final int f1036e;

    /* renamed from: f, reason: collision with root package name */
    public final String f1037f;

    /* renamed from: g, reason: collision with root package name */
    public final int f1038g;
    public final int h;

    /* renamed from: i, reason: collision with root package name */
    public final CharSequence f1039i;

    /* renamed from: j, reason: collision with root package name */
    public final int f1040j;

    /* renamed from: k, reason: collision with root package name */
    public final CharSequence f1041k;

    /* renamed from: l, reason: collision with root package name */
    public final ArrayList f1042l;

    /* renamed from: m, reason: collision with root package name */
    public final ArrayList f1043m;

    /* renamed from: n, reason: collision with root package name */
    public final boolean f1044n;

    public C0053b(C0052a c0052a) {
        int size = c0052a.f1016a.size();
        this.f1032a = new int[size * 6];
        if (c0052a.f1022g) {
            this.f1033b = new ArrayList(size);
            this.f1034c = new int[size];
            this.f1035d = new int[size];
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                z zVar = (z) c0052a.f1016a.get(i3);
                this.f1032a[i2] = zVar.f1135a;
                this.f1033b.add(null);
                int[] iArr = this.f1032a;
                iArr[i2 + 1] = zVar.f1136b ? 1 : 0;
                iArr[i2 + 2] = zVar.f1137c;
                iArr[i2 + 3] = zVar.f1138d;
                int i4 = i2 + 5;
                iArr[i2 + 4] = zVar.f1139e;
                i2 += 6;
                iArr[i4] = zVar.f1140f;
                this.f1034c[i3] = zVar.f1141g.ordinal();
                this.f1035d[i3] = zVar.h.ordinal();
            }
            this.f1036e = c0052a.f1021f;
            this.f1037f = c0052a.h;
            this.f1038g = c0052a.f1031q;
            this.h = c0052a.f1023i;
            this.f1039i = c0052a.f1024j;
            this.f1040j = c0052a.f1025k;
            this.f1041k = c0052a.f1026l;
            this.f1042l = c0052a.f1027m;
            this.f1043m = c0052a.f1028n;
            this.f1044n = c0052a.f1029o;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeIntArray(this.f1032a);
        parcel.writeStringList(this.f1033b);
        parcel.writeIntArray(this.f1034c);
        parcel.writeIntArray(this.f1035d);
        parcel.writeInt(this.f1036e);
        parcel.writeString(this.f1037f);
        parcel.writeInt(this.f1038g);
        parcel.writeInt(this.h);
        TextUtils.writeToParcel(this.f1039i, parcel, 0);
        parcel.writeInt(this.f1040j);
        TextUtils.writeToParcel(this.f1041k, parcel, 0);
        parcel.writeStringList(this.f1042l);
        parcel.writeStringList(this.f1043m);
        parcel.writeInt(this.f1044n ? 1 : 0);
    }

    public C0053b(Parcel parcel) {
        this.f1032a = parcel.createIntArray();
        this.f1033b = parcel.createStringArrayList();
        this.f1034c = parcel.createIntArray();
        this.f1035d = parcel.createIntArray();
        this.f1036e = parcel.readInt();
        this.f1037f = parcel.readString();
        this.f1038g = parcel.readInt();
        this.h = parcel.readInt();
        Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.f1039i = (CharSequence) creator.createFromParcel(parcel);
        this.f1040j = parcel.readInt();
        this.f1041k = (CharSequence) creator.createFromParcel(parcel);
        this.f1042l = parcel.createStringArrayList();
        this.f1043m = parcel.createStringArrayList();
        this.f1044n = parcel.readInt() != 0;
    }
}

package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class x implements Parcelable {
    public static final Parcelable.Creator<x> CREATOR = new K.h(6);

    /* renamed from: a, reason: collision with root package name */
    public final String f1119a;

    /* renamed from: b, reason: collision with root package name */
    public final String f1120b;

    /* renamed from: c, reason: collision with root package name */
    public final boolean f1121c;

    /* renamed from: d, reason: collision with root package name */
    public final int f1122d;

    /* renamed from: e, reason: collision with root package name */
    public final int f1123e;

    /* renamed from: f, reason: collision with root package name */
    public final String f1124f;

    /* renamed from: g, reason: collision with root package name */
    public final boolean f1125g;
    public final boolean h;

    /* renamed from: i, reason: collision with root package name */
    public final boolean f1126i;

    /* renamed from: j, reason: collision with root package name */
    public final Bundle f1127j;

    /* renamed from: k, reason: collision with root package name */
    public final boolean f1128k;

    /* renamed from: l, reason: collision with root package name */
    public final int f1129l;

    /* renamed from: m, reason: collision with root package name */
    public final Bundle f1130m;

    public x(Parcel parcel) {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        this.f1119a = parcel.readString();
        this.f1120b = parcel.readString();
        if (parcel.readInt() != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f1121c = z2;
        this.f1122d = parcel.readInt();
        this.f1123e = parcel.readInt();
        this.f1124f = parcel.readString();
        if (parcel.readInt() != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.f1125g = z3;
        if (parcel.readInt() != 0) {
            z4 = true;
        } else {
            z4 = false;
        }
        this.h = z4;
        if (parcel.readInt() != 0) {
            z5 = true;
        } else {
            z5 = false;
        }
        this.f1126i = z5;
        this.f1127j = parcel.readBundle();
        this.f1128k = parcel.readInt() != 0;
        this.f1130m = parcel.readBundle();
        this.f1129l = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f1119a);
        sb.append(" (");
        sb.append(this.f1120b);
        sb.append(")}:");
        if (this.f1121c) {
            sb.append(" fromLayout");
        }
        int i2 = this.f1123e;
        if (i2 != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(i2));
        }
        String str = this.f1124f;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(str);
        }
        if (this.f1125g) {
            sb.append(" retainInstance");
        }
        if (this.h) {
            sb.append(" removing");
        }
        if (this.f1126i) {
            sb.append(" detached");
        }
        if (this.f1128k) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f1119a);
        parcel.writeString(this.f1120b);
        parcel.writeInt(this.f1121c ? 1 : 0);
        parcel.writeInt(this.f1122d);
        parcel.writeInt(this.f1123e);
        parcel.writeString(this.f1124f);
        parcel.writeInt(this.f1125g ? 1 : 0);
        parcel.writeInt(this.h ? 1 : 0);
        parcel.writeInt(this.f1126i ? 1 : 0);
        parcel.writeBundle(this.f1127j);
        parcel.writeInt(this.f1128k ? 1 : 0);
        parcel.writeBundle(this.f1130m);
        parcel.writeInt(this.f1129l);
    }
}

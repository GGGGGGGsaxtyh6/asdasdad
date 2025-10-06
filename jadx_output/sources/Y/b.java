package Y;

import android.os.Parcel;
import android.util.SparseIntArray;
import n.f;
import n.k;
import org.conscrypt.BuildConfig;

/* loaded from: classes.dex */
public final class b extends a {

    /* renamed from: d, reason: collision with root package name */
    public final SparseIntArray f682d;

    /* renamed from: e, reason: collision with root package name */
    public final Parcel f683e;

    /* renamed from: f, reason: collision with root package name */
    public final int f684f;

    /* renamed from: g, reason: collision with root package name */
    public final int f685g;
    public final String h;

    /* renamed from: i, reason: collision with root package name */
    public int f686i;

    /* renamed from: j, reason: collision with root package name */
    public int f687j;

    /* renamed from: k, reason: collision with root package name */
    public int f688k;

    /* JADX WARN: Type inference failed for: r5v0, types: [n.f, n.k] */
    /* JADX WARN: Type inference failed for: r6v0, types: [n.f, n.k] */
    /* JADX WARN: Type inference failed for: r7v0, types: [n.f, n.k] */
    public b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), BuildConfig.FLAVOR, new k(0), new k(0), new k(0));
    }

    @Override // Y.a
    public final b a() {
        Parcel parcel = this.f683e;
        int dataPosition = parcel.dataPosition();
        int i2 = this.f687j;
        if (i2 == this.f684f) {
            i2 = this.f685g;
        }
        return new b(parcel, dataPosition, i2, this.h + "  ", this.f679a, this.f680b, this.f681c);
    }

    @Override // Y.a
    public final boolean e(int i2) {
        while (this.f687j < this.f685g) {
            int i3 = this.f688k;
            if (i3 == i2) {
                return true;
            }
            if (String.valueOf(i3).compareTo(String.valueOf(i2)) > 0) {
                return false;
            }
            int i4 = this.f687j;
            Parcel parcel = this.f683e;
            parcel.setDataPosition(i4);
            int readInt = parcel.readInt();
            this.f688k = parcel.readInt();
            this.f687j += readInt;
        }
        if (this.f688k != i2) {
            return false;
        }
        return true;
    }

    @Override // Y.a
    public final void h(int i2) {
        int i3 = this.f686i;
        SparseIntArray sparseIntArray = this.f682d;
        Parcel parcel = this.f683e;
        if (i3 >= 0) {
            int i4 = sparseIntArray.get(i3);
            int dataPosition = parcel.dataPosition();
            parcel.setDataPosition(i4);
            parcel.writeInt(dataPosition - i4);
            parcel.setDataPosition(dataPosition);
        }
        this.f686i = i2;
        sparseIntArray.put(i2, parcel.dataPosition());
        parcel.writeInt(0);
        parcel.writeInt(i2);
    }

    public b(Parcel parcel, int i2, int i3, String str, f fVar, f fVar2, f fVar3) {
        super(fVar, fVar2, fVar3);
        this.f682d = new SparseIntArray();
        this.f686i = -1;
        this.f688k = -1;
        this.f683e = parcel;
        this.f684f = i2;
        this.f685g = i3;
        this.f687j = i2;
        this.h = str;
    }
}

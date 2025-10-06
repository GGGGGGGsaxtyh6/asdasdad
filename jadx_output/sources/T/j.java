package T;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Objects;

/* loaded from: classes.dex */
public final class j {

    /* renamed from: a, reason: collision with root package name */
    public final int f651a;

    /* renamed from: b, reason: collision with root package name */
    public final int f652b;

    /* renamed from: c, reason: collision with root package name */
    public final long f653c;

    /* renamed from: d, reason: collision with root package name */
    public final long f654d;

    public j(int i2, int i3, long j2, long j3) {
        this.f651a = i2;
        this.f652b = i3;
        this.f653c = j2;
        this.f654d = j3;
    }

    public static j a(File file) {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        try {
            j jVar = new j(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readLong());
            dataInputStream.close();
            return jVar;
        } catch (Throwable th) {
            try {
                dataInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final void b(File file) {
        file.delete();
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        try {
            dataOutputStream.writeInt(this.f651a);
            dataOutputStream.writeInt(this.f652b);
            dataOutputStream.writeLong(this.f653c);
            dataOutputStream.writeLong(this.f654d);
            dataOutputStream.close();
        } catch (Throwable th) {
            try {
                dataOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        if (this.f652b == jVar.f652b && this.f653c == jVar.f653c && this.f651a == jVar.f651a && this.f654d == jVar.f654d) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.f652b), Long.valueOf(this.f653c), Integer.valueOf(this.f651a), Long.valueOf(this.f654d));
    }
}

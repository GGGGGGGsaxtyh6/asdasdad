package M;

import H.D;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class y {

    /* renamed from: d, reason: collision with root package name */
    public static final ThreadLocal f578d = new ThreadLocal();

    /* renamed from: a, reason: collision with root package name */
    public final int f579a;

    /* renamed from: b, reason: collision with root package name */
    public final v f580b;

    /* renamed from: c, reason: collision with root package name */
    public volatile int f581c = 0;

    public y(v vVar, int i2) {
        this.f580b = vVar;
        this.f579a = i2;
    }

    public final int a(int i2) {
        N.a c2 = c();
        int a2 = c2.a(16);
        if (a2 != 0) {
            ByteBuffer byteBuffer = (ByteBuffer) c2.f319d;
            int i3 = a2 + c2.f316a;
            return byteBuffer.getInt((i2 * 4) + byteBuffer.getInt(i3) + i3 + 4);
        }
        return 0;
    }

    public final int b() {
        N.a c2 = c();
        int a2 = c2.a(16);
        if (a2 != 0) {
            int i2 = a2 + c2.f316a;
            return ((ByteBuffer) c2.f319d).getInt(((ByteBuffer) c2.f319d).getInt(i2) + i2);
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object, H.D] */
    public final N.a c() {
        ThreadLocal threadLocal = f578d;
        N.a aVar = (N.a) threadLocal.get();
        N.a aVar2 = aVar;
        if (aVar == null) {
            ?? d2 = new D();
            threadLocal.set(d2);
            aVar2 = d2;
        }
        N.b bVar = (N.b) this.f580b.f570a;
        int a2 = bVar.a(6);
        if (a2 != 0) {
            int i2 = a2 + bVar.f316a;
            int i3 = (this.f579a * 4) + ((ByteBuffer) bVar.f319d).getInt(i2) + i2 + 4;
            int i4 = ((ByteBuffer) bVar.f319d).getInt(i3) + i3;
            ByteBuffer byteBuffer = (ByteBuffer) bVar.f319d;
            aVar2.f319d = byteBuffer;
            if (byteBuffer != null) {
                aVar2.f316a = i4;
                int i5 = i4 - byteBuffer.getInt(i4);
                aVar2.f317b = i5;
                aVar2.f318c = ((ByteBuffer) aVar2.f319d).getShort(i5);
            } else {
                aVar2.f316a = 0;
                aVar2.f317b = 0;
                aVar2.f318c = 0;
            }
        }
        return aVar2;
    }

    public final String toString() {
        int i2;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id:");
        N.a c2 = c();
        int a2 = c2.a(4);
        if (a2 != 0) {
            i2 = ((ByteBuffer) c2.f319d).getInt(a2 + c2.f316a);
        } else {
            i2 = 0;
        }
        sb.append(Integer.toHexString(i2));
        sb.append(", codepoints:");
        int b2 = b();
        for (int i3 = 0; i3 < b2; i3++) {
            sb.append(Integer.toHexString(a(i3)));
            sb.append(" ");
        }
        return sb.toString();
    }
}

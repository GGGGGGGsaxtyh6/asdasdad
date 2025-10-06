package E;

import android.util.Base64;
import java.util.List;

/* loaded from: classes.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    public final String f278a;

    /* renamed from: b, reason: collision with root package name */
    public final String f279b;

    /* renamed from: c, reason: collision with root package name */
    public final String f280c;

    /* renamed from: d, reason: collision with root package name */
    public final List f281d;

    /* renamed from: e, reason: collision with root package name */
    public final String f282e;

    public g(String str, String str2, String str3, List list) {
        str.getClass();
        this.f278a = str;
        str2.getClass();
        this.f279b = str2;
        this.f280c = str3;
        list.getClass();
        this.f281d = list;
        this.f282e = str + "-" + str2 + "-" + str3;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f278a + ", mProviderPackage: " + this.f279b + ", mQuery: " + this.f280c + ", mCertificates:");
        int i2 = 0;
        while (true) {
            List list = this.f281d;
            if (i2 < list.size()) {
                sb.append(" [");
                List list2 = (List) list.get(i2);
                for (int i3 = 0; i3 < list2.size(); i3++) {
                    sb.append(" \"");
                    sb.append(Base64.encodeToString((byte[]) list2.get(i3), 0));
                    sb.append("\"");
                }
                sb.append(" ]");
                i2++;
            } else {
                sb.append("}mCertificatesArray: 0");
                return sb.toString();
            }
        }
    }
}
